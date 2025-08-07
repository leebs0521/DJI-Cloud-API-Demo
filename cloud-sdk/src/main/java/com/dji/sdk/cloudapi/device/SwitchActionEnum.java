package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 스위치 액션 열거형 클래스
 * 
 * 이 클래스는 스위치의 동작 상태를 정의합니다.
 * 기능의 활성화/비활성화를 나타내는 간단한 스위치 상태를 관리합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/28
 */
public enum SwitchActionEnum {

    /**
     * 비활성화 (정수값: 0)
     */
    DISABLE(0),

    /**
     * 활성화 (정수값: 1)
     */
    ENABLE(1);

    /**
     * 액션 정수값
     */
    private final int action;

    /**
     * 스위치 액션 열거형 생성자
     * 
     * @param action 액션 정수값
     */
    SwitchActionEnum(int action) {
        this.action = action;
    }

    /**
     * 액션 정수값을 반환합니다.
     * 
     * @return 액션 정수값
     */
    @JsonValue
    public int getAction() {
        return action;
    }

    /**
     * 정수값으로 스위치 액션을 찾습니다.
     * 
     * @param action 찾을 액션 정수값
     * @return 찾은 스위치 액션 열거형
     * @throws CloudSDKException 해당하는 액션을 찾을 수 없는 경우
     */
    @JsonCreator
    public static SwitchActionEnum find(int action) {
        return Arrays.stream(values()).filter(actionEnum -> actionEnum.action == action).findAny()
                .orElseThrow(() -> new CloudSDKException(SwitchActionEnum.class, action));
    }
}
