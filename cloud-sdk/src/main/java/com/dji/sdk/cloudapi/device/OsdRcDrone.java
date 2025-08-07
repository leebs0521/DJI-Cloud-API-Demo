package com.dji.sdk.cloudapi.device;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * RC 드론 OSD (On-Screen Display) 정보 클래스
 * 
 * 이 클래스는 RC에 연결된 드론의 실시간 상태 정보를 담습니다.
 * 드론의 위치, 속도, 배터리, 페이로드, 비행 모드 등 다양한 정보를 포함합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/23
 */
public class OsdRcDrone {

    /**
     * 드론의 헤딩 각도 (도)
     */
    private Float attitudeHead;

    /**
     * 드론의 피치 각도 (도)
     */
    private Double attitudePitch;

    /**
     * 드론의 롤 각도 (도)
     */
    private Double attitudeRoll;

    /**
     * 드론의 고도 (미터)
     */
    private Float elevation;

    /**
     * 드론 배터리 정보
     */
    private DroneBattery battery;

    /**
     * 드론 펌웨어 버전
     */
    private String firmwareVersion;

    /**
     * 드론 기어 상태
     */
    private GearEnum gear;

    /**
     * 드론의 현재 높이 (미터)
     */
    private Float height;

    /**
     * 홈 포인트까지의 거리 (미터)
     */
    private Float homeDistance;

    /**
     * 수평 속도 (m/s)
     */
    private Float horizontalSpeed;

    /**
     * 드론의 위도
     */
    private Float latitude;

    /**
     * 드론의 경도
     */
    private Float longitude;

    /**
     * 드론의 비행 모드 코드
     */
    private DroneModeCodeEnum modeCode;

    /**
     * 총 비행 거리 (미터)
     */
    private Double totalFlightDistance;

    /**
     * 총 비행 시간 (초)
     */
    private Float totalFlightTime;

    /**
     * 수직 속도 (m/s)
     */
    private Float verticalSpeed;

    /**
     * 풍향
     */
    private WindDirectionEnum windDirection;

    /**
     * 풍속 (m/s)
     */
    private Float windSpeed;

    /**
     * 드론 위치 상태
     */
    private DronePositionState positionState;

    /**
     * 드론에 연결된 페이로드 목록
     */
    @JsonProperty(PayloadModelConst.PAYLOAD_KEY)
    private List<RcDronePayload> payloads;

    /**
     * 스토리지 정보
     */
    private Storage storage;

    /**
     * 높이 제한 (미터)
     */
    private Integer heightLimit;

    /**
     * 거리 제한 상태
     */
    private RcDistanceLimitStatus distanceLimitStatus;

    /**
     * 추적 ID
     */
    private String trackId;

    /**
     * 기본 생성자
     */
    public OsdRcDrone() {
    }

    @Override
    public String toString() {
        return "OsdRcDrone{" +
                "attitudeHead=" + attitudeHead +
                ", attitudePitch=" + attitudePitch +
                ", attitudeRoll=" + attitudeRoll +
                ", elevation=" + elevation +
                ", battery=" + battery +
                ", firmwareVersion='" + firmwareVersion + '\'' +
                ", gear=" + gear +
                ", height=" + height +
                ", homeDistance=" + homeDistance +
                ", horizontalSpeed=" + horizontalSpeed +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", modeCode=" + modeCode +
                ", totalFlightDistance=" + totalFlightDistance +
                ", totalFlightTime=" + totalFlightTime +
                ", verticalSpeed=" + verticalSpeed +
                ", windDirection=" + windDirection +
                ", windSpeed=" + windSpeed +
                ", positionState=" + positionState +
                ", payloads=" + payloads +
                ", storage=" + storage +
                ", heightLimit=" + heightLimit +
                ", distanceLimitStatus=" + distanceLimitStatus +
                ", trackId='" + trackId + '\'' +
                '}';
    }

    /**
     * 드론의 헤딩 각도를 반환합니다.
     * 
     * @return 헤딩 각도 (도)
     */
    public Float getAttitudeHead() {
        return attitudeHead;
    }

