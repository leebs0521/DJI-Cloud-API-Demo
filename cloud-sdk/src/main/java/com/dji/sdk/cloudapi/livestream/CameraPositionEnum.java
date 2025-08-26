package com.dji.sdk.cloudapi.livestream;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 카메라 위치를 나타내는 열거형 클래스
 * 
 * DJI Cloud API에서 카메라의 물리적 위치를 정의합니다.
 * 카메라가 내부에 있는지 외부에 있는지를 구분합니다.
 */
public enum CameraPositionEnum {
    
    /**
     * 내부 카메라
     * 장비 내부에 설치된 카메라를 나타냅니다.
     */
    INSIDE(0),

    /**
     * 외부 카메라
     * 장비 외부에 설치된 카메라를 나타냅니다.
     */
    OUTSIDE(1);

    /**
     * 카메라 위치의 정수값
     */
    private final int value;

    /**
     * 카메라 위치 열거형 생성자
     * 
     * @param value 카메라 위치를 나타내는 정수값
     */
    CameraPositionEnum(int value) {
        this.value = value;
    }

    /**
     * 카메라 위치의 정수값을 반환합니다.
     * JSON 직렬화 시 이 값이 사용됩니다.
     * 
     * @return 카메라 위치의 정수값
     */
    @JsonValue
    public int getValue() {
        return value;
    }

    /**
     * 정수값으로 카메라 위치 열거형을 찾습니다.
     * JSON 역직렬화 시 사용됩니다.
     * 
     * @param value 찾을 카메라 위치의 정수값
     * @return 찾은 카메라 위치 열거형
     * @throws CloudSDKException 해당하는 카메라 위치를 찾을 수 없는 경우
     */
    @JsonCreator
    public static CameraPositionEnum find(int value) {
        return Arrays.stream(values()).filter(position -> position.value == value).findAny()
                .orElseThrow(() -> new CloudSDKException(CameraPositionEnum.class, value));
    }
}
