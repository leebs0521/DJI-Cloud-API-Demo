package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 실행 단계 열거형
 * 
 * 이 열거형은 웨이라인 비행 작업의 세부적인 실행 단계를 정의합니다.
 * 초기 상태부터 완료까지의 모든 단계를 포함하여
 * 비행 작업의 진행 상황을 정확히 추적할 수 있습니다.
 * 
 * 주요 구성 요소:
 * - 초기화 단계: INITIAL, PRE_CHECK 등
 * - 준비 단계: PREPARATION, OPERATIONAL 등
 * - 실행 단계: WAYLINE_EXECUTION, IN_PROGRESS 등
 * - 완료 단계: LADING, TASK_COMPLETED 등
 * - 로그 처리 단계: RETRIEVAL_DRONE_LOG, UPLOAD_DRONE_LOG 등
 * 
 * 이 열거형은 비행 작업의 세부적인 진행 상황을
 * 모니터링하고 적절한 처리 로직을 적용하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/6
 */
public enum ExecutionStepEnum {

    /**
     * 초기 상태
     * 
     * 비행 작업의 초기 상태입니다.
     */
    INITIAL(0, "Initial state"),

    /**
     * 사전 점검 - 경로 실행 확인
     * 
     * 우주선이 경로를 실행하고 있는지 사전 점검합니다.
     */
    PRE_CHECK(1, "Pre-launch check: Is the spacecraft executing the route?"),

    /**
     * 사전 점검 - 작업 모드 종료 확인
     * 
     * 공항이 작업 모드를 종료하고 있는지 사전 점검합니다.
     */
    CHECK_WORK_MODE(2, "Pre-launch check: Is the airport exiting work mode?"),

    /**
     * 사전 점검 - 경로 실행 진행 중
     * 
     * 경로 실행이 진행 중인지 사전 점검합니다.
     */
    CHECK_EXECUTION(3, "Pre-launch check: Route execution in progress"),

    /**
     * 사전 점검 - 귀환 진행 중
     * 
     * 귀환이 진행 중인지 사전 점검합니다.
     */
    CHECK_RETURN(4, "Pre-launch check: Return in progress"),

    /**
     * 준비 상태
     * 
     * 경로 실행이 준비 상태로 진입하고, 작업 발행을 기다리는 상태입니다.
     */
    PREPARATION(5, "Route execution entering preparation state, waiting for task issuance to begin"),

    /**
     * 운영 상태
     * 
     * 공항이 운영 상태로 진입합니다.
     */
    OPERATIONAL(6, "Airport entering operational state"),

    /**
     * 덮개 열기 준비
     * 
     * 시작 점검 준비와 해치 열기 준비 상태로 진입합니다.
     */
    OPEN_COVER_PREPARATION(7, "Entering startup check preparation and hatch opening preparation"),

    /**
     * 비행 시스템 준비 대기
     * 
     * 비행 시스템 준비를 기다리고, 푸시 연결을 설정합니다.
     */
    WAITING_FOR_FLIGHT_SYSTEM_READINESS(8, "Waiting for flight system readiness, push connection establishment"),

    /**
     * RTK 대기
     * 
     * RTK 소스 모니터링을 보고된 값과 함께 기다립니다.
     */
    WAITING_FOR_RTK(9, "Waiting for RTK source monitoring with reported values"),

    /**
     * RTK 소스 확인
     * 
     * RTK 소스가 공항에서 나오는지 확인하고, 그렇지 않으면 재설정합니다.
     */
    CHECK_RTK_SOURCE(10, "Check if RTK source is from the airport; if not, reset"),

    /**
     * 비행 제어 대기
     * 
     * 비행 제어 알림을 기다립니다.
     */
    WAITING_FOR_FLIGHT_CONTROL(11, "Waiting for flight control notification"),

    /**
     * 비행 제어 탈취
     * 
     * 공항에 제어가 없고, 항공기에서 제어를 탈취합니다.
     */
    WRESTING_FLIGHT_CONTROL(12, "Airport has no control; wresting control from the aircraft"),

