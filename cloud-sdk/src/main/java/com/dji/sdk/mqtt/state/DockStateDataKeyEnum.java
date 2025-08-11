package com.dji.sdk.mqtt.state;

import com.dji.sdk.cloudapi.device.*;
import com.dji.sdk.cloudapi.livestream.DockLivestreamAbilityUpdate;
import com.dji.sdk.cloudapi.property.DockDroneCommanderFlightHeight;
import com.dji.sdk.cloudapi.property.DockDroneCommanderModeLostAction;
import com.dji.sdk.cloudapi.property.DockDroneOfflineMapEnable;
import com.dji.sdk.cloudapi.property.DockDroneRthMode;
import com.dji.sdk.exception.CloudSDKException;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

/**
 * 도크 상태 데이터 키 열거형
 * 도크 관련 상태 데이터 키와 해당하는 클래스 타입을 정의
 * 
 * @author sean.zhou
 * @date 2021/11/18
 * @version 0.1
 */
public enum DockStateDataKeyEnum {

    /** 펌웨어 버전 */
    FIRMWARE_VERSION(Set.of("firmware_version"), DockFirmwareVersion.class),

    /** 라이브스트림 능력 */
    LIVE_CAPACITY(Set.of("live_capacity"), DockLivestreamAbilityUpdate.class),

    /** 제어 소스 */
    CONTROL_SOURCE(Set.of("control_source"), DockDroneControlSource.class),

    /** 라이브 상태 */
    LIVE_STATUS(Set.of("live_status"), DockLiveStatus.class),

    /** WPMZ 버전 */
    WPMZ_VERSION(Set.of("wpmz_version"), DockDroneWpmzVersion.class),

    /** 열화상 지원 팔레트 스타일 */
    THERMAL_SUPPORTED_PALETTE_STYLE(PayloadModelConst.getAllIndexWithPosition(), DockDroneThermalSupportedPaletteStyle.class),

    /** RTH 모드 */
    RTH_MODE(Set.of("rth_mode"), DockDroneRthMode.class),

    /** 현재 RTH 모드 */
    CURRENT_RTH_MODE(Set.of("current_rth_mode"), DockDroneCurrentRthMode.class),

    /** 커맨더 모드 손실 액션 */
    COMMANDER_MODE_LOST_ACTION(Set.of("commander_mode_lost_action"), DockDroneCommanderModeLostAction.class),

    /** 현재 커맨더 비행 모드 */
    CURRENT_COMMANDER_FLIGHT_MODE(Set.of("current_commander_flight_mode"), DockDroneCurrentCommanderFlightMode.class),

    /** 커맨더 비행 높이 */
    COMMANDER_FLIGHT_HEIGHT(Set.of("commander_flight_height"), DockDroneCommanderFlightHeight.class),

    /** 모드 코드 이유 */
    MODE_CODE_REASON(Set.of("mode_code_reason"), DockDroneModeCodeReason.class),

    /** 오프라인 맵 활성화 */
    OFFLINE_MAP_ENABLE(Set.of("offline_map_enable"), DockDroneOfflineMapEnable.class),

    /** 동글 정보 */
    DONGLE_INFOS(Set.of("dongle_infos"), DongleInfos.class),

    /** 무음 모드 */
    SILENT_MODE(Set.of("silent_mode"), DockSilentMode.class),

    ;

    /** 상태 데이터 키들 */
    private final Set<String> keys;

    /** 해당 상태의 클래스 타입 */
    private final Class classType;

    /**
     * DockStateDataKeyEnum 생성자
     * 
     * @param keys 상태 데이터 키들
     * @param classType 클래스 타입
     */
    DockStateDataKeyEnum(Set<String> keys, Class classType) {
        this.keys = keys;
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
     * 상태 데이터 키들을 반환합니다.
     * @return 상태 데이터 키들
     */
    public Set<String> getKeys() {
        return keys;
    }

    /**
     * 키들로 해당하는 DockStateDataKeyEnum을 찾습니다.
     * 
     * @param keys 상태 데이터 키들
     * @return 해당하는 DockStateDataKeyEnum
     * @throws CloudSDKException 해당하는 키를 찾을 수 없는 경우
     */
    public static DockStateDataKeyEnum find(Set<String> keys) {
        return Arrays.stream(values()).filter(keyEnum -> !Collections.disjoint(keys, keyEnum.keys)).findAny()
                .orElseThrow(() -> new CloudSDKException(DockStateDataKeyEnum.class, keys));
    }

}
