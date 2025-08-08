package com.dji.sdk.cloudapi.map.api;

import com.dji.sdk.annotations.CloudSDKVersion;
import com.dji.sdk.cloudapi.map.MapMethodEnum;
import com.dji.sdk.cloudapi.map.OfflineMapGetRequest;
import com.dji.sdk.cloudapi.map.OfflineMapGetResponse;
import com.dji.sdk.cloudapi.map.OfflineMapSyncProgress;
import com.dji.sdk.cloudapi.property.DockDroneOfflineMapEnable;
import com.dji.sdk.config.version.CloudSDKVersionEnum;
import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.config.version.GatewayTypeEnum;
import com.dji.sdk.mqtt.ChannelName;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.requests.TopicRequestsRequest;
import com.dji.sdk.mqtt.requests.TopicRequestsResponse;
import com.dji.sdk.mqtt.services.ServicesPublish;
import com.dji.sdk.mqtt.services.ServicesReplyData;
import com.dji.sdk.mqtt.services.TopicServicesResponse;
import com.dji.sdk.mqtt.state.TopicStateRequest;
import com.dji.sdk.mqtt.state.TopicStateResponse;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Resource;

/**
 * 오프라인 맵 서비스 추상 클래스
 * 오프라인 맵 동기화 및 관리를 위한 기본 기능을 제공합니다.
 * @author sean
 * @version 1.7
 * @date 2023/10/19
 */
public abstract class AbstractOfflineMapService {

    @Resource
    private ServicesPublish servicesPublish;

    /**
     * 도킹 스테이션 드론 오프라인 맵 활성화 처리
     * 오프라인 맵이 비활성화되면, 오프라인 맵 동기화가 더 이상 자동으로 동기화되지 않습니다.
     * @param request 요청 데이터
     * @param headers 메시지 헤더
     * @return 상태 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_1, include = GatewayTypeEnum.DOCK2)
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_DOCK_DRONE_OFFLINE_MAP_ENABLE, outputChannel = ChannelName.OUTBOUND_STATE)
    public TopicStateResponse<MqttReply> dockDroneOfflineMapEnable(TopicStateRequest<DockDroneOfflineMapEnable> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("dockDroneOfflineMapEnable not implemented");
    }

    /**
     * 오프라인 맵 업데이트 활성 트리거
     * 메시지를 받은 후, 공항은 적절한 시점에 오프라인 맵 정보를 적극적으로 가져와서
     * 오프라인 맵 동기화 메커니즘을 트리거합니다.
     * @param gateway 게이트웨이 디바이스
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_1, include = GatewayTypeEnum.DOCK2)
    public TopicServicesResponse<ServicesReplyData> offlineMapUpdate(GatewayManager gateway) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                MapMethodEnum.OFFLINE_MAP_UPDATE.getMethod());
    }

    /**
     * 오프라인 맵 파일 동기화 상태 처리
     * @param request 요청 데이터
     * @param headers 메시지 헤더
     * @return 이벤트 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_OFFLINE_MAP_SYNC_PROGRESS, outputChannel = ChannelName.OUTBOUND_EVENTS)
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_1, include = GatewayTypeEnum.DOCK2)
    public TopicRequestsResponse<MqttReply> offlineMapSyncProgress(TopicRequestsRequest<OfflineMapSyncProgress> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("offlineMapSyncProgress not implemented");
    }

    /**
     * 오프라인 맵 정보 조회
     * 도킹 스테이션은 최신 오프라인 맵 파일 정보를 적극적으로 가져옵니다.
     * 이 정보에서 항공기의 오프라인 맵 파일명이나 체크섬이 변경되었는지 확인합니다.
     * 변경사항이 발견되면 오프라인 맵 동기화가 트리거되고, 그렇지 않으면 동기화되지 않습니다.
     * @param request 요청 데이터
     * @param headers 메시지 헤더
     * @return 요청 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_REQUESTS_OFFLINE_MAP_GET, outputChannel = ChannelName.OUTBOUND_REQUESTS)
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_1, include = GatewayTypeEnum.DOCK2)
    public TopicRequestsResponse<MqttReply<OfflineMapGetResponse>> offlineMapGet(TopicRequestsRequest<OfflineMapGetRequest> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("offlineMapGet not implemented");
    }
}
