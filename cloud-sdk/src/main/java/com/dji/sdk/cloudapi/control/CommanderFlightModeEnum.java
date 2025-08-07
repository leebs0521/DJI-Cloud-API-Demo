package com.dji.sdk.cloudapi.control;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 커맨더 비행 모드 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 커맨더 모드에서의 비행 모드를 정의합니다.
 * 스마트 고도와 설정 고도 모드를 지원합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/8/7
 */
public enum CommanderFlightModeEnum {

    /**
     * 스마트 고도
     * 자동으로 고도를 조정하는 모드
     */
    SMART_HEIGHT(0),

    /**
     * 설정 고도
     * 사용자가 설정한 고도를 유지하는 모드
     */
    SETTING_HEIGHT(1);

    /**
     * 비행 모드 값
     */
    private final int mode;

    /**
     * 커맨더 비행 모드 열거형 생성자
     * 
     * @param mode 비행 모드 값
     */
    CommanderFlightModeEnum(int mode) {
        this.mode = mode;
    }

    /**
     * 비행 모드 값을 반환합니다.
     * 
     * @return 비행 모드 값
     */
    @JsonValue
    public int getMode() {
        return mode;
    }

    /**
     * 정수 값으로 커맨더 비행 모드를 찾습니다.
     * 
     * @param mode 찾을 비행 모드 값
     * @return 찾은 커맨더 비행 모드 열거형
     * @throws CloudSDKException 해당하는 비행 모드를 찾을 수 없는 경우
     */
    @JsonCreator
    public static CommanderFlightModeEnum find(int mode) {
        return Arrays.stream(values()).filter(modeEnum -> modeEnum.mode == mode).findAny()
            .orElseThrow(() -> new CloudSDKException(CommanderFlightModeEnum.class, mode));
    }

}
