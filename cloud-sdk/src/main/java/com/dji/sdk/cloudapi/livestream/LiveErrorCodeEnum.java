package com.dji.sdk.cloudapi.livestream;

import com.dji.sdk.common.IErrorInfo;
import com.dji.sdk.mqtt.services.IServicesErrorCode;

import java.util.Arrays;

/**
 * 라이브스트림 관련 오류 코드를 정의하는 열거형
 * 라이브스트림 기능에서 발생할 수 있는 다양한 오류 상황을 관리합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/25
 */
public enum LiveErrorCodeEnum implements IServicesErrorCode, IErrorInfo {

    /** 성공 */
    SUCCESS(0, "Success"),

    /** 항공기가 없음 */
    NO_AIRCRAFT(13001, "No aircraft."),

    /** 카메라가 없음 */
    NO_CAMERA(13002, "No camera."),

    /** 라이브스트림이 이미 시작됨 */
    LIVE_STREAM_ALREADY_STARTED(13003, "The camera has started live streaming."),

    /** 지원하지 않는 기능 */
    FUNCTION_NOT_SUPPORT(13004, "The function is not supported."),

    /** 지원하지 않는 전략 */
    STRATEGY_NOT_SUPPORT(13005, "The strategy is not supported."),

    /** 카메라 인터페이스에 있지 않음 */
    NOT_IN_CAMERA_INTERFACE(13006, "The current app is not in the camera interface."),

    /** 비행 제어 권한이 없음 */
    NO_FLIGHT_CONTROL(13007, "The remote control has no flight control rights and cannot respond to control commands"),

    /** 스트림 데이터가 없음 */
    NO_STREAM_DATA(13008, "The current app has no stream data."),

    /** 너무 빈번한 작업 */
    TOO_FREQUENT(13009, "The operation is too frequent."),

    /** 활성화 실패 */
    ENABLE_FAILED(13010, "Please check whether the live stream service is normal."),

    /** 현재 라이브스트림이 없음 */
    NO_LIVE_STREAM(13011, "There are no live stream currently."),

    /** 스트림 전환이 지원되지 않음 */
    SWITCH_NOT_SUPPORT(13012, "There is already another camera in the live stream. It's not support to switch the stream directly."),

    /** 지원하지 않는 URL 타입 */
    URL_TYPE_NOT_SUPPORTED(13013, "This url type is not supported."),

    /** 잘못된 매개변수 */
    ERROR_PARAMETERS(13014, "The live stream parameters are abnormal or incomplete."),

    /** 네트워크 혼잡 */
    NETWORK_CONGESTION(13015, "Please check the network."),

    /** 프레임 오류 */
    ERROR_FRAME(13016, "Live decoding failed."),

    /** 장치 내부 알 수 없는 오류 */
    DEVICE_UNKNOWN(13099, "Unknown error inside the device."),

    /** 알 수 없는 오류 */
    UNKNOWN(-1, "UNKNOWN"),
    ;


    /** 오류 메시지 */
    private final String msg;

    /** 오류 코드 */
    private final int code;

    /**
     * 라이브스트림 오류 코드 열거형 생성자
     * @param code 오류 코드
     * @param msg 오류 메시지
     */
    LiveErrorCodeEnum(int code, String msg) {
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
     * @return 해당하는 LiveErrorCodeEnum 객체, 없으면 UNKNOWN 반환
     */
    public static LiveErrorCodeEnum find(int code) {
        return Arrays.stream(values()).filter(codeEnum -> codeEnum.code == code).findAny().orElse(UNKNOWN);
    }

}
