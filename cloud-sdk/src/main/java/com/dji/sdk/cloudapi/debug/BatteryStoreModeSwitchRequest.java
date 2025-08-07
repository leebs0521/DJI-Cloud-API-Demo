package com.dji.sdk.cloudapi.debug;

import com.dji.sdk.cloudapi.device.BatteryStoreModeEnum;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 배터리 저장 모드 전환 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 배터리 저장 모드를 전환하기 위한 요청을 정의합니다.
 * 배터리 저장 모드를 포함하여 배터리의 저장 상태를 변경합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/25
 */
public class BatteryStoreModeSwitchRequest extends BaseModel {

    /**
     * 배터리 저장 모드 액션 (필수)
     * 전환할 배터리 저장 모드
     */
    @NotNull
    private BatteryStoreModeEnum action;

    /**
     * 기본 생성자
     */
    public BatteryStoreModeSwitchRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "BatteryStoreModeSwitchRequest{" +
                "action=" + action +
                '}';
    }

    /**
     * 배터리 저장 모드 액션을 반환합니다.
     * 
     * @return 배터리 저장 모드 액션
     */
    public BatteryStoreModeEnum getAction() {
        return action;
    }

    /**
     * 배터리 저장 모드 액션을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param action 설정할 배터리 저장 모드 액션
     * @return 현재 BatteryStoreModeSwitchRequest 객체
     */
    public BatteryStoreModeSwitchRequest setAction(BatteryStoreModeEnum action) {
        this.action = action;
        return this;
    }
}
