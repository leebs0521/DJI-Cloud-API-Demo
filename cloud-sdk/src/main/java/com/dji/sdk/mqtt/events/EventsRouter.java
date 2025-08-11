package com.dji.sdk.mqtt.events;

import com.dji.sdk.common.Common;
import com.dji.sdk.exception.CloudSDKException;
import com.dji.sdk.mqtt.ChannelName;
import com.dji.sdk.mqtt.MqttGatewayPublish;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import static com.dji.sdk.mqtt.TopicConst.*;

/**
 * MQTT 이벤트 라우터 클래스
 * 이벤트 메시지를 적절한 채널로 라우팅하는 Spring Integration 설정
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/1
 */
@Configuration
public class EventsRouter {

    /** MQTT 게이트웨이 발행 서비스 */
    @Resource
    private MqttGatewayPublish gatewayPublish;

    /**
     * 이벤트 메서드 라우터 플로우를 생성합니다.
     * MQTT 이벤트 메시지를 받아서 적절한 채널로 라우팅합니다.
     * 
     * @return 이벤트 메서드 라우터 IntegrationFlow
     */
    @Bean
    public IntegrationFlow eventsMethodRouterFlow() {
        return IntegrationFlows
                .from(ChannelName.INBOUND_EVENTS)
                .transform(Message.class, source -> {
                    try {
                        // MQTT 메시지를 TopicEventsRequest 객체로 변환
                        TopicEventsRequest data = Common.getObjectMapper().readValue((byte[]) source.getPayload(), TopicEventsRequest.class);
                        String topic = String.valueOf(source.getHeaders().get(MqttHeaders.RECEIVED_TOPIC));
                        // 토픽에서 디바이스 정보를 추출하여 설정
                        return data.setFrom(topic.substring((THING_MODEL_PRE + PRODUCT).length(), topic.indexOf(EVENTS_SUF)))
                                .setData(Common.getObjectMapper().convertValue(data.getData(), EventsMethodEnum.find(data.getMethod()).getClassType()));
                    } catch (IOException e) {
                        throw new CloudSDKException(e);
                    }
                }, null)
                .<TopicEventsRequest, EventsMethodEnum>route(
                        response -> EventsMethodEnum.find(response.getMethod()),
                        mapping -> Arrays.stream(EventsMethodEnum.values()).forEach(
                                methodEnum -> mapping.channelMapping(methodEnum, methodEnum.getChannelName())))
                .get();
    }

    /**
     * 이벤트 성공 응답을 처리하는 플로우를 생성합니다.
     * 이벤트 응답을 MQTT로 발행합니다.
     * 
     * @return 이벤트 응답 처리 IntegrationFlow
     */
    @Bean
    public IntegrationFlow replySuccessEvents() {
        return IntegrationFlows
                .from(ChannelName.OUTBOUND_EVENTS)
                .handle(this::publish)
                .nullChannel();

    }

    /**
     * 이벤트 응답을 MQTT로 발행합니다.
     * 
     * @param request 이벤트 응답 데이터
     * @param headers 메시지 헤더
     * @return 발행된 응답 데이터
     */
    private TopicEventsResponse publish(TopicEventsResponse request, MessageHeaders headers) {
        if (Objects.isNull(request) || Objects.isNull(request.getData())) {
            return null;
        }
        gatewayPublish.publishReply(request, headers);
        return request;
    }

}
