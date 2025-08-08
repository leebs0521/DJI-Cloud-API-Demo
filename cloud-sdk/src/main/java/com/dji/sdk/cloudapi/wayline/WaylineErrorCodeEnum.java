package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.common.IErrorInfo;
import com.dji.sdk.mqtt.events.IEventsErrorCode;
import com.dji.sdk.mqtt.services.IServicesErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * 웨이라인 오류 코드 열거형
 * 
 * 이 열거형은 웨이라인 비행 작업에서 발생할 수 있는
 * 모든 오류 코드를 정의합니다.
 * 
 * 주요 구성 요소:
 * - 성공: SUCCESS
 * - 작업 분배 오류: WRONG_PARAM, DISTRIBUTE_TASK_FAILED_1 등
 * - 파일 오류: MD5_EMPTY, WRONG_WAYLINE_FILE 등
 * - 시스템 오류: DOCK_SYSTEM_ERROR_1, INITIATE_AIRCRAFT_FAILED_1 등
 * - 센서 오류: AIRCRAFT_RTK_ERROR, WEAK_GPS 등
 * - 안전 조건: LOW_POWER, OBSTACLE_SENSED 등
 * - 사용자 액션: USER_CONTROL, USER_SEND_RTH 등
 * - 중단점 오류: MISSING_BREAKPOINT, WRONG_BREAKPOINT 등
 * 
 * 이 열거형은 웨이라인 작업에서 발생하는 모든 가능한
 * 오류를 분류하고 관리합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/25
 */
public enum WaylineErrorCodeEnum implements IServicesErrorCode, IEventsErrorCode, IErrorInfo {

    /**
     * 성공
     * 
     * 작업이 성공적으로 완료되었습니다.
     */
    SUCCESS(0, "success"),

    /**
     * 잘못된 매개변수
     * 
     * 작업 분배에 실패했습니다. 나중에 다시 시도하세요.
     */
    WRONG_PARAM(314001, "Failed to distribute task. Try again later"),

    /**
     * MD5 비어있음
     * 
     * 발행된 웨이라인 작업 md5가 비어있습니다.
     */
    MD5_EMPTY(314002, "The issued wayline task md5 is empty."),

    /**
     * 잘못된 웨이라인 파일
     * 
     * 웨이라인 파일 형식이 지원되지 않습니다. 파일을 확인하세요.
     */
    WRONG_WAYLINE_FILE(314003, "Wayline file format not supported. Check file."),

    /**
     * 작업 분배 실패 1
     * 
     * 작업 분배에 실패했습니다.
     */
    DISTRIBUTE_TASK_FAILED_1(314004, "Failed to distribute task."),

    /**
     * MD5 검사 실패
     * 
     * 웨이라인 MD5 검사에 실패했습니다.
     */
    MD5_CHECK_FAILED(314005, "Wayline MD5 check failed."),

    /**
     * 항공기 초기화 실패 1
     * 
     * 항공기 초기화에 실패했습니다. 도크를 재시작하고 다시 시도하세요.
     */
    INITIATE_AIRCRAFT_FAILED_1(314006, "Failed to initiate aircraft. Restart dock and try again."),

    /**
     * KMZ 파일 전송 실패
     * 
     * 도크에서 항공기로 웨이라인 파일 분배에 실패했습니다.
     */
    TRANSFER_KMZ_FILE_FAILED(314007, "Failed to distribute wayline file from dock to aircraft."),

    /**
     * 준비 타임아웃
     * 
     * 항공기 작업 준비가 타임아웃되었습니다. 도크를 재시작하고 다시 시도하세요.
     */
    PREPARATION_TIMED_OUT(314008, "Aircraft task preparation timed out. Restart dock and try again."),

    /**
     * 항공기 초기화 실패 2
     * 
     * 항공기 초기화에 실패했습니다. 도크를 재시작하고 다시 시도하세요.
     */
    INITIATE_AIRCRAFT_FAILED_2(314009, "Failed to initiate aircraft. Restart dock and try again."),

    /**
     * 작업 수행 실패
     * 
     * 작업을 수행할 수 없습니다.
     */
    PERFORM_TASK_FAILED(314010, "Unable to perform task."),

    /**
     * 쿼리 타임아웃
     * 
     * 웨이라인 실행 결과 쿼리가 타임아웃되었습니다.
     */
    QUERY_TIMEOUT(314011, "Wayline execution result query timed out."),

    /**
     * 준비 실패 1
     * 
     * 항공기 작업 준비에 실패했습니다. 작업을 수행할 수 없습니다. 도크를 재시작하고 다시 시도하세요.
     */
    PREPARATION_FAILED_1(314012, "Aircraft task preparation failed. Unable to perform task. Restart dock and try again."),

    /**
     * 잘못된 KMZ URL
     * 
     * KMZ 다운로드 주소 가져오기에 실패했습니다.
     */
    WRONG_KMZ_URL(314013, "Get KMZ download address failed."),

    /**
     * 도크 시스템 오류 1
     * 
     * 도크 시스템 오류입니다. 작업 수행에 실패했습니다. 나중에 다시 시도하세요.
     */
    DOCK_SYSTEM_ERROR_1(314014, "Dock system error. Failed to perform task. Try again later."),

    /**
     * 4세대 종료 실패
     * 
     * 도크에서 항공기로 AI-Spot Check 웨이라인 분배에 실패했습니다. 작업을 수행할 수 없습니다. 나중에 다시 시도하거나 도크를 재시작하고 다시 시도하세요.
     */
    CLOSE_FOURTH_GENERATION_FAILED(314015, "Failed to distribute AI-Spot Check wayline from dock to aircraft. Unable to perform task. Try again later or restart dock and try again."),

