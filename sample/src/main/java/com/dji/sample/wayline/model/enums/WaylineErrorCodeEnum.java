package com.dji.sample.wayline.model.enums;

import com.dji.sdk.common.IErrorInfo;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

/**
 * DJI Cloud API 웨이라인 오류 코드 열거형
 * 
 * 이 열거형은 웨이라인 작업 실행 중 발생할 수 있는 모든 오류 코드를 정의합니다.
 * DJI SDK의 IErrorInfo 인터페이스를 구현하여 표준화된 오류 정보를 제공하며,
 * 각 오류의 차단 여부를 나타내는 플래그도 함께 관리합니다.
 * 
 * 주요 기능:
 * - 웨이라인 작업 오류 코드 정의 및 관리
 * - 오류 메시지 및 차단 여부 관리
 * - JSON 역직렬화를 위한 오류 코드 검색
 * - 표준화된 오류 정보 인터페이스 구현
 * 
 * 오류 분류:
 * - 시스템 오류: Dock, 드론 시스템 관련 오류
 * - 하드웨어 오류: 센서, 통신, 전원 관련 오류
 * - 환경 오류: 기상, 비행 제한 구역 관련 오류
 * - 작업 오류: 작업 실행 조건 관련 오류
 * 
 * @author sean
 * @version 1.3
 * @date 2023/2/17
 */
public enum WaylineErrorCodeEnum implements IErrorInfo {

    /**
     * 성공 (Success)
     * 작업이 성공적으로 완료됨
     * 
     * 오류 코드: 0
     * 메시지: "success"
     * 차단 여부: false (성공이므로 차단되지 않음)
     */
    SUCCESS(0, "success", false),

    /**
     * 비상 버튼 (Emergency Button)
     * Dock의 비상 버튼이 눌려진 상태
     * 
     * 오류 코드: 316026
     * 메시지: "The emergency button at the dock was pressed."
     * 차단 여부: true (비상 상황이므로 작업 차단)
     * 
     * 발생 원인:
     * - 사용자가 Dock의 비상 버튼을 눌렀을 때
     * - 안전상의 이유로 즉시 모든 작업이 중단됨
     */
    EMERGENCY_BUTTON(316026, "The emergency button at the dock was pressed.", true),
    
    /**
     * 작업 센터 비활성 (Not Idle)
     * 작업 센터가 현재 비활성 상태가 아님
     * 
     * 오류 코드: 319001
     * 메시지: "Task Center is not currently idle."
     * 차단 여부: true (다른 작업이 실행 중이므로 차단)
     * 
     * 발생 원인:
     * - 다른 웨이라인 작업이 실행 중일 때
     * - 시스템 점검 중일 때
     * - Dock이 다른 작업을 수행 중일 때
     */
    NOT_IDLE(319001, "Task Center is not currently idle.", true),
    
    /**
     * 작업 수행 중 (Performing Task)
     * Dock이 다른 작업을 수행 중임
     * 
     * 오류 코드: 319016
     * 메시지: "The dock is performing other tasks."
     * 차단 여부: true (다른 작업이 실행 중이므로 차단)
     * 
     * 발생 원인:
     * - 다른 웨이라인 작업이 실행 중
     * - 시스템 업데이트 중
     * - 펌웨어 업그레이드 중
     */
    PERFORMING_TASK(319016, "The dock is performing other tasks.", true),
    
    /**
     * 로그 내보내기 중 (Exporting Logs)
     * Dock이 로그를 내보내는 중임
     * 
     * 오류 코드: 319018
     * 메시지: "The dock is exporting logs."
     * 차단 여부: true (로그 내보내기 중이므로 차단)
     * 
     * 발생 원인:
     * - 시스템 로그를 외부로 내보내는 중
     * - 디버깅을 위한 로그 수집 중
     */
    EXPORTING_LOGS(319018, "The dock is exporting logs.", true),
    
    /**
     * 로그 가져오기 중 (Pulling Logs)
     * Dock이 로그를 가져오는 중임
     * 
     * 오류 코드: 319019
     * 메시지: "The dock is pulling logs."
     * 차단 여부: true (로그 가져오기 중이므로 차단)
     * 
     * 발생 원인:
     * - 외부에서 로그를 가져오는 중
     * - 로그 동기화 작업 중
     */
    PULLING_LOGS(319019, "The dock is pulling logs.", true),
    
