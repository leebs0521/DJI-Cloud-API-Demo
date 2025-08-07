package com.dji.sdk.cloudapi.control;

import com.dji.sdk.annotations.CloudSDKVersion;
import com.dji.sdk.cloudapi.device.ExitWaylineWhenRcLostEnum;
import com.dji.sdk.cloudapi.device.RcLostActionEnum;
import com.dji.sdk.cloudapi.wayline.RthModeEnum;
import com.dji.sdk.cloudapi.wayline.SimulateMission;
import com.dji.sdk.common.BaseModel;
import com.dji.sdk.config.version.CloudSDKVersionEnum;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 지정 지점 이륙 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 드론을 특정 지점으로 이륙시키기 위한 요청을 정의합니다.
 * 목적지 좌표, 안전 이륙 고도, RTH 설정, 커맨더 모드 설정 등을 포함합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/1
 */
public class TakeoffToPointRequest extends BaseModel {

    /**
     * 비행 ID (필수)
     * 이륙 작업의 고유 식별자
     * 특수 문자 제한: < > : " / | ? * . _ \
     */
    @Pattern(regexp = "^[^<>:\"/|?*._\\\\]+$")
    @NotNull
    private String flightId;

    /**
     * 목적지 경도 (필수)
     * -180 ~ 180 범위, 서경은 음수, 동경은 양수
     */
    @Min(-180)
    @Max(180)
    @NotNull
    private Float targetLongitude;

    /**
     * 목적지 위도 (필수)
     * -90 ~ 90 범위, 남위는 음수, 북위는 양수
     */
    @Min(-90)
    @Max(90)
    @NotNull
    private Float targetLatitude;

    /**
     * 목적지 고도 (필수)
     * 2 ~ 10000 범위, 목적지의 고도 (미터)
     */
    @Min(2)
    @Max(10000)
    @NotNull
    private Float targetHeight;

    /**
     * 안전 이륙 고도 (필수)
     * 20 ~ 1500 범위, 이륙 시 안전 고도 (미터)
     */
    @Min(20)
    @Max(1500)
    @NotNull
    private Float securityTakeoffHeight;

    /**
     * RTH 고도 (필수)
     * 2 ~ 1500 범위, 자동 복귀 시 고도 (미터)
     */
    @Min(2)
    @Max(1500)
    @NotNull
    private Float rthAltitude;

    /**
     * RC 신호 손실 액션 (필수)
     * RC 신호 손실 시 수행할 동작
     */
    @NotNull
    private RcLostActionEnum rcLostAction;

    /**
     * RC 신호 손실 시 웨이라인 종료 (필수, deprecated)
     * RC 신호 손실 시 웨이라인을 종료할지 여부
     */
    @NotNull
    @CloudSDKVersion(deprecated = CloudSDKVersionEnum.V1_0_0)
    private ExitWaylineWhenRcLostEnum exitWaylineWhenRcLost;

    /**
     * 최대 속도 (필수)
     * 1 ~ 15 범위, 비행 중 최대 속도 (m/s)
     */
    @Min(1)
    @Max(15)
    @NotNull
    private Integer maxSpeed;

    /**
     * RTH 모드 (필수, V1.0.0 이후)
     * 자동 복귀 모드 설정
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    @NotNull
    private RthModeEnum rthMode;

    /**
     * 커맨더 모드 신호 손실 액션 (필수, V1.0.0 이후)
     * 커맨더 모드에서 신호 손실 시 수행할 동작
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    @NotNull
    private CommanderModeLostActionEnum commanderModeLostAction;

    /**
     * 커맨더 비행 모드 (필수, V1.0.0 이후)
     * 커맨더 모드에서의 비행 모드
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    @NotNull
    private CommanderFlightModeEnum commanderFlightMode;

    /**
     * 커맨더 비행 고도 (필수, V1.0.0 이후)
     * 2 ~ 3000 범위, 커맨더 모드에서의 비행 고도 (미터)
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    @NotNull
    @Min(2)
    @Max(3000)
    private Float commanderFlightHeight;

    /**
     * 시뮬레이션 미션 (V1.0.0 이후)
     * 비행 전 시뮬레이션 미션 설정
     */
    @Valid
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    private SimulateMission simulateMission;

