package com.dji.sdk.cloudapi.map;

import com.dji.sdk.cloudapi.flightarea.GeometrySubTypeEnum;

import java.util.List;

/**
 * 원 지오메트리 클래스
 * 
 * 이 클래스는 지도에서 원을 표현하는 지오메트리입니다.
 * 점 지오메트리를 확장하여 반지름 정보를 추가한 형태입니다.
 * 드론의 비행 반경, 안전 구역 등을 표현할 때 사용됩니다.
 * 
 * 주요 특징:
 * - 중심점과 반지름으로 원을 정의
 * - 점 지오메트리를 기반으로 확장
 * - 반지름은 미터 단위로 정의
 * 
 * 사용 예시:
 * - 드론 비행 반경 표시
 * - 안전 구역 (Safety Zone) 표시
 * - 통신 범위 표시
 * - 비행 제한 구역 표시
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/22
 */
public class ElementCircleGeometry extends ElementPointGeometry {

    /**
     * 지오메트리 타입
     * 항상 "Circle"로 설정되어 원 지오메트리임을 나타냅니다.
     */
    private final String type = GeometrySubTypeEnum.CIRCLE.getSubType();

    /**
     * 원의 반지름
     * 미터 단위로 정의되며, 원의 크기를 결정합니다.
     * 예: 100.0f (반지름 100미터)
     */
    private Float radius;

    public ElementCircleGeometry() {
    }

    @Override
    public String toString() {
        return "ElementCircleGeometry{" +
                "type='" + type + '\'' +
                ", radius=" + radius +
                '}';
    }

    /**
     * 좌표 배열을 ElementCoordinate 객체 리스트로 변환합니다.
     * 
     * 원 지오메트리는 점 지오메트리를 기반으로 하므로,
     * 부모 클래스의 convertToList 메서드를 그대로 사용합니다.
     * 
     * @return 좌표 리스트 (중심점 좌표 포함)
     */
    @Override
    public List<ElementCoordinate> convertToList() {
        return super.convertToList();
    }

    /**
     * ElementCoordinate 객체 리스트를 좌표 배열로 변환합니다.
     * 
     * 원 지오메트리는 중심점만 필요하므로, 점 지오메트리와 동일하게 처리합니다.
     * 반지름 정보는 별도로 관리됩니다.
     * 
     * @param coordinateList 변환할 좌표 리스트 (중심점 좌표)
     */
    @Override
    public void adapterCoordinateType(List<ElementCoordinate> coordinateList) {
        super.adapterCoordinateType(coordinateList);
        Double[] coordinates = this.getCoordinates();
        this.setCoordinates(new Double[]{coordinates[0], coordinates[1]});
    }

    @Override
    public Double[] getCoordinates() {
        return super.getCoordinates();
    }

    @Override
    public ElementPointGeometry setCoordinates(Double[] coordinates) {
        return super.setCoordinates(coordinates);
    }

    @Override
    public String getType() {
        return type;
    }

    /**
     * 원의 반지름을 반환합니다.
     * 
     * @return 반지름 (미터 단위)
     */
    public Float getRadius() {
        return radius;
    }

    /**
     * 원의 반지름을 설정합니다.
     * 
     * @param radius 반지름 (미터 단위)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ElementCircleGeometry setRadius(Float radius) {
        this.radius = radius;
        return this;
    }
}
