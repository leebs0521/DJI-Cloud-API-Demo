package com.dji.sdk.cloudapi.device;

import java.util.List;

/**
 * 드론 배터리 정보 클래스
 * 
 * 이 클래스는 드론의 배터리 상태 정보를 담습니다.
 * 개별 배터리 정보, 전체 용량, 착륙 전력, 남은 비행 시간, 홈 복귀 전력 등을 포함합니다.
 * 
 * @author sean
 * @version 0.3
 * @date 2022/1/27
 */
public class DroneBattery {

    /**
     * 개별 배터리 정보 목록
     */
    private List<Battery> batteries;

    /**
     * 전체 배터리 용량 퍼센트 (%)
     */
    private Integer capacityPercent;

    /**
     * 착륙 전력 (%)
     */
    private Integer landingPower;

    /**
     * 남은 비행 시간 (분)
     */
    private Integer remainFlightTime;

    /**
     * 홈 복귀 전력 (%)
     */
    private Integer returnHomePower;

    /**
     * 기본 생성자
     */
    public DroneBattery() {
    }

    @Override
    public String toString() {
        return "DroneBattery{" +
                "batteries=" + batteries +
                ", capacityPercent=" + capacityPercent +
                ", landingPower=" + landingPower +
                ", remainFlightTime=" + remainFlightTime +
                ", returnHomePower=" + returnHomePower +
                '}';
    }

    /**
     * 개별 배터리 정보 목록을 반환합니다.
     * 
     * @return 배터리 정보 목록
     */
    public List<Battery> getBatteries() {
        return batteries;
    }

    /**
     * 개별 배터리 정보 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param batteries 설정할 배터리 정보 목록
     * @return 현재 DroneBattery 객체
     */
    public DroneBattery setBatteries(List<Battery> batteries) {
        this.batteries = batteries;
        return this;
    }

    /**
     * 전체 배터리 용량 퍼센트를 반환합니다.
     * 
     * @return 전체 배터리 용량 퍼센트 (%)
     */
    public Integer getCapacityPercent() {
        return capacityPercent;
    }

    /**
     * 전체 배터리 용량 퍼센트를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param capacityPercent 설정할 전체 배터리 용량 퍼센트 (%)
     * @return 현재 DroneBattery 객체
     */
    public DroneBattery setCapacityPercent(Integer capacityPercent) {
        this.capacityPercent = capacityPercent;
        return this;
    }

    /**
     * 착륙 전력을 반환합니다.
     * 
     * @return 착륙 전력 (%)
     */
    public Integer getLandingPower() {
        return landingPower;
    }

    /**
     * 착륙 전력을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param landingPower 설정할 착륙 전력 (%)
     * @return 현재 DroneBattery 객체
     */
    public DroneBattery setLandingPower(Integer landingPower) {
        this.landingPower = landingPower;
        return this;
    }

    /**
     * 남은 비행 시간을 반환합니다.
     * 
     * @return 남은 비행 시간 (분)
     */
    public Integer getRemainFlightTime() {
        return remainFlightTime;
    }

    /**
     * 남은 비행 시간을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param remainFlightTime 설정할 남은 비행 시간 (분)
     * @return 현재 DroneBattery 객체
     */
    public DroneBattery setRemainFlightTime(Integer remainFlightTime) {
        this.remainFlightTime = remainFlightTime;
        return this;
    }

    /**
     * 홈 복귀 전력을 반환합니다.
     * 
     * @return 홈 복귀 전력 (%)
     */
    public Integer getReturnHomePower() {
        return returnHomePower;
    }

    /**
     * 홈 복귀 전력을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param returnHomePower 설정할 홈 복귀 전력 (%)
     * @return 현재 DroneBattery 객체
     */
    public DroneBattery setReturnHomePower(Integer returnHomePower) {
        this.returnHomePower = returnHomePower;
        return this;
    }
}
