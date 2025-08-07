package com.dji.sdk.cloudapi.airsense.api;

import com.dji.sdk.annotations.CloudSDKVersion;
import com.dji.sdk.cloudapi.airsense.AirsenseWarning;
import com.dji.sdk.config.version.CloudSDKVersionEnum;
import com.dji.sdk.mqtt.ChannelName;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import com.dji.sdk.mqtt.events.TopicEventsResponse;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.List;

/**
 * AirSense 서비스 추상 클래스
 * 
 * 이 클래스는 DJI AirSense 시스템의 경고 메시지를 처리하는 서비스의 기본 구현을 제공합니다.
 * PSDK(Payload Software Development Kit)에서 전송되는 항공기 감지 경고 데이터를 처리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/16
 */
public abstract class AbstractAirsenseService {


    /**
     * PSDK에서 전송되는 항공기 감지 경고 데이터를 처리합니다.
     * 
     * 이 메서드는 MQTT 채널을 통해 수신된 항공기 감지 경고 정보를 처리합니다.
     * 구체적인 구현은 하위 클래스에서 제공해야 합니다.
     * 
     * @param request 항공기 감지 경고 데이터 목록
     * @param headers MQTT 메시지 헤더 정보
     * @return MQTT 응답 메시지
     * @throws UnsupportedOperationException 구현되지 않은 경우
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_AIRSENSE_WARNING, outputChannel = ChannelName.OUTBOUND_EVENTS)
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    public TopicEventsResponse<MqttReply> airsenseWarning(TopicEventsRequest<List<AirsenseWarning>> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("airsenseWarning not implemented");
    }

}
