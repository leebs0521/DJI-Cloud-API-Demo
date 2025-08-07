package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;

/**
 * 디바이스 타입 열거형 클래스
 * 
 * 이 클래스는 DJI 디바이스의 구체적인 타입을 정의합니다.
 * 각 타입은 정수값으로 표현되며, 디바이스의 세부 분류를 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/26
 */
@Schema(description = "device type", enumAsRef = true)
public enum DeviceTypeEnum {

    /**
     * 드론 타입들
     */
    /** M350 드론 (타입값: 89) */
    M350(89),
    /** M300 드론 (타입값: 60) */
    M300(60),
    /** M30 또는 M3T 카메라 (타입값: 67) */
    M30_OR_M3T_CAMERA(67),
    /** M3E 드론 (타입값: 77) */
    M3E(77),
    /** M3D 드론 (타입값: 91) */
    M3D(91),

    /**
     * 페이로드 타입들
     */
    /** Z30 카메라 (타입값: 20) */
    Z30(20),
    /** XT2 열화상 카메라 (타입값: 26) */
    XT2(26),
    /** FPV 카메라 (타입값: 39) */
    FPV(39),
    /** XTS 열화상 카메라 (타입값: 41) */
    XTS(41),
    /** H20 카메라 (타입값: 42) */
    H20(42),
    /** H20T 열화상 카메라 (타입값: 43) */
    H20T(43),
    /** P1 카메라 (타입값: 50) */
    P1(50),
    /** M30 카메라 (타입값: 52) */
    M30_CAMERA(52),
    /** M30T 카메라 (타입값: 53) */
    M30T_CAMERA(53),
    /** H20N 카메라 (타입값: 61) */
    H20N(61),
    /** 도크 카메라 (타입값: 165) */
    DOCK_CAMERA(165),
    /** L1 라이다 (타입값: 90742) */
    L1(90742),
    /** M3E 카메라 (타입값: 66) */
    M3E_CAMERA(66),
    /** M3M 카메라 (타입값: 68) */
    M3M_CAMERA(68),
    /** M3D 카메라 (타입값: 80) */
    M3D_CAMERA(80),
    /** M3TD 카메라 (타입값: 81) */
    M3TD_CAMERA(81),

    /**
     * 리모트 컨트롤 타입들
     */
    /** RC 리모트 컨트롤 (타입값: 56) */
    RC(56),
    /** RC Plus 리모트 컨트롤 (타입값: 119) */
    RC_PLUS(119),
    /** RC Pro 리모트 컨트롤 (타입값: 144) */
    RC_PRO(144),

    /**
     * 도크 타입들
     */
    /** 도크 (타입값: 1) */
    DOCK(1),
    /** 도크2 (타입값: 2) */
    DOCK2(2),
    ;

    /**
     * 타입 정수값
     */
    private final int type;

    /**
     * 디바이스 타입 열거형 생성자
     * 
     * @param type 타입 정수값
     */
    DeviceTypeEnum(int type) {
        this.type = type;
    }

    /**
     * 타입 정수값을 반환합니다.
     * 
     * @return 타입 정수값
     */
    @JsonValue
    public int getType() {
        return type;
    }

    /**
     * 정수값으로 디바이스 타입을 찾습니다.
     * 
     * @param type 찾을 타입 정수값
     * @return 찾은 디바이스 타입 열거형
     * @throws CloudSDKException 해당하는 타입을 찾을 수 없는 경우
     */
    @JsonCreator
    public static DeviceTypeEnum find(int type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type == type).findAny()
                .orElseThrow(() -> new CloudSDKException(DeviceTypeEnum.class, type));
    }
}
