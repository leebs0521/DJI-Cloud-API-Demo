package com.dji.sdk.cloudapi.map;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;

/**
 * 지도 요소 리소스 타입 열거형
 * 
 * 이 열거형은 지도 요소의 리소스 타입을 정의합니다.
 * 각 타입은 숫자 값과 문자열 이름을 모두 가지고 있어서
 * API 통신 시 유연한 타입 처리가 가능합니다.
 * 
 * 지원하는 타입:
 * - POINT(0, "Point"): 점 요소
 * - LINE_STRING(1, "LineString"): 선 요소
 * - POLYGON(2, "Polygon"): 다각형 요소
 * 
 * 이 열거형은 JSON 직렬화/역직렬화를 지원하며,
 * 숫자 값과 문자열 이름 간의 변환을 제공합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/30
 */
@Schema(enumAsRef = true, description = "<p>0: Point <p/><p>1: LineString <p/><p>2: Polygon</p>", allowableValues = {"0", "1", "2"})
public enum ElementResourceTypeEnum {

    /**
     * 점 타입
     * 지도에서 단일 점을 표현하는 요소입니다.
     * 값: 0, 이름: "Point"
     */
    POINT(0, "Point"),

    /**
     * 선 타입
     * 지도에서 선을 표현하는 요소입니다.
     * 값: 1, 이름: "LineString"
     */
    LINE_STRING(1, "LineString"),

    /**
     * 다각형 타입
     * 지도에서 다각형을 표현하는 요소입니다.
     * 값: 2, 이름: "Polygon"
     */
    POLYGON(2, "Polygon");

    /**
     * 타입의 숫자 값
     */
    private final int type;

    /**
     * 타입의 문자열 이름
     */
    private final String typeName;

    ElementResourceTypeEnum(int type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    /**
     * 타입 이름을 반환합니다.
     * 
     * @return 타입 이름 (예: "Point", "LineString", "Polygon")
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 타입의 숫자 값을 반환합니다.
     * JSON 직렬화 시 이 값이 사용됩니다.
     * 
     * @return 타입 값 (0, 1, 2)
     */
    @JsonValue
    public int getType() {
        return type;
    }

    /**
     * 타입 값으로 열거형을 찾습니다.
     * 
     * 이 메서드는 숫자 값에 해당하는 열거형을 반환합니다.
     * JSON 역직렬화 시 사용되며, @JsonCreator 어노테이션이 적용되어 있습니다.
     * 
     * @param type 타입 값 (0, 1, 2)
     * @return 해당하는 열거형
     * @throws CloudSDKException 해당하는 타입이 없는 경우
     */
    @JsonCreator
    public static ElementResourceTypeEnum find(int type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type == type).findAny()
            .orElseThrow(() -> new CloudSDKException(ElementResourceTypeEnum.class, type));
    }

    /**
     * 타입 이름으로 열거형을 찾습니다.
     * 
     * 이 메서드는 문자열 이름에 해당하는 열거형을 반환합니다.
     * 
     * @param typeName 타입 이름 (예: "Point", "LineString", "Polygon")
     * @return 해당하는 열거형
     * @throws CloudSDKException 해당하는 타입이 없는 경우
     */
    public static ElementResourceTypeEnum find(String typeName) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.typeName.equals(typeName)).findAny()
            .orElseThrow(() -> new CloudSDKException(ElementResourceTypeEnum.class, typeName));
    }

}
