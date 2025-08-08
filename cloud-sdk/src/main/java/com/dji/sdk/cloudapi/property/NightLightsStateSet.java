package com.dji.sdk.cloudapi.property;

import com.dji.sdk.cloudapi.device.SwitchActionEnum;
import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * 야간 조명 상태 설정 클래스
 * 
 * 이 클래스는 도크의 야간 조명 기능을 제어하기 위한 데이터를 정의합니다.
 * BaseModel을 상속받아 유효성 검사를 지원하며, 야간 작업 시
 * 안전과 가시성을 위한 조명을 관리합니다.
 * 
 * 주요 구성 요소:
 * - nightLightsState: 야간 조명 상태 열거형
 * 
 * 이 클래스는 도크의 야간 조명을 켜거나 끄는 데 사용됩니다.
 * 야간 작업 시 안전을 위해 조명을 활성화하거나, 스텔스 모드를 위해 비활성화할 수 있습니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/25
 */
public class NightLightsStateSet extends BaseModel {

    /**
     * 야간 조명 상태
     * 
     * 도크의 야간 조명 기능을 켜거나 끄는 상태를 설정합니다.
     * SwitchActionEnum을 사용하여 ON/OFF 상태를 제어할 수 있습니다.
     * 
     * 가능한 상태:
     * - ON: 야간 조명 활성화
     * - OFF: 야간 조명 비활성화
     * 
     * 야간 작업 시 안전을 위해 조명을 켜거나, 스텔스 모드를 위해 끌 수 있습니다.
     */
    @NotNull
    @JsonProperty("night_lights_state")
    private SwitchActionEnum nightLightsState;

    public NightLightsStateSet() {
    }

    @Override
    public String toString() {
        return "NightLightsStateSet{" +
                "nightLightsState=" + nightLightsState +
                '}';
    }

    /**
     * 야간 조명 상태를 반환합니다.
     * 
     * @return 야간 조명 상태 열거형
     */
    public SwitchActionEnum getNightLightsState() {
        return nightLightsState;
    }

    /**
     * 야간 조명 상태를 설정합니다.
     * 
     * @param nightLightsState 야간 조명 상태 열거형
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public NightLightsStateSet setNightLightsState(SwitchActionEnum nightLightsState) {
        this.nightLightsState = nightLightsState;
        return this;
    }
}
