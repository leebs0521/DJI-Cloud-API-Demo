package com.dji.sdk.cloudapi.control;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 렌즈 저장 설정 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 카메라 렌즈의 저장 설정을 정의합니다.
 * 현재, 줌, 와이드, 비전, 적외선 렌즈를 구분합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/12
 */
public enum LensStorageSettingsEnum {

    /**
     * 현재 렌즈
     * 현재 사용 중인 렌즈
     */
    CURRENT("current"),

    /**
     * 줌 렌즈
     * 줌 기능이 있는 렌즈
     */
    ZOOM("zoom"),

    /**
     * 와이드 렌즈
     * 광각 렌즈
     */
    WIDE("wide"),

    /**
     * 비전 렌즈
     * 비전 카메라 렌즈
     */
    VISION("vision"),

    /**
     * 적외선 렌즈
     * 적외선 카메라 렌즈
     */
    INFRARED("ir");

    /**
     * 렌즈 문자열
     */
    private final String lens;

    /**
     * 렌즈 저장 설정 열거형 생성자
     * 
     * @param lens 렌즈 문자열
     */
    LensStorageSettingsEnum(String lens) {
        this.lens = lens;
    }

    /**
     * 렌즈 문자열을 반환합니다.
     * 
     * @return 렌즈 문자열
     */
    @JsonValue
    public String getLens() {
        return lens;
    }

    /**
     * 문자열로 렌즈 저장 설정을 찾습니다.
     * 
     * @param lens 찾을 렌즈 문자열
     * @return 찾은 렌즈 저장 설정 열거형
     * @throws CloudSDKException 해당하는 렌즈 저장 설정을 찾을 수 없는 경우
     */
    @JsonCreator
    public static LensStorageSettingsEnum find(String lens) {
        return Arrays.stream(values()).filter(lensEnum -> lensEnum.lens.equals(lens)).findAny()
            .orElseThrow(() -> new CloudSDKException(LensStorageSettingsEnum.class, lens));
    }
}
