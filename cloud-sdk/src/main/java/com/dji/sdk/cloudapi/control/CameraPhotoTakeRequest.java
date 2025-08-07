package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 카메라 사진 촬영 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 카메라로 사진을 촬영하기 위한 요청을 정의합니다.
 * 특정 페이로드 인덱스를 지정하여 해당 카메라로 사진을 촬영합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/1
 */
public class CameraPhotoTakeRequest extends BaseModel {

    /**
     * 페이로드 인덱스 (필수)
     * 사진을 촬영할 카메라의 페이로드 인덱스
     */
    @NotNull
    private PayloadIndex payloadIndex;

    /**
     * 기본 생성자
     */
    public CameraPhotoTakeRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "CameraPhotoTakeRequest{" +
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
     * @return 현재 CameraPhotoTakeRequest 객체
     */
    public CameraPhotoTakeRequest setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }
}
