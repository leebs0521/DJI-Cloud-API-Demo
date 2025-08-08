package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 홈포인트 귀환 모드 열거형
 * 
 * 이 열거형은 드론의 홈포인트 귀환(RTH) 모드를 정의합니다.
 * 최적 고도와 사전 설정 고도 두 가지 모드를 제공하여
 * 안전하고 효율적인 귀환을 지원합니다.
 * 
 * 주요 구성 요소:
 * - OPTIMAL_HEIGHT: 최적 고도 모드
 * - PRESET_HEIGHT: 사전 설정 고도 모드
 * 
 * 이 열거형은 드론이 홈포인트로 귀환할 때의
 * 고도 설정 방식을 결정하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/8/4
 */
public enum RthModeEnum {

    /**
     * 최적 고도
     * 
     * 시스템이 자동으로 계산한 최적 고도로 귀환합니다.
     * 장애물과 지형을 고려하여 안전한 경로를 선택합니다.
     */
    OPTIMAL_HEIGHT(0),

    /**
     * 사전 설정 고도
     * 
     * 사용자가 미리 설정한 고도로 귀환합니다.
     * 고정된 고도 값을 사용하여 예측 가능한 귀환을 제공합니다.
     */
    PRESET_HEIGHT(1);

    /**
     * 홈포인트 귀환 모드 값
     * 
     * 각 귀환 모드를 구분하는 정수 값입니다.
     */
    private final int rthMode;

    /**
     * 홈포인트 귀환 모드 열거형 생성자
     * 
     * @param rthMode 홈포인트 귀환 모드 값
     */
    RthModeEnum(int rthMode) {
        this.rthMode = rthMode;
    }

    /**
     * 홈포인트 귀환 모드 값을 반환합니다.
     * 
     * @return 홈포인트 귀환 모드 값
     */
    @JsonValue
    public int getRthMode() {
        return rthMode;
    }

    /**
     * 홈포인트 귀환 모드 값으로 귀환 모드를 찾습니다.
     * 
     * 주어진 모드 값에 해당하는 열거형을 반환합니다.
     * 해당하는 모드가 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param rthMode 찾을 홈포인트 귀환 모드 값
     * @return 해당하는 RthModeEnum 열거형
     * @throws CloudSDKException 해당하는 모드가 없을 경우
     */
    @JsonCreator
    public static RthModeEnum find(int rthMode) {
        return Arrays.stream(values()).filter(rthModeEnum -> rthModeEnum.rthMode == rthMode).findAny()
            .orElseThrow(() -> new CloudSDKException(RthModeEnum.class, rthMode));
    }

}
