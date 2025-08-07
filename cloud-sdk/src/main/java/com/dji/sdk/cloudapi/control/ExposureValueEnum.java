package com.dji.sdk.cloudapi.control;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 노출 값 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 카메라의 노출 값(EV)을 정의합니다.
 * -5.0EV부터 +5.0EV까지의 노출 값과 고정 모드를 지원합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public enum ExposureValueEnum {

    /**
     * -5.0EV
     * 가장 어두운 노출 값
     */
    MINUS_5_DOT_0(1, "-5.0EV"),

    /**
     * -4.7EV
     */
    MINUS_4_DOT_7(2, "-4.7EV"),

    /**
     * -4.3EV
     */
    MINUS_4_DOT_3(3, "-4.3EV"),

    /**
     * -4.0EV
     */
    MINUS_4_DOT_0(4, "-4.0EV"),

    /**
     * -3.7EV
     */
    MINUS_3_DOT_7(5, "-3.7EV"),

    /**
     * -3.3EV
     */
    MINUS_3_DOT_3(6, "-3.3EV"),

    /**
     * -3.0EV
     */
    MINUS_3_DOT_0(7, "-3.0EV"),

    /**
     * -2.7EV
     */
    MINUS_2_DOT_7(8, "-2.7EV"),

    /**
     * -2.3EV
     */
    MINUS_2_DOT_3(9, "-2.3EV"),

    /**
     * -2.0EV
     */
    MINUS_2_DOT_0(10, "-2.0EV"),

    /**
     * -1.7EV
     */
    MINUS_1_DOT_7(11, "-1.7EV"),

    /**
     * -1.3EV
     */
    MINUS_1_DOT_3(12, "-1.3EV"),

    /**
     * -1.0EV
     */
    MINUS_1_DOT_0(13, "-1.0EV"),

    /**
     * -0.7EV
     */
    MINUS_0_DOT_7(14, "-0.7EV"),

    /**
     * -0.3EV
     */
    MINUS_0_DOT_3(15, "-0.3EV"),

    /**
     * 0EV
     * 중간 노출 값
     */
    _0(16, "0EV"),

    /**
     * +0.3EV
     */
    _0_DOT_3(17, "0.3EV"),

    /**
     * +0.7EV
     */
    _0_DOT_7(18, "0.7EV"),

    /**
     * +1.0EV
     */
    _1_DOT_0(19, "1.0EV"),

    /**
     * +1.3EV
     */
    _1_DOT_3(20, "1.3EV"),

    /**
     * +1.7EV
     */
    _1_DOT_7(21, "1.7EV"),

    /**
     * +2.0EV
     */
    _2_DOT_0(22, "2.0EV"),

    /**
     * +2.3EV
     */
    _2_DOT_3(23, "2.3EV"),

    /**
     * +2.7EV
     */
    _2_DOT_7(24, "2.7EV"),

    /**
     * +3.0EV
     */
    _3_DOT_0(25, "3.0EV"),

    /**
     * +3.3EV
     */
    _3_DOT_3(26, "3.3EV"),

    /**
     * +3.7EV
     */
    _3_DOT_7(27, "3.7EV"),

    /**
     * +4.0EV
     */
    _4_DOT_0(28, "4.0EV"),

    /**
     * +4.3EV
     */
    _4_DOT_3(29, "4.3EV"),

    /**
     * +4.7EV
     */
    _4_DOT_7(30, "4.7EV"),

    /**
     * +5.0EV
     * 가장 밝은 노출 값
     */
    _5_DOT_0(31, "5.0EV"),

    /**
     * 고정 모드
     * 노출 값을 고정
     */
    FIXED(255, "FIXED"),

    ;


    /**
     * 노출 값
     */
    private final int value;

    /**
     * 노출 값 설명
     */
    private final String desc;

    /**
     * 노출 값 열거형 생성자
     * 
     * @param value 노출 값
     * @param desc 노출 값 설명
     */
    ExposureValueEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 노출 값을 반환합니다.
     * 
     * @return 노출 값
     */
    @JsonValue
    public int getValue() {
        return value;
    }

    /**
     * 노출 값 설명을 반환합니다.
     * 
     * @return 노출 값 설명
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 정수 값으로 노출 값을 찾습니다.
     * 
     * @param value 찾을 노출 값
     * @return 찾은 노출 값 열거형
     * @throws CloudSDKException 해당하는 노출 값을 찾을 수 없는 경우
     */
    @JsonCreator
    public static ExposureValueEnum find(int value) {
        return Arrays.stream(values()).filter(valueEnum -> valueEnum.value == value).findAny()
            .orElseThrow(() -> new CloudSDKException(ExposureValueEnum.class, value));
    }

}
