package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * SIM 카드 상태 열거형 클래스
 * 
 * 이 클래스는 물리 SIM 카드의 상태를 정의합니다.
 * 카드 없음, 삽입됨 등의 상태를 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public enum SimCardStateEnum {

    /**
     * SIM 카드 없음 (상태값: 0)
     */
    NO_CARD(0),

    /**
     * SIM 카드 삽입됨 (상태값: 1)
     */
    INSERTED(1),

    ;

    /**
     * 상태 정수값
     */
    private final int state;

    /**
     * SIM 카드 상태 열거형 생성자
     * 
     * @param state 상태 정수값
     */
    SimCardStateEnum(int state) {
        this.state = state;
    }

    /**
     * 상태 정수값을 반환합니다.
     * 
     * @return 상태 정수값
     */
    @JsonValue
    public int getState() {
        return state;
    }

    /**
     * 정수값으로 SIM 카드 상태를 찾습니다.
     * 
     * @param state 찾을 상태 정수값
     * @return 찾은 SIM 카드 상태 열거형
     * @throws CloudSDKException 해당하는 상태를 찾을 수 없는 경우
     */
    @JsonCreator
    public static SimCardStateEnum find(int state) {
        return Arrays.stream(values()).filter(stateEnum -> stateEnum.state == state).findAny()
                .orElseThrow(() -> new CloudSDKException(SimCardStateEnum.class, state));
    }

}
