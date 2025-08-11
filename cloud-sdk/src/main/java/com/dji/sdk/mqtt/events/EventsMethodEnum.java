package com.dji.sdk.mqtt.events;

import com.dji.sdk.cloudapi.airsense.AirsenseWarning;
import com.dji.sdk.cloudapi.control.*;
import com.dji.sdk.cloudapi.debug.RemoteDebugProgress;
import com.dji.sdk.cloudapi.firmware.OtaProgress;
import com.dji.sdk.cloudapi.flightarea.FlightAreasDroneLocation;
import com.dji.sdk.cloudapi.flightarea.FlightAreasSyncProgress;
import com.dji.sdk.cloudapi.hms.Hms;
import com.dji.sdk.cloudapi.interconnection.CustomDataTransmissionFromEsdk;
import com.dji.sdk.cloudapi.interconnection.CustomDataTransmissionFromPsdk;
import com.dji.sdk.cloudapi.log.FileUploadProgress;
import com.dji.sdk.cloudapi.map.OfflineMapSyncProgress;
import com.dji.sdk.cloudapi.media.FileUploadCallback;
import com.dji.sdk.cloudapi.media.HighestPriorityUploadFlightTaskMedia;
import com.dji.sdk.cloudapi.wayline.DeviceExitHomingNotify;
import com.dji.sdk.cloudapi.wayline.FlighttaskProgress;
import com.dji.sdk.cloudapi.wayline.FlighttaskReady;
import com.dji.sdk.cloudapi.wayline.ReturnHomeInfo;
import com.dji.sdk.mqtt.ChannelName;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Arrays;
import java.util.List;

/**
 * MQTT 이벤트 메서드 열거형
 * 다양한 이벤트 타입과 해당하는 채널명, 클래스 타입을 정의
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/1
 */
public enum EventsMethodEnum {

    /** 비행 작업 진행 상황 */
    FLIGHTTASK_PROGRESS("flighttask_progress", ChannelName.INBOUND_EVENTS_FLIGHTTASK_PROGRESS, new TypeReference<EventsDataRequest<FlighttaskProgress>>() {}),

    /** 디바이스 홈 복귀 해제 알림 */
    DEVICE_EXIT_HOMING_NOTIFY("device_exit_homing_notify", ChannelName.INBOUND_EVENTS_DEVICE_EXIT_HOMING_NOTIFY, new TypeReference<DeviceExitHomingNotify>() {}),

    /** 파일 업로드 콜백 */
    FILE_UPLOAD_CALLBACK("file_upload_callback", ChannelName.INBOUND_EVENTS_FILE_UPLOAD_CALLBACK, new TypeReference<FileUploadCallback>() {}),

    /** HMS (Health Management System) */
    HMS("hms", ChannelName.INBOUND_EVENTS_HMS, new TypeReference<Hms>() {}),

    /** 디바이스 재부팅 */
    DEVICE_REBOOT("device_reboot", ChannelName.INBOUND_EVENTS_CONTROL_PROGRESS, new TypeReference<EventsDataRequest<RemoteDebugProgress>>() {}),

    /** 드론 전원 켜기 */
    DRONE_OPEN("drone_open", ChannelName.INBOUND_EVENTS_CONTROL_PROGRESS, new TypeReference<EventsDataRequest<RemoteDebugProgress>>() {}),

    /** 드론 전원 끄기 */
    DRONE_CLOSE("drone_close", ChannelName.INBOUND_EVENTS_CONTROL_PROGRESS, new TypeReference<EventsDataRequest<RemoteDebugProgress>>() {}),

    /** 드론 포맷 */
    DRONE_FORMAT("drone_format", ChannelName.INBOUND_EVENTS_CONTROL_PROGRESS, new TypeReference<EventsDataRequest<RemoteDebugProgress>>() {}),

    /** 디바이스 포맷 */
    DEVICE_FORMAT("device_format", ChannelName.INBOUND_EVENTS_CONTROL_PROGRESS, new TypeReference<EventsDataRequest<RemoteDebugProgress>>() {}),

