package com.dji.sample.control.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 카메라 모드 열거형 클래스
 * 
 * 카메라의 모드를 정의하는 열거형입니다.
 * 사진 촬영과 비디오 녹화 모드를 지원합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/3
 */
public enum CameraModeEnum {

    /** 사진 촬영 모드 */
    PHOTO, 
    
    /** 비디오 녹화 모드 */
    VIDEO;

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
     * @param val 열거형의 순서 값
     * @return 해당하는 카메라 모드 열거형
     */
    @JsonCreator
    public static CameraModeEnum find(int val) {
        return Arrays.stream(values()).filter(modeEnum -> modeEnum.ordinal() == val).findAny().get();
    }
}
