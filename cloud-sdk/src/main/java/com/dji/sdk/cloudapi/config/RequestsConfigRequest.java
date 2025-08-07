package com.dji.sdk.cloudapi.config;

/**
 * 설정 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 설정 정보를 요청할 때 사용됩니다.
 * 설정 타입과 설정 범위를 지정하여 원하는 설정 정보를 요청할 수 있습니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/10
 */
public class RequestsConfigRequest {

    /**
     * 설정 타입
     * 요청할 설정의 타입 (예: JSON)
     */
    private ConfigTypeEnum configType;

    /**
     * 설정 범위
     * 요청할 설정의 범위 (예: 제품 범위)
     */
    private ConfigScopeEnum configScope;

    /**
     * 기본 생성자
     */
    public RequestsConfigRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "RequestsConfigRequest{" +
                "configType=" + configType +
                ", configScope=" + configScope +
                '}';
    }

    /**
     * 설정 타입을 반환합니다.
     * 
     * @return 설정 타입
     */
    public ConfigTypeEnum getConfigType() {
        return configType;
    }

    /**
     * 설정 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param configType 설정할 설정 타입
     * @return 현재 RequestsConfigRequest 객체
     */
    public RequestsConfigRequest setConfigType(ConfigTypeEnum configType) {
        this.configType = configType;
        return this;
    }

    /**
     * 설정 범위를 반환합니다.
     * 
     * @return 설정 범위
     */
    public ConfigScopeEnum getConfigScope() {
        return configScope;
    }

    /**
     * 설정 범위를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param configScope 설정할 설정 범위
     * @return 현재 RequestsConfigRequest 객체
     */
    public RequestsConfigRequest setConfigScope(ConfigScopeEnum configScope) {
        this.configScope = configScope;
        return this;
    }
}
