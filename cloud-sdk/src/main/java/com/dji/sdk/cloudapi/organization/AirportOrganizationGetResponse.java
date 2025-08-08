package com.dji.sdk.cloudapi.organization;

import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 공항 조직 조회 응답 클래스
 * 
 * 이 클래스는 공항(도크)의 조직 정보 조회 결과를 정의합니다.
 * BaseModel을 상속받아 유효성 검사를 지원하며, 조직명 정보를 포함합니다.
 * 
 * 주요 구성 요소:
 * - organizationName: 조직명
 * 
 * 이 클래스는 공항이 속한 조직의 정보를 응답으로 반환하는 데 사용됩니다.
 * 디바이스 바인딩 과정에서 조직 정보를 확인할 때 활용됩니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/13
 */
public class AirportOrganizationGetResponse extends BaseModel {

    /**
     * 조직명
     * 
     * 공항이 속한 조직의 이름입니다.
     * 사용자가 조직을 쉽게 식별할 수 있도록 사용됩니다.
     */
    @NotNull
    private String organizationName;

    public AirportOrganizationGetResponse() {
    }

    @Override
    public String toString() {
        return "AirportOrganizationGetResponse{" +
                "organizationName='" + organizationName + '\'' +
                '}';
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
    public AirportOrganizationGetResponse setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
        return this;
    }
}
