package com.dji.sdk.cloudapi.device;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 대체 착륙 지점 클래스
 * 
 * 이 클래스는 드론의 대체 착륙 지점 정보를 담습니다.
 * 위도, 경도, 안전 착륙 고도, 설정 여부 등을 포함합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/5/11
 */
public class AlternateLandPoint {

    /**
     * 위도
     */
    private Float latitude;

    /**
     * 경도
     */
    private Float longitude;

    /**
     * 안전 착륙 고도 (미터)
     */
    private Float safeLandHeight;

    /**
     * 대체 착륙 지점 설정 여부
     */
    @JsonProperty("is_configured")
    private Boolean configured;

    /**
     * 기본 생성자
     */
    public AlternateLandPoint() {
    }

    @Override
    public String toString() {
        return "AlternateLandPoint{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", safeLandHeight=" + safeLandHeight +
                ", configured=" + configured +
                '}';
    }

    /**
     * 위도를 반환합니다.
     * 
     * @return 위도
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * 위도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param latitude 설정할 위도
     * @return 현재 AlternateLandPoint 객체
     */
    public AlternateLandPoint setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * 경도를 반환합니다.
     * 
     * @return 경도
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * 경도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param longitude 설정할 경도
     * @return 현재 AlternateLandPoint 객체
     */
    public AlternateLandPoint setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * 안전 착륙 고도를 반환합니다.
     * 
     * @return 안전 착륙 고도 (미터)
     */
    public Float getSafeLandHeight() {
        return safeLandHeight;
    }

    /**
     * 안전 착륙 고도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param safeLandHeight 설정할 안전 착륙 고도 (미터)
     * @return 현재 AlternateLandPoint 객체
     */
    public AlternateLandPoint setSafeLandHeight(Float safeLandHeight) {
        this.safeLandHeight = safeLandHeight;
        return this;
    }

    /**
     * 대체 착륙 지점 설정 여부를 반환합니다.
     * 
     * @return 대체 착륙 지점 설정 여부
     */
    public Boolean getConfigured() {
        return configured;
    }

    /**
     * 대체 착륙 지점 설정 여부를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param configured 설정할 대체 착륙 지점 설정 여부
     * @return 현재 AlternateLandPoint 객체
     */
    public AlternateLandPoint setConfigured(Boolean configured) {
        this.configured = configured;
        return this;
    }
}
