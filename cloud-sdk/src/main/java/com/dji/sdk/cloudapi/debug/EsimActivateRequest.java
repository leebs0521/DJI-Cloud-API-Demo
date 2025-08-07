package com.dji.sdk.cloudapi.debug;

import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * ESIM 활성화 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 ESIM(Embedded SIM)을 활성화하기 위한 요청을 정의합니다.
 * IMEI와 디바이스 타입을 포함하여 ESIM 활성화를 수행합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public class EsimActivateRequest extends BaseModel {

    /**
     * 조작할 동글을 식별합니다.
     * 동글의 고유 식별자
     */
    @NotNull
    private String imei;

    /**
     * 조작할 대상 디바이스를 식별합니다.
     * 동글 디바이스의 타입
     */
    @NotNull
    private DongleDeviceTypeEnum deviceType;

    /**
     * 기본 생성자
     */
    public EsimActivateRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "EsimActivateRequest{" +
                "imei='" + imei + '\'' +
                ", deviceType=" + deviceType +
                '}';
    }

    /**
     * IMEI를 반환합니다.
     * 
     * @return IMEI
     */
    public String getImei() {
        return imei;
    }

    /**
     * IMEI를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param imei 설정할 IMEI
     * @return 현재 EsimActivateRequest 객체
     */
    public EsimActivateRequest setImei(String imei) {
        this.imei = imei;
        return this;
    }

    /**
     * 디바이스 타입을 반환합니다.
     * 
     * @return 디바이스 타입
     */
    public DongleDeviceTypeEnum getDeviceType() {
        return deviceType;
    }

    /**
     * 디바이스 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param deviceType 설정할 디바이스 타입
     * @return 현재 EsimActivateRequest 객체
     */
    public EsimActivateRequest setDeviceType(DongleDeviceTypeEnum deviceType) {
        this.deviceType = deviceType;
        return this;
    }
}