    /**
     * 드론의 헤딩 각도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param attitudeHead 설정할 헤딩 각도 (도)
     * @return 현재 OsdRcDrone 객체
     */
    public OsdRcDrone setAttitudeHead(Float attitudeHead) {
        this.attitudeHead = attitudeHead;
        return this;
    }

    public Double getAttitudePitch() {
        return attitudePitch;
    }

    public OsdRcDrone setAttitudePitch(Double attitudePitch) {
        this.attitudePitch = attitudePitch;
        return this;
    }

    public Double getAttitudeRoll() {
        return attitudeRoll;
    }

    public OsdRcDrone setAttitudeRoll(Double attitudeRoll) {
        this.attitudeRoll = attitudeRoll;
        return this;
    }

    public Float getElevation() {
        return elevation;
    }

    public OsdRcDrone setElevation(Float elevation) {
        this.elevation = elevation;
        return this;
    }

    public DroneBattery getBattery() {
        return battery;
    }

    public OsdRcDrone setBattery(DroneBattery battery) {
        this.battery = battery;
        return this;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public OsdRcDrone setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
        return this;
    }

    public GearEnum getGear() {
        return gear;
    }

    public OsdRcDrone setGear(GearEnum gear) {
        this.gear = gear;
        return this;
    }

    public Float getHeight() {
        return height;
    }

    public OsdRcDrone setHeight(Float height) {
        this.height = height;
        return this;
    }

    public Float getHomeDistance() {
        return homeDistance;
    }

    public OsdRcDrone setHomeDistance(Float homeDistance) {
        this.homeDistance = homeDistance;
        return this;
    }

    public Float getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public OsdRcDrone setHorizontalSpeed(Float horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
        return this;
    }

    public Float getLatitude() {
        return latitude;
    }

    public OsdRcDrone setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    public Float getLongitude() {
        return longitude;
    }

    public OsdRcDrone setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    public DroneModeCodeEnum getModeCode() {
        return modeCode;
    }

    public OsdRcDrone setModeCode(DroneModeCodeEnum modeCode) {
        this.modeCode = modeCode;
        return this;
    }

    public Double getTotalFlightDistance() {
        return totalFlightDistance;
    }

    public OsdRcDrone setTotalFlightDistance(Double totalFlightDistance) {
        this.totalFlightDistance = totalFlightDistance;
        return this;
    }

    public Float getTotalFlightTime() {
        return totalFlightTime;
    }

    public OsdRcDrone setTotalFlightTime(Float totalFlightTime) {
        this.totalFlightTime = totalFlightTime;
        return this;
    }

    public Float getVerticalSpeed() {
        return verticalSpeed;
    }

    public OsdRcDrone setVerticalSpeed(Float verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
        return this;
    }

    public WindDirectionEnum getWindDirection() {
        return windDirection;
    }

    public OsdRcDrone setWindDirection(WindDirectionEnum windDirection) {
        this.windDirection = windDirection;
        return this;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public OsdRcDrone setWindSpeed(Float windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public DronePositionState getPositionState() {
        return positionState;
    }

    public OsdRcDrone setPositionState(DronePositionState positionState) {
        this.positionState = positionState;
        return this;
    }

    public List<RcDronePayload> getPayloads() {
        return payloads;
    }

    public OsdRcDrone setPayloads(List<RcDronePayload> payloads) {
        this.payloads = payloads;
        return this;
    }

    public Storage getStorage() {
        return storage;
    }

    public OsdRcDrone setStorage(Storage storage) {
        this.storage = storage;
        return this;
    }

    public Integer getHeightLimit() {
        return heightLimit;
    }

    public OsdRcDrone setHeightLimit(Integer heightLimit) {
        this.heightLimit = heightLimit;
        return this;
    }

    public RcDistanceLimitStatus getDistanceLimitStatus() {
        return distanceLimitStatus;
    }

    public OsdRcDrone setDistanceLimitStatus(RcDistanceLimitStatus distanceLimitStatus) {
        this.distanceLimitStatus = distanceLimitStatus;
        return this;
    }

    public String getTrackId() {
        return trackId;
    }

    public OsdRcDrone setTrackId(String trackId) {
        this.trackId = trackId;
        return this;
    }
}