package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * IR 카메라 측광 모드 설정 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 적외선 카메라의 측광 모드를 설정하기 위한 요청을 정의합니다.
 * 페이로드 인덱스와 측광 모드를 포함하여 적외선 카메라의 측광 모드를 설정합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public class IrMeteringModeSetRequest extends BaseModel {

    /**
     * 페이로드 인덱스 (필수)
     * 측광 모드를 설정할 적외선 카메라의 페이로드 인덱스
     * 비공식 디바이스 모드 키로, 형식은 "타입-서브타입-짐벌인덱스"입니다.
     * 지원 제품은 [Product Supported](https://developer.dji.com/doc/cloud-api-tutorial/en/overview/product-support.html)를 참조하세요.
     */
    @NotNull
    private PayloadIndex payloadIndex;

    /**
     * 측광 모드 (필수)
     * 설정할 적외선 카메라의 측광 모드
     */
    @NotNull
    private MeteringModeEnum mode;

    /**
     * 기본 생성자
     */
    public IrMeteringModeSetRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "IrMeteringModeSetRequest{" +
                "payloadIndex=" + payloadIndex +
                ", mode=" + mode +
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
     * @return 현재 IrMeteringModeSetRequest 객체
     */
    public IrMeteringModeSetRequest setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 측광 모드를 반환합니다.
     * 
     * @return 측광 모드
     */
    public MeteringModeEnum getMode() {
        return mode;
    }

    /**
     * 측광 모드를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param mode 설정할 측광 모드
     * @return 현재 IrMeteringModeSetRequest 객체
     */
    public IrMeteringModeSetRequest setMode(MeteringModeEnum mode) {
        this.mode = mode;
        return this;
    }
}
