package com.dji.sample.wayline.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * DJI Cloud API 웨이라인 작업 제어 상태 열거형
 * 
 * 이 열거형은 웨이라인 작업의 제어 명령을 정의하는 상수들을 제공합니다.
 * 실행 중인 웨이라인 작업에 대해 일시 정지 및 재개 명령을 내릴 때 사용됩니다.
 * 
 * 주요 기능:
 * - 웨이라인 작업 일시 정지 명령 정의
 * - 웨이라인 작업 재개 명령 정의
 * - JSON 직렬화를 위한 값 매핑
 * 
 * 사용 시나리오:
 * - 실행 중인 웨이라인 작업 일시 정지
 * - 일시 정지된 웨이라인 작업 재개
 * - 작업 제어 명령 전송
 * 
 * 제어 흐름:
 * IN_PROGRESS → PAUSE → RESUME → IN_PROGRESS
 * 
 * @author sean
 * @version 1.3
 * @date 2023/2/1
 */
public enum WaylineTaskStatusEnum {

    /**
     * 일시 정지 (Pause)
     * 실행 중인 웨이라인 작업을 일시적으로 정지하는 명령
     * 
     * ordinal 값: 0
     * 
     * 사용 시점:
     * - 기상 조건 악화로 인한 임시 대기
     * - 시스템 점검을 위한 일시 정지
     * - 사용자가 수동으로 작업을 일시 정지하고 싶을 때
     * 
     * 동작:
     * - 드론이 현재 위치에서 호버링 상태로 대기
     * - 작업 진행 상황은 보존됨
     * - RESUME 명령으로 언제든지 재개 가능
     */
    PAUSE,

    /**
     * 재개 (Resume)
     * 일시 정지된 웨이라인 작업을 다시 시작하는 명령
     * 
     * ordinal 값: 1
     * 
     * 사용 시점:
     * - 일시 정지된 작업을 다시 시작하고 싶을 때
     * - 기상 조건이 개선되어 작업을 계속하고 싶을 때
     * - 시스템 점검이 완료되어 작업을 재개하고 싶을 때
     * 
     * 동작:
     * - 일시 정지된 지점부터 작업 재개
     * - 이전 진행 상황이 그대로 유지됨
     * - 새로운 웨이라인 작업이 아닌 기존 작업의 연속
     */
    RESUME;

    /**
     * JSON 직렬화를 위한 값 반환
     * 
     * @JsonValue 어노테이션을 사용하여 JSON 직렬화 시 ordinal 값을 사용
     * 
     * @return 열거형의 ordinal 값 (0: PAUSE, 1: RESUME)
     */
    @JsonValue
    public int getVal() {
        return ordinal();
    }
}
