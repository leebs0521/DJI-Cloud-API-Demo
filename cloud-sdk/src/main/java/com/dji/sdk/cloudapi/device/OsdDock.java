package com.dji.sdk.cloudapi.device;

import com.dji.sdk.annotations.CloudSDKVersion;
import com.dji.sdk.config.version.CloudSDKVersionEnum;

/**
 * 도크 OSD(On-Screen Display) 정보 클래스
 * <p>
 * 이 클래스는 도크의 실시간 상태 정보를 담고 있습니다.
 * 네트워크 상태, 드론 상태, 환경 정보, 전력 정보 등을 포함합니다.
 *
 * @author sean
 * @version 1.3
 * @date 2022/11/3
 */
public class OsdDock {

    /**
     * 네트워크 상태 정보
     */
    private NetworkState networkState;

    /**
     * 드론이 도크에 있는지 여부
     */
    private Boolean droneInDock;

    /**
     * 드론 충전 상태
     */
    private DroneChargeState droneChargeState;

    /**
     * 강우 상태
     */
    private RainfallEnum rainfall;

    /**
     * 풍속 (m/s)
     */
    private Float windSpeed;

    /**
     * 환경 온도 (°C)
     */
    private Float environmentTemperature;

    /**
     * 도크 내부 온도 (°C)
     */
    private Float temperature;

    /**
     * 도크 내부 습도 (%RH)
     */
    private Integer humidity;

    /**
     * 위도
     */
    private Float latitude;

    /**
     * 경도
     */
    private Float longitude;

    /**
     * 타원체 고도 (m)
     */
    private Float height;

    /**
     * 대체 착륙 지점 정보
     */
    private AlternateLandPoint alternateLandPoint;

    /**
     * 최초 전원 켜진 시간 (타임스탬프)
     */
    private Long firstPowerOn;

    /**
     * 도크 위치 상태
     */
    private DockPositionState positionState;

    /**
     * 저장소 정보
     */
    private Storage storage;

    /**
     * 도크 모드 코드
     */
    private DockModeCodeEnum modeCode;

    /**
     * 커버 상태
     */
    private CoverStateEnum coverState;

    /**
     * 보조 조명 상태
     */
    private Boolean supplementLightState;

    /**
     * 비상 정지 상태
     */
    private Boolean emergencyStopState;

    /**
     * 에어컨 정보
     */
    private AirConditioner airConditioner;

    /**
     * 배터리 저장 모드
     */
    private BatteryStoreModeEnum batteryStoreMode;

    /**
     * 알람 상태
     */
    private Boolean alarmState;

    /**
     * 퍼터 상태
     */
    private PutterStateEnum putterState;

    /**
     * 도크 서브 디바이스 정보
     */
    private DockSubDevice subDevice;

    /**
     * 누적 작업 숫자
     */
    private Integer jobNumber;

    /**
     * 누적 작동 시간 (초)
     */
    private Long accTime;

    /**
     * 활성화 시간 (타임스탬프)
     */
    private Long activationTime;

    /**
     * 도크 유지보수 상태
     */
    private OsdDockMaintainStatus maintainStatus;

    /**
     * 전원 공급 전압 (V)
     */
    private Integer electricSupplyVoltage;

    /**
     * 작동 전압 (mV)
     */
    private Integer workingVoltage;

    /**
     * 작동 전류 (mA)
     */
    private Integer workingCurrent;

    /**
     * 백업 배터리 정보
     */
    private BackupBattery backupBattery;

    /**
     * 드론 배터리 유지보수 정보
     */
    private DroneBatteryMaintenanceInfo droneBatteryMaintenanceInfo;

    /**
     * Dock 임무 상태
     */
    private FlighttaskStepCodeEnum flighttaskStepCode;

    /**
     * 비행 작업 준비 용량
     */
    private Integer flighttaskPrepareCapacity;

    /**
     * 미디어 파일 상세 정보
     */
    private MediaFileDetail mediaFileDetail;

    /**
     * 무선 링크 정보
     */
    private WirelessLink wirelessLink;

    /**
     * DRC 상태
     */
    private DrcStateEnum drcState;

    /**
     * 사용자 경험 개선 프로그램 상태
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    private UserExperienceImprovementEnum userExperienceImprovement;

    /**
     * 드론 권한 정보 클래스
     */
    private DroneAuthorityInfo droneAuthorityInfo;

    /**
     * 홈 포지션 유효성 상태 열거형 클래스
     */
    private HomePositionIsValidEnum homePositionIsValid;

    /**
     * 도크 방위각(°, 진북 기준 각도) -180 ~ 180
     * */
    private Double heading;


    /**
     * 기본 생성자
     */
    public OsdDock() {
    }

