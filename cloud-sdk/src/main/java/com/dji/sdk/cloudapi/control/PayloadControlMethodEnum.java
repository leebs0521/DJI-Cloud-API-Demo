package com.dji.sdk.cloudapi.control;

import com.dji.sdk.common.BaseModel;
import com.dji.sdk.exception.CloudSDKException;

import java.util.Arrays;

/**
 * 페이로드 제어 메서드 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 페이로드(카메라, 짐벌 등) 제어 메서드와 해당 요청 클래스를 매핑합니다.
 * 각 제어 메서드에 대응하는 요청 클래스를 정의하여 타입 안전성을 보장합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/2
 */
public enum PayloadControlMethodEnum {

    /**
     * 카메라 모드 전환
     * 카메라의 촬영 모드를 전환하는 제어
     */
    CAMERA_MODE_SWITCH(ControlMethodEnum.CAMERA_MODE_SWITCH, CameraModeSwitchRequest.class),

    /**
     * 카메라 사진 촬영
     * 카메라로 사진을 촬영하는 제어
     */
    CAMERA_PHOTO_TAKE(ControlMethodEnum.CAMERA_PHOTO_TAKE, CameraPhotoTakeRequest.class),

    /**
     * 카메라 사진 촬영 중지
     * 진행 중인 사진 촬영을 중지하는 제어
     */
    CAMERA_PHOTO_STOP(ControlMethodEnum.CAMERA_PHOTO_STOP, CameraPhotoStopRequest.class),

    /**
     * 카메라 녹화 시작
     * 카메라로 비디오 녹화를 시작하는 제어
     */
    CAMERA_RECORDING_START(ControlMethodEnum.CAMERA_RECORDING_START, CameraRecordingStartRequest.class),

    /**
     * 카메라 녹화 중지
     * 진행 중인 비디오 녹화를 중지하는 제어
     */
    CAMERA_RECORDING_STOP(ControlMethodEnum.CAMERA_RECORDING_STOP, CameraRecordingStopRequest.class),

    /**
     * 카메라 조준
     * 카메라를 특정 지점에 조준하는 제어
     */
    CAMERA_AIM(ControlMethodEnum.CAMERA_AIM, CameraAimRequest.class),

    /**
     * 카메라 초점 거리 설정
     * 카메라의 줌 레벨을 설정하는 제어
     */
    CAMERA_FOCAL_LENGTH_SET(ControlMethodEnum.CAMERA_FOCAL_LENGTH_SET, CameraFocalLengthSetRequest.class),

    /**
     * 짐벌 리셋
     * 짐벌을 기본 위치로 리셋하는 제어
     */
    GIMBAL_RESET(ControlMethodEnum.GIMBAL_RESET, GimbalResetRequest.class),

    /**
     * 카메라 특정 지점 바라보기
     * 카메라가 특정 지점을 바라보도록 설정하는 제어
     */
    CAMERA_LOOK_AT(ControlMethodEnum.CAMERA_LOOK_AT, CameraLookAtRequest.class),

    /**
     * 카메라 화면 분할
     * 카메라 화면을 분할하여 표시하는 제어
     */
    CAMERA_SCREEN_SPLIT(ControlMethodEnum.CAMERA_SCREEN_SPLIT, CameraScreenSplitRequest.class),

    /**
     * 사진 저장 설정
     * 사진 저장 방식을 설정하는 제어
     */
    PHOTO_STORAGE_SET(ControlMethodEnum.PHOTO_STORAGE_SET, PhotoStorageSetRequest.class),

    /**
     * 비디오 저장 설정
     * 비디오 저장 방식을 설정하는 제어
     */
    VIDEO_STORAGE_SET(ControlMethodEnum.VIDEO_STORAGE_SET, VideoStorageSetRequest.class),

    /**
     * 카메라 노출 설정
     * 카메라의 노출 값을 설정하는 제어
     */
    CAMERA_EXPOSURE_SET(ControlMethodEnum.CAMERA_EXPOSURE_SET, CameraExposureSetRequest.class),

    /**
     * 카메라 노출 모드 설정
     * 카메라의 노출 모드를 설정하는 제어
     */
    CAMERA_EXPOSURE_MODE_SET(ControlMethodEnum.CAMERA_EXPOSURE_MODE_SET, CameraExposureModeSetRequest.class),

    /**
     * 카메라 초점 모드 설정
     * 카메라의 초점 모드를 설정하는 제어
     */
    CAMERA_FOCUS_MODE_SET(ControlMethodEnum.CAMERA_FOCUS_MODE_SET, CameraFocusModeSetRequest.class),

    /**
     * 카메라 초점 값 설정
     * 카메라의 초점 값을 설정하는 제어
     */
    CAMERA_FOCUS_VALUE_SET(ControlMethodEnum.CAMERA_FOCUS_VALUE_SET, CameraFocusValueSetRequest.class),

    /**
     * IR 카메라 측광 모드 설정
     * 적외선 카메라의 측광 모드를 설정하는 제어
     */
    IR_METERING_MODE_SET(ControlMethodEnum.IR_METERING_MODE_SET, IrMeteringModeSetRequest.class),

    /**
     * IR 카메라 측광 포인트 설정
     * 적외선 카메라의 측광 포인트를 설정하는 제어
     */
    IR_METERING_POINT_SET(ControlMethodEnum.IR_METERING_POINT_SET, IrMeteringPointSetRequest.class),

    /**
     * IR 카메라 측광 영역 설정
     * 적외선 카메라의 측광 영역을 설정하는 제어
     */
    IR_METERING_AREA_SET(ControlMethodEnum.IR_METERING_AREA_SET, IrMeteringAreaSetRequest.class),

    /**
     * 카메라 포인트 초점 액션
     * 카메라의 특정 지점 초점 기능을 실행하는 제어
     */
    CAMERA_POINT_FOCUS_ACTION(ControlMethodEnum.CAMERA_POINT_FOCUS_ACTION, CameraPointFocusActionRequest.class),

    ;

    /**
     * 페이로드 제어 메서드
     */
    private final ControlMethodEnum payloadMethod;

    /**
     * 해당 제어 메서드의 요청 클래스
     */
    private final Class<? extends BaseModel> clazz;

    /**
     * 페이로드 제어 메서드 열거형 생성자
     * 
     * @param payloadMethod 페이로드 제어 메서드
     * @param clazz 해당 제어 메서드의 요청 클래스
     */
    PayloadControlMethodEnum(ControlMethodEnum payloadMethod, Class<? extends BaseModel> clazz) {
        this.payloadMethod = payloadMethod;
        this.clazz = clazz;
    }

    /**
     * 페이로드 제어 메서드를 반환합니다.
     * 
     * @return 페이로드 제어 메서드
     */
    public ControlMethodEnum getPayloadMethod() {
        return payloadMethod;
    }

    /**
     * 해당 제어 메서드의 요청 클래스를 반환합니다.
     * 
     * @return 요청 클래스
     */
    public Class<? extends BaseModel> getClazz() {
        return clazz;
    }

    /**
     * 문자열로 페이로드 제어 메서드를 찾습니다.
     * 
     * @param method 찾을 제어 메서드 문자열
     * @return 찾은 페이로드 제어 메서드 열거형
     * @throws CloudSDKException 해당하는 제어 메서드를 찾을 수 없는 경우
     */
    public static PayloadControlMethodEnum find(String method) {
        return Arrays.stream(values()).filter(methodEnum -> methodEnum.payloadMethod.getMethod().equals(method)).findAny()
            .orElseThrow(() -> new CloudSDKException(PayloadControlMethodEnum.class, method));
    }
}