    /**
     * KMZ 파일 처리 실패 1
     * 
     * 비행 경로 파일 처리에 실패했습니다. 작업을 수행할 수 없습니다. 파일을 확인하세요.
     */
    PROCESS_KMZ_FILE_FAILED_1(314016, "Failed to process flight route file. Unable to perform task. Check file."),

    /**
     * KMZ 파일 수정 실패
     * 
     * AI Spot-Check의 KMZ 파일 수정에 실패했습니다.
     */
    MODIFY_KMZ_FILE_FAILED(314017, "Failed to modify the KMZ file of AI Spot-Check."),

    /**
     * 항공기 RTK 오류
     * 
     * 항공기 RTK 위치 오류입니다. 작업을 수행할 수 없습니다. 나중에 다시 시도하거나 도크를 재시작하고 다시 시도하세요.
     */
    AIRCRAFT_RTK_ERROR(314018, "Aircraft RTK positioning error. Unable to perform task. Try again later or restart dock and try again."),

    /**
     * RTK 수렴 실패 1
     * 
     * 항공기 RTK 데이터 수렴에 실패했습니다. 작업을 수행할 수 없습니다. 나중에 다시 시도하거나 도크를 재시작하고 다시 시도하세요.
     */
    CONVERGE_RTK_FAILED_1(314019, "Failed to converge aircraft RTK data. Unable to perform task. Try again later or restart dock and try again."),

    /**
     * 항공기 위치 오류
     * 
     * 항공기가 착륙 패드 중앙에 있지 않거나 항공기 방향이 잘못되었습니다. 작업을 수행할 수 없습니다. 항공기 위치와 방향을 확인하세요.
     */
    AIRCRAFT_POSITION_ERROR(314020, "Aircraft not in the middle of landing pad or aircraft heading incorrect. Unable to perform task. Check aircraft position and heading."),

    /**
     * 항공기 RTK 위치 오류
     * 
     * 항공기 RTK 위치 오류입니다. 작업을 수행할 수 없습니다. 나중에 다시 시도하거나 도크를 재시작하고 다시 시도하세요.
     */
    AIRCRAFT_RTK_POSITIONING_ERROR(314021, "Aircraft RTK positioning error. Unable to perform task. Try again later or restart dock and try again."),

    /**
     * 중단점 파일 KMZ 수정 실패
     * 
     * 중단점에서 비행 재개를 위한 KMZ 파일 수정에 실패했습니다.
     */
    MODIFY_KMZ_BREAKPOINT_FILE_FAILED(314022, "Failed to modify KMZ file of resuming flight from breakpoint"),

    /**
     * 백업 착륙 지점 설정 실패
     * 
     * 백업 착륙 지점 설정에 실패했습니다.
     */
    SETTING_BACKUP_LANDING_POINT_FAILED(316001, "Backup landing point setting failed"),

    /**
     * 백업 안전 고도 설정 실패
     * 
     * 전송을 위한 백업 안전 고도 설정에 실패했습니다.
     */
    SETTING_BACKUP_SAFE_HEIGHT_FAILED(316002, "Backup safe height for transfer setting failed"),

    /**
     * 이륙 고도 설정 실패
     * 
     * 이륙 고도 설정에 실패했습니다. 참고: 항공기의 기본 안전 이륙 고도는 도크에서 1.8미터로 설정됩니다. 항공기는 이륙 후 1.8미터까지 비행하며, 0-1.8미터 이륙 과정 중에는 중단할 수 없고, 다른 작업은 이륙 후에만 수행할 수 있습니다. 이 고도는 도크에서 기본적으로 사용되며 수정을 지원하지 않습니다. 목적은 인체 손상을 방지하는 것입니다.
     */
    SETTING_TAKEOFF_HEIGHT_FAILED(316003, "Take-off height setting failed. Note: The default safe take-off height of the aircraft is set to 1.8 meters by dock. The aircraft will fly to 1.8 meters after take-off, and cannot be interrupted during the 0-1.8 meters take-off process, and other actions can only be performed after take-off. This altitude is used by the dock by default and does not support modification. The purpose is to prevent personal injury."),

    /**
     * 제어 상실 액션 설정 실패
     * 
     * 제어 상실 액션 설정에 실패했습니다.
     */
    SETTING_OUT_OF_CONTROL_ACTION_FAILED(316004, "Out of control action setting failed."),

    /**
     * RTK 수렴 실패 2
     * 
     * 항공기 RTK 데이터 수렴에 실패했습니다. 작업을 수행할 수 없습니다. 도크를 재시작하고 다시 시도하세요.
     */
    CONVERGE_RTK_FAILED_2(316005, "Failed to converge aircraft RTK data. Unable to perform task. Restart dock and try again."),

    /**
     * 도크 준비 실패
     * 
     * 항공기가 도크에 착륙할 수 없습니다. 도크 덮개가 닫혀있거나 구동 막대가 제자리에 밀려들어갔습니다. 도크 배치 현장에서 항공기 상태를 확인하세요.
     */
    DOCK_PREPARATION_FAILED(316006, "Aircraft unable to land on dock. Dock cover closed or driving rods pushed into place.  Check aircraft status on dock deployment site."),

