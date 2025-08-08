package com.dji.sdk.cloudapi.property;

import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * RTH 고도 설정 클래스
 * 
 * 이 클래스는 드론의 Return-to-Home(RTH) 고도를 설정하기 위한 데이터를 정의합니다.
 * BaseModel을 상속받아 유효성 검사를 지원하며, 안전한 귀환을 위한
 * 고도를 관리합니다.
 * 
 * 주요 구성 요소:
 * - rthAltitude: RTH 고도 값 (20-50미터)
 * 
 * 이 클래스는 드론이 자동으로 홈포인트로 귀환할 때의 고도를 설정하는 데 사용됩니다.
 * 안전한 귀환을 위해 장애물을 피해 비행할 수 있는 적절한 고도를 설정합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/3
 */
public class RthAltitudeSet extends BaseModel {

    /**
     * RTH 고도 값
     * 
     * 드론이 자동으로 홈포인트로 귀환할 때의 고도를 미터 단위로 설정합니다.
     * 최소 20미터, 최대 50미터까지 설정 가능합니다.
     * 
     * 제한 사항:
     * - 최소값: 20미터 (장애물 회피를 위한 최소 안전 고도)
     * - 최대값: 50미터 (효율적인 귀환을 위한 최대 고도)
     * 
     * 단위: 미터 (m)
     */
    @NotNull
    @Min(20)
    @Max(50)
    @JsonProperty("rth_altitude")
    private Integer rthAltitude;

    public RthAltitudeSet() {
    }

    @Override
    public String toString() {
        return "RthAltitudeSet{" +
                "rthAltitude=" + rthAltitude +
                '}';
    }

    /**
     * RTH 고도 값을 반환합니다.
     * 
     * @return RTH 고도 값 (미터)
     */
    public Integer getRthAltitude() {
        return rthAltitude;
    }

    /**
     * RTH 고도 값을 설정합니다.
     * 
     * @param rthAltitude RTH 고도 값 (미터, 20-50)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public RthAltitudeSet setRthAltitude(Integer rthAltitude) {
        this.rthAltitude = rthAltitude;
        return this;
    }
}
