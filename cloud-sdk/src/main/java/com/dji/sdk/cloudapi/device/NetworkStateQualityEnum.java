package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 네트워크 상태 품질 열거형 클래스
 * 
 * 이 클래스는 네트워크 연결의 품질을 정의합니다.
 * 신호 없음, 나쁨, 보통, 좋음, 매우 좋음 등의 품질을 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public enum NetworkStateQualityEnum {

    /**
     * 신호 없음 (품질값: 0)
     */
    NO_SIGNAL(0),

    /**
     * 나쁨 (품질값: 1)
     */
    BAD(1),

    /**
     * 보통 (품질값: 2)
     */
    POOR(2),

    /**
     * 양호 (품질값: 3)
     */
    FAIR(3),

    /**
     * 좋음 (품질값: 4)
     */
    GOOD(4),

    /**
     * 매우 좋음 (품질값: 5)
     */
    EXCELLENT(5),
    ;

    /**
     * 네트워크 상태 품질 정수값
     */
    private final int quality;

    /**
     * 네트워크 상태 품질 열거형 생성자
     * 
     * @param quality 네트워크 상태 품질 정수값
     */
    NetworkStateQualityEnum(int quality) {
        this.quality = quality;
    }

    /**
     * 네트워크 상태 품질 정수값을 반환합니다.
     * 
     * @return 네트워크 상태 품질 정수값
     */
    @JsonValue
    public int getQuality() {
        return quality;
    }

    /**
     * 정수값으로 네트워크 상태 품질을 찾습니다.
     * 
     * @param quality 찾을 네트워크 상태 품질 정수값
     * @return 찾은 네트워크 상태 품질 열거형
     * @throws CloudSDKException 해당하는 품질을 찾을 수 없는 경우
     */
    @JsonCreator
    public static NetworkStateQualityEnum find(int quality) {
        return Arrays.stream(values()).filter(qualityEnum -> qualityEnum.quality == quality).findAny()
            .orElseThrow(() -> new CloudSDKException(NetworkStateQualityEnum.class, quality));
    }

}
