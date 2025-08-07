package com.dji.sdk.cloudapi.firmware;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 펌웨어 업그레이드 타입 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 펌웨어 업그레이드의 타입을 정의합니다.
 * 일반 업그레이드와 일관성 업그레이드 두 가지 타입을 포함합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/8/15
 */
public enum FirmwareUpgradeTypeEnum {

    /**
     * 일반 업그레이드
     * 일반적인 펌웨어 업그레이드
     */
    NORMAL_UPGRADE(2),

    /**
     * 일관성 업그레이드
     * 일관성 업그레이드가 필요한 경우
     */
    CONSISTENT_UPGRADE(3);

    /**
     * 업그레이드 타입 값
     */
    private final int type;

    /**
     * 펌웨어 업그레이드 타입 열거형 생성자
     * 
     * @param type 업그레이드 타입 값
     */
    FirmwareUpgradeTypeEnum(int type) {
        this.type = type;
    }

    /**
     * 업그레이드 타입 값을 반환합니다.
     * 
     * @return 업그레이드 타입 값
     */
    @JsonValue
    public int getType() {
        return type;
    }

    /**
     * 정수 값으로 펌웨어 업그레이드 타입을 찾습니다.
     * 
     * @param type 찾을 업그레이드 타입 값
     * @return 찾은 펌웨어 업그레이드 타입 열거형
     * @throws CloudSDKException 해당하는 업그레이드 타입을 찾을 수 없는 경우
     */
    @JsonCreator
    public static FirmwareUpgradeTypeEnum find(int type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type == type).findAny()
                .orElseThrow(() -> new CloudSDKException(FirmwareUpgradeTypeEnum.class, type));

    }

}
