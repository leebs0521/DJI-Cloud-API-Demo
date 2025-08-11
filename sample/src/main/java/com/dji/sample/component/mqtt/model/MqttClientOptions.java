package com.dji.sample.component.mqtt.model;

import lombok.Data;

/**
 * MQTT 클라이언트 옵션 클래스
 * MQTT 클라이언트 연결에 필요한 설정 옵션들을 정의합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/1/18
 */
@Data
public class MqttClientOptions {

    /** MQTT 프로토콜 타입 */
    private MqttProtocolEnum protocol;

    /** MQTT 브로커 호스트 */
    private String host;

    /** MQTT 브로커 포트 */
    private Integer port;

    /** MQTT 사용자명 */
    private String username;

    /** MQTT 비밀번호 */
    private String password;

    /** MQTT 클라이언트 ID */
    private String clientId;

    /** MQTT 경로 (WebSocket 프로토콜용) */
    private String path;

    /**
     * 클라이언트 연결 시 즉시 구독할 토픽입니다. 기본 링크에서만 필요합니다.
     */
    private String inboundTopic;
}
