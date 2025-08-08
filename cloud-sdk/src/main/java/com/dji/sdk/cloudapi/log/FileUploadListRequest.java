package com.dji.sdk.cloudapi.log;

import com.dji.sdk.common.BaseModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 파일 업로드 목록 요청을 나타내는 클래스
 * 파일 업로드 목록을 조회하기 위한 요청 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/23
 */
public class FileUploadListRequest extends BaseModel {

    /**
     * 파일 필터 목록
     * 조회할 파일들의 모듈 목록
     */
    @NotNull
    @Size(min = 1, max = 2)
    private List<LogModuleEnum> moduleList;

    /**
     * 기본 생성자
     */
    public FileUploadListRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FileUploadListRequest{" +
                "moduleList=" + moduleList +
                '}';
    }

    /**
     * 파일 필터 목록을 반환합니다.
     * 
     * @return 파일 필터 목록
     */
    public List<LogModuleEnum> getModuleList() {
        return moduleList;
    }

    /**
     * 파일 필터 목록을 설정합니다.
     * 
     * @param moduleList 설정할 파일 필터 목록
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadListRequest setModuleList(List<LogModuleEnum> moduleList) {
        this.moduleList = moduleList;
        return this;
    }
}