    /**
     * KMZ 가져오기
     * 
     * 최신 KMZ URL을 가져옵니다.
     */
    GET_KMZ(13, "Get the latest KMZ URL"),

    /**
     * KMZ 다운로드
     * 
     * KMZ 파일을 다운로드합니다.
     */
    DOWNLOAD_KMZ(14, "Download KMZ"),

    /**
     * KMZ 업로드 중
     * 
     * KMZ 파일을 업로드하고 있습니다.
     */
    KMZ_UPLOADING(15, "KMZ uploading"),

    /**
     * 염료 설정
     * 
     * 염료 설정을 구성합니다.
     */
    DYE_CONFIGURATION(16, "Dye configuration"),

    /**
     * 드론 매개변수 설정
     * 
     * 항공기 이륙 매개변수 설정, 대체 착륙 지점 설정, 이륙 고도 설정, 염료 설정
     */
    SET_DRONE_PARAMETER(17, "Aircraft takeoff parameter settings, alternate landing point settings, takeoff altitude settings, dye settings"),

    /**
     * 이륙 매개변수 설정
     * 
     * 항공기 'flyto' 이륙 매개변수 설정
     */
    SET_TAKEOFF_PARAMETER(18, "Aircraft 'flyto' takeoff parameter settings"),

    /**
     * 홈포인트 설정
     * 
     * 홈포인트를 설정합니다.
     */
    SET_HOME_POINT(19, "Home point settings"),

    /**
     * 웨이라인 실행
     * 
     * 경로 실행을 트리거합니다.
     */
    WAYLINE_EXECUTION(20, "Trigger route execution"),

    /**
     * 진행 중
     * 
     * 경로 실행이 진행 중입니다.
     */
    IN_PROGRESS(21, "Route execution in progress"),

    /**
     * 귀환 점검 준비
     * 
     * 귀환 점검 준비 상태로 진입합니다.
     */
    RETURN_CHECK_PREPARATION(22, "Entering return check preparation"),

    /**
     * 착륙
     * 
     * 항공기가 공항에 착륙합니다.
     */
    LADING(23, "Aircraft landing at the airport"),

    /**
     * 덮개 닫기
     * 
     * 착륙 후 해치를 닫습니다.
     */
    CLOSE_COVER(24, "Hatch closure after landing"),

    /**
     * 작업 모드 종료
     * 
     * 공항이 작업 모드를 종료합니다.
     */
    EXIT_WORK_MODE(25, "Airport exiting work mode"),

    /**
     * 드론 이상 복구
     * 
     * 공항 이상 복구를 수행합니다.
     */
    DRONE_ABNORMAL_RECOVERY(26, "Airport abnormal recovery"),

    /**
     * 비행 시스템 로그 업로드
     * 
     * 공항이 비행 시스템 로그를 업로드합니다.
     */
    UPLOADING_FLIGHT_SYSTEM_LOGS(27, "Airport uploading flight system logs"),

    /**
     * 녹화 상태 확인
     * 
     * 카메라 녹화 상태를 확인합니다.
     */
    CHECK_RECORDING_STATUS(28, "Camera recording status check"),

    /**
     * 미디어 파일 가져오기
     * 
     * 미디어 파일의 개수를 가져옵니다.
     */
    GET_MEDIA_FILES(29, "Get the number of media files"),

    /**
     * 도크 이상 복구
     * 
     * 공항 이륙 해치 열기 이상 복구를 수행합니다.
     */
    DOCK_ABNORMAL_RECOVERY(30, "Abnormal recovery of airport takeoff hatch opening"),

    /**
     * 작업 결과 알림
     * 
     * 작업 결과를 알립니다.
     */
    NOTIFY_TASK_RESULTS(31, "Notify task results"),

    /**
     * 작업 완료
     * 
     * 작업 실행이 완료되었습니다. 설정 파일에 따라 로그 검색을 시작할지 여부를 결정합니다.
     */
    TASK_COMPLETED(32, "Task execution completed; whether to initiate log retrieval based on configuration file"),

