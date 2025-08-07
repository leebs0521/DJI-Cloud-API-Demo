package com.dji.sdk.cloudapi.debug;

import com.dji.sdk.common.BaseModel;
import com.dji.sdk.exception.CloudSDKException;

import java.util.Arrays;

/**
 * 디버그 메서드 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 디버그 관련 메서드를 정의합니다.
 * 디버그 모드, 보조 조명, 디바이스 제어, 배터리 관리, ESIM 관리 등 다양한 디버그 메서드를 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/14
 */
public enum DebugMethodEnum {

    /**
     * 디버그 모드 열기
     * 디버그 모드를 활성화
     */
    DEBUG_MODE_OPEN("debug_mode_open", null),

    /**
     * 디버그 모드 닫기
     * 디버그 모드를 비활성화
     */
    DEBUG_MODE_CLOSE("debug_mode_close", null),

    /**
     * 보조 조명 열기
     * 보조 조명을 켬
     */
    SUPPLEMENT_LIGHT_OPEN("supplement_light_open", null),

    /**
     * 보조 조명 닫기
     * 보조 조명을 끔
     */
    SUPPLEMENT_LIGHT_CLOSE("supplement_light_close", null),

    /**
     * 디바이스 재부팅
     * 디바이스를 재부팅
     */
    DEVICE_REBOOT("device_reboot", null),

    /**
     * 드론 열기
     * 드론 전원을 켬
     */
    DRONE_OPEN("drone_open", null),

    /**
     * 드론 닫기
     * 드론 전원을 끔
     */
    DRONE_CLOSE("drone_close", null),

    /**
     * 드론 포맷
     * 드론 데이터를 포맷
     */
    DRONE_FORMAT("drone_format", null),

    /**
     * 디바이스 포맷
     * 디바이스 데이터를 포맷
     */
    DEVICE_FORMAT("device_format", null),

    /**
     * 커버 열기
     * 독 커버를 염
     */
    COVER_OPEN("cover_open", null),

    /**
     * 커버 닫기
     * 독 커버를 닫음
     */
    COVER_CLOSE("cover_close", null),

    /**
     * 퍼터 열기
     * 퍼터를 염
     */
    PUTTER_OPEN("putter_open", null),

    /**
     * 퍼터 닫기
     * 퍼터를 닫음
     */
    PUTTER_CLOSE("putter_close", null),

    /**
     * 충전 열기
     * 충전을 켬
     */
    CHARGE_OPEN("charge_open", null),

    /**
     * 충전 닫기
     * 충전을 끔
     */
    CHARGE_CLOSE("charge_close", null),

    /**
     * 배터리 유지보수 전환
     * 배터리 유지보수 모드 전환
     */
    BATTERY_MAINTENANCE_SWITCH("battery_maintenance_switch", BatteryMaintenanceSwitchRequest.class),

    /**
     * 알람 상태 전환
     * 알람 상태 전환
     */
    ALARM_STATE_SWITCH("alarm_state_switch", AlarmStateSwitchRequest.class),

    /**
     * 배터리 저장 모드 전환
     * 배터리 저장 모드 전환
     */
    BATTERY_STORE_MODE_SWITCH("battery_store_mode_switch", BatteryStoreModeSwitchRequest.class),

    /**
     * SDR 작업 모드 전환
     * SDR 작업 모드 전환
     */
    SDR_WORKMODE_SWITCH("sdr_workmode_switch", SdrWorkmodeSwitchRequest.class),

    /**
     * 공기조화기 모드 전환
     * 공기조화기 모드 전환
     */
    AIR_CONDITIONER_MODE_SWITCH("air_conditioner_mode_switch", AirConditionerModeSwitchRequest.class),

    /**
     * ESIM 활성화
     * ESIM 활성화
     */
    ESIM_ACTIVATE("esim_activate", EsimActivateRequest.class),

    /**
     * SIM 슬롯 전환
     * SIM 슬롯 전환
     */
    SIM_SLOT_SWITCH("sim_slot_switch", SimSlotSwitchRequest.class),

    /**
     * ESIM 운영자 전환
     * ESIM 운영자 전환
     */
    ESIM_OPERATOR_SWITCH("esim_operator_switch", EsimOperatorSwitchRequest.class),

    ;

    /**
     * 메서드 이름
     */
    private final String method;

    /**
     * 요청 클래스
     */
    private final Class<? extends BaseModel> clazz;

    /**
     * 디버그 메서드 열거형 생성자
     * 
     * @param method 메서드 이름
     * @param clazz 요청 클래스
     */
    DebugMethodEnum(String method, Class<? extends BaseModel> clazz) {
        this.method = method;
        this.clazz = clazz;
    }

    /**
     * 메서드 이름을 반환합니다.
     * 
     * @return 메서드 이름
     */
    public String getMethod() {
        return method;
    }

    /**
     * 요청 클래스를 반환합니다.
     * 
     * @return 요청 클래스
     */
    public Class<? extends BaseModel> getClazz() {
        return clazz;
    }

    /**
     * 문자열로 디버그 메서드를 찾습니다.
     * 
     * @param method 찾을 메서드 이름
     * @return 찾은 디버그 메서드 열거형
     * @throws CloudSDKException 해당하는 메서드를 찾을 수 없는 경우
     */
    public static DebugMethodEnum find(String method) {
        return Arrays.stream(values()).filter(methodEnum -> methodEnum.method.equals(method)).findAny()
            .orElseThrow(() -> new CloudSDKException(DebugMethodEnum.class, method));
    }
}
