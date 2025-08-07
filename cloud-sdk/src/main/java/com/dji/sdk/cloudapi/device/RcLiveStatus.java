package com.dji.sdk.cloudapi.device;

import java.util.List;

/**
 * RC 라이브 상태 클래스
 * 
 * 이 클래스는 RC의 라이브스트림 상태 정보를 관리합니다.
 * 여러 라이브스트림의 상태 데이터를 포함하여 RC의 라이브스트림 기능을 모니터링합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/23
 */
public class RcLiveStatus {

    /**
     * 라이브 상태 데이터 목록
     */
    private List<RcLiveStatusData> liveStatus;

    /**
     * 기본 생성자
     */
    public RcLiveStatus() {
    }

    @Override
    public String toString() {
        return "RcLiveStatus{" +
                "liveStatus=" + liveStatus +
                '}';
    }

    /**
     * 라이브 상태 데이터 목록을 반환합니다.
     * 
     * @return 라이브 상태 데이터 목록
     */
    public List<RcLiveStatusData> getLiveStatus() {
        return liveStatus;
    }

    /**
     * 라이브 상태 데이터 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param liveStatus 설정할 라이브 상태 데이터 목록
     * @return 현재 RcLiveStatus 객체
     */
    public RcLiveStatus setLiveStatus(List<RcLiveStatusData> liveStatus) {
        this.liveStatus = liveStatus;
        return this;
    }
}