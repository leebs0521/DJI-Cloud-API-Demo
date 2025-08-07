package com.dji.sdk.cloudapi.control;

import com.dji.sdk.common.IErrorInfo;
import com.dji.sdk.mqtt.events.IEventsErrorCode;
import com.dji.sdk.mqtt.services.IServicesErrorCode;

import java.util.Arrays;

/**
 * 제어 오류 코드 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 발생할 수 있는 모든 제어 관련 오류 코드를 정의합니다.
 * 비행 제어, 카메라 제어, DRC 모드 등 다양한 제어 기능에서 발생하는 오류를 포함합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/25
 */
public enum ControlErrorCodeEnum implements IServicesErrorCode, IEventsErrorCode, IErrorInfo {

    /**
     * RTH 설정 실패
     * 자동 복귀 고도 설정에 실패
     */
    SETTING_RTH_FAILED(327000, "Height of return to home setting failed."),

    /**
     * 신호 손실 액션 설정 실패
     * 신호 손실 시 동작 설정에 실패
     */
    SETTING_LOST_ACTION_FAILED(327001, "Signal lost action setting failed."),

    /**
     * 제어 권한 획득 실패
     * 드론 제어 권한을 획득하는데 실패
     */
    OBTAIN_CONTROL_FAILED(327002, "Failed to obtain control."),

    /**
     * 디바이스 오프라인
     * 제어 권한 획득 실패 - 디바이스가 오프라인 상태
     */
    DEVICE_OFFLINE(327003, "Failed to obtain control. Device offline."),

    /**
     * 라이브스트림 뷰 드래그 실패
     * 라이브스트림 화면 드래그에 실패
     */
    DRAG_LIVESTREAM_VIEW_FAILED(327004, "Failed to drag livestream view."),

    /**
     * 조준 실패
     * 더블 탭으로 조준하는데 실패
     */
    AIM_FAILED(327005, "Failed to double tab to be AIM."),

    /**
     * 사진 촬영 실패
     * 사진 촬영에 실패
     */
    TAKE_PHOTO_FAILED(327006, "Failed to take photo."),

    /**
     * 녹화 시작 실패
     * 비디오 녹화 시작에 실패
     */
    START_RECORDING_FAILED(327007, "Failed to start recording."),

    /**
     * 녹화 중지 실패
     * 비디오 녹화 중지에 실패
     */
    STOP_RECORDING_FAILED(327008, "Failed to stop recording."),

    /**
     * 카메라 모드 전환 실패
     * 카메라 모드 전환에 실패
     */
    SWITCH_CAMERA_MODE_FAILED(327009, "Failed to switch camera modes."),

    /**
     * 줌 카메라 줌 실패
     * 줌 카메라의 줌 인/아웃에 실패
     */
    ZOOM_CAMERA_ZOOM_FAILED(327010, "Failed to zoom in/out with zoom camera."),

    /**
     * IR 카메라 줌 실패
     * 적외선 카메라의 줌 인/아웃에 실패
     */
    IR_CAMERA_ZOOM_FAILED(327011, "Failed to zoom in/out with IR camera."),

    /**
     * 디바이스 잠금
     * 제어 권한 획득 실패 - 디바이스가 잠금 상태
     */
    DEVICE_LOCK(327012, "Failed to obtain control. Device is locked."),

    /**
     * 웨이라인 신호 손실 액션 설정 실패
     * 웨이라인 비행 중 신호 손실 시 동작 설정에 실패
     */
    SETTING_WAYLINE_LOST_ACTION_FAILED(327013, "Wayline signal lost action setting failed."),

    /**
     * 짐벌 한계 도달
     * 짐벌이 움직임 한계에 도달
     */
    GIMBAL_REACH_LIMIT(327014, "Gimbal reached movement limit."),

    /**
     * 잘못된 렌즈 타입
     * 유효하지 않은 카메라 렌즈 타입
     */
    WRONG_LENS_TYPE(327015, "Invalid camera lens type."),


    /**
     * DRC 이상
     * Direct Remote Control 모드에서 이상 발생
     */
    DRC_ABNORMAL(514300, "DRC abnormal."),

    /**
     * DRC 하트비트 타임아웃
     * DRC 하트비트 응답 시간 초과
     */
    DRC_HEARTBEAT_TIMED_OUT(514301, "DRC heartbeat timed out."),

    /**
     * DRC 인증서 이상
     * DRC 인증서에 문제 발생
     */
    DRC_CERTIFICATE_ABNORMAL(514302, "DRC certificate is abnormal."),

    /**
     * DRC 링크 손실
     * DRC 연결이 끊어짐
     */
    DRC_LINK_LOST(514303, "DRC link is lost."),

    /**
     * DRC 링크 거부
     * DRC 연결이 거부됨
     */
    DRC_LINK_REFUSED(514304, "DRC link is refused."),

    /**
     * 알 수 없는 오류
     * 정의되지 않은 오류
     */
    UNKNOWN(-1, "UNKNOWN"),

    ;


    /**
     * 오류 메시지
     */
    private final String msg;

    /**
     * 오류 코드
     */
    private final int code;

    /**
     * 제어 오류 코드 열거형 생성자
     * 
     * @param code 오류 코드
     * @param msg 오류 메시지
     */
    ControlErrorCodeEnum(int code, String msg) {
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
     * 오류 코드로 열거형 객체를 찾습니다.
     * 
     * @param code 찾을 오류 코드
     * @return 찾은 열거형 객체, 없으면 UNKNOWN
     */
    public static ControlErrorCodeEnum find(int code) {
        return Arrays.stream(values()).filter(codeEnum -> codeEnum.code == code).findAny().orElse(UNKNOWN);
    }

}
