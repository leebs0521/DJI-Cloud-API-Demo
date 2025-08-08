package com.dji.sdk.cloudapi.property;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 열화상 카메라 등온선 상한 설정 클래스
 * 
 * 이 클래스는 열화상 카메라의 등온선 상한 값을 설정하기 위한 데이터를 정의합니다.
 * BaseModel을 상속받아 유효성 검사를 지원하며, 열화상 이미지에서
 * 특정 온도 범위를 시각화하는 등온선 기능의 상한값을 관리합니다.
 * 
 * 주요 구성 요소:
 * - payloadIndex: 페이로드 인덱스
 * - thermalIsothermUpperLimit: 열화상 등온선 상한값
 * 
 * 이 클래스는 열화상 카메라의 등온선 기능에서 상한 온도를 설정하는 데 사용됩니다.
 * 특정 온도 이상의 영역을 시각적으로 구분하여 표시할 수 있습니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public class ThermalIsothermUpperLimitSet extends BaseModel {

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
     * 열화상 등온선 상한값
     * 
     * 열화상 카메라의 등온선 기능에서 사용할 상한 온도값입니다.
     * 이 값 이상의 온도를 가진 영역이 등온선으로 표시됩니다.
     * 
     * 단위: 켈빈(K) 또는 섭씨(°C)
     * 사용 예: 50 (섭씨 50도 이상 영역을 등온선으로 표시)
     */
    @NotNull
    private Integer thermalIsothermUpperLimit;

    public ThermalIsothermUpperLimitSet() {
    }

    @Override
    public String toString() {
        return "ThermalIsothermUpperLimitSet{" +
                "payloadIndex=" + payloadIndex +
                ", thermalIsothermUpperLimit=" + thermalIsothermUpperLimit +
                '}';
    }

    /**
     * 설정을 맵 형태로 변환합니다.
     * 
     * 페이로드 인덱스를 키로 하고, 열화상 등온선 상한값을 값으로 하는 맵을 반환합니다.
     * API 호출 시 사용되는 JSON 형태로 변환됩니다.
     * 
     * @return 설정 정보를 담은 맵
     */
    @JsonValue
    public Map<String, Object> toMap() {
        return Map.of(payloadIndex.toString(), Map.of("thermal_isotherm_upper_limit", thermalIsothermUpperLimit));
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
    public ThermalIsothermUpperLimitSet setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 열화상 등온선 상한값을 반환합니다.
     * 
     * @return 열화상 등온선 상한값
     */
    public Integer getThermalIsothermUpperLimit() {
        return thermalIsothermUpperLimit;
    }

    /**
     * 열화상 등온선 상한값을 설정합니다.
     * 
     * @param thermalIsothermUpperLimit 열화상 등온선 상한값
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ThermalIsothermUpperLimitSet setThermalIsothermUpperLimit(Integer thermalIsothermUpperLimit) {
        this.thermalIsothermUpperLimit = thermalIsothermUpperLimit;
        return this;
    }
}
