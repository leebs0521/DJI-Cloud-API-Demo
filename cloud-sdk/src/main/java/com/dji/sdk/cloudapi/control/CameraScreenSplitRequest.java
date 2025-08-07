package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 카메라 화면 분할 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 카메라 화면을 분할하여 표시하기 위한 요청을 정의합니다.
 * 페이로드 인덱스와 화면 분할 활성화 여부를 포함하여 카메라 화면 분할을 설정합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/12
 */
public class CameraScreenSplitRequest extends BaseModel {

    /**
     * 페이로드 인덱스 (필수)
     * 화면 분할을 설정할 카메라의 페이로드 인덱스
     * 비공식 디바이스 모드 키로, 형식은 "타입-서브타입-짐벌인덱스"입니다.
     * 지원 제품은 [Product Supported](https://developer.dji.com/doc/cloud-api-tutorial/en/overview/product-support.html)를 참조하세요.
     */
    @NotNull
    private PayloadIndex payloadIndex;

    /**
     * 화면 분할 활성화 여부 (필수)
     * 화면 분할 기능을 활성화할지 여부
     */
    @NotNull
    private Boolean enable;

    /**
     * 기본 생성자
     */
    public CameraScreenSplitRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "CameraScreenSplitRequest{" +
                "payloadIndex=" + payloadIndex +
                ", enable=" + enable +
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
     * @return 현재 CameraScreenSplitRequest 객체
     */
    public CameraScreenSplitRequest setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 화면 분할 활성화 여부를 반환합니다.
     * 
     * @return 화면 분할 활성화 여부
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * 화면 분할 활성화 여부를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param enable 설정할 화면 분할 활성화 여부
     * @return 현재 CameraScreenSplitRequest 객체
     */
    public CameraScreenSplitRequest setEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }
}
