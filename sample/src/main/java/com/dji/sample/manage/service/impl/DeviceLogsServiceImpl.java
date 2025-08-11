package com.dji.sample.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dji.sample.component.mqtt.model.EventsReceiver;
import com.dji.sample.component.redis.RedisConst;
import com.dji.sample.component.redis.RedisOpsUtils;
import com.dji.sample.component.websocket.model.BizCodeEnum;
import com.dji.sample.component.websocket.service.IWebSocketMessageService;
import com.dji.sample.manage.dao.IDeviceLogsMapper;
import com.dji.sample.manage.model.dto.*;
import com.dji.sample.manage.model.entity.DeviceLogsEntity;
import com.dji.sample.manage.model.enums.DeviceLogsStatusEnum;
import com.dji.sample.manage.model.enums.UserTypeEnum;
import com.dji.sample.manage.model.param.DeviceLogsCreateParam;
import com.dji.sample.manage.model.param.DeviceLogsQueryParam;
import com.dji.sample.manage.service.IDeviceLogsService;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sample.manage.service.ILogsFileService;
import com.dji.sample.manage.service.ITopologyService;
import com.dji.sample.storage.service.IStorageService;
import com.dji.sdk.cloudapi.log.*;
import com.dji.sdk.cloudapi.log.api.AbstractLogService;
import com.dji.sdk.cloudapi.storage.StsCredentialsResponse;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.Pagination;
import com.dji.sdk.common.PaginationData;
import com.dji.sdk.common.SDKManager;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.events.EventsDataRequest;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import com.dji.sdk.mqtt.events.TopicEventsResponse;
import com.dji.sdk.mqtt.services.ServicesReplyData;
import com.dji.sdk.mqtt.services.TopicServicesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 디바이스 로그 관리 서비스 구현체
 * 
 * DJI Cloud API의 디바이스 로그 관리를 위한 서비스 구현체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 구현합니다:
 * 
 * 1. 로그 파일 업로드 관리
 *    - 로그 파일 업로드 시작 및 진행률 모니터링
 *    - STS 자격 증명을 통한 안전한 파일 업로드
 *    - 업로드 진행률 실시간 추적
 *    - 업로드 완료/실패 상태 관리
 * 
 * 2. 로그 데이터 관리
 *    - 업로드된 로그 목록 조회 및 검색
 *    - 로그 파일 정보 저장 및 관리
 *    - 로그 상태 추적 및 업데이트
 *    - 로그 파일 다운로드 URL 생성
 * 
 * 3. 실시간 로그 모니터링
 *    - MQTT를 통한 로그 업로드 진행률 수신
 *    - WebSocket을 통한 실시간 상태 전송
 *    - 로그 업로드 완료/실패 처리
 *    - 디바이스별 로그 상태 관리
 * 
 * 4. 로그 파일 처리
 *    - 로그 파일 메타데이터 관리
 *    - 로그 파일 삭제 및 정리
 *    - 로그 파일 인덱싱 및 검색
 *    - 로그 파일 접근 권한 관리
 * 
 * 5. 데이터베이스 관리
 *    - 로그 정보 CRUD 작업
 *    - 로그 파일 정보 관리
 *    - 로그 상태 추적
 *    - 트랜잭션 관리
 * 
 * 주요 의존성:
 * - IDeviceLogsMapper: 로그 데이터베이스 접근
 * - ILogsFileService: 로그 파일 관리
 * - IStorageService: 스토리지 서비스
 * - IWebSocketMessageService: WebSocket 메시지 전송
 * - IDeviceRedisService: Redis 캐시 관리
 * - AbstractLogService: DJI 로그 서비스
 * 
 * 이 클래스는 DJI 디바이스의 로그 파일을
 * 안전하고 효율적으로 관리하는 서비스입니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
@Service
@Transactional
@Slf4j
public class DeviceLogsServiceImpl extends AbstractLogService implements IDeviceLogsService {

    private static final String LOGS_FILE_SUFFIX = ".tar";

    @Autowired
    private IDeviceLogsMapper mapper;

    @Autowired
    private ITopologyService topologyService;

    @Autowired
    private ILogsFileService logsFileService;

    @Autowired
    private IStorageService storageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IWebSocketMessageService webSocketMessageService;

    @Autowired
    private IDeviceRedisService deviceRedisService;

    @Autowired
    private AbstractLogService abstractLogService;

