package com.dji.sdk.cloudapi.property;

import com.dji.sdk.cloudapi.control.CommanderModeLostActionEnum;
import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * 도크 드론 커맨더 모드 연결 끊김 동작 설정 클래스
 * 
 * 이 클래스는 도크에 연결된 드론의 커맨더 모드에서 연결이 끊어졌을 때의 동작을
 * 설정하기 위한 데이터를 정의합니다. BaseModel을 상속받아 유효성 검사를 지원하며,
 * 안전한 비상 상황 처리를 위한 동작을 관리합니다.
 * 
 * 주요 구성 요소:
 * - commanderModeLostAction: 커맨더 모드 연결 끊김 동작 열거형
 * 
 * 이 클래스는 커맨더 모드에서 연결이 끊어졌을 때 드론이 취해야 할 동작을 설정하는 데 사용됩니다.
 * 안전한 비상 상황 처리를 위해 적절한 동작을 선택할 수 있습니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/19
 */
public class DockDroneCommanderModeLostAction extends BaseModel {

    /**
     * 커맨더 모드 연결 끊김 동작
     * 
     * 도크에 연결된 드론의 커맨더 모드에서 연결이 끊어졌을 때 드론이 취할 동작을 설정합니다.
     * CommanderModeLostActionEnum을 사용하여 다양한 동작 옵션 중 하나를 선택할 수 있습니다.
     * 
     * 가능한 동작:
     * - HOVER: 현재 위치에서 호버링
     * - RTH: 홈포인트로 자동 귀환
     * - LANDING: 현재 위치에서 자동 착륙
     * - CONTINUE: 현재 작업 계속 수행
     */
    @JsonProperty("commander_mode_lost_action")
    @NotNull
    private CommanderModeLostActionEnum commanderModeLostAction;

    public DockDroneCommanderModeLostAction() {
    }

    @Override
    public String toString() {
        return "DockDroneCommanderModeLostAction{" +
                "commanderModeLostAction=" + commanderModeLostAction +
                '}';
    }

    /**
     * 커맨더 모드 연결 끊김 동작을 반환합니다.
     * 
     * @return 커맨더 모드 연결 끊김 동작 열거형
     */
    public CommanderModeLostActionEnum getCommanderModeLostAction() {
        return commanderModeLostAction;
    }

    /**
     * 커맨더 모드 연결 끊김 동작을 설정합니다.
     * 
     * @param commanderModeLostAction 커맨더 모드 연결 끊김 동작 열거형
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DockDroneCommanderModeLostAction setCommanderModeLostAction(CommanderModeLostActionEnum commanderModeLostAction) {
        this.commanderModeLostAction = commanderModeLostAction;
        return this;
    }
}
