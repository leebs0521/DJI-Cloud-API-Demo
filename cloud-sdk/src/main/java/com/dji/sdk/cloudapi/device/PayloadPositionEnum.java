package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 페이로드 위치 열거형 클래스
 * 
 * 이 클래스는 페이로드(카메라, 센서 등)의 장착 위치를 정의합니다.
 * 전면 좌측, 전면 우측, 상단, FPV 등의 위치를 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/25
 */
public enum PayloadPositionEnum {

    /**
     * 전면 좌측 (위치값: 0)
     */
    FRONT_LEFT(0),

    /**
     * 전면 우측 (위치값: 1)
     */
    FRONT_RIGHT(1),

    /**
     * 상단 (위치값: 2)
     */
    TOP(2),

    /**
     * FPV (위치값: 7)
     */
    FPV(7);

    /**
     * 위치 정수값
     */
    private final int position;

    /**
     * 페이로드 위치 열거형 생성자
     * 
     * @param position 위치 정수값
     */
    PayloadPositionEnum(int position) {
        this.position = position;
    }

    /**
     * 위치 정수값을 반환합니다.
     * 
     * @return 위치 정수값
     */
    @JsonValue
    public int getPosition() {
        return position;
    }

    /**
     * 정수값으로 페이로드 위치를 찾습니다.
     * 
     * @param position 찾을 위치 정수값
     * @return 찾은 페이로드 위치 열거형
     * @throws CloudSDKException 해당하는 위치를 찾을 수 없는 경우
     */
    @JsonCreator
    public static PayloadPositionEnum find(int position) {
        return Arrays.stream(values()).filter(positionEnum -> positionEnum.position == position).findAny()
                .orElseThrow(() -> new CloudSDKException(PayloadPositionEnum.class, position));
    }
}
