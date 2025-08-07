package com.dji.sdk.cloudapi.device;


import java.util.List;

/**
 * 토폴로지 업데이트 클래스
 * 
 * 이 클래스는 디바이스 토폴로지 업데이트 정보를 담습니다.
 * 디바이스 도메인, 타입, 서브타입, 시크릿, 논스, 씽 버전, 서브 디바이스 목록 등을 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/26
 */
public class UpdateTopo {

    /**
     * 디바이스 도메인 (DRONE, PAYLOAD, REMOTER_CONTROL, DOCK)
     */
    private DeviceDomainEnum domain;

    /**
     * 디바이스 타입 (M350, M300, Z30, XT2 등)
     */
    private DeviceTypeEnum type;

    /**
     * 디바이스 서브타입 (ZERO, ONE, TWO 등)
     */
    private DeviceSubTypeEnum subType;

    /**
     * 디바이스 시크릿
     */
    private String deviceSecret;

    /**
     * 논스 (일회성 난수)
     */
    private String nonce;

    /**
     * 씽 버전
     */
    private String thingVersion;

    /**
     * 서브 디바이스 목록
     */
    private List<UpdateTopoSubDevice> subDevices;

    /**
     * 기본 생성자
     */
    public UpdateTopo() {
    }

    @Override
    public String toString() {
        return "UpdateTopo{" +
                "domain=" + domain +
                ", type=" + type +
                ", subType=" + subType +
                ", deviceSecret='" + deviceSecret + '\'' +
                ", nonce='" + nonce + '\'' +
                ", thingVersion=" + thingVersion +
                ", subDevices=" + subDevices +
                '}';
    }

    /**
     * 디바이스 도메인을 반환합니다.
     * 
     * @return 디바이스 도메인
     */
    public DeviceDomainEnum getDomain() {
        return domain;
    }

    /**
     * 디바이스 도메인을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param domain 설정할 디바이스 도메인
     * @return 현재 UpdateTopo 객체
     */
    public UpdateTopo setDomain(DeviceDomainEnum domain) {
        this.domain = domain;
        return this;
    }

    /**
     * 디바이스 타입을 반환합니다.
     * 
     * @return 디바이스 타입
     */
    public DeviceTypeEnum getType() {
        return type;
    }

    /**
     * 디바이스 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param type 설정할 디바이스 타입
     * @return 현재 UpdateTopo 객체
     */
    public UpdateTopo setType(DeviceTypeEnum type) {
        this.type = type;
        return this;
    }

    /**
     * 디바이스 서브타입을 반환합니다.
     * 
     * @return 디바이스 서브타입
     */
    public DeviceSubTypeEnum getSubType() {
        return subType;
    }

    /**
     * 디바이스 서브타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param subType 설정할 디바이스 서브타입
     * @return 현재 UpdateTopo 객체
     */
    public UpdateTopo setSubType(DeviceSubTypeEnum subType) {
        this.subType = subType;
        return this;
    }

    /**
     * 디바이스 시크릿을 반환합니다.
     * 
     * @return 디바이스 시크릿
     */
    public String getDeviceSecret() {
        return deviceSecret;
    }

    /**
     * 디바이스 시크릿을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param deviceSecret 설정할 디바이스 시크릿
     * @return 현재 UpdateTopo 객체
     */
    public UpdateTopo setDeviceSecret(String deviceSecret) {
        this.deviceSecret = deviceSecret;
        return this;
    }

    /**
     * 논스를 반환합니다.
     * 
     * @return 논스 (일회성 난수)
     */
    public String getNonce() {
        return nonce;
    }

    /**
     * 논스를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param nonce 설정할 논스 (일회성 난수)
     * @return 현재 UpdateTopo 객체
     */
    public UpdateTopo setNonce(String nonce) {
        this.nonce = nonce;
        return this;
    }

    /**
     * 씽 버전을 반환합니다.
     * 
     * @return 씽 버전
     */
    public String getThingVersion() {
        return thingVersion;
    }

    /**
     * 씽 버전을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param thingVersion 설정할 씽 버전
     * @return 현재 UpdateTopo 객체
     */
    public UpdateTopo setThingVersion(String thingVersion) {
        this.thingVersion = thingVersion;
        return this;
    }

    /**
     * 서브 디바이스 목록을 반환합니다.
     * 
     * @return 서브 디바이스 목록
     */
    public List<UpdateTopoSubDevice> getSubDevices() {
        return subDevices;
    }

    /**
     * 서브 디바이스 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param subDevices 설정할 서브 디바이스 목록
     * @return 현재 UpdateTopo 객체
     */
    public UpdateTopo setSubDevices(List<UpdateTopoSubDevice> subDevices) {
        this.subDevices = subDevices;
        return this;
    }
}
