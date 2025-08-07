package com.dji.sdk.cloudapi.device;

import com.dji.sdk.annotations.CloudSDKVersion;
import com.dji.sdk.config.version.CloudSDKVersionEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 도크 드론 OSD (On-Screen Display) 정보 클래스
 * 
 * 이 클래스는 도크에 연결된 드론의 실시간 상태 정보를 담습니다.
 * 드론의 위치, 속도, 배터리, 페이로드, 비행 모드 등 다양한 정보를 포함합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/23
 */
public class OsdDockDrone {

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
    private List<DockDronePayload> payloads;

    /**
     * 스토리지 정보
     */
    private Storage storage;

    /**
     * 야간 조명 상태
     */
    private SwitchActionEnum nightLightsState;

    /**
     * 높이 제한 (미터)
     */
    private Integer heightLimit;

    /**
     * 거리 제한 상태
     */
    private DockDistanceLimitStatus distanceLimitStatus;

    /**
     * 장애물 회피 설정
     */
    private ObstacleAvoidance obstacleAvoidance;

    /**
     * 활성화 시간 (타임스탬프)
     */
    private Long activationTime;

    /**
     * 카메라 목록
     */
    private List<OsdCamera> cameras;

    /**
     * RC 연결 손실 시 동작
     */
    private RcLostActionEnum rcLostAction;

    /**
     * RTH 고도 (미터)
     */
    private Integer rthAltitude;

    /**
     * 총 비행 횟수
     */
    private Integer totalFlightSorties;

    /**
     * RC 연결 손실 시 웨이라인 종료 여부 (v1.0.0에서 deprecated)
     */
    @CloudSDKVersion(deprecated = CloudSDKVersionEnum.V1_0_0)
    private ExitWaylineWhenRcLostEnum exitWaylineWhenRcLost;

    /**
     * 국가 코드
     */
    private String country;

    /**
     * RID (Remote ID) 상태
     */
    private Boolean ridState;

    /**
     * 구역 제한 근접 여부
     */
    @JsonProperty("is_near_area_limit")
    private Boolean nearAreaLimit;

    /**
     * 높이 제한 근접 여부
     */
    @JsonProperty("is_near_height_limit")
    private Boolean nearHeightLimit;

    /**
     * 드론 유지보수 상태
     */
    private OsdDroneMaintainStatus maintainStatus;

    /**
     * 추적 ID
     */
    private String trackId;

    /**
     * 기본 생성자
     */
    public OsdDockDrone() {
    }

    @Override
    public String toString() {
        return "OsdDockDrone{" +
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
                ", nightLightsState=" + nightLightsState +
                ", heightLimit=" + heightLimit +
                ", distanceLimitStatus=" + distanceLimitStatus +
                ", obstacleAvoidance=" + obstacleAvoidance +
                ", activationTime=" + activationTime +
                ", cameras=" + cameras +
                ", rcLostAction=" + rcLostAction +
                ", rthAltitude=" + rthAltitude +
                ", totalFlightSorties=" + totalFlightSorties +
                ", exitWaylineWhenRcLost=" + exitWaylineWhenRcLost +
                ", country='" + country + '\'' +
                ", ridState=" + ridState +
                ", nearAreaLimit=" + nearAreaLimit +
                ", nearHeightLimit=" + nearHeightLimit +
                ", maintainStatus=" + maintainStatus +
                ", trackId='" + trackId + '\'' +
                '}';
    }

    public Float getAttitudeHead() {
        return attitudeHead;
    }

    public OsdDockDrone setAttitudeHead(Float attitudeHead) {
        this.attitudeHead = attitudeHead;
        return this;
    }

    public Double getAttitudePitch() {
        return attitudePitch;
    }

    public OsdDockDrone setAttitudePitch(Double attitudePitch) {
        this.attitudePitch = attitudePitch;
        return this;
    }

    public Double getAttitudeRoll() {
        return attitudeRoll;
    }

    public OsdDockDrone setAttitudeRoll(Double attitudeRoll) {
        this.attitudeRoll = attitudeRoll;
        return this;
    }

    public Float getElevation() {
        return elevation;
    }

    public OsdDockDrone setElevation(Float elevation) {
        this.elevation = elevation;
        return this;
    }

    public DroneBattery getBattery() {
        return battery;
    }

    public OsdDockDrone setBattery(DroneBattery battery) {
        this.battery = battery;
        return this;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public OsdDockDrone setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
        return this;
    }

    public GearEnum getGear() {
        return gear;
    }

    public OsdDockDrone setGear(GearEnum gear) {
        this.gear = gear;
        return this;
    }

    public Float getHeight() {
        return height;
    }

    public OsdDockDrone setHeight(Float height) {
        this.height = height;
        return this;
    }

    public Float getHomeDistance() {
        return homeDistance;
    }

    public OsdDockDrone setHomeDistance(Float homeDistance) {
        this.homeDistance = homeDistance;
        return this;
    }

    public Float getHorizontalSpeed() {
        return horizontalSpeed;
    }

    public OsdDockDrone setHorizontalSpeed(Float horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
        return this;
    }

    public Float getLatitude() {
        return latitude;
    }

    public OsdDockDrone setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    public Float getLongitude() {
        return longitude;
    }

