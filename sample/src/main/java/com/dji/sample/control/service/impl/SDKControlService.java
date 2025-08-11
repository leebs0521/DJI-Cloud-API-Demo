package com.dji.sample.control.service.impl;

import com.dji.sample.component.websocket.model.BizCodeEnum;
import com.dji.sample.component.websocket.service.IWebSocketMessageService;
import com.dji.sample.control.model.dto.ResultNotifyDTO;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.model.enums.UserTypeEnum;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sdk.cloudapi.control.*;
import com.dji.sdk.cloudapi.control.api.AbstractControlService;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import com.dji.sdk.mqtt.events.TopicEventsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * SDK 제어 서비스 구현 클래스
 * 
 * DJI SDK의 제어 이벤트를 처리하는 서비스 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 비행 진행 상황 모니터링
 *    - 특정 지점 비행 진행 상황 처리
 *    - 특정 지점 이륙 진행 상황 처리
 *    - 실시간 비행 상태 업데이트
 * 
 * 2. DRC 모드 상태 알림
 *    - DRC 모드 상태 변경 알림 처리
 *    - DRC 모드 오류 상태 알림
 *    - 실시간 DRC 상태 모니터링
 * 
 * 3. 조이스틱 상태 알림
 *    - 조이스틱 무효화 알림 처리
 *    - 조이스틱 오류 원인 분석
 *    - 사용자에게 조이스틱 상태 전달
 * 
 * 4. 웹소켓 실시간 알림
 *    - 웹 클라이언트에게 실시간 상태 전송
 *    - 비즈니스 코드별 알림 분류
 *    - 사용자별 알림 전송
 * 
 * 이 클래스는 DJI SDK의 이벤트를 수신하여 웹 클라이언트에게
 * 실시간으로 상태 정보를 전달하는 브리지 역할을 수행합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/7/4
 */
@Service
@Slf4j
public class SDKControlService extends AbstractControlService {

    /** 웹소켓 메시지 서비스 - 실시간 알림 전송 */
    @Autowired
    private IWebSocketMessageService webSocketMessageService;

    /** 디바이스 Redis 서비스 - 디바이스 온라인 상태 캐시 관리 */
    @Autowired
    private IDeviceRedisService deviceRedisService;

    /** JSON 직렬화를 위한 ObjectMapper */
    @Autowired
    private ObjectMapper mapper;

    /**
     * 특정 지점 비행 진행 상황을 처리합니다.
     * 
     * 이 메서드는 드론이 특정 지점으로 비행하는 동안 실시간으로 전송되는
     * 진행 상황 정보를 처리하고 웹 클라이언트에게 알림을 전송합니다:
     * - 비행 진행률 업데이트
     * - 비행 상태 변경 알림
     * - 비행 완료/실패 알림
     * 
     * 도크가 오프라인인 경우 알림을 전송하지 않습니다.
     * 
     * @param request 비행 진행 상황 이벤트 요청
     * @param headers 메시지 헤더
     * @return MQTT 응답 (성공)
     */
    @Override
    public TopicEventsResponse<MqttReply> flyToPointProgress(TopicEventsRequest<FlyToPointProgress> request, MessageHeaders headers) {
        String dockSn  = request.getGateway();

        // 도크 온라인 상태 확인
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(dockSn);
        if (deviceOpt.isEmpty()) {
            log.error("The dock is offline.");
            return null;
        }

        // 비행 진행 상황 정보 추출 및 웹소켓 알림 전송
        FlyToPointProgress eventsReceiver = request.getData();
        webSocketMessageService.sendBatch(deviceOpt.get().getWorkspaceId(), UserTypeEnum.WEB.getVal(),
                BizCodeEnum.FLY_TO_POINT_PROGRESS.getCode(),
                ResultNotifyDTO.builder().sn(dockSn)
                        .message(eventsReceiver.getResult().toString())
                        .result(eventsReceiver.getResult().getCode())
                        .build());
        return new TopicEventsResponse<MqttReply>().setData(MqttReply.success());
    }

