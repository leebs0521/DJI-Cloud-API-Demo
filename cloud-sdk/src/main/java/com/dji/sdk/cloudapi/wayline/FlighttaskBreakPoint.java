package com.dji.sdk.cloudapi.wayline;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 비행 작업 중단점 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업의 중단점 정보를 정의합니다.
 * 비행이 중단된 위치와 상태에 대한 정보를 포함하여
 * 복구 작업을 지원합니다.
 * 
 * 주요 구성 요소:
 * - index: 중단점 인덱스
 * - state: 중단점 상태
 * - progress: 현재 웨이라인 세그먼트 진행률
 * - waylineId: 웨이라인 ID
 * 
 * 이 클래스는 비행 작업이 중단되었을 때 정확한 위치와
 * 상태 정보를 제공하여 복구 작업을 지원합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/6
 */
public class FlighttaskBreakPoint {

    /**
     * 중단점 인덱스
     * 
     * 웨이라인에서 중단된 지점의 인덱스입니다.
     * 
     * 제약 조건: 최소값 0
     */
    @NotNull
    @Min(0)
    private Integer index;

    /**
     * 중단점 상태
     * 
     * 중단점이 웨이라인 세그먼트 위에 있는지
     * 또는 웨이포인트 위에 있는지를 나타냅니다.
     */
    @NotNull
    private BreakpointStateEnum state;

    /**
     * 현재 웨이라인 세그먼트 진행률
     * 
     * 현재 웨이라인 세그먼트의 진행률을 백분율로 나타냅니다.
     * 0.0 ~ 1.0 사이의 값입니다.
     * 
     * 제약 조건: 0.0 ~ 1.0 사이의 값
     */
    @NotNull
    @Min(0)
    @Max(1)
    private Float progress;

    /**
     * 웨이라인 ID
     * 
     * 중단된 웨이라인의 고유 식별자입니다.
     */
    @NotNull
    private Integer waylineId;

    public FlighttaskBreakPoint() {}

    @Override
    public String toString() {
        return "FlighttaskBreakPoint{" +
                "index=" + index +
                ", state=" + state +
                ", progress=" + progress +
                ", waylineId=" + waylineId +
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
    public FlighttaskBreakPoint setIndex(Integer index) {
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
    public FlighttaskBreakPoint setState(BreakpointStateEnum state) {
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
    public FlighttaskBreakPoint setProgress(Float progress) {
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
    public FlighttaskBreakPoint setWaylineId(Integer waylineId) {
        this.waylineId = waylineId;
        return this;
    }
}