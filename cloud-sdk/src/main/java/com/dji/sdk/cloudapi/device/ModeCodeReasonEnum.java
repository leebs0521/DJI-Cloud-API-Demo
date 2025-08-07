package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 모드 코드 이유 열거형 클래스
 * 
 * 이 클래스는 드론의 모드 변경 이유를 정의합니다.
 * 저전력, RC 제어, 앱 제어, 지리적 제한 등의 이유를 나타냅니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/2/28
 */
public enum ModeCodeReasonEnum {

    /**
     * 의미 없음 (이유값: 0)
     */
    NO_MEANING(0),

    /**
     * 저전력 (이유값: 1)
     */
    LOW_POWER(1),

    /**
     * 저전압 (이유값: 2)
     */
    LOW_VOLTAGE(2),

    /**
     * 심각한 저전압 (이유값: 3)
     */
    SERIOUS_LOW_VOLTAGE(3),

    /**
     * RC 제어 (이유값: 4)
     */
    RC_CONTROL(4),

    /**
     * 앱 제어 (이유값: 5)
     */
    APP_CONTROL(5),

    /**
     * RC 신호 손실 (이유값: 6)
     */
    RC_SIGNAL_LOST(6),

    /**
     * 외부 장치 트리거 (이유값: 7)
     */
    EXTERNAL_DEVICE_TRIGGERED(7),

    /**
     * 지리적 제한 (이유값: 8)
     */
    GEO_ZONE(8),

    /**
     * 홈 포인트 너무 가까움 (이유값: 9)
     */
    HOME_POINT_TOO_CLOSED(9),

    /**
     * 홈 포인트 너무 멀음 (이유값: 10)
     */
    HOME_POINT_TOO_FAR(10),

    /**
     * 웨이포인트 임무 실행 중 (이유값: 11)
     */
    EXECUTING_WAYPOINT_MISSION(11),

    /**
     * 홈 포인트 도착 (이유값: 12)
     */
    ARRIVE_HOME_POINT(12),

    /**
     * 2차 제한 착륙 (이유값: 13)
     */
    SECOND_LIMIT_LANDING(13),

    /**
     * 앱 강제 중단 보호 (이유값: 14)
     */
    APP_FORCIBLY_BREAK_PROTECTION(14),

    /**
     * 근처 비행기 통과 (이유값: 15)
     */
    PLANES_PASSING_NEARBY(15),

    /**
     * 고도 제어 실패 (이유값: 16)
     */
    HEIGHT_CONTROL_FAILED(16),

    /**
     * 저전력 RTH (이유값: 17)
     */
    LOW_POWER_RTH(17),

    /**
     * AP 제어 (이유값: 18)
     */
    AP_CONTROL(18),

    /**
     * 하드웨어 이상 (이유값: 19)
     */
    HARDWARE_ABNORMAL(19),

    /**
     * 착륙 회피 보호 (이유값: 20)
     */
    TOUCHDOWN_AVOIDANCE_PROTECTION(20),

    /**
     * RTH 취소 (이유값: 21)
     */
    CANCEL_RTH(21),

    /**
     * RTH 장애물 회피 (이유값: 22)
     */
    RTH_OBSTACLE_AVOIDANCE(22),

    /**
     * RTH 강풍 (이유값: 23)
     */
    RTH_STRONG_GALE(23),

    ;

    /**
     * 모드 코드 이유 정수값
     */
    private final int reason;

    /**
     * 모드 코드 이유 열거형 생성자
     * 
     * @param reason 모드 코드 이유 정수값
     */
    ModeCodeReasonEnum(int reason) {
        this.reason = reason;
    }

    /**
     * 모드 코드 이유 정수값을 반환합니다.
     * 
     * @return 모드 코드 이유 정수값
     */
    @JsonValue
    public int getReason() {
        return reason;
    }

    /**
     * 정수값으로 모드 코드 이유를 찾습니다.
     * 
     * @param reason 찾을 모드 코드 이유 정수값
     * @return 찾은 모드 코드 이유 열거형
     * @throws CloudSDKException 해당하는 이유를 찾을 수 없는 경우
     */
    @JsonCreator
    public static ModeCodeReasonEnum find(int reason) {
        return Arrays.stream(values()).filter(reasonEnum -> reasonEnum.reason == reason).findAny()
                .orElseThrow(() -> new CloudSDKException(ModeCodeReasonEnum.class, reason));
    }
}
