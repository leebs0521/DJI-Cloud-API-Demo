package com.dji.sample.media.service;

import com.dji.sdk.cloudapi.media.MediaUploadCallbackRequest;

import java.util.List;

/**
 * 미디어 서비스 인터페이스
 * 미디어 파일의 업로드, 저장, 중복 검사 등의 핵심 비즈니스 로직을 정의하는 서비스 인터페이스입니다.
 * 파일 업로드 콜백 처리, 빠른 업로드 검증, 지문 기반 중복 검사 등의 기능을 제공합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/9
 */
public interface IMediaService {

    /**
     * 파일의 지문(fingerprint)을 통해 해당 파일이 이미 업로드되었는지 확인합니다.
     * 빠른 업로드 검증을 위해 사용되며, 중복 파일 업로드를 방지합니다.
     * @param workspaceId 워크스페이스 ID
     * @param fingerprint 파일 지문 (파일 내용의 해시 값)
     * @return 파일 존재 여부 (true: 이미 업로드됨, false: 업로드되지 않음)
     */
    Boolean fastUpload(String workspaceId, String fingerprint);

    /**
     * 파일의 기본 정보를 데이터베이스에 저장합니다.
     * 업로드된 미디어 파일의 메타데이터를 영구 저장소에 기록합니다.
     * @param workspaceId 워크스페이스 ID
     * @param file 미디어 업로드 콜백 요청 객체
     * @return 저장된 레코드의 ID
     */
    Integer saveMediaFile(String workspaceId, MediaUploadCallbackRequest file);

    /**
     * 워크스페이스 ID를 기반으로 해당 워크스페이스의 모든 파일의 작은 지문(tiny fingerprint)을 조회합니다.
     * 빠른 중복 검사를 위해 사용되는 축약된 해시 값들을 반환합니다.
     * @param workspaceId 워크스페이스 ID
     * @return 작은 지문 문자열 목록
     */
    List<String> getAllTinyFingerprintsByWorkspaceId(String workspaceId);

    /**
     * 입력받은 작은 지문(tiny fingerprint) 데이터를 기반으로 이미 존재하는 지문들을 조회합니다.
     * 업로드 전 중복 파일 검사를 위해 사용됩니다.
     * @param workspaceId 워크스페이스 ID
     * @param tinyFingerprints 검사할 작은 지문 목록
     * @return 이미 존재하는 작은 지문 목록
     */
    List<String> getExistTinyFingerprints(String workspaceId, List<String> tinyFingerprints);

}
