package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * SIM 타입 열거형 클래스
 * 
 * 이 클래스는 SIM 카드의 타입을 정의합니다.
 * 일반 SIM과 3개 네트워크 모드를 지원하는 SIM을 구분합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public enum SimTypeEnum {

    /**
     * 알 수 없는 SIM 타입 (정수값: 0)
     */
    UNKNOWN(0),

    /**
     * 일반 SIM (정수값: 1)
     */
    ORDINARY(1),

    /**
     * 3개 네트워크 모드 지원 SIM (정수값: 2)
     */
    THREE_NETWORK_MODES(2),

    ;

    /**
     * SIM 타입 정수값
     */
    private final int type;

    /**
     * SIM 타입 열거형 생성자
     * 
     * @param type SIM 타입 정수값
     */
    SimTypeEnum(int type) {
        this.type = type;
    }

    /**
     * SIM 타입 정수값을 반환합니다.
     * 
     * @return SIM 타입 정수값
     */
    @JsonValue
    public int getType() {
        return type;
    }

    /**
     * 정수값으로 SIM 타입을 찾습니다.
     * 
     * @param type 찾을 SIM 타입 정수값
     * @return 찾은 SIM 타입 열거형
     * @throws CloudSDKException 해당하는 SIM 타입을 찾을 수 없는 경우
     */
    @JsonCreator
    public static SimTypeEnum find(int type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type == type).findAny()
            .orElseThrow(() -> new CloudSDKException(SimTypeEnum.class, type));
    }

}
