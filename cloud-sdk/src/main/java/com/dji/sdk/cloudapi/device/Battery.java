package com.dji.sdk.cloudapi.device;

/**
 * 배터리 정보 클래스
 * 
 * 이 클래스는 개별 배터리의 상세 정보를 담습니다.
 * 펌웨어 버전, 배터리 인덱스, 충전 횟수, 용량, 온도, 전압 등을 포함합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/24
 */
public class Battery {

    /**
     * 배터리 펌웨어 버전
     */
    private String firmwareVersion;

    /**
     * 배터리 인덱스 (배터리 위치 식별)
     */
    private BatteryIndexEnum index;

    /**
     * 배터리 충전 횟수
     */
    private Integer loopTimes;

    /**
     * 배터리 용량 퍼센트 (%)
     */
    private Integer capacityPercent;

    /**
     * 배터리 시리얼 번호
     */
    private String sn;

    /**
     * 배터리 서브타입
     */
    private Integer subType;

    /**
     * 배터리 온도 (섭씨)
     */
    private Float temperature;

    /**
     * 배터리 타입
     */
    private Integer type;

    /**
     * 배터리 전압 (mV)
     */
    private Integer voltage;

    /**
     * 고전압 저장 일수
     */
    private Integer highVoltageStorageDays;

    /**
     * 기본 생성자
     */
    public Battery() {
    }

    @Override
    public String toString() {
        return "Battery{" +
                "firmwareVersion='" + firmwareVersion + '\'' +
                ", index=" + index +
                ", loopTimes=" + loopTimes +
                ", capacityPercent=" + capacityPercent +
                ", sn='" + sn + '\'' +
                ", subType=" + subType +
                ", temperature=" + temperature +
                ", type=" + type +
                ", voltage=" + voltage +
                ", highVoltageStorageDays=" + highVoltageStorageDays +
                '}';
    }

    /**
     * 배터리 펌웨어 버전을 반환합니다.
     * 
     * @return 배터리 펌웨어 버전
     */
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * 배터리 펌웨어 버전을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param firmwareVersion 설정할 배터리 펌웨어 버전
     * @return 현재 Battery 객체
     */
    public Battery setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
        return this;
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
     * @return 현재 Battery 객체
     */
    public Battery setIndex(BatteryIndexEnum index) {
        this.index = index;
        return this;
    }

    /**
     * 배터리 충전 횟수를 반환합니다.
     * 
     * @return 배터리 충전 횟수
     */
    public Integer getLoopTimes() {
        return loopTimes;
    }

    /**
     * 배터리 충전 횟수를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param loopTimes 설정할 배터리 충전 횟수
     * @return 현재 Battery 객체
     */
    public Battery setLoopTimes(Integer loopTimes) {
        this.loopTimes = loopTimes;
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
     * @return 현재 Battery 객체
     */
    public Battery setCapacityPercent(Integer capacityPercent) {
        this.capacityPercent = capacityPercent;
        return this;
    }

    /**
     * 배터리 시리얼 번호를 반환합니다.
     * 
     * @return 배터리 시리얼 번호
     */
    public String getSn() {
        return sn;
    }

    /**
     * 배터리 시리얼 번호를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param sn 설정할 배터리 시리얼 번호
     * @return 현재 Battery 객체
     */
    public Battery setSn(String sn) {
        this.sn = sn;
        return this;
    }

    /**
     * 배터리 서브타입을 반환합니다.
     * 
     * @return 배터리 서브타입
     */
    public Integer getSubType() {
        return subType;
    }

    /**
     * 배터리 서브타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param subType 설정할 배터리 서브타입
     * @return 현재 Battery 객체
     */
    public Battery setSubType(Integer subType) {
        this.subType = subType;
        return this;
    }

    /**
     * 배터리 온도를 반환합니다.
     * 
     * @return 배터리 온도 (섭씨)
     */
    public Float getTemperature() {
        return temperature;
    }

    /**
     * 배터리 온도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param temperature 설정할 배터리 온도 (섭씨)
     * @return 현재 Battery 객체
     */
    public Battery setTemperature(Float temperature) {
        this.temperature = temperature;
        return this;
    }

    /**
     * 배터리 타입을 반환합니다.
     * 
     * @return 배터리 타입
     */
    public Integer getType() {
        return type;
    }

    /**
     * 배터리 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param type 설정할 배터리 타입
     * @return 현재 Battery 객체
     */
    public Battery setType(Integer type) {
        this.type = type;
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
     * @return 현재 Battery 객체
     */
    public Battery setVoltage(Integer voltage) {
        this.voltage = voltage;
        return this;
    }

    /**
     * 고전압 저장 일수를 반환합니다.
     * 
     * @return 고전압 저장 일수
     */
    public Integer getHighVoltageStorageDays() {
        return highVoltageStorageDays;
    }

    /**
     * 고전압 저장 일수를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param highVoltageStorageDays 설정할 고전압 저장 일수
     * @return 현재 Battery 객체
     */
    public Battery setHighVoltageStorageDays(Integer highVoltageStorageDays) {
        this.highVoltageStorageDays = highVoltageStorageDays;
        return this;
    }
}