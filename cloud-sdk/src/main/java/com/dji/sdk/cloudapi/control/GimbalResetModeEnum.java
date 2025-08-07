package com.dji.sdk.cloudapi.control;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 짐벌 리셋 모드 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 짐벌 리셋의 모드를 정의합니다.
 * 중앙 정렬, 아래쪽, 중앙 정렬 팬, 피치 다운 등의 모드를 지원합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/13
 */
public enum GimbalResetModeEnum {

    /**
     * 중앙 정렬
     * 짐벌을 중앙 위치로 리셋
     */
    RECENTER(0),

    /**
     * 아래쪽
     * 짐벌을 아래쪽으로 리셋
     */
    DOWN(1),

    /**
     * 중앙 정렬 팬
     * 짐벌을 중앙 위치로 리셋하고 팬 동작 포함
     */
    RECENTER_PAN(2),

    /**
     * 피치 다운
     * 짐벌을 피치 다운 위치로 리셋
     */
    PITCH_DOWN(3);

    /**
     * 리셋 모드 값
     */
    private final int mode;

    /**
     * 짐벌 리셋 모드 열거형 생성자
     * 
     * @param mode 리셋 모드 값
     */
    GimbalResetModeEnum(int mode) {
        this.mode = mode;
    }

    /**
     * 리셋 모드 값을 반환합니다.
     * 
     * @return 리셋 모드 값
     */
    @JsonValue
    public int getMode() {
        return mode;
    }

    /**
     * 정수 값으로 짐벌 리셋 모드를 찾습니다.
     * 
     * @param mode 찾을 리셋 모드 값
     * @return 찾은 짐벌 리셋 모드 열거형
     * @throws CloudSDKException 해당하는 리셋 모드를 찾을 수 없는 경우
     */
    @JsonCreator
    public static GimbalResetModeEnum find(int mode) {
        return Arrays.stream(values()).filter(resetModeEnum -> resetModeEnum.ordinal() == mode).findAny()
                .orElseThrow(() -> new CloudSDKException(GimbalResetModeEnum.class, mode));
    }
}
