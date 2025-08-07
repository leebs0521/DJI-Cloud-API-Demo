package com.dji.sdk.cloudapi.device;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;

/**
 * 페이로드 펌웨어 버전 클래스
 * 
 * 이 클래스는 페이로드의 펌웨어 버전 정보를 담습니다.
 * 페이로드 위치와 펌웨어 버전을 포함합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/4/28
 */
public class PayloadFirmwareVersion {

    /**
     * 페이로드 위치
     */
    private PayloadPositionEnum position;

    /**
     * 펌웨어 버전 문자열
     */
    private String firmwareVersion;

    /**
     * 기본 생성자
     */
    public PayloadFirmwareVersion() {
    }

    /**
     * Map으로부터 PayloadFirmwareVersion 객체를 생성합니다.
     * 
     * @param map 페이로드 펌웨어 버전 정보가 담긴 Map
     */
    @JsonCreator
    public PayloadFirmwareVersion(Map<String, Object> map) {
        Map.Entry<String, Object> entry = (Map.Entry<String, Object>) map.entrySet().toArray()[0];
        this.position = PayloadPositionEnum.find(Integer.parseInt(entry.getKey().split("-")[1]));
        this.firmwareVersion = ((Map<String, String>) entry.getValue()).values().toArray(String[]::new)[0];
    }

    @Override
    public String toString() {
        return "PayloadFirmwareVersion{" +
                "position=" + position +
                ", firmwareVersion='" + firmwareVersion + '\'' +
                '}';
    }

    /**
     * 페이로드 위치를 반환합니다.
     * 
     * @return 페이로드 위치
     */
    public PayloadPositionEnum getPosition() {
        return position;
    }

    /**
     * 페이로드 위치를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param position 설정할 페이로드 위치
     * @return 현재 PayloadFirmwareVersion 객체
     */
    public PayloadFirmwareVersion setPosition(PayloadPositionEnum position) {
        this.position = position;
        return this;
    }

    /**
     * 펌웨어 버전을 반환합니다.
     * 
     * @return 펌웨어 버전 문자열
     */
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * 펌웨어 버전을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param firmwareVersion 설정할 펌웨어 버전 문자열
     * @return 현재 PayloadFirmwareVersion 객체
     */
    public PayloadFirmwareVersion setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
        return this;
    }
}
