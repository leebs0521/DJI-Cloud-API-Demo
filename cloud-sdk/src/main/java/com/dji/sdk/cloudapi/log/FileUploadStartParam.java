package com.dji.sdk.cloudapi.log;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 파일 업로드 시작 매개변수를 나타내는 클래스
 * 파일 업로드를 시작할 때 필요한 매개변수 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
public class FileUploadStartParam {

    /** 파일 업로드 시작 파일 목록 (1-2개) */
    @NotNull
    @Size(min = 1, max = 2)
    private List<@Valid FileUploadStartFile> files;

    /**
     * 기본 생성자
     */
    public FileUploadStartParam() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FileUploadStartParam{" +
                "files=" + files +
                '}';
    }

    /**
     * 파일 업로드 시작 파일 목록을 반환합니다.
     * 
     * @return 파일 업로드 시작 파일 목록
     */
    public List<FileUploadStartFile> getFiles() {
        return files;
    }

    /**
     * 파일 업로드 시작 파일 목록을 설정합니다.
     * 
     * @param files 설정할 파일 업로드 시작 파일 목록
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadStartParam setFiles(List<FileUploadStartFile> files) {
        this.files = files;
        return this;
    }
}
