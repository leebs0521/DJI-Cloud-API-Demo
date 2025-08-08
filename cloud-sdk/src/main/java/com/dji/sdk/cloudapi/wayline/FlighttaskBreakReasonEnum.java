package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 비행 작업 중단 이유 열거형
 * 
 * 이 열거형은 웨이라인 비행 작업이 중단되는
 * 다양한 이유를 정의합니다.
 * 
 * 주요 구성 요소:
 * - 정상 상태: NORMAL
 * - 시스템 오류: NOT_ID, UNCOMMON_ERROR, ERROR_LOADING_FILE 등
 * - 상태 충돌: ALREADY_STARTED, UNABLE_TO_INTERRUPT_WAYLINE 등
 * - 안전 조건: MAXIMUM_ALTITUDE_LIMIT, OBSTACLE_AVOIDANCE 등
 * - 센서 문제: WEAK_GPS, POOR_RTK 등
 * - 사용자 액션: USER_EXIT, USER_INTERRUPTION 등
 * - 파일 오류: INCORRECT_START_INFORMATION, UNSUPPORTED_COORDINATE_SYSTEM 등
 * - 중단점 오류: BREAKPOINT_INVALID_MISSION_ID 등
 * 
 * 이 열거형은 비행 작업이 중단되는 모든 가능한
 * 이유를 분류하고 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/6
 */
public enum FlighttaskBreakReasonEnum {

    /**
     * 정상 상태
     * 
     * 이상이 없는 정상적인 상태입니다.
     */
    NORMAL(0, "No abnormalities"),

    /**
     * 미션 ID가 존재하지 않음
     * 
     * 미션 ID가 존재하지 않습니다. 웨이라인 미션이 실행되지 않았습니다.
     */
    NOT_ID(1, "Mission ID does not exist. The wayline mission has not been executed."),

    /**
     * 일반적이지 않은 오류
     * 
     * 일반적이지 않은 오류입니다. 기술 지원팀에 문의하세요.
     */
    UNCOMMON_ERROR(2, "Uncommon error, please contact technical support."),

    /**
     * 파일 로딩 오류
     * 
     * 웨이라인 미션 시작/재개 요청 시 웨이라인 파일 로딩 오류입니다.
     * 파일을 다시 업로드하거나 기술 지원팀에 문의하세요.
     */
    ERROR_LOADING_FILE(4, "Error loading wayline file when requesting to start/resume the wayline mission, please try uploading the file again or contact technical support."),

    /**
     * 중단점 파일 오류
     * 
     * 중단점 정보 요청 시 중단점 파일 조회 실패입니다.
     * 웨이라인 미션 재개 요청 시 중단점 타입 파싱 실패입니다.
     */
    ERROR_BREAKPOINT_FILE(5, "Failed to query breakpoint file when requesting breakpoint information. Failed to parse breakpoint type when requesting to resume the wayline mission."),

    /**
     * 잘못된 매개변수
     * 
     * 웨이라인 미션 시작/종료 요청 시 잘못된 cmd 매개변수입니다.
     * 요청의 잘못된 프로토콜 명령입니다.
     * 웨이라인 미션 재개 요청 시 중단점 타입 파싱 실패입니다.
     */
    INCORRECT_PARAMETER(6, "Incorrect cmd parameter when requesting to start/end the wayline mission, incorrect protocol command in the request. Failed to parse breakpoint type when requesting to resume the wayline mission."),

    /**
     * 파일 파싱 타임아웃
     * 
     * 웨이라인 미션 시작/재개 요청 시 WPMZ 파일 파싱 타임아웃입니다.
     * 다시 시도하세요.
     */
    PARSING_FILE_TIMEOUT(7, "Timeout parsing the WPMZ file when requesting to start/resume the wayline mission, please retry."),

    /**
     * 이미 시작됨
     * 
     * 웨이라인이 이미 시작되어 다시 시작할 수 없습니다.
     */
    ALREADY_STARTED(257, "Wayline has already started, cannot start again."),

