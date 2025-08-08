package com.dji.sdk.cloudapi.wayline;

import java.util.List;

/**
 * 비행 작업 준비 완료 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업의 준비 완료 상태를 나타내는 데이터를 정의합니다.
 * 작업 준비 조건을 충족한 작업 ID들의 목록을 포함하여
 * 실행 가능한 비행 작업들을 식별할 수 있습니다.
 * 
 * 주요 구성 요소:
 * - flightIds: 준비 완료된 작업 ID 목록
 * 
 * 이 클래스는 비행 작업이 실행 준비가 완료되었음을
 * 알리고 실행 가능한 작업들을 관리하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/6
 */
public class FlighttaskReady {

    /**
     * 작업 준비 조건을 충족한 작업 ID 목록
     * 
     * 현재 작업 준비 조건을 충족한 비행 작업들의 ID 목록입니다.
     * 날씨, 시간, 위치 등의 조건이 만족되어 실행 가능한
     * 작업들을 식별하는 데 사용됩니다.
     * 
     * 예시: ["task_001", "task_002", "task_003"]
     */
    private List<String> flightIds;

    public FlighttaskReady() {
    }

    @Override
    public String toString() {
        return "FlighttaskReady{" +
                "flightIds=" + flightIds +
                '}';
    }

    /**
     * 준비 완료된 작업 ID 목록을 반환합니다.
     * 
     * @return 준비 완료된 작업 ID 목록
     */
    public List<String> getFlightIds() {
        return flightIds;
    }

    /**
     * 준비 완료된 작업 ID 목록을 설정합니다.
     * 
     * @param flightIds 준비 완료된 작업 ID 목록
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskReady setFlightIds(List<String> flightIds) {
        this.flightIds = flightIds;
        return this;
    }
}
