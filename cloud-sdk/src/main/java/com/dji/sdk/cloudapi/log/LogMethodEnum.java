package com.dji.sdk.cloudapi.log;

/**
 * 로그 관련 메서드를 정의하는 열거형
 * 로그 파일 업로드 관련 작업의 메서드 타입을 정의합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/14
 */
public enum LogMethodEnum {

    /** 파일 업로드 목록 조회 */
    FILE_UPLOAD_LIST("fileupload_list"),

    /** 파일 업로드 시작 */
    FILE_UPLOAD_START("fileupload_start"),

    /** 파일 업로드 상태 업데이트 */
    FILE_UPLOAD_UPDATE("fileupload_update");

    /** 메서드 타입을 나타내는 문자열 값 */
    private final String method;

    /**
     * 로그 메서드 열거형 생성자
     * @param method 메서드 타입을 나타내는 문자열 값
     */
    LogMethodEnum(String method) {
        this.method = method;
    }

    /**
     * 메서드 타입 값을 반환합니다.
     * 
     * @return 메서드 타입 문자열 값
     */
    public String getMethod() {
        return method;
    }
}
