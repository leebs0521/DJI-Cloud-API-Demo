package com.dji.sdk.cloudapi.debug;

import com.dji.sdk.common.IErrorInfo;
import com.dji.sdk.mqtt.events.IEventsErrorCode;
import com.dji.sdk.mqtt.services.IServicesErrorCode;

import java.util.Arrays;

/**
 * 디버그 오류 코드 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 디버그 관련 오류 코드를 정의합니다.
 * 항공기 동글, 독, 배터리, 충전, 환경 등 다양한 디버그 오류 상황을 포함합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/25
 */
public enum DebugErrorCodeEnum implements IServicesErrorCode, IEventsErrorCode, IErrorInfo {

    /**
     * 항공기 동글 없음
     * 항공기에 DJI Cellular 모듈이 설치되지 않음
     */
    AIRCRAFT_NO_DONGLE(326002, "The DJI Cellular module is not installed on the aircraft."),

    /**
     * 항공기 동글 SIM 없음
     * 항공기의 DJI Cellular 모듈에 SIM 카드가 설치되지 않음
     */
    AIRCRAFT_DONGLE_NO_SIM(326003, "There is no SIM card installed in the DJI Cellular module of the aircraft."),

    /**
     * 항공기 동글 업그레이드 필요
     * 항공기의 DJI Cellular 모듈이 업그레이드가 필요함
     */
    AIRCRAFT_DONGLE_NEED_UPGRADE(326004, "The DJI Cellular module of the aircraft needs to be upgraded, otherwise it cannot be used."),

    /**
     * 연결 설정 실패
     * 항공기의 4G 전송 활성화 실패
     */
    ESTABLISH_CONNECTION_FAILED(326005, "The 4G transmission of the aircraft fails to be enabled, and the 4G transmission cannot establish a connection. Please check the 4G signal strength, or consult the operator to check the package traffic and APN settings."),

    /**
     * SDR 전환 실패
     * 4G 전송 스위치 실패
     */
    SDR_SWITCH_FAILED(326006, "The 4G transmission switch failed, please try again later."),

    /**
     * 잘못된 명령 형식
     * 명령 형식이 잘못됨
     */
    WRONG_COMMAND_FORMAT(326007, "The command format is wrong."),

    /**
     * 독 동글 없음
     * 독에 DJI Cellular 모듈이 설치되지 않음
     */
    DOCK_NO_DONGLE(326008, "The DJI Cellular module is not installed on the dock."),

    /**
     * 독 동글 SIM 없음
     * 독의 DJI Cellular 모듈에 SIM 카드가 설치되지 않음
     */
    DOCK_DONGLE_NO_SIM(326009, "There is no SIM card installed in the DJI Cellular module of the dock."),

    /**
     * 독 동글 업그레이드 필요
     * 독의 DJI Cellular 모듈이 업그레이드가 필요함
     */
    DOCK_DONGLE_NEED_UPGRADE(326010, "The DJI Cellular module of the dock needs to be upgraded, otherwise it cannot be used."),

    /**
     * 지원하지 않는 명령
     * 독 오류, 독을 재시작하고 다시 시도
     */
    COMMAND_NOT_SUPPORTED(514100, "Dock error. Restart dock and try again."),

    /**
     * 구동 로드 밀어넣기 실패
     * 구동 로드를 제자리에 밀어넣는 데 실패
     */
    PUSH_DRIVING_RODS_FAILED(514101, "Failed to push driving rods into place."),

    /**
     * 구동 로드 당기기 실패
     * 구동 로드를 뒤로 당기는 데 실패
     */
    PULL_DRIVING_RODS_FAILED(514102, "Failed to pull driving rods back."),

    /**
     * 배터리 부족 1
     * 항공기 배터리 레벨이 낮음, 작업 수행 불가
     */
    LOW_POWER_1(514103, "Aircraft battery level low. Unable to perform task. Wait until aircraft is charged up to 50% and try again."),

    /**
     * 충전 실패
     * 배터리 충전 실패
     */
    CHARGE_FAILED(514104, "Failed to charge battery."),

    /**
     * 충전 정지 실패
     * 배터리 충전 정지 실패
     */
    STOP_CHARGING_FAILED(514105, "Failed to stop charging battery."),

    /**
     * 드론 재부팅 실패
     * 드론 재부팅 실패
     */
    REBOOT_DRONE_FAILED(514106, "Failed to reboot drone."),

    /**
     * 독 커버 열기 실패
     * 독 커버 열기 실패
     */
    OPEN_DOCK_COVER_FAILED(514107, "Failed to open dock cover."),

    /**
     * 독 커버 닫기 실패
     * 독 커버 닫기 실패
     */
    CLOSE_DOCK_COVER_FAILED(514108, "Failed to close dock cover."),

    /**
     * 항공기 전원 켜기 실패
     * 항공기 전원 켜기 실패
     */
    POWER_ON_AIRCRAFT_FAILED(514109, "Failed to power on aircraft."),

