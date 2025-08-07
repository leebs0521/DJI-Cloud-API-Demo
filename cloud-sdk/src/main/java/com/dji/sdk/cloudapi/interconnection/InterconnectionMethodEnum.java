package com.dji.sdk.cloudapi.interconnection;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 상호 연결 메서드 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 상호 연결(Interconnection) 관련 메서드를 정의합니다.
 * 클라우드와 ESDK/PSDK 간의 커스텀 데이터 전송 메서드를 포함하여
 * 다양한 SDK와의 데이터 교환 기능을 지원합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/16
 */
public enum InterconnectionMethodEnum {

    /**
     * 클라우드에서 ESDK로 커스텀 데이터 전송
     * 클라우드에서 ESDK(Enterprise SDK)로 커스텀 데이터를 전송하는 메서드
     */
    CUSTOM_DATA_TRANSMISSION_TO_ESDK("custom_data_transmission_to_esdk"),

    /**
     * 클라우드에서 PSDK로 커스텀 데이터 전송
     * 클라우드에서 PSDK(Payload SDK)로 커스텀 데이터를 전송하는 메서드
     */
    CUSTOM_DATA_TRANSMISSION_TO_PSDK("custom_data_transmission_to_psdk"),

    ;

    /**
     * 메서드 이름
     * 각 상호 연결 메서드에 대한 고유한 문자열 식별자
     */
    private final String method;

    /**
     * 상호 연결 메서드 열거형 생성자
     * 
     * @param method 메서드 이름
     */
    InterconnectionMethodEnum(String method) {
        this.method = method;
    }

    /**
     * 메서드 이름을 반환합니다.
     * JSON 직렬화 시 사용됩니다.
     * 
     * @return 메서드 이름
     */
    @JsonValue
    public String getMethod() {
        return method;
    }
}
