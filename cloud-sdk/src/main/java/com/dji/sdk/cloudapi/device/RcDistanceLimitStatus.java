package com.dji.sdk.cloudapi.device;

/**
 * RC 거리 제한 상태 클래스
 * 
 * 이 클래스는 드론의 거리 제한 상태를 관리합니다.
 * 거리 제한의 활성화 상태와 제한 거리 값을 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/27
 */
public class RcDistanceLimitStatus {

    /**
     * 거리 제한 상태 (0: 비활성화, 1: 활성화)
     */
    private Integer state;

    /**
     * 거리 제한 값 (미터)
     */
    private Integer distanceLimit;

    /**
     * 기본 생성자
     */
    public RcDistanceLimitStatus() {
    }

    @Override
    public String toString() {
        return "RcDistanceLimitStatusSet{" +
                "state=" + state +
                ", distanceLimit=" + distanceLimit +
                '}';
    }

    /**
     * 거리 제한 상태를 반환합니다.
     * 
     * @return 거리 제한 상태 (0: 비활성화, 1: 활성화)
     */
    public Integer getState() {
        return state;
    }

    /**
     * 거리 제한 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param state 설정할 거리 제한 상태 (0: 비활성화, 1: 활성화)
     * @return 현재 RcDistanceLimitStatus 객체
     */
    public RcDistanceLimitStatus setState(Integer state) {
        this.state = state;
        return this;
    }

    /**
     * 거리 제한 값을 반환합니다.
     * 
     * @return 거리 제한 값 (미터)
     */
    public Integer getDistanceLimit() {
        return distanceLimit;
    }

    /**
     * 거리 제한 값을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param distanceLimit 설정할 거리 제한 값 (미터)
     * @return 현재 RcDistanceLimitStatus 객체
     */
    public RcDistanceLimitStatus setDistanceLimit(Integer distanceLimit) {
        this.distanceLimit = distanceLimit;
        return this;
    }
}
