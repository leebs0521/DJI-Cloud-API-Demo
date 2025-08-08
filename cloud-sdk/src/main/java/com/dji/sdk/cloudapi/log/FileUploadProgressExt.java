package com.dji.sdk.cloudapi.log;

import java.util.List;

/**
 * 파일 업로드 확장 진행 상황을 나타내는 클래스
 * 파일 업로드 과정의 상세한 진행 상황 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/9
 */
public class FileUploadProgressExt {

    /** 업로드 진행 상황 파일 목록 */
    private List<FileUploadProgressFile> files;

    /**
     * 기본 생성자
     */
    public FileUploadProgressExt() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FileUploadProgressExt{" +
                "files=" + files +
                '}';
    }

    /**
     * 업로드 진행 상황 파일 목록을 반환합니다.
     * 
     * @return 업로드 진행 상황 파일 목록
     */
    public List<FileUploadProgressFile> getFiles() {
        return files;
    }

    /**
     * 업로드 진행 상황 파일 목록을 설정합니다.
     * 
     * @param files 설정할 업로드 진행 상황 파일 목록
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadProgressExt setFiles(List<FileUploadProgressFile> files) {
        this.files = files;
        return this;
    }
}
