package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 카메라 초점 거리 설정 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 카메라의 초점 거리(줌)를 설정하기 위한 요청을 정의합니다.
 * 페이로드 인덱스, 카메라 타입, 줌 배율을 포함하여 카메라의 초점 거리를 설정합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/1
 */
public class CameraFocalLengthSetRequest extends BaseModel {

    /**
     * 페이로드 인덱스 (필수)
     * 초점 거리를 설정할 카메라의 페이로드 인덱스
     */
    @NotNull
    private PayloadIndex payloadIndex;

    /**
     * 카메라 타입 (필수)
     * 초점 거리를 설정할 카메라의 타입
     */
    @NotNull
    private ZoomCameraTypeEnum cameraType;

    /**
     * 줌 배율 (필수)
     * 2 ~ 200 범위, 설정할 카메라의 줌 배율
     */
    @Min(2)
    @Max(200)
    @NotNull
    private Float zoomFactor;

    /**
     * 기본 생성자
     */
    public CameraFocalLengthSetRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "CameraFocalLengthSetRequest{" +
                "payloadIndex=" + payloadIndex +
                ", cameraType=" + cameraType +
                ", zoomFactor=" + zoomFactor +
                '}';
    }

    /**
     * 페이로드 인덱스를 반환합니다.
     * 
     * @return 페이로드 인덱스
     */
    public PayloadIndex getPayloadIndex() {
        return payloadIndex;
    }

    /**
     * 페이로드 인덱스를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param payloadIndex 설정할 페이로드 인덱스
     * @return 현재 CameraFocalLengthSetRequest 객체
     */
    public CameraFocalLengthSetRequest setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 카메라 타입을 반환합니다.
     * 
     * @return 카메라 타입
     */
    public ZoomCameraTypeEnum getCameraType() {
        return cameraType;
    }

    /**
     * 카메라 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param cameraType 설정할 카메라 타입
     * @return 현재 CameraFocalLengthSetRequest 객체
     */
    public CameraFocalLengthSetRequest setCameraType(ZoomCameraTypeEnum cameraType) {
        this.cameraType = cameraType;
        return this;
    }

    /**
     * 줌 배율을 반환합니다.
     * 
     * @return 줌 배율
     */
    public Float getZoomFactor() {
        return zoomFactor;
    }

    /**
     * 줌 배율을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param zoomFactor 설정할 줌 배율
     * @return 현재 CameraFocalLengthSetRequest 객체
     */
    public CameraFocalLengthSetRequest setZoomFactor(Float zoomFactor) {
        this.zoomFactor = zoomFactor;
        return this;
    }
}
