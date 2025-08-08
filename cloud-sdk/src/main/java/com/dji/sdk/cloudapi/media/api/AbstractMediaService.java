package com.dji.sdk.cloudapi.media.api;

import com.dji.sdk.annotations.CloudSDKVersion;
import com.dji.sdk.cloudapi.media.*;
import com.dji.sdk.cloudapi.storage.StsCredentialsResponse;
import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.config.version.GatewayTypeEnum;
import com.dji.sdk.mqtt.ChannelName;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import com.dji.sdk.mqtt.events.TopicEventsResponse;
import com.dji.sdk.mqtt.requests.TopicRequestsRequest;
import com.dji.sdk.mqtt.requests.TopicRequestsResponse;
import com.dji.sdk.mqtt.services.ServicesPublish;
import com.dji.sdk.mqtt.services.ServicesReplyData;
import com.dji.sdk.mqtt.services.TopicServicesResponse;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Resource;

/**
 * 미디어 서비스 추상 클래스
 * 
 * 이 클래스는 미디어 파일 업로드 및 관리를 위한 MQTT 기반 서비스의 기본 기능을 제공합니다.
 * 드론에서 촬영한 미디어 파일의 업로드 상태 추적, 우선순위 관리, 임시 자격 증명 획득 등의
 * 기능을 정의합니다.
 * 
 * 주요 기능:
 * - 미디어 파일 업로드 결과 보고
 * - 파일 업로드 우선순위 관리
 * - 업로드 임시 자격 증명 획득
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/19
 */
public abstract class AbstractMediaService {

    @Resource
    private ServicesPublish servicesPublish;

    /**
     * 미디어 파일 업로드 결과 보고
     * 
     * 드론에서 촬영한 미디어 파일의 업로드 완료 상태를 보고합니다.
     * 파일 업로드 성공/실패 여부와 관련 메타데이터를 처리합니다.
     * 
     * 사용 시나리오:
     * - 드론에서 촬영한 사진/비디오 파일 업로드 완료 알림
     * - 업로드 실패한 파일의 재시도 처리
     * - 업로드 진행률 추적 및 모니터링
     * 
     * @param request 파일 업로드 콜백 요청 데이터
     * @param headers MQTT 메시지 헤더
     * @return 이벤트 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_FILE_UPLOAD_CALLBACK, outputChannel = ChannelName.OUTBOUND_EVENTS)
    public TopicEventsResponse<MqttReply> fileUploadCallback(TopicEventsRequest<FileUploadCallback> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("fileUploadCallback not implemented");
    }

    /**
     * 미디어 파일 업로드 최우선순위 보고
     * 
     * 특정 미디어 파일의 업로드를 최우선순위로 설정합니다.
     * 중요한 미디어 파일(예: 비상 상황 촬영)을 다른 파일보다 먼저 업로드합니다.
     * 
     * 사용 시나리오:
     * - 비상 상황에서 촬영된 중요한 미디어 파일 우선 업로드
     * - 네트워크 대역폭이 제한적인 상황에서 중요 파일 우선 처리
     * - 실시간 모니터링이 필요한 미디어 파일의 즉시 업로드
     * 
     * @param request 최우선순위 업로드 요청 데이터
     * @param headers MQTT 메시지 헤더
     * @return 이벤트 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_HIGHEST_PRIORITY_UPLOAD_FLIGHT_TASK_MEDIA, outputChannel = ChannelName.OUTBOUND_EVENTS)
    public TopicEventsResponse<MqttReply> highestPriorityUploadFlightTaskMedia(TopicEventsRequest<HighestPriorityUploadFlightTaskMedia> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("highestPriorityUploadFlightTaskMedia not implemented");
    }

    /**
     * 비행 작업 미디어 파일 업로드 우선순위 설정
     * 
     * 특정 비행 작업의 미디어 파일 업로드를 최우선순위로 설정합니다.
     * RC(Remote Controller)를 제외한 모든 게이트웨이에서 지원됩니다.
     * 
     * 사용 시나리오:
     * - 특정 비행 작업에서 촬영된 미디어 파일의 우선 업로드
     * - 중요한 비행 데이터의 즉시 클라우드 저장
     * - 네트워크 대역폭 관리 및 우선순위 기반 업로드 스케줄링
     * 
     * @param gateway 게이트웨이 관리자
     * @param request 우선순위 설정 요청 데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> uploadFlighttaskMediaPrioritize(GatewayManager gateway, UploadFlighttaskMediaPrioritize request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                MediaMethodEnum.UPLOAD_FLIGHTTASK_MEDIA_PRIORITIZE.getMethod(),
                request);
    }

    /**
     * 업로드 임시 자격 증명 획득
     * 
     * 클라우드 스토리지에 파일을 업로드하기 위한 임시 자격 증명을 획득합니다.
     * STS(Security Token Service)를 통해 안전한 업로드 권한을 제공합니다.
     * 
     * 사용 시나리오:
     * - 드론에서 촬영한 미디어 파일을 클라우드 스토리지에 업로드
     * - 안전한 인증을 통한 파일 업로드
     * - 임시 자격 증명을 통한 보안 강화
     * 
     * @param request 스토리지 설정 요청 데이터
     * @param headers MQTT 메시지 헤더
     * @return 임시 자격 증명 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_REQUESTS_STORAGE_CONFIG_GET, outputChannel = ChannelName.OUTBOUND_REQUESTS)
    public TopicRequestsResponse<MqttReply<StsCredentialsResponse>> storageConfigGet(TopicRequestsRequest<StorageConfigGet> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("storageConfigGet not implemented");
    }

}
