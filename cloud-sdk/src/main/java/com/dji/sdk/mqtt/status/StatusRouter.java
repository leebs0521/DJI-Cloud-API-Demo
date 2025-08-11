package com.dji.sdk.mqtt.status;

import com.dji.sdk.cloudapi.device.UpdateTopo;
import com.dji.sdk.common.Common;
import com.dji.sdk.exception.CloudSDKException;
import com.dji.sdk.mqtt.ChannelName;
import com.dji.sdk.mqtt.MqttGatewayPublish;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static com.dji.sdk.mqtt.TopicConst.*;

/**
 * MQTT 상태 라우터 클래스
 * 상태 메시지를 온라인/오프라인 채널로 라우팅하는 Spring Integration 설정
 * 
 * @author sean.zhou
 * @date 2021/11/12
 * @version 0.1
 */
@Configuration
public class StatusRouter {

    /** MQTT 게이트웨이 발행 서비스 */
    @Resource
    private MqttGatewayPublish gatewayPublish;

    /**
     * 상태 라우터 플로우를 생성합니다.
     * MQTT 상태 메시지를 받아서 온라인/오프라인 채널로 라우팅합니다.
     * 
     * @return 상태 라우터 IntegrationFlow
     */
    @Bean
    public IntegrationFlow statusRouterFlow() {
        return IntegrationFlows
                .from(ChannelName.INBOUND_STATUS)
                .transform(Message.class, source -> {
                    try {
                        // MQTT 메시지를 TopicStatusRequest 객체로 변환
                        TopicStatusRequest<UpdateTopo> response = Common.getObjectMapper().readValue((byte[]) source.getPayload(), new TypeReference<TopicStatusRequest<UpdateTopo>>() {});
                        String topic = String.valueOf(source.getHeaders().get(MqttHeaders.RECEIVED_TOPIC));
                        // 토픽에서 디바이스 정보를 추출하여 설정
                        return response.setFrom(topic.substring((BASIC_PRE + PRODUCT).length(), topic.indexOf(STATUS_SUF)));
                    } catch (IOException e) {
                        throw new CloudSDKException(e);
                    }
                }, null)
                .<TopicStatusRequest<UpdateTopo>, Boolean>route(
                        // 서브 디바이스 목록이 비어있으면 오프라인, 있으면 온라인으로 라우팅
                        response -> Optional.ofNullable(response.getData()).map(UpdateTopo::getSubDevices).map(CollectionUtils::isEmpty).orElse(true),
                        mapping -> mapping.channelMapping(true, ChannelName.INBOUND_STATUS_OFFLINE)
                                .channelMapping(false, ChannelName.INBOUND_STATUS_ONLINE))
                .get();
    }

    /**
     * 상태 성공 응답을 처리하는 플로우를 생성합니다.
     * 상태 응답을 MQTT로 발행합니다.
     * 
     * @return 상태 응답 처리 IntegrationFlow
     */
    @Bean
    public IntegrationFlow replySuccessStatus() {
        return IntegrationFlows
                .from(ChannelName.OUTBOUND_STATUS)
                .handle(this::publish)
                .nullChannel();

    }

    /**
     * 상태 응답을 MQTT로 발행합니다.
     * 
     * @param request 상태 응답 데이터
     * @param headers 메시지 헤더
     * @return 발행된 응답 데이터
     */
    private TopicStatusResponse publish(TopicStatusResponse request, MessageHeaders headers) {
        if (Objects.isNull(request)) {
            return null;
        }
        gatewayPublish.publishReply(request, headers);
        return request;
    }
}