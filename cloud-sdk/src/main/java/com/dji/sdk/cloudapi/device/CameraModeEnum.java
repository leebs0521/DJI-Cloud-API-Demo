package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 카메라 모드 열거형 클래스
 * 
 * 이 클래스는 카메라의 모드를 정의합니다.
 * 사진, 비디오, 저조도 지능형, 파노라마, 미지원 등의 모드를 나타냅니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/3
 */
public enum CameraModeEnum {

    /**
     * 사진 (모드값: 0)
     */
    PHOTO(0),

    /**
     * 비디오 (모드값: 1)
     */
    VIDEO(1),

    /**
     * 저조도 지능형 (모드값: 2)
     */
    LOW_LIGHT_INTELLIGENCE(2),

    /**
     * 파노라마 (모드값: 3)
     */
    PANORAMA(3),

    /**
     * 미지원 (모드값: -1)
     */
    UNSUPPORTED(-1),
    
    ;

    /**
     * 카메라 모드 정수값
     */
    private final int mode;

    /**
     * 카메라 모드 열거형 생성자
     * 
     * @param mode 카메라 모드 정수값
     */
    CameraModeEnum(int mode) {
        this.mode = mode;
    }

    /**
     * 카메라 모드 정수값을 반환합니다.
     * 
     * @return 카메라 모드 정수값
     */
    @JsonValue
    public int getMode() {
        return mode;
    }

    /**
     * 정수값으로 카메라 모드를 찾습니다.
     * 
     * @param mode 찾을 카메라 모드 정수값
     * @return 찾은 카메라 모드 열거형
     * @throws CloudSDKException 해당하는 모드를 찾을 수 없는 경우
     */
    @JsonCreator
    public static CameraModeEnum find(int mode) {
        return Arrays.stream(values()).filter(modeEnum -> modeEnum.mode == mode).findAny()
                .orElseThrow(() -> new CloudSDKException(CameraModeEnum.class, mode));
    }
}
