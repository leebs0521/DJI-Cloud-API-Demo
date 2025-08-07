package com.dji.sdk.cloudapi.device;

import com.dji.sdk.cloudapi.control.CommanderFlightModeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 도크 드론 현재 커맨더 비행 모드 클래스
 * 
 * 이 클래스는 도크에 연결된 드론의 현재 커맨더 비행 모드 정보를 담습니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public class DockDroneCurrentCommanderFlightMode {

    /**
     * 현재 커맨더 비행 모드
     */
    @JsonProperty("current_commander_flight_mode")
    private CommanderFlightModeEnum currentCommanderFlightMode;

    /**
     * 기본 생성자
     */
    public DockDroneCurrentCommanderFlightMode() {
    }

    @Override
    public String toString() {
        return "DockDroneCurrentCommanderFlightMode{" +
                "currentCommanderFlightMode=" + currentCommanderFlightMode +
                '}';
    }

    /**
     * 현재 커맨더 비행 모드를 반환합니다.
     * 
     * @return 현재 커맨더 비행 모드
     */
    public CommanderFlightModeEnum getCurrentCommanderFlightMode() {
        return currentCommanderFlightMode;
    }

    /**
     * 현재 커맨더 비행 모드를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param currentCommanderFlightMode 설정할 현재 커맨더 비행 모드
     * @return 현재 DockDroneCurrentCommanderFlightMode 객체
     */
    public DockDroneCurrentCommanderFlightMode setCurrentCommanderFlightMode(CommanderFlightModeEnum currentCommanderFlightMode) {
        this.currentCommanderFlightMode = currentCommanderFlightMode;
        return this;
    }
}
