package com.dji.sdk.cloudapi.debug;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 동글 디바이스 타입 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 동글 디바이스의 타입을 정의합니다.
 * 독(Dock)과 드론(Drone) 두 가지 타입을 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public enum DongleDeviceTypeEnum {

    /**
     * 독
     * 독에 설치된 동글 디바이스
     */
    DOCK("dock"),

    /**
     * 드론
     * 드론에 설치된 동글 디바이스
     */
    DRONE("drone"),

    ;

    /**
     * 디바이스 타입
     */
    private final String type;

    /**
     * 동글 디바이스 타입 열거형 생성자
     * 
     * @param type 디바이스 타입
     */
    DongleDeviceTypeEnum(String type) {
        this.type = type;
    }

    /**
     * 디바이스 타입을 반환합니다.
     * 
     * @return 디바이스 타입
     */
    @JsonValue
    public String getType() {
        return type;
    }

    /**
     * 문자열로 동글 디바이스 타입을 찾습니다.
     * 
     * @param type 찾을 디바이스 타입
     * @return 찾은 동글 디바이스 타입 열거형
     * @throws CloudSDKException 해당하는 타입을 찾을 수 없는 경우
     */
    @JsonCreator
    public static DongleDeviceTypeEnum find(String type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type.equals(type)).findAny()
            .orElseThrow(() -> new CloudSDKException(DongleDeviceTypeEnum.class, type));
    }
}
