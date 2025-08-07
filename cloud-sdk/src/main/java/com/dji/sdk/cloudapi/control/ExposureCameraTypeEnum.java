package com.dji.sdk.cloudapi.control;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 노출 카메라 타입 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 노출 설정이 가능한 카메라의 타입을 정의합니다.
 * 줌 카메라와 와이드 카메라를 구분합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public enum ExposureCameraTypeEnum {

    /**
     * 줌 카메라
     * 줌 기능이 있는 카메라
     */
    ZOOM("zoom"),

    /**
     * 와이드 카메라
     * 광각 렌즈를 사용하는 카메라
     */
    WIDE("wide");

    /**
     * 카메라 타입 문자열
     */
    private final String type;

    /**
     * 노출 카메라 타입 열거형 생성자
     * 
     * @param type 카메라 타입 문자열
     */
    ExposureCameraTypeEnum(String type) {
        this.type = type;
    }

    /**
     * 카메라 타입 문자열을 반환합니다.
     * 
     * @return 카메라 타입 문자열
     */
    @JsonValue
    public String getType() {
        return type;
    }

    /**
     * 문자열로 노출 카메라 타입을 찾습니다.
     * 
     * @param type 찾을 카메라 타입 문자열
     * @return 찾은 노출 카메라 타입 열거형
     * @throws CloudSDKException 해당하는 카메라 타입을 찾을 수 없는 경우
     */
    @JsonCreator
    public static ExposureCameraTypeEnum find(String type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type.equals(type)).findAny()
                .orElseThrow(() -> new CloudSDKException(ExposureCameraTypeEnum.class, type));
    }
}
