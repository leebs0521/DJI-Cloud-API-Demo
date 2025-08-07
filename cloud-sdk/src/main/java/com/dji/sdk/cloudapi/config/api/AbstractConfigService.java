package com.dji.sdk.cloudapi.config.api;

import com.dji.sdk.cloudapi.config.ProductConfigResponse;
import com.dji.sdk.cloudapi.config.RequestsConfigRequest;
import com.dji.sdk.mqtt.ChannelName;
import com.dji.sdk.mqtt.requests.TopicRequestsRequest;
import com.dji.sdk.mqtt.requests.TopicRequestsResponse;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

/**
 * 설정 서비스 추상 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 설정 관련 요청을 처리하는 서비스의 기본 구현을 제공합니다.
 * MQTT 채널을 통해 설정 요청을 수신하고 적절한 설정 정보를 응답합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public abstract class AbstractConfigService {

    /**
     * 설정 요청을 처리합니다.
     * 
     * 이 메서드는 MQTT 채널을 통해 수신된 설정 요청을 처리합니다.
     * 설정 타입과 범위에 따라 적절한 설정 정보를 응답합니다.
     * 구체적인 구현은 하위 클래스에서 제공해야 합니다.
     * 
     * @param request 설정 요청 데이터
     * @param headers MQTT 메시지 헤더 정보
     * @return 설정 응답 메시지
     * @throws UnsupportedOperationException 구현되지 않은 경우
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_REQUESTS_CONFIG, outputChannel = ChannelName.OUTBOUND_REQUESTS)
    public TopicRequestsResponse<ProductConfigResponse> requestsConfig(TopicRequestsRequest<RequestsConfigRequest> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("requestsConfig not implemented");
    }

}
