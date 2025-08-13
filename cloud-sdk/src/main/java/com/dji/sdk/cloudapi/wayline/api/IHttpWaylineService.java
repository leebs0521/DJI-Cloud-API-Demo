package com.dji.sdk.cloudapi.wayline.api;

import com.dji.sdk.cloudapi.wayline.GetWaylineListRequest;
import com.dji.sdk.cloudapi.wayline.GetWaylineListResponse;
import com.dji.sdk.cloudapi.wayline.WaylineUploadCallbackRequest;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.PaginationData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 웨이라인 HTTP 서비스 인터페이스
 * 
 * 이 인터페이스는 웨이라인 파일과 관련된 HTTP API를 정의합니다.
 * 웨이라인 파일의 조회, 다운로드, 업로드, 즐겨찾기 등의 기능을 포함하며,
 * RESTful API 엔드포인트를 제공합니다.
 * 
 * 주요 기능:
 * - 웨이라인 파일 목록 조회
 * - 웨이라인 파일 다운로드
 * - 중복 파일명 확인
 * - 파일 업로드 결과 보고
 * - 즐겨찾기 관리
 * 
 * 이 인터페이스는 웨이라인 파일의 전체 생명주기를 관리하는
 * HTTP API 서비스를 제공합니다.
 * 
 * @author sean
 * @version 0.3
 * @date 2021/12/22
 */
//@Tag(name = "웨이라인 인터페이스")
public interface IHttpWaylineService {

    /**
     * API 경로 접두사
     * 
     * 웨이라인 관련 API의 기본 경로를 정의합니다.
     * 모든 웨이라인 API는 이 접두사로 시작합니다.
     */
    String PREFIX = "wayline/api/v1";

    /**
     * 조회 조건에 따라 웨이라인 파일의 기본 데이터를 조회합니다.
     * 
     * 파일럿의 조회 조건 필드는 고정되어 있습니다.
     * 웨이라인 파일의 목록을 페이지네이션과 함께 조회할 수 있습니다.
     * 
     * @param request 웨이라인 목록 조회 매개변수
     * @param workspaceId 워크스페이스 ID (UUID 형식)
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 웨이라인 목록 (페이지네이션 포함)
     */
    @Operation(summary = "웨이라인 목록 조회", description = "조회 조건에 따라 웨이라인 파일의 기본 데이터를 조회합니다. " +
            "파일럿의 조회 조건 필드는 고정되어 있습니다.",
            parameters = {
                @Parameter(name = "workspace_id", description = "워크스페이스 ID", schema = @Schema(format = "uuid"))
            })
    @GetMapping(PREFIX + "/workspaces/{workspace_id}/waylines")
    HttpResultResponse<PaginationData<GetWaylineListResponse>> getWaylineList(
            @Valid @ParameterObject GetWaylineListRequest request,
            @PathVariable(name = "workspace_id") String workspaceId,
            HttpServletRequest req, HttpServletResponse rsp);

    /**
     * 웨이라인 파일 ID에 따라 파일의 다운로드 주소를 조회하고,
     * 해당 주소로 직접 리다이렉트하여 다운로드합니다.
     * 
     * @param workspaceId 워크스페이스 ID (UUID 형식)
     * @param waylineId 웨이라인 파일 ID (UUID 형식)
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     */
    @Operation(summary = "웨이라인 파일 다운로드 주소 조회", description = "웨이라인 파일 ID에 따라 파일의 다운로드 주소를 조회하고, " +
            "해당 주소로 직접 리다이렉트하여 다운로드합니다.",
            parameters = {
                @Parameter(name = "workspace_id", description = "워크스페이스 ID", schema = @Schema(format = "uuid")),
                @Parameter(name = "wayline_id", description = "웨이라인 ID", schema = @Schema(format = "uuid"))
            })
    @GetMapping(PREFIX + "/workspaces/{workspace_id}/waylines/{wayline_id}/url")
    void getWaylineFileDownloadAddress(
            @PathVariable(name = "workspace_id") String workspaceId,
            @PathVariable(name = "wayline_id") String waylineId,
            HttpServletRequest req, HttpServletResponse rsp);

