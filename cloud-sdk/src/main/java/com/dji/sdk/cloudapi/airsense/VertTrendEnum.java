package com.dji.sdk.cloudapi.airsense;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 수직 변화 추세 열거형 클래스
 * 
 * 이 클래스는 항공기의 상대 고도 변화 추세를 정의합니다.
 * 드론 대비 항공기의 고도가 증가하는지, 감소하는지, 변화가 없는지를 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/16
 */
public enum VertTrendEnum {

    /**
     * 상대 고도 변화 없음
     * 항공기의 고도가 드론 대비 변화가 없음
     */
    RELATIVE_HEIGHT_UNCHANGED(0),

    /**
     * 상대 고도 증가
     * 항공기의 고도가 드론 대비 증가함
     */
    RELATIVE_HEIGHT_INCREASE(1),

    /**
     * 상대 고도 감소
     * 항공기의 고도가 드론 대비 감소함
     */
    RELATIVE_HEIGHT_DECREASE(2),

    ;

    /**
     * 수직 변화 추세 값
     */
    private final int trend;

    /**
     * 수직 변화 추세 열거형 생성자
     * 
     * @param trend 수직 변화 추세 값
     */
    VertTrendEnum(int trend) {
        this.trend = trend;
    }

    /**
     * 수직 변화 추세 값을 반환합니다.
     * 
     * @return 수직 변화 추세 값
     */
    @JsonValue
    public int getTrend() {
        return trend;
    }

    /**
     * 정수 값으로 수직 변화 추세를 찾습니다.
     * 
     * @param trend 찾을 수직 변화 추세 값
     * @return 찾은 수직 변화 추세 열거형
     * @throws CloudSDKException 해당하는 수직 변화 추세를 찾을 수 없는 경우
     */
    @JsonCreator
    public static VertTrendEnum find(int trend) {
        return Arrays.stream(values()).filter(trendEnum -> trendEnum.trend == trend).findAny()
            .orElseThrow(() -> new CloudSDKException(VertTrendEnum.class, trend));
    }

}
