package com.dji.sdk.cloudapi.map.api;

import com.dji.sdk.cloudapi.map.CreateMapElementRequest;
import com.dji.sdk.cloudapi.map.CreateMapElementResponse;
import com.dji.sdk.cloudapi.map.GetMapElementsResponse;
import com.dji.sdk.cloudapi.map.UpdateMapElementRequest;
import com.dji.sdk.common.HttpResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 지도 요소 관리를 위한 HTTP API 인터페이스
 * @author sean
 * @version 0.2
 * @date 2021/11/29
 */
@Tag(name = "지도 인터페이스")
public interface IHttpMapService {

    String PREFIX = "map/api/v1";

    /**
     * 지도 요소 목록 조회
     * 첫 번째 연결 시, 파일럿이 이 HTTP 요청을 보내서 그룹 요소 목록을 가져옵니다.
     * 또한, 파일럿이 WebSocket에서 그룹 새로고침 지시를 받으면,
     * 동일한 인터페이스를 사용하여 그룹 요소 목록을 요청해야 합니다.
     * @param workspaceId 워크스페이스 ID
     * @param groupId 그룹 ID (선택사항)
     * @param isDistributed 분산 여부 (선택사항)
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 지도 요소 목록
     */
    @Operation(
            summary = "지도 요소 목록 조회",
            description = "첫 연결 시 파일럿은 그룹 요소 목록을 가져오기 위해 이 HTTP 요청을 전송합니다. 또한 파일럿이 WebSocket에서 그룹 새로고침 지시를 받으면 동일한 인터페이스로 그룹 요소 목록을 다시 요청해야 합니다.",
            parameters = {
                    @Parameter(name = "workspace_id", description = "워크스페이스 ID", schema = @Schema(format = "uuid")),
                    @Parameter(name = "group_id", description = "요소 그룹 ID. 동일한 요소 그룹은 여러 지도 요소를 포함할 수 있으며, 이는 지도 요소를 그룹화하는 것과 같습니다. 요청 시 group_id 파라미터가 포함되지 않으면 서버는 모든 지도 요소를 반환해야 합니다. group_id가 지정되면 해당 요소 그룹에 속한 요소 집합만 반환하면 됩니다.", schema = @Schema(format = "uuid")),
                    @Parameter(name = "is_distributed", description = "요소 그룹이 분산되어 있는지 여부.")
            }
    )
    @GetMapping(PREFIX + "/workspaces/{workspace_id}/element-groups")
    HttpResultResponse<List<GetMapElementsResponse>> getMapElements(
            @PathVariable(name = "workspace_id") String workspaceId,
            @RequestParam(name = "group_id", required = false) String groupId,
            @RequestParam(name = "is_distributed", required = false) Boolean isDistributed,
            HttpServletRequest req, HttpServletResponse rsp);

    /**
     * 지도 요소 생성
     * 사용자가 PILOT/Web 측에서 점, 선 또는 다각형을 그릴 때 호출됩니다.
     * @param workspaceId 워크스페이스 ID
     * @param groupId 그룹 ID
     * @param elementCreate 생성할 요소 정보
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 생성된 요소 정보
     */
    @Operation(
            summary = "지도 요소 생성",
            description = "사용자가 PILOT/Web에서 점, 선 또는 다각형을 그릴 때 호출됩니다.",
            parameters = {
                    @Parameter(name = "workspace_id", description = "워크스페이스 ID", schema = @Schema(format = "uuid")),
                    @Parameter(name = "group_id", description = "요소 그룹 ID. 동일한 요소 그룹은 여러 지도 요소를 포함할 수 있으며, 이는 지도 요소를 그룹화하는 것과 같습니다. 요청 시 group_id 파라미터가 포함되지 않으면 서버는 모든 지도 요소를 반환해야 합니다. group_id가 지정되면 해당 요소 그룹에 속한 요소 집합만 반환하면 됩니다.", schema = @Schema(format = "uuid"))
            }
    )
    @PostMapping(PREFIX + "/workspaces/{workspace_id}/element-groups/{group_id}/elements")
    HttpResultResponse<CreateMapElementResponse> createMapElement(
            @PathVariable(name = "workspace_id") String workspaceId,
            @PathVariable(name = "group_id") String groupId,
            @Valid @RequestBody CreateMapElementRequest elementCreate,
            HttpServletRequest req, HttpServletResponse rsp);

    /**
     * 지도 요소 수정
     * 사용자가 PILOT/Web 측에서 점, 선 또는 다각형을 편집할 때 호출됩니다.
     * @param workspaceId 워크스페이스 ID
     * @param elementId 요소 ID
     * @param elementUpdate 수정할 요소 정보
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 수정 결과
     */
    @Operation(
            summary = "지도 요소 수정",
            description = "사용자가 PILOT/Web에서 점, 선 또는 다각형을 편집할 때 호출됩니다.",
            parameters = {
                    @Parameter(name = "workspace_id", description = "워크스페이스 ID", schema = @Schema(format = "uuid")),
                    @Parameter(name = "element_id", description = "요소 ID", schema = @Schema(format = "uuid"))
            }
    )
    @PutMapping(PREFIX + "/workspaces/{workspace_id}/elements/{element_id}")
    HttpResultResponse updateMapElement(
            @PathVariable(name = "workspace_id") String workspaceId,
            @PathVariable(name = "element_id") String elementId,
            @Valid @RequestBody UpdateMapElementRequest elementUpdate,
            HttpServletRequest req, HttpServletResponse rsp);

    /**
     * 지도 요소 삭제
     * 사용자가 PILOT/Web 측에서 점, 선 또는 다각형을 삭제할 때 호출됩니다.
     * @param workspaceId 워크스페이스 ID
     * @param elementId 요소 ID
     * @return 삭제 결과
     */
    @Operation(
            summary = "지도 요소 삭제",
            description = "사용자가 PILOT/Web에서 점, 선 또는 다각형을 삭제할 때 호출됩니다.",
            parameters = {
                    @Parameter(name = "workspace_id", description = "워크스페이스 ID", schema = @Schema(format = "uuid")),
                    @Parameter(name = "element_id", description = "요소 ID", schema = @Schema(format = "uuid"))
            }
    )
    @DeleteMapping(PREFIX + "/workspaces/{workspace_id}/elements/{element_id}")
    HttpResultResponse deleteMapElement(
            @PathVariable(name = "workspace_id") String workspaceId,
            @PathVariable(name = "element_id") String elementId,
            HttpServletRequest req, HttpServletResponse rsp);
}
