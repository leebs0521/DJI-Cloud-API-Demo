package com.dji.sdk.cloudapi.property;

import com.dji.sdk.cloudapi.wayline.RthModeEnum;
import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * 도크 드론 RTH 모드 설정 클래스
 * 
 * 이 클래스는 도크에 연결된 드론의 Return-to-Home(RTH) 모드를 설정하기 위한
 * 데이터를 정의합니다. BaseModel을 상속받아 유효성 검사를 지원하며,
 * 안전한 귀환을 위한 RTH 모드를 관리합니다.
 * 
 * 주요 구성 요소:
 * - rthMode: RTH 모드 열거형
 * 
 * 이 클래스는 도크에 연결된 드론이 자동으로 홈포인트로 귀환할 때의
 * 모드를 설정하는 데 사용됩니다. 다양한 귀환 옵션 중 하나를 선택할 수 있습니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/11
 */
public class DockDroneRthMode extends BaseModel {

    /**
     * RTH 모드
     * 
     * 도크에 연결된 드론의 Return-to-Home(RTH) 모드를 설정합니다.
     * RthModeEnum을 사용하여 다양한 귀환 모드 중 하나를 선택할 수 있습니다.
     * 
     * 가능한 모드:
     * - SMART_RTH: 스마트 귀환 (장애물 회피 포함)
     * - DIRECT_RTH: 직선 귀환 (최단 경로)
     * - HOVER: 호버링 (현재 위치 유지)
     * - LANDING: 착륙 (현재 위치에서 착륙)
     */
    @JsonProperty("rth_mode")
    @NotNull
    private RthModeEnum rthMode;

    public DockDroneRthMode() {
    }

    @Override
    public String toString() {
        return "DockDroneRthMode{" +
                "rthMode=" + rthMode +
                '}';
    }

    /**
     * RTH 모드를 반환합니다.
     * 
     * @return RTH 모드 열거형
     */
    public RthModeEnum getRthMode() {
        return rthMode;
    }

    /**
     * RTH 모드를 설정합니다.
     * 
     * @param rthMode RTH 모드 열거형
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DockDroneRthMode setRthMode(RthModeEnum rthMode) {
        this.rthMode = rthMode;
        return this;
    }
}
