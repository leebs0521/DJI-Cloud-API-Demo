package com.dji.sdk.cloudapi.device;

import com.dji.sdk.cloudapi.property.SilentModeEnum;
import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * 도크 무음 모드 클래스
 * 
 * 이 클래스는 도크의 무음 모드 설정을 담습니다.
 * 무음 모드 상태를 포함합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public class DockSilentMode extends BaseModel {

    /**
     * 무음 모드 상태
     */
    @NotNull
    @JsonProperty("silent_mode")
    private SilentModeEnum silentMode;

    /**
     * 기본 생성자
     */
    public DockSilentMode() {
    }

    @Override
    public String toString() {
        return "DockSilentMode{" +
                "silentMode=" + silentMode +
                '}';
    }

    /**
     * 무음 모드 상태를 반환합니다.
     * 
     * @return 무음 모드 상태
     */
    public SilentModeEnum getSilentMode() {
        return silentMode;
    }

    /**
     * 무음 모드 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param silentMode 설정할 무음 모드 상태
     * @return 현재 DockSilentMode 객체
     */
    public DockSilentMode setSilentMode(SilentModeEnum silentMode) {
        this.silentMode = silentMode;
        return this;
    }
}
