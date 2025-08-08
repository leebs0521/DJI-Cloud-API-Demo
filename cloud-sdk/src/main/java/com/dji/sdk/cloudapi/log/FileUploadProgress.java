package com.dji.sdk.cloudapi.log;

/**
 * 파일 업로드 진행 상황을 나타내는 클래스
 * 파일 업로드 과정의 진행 상황과 상태 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/9
 */
public class FileUploadProgress {

    /** 확장 진행 상황 정보 */
    private FileUploadProgressExt ext;

    /** 업로드 상태 */
    private FileUploadStatusEnum status;

    /**
     * 기본 생성자
     */
    public FileUploadProgress() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FileUploadProgress{" +
                "ext=" + ext +
                ", status=" + status +
                '}';
    }

    /**
     * 확장 진행 상황 정보를 반환합니다.
     * 
     * @return 확장 진행 상황 정보
     */
    public FileUploadProgressExt getExt() {
        return ext;
    }

    /**
     * 확장 진행 상황 정보를 설정합니다.
     * 
     * @param ext 설정할 확장 진행 상황 정보
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadProgress setExt(FileUploadProgressExt ext) {
        this.ext = ext;
        return this;
    }

    /**
     * 업로드 상태를 반환합니다.
     * 
     * @return 업로드 상태
     */
    public FileUploadStatusEnum getStatus() {
        return status;
    }

    /**
     * 업로드 상태를 설정합니다.
     * 
     * @param status 설정할 업로드 상태
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadProgress setStatus(FileUploadStatusEnum status) {
        this.status = status;
        return this;
    }
}