    /**
     * 웨이라인 중단 불가
     * 
     * 이 상태에서는 웨이라인을 중단할 수 없습니다.
     * 실행 상태에서만 웨이라인을 일시정지할 수 있습니다.
     */
    UNABLE_TO_INTERRUPT_WAYLINE(258, "Unable to interrupt the wayline in this state, only allowed to pause the wayline in the executing state."),

    /**
     * 시작되지 않음
     * 
     * 웨이라인이 시작되지 않아 웨이라인을 종료할 수 없습니다.
     */
    NOT_STARTED(259, "Wayline has not started, cannot end the wayline."),

    /**
     * 비행 미션 충돌
     * 
     * 비행 미션 충돌로 항공기 제어를 얻을 수 없습니다.
     * 착륙 및 귀환 중에는 웨이라인 시작이 허용되지 않습니다.
     */
    FLIGHT_MISSION_CONFLICT(261, "Flight mission conflict, unable to obtain control of the aircraft, not allowed to start the wayline during landing and return."),

    /**
     * 웨이라인 재개 불가
     * 
     * 이 상태에서는 웨이라인을 재개할 수 없습니다.
     * 웨이라인이 일시정지된 경우에만 허용됩니다.
     */
    UNABLE_TO_RESUME_WAYLINE(262, "Unable to resume wayline in this state, only allowed when the wayline is paused."),

    /**
     * 최대 고도 제한
     * 
     * 항공기가 최대 고도 제한을 초과했습니다.
     */
    MAXIMUM_ALTITUDE_LIMIT(513, "Aircraft exceeded the maximum altitude limit."),

    /**
     * 최대 거리 제한
     * 
     * 항공기가 최대 거리 제한을 초과했습니다.
     */
    MAXIMUM_DISTANCE_LIMIT(514, "Aircraft exceeded the maximum distance limit."),

    /**
     * 고도가 너무 낮음
     * 
     * 드론의 고도가 너무 낮습니다.
     */
    TOO_LOW_HEIGHT(516, "The height of the drone is too low."),

    /**
     * 장애물 회피
     * 
     * 항공기가 장애물 감지를 트리거했습니다.
     */
    OBSTACLE_AVOIDANCE(517, "Aircraft triggered obstacle sensing."),

    /**
     * RTK 신호 불량
     * 
     * RTK 신호가 불량합니다.
     */
    POOR_RTK(518, "Poor RTK signal"),

    /**
     * 제한 구역 경계
     * 
     * 제한 구역의 경계에 접근했습니다.
     */
    BOUNDARY_OF_RESTRICTED_ZONE(519, "Approaching the boundary of Restricted Zone."),

    /**
     * GEO 고도 제한
     * 
     * 도크의 GEO 구역 고도 제한을 초과했습니다.
     */
    GEO_ALTITUDE_LIMIT(521, "Exceeded the dock's GEO zone altitude limit."),

    /**
     * 이륙 요청 실패
     * 
     * 웨이라인 이륙 요청이 실패했습니다.
     */
    TAKEOFF_REQUEST_FAILED(522, "Failed to request takeoff for the wayline."),

    /**
     * 이륙 실행 실패
     * 
     * 이륙 미션 실행이 실패했습니다.
     */
    TAKEOFF_EXECUTION_FAILED(523, "Takeoff mission execution failed."),

    /**
     * 웨이라인 미션 요청 실패
     * 
     * 웨이라인 미션 요청이 실패했습니다.
     */
    WAYLINE_MISSION_REQUEST_FAILED(524, "Failed to request wayline mission."),

    /**
     * RTK 고정 요청 실패
     * 
     * 웨이라인 RTK 고정 미션 요청이 실패했습니다.
     */
    RTK_FIXING_REQUEST_FAILED(526, "Failed to request wayline RTK fixing mission."),

    /**
     * RTK 고정 실행 실패
     * 
     * 웨이라인 RTK 고정 미션 실행이 실패했습니다.
     */
    RTK_FIXING_EXECUTION_FAILED(527, "Wayline RTK fixing mission failed to run."),

