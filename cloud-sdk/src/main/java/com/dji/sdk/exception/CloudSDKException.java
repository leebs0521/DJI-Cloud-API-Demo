package com.dji.sdk.exception;

import com.dji.sdk.common.IErrorInfo;

import java.util.Arrays;

/**
 * Cloud SDK 예외 처리 클래스
 * 
 * 이 클래스는 Cloud SDK에서 발생하는 모든 예외를 처리합니다.
 * IErrorInfo 인터페이스를 구현한 오류 정보를 포함하며,
 * 다양한 생성자를 통해 다양한 상황에 맞는 예외를 생성할 수 있습니다.
 * 
 * 주요 기능:
 * - 다양한 생성자를 통한 예외 생성
 * - IErrorInfo를 통한 표준화된 오류 정보 제공
 * - 클래스와 코드 정보를 통한 상세한 오류 메시지 생성
 * - 원인 예외와 추가 메시지를 통한 예외 체이닝
 * 
 * 이 클래스는 SDK의 모든 예외 처리를 표준화하고
 * 일관된 오류 정보를 제공합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/19
 */
public class CloudSDKException extends RuntimeException {

    /**
     * 오류 정보를 담는 객체
     * 
     * 예외와 관련된 상세한 오류 정보를 포함합니다.
     */
    private IErrorInfo errorInfo;

    /**
     * 메시지만으로 예외를 생성합니다.
     * 
     * @param message 예외 메시지
     */
    public CloudSDKException(String message) {
        super(message);
        this.errorInfo = CloudSDKErrorEnum.UNKNOWN;
    }

    /**
     * 원인 예외로 예외를 생성합니다.
     * 
     * @param cause 원인 예외
     */
    public CloudSDKException(Throwable cause) {
        super(cause);
        this.errorInfo = CloudSDKErrorEnum.UNKNOWN;
    }

    /**
     * 기본 예외를 생성합니다.
     * 
     * 기본 메시지 "SDK Exception"으로 예외를 생성합니다.
     */
    public CloudSDKException() {
        this("SDK Exception");
        this.errorInfo = CloudSDKErrorEnum.UNKNOWN;
    }

    /**
     * 클래스와 코드 정보로 예외를 생성합니다.
     * 
     * @param clazz 예외가 발생한 클래스
     * @param code 오류 코드 배열
     */
    public CloudSDKException(Class clazz, Object... code) {
        this(clazz.getName() + " has unknown data: " + Arrays.toString(code));
        this.errorInfo = CloudSDKErrorEnum.WRONG_DATA;
    }

    /**
     * IErrorInfo로 예외를 생성합니다.
     * 
     * @param err 오류 정보 객체
     */
    public CloudSDKException(IErrorInfo err) {
        this(err, null);
    }

    /**
     * IErrorInfo와 추가 메시지로 예외를 생성합니다.
     * 
     * @param err 오류 정보 객체
     * @param msg 추가 메시지
     */
    public CloudSDKException(IErrorInfo err, String msg) {
        this(String.format("Error Code: %d, Error Msg: %s. %s", err.getCode(), err.getMessage(), msg));
        this.errorInfo = err;
    }

    /**
     * 오류 정보를 반환합니다.
     * 
     * @return 오류 정보 객체
     */
    public IErrorInfo getErrorInfo() {
        return errorInfo;
    }
}
