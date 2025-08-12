package com.dji.sdk.cloudapi.property;

import com.dji.sdk.cloudapi.device.DockSilentMode;
import com.dji.sdk.common.BaseModel;
import com.dji.sdk.config.version.CloudSDKVersionEnum;
import com.dji.sdk.config.version.GatewayTypeEnum;
import com.dji.sdk.exception.CloudSDKException;

import java.util.Arrays;
import java.util.Set;

/**
 * 속성 설정 열거형
 * 
 * 이 열거형은 드론과 도크의 다양한 속성 설정을 정의합니다.
 * 각 속성의 이름, 클래스, 지원 버전, 지원 디바이스 등을 관리합니다.
 * 
 * 주요 구성 요소:
 * - property: 속성 이름
 * - clazz: 속성 설정 클래스
 * - since: 지원 시작 버전
 * - supportedDevices: 지원 디바이스 목록
 * - deprecated: 사용 중단 여부
 * 
 * 이 열거형은 드론과 도크의 다양한 설정을 체계적으로 관리하는 데 사용됩니다.
 * 버전별 호환성과 디바이스별 지원 여부를 확인할 수 있습니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/27
 */
public enum PropertySetEnum {

    /**
     * 야간 조명 상태 설정
     * 
     * 도크의 야간 조명 기능을 제어합니다.
     * 지원 디바이스: DOCK, DOCK2
     */
    NIGHT_LIGHTS_STATE("night_lights_state", NightLightsStateSet.class, CloudSDKVersionEnum.V0_0_1, Set.of(GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2_OR_DOCK3)),

    /**
     * 고도 제한 설정
     * 
     * 드론의 최대 비행 고도를 제한합니다.
     * 지원 디바이스: DOCK, DOCK2
     */
    HEIGHT_LIMIT("height_limit", HeightLimitSet.class, CloudSDKVersionEnum.V0_0_1, Set.of(GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2_OR_DOCK3)),

    /**
     * 거리 제한 상태 설정
     * 
     * 드론의 비행 거리 제한을 제어합니다.
     * 지원 디바이스: DOCK, DOCK2
     */
    DISTANCE_LIMIT_STATUS("distance_limit_status", DistanceLimitStatusSet.class, CloudSDKVersionEnum.V0_0_1, Set.of(GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2_OR_DOCK3)),

    /**
     * 장애물 회피 설정
     * 
     * 드론의 장애물 회피 기능을 제어합니다.
     * 지원 디바이스: DOCK, DOCK2
     */
    OBSTACLE_AVOIDANCE("obstacle_avoidance", ObstacleAvoidanceSet.class, CloudSDKVersionEnum.V0_0_1, Set.of(GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2_OR_DOCK3)),

    /**
     * RTH 고도 설정
     * 
     * 드론의 Return-to-Home 고도를 설정합니다.
     * 지원 디바이스: DOCK, DOCK2
     */
    RTH_ALTITUDE("rth_altitude", RthAltitudeSet.class, CloudSDKVersionEnum.V0_0_1, Set.of(GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2_OR_DOCK3)),

    /**
     * RC 연결 끊김 시 동작 설정
     * 
     * RC 연결이 끊어졌을 때 드론의 동작을 설정합니다.
     * 지원 디바이스: DOCK, DOCK2
     */
    OUT_OF_CONTROL_ACTION("rc_lost_action", RcLostActionSet.class, CloudSDKVersionEnum.V0_0_1, Set.of(GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2_OR_DOCK3)),

    /**
     * RC 연결 끊김 시 웨이라인 종료 설정
     * 
     * RC 연결이 끊어졌을 때 웨이라인 비행을 종료할지 설정합니다.
     * 지원 디바이스: DOCK, DOCK2
     */
    EXIT_WAYLINE_WHEN_RC_LOST("exit_wayline_when_rc_lost", ExitWaylineWhenRcLostSet.class, CloudSDKVersionEnum.V0_0_1, Set.of(GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2_OR_DOCK3), true),

