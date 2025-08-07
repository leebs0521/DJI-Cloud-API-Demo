package com.dji.sdk.cloudapi.device;

import com.dji.sdk.cloudapi.livestream.LiveErrorCodeEnum;
import com.dji.sdk.common.ErrorCodeSourceEnum;
import com.dji.sdk.mqtt.MqttReply;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 도크 라이브 오류 상태 클래스
 * 
 * 이 클래스는 도크의 라이브 스트림 오류 상태를 관리합니다.
 * 오류 코드, 오류 메시지, 성공 여부 등을 포함합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/23
 */
public class DockLiveErrorStatus {

    /**
     * 모듈로 연산을 위한 상수 (100,000)
     */
    private static final int MOD = 100_000;

    /**
     * 오류 코드 소스 (기본값: DOCK)
     */
    private ErrorCodeSourceEnum source = ErrorCodeSourceEnum.DOCK;

    /**
     * 라이브 오류 코드
     */
    private LiveErrorCodeEnum errorCode;

    /**
     * 성공 여부
     */
    private boolean success;

    @Override
    public String toString() {
        return "{" +
                "errorCode=" + getCode() +
                ", errorMsg=" + getMessage() +
                '}';
    }

    /**
     * 오류 코드로부터 DockLiveErrorStatus 객체를 생성합니다.
     * 
     * @param code 오류 코드
     */
    @JsonCreator
    public DockLiveErrorStatus(int code) {
        this.success = MqttReply.CODE_SUCCESS == code;
        this.source = ErrorCodeSourceEnum.find(code / MOD);
        this.errorCode = LiveErrorCodeEnum.find(code % MOD);
    }

    /**
     * 오류 메시지를 반환합니다.
     * 
     * @return 오류 메시지
     */
    public String getMessage() {
        return errorCode.getMessage();
    }

    /**
     * 오류 코드를 반환합니다.
     * 
     * @return 오류 코드
     */
    @JsonValue
    public Integer getCode() {
        return source.getSource() * MOD + errorCode.getCode();
    }

    /**
     * 성공 여부를 반환합니다.
     * 
     * @return 성공 여부 (true: 성공, false: 실패)
     */
    public boolean isSuccess() {
        return success;
    }
}