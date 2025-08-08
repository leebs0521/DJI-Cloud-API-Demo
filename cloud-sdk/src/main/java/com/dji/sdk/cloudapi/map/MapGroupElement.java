package com.dji.sdk.cloudapi.map;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 지도 그룹 요소 데이터 클래스
 * 
 * 이 클래스는 지도 그룹에 포함된 개별 요소의 정보를 정의합니다.
 * 지도 요소 목록 조회 시 각 요소의 상세 정보를 담고 있습니다.
 * 
 * 주요 구성 요소:
 * - id: 요소의 고유 식별자 (UUID 형식)
 * - name: 요소의 이름
 * - createTime: 요소 생성 시간 (타임스탬프)
 * - updateTime: 요소 수정 시간 (타임스탬프)
 * - resource: 요소의 리소스 정보 (타입, 사용자, 콘텐츠)
 * 
 * 이 클래스는 지도에서 실제로 표시될 요소의 모든 메타데이터와 데이터를 포함합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/29
 */
@Schema(description = "element data")
public class MapGroupElement {

    /**
     * 요소의 고유 식별자
     * 
     * UUID 형식으로 생성되며, 지도에서 해당 요소를 식별하는 데 사용됩니다.
     * 정규식 패턴을 통해 올바른 UUID 형식인지 검증합니다.
     * 이 ID는 요소의 수정, 삭제, 조회 등 모든 작업에서 사용됩니다.
     */
    @NotNull
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
    @Schema(description = "element id", format = "uuid")
    private String id;

    /**
     * 요소의 이름
     * 
     * 사용자가 지도에서 요소를 구분하기 위해 지정하는 이름입니다.
     * 예: "비행 경로 1", "착륙 지점", "금지 구역" 등
     */
    @NotNull
    @Schema(description = "element name", example = "PILOT 1")
    private String name;

    /**
     * 요소 생성 시간
     * 
     * 요소가 생성된 시간을 밀리초 단위의 타임스탬프로 나타냅니다.
     * Unix epoch time (1970년 1월 1일부터의 밀리초) 형식입니다.
     * 최소값 검증을 통해 유효한 타임스탬프인지 확인합니다.
     */
    @NotNull
    @Schema(description = "element create time", example = "123456789012")
    @JsonProperty(value = "create_time")
    @Min(123456789012L)
    private Long createTime;

    /**
     * 요소 수정 시간
     * 
     * 요소가 마지막으로 수정된 시간을 밀리초 단위의 타임스탬프로 나타냅니다.
     * Unix epoch time (1970년 1월 1일부터의 밀리초) 형식입니다.
     * 최소값 검증을 통해 유효한 타임스탬프인지 확인합니다.
     */
    @NotNull
    @Schema(description = "element update time", example = "123456789012")
    @JsonProperty(value = "update_time")
    @Min(123456789012L)
    private Long updateTime;

    /**
     * 요소의 리소스 정보
     * 
     * 지도 요소의 실제 데이터를 포함합니다.
     * 요소의 타입(점, 선, 다각형), 생성자 정보, 콘텐츠(좌표, 속성 등)를 담고 있습니다.
     * 지도에서 실제로 표시될 요소의 모든 데이터가 이 객체에 담겨있습니다.
     */
    @NotNull
    @Valid
    private ElementResource resource;

    public MapGroupElement() {
    }

    @Override
    public String toString() {
        return "MapGroupElement{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", resource=" + resource +
                '}';
    }

    public String getId() {
        return id;
    }

    public MapGroupElement setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MapGroupElement setName(String name) {
        this.name = name;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public MapGroupElement setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public MapGroupElement setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public ElementResource getResource() {
        return resource;
    }

    public MapGroupElement setResource(ElementResource resource) {
        this.resource = resource;
        return this;
    }
}
