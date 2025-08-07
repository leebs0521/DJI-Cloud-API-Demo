package com.dji.sdk.cloudapi.debug;

import com.dji.sdk.cloudapi.device.SwitchActionEnum;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 알람 상태 전환 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 알람 상태를 전환하기 위한 요청을 정의합니다.
 * 스위치 액션을 포함하여 알람의 켜기/끄기 상태를 변경합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/25
 */
public class AlarmStateSwitchRequest extends BaseModel {

    /**
     * 스위치 액션 (필수)
     * 알람 상태 전환 액션 (켜기/끄기)
     */
    @NotNull
    private SwitchActionEnum action;

    /**
     * 기본 생성자
     */
    public AlarmStateSwitchRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "AlarmStateSwitchRequest{" +
                "action=" + action +
                '}';
    }

    /**
     * 스위치 액션을 반환합니다.
     * 
     * @return 스위치 액션
     */
    public SwitchActionEnum getAction() {
        return action;
    }

    /**
     * 스위치 액션을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param action 설정할 스위치 액션
     * @return 현재 AlarmStateSwitchRequest 객체
     */
    public AlarmStateSwitchRequest setAction(SwitchActionEnum action) {
        this.action = action;
        return this;
    }
}
