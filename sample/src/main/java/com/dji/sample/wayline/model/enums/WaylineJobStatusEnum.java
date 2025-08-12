package com.dji.sample.wayline.model.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * DJI Cloud API 웨이라인 작업 상태 열거형
 * 
 * 이 열거형은 웨이라인 작업의 현재 상태를 정의하는 상수들을 제공합니다.
 * 웨이라인 작업의 전체 라이프사이클에서 발생할 수 있는 모든 상태를 포함하며,
 * 각 상태의 종료 여부를 나타내는 플래그도 함께 관리합니다.
 * 
 * 주요 기능:
 * - 웨이라인 작업 상태 정의 및 관리
 * - 작업 종료 여부 판단
 * - 상태값으로부터 열거형 객체 조회
 * 
 * 상태 라이프사이클:
 * PENDING → IN_PROGRESS → SUCCESS/FAILED/CANCEL
 *           ↓
 *         PAUSED → IN_PROGRESS (재개 시)
 * 
 * @author sean
 * @version 1.3
 * @date 2022/9/26
 */
@Getter
public enum WaylineJobStatusEnum {

    /**
     * 대기 중 (Pending)
     * 웨이라인 작업이 생성되었으나 아직 실행되지 않은 상태
     * 
     * 상태값: 1
     * 종료 여부: false (작업이 계속 진행될 수 있음)
     * 
     * 발생 시점:
     * - 웨이라인 작업 생성 직후
     * - 실행 조건이 만족되지 않아 대기 중인 경우
     * - 예약된 작업이 실행 시간을 기다리는 경우
     */
    PENDING(1, false),

    /**
     * 실행 중 (In Progress)
     * 웨이라인 작업이 현재 실행 중인 상태
     * 
     * 상태값: 2
     * 종료 여부: false (작업이 계속 진행 중)
     * 
     * 발생 시점:
     * - 웨이라인 작업이 실제로 시작된 후
     * - 드론이 웨이포인트를 따라 비행 중인 경우
     * - 미디어 촬영이나 데이터 수집이 진행 중인 경우
     */
    IN_PROGRESS(2, false),

    /**
     * 성공 완료 (Success)
     * 웨이라인 작업이 성공적으로 완료된 상태
     * 
     * 상태값: 3
     * 종료 여부: true (작업이 정상적으로 종료됨)
     * 
     * 발생 시점:
     * - 모든 웨이포인트를 성공적으로 완료한 경우
     * - 드론이 안전하게 착륙한 경우
     * - 모든 미디어 파일이 성공적으로 생성된 경우
     */
    SUCCESS(3, true),

    /**
     * 취소됨 (Cancel)
     * 웨이라인 작업이 사용자에 의해 취소된 상태
     * 
     * 상태값: 4
     * 종료 여부: true (작업이 취소로 인해 종료됨)
     * 
     * 발생 시점:
     * - 사용자가 작업을 수동으로 취소한 경우
     * - 시스템에서 안전상의 이유로 작업을 중단한 경우
     * - 예약된 작업이 실행 전에 취소된 경우
     */
    CANCEL(4, true),

    /**
     * 실패 (Failed)
     * 웨이라인 작업이 오류로 인해 실패한 상태
     * 
     * 상태값: 5
     * 종료 여부: true (작업이 오류로 인해 종료됨)
     * 
     * 발생 시점:
     * - 드론 하드웨어 오류 발생
     * - 통신 오류로 인한 작업 중단
     * - 기상 조건 악화로 인한 강제 착륙
     * - 배터리 부족으로 인한 비상 착륙
     */
    FAILED(5, true),

    /**
     * 일시 정지 (Paused)
     * 웨이라인 작업이 일시적으로 정지된 상태
     * 
     * 상태값: 6
     * 종료 여부: false (작업이 재개될 수 있음)
     * 
     * 발생 시점:
     * - 사용자가 작업을 일시 정지한 경우
     * - 기상 조건 악화로 인한 임시 대기
     * - 시스템 점검으로 인한 일시 정지
     */
    PAUSED(6, false),

    /**
     * 알 수 없음 (Unknown)
     * 웨이라인 작업의 상태를 알 수 없는 상태
     * 
     * 상태값: -1
     * 종료 여부: true (알 수 없는 상태이므로 종료로 간주)
     * 
     * 발생 시점:
     * - 시스템 오류로 인한 상태 정보 손실
     * - 지원되지 않는 상태값 수신
     * - 통신 오류로 인한 상태 확인 불가
     */
    UNKNOWN(-1, true);

    /**
     * 상태값
     * 데이터베이스에 저장되는 정수 형태의 상태값
     */
    int val;

    /**
     * 종료 여부
     * 해당 상태가 작업의 최종 종료 상태인지 여부
     * true: 작업이 종료된 상태 (더 이상 진행되지 않음)
     * false: 작업이 진행 중이거나 재개 가능한 상태
     */
    Boolean end;

    /**
     * 웨이라인 작업 상태 열거형 생성자
     * 
     * @param val 상태값 (정수)
     * @param end 종료 여부 (boolean)
     */
    WaylineJobStatusEnum(int val, boolean end) {
        this.end = end;
        this.val = val;
    }

    /**
     * 상태값으로부터 웨이라인 작업 상태 열거형 객체를 찾습니다.
     * 
     * @param val 찾을 상태값
     * @return 해당 상태값에 대응하는 열거형 객체, 없으면 UNKNOWN 반환
     */
    public static WaylineJobStatusEnum find(int val) {
        return Arrays.stream(WaylineJobStatusEnum.values())
                .filter(statue -> statue.val == val)
                .findAny()
                .orElse(UNKNOWN);
    }
}
