package com.dji.sdk.cloudapi.property;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 무음 모드 열거형
 * 
 * 이 열거형은 디바이스의 알림음 모드를 정의합니다.
 * 링 모드와 무음 모드를 구분하여 사용자 환경에 맞는 알림 설정을 제공합니다.
 * 
 * 주요 구성 요소:
 * - RING(0): 링 모드 (알림음 활성화)
 * - SILENT(1): 무음 모드 (알림음 비활성화)
 * 
 * 이 열거형은 디바이스의 알림음 설정을 관리하는 데 사용됩니다.
 * 사용자의 환경이나 상황에 따라 적절한 알림 모드를 선택할 수 있습니다.
 * 
 * @author sean
 * @date 2023/12/12
 * @version 1.9
 */
public enum SilentModeEnum {

    /**
     * 링 모드
     * 
     * 디바이스의 알림음이 활성화된 상태입니다.
     * 모든 알림과 경고음이 정상적으로 재생됩니다.
     */
    RING(0),

    /**
     * 무음 모드
     * 
     * 디바이스의 알림음이 비활성화된 상태입니다.
     * 알림과 경고음이 재생되지 않아 조용한 환경에서 사용하기 적합합니다.
     */
    SILENT(1);

    /**
     * 모드 값
     * 
     * 각 모드를 구분하는 정수 값입니다.
     */
    private final int mode;

    SilentModeEnum(int mode) {
        this.mode = mode;
    }

    /**
     * 모드 값을 반환합니다.
     * 
     * @return 모드 값 (정수)
     */
    @JsonValue
    public int getMode() {
        return mode;
    }

    /**
     * 모드 값으로 열거형을 찾습니다.
     * 
     * 주어진 모드 값에 해당하는 열거형을 반환합니다.
     * 해당하는 모드가 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param mode 찾을 모드 값
     * @return 해당하는 SilentModeEnum 열거형
     * @throws CloudSDKException 해당하는 모드가 없을 경우
     */
    @JsonCreator
    public static SilentModeEnum find(int mode) {
        return Arrays.stream(values()).filter(modeEnum -> modeEnum.mode == mode).findAny()
            .orElseThrow(() -> new CloudSDKException(SilentModeEnum.class, mode));
    }

}