    /**
     * 항공기 초기화 실패
     * 
     * 항공기 초기화에 실패했습니다. 도크를 재시작하고 다시 시도하세요.
     */
    INITIATE_AIRCRAFT_FAILED(316007, "Failed to initiate aircraft. Restart dock and try again."),

    /**
     * 비행 제어 획득 실패
     * 
     * 도크가 항공기 비행 제어를 획득하는 데 실패했습니다. 작업을 수행할 수 없습니다. 비행 제어가 리모컨에 의해 잠기지 않았는지 확인하세요.
     */
    OBTAIN_FLIGHT_CONTROL_FAILED(316008, "Dock failed to obtain aircraft flight control. Unable to perform task. Make sure flight control not locked by remote controller."),

    /**
     * 배터리 부족
     * 
     * 항공기 배터리 레벨이 낮습니다. 작업을 수행할 수 없습니다. 항공기가 50%까지 충전될 때까지 기다린 후 다시 시도하세요.
     */
    LOW_POWER(316009, "Aircraft battery level low. Unable to perform task. Wait until aircraft is charged up to 50% and try again"),

    /**
     * 항공기 감지되지 않음
     * 
     * 항공기가 감지되지 않습니다. 작업을 수행할 수 없습니다. 항공기가 도크 내부에 있고 도크와 연결되어 있는지 확인하거나, 도크를 재시작하고 다시 시도하세요.
     */
    AIRCRAFT_NOT_DETECTED(316010, "Aircraft not detected. Unable to perform task. Check if aircraft is inside dock and linked to dock, or restart dock and try again."),

    /**
     * 잘못된 위치에 착륙
     * 
     * 항공기가 잘못된 위치에 착륙했습니다. 항공기를 도크 배치 현장에 수동으로 배치해야 하는지 확인하세요.
     */
    LANDED_ON_INCORRECT_LOCATION(316011, "Aircraft landed on incorrect location. Check if aircraft should be manually placed on dock deployment site."),

    /**
     * 폴더 색상 지정 실패
     * 
     * 항공기 작업 준비에 실패했습니다. 폴더 색상 지정에 실패했습니다.
     */
    FOLDER_COLORING_FAILED(316012, "Aircraft task preparation failed. Folder coloring failed."),

    /**
     * 배터리 전력 획득 실패
     * 
     * 배터리 전력 쿼리에 실패했습니다.
     */
    OBTAIN_BATTERY_POWER_FAILED(316013, "Query of battery power failed."),

    /**
     * 비행 제어 푸시 타임아웃
     * 
     * 비행 제어 푸시 수신이 타임아웃되었습니다.
     */
    FLIGHT_CONTROL_PUSHING_TIMED_OUT(316014, "The receive of flight control pushing timed out."),

    /**
     * 항공기 위치가 너무 멀음
     * 
     * RTK 장치로 보정된 항공기 위치가 도크에서 멀리 떨어져 있습니다. 작업을 수행할 수 없습니다. 도크를 재시작하고 다시 시도하세요.
     */
    AIRCRAFT_LOCATION_TOO_FAR(316015, "Aircraft location calibrated by RTK device is far from dock. Unable to perform task. Restart dock and try again."),

    /**
     * 착륙 타임아웃
     * 
     * 도크에 항공기 착륙이 타임아웃되었습니다. 항공기와 도크가 연결 해제되었을 수 있습니다. 라이브스트림 뷰를 확인하여 항공기가 도크에 착륙했는지 확인하세요.
     */
    LANDING_TIMEOUT(316016, "Aircraft landing on dock timed out. Aircraft and dock may be disconnected. Check livestream view to see if aircraft landed on dock"),

    /**
     * 미디어 획득 타임아웃
     * 
     * 항공기 미디어 파일 개수 획득이 타임아웃되었습니다. 항공기와 도크가 연결 해제되었을 수 있습니다. 라이브스트림 뷰를 확인하여 항공기가 도크에 착륙했는지 확인하세요.
     */
    OBTAIN_MEDIA_TIMEOUT(316017, "Obtaining number of aircraft media files timed out. Aircraft and dock may be disconnected. Check livestream view to see if aircraft landed on dock"),

    /**
     * 작업 성능 타임아웃
     * 
     * 작업 성능이 타임아웃되었습니다. 항공기와 도크가 연결 해제되었을 수 있습니다. 라이브스트림 뷰를 확인하여 항공기가 도크에 착륙했는지 확인하세요.
     */
    TASK_PERFORMANCE_TIMED_OUT(316018, "Task performance timed out. Aircraft and dock may be disconnected. Check livestream view to see if aircraft landed on dock"),

    /**
     * 카메라 색상 지정 타임아웃
     * 
     * 카메라 색상 지정이 타임아웃되었습니다.
     */
    CAMERA_COLORING_TIMED_OUT(316019, "Camera coloring timed out"),

    /**
     * RTK 소스 오류
     * 
     * 항공기 RTK 신호 소스 오류입니다.
     */
    RTK_SOURCE_ERROR(316020, "Aircraft RTK signal source error."),

    /**
     * RTK 소스 타임아웃
     * 
     * 항공기 RTK 신호 소스 확인이 타임아웃되었습니다.
     */
    RTK_SOURCE_TIMEOUT(316021, "Checking aircraft RTK signal source timed out."),

    /**
     * 항공기 연결되지 않음
     * 
     * 항공기가 홈으로 돌아갈 수 없습니다. 항공기가 켜져 있고, 항공기와 도크가 연결되어 있는지 확인하고 다시 시도하세요.
     */
    AIRCRAFT_NOT_CONNECTED(316022, "Aircraft unable to return to home. Check if aircraft is powered on, aircraft and dock are connected, and try again"),

