package com.dji.sdk.cloudapi.map;

import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * 지도 요소 그룹 응답 데이터 클래스
 *
 * 이 클래스는 지도 요소 그룹의 정보를 담고 있는 응답 데이터를 정의합니다.
 * 지도 요소들은 그룹으로 관리되며, 각 그룹은 여러 요소를 포함할 수 있습니다.
 *
 * 주요 구성 요소:
 * - id: 그룹의 고유 식별자 (UUID 형식)
 * - name: 그룹의 이름
 * - type: 그룹의 타입 (GroupTypeEnum)
 * - elements: 그룹에 포함된 요소들의 리스트
 * - lock: 그룹의 잠금 상태
 *
 * 이 클래스는 지도 요소 목록 조회 API의 응답으로 사용됩니다.
 *
 * @author sean
 * @version 0.2
 * @date 2021/11/29
 */
@Schema(description = "지도 요소 그룹 데이터")
public class GetMapElementsResponse extends BaseModel {

    /**
     * 그룹의 고유 식별자
     *
     * UUID 형식으로 생성되며, 지도에서 해당 그룹을 식별하는 데 사용됩니다.
     * 정규식 패턴을 통해 올바른 UUID 형식인지 검증합니다.
     */
    @NotNull
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
    @Schema(description = "그룹 ID", format = "uuid")
    private String id;

    /**
     * 그룹의 이름
     *
     * 사용자가 지도에서 그룹을 구분하기 위해 지정하는 이름입니다.
     * 예: "파일럿 공유 레이어", "관리자 레이어", "시스템 레이어" 등
     */
    @NotNull
    @Schema(description = "그룹 이름", example = "파일럿 공유 레이어")
    private String name;

    /**
     * 그룹의 타입
     *
     * 그룹의 종류를 나타내는 타입입니다.
     * GroupTypeEnum을 사용하여 타입을 정의합니다.
     * 예: 사용자 그룹, 시스템 그룹, 공유 그룹 등
     */
    @NotNull
    @Schema(description = "그룹 타입")
    private GroupTypeEnum type;

    /**
     * 그룹에 포함된 요소들의 리스트
     *
     * 이 그룹에 속한 모든 지도 요소들의 정보를 포함합니다.
     * 각 요소는 MapGroupElement 객체로 표현됩니다.
     * 빈 리스트일 수도 있습니다 (그룹에 요소가 없는 경우).
     */
    @NotNull
    @Schema(description = "요소 데이터 목록")
    private List<@Valid MapGroupElement> elements;

    /**
     * 그룹의 잠금 상태
     *
     * true인 경우 그룹이 잠겨있어 수정할 수 없습니다.
     * false인 경우 그룹을 자유롭게 수정할 수 있습니다.
     * 시스템 그룹이나 중요한 그룹의 경우 잠금 상태로 설정됩니다.
     */
    @JsonProperty(value = "is_lock")
    @NotNull
    @Schema(description = "요소 그룹이 잠겨 있는지 여부")
    private Boolean lock;

    public GetMapElementsResponse() {
    }

    @Override
    public String toString() {
        return "GetMapElementsResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", elements=" + elements +
                ", lock=" + lock +
                '}';
    }

    public String getId() {
        return id;
    }

    public GetMapElementsResponse setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GetMapElementsResponse setName(String name) {
        this.name = name;
        return this;
    }

    public GroupTypeEnum getType() {
        return type;
    }

    public GetMapElementsResponse setType(GroupTypeEnum type) {
        this.type = type;
        return this;
    }

    public List<MapGroupElement> getElements() {
        return elements;
    }

    public GetMapElementsResponse setElements(List<MapGroupElement> elements) {
        this.elements = elements;
        return this;
    }

    public Boolean getLock() {
        return lock;
    }

    public GetMapElementsResponse setLock(Boolean lock) {
        this.lock = lock;
        return this;
    }
}
