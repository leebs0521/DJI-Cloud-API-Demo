package com.dji.sdk.cloudapi.livestream;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 렌즈 변경 비디오 타입을 정의하는 열거형
 * 라이브스트림 중 렌즈 전환이 가능한 비디오 타입을 정의합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/25
 */
public enum LensChangeVideoTypeEnum {

    /** 줌 렌즈 */
    ZOOM("zoom"),

    /** 광각 렌즈 */
    WIDE("wide"),

    /** 적외선 렌즈 */
    IR("ir");

    /** 비디오 타입을 나타내는 문자열 값 */
    private final String type;

    /**
     * 렌즈 변경 비디오 타입 열거형 생성자
     * @param type 비디오 타입을 나타내는 문자열 값
     */
    LensChangeVideoTypeEnum(String type) {
        this.type = type;
    }

    /**
     * 비디오 타입 값을 반환합니다.
     * JSON 직렬화 시 사용됩니다.
     * 
     * @return 비디오 타입 문자열 값
     */
    @JsonValue
    public String getType() {
        return type;
    }

    /**
     * 문자열 값으로 렌즈 변경 비디오 타입을 찾습니다.
     * JSON 역직렬화 시 사용됩니다.
     * 
     * @param videoType 찾을 비디오 타입의 문자열 값
     * @return 해당하는 LensChangeVideoTypeEnum 인스턴스
     * @throws CloudSDKException 지정된 타입이 존재하지 않을 경우
     */
    @JsonCreator
    public static LensChangeVideoTypeEnum find(String videoType) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type.equals(videoType)).findAny()
                .orElseThrow(() -> new CloudSDKException(LensChangeVideoTypeEnum.class , videoType));
    }
}
