package com.dji.sample.wayline.controller;

import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.wayline.model.dto.WaylineFileDTO;
import com.dji.sample.wayline.service.IWaylineFileService;
import com.dji.sdk.cloudapi.device.DeviceEnum;
import com.dji.sdk.cloudapi.wayline.*;
import com.dji.sdk.cloudapi.wayline.api.IHttpWaylineService;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.PaginationData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.dji.sample.component.AuthInterceptor.TOKEN_CLAIM;

/**
 * DJI Cloud API 웨이라인 파일 관리 컨트롤러
 * <p>
 * 이 클래스는 DJI Pilot에서 웨이라인 파일을 관리하기 위한 REST API 엔드포인트를 제공합니다.
 * 웨이라인은 드론의 자동 비행 경로를 정의하는 파일로, KMZ 형식으로 저장됩니다.
 * <p>
 * 주요 기능:
 * - 웨이라인 파일 업로드 및 임포트 (KMZ 형식)
 * - 웨이라인 파일 목록 조회 및 페이징 처리
 * - 웨이라인 파일 다운로드 URL 생성
 * - 웨이라인 파일 삭제
 * - 웨이라인 이름 중복 검사
 * - 웨이라인 파일 즐겨찾기 관리
 * - 파일 업로드 완료 콜백 처리
 * <p>
 * API 엔드포인트:
 * - DELETE /waylines/{wayline_id}: 웨이라인 파일 삭제
 * - POST /waylines/file/upload: KMZ 파일 업로드
 * - GET /waylines: 웨이라인 목록 조회
 * - GET /waylines/{wayline_id}/download: 파일 다운로드
 * - POST /waylines/duplicate-names: 중복 이름 검사
 * - POST /waylines/upload-callback: 업로드 완료 콜백
 * - POST /waylines/favorites: 즐겨찾기 추가
 * - DELETE /waylines/favorites: 즐겨찾기 제거
 *
 * @author sean
 * @version 0.3
 * @date 2021/12/22
 */
@Tag(name = "[Wayline] 웨이라인 파일 관리", description = "웨이라인 파일을 관리하기 위한 REST API")
@RequiredArgsConstructor
@RestController
public class WaylineFileController implements IHttpWaylineService {

    private final IWaylineFileService waylineFileService;

    /**
     * 워크스페이스에서 지정된 웨이라인 파일을 삭제합니다.
     * <p>
     * 이 메서드는 웨이라인 ID를 기반으로 해당 웨이라인 파일을 데이터베이스에서 삭제합니다.
     * 삭제 작업은 물리적 파일 삭제와 데이터베이스 레코드 삭제를 모두 포함합니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param waylineId   삭제할 웨이라인 파일의 고유 ID
     * @return 삭제 성공 여부를 포함한 HTTP 응답
     */
    @Operation(description = "웨이라인 파일 삭제",
            summary = "웨이라인 ID를 기반으로 해당 웨이라인 파일")
    @DeleteMapping("${url.wayline.prefix}${url.wayline.version}/workspaces/{workspace_id}/waylines/{wayline_id}")
    public HttpResultResponse deleteWayline(
            @Parameter(description = "워크스페이스 ID") @PathVariable(name = "workspace_id") String workspaceId,
            @Parameter(description = "삭제할 웨이라인 파일의 고유 ID") @PathVariable(name = "wayline_id") String waylineId
    ) {
        // 웨이라인 파일 삭제 작업 수행
        boolean isDel = waylineFileService.deleteByWaylineId(workspaceId, waylineId);

        // 삭제 성공 여부에 따라 응답 반환
        return isDel ? HttpResultResponse.success() : HttpResultResponse.error("Failed to delete wayline.");
    }

    /**
     * KMZ 형식의 웨이라인 파일을 업로드하고 임포트합니다.
     * <p>
     * 이 메서드는 DJI Pilot에서 업로드된 KMZ 파일을 받아서 처리합니다.
     * KMZ 파일은 Google Earth에서 생성된 웨이라인 파일로, 드론의 비행 경로를 정의합니다.
     * <p>
     * 처리 과정:
     * 1. 업로드된 파일 유효성 검사
     * 2. 사용자 인증 정보에서 워크스페이스 ID와 생성자 정보 추출
     * 3. 웨이라인 파일 서비스를 통해 파일 임포트 처리
     *
     * @param request HTTP 요청 객체 (인증 정보 포함)
     * @param file    업로드된 KMZ 웨이라인 파일
     * @return 업로드 성공 여부를 포함한 HTTP 응답
     */
    @Operation(description = "웨이라인 파일을 업로드",
            summary = "웨이라인 파일을 업로드")
    @PostMapping("${url.wayline.prefix}${url.wayline.version}/workspaces/{workspace_id}/waylines/file/upload")
    public HttpResultResponse importKmzFile(
            @Parameter(description = "워크스페이스 ID") @PathVariable(name = "workspace_id") String workspaceId,
            @Parameter(hidden = true) HttpServletRequest request,
            @Parameter(description = "웨이라인 파일") MultipartFile file
    ) {
        // 업로드된 파일 존재 여부 확인
        if (Objects.isNull(file)) {
            return HttpResultResponse.error("No file received.");
        }

        // 인증 토큰에서 사용자 정보 추출
        CustomClaim customClaim = (CustomClaim) request.getAttribute(TOKEN_CLAIM);
//        String workspaceId = customClaim.getWorkspaceId();
        String creator = customClaim.getUsername();

        // 웨이라인 파일 임포트 처리
        waylineFileService.importKmzFile(file, workspaceId, creator);
        return HttpResultResponse.success();
    }

