package com.dji.sdk.mqtt.osd;

import com.dji.sdk.cloudapi.device.OsdDock;
import com.dji.sdk.cloudapi.device.OsdDockDrone;
import com.dji.sdk.cloudapi.device.OsdRcDrone;
import com.dji.sdk.cloudapi.device.OsdRemoteControl;
import com.dji.sdk.config.version.GatewayTypeEnum;
import com.dji.sdk.exception.CloudSDKException;
import com.dji.sdk.mqtt.ChannelName;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * OSD 디바이스 타입 열거형
 * 다양한 디바이스 타입과 해당하는 클래스 타입, 채널명을 정의
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/29
 */
public enum OsdDeviceTypeEnum {

    /** 리모컨 (게이트웨이) */
    RC(true, OsdRemoteControl.class, ChannelName.INBOUND_OSD_RC, GatewayTypeEnum.RC),

    /** 도크 (게이트웨이) */
    DOCK(true, OsdDock.class, ChannelName.INBOUND_OSD_DOCK, GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2),

    /** 리모컨 드론 (서브 디바이스) */
    RC_DRONE(false, OsdRcDrone.class, ChannelName.INBOUND_OSD_RC_DRONE, GatewayTypeEnum.RC),

    /** 도크 드론 (서브 디바이스) */
    DOCK_DRONE(false, OsdDockDrone.class, ChannelName.INBOUND_OSD_DOCK_DRONE, GatewayTypeEnum.DOCK, GatewayTypeEnum.DOCK2);

    /** 게이트웨이 여부 */
    private final boolean gateway;

    /** 지원하는 게이트웨이 타입들 */
    private final Set<GatewayTypeEnum> gatewayType = new HashSet<>();

    /** 해당 디바이스 타입의 클래스 */
    private final Class classType;

    /** 해당 디바이스 타입의 채널명 */
    private final String channelName;

    /**
     * OsdDeviceTypeEnum 생성자
     * 
     * @param gateway 게이트웨이 여부
     * @param classType 클래스 타입
     * @param channelName 채널명
     * @param gatewayType 지원하는 게이트웨이 타입들
     */
    OsdDeviceTypeEnum(boolean gateway, Class classType, String channelName, GatewayTypeEnum... gatewayType) {
        this.gateway = gateway;
        this.classType = classType;
        this.channelName = channelName;
        Collections.addAll(this.gatewayType, gatewayType);
    }

    /**
     * 지원하는 게이트웨이 타입들을 반환합니다.
     * @return 지원하는 게이트웨이 타입들
     */
    public Set<GatewayTypeEnum> getGatewayType() {
        return gatewayType;
    }

    /**
     * 게이트웨이 여부를 반환합니다.
     * @return 게이트웨이 여부
     */
    public boolean isGateway() {
        return gateway;
    }

    /**
     * 클래스 타입을 반환합니다.
     * @return 클래스 타입
     */
    public Class getClassType() {
        return classType;
    }

    /**
     * 채널명을 반환합니다.
     * @return 채널명
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 게이트웨이 타입과 게이트웨이 여부로 해당하는 OsdDeviceTypeEnum을 찾습니다.
     * 
     * @param gatewayType 게이트웨이 타입
     * @param isGateway 게이트웨이 여부
     * @return 해당하는 OsdDeviceTypeEnum
     * @throws CloudSDKException 해당하는 타입을 찾을 수 없는 경우
     */
    public static OsdDeviceTypeEnum find(GatewayTypeEnum gatewayType, boolean isGateway) {
        return Arrays.stream(values()).filter(osdEnum -> osdEnum.gatewayType.contains(gatewayType) && osdEnum.gateway == isGateway).findAny()
            .orElseThrow(() -> new CloudSDKException(OsdDeviceTypeEnum.class, gatewayType, isGateway));
    }

    /**
     * 클래스 타입으로 해당하는 OsdDeviceTypeEnum을 찾습니다.
     * 
     * @param classType 클래스 타입
     * @return 해당하는 OsdDeviceTypeEnum
     * @throws CloudSDKException 해당하는 타입을 찾을 수 없는 경우
     */
    public static OsdDeviceTypeEnum find(Class classType) {
        return Arrays.stream(values()).filter(type -> type.classType == classType).findAny()
                .orElseThrow(() -> new CloudSDKException(OsdDeviceTypeEnum.class, classType));
    }
}
