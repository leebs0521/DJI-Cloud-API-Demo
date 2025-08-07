package com.dji.sdk.cloudapi.control;

import java.util.List;

/**
 * 지연 정보 푸시 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 지연 정보를 푸시하기 위한 클래스입니다.
 * SDR 명령 지연과 라이브뷰 지연 목록을 포함하여 지연 정보를 전송합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public class DelayInfoPush {

    /**
     * SDR 명령 지연
     * SDR 명령의 지연 시간
     */
    private Integer sdrCmdDelay;

    /**
     * 라이브뷰 지연 목록
     * 라이브뷰의 지연 정보 목록
     */
    private List<LiveviewDelay> liveviewDelayList;

    /**
     * 기본 생성자
     */
    public DelayInfoPush() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "DelayInfoPush{" +
                "sdrCmdDelay=" + sdrCmdDelay +
                ", liveviewDelayList=" + liveviewDelayList +
                '}';
    }

    /**
     * SDR 명령 지연을 반환합니다.
     * 
     * @return SDR 명령 지연
     */
    public Integer getSdrCmdDelay() {
        return sdrCmdDelay;
    }

    /**
     * SDR 명령 지연을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param sdrCmdDelay 설정할 SDR 명령 지연
     * @return 현재 DelayInfoPush 객체
     */
    public DelayInfoPush setSdrCmdDelay(Integer sdrCmdDelay) {
        this.sdrCmdDelay = sdrCmdDelay;
        return this;
    }

    /**
     * 라이브뷰 지연 목록을 반환합니다.
     * 
     * @return 라이브뷰 지연 목록
     */
    public List<LiveviewDelay> getLiveviewDelayList() {
        return liveviewDelayList;
    }

    /**
     * 라이브뷰 지연 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param liveviewDelayList 설정할 라이브뷰 지연 목록
     * @return 현재 DelayInfoPush 객체
     */
    public DelayInfoPush setLiveviewDelayList(List<LiveviewDelay> liveviewDelayList) {
        this.liveviewDelayList = liveviewDelayList;
        return this;
    }
}
