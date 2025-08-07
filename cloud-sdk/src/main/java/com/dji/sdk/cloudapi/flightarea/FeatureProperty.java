package com.dji.sdk.cloudapi.flightarea;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;

/**
 * 피처 속성 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 비행 구역 피처의 속성을 담는 클래스입니다.
 * 지오메트리 서브 타입, 반지름, 활성화 여부를 포함하여 비행 구역 피처의 속성을 정의합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/21
 */
public class FeatureProperty {

    /**
     * 지오메트리 서브 타입
     * 피처의 지오메트리 서브 타입 (예: 원형)
     */
    @JsonProperty("subType")
    private GeometrySubTypeEnum subType;

    /**
     * 반지름
     * 원형 피처의 반지름 (최소 10)
     */
    @Min(10)
    private Float radius = 0f;

    /**
     * 활성화 여부
     * 피처의 활성화 상태
     */
    private Boolean enable;

    /**
     * 기본 생성자
     */
    public FeatureProperty() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FeatureProperty{" +
                "subType=" + subType +
                ", radius=" + radius +
                ", enable=" + enable +
                '}';
    }

    /**
     * 지오메트리 서브 타입을 반환합니다.
     * 
     * @return 지오메트리 서브 타입
     */
    public GeometrySubTypeEnum getSubType() {
        return subType;
    }

    /**
     * 지오메트리 서브 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param subType 설정할 지오메트리 서브 타입
     * @return 현재 FeatureProperty 객체
     */
    public FeatureProperty setSubType(GeometrySubTypeEnum subType) {
        this.subType = subType;
        return this;
    }

    /**
     * 반지름을 반환합니다.
     * 
     * @return 반지름
     */
    public Float getRadius() {
        return radius;
    }

    /**
     * 반지름을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param radius 설정할 반지름
     * @return 현재 FeatureProperty 객체
     */
    public FeatureProperty setRadius(Float radius) {
        this.radius = radius;
        return this;
    }

    /**
     * 활성화 여부를 반환합니다.
     * 
     * @return 활성화 여부
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * 활성화 여부를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param enable 설정할 활성화 여부
     * @return 현재 FeatureProperty 객체
     */
    public FeatureProperty setEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }
}