    @Override
    public String toString() {
        return "OsdDock{" +
                "networkState=" + networkState +
                ", droneInDock=" + droneInDock +
                ", droneChargeState=" + droneChargeState +
                ", rainfall=" + rainfall +
                ", windSpeed=" + windSpeed +
                ", environmentTemperature=" + environmentTemperature +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", height=" + height +
                ", alternateLandPoint=" + alternateLandPoint +
                ", firstPowerOn=" + firstPowerOn +
                ", positionState=" + positionState +
                ", storage=" + storage +
                ", modeCode=" + modeCode +
                ", coverState=" + coverState +
                ", supplementLightState=" + supplementLightState +
                ", emergencyStopState=" + emergencyStopState +
                ", airConditioner=" + airConditioner +
                ", batteryStoreMode=" + batteryStoreMode +
                ", alarmState=" + alarmState +
                ", putterState=" + putterState +
                ", subDevice=" + subDevice +
                ", jobNumber=" + jobNumber +
                ", accTime=" + accTime +
                ", activationTime=" + activationTime +
                ", maintainStatus=" + maintainStatus +
                ", electricSupplyVoltage=" + electricSupplyVoltage +
                ", workingVoltage=" + workingVoltage +
                ", workingCurrent=" + workingCurrent +
                ", backupBattery=" + backupBattery +
                ", droneBatteryMaintenanceInfo=" + droneBatteryMaintenanceInfo +
                ", flighttaskStepCode=" + flighttaskStepCode +
                ", flighttaskPrepareCapacity=" + flighttaskPrepareCapacity +
                ", mediaFileDetail=" + mediaFileDetail +
                ", wirelessLink=" + wirelessLink +
                ", drcState=" + drcState +
                ", userExperienceImprovement=" + userExperienceImprovement +
                ", droneAuthorityInfo=" + droneAuthorityInfo +
                '}';
    }

    public NetworkState getNetworkState() {
        return networkState;
    }

    public OsdDock setNetworkState(NetworkState networkState) {
        this.networkState = networkState;
        return this;
    }

    public Boolean getDroneInDock() {
        return droneInDock;
    }

    public OsdDock setDroneInDock(Boolean droneInDock) {
        this.droneInDock = droneInDock;
        return this;
    }

    public DroneChargeState getDroneChargeState() {
        return droneChargeState;
    }

    public OsdDock setDroneChargeState(DroneChargeState droneChargeState) {
        this.droneChargeState = droneChargeState;
        return this;
    }

    public RainfallEnum getRainfall() {
        return rainfall;
    }

