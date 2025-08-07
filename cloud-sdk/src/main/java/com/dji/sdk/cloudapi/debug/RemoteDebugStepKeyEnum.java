package com.dji.sdk.cloudapi.debug;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 원격 디버그 단계 키 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 원격 디버그의 단계 키를 정의합니다.
 * 각 단계별 디버그 작업의 키와 설명을 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public enum RemoteDebugStepKeyEnum {

    /**
     * BID 획득
     * 디버그 세션의 고유 식별자를 획득
     */
    GET_BID("get_bid", "Get bid"),

    /**
     * 업그레이드 중 재부팅 방지
     * 디바이스가 업데이트 중인지 확인
     */
    UPGRADING_PREVENT_REBOOT("upgrading_prevent_reboot", "Check if the device is being updated"),

    /**
     * 작업 모드 확인
     * 원격 디버깅 모드로 진입할지 확인
     */
    CHECK_WORK_MODE("check_work_mode", "Check whether to enter remote debugging mode"),

    /**
     * 작업 상태 확인
     * DJI Dock이 사용 가능한지 확인
     */
    CHECK_TASK_STATE("check_task_state", "Check if the DJI Dock is free"),

    /**
     * 착륙 MCU 재부팅
     * 착륙 관련 MCU 재부팅
     */
    LAND_MCU_REBOOT("land_mcu_reboot", "Land MCU reboot"),

    /**
     * 기상 MCU 재부팅
     * 기상 관측소 MCU 재부팅
     */
    RAIN_MCU_REBOOT("rain_mcu_reboot", "Weather station MCU reboot"),

    /**
     * 코어 MCU 재부팅
     * 중앙 제어 MCU 재부팅
     */
    CORE_MCU_REBOOT("core_mcu_reboot", "Central control MCU reboot"),

    /**
     * SDR 재부팅
     * SDR(Signal Data Radio) 재부팅
     */
    SDR_REBOOT("sdr_reboot", "SDR reboot"),

    /**
     * 재부팅 파라미터 파일 작성
     * 재부팅 플래그 작성
     */
    WRITE_REBOOT_PARAM_FILE("write_reboot_param_file", "Write reboot flag"),

    /**
     * 드론 전원 상태 획득
     * 배터리 충전 상태 획득
     */
    GET_DRONE_POWER_STATE("get_drone_power_state", "Get battery charge state"),

    /**
     * 퍼터 닫기
     * 퍼터를 닫음
     */
    CLOSE_PUTTER("close_putter", "Close the putter"),

    /**
     * 유선 연결 상태 확인
     * 항공기 상태 획득
     */
    CHECK_WIRED_CONNECT_STATE("check_wired_connect_state", "Get aircraft state"),

    /**
     * 드론 열기
     * 항공기를 염
     */
    OPEN_DRONE("open_drone", "Open the plane"),

    /**
     * 알람 열기
     * 소리와 빛 알람을 켬
     */
    OPEN_ALARM("open_alarm", "Open sound and light alarm"),

    /**
     * 스크램 상태 확인
     * 비상 정지 스위치가 눌렸는지 확인
     */
    CHECK_SCRAM_STATE("check_scram_state", "Check if the emergency stop switch is pressed"),

    /**
     * 커버 열기
     * 해치를 염
     */
    OPEN_COVER("open_cover", "Open the hatch"),

    /**
     * 드론 SDR 연결 상태 확인
     * SDR 무선 연결을 설정
     */
    CHECK_DRONE_SDR_CONNECT_STATE("check_drone_sdr_connect_state", "Establish SDR wireless connection"),

    /**
     * 드론 켜기
     * 항공기를 켬
     */
    TURN_ON_DRONE("turn_on_drone", "Turn the plane on"),

    /**
     * 드론 패들 전진
     * 전진 패들을 켬
     */
    DRONE_PADDLE_FORWARD("drone_paddle_forward", "Turn on forward paddle"),

    /**
     * 커버 닫기
     * 해치를 닫음
     */
    CLOSE_COVER("close_cover", "Close the hatch"),

    /**
     * 드론 패들 후진
     * 후진 패들을 켬
     */
    DRONE_PADDLE_REVERSE("drone_paddle_reverse", "Turn on reverse paddle"),

    /**
     * 드론 패들 정지
     * 패들 회전을 정지
     */
    DRONE_PADDLE_STOP("drone_paddle_stop", "Stop Paddle Rotation"),

    /**
     * 퍼터 해제
     * 퍼터를 해제
     */
    FREE_PUTTER("free_putter", "Free Putter"),

    /**
     * 충전 정지
     * 충전을 정지
     */
    STOP_CHARGE("stop_charge", "Stop charging");

    /**
     * 단계 키
     */
    private final String stepKey;

    /**
     * 단계 메시지
     */
    private final String message;

    /**
     * 원격 디버그 단계 키 열거형 생성자
     * 
     * @param stepKey 단계 키
     * @param message 단계 메시지
     */
    RemoteDebugStepKeyEnum(String stepKey, String message) {
        this.stepKey = stepKey;
        this.message = message;
    }

    /**
     * 단계 키를 반환합니다.
     * 
     * @return 단계 키
     */
    @JsonValue
    public String getStepKey() {
        return stepKey;
    }

    /**
     * 단계 메시지를 반환합니다.
     * 
     * @return 단계 메시지
     */
    public String getMessage() {
        return message;
    }

    /**
     * 문자열로 원격 디버그 단계 키를 찾습니다.
     * 
     * @param stepKey 찾을 단계 키
     * @return 찾은 원격 디버그 단계 키 열거형
     * @throws CloudSDKException 해당하는 단계 키를 찾을 수 없는 경우
     */
    @JsonCreator
    public static RemoteDebugStepKeyEnum find(String stepKey) {
        return Arrays.stream(values()).filter(stepKeyEnum -> stepKeyEnum.stepKey.equals(stepKey)).findAny()
            .orElseThrow(() -> new CloudSDKException(RemoteDebugStepKeyEnum.class,stepKey));
    }

}
