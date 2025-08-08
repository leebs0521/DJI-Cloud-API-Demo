package com.dji.sdk.cloudapi.property;

import com.dji.sdk.cloudapi.device.RcLostActionEnum;
import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * RC 연결 끊김 동작 설정 클래스
 * 
 * 이 클래스는 RC(Remote Controller) 연결이 끊어졌을 때 드론의 동작을 설정하기 위한
 * 데이터를 정의합니다. BaseModel을 상속받아 유효성 검사를 지원하며,
 * 안전한 비상 상황 처리를 위한 동작을 관리합니다.
 * 
 * 주요 구성 요소:
 * - rcLostAction: RC 연결 끊김 시 동작 열거형
 * 
 * 이 클래스는 RC 연결이 끊어졌을 때 드론이 취해야 할 동작을 설정하는 데 사용됩니다.
 * 안전한 비상 상황 처리를 위해 적절한 동작을 선택할 수 있습니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/3
 */
public class RcLostActionSet extends BaseModel {

    /**
     * RC 연결 끊김 시 동작
     * 
     * RC(Remote Controller) 연결이 끊어졌을 때 드론이 취할 동작을 설정합니다.
     * RcLostActionEnum을 사용하여 다양한 동작 옵션 중 하나를 선택할 수 있습니다.
     * 
     * 가능한 동작:
     * - HOVER: 현재 위치에서 호버링
     * - RTH: 홈포인트로 자동 귀환
     * - LANDING: 현재 위치에서 자동 착륙
     * - CONTINUE: 현재 작업 계속 수행
     */
    @NotNull
    @JsonProperty("rc_lost_action")
    private RcLostActionEnum rcLostAction;

    public RcLostActionSet() {
    }

    @Override
    public String toString() {
        return "RcLostActionSet{" +
                "rcLostAction=" + rcLostAction +
                '}';
    }

    /**
     * RC 연결 끊김 시 동작을 반환합니다.
     * 
     * @return RC 연결 끊김 시 동작 열거형
     */
    public RcLostActionEnum getRcLostAction() {
        return rcLostAction;
    }

    /**
     * RC 연결 끊김 시 동작을 설정합니다.
     * 
     * @param rcLostAction RC 연결 끊김 시 동작 열거형
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public RcLostActionSet setRcLostAction(RcLostActionEnum rcLostAction) {
        this.rcLostAction = rcLostAction;
        return this;
    }
}
