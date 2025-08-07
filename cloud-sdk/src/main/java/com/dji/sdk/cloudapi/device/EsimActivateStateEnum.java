package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * eSIM 활성화 상태 열거형 클래스
 * 
 * 이 클래스는 eSIM의 활성화 상태를 정의합니다.
 * 비활성화, 활성화 등의 상태를 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public enum EsimActivateStateEnum {

    /**
     * 비활성화 (상태값: 0)
     */
    INACTIVATED(0),

    /**
     * 활성화 (상태값: 1)
     */
    ACTIVATED(1),

    ;

    /**
     * eSIM 활성화 상태 정수값
     */
    private final int state;

    /**
     * eSIM 활성화 상태 열거형 생성자
     * 
     * @param state eSIM 활성화 상태 정수값
     */
    EsimActivateStateEnum(int state) {
        this.state = state;
    }

    /**
     * eSIM 활성화 상태 정수값을 반환합니다.
     * 
     * @return eSIM 활성화 상태 정수값
     */
    @JsonValue
    public int getState() {
        return state;
    }

    /**
     * 정수값으로 eSIM 활성화 상태를 찾습니다.
     * 
     * @param state 찾을 eSIM 활성화 상태 정수값
     * @return 찾은 eSIM 활성화 상태 열거형
     * @throws CloudSDKException 해당하는 상태를 찾을 수 없는 경우
     */
    @JsonCreator
    public static EsimActivateStateEnum find(int state) {
        return Arrays.stream(values()).filter(stateEnum -> stateEnum.state == state).findAny()
            .orElseThrow(() -> new CloudSDKException(EsimActivateStateEnum.class, state));
    }

}
