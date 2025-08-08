package com.dji.sdk.cloudapi.wayline;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 웨이라인 업로드 콜백 요청 데이터 클래스
 * 
 * 이 클래스는 웨이라인 파일 업로드 결과를 콜백으로 받기 위한 데이터를 정의합니다.
 * 업로드된 파일의 객체 키, 파일명, 메타데이터 등의 정보를 포함하여
 * 웨이라인 파일 업로드의 완료 상태를 관리합니다.
 * 
 * 주요 구성 요소:
 * - objectKey: 버킷 내 객체 키
 * - name: 웨이라인 파일명
 * - metadata: 웨이라인 파일 메타데이터
 * 
 * 이 클래스는 웨이라인 파일 업로드가 완료되었을 때
 * 업로드 결과를 알림받기 위한 콜백 요청에 사용됩니다.
 * 
 * @author sean
 * @version 0.3
 * @date 2021/12/23
 */
@Schema(description = "업로드 결과 콜백의 데이터 클래스")
public class WaylineUploadCallbackRequest {

    /**
     * 버킷 내 객체 키
     * 
     * 클라우드 스토리지 버킷에서 웨이라인 파일의 위치를 나타내는 키입니다.
     * 파일의 경로와 파일명을 포함한 전체 경로입니다.
     * 
     * 예시: "wayline/waylineFile.kmz"
     */
    @NotNull
    @Schema(description = "버킷 내 객체의 키", example = "wayline/waylineFile.kmz")
    @JsonProperty("object_key")
    private String objectKey;

    /**
     * 웨이라인 파일명
     * 
     * 업로드된 웨이라인 파일의 이름입니다.
     * 확장자를 제외한 파일명만 포함합니다.
     * 
     * 예시: "waylineFile"
     */
    @NotNull
    @Schema(description = "웨이라인 파일명", example = "waylineFile")
    private String name;

    /**
     * 웨이라인 파일 메타데이터
     * 
     * 웨이라인 파일의 메타데이터 정보입니다.
     * 드론 모델, 페이로드 모델, 템플릿 타입 등의 정보를 포함합니다.
     */
    @Valid
    @NotNull
    @Schema(description = "웨이라인 파일 메타데이터")
    private WaylineUploadCallbackMetadata metadata;

    public WaylineUploadCallbackRequest() {
    }

    @Override
    public String toString() {
        return "WaylineUploadCallbackRequest{" +
                "objectKey='" + objectKey + '\'' +
                ", name='" + name + '\'' +
                ", metadata=" + metadata +
                '}';
    }

    /**
     * 버킷 내 객체 키를 반환합니다.
     * 
     * @return 버킷 내 객체 키
     */
    public String getObjectKey() {
        return objectKey;
    }

    /**
     * 버킷 내 객체 키를 설정합니다.
     * 
     * @param objectKey 버킷 내 객체 키
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public WaylineUploadCallbackRequest setObjectKey(String objectKey) {
        this.objectKey = objectKey;
        return this;
    }

    /**
     * 웨이라인 파일명을 반환합니다.
     * 
     * @return 웨이라인 파일명
     */
    public String getName() {
        return name;
    }

    /**
     * 웨이라인 파일명을 설정합니다.
     * 
     * @param name 웨이라인 파일명
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public WaylineUploadCallbackRequest setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 웨이라인 파일 메타데이터를 반환합니다.
     * 
     * @return 웨이라인 파일 메타데이터
     */
    public WaylineUploadCallbackMetadata getMetadata() {
        return metadata;
    }

    /**
     * 웨이라인 파일 메타데이터를 설정합니다.
     * 
     * @param metadata 웨이라인 파일 메타데이터
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public WaylineUploadCallbackRequest setMetadata(WaylineUploadCallbackMetadata metadata) {
        this.metadata = metadata;
        return this;
    }
}
