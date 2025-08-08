package com.dji.sdk.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * MQTT 메시지 게이트웨이 인터페이스
 * 
 * 이 인터페이스는 MQTT 메시지 발행을 위한 게이트웨이를 정의합니다.
 * Spring Integration의 MessagingGateway를 사용하여
 * MQTT 브로커로 메시지를 발행하는 기능을 제공합니다.
 * 
 * 주요 기능:
 * - 특정 토픽으로 메시지 발행
 * - QoS 레벨을 지정한 메시지 발행
 * - 바이트 배열 형태의 페이로드 지원
 * 
 * 이 인터페이스는 SDK의 MQTT 통신을
 * 표준화된 방식으로 처리합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/10
 * @version 0.1
 */
@Component
@MessagingGateway(defaultRequestChannel = ChannelName.OUTBOUND)
public interface IMqttMessageGateway {

    /**
     * 특정 토픽으로 메시지를 발행합니다.
     * 
     * @param topic 대상 토픽
     * @param payload 발행할 메시지 페이로드
     */
    void publish(@Header(MqttHeaders.TOPIC) String topic, byte[] payload);

    /**
     * 특정 QoS 레벨을 사용하여 특정 토픽으로 메시지를 발행합니다.
     * 
     * @param topic 대상 토픽
     * @param payload 발행할 메시지 페이로드
     * @param qos QoS 레벨 (0, 1, 2)
     */
    void publish(@Header(MqttHeaders.TOPIC) String topic, byte[] payload, @Header(MqttHeaders.QOS) int qos);
}
