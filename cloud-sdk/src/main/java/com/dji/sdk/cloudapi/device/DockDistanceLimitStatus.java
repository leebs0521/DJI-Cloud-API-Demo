package com.dji.sdk.cloudapi.device;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 도크 거리 제한 상태 클래스
 * 
 * 이 클래스는 드론의 거리 제한 상태를 담습니다.
 * 거리 제한 상태, 거리 제한값, 거리 제한 근접 여부 등을 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/27
 */
public class DockDistanceLimitStatus {

    /**
     * 거리 제한 상태
     */
    private SwitchActionEnum state;

    /**
     * 거리 제한값 (미터)
     */
    private Integer distanceLimit;

    /**
     * 거리 제한 근접 여부 (true: 근접, false: 근접하지 않음)
     */
    @JsonProperty("is_near_distance_limit")
    private Boolean nearDistanceLimit;

    /**
     * 기본 생성자
     */
    public DockDistanceLimitStatus() {
    }

    @Override
    public String toString() {
        return "DockDistanceLimitStatusSet{" +
                "state=" + state +
                ", distanceLimit=" + distanceLimit +
                ", nearDistanceLimit=" + nearDistanceLimit +
                '}';
    }

    /**
     * 거리 제한 상태를 반환합니다.
     * 
     * @return 거리 제한 상태
     */
    public SwitchActionEnum getState() {
        return state;
    }

    /**
     * 거리 제한 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param state 설정할 거리 제한 상태
     * @return 현재 DockDistanceLimitStatus 객체
     */
    public DockDistanceLimitStatus setState(SwitchActionEnum state) {
        this.state = state;
        return this;
    }

    /**
     * 거리 제한값을 반환합니다.
     * 
     * @return 거리 제한값 (미터)
     */
    public Integer getDistanceLimit() {
        return distanceLimit;
    }

    /**
     * 거리 제한값을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param distanceLimit 설정할 거리 제한값 (미터)
     * @return 현재 DockDistanceLimitStatus 객체
     */
    public DockDistanceLimitStatus setDistanceLimit(Integer distanceLimit) {
        this.distanceLimit = distanceLimit;
        return this;
    }

    /**
     * 거리 제한 근접 여부를 반환합니다.
     * 
     * @return 거리 제한 근접 여부 (true: 근접, false: 근접하지 않음)
     */
    public Boolean getNearDistanceLimit() {
        return nearDistanceLimit;
    }

    /**
     * 거리 제한 근접 여부를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param nearDistanceLimit 설정할 거리 제한 근접 여부
     * @return 현재 DockDistanceLimitStatus 객체
     */
    public DockDistanceLimitStatus setNearDistanceLimit(Boolean nearDistanceLimit) {
        this.nearDistanceLimit = nearDistanceLimit;
        return this;
    }
}