    public OsdDock setRainfall(RainfallEnum rainfall) {
        this.rainfall = rainfall;
        return this;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public OsdDock setWindSpeed(Float windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public Float getEnvironmentTemperature() {
        return environmentTemperature;
    }

    public OsdDock setEnvironmentTemperature(Float environmentTemperature) {
        this.environmentTemperature = environmentTemperature;
        return this;
    }

    public Float getTemperature() {
        return temperature;
    }

    public OsdDock setTemperature(Float temperature) {
        this.temperature = temperature;
        return this;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public OsdDock setHumidity(Integer humidity) {
        this.humidity = humidity;
        return this;
    }

    public Float getLatitude() {
        return latitude;
    }

    public OsdDock setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    public Float getLongitude() {
        return longitude;
    }

    public OsdDock setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    public Float getHeight() {
        return height;
    }

    public OsdDock setHeight(Float height) {
        this.height = height;
        return this;
    }

    public AlternateLandPoint getAlternateLandPoint() {
        return alternateLandPoint;
    }

    public OsdDock setAlternateLandPoint(AlternateLandPoint alternateLandPoint) {
        this.alternateLandPoint = alternateLandPoint;
        return this;
    }

    public Long getFirstPowerOn() {
        return firstPowerOn;
    }

    public OsdDock setFirstPowerOn(Long firstPowerOn) {
        this.firstPowerOn = firstPowerOn;
        return this;
    }

    public DockPositionState getPositionState() {
        return positionState;
    }

    public OsdDock setPositionState(DockPositionState positionState) {
        this.positionState = positionState;
        return this;
    }

    public Storage getStorage() {
        return storage;
    }

    public OsdDock setStorage(Storage storage) {
        this.storage = storage;
        return this;
    }

    public DockModeCodeEnum getModeCode() {
        return modeCode;
    }

    public OsdDock setModeCode(DockModeCodeEnum modeCode) {
        this.modeCode = modeCode;
        return this;
    }

    public CoverStateEnum getCoverState() {
        return coverState;
    }

    public OsdDock setCoverState(CoverStateEnum coverState) {
        this.coverState = coverState;
        return this;
    }

    public Boolean getSupplementLightState() {
        return supplementLightState;
    }

    public OsdDock setSupplementLightState(Boolean supplementLightState) {
        this.supplementLightState = supplementLightState;
        return this;
    }

    public Boolean getEmergencyStopState() {
        return emergencyStopState;
    }

    public OsdDock setEmergencyStopState(Boolean emergencyStopState) {
        this.emergencyStopState = emergencyStopState;
        return this;
    }

    public AirConditioner getAirConditioner() {
        return airConditioner;
    }

    public OsdDock setAirConditioner(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
        return this;
    }

    public BatteryStoreModeEnum getBatteryStoreMode() {
        return batteryStoreMode;
    }

    public OsdDock setBatteryStoreMode(BatteryStoreModeEnum batteryStoreMode) {
        this.batteryStoreMode = batteryStoreMode;
        return this;
    }

    public Boolean getAlarmState() {
        return alarmState;
    }

    public OsdDock setAlarmState(Boolean alarmState) {
        this.alarmState = alarmState;
        return this;
    }

    public PutterStateEnum getPutterState() {
        return putterState;
    }

    public OsdDock setPutterState(PutterStateEnum putterState) {
        this.putterState = putterState;
        return this;
    }

    public DockSubDevice getSubDevice() {
        return subDevice;
    }

    public OsdDock setSubDevice(DockSubDevice subDevice) {
        this.subDevice = subDevice;
        return this;
    }

    public Integer getJobNumber() {
        return jobNumber;
    }

    public OsdDock setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
        return this;
    }

    public Long getAccTime() {
        return accTime;
    }

    public OsdDock setAccTime(Long accTime) {
        this.accTime = accTime;
        return this;
    }

    public Long getActivationTime() {
        return activationTime;
    }

    public OsdDock setActivationTime(Long activationTime) {
        this.activationTime = activationTime;
        return this;
    }

    public OsdDockMaintainStatus getMaintainStatus() {
        return maintainStatus;
    }

    public OsdDock setMaintainStatus(OsdDockMaintainStatus maintainStatus) {
        this.maintainStatus = maintainStatus;
        return this;
    }

    public Integer getElectricSupplyVoltage() {
        return electricSupplyVoltage;
    }

    public OsdDock setElectricSupplyVoltage(Integer electricSupplyVoltage) {
        this.electricSupplyVoltage = electricSupplyVoltage;
        return this;
    }

    public Integer getWorkingVoltage() {
        return workingVoltage;
    }

    public OsdDock setWorkingVoltage(Integer workingVoltage) {
        this.workingVoltage = workingVoltage;
        return this;
    }

    public Integer getWorkingCurrent() {
        return workingCurrent;
    }

    public OsdDock setWorkingCurrent(Integer workingCurrent) {
        this.workingCurrent = workingCurrent;
        return this;
    }

    public BackupBattery getBackupBattery() {
        return backupBattery;
    }

    public OsdDock setBackupBattery(BackupBattery backupBattery) {
        this.backupBattery = backupBattery;
        return this;
    }

    public DroneBatteryMaintenanceInfo getDroneBatteryMaintenanceInfo() {
        return droneBatteryMaintenanceInfo;
    }

    public OsdDock setDroneBatteryMaintenanceInfo(DroneBatteryMaintenanceInfo droneBatteryMaintenanceInfo) {
        this.droneBatteryMaintenanceInfo = droneBatteryMaintenanceInfo;
        return this;
    }

    public FlighttaskStepCodeEnum getFlighttaskStepCode() {
        return flighttaskStepCode;
    }

    public OsdDock setFlighttaskStepCode(FlighttaskStepCodeEnum flighttaskStepCode) {
        this.flighttaskStepCode = flighttaskStepCode;
        return this;
    }

    public Integer getFlighttaskPrepareCapacity() {
        return flighttaskPrepareCapacity;
    }

    public OsdDock setFlighttaskPrepareCapacity(Integer flighttaskPrepareCapacity) {
        this.flighttaskPrepareCapacity = flighttaskPrepareCapacity;
        return this;
    }

    public MediaFileDetail getMediaFileDetail() {
        return mediaFileDetail;
    }

    public OsdDock setMediaFileDetail(MediaFileDetail mediaFileDetail) {
        this.mediaFileDetail = mediaFileDetail;
        return this;
    }

    public WirelessLink getWirelessLink() {
        return wirelessLink;
    }

    public OsdDock setWirelessLink(WirelessLink wirelessLink) {
        this.wirelessLink = wirelessLink;
        return this;
    }

    public DrcStateEnum getDrcState() {
        return drcState;
    }

    public OsdDock setDrcState(DrcStateEnum drcState) {
        this.drcState = drcState;
        return this;
    }

    public UserExperienceImprovementEnum getUserExperienceImprovement() {
        return userExperienceImprovement;
    }

    public OsdDock setUserExperienceImprovement(UserExperienceImprovementEnum userExperienceImprovement) {
        this.userExperienceImprovement = userExperienceImprovement;
        return this;
    }

    public DroneAuthorityInfo getDroneAuthorityInfo() {
        return droneAuthorityInfo;
    }

    public OsdDock setDroneAuthorityInfo(DroneAuthorityInfo droneAuthorityInfo) {
        this.droneAuthorityInfo = droneAuthorityInfo;
        return this;
    }
}
