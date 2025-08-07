package com.dji.sdk.cloudapi.airsense;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 고도 타입 열거형 클래스
 * 
 * 이 클래스는 항공기 고도의 측정 방식을 정의합니다.
 * 타원체 고도와 해수면 기준 고도 중 하나를 선택할 수 있습니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/16
 */
public enum AltitudeTypeEnum {

    /**
     * 타원체 고도 (WGS84 타원체 기준)
     * 지구 타원체 표면으로부터의 높이
     */
    ELLIPSOID_HEIGHT(0),

    /**
     * 해수면 기준 고도 (MSL - Mean Sea Level)
     * 평균 해수면으로부터의 높이
     */
    ABOVE_SEA_LEVEL(1),

    ;

    /**
     * 고도 타입 값
     */
    private final int type;

    /**
     * 고도 타입 열거형 생성자
     * 
     * @param type 고도 타입 값
     */
    AltitudeTypeEnum(int type) {
        this.type = type;
    }

    /**
     * 고도 타입 값을 반환합니다.
     * 
     * @return 고도 타입 값
     */
    @JsonValue
    public int getType() {
        return type;
    }

    /**
     * 정수 값으로 고도 타입을 찾습니다.
     * 
     * @param type 찾을 고도 타입 값
     * @return 찾은 고도 타입 열거형
     * @throws CloudSDKException 해당하는 고도 타입을 찾을 수 없는 경우
     */
    @JsonCreator
    public static AltitudeTypeEnum find(int type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type == type).findAny()
            .orElseThrow(() -> new CloudSDKException(AltitudeTypeEnum.class, type));
    }

}
