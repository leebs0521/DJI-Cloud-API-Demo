package com.dji.sdk.cloudapi.control;

/**
 * 제어 메서드 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 지원하는 모든 제어 메서드를 정의합니다.
 * 비행 제어, 페이로드 제어, 카메라 제어, DRC 모드 등 다양한 제어 기능을 포함합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/2
 */
public enum ControlMethodEnum {

    /**
     * 비행 권한 획득
     * 드론의 비행 제어 권한을 획득
     */
    FLIGHT_AUTHORITY_GRAB("flight_authority_grab"),

    /**
     * 페이로드 권한 획득
     * 카메라, 짐벌 등 페이로드의 제어 권한을 획득
     */
    PAYLOAD_AUTHORITY_GRAB("payload_authority_grab"),

    /**
     * DRC 모드 진입
     * Direct Remote Control 모드로 진입
     */
    DRC_MODE_ENTER("drc_mode_enter"),

    /**
     * DRC 모드 종료
     * Direct Remote Control 모드에서 종료
     */
    DRC_MODE_EXIT("drc_mode_exit"),

    /**
     * 지정 지점으로 비행
     * 특정 좌표로 드론을 비행시킴
     */
    FLY_TO_POINT("fly_to_point"),

    /**
     * 지정 지점 비행 중지
     * 진행 중인 지정 지점 비행을 중지
     */
    FLY_TO_POINT_STOP("fly_to_point_stop"),

    /**
     * 지정 지점 비행 업데이트
     * 진행 중인 지정 지점 비행의 목적지를 업데이트
     */
    FLY_TO_POINT_UPDATE("fly_to_point_update"),

    /**
     * 지정 지점으로 이륙
     * 특정 좌표로 이륙하여 비행
     */
    TAKEOFF_TO_POINT("takeoff_to_point"),

    /**
     * 카메라 모드 전환
     * 카메라의 촬영 모드를 전환
     */
    CAMERA_MODE_SWITCH("camera_mode_switch"),

    /**
     * 카메라 사진 촬영
     * 카메라로 사진을 촬영
     */
    CAMERA_PHOTO_TAKE("camera_photo_take"),

    /**
     * 카메라 사진 촬영 중지
     * 진행 중인 사진 촬영을 중지
     */
    CAMERA_PHOTO_STOP("camera_photo_stop"),

    /**
     * 카메라 녹화 시작
     * 카메라로 비디오 녹화를 시작
     */
    CAMERA_RECORDING_START("camera_recording_start"),

    /**
     * 카메라 녹화 중지
     * 진행 중인 비디오 녹화를 중지
     */
    CAMERA_RECORDING_STOP("camera_recording_stop"),

    /**
     * 카메라 조준
     * 카메라를 특정 지점에 조준
     */
    CAMERA_AIM("camera_aim"),

    /**
     * 카메라 초점 거리 설정
     * 카메라의 줌 레벨을 설정
     */
    CAMERA_FOCAL_LENGTH_SET("camera_focal_length_set"),

    /**
     * 짐벌 리셋
     * 짐벌을 기본 위치로 리셋
     */
    GIMBAL_RESET("gimbal_reset"),

    /**
     * 카메라 특정 지점 바라보기
     * 카메라가 특정 지점을 바라보도록 설정
     */
    CAMERA_LOOK_AT("camera_look_at"),

    /**
     * 카메라 화면 분할
     * 카메라 화면을 분할하여 표시
     */
    CAMERA_SCREEN_SPLIT("camera_screen_split"),

    /**
     * 사진 저장 설정
     * 사진 저장 방식을 설정
     */
    PHOTO_STORAGE_SET("photo_storage_set"),

    /**
     * 비디오 저장 설정
     * 비디오 저장 방식을 설정
     */
    VIDEO_STORAGE_SET("video_storage_set"),

    /**
     * 카메라 노출 설정
     * 카메라의 노출 값을 설정
     */
    CAMERA_EXPOSURE_SET("camera_exposure_set"),

    /**
     * 카메라 노출 모드 설정
     * 카메라의 노출 모드를 설정
     */
    CAMERA_EXPOSURE_MODE_SET("camera_exposure_mode_set"),

    /**
     * 카메라 초점 모드 설정
     * 카메라의 초점 모드를 설정
     */
    CAMERA_FOCUS_MODE_SET("camera_focus_mode_set"),

    /**
     * 카메라 초점 값 설정
     * 카메라의 초점 값을 설정
     */
    CAMERA_FOCUS_VALUE_SET("camera_focus_value_set"),

    /**
     * IR 카메라 측광 모드 설정
     * 적외선 카메라의 측광 모드를 설정
     */
    IR_METERING_MODE_SET("ir_metering_mode_set"),

    /**
     * IR 카메라 측광 포인트 설정
     * 적외선 카메라의 측광 포인트를 설정
     */
    IR_METERING_POINT_SET("ir_metering_point_set"),

    /**
     * IR 카메라 측광 영역 설정
     * 적외선 카메라의 측광 영역을 설정
     */
    IR_METERING_AREA_SET("ir_metering_area_set"),

    /**
     * 카메라 포인트 초점 액션
     * 카메라의 특정 지점 초점 기능 실행
     */
    CAMERA_POINT_FOCUS_ACTION("camera_point_focus_action"),

    /**
     * 드론 제어
     * 드론의 직접 제어 (조이스틱 입력)
     */
    DRONE_CONTROL("drone_control"),

    /**
     * 드론 비상 정지
     * 드론을 즉시 정지시킴
     */
    DRONE_EMERGENCY_STOP("drone_emergency_stop"),

    /**
     * 하트비트
     * 연결 상태 확인을 위한 하트비트
     */
    HEART_BEAT("heart_beat"),

    /**
     * POI 모드 진입
     * Point of Interest 모드로 진입
     */
    POI_MODE_ENTER("poi_mode_enter"),

    /**
     * POI 모드 종료
     * Point of Interest 모드에서 종료
     */
    POI_MODE_EXIT("poi_mode_exit"),

    /**
     * POI 원형 비행 속도 설정
     * POI 모드에서 원형 비행 속도를 설정
     */
    POI_CIRCLE_SPEED_SET("poi_circle_speed_set"),

    ;

    /**
     * 제어 메서드 문자열
     */
    private final String method;

    /**
     * 제어 메서드 열거형 생성자
     * 
     * @param method 제어 메서드 문자열
     */
    ControlMethodEnum(String method) {
        this.method = method;
    }

    /**
     * 제어 메서드 문자열을 반환합니다.
     * 
     * @return 제어 메서드 문자열
     */
    public String getMethod() {
        return method;
    }

}