    /** 커버 열기 */
    COVER_OPEN("cover_open", ChannelName.INBOUND_EVENTS_CONTROL_PROGRESS, new TypeReference<EventsDataRequest<RemoteDebugProgress>>() {}),

    /** 커버 닫기 */
    COVER_CLOSE("cover_close", ChannelName.INBOUND_EVENTS_CONTROL_PROGRESS, new TypeReference<EventsDataRequest<RemoteDebugProgress>>() {}),

    /** 퍼터 열기 */
    PUTTER_OPEN("putter_open", ChannelName.INBOUND_EVENTS_CONTROL_PROGRESS, new TypeReference<EventsDataRequest<RemoteDebugProgress>>() {}),

    /** 퍼터 닫기 */
    PUTTER_CLOSE("putter_close", ChannelName.INBOUND_EVENTS_CONTROL_PROGRESS, new TypeReference<EventsDataRequest<RemoteDebugProgress>>() {}),

    /** 충전 열기 */
    CHARGE_OPEN("charge_open", ChannelName.INBOUND_EVENTS_CONTROL_PROGRESS, new TypeReference<EventsDataRequest<RemoteDebugProgress>>() {}),

    /** 충전 닫기 */
    CHARGE_CLOSE("charge_close", ChannelName.INBOUND_EVENTS_CONTROL_PROGRESS, new TypeReference<EventsDataRequest<RemoteDebugProgress>>() {}),

    /** eSIM 활성화 */
    ESIM_ACTIVATE("esim_activate", ChannelName.INBOUND_EVENTS_CONTROL_PROGRESS, new TypeReference<EventsDataRequest<RemoteDebugProgress>>() {}),

    /** eSIM 운영자 전환 */
    ESIM_OPERATOR_SWITCH("esim_operator_switch", ChannelName.INBOUND_EVENTS_CONTROL_PROGRESS, new TypeReference<EventsDataRequest<RemoteDebugProgress>>() {}),

    /** OTA 진행 상황 */
    OTA_PROGRESS("ota_progress", ChannelName.INBOUND_EVENTS_OTA_PROGRESS, new TypeReference<EventsDataRequest<OtaProgress>>() {}),

    /** 파일 업로드 진행 상황 */
    FILE_UPLOAD_PROGRESS("fileupload_progress", ChannelName.INBOUND_EVENTS_FILEUPLOAD_PROGRESS, new TypeReference<EventsDataRequest<FileUploadProgress>>() {}),

    /** 최우선 업로드 비행 작업 미디어 */
    HIGHEST_PRIORITY_UPLOAD_FLIGHT_TASK_MEDIA("highest_priority_upload_flighttask_media", ChannelName.INBOUND_EVENTS_HIGHEST_PRIORITY_UPLOAD_FLIGHT_TASK_MEDIA, new TypeReference<HighestPriorityUploadFlightTaskMedia>() {}),

    /** 비행 작업 준비 완료 */
    FLIGHT_TASK_READY("flighttask_ready", ChannelName.INBOUND_EVENTS_FLIGHTTASK_READY, new TypeReference<FlighttaskReady>() {}),

    /** 지점 비행 진행 상황 */
    FLY_TO_POINT_PROGRESS("fly_to_point_progress", ChannelName.INBOUND_EVENTS_FLY_TO_POINT_PROGRESS, new TypeReference<FlyToPointProgress>() {}),

    /** 지점 이착륙 진행 상황 */
    TAKE_OFF_TO_POINT_PROGRESS("takeoff_to_point_progress", ChannelName.INBOUND_EVENTS_TAKEOFF_TO_POINT_PROGRESS, new TypeReference<TakeoffToPointProgress>() {}),

    /** DRC 상태 알림 */
    DRC_STATUS_NOTIFY("drc_status_notify", ChannelName.INBOUND_EVENTS_DRC_STATUS_NOTIFY, new TypeReference<DrcStatusNotify>() {}),

    /** 조이스틱 무효 알림 */
    JOYSTICK_INVALID_NOTIFY("joystick_invalid_notify", ChannelName.INBOUND_EVENTS_JOYSTICK_INVALID_NOTIFY, new TypeReference<JoystickInvalidNotify>() {}),

