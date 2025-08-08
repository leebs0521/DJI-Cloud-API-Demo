package com.dji.sdk.mqtt;

/**
 * Spring Integration 채널 이름 상수 클래스
 * 
 * MQTT 메시지 처리를 위한 모든 채널 이름을 정의합니다.
 * 각 채널은 특정 타입의 메시지를 처리하는 역할을 담당합니다.
 * 
 * 주요 구성 요소:
 * - 기본 채널: INBOUND, OUTBOUND, DEFAULT
 * - 상태 관련 채널: STATUS, STATE 관련 채널들
 * - 서비스 관련 채널: SERVICES_REPLY
 * - OSD 관련 채널: OSD 관련 채널들
 * - 요청 관련 채널: REQUESTS 관련 채널들
 * - 이벤트 관련 채널: EVENTS 관련 채널들
 * - 속성 관련 채널: PROPERTY_SET_REPLY
 * - DRC 관련 채널: DRC_UP 관련 채널들
 * 
 * 이 클래스는 MQTT 메시지 라우팅을 위한
 * 채널 이름의 표준화를 제공합니다.
 *
 * @author sean.zhou
 * @date 2021/11/10
 * @version 0.1
 */
public class ChannelName {

    /**
     * 기본 채널들
     */
    /** MQTT 브로커로부터 수신된 메시지를 처리하는 채널 */
    public static final String INBOUND = "inbound";
    /** 처리되지 않은 메시지를 로깅하는 기본 채널 */
    public static final String DEFAULT = "default";
    /** MQTT 브로커로 전송할 메시지를 처리하는 채널 */
    public static final String OUTBOUND = "outbound";

    /**
     * 상태 관련 채널들
     */
    /** 디바이스 온라인/오프라인 상태 정보를 처리하는 채널 */
    public static final String INBOUND_STATUS = "inboundStatus";
    /** 아웃바운드 상태 정보를 처리하는 채널 */
    public static final String OUTBOUND_STATUS = "outboundStatus";
    /** 디바이스 온라인 상태 알림을 처리하는 채널 */
    public static final String INBOUND_STATUS_ONLINE = "inboundStatusOnline";
    /** 디바이스 오프라인 상태 알림을 처리하는 채널 */
    public static final String INBOUND_STATUS_OFFLINE = "inboundStatusOffline";

    /**
     * 상태 정보 관련 채널들
     */
    /** 디바이스 상태 정보를 처리하는 채널 */
    public static final String INBOUND_STATE = "inboundState";
    /** RC 제어 소스 정보를 처리하는 채널 */
    public static final String INBOUND_STATE_RC_CONTROL_SOURCE = "inboundStateRcControlSource";
    /** 도크 드론 제어 소스 정보를 처리하는 채널 */
    public static final String INBOUND_STATE_DOCK_DRONE_CONTROL_SOURCE = "inboundStateDockControlSource";
    /** RC 라이브스트림 능력 업데이트를 처리하는 채널 */
    public static final String INBOUND_STATE_RC_LIVESTREAM_ABILITY_UPDATE = "inboundStateRcLiveCapacity";
    /** 도크 라이브스트림 능력 업데이트를 처리하는 채널 */
    public static final String INBOUND_STATE_DOCK_LIVESTREAM_ABILITY_UPDATE = "inboundStateDockLiveCapacity";
    /** RC 라이브 상태를 처리하는 채널 */
    public static final String INBOUND_STATE_RC_LIVE_STATUS = "inboundStateRcLiveStatus";
    /** 도크 라이브 상태를 처리하는 채널 */
    public static final String INBOUND_STATE_DOCK_LIVE_STATUS = "inboundStateDockLiveStatus";
    /** RC 및 드론 펌웨어 버전 정보를 처리하는 채널 */
    public static final String INBOUND_STATE_RC_AND_DRONE_FIRMWARE_VERSION = "inboundStateRcAndDroneFirmwareVersion";
    /** 도크 펌웨어 버전 정보를 처리하는 채널 */
    public static final String INBOUND_STATE_DOCK_FIRMWARE_VERSION = "inboundStateDockFirmwareVersion";
    /** RC 페이로드 펌웨어 정보를 처리하는 채널 */
    public static final String INBOUND_STATE_RC_PAYLOAD_FIRMWARE = "inboundStateRcPayloadFirmware";
    /** 도크 드론 WPMZ 버전 정보를 처리하는 채널 */
    public static final String INBOUND_STATE_DOCK_DRONE_WPMZ_VERSION = "inboundStateDockDroneWpmzVersion";
    /** 도크 드론 열화상 지원 팔레트 스타일 정보를 처리하는 채널 */
    public static final String INBOUND_STATE_DOCK_DRONE_THERMAL_SUPPORTED_PALETTE_STYLE = "inboundStateDockDronePayload";
    /** 도크 드론 RTH 모드 정보를 처리하는 채널 */
    public static final String INBOUND_STATE_DOCK_DRONE_RTH_MODE = "inboundStateDockDroneRthMode";
    /** 도크 드론 현재 RTH 모드 정보를 처리하는 채널 */
    public static final String INBOUND_STATE_DOCK_DRONE_CURRENT_RTH_MODE = "inboundStateDockDroneCurrentRthMode";
    /** 도크 드론 커맨더 모드 손실 액션 정보를 처리하는 채널 */
    public static final String INBOUND_STATE_DOCK_DRONE_COMMANDER_MODE_LOST_ACTION = "inboundStateDockDroneCommanderModeLostAction";
    /** 도크 드론 현재 커맨더 비행 모드 정보를 처리하는 채널 */
    public static final String INBOUND_STATE_DOCK_DRONE_CURRENT_COMMANDER_FLIGHT_MODE = "inboundStateDockDroneCurrentCommanderFlightMode";
    /** 도크 드론 커맨더 비행 높이 정보를 처리하는 채널 */
    public static final String INBOUND_STATE_DOCK_DRONE_COMMANDER_FLIGHT_HEIGHT = "inboundStateDockDroneCommanderFlightHeight";
    /** 도크 드론 모드 코드 이유 정보를 처리하는 채널 */
    public static final String INBOUND_STATE_DOCK_DRONE_MODE_CODE_REASON = "inboundStateDockDroneModeCodeReason";
    /** 도크 드론 오프라인 맵 활성화 정보를 처리하는 채널 */
    public static final String INBOUND_STATE_DOCK_DRONE_OFFLINE_MAP_ENABLE = "inboundStateDockDroneOfflineMapEnable";
    /** 도크 및 드론 동글 정보를 처리하는 채널 */
    public static final String INBOUND_STATE_DOCK_AND_DRONE_DONGLE_INFOS = "inboundStateDockAndDroneDongleInfos";
    /** 도크 무음 모드 정보를 처리하는 채널 */
    public static final String INBOUND_STATE_DOCK_SILENT_MODE = "inboundStateDockSilentMode";

