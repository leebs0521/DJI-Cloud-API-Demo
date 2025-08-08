package com.dji.sdk.cloudapi.property;

import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 도크 드론 커맨더 비행 고도 설정 클래스
 * 
 * 이 클래스는 도크에 연결된 드론의 커맨더 모드에서의 비행 고도를 설정하기 위한
 * 데이터를 정의합니다. BaseModel을 상속받아 유효성 검사를 지원하며,
 * 안전한 커맨더 비행을 위한 고도를 관리합니다.
 * 
 * 주요 구성 요소:
 * - commanderFlightHeight: 커맨더 비행 고도 (2-3000미터)
 * 
 * 이 클래스는 도크에 연결된 드론이 커맨더 모드로 비행할 때의 고도를 설정하는 데 사용됩니다.
 * 안전한 비행을 위해 적절한 고도를 설정할 수 있습니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public class DockDroneCommanderFlightHeight extends BaseModel {

    /**
     * 커맨더 비행 고도
     * 
     * 도크에 연결된 드론의 커맨더 모드에서의 비행 고도를 미터 단위로 설정합니다.
     * 최소 2미터, 최대 3000미터까지 설정 가능합니다.
     * 
     * 제한 사항:
     * - 최소값: 2미터 (안전한 이륙을 위한 최소 고도)
     * - 최대값: 3000미터 (법적 규정 및 안전 고도 제한)
     * 
     * 단위: 미터 (m)
     */
    @JsonProperty("commander_flight_height")
    @NotNull
    @Min(2)
    @Max(3000)
    private Float commanderFlightHeight;

    public DockDroneCommanderFlightHeight() {
    }

    @Override
    public String toString() {
        return "DockDroneCommanderFlightHeight{" +
                "commanderFlightHeight=" + commanderFlightHeight +
                '}';
    }

    /**
     * 커맨더 비행 고도를 반환합니다.
     * 
     * @return 커맨더 비행 고도 (미터)
     */
    public Float getCommanderFlightHeight() {
        return commanderFlightHeight;
    }

    /**
     * 커맨더 비행 고도를 설정합니다.
     * 
     * @param commanderFlightHeight 커맨더 비행 고도 (미터, 2-3000)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DockDroneCommanderFlightHeight setCommanderFlightHeight(Float commanderFlightHeight) {
        this.commanderFlightHeight = commanderFlightHeight;
        return this;
    }
}
