package com.dji.sdk.mqtt.services;

/**
 * MQTT 서비스 에러 코드 인터페이스
 * 서비스 관련 에러 코드의 기본 구조를 정의하는 인터페이스
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/25
 */
public interface IServicesErrorCode {

    /**
     * 에러 메시지를 반환합니다.
     * @return 에러 메시지
     */
    String getMessage();

    /**
     * 에러 코드를 반환합니다.
     * @return 에러 코드
     */
    Integer getCode();

}
