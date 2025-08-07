package com.dji.sdk.cloudapi.hms.api;

import com.dji.sdk.cloudapi.hms.Hms;
import com.dji.sdk.mqtt.ChannelName;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHeaders;

/**
 * HMS 서비스 추상 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 HMS(Health Management System) 관련 서비스의 추상 클래스입니다.
 * MQTT를 통한 HMS 이벤트 처리를 담당하며, HMS 보고서를 수신하고 처리하는 기능을 제공합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/27
 */
public abstract class AbstractHmsService {

    /**
     * HMS 보고서 처리
     * HMS 이벤트를 수신하고 처리하는 메서드입니다.
     * 
     * @param response HMS 이벤트 요청 데이터
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_HMS)
    public void hms(TopicEventsRequest<Hms> response, MessageHeaders headers) {
        throw new UnsupportedOperationException("hms not implemented");
    }
}
