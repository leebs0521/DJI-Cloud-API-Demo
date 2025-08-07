package com.dji.sdk.cloudapi.device;

import java.util.List;

/**
 * 도크 라이브 상태 클래스
 * 
 * 이 클래스는 도크의 라이브 상태 정보를 담습니다.
 * 도크 라이브 상태 데이터 목록을 포함합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/23
 */
public class DockLiveStatus {

    /**
     * 도크 라이브 상태 데이터 목록
     */
    private List<DockLiveStatusData> liveStatus;

    /**
     * 기본 생성자
     */
    public DockLiveStatus() {
    }

    @Override
    public String toString() {
        return "DockLiveStatus{" +
                "liveStatus=" + liveStatus +
                '}';
    }

    /**
     * 도크 라이브 상태 데이터 목록을 반환합니다.
     * 
     * @return 도크 라이브 상태 데이터 목록
     */
    public List<DockLiveStatusData> getLiveStatus() {
        return liveStatus;
    }

    /**
     * 도크 라이브 상태 데이터 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param liveStatus 설정할 도크 라이브 상태 데이터 목록
     * @return 현재 DockLiveStatus 객체
     */
    public DockLiveStatus setLiveStatus(List<DockLiveStatusData> liveStatus) {
        this.liveStatus = liveStatus;
        return this;
    }
}