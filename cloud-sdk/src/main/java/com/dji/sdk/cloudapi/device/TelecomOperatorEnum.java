package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 통신사 열거형 클래스
 * 
 * 이 클래스는 중국의 주요 통신사를 정의합니다.
 * SIM 카드나 eSIM의 통신사 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public enum TelecomOperatorEnum {

    /**
     * 알 수 없는 통신사 (정수값: 0)
     */
    UNKNOWN(0),

    /**
     * 중국 모바일 (정수값: 1)
     */
    CHINA_MOBILE(1),

    /**
     * 중국 유니콤 (정수값: 2)
     */
    CHINA_UNICOM(2),

    /**
     * 중국 텔레콤 (정수값: 3)
     */
    CHINA_TELECOM(3),

    ;

    /**
     * 통신사 정수값
     */
    private final int operator;

    /**
     * 통신사 열거형 생성자
     * 
     * @param operator 통신사 정수값
     */
    TelecomOperatorEnum(int operator) {
        this.operator = operator;
    }

    /**
     * 통신사 정수값을 반환합니다.
     * 
     * @return 통신사 정수값
     */
    @JsonValue
    public int getOperator() {
        return operator;
    }

    /**
     * 정수값으로 통신사를 찾습니다.
     * 
     * @param operator 찾을 통신사 정수값
     * @return 찾은 통신사 열거형
     * @throws CloudSDKException 해당하는 통신사를 찾을 수 없는 경우
     */
    @JsonCreator
    public static TelecomOperatorEnum find(int operator) {
        return Arrays.stream(values()).filter(operatorEnum -> operatorEnum.operator == operator).findAny()
            .orElseThrow(() -> new CloudSDKException(TelecomOperatorEnum.class, operator));
    }

}
