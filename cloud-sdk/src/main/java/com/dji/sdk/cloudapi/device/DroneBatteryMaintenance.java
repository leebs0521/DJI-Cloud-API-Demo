package com.dji.sdk.cloudapi.device;

/**
 * 드론 배터리 유지보수 클래스
 * 
 * 이 클래스는 드론 배터리의 유지보수 정보를 관리합니다.
 * 배터리 인덱스, 용량 퍼센트, 전압, 온도 등의 배터리 상태 정보를 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public class DroneBatteryMaintenance {

    /**
     * 배터리 인덱스
     */
    private BatteryIndexEnum index;

    /**
     * 배터리 용량 퍼센트 (%)
     */
    private Integer capacityPercent;

    /**
     * 배터리 전압 (mV)
     */
    private Integer voltage;

    /**
     * 배터리 온도 (°C)
     */
    private Float temperature;

    /**
     * 기본 생성자
     */
    public DroneBatteryMaintenance() {
    }

    @Override
    public String toString() {
        return "DroneBatteryMaintenance{" +
                "index=" + index +
                ", capacityPercent=" + capacityPercent +
                ", voltage=" + voltage +
                ", temperature=" + temperature +
                '}';
    }

    /**
     * 배터리 인덱스를 반환합니다.
     * 
     * @return 배터리 인덱스
     */
    public BatteryIndexEnum getIndex() {
        return index;
    }

    /**
     * 배터리 인덱스를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param index 설정할 배터리 인덱스
     * @return 현재 DroneBatteryMaintenance 객체
     */
    public DroneBatteryMaintenance setIndex(BatteryIndexEnum index) {
        this.index = index;
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
     * @return 현재 DroneBatteryMaintenance 객체
     */
    public DroneBatteryMaintenance setCapacityPercent(Integer capacityPercent) {
        this.capacityPercent = capacityPercent;
        return this;
    }

    /**
     * 배터리 전압을 반환합니다.
     * 
     * @return 배터리 전압 (mV)
     */
    public Integer getVoltage() {
        return voltage;
    }

    /**
     * 배터리 전압을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param voltage 설정할 배터리 전압 (mV)
     * @return 현재 DroneBatteryMaintenance 객체
     */
    public DroneBatteryMaintenance setVoltage(Integer voltage) {
        this.voltage = voltage;
        return this;
    }

    /**
     * 배터리 온도를 반환합니다.
     * 
     * @return 배터리 온도 (°C)
     */
    public Float getTemperature() {
        return temperature;
    }

    /**
     * 배터리 온도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param temperature 설정할 배터리 온도 (°C)
     * @return 현재 DroneBatteryMaintenance 객체
     */
    public DroneBatteryMaintenance setTemperature(Float temperature) {
        this.temperature = temperature;
        return this;
    }
}
