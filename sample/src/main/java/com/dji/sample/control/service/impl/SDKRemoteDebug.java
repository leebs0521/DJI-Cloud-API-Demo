package com.dji.sample.control.service.impl;

import com.dji.sample.component.mqtt.model.EventsReceiver;
import com.dji.sample.component.websocket.service.IWebSocketMessageService;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.model.enums.UserTypeEnum;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sdk.cloudapi.debug.RemoteDebugProgress;
import com.dji.sdk.cloudapi.debug.api.AbstractDebugService;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.events.EventsDataRequest;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import com.dji.sdk.mqtt.events.TopicEventsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * SDK 원격 디버깅 서비스 구현 클래스
 * 
 * DJI SDK의 원격 디버깅 이벤트를 처리하는 서비스 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 원격 디버깅 진행 상황 모니터링
 *    - 원격 디버깅 명령 실행 진행 상황 처리
 *    - 디버깅 결과 상태 확인
 *    - 실시간 디버깅 상태 업데이트
 * 
 * 2. 디버깅 이벤트 처리
 *    - 디버깅 진행 상황 이벤트 수신
 *    - 디버깅 결과 분석 및 로깅
 *    - 오류 상태 감지 및 처리
 * 
 * 3. 웹소켓 실시간 알림
 *    - 웹 클라이언트에게 실시간 디버깅 상태 전송
 *    - 디버깅 진행률 및 결과 전달
 *    - 사용자별 디버깅 알림 전송
 * 
 * 4. 디버깅 로깅
 *    - 디버깅 진행 상황 로깅
 *    - 오류 발생 시 상세 로깅
 *    - 디버깅 상태 추적
 * 
 * 이 클래스는 DJI SDK의 원격 디버깅 이벤트를 수신하여 웹 클라이언트에게
 * 실시간으로 디버깅 상태 정보를 전달하는 브리지 역할을 수행합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/7/4
 */
@Service
@Slf4j
public class SDKRemoteDebug extends AbstractDebugService {

    /** 웹소켓 메시지 서비스 - 실시간 알림 전송 */
    @Autowired
    private IWebSocketMessageService webSocketMessageService;

    /** 디바이스 Redis 서비스 - 디바이스 온라인 상태 캐시 관리 */
    @Autowired
    private IDeviceRedisService deviceRedisService;

    /**
     * 원격 디버깅 진행 상황을 처리합니다.
     * 
     * 이 메서드는 원격 디버깅 명령 실행 중 실시간으로 전송되는
     * 진행 상황 정보를 처리하고 웹 클라이언트에게 알림을 전송합니다:
     * - 디버깅 진행률 업데이트
     * - 디버깅 상태 변경 알림
     * - 디버깅 완료/실패 알림
     * - 오류 발생 시 상세 로깅
     * 
     * 디바이스가 오프라인인 경우 RuntimeException을 발생시킵니다.
     * 
     * @param request 원격 디버깅 진행 상황 이벤트 요청
     * @param headers 메시지 헤더
     * @return MQTT 응답 (성공)
     */
    @Override
    public TopicEventsResponse<MqttReply> remoteDebugProgress(TopicEventsRequest<EventsDataRequest<RemoteDebugProgress>> request, MessageHeaders headers) {
        String sn = request.getGateway();

        // 원격 디버깅 진행 상황 정보 구성
        EventsReceiver<RemoteDebugProgress> eventsReceiver = new EventsReceiver<RemoteDebugProgress>()
                .setOutput(request.getData().getOutput()).setResult(request.getData().getResult());
        eventsReceiver.setBid(request.getBid());
        eventsReceiver.setSn(sn);

        // 디버깅 진행 상황 로깅
        log.info("SN: {}, {} ===> Control progress: {}", sn, request.getMethod(), eventsReceiver.getOutput().getProgress());

        // 오류 발생 시 상세 로깅
        if (!eventsReceiver.getResult().isSuccess()) {
            log.error("SN: {}, {} ===> Error: {}", sn, request.getMethod(), eventsReceiver.getResult());
        }

        // 디바이스 온라인 상태 확인
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(sn);

        if (deviceOpt.isEmpty()) {
            throw new RuntimeException("The device is offline.");
        }

        // 웹소켓을 통한 실시간 알림 전송
        DeviceDTO device = deviceOpt.get();
        webSocketMessageService.sendBatch(device.getWorkspaceId(), UserTypeEnum.WEB.getVal(),
                request.getMethod(), eventsReceiver);

        return new TopicEventsResponse<MqttReply>().setData(MqttReply.success());
    }
}
