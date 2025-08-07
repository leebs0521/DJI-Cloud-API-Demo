package com.dji.sdk.cloudapi.hms;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * HMS 비행 중 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 HMS(Health Management System)의 비행 중 상태를 정의합니다.
 * 디바이스가 비행 중일 때 발생하는 HMS를 구분하기 위한 열거형입니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/7/7
 */
public enum HmsInTheSkyEnum {

    /**
     * 비행 중
     * 디바이스가 비행 중일 때의 HMS 상태
     */
    IN_THE_SKY("_in_the_sky");

    /**
     * 비행 중 텍스트
     * 비행 중 상태를 나타내는 문자열
     */
    private final String text;

    /**
     * HMS 비행 중 열거형 생성자
     * 
     * @param text 비행 중 텍스트
     */
    HmsInTheSkyEnum(String text) {
        this.text = text;
    }

    /**
     * 비행 중 텍스트를 반환합니다.
     * JSON 직렬화 시 사용됩니다.
     * 
     * @return 비행 중 텍스트
     */
    @JsonValue
    public String getText() {
        return text;
    }
}