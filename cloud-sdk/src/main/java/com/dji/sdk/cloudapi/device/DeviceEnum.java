package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;

/**
 * DJI 디바이스 모델 열거형 클래스
 * 
 * 이 클래스는 DJI의 모든 디바이스 모델을 정의합니다.
 * 각 디바이스는 도메인, 타입, 서브타입으로 구성되며, 
 * "domain-type-subType" 형식의 문자열로 표현됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/19
 */
@Schema(description = "device model key.", format = "domain-type-subType", enumAsRef = true, example = "0-89-0")
public enum DeviceEnum {

    /**
     * 드론 모델들
     */
    /** M350 드론 */
    M350(DeviceDomainEnum.DRONE, DeviceTypeEnum.M350, DeviceSubTypeEnum.ZERO),
    /** M300 드론 */
    M300(DeviceDomainEnum.DRONE, DeviceTypeEnum.M300, DeviceSubTypeEnum.ZERO),
    /** M30 드론 */
    M30(DeviceDomainEnum.DRONE, DeviceTypeEnum.M30_OR_M3T_CAMERA, DeviceSubTypeEnum.ZERO),
    /** M30T 드론 */
    M30T(DeviceDomainEnum.DRONE, DeviceTypeEnum.M30_OR_M3T_CAMERA, DeviceSubTypeEnum.ONE),
    /** M3E 드론 */
    M3E(DeviceDomainEnum.DRONE, DeviceTypeEnum.M3E, DeviceSubTypeEnum.ZERO),
    /** M3T 드론 */
    M3T(DeviceDomainEnum.DRONE, DeviceTypeEnum.M3E, DeviceSubTypeEnum.ONE),
    /** M3M 드론 */
    M3M(DeviceDomainEnum.DRONE, DeviceTypeEnum.M3E, DeviceSubTypeEnum.TWO),
    /** M3D 드론 */
    M3D(DeviceDomainEnum.DRONE, DeviceTypeEnum.M3D, DeviceSubTypeEnum.ZERO),
    /** M3TD 드론 */
    M3TD(DeviceDomainEnum.DRONE, DeviceTypeEnum.M3D, DeviceSubTypeEnum.ONE),

    /**
     * 페이로드 모델들
     */
    /** Z30 카메라 */
    Z30(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.Z30, DeviceSubTypeEnum.ZERO),
    /** XT2 열화상 카메라 */
    XT2(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.XT2, DeviceSubTypeEnum.ZERO),
    /** FPV 카메라 */
    FPV(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.FPV, DeviceSubTypeEnum.ZERO),
    /** XTS 열화상 카메라 */
    XTS(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.XTS, DeviceSubTypeEnum.ZERO),
    /** H20 카메라 */
    H20(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.H20, DeviceSubTypeEnum.ZERO),
    /** H20T 열화상 카메라 */
    H20T(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.H20T, DeviceSubTypeEnum.ZERO),
    /** P1 카메라 */
    P1(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.P1, DeviceSubTypeEnum._65535),
    /** M30 카메라 */
    M30_CAMERA(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.M30_CAMERA, DeviceSubTypeEnum.ZERO),
    /** M30T 카메라 */
    M30T_CAMERA(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.M30T_CAMERA, DeviceSubTypeEnum.ZERO),
    /** H20N 카메라 */
    H20N(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.H20N, DeviceSubTypeEnum.ZERO),
    /** 도크 카메라 */
    DOCK_CAMERA(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.DOCK_CAMERA, DeviceSubTypeEnum.ZERO),
    /** L1 라이다 */
    L1(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.L1, DeviceSubTypeEnum.ZERO),
    /** M3E 카메라 */
    M3E_CAMERA(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.M3E_CAMERA, DeviceSubTypeEnum.ZERO),
    /** M3T 카메라 */
    M3T_CAMERA(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.M30_OR_M3T_CAMERA, DeviceSubTypeEnum.ZERO),
    /** M3M 카메라 */
    M3M_CAMERA(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.M3M_CAMERA, DeviceSubTypeEnum.ZERO),
    /** M3D 카메라 */
    M3D_CAMERA(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.M3D_CAMERA, DeviceSubTypeEnum.ZERO),
    /** M3TD 카메라 */
    M3TD_CAMERA(DeviceDomainEnum.PAYLOAD, DeviceTypeEnum.M3TD_CAMERA, DeviceSubTypeEnum.ZERO),

