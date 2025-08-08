package com.dji.sdk.cloudapi.property;

import com.dji.sdk.cloudapi.device.SwitchActionEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 드론 거리 제한 상태 데이터 클래스
 * 
 * 이 클래스는 드론의 거리 제한 기능 상태를 정의합니다.
 * 거리 제한의 활성화/비활성화 상태와 최대 비행 거리를 관리합니다.
 * 
 * 주요 구성 요소:
 * - state: 거리 제한 활성화/비활성화 상태
 * - distanceLimit: 최대 비행 거리 (미터)
 * 
 * 이 클래스는 드론의 안전한 비행을 위해 최대 비행 거리를 제한하는 데 사용됩니다.
 * 규정 준수 및 안전 관리를 위해 비행 거리를 제한할 수 있습니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/27
 */
public class DistanceLimitStatusData {

    /**
     * 거리 제한 상태
     * 
     * 드론의 거리 제한 기능을 활성화하거나 비활성화하는 상태를 설정합니다.
     * SwitchActionEnum을 사용하여 ON/OFF 상태를 제어할 수 있습니다.
     * 
     * 가능한 상태:
     * - ENABLE: 거리 제한 기능 활성화
     * - DISABLE: 거리 제한 기능 비활성화
     */
    private SwitchActionEnum state;

    /**
     * 거리 제한값
     * 
     * 드론의 최대 비행 거리를 미터 단위로 설정합니다.
     * 최소 15미터, 최대 8000미터까지 설정 가능합니다.
     * 
     * 제약 조건:
     * - 최소값: 15미터
     * - 최대값: 8000미터
     * 
     * 이 값은 드론이 출발점으로부터 최대 얼마나 멀리 비행할 수 있는지를 결정합니다.
     */
    @Min(15)
    @Max(8000)
    @JsonProperty("distance_limit")
    private Integer distanceLimit;

    public DistanceLimitStatusData() {
    }

    @Override
    public String toString() {
        return "DistanceLimitStatusData{" +
                "state=" + state +
                ", distanceLimit=" + distanceLimit +
                '}';
    }

    /**
     * 거리 제한 상태를 반환합니다.
     * 
     * @return 거리 제한 상태 열거형
     */
    public SwitchActionEnum getState() {
        return state;
    }

    /**
     * 거리 제한 상태를 설정합니다.
     * 
     * @param state 거리 제한 상태 열거형
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DistanceLimitStatusData setState(SwitchActionEnum state) {
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
     * 거리 제한값을 설정합니다.
     * 
     * @param distanceLimit 거리 제한값 (미터)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DistanceLimitStatusData setDistanceLimit(Integer distanceLimit) {
        this.distanceLimit = distanceLimit;
        return this;
    }
}
