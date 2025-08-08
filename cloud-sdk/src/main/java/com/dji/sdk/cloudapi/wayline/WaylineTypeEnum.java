package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;

/**
 * 웨이라인 타입 열거형
 * 
 * 이 열거형은 웨이라인의 다양한 타입을 정의합니다.
 * 웨이포인트 비행, 2D 매핑, 3D 매핑, 스트립 매핑 등의
 * 다양한 비행 모드를 지원합니다.
 * 
 * 주요 구성 요소:
 * - WAYPOINT: 웨이포인트 비행
 * - MAPPING_2D: 2D 매핑
 * - MAPPING_3D: 3D 매핑
 * - MAPPING_STRIP: 스트립 매핑
 * 
 * 이 열거형은 웨이라인의 타입을 식별하고
 * 각 타입에 맞는 적절한 처리 로직을 적용하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/9/26
 */
@Schema(enumAsRef = true, type = "int", allowableValues = {"0", "1", "2", "3"},
        description = "<p>0: 웨이포인트<p/><p>1: 2D 매핑<p/><p>2: 3D 매핑<p/><p>3: 스트립 매핑</p>")
public enum WaylineTypeEnum {

    /**
     * 웨이포인트 비행
     * 
     * 미리 정의된 웨이포인트들을 순차적으로 비행하는 타입입니다.
     * 일반적인 자동 비행 작업에 사용됩니다.
     */
    WAYPOINT(0, "waypoint"),

    /**
     * 2D 매핑
     * 
     * 2차원 지도 생성을 위한 매핑 비행 타입입니다.
     * 평면적인 지형의 매핑에 사용됩니다.
     */
    MAPPING_2D(1, "mapping2d"),

    /**
     * 3D 매핑
     * 
     * 3차원 지도 생성을 위한 매핑 비행 타입입니다.
     * 입체적인 지형의 매핑에 사용됩니다.
     */
    MAPPING_3D(2, "mapping3d"),

    /**
     * 스트립 매핑
     * 
     * 선형 경로를 따라 매핑하는 비행 타입입니다.
     * 도로나 하천 등의 선형 지형 매핑에 사용됩니다.
     */
    MAPPING_STRIP(3, "mappingStrip");

    /**
     * 웨이라인 타입 값
     * 
     * 각 웨이라인 타입을 구분하는 정수 값입니다.
     */
    private final int value;

    /**
     * 웨이라인 타입 문자열
     * 
     * 각 웨이라인 타입을 구분하는 문자열 값입니다.
     */
    private final String type;

    /**
     * 웨이라인 타입 열거형 생성자
     * 
     * @param value 웨이라인 타입 값
     * @param type 웨이라인 타입 문자열
     */
    WaylineTypeEnum(int value, String type) {
        this.value = value;
        this.type = type;
    }

    /**
     * 웨이라인 타입 값을 반환합니다.
     * 
     * @return 웨이라인 타입 값
     */
    @JsonValue
    public int getValue() {
        return value;
    }

    /**
     * 웨이라인 타입 값으로 웨이라인 타입을 찾습니다.
     * 
     * 주어진 값에 해당하는 열거형을 반환합니다.
     * 해당하는 타입이 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param value 찾을 웨이라인 타입 값
     * @return 해당하는 WaylineTypeEnum 열거형
     * @throws CloudSDKException 해당하는 타입이 없을 경우
     */
    @JsonCreator
    public static WaylineTypeEnum find(int value) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.value == value).findAny()
                .orElseThrow(() -> new CloudSDKException(WaylineTypeEnum.class, value));
    }

    /**
     * 웨이라인 타입 문자열로 웨이라인 타입을 찾습니다.
     * 
     * 주어진 문자열에 해당하는 열거형을 반환합니다.
     * 해당하는 타입이 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param type 찾을 웨이라인 타입 문자열
     * @return 해당하는 WaylineTypeEnum 열거형
     * @throws CloudSDKException 해당하는 타입이 없을 경우
     */
    public static WaylineTypeEnum find(String type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type.equals(type)).findAny()
                .orElseThrow(() -> new CloudSDKException(WaylineTypeEnum.class, type));
    }
}