    /**
     * 리모트 컨트롤 모델들
     */
    /** RC 리모트 컨트롤 */
    RC(DeviceDomainEnum.REMOTER_CONTROL, DeviceTypeEnum.RC, DeviceSubTypeEnum.ZERO),
    /** RC Plus 리모트 컨트롤 */
    RC_PLUS(DeviceDomainEnum.REMOTER_CONTROL, DeviceTypeEnum.RC_PLUS, DeviceSubTypeEnum.ZERO),
    /** RC Pro 리모트 컨트롤 */
    RC_PRO(DeviceDomainEnum.REMOTER_CONTROL, DeviceTypeEnum.RC_PRO, DeviceSubTypeEnum.ZERO),

    /**
     * 도크 모델들
     */
    /** 도크 */
    DOCK(DeviceDomainEnum.DOCK, DeviceTypeEnum.DOCK, DeviceSubTypeEnum.ZERO),
    /** 도크2 */
    DOCK2(DeviceDomainEnum.DOCK, DeviceTypeEnum.DOCK2, DeviceSubTypeEnum.ZERO),
    ;

    /**
     * 디바이스 도메인 (DRONE, PAYLOAD, REMOTER_CONTROL, DOCK)
     */
    @Schema(enumAsRef = true)
    private final DeviceDomainEnum domain;

    /**
     * 디바이스 타입 (M350, M300, Z30, XT2 등)
     */
    @Schema(enumAsRef = true)
    private final DeviceTypeEnum type;

    /**
     * 디바이스 서브타입 (ZERO, ONE, TWO 등)
     */
    @Schema(enumAsRef = true)
    private final DeviceSubTypeEnum subType;

    /**
     * 디바이스 열거형 생성자
     * 
     * @param domain 디바이스 도메인
     * @param type 디바이스 타입
     * @param subType 디바이스 서브타입
     */
    DeviceEnum(DeviceDomainEnum domain, DeviceTypeEnum type, DeviceSubTypeEnum subType) {
        this.domain = domain;
        this.type = type;
        this.subType = subType;
    }

    /**
     * 디바이스 도메인을 반환합니다.
     * 
     * @return 디바이스 도메인
     */
    public DeviceDomainEnum getDomain() {
        return domain;
    }

    /**
     * 디바이스 타입을 반환합니다.
     * 
     * @return 디바이스 타입
     */
    public DeviceTypeEnum getType() {
        return type;
    }

    /**
     * 디바이스 서브타입을 반환합니다.
     * 
     * @return 디바이스 서브타입
     */
    public DeviceSubTypeEnum getSubType() {
        return subType;
    }

    /**
     * 디바이스를 "domain-type-subType" 형식의 문자열로 반환합니다.
     * 
     * @return 디바이스 식별 문자열
     */
    @JsonValue
    public String getDevice() {
        return String.format("%s-%s-%s", domain.getDomain(), type.getType(), subType.getSubType());
    }

    /**
     * 도메인, 타입, 서브타입으로 디바이스를 찾습니다.
     * 
     * @param domain 디바이스 도메인
     * @param type 디바이스 타입
     * @param subType 디바이스 서브타입
     * @return 찾은 디바이스 열거형
     */
    public static DeviceEnum find(DeviceDomainEnum domain, DeviceTypeEnum type, DeviceSubTypeEnum subType) {
        return DeviceEnum.find(domain.getDomain(), type.getType(), subType.getSubType());
    }

    /**
     * 도메인, 타입, 서브타입의 정수값으로 디바이스를 찾습니다.
     * 
     * @param domain 디바이스 도메인 정수값
     * @param type 디바이스 타입 정수값
     * @param subType 디바이스 서브타입 정수값
     * @return 찾은 디바이스 열거형
     * @throws CloudSDKException 해당하는 디바이스를 찾을 수 없는 경우
     */
    public static DeviceEnum find(int domain, int type, int subType) {
        return Arrays.stream(values()).filter(device -> device.domain.getDomain() == domain &&
                device.type.getType() == type && device.subType.getSubType() == subType)
                .findAny().orElseThrow(() -> new CloudSDKException(DeviceEnum.class,
                        String.format("%s-%s-%s", domain, type, subType)));
    }

    /**
     * 문자열 키로 디바이스를 찾습니다.
     * 
     * @param key "domain-type-subType" 형식의 디바이스 키
     * @return 찾은 디바이스 열거형
     * @throws CloudSDKException 해당하는 디바이스를 찾을 수 없는 경우
     */
    @JsonCreator
    public static DeviceEnum find(String key) {
        return Arrays.stream(values()).filter(device -> device.getDevice().equals(key))
                .findAny().orElseThrow(() -> new CloudSDKException(DeviceEnum.class, key));
    }
}
