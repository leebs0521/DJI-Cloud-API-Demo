package com.dji.sample.control.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 짐벌 리셋 모드 열거형 클래스
 * 
 * 짐벌의 리셋 모드를 정의하는 열거형입니다.
 * 다양한 짐벌 리셋 동작을 지원합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/13
 */
public enum GimbalResetModeEnum {

    /** 중앙으로 리셋 */
    RECENTER, 
    
    /** 아래로 리셋 */
    DOWN, 
    
    /** 중앙 팬 리셋 */
    RECENTER_PAN, 
    
    /** 피치 다운 리셋 */
    PITCH_DOWN;

    /**
     * JSON 직렬화를 위한 값 반환
     * 
     * @return 열거형의 순서 값
     */
    @JsonValue
    public int getVal() {
        return ordinal();
    }

    /**
     * JSON 역직렬화를 위한 열거형 찾기
     * 
     * @param value 열거형의 순서 값
     * @return 해당하는 짐벌 리셋 모드 열거형
     */
    @JsonCreator
    public static GimbalResetModeEnum find(int value) {
        return Arrays.stream(values()).filter(resetModeEnum -> resetModeEnum.ordinal() == value).findAny().get();
    }
}
