package com.dji.sdk.cloudapi.control;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 노출 모드 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 카메라의 노출 모드를 정의합니다.
 * 자동, 셔터 우선, 조리개 우선, 수동 모드를 지원합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public enum ExposureModeEnum {

    /**
     * 자동 모드
     * 카메라가 자동으로 노출을 조정
     */
    AUTO(1),

    /**
     * 셔터 우선 모드
     * 셔터 속도를 우선적으로 설정하고 조리개를 자동 조정
     */
    SHUTTER_PRIORITY(2),

    /**
     * 조리개 우선 모드
     * 조리개를 우선적으로 설정하고 셔터 속도를 자동 조정
     */
    APERTURE_PRIORITY(3),

    /**
     * 수동 모드
     * 셔터 속도와 조리개를 모두 수동으로 설정
     */
    MANUAL(4),

    ;


    /**
     * 노출 모드 값
     */
    private final int mode;

    /**
     * 노출 모드 열거형 생성자
     * 
     * @param mode 노출 모드 값
     */
    ExposureModeEnum(int mode) {
        this.mode = mode;
    }

    /**
     * 노출 모드 값을 반환합니다.
     * 
     * @return 노출 모드 값
     */
    @JsonValue
    public int getMode() {
        return mode;
    }

    /**
     * 정수 값으로 노출 모드를 찾습니다.
     * 
     * @param mode 찾을 노출 모드 값
     * @return 찾은 노출 모드 열거형
     * @throws CloudSDKException 해당하는 노출 모드를 찾을 수 없는 경우
     */
    @JsonCreator
    public static ExposureModeEnum find(int mode) {
        return Arrays.stream(values()).filter(modeEnum -> modeEnum.mode == mode).findAny()
            .orElseThrow(() -> new CloudSDKException(ExposureModeEnum.class, mode));
    }

}
