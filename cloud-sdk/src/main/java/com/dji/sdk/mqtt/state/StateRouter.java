package com.dji.sdk.mqtt.state;

import com.dji.sdk.common.Common;
import com.dji.sdk.common.SDKManager;
import com.dji.sdk.exception.CloudSDKErrorEnum;
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

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.dji.sdk.mqtt.TopicConst.*;

/**
 * MQTT 상태 라우터 클래스
 * 상태 메시지를 적절한 채널로 라우팅하는 Spring Integration 설정
 * 
 * @author sean.zhou
 * @date 2021/11/17
 * @version 0.1
 */
@Configuration
public class StateRouter {

    /** MQTT 게이트웨이 발행 서비스 */
    @Resource
    private MqttGatewayPublish gatewayPublish;

    /**
     * 상태 데이터 라우터 플로우를 생성합니다.
     * MQTT 상태 메시지를 받아서 적절한 채널로 라우팅합니다.
     * 
     * @return 상태 데이터 라우터 IntegrationFlow
     */
    @Bean
    public IntegrationFlow stateDataRouterFlow() {
        return IntegrationFlows
                .from(ChannelName.INBOUND_STATE)
                .transform(Message.class, source -> {
                    try {
                        // MQTT 메시지를 TopicStateRequest 객체로 변환
                        TopicStateRequest response = Common.getObjectMapper().readValue((byte[]) source.getPayload(), new TypeReference<TopicStateRequest>() {});
                        String topic = String.valueOf(source.getHeaders().get(MqttHeaders.RECEIVED_TOPIC));
                        // 토픽에서 디바이스 정보를 추출하여 설정
                        String from = topic.substring((THING_MODEL_PRE + PRODUCT).length(), topic.indexOf(STATE_SUF));
                        return response.setFrom(from)
                                .setData(Common.getObjectMapper().convertValue(response.getData(), getTypeReference(response.getGateway(), response.getData())));
                    } catch (IOException e) {
                        throw new CloudSDKException(e);
                    }
                }, null)
                .<TopicStateRequest, StateDataKeyEnum>route(response -> StateDataKeyEnum.find(response.getData().getClass()),
                        mapping -> Arrays.stream(StateDataKeyEnum.values()).forEach(key -> mapping.channelMapping(key, key.getChannelName())))
                .get();
    }

    /**
     * 상태 성공 응답을 처리하는 플로우를 생성합니다.
     * 상태 응답을 MQTT로 발행합니다.
     * 
     * @return 상태 응답 처리 IntegrationFlow
     */
    @Bean
    public IntegrationFlow replySuccessState() {
        return IntegrationFlows
                .from(ChannelName.OUTBOUND_STATE)
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
    private TopicStateResponse publish(TopicStateResponse request, MessageHeaders headers) {
        if (Objects.isNull(request) || Objects.isNull(request.getData())) {
            return null;
        }
        gatewayPublish.publishReply(request, headers);
        return request;
    }

    /**
     * 게이트웨이 타입에 따라 적절한 클래스 타입을 반환합니다.
     * 
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @param data 상태 데이터
     * @return 해당하는 클래스 타입
     */
    private Class getTypeReference(String gatewaySn, Object data) {
        Set<String> keys = ((Map<String, Object>) data).keySet();
        switch (SDKManager.getDeviceSDK(gatewaySn).getType()) {
            case RC:
                // 리모컨 타입인 경우 RC 상태 데이터 키 열거형에서 찾기
                return RcStateDataKeyEnum.find(keys).getClassType();
            case DOCK:
            case DOCK2:
                // 도크 타입인 경우 도크 상태 데이터 키 열거형에서 찾기
                return DockStateDataKeyEnum.find(keys).getClassType();
            default:
                throw new CloudSDKException(CloudSDKErrorEnum.WRONG_DATA, "Unexpected value: " + SDKManager.getDeviceSDK(gatewaySn).getType());
        }
    }
}