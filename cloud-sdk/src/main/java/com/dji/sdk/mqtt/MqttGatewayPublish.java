package com.dji.sdk.mqtt;

import com.dji.sdk.common.Common;
import com.dji.sdk.exception.CloudSDKErrorEnum;
import com.dji.sdk.exception.CloudSDKException;
import com.dji.sdk.websocket.api.WebSocketMessageSend;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * MQTT 메시지 발행 게이트웨이 클래스
 * 
 * 이 클래스는 MQTT 브로커로 메시지를 발행하는 기능을 제공합니다.
 * 요청/응답 메시지 발행, 재시도 로직, 응답 대기 기능을 포함합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/16
 * @version 0.1
 */
@Component
public class MqttGatewayPublish {

    /**
     * 로깅을 위한 Logger 인스턴스
     */
    private static final Logger log = LoggerFactory.getLogger(WebSocketMessageSend.class);

    /**
     * 기본 QoS 레벨
     */
    private static final int DEFAULT_QOS = 0;
    
    /**
     * 기본 재시도 횟수
     */
    public static final int DEFAULT_RETRY_COUNT = 2;
    
    /**
     * 기본 재시도 타임아웃 (밀리초)
     */
    public static final int DEFAULT_RETRY_TIMEOUT = 3000;

    /**
     * MQTT 메시지 게이트웨이 인터페이스
     */
    @Resource
    private IMqttMessageGateway messageGateway;

    /**
     * 요청 메시지를 MQTT 브로커로 발행합니다.
     * 
     * @param topic 발행할 토픽
     * @param qos QoS 레벨 (0, 1, 2)
     * @param request 발행할 요청 객체
     */
    public void publish(String topic, int qos, CommonTopicRequest request) {
        try {
            log.debug("send topic: {}, payload: {}", topic, request.toString());
            byte[] payload = Common.getObjectMapper().writeValueAsBytes(request);
            messageGateway.publish(topic, payload, qos);
        } catch (JsonProcessingException e) {
            log.error("Failed to publish the message. {}", request.toString());
            e.printStackTrace();
        }
    }

    /**
     * 응답 메시지를 MQTT 브로커로 발행합니다.
     * 
     * @param topic 발행할 토픽
     * @param qos QoS 레벨 (0, 1, 2)
     * @param response 발행할 응답 객체
     */
    public void publish(String topic, int qos, CommonTopicResponse response) {
        try {
            log.debug("send topic: {}, payload: {}", topic, response.toString());
            byte[] payload = Common.getObjectMapper().writeValueAsBytes(response);
            messageGateway.publish(topic, payload, qos);
        } catch (JsonProcessingException e) {
            log.error("Failed to publish the message. {}", response.toString());
            e.printStackTrace();
        }
    }

    /**
     * 요청 메시지를 지정된 횟수만큼 반복 발행합니다.
     * 
     * @param topic 발행할 토픽
     * @param request 발행할 요청 객체
     * @param publishCount 발행 횟수
     */
    public void publish(String topic, CommonTopicRequest request, int publishCount) {
        AtomicInteger time = new AtomicInteger(0);
        while (time.getAndIncrement() < publishCount) {
            this.publish(topic, DEFAULT_QOS, request);
        }
    }

    /**
     * 요청 메시지를 기본 QoS로 발행합니다.
     * 
     * @param topic 발행할 토픽
     * @param request 발행할 요청 객체
     */
    public void publish(String topic, CommonTopicRequest request) {
        this.publish(topic, DEFAULT_QOS, request);
    }

    /**
     * 응답 메시지를 원본 토픽에 REPLY 접미사를 붙여 발행합니다.
     * 
     * @param response 발행할 응답 객체
     * @param headers 원본 메시지의 헤더 정보
     */
    public void publishReply(CommonTopicResponse response, MessageHeaders headers) {
        this.publish(headers.get(MqttHeaders.RECEIVED_TOPIC) + TopicConst._REPLY_SUF, 2, response);
    }

    /**
     * 요청 메시지를 발행하고 응답을 대기하는 메서드입니다.
     * 
     * 지정된 타임아웃 내에 응답이 없으면 재시도하며, 최대 재시도 횟수까지 시도합니다.
     * 응답의 tid와 bid가 요청과 일치하는지 확인합니다.
     * 
     * @param clazz 응답 데이터의 타입
     * @param topic 발행할 토픽
     * @param request 발행할 요청 객체
     * @param retryCount 최대 재시도 횟수
     * @param timeout 응답 대기 시간 (밀리초)
     * @param <T> 응답 데이터의 타입
     * @return 응답 객체
     * @throws CloudSDKException 응답을 받지 못한 경우
     * @throws TypeMismatchException 응답 데이터 타입이 예상과 다른 경우
     */
    public <T> CommonTopicResponse<T> publishWithReply(Class<T> clazz, String topic, CommonTopicRequest request, int retryCount, long timeout) {
        AtomicInteger time = new AtomicInteger(0);
        boolean hasBid = StringUtils.hasText(request.getBid());
        request.setBid(hasBid ? request.getBid() : UUID.randomUUID().toString());
        // Retry
        while (time.getAndIncrement() <= retryCount) {
            this.publish(topic, request);

            // If the message is not received in 3 seconds then resend it again.
            CommonTopicResponse<T> receiver = Chan.getInstance(request.getTid(), true).get(request.getTid(), timeout);
            // Need to match tid and bid.
            if (Objects.nonNull(receiver)
                    && receiver.getTid().equals(request.getTid())
                    && receiver.getBid().equals(request.getBid())) {
                if (clazz.isAssignableFrom(receiver.getData().getClass())) {
                    return receiver;
                }
                throw new TypeMismatchException(receiver.getData(), clazz);
            }
            // It must be guaranteed that the tid and bid of each message are different.
            if (!hasBid) {
                request.setBid(UUID.randomUUID().toString());
            }
            request.setTid(UUID.randomUUID().toString());
        }
        throw new CloudSDKException(CloudSDKErrorEnum.MQTT_PUBLISH_ABNORMAL, "No message reply received.");
    }


}