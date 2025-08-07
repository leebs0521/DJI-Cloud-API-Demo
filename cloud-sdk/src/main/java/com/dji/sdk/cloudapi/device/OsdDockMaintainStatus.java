package com.dji.sdk.cloudapi.device;

import java.util.List;

/**
 * 도크 OSD 유지보수 상태 클래스
 * 
 * 이 클래스는 도크의 유지보수 상태 정보를 담습니다.
 * 도크 유지보수 상태 배열을 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public class OsdDockMaintainStatus {

    /**
     * 도크 유지보수 상태 배열
     */
    private List<DockMaintainStatus> maintainStatusArray;

    /**
     * 기본 생성자
     */
    public OsdDockMaintainStatus() {
    }

    @Override
    public String toString() {
        return "OsdDroneMaintainStatus{" +
                "maintainStatusArray=" + maintainStatusArray +
                '}';
    }

    /**
     * 도크 유지보수 상태 배열을 반환합니다.
     * 
     * @return 도크 유지보수 상태 배열
     */
    public List<DockMaintainStatus> getMaintainStatusArray() {
        return maintainStatusArray;
    }

    /**
     * 도크 유지보수 상태 배열을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param maintainStatusArray 설정할 도크 유지보수 상태 배열
     * @return 현재 OsdDockMaintainStatus 객체
     */
    public OsdDockMaintainStatus setMaintainStatusArray(List<DockMaintainStatus> maintainStatusArray) {
        this.maintainStatusArray = maintainStatusArray;
        return this;
    }
}
