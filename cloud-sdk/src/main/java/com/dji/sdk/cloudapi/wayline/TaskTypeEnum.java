package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 작업 타입 열거형
 * 
 * 이 열거형은 웨이라인 비행 작업의 실행 타입을 정의합니다.
 * 즉시 실행, 예약 실행, 조건부 실행 세 가지 타입을 제공하여
 * 다양한 비행 작업 시나리오를 지원합니다.
 * 
 * 주요 구성 요소:
 * - IMMEDIATE: 즉시 실행
 * - TIMED: 예약 실행
 * - CONDITIONAL: 조건부 실행
 * 
 * 이 열거형은 웨이라인 작업의 실행 방식을
 * 결정하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/14
 */
public enum TaskTypeEnum {

    /**
     * 즉시 실행
     * 
     * 작업이 생성되면 즉시 실행됩니다.
     * 별도의 시간 설정이나 조건 없이 바로 시작합니다.
     */
    IMMEDIATE(0),

    /**
     * 예약 실행
     * 
     * 지정된 시간에 작업이 실행됩니다.
     * 미리 설정된 시간에 맞춰 자동으로 시작합니다.
     */
    TIMED(1),

    /**
     * 조건부 실행
     * 
     * 특정 조건이 만족될 때 작업이 실행됩니다.
     * 배터리, 날씨, 위치 등의 조건을 확인하여 실행합니다.
     */
    CONDITIONAL(2);

    /**
     * 작업 타입 값
     * 
     * 각 작업 타입을 구분하는 정수 값입니다.
     */
    private final int type;

    /**
     * 작업 타입 열거형 생성자
     * 
     * @param type 작업 타입 값
     */
    TaskTypeEnum(int type) {
        this.type = type;
    }

    /**
     * 작업 타입 값을 반환합니다.
     * 
     * @return 작업 타입 값
     */
    @JsonValue
    public int getType() {
        return this.type;
    }

    /**
     * 작업 타입 값으로 작업 타입을 찾습니다.
     * 
     * 주어진 타입 값에 해당하는 열거형을 반환합니다.
     * 해당하는 타입이 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param type 찾을 작업 타입 값
     * @return 해당하는 TaskTypeEnum 열거형
     * @throws CloudSDKException 해당하는 타입이 없을 경우
     */
    @JsonCreator
    public static TaskTypeEnum find(int type) {
        return Arrays.stream(values()).filter(typeEnum ->  typeEnum.type == type).findAny()
                .orElseThrow(() -> new CloudSDKException(TaskTypeEnum.class, type));
    }
}
