package com.dji.sdk.cloudapi.device;

/**
 * 드론 유지보수 상태 클래스
 * 
 * 이 클래스는 드론의 유지보수 상태 정보를 담습니다.
 * 마지막 유지보수 비행 횟수, 마지막 유지보수 비행 시간, 마지막 유지보수 시간, 마지막 유지보수 타입, 상태 등을 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public class DroneMaintainStatus {

    /**
     * 마지막 유지보수 비행 횟수
     */
    private Integer lastMaintainFlightSorties;

    /**
     * 마지막 유지보수 비행 시간 (분)
     */
    private Integer lastMaintainFlightTime;

    /**
     * 마지막 유지보수 시간 (타임스탬프)
     */
    private Long lastMaintainTime;

    /**
     * 마지막 유지보수 타입
     */
    private MaintainTypeEnum lastMaintainType;

    /**
     * 유지보수 상태 (true: 유지보수 필요, false: 유지보수 불필요)
     */
    private Boolean state;

    /**
     * 기본 생성자
     */
    public DroneMaintainStatus() {
    }

    @Override
    public String toString() {
        return "DroneMaintainStatus{" +
                "lastMaintainFlightSorties=" + lastMaintainFlightSorties +
                ", lastMaintainFlightTime=" + lastMaintainFlightTime +
                ", lastMaintainTime=" + lastMaintainTime +
                ", lastMaintainType=" + lastMaintainType +
                ", state=" + state +
                '}';
    }

    /**
     * 마지막 유지보수 비행 횟수를 반환합니다.
     * 
     * @return 마지막 유지보수 비행 횟수
     */
    public Integer getLastMaintainFlightSorties() {
        return lastMaintainFlightSorties;
    }

    /**
     * 마지막 유지보수 비행 횟수를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param lastMaintainFlightSorties 설정할 마지막 유지보수 비행 횟수
     * @return 현재 DroneMaintainStatus 객체
     */
    public DroneMaintainStatus setLastMaintainFlightSorties(Integer lastMaintainFlightSorties) {
        this.lastMaintainFlightSorties = lastMaintainFlightSorties;
        return this;
    }

    /**
     * 마지막 유지보수 비행 시간을 반환합니다.
     * 
     * @return 마지막 유지보수 비행 시간 (분)
     */
    public Integer getLastMaintainFlightTime() {
        return lastMaintainFlightTime;
    }

    /**
     * 마지막 유지보수 비행 시간을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param lastMaintainFlightTime 설정할 마지막 유지보수 비행 시간 (분)
     * @return 현재 DroneMaintainStatus 객체
     */
    public DroneMaintainStatus setLastMaintainFlightTime(Integer lastMaintainFlightTime) {
        this.lastMaintainFlightTime = lastMaintainFlightTime;
        return this;
    }

    /**
     * 마지막 유지보수 시간을 반환합니다.
     * 
     * @return 마지막 유지보수 시간 (타임스탬프)
     */
    public Long getLastMaintainTime() {
        return lastMaintainTime;
    }

    /**
     * 마지막 유지보수 시간을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param lastMaintainTime 설정할 마지막 유지보수 시간 (타임스탬프)
     * @return 현재 DroneMaintainStatus 객체
     */
    public DroneMaintainStatus setLastMaintainTime(Long lastMaintainTime) {
        this.lastMaintainTime = lastMaintainTime;
        return this;
    }

    /**
     * 마지막 유지보수 타입을 반환합니다.
     * 
     * @return 마지막 유지보수 타입
     */
    public MaintainTypeEnum getLastMaintainType() {
        return lastMaintainType;
    }

    /**
     * 마지막 유지보수 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param lastMaintainType 설정할 마지막 유지보수 타입
     * @return 현재 DroneMaintainStatus 객체
     */
    public DroneMaintainStatus setLastMaintainType(MaintainTypeEnum lastMaintainType) {
        this.lastMaintainType = lastMaintainType;
        return this;
    }

    /**
     * 유지보수 상태를 반환합니다.
     * 
     * @return 유지보수 상태 (true: 유지보수 필요, false: 유지보수 불필요)
     */
    public Boolean getState() {
        return state;
    }

    /**
     * 유지보수 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param state 설정할 유지보수 상태
     * @return 현재 DroneMaintainStatus 객체
     */
    public DroneMaintainStatus setState(Boolean state) {
        this.state = state;
        return this;
    }
}
