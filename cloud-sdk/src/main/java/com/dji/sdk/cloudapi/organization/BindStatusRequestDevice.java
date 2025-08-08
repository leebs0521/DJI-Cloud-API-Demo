package com.dji.sdk.cloudapi.organization;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * 바인딩 상태 요청 디바이스 클래스
 * 
 * 이 클래스는 디바이스의 조직 바인딩 상태를 조회하기 위한 요청 데이터를 정의합니다.
 * 디바이스의 바인딩 상태, 조직 정보, 디바이스 호출명 등을 포함합니다.
 * 
 * 주요 구성 요소:
 * - sn: 디바이스 시리얼 번호
 * - deviceBindOrganization: 디바이스 조직 바인딩 여부
 * - organizationId: 조직 ID
 * - organizationName: 조직명
 * - deviceCallsign: 디바이스 호출명
 * 
 * 이 클래스는 디바이스가 특정 조직에 바인딩되어 있는지
 * 확인하고 관련 정보를 조회하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/14
 */
public class BindStatusRequestDevice {

    /**
     * 디바이스 시리얼 번호
     * 
     * 바인딩 상태를 조회할 디바이스의 고유 시리얼 번호입니다.
     * 드론, 도크, RC 등의 디바이스를 식별하는 데 사용됩니다.
     */
    @NotNull
    private String sn;

    /**
     * 디바이스 조직 바인딩 여부
     * 
     * 디바이스가 조직에 바인딩되어 있는지 여부를 나타냅니다.
     * true: 조직에 바인딩됨, false: 조직에 바인딩되지 않음
     */
    @NotNull
    @JsonProperty("is_device_bind_organization")
    private Boolean deviceBindOrganization;

    /**
     * 조직 ID
     * 
     * 디바이스가 바인딩된 조직의 고유 식별자입니다.
     * 바인딩되지 않은 경우 빈 값이 됩니다.
     */
    @NotNull
    private String organizationId;

    /**
     * 조직명
     * 
     * 디바이스가 바인딩된 조직의 이름입니다.
     * 사용자가 조직을 쉽게 식별할 수 있도록 사용됩니다.
     */
    @NotNull
    private String organizationName;

    /**
     * 디바이스 호출명
     * 
     * 디바이스를 식별하기 위한 사용자 정의 호출명입니다.
     * 조직 내에서 디바이스를 쉽게 구분할 수 있도록 사용됩니다.
     */
    @NotNull
    private String deviceCallsign;

    public BindStatusRequestDevice() {
    }

    @Override
    public String toString() {
        return "BindStatusRequestDevice{" +
                "sn='" + sn + '\'' +
                ", deviceBindOrganization=" + deviceBindOrganization +
                ", organizationId='" + organizationId + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", deviceCallsign='" + deviceCallsign + '\'' +
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
     * 디바이스 시리얼 번호를 설정합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public BindStatusRequestDevice setSn(String sn) {
        this.sn = sn;
        return this;
    }

    /**
     * 디바이스 조직 바인딩 여부를 반환합니다.
     * 
     * @return 디바이스 조직 바인딩 여부
     */
    public Boolean getDeviceBindOrganization() {
        return deviceBindOrganization;
    }

    /**
     * 디바이스 조직 바인딩 여부를 설정합니다.
     * 
     * @param deviceBindOrganization 디바이스 조직 바인딩 여부
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public BindStatusRequestDevice setDeviceBindOrganization(Boolean deviceBindOrganization) {
        this.deviceBindOrganization = deviceBindOrganization;
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
    public BindStatusRequestDevice setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    /**
     * 조직명을 반환합니다.
     * 
     * @return 조직명
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * 조직명을 설정합니다.
     * 
     * @param organizationName 조직명
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public BindStatusRequestDevice setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
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
    public BindStatusRequestDevice setDeviceCallsign(String deviceCallsign) {
        this.deviceCallsign = deviceCallsign;
        return this;
    }
}
