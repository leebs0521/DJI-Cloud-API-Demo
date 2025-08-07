package com.dji.sdk.cloudapi.control;

import com.dji.sdk.common.IErrorInfo;
import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * DRC 상태 오류 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 DRC(Direct Remote Control) 모드의 상태 오류를 정의합니다.
 * 성공과 다양한 MQTT 연결 오류 상황을 포함합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/17
 */
public enum DrcStatusErrorEnum implements IErrorInfo {

    /**
     * 성공
     * DRC 모드가 정상적으로 작동 중
     */
    SUCCESS(0, "success"),

    /**
     * MQTT 오류
     * MQTT 연결에 오류가 발생함
     */
    MQTT_ERR(514300, "The mqtt connection error."),

    /**
     * 하트비트 타임아웃
     * 하트비트 응답 시간 초과로 도크 연결이 끊어짐
     */
    HEARTBEAT_TIMEOUT(514301, "The heartbeat times out and the dock disconnects."),

    /**
     * MQTT 인증서 오류
     * MQTT 인증서에 이상이 있어 연결에 실패함
     */
    MQTT_CERTIFICATE_ERR(514302, "The mqtt certificate is abnormal and the connection fails."),

    /**
     * MQTT 연결 손실
     * 도크 네트워크 이상으로 MQTT 연결이 끊어짐
     */
    MQTT_LOST(514303, "The dock network is abnormal and the mqtt connection is lost."),

    /**
     * MQTT 연결 거부
     * MQTT 서버로의 도크 연결이 거부됨
     */
    MQTT_REFUSE(514304, "The dock connection to mqtt server was refused.");

    /**
     * 오류 메시지
     */
    private final String msg;

    /**
     * 오류 코드
     */
    private final int code;

    /**
     * DRC 상태 오류 열거형 생성자
     * 
     * @param code 오류 코드
     * @param msg 오류 메시지
     */
    DrcStatusErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 오류 메시지를 반환합니다.
     * 
     * @return 오류 메시지
     */
    @Override
    public String getMessage() {
        return msg;
    }

    /**
     * 오류 코드를 반환합니다.
     * 
     * @return 오류 코드
     */
    @Override
    public Integer getCode() {
        return code;
    }

    /**
     * 정수 값으로 DRC 상태 오류를 찾습니다.
     * 
     * @param code 찾을 오류 코드
     * @return 찾은 DRC 상태 오류 열거형
     * @throws CloudSDKException 해당하는 오류를 찾을 수 없는 경우
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static DrcStatusErrorEnum find(int code) {
        return Arrays.stream(values()).filter(error -> error.code == code).findAny()
                .orElseThrow(() -> new CloudSDKException(DrcStatusErrorEnum.class, code));
    }
}
