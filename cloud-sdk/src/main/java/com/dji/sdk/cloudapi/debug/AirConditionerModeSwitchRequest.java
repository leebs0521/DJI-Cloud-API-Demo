package com.dji.sdk.cloudapi.debug;

import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 공기조화기 모드 전환 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 공기조화기의 모드를 전환하기 위한 요청을 정의합니다.
 * 공기조화기 모드 전환 액션을 포함하여 공기조화기의 작동 모드를 변경합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/25
 */
public class AirConditionerModeSwitchRequest extends BaseModel {

    /**
     * 공기조화기 모드 전환 액션 (필수)
     * 전환할 공기조화기 모드 액션
     */
    @NotNull
    private AirConditionerModeSwitchActionEnum action;

    /**
     * 기본 생성자
     */
    public AirConditionerModeSwitchRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "AirConditionerModeSwitchRequest{" +
                "action=" + action +
                '}';
    }

    /**
     * 공기조화기 모드 전환 액션을 반환합니다.
     * 
     * @return 공기조화기 모드 전환 액션
     */
    public AirConditionerModeSwitchActionEnum getAction() {
        return action;
    }

    /**
     * 공기조화기 모드 전환 액션을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param action 설정할 공기조화기 모드 전환 액션
     * @return 현재 AirConditionerModeSwitchRequest 객체
     */
    public AirConditionerModeSwitchRequest setAction(AirConditionerModeSwitchActionEnum action) {
        this.action = action;
        return this;
    }
}
