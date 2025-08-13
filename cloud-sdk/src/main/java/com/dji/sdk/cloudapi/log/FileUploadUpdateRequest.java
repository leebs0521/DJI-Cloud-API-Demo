package com.dji.sdk.cloudapi.log;

import com.dji.sdk.common.BaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 파일 업로드 업데이트 요청을 나타내는 클래스
 * 파일 업로드 상태를 업데이트하기 위한 요청 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/23
 */
@Schema(description = "파일 업로드 상태 업데이트 요청")
public class FileUploadUpdateRequest extends BaseModel {

    /**
     * 파일 필터 목록
     * 업데이트할 파일들의 모듈 목록
     */
    @NotNull
    @Size(min = 1, max = 2)
    @ArraySchema(schema = @Schema(implementation = LogModuleEnum.class))
    @Schema(description = "업데이트할 파일들의 모듈 목록")
    private List<LogModuleEnum> moduleList;

    /** 업데이트할 상태 */
    @NotNull
    @Schema(description = "업데이트할 상태")
    private FileUploadUpdateStatusEnum status;

    /**
     * 기본 생성자
     */
    public FileUploadUpdateRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FileUploadUpdateRequest{" +
                "moduleList=" + moduleList +
                ", status=" + status +
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
    public FileUploadUpdateRequest setModuleList(List<LogModuleEnum> moduleList) {
        this.moduleList = moduleList;
        return this;
    }

    /**
     * 업데이트할 상태를 반환합니다.
     * 
     * @return 업데이트할 상태
     */
    public FileUploadUpdateStatusEnum getStatus() {
        return status;
    }

    /**
     * 업데이트할 상태를 설정합니다.
     * 
     * @param status 설정할 업데이트할 상태
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadUpdateRequest setStatus(FileUploadUpdateStatusEnum status) {
        this.status = status;
        return this;
    }
}
