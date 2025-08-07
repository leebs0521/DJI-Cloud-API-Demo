package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 짐벌 리셋 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 짐벌을 기본 위치로 리셋하기 위한 요청을 정의합니다.
 * 특정 페이로드 인덱스와 리셋 모드를 지정하여 짐벌을 리셋합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/1
 */
public class GimbalResetRequest extends BaseModel {

    /**
     * 페이로드 인덱스 (필수)
     * 리셋할 짐벌의 페이로드 인덱스
     */
    @NotNull
    private PayloadIndex payloadIndex;

    /**
     * 리셋 모드 (필수)
     * 짐벌 리셋의 모드 설정
     */
    @NotNull
    private GimbalResetModeEnum resetMode;

    /**
     * 기본 생성자
     */
    public GimbalResetRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "GimbalResetRequest{" +
                "payloadIndex=" + payloadIndex +
                ", resetMode=" + resetMode +
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
     * @return 현재 GimbalResetRequest 객체
     */
    public GimbalResetRequest setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 리셋 모드를 반환합니다.
     * 
     * @return 리셋 모드
     */
    public GimbalResetModeEnum getResetMode() {
        return resetMode;
    }

    /**
     * 리셋 모드를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param resetMode 설정할 리셋 모드
     * @return 현재 GimbalResetRequest 객체
     */
    public GimbalResetRequest setResetMode(GimbalResetModeEnum resetMode) {
        this.resetMode = resetMode;
        return this;
    }
}
