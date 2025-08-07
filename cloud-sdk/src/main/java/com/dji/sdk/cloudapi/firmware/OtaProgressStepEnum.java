package com.dji.sdk.cloudapi.firmware;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * OTA 진행 단계 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 OTA(Over-The-Air) 펌웨어 업데이트의 진행 단계를 정의합니다.
 * 다운로드와 업그레이드 두 가지 단계를 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/28
 */
public enum OtaProgressStepEnum {

    /**
     * 다운로드 중
     * 펌웨어 파일을 다운로드하고 있는 단계
     */
    DOWNLOADING(1),

    /**
     * 업그레이드 중
     * 펌웨어를 업그레이드하고 있는 단계
     */
    UPGRADING(2);

    /**
     * 진행 단계 값
     */
    private final int step;

    /**
     * OTA 진행 단계 열거형 생성자
     * 
     * @param step 진행 단계 값
     */
    OtaProgressStepEnum(int step) {
        this.step = step;
    }

    /**
     * 진행 단계 값을 반환합니다.
     * 
     * @return 진행 단계 값
     */
    @JsonValue
    public int getStep() {
        return step;
    }

    /**
     * 정수 값으로 OTA 진행 단계를 찾습니다.
     * 
     * @param step 찾을 진행 단계 값
     * @return 찾은 OTA 진행 단계 열거형
     * @throws CloudSDKException 해당하는 진행 단계를 찾을 수 없는 경우
     */
    @JsonCreator
    public static OtaProgressStepEnum find(int step) {
        return Arrays.stream(values()).filter(stepEnum -> stepEnum.step == step).findAny()
                .orElseThrow(() -> new CloudSDKException(OtaProgressStepEnum.class, step));
    }
}
