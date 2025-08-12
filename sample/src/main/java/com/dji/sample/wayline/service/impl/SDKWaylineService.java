package com.dji.sample.wayline.service.impl;

import com.dji.sample.common.error.CommonErrorEnum;
import com.dji.sample.component.mqtt.model.EventsReceiver;
import com.dji.sample.component.websocket.model.BizCodeEnum;
import com.dji.sample.component.websocket.service.IWebSocketMessageService;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.model.enums.UserTypeEnum;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sample.media.model.MediaFileCountDTO;
import com.dji.sample.media.service.IMediaRedisService;
import com.dji.sample.wayline.model.dto.WaylineJobDTO;
import com.dji.sample.wayline.model.enums.WaylineJobStatusEnum;
import com.dji.sample.wayline.service.IWaylineFileService;
import com.dji.sample.wayline.service.IWaylineJobService;
import com.dji.sample.wayline.service.IWaylineRedisService;
import com.dji.sdk.cloudapi.wayline.*;
import com.dji.sdk.cloudapi.wayline.api.AbstractWaylineService;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.events.EventsDataRequest;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import com.dji.sdk.mqtt.events.TopicEventsResponse;
import com.dji.sdk.mqtt.requests.TopicRequestsRequest;
import com.dji.sdk.mqtt.requests.TopicRequestsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * DJI Cloud API SDK 웨이라인 서비스 구현 클래스
 * 
 * 이 클래스는 DJI SDK의 AbstractWaylineService를 상속받아 웨이라인 관련 MQTT 이벤트와 요청을 처리합니다.
 * 웨이라인 작업의 진행 상황 추적, 리소스 요청 처리, 실시간 상태 업데이트 등의 기능을 제공하며,
 * Redis 캐시와 WebSocket을 통한 실시간 통신을 지원합니다.
 * 
 * 주요 기능:
 * - 웨이라인 작업 진행 상황 실시간 추적
 * - 웨이라인 작업 리소스 요청 처리
 * - 웨이라인 작업 상태 Redis 캐싱
 * - 미디어 파일 카운트 관리
 * - WebSocket을 통한 실시간 상태 전송
 * - 웨이라인 작업 완료/실패 처리
 * 
 * 상속 관계:
 * - AbstractWaylineService: DJI SDK의 기본 웨이라인 서비스 기능 상속
 * 
 * 의존성:
 * - IDeviceRedisService: 디바이스 온라인 상태 관리
 * - IWaylineRedisService: 웨이라인 작업 상태 캐싱
 * - IMediaRedisService: 미디어 파일 카운트 관리
 * - IWebSocketMessageService: 실시간 메시지 전송
 * - IWaylineJobService: 웨이라인 작업 데이터 관리
 * - IWaylineFileService: 웨이라인 파일 관리
 * 
 * @author sean
 * @version 1.7
 * @date 2023/7/7
 */
@Service
@Slf4j
public class SDKWaylineService extends AbstractWaylineService {

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
     * WebSocket 메시지 서비스
     * 실시간으로 클라이언트에게 메시지를 전송하는 서비스
     */
    @Autowired
    private IWebSocketMessageService webSocketMessageService;

    /**
     * 웨이라인 작업 서비스
     * 웨이라인 작업의 데이터베이스 관리를 담당하는 서비스
     */
    @Autowired
    private IWaylineJobService waylineJobService;

    /**
     * 웨이라인 파일 서비스
     * 웨이라인 파일의 관리와 다운로드 URL 생성을 담당하는 서비스
     */
    @Autowired
    private IWaylineFileService waylineFileService;

    /**
     * 디바이스 귀환 알림 처리
     * 
     * @param request 디바이스 귀환 알림 요청
     * @param headers MQTT 메시지 헤더
     * @return MQTT 이벤트 응답
     */
    @Override
    public TopicEventsResponse<MqttReply> deviceExitHomingNotify(TopicEventsRequest<DeviceExitHomingNotify> request, MessageHeaders headers) {
        return super.deviceExitHomingNotify(request, headers);
    }

