package com.dji.sample.component.redis;

/**
 * Redis 상수 클래스
 * 
 * Redis에서 사용되는 키 접두사와 시간 상수들을 정의합니다.
 * 다양한 기능별로 구분된 키 네임스페이스를 제공합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/4/21
 */
public final class RedisConst {

    /** 웨이라인 작업 블록 시간 (초) */
    public static final int WAYLINE_JOB_BLOCK_TIME = 600;

    /**
     * 기본 생성자 (private)
     */
    private RedisConst() {

    }

    /** 키 구분자 */
    public static final String DELIMITER = ":";

    /** 디바이스 생존 시간 (초) */
    public static final Integer DEVICE_ALIVE_SECOND = 60;

    /** WebSocket 생존 시간 (초) - 24시간 */
    public static final Integer WEBSOCKET_ALIVE_SECOND = 60 * 60 * 24;

    /** 디바이스 온라인 접두사 */
    public static final String DEVICE_ONLINE_PREFIX = "online" + DELIMITER;

    /** WebSocket 접두사 */
    public static final String WEBSOCKET_PREFIX = "webSocket" + DELIMITER;

    /** WebSocket 전체 접두사 */
    public static final String WEBSOCKET_ALL = WEBSOCKET_PREFIX + "all";

    /** HMS(Health Management System) 접두사 */
    public static final String HMS_PREFIX = "hms" + DELIMITER;

    /** 펌웨어 업그레이드 접두사 */
    public static final String FIRMWARE_UPGRADING_PREFIX = "upgrading" + DELIMITER;

    /** 상태 페이로드 접두사 */
    public static final String STATE_PAYLOAD_PREFIX = "payload" + DELIMITER;

    /** 로그 파일 접두사 */
    public static final String LOGS_FILE_PREFIX = "logs_file" + DELIMITER;

    /** 웨이라인 작업 시간 실행 */
    public static final String WAYLINE_JOB_TIMED_EXECUTE = "wayline_job_timed_execute";

    /** 웨이라인 작업 조건 준비 */
    public static final String WAYLINE_JOB_CONDITION_PREPARE = "wayline_job_condition_prepare";

    /** 웨이라인 작업 조건 접두사 */
    public static final String WAYLINE_JOB_CONDITION_PREFIX = WAYLINE_JOB_CONDITION_PREPARE + DELIMITER;

    /** 웨이라인 작업 블록 접두사 */
    public static final String WAYLINE_JOB_BLOCK_PREFIX = "wayline_job_block" + DELIMITER;

    /** 웨이라인 작업 실행 중 접두사 */
    public static final String WAYLINE_JOB_RUNNING_PREFIX = "wayline_job_running" + DELIMITER;

    /** 웨이라인 작업 일시정지 접두사 */
    public static final String WAYLINE_JOB_PAUSED_PREFIX = "wayline_job_paused" + DELIMITER;

    /** OSD(On-Screen Display) 접두사 */
    public static final String OSD_PREFIX = "osd" + DELIMITER;

    /** 미디어 파일 접두사 */
    public static final String MEDIA_FILE_PREFIX = "media_file" + DELIMITER;

    /** 미디어 최고 우선순위 접두사 */
    public static final String MEDIA_HIGHEST_PRIORITY_PREFIX = "media_highest_priority" + DELIMITER;

    /** 라이브 용량 */
    public static final String LIVE_CAPACITY = "live_capacity";

    /** DRC(Direct Remote Control) 접두사 */
    public static final String DRC_PREFIX = "drc" + DELIMITER;

    /** DRC 모드 생존 시간 (초) - 1시간 */
    public static final Integer DRC_MODE_ALIVE_SECOND = 3600;

    /** MQTT ACL(Access Control List) 접두사 */
    public static final String MQTT_ACL_PREFIX = "mqtt_acl" + DELIMITER;

    /** 파일 업로드 중 접두사 */
    public static final String FILE_UPLOADING_PREFIX = "file_uploading" + DELIMITER;

    /** 드론 제어 접두사 */
    public static final String DRONE_CONTROL_PREFiX = "control_source" + DELIMITER;
}