package com.dji.sdk.cloudapi.livestream;

import java.util.List;

/**
 * RC(Remote Controller) 라이브스트림 용량 정보를 나타내는 클래스
 * RC에서 지원하는 라이브스트림 기능의 제한사항과 가능한 스트림 수를 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/23
 */
public class RcLiveCapacity {

    /**
     * 라이브스트리밍에 사용 가능한 총 비디오 스트림 수
     * 항공기나 장치가 소유한 모든 사용 가능한 라이브 비디오 스트림의 총 개수를 나타냅니다.
     */
    private Integer availableVideoNumber;

    /**
     * 동시에 라이브 스트리밍할 수 있는 최대 총 비디오 스트림 수
     */
    private Integer coexistVideoNumberMax;

    /**
     * 장치 라이브스트리밍 기능 목록
     */
    private List<RcLiveCapacityDevice> deviceList;

    /**
     * 기본 생성자
     */
    public RcLiveCapacity() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "RcLiveCapacity{" +
                "availableVideoNumber=" + availableVideoNumber +
                ", coexistVideoNumberMax=" + coexistVideoNumberMax +
                ", deviceList=" + deviceList +
                '}';
    }

    /**
     * 사용 가능한 비디오 스트림 수를 반환합니다.
     * 
     * @return 사용 가능한 비디오 스트림 수
     */
    public Integer getAvailableVideoNumber() {
        return availableVideoNumber;
    }

    /**
     * 사용 가능한 비디오 스트림 수를 설정합니다.
     * 
     * @param availableVideoNumber 설정할 비디오 스트림 수
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public RcLiveCapacity setAvailableVideoNumber(Integer availableVideoNumber) {
        this.availableVideoNumber = availableVideoNumber;
        return this;
    }

    /**
     * 동시 라이브스트리밍 가능한 최대 비디오 스트림 수를 반환합니다.
     * 
     * @return 동시 라이브스트리밍 가능한 최대 비디오 스트림 수
     */
    public Integer getCoexistVideoNumberMax() {
        return coexistVideoNumberMax;
    }

    /**
     * 동시 라이브스트리밍 가능한 최대 비디오 스트림 수를 설정합니다.
     * 
     * @param coexistVideoNumberMax 설정할 최대 동시 비디오 스트림 수
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public RcLiveCapacity setCoexistVideoNumberMax(Integer coexistVideoNumberMax) {
        this.coexistVideoNumberMax = coexistVideoNumberMax;
        return this;
    }

    /**
     * 장치 라이브스트리밍 기능 목록을 반환합니다.
     * 
     * @return 장치 라이브스트리밍 기능 목록
     */
    public List<RcLiveCapacityDevice> getDeviceList() {
        return deviceList;
    }

    /**
     * 장치 라이브스트리밍 기능 목록을 설정합니다.
     * 
     * @param deviceList 설정할 장치 라이브스트리밍 기능 목록
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public RcLiveCapacity setDeviceList(List<RcLiveCapacityDevice> deviceList) {
        this.deviceList = deviceList;
        return this;
    }
}
