package com.dji.sdk.cloudapi.firmware;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 펌웨어 메서드 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 펌웨어 관련 메서드를 정의합니다.
 * OTA(Over-The-Air) 펌웨어 업데이트 생성 메서드를 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/14
 */
public enum FirmwareMethodEnum {

    /**
     * OTA 생성
     * OTA 펌웨어 업데이트를 생성하는 메서드
     */
    OTA_CREATE("ota_create");

    /**
     * 메서드 이름
     */
    private final String method;

    /**
     * 펌웨어 메서드 열거형 생성자
     * 
     * @param method 메서드 이름
     */
    FirmwareMethodEnum(String method) {
        this.method = method;
    }

    /**
     * 메서드 이름을 반환합니다.
     * 
     * @return 메서드 이름
     */
    @JsonValue
    public String getMethod() {
        return method;
    }
}