    /**
     * 항공기 전원 끄기 실패
     * 항공기 전원 끄기 실패
     */
    POWER_OFF_AIRCRAFT_FAILED(514110, "Failed to power off aircraft."),

    /**
     * 저속 모션 열기 실패
     * 저속 모션 모드에서 프로펠러 오류
     */
    OPEN_SLOW_MOTION_FAILED(514111, "Propeller error in opening slow motion mode"),

    /**
     * 저속 모션 닫기 실패
     * 저속 모션 모드 닫기에서 프로펠러 오류
     */
    CLOSE_SLOW_MOTION_FAILED(514112, "Propeller error in closing slow motion mode"),

    /**
     * 항공기 없음 1
     * 구동 로드와 항공기 간 연결 오류
     */
    AIRCRAFT_NOT_FOUND_1(514113, "Connection error between driving rod and aircraft. Check if aircraft is inside dock, driving rods are stuck, or charging connector is stained or damaged."),

    /**
     * 배터리 획득 실패
     * 항공기 배터리 상태 획득 실패
     */
    OBTAIN_BATTERY_FAILED(514114, "Failed to obtain aircraft battery status. Restart dock and try again."),

    /**
     * 독 사용 중
     * 독이 다른 명령을 실행 중이어서 작업 수행 불가
     */
    DOCK_BUSY(514116, "Unable to perform operation. Dock is executing other command. Try again later."),

    /**
     * 독 커버 획득 실패
     * 독 커버가 열려있거나 완전히 닫히지 않음
     */
    OBTAIN_DOCK_COVER_FAILED(514117, "Dock cover is open or not fully closed. Restart dock and try again"),

    /**
     * 구동 로드 획득 실패
     * 구동 로드가 뒤로 당겨졌거나 제자리에 밀어넣어지지 않음
     */
    OBTAIN_DRIVING_RODS_FAILED(514118, "Driving rods pulled back or not pushed into place. Restart dock and try again."),

    /**
     * 전송 오류
     * 독과 항공기 연결 해제
     */
    TRANSMISSION_ERROR(514120, "Dock and aircraft disconnected. Restart dock and try again or relink dock and aircraft."),

    /**
     * 비상 버튼 눌림
     * 비상 정지 버튼이 눌림
     */
    EMERGENCY_BUTTON_PRESSED_DOWN(514121, "Emergency stop button pressed down. Release button."),

    /**
     * 충전 상태 획득 실패
     * 항공기 충전 상태 획득 실패
     */
    OBTAIN_CHARGING_STATUS_FAILED(514122, "Failed to obtain aircraft charging status. Restart dock and try again."),

    /**
     * 배터리 부족 2
     * 항공기 배터리 레벨이 너무 낮아 전원 켜기 불가
     */
    LOW_POWER_2(514123, "Aircraft battery level too low. Unable to power on aircraft."),

    /**
     * 배터리 상태 획득 실패
     * 항공기 배터리 정보 획득 실패
     */
    OBTAIN_BATTERY_STATUS_FAILED(514124, "Failed to obtain aircraft battery information."),

    /**
     * 배터리 가득참
     * 항공기 배터리 레벨이 거의 가득참
     */
    BATTERY_FULL(514125, "Aircraft battery level almost full. Unable to start charging. Charge battery when battery level is lower than 95%."),

    /**
     * 폭우
     * 폭우로 작업 수행 불가
     */
    HEAVY_RAINFALL(514134, "Heavy rainfall. Unable to perform task. Try again later."),

    /**
     * 강풍
     * 풍속이 너무 높음 (≥12 m/s)
     */
    HIGH_WIND(514135, "Wind speed too high (≥12 m/s). Unable to perform task. Try again later."),

    /**
     * 전원 공급 오류
     * 독 전원 공급 오류
     */
    POWER_SUPPLY_ERROR(514136, "Dock power supply error. Unable to perform task. Resume power supply and try again."),

    /**
     * 낮은 환경 온도
     * 환경 온도가 너무 낮음 (-20° C 미만)
     */
    LOW_ENVIRONMENT_TEMPERATURE(514137, "Environment temperature too low (lower than -20° C). Unable to perform task. Try again later."),

    /**
     * 배터리 유지보수 중
     * 항공기 배터리 유지보수 중
     */
    BATTERY_MAINTAINING(514138, "Maintaining aircraft battery. Unable to perform task. Wait until maintenance is complete."),

    /**
     * 배터리 유지보수 실패
     * 항공기 배터리 유지보수 실패
     */
    MAINTAIN_BATTERY_FAILED(514139, "Failed to maintain aircraft battery. No maintenance required."),

    /**
     * 배터리 저장 모드 설정 실패
     * 배터리 저장 모드 설정 실패
     */
    SETTING_BATTERY_STORAGE_FAILED(514140, "Failed to set battery storage mode."),

    /**
     * 독 시스템 오류
     * 독 시스템 오류
     */
    DOCK_SYSTEM_ERROR(514141, "Dock system error. Restart dock and try again."),

