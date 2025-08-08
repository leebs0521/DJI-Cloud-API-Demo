package com.dji.sdk.config.version;

import com.dji.sdk.exception.CloudSDKVersionException;
import com.fasterxml.jackson.annotation.JsonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;

/**
 * 드론 Thing 버전 열거형
 * 
 * 이 열거형은 드론 디바이스의 Thing 모델 버전을 정의합니다.
 * 각 버전은 Thing 모델 버전과 해당하는 Cloud SDK 버전을 포함합니다.
 * 
 * 주요 구성 요소:
 * - V1_0_0: 초기 버전 (Cloud SDK 0.0.1)
 * - V1_1_0: Thing 버전 1.1.0 (Cloud SDK 1.0.0)
 * - V1_1_2: Thing 버전 1.1.2 (Cloud SDK 1.0.0)
 * - V1_1_3: Thing 버전 1.1.3 (Cloud SDK 1.0.2)
 * - V1_2_0: Thing 버전 1.2.0 (Cloud SDK 1.0.3)
 * 
 * 이 열거형은 드론 디바이스의 버전 호환성을
 * 관리하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/19
 */
public enum DroneThingVersionEnum implements IThingVersion {

    /**
     * Thing 버전 1.0.0
     * 
     * 초기 드론 Thing 모델 버전입니다.
     */
    V1_0_0("1.0.0", CloudSDKVersionEnum.V0_0_1),

    /**
     * Thing 버전 1.1.0
     * 
     * 드론 Thing 모델의 첫 번째 업데이트 버전입니다.
     */
    V1_1_0("1.1.0", CloudSDKVersionEnum.V1_0_0),

    /**
     * Thing 버전 1.1.2
     * 
     * 드론 Thing 모델의 두 번째 업데이트 버전입니다.
     */
    V1_1_2("1.1.2", CloudSDKVersionEnum.V1_0_0),

    /**
     * Thing 버전 1.1.3
     * 
     * 드론 Thing 모델의 세 번째 업데이트 버전입니다.
     */
    V1_1_3("1.1.3", CloudSDKVersionEnum.V1_0_2),

    /**
     * Thing 버전 1.2.0
     * 
     * 드론 Thing 모델의 네 번째 업데이트 버전입니다.
     */
    V1_2_0("1.2.0", CloudSDKVersionEnum.V1_0_3),

    ;

    /**
     * 로거
     * 
     * 디버깅 및 로깅을 위한 로거 인스턴스입니다.
     */
    private static final Logger log = LoggerFactory.getLogger(DroneThingVersionEnum.class);

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
     * 드론 Thing 버전 열거형 생성자
     * 
     * @param thingVersion Thing 모델 버전
     * @param cloudSDKVersion Cloud SDK 버전
     */
    DroneThingVersionEnum(String thingVersion, CloudSDKVersionEnum cloudSDKVersion) {
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
     * Thing 버전 문자열로 드론 Thing 버전을 찾습니다.
     * 
     * 주어진 Thing 버전에 해당하는 열거형을 반환합니다.
     * 해당하는 버전이 없으면 CloudSDKVersionException을 발생시킵니다.
     * 
     * @param thingVersion 찾을 Thing 버전 문자열
     * @return 해당하는 DroneThingVersionEnum 열거형
     * @throws CloudSDKVersionException 해당하는 버전이 없을 경우
     */
    public static DroneThingVersionEnum find(String thingVersion) {
        Optional<DroneThingVersionEnum> opt = Arrays.stream(values())
                .filter(thingVersionEnum -> thingVersionEnum.thingVersion.equals(thingVersion)).findAny();
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new CloudSDKVersionException(thingVersion);
    }
}
