package com.dji.sdk.cloudapi.control;

import com.dji.sdk.common.BaseModel;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * DRC 모드 진입 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 Direct Remote Control(DRC) 모드로 진입하기 위한 요청을 정의합니다.
 * MQTT 브로커 설정, OSD 주파수, HSI 주파수 정보를 포함하여 DRC 모드를 시작합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/1/12
 */
public class DrcModeEnterRequest extends BaseModel {

    /**
     * MQTT 브로커 설정 (필수)
     * DRC 모드에서 사용할 MQTT 브로커 정보
     */
    @NotNull
    @Valid
    private DrcModeMqttBroker mqttBroker;

    /**
     * OSD 주파수 (필수)
     * 1 ~ 30 범위, On-Screen Display 데이터 전송 주파수
     */
    @Min(1)
    @Max(30)
    @NotNull
    private Integer osdFrequency;

    /**
     * HSI 주파수 (필수)
     * 1 ~ 30 범위, Horizontal Situation Indicator 데이터 전송 주파수
     */
    @Min(1)
    @Max(30)
    @NotNull
    private Integer hsiFrequency;

    /**
     * 기본 생성자
     */
    public DrcModeEnterRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "DrcModeEnterRequest{" +
                "mqttBroker=" + mqttBroker +
                ", osdFrequency=" + osdFrequency +
                ", hsiFrequency=" + hsiFrequency +
                '}';
    }

    /**
     * MQTT 브로커 설정을 반환합니다.
     * 
     * @return MQTT 브로커 설정
     */
    public DrcModeMqttBroker getMqttBroker() {
        return mqttBroker;
    }

    /**
     * MQTT 브로커 설정을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param mqttBroker 설정할 MQTT 브로커 설정
     * @return 현재 DrcModeEnterRequest 객체
     */
    public DrcModeEnterRequest setMqttBroker(DrcModeMqttBroker mqttBroker) {
        this.mqttBroker = mqttBroker;
        return this;
    }

    /**
     * OSD 주파수를 반환합니다.
     * 
     * @return OSD 주파수
     */
    public Integer getOsdFrequency() {
        return osdFrequency;
    }

    /**
     * OSD 주파수를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param osdFrequency 설정할 OSD 주파수
     * @return 현재 DrcModeEnterRequest 객체
     */
    public DrcModeEnterRequest setOsdFrequency(Integer osdFrequency) {
        this.osdFrequency = osdFrequency;
        return this;
    }

    /**
     * HSI 주파수를 반환합니다.
     * 
     * @return HSI 주파수
     */
    public Integer getHsiFrequency() {
        return hsiFrequency;
    }

    /**
     * HSI 주파수를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param hsiFrequency 설정할 HSI 주파수
     * @return 현재 DrcModeEnterRequest 객체
     */
    public DrcModeEnterRequest setHsiFrequency(Integer hsiFrequency) {
        this.hsiFrequency = hsiFrequency;
        return this;
    }
}
