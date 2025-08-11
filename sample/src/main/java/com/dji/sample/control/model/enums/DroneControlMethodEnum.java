package com.dji.sample.control.model.enums;

import lombok.Getter;

/**
 * 드론 제어 메서드 열거형 클래스
 * 
 * 드론 제어를 위한 메서드를 정의하는 열거형입니다.
 * 권한 획득, 비행 제어 등의 메서드를 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/2/21
 */
@Getter
public enum DroneControlMethodEnum {

    /** 비행 권한 획득 */
    FLIGHT_AUTHORITY_GRAB("flight_authority_grab"),

    /** 페이로드 권한 획득 */
    PAYLOAD_AUTHORITY_GRAB("payload_authority_grab"),

    /** 특정 지점으로 비행 */
    FLY_TO_POINT("fly_to_point"),

    /** 특정 지점 비행 중지 */
    FLY_TO_POINT_STOP("fly_to_point_stop"),

    /** 특정 지점으로 이륙 */
    TAKE_OFF_TO_POINT("takeoff_to_point");

    /** 메서드 이름 */
    String method;

    /**
     * 드론 제어 메서드 열거형 생성자
     * 
     * @param method 메서드 이름
     */
    DroneControlMethodEnum(String method) {
        this.method = method;
    }
}