    /**
     * 비행 제어 없음 1
     * 
     * 컨트롤러 B에 의해 제어되는 항공기가 홈으로 돌아갈 수 없습니다. 컨트롤러 B에서 항공기를 제어하거나 리모컨을 끄고 다시 시도하세요.
     */
    NO_FLIGHT_CONTROL_1(316023, "Aircraft controlled by Controller B and unable to return to home. Control aircraft from Controller B or power off remote controller and try again."),

    /**
     * 잘못된 명령
     * 
     * 항공기가 홈으로 돌아갈 수 없습니다. 항공기가 이륙했는지 확인하고 다시 시도하세요.
     */
    WRONG_COMMAND(316024, "Aircraft failed to return to home. Check if aircraft has taken off and try again."),

    /**
     * 항공기 파라미터 설정 실패
     * 
     * 항공기 파라미터 설정에 실패했습니다. 나중에 다시 시도하거나 도크를 재시작하고 다시 시도하세요.
     */
    SETTING_AIRCRAFT_PARAMETERS_FAILED(316025, "Failed to configure aircraft parameters. Try again later or restart dock and try again."),

    /**
     * 응급 버튼 눌림
     * 
     * 도크 응급 정지 버튼이 눌렸습니다. 작업을 수행할 수 없습니다. 버튼을 떼고 다시 시도하세요.
     */
    EMERGENCY_BUTTON_PRESSED_DOWN(316026, "Dock emergency stop button pressed down. Unable to perform task. Release button and try again."),

    /**
     * 항공기 파라미터 설정 타임아웃
     * 
     * 항공기 파라미터 설정이 타임아웃되었습니다. 나중에 다시 시도하거나 도크를 재시작하고 다시 시도하세요.
     */
    SETTING_AIRCRAFT_PARAMETERS_TIMEOUT(316027, "Setting aircraft parameters timed out. Try again later or restart dock and try again."),

    /**
     * 백업 착륙 지점 1
     * 
     * 도크 응급 정지 버튼이 눌렸습니다. 항공기가 백업 착륙 지점으로 비행합니다. 항공기가 안전하게 착륙했는지 확인하고 항공기를 도크 내부로 배치하세요.
     */
    FLYING_TO_BACKUP_POINT_1(316029, "Dock emergency stop button pressed down. Aircraft flying to alternate landing site. Make sure aircraft has safely landed and place aircraft inside dock"),

    /**
     * 홈 지점 새로고침 실패
     * 
     * 홈 지점 새로고침에 실패했습니다. 다시 시도하세요.
     */
    REFRESH_HOME_POINT_FAILED(316030, "Refresh of home point failed. Please try again."),

    /**
     * 반환 홈 모드 설정 실패
     * 
     * 반환 홈 모드 설정에 실패했습니다. 다시 시도하세요.
     */
    SETTING_RTH_MODE_FAILED(316031, "Failed to set return home mode. Please try again."),

    /**
     * 배터리 부족 착륙 외부
     * 
     * 항공기가 배터리가 낮아 도크 외부에 착륙했습니다. 항공기가 안전하게 착륙했는지 확인하고 항공기를 도크로 반환하세요.
     */
    LOW_POWER_LANDING_OUTSIDE(316050, "The aircraft has landed outside the dock due to low battery. Please check immediately whether the aircraft has landed safely and return the aircraft to the dock."),

    /**
     * 작업 비정상 착륙 외부
     * 
     * 웨이라인 작업이 비정상적입니다. 항공기가 도크 외부에 착륙했습니다. 항공기가 안전하게 착륙했는지 확인하고 항공기를 도크로 반환하세요.
     */
    TASK_ABNORMAL_LANDING_OUTSIDE(316051, "The wayline task is abnormal, the aircraft landed outside the dock, please check immediately whether the aircraft has landed safely and return the aircraft to the dock."),

    /**
     * 백업 착륙 지점 2
     * 
     * 웨이라인 작업이 비정상적입니다. 항공기가 백업 착륙 지점으로 비행합니다. 항공기가 안전하게 착륙했는지 확인하고 항공기를 도크로 반환하세요.
     */
    FLYING_TO_BACKUP_POINT_2(316052, "The wayline task is abnormal, the aircraft will fly to the backup landing point, please check immediately whether the aircraft has landed safely and return the aircraft to the dock."),

    /**
     * 사용자 제어 착륙
     * 
     * 사용자가 항공기를 착륙하도록 제어합니다.
     */
    USER_CONTROL_LANDING(316053, "The user controls the aircraft to land."),

    /**
     * 미디어 획득 실패
     * 
     * 항공기 미디어 파일 개수 획득에 실패했습니다.
     */
    OBTAIN_MEDIA_FAILED(317001, "Failed to obtain number of aircraft media files."),

    /**
     * 카메라 연결되지 않음
     * 
     * 항공기 저장소 포맷에 실패했습니다. 항공기가 켜져 있고, 도크와 연결되어 있으며, 카메라가 감지될 수 있는지 확인하거나, 항공기를 재시작하고 다시 시도하세요.
     */
    CAMERA_NOT_CONNECTED(317002, "Failed to format aircraft storage. Make sure aircraft is powered on and connected to dock and camera can be detected. Or restart aircraft and try again."),

    /**
     * 항공기 저장소 포맷 실패
     * 
     * 항공기 저장소 포맷에 실패했습니다.
     */
    FORMAT_AIRCRAFT_STORAGE_FAILED(317003, "Failed to format aircraft storage."),

