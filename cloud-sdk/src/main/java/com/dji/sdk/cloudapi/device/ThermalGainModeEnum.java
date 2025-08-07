package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 열화상 게인 모드 열거형 클래스
 * 
 * 이 클래스는 열화상 카메라의 게인 모드를 정의합니다.
 * 자동, 낮음, 높음 모드를 통해 열화상 카메라의 감도를 조절합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public enum ThermalGainModeEnum {

    /**
     * 자동 게인 모드 (정수값: 0)
     */
    AUTOMATIC(0),

    /**
     * 낮은 게인 모드 (정수값: 1)
     */
    LOW(1),

    /**
     * 높은 게인 모드 (정수값: 2)
     */
    HIGH(2),
    ;

    /**
     * 게인 모드 정수값
     */
    private final int mode;

    /**
     * 열화상 게인 모드 열거형 생성자
     * 
     * @param mode 게인 모드 정수값
     */
    ThermalGainModeEnum(int mode) {
        this.mode = mode;
    }

    /**
     * 게인 모드 정수값을 반환합니다.
     * 
     * @return 게인 모드 정수값
     */
    @JsonValue
    public int getMode() {
        return mode;
    }

    /**
     * 정수값으로 열화상 게인 모드를 찾습니다.
     * 
     * @param mode 찾을 게인 모드 정수값
     * @return 찾은 열화상 게인 모드 열거형
     * @throws CloudSDKException 해당하는 게인 모드를 찾을 수 없는 경우
     */
    @JsonCreator
    public static ThermalGainModeEnum find(int mode) {
        return Arrays.stream(values()).filter(modeEnum -> modeEnum.mode == mode).findAny()
            .orElseThrow(() -> new CloudSDKException(ThermalGainModeEnum.class, mode));
    }

}
