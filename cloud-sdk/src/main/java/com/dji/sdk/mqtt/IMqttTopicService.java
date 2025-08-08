package com.dji.sdk.mqtt;

import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * MQTT 토픽 서비스 인터페이스
 * 
 * 이 인터페이스는 MQTT 토픽 구독 및 구독 해제를 위한
 * 서비스를 정의합니다.
 * 
 * 주요 기능:
 * - 특정 토픽 구독
 * - QoS 레벨을 지정한 토픽 구독
 * - 특정 토픽 구독 해제
 * - 구독 중인 토픽 목록 조회
 * 
 * 이 인터페이스는 SDK의 MQTT 토픽 관리를
 * 표준화된 방식으로 처리합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/10
 * @version 0.1
 */
public interface IMqttTopicService {

    /**
     * 특정 토픽을 구독합니다.
     * 
     * @param topics 구독할 토픽들
     */
    void subscribe(@Header(MqttHeaders.TOPIC) String... topics);

    /**
     * 특정 QoS 레벨을 사용하여 특정 토픽을 구독합니다.
     * 
     * @param topic 구독할 토픽
     * @param qos QoS 레벨 (0, 1, 2)
     */
    void subscribe(@Header(MqttHeaders.TOPIC) String topic, int qos);

    /**
     * 특정 토픽의 구독을 해제합니다.
     * 
     * @param topics 구독 해제할 토픽들
     */
    void unsubscribe(@Header(MqttHeaders.TOPIC) String... topics);

    /**
     * 구독 중인 모든 토픽을 가져옵니다.
     * 
     * @return 구독 중인 토픽 배열
     */
    String[] getSubscribedTopic();
}
