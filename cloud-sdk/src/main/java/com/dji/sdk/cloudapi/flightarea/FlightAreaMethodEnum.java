package com.dji.sdk.cloudapi.flightarea;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 비행 구역 메서드 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 비행 구역 관련 메서드를 정의합니다.
 * 비행 구역 업데이트와 삭제 메서드를 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/17
 */
public enum FlightAreaMethodEnum {

    /**
     * 비행 구역 업데이트
     * 비행 구역 정보를 업데이트하는 메서드
     */
    FLIGHT_AREAS_UPDATE("flight_areas_update"),

    /**
     * 비행 구역 삭제
     * 비행 구역 정보를 삭제하는 메서드
     */
    FLIGHT_AREAS_DELETE("flight_areas_delete"),
    ;

    /**
     * 메서드 이름
     */
    private final String method;

    /**
     * 비행 구역 메서드 열거형 생성자
     * 
     * @param method 메서드 이름
     */
    FlightAreaMethodEnum(String method) {
        this.method = method;
    }

    /**
     * 메서드 이름을 반환합니다.
     * 
     * @return 메서드 이름
     */
    @JsonValue
    public String getMethod() {
        return method;
    }
}
