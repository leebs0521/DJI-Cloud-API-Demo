package com.dji.sdk.cloudapi.log;

import java.util.List;

/**
 * 파일 업로드 목록 응답을 나타내는 클래스
 * 파일 업로드 목록 조회 결과를 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/23
 */
public class FileUploadListResponse {

    /** 파일 업로드 목록 파일 정보 목록 */
    private List<FileUploadListFile> files;

    /**
     * 기본 생성자
     */
    public FileUploadListResponse() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FileUploadListResponse{" +
                "files=" + files +
                '}';
    }

    /**
     * 파일 업로드 목록 파일 정보 목록을 반환합니다.
     * 
     * @return 파일 업로드 목록 파일 정보 목록
     */
    public List<FileUploadListFile> getFiles() {
        return files;
    }

    /**
     * 파일 업로드 목록 파일 정보 목록을 설정합니다.
     * 
     * @param files 설정할 파일 업로드 목록 파일 정보 목록
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadListResponse setFiles(List<FileUploadListFile> files) {
        this.files = files;
        return this;
    }
}
