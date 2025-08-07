package com.dji.sdk.cloudapi.config;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 설정 타입 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 설정의 타입을 정의합니다.
 * 현재는 JSON 형식만 지원합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/10
 */
public enum ConfigTypeEnum {

    /**
     * JSON 형식 설정
     * JSON 형태로 구성된 설정 데이터
     */
    JSON("json");

    /**
     * 설정 타입 값
     */
    private final String type;

    /**
     * 설정 타입 열거형 생성자
     * 
     * @param type 설정 타입 값
     */
    ConfigTypeEnum(String type) {
        this.type = type;
    }

    /**
     * 설정 타입 값을 반환합니다.
     * 
     * @return 설정 타입 값
     */
    @JsonValue
    public String getType() {
        return type;
    }

    /**
     * 문자열로 설정 타입을 찾습니다.
     * 
     * @param type 찾을 설정 타입 문자열
     * @return 찾은 설정 타입 열거형
     * @throws CloudSDKException 해당하는 설정 타입을 찾을 수 없는 경우
     */
    @JsonCreator
    public static ConfigTypeEnum find(String type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type.equals(type)).findAny()
                .orElseThrow(() -> new CloudSDKException(ConfigTypeEnum.class, type));
    }
}
