package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 드론 기어 상태 열거형 클래스
 * 
 * 이 클래스는 드론의 기어 상태를 정의합니다.
 * 각 기어는 정수값으로 표현되며, 드론의 비행 모드나 설정을 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public enum GearEnum {

    /**
     * A 기어 (정수값: 0)
     */
    A(0),

    /**
     * P 기어 (정수값: 1)
     */
    P(1),

    /**
     * NAV 기어 (정수값: 2)
     */
    NAV(2),

    /**
     * FPV 기어 (정수값: 3)
     */
    FPV(3),

    /**
     * FARM 기어 (정수값: 4)
     */
    FARM(4),

    /**
     * S 기어 (정수값: 5)
     */
    S(5),

    /**
     * F 기어 (정수값: 6)
     */
    F(6),

    /**
     * M 기어 (정수값: 7)
     */
    M(7),

    /**
     * G 기어 (정수값: 8)
     */
    G(8),

    /**
     * T 기어 (정수값: 9)
     */
    T(9),
    ;

    /**
     * 기어 정수값
     */
    private final int gear;

    /**
     * 기어 열거형 생성자
     * 
     * @param gear 기어 정수값
     */
    GearEnum(int gear) {
        this.gear = gear;
    }

    /**
     * 기어 정수값을 반환합니다.
     * 
     * @return 기어 정수값
     */
    @JsonValue
    public int getGear() {
        return gear;
    }

    /**
     * 정수값으로 기어를 찾습니다.
     * 
     * @param gear 찾을 기어 정수값
     * @return 찾은 기어 열거형
     * @throws CloudSDKException 해당하는 기어를 찾을 수 없는 경우
     */
    @JsonCreator
    public static GearEnum find(int gear) {
        return Arrays.stream(values()).filter(gearEnum -> gearEnum.gear == gear).findAny()
            .orElseThrow(() -> new CloudSDKException(GearEnum.class, gear));
    }

}
