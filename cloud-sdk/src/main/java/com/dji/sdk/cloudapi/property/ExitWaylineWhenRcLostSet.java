package com.dji.sdk.cloudapi.property;

import com.dji.sdk.cloudapi.device.ExitWaylineWhenRcLostEnum;
import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * RC 연결 끊김 시 웨이라인 종료 설정 클래스
 * 
 * 이 클래스는 RC(Remote Controller) 연결이 끊어졌을 때 웨이라인 비행을
 * 종료할지 여부를 설정하기 위한 데이터를 정의합니다.
 * BaseModel을 상속받아 유효성 검사를 지원하며, 안전한 비상 상황 처리를 위한
 * 웨이라인 종료 동작을 관리합니다.
 * 
 * 주요 구성 요소:
 * - exitWaylineWhenRcLost: RC 연결 끊김 시 웨이라인 종료 열거형
 * 
 * 이 클래스는 RC 연결이 끊어졌을 때 웨이라인 비행을 즉시 종료할지
 * 아니면 계속 진행할지를 설정하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/3
 */
public class ExitWaylineWhenRcLostSet extends BaseModel {

    /**
     * RC 연결 끊김 시 웨이라인 종료 설정
     * 
     * RC(Remote Controller) 연결이 끊어졌을 때 웨이라인 비행을 종료할지 여부를 설정합니다.
     * ExitWaylineWhenRcLostEnum을 사용하여 다양한 옵션 중 하나를 선택할 수 있습니다.
     * 
     * 가능한 설정:
     * - EXIT: RC 연결 끊김 시 웨이라인 비행 즉시 종료
     * - CONTINUE: RC 연결 끊김 시에도 웨이라인 비행 계속 진행
     * 
     * 안전을 위해 RC 연결이 끊어지면 웨이라인을 종료하거나,
     * 자동화된 비행을 위해 계속 진행할 수 있습니다.
     */
    @NotNull
    @JsonProperty("exit_wayline_when_rc_lost")
    private ExitWaylineWhenRcLostEnum exitWaylineWhenRcLost;

    public ExitWaylineWhenRcLostSet() {
    }

    @Override
    public String toString() {
        return "ExitWaylineWhenRcLostSet{" +
                "exitWaylineWhenRcLost=" + exitWaylineWhenRcLost +
                '}';
    }

    /**
     * RC 연결 끊김 시 웨이라인 종료 설정을 반환합니다.
     * 
     * @return RC 연결 끊김 시 웨이라인 종료 열거형
     */
    public ExitWaylineWhenRcLostEnum getExitWaylineWhenRcLost() {
        return exitWaylineWhenRcLost;
    }

    /**
     * RC 연결 끊김 시 웨이라인 종료 설정을 설정합니다.
     * 
     * @param exitWaylineWhenRcLost RC 연결 끊김 시 웨이라인 종료 열거형
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ExitWaylineWhenRcLostSet setExitWaylineWhenRcLost(ExitWaylineWhenRcLostEnum exitWaylineWhenRcLost) {
        this.exitWaylineWhenRcLost = exitWaylineWhenRcLost;
        return this;
    }
}
