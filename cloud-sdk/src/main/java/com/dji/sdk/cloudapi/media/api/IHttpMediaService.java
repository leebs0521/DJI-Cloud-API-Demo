package com.dji.sdk.cloudapi.media.api;

import com.dji.sdk.cloudapi.media.*;
import com.dji.sdk.common.HttpResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 미디어 관리 HTTP API 인터페이스
 *
 * 이 인터페이스는 드론에서 촬영한 미디어 파일(사진, 비디오)의 업로드 및 관리를 위한
 * HTTP API를 정의합니다. 파일 업로드, 업로드 상태 확인, 파일 중복 검사 등의 기능을 제공합니다.
 *
 * 주요 기능:
 * - 미디어 파일 빠른 업로드(중복 파일 검사)
 * - 파일 업로드 완료 콜백
 * - 파일 지문(fingerprint) 기반 중복 검사
 * - 파일 그룹 업로드 상태 보고
 *
 * author: sean
 * version: 0.2
 * date: 2021/12/9
 */
@Tag(name = "미디어 인터페이스")
public interface IHttpMediaService {

    String PREFIX = "media/api/v1";

    /**
     * 미디어 파일 빠른 업로드
     *
     * 파일 지문(fingerprint)을 사용하여 파일이 이미 업로드되었는지 확인합니다.
     * 중복 파일인 경우 업로드를 건너뛰고 기존 파일 정보를 반환합니다.
     *
     * 사용 시나리오:
     * - 드론에서 촬영한 미디어 파일을 업로드하기 전에 중복 검사
     * - 네트워크 대역폭 절약을 위한 중복 업로드 방지
     * - 파일 업로드 시간 단축
     *
     * @param workspaceId 워크스페이스 ID
     * @param request 파일 지문 정보가 포함된 요청
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 업로드 결과(중복 파일인 경우 기존 파일 정보 반환)
     */
    @Operation(
            summary = "미디어 빠른 업로드",
            description = "파일 지문을 통해 해당 파일의 업로드 여부를 확인합니다.",
            parameters = {
                    @Parameter(name = "workspace_id", description = "워크스페이스 ID", schema = @Schema(format = "uuid"))
            }
    )
    @PostMapping(PREFIX + "/workspaces/{workspace_id}/fast-upload")
    HttpResultResponse mediaFastUpload(
            @PathVariable(name = "workspace_id") String workspaceId,
            @Valid @RequestBody MediaFastUploadRequest request,
            HttpServletRequest req, HttpServletResponse rsp);

    /**
     * 미디어 파일 업로드 완료 콜백
     *
     * 파일럿이 스토리지 서버에 파일을 업로드한 후,
     * 파일의 기본 정보를 이 인터페이스를 통해 보고합니다.
     *
     * 사용 시나리오:
     * - 드론에서 촬영한 사진/비디오 파일 업로드 완료 후 서버에 알림
     * - 파일 메타데이터(촬영 시간, 위치, 파일 크기 등) 보고
     * - 업로드된 파일의 클라우드 저장소 경로 반환
     *
     * @param workspaceId 워크스페이스 ID
     * @param request 업로드된 파일의 메타데이터 정보
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 업로드된 파일의 클라우드 저장소 경로
     */
    @Operation(
            summary = "앱 파일 업로드 결과 보고",
            description = "파일럿이 스토리지 서버로 파일 업로드를 완료한 후, 파일의 기본 정보를 이 인터페이스를 통해 보고합니다.",
            parameters = {
                    @Parameter(name = "workspace_id", description = "워크스페이스 ID", schema = @Schema(format = "uuid"))
            },
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "responseObjectKey",
                                            summary = "응답 객체 키 예시",
                                            description = "응답 객체의 키 예시",
                                            value = "{\"code\": 0, \"message\":\"success\", \"data\": \"media/DJI_20220831151616_0004_W_Waypoint4.JPG\"}"
                                    )
                            }
                    )
            )
    )
    @PostMapping(PREFIX + "/workspaces/{workspace_id}/upload-callback")
    HttpResultResponse<String> mediaUploadCallback(
            @PathVariable(name = "workspace_id") String workspaceId,
            @Valid @RequestBody MediaUploadCallbackRequest request,
            HttpServletRequest req, HttpServletResponse rsp);

    /**
     * 파일 지문 존재 여부 확인
     *
     * 워크스페이스 ID와 작은 지문(tiny fingerprint) 컬렉션을 기반으로
     * 이 워크스페이스에 이미 존재하는 파일들을 조회합니다.
     *
     * 사용 시나리오:
     * - 대용량 파일 업로드 전에 중복 파일 확인
     * - 여러 파일을 일괄 업로드할 때 중복 검사
     * - 네트워크 대역폭 절약을 위한 효율적인 파일 관리
     *
     * @param workspaceId 워크스페이스 ID
     * @param request 작은 지문 정보가 포함된 요청(body에는 tiny_fingerprint 파라미터만 포함)
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 존재하는 파일들의 지문 정보
     */
    @Operation(
            summary = "파일 지문 존재 여부 확인",
            description = "워크스페이스 ID와 작은 지문(tiny fingerprint) 집합을 기반으로, 해당 워크스페이스에 이미 존재하는 파일을 조회합니다.",
            parameters = {
                    @Parameter(name = "workspace_id", description = "워크스페이스 ID", schema = @Schema(format = "uuid"))
            }
    )
    @PostMapping(PREFIX + "/workspaces/{workspace_id}/files/tiny-fingerprints")
    HttpResultResponse<GetFileFingerprintResponse> getExistFileTinyFingerprint(
            @PathVariable(name = "workspace_id") String workspaceId,
            @Valid @RequestBody GetFileFingerprintRequest request,
            HttpServletRequest req, HttpServletResponse rsp);

    /**
     * 파일 그룹 업로드 완료 콜백
     *
     * 파일 그룹 내의 미디어 파일들의 업로드 상태를 실시간으로 보고합니다.
     * 여러 파일이 포함된 그룹 업로드의 완료 상태를 관리합니다.
     *
     * 사용 시나리오:
     * - 드론 비행 중 촬영된 여러 미디어 파일들의 일괄 업로드 완료 알림
     * - 파일 그룹의 전체 업로드 진행률 추적
     * - 업로드 실패한 파일들의 재시도 관리
     *
     * @param workspaceId 워크스페이스 ID
     * @param request 파일 그룹의 업로드 상태 정보
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 업로드 상태 처리 결과
     */
    @Operation(
            summary = "파일 그룹 업로드 완료 콜백",
            description = "파일 그룹에 포함된 미디어 파일들의 업로드 상태를 실시간으로 보고합니다.",
            parameters = {
                    @Parameter(name = "workspace_id", description = "워크스페이스 ID", schema = @Schema(format = "uuid"))
            }
    )
    @PostMapping(PREFIX + "/workspaces/{workspace_id}/group-upload-callback")
    HttpResultResponse folderUploadCallback(
            @PathVariable(name = "workspace_id") String workspaceId,
            @Valid @RequestBody FolderUploadCallbackRequest request,
            HttpServletRequest req, HttpServletResponse rsp);
}
