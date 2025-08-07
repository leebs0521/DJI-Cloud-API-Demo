package com.dji.sdk.cloudapi.device;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 백업 배터리 정보 클래스
 * 
 * 이 클래스는 도크의 백업 배터리 정보를 담습니다.
 * 전압, 온도, 배터리 스위치 상태 등을 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/3
 */
public class BackupBattery {

    /**
     * 배터리 전압 (mV)
     */
    private Integer voltage;

    /**
     * 배터리 온도 (°C)
     */
    private Float temperature;

    /**
     * 배터리 스위치 상태 (true: 켜짐, false: 꺼짐)
     */
    @JsonProperty("switch")
    private Boolean batterySwitch;

    /**
     * 기본 생성자
     */
    public BackupBattery() {
    }

    @Override
    public String toString() {
        return "BackupBattery{" +
                "voltage=" + voltage +
                ", temperature=" + temperature +
                ", batterySwitch=" + batterySwitch +
                '}';
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
     * @return 현재 BackupBattery 객체
     */
    public BackupBattery setVoltage(Integer voltage) {
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
     * @return 현재 BackupBattery 객체
     */
    public BackupBattery setTemperature(Float temperature) {
        this.temperature = temperature;
        return this;
    }

    /**
     * 배터리 스위치 상태를 반환합니다.
     * 
     * @return 배터리 스위치 상태 (true: 켜짐, false: 꺼짐)
     */
    public Boolean getBatterySwitch() {
        return batterySwitch;
    }

    /**
     * 배터리 스위치 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param batterySwitch 설정할 배터리 스위치 상태
     * @return 현재 BackupBattery 객체
     */
    public BackupBattery setBatterySwitch(Boolean batterySwitch) {
        this.batterySwitch = batterySwitch;
        return this;
    }
}
