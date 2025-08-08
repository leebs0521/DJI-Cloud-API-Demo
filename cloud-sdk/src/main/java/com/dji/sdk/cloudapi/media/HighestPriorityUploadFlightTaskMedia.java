package com.dji.sdk.cloudapi.media;

/**
 * 최우선순위 비행 작업 미디어 업로드 데이터 클래스
 * 
 * 이 클래스는 특정 비행 작업의 미디어 파일을 최우선순위로 업로드하기 위한
 * 데이터를 정의합니다. 비상 상황이나 중요한 미디어 파일을 즉시 업로드할 때 사용됩니다.
 * 
 * 주요 구성 요소:
 * - flightId: 비행 작업 ID
 * 
 * 이 클래스는 중요한 비행 작업의 미디어 파일을 다른 모든 파일보다
 * 먼저 업로드하도록 최우선순위를 설정하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/27
 */
public class HighestPriorityUploadFlightTaskMedia {

    /**
     * 비행 작업 ID
     * 
     * 최우선순위로 업로드할 미디어 파일이 속한 비행 작업의 고유 식별자입니다.
     * 이 비행 작업의 모든 미디어 파일이 최우선순위로 처리됩니다.
     */
    private String flightId;

    public HighestPriorityUploadFlightTaskMedia() {
    }

    @Override
    public String toString() {
        return "HighestPriorityUploadFlightTaskMedia{" +
                "flightId='" + flightId + '\'' +
                '}';
    }

    /**
     * 비행 작업 ID를 반환합니다.
     * 
     * @return 비행 작업 ID
     */
    public String getFlightId() {
        return flightId;
    }

    /**
     * 비행 작업 ID를 설정합니다.
     * 
     * @param flightId 비행 작업 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public HighestPriorityUploadFlightTaskMedia setFlightId(String flightId) {
        this.flightId = flightId;
        return this;
    }
}
