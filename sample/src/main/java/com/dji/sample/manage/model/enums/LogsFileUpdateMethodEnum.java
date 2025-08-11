package com.dji.sample.manage.model.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * 로그 파일 업데이트 방법 열거형
 * 
 * DJI Cloud API의 로그 파일 업데이트 방법을 정의하는 열거형 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 파일 업데이트 방법 정의
 *    - 로그 파일 업데이트 취소 (CANCEL)
 *    - 알 수 없는 업데이트 방법 (UNKNOWN)
 * 
 * 2. 업데이트 방법 검색
 *    - 문자열 값으로부터 열거형 상수 검색
 *    - 스트림 API를 활용한 효율적인 검색
 *    - 일치하는 값이 없을 경우 UNKNOWN 반환
 * 
 * 3. 로그 파일 관리
 *    - 로그 파일 업로드 프로세스 제어
 *    - 업로드 작업의 취소 및 중단 처리
 *    - 로그 파일 상태 관리
 * 
 * 이 열거형은 로그 파일 업로드 및 관리 과정에서
 * 다양한 업데이트 방법을 표준화된 방식으로
 * 처리할 수 있도록 지원합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/9
 */
@Getter
public enum LogsFileUpdateMethodEnum {

    /**
     * 로그 파일 업데이트 취소
     * 진행 중인 로그 파일 업데이트를 취소
     */
    CANCEL("cancel"),

    /**
     * 알 수 없는 업데이트 방법
     * 정의되지 않은 업데이트 방법에 대한 기본값
     */
    UNKNOWN("unknown");

    /**
     * 업데이트 방법 문자열
     * 각 업데이트 방법에 대응하는 문자열 값
     */
    String method;

    /**
     * 생성자
     * @param method 업데이트 방법 문자열
     */
    LogsFileUpdateMethodEnum(String method) {
        this.method = method;
    }

    /**
     * 업데이트 방법 문자열로부터 열거형 상수를 찾습니다.
     * 
     * 주어진 업데이트 방법 문자열에 해당하는 열거형 상수를 반환합니다.
     * 일치하는 값이 없으면 UNKNOWN을 반환합니다.
     * 
     * @param method 찾을 업데이트 방법 문자열
     * @return 해당하는 열거형 상수 또는 UNKNOWN
     */
    public static LogsFileUpdateMethodEnum find(String method) {
        return Arrays.stream(LogsFileUpdateMethodEnum.values()).filter(e -> e.method.equals(method)).findAny().orElse(UNKNOWN);
    }
}
