package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 배터리 인덱스 열거형 클래스
 * 
 * 이 클래스는 배터리의 위치를 정의합니다.
 * 좌측, 우측 등의 배터리 위치를 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public enum BatteryIndexEnum {

    /**
     * 좌측 배터리 (인덱스값: 0)
     */
    LEFT(0),

    /**
     * 우측 배터리 (인덱스값: 1)
     */
    RIGHT(1);

    /**
     * 배터리 인덱스 정수값
     */
    private final int index;

    /**
     * 배터리 인덱스 열거형 생성자
     * 
     * @param index 배터리 인덱스 정수값
     */
    BatteryIndexEnum(int index) {
        this.index = index;
    }

    /**
     * 배터리 인덱스 정수값을 반환합니다.
     * 
     * @return 배터리 인덱스 정수값
     */
    @JsonValue
    public int getIndex() {
        return index;
    }

    /**
     * 정수값으로 배터리 인덱스를 찾습니다.
     * 
     * @param index 찾을 배터리 인덱스 정수값
     * @return 찾은 배터리 인덱스 열거형
     * @throws CloudSDKException 해당하는 인덱스를 찾을 수 없는 경우
     */
    @JsonCreator
    public static BatteryIndexEnum find(int index) {
        return Arrays.stream(values()).filter(indexEnum -> indexEnum.index == index).findAny()
            .orElseThrow(() -> new CloudSDKException(BatteryIndexEnum.class, index));
    }

}