    /**
     * 드론 로그 목록 검색
     * 
     * 로그 목록 검색 - 항공기 목록
     */
    RETRIEVAL_DRONE_LOG_LIST(33, "Log list retrieval - Aircraft list"),

    /**
     * 도크 로그 목록 검색
     * 
     * 로그 목록 검색 - 공항 목록 검색
     */
    RETRIEVAL_DOCK_LOG_LIST(34, "Log list retrieval - Airport list retrieval"),

    /**
     * 로그 목록 결과 업로드
     * 
     * 로그 목록 검색 - 로그 목록 결과 업로드
     */
    UPLOAD_LOG_LIST_RESULTS(35, "Log list retrieval - Upload log list results"),

    /**
     * 드론 로그 검색
     * 
     * 로그 검색 - 항공기 로그 검색
     */
    RETRIEVAL_DRONE_LOG(36, "Log retrieval - Retrieve aircraft logs"),

    /**
     * 도크 로그 검색
     * 
     * 로그 검색 - 공항 로그 검색
     */
    RETRIEVAL_DOCK_LOG(37, "Log retrieval - Retrieve airport logs"),

    /**
     * 드론 로그 압축
     * 
     * 로그 검색 - 항공기 로그 압축
     */
    COMPRESS_DRONE_LOG(38, "Log retrieval - Compress aircraft logs"),

    /**
     * 도크 로그 압축
     * 
     * 로그 검색 - 공항 로그 압축
     */
    COMPRESS_DOCK_LOG(39, "Log retrieval - Compress airport logs"),

    /**
     * 드론 로그 업로드
     * 
     * 로그 검색 - 항공기 로그 업로드
     */
    UPLOAD_DRONE_LOG(40, "Log retrieval - Upload aircraft logs"),

    /**
     * 도크 로그 업로드
     * 
     * 로그 검색 - 공항 로그 업로드
     */
    UPLOAD_DOCK_LOG(41, "Log retrieval - Upload airport logs"),

    /**
     * 로그 결과 알림
     * 
     * 로그 검색 - 결과 알림
     */
    NOTIFY_LOG_RESULTS(42, "Log retrieval - Notify results"),

    /**
     * 서비스 응답 대기
     * 
     * 완료 후 서비스 응답을 기다립니다.
     */
    WAITING_FOR_SERVICE_RESPONSE(65533, "Waiting for service response after completion"),

    /**
     * 특정 상태 없음
     * 
     * 특정 상태가 없습니다.
     */
    NO_SPECIFIC_STATUS(65534, "No specific status"),

    /**
     * 알 수 없음
     * 
     * 알 수 없는 상태입니다.
     */
    UNKNOWN(65535, "UNKNOWN");

    /**
     * 실행 단계 값
     * 
     * 각 실행 단계를 구분하는 정수 값입니다.
     */
    private final int step;

    /**
     * 실행 단계 메시지
     * 
     * 각 실행 단계에 대한 설명 메시지입니다.
     */
    private final String msg;

    /**
     * 실행 단계 열거형 생성자
     * 
     * @param step 실행 단계 값
     * @param msg 실행 단계 메시지
     */
    ExecutionStepEnum(int step, String msg) {
        this.step = step;
        this.msg = msg;
    }

    /**
     * 실행 단계 값을 반환합니다.
     * 
     * @return 실행 단계 값
     */
    @JsonValue
    public int getStep() {
        return step;
    }

    /**
     * 실행 단계 메시지를 반환합니다.
     * 
     * @return 실행 단계 메시지
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 실행 단계 값으로 실행 단계를 찾습니다.
     * 
     * 주어진 단계 값에 해당하는 열거형을 반환합니다.
     * 해당하는 단계가 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param step 찾을 실행 단계 값
     * @return 해당하는 ExecutionStepEnum 열거형
     * @throws CloudSDKException 해당하는 단계가 없을 경우
     */
    @JsonCreator
    public static ExecutionStepEnum find(int step) {
        return Arrays.stream(values()).filter(stepEnum -> stepEnum.step == step).findAny()
                .orElseThrow(() -> new CloudSDKException(ExecutionStepEnum.class, step));
    }
}
