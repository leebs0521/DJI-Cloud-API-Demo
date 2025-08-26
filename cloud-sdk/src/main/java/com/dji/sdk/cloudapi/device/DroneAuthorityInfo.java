package com.dji.sdk.cloudapi.device;

import java.util.List;

/**
 * 드론 권한 정보 클래스
 * 
 * 이 클래스는 드론의 제어 권한과 관련된 정보를 관리합니다.
 * 제어 소스, 잠금 상태, 페이로드 제어 소스 정보를 포함합니다.
 * 
 * @author leebs0521
 * @version 1.0
 * @date 2025/8/26
 */
public class DroneAuthorityInfo {
    
    /**
     * 드론의 제어 소스
     */
    private ControlSourceEnum controlSource;
    
    /**
     * 드론의 잠금 상태
     * true: 잠김, false: 잠금 해제
     */
    private Boolean locked;
    
    /**
     * 드론의 페이로드 제어 소스 목록
     */
    private List<DockPayloadControlSource> payloads;

    /**
     * 기본 생성자
     */
    public DroneAuthorityInfo() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "DroneAuthorityInfo{" +
                "controlSource=" + controlSource +
                ", locked=" + locked +
                ", payloads=" + payloads +
                '}';
    }

    /**
     * 제어 소스를 반환합니다.
     * 
     * @return 드론의 제어 소스
     */
    public ControlSourceEnum getControlSource() {
        return controlSource;
    }

    /**
     * 제어 소스를 설정합니다.
     * 
     * @param controlSource 설정할 제어 소스
     */
    public void setControlSource(ControlSourceEnum controlSource) {
        this.controlSource = controlSource;
    }

    /**
     * 잠금 상태를 반환합니다.
     * 
     * @return true: 잠김, false: 잠금 해제
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     * 잠금 상태를 설정합니다.
     * 
     * @param locked 설정할 잠금 상태 (true: 잠김, false: 잠금 해제)
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    /**
     * 페이로드 제어 소스 목록을 반환합니다.
     * 
     * @return 드론의 페이로드 제어 소스 목록
     */
    public List<DockPayloadControlSource> getPayloads() {
        return payloads;
    }

    /**
     * 페이로드 제어 소스 목록을 설정합니다.
     * 
     * @param payloads 설정할 페이로드 제어 소스 목록
     */
    public void setPayloads(List<DockPayloadControlSource> payloads) {
        this.payloads = payloads;
    }
}