    /** 홈 복귀 정보 */
    RETURN_HOME_INFO("return_home_info", ChannelName.INBOUND_EVENTS_RETURN_HOME_INFO, new TypeReference<ReturnHomeInfo>() {}),

    /** ESDK에서의 사용자 정의 데이터 전송 */
    CUSTOM_DATA_TRANSMISSION_FROM_ESDK("custom_data_transmission_from_esdk", ChannelName.INBOUND_EVENTS_CUSTOM_DATA_TRANSMISSION_FROM_ESDK, new TypeReference<CustomDataTransmissionFromEsdk>() {}),

    /** PSDK에서의 사용자 정의 데이터 전송 */
    CUSTOM_DATA_TRANSMISSION_FROM_PSDK("custom_data_transmission_from_psdk", ChannelName.INBOUND_EVENTS_CUSTOM_DATA_TRANSMISSION_FROM_PSDK, new TypeReference<CustomDataTransmissionFromPsdk>() {}),

    /** AirSense 경고 */
    AIRSENSE_WARNING("airsense_warning", ChannelName.INBOUND_EVENTS_AIRSENSE_WARNING, new TypeReference<List<AirsenseWarning>>() {}),

    /** 비행 구역 동기화 진행 상황 */
    FLIGHT_AREAS_SYNC_PROGRESS("flight_areas_sync_progress", ChannelName.INBOUND_EVENTS_FLIGHT_AREAS_SYNC_PROGRESS, new TypeReference<FlightAreasSyncProgress>() {}),

    /** 비행 구역 드론 위치 */
    FLIGHT_AREAS_DRONE_LOCATION("flight_areas_drone_location", ChannelName.INBOUND_EVENTS_FLIGHT_AREAS_DRONE_LOCATION, new TypeReference<FlightAreasDroneLocation>() {}),

    /** 오프라인 맵 동기화 진행 상황 */
    OFFLINE_MAP_SYNC_PROGRESS("offline_map_sync_progress", ChannelName.INBOUND_EVENTS_OFFLINE_MAP_SYNC_PROGRESS, new TypeReference<OfflineMapSyncProgress>() {}),

    /** POI 상태 알림 */
    POI_STATUS_NOTIFY("poi_status_notify", ChannelName.INBOUND_EVENTS_POI_STATUS_NOTIFY, new TypeReference<PoiStatusNotify>() {}),

    /** 카메라 사진 촬영 진행 상황 */
    CAMERA_PHOTO_TAKE_PROGRESS("camera_photo_take_progress", ChannelName.INBOUND_EVENTS_CAMERA_PHOTO_TAKE_PROGRESS, new TypeReference<EventsDataRequest<CameraPhotoTakeProgress>>() {}),

    /** 알 수 없는 메서드 */
    UNKNOWN("", ChannelName.DEFAULT, new TypeReference<>() {});

    /** 이벤트 메서드명 */
    private final String method;

    /** 해당 이벤트의 채널명 */
    private final String channelName;

    /** 해당 이벤트의 클래스 타입 */
    private final TypeReference classType;

    /**
     * EventsMethodEnum 생성자
     * 
     * @param method 이벤트 메서드명
     * @param channelName 채널명
     * @param classType 클래스 타입
     */
    EventsMethodEnum(String method, String channelName, TypeReference classType) {
        this.method = method;
        this.channelName = channelName;
        this.classType = classType;
    }

    /**
     * 이벤트 메서드명을 반환합니다.
     * @return 이벤트 메서드명
     */
    public String getMethod() {
        return method;
    }

    /**
     * 채널명을 반환합니다.
     * @return 채널명
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 클래스 타입을 반환합니다.
     * @return 클래스 타입
     */
    public TypeReference getClassType() {
        return classType;
    }

    /**
     * 메서드명으로 해당하는 EventsMethodEnum을 찾습니다.
     * 
     * @param method 이벤트 메서드명
     * @return 해당하는 EventsMethodEnum, 없으면 UNKNOWN
     */
    public static EventsMethodEnum find(String method) {
        return Arrays.stream(EventsMethodEnum.values())
                .filter(methodEnum -> methodEnum.method.equals(method))
                .findAny().orElse(UNKNOWN);
    }
}
