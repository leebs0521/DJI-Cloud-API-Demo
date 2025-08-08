package com.dji.sdk.cloudapi.map;

import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 지도 요소 생성 WebSocket 응답 클래스
 * 
 * 이 클래스는 지도 요소가 생성되었을 때 WebSocket을 통해 전송되는 응답 데이터를 정의합니다.
 * BizCode: map_element_create
 * 
 * 주요 구성 요소:
 * - groupId: 요소가 속한 그룹의 ID
 * - id: 생성된 요소의 고유 식별자
 * - name: 요소의 이름
 * - createTime: 요소 생성 시간
 * - updateTime: 요소 수정 시간
 * - resource: 요소의 리소스 정보
 * 
 * 이 클래스는 실시간으로 지도 요소 생성 이벤트를 클라이언트에게 알리는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/19
 */
@Schema(description = "BizCode: map_element_create.<p>Websocket response data when element is created.</p>")
public class MapElementCreateWsResponse extends BaseModel {

    /**
     * 요소가 속한 그룹의 ID
     * 
     * UUID 형식으로 생성되며, 지도에서 해당 그룹을 식별하는 데 사용됩니다.
     * 정규식 패턴을 통해 올바른 UUID 형식인지 검증합니다.
     */
    @JsonProperty("group_id")
    @NotNull
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
    @Schema(description = "group id", format = "uuid")
    private String groupId;

    /**
     * 생성된 요소의 고유 식별자
     * 
     * UUID 형식으로 생성되며, 지도에서 해당 요소를 식별하는 데 사용됩니다.
     * 정규식 패턴을 통해 올바른 UUID 형식인지 검증합니다.
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

    public MapElementCreateWsResponse() {
    }

    @Override
    public String toString() {
        return "MapElementCreateWsResponse{" +
                "groupId='" + groupId + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", resource=" + resource +
                '}';
    }

    public String getId() {
        return id;
    }

    public MapElementCreateWsResponse setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MapElementCreateWsResponse setName(String name) {
        this.name = name;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public MapElementCreateWsResponse setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public MapElementCreateWsResponse setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public ElementResource getResource() {
        return resource;
    }

    public MapElementCreateWsResponse setResource(ElementResource resource) {
        this.resource = resource;
        return this;
    }

    public String getGroupId() {
        return groupId;
    }

    public MapElementCreateWsResponse setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

}
