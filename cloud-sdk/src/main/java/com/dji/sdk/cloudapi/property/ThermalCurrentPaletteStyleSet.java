package com.dji.sdk.cloudapi.property;

import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.cloudapi.device.ThermalPaletteStyleEnum;
import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 열화상 카메라 팔레트 스타일 설정 클래스
 * 
 * 이 클래스는 열화상 카메라의 색상 팔레트 스타일을 설정하기 위한 데이터를 정의합니다.
 * BaseModel을 상속받아 유효성 검사를 지원하며, 열화상 이미지의
 * 온도를 색상으로 표현하는 팔레트 스타일을 관리합니다.
 * 
 * 주요 구성 요소:
 * - payloadIndex: 페이로드 인덱스
 * - thermalCurrentPaletteStyle: 열화상 현재 팔레트 스타일 열거형
 * 
 * 이 클래스는 열화상 카메라의 색상 팔레트 스타일을 설정하여
 * 온도 정보를 시각적으로 표현하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public class ThermalCurrentPaletteStyleSet extends BaseModel {

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
     * 열화상 현재 팔레트 스타일
     * 
     * 열화상 카메라의 색상 팔레트 스타일을 설정합니다.
     * ThermalPaletteStyleEnum을 사용하여 다양한 색상 팔레트 중 하나를 선택할 수 있습니다.
     * 
     * 가능한 스타일:
     * - WHITE_HOT: 흰색이 뜨거운 온도를 나타내는 스타일
     * - BLACK_HOT: 검은색이 뜨거운 온도를 나타내는 스타일
     * - RED_HOT: 빨간색이 뜨거운 온도를 나타내는 스타일
     * - GREEN_HOT: 초록색이 뜨거운 온도를 나타내는 스타일
     * - BLUE_HOT: 파란색이 뜨거운 온도를 나타내는 스타일
     * - RAINBOW: 무지개 색상 팔레트
     * - IRON: 철 색상 팔레트
     */
    @NotNull
    private ThermalPaletteStyleEnum thermalCurrentPaletteStyle;

    public ThermalCurrentPaletteStyleSet() {
    }

    @Override
    public String toString() {
        return "ThermalCurrentPaletteStyleSet{" +
                "payloadIndex=" + payloadIndex +
                ", thermalCurrentPaletteStyle=" + thermalCurrentPaletteStyle +
                '}';
    }

    /**
     * 설정을 맵 형태로 변환합니다.
     * 
     * 페이로드 인덱스를 키로 하고, 열화상 현재 팔레트 스타일을 값으로 하는 맵을 반환합니다.
     * API 호출 시 사용되는 JSON 형태로 변환됩니다.
     * 
     * @return 설정 정보를 담은 맵
     */
    @JsonValue
    public Map<String, Object> toMap() {
        return Map.of(payloadIndex.toString(), Map.of("thermal_current_palette_style", thermalCurrentPaletteStyle.getStyle()));
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
    public ThermalCurrentPaletteStyleSet setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 열화상 현재 팔레트 스타일을 반환합니다.
     * 
     * @return 열화상 현재 팔레트 스타일 열거형
     */
    public ThermalPaletteStyleEnum getThermalCurrentPaletteStyle() {
        return thermalCurrentPaletteStyle;
    }

    /**
     * 열화상 현재 팔레트 스타일을 설정합니다.
     * 
     * @param thermalCurrentPaletteStyle 열화상 현재 팔레트 스타일 열거형
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ThermalCurrentPaletteStyleSet setThermalCurrentPaletteStyle(ThermalPaletteStyleEnum thermalCurrentPaletteStyle) {
        this.thermalCurrentPaletteStyle = thermalCurrentPaletteStyle;
        return this;
    }
}