    /** 아웃바운드 상태 정보를 처리하는 채널 */
    public static final String OUTBOUND_STATE = "outboundState";

    /**
     * 서비스 응답 관련 채널들
     */
    /** 서비스 응답을 처리하는 채널 */
    public static final String INBOUND_SERVICES_REPLY = "inboundServicesReply";

    /**
     * OSD (On-Screen Display) 관련 채널들
     */
    /** OSD 정보를 처리하는 채널 */
    public static final String INBOUND_OSD = "inboundOsd";
    /** RC OSD 정보를 처리하는 채널 */
    public static final String INBOUND_OSD_RC = "inboundOsdRc";
    /** 도크 OSD 정보를 처리하는 채널 */
    public static final String INBOUND_OSD_DOCK = "inboundOsdDock";
    /** RC 드론 OSD 정보를 처리하는 채널 */
    public static final String INBOUND_OSD_RC_DRONE = "inboundOsdRcDrone";
    /** 도크 드론 OSD 정보를 처리하는 채널 */
    public static final String INBOUND_OSD_DOCK_DRONE = "inboundOsdDockDrone";

    /**
     * 요청 관련 채널들
     */
    /** 요청을 처리하는 채널 */
    public static final String INBOUND_REQUESTS = "inboundRequests";
    /** 스토리지 설정 조회 요청을 처리하는 채널 */
    public static final String INBOUND_REQUESTS_STORAGE_CONFIG_GET = "inboundRequestsStorageConfigGet";
    /** 공항 바인딩 상태 요청을 처리하는 채널 */
    public static final String INBOUND_REQUESTS_AIRPORT_BIND_STATUS = "inboundRequestsAirportBindStatus";
    /** 공항 조직 조회 요청을 처리하는 채널 */
    public static final String INBOUND_REQUESTS_AIRPORT_ORGANIZATION_GET = "inboundRequestsAirportOrganizationGet";
    /** 공항 조직 바인딩 요청을 처리하는 채널 */
    public static final String INBOUND_REQUESTS_AIRPORT_ORGANIZATION_BIND = "inboundRequestsAirportOrganizationBind";
    /** 설정 요청을 처리하는 채널 */
    public static final String INBOUND_REQUESTS_CONFIG = "inboundRequestsConfig";
    /** 비행 작업 리소스 조회 요청을 처리하는 채널 */
    public static final String INBOUND_REQUESTS_FLIGHTTASK_RESOURCE_GET = "inboundRequestsFlightTaskResourceGet";
    /** 비행 구역 조회 요청을 처리하는 채널 */
    public static final String INBOUND_REQUESTS_FLIGHT_AREAS_GET = "inboundRequestsFlightAreasGet";
    /** 오프라인 맵 조회 요청을 처리하는 채널 */
    public static final String INBOUND_REQUESTS_OFFLINE_MAP_GET = "inboundRequestsOfflineMapGet";

    /** 아웃바운드 요청을 처리하는 채널 */
    public static final String OUTBOUND_REQUESTS = "outboundRequests";

