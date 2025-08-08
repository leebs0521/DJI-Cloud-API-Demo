package com.dji.sdk.cloudapi.media;

import com.dji.sdk.cloudapi.device.DeviceEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 업로드 콜백 파일 확장 데이터 클래스
 * 
 * 이 클래스는 파일 업로드 콜백 시 사용되는 확장 정보를 정의합니다.
 * 드론 모델, 페이로드 정보, 비행 ID 등의 메타데이터를 포함합니다.
 * 
 * 주요 구성 요소:
 * - droneModelKey: 드론 디바이스 제품 열거형
 * - original: 원본 이미지 여부
 * - payloadModelKey: 페이로드 디바이스 제품 열거형
 * - flightId: 비행 ID
 * 
 * 이 클래스는 업로드된 파일의 출처와 특성을 식별하는 데 사용됩니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/7
 */
public class UploadCallbackFileExtension {

    /**
     * 드론 디바이스 제품 열거형
     * 
     * 미디어 파일을 촬영한 드론의 모델 정보입니다.
     * DeviceEnum을 사용하여 드론의 제품 타입을 식별합니다.
     */
    private DeviceEnum droneModelKey;

    /**
     * 원본 이미지 여부
     * 
     * 이미지가 원본 이미지인지 여부를 나타냅니다.
     * true: 원본 이미지 (압축되지 않은 고품질 이미지)
     * false: 압축된 이미지 또는 썸네일
     */
    @JsonProperty("is_original")
    private Boolean original;

    /**
     * 페이로드 디바이스 제품 열거형
     * 
     * 미디어 파일을 촬영한 페이로드(카메라)의 모델 정보입니다.
     * DeviceEnum을 사용하여 페이로드의 제품 타입을 식별합니다.
     */
    private DeviceEnum payloadModelKey;

    /**
     * 비행 ID
     * 
     * 미디어 파일이 촬영된 비행 세션의 고유 식별자입니다.
     * 비행 세션을 추적하고 관리하는 데 사용됩니다.
     */
    private String flightId;

    public UploadCallbackFileExtension() {
    }

    @Override
    public String toString() {
        return "UploadCallbackFileExtension{" +
                "droneModelKey=" + droneModelKey +
                ", original=" + original +
                ", payloadModelKey=" + payloadModelKey +
                ", flightId='" + flightId + '\'' +
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
    public UploadCallbackFileExtension setDroneModelKey(DeviceEnum droneModelKey) {
        this.droneModelKey = droneModelKey;
        return this;
    }

    /**
     * 원본 이미지 여부를 반환합니다.
     * 
     * @return 원본 이미지 여부
     */
    public Boolean getOriginal() {
        return original;
    }

    /**
     * 원본 이미지 여부를 설정합니다.
     * 
     * @param original 원본 이미지 여부
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public UploadCallbackFileExtension setOriginal(Boolean original) {
        this.original = original;
        return this;
    }

    /**
     * 페이로드 디바이스 제품 열거형을 반환합니다.
     * 
     * @return 페이로드 디바이스 제품 열거형
     */
    public DeviceEnum getPayloadModelKey() {
        return payloadModelKey;
    }

    /**
     * 페이로드 디바이스 제품 열거형을 설정합니다.
     * 
     * @param payloadModelKey 페이로드 디바이스 제품 열거형
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public UploadCallbackFileExtension setPayloadModelKey(DeviceEnum payloadModelKey) {
        this.payloadModelKey = payloadModelKey;
        return this;
    }

    /**
     * 비행 ID를 반환합니다.
     * 
     * @return 비행 ID
     */
    public String getFlightId() {
        return flightId;
    }

    /**
     * 비행 ID를 설정합니다.
     * 
     * @param flightId 비행 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public UploadCallbackFileExtension setFlightId(String flightId) {
        this.flightId = flightId;
        return this;
    }
}
