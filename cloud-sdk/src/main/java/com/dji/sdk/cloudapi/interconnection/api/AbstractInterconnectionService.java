package com.dji.sdk.cloudapi.interconnection.api;

import com.dji.sdk.annotations.CloudSDKVersion;
import com.dji.sdk.cloudapi.interconnection.CustomDataTransmissionFromEsdk;
import com.dji.sdk.cloudapi.interconnection.CustomDataTransmissionToEsdkRequest;
import com.dji.sdk.cloudapi.interconnection.CustomDataTransmissionToPsdkRequest;
import com.dji.sdk.cloudapi.interconnection.InterconnectionMethodEnum;
import com.dji.sdk.config.version.CloudSDKVersionEnum;
import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.config.version.GatewayTypeEnum;
import com.dji.sdk.mqtt.ChannelName;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import com.dji.sdk.mqtt.events.TopicEventsResponse;
import com.dji.sdk.mqtt.services.ServicesPublish;
import com.dji.sdk.mqtt.services.ServicesReplyData;
import com.dji.sdk.mqtt.services.TopicServicesResponse;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Resource;

/**
 * 상호 연결 서비스 추상 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 상호 연결(Interconnection) 관련 서비스의 추상 클래스입니다.
 * MQTT를 통한 클라우드와 ESDK/PSDK 간의 커스텀 데이터 전송을 담당하며,
 * 양방향 데이터 교환 기능을 제공합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/16
 */
public abstract class AbstractInterconnectionService {

    /**
     * 서비스 발행 객체
     * MQTT 서비스 발행을 담당하는 객체
     */
    @Resource
    private ServicesPublish servicesPublish;

    /**
     * ESDK에서 클라우드로의 커스텀 데이터 전송 처리
     * ESDK에서 클라우드로 전송되는 커스텀 데이터를 수신하고 처리합니다.
     * 
     * @param request ESDK에서 전송된 데이터
     * @param headers 메시지 헤더
     * @return 이벤트 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_CUSTOM_DATA_TRANSMISSION_FROM_ESDK, outputChannel = ChannelName.OUTBOUND_EVENTS)
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    public TopicEventsResponse<MqttReply> customDataTransmissionFromEsdk(TopicEventsRequest<CustomDataTransmissionFromEsdk> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("customDataTransmissionFromEsdk not implemented");
    }

    /**
     * 클라우드에서 ESDK로의 커스텀 데이터 전송
     * 클라우드에서 ESDK로 커스텀 데이터를 전송합니다.
     * 
     * @param gateway 게이트웨이 디바이스
     * @param request 전송할 데이터 요청
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0, exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> customDataTransmissionToEsdk(GatewayManager gateway, CustomDataTransmissionToEsdkRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                InterconnectionMethodEnum.CUSTOM_DATA_TRANSMISSION_TO_ESDK.getMethod(),
                request);
    }

    /**
     * PSDK에서 클라우드로의 커스텀 데이터 전송 처리
     * PSDK에서 클라우드로 전송되는 커스텀 데이터를 수신하고 처리합니다.
     * 
     * @param request PSDK에서 전송된 데이터
     * @param headers 메시지 헤더
     * @return 이벤트 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_CUSTOM_DATA_TRANSMISSION_FROM_PSDK, outputChannel = ChannelName.OUTBOUND_EVENTS)
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    public TopicEventsResponse<MqttReply> customDataTransmissionFromPsdk(TopicEventsRequest<CustomDataTransmissionFromEsdk> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("customDataTransmissionFromPsdk not implemented");
    }

    /**
     * 클라우드에서 PSDK로의 커스텀 데이터 전송
     * 클라우드에서 PSDK로 커스텀 데이터를 전송합니다.
     * 
     * @param gateway 게이트웨이 디바이스
     * @param request 전송할 데이터 요청
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0, exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> customDataTransmissionToPsdk(GatewayManager gateway, CustomDataTransmissionToPsdkRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                InterconnectionMethodEnum.CUSTOM_DATA_TRANSMISSION_TO_PSDK.getMethod(),
                request);
    }
}
