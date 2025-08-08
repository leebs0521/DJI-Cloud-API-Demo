package com.dji.sdk.cloudapi.property;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.cloudapi.device.SwitchActionEnum;
import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 열화상 카메라 등온선 상태 설정 클래스
 * 
 * 이 클래스는 열화상 카메라의 등온선 기능 상태를 설정하기 위한 데이터를 정의합니다.
 * BaseModel을 상속받아 유효성 검사를 지원하며, 열화상 이미지에서
 * 특정 온도 범위를 시각적으로 강조하는 등온선 기능을 관리합니다.
 * 
 * 주요 구성 요소:
 * - payloadIndex: 페이로드 인덱스
 * - thermalIsothermState: 열화상 등온선 상태 열거형
 * 
 * 이 클래스는 열화상 카메라의 등온선 기능을 활성화하거나 비활성화하는 데 사용됩니다.
 * 등온선 기능을 통해 관심 있는 온도 범위의 객체를 더욱 명확하게 관찰할 수 있습니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public class ThermalIsothermStateSet extends BaseModel {

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
     * 열화상 등온선 상태
     * 
     * 열화상 카메라의 등온선 기능 상태를 설정합니다.
     * SwitchActionEnum을 사용하여 등온선 기능을 활성화하거나 비활성화할 수 있습니다.
     * 
     * 가능한 상태:
     * - ENABLE: 등온선 기능 활성화
     * - DISABLE: 등온선 기능 비활성화
     * 
     * 등온선 기능이 활성화되면 설정된 온도 범위의 객체가 더욱 명확하게 표시됩니다.
     */
    @NotNull
    private SwitchActionEnum thermalIsothermState;

    public ThermalIsothermStateSet() {
    }

    @Override
    public String toString() {
        return "ThermalIsothermStateSet{" +
                "payloadIndex=" + payloadIndex +
                ", thermalIsothermState=" + thermalIsothermState +
                '}';
    }

    /**
     * 설정을 맵 형태로 변환합니다.
     * 
     * 페이로드 인덱스를 키로 하고, 열화상 등온선 상태를 값으로 하는 맵을 반환합니다.
     * API 호출 시 사용되는 JSON 형태로 변환됩니다.
     * 
     * @return 설정 정보를 담은 맵
     */
    @JsonValue
    public Map<String, Object> toMap() {
        return Map.of(payloadIndex.toString(), Map.of("thermal_isotherm_state", thermalIsothermState.getAction()));
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
    public ThermalIsothermStateSet setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 열화상 등온선 상태를 반환합니다.
     * 
     * @return 열화상 등온선 상태 열거형
     */
    public SwitchActionEnum getThermalIsothermState() {
        return thermalIsothermState;
    }

    /**
     * 열화상 등온선 상태를 설정합니다.
     * 
     * @param thermalIsothermState 열화상 등온선 상태 열거형
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ThermalIsothermStateSet setThermalIsothermState(SwitchActionEnum thermalIsothermState) {
        this.thermalIsothermState = thermalIsothermState;
        return this;
    }
}
