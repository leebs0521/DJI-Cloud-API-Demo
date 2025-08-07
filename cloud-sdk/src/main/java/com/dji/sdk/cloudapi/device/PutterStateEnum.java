package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 퍼터 상태 열거형 클래스
 * 
 * 이 클래스는 퍼터(출구)의 상태를 정의합니다.
 * 닫힘, 열림, 반열림, 이상 등의 상태를 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public enum PutterStateEnum {

    /**
     * 닫힘 (상태값: 0)
     */
    CLOSED(0),

    /**
     * 열림 (상태값: 1)
     */
    OPENED(1),

    /**
     * 반열림 (상태값: 2)
     */
    HALF_OPEN(2),

    /**
     * 이상 (상태값: 3)
     */
    ABNORMAL(3),
    ;

    /**
     * 퍼터 상태 정수값
     */
    private final int state;

    /**
     * 퍼터 상태 열거형 생성자
     * 
     * @param state 퍼터 상태 정수값
     */
    PutterStateEnum(int state) {
        this.state = state;
    }

    /**
     * 퍼터 상태 정수값을 반환합니다.
     * 
     * @return 퍼터 상태 정수값
     */
    @JsonValue
    public int getState() {
        return state;
    }

    /**
     * 정수값으로 퍼터 상태를 찾습니다.
     * 
     * @param state 찾을 퍼터 상태 정수값
     * @return 찾은 퍼터 상태 열거형
     * @throws CloudSDKException 해당하는 상태를 찾을 수 없는 경우
     */
    @JsonCreator
    public static PutterStateEnum find(int state) {
        return Arrays.stream(values()).filter(stateEnum -> stateEnum.state == state).findAny()
            .orElseThrow(() -> new CloudSDKException(PutterStateEnum.class, state));
    }

}
