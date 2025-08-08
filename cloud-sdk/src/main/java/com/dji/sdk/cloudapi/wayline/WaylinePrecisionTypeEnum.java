package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 웨이라인 정밀도 타입 열거형
 * 
 * 이 열거형은 웨이라인 비행에서 사용되는 위치 정밀도 타입을 정의합니다.
 * GPS와 RTK 두 가지 정밀도 타입을 지원하여 다양한 비행 환경에
 * 적합한 정밀도를 선택할 수 있습니다.
 * 
 * 주요 구성 요소:
 * - GPS: 일반 GPS 정밀도
 * - RTK: RTK(Real Time Kinematic) 고정밀도
 * 
 * 이 열거형은 웨이라인 비행의 정밀도를 설정하고
 * 위치 정확도를 제어하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/19
 */
public enum WaylinePrecisionTypeEnum {

    /**
     * GPS 정밀도
     * 
     * 일반적인 GPS 정밀도를 사용합니다.
     * 표준 GPS 신호를 사용하여 위치를 측정합니다.
     */
    GPS(0),

    /**
     * RTK 정밀도
     * 
     * RTK(Real Time Kinematic) 고정밀도를 사용합니다.
     * 실시간 키네마틱 기술을 사용하여 센티미터 수준의 정밀도를 제공합니다.
     */
    RTK(1);

    /**
     * 정밀도 타입 값
     * 
     * 각 정밀도 타입을 구분하는 정수 값입니다.
     */
    private final int type;

    /**
     * 웨이라인 정밀도 타입 열거형 생성자
     * 
     * @param type 정밀도 타입 값
     */
    WaylinePrecisionTypeEnum(int type) {
        this.type = type;
    }

    /**
     * 정밀도 타입 값을 반환합니다.
     * 
     * @return 정밀도 타입 값
     */
    @JsonValue
    public int getType() {
        return type;
    }

    /**
     * 정밀도 타입 값으로 웨이라인 정밀도 타입을 찾습니다.
     * 
     * 주어진 타입 값에 해당하는 열거형을 반환합니다.
     * 해당하는 타입이 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param type 찾을 정밀도 타입 값
     * @return 해당하는 WaylinePrecisionTypeEnum 열거형
     * @throws CloudSDKException 해당하는 타입이 없을 경우
     */
    @JsonCreator
    public static WaylinePrecisionTypeEnum find(int type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type == type).findAny()
            .orElseThrow(() -> new CloudSDKException(WaylinePrecisionTypeEnum.class, type));
    }

}
