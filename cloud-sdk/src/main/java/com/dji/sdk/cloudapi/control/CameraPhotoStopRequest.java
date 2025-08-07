package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 카메라 사진 촬영 중지 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 카메라의 사진 촬영을 중지하기 위한 요청을 정의합니다.
 * 특정 페이로드 인덱스를 지정하여 해당 카메라의 사진 촬영을 중지합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public class CameraPhotoStopRequest extends BaseModel {

    /**
     * 페이로드 인덱스 (필수)
     * 사진 촬영을 중지할 카메라의 페이로드 인덱스
     */
    @NotNull
    private PayloadIndex payloadIndex;

    /**
     * 기본 생성자
     */
    public CameraPhotoStopRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "CameraPhotoStopRequest{" +
                "payloadIndex=" + payloadIndex +
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
     * @return 현재 CameraPhotoStopRequest 객체
     */
    public CameraPhotoStopRequest setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }
}
