package com.dji.sample.control.model.enums;

import lombok.Getter;

/**
 * MQTT ACL 접근 열거형 클래스
 * 
 * MQTT ACL(Access Control List)의 접근 권한을 정의하는 열거형입니다.
 * 구독, 발행, 전체 권한을 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/1/13
 */
@Getter
public enum MqttAclAccessEnum {

    /** 구독 권한 */
    SUB(1),

    /** 발행 권한 */
    PUB(2),

    /** 전체 권한 */
    ALL(3);

    /** 접근 권한 값 */
    int value;

    /**
     * MQTT ACL 접근 열거형 생성자
     * 
     * @param value 접근 권한 값
     */
    MqttAclAccessEnum(int value) {
        this.value = value;
    }
}
