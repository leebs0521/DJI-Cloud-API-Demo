
package com.dji.sdk.common;

import com.dji.sdk.mqtt.events.IEventsErrorCode;
import com.dji.sdk.mqtt.services.IServicesErrorCode;

import java.util.Arrays;

/**
 * 공통 오류 열거형
 * 
 * 이 열거형은 SDK에서 공통적으로 사용되는
 * 기본적인 오류 코드들을 정의합니다.
 * 
 * 주요 구성 요소:
 * - SUCCESS: 성공 상태
 * - STATUS_NOT_SUPPORTED: 상태 지원되지 않음 (로그 업로드 중이거나 비행 임무 실행 중)
 * - WRONG_PARAMETER: 잘못된 매개변수 (클라우드 명령 매개변수 오류)
 * - UNKNOWN: 알 수 없는 오류
 * 
 * 이 열거형은 여러 서비스에서 공통적으로
 * 발생할 수 있는 오류를 표준화하여 관리합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/25
 */
public enum CommonErrorEnum implements IServicesErrorCode, IEventsErrorCode, IErrorInfo {

    /**
     * 성공
     * 
     * 작업이 성공적으로 완료되었습니다.
     */
    SUCCESS(0, "Success"),

    /**
     * 상태 지원되지 않음
     * 
     * 디바이스가 로그를 업로드하거나 비행 임무를 실행하고 있습니다. 나중에 다시 시도하세요.
     */
    STATUS_NOT_SUPPORTED(314000, "The device is either uploading logs or executing a flight mission. Please try again later."),

    /**
     * 잘못된 매개변수
     * 
     * 클라우드 명령 매개변수 오류입니다. 도크가 명령을 실행할 수 없습니다.
     */
    WRONG_PARAMETER(325001, "Cloud command parameter error. Dock unable to execute command."),

    /**
     * 알 수 없음
     * 
     * 알 수 없는 오류입니다.
     */
    UNKNOWN(-1, "Unknown");

    /**
     * 오류 코드
     * 
     * 각 오류를 구분하는 정수 값입니다.
     */
    private final int code;

    /**
     * 오류 메시지
     * 
     * 각 오류에 대한 설명 메시지입니다.
     */
    private final String msg;

    /**
     * 공통 오류 열거형 생성자
     * 
     * @param code 오류 코드
     * @param msg 오류 메시지
     */
    CommonErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 오류 코드를 반환합니다.
     * 
     * @return 오류 코드
     */
    public Integer getCode() {
        return this.code;
    }

    /**
     * 오류 메시지를 반환합니다.
     * 
     * @return 오류 메시지
     */
    public String getMessage() {
        return this.msg;
    }

    /**
     * 오류 코드로 공통 오류를 찾습니다.
     * 
     * 주어진 오류 코드에 해당하는 열거형을 반환합니다.
     * 해당하는 오류 코드가 없으면 UNKNOWN을 반환합니다.
     * 
     * @param code 찾을 오류 코드
     * @return 해당하는 CommonErrorEnum 열거형
     */
    public static CommonErrorEnum find(int code) {
        return Arrays.stream(values()).filter(error -> error.code == code).findAny().orElse(UNKNOWN);
    }
}
