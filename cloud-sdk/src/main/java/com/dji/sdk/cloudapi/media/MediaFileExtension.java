package com.dji.sdk.cloudapi.media;

import com.dji.sdk.cloudapi.device.DeviceEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

/**
 * 미디어 파일 업로드 콜백 확장 데이터 클래스
 * 
 * 이 클래스는 미디어 파일 업로드 콜백 시 사용되는 확장 정보를 정의합니다.
 * 드론 모델, 파일 그룹, 페이로드 정보, 파일 지문 등의 메타데이터를 포함합니다.
 * 
 * 주요 구성 요소:
 * - droneModelKey: 드론 디바이스 제품 열거형
 * - fileGroupId: 파일 그룹 ID (웨이포인트 촬영 시 사용)
 * - original: 원본 이미지 여부
 * - payloadModelKey: 페이로드 디바이스 제품 열거형
 * - tinnyFingerprint: 파일의 작은 지문
 * - sn: 드론 시리얼 번호
 * 
 * 이 클래스는 업로드된 미디어 파일의 출처와 특성을 식별하는 데 사용됩니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/7
 */
@Schema(description = "media file upload callback extension data")
public class MediaFileExtension {

    /**
     * 드론 디바이스 제품 열거형
     * 
     * 미디어 파일을 촬영한 드론의 모델 정보입니다.
     * DeviceEnum을 사용하여 드론의 제품 타입을 식별합니다.
     */
    @NotNull
    @JsonProperty("drone_model_key")
    @Schema(description = "drone device product enum")
    private DeviceEnum droneModelKey;

    /**
     * 파일 그룹 ID
     * 
     * 미디어 파일이 속한 파일 그룹의 고유 식별자입니다.
     * 웨이포인트에서 촬영된 경우 이 값이 설정되고,
     * 수동 촬영의 경우 null이 됩니다.
     * UUID 형식으로 관리됩니다.
     */
    @NotNull
    @JsonProperty("file_group_id")
    @Schema(description = "If the media file was shot during the wayline, this value will not be null.", format = "uuid")
    private String fileGroupId;

    /**
     * 원본 이미지 여부
     * 
     * 이미지가 원본 이미지인지 여부를 나타냅니다.
     * true: 원본 이미지 (압축되지 않은 고품질 이미지)
     * false: 압축된 이미지 또는 썸네일
     */
    @JsonProperty("is_original")
    @NotNull
    @Schema(description = "Whether the image is the original image.")
    private Boolean original;

    /**
     * 페이로드 디바이스 제품 열거형
     * 
     * 미디어 파일을 촬영한 페이로드(카메라)의 모델 정보입니다.
     * DeviceEnum을 사용하여 페이로드의 제품 타입을 식별합니다.
     * 예: "1-42-0" (특정 카메라 모델)
     */
    @NotNull
    @JsonProperty("payload_model_key")
    @Schema(description = "payload device product enum", example = "1-42-0")
    private DeviceEnum payloadModelKey;

    /**
     * 파일의 작은 지문
     * 
     * 파일의 작은 지문(tiny fingerprint)으로, 파일의 고유 식별자입니다.
     * 파일명과 촬영 시간을 기반으로 생성됩니다.
     * 예: "297f490b0252690d3f93841818567cc6_2022_8_31_15_16_16"
     */
    @NotNull
    @JsonProperty("tinny_fingerprint")
    @Schema(description = "tiny fingerprint of the file.", example = "297f490b0252690d3f93841818567cc6_2022_8_31_15_16_16")
    private String tinnyFingerprint;

    /**
     * 드론 시리얼 번호
     * 
     * 미디어 파일을 촬영한 드론의 고유 시리얼 번호입니다.
     * 드론을 식별하고 추적하는 데 사용됩니다.
     * 예: "1AD3CA2VL3LAD6"
     */
    @NotNull
    @Schema(description = "drone sn", example = "1AD3CA2VL3LAD6")
    private String sn;

    public MediaFileExtension() {
    }

    @Override
    public String toString() {
        return "MediaFileExtension{" +
                "droneModelKey=" + droneModelKey +
                ", fileGroupId='" + fileGroupId + '\'' +
                ", original=" + original +
                ", payloadModelKey=" + payloadModelKey +
                ", tinnyFingerprint='" + tinnyFingerprint + '\'' +
                ", sn='" + sn + '\'' +
                '}';
    }

    public DeviceEnum getDroneModelKey() {
        return droneModelKey;
    }

    public MediaFileExtension setDroneModelKey(DeviceEnum droneModelKey) {
        this.droneModelKey = droneModelKey;
        return this;
    }

    public Boolean getOriginal() {
        return original;
    }

    public MediaFileExtension setOriginal(Boolean original) {
        this.original = original;
        return this;
    }

    public DeviceEnum getPayloadModelKey() {
        return payloadModelKey;
    }

    public MediaFileExtension setPayloadModelKey(DeviceEnum payloadModelKey) {
        this.payloadModelKey = payloadModelKey;
        return this;
    }

    public String getTinnyFingerprint() {
        return tinnyFingerprint;
    }

    public MediaFileExtension setTinnyFingerprint(String tinnyFingerprint) {
        this.tinnyFingerprint = tinnyFingerprint;
        return this;
    }

    public String getSn() {
        return sn;
    }

    public MediaFileExtension setSn(String sn) {
        this.sn = sn;
        return this;
    }

    public String getFileGroupId() {
        return fileGroupId;
    }

    public MediaFileExtension setFileGroupId(String fileGroupId) {
        this.fileGroupId = fileGroupId;
        return this;
    }
}
