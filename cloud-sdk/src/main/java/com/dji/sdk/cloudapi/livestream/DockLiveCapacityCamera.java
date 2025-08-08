package com.dji.sdk.cloudapi.livestream;

import com.dji.sdk.cloudapi.device.PayloadIndex;

import java.util.List;

/**
 * Dock 라이브스트림 카메라 용량 정보를 나타내는 클래스
 * Dock에서 사용하는 카메라의 라이브스트림 기능과 제한사항을 관리합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/18
 * @version 0.1
 */
public class DockLiveCapacityCamera {

    /**
     * 라이브스트리밍에 사용 가능한 총 비디오 스트림 수
     * 카메라가 라이브스트리밍할 수 있는 총 비디오 스트림 수
     */
    private Integer availableVideoNumber;

    /**
     * 카메라가 동시에 라이브스트리밍할 수 있는 최대 비디오 스트림 수
     */
    private Integer coexistVideoNumberMax;

    /**
     * 카메라 인덱스, 제품 타입 열거형과 짐벌 인덱스로 구성됨
     */
    private PayloadIndex cameraIndex;

    /** 비디오 목록 */
    private List<DockLiveCapacityVideo> videoList;

    /**
     * 기본 생성자
     */
    public DockLiveCapacityCamera() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "DockLiveCapacityCamera{" +
                "availableVideoNumber=" + availableVideoNumber +
                ", coexistVideoNumberMax=" + coexistVideoNumberMax +
                ", cameraIndex=" + cameraIndex +
                ", videoList=" + videoList +
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
    public DockLiveCapacityCamera setAvailableVideoNumber(Integer availableVideoNumber) {
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
    public DockLiveCapacityCamera setCoexistVideoNumberMax(Integer coexistVideoNumberMax) {
        this.coexistVideoNumberMax = coexistVideoNumberMax;
        return this;
    }

    /**
     * 카메라 인덱스를 반환합니다.
     * 
     * @return 카메라 인덱스
     */
    public PayloadIndex getCameraIndex() {
        return cameraIndex;
    }

    /**
     * 카메라 인덱스를 설정합니다.
     * 
     * @param cameraIndex 설정할 카메라 인덱스
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DockLiveCapacityCamera setCameraIndex(PayloadIndex cameraIndex) {
        this.cameraIndex = cameraIndex;
        return this;
    }

    /**
     * 비디오 목록을 반환합니다.
     * 
     * @return 비디오 목록
     */
    public List<DockLiveCapacityVideo> getVideoList() {
        return videoList;
    }

    /**
     * 비디오 목록을 설정합니다.
     * 
     * @param videoList 설정할 비디오 목록
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DockLiveCapacityCamera setVideoList(List<DockLiveCapacityVideo> videoList) {
        this.videoList = videoList;
        return this;
    }
}