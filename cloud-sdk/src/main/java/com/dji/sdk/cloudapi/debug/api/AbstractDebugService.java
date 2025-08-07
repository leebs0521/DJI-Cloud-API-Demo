package com.dji.sdk.cloudapi.debug.api;

import com.dji.sdk.annotations.CloudSDKVersion;
import com.dji.sdk.cloudapi.debug.*;
import com.dji.sdk.common.BaseModel;
import com.dji.sdk.common.Common;
import com.dji.sdk.common.SpringBeanUtils;
import com.dji.sdk.config.version.CloudSDKVersionEnum;
import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.config.version.GatewayTypeEnum;
import com.dji.sdk.exception.CloudSDKException;
import com.dji.sdk.mqtt.ChannelName;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.events.EventsDataRequest;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import com.dji.sdk.mqtt.events.TopicEventsResponse;
import com.dji.sdk.mqtt.services.ServicesPublish;
import com.dji.sdk.mqtt.services.ServicesReplyData;
import com.dji.sdk.mqtt.services.TopicServicesResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 추상 디버그 서비스 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 디버그 기능을 제공하는 추상 서비스 클래스입니다.
 * 디버그 모드, 보조 조명, 배터리 관리, 디바이스 제어, ESIM 관리 등 다양한 디버그 기능을 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public abstract class AbstractDebugService {

    /**
     * 서비스 발행 객체
     */
    @Resource
    private ServicesPublish servicesPublish;

    /**
     * 디버그 모드 열기
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> debugModeOpen(GatewayManager gateway) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.DEBUG_MODE_OPEN.getMethod());
    }

    /**
     * 디버그 모드 닫기
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> debugModeClose(GatewayManager gateway) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.DEBUG_MODE_CLOSE.getMethod());
    }

    /**
     * 보조 조명 열기
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> supplementLightOpen(GatewayManager gateway) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.SUPPLEMENT_LIGHT_OPEN.getMethod());
    }

    /**
     * 보조 조명 닫기
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> supplementLightClose(GatewayManager gateway) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.SUPPLEMENT_LIGHT_CLOSE.getMethod());
    }

    /**
     * 배터리 유지보수 상태 전환
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> batteryMaintenanceSwitch(GatewayManager gateway, BatteryMaintenanceSwitchRequest request) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.BATTERY_MAINTENANCE_SWITCH.getMethod(),
                request);
    }

    /**
     * 독 공기조화기 작동 모드 전환
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> airConditionerModeSwitch(GatewayManager gateway, AirConditionerModeSwitchRequest request) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.AIR_CONDITIONER_MODE_SWITCH.getMethod(),
                request);
    }

    /**
     * 독 소리 및 조명 경보 전환
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> alarmStateSwitch(GatewayManager gateway, AlarmStateSwitchRequest request) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.ALARM_STATE_SWITCH.getMethod(),
                request);
    }

    /**
     * 독 배터리 저장 모드 전환
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> batteryStoreModeSwitch(GatewayManager gateway, BatteryStoreModeSwitchRequest request) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.BATTERY_STORE_MODE_SWITCH.getMethod(),
                request);
    }

    /**
     * 독 재부팅
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> deviceReboot(GatewayManager gateway) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.DEVICE_REBOOT.getMethod());
    }

    /**
     * 소형기 전원 켜기
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> droneOpen(GatewayManager gateway) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.DRONE_OPEN.getMethod());
    }

    /**
     * 소형기 전원 끄기
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> droneClose(GatewayManager gateway) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.DRONE_CLOSE.getMethod());
    }

    /**
     * 독 데이터 포맷
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> deviceFormat(GatewayManager gateway) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.DEVICE_FORMAT.getMethod());
    }

    /**
     * 소형기 데이터 포맷
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> droneFormat(GatewayManager gateway) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.DRONE_FORMAT.getMethod());
    }

    /**
     * 독 커버 열기
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> coverOpen(GatewayManager gateway) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.COVER_OPEN.getMethod());
    }

    /**
     * 독 커버 닫기
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> coverClose(GatewayManager gateway) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.COVER_CLOSE.getMethod());
    }

    /**
     * 풀러 열기
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = {GatewayTypeEnum.RC, GatewayTypeEnum.DOCK2})
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> putterOpen(GatewayManager gateway) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.PUTTER_OPEN.getMethod());
    }

    /**
     * 풀러 닫기
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = {GatewayTypeEnum.RC, GatewayTypeEnum.DOCK2})
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> putterClose(GatewayManager gateway) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.PUTTER_CLOSE.getMethod());
    }

    /**
     * 충전 켜기
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> chargeOpen(GatewayManager gateway) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.CHARGE_OPEN.getMethod());
    }

    /**
     * 충전 끄기
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> chargeClose(GatewayManager gateway) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.CHARGE_CLOSE.getMethod());
    }

    /**
     * 4G 강화 모드 전환
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> sdrWorkmodeSwitch(GatewayManager gateway, SdrWorkmodeSwitchRequest request) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.SDR_WORKMODE_SWITCH.getMethod(),
                request);
    }

    /**
     * 원격 디버깅 공통 인터페이스
     * 
     * @param gateway 게이트웨이 관리자
     * @param methodEnum 메서드 열거형
     * @param request  데이터
     * @return 서비스 응답
     */
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> remoteDebug(GatewayManager gateway, DebugMethodEnum methodEnum, BaseModel request) {
        try {
            List<Class> clazz = new ArrayList<>();
            List<Object> args = new ArrayList<>();
            clazz.add(GatewayManager.class);
            args.add(gateway);
            if (Objects.nonNull(request)) {
                clazz.add(request.getClass());
                args.add(request);
            }
            AbstractDebugService abstractDebugService = SpringBeanUtils.getBean(this.getClass());
            Method method = abstractDebugService.getClass().getDeclaredMethod(Common.convertSnake(methodEnum.getMethod()), clazz.toArray(Class[]::new));
            return (TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>>) method.invoke(abstractDebugService, args.toArray());
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new CloudSDKException(e);
        } catch (InvocationTargetException e) {
            throw new CloudSDKException(e.getTargetException());
        }
    }

    /**
     * 원격 디버깅 진행 상태 알림
     * 
     * @param request 데이터
     * @param headers 메시지의 헤더입니다.
     * @return events_reply
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_CONTROL_PROGRESS, outputChannel = ChannelName.OUTBOUND_EVENTS)
    public TopicEventsResponse<MqttReply> remoteDebugProgress(TopicEventsRequest<EventsDataRequest<RemoteDebugProgress>> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("remoteDebugProgress not implemented");
    }

    /**
     * esim 활성화
     * 
     * @param gateway  게이트웨이 디바이스
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_1, include = GatewayTypeEnum.DOCK2)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> esimActivate(GatewayManager gateway, EsimActivateRequest request) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.ESIM_ACTIVATE.getMethod(),
                request);
    }

    /**
     * esim 및 sim 슬롯 전환
     * 
     * @param gateway  게이트웨이 디바이스
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_1, include = GatewayTypeEnum.DOCK2)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> simSlotSwitch(GatewayManager gateway, SimSlotSwitchRequest request) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.SIM_SLOT_SWITCH.getMethod(),
                request);
    }

    /**
     * esim 운영자 전환
     * 
     * @param gateway  게이트웨이 디바이스
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_1, include = GatewayTypeEnum.DOCK2)
    public TopicServicesResponse<ServicesReplyData<RemoteDebugResponse>> esimOperatorSwitch(GatewayManager gateway, EsimOperatorSwitchRequest request) {
        return servicesPublish.publish(
                new TypeReference<RemoteDebugResponse>() {},
                gateway.getGatewaySn(),
                DebugMethodEnum.ESIM_OPERATOR_SWITCH.getMethod(),
                request);
    }


}