    /**
     * 미디어 파일 포맷 실패
     * 
     * 미디어 파일 포맷에 실패했습니다.
     */
    FORMAT_MEDIA_FILES_FAILED(317004, "Failed to format media files."),

    /**
     * 녹화 종료 실패
     * 
     * 항공기 비디오 녹화가 불완전하게 종료되었습니다. 이 비행 임무에 대한 미디어 파일을 업로드할 수 없을 수 있습니다.
     */
    STOP_RECORDING_FAILED(317005, "Aircraft video recording terminated unsuccessfully, media files for this flight mission may not be able to be uploaded."),

    /**
     * 작업 불가
     * 
     * 작업을 수행할 수 없습니다. 도크가 작업을 수행하거나 문제 로그를 업로드하고 있습니다. 작업이 완료되거나 로그가 업로드될 때까지 기다리고 다시 시도하세요.
     */
    NOT_IDLE(319001, "Unable to perform task. Dock is performing task or uploading issue logs. Wait until task is complete or logs uploaded and try again."),

    /**
     * 도크 시스템 오류 2
     * 
     * 도크 시스템 오류입니다. 도크를 재시작하고 다시 시도하세요.
     */
    DOCK_SYSTEM_ERROR_2(319002, "Dock system error. Restart dock and try again."),

    /**
     * 작업 ID 없음
     * 
     * 도크에 작업 ID가 없습니다.
     */
    TASK_ID_NOT_EXIST(319003, "Task ID doesn't exist in dock"),

    /**
     * 작업 만료
     * 
     * 작업이 만료되었습니다.
     */
    TASK_EXPIRE(319004, "The task has expired."),

    /**
     * 명령 실행 타임아웃
     * 
     * 명령 전달이 타임아웃되었습니다. 작업을 수행할 수 없습니다.
     */
    FLIGHTTASK_EXECUTE_COMMAND_TIMEOUT(319005, "Execution command delivery timed out. Unable to perform task."),

    /**
     * 작업 취소 실패 1
     * 
     * 작업 취소에 실패했습니다. 진행 중인 작업입니다.
     */
    CANCEL_TASK_FAILED_1(319006, "Failed to cancel task. Task in progress."),

    /**
     * 작업 수정 실패
     * 
     * 작업 수정에 실패했습니다. 진행 중인 작업입니다.
     */
    EDIT_TASK_FAILED(319007, "Failed to edit task. Task in progress."),

    /**
     * 시간 동기화되지 않음
     * 
     * 도크와 클라우드 시간이 동기화되지 않았습니다. 도크가 작업을 수행할 수 없습니다.
     */
    TIME_NOT_SYNCED(319008, "Dock and cloud time not synced. Dock unable to perform task."),

    /**
     * 작업 분배 실패 2
     * 
     * 작업 분배에 실패했습니다. 나중에 다시 시도하거나 도크를 재시작하고 다시 시도하세요.
     */
    DISTRIBUTE_TASK_FAILED_2(319009, "Failed to distribute task. Try again later or restart dock and try again."),

    /**
     * 버전 너무 빠름
     * 
     * 도크 펌웨어 버전이 너무 빠릅니다. 작업을 수행할 수 없습니다. 도크를 최신 버전으로 업데이트하고 다시 시도하세요.
     */
    VERSION_TOO_EARLY(319010, "Dock firmware version too early. Unable to perform task. Update dock to latest version and try again."),

    /**
     * 도크 초기화 중
     * 
     * 도크가 초기화 중입니다. 작업을 수행할 수 없습니다. 초기화가 완료될 때까지 기다리세요.
     */
    INITIALIZING_DOCK(319015, "Initializing dock. Unable to perform task. Wait until initialization is complete."),

    /**
     * 다른 작업 수행 중
     * 
     * 도크가 다른 작업을 수행하고 있습니다. 현재 작업을 수행할 수 없습니다.
     */
    PERFORMING_OTHER_TASK(319016, "Dock performing other task. Unable to perform current task."),

    /**
     * 미디어 파일 처리 중
     * 
     * 마지막 작업에서 캡처된 미디어 파일을 도크가 처리하고 있습니다. 현재 작업을 수행할 수 없습니다. 나중에 다시 시도하세요.
     */
    PROCESSING_MEDIA_FILE(319017, "Dock processing media files captured in last task. Unable to perform current task. Try again later."),

    /**
     * 로그 내보내기
     * 
     * 작업을 수행할 수 없습니다. 도크가 문제 로그를 업로드하고 있습니다. 나중에 다시 시도하세요.
     */
    EXPORTING_LOGS(319018, "Unable to perform task. Dock uploading issue logs. Try again later."),

    /**
     * 로그 획득
     * 
     * 작업을 수행할 수 없습니다. 도크가 문제 로그를 획득하고 있습니다. 나중에 다시 시도하세요.
     */
    PULLING_LOGS(319019, "Unable to perform task. Dock obtaining issue logs. Try again later."),

    /**
     * 작업 일시 중지 실패
     * 
     * 비행 작업 일시 중지에 실패했습니다.
     */
    PAUSE_TASK_FAILED(319020, "Failed to pause flight task."),

    /**
     * 비행 제어 비활성화 실패
     * 
     * 라이브 비행 제어 비활성화에 실패했습니다.
     */
    DISABLE_FLIGHT_CONTROL_FAILED(319021, "Failed to disable Live Flight Controls."),

