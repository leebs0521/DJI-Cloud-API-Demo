package com.dji.sdk.config.version;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 리모컨 Thing 버전 열거형
 * 
 * 이 열거형은 리모컨 디바이스의 Thing 모델 버전을 정의합니다.
 * 각 버전은 Thing 모델 버전과 해당하는 Cloud SDK 버전을 포함합니다.
 * 
 * 주요 구성 요소:
 * - V1_0_0: 초기 버전 (Cloud SDK 0.0.1)
 * 
 * 이 열거형은 리모컨 디바이스의 버전 호환성을
 * 관리하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/19
 */
public enum RcThingVersionEnum implements IThingVersion {

    /**
     * Thing 버전 1.0.0
     * 
     * 초기 리모컨 Thing 모델 버전입니다.
     */
    V1_0_0("1.0.0", CloudSDKVersionEnum.V0_0_1);

    /**
     * Thing 모델 버전
     * 
     * 각 버전을 나타내는 문자열 값입니다.
     */
    private final String thingVersion;

    /**
     * Cloud SDK 버전
     * 
     * 각 Thing 버전에 해당하는 Cloud SDK 버전입니다.
     */
    private final CloudSDKVersionEnum cloudSDKVersion;

    /**
     * 리모컨 Thing 버전 열거형 생성자
     * 
     * @param thingVersion Thing 모델 버전
     * @param cloudSDKVersion Cloud SDK 버전
     */
    RcThingVersionEnum(String thingVersion, CloudSDKVersionEnum cloudSDKVersion) {
        this.thingVersion = thingVersion;
        this.cloudSDKVersion = cloudSDKVersion;
    }

    /**
     * Thing 모델 버전을 반환합니다.
     * 
     * @return Thing 모델 버전
     */
    @JsonValue
    public String getThingVersion() {
        return thingVersion;
    }

    /**
     * Cloud SDK 버전을 반환합니다.
     * 
     * @return Cloud SDK 버전
     */
    public CloudSDKVersionEnum getCloudSDKVersion() {
        return cloudSDKVersion;
    }

    /**
     * Thing 버전 문자열로 리모컨 Thing 버전을 찾습니다.
     * 
     * 현재는 V1_0_0만 지원하므로 항상 V1_0_0을 반환합니다.
     * 
     * @param thingVersion 찾을 Thing 버전 문자열
     * @return V1_0_0 열거형
     */
    public static RcThingVersionEnum find(String thingVersion) {
        return V1_0_0;
    }
}