    /**
     * 업로드된 로그 목록을 페이지네이션으로 조회합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @param param 조회 파라미터
     * @return 페이지네이션된 로그 목록
     */
    @Override
    public PaginationData<DeviceLogsDTO> getUploadedLogs(String deviceSn, DeviceLogsQueryParam param) {
        LambdaQueryWrapper<DeviceLogsEntity> queryWrapper = new LambdaQueryWrapper<DeviceLogsEntity>()
                .eq(DeviceLogsEntity::getDeviceSn, deviceSn)
                .between(Objects.nonNull(param.getBeginTime()) && Objects.nonNull(param.getEndTime()),
                        DeviceLogsEntity::getCreateTime, param.getBeginTime(), param.getEndTime())
                .eq(Objects.nonNull(param.getStatus()), DeviceLogsEntity::getStatus, param.getStatus())
                .like(StringUtils.hasText(param.getLogsInformation()),
                        DeviceLogsEntity::getLogsInfo, param.getLogsInformation())
                .orderByDesc(DeviceLogsEntity::getCreateTime);

        Page<DeviceLogsEntity> pagination = mapper.selectPage(new Page<>(param.getPage(), param.getPageSize()), queryWrapper);

        List<DeviceLogsDTO> deviceLogsList = pagination.getRecords().stream().map(this::entity2Dto).collect(Collectors.toList());

        return new PaginationData<DeviceLogsDTO>(deviceLogsList, new Pagination(pagination.getCurrent(), pagination.getSize(), pagination.getTotal()));
    }

    /**
     * 실시간 로그 파일 목록을 조회합니다.
     * 
     * 디바이스의 현재 로그 파일 목록을 조회합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @param domainList 로그 모듈 목록
     * @return 실시간 로그 파일 목록
     */
    @Override
    public HttpResultResponse getRealTimeLogs(String deviceSn, List<LogModuleEnum> domainList) {
        boolean exist = deviceRedisService.checkDeviceOnline(deviceSn);
        if (!exist) {
            return HttpResultResponse.error("Device is offline.");
        }

        TopicServicesResponse<ServicesReplyData<FileUploadListResponse>> response = abstractLogService
                .fileuploadList(SDKManager.getDeviceSDK(deviceSn), new FileUploadListRequest().setModuleList(domainList));
        for (FileUploadListFile file : response.getData().getOutput().getFiles()) {
            if (file.getDeviceSn().isBlank()) {
                file.setDeviceSn(deviceSn);
            }
        }
        return HttpResultResponse.success(response.getData().getOutput());
    }

    /**
     * 디바이스 로그 정보를 데이터베이스에 삽입합니다.
     * 
     * @param bid 비즈니스 ID
     * @param username 사용자명
     * @param deviceSn 디바이스 시리얼 번호
     * @param param 로그 생성 파라미터
     * @return 로그 ID
     */
    @Override
    public String insertDeviceLogs(String bid, String username, String deviceSn, DeviceLogsCreateParam param) {
        DeviceLogsEntity entity = DeviceLogsEntity.builder()
                .deviceSn(deviceSn)
                .username(username)
                .happenTime(param.getHappenTime())
                .logsInfo(Objects.requireNonNullElse(param.getLogsInformation(), ""))
                .logsId(bid)
                .status(DeviceLogsStatusEnum.UPLOADING.getVal())
                .build();
        boolean insert = mapper.insert(entity) > 0;
        if (!insert) {
            return "";
        }
        for (FileUploadStartFile file : param.getFiles()) {
            insert = logsFileService.insertFile(file, entity.getLogsId());
            if (!insert) {
                return "";
            }
        }

        return bid;
    }

