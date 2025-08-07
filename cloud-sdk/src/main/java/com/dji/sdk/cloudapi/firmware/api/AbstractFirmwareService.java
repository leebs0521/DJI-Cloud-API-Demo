package com.dji.sdk.cloudapi.firmware.api;

import com.dji.sdk.cloudapi.firmware.FirmwareMethodEnum;
import com.dji.sdk.cloudapi.firmware.OtaCreateRequest;
import com.dji.sdk.cloudapi.firmware.OtaCreateResponse;
import com.dji.sdk.cloudapi.firmware.OtaProgress;
import com.dji.sdk.config.version.GatewayManager;
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

/**
 * 추상 펌웨어 서비스 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 펌웨어 관련 기능을 제공하는 추상 서비스 클래스입니다.
 * OTA(Over-The-Air) 펌웨어 업데이트 생성과 진행 상황 모니터링 기능을 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/28
 */
public abstract class AbstractFirmwareService {

    /**
     * 서비스 발행 객체
     */
    @Resource
    private ServicesPublish servicesPublish;

    /**
     * 펌웨어 업그레이드 진행 상황
     * 
     * @param request 데이터
     * @param headers 메시지의 헤더입니다.
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_OTA_PROGRESS, outputChannel = ChannelName.OUTBOUND_EVENTS)
    public TopicEventsResponse<MqttReply> otaProgress(TopicEventsRequest<EventsDataRequest<OtaProgress>> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("otaProgress not implemented");
    }

    /**
     * 펌웨어 업그레이드
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    public TopicServicesResponse<ServicesReplyData<OtaCreateResponse>> otaCreate(GatewayManager gateway, OtaCreateRequest request) {
        return servicesPublish.publish(
                new TypeReference<OtaCreateResponse>() {},
                gateway.getGatewaySn(),
                FirmwareMethodEnum.OTA_CREATE.getMethod(),
                request);
    }

}
