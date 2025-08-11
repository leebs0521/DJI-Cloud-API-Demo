package com.dji.sample.media.service;

import com.dji.sample.media.model.MediaFileDTO;
import com.dji.sdk.cloudapi.media.MediaUploadCallbackRequest;
import com.dji.sdk.common.PaginationData;

import java.net.URL;
import java.util.List;

/**
 * 파일 서비스 인터페이스
 * 미디어 파일의 데이터베이스 조작과 관련된 비즈니스 로직을 정의하는 서비스 인터페이스입니다.
 * 파일의 저장, 조회, 중복 검사, 다운로드 URL 생성 등의 기능을 제공합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/9
 */
public interface IFileService {

    /**
     * 워크스페이스 ID와 파일의 지문(fingerprint)을 기반으로 파일이 이미 존재하는지 확인합니다.
     * @param workspaceId 워크스페이스 ID
     * @param fingerprint 파일 지문 (파일 내용의 해시 값)
     * @return 파일 존재 여부 (true: 존재함, false: 존재하지 않음)
     */
    Boolean checkExist(String workspaceId, String fingerprint);

    /**
     * 파일의 기본 정보를 데이터베이스에 저장합니다.
     * @param workspaceId 워크스페이스 ID
     * @param file 미디어 업로드 콜백 요청 객체
     * @return 저장된 레코드의 ID
     */
    Integer saveFile(String workspaceId, MediaUploadCallbackRequest file);

    /**
     * 워크스페이스 ID를 기반으로 해당 워크스페이스의 모든 파일 정보를 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @return 미디어 파일 DTO 목록
     */
    List<MediaFileDTO> getAllFilesByWorkspaceId(String workspaceId);

    /**
     * 워크스페이스의 모든 미디어 파일을 페이징 처리하여 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @param page 페이지 번호
     * @param pageSize 페이지 크기
     * @return 페이징 처리된 미디어 파일 데이터
     */
    PaginationData<MediaFileDTO> getMediaFilesPaginationByWorkspaceId(String workspaceId, long page, long pageSize);

    /**
     * 파일의 다운로드 주소를 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @param fileId 파일 ID
     * @return 파일 다운로드 URL
     */
    URL getObjectUrl(String workspaceId, String fileId);

    /**
     * 특정 작업의 모든 미디어 파일을 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @param jobId 작업 ID
     * @return 해당 작업의 미디어 파일 DTO 목록
     */
    List<MediaFileDTO> getFilesByWorkspaceAndJobId(String workspaceId, String jobId);
}
