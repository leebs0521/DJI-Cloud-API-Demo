package com.dji.sdk.cloudapi.wayline;

/**
 * 비행 작업 리소스 조회 요청 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업에 필요한 리소스를 조회하기 위한 요청을 정의합니다.
 * 작업 ID를 기반으로 웨이라인 파일, 설정 정보 등의 리소스를
 * 조회할 수 있도록 합니다.
 * 
 * 주요 구성 요소:
 * - flightId: 비행 작업 ID
 * 
 * 이 클래스는 비행 작업 실행에 필요한 리소스 정보를
 * 조회하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/25
 */
public class FlighttaskResourceGetRequest {

    /**
     * 비행 작업 ID
     * 
     * 리소스를 조회할 비행 작업의 고유 식별자입니다.
     * 이 ID를 기반으로 해당 작업에 필요한 웨이라인 파일,
     * 설정 정보 등의 리소스를 조회합니다.
     */
    private String flightId;

    public FlighttaskResourceGetRequest() {
    }

    @Override
    public String toString() {
        return "FlighttaskResourceGetRequest{" +
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
    public FlighttaskResourceGetRequest setFlightId(String flightId) {
        this.flightId = flightId;
        return this;
    }
}
