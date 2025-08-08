package com.dji.sdk.common;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * HTTP 응답 데이터의 표준 형식을 정의하는 클래스
 * 
 * 모든 HTTP API 응답은 이 클래스를 통해 일관된 형식으로 반환됩니다.
 * 제네릭 타입 T를 사용하여 다양한 데이터 타입을 지원합니다.
 * 
 * 주요 구성 요소:
 * - code: 응답 코드 (0: 성공, 0이 아닌 값: 오류)
 * - message: 응답 메시지
 * - data: 응답 데이터
 * 
 * 이 클래스는 API 응답의 표준화를 통해
 * 클라이언트가 일관된 방식으로 응답을 처리할 수 있도록 합니다.
 * 
 * @param <T> 응답 데이터의 타입
 */
@Schema(description = "The data format of the http response.")
public class HttpResultResponse<T> {

    /**
     * 성공 응답 코드
     * 
     * 모든 성공적인 응답에서 사용되는 표준 코드입니다.
     */
    public static final int CODE_SUCCESS = 0;
    
    /**
     * 실패 응답 코드
     * 
     * 일반적인 실패 응답에서 사용되는 표준 코드입니다.
     */
    public static final int CODE_FAILED = -1;
    
    /**
     * 성공 메시지
     * 
     * 성공적인 응답에서 사용되는 표준 메시지입니다.
     */
    public static final String MESSAGE_SUCCESS = "success";
    
    /**
     * 실패 메시지
     * 
     * 실패 응답에서 사용되는 표준 메시지입니다.
     */
    public static final String MESSAGE_FAILED = "failed";

    /**
     * 응답 코드
     * 
     * 0은 성공을, 0이 아닌 값은 오류를 나타냅니다.
     */
    @Schema(description = "0 means success, non-zero means error.", example = "0")
    private int code;

    /**
     * 응답 메시지
     * 
     * 응답에 대한 설명 메시지입니다.
     */
    @Schema(description = "The response message.", example = MESSAGE_SUCCESS)
    private String message;

    /**
     * 응답 데이터
     * 
     * 실제 응답 데이터를 포함합니다.
     */
    @Schema(description = "The response data.")
    private T data;
    
    /**
     * 기본 생성자
     * 
     * 빈 HttpResultResponse 인스턴스를 생성합니다.
     */
    public HttpResultResponse() {
    }

    /**
     * 문자열 표현을 반환합니다.
     * 
     * @return 문자열 표현
     */
    @Override
    public String toString() {
        return "HttpResultResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * 응답 코드를 반환합니다.
     * 
     * @return 응답 코드
     */
    public int getCode() {
        return code;
    }

    /**
     * 응답 코드를 설정합니다.
     * 
     * @param code 설정할 응답 코드
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public HttpResultResponse<T> setCode(int code) {
        this.code = code;
        return this;
    }

    /**
     * 응답 메시지를 반환합니다.
     * 
     * @return 응답 메시지
     */
    public String getMessage() {
        return message;
    }

    /**
     * 응답 메시지를 설정합니다.
     * 
     * @param message 설정할 응답 메시지
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public HttpResultResponse<T> setMessage(String message) {
        this.message = message;;
        return this;
    }

    /**
     * 응답 데이터를 반환합니다.
     * 
     * @return 응답 데이터
     */
    public T getData() {
        return data;
    }

    /**
     * 응답 데이터를 설정합니다.
     * 
     * @param data 설정할 응답 데이터
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public HttpResultResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 성공 응답을 생성합니다.
     * 
     * 데이터 없이 성공 응답을 생성합니다.
     * 
     * @return 성공 응답 객체
     */
    public static HttpResultResponse success() {
        return new HttpResultResponse()
                .setCode(CODE_SUCCESS)
                .setMessage(MESSAGE_SUCCESS)
                .setData("");
    }

    /**
     * 데이터가 포함된 성공 응답을 생성합니다.
     * 
     * @param data 응답에 포함할 데이터
     * @param <T> 데이터 타입
     * @return 성공 응답 객체
     */
    public static <T> HttpResultResponse<T> success(T data) {
        return HttpResultResponse.success().setData(data);
    }

    /**
     * 기본 오류 응답을 생성합니다.
     * 
     * @return 오류 응답 객체
     */
    public static HttpResultResponse error() {
        return new HttpResultResponse()
                .setCode(CODE_FAILED)
                .setMessage(MESSAGE_FAILED);
    }

    /**
     * 사용자 정의 메시지가 포함된 오류 응답을 생성합니다.
     * 
     * @param message 오류 메시지
     * @return 오류 응답 객체
     */
    public static HttpResultResponse error(String message) {
        return new HttpResultResponse()
                .setCode(CODE_FAILED)
                .setMessage(message);
    }

    /**
     * 사용자 정의 코드와 메시지가 포함된 오류 응답을 생성합니다.
     * 
     * @param code 오류 코드
     * @param message 오류 메시지
     * @return 오류 응답 객체
     */
    public static HttpResultResponse error(int code, String message) {
        return new HttpResultResponse()
                .setCode(code)
                .setMessage(message);
    }

    /**
     * IErrorInfo 인터페이스를 구현한 객체로부터 오류 응답을 생성합니다.
     * 
     * @param errorInfo 오류 정보를 담고 있는 객체
     * @return 오류 응답 객체
     */
    public static HttpResultResponse error(IErrorInfo errorInfo) {
        return new HttpResultResponse()
                .setCode(errorInfo.getCode())
                .setMessage(errorInfo.getMessage());
    }
}
