package com.dji.sdk.cloudapi.livestream;

/**
 * RC 라이브스트림 비디오 용량 정보를 나타내는 클래스
 * RC에서 사용하는 비디오 스트림의 정보를 관리합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/18
 * @version 0.1
 */
public class RcLiveCapacityVideo {

    /** 비디오 인덱스 */
    private String videoIndex;

    /** 비디오 타입 */
    private VideoTypeEnum videoType;

    /**
     * 기본 생성자
     */
    public RcLiveCapacityVideo() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "RcLiveCapacityVideo{" +
                "videoIndex='" + videoIndex + '\'' +
                ", videoType=" + videoType +
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
    public RcLiveCapacityVideo setVideoIndex(String videoIndex) {
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
    public RcLiveCapacityVideo setVideoType(VideoTypeEnum videoType) {
        this.videoType = videoType;
        return this;
    }
}