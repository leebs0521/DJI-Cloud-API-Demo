package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 포커스 상태 열거형 클래스
 * 
 * 이 클래스는 카메라의 포커스 상태를 정의합니다.
 * 대기, 포커싱, 성공, 실패 등의 상태를 나타냅니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public enum FocusStateEnum {

    /**
     * 대기 (상태값: 0)
     */
    IDLE(0),

    /**
     * 포커싱 (상태값: 1)
     */
    FOCUSING(1),

    /**
     * 성공 (상태값: 2)
     */
    SUCCESS(2),

    /**
     * 실패 (상태값: 3)
     */
    FAILED(3),

    ;

    /**
     * 포커스 상태 정수값
     */
    private final int state;

    /**
     * 포커스 상태 열거형 생성자
     * 
     * @param state 포커스 상태 정수값
     */
    FocusStateEnum(int state) {
        this.state = state;
    }

    /**
     * 포커스 상태 정수값을 반환합니다.
     * 
     * @return 포커스 상태 정수값
     */
    @JsonValue
    public int getState() {
        return state;
    }

    /**
     * 정수값으로 포커스 상태를 찾습니다.
     * 
     * @param state 찾을 포커스 상태 정수값
     * @return 찾은 포커스 상태 열거형
     * @throws CloudSDKException 해당하는 상태를 찾을 수 없는 경우
     */
    @JsonCreator
    public static FocusStateEnum find(int state) {
        return Arrays.stream(values()).filter(stateEnum -> stateEnum.state == state).findAny()
            .orElseThrow(() -> new CloudSDKException(FocusStateEnum.class, state));
    }

}
