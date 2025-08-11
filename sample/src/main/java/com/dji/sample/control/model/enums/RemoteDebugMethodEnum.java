package com.dji.sample.control.model.enums;

import com.dji.sample.control.model.dto.*;
import com.dji.sample.control.service.impl.RemoteDebugHandler;
import com.dji.sdk.cloudapi.debug.DebugMethodEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

/**
 * 원격 디버깅 메서드 열거형 클래스
 * 
 * 원격 디버깅을 위한 메서드를 정의하는 열거형입니다.
 * 디버깅 모드, 디바이스 제어, 복귀 제어 등의 메서드를 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/14
 */
@Getter
public enum RemoteDebugMethodEnum {

    /** 디버깅 모드 열기 */
    DEBUG_MODE_OPEN(DebugMethodEnum.DEBUG_MODE_OPEN, false, RemoteDebugOpenState.class),

    /** 디버깅 모드 닫기 */
    DEBUG_MODE_CLOSE(DebugMethodEnum.DEBUG_MODE_CLOSE, false, null),

    /** 보조 조명 열기 */
    SUPPLEMENT_LIGHT_OPEN(DebugMethodEnum.SUPPLEMENT_LIGHT_OPEN, false, null),

    /** 보조 조명 닫기 */
    SUPPLEMENT_LIGHT_CLOSE(DebugMethodEnum.SUPPLEMENT_LIGHT_CLOSE, false, null),

    /** 복귀 */
    RETURN_HOME("return_home", false, ReturnHomeState.class),

    /** 복귀 취소 */
    RETURN_HOME_CANCEL("return_home_cancel", false, ReturnHomeCancelState.class),

    /** 디바이스 재부팅 */
    DEVICE_REBOOT(DebugMethodEnum.DEVICE_REBOOT, true, null),

    /** 드론 열기 */
    DRONE_OPEN(DebugMethodEnum.DRONE_OPEN, true, null),

    /** 드론 닫기 */
    DRONE_CLOSE(DebugMethodEnum.DRONE_CLOSE, true, null),

    /** 드론 포맷 */
    DRONE_FORMAT(DebugMethodEnum.DRONE_FORMAT, true, null),

    /** 디바이스 포맷 */
    DEVICE_FORMAT(DebugMethodEnum.DEVICE_FORMAT, true, null),

    /** 커버 열기 */
    COVER_OPEN(DebugMethodEnum.COVER_OPEN, true, null),

    /** 커버 닫기 */
    COVER_CLOSE(DebugMethodEnum.COVER_CLOSE, true, null),

    /** 퍼터 열기 */
    PUTTER_OPEN(DebugMethodEnum.PUTTER_OPEN, true, null),

    /** 퍼터 닫기 */
    PUTTER_CLOSE(DebugMethodEnum.PUTTER_CLOSE, true, null),

    /** 충전 열기 */
    CHARGE_OPEN(DebugMethodEnum.CHARGE_OPEN, true, null),

    /** 충전 닫기 */
    CHARGE_CLOSE(DebugMethodEnum.CHARGE_CLOSE, true, null),

    /** 배터리 유지보수 스위치 */
    BATTERY_MAINTENANCE_SWITCH(DebugMethodEnum.BATTERY_MAINTENANCE_SWITCH, false, AlarmState.class),

    /** 알람 상태 스위치 */
    ALARM_STATE_SWITCH(DebugMethodEnum.ALARM_STATE_SWITCH, false, AlarmState.class),
    
    /** 배터리 저장 모드 스위치 */
    BATTERY_STORE_MODE_SWITCH(DebugMethodEnum.BATTERY_STORE_MODE_SWITCH, false, BatteryStoreMode.class),

    /** SDR 작업 모드 스위치 */
    SDR_WORK_MODE_SWITCH(DebugMethodEnum.SDR_WORKMODE_SWITCH, false, LinkWorkMode.class),

    /** 에어컨 모드 스위치 */
    AIR_CONDITIONER_MODE_SWITCH(DebugMethodEnum.AIR_CONDITIONER_MODE_SWITCH, false, AirConditionerMode.class);

    /** 디버깅 메서드 열거형 */
    private DebugMethodEnum debugMethodEnum;

    /** 메서드 이름 */
    private String method;

    /** 진행 상황 표시 여부 */
    private boolean progress;
    
    /** 원격 디버깅 핸들러 클래스 */
    private Class<? extends RemoteDebugHandler> clazz;

    /**
     * 원격 디버깅 메서드 열거형 생성자 (DebugMethodEnum 사용)
     * 
     * @param debugMethodEnum 디버깅 메서드 열거형
     * @param progress 진행 상황 표시 여부
     * @param clazz 원격 디버깅 핸들러 클래스
     */
    RemoteDebugMethodEnum(DebugMethodEnum debugMethodEnum, boolean progress, Class<? extends RemoteDebugHandler> clazz) {
        this.debugMethodEnum = debugMethodEnum;
        this.progress = progress;
        this.clazz = clazz;
        this.method = debugMethodEnum.getMethod();
    }

    /**
     * 원격 디버깅 메서드 열거형 생성자 (문자열 메서드 사용)
     * 
     * @param method 메서드 이름
     * @param progress 진행 상황 표시 여부
     * @param clazz 원격 디버깅 핸들러 클래스
     */
    RemoteDebugMethodEnum(String method, boolean progress, Class<? extends RemoteDebugHandler> clazz) {
        this.debugMethodEnum = null;
        this.progress = progress;
        this.clazz = clazz;
        this.method = method;
    }

    /**
     * JSON 역직렬화를 위한 열거형 찾기
     * 
     * @param method 메서드 이름
     * @return 해당하는 원격 디버깅 메서드 열거형
     */
    @JsonCreator
    public static RemoteDebugMethodEnum find(String method) {
        return Arrays.stream(values())
                .filter(methodEnum -> methodEnum.method.equals(method)
                        || (Objects.nonNull(methodEnum.debugMethodEnum)
                            && methodEnum.debugMethodEnum.getMethod().equals(method)))
                .findAny()
                .orElseThrow();
    }

}
