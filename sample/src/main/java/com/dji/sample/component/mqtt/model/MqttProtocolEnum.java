package com.dji.sample.component.mqtt.model;

import lombok.Getter;

/**
 * MQTT 프로토콜 열거형 클래스
 * MQTT 연결에 사용되는 다양한 프로토콜 타입들을 정의합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/1/18
 */
@Getter
public enum MqttProtocolEnum {

    /** MQTT 프로토콜 (TCP) */
    MQTT("tcp"),

    /** MQTT 프로토콜 (SSL/TLS) */
    MQTTS("ssl"),

    /** WebSocket 프로토콜 */
    WS("ws"),

    /** WebSocket 프로토콜 (SSL/TLS) */
    WSS("wss");

    /** 프로토콜 문자열 */
    String protocol;

    /**
     * MQTT 프로토콜 열거형 생성자
     * 
     * @param protocol 프로토콜 문자열
     */
    MqttProtocolEnum(String protocol) {
        this.protocol = protocol;
    }

    /**
     * 프로토콜 주소를 반환합니다.
     * 
     * @return 프로토콜 주소 (예: "tcp://", "ws://")
     */
    public String getProtocolAddr() {
        return protocol + "://";
    }
}
