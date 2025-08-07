package com.dji.sdk.cloudapi.device;

import java.util.List;

/**
 * 드론 OSD 유지보수 상태 클래스
 * 
 * 이 클래스는 드론의 유지보수 상태 정보를 담습니다.
 * 드론 유지보수 상태 배열을 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public class OsdDroneMaintainStatus {

    /**
     * 드론 유지보수 상태 배열
     */
    private List<DroneMaintainStatus> maintainStatusArray;

    /**
     * 기본 생성자
     */
    public OsdDroneMaintainStatus() {
    }

    @Override
    public String toString() {
        return "OsdDroneMaintainStatus{" +
                "maintainStatusArray=" + maintainStatusArray +
                '}';
    }

    /**
     * 드론 유지보수 상태 배열을 반환합니다.
     * 
     * @return 드론 유지보수 상태 배열
     */
    public List<DroneMaintainStatus> getMaintainStatusArray() {
        return maintainStatusArray;
    }

    /**
     * 드론 유지보수 상태 배열을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param maintainStatusArray 설정할 드론 유지보수 상태 배열
     * @return 현재 OsdDroneMaintainStatus 객체
     */
    public OsdDroneMaintainStatus setMaintainStatusArray(List<DroneMaintainStatus> maintainStatusArray) {
        this.maintainStatusArray = maintainStatusArray;
        return this;
    }
}
