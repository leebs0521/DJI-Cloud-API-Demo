package com.dji.sdk.cloudapi.device;

import com.dji.sdk.annotations.CloudSDKVersion;
import com.dji.sdk.config.version.GatewayTypeEnum;

import java.util.List;

/**
 * 도크 드론 페이로드 클래스
 * 
 * 이 클래스는 도크에 연결된 드론의 페이로드 정보를 담습니다.
 * 짐벌 상태, 측정 대상 정보, 열화상 설정, 스마트 트래킹 등을 포함합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/5/6
 */
public class DockDronePayload {

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
     * 버전
     */
    private Integer version;

    /**
     * 열화상 현재 팔레트 스타일
     */
    private ThermalPaletteStyleEnum thermalCurrentPaletteStyle;

    /**
     * 열화상 게인 모드
     */
    private ThermalGainModeEnum thermalGainMode;

    /**
     * 열화상 전체 최고 온도 (°C)
     */
    private Float thermalGlobalTemperatureMax;

    /**
     * 열화상 전체 최저 온도 (°C)
     */
    private Float thermalGlobalTemperatureMin;

    /**
     * 열화상 등온선 하한값
     */
    private Integer thermalIsothermLowerLimit;

    /**
     * 열화상 등온선 상태
     */
    private SwitchActionEnum thermalIsothermState;

    /**
     * 열화상 등온선 상한값
     */
    private Integer thermalIsothermUpperLimit;

    /**
     * 스마트 트래킹 포인트 목록
     */
    private List<SmartTrackPoint> smartTrackPoint;

    /**
     * 줌 배율 (DOCK2에서만 지원)
     */
    @CloudSDKVersion(include = GatewayTypeEnum.DOCK2)
    private Float zoomFactor;

    /**
     * 기본 생성자
     */
    public DockDronePayload() {
    }

    @Override
    public String toString() {
        return "DockDronePayload{" +
                "payloadIndex=" + payloadIndex +
                ", gimbalPitch=" + gimbalPitch +
                ", gimbalRoll=" + gimbalRoll +
                ", gimbalYaw=" + gimbalYaw +
                ", measureTargetAltitude=" + measureTargetAltitude +
                ", measureTargetDistance=" + measureTargetDistance +
                ", measureTargetLatitude=" + measureTargetLatitude +
                ", measureTargetLongitude=" + measureTargetLongitude +
                ", measureTargetErrorState=" + measureTargetErrorState +
                ", version=" + version +
                ", thermalCurrentPaletteStyle=" + thermalCurrentPaletteStyle +
                ", thermalGainMode=" + thermalGainMode +
                ", thermalGlobalTemperatureMax=" + thermalGlobalTemperatureMax +
                ", thermalGlobalTemperatureMin=" + thermalGlobalTemperatureMin +
                ", thermalIsothermLowerLimit=" + thermalIsothermLowerLimit +
                ", thermalIsothermState=" + thermalIsothermState +
                ", thermalIsothermUpperLimit=" + thermalIsothermUpperLimit +
                ", smartTrackPoint=" + smartTrackPoint +
                ", zoomFactor=" + zoomFactor +
                '}';
    }

    public PayloadIndex getPayloadIndex() {
        return payloadIndex;
    }

    public DockDronePayload setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    public Float getGimbalPitch() {
        return gimbalPitch;
    }

    public DockDronePayload setGimbalPitch(Float gimbalPitch) {
        this.gimbalPitch = gimbalPitch;
        return this;
    }

    public Float getGimbalRoll() {
        return gimbalRoll;
    }

    public DockDronePayload setGimbalRoll(Float gimbalRoll) {
        this.gimbalRoll = gimbalRoll;
        return this;
    }

    public Float getGimbalYaw() {
        return gimbalYaw;
    }

    public DockDronePayload setGimbalYaw(Float gimbalYaw) {
        this.gimbalYaw = gimbalYaw;
        return this;
    }

    public Float getMeasureTargetAltitude() {
        return measureTargetAltitude;
    }

