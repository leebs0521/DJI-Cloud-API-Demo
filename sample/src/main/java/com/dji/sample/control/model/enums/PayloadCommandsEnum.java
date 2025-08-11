package com.dji.sample.control.model.enums;

import com.dji.sample.control.service.impl.*;
import com.dji.sdk.cloudapi.control.PayloadControlMethodEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 페이로드 명령 열거형 클래스
 * 
 * 페이로드 제어를 위한 명령을 정의하는 열거형입니다.
 * 카메라 제어, 짐벌 제어 등의 명령과 해당 핸들러 클래스를 매핑합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/2
 */
public enum PayloadCommandsEnum {

    /** 카메라 모드 전환 */
    CAMERA_MODE_SWitCH(PayloadControlMethodEnum.CAMERA_MODE_SWITCH, CameraModeSwitchImpl.class),

    /** 카메라 사진 촬영 */
    CAMERA_PHOTO_TAKE(PayloadControlMethodEnum.CAMERA_PHOTO_TAKE, CameraPhotoTakeImpl.class),

    /** 카메라 녹화 시작 */
    CAMERA_RECORDING_START(PayloadControlMethodEnum.CAMERA_RECORDING_START, CameraRecordingStartImpl.class),

    /** 카메라 녹화 중지 */
    CAMERA_RECORDING_STOP(PayloadControlMethodEnum.CAMERA_RECORDING_STOP, CameraRecordingStopImpl.class),

    /** 카메라 조준 */
    CAMERA_AIM(PayloadControlMethodEnum.CAMERA_AIM, CameraAimImpl.class),

    /** 카메라 초점 거리 설정 */
    CAMERA_FOCAL_LENGTH_SET(PayloadControlMethodEnum.CAMERA_FOCAL_LENGTH_SET, CameraFocalLengthSetImpl.class),

    /** 짐벌 리셋 */
    GIMBAL_RESET(PayloadControlMethodEnum.GIMBAL_RESET, GimbalResetImpl.class);

    /** 페이로드 제어 메서드 */
    PayloadControlMethodEnum cmd;

    /** 페이로드 명령 핸들러 클래스 */
    Class<? extends PayloadCommandsHandler> clazz;

    /**
     * 페이로드 명령 열거형 생성자
     * 
     * @param cmd 페이로드 제어 메서드
     * @param clazz 페이로드 명령 핸들러 클래스
     */
    PayloadCommandsEnum(PayloadControlMethodEnum cmd, Class<? extends PayloadCommandsHandler> clazz) {
        this.cmd = cmd;
        this.clazz = clazz;
    }

    /**
     * JSON 직렬화를 위한 메서드 이름 반환
     * 
     * @return 페이로드 메서드 이름
     */
    @JsonValue
    public String getMethod() {
        return cmd.getPayloadMethod().getMethod();
    }

    /**
     * 페이로드 명령 핸들러 클래스를 반환합니다.
     * 
     * @return 페이로드 명령 핸들러 클래스
     */
    public Class<? extends PayloadCommandsHandler> getClazz() {
        return clazz;
    }

    /**
     * 페이로드 제어 메서드를 반환합니다.
     * 
     * @return 페이로드 제어 메서드
     */
    public PayloadControlMethodEnum getCmd() {
        return cmd;
    }

    /**
     * JSON 역직렬화를 위한 열거형 찾기
     * 
     * @param method 페이로드 메서드 이름
     * @return 해당하는 페이로드 명령 열거형
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static PayloadCommandsEnum find(String method) {
        return Arrays.stream(values()).filter(methodEnum -> methodEnum.cmd.getPayloadMethod().getMethod().equals(method)).findAny()
                .orElseThrow();
    }
}
