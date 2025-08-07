package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 배터리 저장 모드 열거형 클래스
 * 
 * 이 클래스는 배터리의 저장 모드를 정의합니다.
 * 계획, 비상 등의 저장 모드를 나타냅니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/14
 */
public enum BatteryStoreModeEnum {

    /**
     * 계획 (모드값: 1)
     */
    PLAN(1),

    /**
     * 비상 (모드값: 2)
     */
    EMERGENCY(2);

    /**
     * 배터리 저장 모드 정수값
     */
    private final int mode;

    /**
     * 배터리 저장 모드 열거형 생성자
     * 
     * @param mode 배터리 저장 모드 정수값
     */
    BatteryStoreModeEnum(int mode) {
        this.mode = mode;
    }

    /**
     * 배터리 저장 모드 정수값을 반환합니다.
     * 
     * @return 배터리 저장 모드 정수값
     */
    @JsonValue
    public int getMode() {
        return mode;
    }

    /**
     * 정수값으로 배터리 저장 모드를 찾습니다.
     * 
     * @param mode 찾을 배터리 저장 모드 정수값
     * @return 찾은 배터리 저장 모드 열거형
     * @throws CloudSDKException 해당하는 모드를 찾을 수 없는 경우
     */
    @JsonCreator
    public static BatteryStoreModeEnum find(int mode) {
        return Arrays.stream(BatteryStoreModeEnum.values()).filter(modeEnum -> modeEnum.mode == mode).findAny()
                .orElseThrow(() -> new CloudSDKException(BatteryStoreModeEnum.class, mode));
    }
}
