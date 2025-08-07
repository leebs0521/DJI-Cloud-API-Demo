package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 에어컨 상태 열거형 클래스
 * 
 * 이 클래스는 에어컨의 동작 상태를 정의합니다.
 * 대기, 냉각, 난방, 제습 등의 상태를 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public enum AirConditionerStateEnum {

    /**
     * 대기 (상태값: 0)
     */
    IDLE(0),

    /**
     * 냉각 (상태값: 1)
     */
    COOL(1),

    /**
     * 난방 (상태값: 2)
     */
    HEAT(2),

    /**
     * 제습 (상태값: 3)
     */
    DEHUMIDIFICATION(3),

    /**
     * 냉각 종료 (상태값: 4)
     */
    COOLING_EXIT(4),

    /**
     * 난방 종료 (상태값: 5)
     */
    HEATING_EXIT(5),

    /**
     * 제습 종료 (상태값: 6)
     */
    DEHUMIDIFICATION_EXIT(6),

    /**
     * 냉각 준비 (상태값: 7)
     */
    COOLING_PREPARATION(7),

    /**
     * 난방 준비 (상태값: 8)
     */
    HEATING_PREPARATION(8),

    /**
     * 제습 준비 (상태값: 9)
     */
    DEHUMIDIFICATION_PREPARATION(9),

    /**
     * 연결 해제 (상태값: 32767)
     */
    DISCONNECTED(32767),
    ;

    /**
     * 에어컨 상태 정수값
     */
    private final int state;

    /**
     * 에어컨 상태 열거형 생성자
     * 
     * @param state 에어컨 상태 정수값
     */
    AirConditionerStateEnum(int state) {
        this.state = state;
    }

    /**
     * 에어컨 상태 정수값을 반환합니다.
     * 
     * @return 에어컨 상태 정수값
     */
    @JsonValue
    public int getState() {
        return state;
    }

    /**
     * 정수값으로 에어컨 상태를 찾습니다.
     * 
     * @param state 찾을 에어컨 상태 정수값
     * @return 찾은 에어컨 상태 열거형
     * @throws CloudSDKException 해당하는 상태를 찾을 수 없는 경우
     */
    @JsonCreator
    public static AirConditionerStateEnum find(int state) {
        return Arrays.stream(values()).filter(stateEnum -> stateEnum.state == state).findAny()
            .orElseThrow(() -> new CloudSDKException(AirConditionerStateEnum.class, state));
    }

}
