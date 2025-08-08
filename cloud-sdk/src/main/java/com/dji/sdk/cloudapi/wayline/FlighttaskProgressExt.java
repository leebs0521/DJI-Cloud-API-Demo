package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.annotations.CloudSDKVersion;
import com.dji.sdk.config.version.CloudSDKVersionEnum;

/**
 * 비행 작업 진행 상황 확장 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업의 진행 상황에 대한 확장 정보를 정의합니다.
 * 현재 웨이포인트 인덱스, 미디어 파일 수, 중단점 정보 등을 포함하여
 * 비행 작업의 상세한 진행 상황을 추적할 수 있습니다.
 * 
 * 주요 구성 요소:
 * - currentWaypointIndex: 현재 웨이포인트 인덱스
 * - mediaCount: 미디어 파일 수
 * - flightId: 비행 ID
 * - trackId: 트랙 ID
 * - breakPoint: 중단점 정보
 * - waylineMissionState: 웨이라인 미션 상태
 * - waylineId: 웨이라인 ID
 * 
 * 이 클래스는 비행 작업의 세부적인 진행 상황을
 * 모니터링하고 관리하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/9
 */
public class FlighttaskProgressExt {

    /**
     * 현재 웨이포인트 인덱스
     * 
     * 비행 작업에서 현재 실행 중인 웨이포인트의 인덱스입니다.
     * 0부터 시작하며, 전체 웨이포인트 중 현재 위치를 나타냅니다.
     */
    private Integer currentWaypointIndex;

    /**
     * 미디어 파일 수
     * 
     * 비행 작업 중 생성된 미디어 파일의 개수입니다.
     * 사진, 비디오 등의 미디어 파일 수를 추적합니다.
     */
    private Integer mediaCount;

    /**
     * 비행 ID
     * 
     * 현재 실행 중인 비행 작업의 고유 식별자입니다.
     * 비행 작업을 구분하고 관리하는 데 사용됩니다.
     */
    private String flightId;

    /**
     * 트랙 ID
     * 
     * 비행 경로의 고유 식별자입니다.
     * 웨이라인 비행의 경로를 추적하는 데 사용됩니다.
     */
    private String trackId;

    /**
     * 중단점 정보
     * 
     * 비행 작업의 중단점 관련 정보를 포함합니다.
     * 중단점 설정, 복구 등의 정보를 제공합니다.
     */
    private ProgressExtBreakPoint breakPoint;

    /**
     * 웨이라인 미션 상태
     * 
     * 웨이라인 비행 미션의 현재 상태를 나타냅니다.
     * V1_0_0 버전부터 지원됩니다.
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    private WaylineMissionStateEnum waylineMissionState;

    /**
     * 웨이라인 ID
     * 
     * 현재 실행 중인 웨이라인의 고유 식별자입니다.
     * 비행 경로 진입의 전환 단계를 포함합니다.
     * 예를 들어, 0은 우주선이 첫 번째 경로에 진입하거나
     * 이미 실행 중임을 나타냅니다.
     * V1_0_0 버전부터 지원됩니다.
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    private Integer waylineId;

    public FlighttaskProgressExt() {
    }

    @Override
    public String toString() {
        return "FlighttaskProgressExt{" +
                "currentWaypointIndex=" + currentWaypointIndex +
                ", mediaCount=" + mediaCount +
                ", flightId='" + flightId + '\'' +
                ", trackId='" + trackId + '\'' +
                ", breakPoint=" + breakPoint +
                ", waylineMissionState=" + waylineMissionState +
                ", waylineId=" + waylineId +
                '}';
    }

    /**
     * 현재 웨이포인트 인덱스를 반환합니다.
     * 
     * @return 현재 웨이포인트 인덱스
     */
    public Integer getCurrentWaypointIndex() {
        return currentWaypointIndex;
    }

    /**
     * 현재 웨이포인트 인덱스를 설정합니다.
     * 
     * @param currentWaypointIndex 현재 웨이포인트 인덱스
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskProgressExt setCurrentWaypointIndex(Integer currentWaypointIndex) {
        this.currentWaypointIndex = currentWaypointIndex;
        return this;
    }

    /**
     * 미디어 파일 수를 반환합니다.
     * 
     * @return 미디어 파일 수
     */
    public Integer getMediaCount() {
        return mediaCount;
    }

    /**
     * 미디어 파일 수를 설정합니다.
     * 
     * @param mediaCount 미디어 파일 수
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskProgressExt setMediaCount(Integer mediaCount) {
        this.mediaCount = mediaCount;
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
     * 비행 ID를 설정합니다.
     * 
     * @param flightId 비행 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskProgressExt setFlightId(String flightId) {
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
     * 트랙 ID를 설정합니다.
     * 
     * @param trackId 트랙 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskProgressExt setTrackId(String trackId) {
        this.trackId = trackId;
        return this;
    }

    /**
     * 중단점 정보를 반환합니다.
     * 
     * @return 중단점 정보
     */
    public ProgressExtBreakPoint getBreakPoint() {
        return breakPoint;
    }

    /**
     * 중단점 정보를 설정합니다.
     * 
     * @param breakPoint 중단점 정보
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskProgressExt setBreakPoint(ProgressExtBreakPoint breakPoint) {
        this.breakPoint = breakPoint;
        return this;
    }

    /**
     * 웨이라인 미션 상태를 반환합니다.
     * 
     * @return 웨이라인 미션 상태
     */
    public WaylineMissionStateEnum getWaylineMissionState() {
        return waylineMissionState;
    }

    /**
     * 웨이라인 미션 상태를 설정합니다.
     * 
     * @param waylineMissionState 웨이라인 미션 상태
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskProgressExt setWaylineMissionState(WaylineMissionStateEnum waylineMissionState) {
        this.waylineMissionState = waylineMissionState;
        return this;
    }

    /**
     * 웨이라인 ID를 반환합니다.
     * 
     * @return 웨이라인 ID
     */
    public Integer getWaylineId() {
        return waylineId;
    }

    /**
     * 웨이라인 ID를 설정합니다.
     * 
     * @param waylineId 웨이라인 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskProgressExt setWaylineId(Integer waylineId) {
        this.waylineId = waylineId;
        return this;
    }
}


