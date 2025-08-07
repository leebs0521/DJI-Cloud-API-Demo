package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 카메라 상태 열거형 클래스
 * 
 * 이 클래스는 카메라의 상태를 정의합니다.
 * 대기, 작업 중 등의 상태를 나타냅니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/4/23
 */
public enum CameraStateEnum {

    /**
     * 대기 (상태값: 0)
     */
    IDLE(0),

    /**
     * 작업 중 (상태값: 1)
     */
    WORKING(1),
    ;

    /**
     * 카메라 상태 정수값
     */
    private final int state;

    /**
     * 카메라 상태 열거형 생성자
     * 
     * @param state 카메라 상태 정수값
     */
    CameraStateEnum(int state) {
        this.state = state;
    }

    /**
     * 카메라 상태 정수값을 반환합니다.
     * 
     * @return 카메라 상태 정수값
     */
    @JsonValue
    public int getState() {
        return state;
    }

    /**
     * 정수값으로 카메라 상태를 찾습니다.
     * 
     * @param state 찾을 카메라 상태 정수값
     * @return 찾은 카메라 상태 열거형
     * @throws CloudSDKException 해당하는 상태를 찾을 수 없는 경우
     */
    @JsonCreator
    public static CameraStateEnum find(int state) {
        return Arrays.stream(values()).filter(stateEnum -> stateEnum.state == state).findAny()
                .orElseThrow(() -> new CloudSDKException(CameraStateEnum.class, state));
    }
}
