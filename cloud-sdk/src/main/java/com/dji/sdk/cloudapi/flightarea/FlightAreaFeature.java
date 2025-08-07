package com.dji.sdk.cloudapi.flightarea;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 비행 구역 피처 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 비행 구역의 피처를 정의하는 클래스입니다.
 * 고유 ID, 타입, 지오펜스 타입, 지오메트리, 속성을 포함하여 비행 구역 피처의 구조를 정의합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/21
 */
public class FlightAreaFeature {

    /**
     * 고유 ID (필수)
     * 피처의 고유 식별자 (UUID 형식)
     */
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
    @NotNull
    private String id;

    /**
     * 타입
     * 피처의 타입 (항상 "Feature")
     */
    private final String type = "Feature";

    /**
     * 지오펜스 타입 (필수)
     * 비행 구역의 지오펜스 타입
     */
    @NotNull
    private GeofenceTypeEnum geofenceType;

    /**
     * 지오메트리 (필수)
     * 비행 구역의 지오메트리 정보
     */
    @NotNull
    @Valid
    private FlightAreaGeometry geometry;

    /**
     * 속성 (필수)
     * 비행 구역 피처의 속성 정보
     */
    @NotNull
    private FeatureProperty properties;

    /**
     * 기본 생성자
     */
    public FlightAreaFeature() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FlightAreaFeature{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", geofenceType=" + geofenceType +
                ", geometry=" + geometry +
                ", properties=" + properties +
                '}';
    }

    /**
     * 고유 ID를 반환합니다.
     * 
     * @return 고유 ID
     */
    public String getId() {
        return id;
    }

    /**
     * 고유 ID를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param id 설정할 고유 ID
     * @return 현재 FlightAreaFeature 객체
     */
    public FlightAreaFeature setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * 타입을 반환합니다.
     * 
     * @return 타입
     */
    public String getType() {
        return type;
    }

    /**
     * 지오펜스 타입을 반환합니다.
     * 
     * @return 지오펜스 타입
     */
    public GeofenceTypeEnum getGeofenceType() {
        return geofenceType;
    }

    /**
     * 지오펜스 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param geofenceType 설정할 지오펜스 타입
     * @return 현재 FlightAreaFeature 객체
     */
    public FlightAreaFeature setGeofenceType(GeofenceTypeEnum geofenceType) {
        this.geofenceType = geofenceType;
        return this;
    }

    /**
     * 지오메트리를 반환합니다.
     * 
     * @return 지오메트리
     */
    public FlightAreaGeometry getGeometry() {
        return geometry;
    }

    /**
     * 지오메트리를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param geometry 설정할 지오메트리
     * @return 현재 FlightAreaFeature 객체
     */
    public FlightAreaFeature setGeometry(FlightAreaGeometry geometry) {
        this.geometry = geometry;
        return this;
    }

    /**
     * 속성을 반환합니다.
     * 
     * @return 속성
     */
    public FeatureProperty getProperties() {
        return properties;
    }

    /**
     * 속성을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param properties 설정할 속성
     * @return 현재 FlightAreaFeature 객체
     */
    public FlightAreaFeature setProperties(FeatureProperty properties) {
        this.properties = properties;
        return this;
    }
}
