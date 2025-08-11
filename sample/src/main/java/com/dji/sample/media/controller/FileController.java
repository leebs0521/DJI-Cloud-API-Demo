package com.dji.sample.media.controller;

import com.dji.sample.media.model.MediaFileDTO;
import com.dji.sample.media.service.IFileService;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.PaginationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

/**
 * 파일 컨트롤러
 * 미디어 파일의 조회 및 다운로드 관련 API를 제공합니다.
 * @author sean
 * @version 0.2
 * @date 2021/12/9
 */
@RestController
@RequestMapping("${url.media.prefix}${url.media.version}/files")
public class FileController {

    @Autowired
    private IFileService fileService;

    /**
     * 워크스페이스 ID를 기반으로 해당 워크스페이스의 모든 미디어 파일 정보를 조회합니다.
     * @param page 페이지 번호 (기본값: 1)
     * @param pageSize 페이지 크기 (기본값: 10)
     * @param workspaceId 워크스페이스 ID
     * @return 미디어 파일 목록 (페이징 처리됨)
     */
    @GetMapping("/{workspace_id}/files")
    public HttpResultResponse<PaginationData<MediaFileDTO>> getFilesList(@RequestParam(defaultValue = "1") Long page,
                                                                         @RequestParam(name = "page_size", defaultValue = "10") Long pageSize,
                                                                         @PathVariable(name = "workspace_id") String workspaceId) {
        PaginationData<MediaFileDTO> filesList = fileService.getMediaFilesPaginationByWorkspaceId(workspaceId, page, pageSize);
        return HttpResultResponse.success(filesList);
    }

    /**
     * 미디어 파일 ID에 따라 파일의 다운로드 주소를 조회하고,
     * 해당 주소로 직접 리다이렉트하여 다운로드를 수행합니다.
     * @param workspaceId 워크스페이스 ID
     * @param fileId 파일 ID
     * @param response HTTP 응답 객체
     */
    @GetMapping("/{workspace_id}/file/{file_id}/url")
    public void getFileUrl(@PathVariable(name = "workspace_id") String workspaceId,
                           @PathVariable(name = "file_id") String fileId, HttpServletResponse response) {

        try {
            URL url = fileService.getObjectUrl(workspaceId, fileId);
            response.sendRedirect(url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
