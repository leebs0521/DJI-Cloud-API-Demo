package com.dji.sdk.mqtt.osd;

import com.dji.sdk.cloudapi.device.PayloadModelConst;
import com.dji.sdk.common.Common;
import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.common.SDKManager;
import com.dji.sdk.exception.CloudSDKException;
import com.dji.sdk.mqtt.ChannelName;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.dji.sdk.mqtt.TopicConst.*;

/**
 * MQTT OSD 라우터 클래스
 * OSD 메시지를 적절한 채널로 라우팅하는 Spring Integration 설정
 * 
 * @author sean.zhou
 * @date 2021/11/17
 * @version 0.1
 */
@Configuration
public class OsdRouter {

    /**
     * OSD 라우터 플로우를 생성합니다.
     * MQTT OSD 메시지를 받아서 적절한 채널로 라우팅합니다.
     * 
     * @return OSD 라우터 IntegrationFlow
     */
    @Bean
    public IntegrationFlow osdRouterFlow() {
        return IntegrationFlows
                .from(ChannelName.INBOUND_OSD)
                .transform(Message.class, source -> {
                    try {
                        // MQTT 메시지를 TopicOsdRequest 객체로 변환
                        TopicOsdRequest response = Common.getObjectMapper().readValue((byte[]) source.getPayload(), new TypeReference<TopicOsdRequest>() {});
                        String topic = String.valueOf(source.getHeaders().get(MqttHeaders.RECEIVED_TOPIC));
                        // 토픽에서 디바이스 정보를 추출하여 설정
                        return response.setFrom(topic.substring((THING_MODEL_PRE + PRODUCT).length(), topic.indexOf(OSD_SUF)));
                    } catch (IOException e) {
                        throw new CloudSDKException(e);
                    }
                }, null)
                .<TopicOsdRequest>handle((response, headers) -> {
                    // 게이트웨이 매니저를 가져와서 디바이스 타입을 판별
                    GatewayManager gateway = SDKManager.getDeviceSDK(response.getGateway());
                    OsdDeviceTypeEnum typeEnum = OsdDeviceTypeEnum.find(gateway.getType(), response.getFrom().equals(response.getGateway()));
                    Map<String, Object> data = (Map<String, Object>) response.getData();
                    
                    // 게이트웨이가 아닌 경우 페이로드 데이터를 처리
                    if (!typeEnum.isGateway()) {
                        List payloadData = (List) data.getOrDefault(PayloadModelConst.PAYLOAD_KEY, new ArrayList<>());
                        // 모든 인덱스와 위치 정보를 페이로드 데이터에 추가
                        PayloadModelConst.getAllIndexWithPosition().stream().filter(data::containsKey)
                                .map(data::get).forEach(payloadData::add);
                        data.put(PayloadModelConst.PAYLOAD_KEY, payloadData);
                    }
                    // 데이터를 해당 클래스 타입으로 변환
                    return response.setData(Common.getObjectMapper().convertValue(data, typeEnum.getClassType()));
                })
                .<TopicOsdRequest, OsdDeviceTypeEnum>route(response -> OsdDeviceTypeEnum.find(response.getData().getClass()),
                        mapping -> Arrays.stream(OsdDeviceTypeEnum.values()).forEach(key -> mapping.channelMapping(key, key.getChannelName())))
                .get();
    }

}