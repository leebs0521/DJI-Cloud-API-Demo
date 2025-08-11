package com.dji.sample.control.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 드론 권한 열거형 클래스
 * 
 * 드론의 제어 권한을 정의하는 열거형입니다.
 * 비행 권한과 페이로드 권한을 포함합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/1
 */
public enum DroneAuthorityEnum {

    /** 비행 권한 */
    FLIGHT(1), 
    
    /** 페이로드 권한 */
    PAYLOAD(2);

    /** 권한 값 */
    int val;

    /**
     * 드론 권한 열거형 생성자
     * 
     * @param val 권한 값
     */
    DroneAuthorityEnum(int val) {
        this.val = val;
    }

    /**
     * JSON 직렬화를 위한 값 반환
     * 
     * @return 권한 값
     */
    @JsonValue
    public int getVal() {
        return val;
    }

}