    /**
     * FlyTo 작업 실패
     * 
     * FlyTo 작업에 실패했습니다.
     */
    FLYTO_TASK_FAILED(319022, "FlyTo task failed."),

    /**
     * FlyTo 작업 중지 실패
     * 
     * FlyTo 작업 중지에 실패했습니다.
     */
    STOP_FLYTO_TASK_FAILED(319023, "Failed to stop FlyTo task."),

    /**
     * 이륙 작업 실패
     * 
     * 한 번의 키 이륙에 실패했습니다.
     */
    TAKING_OFF_TASK_FAILED(319024, "One-key taking off failed."),

    /**
     * 작업 준비 중
     * 
     * 작업이 준비 중입니다. 도크가 클라우드에서 배포된 작업을 수행할 수 없습니다. 나중에 다시 시도하세요.
     */
    TASK_IN_PREPARATION(319025, "Task in preparation. Dock unable to perform task distributed from cloud. Try again later"),

    /**
     * 배터리 레벨 미설정값보다 낮음
     * 
     * 항공기 배터리 레벨이 설정값보다 낮습니다. 작업을 수행할 수 없습니다. 충전이 완료될 때까지 기다린 후 다시 시도하세요.
     */
    LOW_POWER_THAN_SET_VALUE(319026, "Aircraft battery level lower than set value. Unable to perform task. Wait until charging completes and try again."),

    /**
     * 저장소 부족
     * 
     * 도크 또는 항공기에 저장소가 부족합니다. 작업을 수행할 수 없습니다. 미디어 파일이 클라우드에 업로드될 때까지 기다린 후 다시 시도하세요.
     */
    INSUFFICIENT_STORAGE(319027, "Insufficient storage on dock or aircraft. Unable to perform task. Wait until media files are uploaded to cloud and try again."),

    /**
     * 비행 제어 없음 2
     * 
     * 도크에 비행 제어 권한이 없습니다.
     */
    NO_FLIGHT_CONTROL_2(319030, "Dock has no flight control authority."),

    /**
     * 페이로드 제어 없음
     * 
     * 도크에 페이로드 제어 권한이 없습니다.
     */
    NO_PAYLOAD_CONTROL(319031, "Dock has no payload control authority"),

    /**
     * 잘못된 포인트 번호
     * 
     * Flyto 목표 포인트, 포인트 번호가 잘못되었습니다.
     */
    WRONG_POINT_NUMBER(319032, "Flyto target point, the point number is wrong."),

    /**
     * 시퀀스 번호가 마지막 시퀀스 번호보다 작음
     * 
     * DRC - 비행 제어 실패. 패키지 시퀀스 번호가 마지막 시퀀스 번호보다 작습니다.
     */
    SEQ_NUMBER_SMALLER_THAN_LAST(319033, "DRC - flight control failed. Package sequence number is smaller than last one."),

    /**
     * 패키지 수신 시간이 설정된 시간보다 작음
     * 
     * DRC - 비행 제어 실패. 패키지 수신 시간이 설정된 시간을 초과했습니다.
     */
    DELAY_TIME_SMALLER_THAN_SET(319034, "DRC - flight control failed. Package received time out."),

    /**
     * 응급 정지 실패
     * 
     * 응급 정지에 실패했습니다. 다시 시도하세요.
     */
    EMERGENCY_STOP_FAILED(319035, "Emergency stop failed, please try again."),

    /**
     * 원격 디버깅 모드
     * 
     * 원격 디버깅 모드입니다.
     */
    REMOTE_DEBUGGING_MODE(319036, "Device in remote debugging mode. "),

    /**
     * 현장 디버깅 모드
     * 
     * 현장 디버깅 모드입니다.
     */
    ONSITE_DEBUGGING_MODE(319037, "Device in onsite debugging mode."),

    /**
     * 업데이트 중
     * 
     * 기기가 업데이트 중입니다. 나중에 다시 시도하세요.
     */
    UPDATING(319038, "Updating device. Try again later."),

    /**
     * 작업 복원 실패
     * 
     * 비행 복원에 실패했습니다.
     */
    RESUME_TASK_FAILED(319042, "Failed to resume flight."),

    /**
     * 작업 취소 실패 2
     * 
     * 반환 홈 실패에 실패했습니다.
     */
    CANCEL_TASK_FAILED_2(319043, "Failed to cancel RTH."),

    /**
     * 중단점 없음
     * 
     * 작업이 완료되었습니다. 복원할 수 없습니다.
     */
    NO_BREAKPOINT(319044, "Task completed. Unable to resume."),

    /**
     * 응급 정지 상태
     * 
     * DRC - 비행 제어 실패. 항공기가 일시 중지되었습니다.
     */
    EMERGENCY_STOP_STATUS(319045, "DRC - flight control failed. Aircraft paused."),

    /**
     * 웨이라인 중단 또는 일시 중지됨
     * 
     * 작업이 완료되었거나 일시 중지되었습니다. 일시 중지할 수 없습니다.
     */
    NOT_IN_WAYLINE(319046, "Task completed or paused. Unable to pause."),

    /**
     * 도크 시스템 오류 3
     * 
     * 도크 시스템 오류입니다. 도크를 재시작하고 다시 시도하세요.
     */
    DOCK_SYSTEM_ERROR_3(319999, "Dock system error. Restart dock and try again."),

    /**
     * 작업 오류
     * 
     * 작업 오류입니다. 나중에 다시 시도하거나 도크를 재시작하고 다시 시도하세요.
     */
    TASK_ERROR(321000, "Task error. Try again later or restart dock and try again."),

