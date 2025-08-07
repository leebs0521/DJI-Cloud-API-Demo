package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 열 상태 열거형 클래스
 * 
 * 이 클래스는 디바이스의 열 상태를 정의합니다.
 * 비활성화, 가열 중, 단열 등의 상태를 나타냅니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2022/11/3
 */
public enum HeatStateEnum {

    /**
     * 비활성화 (상태값: 0)
     */
    DISABLED(0),

    /**
     * 가열 중 (상태값: 1)
     */
    HEATING(1),

    /**
     * 단열 (상태값: 2)
     */
    INSULATION(2),

    ;

    /**
     * 열 상태 정수값
     */
    private final int state;

    /**
     * 열 상태 열거형 생성자
     * 
     * @param state 열 상태 정수값
     */
    HeatStateEnum(int state) {
        this.state = state;
    }

    /**
     * 열 상태 정수값을 반환합니다.
     * 
     * @return 열 상태 정수값
     */
    @JsonValue
    public int getState() {
        return state;
    }

    /**
     * 정수값으로 열 상태를 찾습니다.
     * 
     * @param state 찾을 열 상태 정수값
     * @return 찾은 열 상태 열거형
     * @throws CloudSDKException 해당하는 상태를 찾을 수 없는 경우
     */
    @JsonCreator
    public static HeatStateEnum find(int state) {
        return Arrays.stream(values()).filter(stateEnum -> stateEnum.state == state).findAny()
            .orElseThrow(() -> new CloudSDKException(HeatStateEnum.class, state));
    }

}
