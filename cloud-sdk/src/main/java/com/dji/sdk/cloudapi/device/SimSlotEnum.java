package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * SIM 슬롯 열거형 클래스
 * 
 * 이 클래스는 SIM 카드 슬롯의 타입을 정의합니다.
 * 물리적 SIM 카드와 eSIM(임베디드 SIM)을 구분하여 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public enum SimSlotEnum {

    /**
     * 알 수 없는 슬롯 (정수값: 0)
     */
    UNKNOWN(0),

    /**
     * 물리적 SIM 카드 슬롯 (정수값: 1)
     */
    SIM(1),

    /**
     * eSIM(임베디드 SIM) 슬롯 (정수값: 2)
     */
    ESIM(2),

    ;

    /**
     * 슬롯 정수값
     */
    private final int slot;

    /**
     * SIM 슬롯 열거형 생성자
     * 
     * @param slot 슬롯 정수값
     */
    SimSlotEnum(int slot) {
        this.slot = slot;
    }

    /**
     * 슬롯 정수값을 반환합니다.
     * 
     * @return 슬롯 정수값
     */
    @JsonValue
    public int getSlot() {
        return slot;
    }

    /**
     * 정수값으로 SIM 슬롯을 찾습니다.
     * 
     * @param slot 찾을 슬롯 정수값
     * @return 찾은 SIM 슬롯 열거형
     * @throws CloudSDKException 해당하는 슬롯을 찾을 수 없는 경우
     */
    @JsonCreator
    public static SimSlotEnum find(int slot) {
        return Arrays.stream(values()).filter(slotEnum -> slotEnum.slot == slot).findAny()
            .orElseThrow(() -> new CloudSDKException(SimSlotEnum.class, slot));
    }

}
