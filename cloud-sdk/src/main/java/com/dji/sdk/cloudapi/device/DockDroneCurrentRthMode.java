package com.dji.sdk.cloudapi.device;

import com.dji.sdk.cloudapi.wayline.RthModeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * 도크 드론 현재 RTH 모드 클래스
 * 
 * 이 클래스는 도크에 연결된 드론의 현재 RTH(Return to Home) 모드 정보를 담습니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/11
 */
public class DockDroneCurrentRthMode {

    /**
     * 현재 RTH 고도 모드
     */
    @JsonProperty("current_rth_mode")
    @NotNull
    private RthModeEnum currentRthMode;

    /**
     * 기본 생성자
     */
    public DockDroneCurrentRthMode() {
    }

    @Override
    public String toString() {
        return "DockDroneCurrentRthMode{" +
                "currentRthMode=" + currentRthMode +
                '}';
    }

    /**
     * 현재 RTH 모드를 반환합니다.
     * 
     * @return 현재 RTH 모드
     */
    public RthModeEnum getCurrentRthMode() {
        return currentRthMode;
    }

    /**
     * 현재 RTH 모드를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param currentRthMode 설정할 현재 RTH 모드
     * @return 현재 DockDroneCurrentRthMode 객체
     */
    public DockDroneCurrentRthMode setCurrentRthMode(RthModeEnum currentRthMode) {
        this.currentRthMode = currentRthMode;
        return this;
    }
}