    /**
     * 기본 생성자
     */
    public TakeoffToPointRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "TakeoffToPointRequest{" +
                "flightId='" + flightId + '\'' +
                ", targetLongitude=" + targetLongitude +
                ", targetLatitude=" + targetLatitude +
                ", targetHeight=" + targetHeight +
                ", securityTakeoffHeight=" + securityTakeoffHeight +
                ", rthAltitude=" + rthAltitude +
                ", rcLostAction=" + rcLostAction +
                ", exitWaylineWhenRcLost=" + exitWaylineWhenRcLost +
                ", maxSpeed=" + maxSpeed +
                ", rthMode=" + rthMode +
                ", commanderModeLostAction=" + commanderModeLostAction +
                ", commanderFlightMode=" + commanderFlightMode +
                ", commanderFlightHeight=" + commanderFlightHeight +
                ", simulateMission=" + simulateMission +
                '}';
    }

    /**
     * 비행 ID를 반환합니다.
     * 
     * @return 비행 ID
     */
    public String getFlightId() {
        return flightId;
    }

    /**
     * 비행 ID를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param flightId 설정할 비행 ID
     * @return 현재 TakeoffToPointRequest 객체
     */
    public TakeoffToPointRequest setFlightId(String flightId) {
        this.flightId = flightId;
        return this;
    }

    /**
     * 목적지 경도를 반환합니다.
     * 
     * @return 목적지 경도
     */
    public Float getTargetLongitude() {
        return targetLongitude;
    }

    /**
     * 목적지 경도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param targetLongitude 설정할 목적지 경도
     * @return 현재 TakeoffToPointRequest 객체
     */
    public TakeoffToPointRequest setTargetLongitude(Float targetLongitude) {
        this.targetLongitude = targetLongitude;
        return this;
    }

    /**
     * 목적지 위도를 반환합니다.
     * 
     * @return 목적지 위도
     */
    public Float getTargetLatitude() {
        return targetLatitude;
    }

    /**
     * 목적지 위도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param targetLatitude 설정할 목적지 위도
     * @return 현재 TakeoffToPointRequest 객체
     */
    public TakeoffToPointRequest setTargetLatitude(Float targetLatitude) {
        this.targetLatitude = targetLatitude;
        return this;
    }

    /**
     * 목적지 고도를 반환합니다.
     * 
     * @return 목적지 고도 (미터)
     */
    public Float getTargetHeight() {
        return targetHeight;
    }

    /**
     * 목적지 고도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param targetHeight 설정할 목적지 고도 (미터)
     * @return 현재 TakeoffToPointRequest 객체
     */
    public TakeoffToPointRequest setTargetHeight(Float targetHeight) {
        this.targetHeight = targetHeight;
        return this;
    }

    /**
     * 안전 이륙 고도를 반환합니다.
     * 
     * @return 안전 이륙 고도 (미터)
     */
    public Float getSecurityTakeoffHeight() {
        return securityTakeoffHeight;
    }

    /**
     * 안전 이륙 고도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param securityTakeoffHeight 설정할 안전 이륙 고도 (미터)
     * @return 현재 TakeoffToPointRequest 객체
     */
    public TakeoffToPointRequest setSecurityTakeoffHeight(Float securityTakeoffHeight) {
        this.securityTakeoffHeight = securityTakeoffHeight;
        return this;
    }

    /**
     * RTH 고도를 반환합니다.
     * 
     * @return RTH 고도 (미터)
     */
    public Float getRthAltitude() {
        return rthAltitude;
    }

    /**
     * RTH 고도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param rthAltitude 설정할 RTH 고도 (미터)
     * @return 현재 TakeoffToPointRequest 객체
     */
    public TakeoffToPointRequest setRthAltitude(Float rthAltitude) {
        this.rthAltitude = rthAltitude;
        return this;
    }

    /**
     * RC 신호 손실 액션을 반환합니다.
     * 
     * @return RC 신호 손실 액션
     */
    public RcLostActionEnum getRcLostAction() {
        return rcLostAction;
    }

    /**
     * RC 신호 손실 액션을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param rcLostAction 설정할 RC 신호 손실 액션
     * @return 현재 TakeoffToPointRequest 객체
     */
    public TakeoffToPointRequest setRcLostAction(RcLostActionEnum rcLostAction) {
        this.rcLostAction = rcLostAction;
        return this;
    }

    /**
     * RC 신호 손실 시 웨이라인 종료를 반환합니다.
     * 
     * @return RC 신호 손실 시 웨이라인 종료
     */
    public ExitWaylineWhenRcLostEnum getExitWaylineWhenRcLost() {
        return exitWaylineWhenRcLost;
    }

    /**
     * RC 신호 손실 시 웨이라인 종료를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param exitWaylineWhenRcLost 설정할 RC 신호 손실 시 웨이라인 종료
     * @return 현재 TakeoffToPointRequest 객체
     */
    public TakeoffToPointRequest setExitWaylineWhenRcLost(ExitWaylineWhenRcLostEnum exitWaylineWhenRcLost) {
        this.exitWaylineWhenRcLost = exitWaylineWhenRcLost;
        return this;
    }

    /**
     * 최대 속도를 반환합니다.
     * 
     * @return 최대 속도 (m/s)
     */
    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * RTH 모드를 반환합니다.
     * 
     * @return RTH 모드
     */
    public RthModeEnum getRthMode() {
        return rthMode;
    }

    /**
     * RTH 모드를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param rthMode 설정할 RTH 모드
     * @return 현재 TakeoffToPointRequest 객체
     */
    public TakeoffToPointRequest setRthMode(RthModeEnum rthMode) {
        this.rthMode = rthMode;
        return this;
    }

    /**
     * 커맨더 모드 신호 손실 액션을 반환합니다.
     * 
     * @return 커맨더 모드 신호 손실 액션
     */
    public CommanderModeLostActionEnum getCommanderModeLostAction() {
        return commanderModeLostAction;
    }

    /**
     * 커맨더 모드 신호 손실 액션을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param commanderModeLostAction 설정할 커맨더 모드 신호 손실 액션
     * @return 현재 TakeoffToPointRequest 객체
     */
    public TakeoffToPointRequest setCommanderModeLostAction(CommanderModeLostActionEnum commanderModeLostAction) {
        this.commanderModeLostAction = commanderModeLostAction;
        return this;
    }

    /**
     * 커맨더 비행 모드를 반환합니다.
     * 
     * @return 커맨더 비행 모드
     */
    public CommanderFlightModeEnum getCommanderFlightMode() {
        return commanderFlightMode;
    }

    /**
     * 커맨더 비행 모드를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param commanderFlightMode 설정할 커맨더 비행 모드
     * @return 현재 TakeoffToPointRequest 객체
     */
    public TakeoffToPointRequest setCommanderFlightMode(CommanderFlightModeEnum commanderFlightMode) {
        this.commanderFlightMode = commanderFlightMode;
        return this;
    }

    /**
     * 커맨더 비행 고도를 반환합니다.
     * 
     * @return 커맨더 비행 고도 (미터)
     */
    public Float getCommanderFlightHeight() {
        return commanderFlightHeight;
    }

    /**
     * 커맨더 비행 고도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param commanderFlightHeight 설정할 커맨더 비행 고도 (미터)
     * @return 현재 TakeoffToPointRequest 객체
     */
    public TakeoffToPointRequest setCommanderFlightHeight(Float commanderFlightHeight) {
        this.commanderFlightHeight = commanderFlightHeight;
        return this;
    }

    /**
     * 최대 속도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param maxSpeed 설정할 최대 속도 (m/s)
     * @return 현재 TakeoffToPointRequest 객체
     */
    public TakeoffToPointRequest setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    /**
     * 시뮬레이션 미션을 반환합니다.
     * 
     * @return 시뮬레이션 미션
     */
    public SimulateMission getSimulateMission() {
        return simulateMission;
    }

    /**
     * 시뮬레이션 미션을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param simulateMission 설정할 시뮬레이션 미션
     * @return 현재 TakeoffToPointRequest 객체
     */
    public TakeoffToPointRequest setSimulateMission(SimulateMission simulateMission) {
        this.simulateMission = simulateMission;
        return this;
    }
}
