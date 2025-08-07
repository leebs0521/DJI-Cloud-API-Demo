package com.dji.sdk.cloudapi.debug;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 공기조화기 모드 전환 액션 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 공기조화기의 모드 전환 액션을 정의합니다.
 * 대기 모드, 냉각 모드, 난방 모드, 제습 모드 등을 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/28
 */
public enum AirConditionerModeSwitchActionEnum {

    /**
     * 대기 모드
     * 공기조화기가 대기 상태인 모드
     */
    IDLE_MODE(0),

    /**
     * 냉각 모드
     * 공기조화기가 냉각 작동 중인 모드
     */
    COOLING_MODE(1),

    /**
     * 난방 모드
     * 공기조화기가 난방 작동 중인 모드
     */
    heating_mode(2),

    /**
     * 제습 모드
     * 공기조화기가 제습 작동 중인 모드
     */
    DEHUMIDIFICATION_MODE(3);

    /**
     * 액션 값
     */
    private final int action;

    /**
     * 공기조화기 모드 전환 액션 열거형 생성자
     * 
     * @param action 액션 값
     */
    AirConditionerModeSwitchActionEnum(int action) {
        this.action = action;
    }

    /**
     * 액션 값을 반환합니다.
     * 
     * @return 액션 값
     */
    @JsonValue
    public int getAction() {
        return action;
    }

    /**
     * 정수 값으로 공기조화기 모드 전환 액션을 찾습니다.
     * 
     * @param action 찾을 액션 값
     * @return 찾은 공기조화기 모드 전환 액션 열거형
     * @throws CloudSDKException 해당하는 액션을 찾을 수 없는 경우
     */
    @JsonCreator
    public static AirConditionerModeSwitchActionEnum find(int action) {
        return Arrays.stream(values()).filter(actionEnum -> actionEnum.action == action).findAny()
                .orElseThrow(() -> new CloudSDKException(AirConditionerModeSwitchActionEnum.class, action));
    }
}
