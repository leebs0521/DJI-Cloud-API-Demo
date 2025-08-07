package com.dji.sdk.cloudapi.device;

import com.dji.sdk.cloudapi.livestream.VideoQualityEnum;
import com.dji.sdk.cloudapi.livestream.VideoTypeEnum;

/**
 * 도크 라이브 상태 데이터 클래스
 * 
 * 이 클래스는 도크의 라이브 스트림 상태 정보를 담습니다.
 * 라이브 상태, 비디오 ID, 비디오 품질, 비디오 타입, 오류 상태 등을 포함합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/23
 */
public class DockLiveStatusData {

    /**
     * 라이브 스트림 상태 (true: 활성화, false: 비활성화)
     */
    private Boolean status;

    /**
     * 비디오 ID
     */
    private VideoId videoId;

    /**
     * 비디오 품질
     */
    private VideoQualityEnum videoQuality;

    /**
     * 비디오 타입
     */
    private VideoTypeEnum videoType;

    /**
     * 도크 라이브 오류 상태
     */
    private DockLiveErrorStatus errorStatus;

    /**
     * 기본 생성자
     */
    public DockLiveStatusData() {
    }

    @Override
    public String toString() {
        return "DockLiveStatusData{" +
                "status=" + status +
                ", videoId=" + videoId +
                ", videoQuality=" + videoQuality +
                ", videoType=" + videoType +
                ", errorStatus=" + errorStatus +
                '}';
    }

    /**
     * 라이브 스트림 상태를 반환합니다.
     * 
     * @return 라이브 스트림 상태 (true: 활성화, false: 비활성화)
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 라이브 스트림 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param status 설정할 라이브 스트림 상태
     * @return 현재 DockLiveStatusData 객체
     */
    public DockLiveStatusData setStatus(Boolean status) {
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
     * @return 현재 DockLiveStatusData 객체
     */
    public DockLiveStatusData setVideoId(VideoId videoId) {
        this.videoId = videoId;
        return this;
    }

    /**
     * 비디오 품질을 반환합니다.
     * 
     * @return 비디오 품질
     */
    public VideoQualityEnum getVideoQuality() {
        return videoQuality;
    }

    /**
     * 비디오 품질을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param videoQuality 설정할 비디오 품질
     * @return 현재 DockLiveStatusData 객체
     */
    public DockLiveStatusData setVideoQuality(VideoQualityEnum videoQuality) {
        this.videoQuality = videoQuality;
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
     * 비디오 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param videoType 설정할 비디오 타입
     * @return 현재 DockLiveStatusData 객체
     */
    public DockLiveStatusData setVideoType(VideoTypeEnum videoType) {
        this.videoType = videoType;
        return this;
    }

    /**
     * 도크 라이브 오류 상태를 반환합니다.
     * 
     * @return 도크 라이브 오류 상태
     */
    public DockLiveErrorStatus getErrorStatus() {
        return errorStatus;
    }

    /**
     * 도크 라이브 오류 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param errorStatus 설정할 도크 라이브 오류 상태
     * @return 현재 DockLiveStatusData 객체
     */
    public DockLiveStatusData setErrorStatus(DockLiveErrorStatus errorStatus) {
        this.errorStatus = errorStatus;
        return this;
    }
}