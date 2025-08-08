package com.dji.sdk.cloudapi.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 미디어 파일 업로드 콜백 요청 데이터 클래스
 * 
 * 이 클래스는 미디어 파일이 스토리지 서버에 업로드된 후,
 * 파일의 기본 정보를 서버에 보고하기 위한 요청 데이터를 정의합니다.
 * 
 * 주요 구성 요소:
 * - ext: 미디어 파일 확장 정보
 * - fingerprint: 미디어 파일 지문
 * - name: 미디어 파일명
 * - path: 미디어 파일 경로
 * - objectKey: 버킷 내 객체 키
 * - subFileType: 서브 파일 타입 열거형
 * - metadata: 미디어 파일 메타데이터
 * 
 * 이 클래스는 파일 업로드 완료 후 서버에 파일 정보를 알리는 데 사용됩니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/7
 */
@Schema(description = "media fast upload request data")
public class MediaUploadCallbackRequest {

    /**
     * 미디어 파일 확장 정보
     * 
     * 미디어 파일의 추가 정보를 포함합니다.
     * 파일 타입, 드론 정보, 페이로드 정보 등의 메타데이터가 담겨있습니다.
     */
    @NotNull
    @Valid
    private MediaFileExtension ext;

    /**
     * 미디어 파일 지문
     * 
     * 파일의 고유 식별자로 사용되는 지문(fingerprint)입니다.
     * 파일 내용을 기반으로 생성되며, 중복 파일 검사에 사용됩니다.
     * 예: "7F78C9F1999425CB61F10E1FE206009E"
     */
    @NotNull
    @Schema(description = "media file fingerprint", example = "7F78C9F1999425CB61F10E1FE206009E")
    private String fingerprint;

    /**
     * 미디어 파일명
     * 
     * 드론에서 촬영한 미디어 파일의 이름입니다.
     * DJI 카메라에서 자동으로 생성되는 파일명 형식을 따릅니다.
     * 예: "DJI_20220831151616_0004_W_Waypoint4.JPG"
     */
    @NotNull
    @Schema(description = "media file name", example = "DJI_20220831151616_0004_W_Waypoint4.JPG")
    private String name;

    /**
     * 미디어 파일 경로
     * 
     * 미디어 파일의 저장 경로입니다.
     * 웨이포인트에서 촬영된 경우 경로가 포함되고,
     * 수동 촬영의 경우 빈 값이 됩니다.
     * 예: "DJI_202208311455_008_Waypoint1"
     */
    @Schema(description = "media file path. This value is empty if the photo was not taken in the wayline.", example = "DJI_202208311455_008_Waypoint1")
    private String path;

    /**
     * 버킷 내 객체 키
     * 
     * 클라우드 스토리지 버킷 내에서 파일의 위치를 나타내는 키입니다.
     * 이 키를 사용하여 업로드된 파일에 접근할 수 있습니다.
     * 예: "media/DJI_20220831151616_0004_W_Waypoint4.JPG"
     */
    @JsonProperty("object_key")
    @NotNull
    @Schema(description = "The key of the object in the bucket", example = "media/DJI_20220831151616_0004_W_Waypoint4.JPG")
    private String objectKey;

    /**
     * 서브 파일 타입 열거형
     * 
     * 미디어 파일의 서브 타입을 나타냅니다.
     * 원본 이미지, 썸네일, 비디오 등 파일의 종류를 구분합니다.
     */
    @Schema(type = "int")
    @JsonProperty("sub_file_type")
    @NotNull
    private MediaSubFileTypeEnum subFileType;

    /**
     * 미디어 파일 메타데이터
     * 
     * 미디어 파일의 상세 메타데이터를 포함합니다.
     * 촬영 시간, 위치, 파일 크기, 해상도 등의 정보가 담겨있습니다.
     */
    @Valid
    @NotNull
    private MediaFileMetadata metadata;

    public MediaUploadCallbackRequest() {
    }

    @Override
    public String toString() {
        return "MediaUploadCallbackRequest{" +
                "ext=" + ext +
                ", fingerprint='" + fingerprint + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", objectKey='" + objectKey + '\'' +
                ", subFileType=" + subFileType +
                ", metadata=" + metadata +
                '}';
    }

    public MediaFileExtension getExt() {
        return ext;
    }

    public MediaUploadCallbackRequest setExt(MediaFileExtension ext) {
        this.ext = ext;
        return this;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public MediaUploadCallbackRequest setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
        return this;
    }

    public String getName() {
        return name;
    }

    public MediaUploadCallbackRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getPath() {
        return path;
    }

    public MediaUploadCallbackRequest setPath(String path) {
        this.path = path;
        return this;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public MediaUploadCallbackRequest setObjectKey(String objectKey) {
        this.objectKey = objectKey;
        return this;
    }

    public MediaSubFileTypeEnum getSubFileType() {
        return subFileType;
    }

    public MediaUploadCallbackRequest setSubFileType(MediaSubFileTypeEnum subFileType) {
        this.subFileType = subFileType;
        return this;
    }

    public MediaFileMetadata getMetadata() {
        return metadata;
    }

    public MediaUploadCallbackRequest setMetadata(MediaFileMetadata metadata) {
        this.metadata = metadata;
        return this;
    }
}
