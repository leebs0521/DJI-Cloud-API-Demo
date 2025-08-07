package com.dji.sdk.cloudapi.device;

/**
 * 토폴로지 업데이트 서브 디바이스 클래스
 * 
 * 이 클래스는 토폴로지 업데이트 시 서브 디바이스의 정보를 담습니다.
 * 시리얼 번호, 도메인, 타입, 서브타입, 인덱스, 시크릿, 논스, 씽 버전 등을 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/26
 */
public class UpdateTopoSubDevice {

    /**
     * 디바이스 시리얼 번호
     */
    private String sn;

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
     * 제어 소스 인덱스 (A, B 등)
     */
    private ControlSourceEnum index;

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
     * 기본 생성자
     */
    public UpdateTopoSubDevice() {
    }

    @Override
    public String toString() {
        return "UpdateTopoSubDevice{" +
                "sn='" + sn + '\'' +
                ", domain=" + domain +
                ", type=" + type +
                ", subType=" + subType +
                ", index=" + index +
                ", deviceSecret='" + deviceSecret + '\'' +
                ", nonce='" + nonce + '\'' +
                ", thingVersion=" + thingVersion +
                '}';
    }

    /**
     * 디바이스 시리얼 번호를 반환합니다.
     * 
     * @return 디바이스 시리얼 번호
     */
    public String getSn() {
        return sn;
    }

    /**
     * 디바이스 시리얼 번호를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param sn 설정할 디바이스 시리얼 번호
     * @return 현재 UpdateTopoSubDevice 객체
     */
    public UpdateTopoSubDevice setSn(String sn) {
        this.sn = sn;
        return this;
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
     * @return 현재 UpdateTopoSubDevice 객체
     */
    public UpdateTopoSubDevice setDomain(DeviceDomainEnum domain) {
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
     * @return 현재 UpdateTopoSubDevice 객체
     */
    public UpdateTopoSubDevice setType(DeviceTypeEnum type) {
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
     * @return 현재 UpdateTopoSubDevice 객체
     */
    public UpdateTopoSubDevice setSubType(DeviceSubTypeEnum subType) {
        this.subType = subType;
        return this;
    }

    /**
     * 제어 소스 인덱스를 반환합니다.
     * 
     * @return 제어 소스 인덱스
     */
    public ControlSourceEnum getIndex() {
        return index;
    }

    /**
     * 제어 소스 인덱스를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param index 설정할 제어 소스 인덱스
     * @return 현재 UpdateTopoSubDevice 객체
     */
    public UpdateTopoSubDevice setIndex(ControlSourceEnum index) {
        this.index = index;
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
     * @return 현재 UpdateTopoSubDevice 객체
     */
    public UpdateTopoSubDevice setDeviceSecret(String deviceSecret) {
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
     * @return 현재 UpdateTopoSubDevice 객체
     */
    public UpdateTopoSubDevice setNonce(String nonce) {
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
     * @return 현재 UpdateTopoSubDevice 객체
     */
    public UpdateTopoSubDevice setThingVersion(String thingVersion) {
        this.thingVersion = thingVersion;
        return this;
    }
}
