package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 추적 타겟 모드 열거형 클래스
 * 
 * 이 클래스는 드론의 타겟 추적 모드를 정의합니다.
 * 일반, 낮은 신뢰도, 예측 모드를 통해 타겟 추적의 정확도를 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public enum TrackTargetModeEnum {

    /**
     * 일반 추적 모드 (정수값: 1)
     */
    NORMAL(1),

    /**
     * 낮은 신뢰도 추적 모드 (정수값: 2)
     */
    LOW_CREDIBILITY(2),

    /**
     * 예측 추적 모드 (정수값: 3)
     */
    PREDICTED(3),
    ;
    /**
     * 추적 모드 정수값
     */
    private final int mode;

    /**
     * 추적 타겟 모드 열거형 생성자
     * 
     * @param mode 추적 모드 정수값
     */
    TrackTargetModeEnum(int mode) {
        this.mode = mode;
    }

    /**
     * 추적 모드 정수값을 반환합니다.
     * 
     * @return 추적 모드 정수값
     */
    @JsonValue
    public int getMode() {
        return mode;
    }

    /**
     * 정수값으로 추적 타겟 모드를 찾습니다.
     * 
     * @param mode 찾을 추적 모드 정수값
     * @return 찾은 추적 타겟 모드 열거형
     * @throws CloudSDKException 해당하는 추적 모드를 찾을 수 없는 경우
     */
    @JsonCreator
    public static TrackTargetModeEnum find(int mode) {
        return Arrays.stream(values()).filter(modeEnum -> modeEnum.mode == mode).findAny()
            .orElseThrow(() -> new CloudSDKException(TrackTargetModeEnum.class, mode));
    }

}
