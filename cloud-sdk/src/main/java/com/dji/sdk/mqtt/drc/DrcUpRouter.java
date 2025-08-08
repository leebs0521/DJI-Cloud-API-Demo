package com.dji.sdk.mqtt.drc;

import com.dji.sdk.common.Common;
import com.dji.sdk.exception.CloudSDKException;
import com.dji.sdk.mqtt.ChannelName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.Message;

import java.io.IOException;
import java.util.Arrays;

/**
 * DRC 업로드 라우터
 * 
 * 이 클래스는 DRC(Direct Remote Control) 업로드 메시지를
 * 적절한 채널로 라우팅하는 역할을 합니다.
 * 
 * 주요 기능:
 * - DRC 업로드 메시지 수신
 * - 메시지 변환 및 파싱
 * - 메서드별 채널 라우팅
 * - JSON 직렬화/역직렬화
 * 
 * 이 클래스는 Spring Integration DSL을 사용하여
 * DRC 업로드 메시지의 라우팅을 처리합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/1
 */
@Configuration
public class DrcUpRouter {

    /**
     * DRC 업로드 라우터 플로우를 생성합니다.
     * 
     * DRC 업로드 메시지를 수신하고 적절한 채널로 라우팅하는
     * Integration Flow를 정의합니다.
     * 
     * @return DRC 업로드 라우터 플로우
     */
    @Bean
    public IntegrationFlow drcUpRouterFlow() {
        return IntegrationFlows
                .from(ChannelName.INBOUND_DRC_UP)
                .transform(Message.class, source -> {
                    try {
                        TopicDrcRequest data = Common.getObjectMapper().readValue((byte[]) source.getPayload(), TopicDrcRequest.class);
                        return data.setData(Common.getObjectMapper().convertValue(data.getData(), DrcUpMethodEnum.find(data.getMethod()).getClassType()));
                    } catch (IOException e) {
                        throw new CloudSDKException(e);
                    }
                }, null)
                .<TopicDrcRequest, DrcUpMethodEnum>route(
                        response -> DrcUpMethodEnum.find(response.getMethod()),
                        mapping -> Arrays.stream(DrcUpMethodEnum.values()).forEach(
                                methodEnum -> mapping.channelMapping(methodEnum, methodEnum.getChannelName())))
                .get();
    }
}
