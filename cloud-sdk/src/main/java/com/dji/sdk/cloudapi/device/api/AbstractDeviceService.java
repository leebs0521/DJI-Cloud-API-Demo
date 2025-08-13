package com.dji.sdk.cloudapi.device.api;

import com.dji.sdk.annotations.CloudSDKVersion;
import com.dji.sdk.cloudapi.device.*;
import com.dji.sdk.cloudapi.property.DockDroneCommanderFlightHeight;
import com.dji.sdk.cloudapi.property.DockDroneCommanderModeLostAction;
import com.dji.sdk.cloudapi.property.DockDroneRthMode;
import com.dji.sdk.config.version.CloudSDKVersionEnum;
import com.dji.sdk.config.version.GatewayTypeEnum;
import com.dji.sdk.mqtt.ChannelName;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.osd.TopicOsdRequest;
import com.dji.sdk.mqtt.state.TopicStateRequest;
import com.dji.sdk.mqtt.state.TopicStateResponse;
import com.dji.sdk.mqtt.status.TopicStatusRequest;
import com.dji.sdk.mqtt.status.TopicStatusResponse;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHeaders;

/**
 * 추상 디바이스 서비스 클래스
 * 
 * 이 클래스는 DJI 디바이스와의 통신을 위한 추상 서비스 클래스입니다.
 * 도크, 드론, 리모트 컨트롤의 OSD(On-Screen Display) 데이터 처리,
 * 디바이스 상태 업데이트, 펌웨어 버전 관리 등의 기능을 제공합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public class AbstractDeviceService {

    /**
     * 도크 OSD 데이터를 처리합니다.
     * 
     * @param request 도크 OSD 데이터 요청
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_OSD_DOCK)
    public void osdDock(TopicOsdRequest<OsdDock> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("osdDock not implemented");
    }

    /**
     * 도크 드론 OSD 데이터를 처리합니다.
     * 
     * @param request 도크 드론 OSD 데이터 요청
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_OSD_DOCK_DRONE)
    public void osdDockDrone(TopicOsdRequest<OsdDockDrone> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("osdDockDrone not implemented");
    }

    /**
     * 리모트 컨트롤 OSD 데이터를 처리합니다.
     * 
     * @param request 리모트 컨트롤 OSD 데이터 요청
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_OSD_RC)
    public void osdRemoteControl(TopicOsdRequest<OsdRemoteControl> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("osdRemoteControl not implemented");
    }

    /**
     * 리모트 컨트롤 드론 OSD 데이터를 처리합니다.
     * 
     * @param request 리모트 컨트롤 드론 OSD 데이터 요청
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_OSD_RC_DRONE)
    public void osdRcDrone(TopicOsdRequest<OsdRcDrone> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("osdRcDrone not implemented");
    }

    /**
     * 게이트웨이 디바이스와 서브 디바이스의 온라인 상태를 업데이트합니다.
     * 
     * @param request 토폴로지 업데이트 요청
     * @param headers 메시지 헤더
     * @return 상태 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATUS_ONLINE, outputChannel = ChannelName.OUTBOUND_STATUS)
    public TopicStatusResponse<MqttReply> updateTopoOnline(TopicStatusRequest<UpdateTopo> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("updateTopoOnline not implemented");
    }

    /**
     * 서브 디바이스의 오프라인 상태를 업데이트합니다.
     * 
     * @param request 토폴로지 업데이트 요청
     * @param headers 메시지 헤더
     * @return 상태 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATUS_OFFLINE, outputChannel = ChannelName.OUTBOUND_STATUS)
    public TopicStatusResponse<MqttReply> updateTopoOffline(TopicStatusRequest<UpdateTopo> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("updateTopoOffline not implemented");
    }

    /**
     * 도크와 드론의 펌웨어 버전을 업데이트합니다.
     * 
     * @param request 도크 펌웨어 버전 요청
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_DOCK_FIRMWARE_VERSION)
    public void dockFirmwareVersionUpdate(TopicStateRequest<DockFirmwareVersion> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("dockFirmwareVersionUpdate not implemented");
    }

    /**
     * 리모트 컨트롤과 드론의 펌웨어 버전을 업데이트합니다.
     * 
     * @param request 펌웨어 버전 요청
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_RC_AND_DRONE_FIRMWARE_VERSION)
    public void rcAndDroneFirmwareVersionUpdate(TopicStateRequest<FirmwareVersion> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("rcAndDroneFirmwareVersionUpdate not implemented");
    }

    /**
     * 도크와 드론의 컨트롤 소스를 업데이트합니다.
     * 
     * @param request 컨트롤 소스 요청
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_DOCK_DRONE_CONTROL_SOURCE)
    public void dockControlSourceUpdate(TopicStateRequest<DockDroneControlSource> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("dockControlSourceUpdate not implemented");
    }

    /**
     * 리모트 컨트롤과 드론의 컨트롤 소스를 업데이트합니다.
     * 
     * @param request 컨트롤 소스 요청
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_RC_CONTROL_SOURCE)
    public void rcControlSourceUpdate(TopicStateRequest<RcDroneControlSource> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("rcControlSourceUpdate not implemented");
    }

    /**
     * 도크와 드론의 라이브 상태를 업데이트합니다.
     * 
     * @param request 라이브 상태 요청
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_DOCK_LIVE_STATUS)
    public void dockLiveStatusUpdate(TopicStateRequest<DockLiveStatus> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("dockLiveStatusUpdate not implemented");
    }

    /**
     * 리모트 컨트롤과 드론의 라이브 상태를 업데이트합니다.
     * 
     * @param request 라이브 상태 요청
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_RC_LIVE_STATUS)
    public void rcLiveStatusUpdate(TopicStateRequest<RcLiveStatus> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("rcLiveStatusUpdate not implemented");
    }

    /**
     * 리모트 컨트롤과 드론의 페이로드 펌웨어 버전을 업데이트합니다.
     * 
     * @param request 페이로드 펌웨어 버전 요청
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_RC_PAYLOAD_FIRMWARE)
    public void rcPayloadFirmwareVersionUpdate(TopicStateRequest<PayloadFirmwareVersion> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("rcPayloadFirmwareVersionUpdate not implemented");
    }

    /**
     * 드론의 Wpmz 펌웨어 버전을 업데이트합니다.
     * 
     * @param request Wpmz 펌웨어 버전 요청
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_DOCK_DRONE_WPMZ_VERSION)
    public void dockWpmzVersionUpdate(TopicStateRequest<DockDroneWpmzVersion> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("dockWpmzVersionUpdate not implemented");
    }

    /**
     * IR 팔레트 지원 스타일을 업데이트합니다.
     * 
     * @param request IR 팔레트 지원 스타일 요청
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_DOCK_DRONE_THERMAL_SUPPORTED_PALETTE_STYLE)
    public void dockThermalSupportedPaletteStyle(TopicStateRequest<DockDroneThermalSupportedPaletteStyle> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("dockThermalSupportedPaletteStyle not implemented");
    }

    /**
     * 최적 RTH 모드에서 항공기는 자동으로 최적 반환 고도를 계획합니다.
     * 환경과 조명이 시각 시스템의 요구 사항을 충족하지 못하는 경우(예: 저녁 시간대 직사광선 또는 야간 빛 없음), 항공기는 설정된 고도에서 직선 반환을 수행합니다.
     * @param request 데이터
     * @param headers 메시지 헤더
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_DOCK_DRONE_RTH_MODE, outputChannel = ChannelName.OUTBOUND_STATE)
    public TopicStateResponse<MqttReply> dockDroneRthMode(TopicStateRequest<DockDroneRthMode> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("dockRthMode not implemented");
    }

    /**
     * 현재 RTH 높이 모드
     * @param request 데이터
     * @param headers 메시지 헤더
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_DOCK_DRONE_CURRENT_RTH_MODE, outputChannel = ChannelName.OUTBOUND_STATE)
    public TopicStateResponse<MqttReply> dockDroneCurrentRthMode(TopicStateRequest<DockDroneCurrentRthMode> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("dockCurrentRthMode not implemented");
    }

    /**
     * 종점 비행 임무 모드 손실 동작
     * @param request 데이터
     * @param headers 메시지 헤더
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_DOCK_DRONE_COMMANDER_MODE_LOST_ACTION, outputChannel = ChannelName.OUTBOUND_STATE)
    public TopicStateResponse<MqttReply> dockDroneCommanderModeLostAction(TopicStateRequest<DockDroneCommanderModeLostAction> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("dockDroneCommanderModeLostAction not implemented");
    }

    /**
     * 종점 비행 임무 모드
     * @param request 데이터
     * @param headers 메시지 헤더
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_DOCK_DRONE_CURRENT_COMMANDER_FLIGHT_MODE, outputChannel = ChannelName.OUTBOUND_STATE)
    public TopicStateResponse<MqttReply> dockDroneCurrentCommanderFlightMode(TopicStateRequest<DockDroneCurrentCommanderFlightMode> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("dockDroneCurrentCommanderFlightMode not implemented");
    }

    /**
     * 종점 비행 임무 고도
     * @param request 데이터
     * @param headers 메시지 헤더
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_DOCK_DRONE_COMMANDER_FLIGHT_HEIGHT, outputChannel = ChannelName.OUTBOUND_STATE)
    public TopicStateResponse<MqttReply> dockDroneCommanderFlightHeight(TopicStateRequest<DockDroneCommanderFlightHeight> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("dockDroneCommanderFlightHeight not implemented");
    }

    /**
     * 드론이 현재 상태가 된 이유
     * @param request 데이터
     * @param headers 메시지 헤더
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_DOCK_DRONE_MODE_CODE_REASON, outputChannel = ChannelName.OUTBOUND_STATE)
    public TopicStateResponse<MqttReply> dockDroneModeCodeReason(TopicStateRequest<DockDroneModeCodeReason> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("dockDroneModeCodeReason not implemented");
    }

    /**
     * 4g 동글 정보
     * @param request 데이터
     * @param headers 메시지 헤더
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_1, include = GatewayTypeEnum.DOCK2)
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_DOCK_AND_DRONE_DONGLE_INFOS, outputChannel = ChannelName.OUTBOUND_STATE)
    public TopicStateResponse<MqttReply> dongleInfos(TopicStateRequest<DongleInfos> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("dongleInfos not implemented");
    }

    /**
     * 무음 모드
     * @param request 데이터
     * @param headers 메시지 헤더
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_2, include = GatewayTypeEnum.DOCK)
    @ServiceActivator(inputChannel = ChannelName.INBOUND_STATE_DOCK_SILENT_MODE, outputChannel = ChannelName.OUTBOUND_STATE)
    public TopicStateResponse<MqttReply> dockSilentMode(TopicStateRequest<DockSilentMode> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("dockSilentMode not implemented");
    }

}
