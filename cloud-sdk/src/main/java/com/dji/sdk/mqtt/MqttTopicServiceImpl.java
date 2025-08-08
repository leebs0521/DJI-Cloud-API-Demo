package com.dji.sdk.mqtt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * MQTT 토픽 서비스 구현체
 * 
 * 이 클래스는 IMqttTopicService 인터페이스의 구현체입니다.
 * MQTT 토픽 구독 및 구독 해제 기능을 제공합니다.
 * 
 * 주요 기능:
 * - 토픽 구독 (기본 QoS 1)
 * - 특정 QoS로 토픽 구독
 * - 토픽 구독 해제
 * - 구독 중인 토픽 목록 조회
 * 
 * 이 클래스는 Spring Integration의 MqttPahoMessageDrivenChannelAdapter를
 * 사용하여 MQTT 브로커와 통신합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/10
 * @version 0.1
 */
@Component
public class MqttTopicServiceImpl implements IMqttTopicService {

    /**
     * 로거 인스턴스
     * 
     * 디버그 로깅을 위한 로거입니다.
     */
    private static final Logger log = LoggerFactory.getLogger(MqttTopicServiceImpl.class);

    /**
     * MQTT 어댑터
     * 
     * MQTT 브로커와의 통신을 담당하는 어댑터입니다.
     */
    @Resource
    private MqttPahoMessageDrivenChannelAdapter adapter;

    /**
     * 토픽을 구독합니다.
     * 
     * 기본 QoS 1을 사용하여 토픽을 구독합니다.
     * 이미 구독 중인 토픽은 무시됩니다.
     * 
     * @param topics 구독할 토픽들
     */
    @Override
    public void subscribe(String... topics) {
        Set<String> topicSet = new HashSet<>(Arrays.asList(getSubscribedTopic()));
        for (String topic : topics) {
            if (topicSet.contains(topic)) {
                return;
            }
            subscribe(topic, 1);
        }
    }

    /**
     * 특정 QoS로 토픽을 구독합니다.
     * 
     * 지정된 QoS 레벨을 사용하여 토픽을 구독합니다.
     * 이미 구독 중인 토픽은 무시됩니다.
     * 
     * @param topic 구독할 토픽
     * @param qos QoS 레벨
     */
    @Override
    public void subscribe(String topic, int qos) {
        Set<String> topicSet = new HashSet<>(Arrays.asList(getSubscribedTopic()));
        if (topicSet.contains(topic)) {
            return;
        }
        log.debug("subscribe topic: {}", topic);
        adapter.addTopic(topic, qos);
    }

    /**
     * 토픽 구독을 해제합니다.
     * 
     * 지정된 토픽들의 구독을 해제합니다.
     * 
     * @param topics 구독 해제할 토픽들
     */
    @Override
    public void unsubscribe(String... topics) {
        log.debug("unsubscribe topic: {}", Arrays.toString(topics));
        adapter.removeTopic(topics);
    }

    /**
     * 구독 중인 토픽 목록을 반환합니다.
     * 
     * @return 구독 중인 토픽 배열
     */
    public String[] getSubscribedTopic() {
        return adapter.getTopic();
    }
}
