package com.dji.sdk.cloudapi.livestream;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 비디오 카메라 타입을 정의하는 열거형
 * 다양한 카메라 모드를 지원합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/25
 */
public enum VideoTypeEnum {

    /** 줌 카메라 */
    ZOOM("zoom"),

    /** 광각 카메라 */
    WIDE("wide"),

    /** 열화상 카메라 */
    THERMAL("thermal"),

    /** 일반 카메라 */
    NORMAL("normal"),

    /** 적외선 카메라 */
    IR("ir"),

    MULTI_SPECTRAL("multi-spectral"),

    RGB("rgb"),
    ;

    /** 비디오 타입을 나타내는 문자열 값 */
    private final String type;

    /**
     * 비디오 타입 열거형 생성자
     * @param type 비디오 타입을 나타내는 문자열 값
     */
    VideoTypeEnum(String type) {
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
     * 문자열 값으로 비디오 타입을 찾습니다.
     * JSON 역직렬화 시 사용됩니다.
     * 
     * @param videoType 찾을 비디오 타입의 문자열 값
     * @return 해당하는 VideoTypeEnum 인스턴스
     * @throws CloudSDKException 지정된 타입이 존재하지 않을 경우
     */
    @JsonCreator
    public static VideoTypeEnum find(String videoType) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type.equals(videoType)).findAny()
                .orElseThrow(() -> new CloudSDKException(VideoTypeEnum.class , videoType));
    }
}
