package com.dji.sdk.cloudapi.livestream;

import com.dji.sdk.cloudapi.device.VideoId;
import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 라이브스트림 렌즈 변경 요청을 나타내는 클래스
 * 라이브스트림 중 카메라 렌즈를 전환하기 위한 요청 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/23
 */
public class LiveLensChangeRequest extends BaseModel {

    /** 변경할 비디오 타입 */
    @NotNull
    private LensChangeVideoTypeEnum videoType;

    /**
     * 비디오 ID
     * 형식: #{uav_sn}/#{camera_id}/#{video_index}
     * 드론 시리얼 번호/페이로드 및 장착 위치 열거형 값/페이로드 렌즈 번호
     */
    @NotNull
    private VideoId videoId;

    /**
     * 기본 생성자
     */
    public LiveLensChangeRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "LiveLensChangeRequest{" +
                "videoType=" + videoType +
                ", videoId=" + videoId +
                '}';
    }

    /**
     * 변경할 비디오 타입을 반환합니다.
     * 
     * @return 변경할 비디오 타입
     */
    public LensChangeVideoTypeEnum getVideoType() {
        return videoType;
    }

    /**
     * 변경할 비디오 타입을 설정합니다.
     * 
     * @param videoType 설정할 비디오 타입
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LiveLensChangeRequest setVideoType(LensChangeVideoTypeEnum videoType) {
        this.videoType = videoType;
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
     * 비디오 ID를 설정합니다.
     * 
     * @param videoId 설정할 비디오 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LiveLensChangeRequest setVideoId(VideoId videoId) {
        this.videoId = videoId;
        return this;
    }
}