    /**
     * 열화상 카메라 팔레트 스타일 설정
     * 
     * 열화상 카메라의 색상 팔레트 스타일을 설정합니다.
     * 지원 디바이스: DOCK, DOCK2
     */
    THERMAL_CURRENT_PALETTE_STYLE("thermal_current_palette_style", ThermalCurrentPaletteStyleSet.class, CloudSDKVersionEnum.V0_0_1, Set.of(GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2_OR_DOCK3)),

    /**
     * 열화상 카메라 게인 모드 설정
     * 
     * 열화상 카메라의 게인 모드를 설정합니다.
     * 지원 디바이스: DOCK, DOCK2
     */
    THERMAL_GAIN_MODE("thermal_gain_mode", ThermalGainModeSet.class, CloudSDKVersionEnum.V0_0_1, Set.of(GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2_OR_DOCK3)),

    /**
     * 열화상 카메라 등온선 상태 설정
     * 
     * 열화상 카메라의 등온선 기능 상태를 설정합니다.
     * 지원 디바이스: DOCK, DOCK2
     */
    THERMAL_ISOTHERM_STATE("thermal_isotherm_state", ThermalIsothermStateSet.class, CloudSDKVersionEnum.V0_0_1, Set.of(GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2_OR_DOCK3)),

    /**
     * 열화상 카메라 등온선 상한 설정
     * 
     * 열화상 카메라의 등온선 상한 값을 설정합니다.
     * 지원 디바이스: DOCK, DOCK2
     */
    THERMAL_ISOTHERM_UPPER_LIMIT("thermal_isotherm_upper_limit", ThermalIsothermUpperLimitSet.class, CloudSDKVersionEnum.V0_0_1, Set.of(GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2_OR_DOCK3)),

    /**
     * 열화상 카메라 등온선 하한 설정
     * 
     * 열화상 카메라의 등온선 하한 값을 설정합니다.
     * 지원 디바이스: DOCK, DOCK2
     */
    THERMAL_ISOTHERM_LOWER_LIMIT("thermal_isotherm_lower_limit", ThermalIsothermLowerLimitSet.class, CloudSDKVersionEnum.V0_0_1, Set.of(GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2_OR_DOCK3)),

    /**
     * RTH 모드 설정
     * 
     * 드론의 Return-to-Home 모드를 설정합니다.
     * 지원 디바이스: DOCK2
     */
    RTH_MODE("rth_mode", DockDroneRthMode.class, CloudSDKVersionEnum.V1_0_0, Set.of(GatewayTypeEnum.DOCK2_OR_DOCK3)),

    /**
     * 사용자 경험 개선 설정
     * 
     * 사용자 경험을 개선하기 위한 설정을 제어합니다.
     * 지원 디바이스: DOCK, DOCK2
     */
    USER_EXPERIENCE_IMPROVEMENT("user_experience_improvement", UserExperienceImprovementSet.class, CloudSDKVersionEnum.V1_0_0, Set.of(GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2_OR_DOCK3)),

    /**
     * 커맨더 모드 연결 끊김 동작 설정
     * 
     * 커맨더 모드에서 연결이 끊어졌을 때의 동작을 설정합니다.
     * 지원 디바이스: DOCK, DOCK2
     */
    COMMANDER_MODE_LOST_ACTION("commander_mode_lost_action", DockDroneCommanderModeLostAction.class, CloudSDKVersionEnum.V1_0_0, Set.of(GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2_OR_DOCK3)),

    /**
     * 커맨더 비행 고도 설정
     * 
     * 커맨더 모드에서의 비행 고도를 설정합니다.
     * 지원 디바이스: DOCK, DOCK2
     */
    COMMANDER_FLIGHT_HEIGHT("commander_flight_height", DockDroneCommanderFlightHeight.class, CloudSDKVersionEnum.V1_0_0, Set.of(GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2_OR_DOCK3)),

    /**
     * 오프라인 맵 활성화 설정
     * 
     * 오프라인 맵 기능의 활성화 여부를 설정합니다.
     * 지원 디바이스: DOCK2
     */
    OFFLINE_MAP_ENABLE("offline_map_enable", DockDroneOfflineMapEnable.class, CloudSDKVersionEnum.V1_0_1, Set.of(GatewayTypeEnum.DOCK2_OR_DOCK3)),

