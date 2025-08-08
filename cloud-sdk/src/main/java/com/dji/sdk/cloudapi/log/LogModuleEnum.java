package com.dji.sdk.cloudapi.log;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 로그 모듈을 정의하는 열거형
 * 로그 파일이 생성되는 장치 타입을 정의합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public enum LogModuleEnum {
    
    /** 드론 (항공기) */
    DRONE("0"),

    /** 독 (지상 스테이션) */
    DOCK ("3");

    /** 모듈 도메인을 나타내는 문자열 값 */
    private final String domain;

    /**
     * 로그 모듈 열거형 생성자
     * @param domain 모듈 도메인을 나타내는 문자열 값
     */
    LogModuleEnum(String domain) {
        this.domain = domain;
    }

    /**
     * 문자열 값으로 로그 모듈을 찾습니다.
     * JSON 역직렬화 시 사용됩니다.
     * 
     * @param domain 찾을 모듈 도메인의 문자열 값
     * @return 해당하는 LogModuleEnum 인스턴스
     * @throws CloudSDKException 지정된 도메인이 존재하지 않을 경우
     */
    @JsonCreator
    public static LogModuleEnum find(String domain) {
        return Arrays.stream(values()).filter(domainEnum -> domainEnum.domain.equals(domain)).findAny()
                .orElseThrow(() -> new CloudSDKException(LogModuleEnum.class, domain));
    }

    /**
     * 모듈 도메인 값을 반환합니다.
     * JSON 직렬화 시 사용됩니다.
     * 
     * @return 모듈 도메인 문자열 값
     */
    @JsonValue
    public String getDomain() {
        return domain;
    }
}
