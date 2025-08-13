package com.dji.sdk.cloudapi.map;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 지도 요소 생성 요청 데이터 클래스
 *
 * 이 클래스는 지도에 새로운 요소(점, 선, 다각형, 원 등)를 생성할 때 사용되는 요청 데이터를 정의합니다.
 * 클라이언트(파일럿 앱 또는 웹 인터페이스)에서 지도에 요소를 그릴 때 서버로 전송되는 데이터 구조입니다.
 *
 * 주요 구성 요소:
 * - id: 요소의 고유 식별자 (UUID 형식)
 * - name: 요소의 이름 (사용자가 지정한 이름)
 * - resource: 요소의 실제 데이터 (타입, 사용자 정보, 콘텐츠 포함)
 *
 * @author sean
 * @version 0.2
 * @date 2021/11/30
 */
@Schema(description = "지도 요소 생성 요청 데이터")
public class CreateMapElementRequest {

    /**
     * 요소의 고유 식별자
     * UUID 형식으로 생성되며, 지도에서 해당 요소를 식별하는 데 사용됩니다.
     * 정규식 패턴을 통해 올바른 UUID 형식인지 검증합니다.
     */
    @NotNull
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
    @Schema(description = "요소 ID", format = "uuid")
    private String id;

    /**
     * 요소의 이름
     * 사용자가 지도에서 요소를 구분하기 위해 지정하는 이름입니다.
     * 예: "비행 경로 1", "착륙 지점", "금지 구역" 등
     */
    @Schema(description = "요소 이름", example = "PILOT 1")
    @NotNull
    private String name;

    /**
     * 요소의 리소스 정보
     * 요소의 타입(점, 선, 다각형), 생성자 정보, 실제 콘텐츠(좌표, 속성 등)를 포함합니다.
     * 지도에서 실제로 표시될 요소의 모든 데이터가 이 객체에 담겨있습니다.
     */
    @NotNull
    @Valid
    private ElementResource resource;

    public CreateMapElementRequest() {
    }

    @Override
    public String toString() {
        return "CreateMapElementRequest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", resource=" + resource +
                '}';
    }

    public String getId() {
        return id;
    }

    public CreateMapElementRequest setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CreateMapElementRequest setName(String name) {
        this.name = name;
        return this;
    }

    public ElementResource getResource() {
        return resource;
    }

    public CreateMapElementRequest setResource(ElementResource resource) {
        this.resource = resource;
        return this;
    }
}
