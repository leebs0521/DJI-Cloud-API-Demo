package com.dji.sample.media.controller;

import com.dji.sample.media.model.MediaFileDTO;
import com.dji.sample.media.service.IFileService;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.PaginationData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

/**
 * 파일 컨트롤러
 * 미디어 파일의 조회 및 다운로드 관련 API를 제공합니다.
 * author sean
 * version 0.2
 * date 2021/12/9
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("${url.media.prefix}${url.media.version}/files")
@Tag(name = "[Media] 미디어 파일", description = "미디어 파일 조회 및 다운로드 API")
public class FileController {

    private final IFileService fileService;

    /**
     * 워크스페이스 ID를 기반으로 해당 워크스페이스의 모든 미디어 파일 정보를 조회합니다.
     *
     * @param page        페이지 번호 (기본값: 1)
     * @param pageSize    페이지 크기 (기본값: 10)
     * @param workspaceId 워크스페이스 ID
     * @return 미디어 파일 목록 (페이징 처리됨)
     */
    @GetMapping("/{workspace_id}/files")
    @Operation(summary = "미디어 파일 목록 조회", description = "워크스페이스 ID로 페이징된 미디어 파일 목록을 조회합니다.")
    public HttpResultResponse<PaginationData<MediaFileDTO>> getFilesList(
            @Parameter(description = "페이지 번호 (기본값: 1)") @RequestParam(defaultValue = "1") Long page,
            @Parameter(description = "페이지 크기 (기본값: 10)") @RequestParam(name = "page_size", defaultValue = "10") Long pageSize,
            @Parameter(description = "워크스페이스 ID") @PathVariable(name = "workspace_id") String workspaceId
    ) {
        PaginationData<MediaFileDTO> filesList = fileService.getMediaFilesPaginationByWorkspaceId(workspaceId, page, pageSize);
        return HttpResultResponse.success(filesList);
    }

    /**
     * 미디어 파일 ID에 따라 파일의 다운로드 주소를 조회하고,
     * 해당 주소로 직접 리다이렉트하여 다운로드를 수행합니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param fileId      파일 ID
     * @param response    HTTP 응답 객체
     */
    @GetMapping("/{workspace_id}/file/{file_id}/url")
    @Operation(summary = "파일 다운로드 URL 리다이렉트", description = "파일 ID에 해당하는 다운로드 주소를 조회하여 302 리다이렉트합니다.")
    public void getFileUrl(
            @Parameter(description = "워크스페이스 ID") @PathVariable(name = "workspace_id") String workspaceId,
            @Parameter(description = "파일 ID") @PathVariable(name = "file_id") String fileId,
            @Parameter(hidden = true) HttpServletResponse response
    ) {
        try {
            URL url = fileService.getObjectUrl(workspaceId, fileId);
            response.sendRedirect(url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
