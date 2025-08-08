package com.dji.sdk.cloudapi.map;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 지도 요소 속성 클래스
 * 
 * 이 클래스는 지도 요소의 시각적 속성과 메타데이터를 정의합니다.
 * 지도에서 요소가 어떻게 표시될지를 결정하는 속성들을 포함합니다.
 * 
 * 주요 속성:
 * - color: 요소의 색상 (16진수 색상 코드)
 * - clampToGround: 지면에 고정할지 여부 (3D 지도에서 중요)
 * 
 * 이 클래스는 지도 렌더링 시 요소의 시각적 표현을 결정하는 데 사용됩니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/30
 */
@Schema(description = "properties of the element")
public class ElementProperty {

    /**
     * 요소의 색상
     * 16진수 색상 코드 형식으로 정의됩니다 (예: #2D8CF0).
     * 정규식 패턴을 통해 올바른 색상 형식인지 검증합니다.
     * 지도에서 요소를 구분하거나 강조하는 데 사용됩니다.
     */
    @NotNull
    @Schema(description = "element color", example = "#2D8CF0")
    @Pattern(regexp = "^#[0-9a-fA-F]{6}$")
    private String color;

    /**
     * 지면 고정 여부
     * true인 경우 요소가 지면에 고정되어 표시됩니다.
     * false인 경우 고도 정보에 따라 3D 공간에 배치됩니다.
     * 3D 지도에서 요소의 배치 방식을 결정하는 중요한 속성입니다.
     */
    @JsonProperty("clampToGround")
    @Schema(description = "Whether it is on the ground.")
    private Boolean clampToGround;

    public ElementProperty() {
    }

    @Override
    public String toString() {
        return "ElementProperty{" +
                "color='" + color + '\'' +
                ", clampToGround=" + clampToGround +
                '}';
    }

    public String getColor() {
        return color;
    }

    public ElementProperty setColor(String color) {
        this.color = color;
        return this;
    }

    public Boolean getClampToGround() {
        return clampToGround;
    }

    public ElementProperty setClampToGround(Boolean clampToGround) {
        this.clampToGround = clampToGround;
        return this;
    }
}