    /**
     * GPS 신호 약함
     * 
     * GPS 신호가 약합니다.
     */
    WEAK_GPS(769, "Weak GPS signal."),

    /**
     * RC 모드 오류
     * 
     * 리모컨이 N 모드가 아니어서 작업을 시작할 수 없습니다.
     */
    ERROR_RC_MODE(770, "Remote controller not in N mode, unable to start the task."),

    /**
     * 홈포인트 미새로고침
     * 
     * 홈포인트가 새로고침되지 않았습니다.
     */
    HOME_POINT_NOT_REFRESHED(771, "Home point not refreshed."),

    /**
     * 배터리 부족
     * 
     * 현재 배터리 레벨이 낮아 미션을 시작할 수 없습니다.
     */
    LOW_BATTERY(772, "Unable to start the mission due to low current battery level."),

    /**
     * 배터리 부족으로 인한 귀환
     * 
     * 배터리 부족으로 인해 웨이라인이 중단되고 홈포인트로 귀환했습니다.
     */
    LOW_BATTERY_RTH(773, "Wayline interrupted due to low battery causing return to home."),

    /**
     * RC 연결 해제
     * 
     * 리모컨과 항공기 간의 연결이 해제되었습니다.
     */
    RC_DISCONNECTION(775, "Disconnection between the remote controller and the aircraft."),

    /**
     * 지상에 있음
     * 
     * 항공기가 지상에 있고 프로펠러가 회전하고 있어 웨이라인을 시작할 수 없습니다.
     */
    ON_THE_GROUND(778, "Aircraft is on the ground with propellers spinning, not allowed to start the wayline."),

    /**
     * 비정상적인 시각 상태
     * 
     * 실시간 지형 추적 중 비정상적인 시각 상태입니다.
     * (예: 너무 밝음, 너무 어두움, 양쪽 밝기가 일치하지 않음)
     */
    ABNORMAL_VISUAL_STATUS(779, "Abnormal visual status (for example, too bright, too dark, inconsistent brightness on both sides) during real-time terrain follow."),

    /**
     * 잘못된 고도
     * 
     * 사용자가 설정한 실시간 지형 추적 고도가 잘못되었습니다.
     * (200m 초과 또는 30m 미만)
     */
    INVALID_ALTITUDE(780, "Real-time terrain-following altitude set by the user is invalid (greater than 200m or less than 30m)."),

    /**
     * 계산 오류
     * 
     * 실시간 지형 추적 중 글로벌 맵 계산 오류입니다.
     */
    CALCULATION_ERROR(781, "Global map calculation error during real-time terrain follow."),

    /**
     * 강풍으로 인한 귀환
     * 
     * 강풍으로 인해 웨이라인이 중단되고 홈포인트로 귀환했습니다.
     */
    STRONG_WINDS_RTH(784, "Wayline interrupted due to strong winds causing return to home."),

    /**
     * 사용자 종료
     * 
     * 사용자가 종료했습니다.
     */
    USER_EXIT(1281, "User exit."),

    /**
     * 사용자 중단
     * 
     * 사용자가 중단했습니다.
     */
    USER_INTERRUPTION(1282, "User interruption."),

    /**
     * 사용자 트리거 귀환
     * 
     * 사용자가 홈포인트 귀환을 트리거했습니다.
     */
    USER_TRIGGERED_RTH(1283, "User triggered return to home."),

    /**
     * 잘못된 시작 정보
     * 
     * 잘못된 시작 정보입니다. (웨이포인트 인덱스 또는 진행률)
     */
    INCORRECT_START_INFORMATION(1539, "Incorrect start information (waypoint index or progress)."),

    /**
     * 지원되지 않는 좌표계
     * 
     * 지원되지 않는 좌표계를 사용하고 있습니다.
     */
    UNSUPPORTED_COORDINATE_SYSTEM(1540, "Using an unsupported coordinate system."),

    /**
     * 지원되지 않는 고도 모드
     * 
     * 지원되지 않는 고도 모드를 사용하고 있습니다.
     */
    UNSUPPORTED_ALTITUDE_MODE(1541, "Using an unsupported altitude mode."),

