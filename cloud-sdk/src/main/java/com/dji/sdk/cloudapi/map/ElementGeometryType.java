package com.dji.sdk.cloudapi.map;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * 지도 요소 지오메트리 타입 추상 클래스
 * 
 * 이 클래스는 지도에서 사용되는 모든 지오메트리 타입의 기본 클래스입니다.
 * 점(Point), 선(LineString), 다각형(Polygon), 원(Circle) 등의 지오메트리를 정의합니다.
 * 
 * 주요 특징:
 * - JsonTypeInfo를 사용한 다형성 지원
 * - 각 지오메트리 타입별로 좌표 변환 메서드 제공
 * - GeoJSON 표준을 따르는 지오메트리 정의
 * 
 * 하위 클래스들:
 * - ElementPointGeometry: 점 지오메트리
 * - ElementLineStringGeometry: 선 지오메트리
 * - ElementPolygonGeometry: 다각형 지오메트리
 * - ElementCircleGeometry: 원 지오메트리
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/30
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type",
        include = JsonTypeInfo.As.EXISTING_PROPERTY, defaultImpl = ElementGeometryType.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ElementCircleGeometry.class, name = "Circle"),
        @JsonSubTypes.Type(value = ElementPointGeometry.class, name = "Point"),
        @JsonSubTypes.Type(value = ElementLineStringGeometry.class, name = "LineString"),
        @JsonSubTypes.Type(value = ElementPolygonGeometry.class, name = "Polygon")
})
@Schema(oneOf = {ElementPointGeometry.class, ElementLineStringGeometry.class, ElementPolygonGeometry.class})
public abstract class ElementGeometryType {

    /**
     * 지오메트리 타입
     * 각 하위 클래스에서 자신의 타입을 정의합니다.
     * 예: "Point", "LineString", "Polygon", "Circle"
     */
    private String type;

    ElementGeometryType(String type) {
        this.type = type;
    }

    public ElementGeometryType() {
    }

    public String getType() {
        return type;
    }

    /**
     * 좌표 데이터를 객체로 변환하여 컬렉션에 추가합니다.
     * 
     * 이 메서드는 각 지오메트리 타입의 좌표 배열을 ElementCoordinate 객체 리스트로 변환합니다.
     * 지도 렌더링이나 좌표 처리 시 사용됩니다.
     * 
     * @return 좌표 리스트 (ElementCoordinate 객체들의 컬렉션)
     */
    public abstract List<ElementCoordinate> convertToList();

    /**
     * 객체 컬렉션의 좌표를 특정 타입으로 변환합니다.
     * 
     * 이 메서드는 ElementCoordinate 객체 리스트를 각 지오메트리 타입에 맞는
     * 좌표 배열 형태로 변환합니다.
     * 
     * @param coordinateList 변환할 좌표 리스트
     */
    public abstract void adapterCoordinateType(List<ElementCoordinate> coordinateList);
}
