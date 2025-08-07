package com.dji.sdk.cloudapi.flightarea;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 지오펜스 타입 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 지오펜스의 타입을 정의합니다.
 * DFENCE(동적 펜스)와 NFZ(No Fly Zone) 두 가지 타입을 포함합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/21
 */
public enum GeofenceTypeEnum {

    /**
     * 동적 펜스
     * 동적으로 설정되는 비행 제한 구역
     */
    DFENCE("dfence"),

    /**
     * 비행 금지 구역
     * No Fly Zone, 항상 비행이 금지된 구역
     */
    NFZ("nfz"),

    ;

    /**
     * 지오펜스 타입
     */
    private final String type;

    /**
     * 지오펜스 타입 열거형 생성자
     * 
     * @param type 지오펜스 타입
     */
    GeofenceTypeEnum(String type) {
        this.type = type;
    }

    /**
     * 지오펜스 타입을 반환합니다.
     * 
     * @return 지오펜스 타입
     */
    @JsonValue
    public String getType() {
        return type;
    }

    /**
     * 문자열로 지오펜스 타입을 찾습니다.
     * 
     * @param type 찾을 지오펜스 타입
     * @return 찾은 지오펜스 타입 열거형
     * @throws CloudSDKException 해당하는 타입을 찾을 수 없는 경우
     */
    @JsonCreator
    public static GeofenceTypeEnum find(String type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type.equals(type)).findAny()
            .orElseThrow(() -> new CloudSDKException(GeofenceTypeEnum.class, type));
    }
}
