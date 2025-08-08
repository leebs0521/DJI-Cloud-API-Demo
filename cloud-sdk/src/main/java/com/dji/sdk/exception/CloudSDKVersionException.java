package com.dji.sdk.exception;

import com.dji.sdk.config.version.CloudSDKVersionEnum;

/**
 * Cloud SDK 버전 예외 클래스
 * 
 * 이 클래스는 Cloud SDK 버전 호환성 문제로 인해
 * 발생하는 예외를 처리합니다.
 * 
 * 주요 기능:
 * - Thing 버전과 Cloud SDK 버전 간의 호환성 검사
 * - 지원되지 않는 Thing 버전에 대한 예외 발생
 * - 적절한 오류 메시지 제공
 * 
 * 이 클래스는 SDK의 버전 호환성을 관리하고
 * 사용자에게 명확한 오류 정보를 제공합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/7
 */
public class CloudSDKVersionException extends CloudSDKException {

    /**
     * Cloud SDK 버전 예외 생성자
     * 
     * 지원되지 않는 Thing 버전에 대한 예외를 생성합니다.
     * 현재 Cloud SDK 버전과 요청된 Thing 버전을 포함한
     * 상세한 오류 메시지를 제공합니다.
     * 
     * @param thingVersion 지원되지 않는 Thing 버전
     */
    public CloudSDKVersionException(String thingVersion) {
        super(String.format("The current CloudSDK version(%s) does not support this thing version(%s), " +
                "please replace the corresponding CloudSDK version.)", CloudSDKVersionEnum.DEFAULT.getVersion(), thingVersion));
    }

}
