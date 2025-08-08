package com.dji.sdk.cloudapi.organization;

import com.dji.sdk.cloudapi.device.DeviceEnum;

/**
 * 조직 바인딩 디바이스 클래스
 * 
 * 이 클래스는 디바이스를 조직에 바인딩하기 위한 정보를 정의합니다.
 * 디바이스 바인딩 코드, 조직 ID, 디바이스 호출명, 시리얼 번호, 디바이스 모델 등을 포함합니다.
 * 
 * 주요 구성 요소:
 * - deviceBindingCode: 디바이스 바인딩 코드
 * - organizationId: 조직 ID
 * - deviceCallsign: 디바이스 호출명
 * - sn: 디바이스 시리얼 번호
 * - deviceModelKey: 디바이스 모델 열거형
 * 
 * 이 클래스는 디바이스를 특정 조직에 등록하고 관리하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/13
 */
public class OrganizationBindDevice {

    /**
     * 디바이스 바인딩 코드
     * 
     * 디바이스를 조직에 바인딩하기 위한 고유 코드입니다.
     * 보안을 위해 일회성으로 생성되며, 바인딩 완료 후 무효화됩니다.
     */
    private String deviceBindingCode;

    /**
     * 조직 ID
     * 
     * 디바이스를 바인딩할 대상 조직의 고유 식별자입니다.
     * 조직 내에서 디바이스를 관리하고 권한을 제어하는 데 사용됩니다.
     */
    private String organizationId;

    /**
     * 디바이스 호출명
     * 
     * 디바이스를 식별하기 위한 사용자 정의 호출명입니다.
     * 조직 내에서 디바이스를 쉽게 구분할 수 있도록 사용됩니다.
     * 예: "드론-001", "도크-A", "RC-메인"
     */
    private String deviceCallsign;

    /**
     * 디바이스 시리얼 번호
     * 
     * 디바이스의 고유 시리얼 번호입니다.
     * 드론, 도크, RC 등의 디바이스를 식별하는 데 사용됩니다.
     */
    private String sn;

    /**
     * 디바이스 모델 열거형
     * 
     * 디바이스의 제품 모델 정보입니다.
     * DeviceEnum을 사용하여 디바이스의 제품 타입을 식별합니다.
     */
    private DeviceEnum deviceModelKey;

    public OrganizationBindDevice() {
    }

    @Override
    public String toString() {
        return "OrganizationBindDevice{" +
                "deviceBindingCode='" + deviceBindingCode + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", deviceCallsign='" + deviceCallsign + '\'' +
                ", sn='" + sn + '\'' +
                ", deviceModelKey=" + deviceModelKey +
                '}';
    }

    /**
     * 디바이스 바인딩 코드를 반환합니다.
     * 
     * @return 디바이스 바인딩 코드
     */
    public String getDeviceBindingCode() {
        return deviceBindingCode;
    }

    /**
     * 디바이스 바인딩 코드를 설정합니다.
     * 
     * @param deviceBindingCode 디바이스 바인딩 코드
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public OrganizationBindDevice setDeviceBindingCode(String deviceBindingCode) {
        this.deviceBindingCode = deviceBindingCode;
        return this;
    }

    /**
     * 조직 ID를 반환합니다.
     * 
     * @return 조직 ID
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * 조직 ID를 설정합니다.
     * 
     * @param organizationId 조직 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public OrganizationBindDevice setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    /**
     * 디바이스 호출명을 반환합니다.
     * 
     * @return 디바이스 호출명
     */
    public String getDeviceCallsign() {
        return deviceCallsign;
    }

    /**
     * 디바이스 호출명을 설정합니다.
     * 
     * @param deviceCallsign 디바이스 호출명
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public OrganizationBindDevice setDeviceCallsign(String deviceCallsign) {
        this.deviceCallsign = deviceCallsign;
        return this;
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
     * 디바이스 시리얼 번호를 설정합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public OrganizationBindDevice setSn(String sn) {
        this.sn = sn;
        return this;
    }

    /**
     * 디바이스 모델 열거형을 반환합니다.
     * 
     * @return 디바이스 모델 열거형
     */
    public DeviceEnum getDeviceModelKey() {
        return deviceModelKey;
    }

    /**
     * 디바이스 모델 열거형을 설정합니다.
     * 
     * @param deviceModelKey 디바이스 모델 열거형
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public OrganizationBindDevice setDeviceModelKey(DeviceEnum deviceModelKey) {
        this.deviceModelKey = deviceModelKey;
        return this;
    }
}
