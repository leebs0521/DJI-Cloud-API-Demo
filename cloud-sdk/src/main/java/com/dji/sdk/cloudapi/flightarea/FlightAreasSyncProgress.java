package com.dji.sdk.cloudapi.flightarea;

/**
 * 비행 구역 동기화 진행 상황 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 비행 구역 동기화의 진행 상황을 담는 클래스입니다.
 * 동기화 상태, 반환 코드, 커스텀 비행 구역 파일을 포함하여 비행 구역 동기화의 진행 상황을 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/17
 */
public class FlightAreasSyncProgress {

    /**
     * 동기화 상태
     * 비행 구역 동기화의 현재 상태
     */
    private FlightAreaSyncStatusEnum status;

    /**
     * 반환 코드
     * 비행 구역 동기화의 결과 코드
     */
    private FlightAreaSyncReasonEnum reason;

    /**
     * 커스텀 비행 구역 파일
     * 동기화된 커스텀 비행 구역 파일 정보
     */
    private FlightAreaFile file;

    /**
     * 기본 생성자
     */
    public FlightAreasSyncProgress() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FlightAreasSyncProgress{" +
                "status=" + status +
                ", reason=" + reason +
                ", file=" + file +
                '}';
    }

    /**
     * 동기화 상태를 반환합니다.
     * 
     * @return 동기화 상태
     */
    public FlightAreaSyncStatusEnum getStatus() {
        return status;
    }

    /**
     * 동기화 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param status 설정할 동기화 상태
     * @return 현재 FlightAreasSyncProgress 객체
     */
    public FlightAreasSyncProgress setStatus(FlightAreaSyncStatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * 반환 코드를 반환합니다.
     * 
     * @return 반환 코드
     */
    public FlightAreaSyncReasonEnum getReason() {
        return reason;
    }

    /**
     * 반환 코드를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param reason 설정할 반환 코드
     * @return 현재 FlightAreasSyncProgress 객체
     */
    public FlightAreasSyncProgress setReason(FlightAreaSyncReasonEnum reason) {
        this.reason = reason;
        return this;
    }

    /**
     * 커스텀 비행 구역 파일을 반환합니다.
     * 
     * @return 커스텀 비행 구역 파일
     */
    public FlightAreaFile getFile() {
        return file;
    }

    /**
     * 커스텀 비행 구역 파일을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param file 설정할 커스텀 비행 구역 파일
     * @return 현재 FlightAreasSyncProgress 객체
     */
    public FlightAreasSyncProgress setFile(FlightAreaFile file) {
        this.file = file;
        return this;
    }
}
