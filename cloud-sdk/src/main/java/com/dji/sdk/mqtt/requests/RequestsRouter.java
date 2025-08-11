package com.dji.sdk.mqtt.requests;

import com.dji.sdk.common.Common;
import com.dji.sdk.exception.CloudSDKErrorEnum;
import com.dji.sdk.exception.CloudSDKException;
import com.dji.sdk.mqtt.ChannelName;
import com.dji.sdk.mqtt.MqttGatewayPublish;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * MQTT 요청 라우터 클래스
 * 요청 메시지를 적절한 채널로 라우팅하는 Spring Integration 설정
 * 
 * @author sean
 * @version 1.0
 * @date 2022/5/25
 */
@Configuration
public class RequestsRouter {

    /** MQTT 게이트웨이 발행 서비스 */
    @Resource
    private MqttGatewayPublish gatewayPublish;

    /**
     * 요청 메서드 라우터 플로우를 생성합니다.
     * MQTT 요청 메시지를 받아서 적절한 채널로 라우팅합니다.
     * 
     * @return 요청 메서드 라우터 IntegrationFlow
     */
    @Bean
    public IntegrationFlow requestsMethodRouterFlow() {
        return IntegrationFlows
                .from(ChannelName.INBOUND_REQUESTS)
                .<byte[], TopicRequestsRequest>transform(payload -> {
                    try {
                        // MQTT 메시지를 TopicRequestsRequest 객체로 변환
                        TopicRequestsRequest response = Common.getObjectMapper().readValue(payload, TopicRequestsRequest.class);
                        // 데이터를 해당 클래스 타입으로 변환
                        return response.setData(Common.getObjectMapper().convertValue(response.getData(), RequestsMethodEnum.find(response.getMethod()).getClassType()));
                    } catch (IOException e) {
                        throw new CloudSDKException(e);
                    }
                })
                .<TopicRequestsRequest, RequestsMethodEnum>route(
                        receiver -> RequestsMethodEnum.find(receiver.getMethod()),
                                mapping -> Arrays.stream(RequestsMethodEnum.values()).forEach(
                                        methodEnum -> mapping.channelMapping(methodEnum, methodEnum.getChannelName())))
                .get();
    }

    /**
     * 요청 메서드 응답을 처리하는 플로우를 생성합니다.
     * 요청 응답을 MQTT로 발행합니다.
     * 
     * @return 요청 응답 처리 IntegrationFlow
     */
    @Bean
    public IntegrationFlow replyRequestsMethod() {
        return IntegrationFlows
                .from(ChannelName.OUTBOUND_REQUESTS)
                .handle(this::publish)
                .nullChannel();
    }

    /**
     * 요청 응답을 MQTT로 발행합니다.
     * 
     * @param request 요청 응답 데이터
     * @param headers 메시지 헤더
     * @return 발행된 응답 데이터
     */
    private TopicRequestsResponse publish(TopicRequestsResponse request, MessageHeaders headers) {
        if (Objects.isNull(request)) {
            throw new CloudSDKException(CloudSDKErrorEnum.INVALID_PARAMETER, "The return value cannot be null.");
        }
        gatewayPublish.publishReply(request, headers);
        return request;
    }
}
