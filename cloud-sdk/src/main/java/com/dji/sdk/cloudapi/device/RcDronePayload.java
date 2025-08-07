package com.dji.sdk.cloudapi.device;

import java.util.List;

/**
 * RC 드론 페이로드 클래스
 * 
 * 이 클래스는 RC에 연결된 드론의 페이로드 정보를 담습니다.
 * 짐벌 상태, 측정 대상 정보, 스마트 트래킹 등을 포함합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/5/6
 */
public class RcDronePayload {

    /**
     * 페이로드 인덱스
     */
    private PayloadIndex payloadIndex;

    /**
     * 짐벌 피치 각도 (도)
     */
    private Float gimbalPitch;

    /**
     * 짐벌 롤 각도 (도)
     */
    private Float gimbalRoll;

    /**
     * 짐벌 요 각도 (도)
     */
    private Float gimbalYaw;

    /**
     * 측정 대상 고도 (미터)
     */
    private Float measureTargetAltitude;

    /**
     * 측정 대상 거리 (미터)
     */
    private Float measureTargetDistance;

    /**
     * 측정 대상 위도
     */
    private Float measureTargetLatitude;

    /**
     * 측정 대상 경도
     */
    private Float measureTargetLongitude;

    /**
     * 측정 대상 오류 상태
     */
    private MeasureTargetStateEnum measureTargetErrorState;

    /**
     * 스마트 트래킹 포인트 목록
     */
    private List<SmartTrackPoint> smartTrackPoint;

    /**
     * 기본 생성자
     */
    public RcDronePayload() {
    }

    @Override
    public String toString() {
        return "RcDronePayload{" +
                "payloadIndex=" + payloadIndex +
                ", gimbalPitch=" + gimbalPitch +
                ", gimbalRoll=" + gimbalRoll +
                ", gimbalYaw=" + gimbalYaw +
                ", measureTargetAltitude=" + measureTargetAltitude +
                ", measureTargetDistance=" + measureTargetDistance +
                ", measureTargetLatitude=" + measureTargetLatitude +
                ", measureTargetLongitude=" + measureTargetLongitude +
                ", measureTargetErrorState=" + measureTargetErrorState +
                ", smartTrackPoint=" + smartTrackPoint +
                '}';
    }

    public PayloadIndex getPayloadIndex() {
        return payloadIndex;
    }

    public RcDronePayload setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    public Float getGimbalPitch() {
        return gimbalPitch;
    }

    public RcDronePayload setGimbalPitch(Float gimbalPitch) {
        this.gimbalPitch = gimbalPitch;
        return this;
    }

    public Float getGimbalRoll() {
        return gimbalRoll;
    }

    public RcDronePayload setGimbalRoll(Float gimbalRoll) {
        this.gimbalRoll = gimbalRoll;
        return this;
    }

    public Float getGimbalYaw() {
        return gimbalYaw;
    }

    public RcDronePayload setGimbalYaw(Float gimbalYaw) {
        this.gimbalYaw = gimbalYaw;
        return this;
    }

    public Float getMeasureTargetAltitude() {
        return measureTargetAltitude;
    }

    public RcDronePayload setMeasureTargetAltitude(Float measureTargetAltitude) {
        this.measureTargetAltitude = measureTargetAltitude;
        return this;
    }

    public Float getMeasureTargetDistance() {
        return measureTargetDistance;
    }

    public RcDronePayload setMeasureTargetDistance(Float measureTargetDistance) {
        this.measureTargetDistance = measureTargetDistance;
        return this;
    }

    public Float getMeasureTargetLatitude() {
        return measureTargetLatitude;
    }

    public RcDronePayload setMeasureTargetLatitude(Float measureTargetLatitude) {
        this.measureTargetLatitude = measureTargetLatitude;
        return this;
    }

    public Float getMeasureTargetLongitude() {
        return measureTargetLongitude;
    }

    public RcDronePayload setMeasureTargetLongitude(Float measureTargetLongitude) {
        this.measureTargetLongitude = measureTargetLongitude;
        return this;
    }

    public MeasureTargetStateEnum getMeasureTargetErrorState() {
        return measureTargetErrorState;
    }

    public RcDronePayload setMeasureTargetErrorState(MeasureTargetStateEnum measureTargetErrorState) {
        this.measureTargetErrorState = measureTargetErrorState;
        return this;
    }

    public List<SmartTrackPoint> getSmartTrackPoint() {
        return smartTrackPoint;
    }

    public RcDronePayload setSmartTrackPoint(List<SmartTrackPoint> smartTrackPoint) {
        this.smartTrackPoint = smartTrackPoint;
        return this;
    }
}