    /**
     * KMZ 파일 처리 실패 2
     * 
     * 비행 경로 파일 처리에 실패했습니다. 작업을 수행할 수 없습니다. 파일을 확인하세요.
     */
    PROCESS_KMZ_FILE_FAILED_2(321004, "Failed to process flight route file. Unable to perform task. Check file."),

    /**
     * 중단점 정보 누락
     * 
     * 웨이라인에서 중단점 정보가 누락되었습니다.
     */
    MISSING_BREAKPOINT(321005, "Missing breakpoint info in wayline."),

    /**
     * 작업 진행 중
     * 
     * 작업이 진행 중이며, 작업을 다시 시작할 수 없습니다.
     */
    TASK_IN_PROGRESS(321257, "Task in progress. Failed to start task again."),

    /**
     * 상태 지원되지 않음
     * 
     * 작업을 중지할 수 없습니다. 항공기 상태를 확인하세요.
     */
    STATUS_NOT_SUPPORTED(321258, "Unable to stop task. Check aircraft status."),

    /**
     * 시작되지 않은 작업은 중지할 수 없음
     * 
     * 작업이 시작되지 않았습니다. 작업을 중지할 수 없습니다.
     */
    NOT_STARTED_CANNOT_STOP(321259, "Task not started. Unable to stop task."),

    /**
     * 시작되지 않은 작업은 중단할 수 없음
     * 
     * 작업이 시작되지 않았습니다. 작업을 중단할 수 없습니다.
     */
    NOT_STARTED_CANNOT_INTERRUPT(321260, "Task not started. Unable to pause task."),

    /**
     * 높이 제한
     * 
     * 작업을 수행할 수 없습니다. 비행 경로 고도가 항공기 최대 비행 고도를 초과합니다.
     */
    HEIGHT_LIMIT(321513, "Unable to perform task. Flight route altitude greater than aircraft max flight altitude."),

    /**
     * 거리 제한
     * 
     * 작업을 수행할 수 없습니다. 버퍼 존 또는 버퍼 존 외부의 비행 경로 시작 또는 끝 포인트에 거리 제한이 있습니다.
     */
    DISTANCE_LIMIT(321514, "Failed to perform task. Flight route start or end point in buffer zone or exceeds distance limit."),

    /**
     * 지리 존
     * 
     * 작업을 수행할 수 없습니다. 항공기가 지리 존을 비행합니다.
     */
    GEO_ZONE(321515, "Unable to perform task. Aircraft will fly across GEO Zone."),

    /**
     * 높이 너무 낮음
     * 
     * 작업이 중단되었습니다.
     */
    HEIGHT_TOO_LOW(321516, "Flight altitude too low. Task stopped."),

    /**
     * 장애물 감지
     * 
     * 장애물이 감지되었습니다. 작업이 중단되었습니다.
     */
    OBSTACLE_SENSED(321517, "Obstacle sensed. Task stopped."),

    /**
     * 근접 지리 존
     * 
     * 항공기가 지리 존에 접근하거나 최대 거리에 도달하여 자동으로 홈으로 돌아갔습니다. 작업을 완료할 수 없습니다.
     */
    APPROACHED_GEO_ZONE(321519, "Aircraft approached GEO Zone or reached max distance and automatically returned to home. Unable to complete task."),

    /**
     * 프로펠러 확인 실패
     * 
     * 항공기 프로펠러 확인에 실패했습니다. 프로펠러가 손상되었을 수 있습니다. 나중에 다시 시도하세요. DJI 지원에 연락하여 문제가 계속되면 프로펠러를 교체하세요.
     */
    PROPELLER_CHECK_FAILED(321523, "Aircraft propeller check failed. Propeller may be damaged. Try again later. Contact DJI Support to replace propeller if issue persists."),

    /**
     * 준비 실패 2
     * 
     * 항공기의 이륙 전 준비가 실패했습니다. 항공기가 위치를 찾거나 기어 오류가 발생할 수 있습니다. 항공기 상태를 확인하세요.
     */
    PREPARATION_FAILED_2(321524, "The preparation before takeoff of the aircraft has failed, possibly due to the aircraft's inability to locate or gear error. Please check the status of the aircraft."),

    /**
     * 약한 GPS
     * 
     * 항공기의 GPS 신호가 약합니다. 작업을 수행할 수 없습니다. 도크를 재시작하고 다시 시도하세요.
     */
    WEAK_GPS(321769, "Aircraft satellite positioning signal weak. Unable to perform task. Restart dock and try again."),

    /**
     * 잘못된 기어 모드
     * 
     * 항공기 비행 모드가 잘못되었습니다. 작업을 수행할 수 없습니다. 도크를 재시작하고 다시 시도하세요.
     */
    WRONG_GEAR_MODE(321770, "Aircraft flight mode error. Unable to perform task. Restart dock and try again."),

    /**
     * 홈 지점 설정되지 않음
     * 
     * 항공기 홈 지점이 설정되지 않았습니다. 작업을 수행할 수 없습니다. 도크를 재시작하고 다시 시도하세요.
     */
    HOME_POINT_NOT_SET(321771, "Aircraft Home Point not set. Unable to perform task. Restart dock and try again."),

    /**
     * 배터리 부족 작업 수행
     * 
     * 항공기 배터리 레벨이 낮습니다. 작업을 수행할 수 없습니다. 항공기가 50%까지 충전될 때까지 기다린 후 다시 시도하세요.
     */
    LOW_POWER_PERFORM_TASK(321772, "Aircraft battery level low. Unable to perform task. Wait until aircraft is charged up to 50% and try again."),

