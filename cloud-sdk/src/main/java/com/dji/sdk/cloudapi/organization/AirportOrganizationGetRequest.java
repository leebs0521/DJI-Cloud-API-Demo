package com.dji.sdk.cloudapi.organization;

/**
 * 공항 조직 조회 요청 클래스
 * 
 * 이 클래스는 공항(도크)의 조직 정보를 조회하기 위한 요청 데이터를 정의합니다.
 * 디바이스 바인딩 코드와 조직 ID를 사용하여 조직 정보를 조회합니다.
 * 
 * 주요 구성 요소:
 * - deviceBindingCode: 디바이스 바인딩 코드
 * - organizationId: 조직 ID
 * 
 * 이 클래스는 공항이 속한 조직의 정보를 조회하는 데 사용됩니다.
 * 디바이스 바인딩 과정에서 조직 정보를 확인할 때 활용됩니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/13
 */
public class AirportOrganizationGetRequest {

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
     * 조회할 조직의 고유 식별자입니다.
     * 조직의 상세 정보를 조회하는 데 사용됩니다.
     */
    private String organizationId;

    public AirportOrganizationGetRequest() {
    }

    @Override
    public String toString() {
        return "AirportOrganizationGetRequest{" +
                "deviceBindingCode='" + deviceBindingCode + '\'' +
                ", organizationId='" + organizationId + '\'' +
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
    public AirportOrganizationGetRequest setDeviceBindingCode(String deviceBindingCode) {
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
    public AirportOrganizationGetRequest setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }
}
