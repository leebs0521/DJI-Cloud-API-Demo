package com.dji.sdk.cloudapi.map;

import com.dji.sdk.exception.CloudSDKException;

import java.util.Optional;

/**
 * 지도 요소 타입 열거형
 * 
 * 이 열거형은 지도에서 사용되는 요소의 타입을 정의하고 관리합니다.
 * 각 타입은 특정 지오메트리 클래스와 매핑되어 있습니다.
 * 
 * 지원하는 타입:
 * - POINT: 점 요소 (ElementPointGeometry와 매핑)
 * - LINE_STRING: 선 요소 (ElementLineStringGeometry와 매핑)
 * - POLYGON: 다각형 요소 (ElementPolygonGeometry와 매핑)
 * 
 * 이 열거형은 타입 값과 설명 간의 변환을 제공하며,
 * 런타임에 동적으로 지오메트리 객체를 생성하는 데 사용됩니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/30
 */
public enum ElementTypeEnum {

    /**
     * 점 타입
     * 지도에서 단일 점을 표현하는 요소입니다.
     * 착륙 지점, 경유지, 관심 지점 등을 표현할 때 사용됩니다.
     */
    POINT(ElementResourceTypeEnum.POINT),

    /**
     * 선 타입
     * 지도에서 선을 표현하는 요소입니다.
     * 비행 경로, 경계선, 도로 등을 표현할 때 사용됩니다.
     */
    LINE_STRING(ElementResourceTypeEnum.LINE_STRING),

    /**
     * 다각형 타입
     * 지도에서 다각형을 표현하는 요소입니다.
     * 비행 금지 구역, 관심 구역, 착륙장 등을 표현할 때 사용됩니다.
     */
    POLYGON(ElementResourceTypeEnum.POLYGON);

    /**
     * 해당 타입에 매핑된 리소스 타입 열거형
     */
    private ElementResourceTypeEnum typeEnum;

    ElementTypeEnum(ElementResourceTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    /**
     * 값에 해당하는 지오메트리 타입을 찾습니다.
     * 
     * 이 메서드는 숫자 값에 해당하는 지오메트리 객체를 동적으로 생성합니다.
     * 타입 값이 유효하지 않은 경우 빈 Optional을 반환합니다.
     * 
     * @param val 타입 값 (0: 점, 1: 선, 2: 다각형)
     * @return 지오메트리 타입 (Optional) - 유효하지 않은 값인 경우 빈 Optional
     */
    public static Optional<ElementGeometryType> findType(int val) {
        if (POINT.typeEnum.getType() == val) {
            return Optional.of(new ElementPointGeometry());
        }

        if (LINE_STRING.typeEnum.getType() == val) {
            return Optional.of(new ElementLineStringGeometry());
        }

        if (POLYGON.typeEnum.getType() == val) {
            return Optional.of(new ElementPolygonGeometry());
        }

        return Optional.empty();
    }

    /**
     * 타입의 설명을 반환합니다.
     * 
     * @return 타입 설명 (예: "Point", "LineString", "Polygon")
     */
    public String getDesc() {
        return typeEnum.getTypeName();
    }

    /**
     * 설명에 해당하는 값을 찾습니다.
     * 
     * 이 메서드는 타입 설명 문자열에 해당하는 숫자 값을 반환합니다.
     * 알 수 없는 타입인 경우 CloudSDKException을 발생시킵니다.
     * 
     * @param desc 타입 설명 (예: "Point", "LineString", "Polygon")
     * @return 타입 값 (0, 1, 2)
     * @throws CloudSDKException 알 수 없는 타입인 경우
     */
    public static int findVal(String desc) {
        if (POINT.typeEnum.getTypeName().equals(desc)) {
            return POINT.typeEnum.getType();
        }

        if (LINE_STRING.typeEnum.getTypeName().equals(desc)) {
            return LINE_STRING.typeEnum.getType();
        }

        if (POLYGON.typeEnum.getTypeName().equals(desc)) {
            return POLYGON.typeEnum.getType();
        }

        throw new CloudSDKException("unknown type:" + desc);
    }
}
