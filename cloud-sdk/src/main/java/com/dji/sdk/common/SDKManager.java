package com.dji.sdk.common;

import com.dji.sdk.cloudapi.device.DeviceDomainEnum;
import com.dji.sdk.cloudapi.device.DeviceEnum;
import com.dji.sdk.cloudapi.device.DeviceSubTypeEnum;
import com.dji.sdk.cloudapi.device.DeviceTypeEnum;
import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.config.version.GatewayTypeEnum;
import com.dji.sdk.exception.CloudSDKErrorEnum;
import com.dji.sdk.exception.CloudSDKException;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DJI Cloud SDK 관리자 클래스
 * 
 * 이 클래스는 DJI Cloud SDK의 핵심 관리자로, 디바이스 등록, 해제, 조회 기능을 제공합니다.
 * ConcurrentHashMap을 사용하여 스레드 안전한 디바이스 관리가 가능합니다.
 * 
 * 주요 기능:
 * - 디바이스 등록 및 관리
 * - 등록된 디바이스 조회
 * - 디바이스 로그아웃 (제거)
 * - 스레드 안전한 디바이스 관리
 * 
 * 이 클래스는 SDK의 모든 디바이스 인스턴스를
 * 중앙에서 관리하는 싱글톤 패턴을 구현합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/19
 */
public class SDKManager {

    /**
     * 기본 생성자
     * 
     * 외부에서 인스턴스화를 방지하기 위해 private으로 선언되었습니다.
     */
    private SDKManager() {
    }

    /**
     * 등록된 디바이스들을 저장하는 스레드 안전한 맵
     * 
     * 키: 게이트웨이 시리얼 번호
     * 값: GatewayManager 인스턴스
     */
    private static final ConcurrentHashMap<String, GatewayManager> SDK_MAP = new ConcurrentHashMap<>(16);

    /**
     * 등록된 디바이스의 SDK 인스턴스를 조회합니다.
     * 
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @return 해당 디바이스의 GatewayManager 인스턴스
     * @throws CloudSDKException 디바이스가 등록되지 않은 경우 예외 발생
     */
    public static GatewayManager getDeviceSDK(String gatewaySn) {
        if (SDK_MAP.containsKey(gatewaySn)) {
            return SDK_MAP.get(gatewaySn);
        }
        throw new CloudSDKException(CloudSDKErrorEnum.NOT_REGISTERED,
                "The device has not been registered, please call the 'SDKManager.registerDevice()' method to register the device first.");
    }

    /**
     * 디바이스를 등록합니다. (도메인, 타입, 서브타입 기반)
     * 
     * 디바이스의 도메인, 타입, 서브타입을 기반으로 적절한 게이트웨이 타입을
     * 자동으로 결정하여 디바이스를 등록합니다.
     * 
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @param droneSn 드론 시리얼 번호
     * @param domain 디바이스 도메인 (예: PILOT, DOCK)
     * @param type 디바이스 타입 (예: AIRCRAFT, RC)
     * @param subType 디바이스 서브타입
     * @param gatewayThingVersion 게이트웨이 Thing 모델 버전
     * @param droneThingVersion 드론 Thing 모델 버전
     * @return 등록된 GatewayManager 인스턴스
     */
    public static GatewayManager registerDevice(String gatewaySn, String droneSn,
            DeviceDomainEnum domain, DeviceTypeEnum type, DeviceSubTypeEnum subType, String gatewayThingVersion, String droneThingVersion) {
        return registerDevice(gatewaySn, droneSn, GatewayTypeEnum.find(DeviceEnum.find(domain, type, subType)), gatewayThingVersion, droneThingVersion);
    }

    /**
     * 디바이스를 등록합니다. (GatewayTypeEnum 기반)
     * 
     * 명시적으로 게이트웨이 타입을 지정하여 디바이스를 등록합니다.
     * 
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @param droneSn 드론 시리얼 번호
     * @param type 게이트웨이 타입 열거형
     * @param gatewayThingVersion 게이트웨이 Thing 모델 버전
     * @param droneThingVersion 드론 Thing 모델 버전
     * @return 등록된 GatewayManager 인스턴스
     */
    public static GatewayManager registerDevice(String gatewaySn, String droneSn, GatewayTypeEnum type, String gatewayThingVersion, String droneThingVersion) {
        return registerDevice(new GatewayManager(Objects.requireNonNull(gatewaySn), droneSn, type, gatewayThingVersion, droneThingVersion));
    }

    /**
     * GatewayManager 인스턴스를 직접 등록합니다.
     * 
     * 이미 생성된 GatewayManager 인스턴스를 직접 등록합니다.
     * 
     * @param gateway 등록할 GatewayManager 인스턴스
     * @return 등록된 GatewayManager 인스턴스
     */
    public static GatewayManager registerDevice(GatewayManager gateway) {
        SDK_MAP.put(gateway.getGatewaySn(), gateway);
        return gateway;
    }

    /**
     * 등록된 디바이스를 로그아웃(제거)합니다.
     * 
     * 지정된 게이트웨이 시리얼 번호에 해당하는 디바이스를
     * SDK 관리자에서 제거합니다.
     * 
     * @param gatewaySn 제거할 게이트웨이 시리얼 번호
     */
    public static void logoutDevice(String gatewaySn) {
        SDK_MAP.remove(gatewaySn);
    }
}