    public DockDronePayload setMeasureTargetAltitude(Float measureTargetAltitude) {
        this.measureTargetAltitude = measureTargetAltitude;
        return this;
    }

    public Float getMeasureTargetDistance() {
        return measureTargetDistance;
    }

    public DockDronePayload setMeasureTargetDistance(Float measureTargetDistance) {
        this.measureTargetDistance = measureTargetDistance;
        return this;
    }

    public Float getMeasureTargetLatitude() {
        return measureTargetLatitude;
    }

    public DockDronePayload setMeasureTargetLatitude(Float measureTargetLatitude) {
        this.measureTargetLatitude = measureTargetLatitude;
        return this;
    }

    public Float getMeasureTargetLongitude() {
        return measureTargetLongitude;
    }

    public DockDronePayload setMeasureTargetLongitude(Float measureTargetLongitude) {
        this.measureTargetLongitude = measureTargetLongitude;
        return this;
    }

    public MeasureTargetStateEnum getMeasureTargetErrorState() {
        return measureTargetErrorState;
    }

    public DockDronePayload setMeasureTargetErrorState(MeasureTargetStateEnum measureTargetErrorState) {
        this.measureTargetErrorState = measureTargetErrorState;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public DockDronePayload setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public ThermalPaletteStyleEnum getThermalCurrentPaletteStyle() {
        return thermalCurrentPaletteStyle;
    }

    public DockDronePayload setThermalCurrentPaletteStyle(ThermalPaletteStyleEnum thermalCurrentPaletteStyle) {
        this.thermalCurrentPaletteStyle = thermalCurrentPaletteStyle;
        return this;
    }

    public ThermalGainModeEnum getThermalGainMode() {
        return thermalGainMode;
    }

    public DockDronePayload setThermalGainMode(ThermalGainModeEnum thermalGainMode) {
        this.thermalGainMode = thermalGainMode;
        return this;
    }

    public Float getThermalGlobalTemperatureMax() {
        return thermalGlobalTemperatureMax;
    }

    public DockDronePayload setThermalGlobalTemperatureMax(Float thermalGlobalTemperatureMax) {
        this.thermalGlobalTemperatureMax = thermalGlobalTemperatureMax;
        return this;
    }

    public Float getThermalGlobalTemperatureMin() {
        return thermalGlobalTemperatureMin;
    }

    public DockDronePayload setThermalGlobalTemperatureMin(Float thermalGlobalTemperatureMin) {
        this.thermalGlobalTemperatureMin = thermalGlobalTemperatureMin;
        return this;
    }

    public Integer getThermalIsothermLowerLimit() {
        return thermalIsothermLowerLimit;
    }

    public DockDronePayload setThermalIsothermLowerLimit(Integer thermalIsothermLowerLimit) {
        this.thermalIsothermLowerLimit = thermalIsothermLowerLimit;
        return this;
    }

    public SwitchActionEnum getThermalIsothermState() {
        return thermalIsothermState;
    }

    public DockDronePayload setThermalIsothermState(SwitchActionEnum thermalIsothermState) {
        this.thermalIsothermState = thermalIsothermState;
        return this;
    }

    public Integer getThermalIsothermUpperLimit() {
        return thermalIsothermUpperLimit;
    }

    public DockDronePayload setThermalIsothermUpperLimit(Integer thermalIsothermUpperLimit) {
        this.thermalIsothermUpperLimit = thermalIsothermUpperLimit;
        return this;
    }

    public List<SmartTrackPoint> getSmartTrackPoint() {
        return smartTrackPoint;
    }

    public DockDronePayload setSmartTrackPoint(List<SmartTrackPoint> smartTrackPoint) {
        this.smartTrackPoint = smartTrackPoint;
        return this;
    }

    public Float getZoomFactor() {
        return zoomFactor;
    }

    public DockDronePayload setZoomFactor(Float zoomFactor) {
        this.zoomFactor = zoomFactor;
        return this;
    }
}
