package com.dji.sdk.config.version;

import com.dji.sdk.exception.CloudSDKVersionException;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 도크2 Thing 버전 열거형
 * 
 * 이 열거형은 도크2 디바이스의 Thing 모델 버전을 정의합니다.
 * 각 버전은 Thing 모델 버전과 해당하는 Cloud SDK 버전을 포함합니다.
 * 
 * 주요 구성 요소:
 * - V1_1_2: Thing 버전 1.1.2 (Cloud SDK 1.0.1)
 * - V1_2_0: Thing 버전 1.2.0 (Cloud SDK 1.0.3)
 * 
 * 이 열거형은 도크2 디바이스의 버전 호환성을
 * 관리하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/9/7
 */
public enum Dock2ThingVersionEnum implements IThingVersion {

    /**
     * Thing 버전 1.1.2
     * 
     * 도크2 Thing 모델의 초기 버전입니다.
     */
    V1_1_2("1.1.2", CloudSDKVersionEnum.V1_0_1),

    /**
     * Thing 버전 1.2.0
     * 
     * 도크2 Thing 모델의 업데이트 버전입니다.
     */
    V1_2_0("1.2.0", CloudSDKVersionEnum.V1_0_3),

    ;

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
     * 도크2 Thing 버전 열거형 생성자
     * 
     * @param thingVersion Thing 모델 버전
     * @param cloudSDKVersion Cloud SDK 버전
     */
    Dock2ThingVersionEnum(String thingVersion, CloudSDKVersionEnum cloudSDKVersion) {
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
     * Thing 버전 문자열로 도크2 Thing 버전을 찾습니다.
     * 
     * 주어진 Thing 버전에 해당하는 열거형을 반환합니다.
     * 해당하는 버전이 없으면 CloudSDKVersionException을 발생시킵니다.
     * 
     * @param thingVersion 찾을 Thing 버전 문자열
     * @return 해당하는 Dock2ThingVersionEnum 열거형
     * @throws CloudSDKVersionException 해당하는 버전이 없을 경우
     */
    public static Dock2ThingVersionEnum find(String thingVersion) {
        return Arrays.stream(values()).filter(thingVersionEnum -> thingVersionEnum.thingVersion.equals(thingVersion))
                .findAny().orElseThrow(() -> new CloudSDKVersionException(thingVersion));
    }
}
