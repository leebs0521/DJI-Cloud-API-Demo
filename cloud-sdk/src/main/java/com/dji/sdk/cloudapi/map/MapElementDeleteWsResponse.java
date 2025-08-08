package com.dji.sdk.cloudapi.map;

import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 지도 요소 삭제 WebSocket 응답 클래스
 * 
 * 이 클래스는 지도 요소가 삭제되었을 때 WebSocket을 통해 전송되는 응답 데이터를 정의합니다.
 * BizCode: map_element_delete
 * 
 * 주요 구성 요소:
 * - groupId: 요소가 속한 그룹의 ID
 * - id: 삭제된 요소의 고유 식별자
 * 
 * 이 클래스는 실시간으로 지도 요소 삭제 이벤트를 클라이언트에게 알리는 데 사용됩니다.
 * 삭제된 요소의 정보는 최소한으로 유지하여 클라이언트가 해당 요소를 제거할 수 있도록 합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/19
 */
@Schema(description = "BizCode: map_element_delete. <p>Websocket response data when element is deleted.</p>")
public class MapElementDeleteWsResponse extends BaseModel {

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
     * 삭제된 요소의 고유 식별자
     * 
     * UUID 형식으로 생성되며, 지도에서 해당 요소를 식별하는 데 사용됩니다.
     * 정규식 패턴을 통해 올바른 UUID 형식인지 검증합니다.
     * 클라이언트는 이 ID를 사용하여 해당 요소를 지도에서 제거합니다.
     */
    @NotNull
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")
    @Schema(description = "element id", format = "uuid")
    private String id;

    public MapElementDeleteWsResponse() {
    }

    @Override
    public String toString() {
        return "MapElementDeleteWsResponse{" +
                "groupId='" + groupId + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getGroupId() {
        return groupId;
    }

    public MapElementDeleteWsResponse setGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getId() {
        return id;
    }

    public MapElementDeleteWsResponse setId(String id) {
        this.id = id;
        return this;
    }
}
