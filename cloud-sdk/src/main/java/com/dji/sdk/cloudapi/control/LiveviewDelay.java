package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.device.VideoId;

/**
 * 라이브뷰 지연 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 라이브뷰의 지연 정보를 담는 클래스입니다.
 * 라이브뷰 지연 시간과 비디오 ID를 포함하여 라이브뷰의 지연 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public class LiveviewDelay {

    /**
     * 라이브뷰 지연 시간
     * 라이브뷰의 지연 시간 (밀리초)
     */
    private Integer liveviewDelayTime;

    /**
     * 비디오 ID
     * 라이브뷰의 비디오 식별자
     */
    private VideoId videoId;

    /**
     * 기본 생성자
     */
    public LiveviewDelay() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "LiveviewDelay{" +
                "liveviewDelayTime=" + liveviewDelayTime +
                ", videoId=" + videoId +
                '}';
    }

    /**
     * 라이브뷰 지연 시간을 반환합니다.
     * 
     * @return 라이브뷰 지연 시간 (밀리초)
     */
    public Integer getLiveviewDelayTime() {
        return liveviewDelayTime;
    }

    /**
     * 라이브뷰 지연 시간을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param liveviewDelayTime 설정할 라이브뷰 지연 시간 (밀리초)
     * @return 현재 LiveviewDelay 객체
     */
    public LiveviewDelay setLiveviewDelayTime(Integer liveviewDelayTime) {
        this.liveviewDelayTime = liveviewDelayTime;
        return this;
    }
}
