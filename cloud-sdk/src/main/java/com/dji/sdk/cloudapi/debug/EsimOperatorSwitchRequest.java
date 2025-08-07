package com.dji.sdk.cloudapi.debug;

import com.dji.sdk.cloudapi.device.TelecomOperatorEnum;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * ESIM 통신사 전환 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 ESIM(Embedded SIM)의 통신사를 전환하기 위한 요청을 정의합니다.
 * IMEI, 디바이스 타입, 대상 통신사를 포함하여 ESIM 통신사 전환을 수행합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public class EsimOperatorSwitchRequest extends BaseModel {

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
     * 전환할 대상 통신사
     * 전환하고자 하는 통신사 정보
     */
    @NotNull
    private TelecomOperatorEnum telecomOperator;

    /**
     * 기본 생성자
     */
    public EsimOperatorSwitchRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "EsimOperatorSwitchRequest{" +
                "imei='" + imei + '\'' +
                ", deviceType=" + deviceType +
                ", telecomOperator=" + telecomOperator +
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
     * @return 현재 EsimOperatorSwitchRequest 객체
     */
    public EsimOperatorSwitchRequest setImei(String imei) {
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
     * @return 현재 EsimOperatorSwitchRequest 객체
     */
    public EsimOperatorSwitchRequest setDeviceType(DongleDeviceTypeEnum deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    /**
     * 통신사를 반환합니다.
     * 
     * @return 통신사
     */
    public TelecomOperatorEnum getTelecomOperator() {
        return telecomOperator;
    }

    /**
     * 통신사를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param telecomOperator 설정할 통신사
     * @return 현재 EsimOperatorSwitchRequest 객체
     */
    public EsimOperatorSwitchRequest setTelecomOperator(TelecomOperatorEnum telecomOperator) {
        this.telecomOperator = telecomOperator;
        return this;
    }
}
