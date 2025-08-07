package com.dji.sdk.cloudapi.control;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 사진 촬영 진행 단계 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 사진 촬영의 진행 단계를 정의합니다.
 * 일반 촬영과 파노라마 촬영의 다양한 단계를 포함합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public enum PhotoTakeProgressStepEnum {

    /**
     * 일반 촬영
     * 일반적인 사진 촬영 단계
     */
    NORMAL(0),

    /**
     * 파노라마 미시작 또는 종료
     * 파노라마 촬영이 시작되지 않았거나 종료된 상태
     */
    PANORAMA_NOT_STARTED_OR_ENDED(3000),

    /**
     * 파노라마 촬영 중
     * 파노라마 사진을 촬영하고 있는 단계
     */
    PANORAMA_TAKING(3002),

    /**
     * 파노라마 합성 중
     * 파노라마 사진을 합성하고 있는 단계
     */
    PANORAMA_COMPOSITING(3005),

    ;

    /**
     * 진행 단계 값
     */
    private final int step;

    /**
     * 사진 촬영 진행 단계 열거형 생성자
     * 
     * @param step 진행 단계 값
     */
    PhotoTakeProgressStepEnum(int step) {
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
     * 정수 값으로 사진 촬영 진행 단계를 찾습니다.
     * 
     * @param step 찾을 진행 단계 값
     * @return 찾은 사진 촬영 진행 단계 열거형
     * @throws CloudSDKException 해당하는 진행 단계를 찾을 수 없는 경우
     */
    @JsonCreator
    public static PhotoTakeProgressStepEnum find(int step) {
        return Arrays.stream(values()).filter(stepEnum -> stepEnum.step == step).findAny()
            .orElseThrow(() -> new CloudSDKException(PhotoTakeProgressStepEnum.class, step));
    }

}
