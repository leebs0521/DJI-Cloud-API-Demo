package com.dji.sdk.cloudapi.device;

/**
 * 스마트 트래킹 포인트 클래스
 * 
 * 이 클래스는 스마트 트래킹 기능의 포인트 정보를 담습니다.
 * 트래킹 타겟 모드, 위도, 경도, 고도 등을 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public class SmartTrackPoint {

    /**
     * 트래킹 타겟 모드
     */
    private TrackTargetModeEnum trackTargetMode;

    /**
     * 트래킹 위도
     */
    private Float trackLatitude;

    /**
     * 트래킹 경도
     */
    private Float trackLongitude;

    /**
     * 트래킹 고도 (미터)
     */
    private Float trackAltitude;

    /**
     * 기본 생성자
     */
    public SmartTrackPoint() {
    }

    @Override
    public String toString() {
        return "SmartTrackPoint{" +
                "trackTargetMode=" + trackTargetMode +
                ", trackLatitude=" + trackLatitude +
                ", trackLongitude=" + trackLongitude +
                ", trackAltitude=" + trackAltitude +
                '}';
    }

    /**
     * 트래킹 타겟 모드를 반환합니다.
     * 
     * @return 트래킹 타겟 모드
     */
    public TrackTargetModeEnum getTrackTargetMode() {
        return trackTargetMode;
    }

    /**
     * 트래킹 타겟 모드를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param trackTargetMode 설정할 트래킹 타겟 모드
     * @return 현재 SmartTrackPoint 객체
     */
    public SmartTrackPoint setTrackTargetMode(TrackTargetModeEnum trackTargetMode) {
        this.trackTargetMode = trackTargetMode;
        return this;
    }

    /**
     * 트래킹 위도를 반환합니다.
     * 
     * @return 트래킹 위도
     */
    public Float getTrackLatitude() {
        return trackLatitude;
    }

    /**
     * 트래킹 위도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param trackLatitude 설정할 트래킹 위도
     * @return 현재 SmartTrackPoint 객체
     */
    public SmartTrackPoint setTrackLatitude(Float trackLatitude) {
        this.trackLatitude = trackLatitude;
        return this;
    }

    /**
     * 트래킹 경도를 반환합니다.
     * 
     * @return 트래킹 경도
     */
    public Float getTrackLongitude() {
        return trackLongitude;
    }

    /**
     * 트래킹 경도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param trackLongitude 설정할 트래킹 경도
     * @return 현재 SmartTrackPoint 객체
     */
    public SmartTrackPoint setTrackLongitude(Float trackLongitude) {
        this.trackLongitude = trackLongitude;
        return this;
    }

    /**
     * 트래킹 고도를 반환합니다.
     * 
     * @return 트래킹 고도 (미터)
     */
    public Float getTrackAltitude() {
        return trackAltitude;
    }

    /**
     * 트래킹 고도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param trackAltitude 설정할 트래킹 고도 (미터)
     * @return 현재 SmartTrackPoint 객체
     */
    public SmartTrackPoint setTrackAltitude(Float trackAltitude) {
        this.trackAltitude = trackAltitude;
        return this;
    }
}
