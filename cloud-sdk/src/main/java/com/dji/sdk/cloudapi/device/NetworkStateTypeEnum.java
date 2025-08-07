package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 네트워크 상태 타입 열거형 클래스
 * 
 * 이 클래스는 네트워크 연결 타입을 정의합니다.
 * 4G, 이더넷 등의 네트워크 타입을 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public enum NetworkStateTypeEnum {

    /**
     * 4G 네트워크 (타입값: 1)
     */
    FOURTH_GENERATION(1),

    /**
     * 이더넷 (타입값: 2)
     */
    ETHERNET(2),
    ;

    /**
     * 네트워크 상태 타입 정수값
     */
    private final int type;

    /**
     * 네트워크 상태 타입 열거형 생성자
     * 
     * @param type 네트워크 상태 타입 정수값
     */
    NetworkStateTypeEnum(int type) {
        this.type = type;
    }

    /**
     * 네트워크 상태 타입 정수값을 반환합니다.
     * 
     * @return 네트워크 상태 타입 정수값
     */
    @JsonValue
    public int getType() {
        return type;
    }

    /**
     * 정수값으로 네트워크 상태 타입을 찾습니다.
     * 
     * @param type 찾을 네트워크 상태 타입 정수값
     * @return 찾은 네트워크 상태 타입 열거형
     * @throws CloudSDKException 해당하는 타입을 찾을 수 없는 경우
     */
    @JsonCreator
    public static NetworkStateTypeEnum find(int type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type == type).findAny()
            .orElseThrow(() -> new CloudSDKException(NetworkStateTypeEnum.class, type));
    }

}
