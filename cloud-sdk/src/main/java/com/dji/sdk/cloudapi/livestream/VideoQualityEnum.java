package com.dji.sdk.cloudapi.livestream;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 비디오 품질을 정의하는 열거형
 * 라이브스트림에서 사용할 수 있는 다양한 비디오 품질 옵션을 제공합니다.
 * 
 * @author sean
 * @version 0.1
 * @date 2021/11/26
 */
public enum VideoQualityEnum {

    /** 자동 품질 설정 */
    AUTO (0),

    /** 부드러운 스트리밍 (낮은 품질, 높은 프레임률) */
    SMOOTH(1),

    /** 표준 화질 (SD) */
    STANDARD_DEFINITION(2),

    /** 고화질 (HD) */
    HIGH_DEFINITION(3),

    /** 초고화질 (Ultra HD) */
    ULTRA_HD(4);

    /** 비디오 품질을 나타내는 정수 값 */
    private final int quality;

    /**
     * 비디오 품질 열거형 생성자
     * @param quality 비디오 품질을 나타내는 정수 값
     */
    VideoQualityEnum(int quality) {
        this.quality = quality;
    }

    /**
     * 비디오 품질 값을 반환합니다.
     * JSON 직렬화 시 사용됩니다.
     * 
     * @return 비디오 품질 정수 값
     */
    @JsonValue
    public int getQuality() {
        return quality;
    }

    /**
     * 정수 값으로 비디오 품질을 찾습니다.
     * JSON 역직렬화 시 사용됩니다.
     * 
     * @param quality 찾을 비디오 품질의 정수 값
     * @return 해당하는 VideoQualityEnum 인스턴스
     * @throws CloudSDKException 지정된 품질이 존재하지 않을 경우
     */
    @JsonCreator
    public static VideoQualityEnum find(int quality) {
        return Arrays.stream(values()).filter(qualityEnum -> qualityEnum.quality == quality).findAny()
                .orElseThrow(() -> new CloudSDKException(VideoQualityEnum.class, quality));
    }
}
