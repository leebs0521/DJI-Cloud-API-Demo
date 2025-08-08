package com.dji.sdk.cloudapi.property;

import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 고도 제한 설정 클래스
 * 
 * 이 클래스는 드론의 최대 비행 고도를 설정하기 위한 데이터를 정의합니다.
 * BaseModel을 상속받아 유효성 검사를 지원하며, 안전한 비행을 위한
 * 고도 제한을 관리합니다.
 * 
 * 주요 구성 요소:
 * - heightLimit: 고도 제한 값 (20-1500미터)
 * 
 * 이 클래스는 드론의 안전한 비행을 위해 최대 고도를 제한하는 데 사용됩니다.
 * 규정 준수 및 안전 관리를 위한 중요한 설정입니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/28
 */
public class HeightLimitSet extends BaseModel {

    /**
     * 고도 제한 값
     * 
     * 드론의 최대 비행 고도를 미터 단위로 설정합니다.
     * 최소 20미터, 최대 1500미터까지 설정 가능합니다.
     * 
     * 제한 사항:
     * - 최소값: 20미터 (안전한 이륙을 위한 최소 고도)
     * - 최대값: 1500미터 (법적 규정 및 안전 고도 제한)
     * 
     * 단위: 미터 (m)
     */
    @NotNull
    @Max(1500)
    @Min(20)
    @JsonProperty("height_limit")
    private Integer heightLimit;

    public HeightLimitSet() {
    }

    @Override
    public String toString() {
        return "HeightLimitSet{" +
                "heightLimit=" + heightLimit +
                '}';
    }

    /**
     * 고도 제한 값을 반환합니다.
     * 
     * @return 고도 제한 값 (미터)
     */
    public Integer getHeightLimit() {
        return heightLimit;
    }

    /**
     * 고도 제한 값을 설정합니다.
     * 
     * @param heightLimit 고도 제한 값 (미터, 20-1500)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public HeightLimitSet setHeightLimit(Integer heightLimit) {
        this.heightLimit = heightLimit;
        return this;
    }
}
