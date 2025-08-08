package com.dji.sdk.config.version;

import com.dji.sdk.annotations.CloudSDKVersion;

import java.util.Arrays;
import java.util.Objects;

/**
 * 게이트웨이 디바이스에 해당하는 SDK 정보 관리 클래스
 * 
 * 이 클래스는 게이트웨이 디바이스의 SDK 정보를 관리합니다.
 * 게이트웨이와 드론의 Thing 버전, SDK 버전, 타입 등을 포함합니다.
 * 
 * 주요 기능:
 * - 게이트웨이와 드론의 Thing 버전 관리
 * - SDK 버전 호환성 검사
 * - 디바이스 타입 지원 여부 확인
 * - API 버전 지원 여부 확인
 * 
 * 이 클래스는 SDK의 버전 호환성을 관리하고
 * API의 지원 여부를 판단하는 핵심 클래스입니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/19
 */
public class GatewayManager {

    /**
     * 게이트웨이 시리얼 번호
     * 
     * 게이트웨이 디바이스를 식별하는 고유 번호입니다.
     */
    private String gatewaySn;

    /**
     * 게이트웨이 Thing 버전
     * 
     * 게이트웨이의 Thing 모델 버전 정보입니다.
     */
    private GatewayThingVersion gatewayThingVersion;

    /**
     * 드론 Thing 버전
     * 
     * 드론의 Thing 모델 버전 정보입니다.
     */
    private DroneThingVersionEnum droneThingVersion;

    /**
     * 게이트웨이 타입
     * 
     * 게이트웨이의 타입 정보입니다.
     */
    private GatewayTypeEnum type;

    /**
     * SDK 버전
     * 
     * 현재 사용 중인 SDK 버전입니다.
     */
    private CloudSDKVersionEnum sdkVersion;

    /**
     * 드론 시리얼 번호
     * 
     * 드론 디바이스를 식별하는 고유 번호입니다.
     */
    private String droneSn;

    /**
     * 기본 생성자
     * 
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @param droneSn 드론 시리얼 번호
     * @param gatewayType 게이트웨이 타입
     */
    private GatewayManager(String gatewaySn, String droneSn, GatewayTypeEnum gatewayType) {
        this.gatewaySn = gatewaySn;
        this.type = gatewayType;
        this.droneSn = droneSn;
    }

    /**
     * 매개변수가 있는 생성자
     * 
     * 게이트웨이와 드론의 Thing 버전을 포함하여 GatewayManager를 생성합니다.
     * 
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @param droneSn 드론 시리얼 번호
     * @param gatewayType 게이트웨이 타입
     * @param gatewayThingVersion 게이트웨이 Thing 버전
     * @param droneThingVersion 드론 Thing 버전
     */
    public GatewayManager(String gatewaySn, String droneSn, GatewayTypeEnum gatewayType, String gatewayThingVersion, String droneThingVersion) {
        this(gatewaySn, droneSn, gatewayType);
        this.gatewayThingVersion = new GatewayThingVersion(gatewayType, gatewayThingVersion);
        if (GatewayTypeEnum.RC == gatewayType) {
            this.sdkVersion = CloudSDKVersionEnum.V0_0_1;
            return;
        }
        if (Objects.isNull(droneThingVersion)) {
            this.sdkVersion = this.gatewayThingVersion.getCloudSDKVersion();
            return;
        }
        this.droneThingVersion = DroneThingVersionEnum.find(droneThingVersion);
        this.sdkVersion = this.gatewayThingVersion.getCloudSDKVersion().isSupported(this.droneThingVersion.getCloudSDKVersion()) ?
                this.droneThingVersion.getCloudSDKVersion() : this.gatewayThingVersion.getCloudSDKVersion();
    }

    /**
     * 게이트웨이 시리얼 번호를 반환합니다.
     * 
     * @return 게이트웨이 시리얼 번호
     */
    public String getGatewaySn() {
        return gatewaySn;
    }

    /**
     * 게이트웨이 Thing 버전을 반환합니다.
     * 
     * @return 게이트웨이 Thing 버전
     */
    public GatewayThingVersion getGatewayThingVersion() {
        return gatewayThingVersion;
    }

    /**
     * 드론 Thing 버전을 반환합니다.
     * 
     * @return 드론 Thing 버전
     */
    public DroneThingVersionEnum getDroneThingVersion() {
        return droneThingVersion;
    }

    /**
     * 게이트웨이 타입을 반환합니다.
     * 
     * @return 게이트웨이 타입
     */
    public GatewayTypeEnum getType() {
        return type;
    }

    /**
     * SDK 버전을 반환합니다.
     * 
     * @return SDK 버전
     */
    public CloudSDKVersionEnum getSdkVersion() {
        return sdkVersion;
    }

    /**
     * 드론 시리얼 번호를 반환합니다.
     * 
     * @return 드론 시리얼 번호
     */
    public String getDroneSn() {
        return droneSn;
    }

    /**
     * 디바이스 타입 지원 여부를 확인합니다.
     * 
     * @param version 확인할 CloudSDKVersion 어노테이션
     * @return 지원 여부
     */
    public boolean isTypeSupport(CloudSDKVersion version) {
        return null != version && Arrays.stream(version.exclude()).noneMatch(typeEnum -> typeEnum == this.getType())
                && (version.include().length == 0
                    || Arrays.stream(version.include()).anyMatch(typeEnum -> typeEnum == this.getType()));
    }

    /**
     * 버전 지원 여부를 확인합니다.
     * 
     * @param version 확인할 CloudSDKVersion 어노테이션
     * @return 지원 여부
     */
    public boolean isVersionSupport(CloudSDKVersion version) {
        return null != version && this.getSdkVersion().isSupported(version.since()) && !isDeprecated(version);
    }

    /**
     * 더 이상 사용되지 않는지 확인합니다.
     * 
     * @param version 확인할 CloudSDKVersion 어노테이션
     * @return 더 이상 사용되지 않는지 여부
     */
    public boolean isDeprecated(CloudSDKVersion version) {
        return null != version && this.getSdkVersion().isDeprecated(version.deprecated());
    }

    /**
     * 속성 유효성을 확인합니다.
     * 
     * @param version 확인할 CloudSDKVersion 어노테이션
     * @return 유효성 여부
     */
    public boolean isPropertyValid(CloudSDKVersion version) {
        return null == version ||
                (!this.getSdkVersion().isDeprecated(version.since())
                        && this.getSdkVersion().isSupported(version.since()));
    }
}
