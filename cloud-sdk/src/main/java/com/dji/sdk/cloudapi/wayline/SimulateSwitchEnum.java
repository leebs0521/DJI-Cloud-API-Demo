package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 시뮬레이션 스위치 열거형
 * 
 * 이 열거형은 웨이라인 비행 작업의 시뮬레이션 모드
 * 활성화/비활성화 상태를 정의합니다.
 * 
 * 주요 구성 요소:
 * - DISABLE: 시뮬레이션 비활성화
 * - ENABLE: 시뮬레이션 활성화
 * 
 * 이 열거형은 실제 비행 없이 웨이라인 작업을
 * 시뮬레이션하여 테스트할지 여부를 결정하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/8/4
 */
public enum SimulateSwitchEnum {

    /**
     * 시뮬레이션 비활성화
     * 
     * 실제 비행 모드로 웨이라인 작업을 실행합니다.
     */
    DISABLE(0),

    /**
     * 시뮬레이션 활성화
     * 
     * 시뮬레이션 모드로 웨이라인 작업을 실행합니다.
     * 실제 비행 없이 작업을 테스트할 수 있습니다.
     */
    ENABLE(1);

    /**
     * 시뮬레이션 상태 값
     * 
     * 각 시뮬레이션 상태를 구분하는 정수 값입니다.
     */
    private final int state;

    /**
     * 시뮬레이션 스위치 열거형 생성자
     * 
     * @param state 시뮬레이션 상태 값
     */
    SimulateSwitchEnum(int state) {
        this.state = state;
    }

    /**
     * 시뮬레이션 상태 값을 반환합니다.
     * 
     * @return 시뮬레이션 상태 값
     */
    @JsonValue
    public int getState() {
        return state;
    }

    /**
     * 시뮬레이션 상태 값으로 시뮬레이션 스위치를 찾습니다.
     * 
     * 주어진 상태 값에 해당하는 열거형을 반환합니다.
     * 해당하는 상태가 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param state 찾을 시뮬레이션 상태 값
     * @return 해당하는 SimulateSwitchEnum 열거형
     * @throws CloudSDKException 해당하는 상태가 없을 경우
     */
    @JsonCreator
    public static SimulateSwitchEnum find(int state) {
        return Arrays.stream(values()).filter(stateEnum -> stateEnum.state == state).findAny()
            .orElseThrow(() -> new CloudSDKException(SimulateSwitchEnum.class, state));
    }

}
