package com.dji.sdk.cloudapi.flightarea;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 지오메트리 타입 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 지오메트리의 타입을 정의합니다.
 * 점(Point)과 다각형(Polygon) 두 가지 타입을 포함합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/21
 */
public enum GeometryTypeEnum {

    /**
     * 점
     * 단일 좌표점 지오메트리 타입
     */
    POINT("Point"),

    /**
     * 다각형
     * 다각형 지오메트리 타입
     */
    POLYGON("Polygon"),

    ;

    /**
     * 지오메트리 타입
     */
    private final String type;

    /**
     * 지오메트리 타입 열거형 생성자
     * 
     * @param type 지오메트리 타입
     */
    GeometryTypeEnum(String type) {
        this.type = type;
    }

    /**
     * 지오메트리 타입을 반환합니다.
     * 
     * @return 지오메트리 타입
     */
    @JsonValue
    public String getType() {
        return type;
    }

    /**
     * 문자열로 지오메트리 타입을 찾습니다.
     * 
     * @param type 찾을 지오메트리 타입
     * @return 찾은 지오메트리 타입 열거형
     * @throws CloudSDKException 해당하는 타입을 찾을 수 없는 경우
     */
    @JsonCreator
    public static GeometryTypeEnum find(String type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type.equals(type)).findAny()
            .orElseThrow(() -> new CloudSDKException(GeometryTypeEnum.class, type));
    }
}
