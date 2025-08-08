package com.dji.sdk.cloudapi.property;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 열화상 카메라 등온선 하한 설정 클래스
 * 
 * 이 클래스는 열화상 카메라의 등온선 하한 값을 설정하기 위한 데이터를 정의합니다.
 * BaseModel을 상속받아 유효성 검사를 지원하며, 열화상 이미지에서
 * 등온선 기능이 적용될 온도 범위의 하한값을 관리합니다.
 * 
 * 주요 구성 요소:
 * - payloadIndex: 페이로드 인덱스
 * - thermalIsothermLowerLimit: 열화상 등온선 하한값 (섭씨)
 * 
 * 이 클래스는 열화상 카메라의 등온선 기능에서 관심 있는 온도 범위의
 * 하한값을 설정하는 데 사용됩니다. 등온선 기능이 활성화된 경우에만 유효합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public class ThermalIsothermLowerLimitSet extends BaseModel {

    /**
     * 페이로드 인덱스
     * 
     * 설정할 열화상 카메라의 페이로드 인덱스입니다.
     * 여러 개의 페이로드가 있을 때 특정 페이로드를 식별하는 데 사용됩니다.
     */
    @NotNull
    @Valid
    private PayloadIndex payloadIndex;

    /**
     * 열화상 등온선 하한값
     * 
     * 열화상 카메라의 등온선 기능에서 관심 있는 온도 범위의 하한값을 설정합니다.
     * 단위는 섭씨(°C)이며, 등온선 기능이 활성화된 경우에만 유효합니다.
     * 
     * 설정 가능한 범위는 열화상 카메라의 사양에 따라 다르며,
     * 일반적으로 -40°C에서 500°C 사이의 값을 설정할 수 있습니다.
     */
    @NotNull
    private Integer thermalIsothermLowerLimit;

    public ThermalIsothermLowerLimitSet() {
    }

    @Override
    public String toString() {
        return "ThermalIsothermLowerLimitSet{" +
                "payloadIndex=" + payloadIndex +
                ", thermalIsothermLowerLimit=" + thermalIsothermLowerLimit +
                '}';
    }

    /**
     * 설정을 맵 형태로 변환합니다.
     * 
     * 페이로드 인덱스를 키로 하고, 열화상 등온선 하한값을 값으로 하는 맵을 반환합니다.
     * API 호출 시 사용되는 JSON 형태로 변환됩니다.
     * 
     * @return 설정 정보를 담은 맵
     */
    @JsonValue
    public Map<String, Object> toMap() {
        return Map.of(payloadIndex.toString(), Map.of("thermal_isotherm_lower_limit", thermalIsothermLowerLimit));
    }

    /**
     * 페이로드 인덱스를 반환합니다.
     * 
     * @return 페이로드 인덱스
     */
    public PayloadIndex getPayloadIndex() {
        return payloadIndex;
    }

    /**
     * 페이로드 인덱스를 설정합니다.
     * 
     * @param payloadIndex 페이로드 인덱스
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ThermalIsothermLowerLimitSet setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 열화상 등온선 하한값을 반환합니다.
     * 
     * @return 열화상 등온선 하한값 (섭씨)
     */
    public Integer getThermalIsothermLowerLimit() {
        return thermalIsothermLowerLimit;
    }

    /**
     * 열화상 등온선 하한값을 설정합니다.
     * 
     * @param thermalIsothermLowerLimit 열화상 등온선 하한값 (섭씨)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ThermalIsothermLowerLimitSet setThermalIsothermLowerLimit(Integer thermalIsothermLowerLimit) {
        this.thermalIsothermLowerLimit = thermalIsothermLowerLimit;
        return this;
    }
}
