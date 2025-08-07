package com.dji.sdk.cloudapi.device;

import com.dji.sdk.cloudapi.livestream.VideoQualityEnum;

/**
 * RC 라이브 상태 데이터 클래스
 * 
 * 이 클래스는 RC의 개별 라이브스트림 상태 데이터를 관리합니다.
 * 라이브스트림의 활성화 상태, 비디오 ID, 비디오 품질 등을 포함합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/23
 */
public class RcLiveStatusData {

    /**
     * 라이브스트림 활성화 상태
     */
    private Boolean status;

    /**
     * 비디오 ID
     */
    private VideoId videoId;

    /**
     * 비디오 품질 열거형
     */
    private VideoQualityEnum videoQuality;

    /**
     * 기본 생성자
     */
    public RcLiveStatusData() {
    }

    @Override
    public String toString() {
        return "RcLiveStatusData{" +
                "status=" + status +
                ", videoId=" + videoId +
                ", videoQuality=" + videoQuality +
                '}';
    }

    /**
     * 라이브스트림 활성화 상태를 반환합니다.
     * 
     * @return 라이브스트림 활성화 상태
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 라이브스트림 활성화 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param status 설정할 라이브스트림 활성화 상태
     * @return 현재 RcLiveStatusData 객체
     */
    public RcLiveStatusData setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    /**
     * 비디오 ID를 반환합니다.
     * 
     * @return 비디오 ID
     */
    public VideoId getVideoId() {
        return videoId;
    }

    /**
     * 비디오 ID를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param videoId 설정할 비디오 ID
     * @return 현재 RcLiveStatusData 객체
     */
    public RcLiveStatusData setVideoId(VideoId videoId) {
        this.videoId = videoId;
        return this;
    }

    /**
     * 비디오 품질을 반환합니다.
     * 
     * @return 비디오 품질 열거형
     */
    public VideoQualityEnum getVideoQuality() {
        return videoQuality;
    }

    /**
     * 비디오 품질을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param videoQuality 설정할 비디오 품질
     * @return 현재 RcLiveStatusData 객체
     */
    public RcLiveStatusData setVideoQuality(VideoQualityEnum videoQuality) {
        this.videoQuality = videoQuality;
        return this;
    }
}