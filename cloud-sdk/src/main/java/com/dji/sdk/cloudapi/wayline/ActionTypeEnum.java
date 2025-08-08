package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 액션 타입 열거형
 * 
 * 이 열거형은 웨이라인 비행에서 수행할 액션의 타입을 정의합니다.
 * 다양한 비행 액션을 구분하여 적절한 처리 로직을 적용할 수 있습니다.
 * 
 * 주요 구성 요소:
 * - SPOT_CHECK: 스팟 체크
 * 
 * 이 열거형은 웨이라인 비행에서 수행할 특별한 액션을
 * 식별하고 관리하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/23
 */
public enum ActionTypeEnum {

    /**
     * 스팟 체크
     * 
     * 특정 지점에서 점검 작업을 수행하는 액션 타입입니다.
     * AI 기반의 자동 점검 기능을 활성화하여
     * 특정 위치에서 상세한 검사를 수행합니다.
     */
    SPOT_CHECK(1);

    /**
     * 액션 타입 값
     * 
     * 각 액션 타입을 구분하는 정수 값입니다.
     */
    private final int type;

    /**
     * 액션 타입 열거형 생성자
     * 
     * @param type 액션 타입 값
     */
    ActionTypeEnum(int type) {
        this.type = type;
    }

    /**
     * 액션 타입 값을 반환합니다.
     * 
     * @return 액션 타입 값
     */
    @JsonValue
    public int getType() {
        return type;
    }

    /**
     * 액션 타입 값으로 액션 타입을 찾습니다.
     * 
     * 주어진 타입 값에 해당하는 열거형을 반환합니다.
     * 해당하는 타입이 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param type 찾을 액션 타입 값
     * @return 해당하는 ActionTypeEnum 열거형
     * @throws CloudSDKException 해당하는 타입이 없을 경우
     */
    @JsonCreator
    public static ActionTypeEnum find(int type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type == type).findAny()
            .orElseThrow(() -> new CloudSDKException(ActionTypeEnum.class, type));
    }

}
