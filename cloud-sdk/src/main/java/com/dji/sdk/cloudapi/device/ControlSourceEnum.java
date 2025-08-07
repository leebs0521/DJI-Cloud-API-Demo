package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 제어 소스 열거형 클래스
 * 
 * 이 클래스는 디바이스의 제어 소스를 정의합니다.
 * A, B, 알 수 없음 등의 제어 소스를 나타냅니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/16
 */
public enum ControlSourceEnum {

    /**
     * 제어 소스 A
     */
    A("A"),

    /**
     * 제어 소스 B
     */
    B("B"),

    /**
     * 알 수 없음
     */
    UNKNOWN("");

    /**
     * 제어 소스 문자열
     */
    private final String controlSource;

    /**
     * 제어 소스 열거형 생성자
     * 
     * @param controlSource 제어 소스 문자열
     */
    ControlSourceEnum(String controlSource) {
        this.controlSource = controlSource;
    }

    /**
     * 제어 소스 문자열을 반환합니다.
     * 
     * @return 제어 소스 문자열
     */
    @JsonValue
    public String getControlSource() {
        return controlSource;
    }

    /**
     * 문자열로 제어 소스를 찾습니다.
     * 
     * @param controlSource 찾을 제어 소스 문자열
     * @return 찾은 제어 소스 열거형
     * @throws CloudSDKException 해당하는 제어 소스를 찾을 수 없는 경우
     */
    @JsonCreator
    public static ControlSourceEnum find(String controlSource) {
        return Arrays.stream(values()).filter(controlSourceEnum -> controlSourceEnum.controlSource.equals(controlSource)).findAny()
                .orElseThrow(() -> new CloudSDKException(ControlSourceEnum.class, controlSource));
    }
}
