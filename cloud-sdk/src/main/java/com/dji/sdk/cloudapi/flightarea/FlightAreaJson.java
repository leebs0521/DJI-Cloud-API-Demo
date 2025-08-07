package com.dji.sdk.cloudapi.flightarea;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 비행 구역 JSON 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 비행 구역의 JSON 구조를 정의하는 클래스입니다.
 * GeoJSON FeatureCollection 형식을 따르며, 비행 구역 피처들의 목록을 포함합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/21
 */
public class FlightAreaJson {

    /**
     * 타입
     * GeoJSON FeatureCollection 타입 (항상 "FeatureCollection")
     */
    private final String type = "FeatureCollection";

    /**
     * 피처 목록 (필수)
     * 비행 구역 피처들의 목록
     */
    @NotNull
    private List<FlightAreaFeature> features;

    /**
     * 기본 생성자
     */
    public FlightAreaJson() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FlightAreaJson{" +
                "type='" + type + '\'' +
                ", features=" + features +
                '}';
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
     * 피처 목록을 반환합니다.
     * 
     * @return 피처 목록
     */
    public List<FlightAreaFeature> getFeatures() {
        return features;
    }

    /**
     * 피처 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param features 설정할 피처 목록
     * @return 현재 FlightAreaJson 객체
     */
    public FlightAreaJson setFeatures(List<FlightAreaFeature> features) {
        this.features = features;
        return this;
    }
}
