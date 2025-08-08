package com.dji.sdk.cloudapi.livestream;

import com.dji.sdk.cloudapi.device.VideoId;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 라이브스트림 품질 설정 요청을 나타내는 클래스
 * 라이브스트림의 비디오 품질을 변경하기 위한 요청 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/23
 */
public class LiveSetQualityRequest extends BaseModel {

    /**
     * 비디오 ID
     * 형식: #{uav_sn}/#{camera_id}/#{video_index}
     * 드론 시리얼 번호/페이로드 및 장착 위치 열거형 값/페이로드 렌즈 번호
     */
    @NotNull
    private VideoId videoId;

    /** 설정할 비디오 품질 */
    @NotNull
    private VideoQualityEnum videoQuality;

    /**
     * 기본 생성자
     */
    public LiveSetQualityRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "LiveSetQualityRequest{" +
                "videoId=" + videoId +
                ", videoQuality=" + videoQuality +
                '}';
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
     * 비디오 ID를 설정합니다.
     * 
     * @param videoId 설정할 비디오 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LiveSetQualityRequest setVideoId(VideoId videoId) {
        this.videoId = videoId;
        return this;
    }

    /**
     * 설정할 비디오 품질을 반환합니다.
     * 
     * @return 설정할 비디오 품질
     */
    public VideoQualityEnum getVideoQuality() {
        return videoQuality;
    }

    /**
     * 설정할 비디오 품질을 설정합니다.
     * 
     * @param videoQuality 설정할 비디오 품질
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LiveSetQualityRequest setVideoQuality(VideoQualityEnum videoQuality) {
        this.videoQuality = videoQuality;
        return this;
    }
}