    /**
     * 고도 제한 (Height Limit)
     * 웨이라인 고도가 드론의 고도 제한을 초과함
     * 
     * 오류 코드: 321513
     * 메시지: "The wayline altitude has exceeded the height limit of the drone."
     * 차단 여부: true (안전상의 이유로 차단)
     * 
     * 발생 원인:
     * - 웨이라인에서 설정한 고도가 드론의 최대 고도 제한을 초과
     * - 규제상의 고도 제한을 초과
     */
    HEIGHT_LIMIT(321513, "The wayline altitude has exceeded the height limit of the drone.", true),
    
    /**
     * 거리 제한 (Distance Limit)
     * 웨이라인 거리가 드론의 거리 제한을 초과함
     * 
     * 오류 코드: 321514
     * 메시지: "The wayline distance has exceeded the limit of the drone."
     * 차단 여부: true (안전상의 이유로 차단)
     * 
     * 발생 원인:
     * - 웨이라인 경로의 총 거리가 드론의 최대 비행 거리를 초과
     * - 배터리 용량을 고려한 안전 거리를 초과
     */
    DISTANCE_LIMIT(321514, "The wayline distance has exceeded the limit of the drone.", true),

    /**
     * 비행 제한 구역 (Restricted Flight Area)
     * 웨이라인이 비행 제한 구역을 통과함
     * 
     * 오류 코드: 321515
     * 메시지: "The wayline passes through a restricted flight area."
     * 차단 여부: true (규제상의 이유로 차단)
     * 
     * 발생 원인:
     * - 공항 근처 비행 제한 구역
     * - 군사 시설 보호 구역
     * - 민감한 시설 보호 구역
     */
    RESTRICTED_FLIGHT_AREA(321515, "The wayline passes through a restricted flight area.", true),
    
    /**
     * SDR 연결 끊김 (SDR Disconnect)
     * Dock과 드론 간의 SDR 링크가 끊어짐
     * 
     * 오류 코드: 514120
     * 메시지: "The sdr link between the dock and the drone is disconnected."
     * 차단 여부: true (통신 오류로 인해 차단)
     * 
     * 발생 원인:
     * - 무선 통신 장애
     * - 하드웨어 오류
     * - 전자파 간섭
     */
    SDR_DISCONNECT(514120, "The sdr link between the dock and the drone is disconnected.", true),

    /**
     * 폭우 (Heavy Rain)
     * 폭우로 인해 비행이 방해됨
     * 
     * 오류 코드: 514134
     * 메시지: "Heavy rain prevented the flight."
     * 차단 여부: true (기상 조건으로 인해 차단)
     * 
     * 발생 원인:
     * - 강한 비가 내리는 경우
     * - 안전한 비행이 불가능한 기상 조건
     */
    HEAVY_RAIN(514134, "Heavy rain prevented the flight.", true),
    
    /**
     * 강풍 (Strong Wind)
     * 강풍으로 인해 비행이 방해됨
     * 
     * 오류 코드: 514135
     * 메시지: "Strong wind prevented the flight."
     * 차단 여부: true (기상 조건으로 인해 차단)
     * 
     * 발생 원인:
     * - 바람이 너무 강한 경우
     * - 드론의 안정적인 비행이 불가능한 풍속
     */
    STRONG_WIND(514135, "Strong wind prevented the flight.", true),
    
    /**
     * 전원 연결 끊김 (Power Disconnect)
     * Dock의 전원 공급이 끊어짐
     * 
     * 오류 코드: 514136
     * 메시지: "The dock's power supply is disconnected."
     * 차단 여부: true (전원 오류로 인해 차단)
     * 
     * 발생 원인:
     * - 전원 케이블 분리
     * - 정전
     * - 전원 공급 장치 오류
     */
    POWER_DISCONNECT(514136, "The dock's power supply is disconnected.", true),

    /**
     * 저온 (Low Temperature)
     * 환경의 저온으로 인해 비행이 방해됨
     * 
     * 오류 코드: 514137
     * 메시지: "The low temperature of the environment prevented flight."
     * 차단 여부: true (기상 조건으로 인해 차단)
     * 
     * 발생 원인:
     * - 온도가 너무 낮은 경우
     * - 배터리 성능 저하로 인한 안전상의 이유
     */
    LOW_TEMPERATURE(514137, "The low temperature of the environment prevented flight.", true),

