package com.dji.sample.component.websocket.model;

/**
 * WebSocket 비즈니스 코드 열거형 클래스
 * 
 * WebSocket을 통해 전송되는 메시지의 비즈니스 타입을 정의합니다.
 * 다양한 디바이스 이벤트, 맵 요소 변경, 파일 업로드 등의 비즈니스 코드를 포함합니다.
 * 
 * @author sean
 * @version 0.1
 * @date 2021/11/26
 */
public enum BizCodeEnum {

    /** 디바이스 온라인 알림 */
    DEVICE_ONLINE("device_online"),

    /** 디바이스 오프라인 알림 */
    DEVICE_OFFLINE("device_offline"),

    /** 디바이스 토폴로지 업데이트 알림 */
    DEVICE_UPDATE_TOPO("device_update_topo"),

    /** 디바이스 OSD 정보 알림 */
    DEVICE_OSD("device_osd"),

    /** RC(Remote Control) OSD 정보 알림 */
    RC_OSD("gateway_osd"),

    /** 도크 OSD 정보 알림 */
    DOCK_OSD("dock_osd"),

    /** 맵 요소 생성 알림 */
    MAP_ELEMENT_CREATE("map_element_create"),

    /** 맵 요소 업데이트 알림 */
    MAP_ELEMENT_UPDATE("map_element_update"),

    /** 맵 요소 삭제 알림 */
    MAP_ELEMENT_DELETE("map_element_delete"),

    /** 맵 그룹 새로고침 알림 */
    MAP_GROUP_REFRESH("map_group_refresh"),

    /** 비행 작업 진행 상황 알림 */
    FLIGHT_TASK_PROGRESS("flighttask_progress"),

    /** 디바이스 HMS(Health Management System) 알림 */
    DEVICE_HMS("device_hms"),

    /** 디바이스 재부팅 알림 */
    DEVICE_REBOOT("device_reboot"),

    /** 드론 열기 알림 */
    DRONE_OPEN("drone_open"),

    /** 드론 닫기 알림 */
    DRONE_CLOSE("drone_close"),

    /** 디바이스 점검 알림 */
    DEVICE_CHECK("device_check"),

    /** 드론 포맷 알림 */
    DRONE_FORMAT("drone_format"),

    /** 디바이스 포맷 알림 */
    DEVICE_FORMAT("device_format"),

    /** 커버 열기 알림 */
    COVER_OPEN("cover_open"),

    /** 커버 닫기 알림 */
    COVER_CLOSE("cover_close"),

    /** 퍼터 열기 알림 */
    PUTTER_OPEN("putter_open"),

    /** 퍼터 닫기 알림 */
    PUTTER_CLOSE("putter_close"),

    /** 충전 열기 알림 */
    CHARGE_OPEN("charge_open"),

    /** 충전 닫기 알림 */
    CHARGE_CLOSE("charge_close"),

    /** 파일 업로드 콜백 알림 */
    FILE_UPLOAD_CALLBACK("file_upload_callback"),

    /** 파일 업로드 진행 상황 알림 */
    FILE_UPLOAD_PROGRESS("fileupload_progress"),

    /** OTA(Over-The-Air) 업데이트 진행 상황 알림 */
    OTA_PROGRESS("ota_progress"),

    /** 최고 우선순위 비행 작업 미디어 업로드 알림 */
    HIGHEST_PRIORITY_UPLOAD_FLIGHT_TASK_MEDIA("highest_priority_upload_flighttask_media"),

    /** 제어 소스 변경 알림 */
    CONTROL_SOURCE_CHANGE("control_source_change"),

    /** 특정 지점으로 비행 진행 상황 알림 */
    FLY_TO_POINT_PROGRESS("fly_to_point_progress"),

    /** 특정 지점으로 이륙 진행 상황 알림 */
    TAKE_OFF_TO_POINT_PROGRESS("takeoff_to_point_progress"),

    /** DRC(Direct Remote Control) 상태 알림 */
    DRC_STATUS_NOTIFY("drc_status_notify"),

    /** 조이스틱 무효 알림 */
    JOYSTICK_INVALID_NOTIFY("joystick_invalid_notify"),

    /** 비행 구역 동기화 진행 상황 알림 */
    FLIGHT_AREAS_SYNC_PROGRESS("flight_areas_sync_progress"),

    /** 비행 구역 드론 위치 알림 */
    FLIGHT_AREAS_DRONE_LOCATION("flight_areas_drone_location"),

    /** 비행 구역 업데이트 알림 */
    FLIGHT_AREAS_UPDATE("flight_areas_update"),

    ;

    /** 비즈니스 코드 문자열 */
    private String code;

    /**
     * 비즈니스 코드 열거형 생성자
     * 
     * @param code 비즈니스 코드 문자열
     */
    BizCodeEnum(String code) {
        this.code = code;
    }

    /**
     * 비즈니스 코드를 반환합니다.
     * 
     * @return 비즈니스 코드 문자열
     */
    public String getCode() {
        return code;
    }
}
