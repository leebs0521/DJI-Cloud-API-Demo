package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.cloudapi.device.ExitWaylineWhenRcLostEnum;
import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 홈포인트 귀환 종료 액션 열거형
 * 
 * 이 열거형은 드론이 홈포인트 귀환(RTH) 상태에서
 * 벗어날 때의 액션을 정의합니다.
 * 
 * 주요 구성 요소:
 * - EXIT: 홈포인트 귀환 종료 상태에서 벗어남
 * - Enter: 홈포인트 귀환 종료 상태로 진입
 * 
 * 이 열거형은 드론이 홈포인트 귀환을 중단하고
 * 다른 비행 모드로 전환할 때의 상태를 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/6
 */
public enum ExitingRTHActionEnum {

    /**
     * 홈포인트 귀환 종료 상태에서 벗어남
     * 
     * 홈포인트 귀환 종료 상태를 종료하고
     * 정상적인 비행 모드로 돌아갑니다.
     */
    EXIT(0, "Exit exiting RTH state"),

    /**
     * 홈포인트 귀환 종료 상태로 진입
     * 
     * 홈포인트 귀환 종료 상태로 진입하여
     * 귀환 중단 처리를 시작합니다.
     */
    Enter(1, "Enter exiting RTH state");

    /**
     * 액션 값
     * 
     * 각 액션을 구분하는 정수 값입니다.
     */
    private final int action;

    /**
     * 액션 메시지
     * 
     * 각 액션에 대한 설명 메시지입니다.
     */
    private final String msg;

    /**
     * 홈포인트 귀환 종료 액션 열거형 생성자
     * 
     * @param action 액션 값
     * @param msg 액션 메시지
     */
    ExitingRTHActionEnum(int action, String msg) {
        this.action = action;
        this.msg = msg;
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
     * 액션 메시지를 반환합니다.
     * 
     * @return 액션 메시지
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 액션 값으로 홈포인트 귀환 종료 액션을 찾습니다.
     * 
     * 주어진 액션 값에 해당하는 열거형을 반환합니다.
     * 해당하는 액션이 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param action 찾을 액션 값
     * @return 해당하는 ExitingRTHActionEnum 열거형
     * @throws CloudSDKException 해당하는 액션이 없을 경우
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static ExitingRTHActionEnum find(int action) {
        return Arrays.stream(values()).filter(actionEnum -> actionEnum.action == action).findAny()
                .orElseThrow(() -> new CloudSDKException(ExitWaylineWhenRcLostEnum.class, action));
    }
}
