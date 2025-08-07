package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 강우량 열거형 클래스
 * 
 * 이 클래스는 강우량의 정도를 정의합니다.
 * 없음, 약함, 보통, 강함 등의 강우량을 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public enum RainfallEnum {

    /**
     * 없음 (강우량값: 0)
     */
    NO(0),

    /**
     * 약함 (강우량값: 1)
     */
    LIGHT(1),

    /**
     * 보통 (강우량값: 2)
     */
    MODERATE(2),

    /**
     * 강함 (강우량값: 3)
     */
    HEAVY(3),
    ;

    /**
     * 강우량 정수값
     */
    private final int rain;

    /**
     * 강우량 열거형 생성자
     * 
     * @param rain 강우량 정수값
     */
    RainfallEnum(int rain) {
        this.rain = rain;
    }

    /**
     * 강우량 정수값을 반환합니다.
     * 
     * @return 강우량 정수값
     */
    @JsonValue
    public int getRain() {
        return rain;
    }

    /**
     * 정수값으로 강우량을 찾습니다.
     * 
     * @param rain 찾을 강우량 정수값
     * @return 찾은 강우량 열거형
     * @throws CloudSDKException 해당하는 강우량을 찾을 수 없는 경우
     */
    @JsonCreator
    public static RainfallEnum find(int rain) {
        return Arrays.stream(values()).filter(rainEnum -> rainEnum.rain == rain).findAny()
            .orElseThrow(() -> new CloudSDKException(RainfallEnum.class, rain));
    }

}
