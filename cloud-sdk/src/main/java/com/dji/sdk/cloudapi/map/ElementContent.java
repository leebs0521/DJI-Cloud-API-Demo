package com.dji.sdk.cloudapi.map;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 지도 요소 콘텐츠 클래스
 * 
 * 이 클래스는 지도 요소의 실제 표시 데이터를 정의합니다.
 * GeoJSON Feature 형식을 따르며, 요소의 속성(properties)과 지오메트리(geometry) 정보를 포함합니다.
 * 
 * 주요 구성 요소:
 * - type: 항상 "Feature"로 고정 (GeoJSON 표준)
 * - properties: 요소의 속성 정보 (색상, 지면 고정 여부 등)
 * - geometry: 요소의 지오메트리 정보 (좌표, 형태 등)
 * 
 * 이 클래스는 지도에서 실제로 렌더링될 요소의 모든 시각적 및 공간적 정보를 담고 있습니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/30
 */
@Schema(description = "element content")
public class ElementContent {

    /**
     * 요소 타입
     * GeoJSON 표준에 따라 항상 "Feature"로 설정됩니다.
     * 이는 지도 요소가 GeoJSON Feature 객체임을 나타냅니다.
     */
    @Schema(defaultValue = "Feature", allowableValues = "Feature")
    @NotNull
    private final String type = "Feature";

    /**
     * 요소의 속성 정보
     * 지도 요소의 시각적 속성과 메타데이터를 포함합니다.
     * 색상, 지면 고정 여부, 사용자 정의 속성 등을 정의할 수 있습니다.
     */
    @NotNull
    @Valid
    private ElementProperty properties;

    /**
     * 요소의 지오메트리 정보
     * 지도 요소의 공간적 정보를 정의합니다.
     * 점, 선, 다각형, 원 등의 형태와 좌표 정보를 포함합니다.
     */
    @Valid
    @NotNull
    private ElementGeometryType geometry;

    public ElementContent() {
    }

    @Override
    public String toString() {
        return "ElementContent{" +
                "type='" + type + '\'' +
                ", properties=" + properties +
                ", geometry=" + geometry +
                '}';
    }

    public String getType() {
        return type;
    }

    public ElementProperty getProperties() {
        return properties;
    }

    public ElementContent setProperties(ElementProperty properties) {
        this.properties = properties;
        return this;
    }

    public ElementGeometryType getGeometry() {
        return geometry;
    }

    public ElementContent setGeometry(ElementGeometryType geometry) {
        this.geometry = geometry;
        return this;
    }
}
