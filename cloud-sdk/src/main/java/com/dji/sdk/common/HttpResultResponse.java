package com.dji.sdk.common;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * HTTP 응답 데이터의 표준 형식을 정의하는 클래스
 * 
 * 모든 HTTP API 응답은 이 클래스를 통해 일관된 형식으로 반환됩니다.
 * 제네릭 타입 T를 사용하여 다양한 데이터 타입을 지원합니다.
 * 
 * @param <T> 응답 데이터의 타입
 */
@Schema(description = "The data format of the http response.")
public class HttpResultResponse<T> {

    /**
     * 성공 응답 코드
     */
    public static final int CODE_SUCCESS = 0;
    
    /**
     * 실패 응답 코드
     */
    public static final int CODE_FAILED = -1;
    
    /**
     * 성공 메시지
     */
    public static final String MESSAGE_SUCCESS = "success";
    
    /**
     * 실패 메시지
     */
    public static final String MESSAGE_FAILED = "failed";

    /**
     * 응답 코드 (0: 성공, 0이 아닌 값: 오류)
     */
    @Schema(description = "0 means success, non-zero means error.", example = "0")
    private int code;

    /**
     * 응답 메시지
     */
    @Schema(description = "The response message.", example = MESSAGE_SUCCESS)
    private String message;

    /**
     * 응답 데이터
     */
    @Schema(description = "The response data.")
    private T data;
    
    /**
     * 기본 생성자
     */
    public HttpResultResponse() {
    }

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
     * 응답 코드를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param code 설정할 응답 코드
     * @return 현재 HttpResultResponse 객체
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
     * 응답 메시지를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param message 설정할 응답 메시지
     * @return 현재 HttpResultResponse 객체
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
     * 응답 데이터를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param data 설정할 응답 데이터
     * @return 현재 HttpResultResponse 객체
     */
    public HttpResultResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 성공 응답을 생성합니다. (데이터 없음)
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
     * 성공 응답을 생성합니다. (데이터 포함)
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
