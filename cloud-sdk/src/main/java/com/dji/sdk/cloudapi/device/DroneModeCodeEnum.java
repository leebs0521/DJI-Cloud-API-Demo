package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 드론 비행 모드 코드 열거형 클래스
 * 
 * 이 클래스는 드론의 다양한 비행 모드를 정의합니다.
 * 각 모드는 정수 코드로 표현되며, 드론의 현재 동작 상태를 나타냅니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/9
 */
public enum DroneModeCodeEnum {

    /**
     * 대기 모드 (코드: 0)
     */
    IDLE(0),

    /**
     * 이륙 준비 모드 (코드: 1)
     */
    TAKEOFF_PREPARE(1),

    /**
     * 이륙 완료 모드 (코드: 2)
     */
    TAKEOFF_FINISHED(2),

    /**
     * 수동 조종 모드 (코드: 3)
     */
    MANUAL(3),

    /**
     * 자동 이륙 모드 (코드: 4)
     */
    TAKEOFF_AUTO(4),

    /**
     * 웨이라인 비행 모드 (코드: 5)
     */
    WAYLINE(5),

    /**
     * 파노라마 촬영 모드 (코드: 6)
     */
    PANORAMIC_SHOT(6),

    /**
     * 액티브 트래킹 모드 (코드: 7)
     */
    ACTIVE_TRACK(7),

    /**
     * ADS-B 회피 모드 (코드: 8)
     */
    ADS_B_AVOIDANCE(8),

    /**
     * 자동 홈 복귀 모드 (코드: 9)
     */
    RETURN_AUTO(9),

    /**
     * 자동 착륙 모드 (코드: 10)
     */
    LANDING_AUTO(10),

    /**
     * 강제 착륙 모드 (코드: 11)
     */
    LANDING_FORCED(11),

    /**
     * 3개 프로펠러 착륙 모드 (코드: 12)
     */
    LANDING_THREE_PROPELLER(12),

    /**
     * 펌웨어 업그레이드 모드 (코드: 13)
     */
    UPGRADING(13),

    /**
     * 연결 해제 모드 (코드: 14)
     */
    DISCONNECTED(14),

    /**
     * APAS (Advanced Pilot Assistance System) 모드 (코드: 15)
     */
    APAS(15),

    /**
     * 가상 조이스틱 모드 (코드: 16)
     */
    VIRTUAL_JOYSTICK(16),

    /**
     * 라이브 비행 제어 모드 (코드: 17)
     */
    LIVE_FLIGHT_CONTROLS(17),

    /**
     * 공중 RTK 고정 모드 (코드: 18)
     */
    AERIAL_RTK_FIXED(18),

    /**
     * 도크 사이트 평가 모드 (코드: 19)
     */
    DOCK_SITE_EVALUATION(19),

    /**
     * POI (Point of Interest) 모드 (코드: 20)
     */
    POI(20),

    ;

    /**
     * 모드 코드 정수값
     */
    private final int code;

    /**
     * 드론 모드 코드 열거형 생성자
     * 
     * @param code 모드 코드 정수값
     */
    DroneModeCodeEnum(int code) {
        this.code = code;
    }

    /**
     * 모드 코드 정수값을 반환합니다.
     * 
     * @return 모드 코드 정수값
     */
    @JsonValue
    public int getCode() {
        return code;
    }

    /**
     * 정수값으로 드론 모드 코드를 찾습니다.
     * 
     * @param code 찾을 모드 코드 정수값
     * @return 찾은 드론 모드 코드 열거형
     * @throws CloudSDKException 해당하는 모드 코드를 찾을 수 없는 경우
     */
    @JsonCreator
    public static DroneModeCodeEnum find(int code) {
        return Arrays.stream(values()).filter(modeCodeEnum -> modeCodeEnum.ordinal() == code).findAny()
                .orElseThrow(() -> new CloudSDKException(DroneModeCodeEnum.class, code));
    }
}