    /**
     * 로그 파일 업로드를 시작합니다.
     * 
     * STS 자격 증명을 받아서 로그 파일 업로드를 시작하고
     * 데이터베이스에 로그 정보를 저장합니다.
     * 
     * @param username 사용자명
     * @param deviceSn 디바이스 시리얼 번호
     * @param param 로그 생성 파라미터
     * @return 업로드 시작 결과
     */
    @Override
    public HttpResultResponse pushFileUpload(String username, String deviceSn, DeviceLogsCreateParam param) {
        // STS 자격 증명 획득
        StsCredentialsResponse stsCredentials = storageService.getSTSCredentials();
        stsCredentials.getCredentials().setExpire(System.currentTimeMillis() + (stsCredentials.getCredentials().getExpire() - 60) * 1000);
        LogsUploadCredentialsDTO credentialsDTO = new LogsUploadCredentialsDTO(stsCredentials);
        // 파일의 스토리지 이름 설정
        List<FileUploadStartFile> files = param.getFiles();
        files.forEach(file -> file.setObjectKey(credentialsDTO.getObjectKeyPrefix() + "/" + UUID.randomUUID().toString() + LOGS_FILE_SUFFIX));

        credentialsDTO.setParams(new FileUploadStartParam().setFiles(files));

        // 로그 파일 업로드 시작 요청
        TopicServicesResponse<ServicesReplyData> response = abstractLogService.fileuploadStart(
                SDKManager.getDeviceSDK(deviceSn), new FileUploadStartRequest()
                        .setCredentials(stsCredentials.getCredentials())
                        .setBucket(stsCredentials.getBucket())
                        .setEndpoint(stsCredentials.getEndpoint())
                        .setFileStoreDir(stsCredentials.getObjectKeyPrefix())
                        .setProvider(stsCredentials.getProvider())
                        .setRegion(stsCredentials.getRegion())
                        .setParams(new FileUploadStartParam().setFiles(files)));

        if (!response.getData().getResult().isSuccess()) {
            return HttpResultResponse.error(response.getData().getResult());
        }

        String id = this.insertDeviceLogs(response.getBid(), username, deviceSn, param);

        // 로그 업로드 상태를 Redis에 저장
        RedisOpsUtils.hashSet(RedisConst.LOGS_FILE_PREFIX + deviceSn, id, LogsOutputProgressDTO.builder().logsId(id).build());
        return HttpResultResponse.success();
    }

    /**
     * 로그 파일 업로드를 업데이트합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @param param 업데이트 파라미터
     * @return 업데이트 결과
     */
    @Override
    public HttpResultResponse pushUpdateFile(String deviceSn, FileUploadUpdateRequest param) {
        TopicServicesResponse<ServicesReplyData> response = abstractLogService.fileuploadUpdate(SDKManager.getDeviceSDK(deviceSn), param);

        if (!response.getData().getResult().isSuccess()) {
            return HttpResultResponse.error(response.getData().getResult());
        }
        return HttpResultResponse.success();
    }

    /**
     * 로그를 삭제합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @param logsId 로그 ID
     */
    @Override
    public void deleteLogs(String deviceSn, String logsId) {
        mapper.delete(new LambdaUpdateWrapper<DeviceLogsEntity>()
                .eq(DeviceLogsEntity::getLogsId, logsId).eq(DeviceLogsEntity::getDeviceSn, deviceSn));
        logsFileService.deleteFileByLogsId(logsId);
    }

