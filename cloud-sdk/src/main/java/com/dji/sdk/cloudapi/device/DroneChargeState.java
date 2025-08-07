package com.dji.sdk.cloudapi.device;

/**
 * 드론 충전 상태 클래스
 * 
 * 이 클래스는 드론의 충전 상태 정보를 담습니다.
 * 충전 상태와 배터리 용량 퍼센트를 포함합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/5/11
 */
public class DroneChargeState {

    /**
     * 충전 상태 (true: 충전 중, false: 충전 중 아님)
     */
    private Boolean state;

    /**
     * 배터리 용량 퍼센트 (%)
     */
    private Integer capacityPercent;

    /**
     * 기본 생성자
     */
    public DroneChargeState() {
    }

    @Override
    public String toString() {
        return "DroneChargeState{" +
                "state=" + state +
                ", capacityPercent=" + capacityPercent +
                '}';
    }

    /**
     * 충전 상태를 반환합니다.
     * 
     * @return 충전 상태 (true: 충전 중, false: 충전 중 아님)
     */
    public Boolean getState() {
        return state;
    }

    /**
     * 충전 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param state 설정할 충전 상태
     * @return 현재 DroneChargeState 객체
     */
    public DroneChargeState setState(Boolean state) {
        this.state = state;
        return this;
    }

    /**
     * 배터리 용량 퍼센트를 반환합니다.
     * 
     * @return 배터리 용량 퍼센트 (%)
     */
    public Integer getCapacityPercent() {
        return capacityPercent;
    }

    /**
     * 배터리 용량 퍼센트를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param capacityPercent 설정할 배터리 용량 퍼센트 (%)
     * @return 현재 DroneChargeState 객체
     */
    public DroneChargeState setCapacityPercent(Integer capacityPercent) {
        this.capacityPercent = capacityPercent;
        return this;
    }
}