    /**
     * 디버깅 중 (Debugging)
     * Dock이 디버깅 중임
     * 
     * 오류 코드: 514145
     * 메시지: "The dock is being debugged."
     * 차단 여부: true (디버깅 중이므로 차단)
     * 
     * 발생 원인:
     * - 시스템 디버깅 작업 중
     * - 개발자 모드 활성화
     */
    DEBUGGING(514145, "The dock is being debugged.", true),

    /**
     * 원격 디버깅 중 (Remote Debugging)
     * Dock이 원격으로 디버깅 중임
     * 
     * 오류 코드: 514146
     * 메시지: "The dock is being debugged remotely."
     * 차단 여부: true (원격 디버깅 중이므로 차단)
     * 
     * 발생 원인:
     * - 원격 디버깅 세션 활성화
     * - 외부에서 시스템 진단 중
     */
    REMOTE_DEBUGGING(514146, "The dock is being debugged remotely.", true),

    /**
     * Dock 업그레이드 중 (Dock Upgrading)
     * Dock이 업그레이드 중임
     * 
     * 오류 코드: 514147
     * 메시지: "The dock is being upgraded."
     * 차단 여부: true (업그레이드 중이므로 차단)
     * 
     * 발생 원인:
     * - 펌웨어 업그레이드 중
     * - 시스템 업데이트 중
     */
    DOCK_UPGRADING(514147, "The dock is being upgraded.", true),

    /**
     * Dock 작업 중 (Dock Working)
     * Dock이 작업 중이어서 새로운 작업을 수행할 수 없음
     * 
     * 오류 코드: 514148
     * 메시지: "The dock is working and cannot perform new tasks."
     * 차단 여부: true (다른 작업 중이므로 차단)
     * 
     * 발생 원인:
     * - 다른 시스템 작업 수행 중
     * - 리소스 부족
     */
    DOCK_WORKING(514148, "The dock is working and cannot perform new tasks.", true),

    /**
     * 알 수 없는 오류 (Unknown)
     * 알 수 없는 웨이라인 오류
     * 
     * 오류 코드: -1
     * 메시지: "Unknown wayline error."
     * 차단 여부: false (알 수 없는 오류이므로 차단하지 않음)
     * 
     * 발생 원인:
     * - 정의되지 않은 오류 코드
     * - 시스템 오류
     * - 통신 오류
     */
    UNKNOWN(-1, "Unknown wayline error.", false);
    
    /**
     * 오류 메시지
     * 사용자에게 표시되는 오류 설명 메시지
     */
    private String msg;

    /**
     * 오류 코드
     * 시스템에서 사용되는 고유한 오류 식별자
     */
    private int code;

    /**
     * 차단 여부
     * 해당 오류가 작업 실행을 차단하는지 여부
     * true: 작업 실행 차단, false: 작업 실행 허용
     */
    boolean block; 
    
    /**
     * 웨이라인 오류 코드 열거형 생성자
     * 
     * @param code 오류 코드 (정수)
     * @param msg 오류 메시지 (문자열)
     * @param block 차단 여부 (boolean)
     */
    WaylineErrorCodeEnum(int code, String msg, boolean block) {
        this.code = code;
        this.msg = msg;
        this.block = block;
    }

    /**
     * 오류 메시지 반환
     * IErrorInfo 인터페이스 구현
     * 
     * @return 오류 메시지
     */
    @Override
    public String getMessage() {
        return msg;
    }

    /**
     * 오류 코드 반환
     * IErrorInfo 인터페이스 구현
     * 
     * @return 오류 코드
     */
    @Override
    public Integer getCode() {
        return code;
    }

    /**
     * 차단 여부 확인
     * 
     * @return 차단 여부 (true: 차단, false: 허용)
     */
    public boolean isBlock() {
        return block;
    }

    /**
     * 오류 코드로부터 웨이라인 오류 코드 열거형 객체를 찾습니다.
     * 
     * @JsonCreator 어노테이션을 사용하여 JSON 역직렬화 시 자동 호출
     * 
     * @param code 찾을 오류 코드
     * @return 해당 오류 코드에 대응하는 열거형 객체, 없으면 UNKNOWN 반환
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static WaylineErrorCodeEnum find(int code) {
        return Arrays.stream(WaylineErrorCodeEnum.values())
                .filter(error -> error.code == code)
                .findAny()
                .orElse(UNKNOWN);
    }
}
