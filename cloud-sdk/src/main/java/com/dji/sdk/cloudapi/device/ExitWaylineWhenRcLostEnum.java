package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * RC 연결 손실 시 웨이라인 종료 열거형 클래스
 * 
 * 이 클래스는 RC 연결이 손실되었을 때 웨이라인 임무의 동작을 정의합니다.
 * 계속 실행, RC 손실 액션 실행 등의 동작을 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/6
 */
public enum ExitWaylineWhenRcLostEnum {

    /**
     * 계속 실행 (액션값: 0)
     */
    CONTINUE(0),

    /**
     * RC 손실 액션 실행 (액션값: 1)
     */
    EXECUTE_RC_LOST_ACTION(1);

    /**
     * RC 연결 손실 시 웨이라인 종료 액션 정수값
     */
    private final int action;

    /**
     * RC 연결 손실 시 웨이라인 종료 열거형 생성자
     * 
     * @param action RC 연결 손실 시 웨이라인 종료 액션 정수값
     */
    ExitWaylineWhenRcLostEnum(int action) {
        this.action = action;
    }

    /**
     * RC 연결 손실 시 웨이라인 종료 액션 정수값을 반환합니다.
     * 
     * @return RC 연결 손실 시 웨이라인 종료 액션 정수값
     */
    @JsonValue
    public int getAction() {
        return action;
    }

    /**
     * 정수값으로 RC 연결 손실 시 웨이라인 종료 액션을 찾습니다.
     * 
     * @param action 찾을 RC 연결 손실 시 웨이라인 종료 액션 정수값
     * @return 찾은 RC 연결 손실 시 웨이라인 종료 열거형
     * @throws CloudSDKException 해당하는 액션을 찾을 수 없는 경우
     */
    @JsonCreator
    public static ExitWaylineWhenRcLostEnum find(int action) {
        return Arrays.stream(values()).filter(actionEnum -> actionEnum.action == action).findAny()
                .orElseThrow(() -> new CloudSDKException(ExitWaylineWhenRcLostEnum.class, action));
    }
}
