package com.dji.sdk.cloudapi.property;

import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * 도크 드론 오프라인 맵 활성화 설정 클래스
 * 
 * 이 클래스는 도크에 연결된 드론의 오프라인 맵 기능 활성화 여부를 설정하기 위한
 * 데이터를 정의합니다. BaseModel을 상속받아 유효성 검사를 지원하며,
 * 오프라인 환경에서의 맵 기능을 관리합니다.
 * 
 * 주요 구성 요소:
 * - offlineMapEnable: 오프라인 맵 활성화 여부 (Boolean)
 * 
 * 이 클래스는 도크에 연결된 드론의 오프라인 맵 기능을 활성화하거나 비활성화하는 데 사용됩니다.
 * 네트워크 연결이 불안정한 환경에서도 맵 기능을 사용할 수 있도록 설정할 수 있습니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public class DockDroneOfflineMapEnable extends BaseModel {

    /**
     * 오프라인 맵 활성화 여부
     * 
     * 도크에 연결된 드론의 오프라인 맵 기능을 활성화하거나 비활성화하는 설정입니다.
     * 네트워크 연결이 불안정한 환경에서도 맵 기능을 사용할 수 있도록 제어합니다.
     * 
     * 가능한 설정:
     * - true: 오프라인 맵 기능 활성화
     * - false: 오프라인 맵 기능 비활성화
     * 
     * 오프라인 맵이 활성화되면 네트워크 연결 없이도 기본적인 맵 기능을 사용할 수 있습니다.
     */
    @JsonProperty("offline_map_enable")
    @NotNull
    private Boolean offlineMapEnable;

    public DockDroneOfflineMapEnable() {
    }

    @Override
    public String toString() {
        return "DockDroneOfflineMapEnable{" +
                "offlineMapEnable=" + offlineMapEnable +
                '}';
    }

    /**
     * 오프라인 맵 활성화 여부를 반환합니다.
     * 
     * @return 오프라인 맵 활성화 여부
     */
    public Boolean getOfflineMapEnable() {
        return offlineMapEnable;
    }

    /**
     * 오프라인 맵 활성화 여부를 설정합니다.
     * 
     * @param offlineMapEnable 오프라인 맵 활성화 여부
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DockDroneOfflineMapEnable setOfflineMapEnable(Boolean offlineMapEnable) {
        this.offlineMapEnable = offlineMapEnable;
        return this;
    }
}