    /**
     * 지원되지 않는 전환 웨이라인 모드
     * 
     * 지원되지 않는 전환 웨이라인 모드를 사용하고 있습니다.
     */
    UNSUPPORTED_TRANSITIONAL_WAYLINE_MODE(1542, "Using an unsupported transitional wayline mode."),

    /**
     * 지원되지 않는 요 모드
     * 
     * 지원되지 않는 요 모드를 사용하고 있습니다.
     */
    UNSUPPORTED_YAW_MODE(1543, "Using an unsupported yaw mode."),

    /**
     * 지원되지 않는 요 방향 반전 모드
     * 
     * 지원되지 않는 요 방향 반전 모드를 사용하고 있습니다.
     */
    UNSUPPORTED_YAW_DIRECTION_REVERSAL_MODE(1544, "Using an unsupported yaw direction reversal mode."),

    /**
     * 지원되지 않는 웨이포인트 타입
     * 
     * 지원되지 않는 웨이포인트 타입을 사용하고 있습니다.
     */
    UNSUPPORTED_WAYPOINT_TYPE(1545, "Using an unsupported waypoint type."),

    /**
     * 잘못된 조정 회전 타입
     * 
     * 조정 회전 타입은 시작점과 끝점에 사용할 수 없습니다.
     */
    INVALID_COORDINATED_TURNING_TYPE(1546, "Coordinated turning type cannot be used for the start and end points."),

    /**
     * 잘못된 글로벌 속도
     * 
     * 웨이라인 글로벌 속도가 합리적인 범위를 초과했습니다.
     */
    INVALID_GLOBAL_SPEED(1547, "Wayline global speed exceeds a reasonable range."),

    /**
     * 웨이포인트 번호 이상
     * 
     * 웨이포인트 번호가 비정상입니다.
     */
    WAYPOINT_NUMBER_ABNORMAL(1548, "Waypoint number abnormal."),

    /**
     * 잘못된 위도와 경도
     * 
     * 위도와 경도 데이터가 비정상입니다.
     */
    INVALID_LATITUDE_AND_LONGITUDE(1549, "Abnormal latitude and longitude data."),

    /**
     * 비정상적인 회전 인터셉트
     * 
     * 회전 인터셉트가 비정상입니다.
     */
    ABNORMAL_TURNING_INTERCEPT(1550, "Abnormal turning intercept."),

    /**
     * 잘못된 세그먼트 최대 속도
     * 
     * 웨이라인 세그먼트의 최대 속도가 합리적인 범위를 초과했습니다.
     */
    INVALID_SEGMENT_MAXIMUM_SPEED(1551, "Maximum speed of wayline segment exceeds a reasonable range."),

    /**
     * 잘못된 목표 속도
     * 
     * 웨이라인 세그먼트 목표 속도가 합리적인 범위를 초과했습니다.
     */
    INVALID_TARGET_SPEED(1552, "Wayline segment target speed exceeds a reasonable range."),

    /**
     * 잘못된 요 각도
     * 
     * 웨이포인트 요 각도가 합리적인 범위를 초과했습니다.
     */
    INVALID_YAW_ANGLE(1553, "Waypoint yaw angle exceeds a reasonable range."),

    /**
     * 중단점 잘못된 미션 ID
     * 
     * 중단점에서 재개할 때 입력한 mission_id가 잘못되었습니다.
     */
    BREAKPOINT_INVALID_MISSION_ID(1555, "Input mission_id of resuming from breakpoint is wrong."),

    /**
     * 중단점 잘못된 진행 정보
     * 
     * 중단점에서 재개할 때 진행 정보 입력 오류입니다.
     */
    BREAKPOINT_INVALID_PROGRESS_INFORMATION(1556, "Progress information of resuming from breakpoint input error."),

    /**
     * 중단점 오류 미션 상태
     * 
     * 중단점에서 재개할 때 미션 상태가 비정상입니다.
     */
    BREAKPOINT_ERROR_MISSION_STATE(1557, "Mission state of resuming from breakpoint is abnormal."),

