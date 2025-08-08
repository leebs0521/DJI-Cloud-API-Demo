package com.dji.sdk.cloudapi.wayline;

/**
 * 진행 상황 확장 중단점 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업의 중단점 정보를 정의합니다.
 * 비행이 중단된 위치와 상태에 대한 상세한 정보를 포함합니다.
 * 
 * 주요 구성 요소:
 * - index: 중단점 인덱스
 * - state: 중단점 상태
 * - progress: 현재 웨이라인 세그먼트 진행률
 * - waylineId: 웨이라인 ID
 * - breakReason: 중단 이유
 * - latitude/longitude/height: 중단점 위치 정보
 * - attitudeHead: 요 각도
 * 
 * 이 클래스는 비행 작업이 중단되었을 때 정확한 위치와
 * 상태 정보를 제공하여 복구 작업을 지원합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/6
 */
public class ProgressExtBreakPoint {

    /**
     * 중단점 인덱스
     * 
     * 웨이라인에서 중단된 지점의 인덱스입니다.
     */
    private Integer index;

    /**
     * 중단점 상태
     * 
     * 중단점이 웨이라인 세그먼트 위에 있는지
     * 또는 웨이포인트 위에 있는지를 나타냅니다.
     */
    private BreakpointStateEnum state;

    /**
     * 현재 웨이라인 세그먼트 진행률
     * 
     * 현재 웨이라인 세그먼트의 진행률을 백분율로 나타냅니다.
     * 0.0 ~ 1.0 사이의 값입니다.
     */
    private Float progress;

    /**
     * 웨이라인 ID
     * 
     * 중단된 웨이라인의 고유 식별자입니다.
     */
    private Integer waylineId;

    /**
     * 중단 이유
     * 
     * 비행이 중단된 이유를 나타냅니다.
     * 사용자 명령, 시스템 오류, 안전 조건 등의 이유를 정의합니다.
     */
    private FlighttaskBreakReasonEnum breakReason;

    /**
     * 중단점 위도
     * 
     * 비행이 중단된 지점의 위도 좌표입니다.
     */
    private Float latitude;

    /**
     * 중단점 경도
     * 
     * 비행이 중단된 지점의 경도 좌표입니다.
     */
    private Float longitude;

    /**
     * 중단점 고도
     * 
     * 지구 타원체 표면을 기준으로 한 중단점의 고도입니다.
     */
    private Float height;

    /**
     * 요 각도
     * 
     * 진북(자오선)을 기준으로 한 요 각도입니다.
     * 0시 방향에서 6시 방향까지는 양수,
     * 6시 방향에서 12시 방향까지는 음수 값입니다.
     */
    private Integer attitudeHead;

    public ProgressExtBreakPoint() {}

    @Override
    public String toString() {
        return "FlighttaskBreakPoint{" +
                "index=" + index +
                ", state=" + state +
                ", progress=" + progress +
                ", waylineId=" + waylineId +
                ", breakReason=" + breakReason +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", height=" + height +
                ", attitudeHead=" + attitudeHead +
                '}';
    }

    /**
     * 중단점 인덱스를 반환합니다.
     * 
     * @return 중단점 인덱스
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * 중단점 인덱스를 설정합니다.
     * 
     * @param index 중단점 인덱스
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ProgressExtBreakPoint setIndex(Integer index) {
        this.index = index;
        return this;
    }

    /**
     * 중단점 상태를 반환합니다.
     * 
     * @return 중단점 상태
     */
    public BreakpointStateEnum getState() {
        return state;
    }

    /**
     * 중단점 상태를 설정합니다.
     * 
     * @param state 중단점 상태
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ProgressExtBreakPoint setState(BreakpointStateEnum state) {
        this.state = state;
        return this;
    }

    /**
     * 현재 웨이라인 세그먼트 진행률을 반환합니다.
     * 
     * @return 진행률 (0.0 ~ 1.0)
     */
    public Float getProgress() {
        return progress;
    }

    /**
     * 현재 웨이라인 세그먼트 진행률을 설정합니다.
     * 
     * @param progress 진행률 (0.0 ~ 1.0)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ProgressExtBreakPoint setProgress(Float progress) {
        this.progress = progress;
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
    public ProgressExtBreakPoint setWaylineId(Integer waylineId) {
        this.waylineId = waylineId;
        return this;
    }

    /**
     * 중단 이유를 반환합니다.
     * 
     * @return 중단 이유
     */
    public FlighttaskBreakReasonEnum getBreakReason() {
        return breakReason;
    }

    /**
     * 중단 이유를 설정합니다.
     * 
     * @param breakReason 중단 이유
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ProgressExtBreakPoint setBreakReason(FlighttaskBreakReasonEnum breakReason) {
        this.breakReason = breakReason;
        return this;
    }

    /**
     * 중단점 위도를 반환합니다.
     * 
     * @return 중단점 위도
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * 중단점 위도를 설정합니다.
     * 
     * @param latitude 중단점 위도
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ProgressExtBreakPoint setLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * 중단점 경도를 반환합니다.
     * 
     * @return 중단점 경도
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * 중단점 경도를 설정합니다.
     * 
     * @param longitude 중단점 경도
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ProgressExtBreakPoint setLongitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    /**
     * 중단점 고도를 반환합니다.
     * 
     * @return 중단점 고도
     */
    public Float getHeight() {
        return height;
    }

    /**
     * 중단점 고도를 설정합니다.
     * 
     * @param height 중단점 고도
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ProgressExtBreakPoint setHeight(Float height) {
        this.height = height;
        return this;
    }

    /**
     * 요 각도를 반환합니다.
     * 
     * @return 요 각도
     */
    public Integer getAttitudeHead() {
        return attitudeHead;
    }

    /**
     * 요 각도를 설정합니다.
     * 
     * @param attitudeHead 요 각도
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ProgressExtBreakPoint setAttitudeHead(Integer attitudeHead) {
        this.attitudeHead = attitudeHead;
        return this;
    }
}