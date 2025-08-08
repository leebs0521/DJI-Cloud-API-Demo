package com.dji.sdk.cloudapi.media;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 미디어 메서드 열거형
 * 
 * 이 열거형은 미디어 관련 API 메서드들을 정의합니다.
 * 현재는 비행 작업 미디어 우선 업로드 메서드만 포함되어 있습니다.
 * 
 * 주요 구성 요소:
 * - UPLOAD_FLIGHTTASK_MEDIA_PRIORITIZE: 비행 작업 미디어 우선 업로드
 * 
 * 이 열거형은 미디어 API 호출 시 사용할 메서드를 식별하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/14
 */
public enum MediaMethodEnum {

    /**
     * 비행 작업 미디어 우선 업로드
     * 
     * 비행 작업 중 촬영된 미디어 파일을 우선적으로 업로드하는 메서드입니다.
     * 일반적인 미디어 업로드보다 높은 우선순위를 가지며,
     * 비행 작업의 완료를 기다리지 않고 즉시 업로드를 시작합니다.
     */
    UPLOAD_FLIGHTTASK_MEDIA_PRIORITIZE("upload_flighttask_media_prioritize");

    /**
     * 메서드 문자열 값
     * 
     * API 호출 시 사용되는 실제 메서드 이름입니다.
     */
    private final String method;

    MediaMethodEnum(String method) {
        this.method = method;
    }

    /**
     * 메서드 문자열 값을 반환합니다.
     * 
     * @return 메서드 문자열 값
     */
    @JsonValue
    public String getMethod() {
        return method;
    }
}
