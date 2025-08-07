package com.dji.sdk.cloudapi.flightarea;

import java.util.Arrays;

/**
 * 비행 구역 다각형 지오메트리 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 비행 구역의 다각형 지오메트리를 정의하는 클래스입니다.
 * FlightAreaGeometry 추상 클래스를 상속받아 다각형 형태의 비행 구역을 표현하며,
 * 다중 좌표점을 통해 복잡한 형태의 비행 제한 구역을 정의합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/21
 */
public class FlightAreaPolygonGeometry extends FlightAreaGeometry {

    /**
     * 지오메트리 타입
     * 다각형 지오메트리 타입으로 항상 POLYGON 값을 가집니다.
     */
    private final GeometryTypeEnum type = GeometryTypeEnum.POLYGON;

    /**
     * 다중 좌표 배열
     * 다각형의 좌표 정보를 담는 3차원 배열로 [[[경도1, 위도1], [경도2, 위도2], ...]] 형식으로 저장됩니다.
     * 첫 번째 차원: 다각형의 개수 (보통 1개)
     * 두 번째 차원: 각 다각형의 외곽선 좌표점들
     * 세 번째 차원: 각 좌표점의 [경도, 위도] 값
     * 예: [[[127.123456, 37.123456], [127.234567, 37.123456], [127.234567, 37.234567], [127.123456, 37.234567]]]
     */
    private Double[][][] coordinates;

    /**
     * 기본 생성자
     * FlightAreaPolygonGeometry 객체를 생성합니다.
     */
    public FlightAreaPolygonGeometry() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현 (타입과 좌표 정보 포함)
     */
    @Override
    public String toString() {
        return "FlightAreaPolygonGeometry{" +
                "type=" + type +
                ", coordinates=" + Arrays.toString(coordinates) +
                '}';
    }

    /**
     * 지오메트리 타입을 반환합니다.
     * 
     * @return 지오메트리 타입 (항상 POLYGON)
     */
    public GeometryTypeEnum getType() {
        return type;
    }

    /**
     * 다중 좌표 배열을 반환합니다.
     * 
     * @return 다중 좌표 배열 [[[경도1, 위도1], [경도2, 위도2], ...]]
     */
    public Double[][][] getCoordinates() {
        return coordinates;
    }

    /**
     * 다중 좌표 배열을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param coordinates 설정할 다중 좌표 배열 [[[경도1, 위도1], [경도2, 위도2], ...]]
     * @return 현재 FlightAreaPolygonGeometry 객체
     */
    public FlightAreaPolygonGeometry setCoordinates(Double[][][] coordinates) {
        this.coordinates = coordinates;
        return this;
    }
}
