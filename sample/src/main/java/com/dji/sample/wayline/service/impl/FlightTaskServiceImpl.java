package com.dji.sample.wayline.service.impl;

import com.dji.sample.common.error.CommonErrorEnum;
import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.component.mqtt.model.EventsReceiver;
import com.dji.sample.component.redis.RedisConst;
import com.dji.sample.component.redis.RedisOpsUtils;
import com.dji.sample.component.websocket.service.IWebSocketMessageService;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sample.media.model.MediaFileCountDTO;
import com.dji.sample.media.service.IMediaRedisService;
import com.dji.sample.wayline.model.dto.ConditionalWaylineJobKey;
import com.dji.sample.wayline.model.dto.WaylineJobDTO;
import com.dji.sample.wayline.model.dto.WaylineTaskConditionDTO;
import com.dji.sample.wayline.model.enums.WaylineErrorCodeEnum;
import com.dji.sample.wayline.model.enums.WaylineJobStatusEnum;
import com.dji.sample.wayline.model.param.CreateJobParam;
import com.dji.sample.wayline.model.param.UpdateJobParam;
import com.dji.sample.wayline.service.IFlightTaskService;
import com.dji.sample.wayline.service.IWaylineFileService;
import com.dji.sample.wayline.service.IWaylineJobService;
import com.dji.sample.wayline.service.IWaylineRedisService;
import com.dji.sdk.cloudapi.device.ExitWaylineWhenRcLostEnum;
import com.dji.sdk.cloudapi.media.UploadFlighttaskMediaPrioritize;
import com.dji.sdk.cloudapi.media.api.AbstractMediaService;
import com.dji.sdk.cloudapi.wayline.*;
import com.dji.sdk.cloudapi.wayline.api.AbstractWaylineService;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.SDKManager;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import com.dji.sdk.mqtt.events.TopicEventsResponse;
import com.dji.sdk.mqtt.services.ServicesReplyData;
import com.dji.sdk.mqtt.services.TopicServicesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * DJI Cloud API 비행 작업 서비스 구현 클래스
 * 
 * 이 클래스는 IFlightTaskService 인터페이스의 실제 구현체로, DJI Dock에 웨이라인 미션을 발행하고 관리합니다.
 * 웨이라인 작업의 실행, 취소, 상태 제어, 스케줄링 등의 기능을 제공하며,
 * Redis 캐시와 스케줄링을 통한 자동화된 작업 관리를 지원합니다.
 * 
 * 주요 기능:
 * - 웨이라인 미션을 Dock에 발행 및 실행
 * - 웨이라인 작업 취소 및 중단
 * - 웨이라인 작업 상태 수동 제어 (일시정지/재개)
 * - 예약 작업 스케줄링 및 자동 실행
 * - 조건부 작업 준비 및 실행
 * - 미디어 파일 업로드 우선순위 설정
 * - 실시간 작업 상태 모니터링
 * 
 * 상속 관계:
 * - AbstractWaylineService: DJI SDK의 기본 웨이라인 서비스 기능 상속
 * - IFlightTaskService: 비행 작업 서비스 인터페이스 구현
 * 
 * 스케줄링 기능:
 * - checkScheduledJob(): 예약 작업 자동 실행 (5초마다)
 * - prepareConditionJob(): 조건부 작업 준비 (5초마다)
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/9
 */
@Service
@Slf4j
public class FlightTaskServiceImpl extends AbstractWaylineService implements IFlightTaskService {

    /**
     * JSON 직렬화/역직렬화를 위한 ObjectMapper
     */
    @Autowired
    private ObjectMapper mapper;

    /**
     * WebSocket 메시지 서비스
     * 실시간으로 클라이언트에게 메시지를 전송하는 서비스
     */
    @Autowired
    private IWebSocketMessageService websocketMessageService;

    /**
     * 웨이라인 작업 서비스
     * 웨이라인 작업의 데이터베이스 관리를 담당하는 서비스
     */
    @Autowired
    private IWaylineJobService waylineJobService;

    /**
     * 디바이스 Redis 서비스
     * 디바이스의 온라인 상태와 정보를 관리하는 서비스
     */
    @Autowired
    private IDeviceRedisService deviceRedisService;

    /**
     * 웨이라인 Redis 서비스
     * 웨이라인 작업의 실시간 상태를 Redis에 캐싱하는 서비스
     */
    @Autowired
    private IWaylineRedisService waylineRedisService;

    /**
     * 미디어 Redis 서비스
     * 미디어 파일 카운트 정보를 관리하는 서비스
     */
    @Autowired
    private IMediaRedisService mediaRedisService;

    /**
     * 웨이라인 파일 서비스
     * 웨이라인 파일의 관리와 다운로드 URL 생성을 담당하는 서비스
     */
    @Autowired
    private IWaylineFileService waylineFileService;

    /**
     * SDK 웨이라인 서비스
     * DJI SDK를 통한 웨이라인 작업 처리를 담당하는 서비스
     */
    @Autowired
    private SDKWaylineService abstractWaylineService;

