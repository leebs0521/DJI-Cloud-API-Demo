package com.dji.sdk.cloudapi.device;

import java.util.List;

/**
 * RC 드론 제어 소스 클래스
 * 
 * 이 클래스는 RC에 연결된 드론의 제어 소스 정보를 관리합니다.
 * 제어 소스, 홈 포인트 좌표, 배터리 경고 임계값, 페이로드 제어 소스 등을 포함합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/18
 * @version 0.1
 */
public class RcDroneControlSource {

    /**
     * 제어 소스 열거형
     */
    private ControlSourceEnum controlSource;

    /**
     * 홈 포인트 위도
     */
    private Float homeLatitude;

    /**
     * 홈 포인트 경도
     */
    private Float homeLongitude;

    /**
     * 낮은 배터리 경고 임계값 (%)
     */
    private Integer lowBatteryWarningThreshold;

    /**
     * 심각한 낮은 배터리 경고 임계값 (%)
     */
    private Integer seriousLowBatteryWarningThreshold;

    /**
     * 페이로드 제어 소스 목록
     */
    private List<RcPayloadControlSource> payloads;

    /**
     * 기본 생성자
     */
    public RcDroneControlSource() {
    }

    @Override
    public String toString() {
        return "RcDroneControlSource{" +
                "controlSource=" + controlSource +
                ", homeLatitude=" + homeLatitude +
                ", homeLongitude=" + homeLongitude +
                ", lowBatteryWarningThreshold=" + lowBatteryWarningThreshold +
                ", seriousLowBatteryWarningThreshold=" + seriousLowBatteryWarningThreshold +
                ", payloads=" + payloads +
                '}';
    }

    /**
     * 제어 소스를 반환합니다.
     * 
     * @return 제어 소스 열거형
     */
    public ControlSourceEnum getControlSource() {
        return controlSource;
    }

    /**
     * 제어 소스를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param controlSource 설정할 제어 소스
     * @return 현재 RcDroneControlSource 객체
     */
    public RcDroneControlSource setControlSource(ControlSourceEnum controlSource) {
        this.controlSource = controlSource;
        return this;
    }

    /**
     * 홈 포인트 위도를 반환합니다.
     * 
     * @return 홈 포인트 위도
     */
    public Float getHomeLatitude() {
        return homeLatitude;
    }

    /**
     * 홈 포인트 위도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param homeLatitude 설정할 홈 포인트 위도
     * @return 현재 RcDroneControlSource 객체
     */
    public RcDroneControlSource setHomeLatitude(Float homeLatitude) {
        this.homeLatitude = homeLatitude;
        return this;
    }

    /**
     * 홈 포인트 경도를 반환합니다.
     * 
     * @return 홈 포인트 경도
     */
    public Float getHomeLongitude() {
        return homeLongitude;
    }

    /**
     * 홈 포인트 경도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param homeLongitude 설정할 홈 포인트 경도
     * @return 현재 RcDroneControlSource 객체
     */
    public RcDroneControlSource setHomeLongitude(Float homeLongitude) {
        this.homeLongitude = homeLongitude;
        return this;
    }

    /**
     * 낮은 배터리 경고 임계값을 반환합니다.
     * 
     * @return 낮은 배터리 경고 임계값 (%)
     */
    public Integer getLowBatteryWarningThreshold() {
        return lowBatteryWarningThreshold;
    }

    /**
     * 낮은 배터리 경고 임계값을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param lowBatteryWarningThreshold 설정할 낮은 배터리 경고 임계값 (%)
     * @return 현재 RcDroneControlSource 객체
     */
    public RcDroneControlSource setLowBatteryWarningThreshold(Integer lowBatteryWarningThreshold) {
        this.lowBatteryWarningThreshold = lowBatteryWarningThreshold;
        return this;
    }

    /**
     * 심각한 낮은 배터리 경고 임계값을 반환합니다.
     * 
     * @return 심각한 낮은 배터리 경고 임계값 (%)
     */
    public Integer getSeriousLowBatteryWarningThreshold() {
        return seriousLowBatteryWarningThreshold;
    }

    /**
     * 심각한 낮은 배터리 경고 임계값을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param seriousLowBatteryWarningThreshold 설정할 심각한 낮은 배터리 경고 임계값 (%)
     * @return 현재 RcDroneControlSource 객체
     */
    public RcDroneControlSource setSeriousLowBatteryWarningThreshold(Integer seriousLowBatteryWarningThreshold) {
        this.seriousLowBatteryWarningThreshold = seriousLowBatteryWarningThreshold;
        return this;
    }

    /**
     * 페이로드 제어 소스 목록을 반환합니다.
     * 
     * @return 페이로드 제어 소스 목록
     */
    public List<RcPayloadControlSource> getPayloads() {
        return payloads;
    }

    /**
     * 페이로드 제어 소스 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param payloads 설정할 페이로드 제어 소스 목록
     * @return 현재 RcDroneControlSource 객체
     */
    public RcDroneControlSource setPayloads(List<RcPayloadControlSource> payloads) {
        this.payloads = payloads;
        return this;
    }
}