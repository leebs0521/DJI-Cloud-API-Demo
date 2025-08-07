package com.dji.sdk.cloudapi.device;

import java.util.Arrays;

/**
 * 도크 드론 열화상 지원 팔레트 스타일 클래스
 * 
 * 이 클래스는 도크에 연결된 드론의 열화상 카메라가 지원하는 팔레트 스타일 정보를 담습니다.
 * 페이로드 인덱스, 지원하는 열화상 팔레트 스타일 목록, 버전 등을 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/23
 */
public class DockDroneThermalSupportedPaletteStyle {

    /**
     * 페이로드 인덱스
     */
    private PayloadIndex payloadIndex;

    /**
     * 지원하는 열화상 팔레트 스타일 목록
     */
    private ThermalPaletteStyleEnum[] thermalSupportedPaletteStyles;

    /**
     * 버전
     */
    private Integer version;

    /**
     * 기본 생성자
     */
    public DockDroneThermalSupportedPaletteStyle() {
    }

    @Override
    public String toString() {
        return "DockDroneThermalSupportedPaletteStyle{" +
                "payloadIndex=" + payloadIndex +
                ", thermalSupportedPaletteStyles=" + Arrays.toString(thermalSupportedPaletteStyles) +
                ", version=" + version +
                '}';
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
     * 페이로드 인덱스를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param payloadIndex 설정할 페이로드 인덱스
     * @return 현재 DockDroneThermalSupportedPaletteStyle 객체
     */
    public DockDroneThermalSupportedPaletteStyle setPayloadIndex(PayloadIndex payloadIndex) {
        this.payloadIndex = payloadIndex;
        return this;
    }

    /**
     * 지원하는 열화상 팔레트 스타일 목록을 반환합니다.
     * 
     * @return 지원하는 열화상 팔레트 스타일 목록
     */
    public ThermalPaletteStyleEnum[] getThermalSupportedPaletteStyles() {
        return thermalSupportedPaletteStyles;
    }

    /**
     * 지원하는 열화상 팔레트 스타일 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param thermalSupportedPaletteStyles 설정할 지원하는 열화상 팔레트 스타일 목록
     * @return 현재 DockDroneThermalSupportedPaletteStyle 객체
     */
    public DockDroneThermalSupportedPaletteStyle setThermalSupportedPaletteStyles(ThermalPaletteStyleEnum[] thermalSupportedPaletteStyles) {
        this.thermalSupportedPaletteStyles = thermalSupportedPaletteStyles;
        return this;
    }

    /**
     * 버전을 반환합니다.
     * 
     * @return 버전
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 버전을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param version 설정할 버전
     * @return 현재 DockDroneThermalSupportedPaletteStyle 객체
     */
    public DockDroneThermalSupportedPaletteStyle setVersion(Integer version) {
        this.version = version;
        return this;
    }
}
