package com.dji.sdk.cloudapi.control;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 측광 모드 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 카메라의 측광 모드를 정의합니다.
 * 측광 비활성화, 스팟 측광, 영역 측광을 지원합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public enum MeteringModeEnum {

    /**
     * 측광 비활성화
     * 측광 기능을 사용하지 않음
     */
    DISABLE(0),

    /**
     * 스팟 측광
     * 특정 지점에 대한 측광
     */
    SPOT(1),

    /**
     * 영역 측광
     * 특정 영역에 대한 측광
     */
    AREA(2),

    ;

    /**
     * 측광 모드 값
     */
    private final int mode;

    /**
     * 측광 모드 열거형 생성자
     * 
     * @param mode 측광 모드 값
     */
    MeteringModeEnum(int mode) {
        this.mode = mode;
    }

    /**
     * 측광 모드 값을 반환합니다.
     * 
     * @return 측광 모드 값
     */
    @JsonValue
    public int getMode() {
        return mode;
    }

    /**
     * 정수 값으로 측광 모드를 찾습니다.
     * 
     * @param mode 찾을 측광 모드 값
     * @return 찾은 측광 모드 열거형
     * @throws CloudSDKException 해당하는 측광 모드를 찾을 수 없는 경우
     */
    @JsonCreator
    public static MeteringModeEnum find(int mode) {
        return Arrays.stream(values()).filter(modeEnum -> modeEnum.mode == mode).findAny()
            .orElseThrow(() -> new CloudSDKException(MeteringModeEnum.class, mode));
    }

}
