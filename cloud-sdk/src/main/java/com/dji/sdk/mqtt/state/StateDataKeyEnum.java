package com.dji.sdk.mqtt.state;

import com.dji.sdk.cloudapi.device.*;
import com.dji.sdk.cloudapi.livestream.DockLivestreamAbilityUpdate;
import com.dji.sdk.cloudapi.livestream.RcLivestreamAbilityUpdate;
import com.dji.sdk.cloudapi.property.DockDroneCommanderFlightHeight;
import com.dji.sdk.cloudapi.property.DockDroneCommanderModeLostAction;
import com.dji.sdk.cloudapi.property.DockDroneOfflineMapEnable;
import com.dji.sdk.cloudapi.property.DockDroneRthMode;
import com.dji.sdk.mqtt.ChannelName;

import java.util.Arrays;

/**
 * MQTT 상태 데이터 키 열거형
 * 다양한 상태 타입과 해당하는 채널명, 클래스 타입을 정의
 * 
 * @author sean.zhou
 * @date 2021/11/18
 * @version 0.1
 */
public enum StateDataKeyEnum {

    /** RC 및 드론 펌웨어 버전 */
    RC_AND_DRONE_FIRMWARE_VERSION(ChannelName.INBOUND_STATE_RC_AND_DRONE_FIRMWARE_VERSION, FirmwareVersion.class),

    /** RC 라이브스트림 능력 */
    RC_LIVE_CAPACITY(ChannelName.INBOUND_STATE_RC_LIVESTREAM_ABILITY_UPDATE, RcLivestreamAbilityUpdate.class),

    /** RC 드론 제어 소스 */
    RC_DRONE_CONTROL_SOURCE(ChannelName.INBOUND_STATE_RC_CONTROL_SOURCE, RcDroneControlSource.class),

    /** RC 라이브 상태 */
    RC_LIVE_STATUS(ChannelName.INBOUND_STATE_RC_LIVE_STATUS, RcLiveStatus.class),

    /** RC 페이로드 펌웨어 */
    RC_PAYLOAD_FIRMWARE(ChannelName.INBOUND_STATE_RC_PAYLOAD_FIRMWARE, PayloadFirmwareVersion.class),

    /** 도크 펌웨어 버전 */
    DOCK_FIRMWARE_VERSION(ChannelName.INBOUND_STATE_DOCK_FIRMWARE_VERSION, DockFirmwareVersion.class),

    /** 도크 라이브스트림 능력 */
    DOCK_LIVE_CAPACITY(ChannelName.INBOUND_STATE_DOCK_LIVESTREAM_ABILITY_UPDATE, DockLivestreamAbilityUpdate.class),

    /** 도크 드론 제어 소스 */
    DOCK_DRONE_CONTROL_SOURCE(ChannelName.INBOUND_STATE_DOCK_DRONE_CONTROL_SOURCE, DockDroneControlSource.class),

    /** 도크 라이브 상태 */
    DOCK_LIVE_STATUS(ChannelName.INBOUND_STATE_DOCK_LIVE_STATUS, DockLiveStatus.class),

    /** 도크 드론 WPMZ 버전 */
    DOCK_DRONE_WPMZ_VERSION(ChannelName.INBOUND_STATE_DOCK_DRONE_WPMZ_VERSION, DockDroneWpmzVersion.class),

    /** 도크 드론 열화상 지원 팔레트 스타일 */
    DOCK_DRONE_THERMAL_SUPPORTED_PALETTE_STYLE(ChannelName.INBOUND_STATE_DOCK_DRONE_THERMAL_SUPPORTED_PALETTE_STYLE, DockDroneThermalSupportedPaletteStyle.class),

    /** 도크 드론 RTH 모드 */
    DOCK_DRONE_RTH_MODE(ChannelName.INBOUND_STATE_DOCK_DRONE_RTH_MODE, DockDroneRthMode.class),

    /** 도크 드론 현재 RTH 모드 */
    DOCK_DRONE_CURRENT_RTH_MODE(ChannelName.INBOUND_STATE_DOCK_DRONE_CURRENT_RTH_MODE, DockDroneCurrentRthMode.class),

    /** 도크 드론 커맨더 모드 손실 액션 */
    DOCK_DRONE_COMMANDER_MODE_LOST_ACTION(ChannelName.INBOUND_STATE_DOCK_DRONE_COMMANDER_MODE_LOST_ACTION, DockDroneCommanderModeLostAction.class),

    /** 도크 드론 현재 커맨더 비행 모드 */
    DOCK_DRONE_CURRENT_COMMANDER_FLIGHT_MODE(ChannelName.INBOUND_STATE_DOCK_DRONE_CURRENT_COMMANDER_FLIGHT_MODE, DockDroneCurrentCommanderFlightMode.class),

    /** 도크 드론 커맨더 비행 높이 */
    DOCK_DRONE_COMMANDER_FLIGHT_HEIGHT(ChannelName.INBOUND_STATE_DOCK_DRONE_COMMANDER_FLIGHT_HEIGHT, DockDroneCommanderFlightHeight.class),

    /** 도크 드론 모드 코드 이유 */
    DOCK_DRONE_MODE_CODE_REASON(ChannelName.INBOUND_STATE_DOCK_DRONE_MODE_CODE_REASON, DockDroneModeCodeReason.class),

    /** 도크 드론 오프라인 맵 활성화 */
    DOCK_DRONE_OFFLINE_MAP_ENABLE(ChannelName.INBOUND_STATE_DOCK_DRONE_OFFLINE_MAP_ENABLE, DockDroneOfflineMapEnable.class),

    /** 도크 및 드론 동글 정보 */
    DOCK_AND_DRONE_DONGLE_INFOS(ChannelName.INBOUND_STATE_DOCK_AND_DRONE_DONGLE_INFOS, DongleInfos.class),

    /** 도크 무음 모드 */
    DOCK_SILENT_MODE(ChannelName.INBOUND_STATE_DOCK_SILENT_MODE, DockSilentMode.class),

    /** 알 수 없는 상태 */
    UNKNOWN(ChannelName.DEFAULT, Object.class);

    /** 해당 상태의 채널명 */
    private final String channelName;

    /** 해당 상태의 클래스 타입 */
    private final Class classType;

    /**
     * StateDataKeyEnum 생성자
     * 
     * @param channelName 채널명
     * @param classType 클래스 타입
     */
    StateDataKeyEnum(String channelName, Class classType) {
        this.channelName = channelName;
        this.classType = classType;
    }

    /**
     * 클래스 타입을 반환합니다.
     * @return 클래스 타입
     */
    public Class getClassType() {
        return classType;
    }

    /**
     * 채널명을 반환합니다.
     * @return 채널명
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 클래스 타입으로 해당하는 StateDataKeyEnum을 찾습니다.
     * 
     * @param clazz 클래스 타입
     * @return 해당하는 StateDataKeyEnum, 없으면 UNKNOWN
     */
    public static StateDataKeyEnum find(Class clazz) {
        return Arrays.stream(values()).filter(keyEnum -> keyEnum.classType == clazz).findAny()
                .orElse(UNKNOWN);
    }

}
