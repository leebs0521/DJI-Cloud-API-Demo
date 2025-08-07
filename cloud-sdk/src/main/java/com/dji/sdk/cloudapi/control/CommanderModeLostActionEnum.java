package com.dji.sdk.cloudapi.control;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 커맨더 모드 신호 손실 액션 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 커맨더 모드에서 신호가 손실되었을 때 수행할 액션을 정의합니다.
 * 계속 진행하거나 RC 신호 손실 액션을 실행하는 옵션을 제공합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/6
 */
public enum CommanderModeLostActionEnum {

    /**
     * 계속 진행
     * 신호 손실 시에도 현재 작업을 계속 진행
     */
    CONTINUE(0),

    /**
     * RC 신호 손실 액션 실행
     * 신호 손실 시 RC 신호 손실 액션을 실행
     */
    EXECUTE_RC_LOST_ACTION(1);

    /**
     * 액션 값
     */
    private final int action;

    /**
     * 커맨더 모드 신호 손실 액션 열거형 생성자
     * 
     * @param action 액션 값
     */
    CommanderModeLostActionEnum(int action) {
        this.action = action;
    }

    /**
     * 액션 값을 반환합니다.
     * 
     * @return 액션 값
     */
    @JsonValue
    public int getAction() {
        return action;
    }

    /**
     * 정수 값으로 커맨더 모드 신호 손실 액션을 찾습니다.
     * 
     * @param action 찾을 액션 값
     * @return 찾은 커맨더 모드 신호 손실 액션 열거형
     * @throws CloudSDKException 해당하는 액션을 찾을 수 없는 경우
     */
    @JsonCreator
    public static CommanderModeLostActionEnum find(int action) {
        return Arrays.stream(values()).filter(actionEnum -> actionEnum.action == action).findAny()
                .orElseThrow(() -> new CloudSDKException(CommanderModeLostActionEnum.class, action));
    }
}
