package com.dji.sdk.cloudapi.firmware;

import com.dji.sdk.common.IErrorInfo;
import com.dji.sdk.mqtt.events.IEventsErrorCode;
import com.dji.sdk.mqtt.services.IServicesErrorCode;

import java.util.Arrays;

/**
 * 펌웨어 오류 코드 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 펌웨어 관련 오류 코드를 정의합니다.
 * 업그레이드 실패, 검증 실패, 전송 오류, 배터리 부족 등 다양한 펌웨어 오류 상황을 포함합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/25
 */
public enum FirmwareErrorCodeEnum implements IServicesErrorCode, IEventsErrorCode, IErrorInfo {

    /**
     * 잘못된 타입
     * 일관성 업그레이드가 트리거되었지만 디바이스가 요청하지 않음
     */
    WRONG_TYPE(312001, "Consistency Upgrade was trrigered, but device didn't request."),

    /**
     * 준비 1 실패
     * 업그레이드 실패, 다시 시도하세요
     */
    READY_1_FAILED(312002, "Failed to upgrade. Please try again."),

    /**
     * 검증 실패
     * 업그레이드 실패, 다시 시도하세요
     */
    VALIDATION_FAILED(312003, "Failed to upgrade. Please try again."),

    /**
     * 준비 2 실패
     * 업그레이드 실패, 다시 시도하세요
     */
    READY_2_FAILED(312004, "Failed to upgrade. Please try again."),

    /**
     * 잘못된 프로토콜
     * 업그레이드 요청이 API와 다름
     */
    WRONG_PROTOCOL(312010, "The upgrade request is different from the API."),

    /**
     * 잘못된 매개변수
     * 매개변수를 확인하고 다시 시도하세요
     */
    WRONG_PARAMETER(312012, "Please check the parameters and try again."),

    /**
     * 명령 1 실패
     * 업그레이드 실패, 다시 시도하세요
     */
    COMMAND_1_FAILED(312013, "Failed to upgrade. Please try again."),

    /**
     * 업데이트 중
     * 디바이스 펌웨어 업데이트 중, 업데이트 완료까지 기다리세요
     */
    UPDATING(312014, "Updating device firmware. Wait until update completed."),

    /**
     * 작업 중
     * 비행 중에는 디바이스를 업그레이드할 수 없음, 기다렸다가 다시 시도하세요
     */
    WORKING(312015, "Device can not upgrade during the flight. Please wait and try again."),

    /**
     * 전송 오류
     * 업데이트 실패, 독과 항공기 전송 오류
     */
    TRANSMISSION_ERROR(312016, "Update failed. Dock and aircraft transmission error. Restart dock and aircraft and try again."),

    /**
     * 버전 확인 실패
     * 버전 확인 실패
     */
    VERSION_CHECK_FAILED(312017, "Failed to check the version."),

    /**
     * 명령 2 실패
     * 업그레이드 실패, 다시 시도하세요
     */
    COMMAND_2_FAILED(312018, "Failed to upgrade. Please try again."),

    /**
     * 명령 3 실패
     * 업그레이드 실패, 다시 시도하세요
     */
    COMMAND_3_FAILED(312019, "Failed to upgrade. Please try again."),

    /**
     * 명령 4 실패
     * 업그레이드 실패, 다시 시도하세요
     */
    COMMAND_4_FAILED(312020, "Failed to upgrade. Please try again."),

    /**
     * 명령 5 실패
     * 업그레이드 실패, 다시 시도하세요
     */
    COMMAND_5_FAILED(312021, "Failed to upgrade. Please try again."),

    /**
     * 항공기 없음
     * 항공기 전원 켜기 실패 또는 항공기 연결 안됨
     */
    AIRCRAFT_NOT_FOUND(312022, "Failed to power on aircraft, or aircraft not connected. Check if aircraft is inside dock, battery installed, and dock and aircraft linked."),

    /**
     * 항공기 외부
     * 구동 로드를 뒤로 당기는 데 실패, 항공기 펌웨어 업데이트 불가
     */
    AIRCRAFT_OUTSIDE(312023, "Failed to push driving rods back into place. Unable to update aircraft firmware. Check if emergency stop button is pressed down or driving rods are stuck."),

    /**
     * 명령 6 실패
     * 업그레이드 실패, 다시 시도하세요
     */
    COMMAND_6_FAILED(312024, "Failed to upgrade. Please try again."),

    /**
     * 삭제 실패
     * 이전 펌웨어 패키지 삭제 실패
     */
    DELETE_FAILED(312025, "Failed to delete old firmware package."),

    /**
     * 압축 해제 실패
     * 오프라인 업그레이드 패키지 압축 해제 실패
     */
    DECOMPRESSION_FAILED(312026, "Failed to decompress the offline upgrade package."),

    /**
     * 항공기 감지 안됨
     * 펌웨어 업데이트 실패, 독 내부에서 항공기가 감지되지 않음
     */
    NO_AIRCRAFT_DETECTED(312027, "Failed to update firmware. Aircraft not detected inside dock."),

    /**
     * 디바이스 재시작 1
     * 펌웨어 업데이트 실패, 업데이트 중 디바이스 재시작됨
     */
    DEVICE_RESTART_1(312028, "Failed to update firmware. Device restarted during update."),

    /**
     * 디바이스 재시작 2
     * 디바이스 재시작 중, 펌웨어 업데이트 불가
     */
    DEVICE_RESTART_2(312029, "Restarting device. Unable to update firmware."),

    /**
     * 4세대 활성화됨
     * 항공기 강화 전송 활성화됨, 펌웨어 업데이트 실패
     */
    FOURTH_GENERATION_IS_ENABLE(312030, "Aircraft enhanced transmission enabled. Failed to update firmware. Disable 4G transmission and try again."),

    /**
     * 배터리 부족
     * 항공기 배터리 레벨이 너무 낮음
     */
    LOW_POWER(312704, "Aircraft battery level too low. Wait until aircraft is charged to above 20% and try again."),

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
     * 펌웨어 오류 코드 열거형 생성자
     * 
     * @param code 오류 코드
     * @param msg 오류 메시지
     */
    FirmwareErrorCodeEnum(int code, String msg) {
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
     * 오류 코드로 펌웨어 오류 코드를 찾습니다.
     * 
     * @param code 찾을 오류 코드
     * @return 찾은 펌웨어 오류 코드 열거형, 없으면 UNKNOWN
     */
    public static FirmwareErrorCodeEnum find(int code) {
        return Arrays.stream(values()).filter(codeEnum -> codeEnum.code == code).findAny().orElse(UNKNOWN);
    }

}
