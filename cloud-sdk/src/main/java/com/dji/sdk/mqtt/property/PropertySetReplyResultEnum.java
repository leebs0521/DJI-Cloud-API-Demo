package com.dji.sdk.mqtt.property;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * MQTT 프로퍼티 설정 응답 결과 열거형
 * 프로퍼티 설정 요청에 대한 응답 결과를 정의
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/28
 */
public enum PropertySetReplyResultEnum {

    /** 성공 */
    SUCCESS(0),

    /** 실패 */
    FAILED(1),

    /** 타임아웃 */
    TIMEOUT(2),

    /** 알 수 없는 결과 */
    UNKNOWN(-1);

    /** 결과 코드 */
    private final int result;

    /**
     * PropertySetReplyResultEnum 생성자
     * 
     * @param result 결과 코드
     */
    PropertySetReplyResultEnum(int result) {
        this.result = result;
    }

    /**
     * 결과 코드를 반환합니다.
     * @return 결과 코드
     */
    @JsonValue
    public int getResult() {
        return result;
    }

    /**
     * 결과 코드로 해당하는 PropertySetReplyResultEnum을 찾습니다.
     * 
     * @param result 결과 코드
     * @return 해당하는 PropertySetReplyResultEnum, 없으면 UNKNOWN
     */
    @JsonCreator
    public static PropertySetReplyResultEnum find(int result) {
        return Arrays.stream(values()).filter(resultEnum -> resultEnum.result == result).findAny().orElse(UNKNOWN);
    }
}
