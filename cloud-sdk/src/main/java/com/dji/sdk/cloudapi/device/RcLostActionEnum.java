package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * RC 연결 손실 액션 열거형 클래스
 * 
 * 이 클래스는 RC 연결이 손실되었을 때 드론이 수행할 액션을 정의합니다.
 * 호버, 착륙, 홈 복귀 등의 안전한 동작을 통해 드론을 보호합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/1
 */
public enum RcLostActionEnum {

    /**
     * 호버 (정수값: 0)
     */
    HOVER(0),

    /**
     * 착륙 (정수값: 1)
     */
    LAND(1),

    /**
     * 홈 복귀 (정수값: 2)
     */
    RETURN_HOME(2);

    /**
     * 액션 정수값
     */
    private final int action;

    /**
     * RC 연결 손실 액션 열거형 생성자
     * 
     * @param action 액션 정수값
     */
    RcLostActionEnum(int action) {
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
     * 정수값으로 RC 연결 손실 액션을 찾습니다.
     * 
     * @param action 찾을 액션 정수값
     * @return 찾은 RC 연결 손실 액션 열거형
     * @throws CloudSDKException 해당하는 액션을 찾을 수 없는 경우
     */
    @JsonCreator
    public static RcLostActionEnum find(int action) {
        return Arrays.stream(values()).filter(actionEnum -> actionEnum.ordinal() == action).findAny()
                .orElseThrow(() -> new CloudSDKException(RcLostActionEnum.class, action));
    }
}