    /**
     * 배터리 부족 반환 홈
     * 
     * 항공기 배터리가 낮아 반환 홈에 도달했습니다. 작업을 완료할 수 없습니다.
     */
    LOW_POWER_RTH(321773, "Aircraft battery level low and returned to home. Unable to complete task."),

    /**
     * 항공기 신호 손실
     * 
     * 항공기가 작업을 수행하는 동안 신호가 손실되었습니다.
     */
    AIRCRAFT_SIGNAL_LOST(321775, "Aircraft signal lost when performing task."),

    /**
     * RTK 준비되지 않음
     * 
     * 항공기 RTK 데이터 수렴에 실패했습니다. 작업을 수행할 수 없습니다. 도크를 재시작하고 다시 시도하세요.
     */
    RTK_NOT_READY(321776, "Failed to converge aircraft RTK data. Unable to perform task. Restart dock and try again."),

    /**
     * 비행하지 않음
     * 
     * 항공기가 비행하지 않습니다. 작업을 시작할 수 없습니다.
     */
    NOT_HOVERING(321777, "Aircraft not hovering. Unable to start task."),

    /**
     * B 제어 프로펠러
     * 
     * 항공기가 컨트롤러 B에 의해 제어되고 프로펠러가 시작되었습니다.
     */
    B_CONTROL_PROPELLERS(321778, "Unable to perform task. Aircraft controlled by Controller B, and propellers started."),

    /**
     * 사용자 제어
     * 
     * 작업이 중단되었습니다. 클라우드 사용자 또는 컨트롤러 B에 의해 항공기 제어가 획득되었습니다.
     */
    USER_CONTROL(322282, "Task stopped. Aircraft control obtained by cloud user or Controller B."),

    /**
     * 사용자 전송 RTH
     * 
     * 사용자가 반환 홈 명령을 보냈습니다. 항공기가 작업을 완료할 수 없습니다.
     */
    USER_SEND_RTH(322283, "RTH command sent by user. Aircraft unable to complete task."),

    /**
     * 잘못된 중단점
     * 
     * 중단점 정보 오류입니다. 도크가 작업을 수행할 수 없습니다.
     */
    WRONG_BREAKPOINT(322539, "Breakpoint info error. Dock unable to perform task"),

    /**
     * 빈 액션 레이어
     * 
     * 액션 트리의 레이어가 비어있을 수 없습니다.
     */
    EMPTY_ACTION_LAYER(322594, "The layer of action tree can not be empty."),

    /**
     * 작업 오류
     * 
     * 작업 오류입니다. 나중에 다시 시도하거나 도크를 재시작하고 다시 시도하세요.
     */
    WRONG_TASK(386535, "Task error. Try again later or restart dock and try again."),

    /**
     * 미디어 우선순위 설정 실패
     * 
     * 미디어 업로드 우선순위 설정에 실패했습니다. 작업이 업로드 큐에 존재하지 않습니다.
     */
    SET_MEDIA_PRIORITY_FAILED(324030, "Setting priority of media upload failed, the task does not exist in the upload queue."),

    /**
     * 미디어 우선순위 명령 너무 빠름
     * 
     * 미디어 업로드 우선순위 설정에 실패했습니다. 명령 발행 작업이 너무 빠르게 발생하여 마지막 명령에 대한 응답이 아직 끝나지 않았습니다.
     */
    MEDIA_PRIORITY_COMMAND_TOO_FAST(324031, "Setting priority of media upload failed, the action of issuing commands is too fast, and the response to the last command has not yet ended."),

    /**
     * 미디어 우선순위 잘못된 파라미터
     * 
     * 미디어 업로드 우선순위 설정에 실패했습니다. 잘못된 파라미터입니다.
     */
    MEDIA_PRIORITY_WRONG_PARAMETER(324032, "Setting priority of media upload failed, incorrect parameter."),

    /**
     * 알 수 없음
     * 
     * 알 수 없는 오류입니다.
     */
    UNKNOWN(-1, "UNKNOWN"),

    ;

    /**
     * 오류 메시지
     * 
     * 각 오류 코드에 대한 설명 메시지입니다.
     */
    private final String msg;

    /**
     * 오류 코드
     * 
     * 각 오류를 구분하는 정수 값입니다.
     */
    private final int code;

    /**
     * 웨이라인 오류 코드 열거형 생성자
     * 
     * @param code 오류 코드
     * @param msg 오류 메시지
     */
    WaylineErrorCodeEnum(int code, String msg) {
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
     * 문자열 표현을 반환합니다.
     * 
     * @return 문자열 표현
     */
    @Override
    public String toString() {
        return "{" +
                "code='" + code + '\'' +
                ", message=" + msg +
                '}';
    }

    /**
     * 오류 코드로 웨이라인 오류 코드를 찾습니다.
     * 
     * 주어진 오류 코드에 해당하는 열거형을 반환합니다.
     * 해당하는 오류 코드가 없으면 UNKNOWN을 반환합니다.
     * 
     * @param code 찾을 오류 코드
     * @return 해당하는 WaylineErrorCodeEnum 열거형
     */
    @JsonCreator
    public static WaylineErrorCodeEnum find(int code) {
        return Arrays.stream(values()).filter(codeEnum -> codeEnum.code == code).findAny().orElse(UNKNOWN);
    }

}
