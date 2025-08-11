package com.dji.sample.component.mqtt.model;

/**
 * MQTT 사용 목적 열거형 클래스
 * MQTT 브로커의 사용 목적을 정의합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/1/18
 */
public enum MqttUseEnum {

    /**
     * 기본 링크용 브로커입니다.
     * 일반적인 MQTT 통신에 사용됩니다.
     */
    BASIC,

    /**
     * DRC(Direct Remote Control) 링크용 브로커입니다.
     * 직접 원격 제어 통신에 사용됩니다.
     */
    DRC
}