    /**
     * 미디어 서비스
     * 미디어 파일 업로드 및 관리를 담당하는 서비스
     */
    @Autowired
    @Qualifier("mediaServiceImpl")
    private AbstractMediaService abstractMediaService;

    /**
     * 예약 작업 스케줄링 체크
     * 
     * 이 메서드는 5초마다 실행되어 예약된 웨이라인 작업을 확인하고 자동으로 실행합니다.
     * Redis의 정렬된 집합(Sorted Set)을 사용하여 실행 시간이 도래한 작업을 찾고,
     * 만료된 작업은 자동으로 삭제하며, 실행 가능한 작업은 즉시 실행합니다.
     * 
     * 처리 과정:
     * 1. Redis에서 가장 가까운 실행 시간의 작업 조회
     * 2. 작업 실행 시간 확인 및 만료 작업 삭제
     * 3. 실행 가능한 작업 즉시 실행
     * 4. 실행 완료 후 Redis에서 작업 제거
     * 
     * Redis 키: WAYLINE_JOB_TIMED_EXECUTE
     * 값 형식: "{workspace_id}:{dock_sn}:{job_id}"
     * 점수: 실행 시간 (Unix timestamp)
     */
    @Scheduled(initialDelay = 10, fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void checkScheduledJob() {
        // Redis에서 가장 가까운 실행 시간의 작업 조회
        Object jobIdValue = RedisOpsUtils.zGetMin(RedisConst.WAYLINE_JOB_TIMED_EXECUTE);
        if (Objects.isNull(jobIdValue)) {
            return;
        }
        log.info("Check the timed tasks of the wayline. {}", jobIdValue);
        
        // 작업 정보 파싱: {workspace_id}:{dock_sn}:{job_id}
        String[] jobArr = String.valueOf(jobIdValue).split(RedisConst.DELIMITER);
        double time = RedisOpsUtils.zScore(RedisConst.WAYLINE_JOB_TIMED_EXECUTE, jobIdValue);
        long now = System.currentTimeMillis();
        int offset = 30_000; // 30초 오프셋

        // 만료된 작업은 즉시 삭제하고 실패 상태로 업데이트
        if (time < now - offset) {
            RedisOpsUtils.zRemove(RedisConst.WAYLINE_JOB_TIMED_EXECUTE, jobIdValue);
            waylineJobService.updateJob(WaylineJobDTO.builder()
                    .jobId(jobArr[2])
                    .status(WaylineJobStatusEnum.FAILED.getVal())
                    .executeTime(LocalDateTime.now())
                    .completedTime(LocalDateTime.now())
                    .code(HttpStatus.SC_REQUEST_TIMEOUT).build());
            return;
        }

        // 실행 가능한 작업 즉시 실행
        if (now <= time && time <= now + offset) {
            try {
                this.executeFlightTask(jobArr[0], jobArr[2]);
            } catch (Exception e) {
                log.info("The scheduled task delivery failed.");
                waylineJobService.updateJob(WaylineJobDTO.builder()
                        .jobId(jobArr[2])
                        .status(WaylineJobStatusEnum.FAILED.getVal())
                        .executeTime(LocalDateTime.now())
                        .completedTime(LocalDateTime.now())
                        .code(HttpStatus.SC_INTERNAL_SERVER_ERROR).build());
            } finally {
                RedisOpsUtils.zRemove(RedisConst.WAYLINE_JOB_TIMED_EXECUTE, jobIdValue);
            }
        }
    }

    /**
     * 조건부 작업 준비 스케줄링
     * 
     * 이 메서드는 5초마다 실행되어 조건부 웨이라인 작업을 준비합니다.
     * 실행 시간 하루 전부터 작업을 준비 상태로 설정하고,
     * 조건이 만족되면 자동으로 실행합니다.
     * 
     * 처리 과정:
     * 1. 가장 가까운 조건부 작업 조회
     * 2. 실행 시간 확인 (하루 전부터 준비)
     * 3. 작업 준비 및 실행 시도
     * 4. 실패 시 재시도 로직 처리
     * 
     * 조건부 작업: 특정 조건(배터리, 저장공간 등)이 만족될 때 실행되는 작업
     */
    @Scheduled(initialDelay = 10, fixedRate = 5, timeUnit = TimeUnit.SECONDS)
    public void prepareConditionJob() {
        // 가장 가까운 조건부 작업 조회
        Optional<ConditionalWaylineJobKey> jobKeyOpt = waylineRedisService.getNearestConditionalWaylineJob();
        if (jobKeyOpt.isEmpty()) {
            return;
        }
        ConditionalWaylineJobKey jobKey = jobKeyOpt.get();
        log.info("Check the conditional tasks of the wayline. {}", jobKey.toString());
        
        // 실행 시간 확인 (하루 전부터 준비)
        double time = waylineRedisService.getConditionalWaylineJobTime(jobKey);
        long now = System.currentTimeMillis();
        int offset = 86_400_000; // 24시간 (밀리초)

        if (now + offset < time) {
            return;
        }

        // 작업 준비 및 실행 시도
        WaylineJobDTO job = WaylineJobDTO.builder()
                .jobId(jobKey.getJobId())
                .status(WaylineJobStatusEnum.FAILED.getVal())
                .executeTime(LocalDateTime.now())
                .completedTime(LocalDateTime.now())
                .code(HttpStatus.SC_INTERNAL_SERVER_ERROR).build();
        try {
            // 조건부 작업 정보 조회
            Optional<WaylineJobDTO> waylineJobOpt = waylineRedisService.getConditionalWaylineJob(jobKey.getJobId());
            if (waylineJobOpt.isEmpty()) {
                job.setCode(CommonErrorEnum.REDIS_DATA_NOT_FOUND.getCode());
                waylineJobService.updateJob(job);
                waylineRedisService.removePrepareConditionalWaylineJob(jobKey);
                return;
            }
            WaylineJobDTO waylineJob = waylineJobOpt.get();

            // 작업 실행 시도
            HttpResultResponse result = this.publishOneFlightTask(waylineJob);
            waylineRedisService.removePrepareConditionalWaylineJob(jobKey);

            if (HttpResultResponse.CODE_SUCCESS == result.getCode()) {
                return;
            }

            // 종료 시간 초과 시 재시도하지 않음
            waylineRedisService.delConditionalWaylineJob(jobKey.getJobId());
            if (waylineJob.getEndTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - RedisConst.WAYLINE_JOB_BLOCK_TIME * 1000 < now) {
                return;
            }

            // 종료 시간이 초과되지 않았으면 재시도
            this.retryPrepareJob(jobKey, waylineJob);

        } catch (Exception e) {
            log.info("Failed to prepare the conditional task.");
            waylineJobService.updateJob(job);
        }
    }

    /**
     * 즉시 작업의 시간 정보를 채웁니다.
     * 
     * 즉시 실행 작업의 경우 서버 시간을 기준으로 작업 시간을 설정합니다.
     * 
     * @param param 웨이라인 작업 생성 파라미터
     */
    private void fillImmediateTime(CreateJobParam param) {
        if (TaskTypeEnum.IMMEDIATE != param.getTaskType()) {
            return;
        }
        long now = System.currentTimeMillis() / 1000;
        param.setTaskDays(List.of(now));
        param.setTaskPeriods(List.of(List.of(now)));
    }

    /**
     * 조건부 작업에 조건을 추가합니다.
     * 
     * 조건부 작업 타입인 경우 실행 조건과 준비 조건을 설정하고 Redis에 저장합니다.
     * 
     * @param waylineJob 웨이라인 작업 정보
     * @param param 작업 생성 파라미터
     * @param beginTime 작업 시작 시간
     * @param endTime 작업 종료 시간
     */
    private void addConditions(WaylineJobDTO waylineJob, CreateJobParam param, Long beginTime, Long endTime) {
        if (TaskTypeEnum.CONDITIONAL != param.getTaskType()) {
            return;
        }

        // 조건부 작업 조건 설정
        waylineJob.setConditions(
                WaylineTaskConditionDTO.builder()
                        .executableConditions(Objects.nonNull(param.getMinStorageCapacity()) ?
                                new ExecutableConditions().setStorageCapacity(param.getMinStorageCapacity()) : null)
                        .readyConditions(new ReadyConditions()
                                .setBatteryCapacity(param.getMinBatteryCapacity())
                                .setBeginTime(beginTime)
                                .setEndTime(endTime))
                        .build());

        // Redis에 조건부 작업 저장
        waylineRedisService.setConditionalWaylineJob(waylineJob);
        // 준비 조건부 작업으로 추가
        boolean isAdd = waylineRedisService.addPrepareConditionalWaylineJob(waylineJob);
        if (!isAdd) {
            throw new RuntimeException("Failed to create conditional job.");
        }
    }

    /**
     * 웨이라인 미션을 Dock에 발행합니다.
     * 
     * 이 메서드는 웨이라인 작업을 생성하고 Dock에 발행하는 메인 메서드입니다.
     * 즉시, 예약, 조건부 작업 타입에 따라 적절한 처리를 수행합니다.
     * 
     * 처리 과정:
     * 1. 즉시 작업의 시간 정보 설정
     * 2. 작업 일정에 따라 웨이라인 작업 생성
     * 3. 조건부 작업의 경우 조건 설정
     * 4. 각 작업을 Dock에 발행
     * 
     * @param param 웨이라인 작업 생성 파라미터
     * @param customClaim 사용자 정보 (권한, 워크스페이스 등)
     * @return HTTP 응답 결과
     * @throws SQLException 데이터베이스 오류 발생 시
     */
    @Override
    public HttpResultResponse publishFlightTask(CreateJobParam param, CustomClaim customClaim) throws SQLException {
        // 즉시 작업의 시간 정보 설정
        fillImmediateTime(param);

        // 작업 일정에 따라 웨이라인 작업 생성 및 발행
        for (Long taskDay : param.getTaskDays()) {
            LocalDate date = LocalDate.ofInstant(Instant.ofEpochSecond(taskDay), ZoneId.systemDefault());
            for (List<Long> taskPeriod : param.getTaskPeriods()) {
                // 작업 시작/종료 시간 계산
                long beginTime = LocalDateTime.of(date, LocalTime.ofInstant(Instant.ofEpochSecond(taskPeriod.get(0)), ZoneId.systemDefault()))
                        .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                long endTime = taskPeriod.size() > 1 ?
                        LocalDateTime.of(date, LocalTime.ofInstant(Instant.ofEpochSecond(taskPeriod.get(1)), ZoneId.systemDefault()))
                                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() : beginTime;
                
                // 과거 시간의 작업은 건너뛰기
                if (TaskTypeEnum.IMMEDIATE != param.getTaskType() && endTime < System.currentTimeMillis()) {
                    continue;
                }

                // 웨이라인 작업 생성
                Optional<WaylineJobDTO> waylineJobOpt = waylineJobService.createWaylineJob(param, customClaim.getWorkspaceId(), customClaim.getUsername(), beginTime, endTime);
                if (waylineJobOpt.isEmpty()) {
                    throw new SQLException("Failed to create wayline job.");
                }
                WaylineJobDTO waylineJob = waylineJobOpt.get();
                
                // 조건부 작업의 경우 조건 설정
                addConditions(waylineJob, param, beginTime, endTime);

                // 작업을 Dock에 발행
                HttpResultResponse response = this.publishOneFlightTask(waylineJob);
                if (HttpResultResponse.CODE_SUCCESS != response.getCode()) {
                    return response;
                }
            }
        }
        return HttpResultResponse.success();
    }

    /**
     * 단일 웨이라인 작업을 Dock에 발행합니다.
     * 
     * 이 메서드는 개별 웨이라인 작업을 Dock에 발행하는 핵심 메서드입니다.
     * 작업 타입에 따라 즉시 실행, 예약 실행, 조건부 실행을 처리합니다.
     * 
     * 처리 과정:
     * 1. Dock 온라인 상태 확인
     * 2. 웨이라인 작업 준비 (파일 다운로드 URL 생성 등)
     * 3. 작업 타입에 따른 실행 처리
     * 
     * @param waylineJob 발행할 웨이라인 작업 정보
     * @return HTTP 응답 결과
     * @throws SQLException 데이터베이스 오류 발생 시
     */
    public HttpResultResponse publishOneFlightTask(WaylineJobDTO waylineJob) throws SQLException {
        // Dock 온라인 상태 확인
        boolean isOnline = deviceRedisService.checkDeviceOnline(waylineJob.getDockSn());
        if (!isOnline) {
            throw new RuntimeException("Dock is offline.");
        }

        // 웨이라인 작업 준비
        boolean isSuccess = this.prepareFlightTask(waylineJob);
        if (!isSuccess) {
            return HttpResultResponse.error("Failed to prepare job.");
        }

        // 즉시 작업 실행
        if (TaskTypeEnum.IMMEDIATE == waylineJob.getTaskType()) {
            if (!executeFlightTask(waylineJob.getWorkspaceId(), waylineJob.getJobId())) {
                return HttpResultResponse.error("Failed to execute job.");
            }
        }

        // 예약 작업 스케줄링
        if (TaskTypeEnum.TIMED == waylineJob.getTaskType()) {
            // Redis에 예약 작업 추가: {workspace_id}:{dock_sn}:{job_id}
            boolean isAdd = RedisOpsUtils.zAdd(RedisConst.WAYLINE_JOB_TIMED_EXECUTE,
                    waylineJob.getWorkspaceId() + RedisConst.DELIMITER + waylineJob.getDockSn() + RedisConst.DELIMITER + waylineJob.getJobId(),
                    waylineJob.getBeginTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            if (!isAdd) {
                return HttpResultResponse.error("Failed to create scheduled job.");
            }
        }

        return HttpResultResponse.success();
    }

    /**
     * 웨이라인 작업을 준비합니다.
     * 
     * 이 메서드는 웨이라인 작업 실행을 위한 사전 준비를 수행합니다.
     * 웨이라인 파일 다운로드 URL을 생성하고 Dock에 작업 준비 명령을 전송합니다.
     * 
     * 처리 과정:
     * 1. 웨이라인 파일 정보 조회
     * 2. 웨이라인 파일 다운로드 URL 생성
     * 3. 작업 준비 요청 생성
     * 4. Dock에 작업 준비 명령 전송
     * 
     * @param waylineJob 준비할 웨이라인 작업 정보
     * @return 준비 성공 여부
     * @throws SQLException 데이터베이스 오류 발생 시
     */
    private Boolean prepareFlightTask(WaylineJobDTO waylineJob) throws SQLException {
        // 웨이라인 파일 정보 조회
        Optional<GetWaylineListResponse> waylineFile = waylineFileService.getWaylineByWaylineId(waylineJob.getWorkspaceId(), waylineJob.getFileId());
        if (waylineFile.isEmpty()) {
            throw new SQLException("Wayline file doesn't exist.");
        }

        // 웨이라인 파일 다운로드 URL 생성
        URL url = waylineFileService.getObjectUrl(waylineJob.getWorkspaceId(), waylineFile.get().getId());

        // 작업 준비 요청 생성
        FlighttaskPrepareRequest flightTask = new FlighttaskPrepareRequest()
                .setFlightId(waylineJob.getJobId())
                .setExecuteTime(waylineJob.getBeginTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .setTaskType(waylineJob.getTaskType())
                .setWaylineType(waylineJob.getWaylineType())
                .setRthAltitude(waylineJob.getRthAltitude())
                .setOutOfControlAction(waylineJob.getOutOfControlAction())
                .setExitWaylineWhenRcLost(ExitWaylineWhenRcLostEnum.EXECUTE_RC_LOST_ACTION)
                .setFile(new FlighttaskFile()
                        .setUrl(url.toString())
                        .setFingerprint(waylineFile.get().getSign()));

        // 조건부 작업의 경우 조건 설정
        if (TaskTypeEnum.CONDITIONAL == waylineJob.getTaskType()) {
            if (Objects.isNull(waylineJob.getConditions())) {
                throw new IllegalArgumentException();
            }
            flightTask.setReadyConditions(waylineJob.getConditions().getReadyConditions());
            flightTask.setExecutableConditions(waylineJob.getConditions().getExecutableConditions());
        }

        // Dock에 작업 준비 명령 전송
        TopicServicesResponse<ServicesReplyData> serviceReply = abstractWaylineService.flighttaskPrepare(
                SDKManager.getDeviceSDK(waylineJob.getDockSn()), flightTask);
        if (!serviceReply.getData().getResult().isSuccess()) {
            log.info("Prepare task ====> Error code: {}", serviceReply.getData().getResult());
            waylineJobService.updateJob(WaylineJobDTO.builder()
                    .workspaceId(waylineJob.getWorkspaceId())
                    .jobId(waylineJob.getJobId())
                    .executeTime(LocalDateTime.now())
                    .status(WaylineJobStatusEnum.FAILED.getVal())
                    .completedTime(LocalDateTime.now())
                    .code(serviceReply.getData().getResult().getCode()).build());
            return false;
        }
        return true;
    }

    /**
     * 웨이라인 작업을 즉시 실행합니다.
     * 
     * 이 메서드는 준비된 웨이라인 작업을 즉시 실행합니다.
     * Dock에 작업 실행 명령을 전송하고 작업 상태를 업데이트합니다.
     * 
     * 처리 과정:
     * 1. 웨이라인 작업 정보 조회
     * 2. Dock 온라인 상태 확인
     * 3. Dock에 작업 실행 명령 전송
     * 4. 작업 상태 업데이트 및 Redis 캐싱
     * 
     * @param workspaceId 워크스페이스 ID
     * @param jobId 실행할 작업 ID
     * @return 실행 성공 여부
     */
    @Override
    public Boolean executeFlightTask(String workspaceId, String jobId) {
        // 웨이라인 작업 정보 조회
        Optional<WaylineJobDTO> waylineJob = waylineJobService.getJobByJobId(workspaceId, jobId);
        if (waylineJob.isEmpty()) {
            throw new IllegalArgumentException("Job doesn't exist.");
        }

        // Dock 온라인 상태 확인
        boolean isOnline = deviceRedisService.checkDeviceOnline(waylineJob.get().getDockSn());
        if (!isOnline) {
            throw new RuntimeException("Dock is offline.");
        }

        WaylineJobDTO job = waylineJob.get();

        // Dock에 작업 실행 명령 전송
        TopicServicesResponse<ServicesReplyData> serviceReply = abstractWaylineService.flighttaskExecute(
                SDKManager.getDeviceSDK(job.getDockSn()), new FlighttaskExecuteRequest().setFlightId(jobId));
        if (!serviceReply.getData().getResult().isSuccess()) {
            log.info("Execute job ====> Error: {}", serviceReply.getData().getResult());
            waylineJobService.updateJob(WaylineJobDTO.builder()
                    .jobId(jobId)
                    .executeTime(LocalDateTime.now())
                    .status(WaylineJobStatusEnum.FAILED.getVal())
                    .completedTime(LocalDateTime.now())
                    .code(serviceReply.getData().getResult().getCode()).build());
            
            // 조건부 작업 실패 시 차단 상태로 설정
            if (TaskTypeEnum.CONDITIONAL == job.getTaskType()
                    && WaylineErrorCodeEnum.find(serviceReply.getData().getResult().getCode()).isBlock()) {
                waylineRedisService.setBlockedWaylineJob(job.getDockSn(), jobId);
            }
            return false;
        }

        // 작업 상태를 실행 중으로 업데이트
        waylineJobService.updateJob(WaylineJobDTO.builder()
                .jobId(jobId)
                .executeTime(LocalDateTime.now())
                .status(WaylineJobStatusEnum.IN_PROGRESS.getVal())
                .build());
        
        // Redis에 실행 중인 작업 상태 캐싱
        waylineRedisService.setRunningWaylineJob(job.getDockSn(), EventsReceiver.<FlighttaskProgress>builder().bid(jobId).sn(job.getDockSn()).build());
        return true;
    }

    /**
     * 웨이라인 작업을 취소합니다.
     * 
     * 이 메서드는 대기 중인 웨이라인 작업들을 취소합니다.
     * 작업 상태를 확인하고 Dock별로 그룹화하여 취소 명령을 전송합니다.
     * 
     * 처리 과정:
     * 1. 대기 중인 작업들 조회
     * 2. 작업 상태 유효성 검증
     * 3. Dock별로 작업 그룹화
     * 4. 각 Dock에 취소 명령 전송
     * 
     * @param workspaceId 워크스페이스 ID
     * @param jobIds 취소할 작업 ID 목록
     */
    @Override
    public void cancelFlightTask(String workspaceId, Collection<String> jobIds) {
        // 대기 중인 작업들 조회
        List<WaylineJobDTO> waylineJobs = waylineJobService.getJobsByConditions(workspaceId, jobIds, WaylineJobStatusEnum.PENDING);

        Set<String> waylineJobIds = waylineJobs.stream().map(WaylineJobDTO::getJobId).collect(Collectors.toSet());
        // 작업 상태 유효성 검증
        boolean isErr = !jobIds.removeAll(waylineJobIds) || !jobIds.isEmpty() ;
        if (isErr) {
            throw new IllegalArgumentException("These tasks have an incorrect status and cannot be canceled. " + Arrays.toString(jobIds.toArray()));
        }

        // Dock별로 작업 그룹화
        Map<String, List<String>> dockJobs = waylineJobs.stream()
                .collect(Collectors.groupingBy(WaylineJobDTO::getDockSn,
                        Collectors.mapping(WaylineJobDTO::getJobId, Collectors.toList())));
        
        // 각 Dock에 취소 명령 전송
        dockJobs.forEach((dockSn, idList) -> this.publishCancelTask(workspaceId, dockSn, idList));
    }

    /**
     * Dock의 웨이라인 작업들을 취소합니다.
     * 
     * 이 메서드는 특정 Dock의 웨이라인 작업들을 취소합니다.
     * Dock에 취소 명령을 전송하고 작업 상태를 업데이트합니다.
     * 
     * 처리 과정:
     * 1. Dock 온라인 상태 확인
     * 2. Dock에 취소 명령 전송
     * 3. 작업 상태를 취소로 업데이트
     * 4. Redis에서 예약 작업 제거
     * 
     * @param workspaceId 워크스페이스 ID
     * @param dockSn Dock 시리얼 번호
     * @param jobIds 취소할 작업 ID 목록
     */
    public void publishCancelTask(String workspaceId, String dockSn, List<String> jobIds) {
        // Dock 온라인 상태 확인
        boolean isOnline = deviceRedisService.checkDeviceOnline(dockSn);
        if (!isOnline) {
            throw new RuntimeException("Dock is offline.");
        }

        // Dock에 취소 명령 전송
        TopicServicesResponse<ServicesReplyData> serviceReply = abstractWaylineService.flighttaskUndo(SDKManager.getDeviceSDK(dockSn),
                new FlighttaskUndoRequest().setFlightIds(jobIds));
        if (!serviceReply.getData().getResult().isSuccess()) {
            log.info("Cancel job ====> Error: {}", serviceReply.getData().getResult());
            throw new RuntimeException("Failed to cancel the wayline job of " + dockSn);
        }

        // 작업 상태를 취소로 업데이트
        for (String jobId : jobIds) {
            waylineJobService.updateJob(WaylineJobDTO.builder()
                    .workspaceId(workspaceId)
                    .jobId(jobId)
                    .status(WaylineJobStatusEnum.CANCEL.getVal())
                    .completedTime(LocalDateTime.now())
                    .build());
            // Redis에서 예약 작업 제거
            RedisOpsUtils.zRemove(RedisConst.WAYLINE_JOB_TIMED_EXECUTE, workspaceId + RedisConst.DELIMITER + dockSn + RedisConst.DELIMITER + jobId);
        }
    }

    /**
     * 웨이라인 작업의 미디어 파일을 즉시 업로드하도록 우선순위를 설정합니다.
     * 
     * 이 메서드는 특정 웨이라인 작업의 미디어 파일을 우선적으로 업로드하도록 설정합니다.
     * 
     * 처리 과정:
     * 1. 웨이라인 작업 정보 조회
     * 2. 이미 우선순위가 설정되어 있는지 확인
     * 3. Dock에 미디어 업로드 우선순위 설정 명령 전송
     * 
     * @param workspaceId 워크스페이스 ID
     * @param jobId 미디어 업로드 우선순위를 설정할 작업 ID
     */
    @Override
    public void uploadMediaHighestPriority(String workspaceId, String jobId) {
        // 웨이라인 작업 정보 조회
        Optional<WaylineJobDTO> jobOpt = waylineJobService.getJobByJobId(workspaceId, jobId);
        if (jobOpt.isEmpty()) {
            throw new RuntimeException(CommonErrorEnum.ILLEGAL_ARGUMENT.getMessage());
        }

        String dockSn = jobOpt.get().getDockSn();
        String key = RedisConst.MEDIA_HIGHEST_PRIORITY_PREFIX + dockSn;
        
        // 이미 우선순위가 설정되어 있는지 확인
        if (RedisOpsUtils.checkExist(key) && jobId.equals(((MediaFileCountDTO) RedisOpsUtils.get(key)).getJobId())) {
            return;
        }

        // Dock에 미디어 업로드 우선순위 설정 명령 전송
        TopicServicesResponse<ServicesReplyData> reply = abstractMediaService.uploadFlighttaskMediaPrioritize(
                SDKManager.getDeviceSDK(dockSn), new UploadFlighttaskMediaPrioritize().setFlightId(jobId));
        if (!reply.getData().getResult().isSuccess()) {
            throw new RuntimeException("Failed to set media job upload priority. Error: " + reply.getData().getResult());
        }
    }

    /**
     * 웨이라인 작업의 실행 상태를 수동으로 제어합니다.
     * 
     * 이 메서드는 실행 중인 웨이라인 작업을 일시정지하거나 재개합니다.
     * 
     * 처리 과정:
     * 1. 웨이라인 작업 정보 조회
     * 2. 작업 상태 유효성 검증
     * 3. 요청된 상태에 따라 일시정지 또는 재개 처리
     * 
     * @param workspaceId 워크스페이스 ID
     * @param jobId 제어할 작업 ID
     * @param param 작업 상태 업데이트 파라미터 (PAUSE, RESUME 등)
     */
    @Override
    public void updateJobStatus(String workspaceId, String jobId, UpdateJobParam param) {
        // 웨이라인 작업 정보 조회
        Optional<WaylineJobDTO> waylineJobOpt = waylineJobService.getJobByJobId(workspaceId, jobId);
        if (waylineJobOpt.isEmpty()) {
            throw new RuntimeException("The job does not exist.");
        }
        WaylineJobDTO waylineJob = waylineJobOpt.get();
        
        // 작업 상태 유효성 검증
        WaylineJobStatusEnum statusEnum = waylineJobService.getWaylineState(waylineJob.getDockSn());
        if (statusEnum.getEnd() || WaylineJobStatusEnum.PENDING == statusEnum) {
            throw new RuntimeException("The wayline job status does not match, and the operation cannot be performed.");
        }

        // 요청된 상태에 따라 처리
        switch (param.getStatus()) {
            case PAUSE:
                pauseJob(workspaceId, waylineJob.getDockSn(), jobId, statusEnum);
                break;
            case RESUME:
                resumeJob(workspaceId, waylineJob.getDockSn(), jobId, statusEnum);
                break;
        }
    }

    /**
     * 웨이라인 작업을 일시정지합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param dockSn Dock 시리얼 번호
     * @param jobId 일시정지할 작업 ID
     * @param statusEnum 현재 작업 상태
     */
    private void pauseJob(String workspaceId, String dockSn, String jobId, WaylineJobStatusEnum statusEnum) {
        // 이미 일시정지된 상태인지 확인
        if (WaylineJobStatusEnum.PAUSED == statusEnum && jobId.equals(waylineRedisService.getPausedWaylineJobId(dockSn))) {
            waylineRedisService.setPausedWaylineJob(dockSn, jobId);
            return;
        }

        // Dock에 일시정지 명령 전송
        TopicServicesResponse<ServicesReplyData> reply = abstractWaylineService.flighttaskPause(SDKManager.getDeviceSDK(dockSn));
        if (!reply.getData().getResult().isSuccess()) {
            throw new RuntimeException("Failed to pause wayline job. Error: " + reply.getData().getResult());
        }
        
        // Redis 상태 업데이트
        waylineRedisService.delRunningWaylineJob(dockSn);
        waylineRedisService.setPausedWaylineJob(dockSn, jobId);
    }

    /**
     * 웨이라인 작업을 재개합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param dockSn Dock 시리얼 번호
     * @param jobId 재개할 작업 ID
     * @param statusEnum 현재 작업 상태
     */
    private void resumeJob(String workspaceId, String dockSn, String jobId, WaylineJobStatusEnum statusEnum) {
        // 이미 실행 중인 상태인지 확인
        Optional<EventsReceiver<FlighttaskProgress>> runningDataOpt = waylineRedisService.getRunningWaylineJob(dockSn);
        if (WaylineJobStatusEnum.IN_PROGRESS == statusEnum && jobId.equals(runningDataOpt.map(EventsReceiver::getSn).get())) {
            waylineRedisService.setRunningWaylineJob(dockSn, runningDataOpt.get());
            return;
        }
        
        // Dock에 재개 명령 전송
        TopicServicesResponse<ServicesReplyData> reply = abstractWaylineService.flighttaskRecovery(SDKManager.getDeviceSDK(dockSn));
        if (!reply.getData().getResult().isSuccess()) {
            throw new RuntimeException("Failed to resume wayline job. Error: " + reply.getData().getResult());
        }

        // Redis 상태 업데이트
        runningDataOpt.ifPresent(runningData -> waylineRedisService.setRunningWaylineJob(dockSn, runningData));
        waylineRedisService.delPausedWaylineJob(dockSn);
    }

    /**
     * 조건부 웨이라인 작업을 재시도합니다.
     * 
     * 이 메서드는 조건부 웨이라인 작업이 실패했을 때 재시도를 위한 새로운 작업을 생성합니다.
     * 
     * 처리 과정:
     * 1. 부모 작업을 기반으로 하위 작업 생성
     * 2. 새로운 실행 시간 설정 (차단 시간 후)
     * 3. Redis에 준비 조건부 작업 추가
     * 4. 조건부 작업 정보 업데이트
     * 
     * @param jobKey 조건부 웨이라인 작업 키
     * @param waylineJob 재시도할 웨이라인 작업 정보
     */
    @Override
    public void retryPrepareJob(ConditionalWaylineJobKey jobKey, WaylineJobDTO waylineJob) {
        // 부모 작업을 기반으로 하위 작업 생성
        Optional<WaylineJobDTO> childJobOpt = waylineJobService.createWaylineJobByParent(jobKey.getWorkspaceId(), jobKey.getJobId());
        if (childJobOpt.isEmpty()) {
            log.error("Failed to create wayline job.");
            return;
        }

        WaylineJobDTO newJob = childJobOpt.get();
        // 새로운 실행 시간 설정 (차단 시간 후)
        newJob.setBeginTime(LocalDateTime.now().plusSeconds(RedisConst.WAYLINE_JOB_BLOCK_TIME));
        
        // Redis에 준비 조건부 작업 추가
        boolean isAdd = waylineRedisService.addPrepareConditionalWaylineJob(newJob);
        if (!isAdd) {
            log.error("Failed to create wayline job. {}", newJob.getJobId());
            return;
        }

        // 조건부 작업 정보 업데이트
        waylineJob.setJobId(newJob.getJobId());
        waylineRedisService.setConditionalWaylineJob(waylineJob);
    }

    /**
     * 웨이라인 작업 준비 완료 이벤트 처리
     * 
     * 이 메서드는 DJI Dock에서 웨이라인 작업 준비가 완료되었을 때 호출됩니다.
     * 조건부 작업의 차단 상태를 확인하고 실행 가능한 작업을 실행합니다.
     * 
     * 처리 과정:
     * 1. 준비 완료된 작업 목록 확인
     * 2. 조건부 작업 차단 상태 확인
     * 3. 실행 가능한 작업 실행
     * 4. 조건부 작업 재시도 준비
     * 
     * @param response 웨이라인 작업 준비 완료 이벤트 요청
     * @param headers MQTT 메시지 헤더
     * @return MQTT 이벤트 응답
     */
    @Override
    public TopicEventsResponse<MqttReply> flighttaskReady(TopicEventsRequest<FlighttaskReady> response, MessageHeaders headers) {
        List<String> flightIds = response.getData().getFlightIds();

        log.info("ready task list：{}", Arrays.toString(flightIds.toArray()) );
        
        // 조건부 작업 차단 상태 확인
        String blockedId = waylineRedisService.getBlockedWaylineJobId(response.getGateway());
        if (!StringUtils.hasText(blockedId)) {
            return null;
        }

        // 디바이스 온라인 상태 확인
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(response.getGateway());
        if (deviceOpt.isEmpty()) {
            return null;
        }
        DeviceDTO device = deviceOpt.get();

        try {
            // 실행 가능한 작업 실행
            for (String jobId : flightIds) {
                boolean isExecute = this.executeFlightTask(device.getWorkspaceId(), jobId);
                if (!isExecute) {
                    return null;
                }
                
                // 조건부 작업 정보 확인
                Optional<WaylineJobDTO> waylineJobOpt = waylineRedisService.getConditionalWaylineJob(jobId);
                if (waylineJobOpt.isEmpty()) {
                    log.info("The conditional job has expired and will no longer be executed.");
                    return new TopicEventsResponse<>();
                }
                
                // 조건부 작업 재시도 준비
                WaylineJobDTO waylineJob = waylineJobOpt.get();
                this.retryPrepareJob(new ConditionalWaylineJobKey(device.getWorkspaceId(), response.getGateway(), jobId), waylineJob);
                return new TopicEventsResponse<>();
            }
        } catch (Exception e) {
            log.error("Failed to execute conditional task.");
            e.printStackTrace();
        }
        return new TopicEventsResponse<>();
    }
}
