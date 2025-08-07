package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 동글 타입 열거형 클래스
 * 
 * 이 클래스는 동글(무선 통신 모듈)의 타입을 정의합니다.
 * 구형 동글, eSIM 지원 동글 등의 타입을 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public enum DongleTypeEnum {

    /**
     * 구형 동글 (타입값: 6)
     */
    OLD_DONGLE(6),

    /**
     * eSIM 지원 동글 (타입값: 10)
     */
    SUPPORTED_ESIM(10),

    ;

    /**
     * 동글 타입 정수값
     */
    private final int type;

    /**
     * 동글 타입 열거형 생성자
     * 
     * @param type 동글 타입 정수값
     */
    DongleTypeEnum(int type) {
        this.type = type;
    }

    /**
     * 동글 타입 정수값을 반환합니다.
     * 
     * @return 동글 타입 정수값
     */
    @JsonValue
    public int getType() {
        return type;
    }

    /**
     * 정수값으로 동글 타입을 찾습니다.
     * 
     * @param type 찾을 동글 타입 정수값
     * @return 찾은 동글 타입 열거형
     * @throws CloudSDKException 해당하는 타입을 찾을 수 없는 경우
     */
    @JsonCreator
    public static DongleTypeEnum find(int type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type == type).findAny()
            .orElseThrow(() -> new CloudSDKException(DongleTypeEnum.class, type));
    }

}
