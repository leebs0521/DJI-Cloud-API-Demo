package com.dji.sdk.cloudapi.livestream;

import java.util.List;

/**
 * RC 라이브스트림 장치 용량 정보를 나타내는 클래스
 * RC에서 사용하는 장치의 라이브스트림 기능과 제한사항을 관리합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/18
 * @version 0.1
 */
public class RcLiveCapacityDevice {

    /**
     * 장치 시리얼 번호
     */
    private String sn;

    /**
     * 라이브스트리밍에 사용 가능한 총 비디오 스트림 수
     * 장치에 속한 라이브스트리밍용 비디오 스트림의 총 개수
     */
    private Integer availableVideoNumber;

    /**
     * 동시에 라이브스트리밍할 수 있는 최대 비디오 스트림 수
     */
    private Integer coexistVideoNumberMax;

    /**
     * 장치의 카메라 목록
     */
    private List<RcLiveCapacityCamera> cameraList;

    /**
     * 기본 생성자
     */
    public RcLiveCapacityDevice() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "RcLiveCapacityDevice{" +
                "sn='" + sn + '\'' +
                ", availableVideoNumber=" + availableVideoNumber +
                ", coexistVideoNumberMax=" + coexistVideoNumberMax +
                ", cameraList=" + cameraList +
                '}';
    }

    /**
     * 장치 시리얼 번호를 반환합니다.
     * 
     * @return 장치 시리얼 번호
     */
    public String getSn() {
        return sn;
    }

    /**
     * 장치 시리얼 번호를 설정합니다.
     * 
     * @param sn 설정할 장치 시리얼 번호
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public RcLiveCapacityDevice setSn(String sn) {
        this.sn = sn;
        return this;
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
    public RcLiveCapacityDevice setAvailableVideoNumber(Integer availableVideoNumber) {
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
    public RcLiveCapacityDevice setCoexistVideoNumberMax(Integer coexistVideoNumberMax) {
        this.coexistVideoNumberMax = coexistVideoNumberMax;
        return this;
    }

    /**
     * 카메라 목록을 반환합니다.
     * 
     * @return 카메라 목록
     */
    public List<RcLiveCapacityCamera> getCameraList() {
        return cameraList;
    }

    /**
     * 카메라 목록을 설정합니다.
     * 
     * @param cameraList 설정할 카메라 목록
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public RcLiveCapacityDevice setCameraList(List<RcLiveCapacityCamera> cameraList) {
        this.cameraList = cameraList;
        return this;
    }
}