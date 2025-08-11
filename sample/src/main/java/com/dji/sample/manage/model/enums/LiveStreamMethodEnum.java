package com.dji.sample.manage.model.enums;

import lombok.Getter;

/**
 * 라이브 스트림 방법 열거형
 * 
 * DJI Cloud API의 라이브 스트림 관련 방법을 정의하는 열거형 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 라이브 스트림 방법 정의
 *    - 라이브 스트림 시작 푸시 (LIVE_START_PUSH)
 *    - 라이브 스트림 중지 푸시 (LIVE_STOP_PUSH)
 *    - 라이브 품질 설정 (LIVE_SET_QUALITY)
 *    - 라이브 렌즈 변경 (LIVE_LENS_CHANGE)
 *    - 알 수 없는 방법 (UNKNOWN)
 * 
 * 2. 라이브 스트림 제어
 *    - 라이브 스트림 시작 및 중지 제어
 *    - 라이브 스트림 품질 동적 조정
 *    - 라이브 스트림 렌즈 전환 기능
 * 
 * 3. 라이브 스트림 관리
 *    - 라이브 스트림 상태 관리
 *    - 라이브 스트림 설정 변경
 *    - 라이브 스트림 오류 처리
 * 
 * 이 열거형은 DJI 디바이스의 라이브 스트리밍 시스템에서
 * 다양한 라이브 스트림 관련 방법을 표준화된 방식으로
 * 관리하고 제어할 수 있도록 지원합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/14
 */
@Getter
public enum LiveStreamMethodEnum {

    /**
     * 라이브 스트림 시작 푸시
     * 라이브 스트림을 시작하는 푸시 명령
     */
    LIVE_START_PUSH("live_start_push"),

    /**
     * 라이브 스트림 중지 푸시
     * 라이브 스트림을 중지하는 푸시 명령
     */
    LIVE_STOP_PUSH("live_stop_push"),

    /**
     * 라이브 품질 설정
     * 라이브 스트림의 품질을 설정하는 명령
     */
    LIVE_SET_QUALITY("live_set_quality"),

    /**
     * 라이브 렌즈 변경
     * 라이브 스트림의 렌즈를 변경하는 명령
     */
    LIVE_LENS_CHANGE("live_lens_change"),

    /**
     * 알 수 없는 방법
     * 정의되지 않은 라이브 스트림 방법에 대한 기본값
     */
    UNKNOWN("unknown");

    /**
     * 라이브 스트림 방법 문자열
     * 각 라이브 스트림 방법에 대응하는 문자열 값
     */
    private String method;

    /**
     * 생성자
     * @param method 라이브 스트림 방법 문자열
     */
    LiveStreamMethodEnum(String method) {
        this.method = method;
    }

}
