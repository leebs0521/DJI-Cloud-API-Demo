package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.cloudapi.device.DeviceEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 웨이라인 파일 메타데이터 클래스
 * 
 * 이 클래스는 웨이라인 파일 업로드 콜백에서 사용되는 메타데이터를 정의합니다.
 * 드론 모델, 페이로드 모델, 웨이라인 템플릿 타입 등의 정보를 포함하여
 * 웨이라인 파일의 호환성과 특성을 관리합니다.
 * 
 * 주요 구성 요소:
 * - droneModelKey: 드론 디바이스 제품 열거형
 * - payloadModelKeys: 페이로드 디바이스 제품 열거형 리스트
 * - templateTypes: 웨이라인 템플릿 컬렉션
 * 
 * 이 클래스는 웨이라인 파일 업로드 시 디바이스 호환성을 확인하고
 * 적절한 템플릿을 적용하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/12
 */
@Schema(description = "웨이라인 파일 메타데이터")
public class WaylineUploadCallbackMetadata {

    /**
     * 드론 디바이스 제품 열거형
     * 
     * 웨이라인 파일이 지원하는 드론의 모델 정보입니다.
     * 드론의 하드웨어 사양과 호환성을 확인하는 데 사용됩니다.
     * 
     * 예시: "0-67-0"
     */
    @NotNull
    @Schema(description = "드론 디바이스 제품 열거형", example = "0-67-0")
    @JsonProperty("drone_model_key")
    private DeviceEnum droneModelKey;

    /**
     * 페이로드 디바이스 제품 열거형 리스트
     * 
     * 웨이라인 파일이 지원하는 페이로드 디바이스들의 모델 정보입니다.
     * 최소 1개 이상의 페이로드가 포함되어야 합니다.
     * 
     * 제약 조건:
     * - 최소 1개 이상의 페이로드 포함
     * 
     * 예시: ["1-53-0"]
     */
    @NotNull
    @Size(min = 1)
    @JsonProperty("payload_model_keys")
    @Schema(description = "페이로드 디바이스 제품 열거형", example = "[\"1-53-0\"]")
    private List<DeviceEnum> payloadModelKeys;

    /**
     * 웨이라인 템플릿 컬렉션
     * 
     * 웨이라인 파일이 지원하는 템플릿 타입들의 리스트입니다.
     * 최소 1개 이상의 템플릿 타입이 포함되어야 합니다.
     * 
     * 제약 조건:
     * - 최소 1개 이상의 템플릿 타입 포함
     * 
     * 예시: [0]
     */
    @NotNull
    @Size(min = 1)
    @Schema(description = "웨이라인 템플릿 컬렉션", example = "[0]")
    @JsonProperty("template_types")
    private List<WaylineTypeEnum> templateTypes;

    public WaylineUploadCallbackMetadata() {
    }

    @Override
    public String toString() {
        return "WaylineUploadCallbackMetadata{" +
                "droneModelKey='" + droneModelKey + '\'' +
                ", payloadModelKeys=" + payloadModelKeys +
                ", templateTypes=" + templateTypes +
                '}';
    }

    /**
     * 드론 디바이스 제품 열거형을 반환합니다.
     * 
     * @return 드론 디바이스 제품 열거형
     */
    public DeviceEnum getDroneModelKey() {
        return droneModelKey;
    }

    /**
     * 드론 디바이스 제품 열거형을 설정합니다.
     * 
     * @param droneModelKey 드론 디바이스 제품 열거형
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public WaylineUploadCallbackMetadata setDroneModelKey(DeviceEnum droneModelKey) {
        this.droneModelKey = droneModelKey;
        return this;
    }

    /**
     * 페이로드 디바이스 제품 열거형 리스트를 반환합니다.
     * 
     * @return 페이로드 디바이스 제품 열거형 리스트
     */
    public List<DeviceEnum> getPayloadModelKeys() {
        return payloadModelKeys;
    }

    /**
     * 페이로드 디바이스 제품 열거형 리스트를 설정합니다.
     * 
     * @param payloadModelKeys 페이로드 디바이스 제품 열거형 리스트
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public WaylineUploadCallbackMetadata setPayloadModelKeys(List<DeviceEnum> payloadModelKeys) {
        this.payloadModelKeys = payloadModelKeys;
        return this;
    }

    /**
     * 웨이라인 템플릿 컬렉션을 반환합니다.
     * 
     * @return 웨이라인 템플릿 컬렉션
     */
    public List<WaylineTypeEnum> getTemplateTypes() {
        return templateTypes;
    }

    /**
     * 웨이라인 템플릿 컬렉션을 설정합니다.
     * 
     * @param templateTypes 웨이라인 템플릿 컬렉션
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public WaylineUploadCallbackMetadata setTemplateTypes(List<WaylineTypeEnum> templateTypes) {
        this.templateTypes = templateTypes;
        return this;
    }
}
