package com.dji.sdk.mqtt.services;

import com.dji.sdk.cloudapi.control.ControlErrorCodeEnum;
import com.dji.sdk.cloudapi.debug.DebugErrorCodeEnum;
import com.dji.sdk.cloudapi.firmware.FirmwareErrorCodeEnum;
import com.dji.sdk.cloudapi.livestream.LiveErrorCodeEnum;
import com.dji.sdk.cloudapi.log.LogErrorCodeEnum;
import com.dji.sdk.cloudapi.wayline.WaylineErrorCodeEnum;
import com.dji.sdk.common.CommonErrorEnum;
import com.dji.sdk.common.ErrorCodeSourceEnum;
import com.dji.sdk.common.IErrorInfo;
import com.dji.sdk.mqtt.MqttReply;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * MQTT 서비스 에러 코드 클래스
 * 다양한 모듈의 에러 코드를 통합 관리하는 클래스
 * 
 * @author sean
 * @version 1.7
 * @date 2023/7/14
 */
public class ServicesErrorCode implements IErrorInfo {

    /** 에러 코드 모듈 구분을 위한 나머지 연산 값 */
    private static final int MOD = 100_000;

    /** 에러 코드 소스 (어떤 모듈에서 발생한 에러인지) */
    private ErrorCodeSourceEnum source;

    /** 실제 에러 코드 정보 */
    private IServicesErrorCode errorCode;

    /** 성공 여부 */
    private boolean success;

    /** 원본 에러 코드 */
    private Integer sourceCode;

    @Override
    public String toString() {
        return "{" +
                "errorCode=" + getCode() +
                ", errorMsg=" + getMessage() +
                '}';
    }

    /**
     * 에러 코드로부터 ServicesErrorCode 객체를 생성합니다.
     * 코드를 분석하여 적절한 에러 정보를 찾아 설정합니다.
     * 
     * @param code 에러 코드
     */
    @JsonCreator
    public ServicesErrorCode(int code) {
        this.sourceCode = code;
        // 성공 코드인 경우
        if (MqttReply.CODE_SUCCESS == code) {
            this.success = true;
            this.errorCode = CommonErrorEnum.SUCCESS;
            return;
        }
        // 에러 코드 소스 판별
        this.source = ErrorCodeSourceEnum.find(code / MOD);
        // 각 모듈별로 에러 코드 검색 (라이브스트림 모듈부터 시작)
        this.errorCode = LiveErrorCodeEnum.find(code % MOD);
        if (errorCode.getCode() != -1) {
            return;
        }
        this.errorCode = DebugErrorCodeEnum.find(code);
        if (errorCode.getCode() != -1) {
            return;
        }
        this.errorCode = ControlErrorCodeEnum.find(code);
        if (errorCode.getCode() != -1) {
            return;
        }
        this.errorCode = LogErrorCodeEnum.find(code);
        if (errorCode.getCode() != -1) {
            return;
        }
        this.errorCode = FirmwareErrorCodeEnum.find(code);
        if (errorCode.getCode() != -1) {
            return;
        }
        this.errorCode = WaylineErrorCodeEnum.find(code);
        if (errorCode.getCode() != -1) {
            return;
        }
        // 모든 모듈에서 찾지 못한 경우 공통 에러로 설정
        this.errorCode = CommonErrorEnum.find(code);
    }

    /**
     * 에러 메시지를 반환합니다.
     * @return 에러 메시지
     */
    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }

    /**
     * 에러 코드를 반환합니다.
     * @return 에러 코드
     */
    @JsonValue
    public Integer getCode() {
        return sourceCode;
    }

    /**
     * 성공 여부를 반환합니다.
     * @return 성공 여부
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 에러 코드 소스를 반환합니다.
     * @return 에러 코드 소스
     */
    public ErrorCodeSourceEnum getSource() {
        return source;
    }
}
