package com.dji.sdk.cloudapi.organization;

import java.util.List;

/**
 * 공항 바인딩 상태 요청 클래스
 * 
 * 이 클래스는 공항(도크)의 디바이스 바인딩 상태를 조회하기 위한 요청 데이터를 정의합니다.
 * 공항에 연결된 여러 디바이스들의 바인딩 상태 정보를 포함합니다.
 * 
 * 주요 구성 요소:
 * - devices: 바인딩 상태 응답 디바이스 목록
 * 
 * 이 클래스는 공항에 연결된 모든 디바이스의 조직 바인딩 상태를
 * 일괄적으로 조회하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public class AirportBindStatusRequest {

    /**
     * 바인딩 상태 응답 디바이스 목록
     * 
     * 공항에 연결된 디바이스들의 바인딩 상태 정보를 담은 목록입니다.
     * 각 디바이스의 시리얼 번호, 바인딩 상태, 조직 정보 등을 포함합니다.
     */
    private List<BindStatusResponseDevice> devices;

    public AirportBindStatusRequest() {
    }

    @Override
    public String toString() {
        return "AirportBindStatusRequest{" +
                "devices=" + devices +
                '}';
    }

    /**
     * 바인딩 상태 응답 디바이스 목록을 반환합니다.
     * 
     * @return 바인딩 상태 응답 디바이스 목록
     */
    public List<BindStatusResponseDevice> getDevices() {
        return devices;
    }

    /**
     * 바인딩 상태 응답 디바이스 목록을 설정합니다.
     * 
     * @param devices 바인딩 상태 응답 디바이스 목록
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public AirportBindStatusRequest setDevices(List<BindStatusResponseDevice> devices) {
        this.devices = devices;
        return this;
    }
}