    /**
     * 무음 모드 설정
     * 
     * 도크의 무음 모드를 설정합니다.
     * 지원 디바이스: DOCK
     */
    SILENT_MODE("silent_mode", DockSilentMode.class, CloudSDKVersionEnum.V1_0_2, Set.of(GatewayTypeEnum.DOCK));

    /**
     * 속성 이름
     * 
     * 각 설정을 구분하는 고유한 속성 이름입니다.
     */
    private final String property;

    /**
     * 속성 설정 클래스
     * 
     * 해당 속성의 설정 데이터를 담는 클래스입니다.
     */
    private final Class<? extends BaseModel> clazz;

    /**
     * 지원 시작 버전
     * 
     * 해당 속성이 지원되기 시작한 SDK 버전입니다.
     */
    private final CloudSDKVersionEnum since;

    /**
     * 지원 디바이스 목록
     * 
     * 해당 속성을 지원하는 디바이스들의 목록입니다.
     */
    private final Set<GatewayTypeEnum> supportedDevices;

    /**
     * 사용 중단 여부
     * 
     * 해당 속성이 사용 중단되었는지 여부입니다.
     */
    private boolean deprecated;

    /**
     * 속성 설정 열거형을 생성합니다.
     * 
     * @param property 속성 이름
     * @param clazz 속성 설정 클래스
     * @param since 지원 시작 버전
     * @param supportedDevices 지원 디바이스 목록
     */
    PropertySetEnum(String property, Class<? extends BaseModel> clazz, CloudSDKVersionEnum since, Set<GatewayTypeEnum> supportedDevices) {
        this.property = property;
        this.clazz = clazz;
        this.since = since;
        this.supportedDevices = supportedDevices;
    }

    /**
     * 속성 설정 열거형을 생성합니다 (사용 중단 포함).
     * 
     * @param property 속성 이름
     * @param clazz 속성 설정 클래스
     * @param since 지원 시작 버전
     * @param supportedDevices 지원 디바이스 목록
     * @param deprecated 사용 중단 여부
     */
    PropertySetEnum(String property, Class<? extends BaseModel> clazz, CloudSDKVersionEnum since, Set<GatewayTypeEnum> supportedDevices, boolean deprecated) {
        this.property = property;
        this.clazz = clazz;
        this.since = since;
        this.supportedDevices = supportedDevices;
        this.deprecated = deprecated;
    }

    /**
     * 속성 이름을 반환합니다.
     * 
     * @return 속성 이름
     */
    public String getProperty() {
        return property;
    }

    /**
     * 속성 설정 클래스를 반환합니다.
     * 
     * @return 속성 설정 클래스
     */
    public Class<? extends BaseModel> getClazz() {
        return clazz;
    }

    /**
     * 지원 시작 버전을 반환합니다.
     * 
     * @return 지원 시작 버전
     */
    public CloudSDKVersionEnum getSince() {
        return since;
    }

    /**
     * 지원 디바이스 목록을 반환합니다.
     * 
     * @return 지원 디바이스 목록
     */
    public Set<GatewayTypeEnum> getSupportedDevices() {
        return supportedDevices;
    }

    /**
     * 사용 중단 여부를 반환합니다.
     * 
     * @return 사용 중단 여부
     */
    public boolean isDeprecated() {
        return deprecated;
    }

    /**
     * 속성 이름으로 열거형을 찾습니다.
     * 
     * 주어진 속성 이름에 해당하는 열거형을 반환합니다.
     * 해당하는 속성이 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param property 찾을 속성 이름
     * @return 해당하는 PropertySetEnum 열거형
     * @throws CloudSDKException 해당하는 속성이 없을 경우
     */
    public static PropertySetEnum find(String property) {
        return Arrays.stream(values()).filter(propertyEnum -> propertyEnum.property.equals(property)).findAny()
                .orElseThrow(() -> new CloudSDKException(PropertySetEnum.class, property));
    }
}
