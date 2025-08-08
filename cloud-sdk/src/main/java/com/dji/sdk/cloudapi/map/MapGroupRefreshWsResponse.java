package com.dji.sdk.cloudapi.map;

import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 지도 그룹 새로고침 WebSocket 응답 클래스
 * 
 * 이 클래스는 지도 그룹의 요소들이 변경되었을 때 WebSocket을 통해 전송되는 응답 데이터를 정의합니다.
 * BizCode: map_group_refresh
 * 
 * 주요 구성 요소:
 * - ids: 새로고침이 필요한 그룹 ID들의 리스트
 * 
 * 사용 시나리오:
 * - 웹에서 요소를 드래그하여 여러 요소가 변경된 경우
 * - 서버 측에서 여러 요소가 동시에 변경된 경우
 * - 클라이언트가 HTTP를 통해 "지도 요소 목록 조회" API를 호출하여 요소 목록을 새로고침
 * 
 * 이 클래스는 실시간으로 지도 그룹 변경 이벤트를 클라이언트에게 알리는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/19
 */
@Schema(description = "BizCode: map_group_refresh. <p>When several elements have changed on the server end, such as drag an element on web end, the user end can be notified through WebSocket. " +
        "The downward parameter has the layer group_id. The user end can call \"*Obtain Map Element List*\" to refresh the element list through http after receiving the ID.</p>")
public class MapGroupRefreshWsResponse extends BaseModel {

    /**
     * 새로고침이 필요한 그룹 ID들의 리스트
     * 
     * 서버 측에서 여러 요소가 변경되었을 때, 새로고침이 필요한 그룹들의 ID를 포함합니다.
     * 각 ID는 UUID 형식이며, 정규식 패턴을 통해 올바른 UUID 형식인지 검증합니다.
     * 최소 1개 이상의 그룹 ID가 포함되어야 합니다.
     * 
     * 클라이언트는 이 ID들을 받은 후 HTTP를 통해 "지도 요소 목록 조회" API를 호출하여
     * 해당 그룹들의 요소 목록을 새로고침합니다.
     */
    @JsonProperty("ids")
    @NotNull
    @Size(min = 1)
    @Schema(description = "group id collection", format = "uuid")
    private List<@Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$") String> ids;

    public MapGroupRefreshWsResponse() {
    }

    @Override
    public String toString() {
        return "MapGroupRefreshWsResponse{" +
                "ids=" + ids +
                '}';
    }

    public List<String> getIds() {
        return ids;
    }

    public MapGroupRefreshWsResponse setIds(List<String> ids) {
        this.ids = ids;
        return this;
    }
}
