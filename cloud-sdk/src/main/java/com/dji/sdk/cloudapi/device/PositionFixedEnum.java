package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 위치 고정 상태 열거형 클래스
 * 
 * 이 클래스는 위치 고정의 상태를 정의합니다.
 * 시작 안함, 고정 중, 성공, 실패 등의 상태를 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public enum PositionFixedEnum {

    /**
     * 시작 안함 (상태값: 0)
     */
    NOT_START(0),

    /**
     * 고정 중 (상태값: 1)
     */
    FIXING(1),

    /**
     * 성공 (상태값: 2)
     */
    SUCCESSFUL(2),

    /**
     * 실패 (상태값: 3)
     */
    FAILED(3),
    ;

    /**
     * 위치 고정 상태 정수값
     */
    private final int fixed;

    /**
     * 위치 고정 상태 열거형 생성자
     * 
     * @param fixed 위치 고정 상태 정수값
     */
    PositionFixedEnum(int fixed) {
        this.fixed = fixed;
    }

    /**
     * 위치 고정 상태 정수값을 반환합니다.
     * 
     * @return 위치 고정 상태 정수값
     */
    @JsonValue
    public int getFixed() {
        return fixed;
    }

    /**
     * 정수값으로 위치 고정 상태를 찾습니다.
     * 
     * @param fixed 찾을 위치 고정 상태 정수값
     * @return 찾은 위치 고정 상태 열거형
     * @throws CloudSDKException 해당하는 상태를 찾을 수 없는 경우
     */
    @JsonCreator
    public static PositionFixedEnum find(int fixed) {
        return Arrays.stream(values()).filter(fixedEnum -> fixedEnum.fixed == fixed).findAny()
            .orElseThrow(() -> new CloudSDKException(PositionFixedEnum.class, fixed));
    }

}
