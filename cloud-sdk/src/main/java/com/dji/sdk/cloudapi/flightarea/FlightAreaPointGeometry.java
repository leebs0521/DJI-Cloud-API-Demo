package com.dji.sdk.cloudapi.flightarea;

import java.util.Arrays;

/**
 * 비행 구역 점 지오메트리 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 비행 구역의 점 지오메트리를 정의하는 클래스입니다.
 * FlightAreaGeometry 추상 클래스를 상속받아 점 형태의 비행 구역을 표현하며,
 * 단일 좌표점을 통해 원형 비행 구역의 중심점을 정의합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/21
 */
public class FlightAreaPointGeometry extends FlightAreaGeometry {

    /**
     * 지오메트리 타입
     * 점 지오메트리 타입으로 항상 POINT 값을 가집니다.
     */
    private final GeometryTypeEnum type = GeometryTypeEnum.POINT;

    /**
     * 좌표 배열
     * 점의 좌표 정보를 담는 배열 [경도, 위도] 형식으로 저장됩니다.
     * 예: [127.123456, 37.123456] (서울 시청 좌표)
     */
    private Double[] coordinates;

    /**
     * 기본 생성자
     * FlightAreaPointGeometry 객체를 생성합니다.
     */
    public FlightAreaPointGeometry() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현 (타입과 좌표 정보 포함)
     */
    @Override
    public String toString() {
        return "FlightAreaPointGeometry{" +
                "type=" + type +
                ", coordinates=" + Arrays.toString(coordinates) +
                '}';
    }

    /**
     * 지오메트리 타입을 반환합니다.
     * 
     * @return 지오메트리 타입 (항상 POINT)
     */
    public GeometryTypeEnum getType() {
        return type;
    }

    /**
     * 좌표 배열을 반환합니다.
     * 
     * @return 좌표 배열 [경도, 위도]
     */
    public Double[] getCoordinates() {
        return coordinates;
    }

    /**
     * 좌표 배열을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param coordinates 설정할 좌표 배열 [경도, 위도]
     * @return 현재 FlightAreaPointGeometry 객체
     */
    public FlightAreaPointGeometry setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
        return this;
    }
}
