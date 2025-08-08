package com.dji.sdk.cloudapi.map;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 지도 요소 리소스 클래스
 * 
 * 이 클래스는 지도 요소의 핵심 데이터를 담고 있는 리소스 정보를 정의합니다.
 * 지도 요소의 타입, 생성자 정보, 그리고 실제 콘텐츠(좌표, 속성 등)를 포함합니다.
 * 
 * 주요 구성 요소:
 * - type: 요소의 타입 (점, 선, 다각형 등)
 * - username: 요소를 생성한 사용자 정보
 * - content: 요소의 실제 콘텐츠 (좌표, 속성, 지오메트리 정보)
 * 
 * 이 클래스는 지도에서 실제로 표시될 요소의 모든 메타데이터와 데이터를 관리합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/29
 */
@Schema(description = "element resource")
public class ElementResource {

    /**
     * 요소의 타입
     * 지도 요소가 어떤 형태인지를 나타냅니다 (점, 선, 다각형 등).
     * ElementResourceTypeEnum을 사용하여 타입을 정의합니다.
     */
    @NotNull
    @Schema(type = "int", enumAsRef = true)
    private ElementResourceTypeEnum type;

    /**
     * 요소를 생성한 사용자 정보
     * 누가 이 요소를 생성했는지 추적하기 위한 사용자명입니다.
     * 예: "pilot", "web_user", "admin" 등
     */
    @Schema(description = "the user who created the element", example = "pilot")
    @JsonProperty(value = "user_name")
    private String username;

    /**
     * 요소의 실제 콘텐츠
     * 지도에서 표시될 요소의 모든 데이터를 포함합니다.
     * 좌표 정보, 속성(색상, 지면 고정 여부 등), 지오메트리 타입 등을 담고 있습니다.
     */
    @NotNull
    @Valid
    private ElementContent content;

    public ElementResource() {
    }

    @Override
    public String toString() {
        return "ElementResource{" +
                "type=" + type +
                ", username='" + username + '\'' +
                ", content=" + content +
                '}';
    }

    public ElementResourceTypeEnum getType() {
        return type;
    }

    public ElementResource setType(ElementResourceTypeEnum type) {
        this.type = type;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ElementResource setUsername(String username) {
        this.username = username;
        return this;
    }

    public ElementContent getContent() {
        return content;
    }

    public ElementResource setContent(ElementContent content) {
        this.content = content;
        return this;
    }
}
