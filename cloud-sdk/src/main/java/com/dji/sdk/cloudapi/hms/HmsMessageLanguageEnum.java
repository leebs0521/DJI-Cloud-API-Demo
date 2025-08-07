package com.dji.sdk.cloudapi.hms;

/**
 * HMS 메시지 언어 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 HMS(Health Management System) 메시지의 언어를 정의합니다.
 * 영어와 중국어 두 가지 언어를 지원하여 다국어 HMS 메시지를 제공합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/7/7
 */
public enum HmsMessageLanguageEnum {

    /**
     * 영어
     * HMS 메시지의 영어 버전
     */
    EN("en"),

    /**
     * 중국어
     * HMS 메시지의 중국어 버전
     */
    ZH("zh");

    /**
     * 언어 코드
     * 각 언어에 대한 고유한 언어 코드
     */
    private final String language;

    /**
     * HMS 메시지 언어 열거형 생성자
     * 
     * @param language 언어 코드
     */
    HmsMessageLanguageEnum(String language) {
        this.language = language;
    }

    /**
     * 언어 코드를 반환합니다.
     * 
     * @return 언어 코드
     */
    public String getLanguage() {
        return language;
    }
}