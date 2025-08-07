package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 링크 작업 모드 열거형 클래스
 * 
 * 이 클래스는 무선 통신 링크의 작업 모드를 정의합니다.
 * SDR 전용, SDR와 4G 조합 등의 모드를 나타냅니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/25
 */
public enum LinkWorkModeEnum {

    /**
     * SDR 전용 (모드값: 0)
     */
    SDR_ONLY(0),

    /**
     * SDR와 4G 조합 (모드값: 1)
     */
    SDR_WITH_4G(1);

    /**
     * 링크 작업 모드 정수값
     */
    private final int mode;

    /**
     * 링크 작업 모드 열거형 생성자
     * 
     * @param mode 링크 작업 모드 정수값
     */
    LinkWorkModeEnum(int mode) {
        this.mode = mode;
    }

    /**
     * 링크 작업 모드 정수값을 반환합니다.
     * 
     * @return 링크 작업 모드 정수값
     */
    @JsonValue
    public int getMode() {
        return mode;
    }

    /**
     * 정수값으로 링크 작업 모드를 찾습니다.
     * 
     * @param mode 찾을 링크 작업 모드 정수값
     * @return 찾은 링크 작업 모드 열거형
     * @throws CloudSDKException 해당하는 모드를 찾을 수 없는 경우
     */
    @JsonCreator
    public static LinkWorkModeEnum find(int mode) {
        return Arrays.stream(LinkWorkModeEnum.values()).filter(modeEnum -> modeEnum.mode == mode).findAny()
                .orElseThrow(() -> new CloudSDKException(LinkWorkModeEnum.class, mode));
    }
}
