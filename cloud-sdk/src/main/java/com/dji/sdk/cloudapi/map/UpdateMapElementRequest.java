package com.dji.sdk.cloudapi.map;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 지도 요소 수정 요청 데이터 클래스
 *
 * 이 클래스는 지도에 이미 존재하는 요소(점, 선, 다각형 등)를 수정할 때 사용되는 요청 데이터를 정의합니다.
 * 클라이언트(파일럿 앱 또는 웹 인터페이스)에서 지도 요소를 편집할 때 서버로 전송되는 데이터 구조입니다.
 *
 * 주요 구성 요소:
 * - name: 수정할 요소의 새로운 이름
 * - content: 수정할 요소의 새로운 콘텐츠 (좌표, 속성, 지오메트리 정보)
 *
 * 이 클래스는 기존 요소의 ID는 URL 경로에서 추출하므로 포함하지 않습니다.
 *
 * @author sean
 * @version 0.2
 * @date 2021/12/1
 */
@Schema(description = "지도 요소 수정 요청 데이터")
public class UpdateMapElementRequest {

    /**
     * 요소의 새로운 이름
     *
     * 사용자가 지도에서 요소를 구분하기 위해 지정하는 새로운 이름입니다.
     * 기존 이름을 변경하거나 유지할 수 있습니다.
     * 예: "비행 경로 1", "착륙 지점", "금지 구역" 등
     */
    @Schema(description = "요소 이름", example = "PILOT 1")
    @NotNull
    private String name;

    /**
     * 요소의 새로운 콘텐츠
     *
     * 수정할 요소의 새로운 데이터를 포함합니다.
     * 좌표 정보, 속성(색상, 지면 고정 여부 등), 지오메트리 타입 등을 담고 있습니다.
     * 지도에서 실제로 표시될 요소의 모든 수정된 데이터가 이 객체에 담겨있습니다.
     */
    @NotNull
    @Valid
    private ElementContent content;

    public UpdateMapElementRequest() {
    }

    @Override
    public String toString() {
        return "UpdateMapElementRequest{" +
                "name='" + name + '\'' +
                ", content=" + content +
                '}';
    }

    public String getName() {
        return name;
    }

    public UpdateMapElementRequest setName(String name) {
        this.name = name;
        return this;
    }

    public ElementContent getContent() {
        return content;
    }

    public UpdateMapElementRequest setContent(ElementContent content) {
        this.content = content;
        return this;
    }
}
