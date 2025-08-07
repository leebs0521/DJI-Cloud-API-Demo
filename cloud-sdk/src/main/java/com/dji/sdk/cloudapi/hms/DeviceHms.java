package com.dji.sdk.cloudapi.hms;

import com.dji.sdk.cloudapi.device.DeviceEnum;

/**
 * 디바이스 HMS 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 개별 디바이스의 HMS(Health Management System) 정보를 담는 클래스입니다.
 * 디바이스의 상태 코드, 디바이스 타입, 긴급성, 비행 상태, 레벨, 모듈, 인자 등을 포함하여
 * 디바이스의 건강 상태를 종합적으로 관리합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/7/6
 */
public class DeviceHms {

    /**
     * HMS 코드
     * 디바이스의 HMS 상태를 나타내는 고유 코드
     */
    private String code;

    /**
     * 디바이스 타입
     * HMS가 발생한 디바이스의 타입
     */
    private DeviceEnum deviceType;

    /**
     * 긴급성 여부
     * HMS가 긴급한 상황인지 여부
     */
    private Boolean imminent;

    /**
     * 비행 중 여부
     * 디바이스가 현재 비행 중인지 여부
     */
    private Boolean inTheSky;

    /**
     * HMS 레벨
     * HMS의 심각도 레벨 (정보, 알림, 경보)
     */
    private HmsLevelEnum level;

    /**
     * HMS 모듈
     * HMS가 발생한 모듈 (비행 미션, 디바이스 관리, 미디어, HMS)
     */
    private HmsModuleEnum module;

    /**
     * HMS 인자
     * HMS와 관련된 추가 정보 (컴포넌트 인덱스, 센서 인덱스, 알람 ID 등)
     */
    private DeviceHmsArgs args;

    /**
     * 기본 생성자
     */
    public DeviceHms() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "DeviceHms{" +
                "code='" + code + '\'' +
                ", deviceType=" + deviceType +
                ", imminent=" + imminent +
                ", inTheSky=" + inTheSky +
                ", level=" + level +
                ", module=" + module +
                ", args=" + args +
                '}';
    }

    /**
     * HMS 코드를 반환합니다.
     * 
     * @return HMS 코드
     */
    public String getCode() {
        return code;
    }

    /**
     * HMS 코드를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param code 설정할 HMS 코드
     * @return 현재 DeviceHms 객체
     */
    public DeviceHms setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * 디바이스 타입을 반환합니다.
     * 
     * @return 디바이스 타입
     */
    public DeviceEnum getDeviceType() {
        return deviceType;
    }

    /**
     * 디바이스 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param deviceType 설정할 디바이스 타입
     * @return 현재 DeviceHms 객체
     */
    public DeviceHms setDeviceType(DeviceEnum deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    /**
     * 긴급성 여부를 반환합니다.
     * 
     * @return 긴급성 여부
     */
    public Boolean getImminent() {
        return imminent;
    }

    /**
     * 긴급성 여부를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param imminent 설정할 긴급성 여부
     * @return 현재 DeviceHms 객체
     */
    public DeviceHms setImminent(Boolean imminent) {
        this.imminent = imminent;
        return this;
    }

    /**
     * 비행 중 여부를 반환합니다.
     * 
     * @return 비행 중 여부
     */
    public Boolean getInTheSky() {
        return inTheSky;
    }

    /**
     * 비행 중 여부를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param inTheSky 설정할 비행 중 여부
     * @return 현재 DeviceHms 객체
     */
    public DeviceHms setInTheSky(Boolean inTheSky) {
        this.inTheSky = inTheSky;
        return this;
    }

    /**
     * HMS 레벨을 반환합니다.
     * 
     * @return HMS 레벨
     */
    public HmsLevelEnum getLevel() {
        return level;
    }

    /**
     * HMS 레벨을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param level 설정할 HMS 레벨
     * @return 현재 DeviceHms 객체
     */
    public DeviceHms setLevel(HmsLevelEnum level) {
        this.level = level;
        return this;
    }

    /**
     * HMS 모듈을 반환합니다.
     * 
     * @return HMS 모듈
     */
    public HmsModuleEnum getModule() {
        return module;
    }

    /**
     * HMS 모듈을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param module 설정할 HMS 모듈
     * @return 현재 DeviceHms 객체
     */
    public DeviceHms setModule(HmsModuleEnum module) {
        this.module = module;
        return this;
    }

    /**
     * HMS 인자를 반환합니다.
     * 
     * @return HMS 인자
     */
    public DeviceHmsArgs getArgs() {
        return args;
    }

    /**
     * HMS 인자를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param args 설정할 HMS 인자
     * @return 현재 DeviceHms 객체
     */
    public DeviceHms setArgs(DeviceHmsArgs args) {
        this.args = args;
        return this;
    }
}
