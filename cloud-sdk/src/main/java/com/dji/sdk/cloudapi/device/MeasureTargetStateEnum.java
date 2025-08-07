package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 측정 타겟 상태 열거형 클래스
 * 
 * 이 클래스는 측정 대상의 상태를 정의합니다.
 * 정상, 너무 가까움, 너무 멀음, 신호 없음 등의 상태를 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public enum MeasureTargetStateEnum {

    /**
     * 정상 (상태값: 0)
     */
    NORMAL(0),

    /**
     * 너무 가까움 (상태값: 1)
     */
    TOO_CLOSE(1),

    /**
     * 너무 멀음 (상태값: 2)
     */
    TOO_FAR(2),

    /**
     * 신호 없음 (상태값: 3)
     */
    NO_SIGNAL(3),
    ;

    /**
     * 측정 타겟 상태 정수값
     */
    private final int state;

    /**
     * 측정 타겟 상태 열거형 생성자
     * 
     * @param state 측정 타겟 상태 정수값
     */
    MeasureTargetStateEnum(int state) {
        this.state = state;
    }

    /**
     * 측정 타겟 상태 정수값을 반환합니다.
     * 
     * @return 측정 타겟 상태 정수값
     */
    @JsonValue
    public int getState() {
        return state;
    }

    /**
     * 정수값으로 측정 타겟 상태를 찾습니다.
     * 
     * @param state 찾을 측정 타겟 상태 정수값
     * @return 찾은 측정 타겟 상태 열거형
     * @throws CloudSDKException 해당하는 상태를 찾을 수 없는 경우
     */
    @JsonCreator
    public static MeasureTargetStateEnum find(int state) {
        return Arrays.stream(values()).filter(stateEnum -> stateEnum.state == state).findAny()
            .orElseThrow(() -> new CloudSDKException(MeasureTargetStateEnum.class, state));
    }

}
