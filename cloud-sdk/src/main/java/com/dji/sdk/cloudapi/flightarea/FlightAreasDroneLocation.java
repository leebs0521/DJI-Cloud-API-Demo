package com.dji.sdk.cloudapi.flightarea;

import java.util.List;

/**
 * 비행 구역 드론 위치 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 비행 구역과 드론(항공기) 간의 위치 관계 정보를 담는 클래스입니다.
 * 드론의 위치와 비행 구역 간의 거리, 비행 구역 내부 여부 등의 정보를 포함하여
 * 드론의 비행 안전성과 비행 구역 준수 상황을 모니터링하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/17
 */
public class FlightAreasDroneLocation {

    /**
     * 드론 위치 목록
     * 드론의 위치 정보와 비행 구역과의 관계를 담는 DroneLocation 객체들의 목록입니다.
     * 각 DroneLocation 객체는 드론의 현재 위치, 비행 구역까지의 거리, 
     * 비행 구역 내부 여부 등의 정보를 포함합니다.
     */
    private List<DroneLocation> droneLocations;

    /**
     * 기본 생성자
     * FlightAreasDroneLocation 객체를 생성합니다.
     */
    public FlightAreasDroneLocation() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현 (드론 위치 목록 정보 포함)
     */
    @Override
    public String toString() {
        return "FlightAreasDroneLocation{" +
                "droneLocations=" + droneLocations +
                '}';
    }

    /**
     * 드론 위치 목록을 반환합니다.
     * 
     * @return 드론 위치 목록 (DroneLocation 객체들의 리스트)
     */
    public List<DroneLocation> getDroneLocations() {
        return droneLocations;
    }

    /**
     * 드론 위치 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param droneLocations 설정할 드론 위치 목록
     * @return 현재 FlightAreasDroneLocation 객체
     */
    public FlightAreasDroneLocation setDroneLocations(List<DroneLocation> droneLocations) {
        this.droneLocations = droneLocations;
        return this;
    }
}