    /**
     * 이벤트 관련 채널들
     */
    /** 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS = "inboundEvents";
    /** 아웃바운드 이벤트를 처리하는 채널 */
    public static final String OUTBOUND_EVENTS = "outboundEvents";
    /** 디바이스 홈 복귀 종료 알림 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_DEVICE_EXIT_HOMING_NOTIFY = "inboundEventsDeviceExitHomingNotify";
    /** 비행 작업 진행 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_FLIGHTTASK_PROGRESS = "inboundEventsFlighttaskProgress";
    /** 비행 작업 준비 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_FLIGHTTASK_READY = "inboundEventsFlighttaskReady";
    /** 파일 업로드 콜백 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_FILE_UPLOAD_CALLBACK = "inboundEventsFileUploadCallback";
    /** HMS (Health Management System) 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_HMS = "inboundEventsHms";
    /** 제어 진행 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_CONTROL_PROGRESS = "inboundEventsControlProgress";
    /** OTA 진행 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_OTA_PROGRESS = "inboundEventsOtaProgress";
    /** 파일 업로드 진행 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_FILEUPLOAD_PROGRESS = "inboundEventsFileUploadProgress";
    /** 특정 지점으로 비행 진행 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_FLY_TO_POINT_PROGRESS = "inboundEventsFlyToPointProgress";
    /** 특정 지점으로 이륙 진행 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_TAKEOFF_TO_POINT_PROGRESS = "inboundEventsTakeoffToPointProgress";
    /** DRC 상태 알림 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_DRC_STATUS_NOTIFY = "inboundEventsDrcStatusNotify";
    /** 조이스틱 무효 알림 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_JOYSTICK_INVALID_NOTIFY = "inboundEventsJoystickInvalidNotify";
    /** 최우선 업로드 비행 작업 미디어 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_HIGHEST_PRIORITY_UPLOAD_FLIGHT_TASK_MEDIA = "inboundEventsHighestPriorityUploadFlightTaskMedia";
    /** 홈 복귀 정보 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_RETURN_HOME_INFO = "inboundEventsReturnHomeInfo";
    /** ESDK에서 사용자 정의 데이터 전송 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_CUSTOM_DATA_TRANSMISSION_FROM_ESDK = "inboundEventsCustomDataTransmissionFromEsdk";
    /** PSDK에서 사용자 정의 데이터 전송 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_CUSTOM_DATA_TRANSMISSION_FROM_PSDK = "inboundEventsCustomDataTransmissionFromPsdk";
    /** AirSense 경고 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_AIRSENSE_WARNING = "inboundEventsAirsenseWarning";
    /** 비행 구역 동기화 진행 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_FLIGHT_AREAS_SYNC_PROGRESS = "inboundEventsFlightAreasSyncProgress";
    /** 비행 구역 드론 위치 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_FLIGHT_AREAS_DRONE_LOCATION = "inboundEventsFlightAreasDroneLocation";
    /** 오프라인 맵 동기화 진행 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_OFFLINE_MAP_SYNC_PROGRESS = "inboundEventsOfflineMapSyncProgress";
    /** POI 상태 알림 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_POI_STATUS_NOTIFY = "inboundEventsPoiStatusNotify";
    /** 카메라 사진 촬영 진행 이벤트를 처리하는 채널 */
    public static final String INBOUND_EVENTS_CAMERA_PHOTO_TAKE_PROGRESS = "inboundEventsCameraPhotoTakeProgress";

    /**
     * 속성 관련 채널들
     */
    /** 속성 설정 응답을 처리하는 채널 */
    public static final String INBOUND_PROPERTY_SET_REPLY = "inboundPropertySetReply";

    /**
     * DRC 업로드 관련 채널들
     */
    /** DRC 업로드를 처리하는 채널 */
    public static final String INBOUND_DRC_UP = "inboundDrcUp";
    /** DRC 드론 제어 업로드를 처리하는 채널 */
    public static final String INBOUND_DRC_UP_DRONE_CONTROL = "inboundDrcUpDroneControl";
    /** DRC 드론 비상 정지 업로드를 처리하는 채널 */
    public static final String INBOUND_DRC_UP_DRONE_EMERGENCY_STOP = "inboundDrcUpDroneEmergencyStop";
    /** DRC 하트비트 업로드를 처리하는 채널 */
    public static final String INBOUND_DRC_UP_HEART_BEAT = "inboundDrcUpHeartBeat";
    /** DRC HSI 정보 푸시를 처리하는 채널 */
    public static final String INBOUND_DRC_UP_HSI_INFO_PUSH = "inboundDrcUpHsiInfoPush";
    /** DRC 지연 정보 푸시를 처리하는 채널 */
    public static final String INBOUND_DRC_UP_DELAY_INFO_PUSH = "inboundDrcUpDelayInfoPush";
    /** DRC OSD 정보 푸시를 처리하는 채널 */
    public static final String INBOUND_DRC_UP_OSD_INFO_PUSH = "inboundDrcUpOsdInfoPush";

}
