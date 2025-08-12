package com.dji.sdk.config.version;

/**
 * 게이트웨이 Thing 버전 클래스
 * 
 * 이 클래스는 게이트웨이의 Thing 모델 버전을 관리합니다.
 * 다양한 게이트웨이 타입에 따라 적절한 Thing 버전을 생성하고 관리합니다.
 * 
 * 주요 기능:
 * - 게이트웨이 타입에 따른 Thing 버전 생성
 * - Thing 모델 버전 정보 제공
 * - Cloud SDK 버전 정보 제공
 * 
 * 이 클래스는 게이트웨이의 버전 호환성을
 * 관리하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/19
 */
public class GatewayThingVersion {

    /**
     * Thing 버전 인터페이스
     * 
     * 게이트웨이의 Thing 모델 버전 정보를 담고 있습니다.
     */
    private IThingVersion thingVersion;

    /**
     * IThingVersion을 직접 받는 생성자
     * 
     * @param thingVersion Thing 버전 인터페이스
     */
    public GatewayThingVersion(IThingVersion thingVersion) {
        this.thingVersion = thingVersion;
    }

    /**
     * 게이트웨이 타입과 Thing 버전 문자열을 받는 생성자
     * 
     * 게이트웨이 타입에 따라 적절한 Thing 버전 열거형을 생성합니다.
     * 
     * @param type 게이트웨이 타입
     * @param thingVersion Thing 버전 문자열
     */
    public GatewayThingVersion(GatewayTypeEnum type, String thingVersion) {
        switch (type) {
            case DOCK:
                this.thingVersion = DockThingVersionEnum.find(thingVersion);
                return;
            case DOCK2_OR_DOCK3:
                this.thingVersion = Dock2ThingVersionEnum.find(thingVersion);
                return;
            case RC:
                this.thingVersion = RcThingVersionEnum.find(thingVersion);
                return;
        }
    }

    /**
     * Thing 모델 버전을 반환합니다.
     * 
     * @return Thing 모델 버전
     */
    public String getThingVersion() {
        return thingVersion.getThingVersion();
    }

    /**
     * Cloud SDK 버전을 반환합니다.
     * 
     * @return Cloud SDK 버전
     */
    public CloudSDKVersionEnum getCloudSDKVersion() {
        return thingVersion.getCloudSDKVersion();
    }
}
