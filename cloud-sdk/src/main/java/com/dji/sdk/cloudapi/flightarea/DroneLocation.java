package com.dji.sdk.cloudapi.flightarea;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 드론 위치 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 드론의 위치 정보를 담는 클래스입니다.
 * 지역 고유 ID, 커스텀 비행 구역 경계까지의 거리, 커스텀 비행 구역 내부 여부를 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/17
 */
public class DroneLocation {

    /**
     * 지역 고유 ID
     * 드론이 위치한 지역의 고유 식별자
     */
    private String areaId;

    /**
     * 커스텀 비행 구역 경계까지의 거리
     * 드론과 커스텀 비행 구역 경계 사이의 거리
     */
    private Float areaDistance;

    /**
     * 커스텀 비행 구역 내부 여부
     * 드론이 커스텀 비행 구역 내부에 있는지 여부
     */
    @JsonProperty("is_in_area")
    private Boolean inArea;

    /**
     * 지역 고유 ID를 반환합니다.
     * 
     * @return 지역 고유 ID
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * 지역 고유 ID를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param areaId 설정할 지역 고유 ID
     * @return 현재 DroneLocation 객체
     */
    public DroneLocation setAreaId(String areaId) {
        this.areaId = areaId;
        return this;
    }

    /**
     * 커스텀 비행 구역 경계까지의 거리를 반환합니다.
     * 
     * @return 커스텀 비행 구역 경계까지의 거리
     */
    public Float getAreaDistance() {
        return areaDistance;
    }

    /**
     * 커스텀 비행 구역 경계까지의 거리를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param areaDistance 설정할 커스텀 비행 구역 경계까지의 거리
     * @return 현재 DroneLocation 객체
     */
    public DroneLocation setAreaDistance(Float areaDistance) {
        this.areaDistance = areaDistance;
        return this;
    }

    /**
     * 커스텀 비행 구역 내부 여부를 반환합니다.
     * 
     * @return 커스텀 비행 구역 내부 여부
     */
    public Boolean getInArea() {
        return inArea;
    }

    /**
     * 커스텀 비행 구역 내부 여부를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param inArea 설정할 커스텀 비행 구역 내부 여부
     * @return 현재 DroneLocation 객체
     */
    public DroneLocation setInArea(Boolean inArea) {
        this.inArea = inArea;
        return this;
    }
}