    /**
     * 파일 업로드 진행률을 처리합니다.
     * 
     * MQTT를 통해 수신된 파일 업로드 진행률 정보를 처리하고
     * WebSocket을 통해 웹 클라이언트로 전송합니다.
     * 
     * @param request MQTT 이벤트 요청
     * @param headers 메시지 헤더
     * @return MQTT 응답
     */
    @Override
    public TopicEventsResponse<MqttReply> fileuploadProgress(TopicEventsRequest<EventsDataRequest<FileUploadProgress>> request, MessageHeaders headers) {
        EventsReceiver<LogsOutputProgressDTO> webSocketData = new EventsReceiver<>();
        webSocketData.setBid(request.getBid());
        webSocketData.setSn(request.getGateway());

        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(request.getGateway());
        if (deviceOpt.isEmpty()) {
            return null;
        }

        DeviceDTO device = deviceOpt.get();
        String key = RedisConst.LOGS_FILE_PREFIX + request.getGateway();

        try {
            FileUploadProgress output = request.getData().getOutput();
            log.info("Logs upload progress: {}", output.toString());

            LogsOutputProgressDTO progress;
            boolean exist = RedisOpsUtils.checkExist(key);
            if (!exist && !output.getStatus().isEnd()) {
                progress = LogsOutputProgressDTO.builder().logsId(request.getBid()).build();
                RedisOpsUtils.hashSet(key, request.getBid(), progress);
            } else if (exist) {
                progress = (LogsOutputProgressDTO) RedisOpsUtils.hashGet(key, request.getBid());
            } else {
                progress = LogsOutputProgressDTO.builder().build();
            }
            progress.setStatus(output.getStatus());

            // 로그 파일이 비어있으면 이 작업의 캐시를 삭제
            List<FileUploadProgressFile> fileReceivers = output.getExt().getFiles();
            if (CollectionUtils.isEmpty(fileReceivers)) {
                RedisOpsUtils.del(key);
            }

            // 캐시 새로고침
            List<LogsProgressDTO> fileProgressList = new ArrayList<>();
            fileReceivers.forEach(file -> {
                LogFileProgress logsProgress = file.getProgress();
                if (!StringUtils.hasText(file.getDeviceSn())) {
                    if (LogModuleEnum.DOCK == file.getModule()) {
                        file.setDeviceSn(request.getGateway());
                    } else if (LogModuleEnum.DRONE == file.getModule()) {
                        file.setDeviceSn(device.getChildDeviceSn());
                    }
                }

                fileProgressList.add(LogsProgressDTO.builder()
                        .deviceSn(file.getDeviceSn())
                        .deviceModelDomain(file.getModule().getDomain())
                        .result(logsProgress.getResult())
                        .status(logsProgress.getStatus().getStatus())
                        .uploadRate(logsProgress.getUploadRate())
                        .progress(((logsProgress.getCurrentStep() - 1) * 100 + logsProgress.getProgress()) / logsProgress.getTotalStep())
                        .build());
            });
            progress.setFiles(fileProgressList);
            webSocketData.setOutput(progress);
            RedisOpsUtils.hashSet(RedisConst.LOGS_FILE_PREFIX + request.getGateway(), request.getBid(), progress);
            // 작업 종료 시 캐시 삭제
            if (output.getStatus().isEnd()) {
                RedisOpsUtils.del(key);
                updateLogsStatus(request.getBid(), DeviceLogsStatusEnum.find(output.getStatus()).getVal());

                fileReceivers.forEach(file -> logsFileService.updateFile(request.getBid(), file));
            }
        } catch (NullPointerException e) {
            this.updateLogsStatus(request.getBid(), DeviceLogsStatusEnum.FAILED.getVal());
            RedisOpsUtils.del(key);
        }

        webSocketMessageService.sendBatch(device.getWorkspaceId(), UserTypeEnum.WEB.getVal(),
                BizCodeEnum.FILE_UPLOAD_PROGRESS.getCode(), webSocketData);

        return new TopicEventsResponse<MqttReply>().setData(MqttReply.success());
    }

    /**
     * 로그 상태를 업데이트합니다.
     * 
     * @param logsId 로그 ID
     * @param value 상태 값
     */
    @Override
    public void updateLogsStatus(String logsId, Integer value) {

        mapper.update(DeviceLogsEntity.builder().status(value).build(),
                new LambdaUpdateWrapper<DeviceLogsEntity>().eq(DeviceLogsEntity::getLogsId, logsId));
        if (DeviceLogsStatusEnum.DONE.getVal() == value) {
            logsFileService.updateFileUploadStatus(logsId, true);
        }
    }

    /**
     * 로그 파일 URL을 조회합니다.
     * 
     * @param logsId 로그 ID
     * @param fileId 파일 ID
     * @return 로그 파일 URL
     */
    @Override
    public URL getLogsFileUrl(String logsId, String fileId) {
        return logsFileService.getLogsFileUrl(logsId, fileId);
    }

    /**
     * 로그 엔티티를 DTO로 변환합니다.
     * 
     * @param entity 로그 엔티티
     * @return 로그 DTO
     */
    private DeviceLogsDTO entity2Dto(DeviceLogsEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        String key = RedisConst.LOGS_FILE_PREFIX + entity.getDeviceSn();
        LogsOutputProgressDTO progress = null;
        if (RedisOpsUtils.hashCheck(key, entity.getLogsId())) {
            progress = (LogsOutputProgressDTO) RedisOpsUtils.hashGet(key, entity.getLogsId());
        }

        return DeviceLogsDTO.builder()
                .logsId(entity.getLogsId())
                .createTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getCreateTime()), ZoneId.systemDefault()))
                .happenTime(Objects.isNull(entity.getHappenTime()) ?
                        null : LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getHappenTime()), ZoneId.systemDefault()))
                .status(entity.getStatus())
                .logsInformation(entity.getLogsInfo())
                .userName(entity.getUsername())
                .deviceLogs(LogsFileUploadListDTO.builder().files(logsFileService.getLogsFileByLogsId(entity.getLogsId())).build())
                .logsProgress(Objects.requireNonNullElse(progress, new LogsOutputProgressDTO()).getFiles())
                .deviceTopo(topologyService.getDeviceTopologyByGatewaySn(entity.getDeviceSn()).orElse(null))
                .build();
    }
}
