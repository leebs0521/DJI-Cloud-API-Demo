package com.dji.sdk.cloudapi.control;

import com.dji.sdk.annotations.CloudSDKVersion;
import com.dji.sdk.cloudapi.wayline.WaylineErrorCodeEnum;
import com.dji.sdk.config.version.CloudSDKVersionEnum;

import java.util.List;

/**
 * 지정 지점 이륙 진행 상황 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 지정 지점 이륙의 진행 상황을 담는 클래스입니다.
 * 이륙 결과, 상태, 비행 ID, 트랙 ID, 웨이포인트 인덱스, 남은 거리, 남은 시간 등을 포함합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/14
 */
public class TakeoffToPointProgress {

    /**
     * 이륙 결과
     * 지정 지점 이륙의 결과 코드
     */
    private WaylineErrorCodeEnum result;

    /**
     * 이륙 상태
     * 지정 지점 이륙의 현재 상태
     */
    private TakeoffStatusEnum status;

    /**
     * 비행 ID
     * 이륙 작업의 고유 식별자
     */
    private String flightId;

    /**
     * 트랙 ID
     * 비행 트랙의 고유 식별자
     */
    private String trackId;

    /**
     * 웨이포인트 인덱스
     * 현재 웨이포인트의 인덱스
     */
    private Integer wayPointIndex;

    /**
     * 남은 미션 거리
     * 단위: 미터 (m)
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    private Float remainingDistance;

    /**
     * 남은 미션 시간
     * 단위: 초 (s)
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    private Integer remainingTime;

    /**
     * 계획된 궤적 포인트 목록
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    private List<Point> plannedPathPoints;


    /**
     * 기본 생성자
     */
    public TakeoffToPointProgress() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "TakeoffToPointProgress{" +
                "result=" + result +
                ", status=" + status +
                ", flightId='" + flightId + '\'' +
                ", trackId='" + trackId + '\'' +
                ", wayPointIndex=" + wayPointIndex +
                ", remainingDistance=" + remainingDistance +
                ", remainingTime=" + remainingTime +
                ", plannedPathPoints=" + plannedPathPoints +
                '}';
    }

    /**
     * 이륙 결과를 반환합니다.
     * 
     * @return 이륙 결과
     */
    public WaylineErrorCodeEnum getResult() {
        return result;
    }

    /**
     * 이륙 결과를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param result 설정할 이륙 결과
     * @return 현재 TakeoffToPointProgress 객체
     */
    public TakeoffToPointProgress setResult(WaylineErrorCodeEnum result) {
        this.result = result;
        return this;
    }

    /**
     * 이륙 상태를 반환합니다.
     * 
     * @return 이륙 상태
     */
    public TakeoffStatusEnum getStatus() {
        return status;
    }

    /**
     * 이륙 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param status 설정할 이륙 상태
     * @return 현재 TakeoffToPointProgress 객체
     */
    public TakeoffToPointProgress setStatus(TakeoffStatusEnum status) {
        this.status = status;
        return this;
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
     * @return 현재 TakeoffToPointProgress 객체
     */
    public TakeoffToPointProgress setFlightId(String flightId) {
        this.flightId = flightId;
        return this;
    }

    /**
     * 트랙 ID를 반환합니다.
     * 
     * @return 트랙 ID
     */
    public String getTrackId() {
        return trackId;
    }

    /**
     * 트랙 ID를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param trackId 설정할 트랙 ID
     * @return 현재 TakeoffToPointProgress 객체
     */
    public TakeoffToPointProgress setTrackId(String trackId) {
        this.trackId = trackId;
        return this;
    }

    /**
     * 웨이포인트 인덱스를 반환합니다.
     * 
     * @return 웨이포인트 인덱스
     */
    public Integer getWayPointIndex() {
        return wayPointIndex;
    }

    /**
     * 웨이포인트 인덱스를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param wayPointIndex 설정할 웨이포인트 인덱스
     * @return 현재 TakeoffToPointProgress 객체
     */
    public TakeoffToPointProgress setWayPointIndex(Integer wayPointIndex) {
        this.wayPointIndex = wayPointIndex;
        return this;
    }

    /**
     * 남은 미션 거리를 반환합니다.
     * 
     * @return 남은 미션 거리 (미터)
     */
    public Float getRemainingDistance() {
        return remainingDistance;
    }

    /**
     * 남은 미션 거리를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param remainingDistance 설정할 남은 미션 거리 (미터)
     * @return 현재 TakeoffToPointProgress 객체
     */
    public TakeoffToPointProgress setRemainingDistance(Float remainingDistance) {
        this.remainingDistance = remainingDistance;
        return this;
    }

    /**
     * 남은 미션 시간을 반환합니다.
     * 
     * @return 남은 미션 시간 (초)
     */
    public Integer getRemainingTime() {
        return remainingTime;
    }

    /**
     * 남은 미션 시간을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param remainingTime 설정할 남은 미션 시간 (초)
     * @return 현재 TakeoffToPointProgress 객체
     */
    public TakeoffToPointProgress setRemainingTime(Integer remainingTime) {
        this.remainingTime = remainingTime;
        return this;
    }

    /**
     * 계획된 궤적 포인트 목록을 반환합니다.
     * 
     * @return 계획된 궤적 포인트 목록
     */
    public List<Point> getPlannedPathPoints() {
        return plannedPathPoints;
    }

    /**
     * 계획된 궤적 포인트 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param plannedPathPoints 설정할 계획된 궤적 포인트 목록
     * @return 현재 TakeoffToPointProgress 객체
     */
    public TakeoffToPointProgress setPlannedPathPoints(List<Point> plannedPathPoints) {
        this.plannedPathPoints = plannedPathPoints;
        return this;
    }
}
