package com.dji.sdk.cloudapi.device;

/**
 * 에어컨 정보 클래스
 * 
 * 이 클래스는 도크의 에어컨 상태 정보를 담습니다.
 * 에어컨 상태와 스위치 시간을 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public class AirConditioner {

    /**
     * 에어컨 상태
     */
    private AirConditionerStateEnum airConditionerState;

    /**
     * 스위치 시간(남은 대기 시간) (타임스탬프)
     */
    private Integer switchTime;

    /**
     * 기본 생성자
     */
    public AirConditioner() {
    }

    @Override
    public String toString() {
        return "AirConditioner{" +
                "airConditionerState=" + airConditionerState +
                ", switchTime=" + switchTime +
                '}';
    }

    /**
     * 에어컨 상태를 반환합니다.
     * 
     * @return 에어컨 상태
     */
    public AirConditionerStateEnum getAirConditionerState() {
        return airConditionerState;
    }

    /**
     * 에어컨 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param airConditionerState 설정할 에어컨 상태
     * @return 현재 AirConditioner 객체
     */
    public AirConditioner setAirConditionerState(AirConditionerStateEnum airConditionerState) {
        this.airConditionerState = airConditionerState;
        return this;
    }

    /**
     * 스위치 시간을 반환합니다.
     * 
     * @return 스위치 시간 (타임스탬프)
     */
    public Integer getSwitchTime() {
        return switchTime;
    }

    /**
     * 스위치 시간을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param switchTime 설정할 스위치 시간 (타임스탬프)
     * @return 현재 AirConditioner 객체
     */
    public AirConditioner setSwitchTime(Integer switchTime) {
        this.switchTime = switchTime;
        return this;
    }
}