    /**
     * 웨이라인 작업 진행 상황 처리
     * 
     * 이 메서드는 DJI Dock에서 전송하는 웨이라인 작업의 실시간 진행 상황을 처리합니다.
     * 작업 상태를 Redis에 캐싱하고, 작업 완료 시 데이터베이스를 업데이트하며,
     * WebSocket을 통해 클라이언트에게 실시간 상태를 전송합니다.
     * 
     * 처리 과정:
     * 1. MQTT 이벤트 데이터를 EventsReceiver로 변환
     * 2. 디바이스 온라인 상태 확인
     * 3. 작업 진행 상황을 Redis에 캐싱
     * 4. 작업 완료 시 데이터베이스 업데이트 및 미디어 카운트 기록
     * 5. WebSocket을 통해 실시간 상태 전송
     * 
     * @param response 웨이라인 작업 진행 상황 이벤트 요청
     * @param headers MQTT 메시지 헤더
     * @return MQTT 이벤트 응답
     */
    @Override
    public TopicEventsResponse<MqttReply> flighttaskProgress(TopicEventsRequest<EventsDataRequest<FlighttaskProgress>> response, MessageHeaders headers) {
        // MQTT 이벤트 데이터를 EventsReceiver로 변환
        EventsReceiver<FlighttaskProgress> eventsReceiver = new EventsReceiver<>();
        eventsReceiver.setResult(response.getData().getResult());
        eventsReceiver.setOutput(response.getData().getOutput());
        eventsReceiver.setBid(response.getBid());
        eventsReceiver.setSn(response.getGateway());

        FlighttaskProgress output = eventsReceiver.getOutput();
        log.info("Task progress: {}", output.getProgress().toString());
        if (!eventsReceiver.getResult().isSuccess()) {
            log.error("Task progress ===> Error: " + eventsReceiver.getResult());
        }

        // 디바이스 온라인 상태 확인
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(response.getGateway());
        if (deviceOpt.isEmpty()) {
            return new TopicEventsResponse<>();
        }

        FlighttaskStatusEnum statusEnum = output.getStatus();
        // 작업 진행 상황을 Redis에 캐싱
        waylineRedisService.setRunningWaylineJob(response.getGateway(), eventsReceiver);

        // 작업 완료 시 처리
        if (statusEnum.isEnd()) {
            WaylineJobDTO job = WaylineJobDTO.builder()
                    .jobId(response.getBid())
                    .status(WaylineJobStatusEnum.SUCCESS.getVal())
                    .completedTime(LocalDateTime.now())
                    .mediaCount(output.getExt().getMediaCount())
                    .build();

            // 미디어 파일 카운트 기록
            if (Objects.nonNull(job.getMediaCount()) && job.getMediaCount() != 0) {
                mediaRedisService.setMediaCount(response.getGateway(), job.getJobId(),
                        MediaFileCountDTO.builder().deviceSn(deviceOpt.get().getChildDeviceSn())
                                .jobId(response.getBid()).mediaCount(job.getMediaCount()).uploadedCount(0).build());
            }

            // 작업 실패 시 상태 업데이트
            if (FlighttaskStatusEnum.OK != statusEnum) {
                job.setCode(eventsReceiver.getResult().getCode());
                job.setStatus(WaylineJobStatusEnum.FAILED.getVal());
            }
            waylineJobService.updateJob(job);
            waylineRedisService.delRunningWaylineJob(response.getGateway());
            waylineRedisService.delPausedWaylineJob(response.getBid());
        }

        // WebSocket을 통해 실시간 상태 전송
        webSocketMessageService.sendBatch(deviceOpt.get().getWorkspaceId(), UserTypeEnum.WEB.getVal(),
                BizCodeEnum.FLIGHT_TASK_PROGRESS.getCode(), eventsReceiver);

        return new TopicEventsResponse<>();
    }

    /**
     * 웨이라인 작업 리소스 요청 처리
     * 
     * 이 메서드는 DJI Dock에서 웨이라인 작업 실행에 필요한 리소스(웨이라인 파일)를 요청할 때 호출됩니다.
     * 요청된 작업 ID에 해당하는 웨이라인 파일의 다운로드 URL과 서명을 제공합니다.
     * 
     * 처리 과정:
     * 1. 디바이스 온라인 상태 확인
     * 2. 웨이라인 작업 정보 조회
     * 3. 웨이라인 파일 정보 조회
     * 4. 웨이라인 파일 다운로드 URL 생성
     * 5. MQTT 응답으로 리소스 정보 반환
     * 
     * @param response 웨이라인 작업 리소스 요청
     * @param headers MQTT 메시지 헤더
     * @return 웨이라인 작업 리소스 응답
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public TopicRequestsResponse<MqttReply<FlighttaskResourceGetResponse>> flighttaskResourceGet(TopicRequestsRequest<FlighttaskResourceGetRequest> response, MessageHeaders headers) {
        String jobId = response.getData().getFlightId();

        // 디바이스 온라인 상태 확인
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(response.getGateway());
        if (deviceOpt.isEmpty()) {
            log.error("The device is offline, please try again later.");
            return new TopicRequestsResponse().setData(MqttReply.error(CommonErrorEnum.DEVICE_OFFLINE));
        }
        
        // 웨이라인 작업 정보 조회
        Optional<WaylineJobDTO> waylineJobOpt = waylineJobService.getJobByJobId(deviceOpt.get().getWorkspaceId(), jobId);
        if (waylineJobOpt.isEmpty()) {
            log.error("The wayline job does not exist.");
            return new TopicRequestsResponse().setData(MqttReply.error(CommonErrorEnum.ILLEGAL_ARGUMENT));
        }

        WaylineJobDTO waylineJob = waylineJobOpt.get();

        // 웨이라인 파일 정보 조회
        Optional<GetWaylineListResponse> waylineFile = waylineFileService.getWaylineByWaylineId(waylineJob.getWorkspaceId(), waylineJob.getFileId());
        if (waylineFile.isEmpty()) {
            log.error("The wayline file does not exist.");
            return new TopicRequestsResponse().setData(MqttReply.error(CommonErrorEnum.ILLEGAL_ARGUMENT));
        }
        
        // 웨이라인 파일 다운로드 URL 생성 및 응답
        try {
            URL url = waylineFileService.getObjectUrl(waylineJob.getWorkspaceId(), waylineFile.get().getId());
            return new TopicRequestsResponse<MqttReply<FlighttaskResourceGetResponse>>().setData(
                    MqttReply.success(new FlighttaskResourceGetResponse()
                            .setFile(new FlighttaskFile()
                                    .setUrl(url.toString())
                                    .setFingerprint(waylineFile.get().getSign()))));
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return new TopicRequestsResponse().setData(MqttReply.error(CommonErrorEnum.SYSTEM_ERROR));
        }
    }
}
