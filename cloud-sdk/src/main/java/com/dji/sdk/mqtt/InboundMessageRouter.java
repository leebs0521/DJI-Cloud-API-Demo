package com.dji.sdk.mqtt;

import com.dji.sdk.common.SpringBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

/**
 * 인바운드 메시지 라우터
 * 
 * 이 클래스는 MQTT 브로커로부터 받은 메시지를
 * 적절한 채널로 라우팅하는 역할을 합니다.
 * 
 * 주요 기능:
 * - MQTT 메시지 수신
 * - 토픽 패턴 매칭
 * - 적절한 채널로 메시지 라우팅
 * - 디버그 로깅
 * 
 * 이 클래스는 Spring Integration의 AbstractMessageRouter를
 * 확장하여 MQTT 메시지 라우팅을 처리합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/10
 * @version 0.1
 */
@Component
public class InboundMessageRouter extends AbstractMessageRouter {

    /**
     * 로거 인스턴스
     * 
     * 디버그 로깅을 위한 로거입니다.
     */
    private static final Logger log = LoggerFactory.getLogger(InboundMessageRouter.class);

    /**
     * 대상 채널을 결정합니다.
     * 
     * 모든 MQTT 브로커 메시지가 여기에 도착한 후
     * 서로 다른 채널로 분배됩니다.
     * 
     * @param message MQTT 브로커로부터의 메시지
     * @return 대상 채널 컬렉션
     */
    @Override
    @Router(inputChannel = ChannelName.INBOUND)
    protected Collection<MessageChannel> determineTargetChannels(Message<?> message) {
        MessageHeaders headers = message.getHeaders();
        String topic = headers.get(MqttHeaders.RECEIVED_TOPIC).toString();
        byte[] payload = (byte[])message.getPayload();

        log.debug("received topic: {} \t payload =>{}", topic, new String(payload));

        CloudApiTopicEnum topicEnum = CloudApiTopicEnum.find(topic);
        MessageChannel bean = (MessageChannel) SpringBeanUtils.getBean(topicEnum.getBeanName());

        return Collections.singleton(bean);
    }
}
