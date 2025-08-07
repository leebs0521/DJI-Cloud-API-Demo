package com.dji.sdk.cloudapi.flightarea;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * 비행 구역 지오메트리 추상 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 비행 구역의 지오메트리를 정의하는 추상 클래스입니다.
 * 점(Point)과 다각형(Polygon) 두 가지 타입의 지오메트리를 지원하며, JSON 타입 정보를 포함합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/21
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type",
        include = JsonTypeInfo.As.EXISTING_PROPERTY, defaultImpl = FlightAreaGeometry.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FlightAreaPointGeometry.class, name = "Point"),
        @JsonSubTypes.Type(value = FlightAreaPolygonGeometry.class, name = "Polygon")
})
public abstract class FlightAreaGeometry {

    /**
     * 지오메트리 타입
     * 지오메트리의 타입 (점 또는 다각형)
     */
    private GeometryTypeEnum type;

}
