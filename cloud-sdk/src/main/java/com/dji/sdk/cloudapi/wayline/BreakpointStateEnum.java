package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 중단점 상태 열거형
 * 
 * 이 열거형은 웨이라인 비행에서 중단점의 상태를 정의합니다.
 * 중단점이 웨이라인 세그먼트에 있는지 웨이포인트에 있는지를
 * 구분하여 적절한 복구 로직을 적용할 수 있습니다.
 * 
 * 주요 구성 요소:
 * - WAYLINE_SEGMENT: 웨이라인 세그먼트 위
 * - WAYPOINT: 웨이포인트 위
 * 
 * 이 열거형은 웨이라인 비행 중단 시 드론의 위치를
 * 정확히 파악하고 적절한 복구 방법을 선택하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/6
 */
public enum BreakpointStateEnum {

    /**
     * 웨이라인 세그먼트 위
     * 
     * 중단점이 웨이라인 세그먼트 위에 있는 상태입니다.
     * 웨이포인트와 웨이포인트 사이의 경로 위에서 중단되었습니다.
     */
    WAYLINE_SEGMENT(0, "On the wayline segment"),

    /**
     * 웨이포인트 위
     * 
     * 중단점이 웨이포인트 위에 있는 상태입니다.
     * 특정 웨이포인트 위치에서 중단되었습니다.
     */
    WAYPOINT(1, "On the waypoint");

    /**
     * 중단점 상태 값
     * 
     * 각 중단점 상태를 구분하는 정수 값입니다.
     */
    private final int state;

    /**
     * 중단점 상태 메시지
     * 
     * 각 중단점 상태에 대한 설명 메시지입니다.
     */
    private final String msg;

    /**
     * 중단점 상태 열거형 생성자
     * 
     * @param state 중단점 상태 값
     * @param msg 중단점 상태 메시지
     */
    BreakpointStateEnum(int state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    /**
     * 중단점 상태 값을 반환합니다.
     * 
     * @return 중단점 상태 값
     */
    @JsonValue
    public int getState() {
        return state;
    }

    /**
     * 중단점 상태 메시지를 반환합니다.
     * 
     * @return 중단점 상태 메시지
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 중단점 상태 값으로 중단점 상태를 찾습니다.
     * 
     * 주어진 상태 값에 해당하는 열거형을 반환합니다.
     * 해당하는 상태가 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param state 찾을 중단점 상태 값
     * @return 해당하는 BreakpointStateEnum 열거형
     * @throws CloudSDKException 해당하는 상태가 없을 경우
     */
    @JsonCreator
    public static BreakpointStateEnum find(int state) {
        return Arrays.stream(values()).filter(stateEnum -> stateEnum.state == state).findAny()
                .orElseThrow(() -> new CloudSDKException(BreakpointStateEnum.class, state));
    }
}
