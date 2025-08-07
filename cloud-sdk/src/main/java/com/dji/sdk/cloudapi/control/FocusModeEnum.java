package com.dji.sdk.cloudapi.control;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 초점 모드 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 카메라의 초점 모드를 정의합니다.
 * 수동 초점, 단일 자동 초점, 연속 자동 초점을 지원합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public enum FocusModeEnum {

    /**
     * 수동 초점 (Manual Focus)
     * 사용자가 수동으로 초점을 조정
     */
    MF(0),

    /**
     * 단일 자동 초점 (Auto Focus Single)
     * 한 번의 자동 초점 조정 후 고정
     */
    AFS(1),

    /**
     * 연속 자동 초점 (Auto Focus Continuous)
     * 지속적으로 자동 초점을 조정
     */
    AFC(2),

    ;
    /**
     * 초점 모드 값
     */
    private final int mode;

    /**
     * 초점 모드 열거형 생성자
     * 
     * @param mode 초점 모드 값
     */
    FocusModeEnum(int mode) {
        this.mode = mode;
    }

    /**
     * 초점 모드 값을 반환합니다.
     * 
     * @return 초점 모드 값
     */
    @JsonValue
    public int getMode() {
        return mode;
    }

    /**
     * 정수 값으로 초점 모드를 찾습니다.
     * 
     * @param mode 찾을 초점 모드 값
     * @return 찾은 초점 모드 열거형
     * @throws CloudSDKException 해당하는 초점 모드를 찾을 수 없는 경우
     */
    @JsonCreator
    public static FocusModeEnum find(int mode) {
        return Arrays.stream(values()).filter(modeEnum -> modeEnum.mode == mode).findAny()
            .orElseThrow(() -> new CloudSDKException(FocusModeEnum.class, mode));
    }

}
