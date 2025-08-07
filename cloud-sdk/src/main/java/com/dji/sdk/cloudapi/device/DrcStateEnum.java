package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * DRC 상태 열거형 클래스
 * 
 * 이 클래스는 DRC(Direct Remote Control) 연결 상태를 정의합니다.
 * 연결 해제, 연결 중, 연결됨 등의 상태를 나타냅니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/2/28
 */
public enum DrcStateEnum {

    /**
     * 연결 해제 (상태값: 0)
     */
    DISCONNECTED(0),

    /**
     * 연결 중 (상태값: 1)
     */
    CONNECTING(1),

    /**
     * 연결됨 (상태값: 2)
     */
    CONNECTED(2);

    /**
     * DRC 상태 정수값
     */
    private final int state;

    /**
     * DRC 상태 열거형 생성자
     * 
     * @param state DRC 상태 정수값
     */
    DrcStateEnum(int state) {
        this.state = state;
    }

    /**
     * DRC 상태 정수값을 반환합니다.
     * 
     * @return DRC 상태 정수값
     */
    @JsonValue
    public int getState() {
        return state;
    }

    /**
     * 정수값으로 DRC 상태를 찾습니다.
     * 
     * @param state 찾을 DRC 상태 정수값
     * @return 찾은 DRC 상태 열거형
     * @throws CloudSDKException 해당하는 상태를 찾을 수 없는 경우
     */
    @JsonCreator
    public static DrcStateEnum find(int state) {
        return Arrays.stream(values()).filter(drcState -> drcState.state == state).findAny()
                .orElseThrow(() -> new CloudSDKException(DrcStateEnum.class, state));
    }
}
