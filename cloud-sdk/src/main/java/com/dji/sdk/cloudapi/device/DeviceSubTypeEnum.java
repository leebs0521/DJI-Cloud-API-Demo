package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;

/**
 * 디바이스 서브 타입 열거형 클래스
 * 
 * 이 클래스는 디바이스의 서브 타입을 정의합니다.
 * 다양한 디바이스 서브 타입을 구분하여 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/26
 */
@Schema(description = "device subType", enumAsRef = true)
public enum DeviceSubTypeEnum {

    /**
     * 서브 타입 0 (값: 0)
     */
    ZERO(0),

    /**
     * 서브 타입 1 (값: 1)
     */
    ONE(1),

    /**
     * 서브 타입 2 (값: 2)
     */
    TWO(2),

    /**
     * 서브 타입 65535 (값: 65535)
     */
    _65535(65535);

    /**
     * 디바이스 서브 타입 정수값
     */
    private final int subType;

    /**
     * 디바이스 서브 타입 열거형 생성자
     * 
     * @param subType 디바이스 서브 타입 정수값
     */
    DeviceSubTypeEnum(int subType) {
        this.subType = subType;
    }

    /**
     * 디바이스 서브 타입 정수값을 반환합니다.
     * 
     * @return 디바이스 서브 타입 정수값
     */
    @JsonValue
    public int getSubType() {
        return subType;
    }

    /**
     * 정수값으로 디바이스 서브 타입을 찾습니다.
     * 
     * @param subType 찾을 디바이스 서브 타입 정수값
     * @return 찾은 디바이스 서브 타입 열거형
     * @throws CloudSDKException 해당하는 서브 타입을 찾을 수 없는 경우
     */
    @JsonCreator
    public static DeviceSubTypeEnum find(int subType) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.subType == subType).findAny()
                .orElseThrow(() -> new CloudSDKException(DeviceSubTypeEnum.class, subType));
    }
}
