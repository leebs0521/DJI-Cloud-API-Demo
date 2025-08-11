package com.dji.sample.media.model;

import lombok.Getter;

/**
 * 미디어 메서드 열거형
 * 미디어 관련 작업에서 사용되는 메서드 타입을 정의하는 열거형입니다.
 * 각 메서드는 특정 미디어 작업 유형을 나타내며, 시스템 내에서 일관된 메서드 식별을 위해 사용됩니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/23
 */
@Getter
public enum MediaMethodEnum {

    /**
     * 비행 작업 미디어 우선 업로드
     * 비행 작업과 관련된 미디어 파일을 우선적으로 업로드하는 메서드입니다.
     * 중요한 비행 데이터나 이미지를 먼저 처리하기 위해 사용됩니다.
     */
    UPLOAD_FLIGHT_TASK_MEDIA_PRIORITIZE("upload_flighttask_media_prioritize");

    /**
     * 메서드 식별자
     * 각 미디어 메서드를 구분하는 고유한 문자열 값입니다.
     */
    private String method;

    /**
     * 미디어 메서드 열거형 생성자
     * @param method 메서드 식별자 문자열
     */
    MediaMethodEnum(String method) {
        this.method = method;
    }
}
