package com.dji.sdk.cloudapi.livestream;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 라이브스트림 메서드를 정의하는 열거형
 * 라이브스트림 관련 작업의 메서드 타입을 정의합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/14
 */
public enum LiveStreamMethodEnum {

    /** 라이브스트림 푸시 시작 */
    LIVE_START_PUSH("live_start_push"),

    /** 라이브스트림 푸시 중지 */
    LIVE_STOP_PUSH("live_stop_push"),

    /** 라이브스트림 품질 설정 */
    LIVE_SET_QUALITY("live_set_quality"),

    /** 라이브스트림 렌즈 변경 */
    LIVE_LENS_CHANGE("live_lens_change");

    /** 메서드 타입을 나타내는 문자열 값 */
    private final String method;

    /**
     * 라이브스트림 메서드 열거형 생성자
     * @param method 메서드 타입을 나타내는 문자열 값
     */
    LiveStreamMethodEnum(String method) {
        this.method = method;
    }

    /**
     * 메서드 타입 값을 반환합니다.
     * JSON 직렬화 시 사용됩니다.
     * 
     * @return 메서드 타입 문자열 값
     */
    @JsonValue
    public String getMethod() {
        return method;
    }
}
