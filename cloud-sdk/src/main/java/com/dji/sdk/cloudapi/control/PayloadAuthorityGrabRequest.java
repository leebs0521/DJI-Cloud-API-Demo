package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 페이로드 권한 획득 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 페이로드(카메라, 짐벌 등)의 제어 권한을 획득하기 위한 요청을 정의합니다.
 * 특정 페이로드 인덱스를 지정하여 해당 페이로드의 제어 권한을 요청합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public class PayloadAuthorityGrabRequest extends BaseModel {

    /**
     * 페이로드 인덱스 (필수)
     * 권한을 획득할 페이로드의 인덱스
     */
    @NotNull
    private PayloadIndex payloadIndex;

    /**
     * 기본 생성자
     */
    public PayloadAuthorityGrabRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "PayloadAuthorityGrabRequest{" +
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
     * @return 현재 PayloadAuthorityGrabRequest 객체
     */
    public PayloadAuthorityGrabRequest setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }
}
