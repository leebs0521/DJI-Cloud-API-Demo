package com.dji.sdk.exception;

import com.dji.sdk.common.IErrorInfo;

/**
 * Cloud SDK 오류 열거형
 * 
 * 이 열거형은 Cloud SDK에서 발생할 수 있는
 * 모든 오류 코드를 정의합니다.
 * 
 * 주요 구성 요소:
 * - 디바이스 관련 오류: NOT_REGISTERED, INVALID_PARAMETER 등
 * - 디바이스 지원 오류: DEVICE_TYPE_NOT_SUPPORT, DEVICE_VERSION_NOT_SUPPORT 등
 * - 통신 관련 오류: MQTT_PUBLISH_ABNORMAL, WEBSOCKET_PUBLISH_ABNORMAL 등
 * - 데이터 관련 오류: WRONG_DATA
 * - 기타 오류: UNKNOWN
 * 
 * 이 열거형은 SDK에서 발생하는 모든 오류를
 * 표준화된 방식으로 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/23
 */
public enum CloudSDKErrorEnum implements IErrorInfo {

    /**
     * 디바이스 미등록
     * 
     * 디바이스가 등록되지 않았습니다.
     */
    NOT_REGISTERED(210001, "Device is not registered."),

    /**
     * 잘못된 매개변수
     * 
     * 매개변수가 잘못되었습니다.
     */
    INVALID_PARAMETER(210002, "Invalid parameter."),

    /**
     * 디바이스 타입 미지원
     * 
     * 현재 디바이스 타입이 이 기능을 지원하지 않습니다.
     */
    DEVICE_TYPE_NOT_SUPPORT(210003, "The current type of the device does not support this function."),

    /**
     * 디바이스 버전 미지원
     * 
     * 현재 디바이스 버전이 이 기능을 지원하지 않습니다.
     */
    DEVICE_VERSION_NOT_SUPPORT(210004, "The current version of the device does not support this function."),

    /**
     * 디바이스 속성 미지원
     * 
     * 현재 디바이스가 이 기능을 지원하지 않습니다.
     */
    DEVICE_PROPERTY_NOT_SUPPORT(210005, "The current device does not support this feature."),

    /**
     * MQTT 발행 이상
     * 
     * MQTT 메시지 전송에 이상이 있습니다.
     */
    MQTT_PUBLISH_ABNORMAL(211001, "The sending of mqtt message is abnormal."),

    /**
     * WebSocket 발행 이상
     * 
     * WebSocket 메시지 전송에 이상이 있습니다.
     */
    WEBSOCKET_PUBLISH_ABNORMAL(212001, "The sending of webSocket message is abnormal."),

    /**
     * 잘못된 데이터
     * 
     * 데이터가 제한을 초과했습니다.
     */
    WRONG_DATA(220001, "Data exceeds limit."),

    /**
     * 알 수 없음
     * 
     * SDK 알 수 없는 오류입니다.
     */
    UNKNOWN(299999, "sdk unknown"),
    ;

    /**
     * 오류 코드
     * 
     * 각 오류를 구분하는 정수 값입니다.
     */
    private final int code;

    /**
     * 오류 메시지
     * 
     * 각 오류에 대한 설명 메시지입니다.
     */
    private final String message;

    /**
     * Cloud SDK 오류 열거형 생성자
     * 
     * @param code 오류 코드
     * @param message 오류 메시지
     */
    CloudSDKErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 오류 메시지를 반환합니다.
     * 
     * @return 오류 메시지
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 오류 코드를 반환합니다.
     * 
     * @return 오류 코드
     */
    @Override
    public Integer getCode() {
        return code;
    }
}