    /**
     * 워크스페이스의 웨이라인 파일 목록을 조회합니다.
     * <p>
     * 이 메서드는 페이징 처리와 검색 조건을 지원하여 웨이라인 파일 목록을 반환합니다.
     * DJI Pilot에서 사용하는 고정된 쿼리 조건을 지원합니다.
     * <p>
     * 지원하는 검색 조건:
     * - 웨이라인 이름 검색
     * - 템플릿 타입 필터링
     * - 페이징 처리 (페이지 번호, 페이지 크기)
     * - 정렬 조건
     *
     * @param request     웨이라인 목록 조회 요청 객체 (검색 조건 포함)
     * @param workspaceId 워크스페이스 ID
     * @param req         HTTP 요청 객체
     * @param rsp         HTTP 응답 객체
     * @return 웨이라인 파일 목록과 페이징 정보를 포함한 HTTP 응답
     */
    @Override
    public HttpResultResponse<PaginationData<GetWaylineListResponse>> getWaylineList(
            @Valid GetWaylineListRequest request,
            String workspaceId,
            HttpServletRequest req, HttpServletResponse rsp
    ) {
        // 웨이라인 파일 목록 조회 및 페이징 처리
        PaginationData<GetWaylineListResponse> data = waylineFileService.getWaylinesByParam(workspaceId, request);
        return HttpResultResponse.success(data);
    }

