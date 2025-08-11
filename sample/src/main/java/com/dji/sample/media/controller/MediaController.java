package com.dji.sample.media.controller;

import com.dji.sample.media.service.IMediaService;
import com.dji.sdk.cloudapi.media.*;
import com.dji.sdk.cloudapi.media.api.IHttpMediaService;
import com.dji.sdk.common.HttpResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 미디어 컨트롤러
 * 미디어 파일의 업로드, 조회, 콜백 처리 등 미디어 관련 API를 제공합니다.
 * @author sean
 * @version 0.2
 * @date 2021/12/7
 */
@Slf4j
@RestController
public class MediaController implements IHttpMediaService {

    @Autowired
    private IMediaService mediaService;

    /**
     * 파일의 지문(fingerprint)을 통해 해당 파일이 이미 업로드되었는지 확인합니다.
     * @param workspaceId 워크스페이스 ID
     * @param request 미디어 빠른 업로드 요청
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 파일 존재 여부에 따른 응답
     */
    @Override
    public HttpResultResponse mediaFastUpload(String workspaceId, @Valid MediaFastUploadRequest request, HttpServletRequest req, HttpServletResponse rsp) {
        boolean isExist = mediaService.fastUpload(workspaceId, request.getFingerprint());

        return isExist ? HttpResultResponse.success() : HttpResultResponse.error(request.getFingerprint() + "don't exist.");
    }

    /**
     * 파일이 파일럿에 의해 스토리지 서버에 업로드될 때,
     * 이 인터페이스를 통해 파일의 기본 정보를 보고합니다.
     * @param workspaceId 워크스페이스 ID
     * @param request 미디어 업로드 콜백 요청
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 업로드된 파일의 객체 키
     */
    @Override
    public HttpResultResponse<String> mediaUploadCallback(String workspaceId, @Valid MediaUploadCallbackRequest request, HttpServletRequest req, HttpServletResponse rsp) {
        mediaService.saveMediaFile(workspaceId, request);
        return HttpResultResponse.success(request.getObjectKey());
    }

    /**
     * 워크스페이스 ID와 작은 지문(tiny fingerprint) 컬렉션을 기반으로
     * 해당 워크스페이스에 이미 존재하는 파일들을 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @param request 작은 지문 요청 (본문에는 tiny_fingerprint 매개변수만 있음.
     *                Map으로 매개변수를 받는 것은 권장하지 않습니다.)
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 존재하는 파일의 작은 지문 목록
     */
    @Override
    public HttpResultResponse<GetFileFingerprintResponse> getExistFileTinyFingerprint(String workspaceId, @Valid GetFileFingerprintRequest request, HttpServletRequest req, HttpServletResponse rsp) {
        List<String> existingList = mediaService.getExistTinyFingerprints(workspaceId, request.getTinyFingerprints());
        return HttpResultResponse.success(new GetFileFingerprintResponse().setTinyFingerprints(existingList));
    }

    /**
     * 폴더 업로드 콜백을 처리합니다.
     * @param workspaceId 워크스페이스 ID
     * @param request 폴더 업로드 콜백 요청
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 폴더 업로드 결과
     */
    @Override
    public HttpResultResponse folderUploadCallback(String workspaceId, @Valid FolderUploadCallbackRequest request, HttpServletRequest req, HttpServletResponse rsp) {
        return null;
    }
}