    /**
     * 특정 지점 이륙 진행 상황을 처리합니다.
     * 
     * 이 메서드는 드론이 특정 지점으로 이륙하는 동안 실시간으로 전송되는
     * 진행 상황 정보를 처리하고 웹 클라이언트에게 알림을 전송합니다:
     * - 이륙 진행률 업데이트
     * - 이륙 상태 변경 알림
     * - 이륙 완료/실패 알림
     * 
     * 도크가 오프라인인 경우 알림을 전송하지 않습니다.
     * 
     * @param request 이륙 진행 상황 이벤트 요청
     * @param headers 메시지 헤더
     * @return MQTT 응답 (성공)
     */
    @Override
    public TopicEventsResponse<MqttReply> takeoffToPointProgress(TopicEventsRequest<TakeoffToPointProgress> request, MessageHeaders headers) {
        String dockSn  = request.getGateway();

        // 도크 온라인 상태 확인
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(dockSn);
        if (deviceOpt.isEmpty()) {
            log.error("The dock is offline.");
            return null;
        }

        // 이륙 진행 상황 정보 추출 및 웹소켓 알림 전송
        TakeoffToPointProgress eventsReceiver = request.getData();
        webSocketMessageService.sendBatch(deviceOpt.get().getWorkspaceId(), UserTypeEnum.WEB.getVal(),
                BizCodeEnum.TAKE_OFF_TO_POINT_PROGRESS.getCode(),
                ResultNotifyDTO.builder().sn(dockSn)
                        .message(eventsReceiver.getResult().toString())
                        .result(eventsReceiver.getResult().getCode())
                        .build());

        return new TopicEventsResponse<MqttReply>().setData(MqttReply.success());
    }

    /**
     * DRC 모드 상태 알림을 처리합니다.
     * 
     * 이 메서드는 DRC 모드의 상태 변경을 처리하고 웹 클라이언트에게 알림을 전송합니다:
     * - DRC 모드 진입/종료 알림
     * - DRC 모드 오류 상태 알림
     * - DRC 모드 상태 변경 추적
     * 
     * 성공 상태가 아닌 경우에만 알림을 전송하여 불필요한 알림을 방지합니다.
     * 
     * @param request DRC 상태 알림 이벤트 요청
     * @param headers 메시지 헤더
     * @return MQTT 응답 (성공)
     */
    @Override
    public TopicEventsResponse<MqttReply> drcStatusNotify(TopicEventsRequest<DrcStatusNotify> request, MessageHeaders headers) {
        String dockSn  = request.getGateway();

        // 도크 온라인 상태 확인
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(dockSn);
        if (deviceOpt.isEmpty()) {
            return null;
        }

        // DRC 상태 정보 추출 및 오류 시에만 웹소켓 알림 전송
        DrcStatusNotify eventsReceiver = request.getData();
        if (DrcStatusErrorEnum.SUCCESS != eventsReceiver.getResult()) {
            webSocketMessageService.sendBatch(
                    deviceOpt.get().getWorkspaceId(), UserTypeEnum.WEB.getVal(), BizCodeEnum.DRC_STATUS_NOTIFY.getCode(),
                    ResultNotifyDTO.builder().sn(dockSn)
                            .message(eventsReceiver.getResult().getMessage())
                            .result(eventsReceiver.getResult().getCode()).build());
        }
        return new TopicEventsResponse<MqttReply>().setData(MqttReply.success());
    }

    /**
     * 조이스틱 무효화 알림을 처리합니다.
     * 
     * 이 메서드는 DRC 모드에서 조이스틱이 무효화되었을 때 발생하는
     * 알림을 처리하고 웹 클라이언트에게 전달합니다:
     * - 조이스틱 무효화 원인 분석
     * - 조이스틱 오류 메시지 전달
     * - 사용자에게 조이스틱 상태 알림
     * 
     * 조이스틱 무효화는 다양한 원인으로 발생할 수 있으며,
     * 원인별로 적절한 메시지를 사용자에게 전달합니다.
     * 
     * @param request 조이스틱 무효화 알림 이벤트 요청
     * @param headers 메시지 헤더
     * @return MQTT 응답 (성공)
     */
    @Override
    public TopicEventsResponse<MqttReply> joystickInvalidNotify(TopicEventsRequest<JoystickInvalidNotify> request, MessageHeaders headers) {
        String dockSn  = request.getGateway();

        // 도크 온라인 상태 확인
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(dockSn);
        if (deviceOpt.isEmpty()) {
            return null;
        }

        // 조이스틱 무효화 정보 추출 및 웹소켓 알림 전송
        JoystickInvalidNotify eventsReceiver = request.getData();
        webSocketMessageService.sendBatch(
                deviceOpt.get().getWorkspaceId(), UserTypeEnum.WEB.getVal(), BizCodeEnum.JOYSTICK_INVALID_NOTIFY.getCode(),
                ResultNotifyDTO.builder().sn(dockSn)
                        .message(eventsReceiver.getReason().getMessage())
                        .result(eventsReceiver.getReason().getVal()).build());
        return new TopicEventsResponse<MqttReply>().setData(MqttReply.success());
    }
}