    /**
     * 웨이라인 이름에 따라 이미 존재하는지 확인합니다.
     * 웨이라인 이름의 고유성을 보장해야 합니다.
     * 웨이라인 업로드 시 이 인터페이스가 호출되며 반드시 사용 가능해야 합니다.
     * 
     * @param workspaceId 워크스페이스 ID (UUID 형식)
     * @param names 웨이라인 파일명 컬렉션
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 이미 존재하는 웨이라인 이름 목록
     */
    @Operation(summary = "중복 웨이라인 이름 조회", description = "웨이라인 이름에 따라 이미 존재하는지 확인합니다. " +
            "웨이라인 이름의 고유성을 보장해야 합니다. " +
            "웨이라인 업로드 시 이 인터페이스가 호출되며 반드시 사용 가능해야 합니다.",
            parameters = {
                @Parameter(name = "workspace_id", description = "워크스페이스 ID", required = true),
                @Parameter(name = "name", description = "웨이라인 파일명", required = true)
            })
    @GetMapping(PREFIX + "/workspaces/{workspace_id}/waylines/duplicate-names")
    HttpResultResponse<List<String>> getDuplicatedWaylineName(
            @PathVariable(name = "workspace_id") String workspaceId,
            @NotNull @Size(min = 1) @RequestParam(name = "name") List<String> names,
            HttpServletRequest req, HttpServletResponse rsp);

    /**
     * 파일럿이 웨이라인 파일을 스토리지 서버에 업로드했을 때,
     * 이 인터페이스를 통해 파일의 기본 정보를 보고합니다.
     * 
     * @param workspaceId 워크스페이스 ID (UUID 형식)
     * @param request 업로드 콜백 매개변수
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 업로드 결과 응답
     */
    @Operation(summary = "파일 업로드 결과 보고", description = "파일럿이 웨이라인 파일을 스토리지 서버에 업로드했을 때, " +
            "이 인터페이스를 통해 파일의 기본 정보를 보고합니다.",
            parameters = {
                @Parameter(name = "workspace_id", description = "워크스페이스 ID", required = true)
            })
    @PostMapping(PREFIX + "/workspaces/{workspace_id}/upload-callback")
    HttpResultResponse fileUploadResultReport(
            @PathVariable(name = "workspace_id") String workspaceId,
            @Valid @RequestBody WaylineUploadCallbackRequest request,
            HttpServletRequest req, HttpServletResponse rsp);

    /**
     * 웨이라인 파일 ID에 따라 웨이라인 파일을 즐겨찾기에 추가합니다.
     * 
     * @param workspaceId 워크스페이스 ID (UUID 형식)
     * @param ids 웨이라인 파일 ID 목록
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 성공 여부
     */
    @Operation(summary = "웨이라인 일괄 즐겨찾기", description = "웨이라인 파일 ID에 따라 웨이라인 파일을 즐겨찾기에 추가합니다.",
            parameters = {
                @Parameter(name = "workspace_id", description = "워크스페이스 ID", required = true),
                @Parameter(name = "id", description = "웨이라인 ID", required = true)
            })
    @PostMapping(PREFIX + "/workspaces/{workspace_id}/favorites")
    HttpResultResponse batchFavoritesWayline(
            @PathVariable(name = "workspace_id") String workspaceId,
            @NotNull @Size(min = 1) @RequestParam(name = "id") List<String> ids,
            HttpServletRequest req, HttpServletResponse rsp);

    /**
     * 웨이라인 파일 ID에 따라 이 웨이라인 파일의 즐겨찾기를 삭제합니다.
     * 
     * @param workspaceId 워크스페이스 ID (UUID 형식)
     * @param ids 웨이라인 파일 ID 목록
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 성공 여부
     */
    @Operation(summary = "웨이라인 일괄 즐겨찾기 해제", description = "웨이라인 파일 ID에 따라 이 웨이라인 파일의 즐겨찾기를 삭제합니다.",
            parameters = {
                @Parameter(name = "workspace_id", description = "워크스페이스 ID", required = true),
                @Parameter(name = "id", description = "웨이라인 ID", required = true)
            })
    @DeleteMapping(PREFIX + "/workspaces/{workspace_id}/favorites")
    HttpResultResponse batchUnfavoritesWayline(
            @PathVariable(name = "workspace_id") String workspaceId,
            @NotNull @Size(min = 1) @RequestParam(name = "id") List<String> ids,
            HttpServletRequest req, HttpServletResponse rsp);
}