    /**
     * 중단점 잘못된 인덱스 정보
     * 
     * 중단점에서 재개할 때 웨이포인트 인덱스 정보 입력 오류입니다.
     */
    BREAKPOINT_INVALID_INDEX_INFORMATION(1558, "Wapoint index information of resuming from breakpoint input error."),

    /**
     * 중단점 잘못된 위도와 경도
     * 
     * 중단점에서 재개할 때 위도와 경도 정보가 잘못되었습니다.
     */
    BREAKPOINT_INCORRECT_LATITUDE_AND_LONGITUDE(1559, "Incorrect latitude and longitude information for resuming from breakpoint."),

    /**
     * 중단점 잘못된 요
     * 
     * 중단점에서 재개할 때 웨이포인트의 요 입력 오류입니다.
     */
    BREAKPOINT_INVALID_YAW(1560, "Yaw input error for waypoints during resuming from breakpoint."),

    /**
     * 중단점 잘못된 플래그 설정
     * 
     * 중단점에서 재개할 때 플래그 설정이 잘못되었습니다.
     */
    BREAKPOINT_INCORRECT_FLAG_SETTING(1561, "Incorrect flag setting for resuming from breakpoint."),

    /**
     * 웨이라인 생성 실패
     * 
     * 웨이라인 생성이 실패했습니다.
     */
    WAYLINE_GENERATION_FAILED(1563, "Wayline generation failed."),

    /**
     * 웨이라인 실행 실패
     * 
     * 웨이라인 실행이 실패했습니다.
     */
    WAYLINE_EXECUTION_FAILED(1564, "Wayline execution failed."),

    /**
     * 웨이라인 장애물 감지
     * 
     * 웨이라인 장애물 감지로 인한 긴급 정지입니다.
     */
    WAYLINE_OBSTACLE_SENSING(1565, "Emergency stop due to wayline obstacle sensing."),

    /**
     * 인식되지 않는 액션 타입
     * 
     * 인식되지 않는 액션 타입입니다.
     */
    UNRECOGNIZED_ACTION_TYPE(1588, "Unrecognized action type."),

    /**
     * 중복 액션 ID
     * 
     * 같은 액션 그룹의 액션 ID는 같을 수 없습니다.
     */
    DUPLICATE_ACTION_ID(1595, "Action ID of same action group can not be the same."),

    /**
     * 액션 ID가 65535가 아님
     * 
     * 액션 ID 값은 65535가 될 수 없습니다.
     */
    ACTION_ID_NOT_65535(1598, "Action ID value cannot be 65535."),

    /**
     * 잘못된 액션 그룹 수
     * 
     * 액션 그룹 수가 합리적인 범위를 초과했습니다.
     */
    INVALID_NUMBER_OF_ACTION_GROUPS(1602, "Number of action groups exceeds a reasonable range."),

    /**
     * 유효 범위 오류
     * 
     * 액션 그룹 유효 범위 오류입니다.
     */
    ERROR_EFFECTIVE_RANGE(1603, "Error in action group effective range."),

    /**
     * 중단점 잘못된 액션 인덱스
     * 
     * 중단점에서 재개할 때 액션 인덱스가 합리적인 범위를 초과했습니다.
     */
    BREAKPOINT_INVALID_ACTION_INDEX(1606, "Action index exceeds a reasonable range during resuming from breakpoint."),

    /**
     * 중단점 트리거 실행 이상
     * 
     * 중단점 정보의 트리거 실행 결과가 비정상입니다.
     */
    BREAKPOINT_TRIGGER_RUNNING_ABNORMAL(1608, "Trigger running result of breakpoint information is abnormal."),

    /**
     * 중단점 중복 액션 그룹 ID
     * 
     * 중단점에서 재개할 때 액션 그룹 ID 정보가 중복될 수 없습니다.
     */
    BREAKPOINT_DUPLICATE_ACTION_GROUP_ID(1609, "Action group ID information can not be duplicated during resume from breakpoint."),

