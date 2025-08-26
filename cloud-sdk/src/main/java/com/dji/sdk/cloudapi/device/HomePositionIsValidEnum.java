package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 홈 포지션 유효성 상태 열거형 클래스
 * 
 * 이 클래스는 드론의 홈 포지션(귀환 지점)의 유효성 상태를 정의합니다.
 * 위도/경도와 헤딩(방향) 정보의 유효성 여부를 나타냅니다.
 * 
 * @author sean 
 * @version 1.7
 * @date 2023/6/25
 */
public enum HomePositionIsValidEnum {

    /**
     * 위도/경도, 헤딩 모두 유효하지 않음 (상태값: 0)
     */
    INVALID(0),

    /**
     * 위도/경도, 헤딩 모두 유효함 (상태값: 1)
     */
    VALID(1),

    /**
     * 헤딩만 유효하고 위도/경도는 유효하지 않음 (상태값: 2)
     */
    HEADING_VALID_LATITUDE_LONGITUDE_INVALID(2),

    /**
     * 위도/경도만 유효하고 헤딩은 유효하지 않음 (상태값: 3)
     */
    LATITUDE_LONGITUDE_VALID_HEADING_INVALID(3),
    ;
    
    /**
     * 유효성 상태 정수값
     */
    private final int state;

    /**
     * 홈 포지션 유효성 상태 열거형 생성자
     * 
     * @param value 유효성 상태 정수값
     */
    HomePositionIsValidEnum(int value) {
        this.state = value;
    }

    /**
     * 유효성 상태 정수값을 반환합니다.
     * 
     * @return 유효성 상태 정수값
     */
    @JsonValue
    public int getState() {
        return state;
    }
    
    /**
     * 정수값으로 홈 포지션 유효성 상태를 찾습니다.
     * 
     * @param state 찾을 유효성 상태 정수값
     * @return 찾은 홈 포지션 유효성 상태 열거형
     * @throws CloudSDKException 해당하는 상태를 찾을 수 없는 경우
     */
    @JsonCreator
    public static HomePositionIsValidEnum find(int state) {
        return Arrays.stream(values()).filter(valid -> valid.state == state).findAny()
                .orElseThrow(() -> new CloudSDKException(HomePositionIsValidEnum.class, state));
    }
}
