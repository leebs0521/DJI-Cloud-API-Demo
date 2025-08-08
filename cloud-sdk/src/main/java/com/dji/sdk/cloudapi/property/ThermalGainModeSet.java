package com.dji.sdk.cloudapi.property;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.cloudapi.device.ThermalGainModeEnum;
import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 열화상 카메라 게인 모드 설정 클래스
 * 
 * 이 클래스는 열화상 카메라의 게인 모드를 설정하기 위한 데이터를 정의합니다.
 * BaseModel을 상속받아 유효성 검사를 지원하며, 열화상 카메라의
 * 감도와 화질을 제어하는 게인 모드를 관리합니다.
 * 
 * 주요 구성 요소:
 * - payloadIndex: 페이로드 인덱스
 * - thermalGainMode: 열화상 게인 모드 열거형
 * 
 * 이 클래스는 열화상 카메라의 게인 모드를 설정하여 다양한 환경에서
 * 최적의 열화상 이미지를 얻을 수 있도록 하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public class ThermalGainModeSet extends BaseModel {

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
     * 열화상 게인 모드
     * 
     * 열화상 카메라의 게인 모드를 설정합니다.
     * ThermalGainModeEnum을 사용하여 다양한 게인 모드 중 하나를 선택할 수 있습니다.
     * 
     * 가능한 모드:
     * - AUTO: 자동 게인 모드
     * - MANUAL: 수동 게인 모드
     * - HIGH: 고감도 모드
     * - LOW: 저감도 모드
     */
    @NotNull
    private ThermalGainModeEnum thermalGainMode;

    public ThermalGainModeSet() {
    }

    @Override
    public String toString() {
        return "ThermalGainModeSet{" +
                "payloadIndex=" + payloadIndex +
                ", thermalGainMode=" + thermalGainMode +
                '}';
    }

    /**
     * 설정을 맵 형태로 변환합니다.
     * 
     * 페이로드 인덱스를 키로 하고, 열화상 게인 모드를 값으로 하는 맵을 반환합니다.
     * API 호출 시 사용되는 JSON 형태로 변환됩니다.
     * 
     * @return 설정 정보를 담은 맵
     */
    @JsonValue
    public Map<String, Object> toMap() {
        return Map.of(payloadIndex.toString(), Map.of("thermal_gain_mode", thermalGainMode.getMode()));
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
    public ThermalGainModeSet setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 열화상 게인 모드를 반환합니다.
     * 
     * @return 열화상 게인 모드 열거형
     */
    public ThermalGainModeEnum getThermalGainMode() {
        return thermalGainMode;
    }

    /**
     * 열화상 게인 모드를 설정합니다.
     * 
     * @param thermalGainMode 열화상 게인 모드 열거형
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ThermalGainModeSet setThermalGainMode(ThermalGainModeEnum thermalGainMode) {
        this.thermalGainMode = thermalGainMode;
        return this;
    }
}