    /**
     * 중단점 중복 액션 그룹 위치
     * 
     * 중단점에서 재개할 때 액션 그룹 위치가 반복될 수 없습니다.
     */
    BREAKPOINT_DUPLICATE_ACTION_GROUP_POSITION(1610, "Action group positions cannot be repeated during resuming from breakpoint."),

    /**
     * 중단점 잘못된 액션 그룹 위치
     * 
     * 중단점에서 재개할 때 액션 그룹 위치가 합리적인 범위를 초과했습니다.
     */
    BREAKPOINT_INVALID_ACTION_GROUP_POSITION(1611, "Action group positions exceed a reasonable range during resuming from breakpoint."),

    /**
     * 중단점 잘못된 액션 ID
     * 
     * 재개할 때 액션 ID가 중단점 정보에 없습니다.
     */
    BREAKPOINT_INVALID_ACTION_ID(1612, "Action ID is not in the breakpoint information during resuming."),

    /**
     * 중단점 중단 불가
     * 
     * 재개할 때 액션 상태를 중단으로 수정할 수 없습니다.
     */
    BREAKPOINT_UNABLE_TO_INTERRUPT(1613, "Cannot modify the action state to interrupt during resuming."),

    /**
     * 잘못된 중단점 정보
     * 
     * 잘못된 중단점 정보로 인한 재개 실패입니다.
     */
    INCORRECT_BREAKPOINT_INFORMATION(1614, "Resume failure due to incorrect breakpoint information."),

    /**
     * 중단점 인식되지 않는 액션 타입
     * 
     * 인식되지 않는 액션 타입입니다.
     */
    BREAKPOINT_UNRECOGNIZED_ACTION_TYPE(1634, "Unrecognized action type."),

    /**
     * 중단점 인식되지 않는 트리거 타입
     * 
     * 인식되지 않는 트리거 타입입니다.
     */
    BREAKPOINT_UNRECOGNIZED_TRIGGER_TYPE(1649, "Unrecognized trigger type."),

    /**
     * 알 수 없는 오류 1
     * 
     * 알 수 없는 오류입니다.
     */
    UNKNOWN_ERROR_1(65534, "Unknown error."),

    /**
     * 알 수 없는 오류 2
     * 
     * 알 수 없는 오류입니다.
     */
    UNKNOWN_ERROR_2(65535, "Unknown error."),

    ;

    /**
     * 비행 작업 중단 이유 값
     * 
     * 각 이유를 구분하는 정수 값입니다.
     */
    private final int reason;

    /**
     * 비행 작업 중단 이유 메시지
     * 
     * 각 이유에 대한 설명 메시지입니다.
     */
    private final String msg;

    /**
     * 비행 작업 중단 이유 열거형 생성자
     * 
     * @param reason 비행 작업 중단 이유 값
     * @param msg 비행 작업 중단 이유 메시지
     */
    FlighttaskBreakReasonEnum(int reason, String msg) {
        this.reason = reason;
        this.msg = msg;
    }

    /**
     * 비행 작업 중단 이유 값을 반환합니다.
     * 
     * @return 비행 작업 중단 이유 값
     */
    @JsonValue
    public int getReason() {
        return reason;
    }

    /**
     * 비행 작업 중단 이유 메시지를 반환합니다.
     * 
     * @return 비행 작업 중단 이유 메시지
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 비행 작업 중단 이유 값으로 이유를 찾습니다.
     * 
     * 주어진 이유 값에 해당하는 열거형을 반환합니다.
     * 해당하는 이유가 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param reason 찾을 비행 작업 중단 이유 값
     * @return 해당하는 FlighttaskBreakReasonEnum 열거형
     * @throws CloudSDKException 해당하는 이유가 없을 경우
     */
    @JsonCreator
    public static FlighttaskBreakReasonEnum find(int reason) {
        return Arrays.stream(values()).filter(reasonEnum -> reasonEnum.reason == reason).findAny()
                .orElseThrow(() -> new CloudSDKException(FlighttaskBreakReasonEnum.class, reason));
    }
}
