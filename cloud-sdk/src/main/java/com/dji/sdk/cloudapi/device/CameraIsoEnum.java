package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 카메라 ISO 열거형 클래스
 * 
 * 이 클래스는 카메라의 ISO 설정을 정의합니다.
 * 자동, 고감도 자동, 고정값 등의 ISO 설정을 나타냅니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public enum CameraIsoEnum {

    /**
     * 자동 (ISO값: 0)
     */
    AUTO(0),

    /**
     * 고감도 자동 (ISO값: 1)
     */
    AUTO_HIGH_SENSE(1),

    /**
     * ISO 50 (ISO값: 2)
     */
    _50(2),

    /**
     * ISO 100 (ISO값: 3)
     */
    _100(3),

    /**
     * ISO 200 (ISO값: 4)
     */
    _200(4),

    /**
     * ISO 400 (ISO값: 5)
     */
    _400(5),

    /**
     * ISO 800 (ISO값: 6)
     */
    _800(6),

    /**
     * ISO 1600 (ISO값: 7)
     */
    _1600(7),

    /**
     * ISO 3200 (ISO값: 8)
     */
    _3200(8),

    /**
     * ISO 6400 (ISO값: 9)
     */
    _6400(9),

    /**
     * ISO 12800 (ISO값: 10)
     */
    _12800(10),

    /**
     * ISO 25600 (ISO값: 11)
     */
    _25600(11),

    /**
     * 고정 (ISO값: 255)
     */
    FIXED(255),

    ;

    /**
     * 카메라 ISO 정수값
     */
    private final int iso;

    /**
     * 카메라 ISO 열거형 생성자
     * 
     * @param iso 카메라 ISO 정수값
     */
    CameraIsoEnum(int iso) {
        this.iso = iso;
    }

    /**
     * 카메라 ISO 정수값을 반환합니다.
     * 
     * @return 카메라 ISO 정수값
     */
    @JsonValue
    public int getIso() {
        return iso;
    }

    /**
     * 정수값으로 카메라 ISO를 찾습니다.
     * 
     * @param iso 찾을 카메라 ISO 정수값
     * @return 찾은 카메라 ISO 열거형
     * @throws CloudSDKException 해당하는 ISO를 찾을 수 없는 경우
     */
    @JsonCreator
    public static CameraIsoEnum find(int iso) {
        return Arrays.stream(values()).filter(isoEnum -> isoEnum.iso == iso).findAny()
            .orElseThrow(() -> new CloudSDKException(CameraIsoEnum.class, iso));
    }

}
