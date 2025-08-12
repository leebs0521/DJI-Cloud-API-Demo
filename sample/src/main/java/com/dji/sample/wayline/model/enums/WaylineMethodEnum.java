package com.dji.sample.wayline.model.enums;

import lombok.Getter;

/**
 * DJI Cloud API 웨이라인 작업 메서드 열거형
 * 
 * 이 열거형은 DJI Dock과의 통신에서 사용되는 웨이라인 작업 관련 메서드명을 정의합니다.
 * MQTT 프로토콜을 통해 DJI Dock에 웨이라인 작업 명령을 전송할 때 사용되는 메서드명들을 포함합니다.
 * 
 * 주요 기능:
 * - 웨이라인 작업 준비 메서드명 정의
 * - 웨이라인 작업 실행 메서드명 정의
 * - 웨이라인 작업 제어 메서드명 정의 (취소, 일시정지, 재개)
 * - MQTT 통신을 위한 메서드명 매핑
 * 
 * 사용 시나리오:
 * - DJI Dock에 웨이라인 작업 명령 전송
 * - MQTT 토픽을 통한 작업 제어
 * - 웨이라인 작업 라이프사이클 관리
 * 
 * 작업 흐름:
 * FLIGHT_TASK_PREPARE → FLIGHT_TASK_EXECUTE → FLIGHT_TASK_CANCEL/PAUSE/RESUME
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/14
 */
@Getter
public enum WaylineMethodEnum {

    /**
     * 비행 작업 준비 (Flight Task Prepare)
     * 웨이라인 작업 실행을 위한 사전 준비 단계
     * 
     * 메서드명: "flighttask_prepare"
     * 
     * 수행 작업:
     * - 웨이라인 파일 다운로드 및 검증
     * - 드론 및 페이로드 상태 확인
     * - 작업 실행 조건 검증
     * - 시스템 리소스 할당
     * 
     * 사용 시점:
     * - 웨이라인 작업 생성 직후
     * - 실제 비행 전 사전 준비 단계
     */
    FLIGHT_TASK_PREPARE("flighttask_prepare"),

    /**
     * 비행 작업 실행 (Flight Task Execute)
     * 웨이라인 작업을 실제로 실행하는 단계
     * 
     * 메서드명: "flighttask_execute"
     * 
     * 수행 작업:
     * - 드론 이륙 및 웨이포인트 이동
     * - 미디어 촬영 및 데이터 수집
     * - 웨이라인 경로 따라 자동 비행
     * - 작업 완료 후 드론 착륙
     * 
     * 사용 시점:
     * - 준비 단계 완료 후
     * - 실제 비행 작업 시작
     */
    FLIGHT_TASK_EXECUTE("flighttask_execute"),

    /**
     * 비행 작업 취소 (Flight Task Cancel)
     * 실행 중인 웨이라인 작업을 취소하는 명령
     * 
     * 메서드명: "flighttask_undo"
     * 
     * 수행 작업:
     * - 현재 실행 중인 작업 중단
     * - 드론 안전 착륙
     * - 작업 리소스 정리
     * - 작업 상태를 CANCELLED로 변경
     * 
     * 사용 시점:
     * - 사용자가 작업을 수동으로 취소할 때
     * - 안전상의 이유로 작업을 중단해야 할 때
     * - 시스템 오류로 인한 강제 중단
     */
    FLIGHT_TASK_CANCEL("flighttask_undo"),

    /**
     * 비행 작업 일시 정지 (Flight Task Pause)
     * 실행 중인 웨이라인 작업을 일시적으로 정지하는 명령
     * 
     * 메서드명: "flighttask_pause"
     * 
     * 수행 작업:
     * - 현재 위치에서 드론 호버링
     * - 작업 진행 상황 보존
     * - 작업 상태를 PAUSED로 변경
     * - 재개 가능한 상태로 유지
     * 
     * 사용 시점:
     * - 기상 조건 악화로 인한 임시 대기
     * - 시스템 점검을 위한 일시 정지
     * - 사용자가 수동으로 작업을 일시 정지할 때
     */
    FLIGHT_TASK_PAUSE("flighttask_pause"),

    /**
     * 비행 작업 재개 (Flight Task Resume)
     * 일시 정지된 웨이라인 작업을 다시 시작하는 명령
     * 
     * 메서드명: "flighttask_recovery"
     * 
     * 수행 작업:
     * - 일시 정지된 지점부터 작업 재개
     * - 이전 진행 상황 복원
     * - 작업 상태를 IN_PROGRESS로 변경
     * - 웨이포인트 이동 및 미디어 촬영 계속
     * 
     * 사용 시점:
     * - 일시 정지된 작업을 다시 시작할 때
     * - 기상 조건이 개선되어 작업을 계속할 때
     * - 시스템 점검이 완료되어 작업을 재개할 때
     */
    FLIGHT_TASK_RESUME("flighttask_recovery");

    /**
     * MQTT 메서드명
     * DJI Dock과의 통신에서 사용되는 실제 메서드명
     */
    private String method;

    /**
     * 웨이라인 작업 메서드 열거형 생성자
     * 
     * @param method MQTT 메서드명
     */
    WaylineMethodEnum(String method) {
        this.method = method;
    }
}