    /**
     * 웨이라인 파일의 다운로드 URL을 생성하고 해당 URL로 리다이렉트합니다.
     * <p>
     * 이 메서드는 웨이라인 파일 ID를 기반으로 클라우드 스토리지에서 파일의 다운로드 URL을 생성하고,
     * 사용자를 해당 URL로 직접 리다이렉트하여 파일 다운로드를 수행합니다.
     * <p>
     * 처리 과정:
     * 1. 웨이라인 파일 ID로 다운로드 URL 생성
     * 2. 생성된 URL로 HTTP 리다이렉트 수행
     * 3. 오류 발생 시 예외 처리
     *
     * @param workspaceId 워크스페이스 ID
     * @param waylineId   다운로드할 웨이라인 파일의 고유 ID
     * @param req         HTTP 요청 객체
     * @param rsp         HTTP 응답 객체 (리다이렉트용)
     */
    @Override
    public void getWaylineFileDownloadAddress(
            String workspaceId,
            String waylineId,
            HttpServletRequest req, HttpServletResponse rsp
    ) {
        try {
            // 웨이라인 파일의 다운로드 URL 생성
            URL url = waylineFileService.getObjectUrl(workspaceId, waylineId);

            // 생성된 URL로 리다이렉트 수행
            rsp.sendRedirect(url.toString());

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 웨이라인 이름의 중복 여부를 검사합니다.
     * <p>
     * 이 메서드는 웨이라인 파일 업로드 시 이름의 고유성을 보장하기 위해 사용됩니다.
     * DJI Pilot에서 웨이라인 업로드 시 반드시 호출되어야 하는 인터페이스입니다.
     * <p>
     * 검사 과정:
     * 1. 입력된 이름 목록에서 현재 워크스페이스에 존재하는 이름들을 찾음
     * 2. 중복되는 이름들을 목록으로 반환
     * 3. 빈 목록이 반환되면 모든 이름이 고유함을 의미
     *
     * @param workspaceId 워크스페이스 ID
     * @param names       중복 검사를 수행할 웨이라인 이름 목록 (최소 1개 이상)
     * @param req         HTTP 요청 객체
     * @param rsp         HTTP 응답 객체
     * @return 중복되는 웨이라인 이름 목록을 포함한 HTTP 응답
     */
    @Override
    public HttpResultResponse<List<String>> getDuplicatedWaylineName(
            String workspaceId,
            @NotNull @Size(min = 1) List<String> names,
            HttpServletRequest req, HttpServletResponse rsp
    ) {
        // 중복되는 웨이라인 이름 목록 조회
        List<String> existNamesList = waylineFileService.getDuplicateNames(workspaceId, names);

        return HttpResultResponse.success(existNamesList);
    }

    /**
     * 웨이라인 파일 업로드 완료 콜백을 처리합니다.
     * <p>
     * 이 메서드는 DJI Pilot에서 웨이라인 파일을 스토리지 서버에 업로드한 후,
     * 파일의 기본 정보를 보고하기 위해 호출됩니다.
     * <p>
     * 처리되는 정보:
     * - 파일 메타데이터 (이름, 크기, 해시값 등)
     * - 템플릿 타입 정보
     * - 페이로드 모델 키 정보
     * - 드론 모델 키 정보
     * - 업로드한 사용자 정보
     *
     * @param workspaceId 워크스페이스 ID
     * @param request     웨이라인 업로드 콜백 요청 객체
     * @param req         HTTP 요청 객체
     * @param rsp         HTTP 응답 객체
     * @return 업로드 완료 처리 성공 여부를 포함한 HTTP 응답
     */
    @Override
    public HttpResultResponse fileUploadResultReport(
            String workspaceId,
            @Valid WaylineUploadCallbackRequest request,
            HttpServletRequest req, HttpServletResponse rsp
    ) {
        // 인증 토큰에서 사용자 정보 추출
        CustomClaim customClaim = (CustomClaim) req.getAttribute(TOKEN_CLAIM);

        // 업로드된 파일의 메타데이터 추출
        WaylineUploadCallbackMetadata metadata = request.getMetadata();

        // 웨이라인 파일 DTO 객체 생성
        WaylineFileDTO file = WaylineFileDTO.builder()
                .username(customClaim.getUsername())                    // 업로드한 사용자명
                .objectKey(request.getObjectKey())                     // 스토리지 객체 키
                .name(request.getName())                               // 웨이라인 파일명
                .templateTypes(metadata.getTemplateTypes().stream()    // 템플릿 타입 목록
                        .map(WaylineTypeEnum::getValue)
                        .collect(Collectors.toList()))
                .payloadModelKeys(metadata.getPayloadModelKeys().stream()  // 페이로드 모델 키 목록
                        .map(DeviceEnum::getDevice)
                        .collect(Collectors.toList()))
                .droneModelKey(metadata.getDroneModelKey().getDevice()) // 드론 모델 키
                .build();

        // 웨이라인 파일 정보를 데이터베이스에 저장
        int id = waylineFileService.saveWaylineFile(workspaceId, file);

        return id <= 0 ? HttpResultResponse.error() : HttpResultResponse.success();
    }

    /**
     * 지정된 웨이라인 파일들을 즐겨찾기로 추가합니다.
     * <p>
     * 이 메서드는 사용자가 자주 사용하는 웨이라인 파일들을 즐겨찾기로 표시할 수 있게 합니다.
     * 즐겨찾기된 웨이라인은 목록에서 우선적으로 표시되거나 별도 섹션에 표시될 수 있습니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param ids         즐겨찾기로 추가할 웨이라인 파일 ID 목록 (최소 1개 이상)
     * @param req         HTTP 요청 객체
     * @param rsp         HTTP 응답 객체
     * @return 즐겨찾기 추가 성공 여부를 포함한 HTTP 응답
     */
    @Override
    public HttpResultResponse batchFavoritesWayline(
            String workspaceId,
            @NotNull @Size(min = 1) List<String> ids,
            HttpServletRequest req, HttpServletResponse rsp
    ) {
        // 웨이라인 파일들을 즐겨찾기로 추가
        boolean isMark = waylineFileService.markFavorite(workspaceId, ids, true);

        return isMark ? HttpResultResponse.success() : HttpResultResponse.error();
    }

    /**
     * 지정된 웨이라인 파일들의 즐겨찾기를 제거합니다.
     * <p>
     * 이 메서드는 이전에 즐겨찾기로 추가된 웨이라인 파일들의 즐겨찾기 표시를 해제합니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param ids         즐겨찾기를 제거할 웨이라인 파일 ID 목록 (최소 1개 이상)
     * @param req         HTTP 요청 객체
     * @param rsp         HTTP 응답 객체
     * @return 즐겨찾기 제거 성공 여부를 포함한 HTTP 응답
     */
    @Override
    public HttpResultResponse batchUnfavoritesWayline(
            String workspaceId,
            @NotNull @Size(min = 1) List<String> ids,
            HttpServletRequest req, HttpServletResponse rsp
    ) {
        // 웨이라인 파일들의 즐겨찾기 표시 해제
        boolean isMark = waylineFileService.markFavorite(workspaceId, ids, false);

        return isMark ? HttpResultResponse.success() : HttpResultResponse.error();
    }
}
