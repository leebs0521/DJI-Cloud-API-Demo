package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 마지막 포인트 타입 열거형
 * 
 * 이 열거형은 웨이라인 비행에서 마지막 포인트의 위치를 정의합니다.
 * 홈포인트 위에 있는지 여부에 따라 비행 종료 방식을 결정하는 데 사용됩니다.
 * 
 * 주요 구성 요소:
 * - OVER_THE_HOME_POINT: 홈포인트 위에 있음
 * - NOT_OVER_THE_HOME_POINT: 홈포인트 위에 있지 않음
 * 
 * 이 열거형은 웨이라인 비행 완료 후 드론의 위치를 확인하고
 * 적절한 착륙 방식을 선택하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/11
 */
public enum LastPointTypeEnum {

    /**
     * 홈포인트 위에 있음
     * 
     * 웨이라인의 마지막 포인트가 홈포인트 위에 있는 경우입니다.
     * 드론이 홈포인트 근처에서 비행을 완료할 수 있습니다.
     */
    OVER_THE_HOME_POINT(0),

    /**
     * 홈포인트 위에 있지 않음
     * 
     * 웨이라인의 마지막 포인트가 홈포인트 위에 있지 않은 경우입니다.
     * 드론이 홈포인트에서 멀리 떨어진 위치에서 비행을 완료합니다.
     */
    NOT_OVER_THE_HOME_POINT(1);

    /**
     * 마지막 포인트 타입 값
     * 
     * 각 마지막 포인트 타입을 구분하는 정수 값입니다.
     */
    private final int type;

    /**
     * 마지막 포인트 타입 열거형 생성자
     * 
     * @param type 마지막 포인트 타입 값
     */
    LastPointTypeEnum(int type) {
        this.type = type;
    }

    /**
     * 마지막 포인트 타입 값을 반환합니다.
     * 
     * @return 마지막 포인트 타입 값
     */
    @JsonValue
    public int getType() {
        return type;
    }

    /**
     * 마지막 포인트 타입 값으로 마지막 포인트 타입을 찾습니다.
     * 
     * 주어진 타입 값에 해당하는 열거형을 반환합니다.
     * 해당하는 타입이 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param type 찾을 마지막 포인트 타입 값
     * @return 해당하는 LastPointTypeEnum 열거형
     * @throws CloudSDKException 해당하는 타입이 없을 경우
     */
    @JsonCreator
    public static LastPointTypeEnum find(int type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type == type).findAny()
            .orElseThrow(() -> new CloudSDKException(LastPointTypeEnum.class, type));
    }

}