    public OsdDockDrone setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    public DroneModeCodeEnum getModeCode() {
        return modeCode;
    }

    public OsdDockDrone setModeCode(DroneModeCodeEnum modeCode) {
        this.modeCode = modeCode;
        return this;
    }

    public Double getTotalFlightDistance() {
        return totalFlightDistance;
    }

    public OsdDockDrone setTotalFlightDistance(Double totalFlightDistance) {
        this.totalFlightDistance = totalFlightDistance;
        return this;
    }

    public Float getTotalFlightTime() {
        return totalFlightTime;
    }

    public OsdDockDrone setTotalFlightTime(Float totalFlightTime) {
        this.totalFlightTime = totalFlightTime;
        return this;
    }

    public Float getVerticalSpeed() {
        return verticalSpeed;
    }

    public OsdDockDrone setVerticalSpeed(Float verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
        return this;
    }

    public WindDirectionEnum getWindDirection() {
        return windDirection;
    }

    public OsdDockDrone setWindDirection(WindDirectionEnum windDirection) {
        this.windDirection = windDirection;
        return this;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public OsdDockDrone setWindSpeed(Float windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public DronePositionState getPositionState() {
        return positionState;
    }

    public OsdDockDrone setPositionState(DronePositionState positionState) {
        this.positionState = positionState;
        return this;
    }

    public List<DockDronePayload> getPayloads() {
        return payloads;
    }

    public OsdDockDrone setPayloads(List<DockDronePayload> payloads) {
        this.payloads = payloads;
        return this;
    }

    public Storage getStorage() {
        return storage;
    }

    public OsdDockDrone setStorage(Storage storage) {
        this.storage = storage;
        return this;
    }

    public SwitchActionEnum getNightLightsState() {
        return nightLightsState;
    }

    public OsdDockDrone setNightLightsState(SwitchActionEnum nightLightsState) {
        this.nightLightsState = nightLightsState;
        return this;
    }

    public Integer getHeightLimit() {
        return heightLimit;
    }

    public OsdDockDrone setHeightLimit(Integer heightLimit) {
        this.heightLimit = heightLimit;
        return this;
    }

    public DockDistanceLimitStatus getDistanceLimitStatus() {
        return distanceLimitStatus;
    }

    public OsdDockDrone setDistanceLimitStatus(DockDistanceLimitStatus distanceLimitStatus) {
        this.distanceLimitStatus = distanceLimitStatus;
        return this;
    }

    public ObstacleAvoidance getObstacleAvoidance() {
        return obstacleAvoidance;
    }

    public OsdDockDrone setObstacleAvoidance(ObstacleAvoidance obstacleAvoidance) {
        this.obstacleAvoidance = obstacleAvoidance;
        return this;
    }

    public Long getActivationTime() {
        return activationTime;
    }

    public OsdDockDrone setActivationTime(Long activationTime) {
        this.activationTime = activationTime;
        return this;
    }

    public List<OsdCamera> getCameras() {
        return cameras;
    }

    public OsdDockDrone setCameras(List<OsdCamera> cameras) {
        this.cameras = cameras;
        return this;
    }

    public RcLostActionEnum getRcLostAction() {
        return rcLostAction;
    }

    public OsdDockDrone setRcLostAction(RcLostActionEnum rcLostAction) {
        this.rcLostAction = rcLostAction;
        return this;
    }

    public Integer getRthAltitude() {
        return rthAltitude;
    }

    public OsdDockDrone setRthAltitude(Integer rthAltitude) {
        this.rthAltitude = rthAltitude;
        return this;
    }

    public Integer getTotalFlightSorties() {
        return totalFlightSorties;
    }

    public OsdDockDrone setTotalFlightSorties(Integer totalFlightSorties) {
        this.totalFlightSorties = totalFlightSorties;
        return this;
    }

    public ExitWaylineWhenRcLostEnum getExitWaylineWhenRcLost() {
        return exitWaylineWhenRcLost;
    }

    public OsdDockDrone setExitWaylineWhenRcLost(ExitWaylineWhenRcLostEnum exitWaylineWhenRcLost) {
        this.exitWaylineWhenRcLost = exitWaylineWhenRcLost;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public OsdDockDrone setCountry(String country) {
        this.country = country;
        return this;
    }

    public Boolean getRidState() {
        return ridState;
    }

    public OsdDockDrone setRidState(Boolean ridState) {
        this.ridState = ridState;
        return this;
    }

    public Boolean getNearAreaLimit() {
        return nearAreaLimit;
    }

    public OsdDockDrone setNearAreaLimit(Boolean nearAreaLimit) {
        this.nearAreaLimit = nearAreaLimit;
        return this;
    }

    public Boolean getNearHeightLimit() {
        return nearHeightLimit;
    }

    public OsdDockDrone setNearHeightLimit(Boolean nearHeightLimit) {
        this.nearHeightLimit = nearHeightLimit;
        return this;
    }

    public OsdDroneMaintainStatus getMaintainStatus() {
        return maintainStatus;
    }

    public OsdDockDrone setMaintainStatus(OsdDroneMaintainStatus maintainStatus) {
        this.maintainStatus = maintainStatus;
        return this;
    }

    public String getTrackId() {
        return trackId;
    }

    public OsdDockDrone setTrackId(String trackId) {
        this.trackId = trackId;
        return this;
    }
}