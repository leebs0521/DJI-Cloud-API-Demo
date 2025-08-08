package com.dji.sdk.cloudapi.log;

import com.dji.sdk.common.IErrorInfo;
import com.dji.sdk.mqtt.events.IEventsErrorCode;
import com.dji.sdk.mqtt.services.IServicesErrorCode;

import java.util.Arrays;

/**
 * 로그 관련 오류 코드를 정의하는 열거형
 * 로그 파일 업로드 및 내보내기 기능에서 발생할 수 있는 다양한 오류 상황을 관리합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/25
 */
public enum LogErrorCodeEnum implements IServicesErrorCode, IEventsErrorCode, IErrorInfo {

    /** 장치 재시작으로 인한 로그 내보내기 중단 */
    DEVICE_RESTART(324001, "Device restart interrupts log export."),

    /** 내보내기 시간 초과 - 선택된 로그가 너무 많음 */
    EXPORT_TIMEOUT(324012, "Compressing logs timed out. Too many logs selected. Unselect some logs and try again."),

    /** 로그 목록 가져오기 실패 */
    PULL_FAILED(324013, "Failed to obtain device log list. Try again later."),

    /** 빈 로그 목록 */
    EMPTY_LOG_LIST(324014, "Device log list is empty. Refresh page or restart dock and try again."),

    /** 항공기 종료 또는 연결 끊김 */
    AIRCRAFT_SHUTDOWN(324015, "Aircraft powered off or not connected. Unable to obtain log list. Make sure aircraft is inside dock. Remotely power on aircraft and try again."),

    /** 저장 공간 부족 */
    INSUFFICIENT_STORAGE_SPACE(324016, "Insufficient dock storage space. Failed to compress logs. Clear space or try again later."),

    /** 로그 없음 */
    NO_LOG(324017, "Failed to compress logs. Unable to obtain logs of selected aircraft. Refresh page or restart dock and try again."),

    /** 압축 실패 */
    COMPRESSION_FAILED(324018, "Failed to compress logs and submit issue report. Try again later or restart dock and try again."),

    /** 업로드 실패 - 네트워크 이상 */
    UPLOAD_FAILED(324019, "Due to network anomalies at the airport, the log upload has failed. Please retry later."),

    /** 알 수 없는 오류 */
    UNKNOWN(-1, "UNKNOWN"),

    ;


    /** 오류 메시지 */
    private final String msg;

    /** 오류 코드 */
    private final int code;

    /**
     * 로그 오류 코드 열거형 생성자
     * @param code 오류 코드
     * @param msg 오류 메시지
     */
    LogErrorCodeEnum(int code, String msg) {
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
        return this.msg;
    }

    /**
     * 오류 코드를 반환합니다.
     * 
     * @return 오류 코드
     */
    @Override
    public Integer getCode() {
        return this.code;
    }

    /**
     * 오류 코드로 해당하는 열거형 객체를 찾습니다.
     * 
     * @param code 찾을 오류 코드
     * @return 해당하는 LogErrorCodeEnum 객체, 없으면 UNKNOWN 반환
     */
    public static LogErrorCodeEnum find(int code) {
        return Arrays.stream(values()).filter(codeEnum -> codeEnum.code == code).findAny().orElse(UNKNOWN);
    }

}
