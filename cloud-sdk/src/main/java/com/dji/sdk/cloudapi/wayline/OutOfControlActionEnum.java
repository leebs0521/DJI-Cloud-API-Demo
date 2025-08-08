package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 제어 상실 시 액션 열거형
 * 
 * 이 열거형은 드론이 제어를 상실했을 때 수행할 액션을 정의합니다.
 * 통신 끊김, 신호 손실 등의 상황에서 드론의 안전한 동작을 보장합니다.
 * 
 * 주요 구성 요소:
 * - RETURN_TO_HOME: 홈포인트로 귀환
 * - HOVERING: 호버링
 * - LANDING: 착륙
 * 
 * 이 열거형은 드론의 안전을 최우선으로 하여
 * 예상치 못한 상황에서도 안전한 대응이 가능하도록 합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/6
 */
public enum OutOfControlActionEnum {

    /**
     * 홈포인트로 귀환
     * 
     * 드론이 제어를 상실했을 때 홈포인트로 자동 귀환합니다.
     * 가장 안전한 대응 방법으로, 미리 설정된 홈포인트로 돌아갑니다.
     */
    RETURN_TO_HOME(0),

    /**
     * 호버링
     * 
     * 드론이 제어를 상실했을 때 현재 위치에서 호버링합니다.
     * 통신이 복구될 때까지 현재 높이를 유지합니다.
     */
    HOVERING(1),

    /**
     * 착륙
     * 
     * 드론이 제어를 상실했을 때 현재 위치에서 즉시 착륙합니다.
     * 긴급 상황에서 가장 빠른 안전 확보 방법입니다.
     */
    LANDING(2);

    /**
     * 제어 상실 시 액션 값
     * 
     * 각 제어 상실 시 액션을 구분하는 정수 값입니다.
     */
    private final int action;

    /**
     * 제어 상실 시 액션 열거형 생성자
     * 
     * @param action 제어 상실 시 액션 값
     */
    OutOfControlActionEnum(int action) {
        this.action = action;
    }

    /**
     * 제어 상실 시 액션 값을 반환합니다.
     * 
     * @return 제어 상실 시 액션 값
     */
    @JsonValue
    public int getAction() {
        return action;
    }

    /**
     * 제어 상실 시 액션 값으로 제어 상실 시 액션을 찾습니다.
     * 
     * 주어진 액션 값에 해당하는 열거형을 반환합니다.
     * 해당하는 액션이 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param action 찾을 제어 상실 시 액션 값
     * @return 해당하는 OutOfControlActionEnum 열거형
     * @throws CloudSDKException 해당하는 액션이 없을 경우
     */
    @JsonCreator
    public static OutOfControlActionEnum find(int action) {
        return Arrays.stream(values()).filter(actionEnum -> actionEnum.action == action).findAny()
                .orElseThrow(() -> new CloudSDKException(OutOfControlActionEnum.class, action));
    }
}
