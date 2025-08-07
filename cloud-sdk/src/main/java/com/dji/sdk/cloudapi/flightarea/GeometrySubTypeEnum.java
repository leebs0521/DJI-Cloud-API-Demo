package com.dji.sdk.cloudapi.flightarea;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 지오메트리 서브 타입 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 지오메트리의 서브 타입을 정의합니다.
 * 원형(Circle) 등의 서브 타입을 포함합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/21
 */
public enum GeometrySubTypeEnum {

    /**
     * 원형
     * 원형 지오메트리 서브 타입
     */
    CIRCLE("Circle"),

    ;

    /**
     * 지오메트리 서브 타입
     */
    private final String subType;

    /**
     * 지오메트리 서브 타입 열거형 생성자
     * 
     * @param subType 지오메트리 서브 타입
     */
    GeometrySubTypeEnum(String subType) {
        this.subType = subType;
    }

    /**
     * 지오메트리 서브 타입을 반환합니다.
     * 
     * @return 지오메트리 서브 타입
     */
    @JsonValue
    public String getSubType() {
        return subType;
    }

    /**
     * 문자열로 지오메트리 서브 타입을 찾습니다.
     * 
     * @param subType 찾을 지오메트리 서브 타입
     * @return 찾은 지오메트리 서브 타입 열거형
     * @throws CloudSDKException 해당하는 서브 타입을 찾을 수 없는 경우
     */
    @JsonCreator
    public static GeometrySubTypeEnum find(String subType) {
        return Arrays.stream(values()).filter(subTypeEnum -> subTypeEnum.subType.equals(subType)).findAny()
            .orElseThrow(() -> new CloudSDKException(GeometrySubTypeEnum.class, subType));
    }
}
