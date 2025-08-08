package com.dji.sdk.cloudapi.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

/**
 * 폴더 업로드 콜백 요청 데이터 클래스
 * 
 * 이 클래스는 파일 그룹(폴더) 업로드 완료 후 호출되는 콜백의 요청 데이터를 정의합니다.
 * 파일 그룹의 업로드 진행 상황을 추적하고 보고하는 데 사용됩니다.
 * 
 * 주요 구성 요소:
 * - fileGroupId: 파일 그룹 ID (UUID 형식)
 * - fileCount: 파일 그룹 내 총 미디어 파일 수
 * - fileUploadedCount: 파일 그룹 내 업로드 완료된 미디어 파일 수
 * 
 * 이 클래스는 여러 파일이 포함된 그룹의 업로드 진행률을
 * 실시간으로 추적하고 관리하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/19
 */
@Schema(description = "folder upload callback request data")
public class FolderUploadCallbackRequest {

    /**
     * 파일 그룹 ID
     * 
     * 업로드 진행 상황을 추적할 파일 그룹의 고유 식별자입니다.
     * UUID 형식으로 관리되며, 웨이포인트 촬영 시 생성됩니다.
     */
    @NotNull
    @JsonProperty("file_group_id")
    @Schema(description = "file group id", format = "uuid")
    private String fileGroupId;

    /**
     * 파일 그룹 내 총 미디어 파일 수
     * 
     * 해당 파일 그룹에 포함된 총 미디어 파일의 개수입니다.
     * 업로드 진행률 계산의 기준이 되는 값입니다.
     */
    @NotNull
    @JsonProperty("file_count")
    @Schema(description = "total amount of media in the file group")
    private Integer fileCount;

    /**
     * 파일 그룹 내 업로드 완료된 미디어 파일 수
     * 
     * 해당 파일 그룹에서 업로드가 완료된 미디어 파일의 개수입니다.
     * fileCount와 함께 업로드 진행률을 계산하는 데 사용됩니다.
     */
    @NotNull
    @JsonProperty("file_uploaded_count")
    @Schema(description = "the number of uploaded media in the file group")
    private Integer fileUploadedCount;

    public FolderUploadCallbackRequest() {
    }

    @Override
    public String toString() {
        return "FolderUploadCallbackRequest{" +
                "fileGroupId='" + fileGroupId + '\'' +
                ", fileCount=" + fileCount +
                ", fileUploadedCount=" + fileUploadedCount +
                '}';
    }

    /**
     * 파일 그룹 ID를 반환합니다.
     * 
     * @return 파일 그룹 ID
     */
    public String getFileGroupId() {
        return fileGroupId;
    }

    /**
     * 파일 그룹 ID를 설정합니다.
     * 
     * @param fileGroupId 파일 그룹 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FolderUploadCallbackRequest setFileGroupId(String fileGroupId) {
        this.fileGroupId = fileGroupId;
        return this;
    }

    /**
     * 파일 그룹 내 총 미디어 파일 수를 반환합니다.
     * 
     * @return 파일 그룹 내 총 미디어 파일 수
     */
    public Integer getFileCount() {
        return fileCount;
    }

    /**
     * 파일 그룹 내 총 미디어 파일 수를 설정합니다.
     * 
     * @param fileCount 파일 그룹 내 총 미디어 파일 수
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FolderUploadCallbackRequest setFileCount(Integer fileCount) {
        this.fileCount = fileCount;
        return this;
    }

    /**
     * 파일 그룹 내 업로드 완료된 미디어 파일 수를 반환합니다.
     * 
     * @return 파일 그룹 내 업로드 완료된 미디어 파일 수
     */
    public Integer getFileUploadedCount() {
        return fileUploadedCount;
    }

    /**
     * 파일 그룹 내 업로드 완료된 미디어 파일 수를 설정합니다.
     * 
     * @param fileUploadedCount 파일 그룹 내 업로드 완료된 미디어 파일 수
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FolderUploadCallbackRequest setFileUploadedCount(Integer fileUploadedCount) {
        this.fileUploadedCount = fileUploadedCount;
        return this;
    }
}
