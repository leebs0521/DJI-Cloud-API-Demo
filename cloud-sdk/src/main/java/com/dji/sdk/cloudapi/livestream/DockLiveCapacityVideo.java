package com.dji.sdk.cloudapi.livestream;

import java.util.List;

/**
 * Dock 라이브스트림 비디오 용량 정보를 나타내는 클래스
 * Dock에서 사용하는 비디오 스트림의 정보를 관리합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/18
 * @version 0.1
 */
public class DockLiveCapacityVideo {

    /** 비디오 인덱스 */
    private String videoIndex;

    /** 비디오 타입 */
    private VideoTypeEnum videoType;

    /** 전환 가능한 비디오 타입 목록 */
    private List<VideoTypeEnum> switchableVideoTypes;

    /**
     * 기본 생성자
     */
    public DockLiveCapacityVideo() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "DockLiveCapacityVideo{" +
                "videoIndex='" + videoIndex + '\'' +
                ", videoType=" + videoType +
                ", switchableVideoTypes=" + switchableVideoTypes +
                '}';
    }

    /**
     * 비디오 인덱스를 반환합니다.
     * 
     * @return 비디오 인덱스
     */
    public String getVideoIndex() {
        return videoIndex;
    }

    /**
     * 비디오 인덱스를 설정합니다.
     * 
     * @param videoIndex 설정할 비디오 인덱스
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DockLiveCapacityVideo setVideoIndex(String videoIndex) {
        this.videoIndex = videoIndex;
        return this;
    }

    /**
     * 비디오 타입을 반환합니다.
     * 
     * @return 비디오 타입
     */
    public VideoTypeEnum getVideoType() {
        return videoType;
    }

    /**
     * 비디오 타입을 설정합니다.
     * 
     * @param videoType 설정할 비디오 타입
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DockLiveCapacityVideo setVideoType(VideoTypeEnum videoType) {
        this.videoType = videoType;
        return this;
    }

    /**
     * 전환 가능한 비디오 타입 목록을 반환합니다.
     * 
     * @return 전환 가능한 비디오 타입 목록
     */
    public List<VideoTypeEnum> getSwitchableVideoTypes() {
        return switchableVideoTypes;
    }

    /**
     * 전환 가능한 비디오 타입 목록을 설정합니다.
     * 
     * @param switchableVideoTypes 설정할 전환 가능한 비디오 타입 목록
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DockLiveCapacityVideo setSwitchableVideoTypes(List<VideoTypeEnum> switchableVideoTypes) {
        this.switchableVideoTypes = switchableVideoTypes;
        return this;
    }
}