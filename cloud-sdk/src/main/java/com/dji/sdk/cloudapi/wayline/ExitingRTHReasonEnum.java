package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 홈포인트 귀환 종료 이유 열거형
 * 
 * 이 열거형은 드론이 홈포인트 귀환(RTH) 상태에서
 * 벗어나는 이유를 정의합니다.
 * 
 * 주요 구성 요소:
 * - 조이스틱 입력: ADD_JOYSTICK_THROTTLE, ADD_JOYSTICK_PITCH
 * - 시스템 오류: INITIALIZATION_FAILED, GPS_AND_VIO_ARE_FALSE
 * - 안전 조건: SURROUNDED_BY_OBSTACLES, FLIGHT_RESTRICTION
 * - 센서 문제: NO_GPS, ERROR_OF_GPS_AND_VIO
 * - 기타: SHORT_DISTANCE_BACKTRACKING, TRIGGER_RTH
 * 
 * 이 열거형은 드론이 홈포인트 귀환을 중단하는
 * 다양한 이유를 분류하고 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/6
 */
public enum ExitingRTHReasonEnum {

    /**
     * 조이스틱 스로틀 추가
     * 
     * 사용자가 조이스틱 스로틀을 조작하여
     * 홈포인트 귀환을 중단했습니다.
     */
    ADD_JOYSTICK_THROTTLE(0, "Add joystick throttle"),

    /**
     * 조이스틱 피치 추가
     * 
     * 사용자가 조이스틱 피치를 조작하여
     * 홈포인트 귀환을 중단했습니다.
     */
    ADD_JOYSTICK_PITCH(1, "Add joystick pitch"),

    /**
     * 초기화 실패
     * 
     * 행동 트리의 초기화가 실패하여
     * 홈포인트 귀환이 중단되었습니다.
     */
    INITIALIZATION_FAILED(2, "The initialization of behavior tree is failed"),

    /**
     * 장애물에 둘러싸임
     * 
     * 드론이 장애물에 둘러싸여
     * 홈포인트 귀환이 중단되었습니다.
     */
    SURROUNDED_BY_OBSTACLES(3, "Surrounded by obstacles"),

    /**
     * 비행 제한
     * 
     * 비행 제한이 트리거되어
     * 홈포인트 귀환이 중단되었습니다.
     */
    FLIGHT_RESTRICTION(4, "Flight restriction is triggered"),

    /**
     * 장애물이 너무 가까움
     * 
     * 장애물이 너무 가까워서
     * 홈포인트 귀환이 중단되었습니다.
     */
    OBSTACLE_IS_TOO_CLOSED(5, "Obstacle is too closed"),

    /**
     * GPS 신호 없음
     * 
     * GPS 신호가 없어서
     * 홈포인트 귀환이 중단되었습니다.
     */
    NO_GPS(6, "No GPS signal"),

    /**
     * GPS와 VIO 위치 출력 플래그가 거짓
     * 
     * GPS와 VIO 위치의 출력 플래그가 거짓이어서
     * 홈포인트 귀환이 중단되었습니다.
     */
    GPS_AND_VIO_ARE_FALSE(7, "The output flag of GPS and VIO location is false"),

    /**
     * GPS와 VIO 융합 위치 오류가 너무 큼
     * 
     * GPS와 VIO 융합 위치의 오류가 너무 커서
     * 홈포인트 귀환이 중단되었습니다.
     */
    ERROR_OF_GPS_AND_VIO(8, "The error of GPS and VIO fusion position is too large"),

    /**
     * 짧은 거리 역추적
     * 
     * 짧은 거리에서 역추적하여
     * 홈포인트 귀환이 중단되었습니다.
     */
    SHORT_DISTANCE_BACKTRACKING(9, "Backtrack in a short distance"),

    /**
     * 홈포인트 귀환 트리거
     * 
     * 짧은 거리에서 홈포인트 귀환이 트리거되어
     * 귀환이 중단되었습니다.
     */
    TRIGGER_RTH(10, "Trigger the RTH in a short distanc");

    /**
     * 홈포인트 귀환 종료 이유 값
     * 
     * 각 이유를 구분하는 정수 값입니다.
     */
    private final int reason;

    /**
     * 홈포인트 귀환 종료 이유 메시지
     * 
     * 각 이유에 대한 설명 메시지입니다.
     */
    private final String msg;

    /**
     * 홈포인트 귀환 종료 이유 열거형 생성자
     * 
     * @param reason 홈포인트 귀환 종료 이유 값
     * @param msg 홈포인트 귀환 종료 이유 메시지
     */
    ExitingRTHReasonEnum(int reason, String msg) {
        this.reason = reason;
        this.msg = msg;
    }

    /**
     * 홈포인트 귀환 종료 이유 값을 반환합니다.
     * 
     * @return 홈포인트 귀환 종료 이유 값
     */
    @JsonValue
    public int getReason() {
        return reason;
    }

    /**
     * 홈포인트 귀환 종료 이유 메시지를 반환합니다.
     * 
     * @return 홈포인트 귀환 종료 이유 메시지
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 홈포인트 귀환 종료 이유 값으로 이유를 찾습니다.
     * 
     * 주어진 이유 값에 해당하는 열거형을 반환합니다.
     * 해당하는 이유가 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param reason 찾을 홈포인트 귀환 종료 이유 값
     * @return 해당하는 ExitingRTHReasonEnum 열거형
     * @throws CloudSDKException 해당하는 이유가 없을 경우
     */
    @JsonCreator
    public static ExitingRTHReasonEnum find(int reason) {
        return Arrays.stream(values()).filter(reasonEnum -> reasonEnum.reason == reason).findAny()
                .orElseThrow(() -> new CloudSDKException(ExitingRTHReasonEnum.class, reason));
    }
}
