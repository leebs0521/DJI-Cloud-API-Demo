package com.dji.sdk.cloudapi.hms;

/**
 * 디바이스 HMS 인자 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 HMS(Health Management System)와 관련된 추가 정보를 담는 클래스입니다.
 * 컴포넌트 인덱스, 센서 인덱스, 알람 ID 등을 포함하여 HMS의 상세 정보를 제공합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/7/6
 */
public class DeviceHmsArgs {

    /**
     * 컴포넌트 인덱스
     * HMS가 발생한 컴포넌트의 인덱스
     */
    private Long componentIndex;

    /**
     * 센서 인덱스
     * HMS가 발생한 센서의 인덱스
     */
    private Integer sensorIndex;

    /**
     * 알람 ID
     * HMS 알람의 고유 식별자
     */
    private Integer alarmId;

    /**
     * 기본 생성자
     */
    public DeviceHmsArgs() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "DeviceHmsArgs{" +
                "componentIndex=" + componentIndex +
                ", sensorIndex=" + sensorIndex +
                ", alarmId=" + alarmId +
                '}';
    }

    /**
     * 컴포넌트 인덱스를 반환합니다.
     * 
     * @return 컴포넌트 인덱스
     */
    public Long getComponentIndex() {
        return componentIndex;
    }

    /**
     * 컴포넌트 인덱스를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param componentIndex 설정할 컴포넌트 인덱스
     * @return 현재 DeviceHmsArgs 객체
     */
    public DeviceHmsArgs setComponentIndex(Long componentIndex) {
        this.componentIndex = componentIndex;
        return this;
    }

    /**
     * 센서 인덱스를 반환합니다.
     * 
     * @return 센서 인덱스
     */
    public Integer getSensorIndex() {
        return sensorIndex;
    }

    /**
     * 센서 인덱스를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param sensorIndex 설정할 센서 인덱스
     * @return 현재 DeviceHmsArgs 객체
     */
    public DeviceHmsArgs setSensorIndex(Integer sensorIndex) {
        this.sensorIndex = sensorIndex;
        return this;
    }

    /**
     * 알람 ID를 반환합니다.
     * 
     * @return 알람 ID
     */
    public Integer getAlarmId() {
        return alarmId;
    }

    /**
     * 알람 ID를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param alarmId 설정할 알람 ID
     * @return 현재 DeviceHmsArgs 객체
     */
    public DeviceHmsArgs setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
        return this;
    }
}
