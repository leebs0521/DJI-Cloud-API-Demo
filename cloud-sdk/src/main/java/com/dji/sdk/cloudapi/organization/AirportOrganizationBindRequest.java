package com.dji.sdk.cloudapi.organization;

import java.util.List;

/**
 * 공항 조직 바인딩 요청 클래스
 * 
 * 이 클래스는 공항(도크)의 디바이스들을 조직에 바인딩하기 위한 요청 데이터를 정의합니다.
 * 공항에 연결된 여러 디바이스들을 일괄적으로 조직에 바인딩할 때 사용됩니다.
 * 
 * 주요 구성 요소:
 * - bindDevices: 조직 바인딩 디바이스 목록
 * 
 * 이 클래스는 공항에 연결된 모든 디바이스를 특정 조직에
 * 일괄적으로 바인딩하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public class AirportOrganizationBindRequest {

    /**
     * 조직 바인딩 디바이스 목록
     * 
     * 공항에 연결된 디바이스들을 조직에 바인딩하기 위한 정보를 담은 목록입니다.
     * 각 디바이스의 바인딩 코드, 조직 ID, 호출명, 시리얼 번호, 모델 정보 등을 포함합니다.
     */
    private List<OrganizationBindDevice> bindDevices;

    public AirportOrganizationBindRequest() {
    }

    @Override
    public String toString() {
        return "AirportOrganizationBindRequest{" +
                "bindDevices=" + bindDevices +
                '}';
    }

    /**
     * 조직 바인딩 디바이스 목록을 반환합니다.
     * 
     * @return 조직 바인딩 디바이스 목록
     */
    public List<OrganizationBindDevice> getBindDevices() {
        return bindDevices;
    }

    /**
     * 조직 바인딩 디바이스 목록을 설정합니다.
     * 
     * @param bindDevices 조직 바인딩 디바이스 목록
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public AirportOrganizationBindRequest setBindDevices(List<OrganizationBindDevice> bindDevices) {
        this.bindDevices = bindDevices;
        return this;
    }
}
