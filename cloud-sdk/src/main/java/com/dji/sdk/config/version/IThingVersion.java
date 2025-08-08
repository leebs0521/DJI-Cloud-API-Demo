package com.dji.sdk.config.version;

/**
 * Thing 버전 인터페이스
 * 
 * 이 인터페이스는 디바이스의 Thing 모델 버전과
 * Cloud SDK 버전 정보를 제공하는 표준 인터페이스입니다.
 * 
 * 주요 구성 요소:
 * - getThingVersion(): Thing 모델 버전 반환
 * - getCloudSDKVersion(): Cloud SDK 버전 반환
 * 
 * 이 인터페이스를 구현하는 클래스들은
 * 디바이스의 버전 호환성을 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/9/7
 */
public interface IThingVersion {

    /**
     * Thing 모델 버전을 반환합니다.
     * 
     * @return Thing 모델 버전
     */
    String getThingVersion();

    /**
     * Cloud SDK 버전을 반환합니다.
     * 
     * @return Cloud SDK 버전
     */
    CloudSDKVersionEnum getCloudSDKVersion();
}