    /**
     * 항공기 없음 2
     * 이륙 전 구동 로드와 항공기 간 연결 오류
     */
    AIRCRAFT_NOT_FOUND_2(514142, "Connection error between driving rod and aircraft before takeoff. Check if aircraft is inside dock, driving rods are stuck, or charging connector is stained or damaged."),

    /**
     * 구동 로드 오류
     * 구동 로드가 뒤로 당겨졌거나 제자리에 밀어넣어지지 않음
     */
    DRIVING_RODS_ERROR(514143, "Driving rods pulled back or not pushed into place. Try again later or restart dock and try again."),

    /**
     * 독 커버 오류
     * 독 커버가 열려있거나 완전히 닫히지 않음
     */
    DOCK_COVER_ERROR(514144, "Dock cover is open or not fully closed."),

    /**
     * 현장 디버깅 모드
     * 독이 현장 디버깅 모드에 있어 현재 작업이나 작업 수행 불가
     */
    ONSITE_DEBUGGING_MODE(514145, "Dock in onsite debugging mode. Unable to perform current operation or task."),

    /**
     * 원격 디버깅 모드
     * 독이 원격 디버깅 모드에 있어 작업 수행 불가
     */
    REMOTE_DEBUGGING_MODE(514146, "Dock in remote debugging mode. Unable to perform task."),

    /**
     * 펌웨어 업데이트 중
     * 디바이스 펌웨어 업데이트 중
     */
    FIRMWARE_UPDATING(514147, "Updating device firmware. Unable to perform task."),

    /**
     * 작업 중
     * 작업 진행 중, 독이 원격 디버깅 모드에 진입하거나 작업을 다시 수행할 수 없음
     */
    WORKING(514148, "Task in progress. Dock unable to enter remote debugging mode or perform task again. "),

    /**
     * 잘못된 상태
     * 공항이 운영 모드가 아닌데 운영 모드 관련 명령이 발행됨
     */
    WRONG_STATUS(514149, "The airport is not in operation mode, but an operation mode-related command has been issued."),

    /**
     * 재시작 중
     * 디바이스 재시작 중
     */
    RESTARTING(514150, "Restarting device."),

    /**
     * 업데이트 중
     * 디바이스 펌웨어 업데이트 중
     */
    UPDATING(514151, "Updating device firmware."),

    /**
     * 원격 디버깅 모드 아님
     * 독이 원격 디버깅 모드에서 나옴
     */
    NOT_REMOTE_DEBUGGING_MODE(514153, "Dock exited remote debugging mode. Unable to perform current operation."),

    /**
     * 초기화 중
     * 독 초기화 중, 작업 수행 불가
     */
    INITIALIZING(514170, "Initializing dock. Unable to perform operation. Wait until initialization completes."),

    /**
     * 잘못된 매개변수
     * 클라우드 명령 매개변수 오류
     */
    WRONG_PARAMETER(514171, "Cloud command parameter error. Dock unable to execute command."),

    /**
     * AC 비활성화 실패
     * AC 냉각 또는 난방 비활성화 실패
     */
    DISABLE_AC_FAILED(514180, "Failed to disable AC cooling or heating."),

    /**
     * AC 냉각 활성화 실패
     * AC 냉각 활성화 실패
     */
    ENABLE_AC_COOLING_FAILED(514181, "Failed to enable AC cooling."),

    /**
     * AC 난방 활성화 실패
     * AC 난방 활성화 실패
     */
    ENABLE_AC_HEATING_FAILED(514182, "Failed to enable AC heating."),

    /**
     * AC 제습 활성화 실패
     * AC 제습 활성화 실패
     */
    ENABLE_AC_DEHUMIDIFYING_FAILED(514183, "Failed to enable AC dehumidifying."),

    /**
     * 낮은 온도
     * 주변 온도가 0° C 미만으로 AC 냉각 활성화 불가
     */
    LOW_TEMPERATURE(514184, "Ambient temperature below 0° C. Unable to enable AC cooling."),

    /**
     * 높은 온도
     * 주변 온도가 45° C 초과로 AC 난방 활성화 불가
     */
    HIGH_TEMPERATURE(514185, "Ambient temperature above 45° C. Unable to enable AC heating"),

    /**
     * 알 수 없음
     * 알 수 없는 오류
     */
    UNKNOWN(-1, "UNKNOWN"),

    ;

    /**
     * 오류 메시지
     */
    private final String msg;

    /**
     * 오류 코드
     */
    private final int code;

    /**
     * 디버그 오류 코드 열거형 생성자
     * 
     * @param code 오류 코드
     * @param msg 오류 메시지
     */
    DebugErrorCodeEnum(int code, String msg) {
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
     * 오류 코드로 디버그 오류 코드를 찾습니다.
     * 
     * @param code 찾을 오류 코드
     * @return 찾은 디버그 오류 코드 열거형, 없으면 UNKNOWN
     */
    public static DebugErrorCodeEnum find(int code) {
        return Arrays.stream(values()).filter(codeEnum -> codeEnum.code == code).findAny().orElse(UNKNOWN);
    }

}
