package com.dji.sdk.config.version;

import com.dji.sdk.cloudapi.device.DeviceEnum;
import com.dji.sdk.exception.CloudSDKException;

import java.util.Arrays;

/**
 * 게이트웨이 타입 열거형
 * <p>
 * 이 열거형은 다양한 게이트웨이 타입을 정의합니다.
 * 각 게이트웨이 타입은 해당하는 디바이스 열거형들을 포함합니다.
 * <p>
 * 주요 구성 요소:
 * - RC: 리모컨 관련 디바이스들 (RC, RC_PLUS, RC_PRO)
 * - DOCK: 도크 디바이스
 * - DOCK2: 도크2 디바이스
 * <p>
 * 이 열거형은 디바이스 타입을 게이트웨이 타입으로
 * 분류하고 관리하는 데 사용됩니다.
 *
 * @author sean
 * @version 1.7
 * @date 2023/5/19
 */
public enum GatewayTypeEnum {

    /**
     * 리모컨 게이트웨이
     * <p>
     * RC, RC_PLUS, RC_PRO 디바이스들을 포함합니다.
     */
    RC(DeviceEnum.RC, DeviceEnum.RC_PLUS, DeviceEnum.RC_PLUS2, DeviceEnum.RC_PRO),

    /**
     * 도크 게이트웨이
     * <p>
     * DOCK 디바이스를 포함합니다.
     */
    DOCK(DeviceEnum.DOCK),

    /**
     * 도크2 게이트웨이
     * <p>
     * DOCK2 디바이스를 포함합니다.
     */
    DOCK2(DeviceEnum.DOCK2),

    /**
     * 도크3 게이트웨이
     * <p>
     * DOCK2 디바이스를 포함합니다.
     */
    DOCK3(DeviceEnum.DOCK3),

    ;
    /**
     * 게이트웨이 디바이스들
     * <p>
     * 각 게이트웨이 타입에 해당하는 디바이스 열거형들입니다.
     */
    private final DeviceEnum[] gateway;

    /**
     * 게이트웨이 타입 열거형 생성자
     *
     * @param gateway 게이트웨이에 포함될 디바이스들
     */
    GatewayTypeEnum(DeviceEnum... gateway) {
        this.gateway = gateway;
    }

    /**
     * 게이트웨이 디바이스들을 반환합니다.
     *
     * @return 게이트웨이 디바이스들
     */
    public DeviceEnum[] getGateway() {
        return gateway;
    }

    /**
     * 디바이스 열거형으로 게이트웨이 타입을 찾습니다.
     * <p>
     * 주어진 디바이스가 속한 게이트웨이 타입을 반환합니다.
     * 해당하는 게이트웨이가 없으면 CloudSDKException을 발생시킵니다.
     *
     * @param device 찾을 디바이스 열거형
     * @return 해당하는 GatewayTypeEnum 열거형
     * @throws CloudSDKException 해당하는 게이트웨이가 없을 경우
     */
    public static GatewayTypeEnum find(DeviceEnum device) {
        return Arrays.stream(values()).filter(gateway -> Arrays.stream(gateway.gateway).anyMatch(deviceEnum -> device == deviceEnum))
                .findAny().orElseThrow(() -> new CloudSDKException(GatewayTypeEnum.class, device));
    }
}
