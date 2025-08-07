package com.dji.sdk.cloudapi.config;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 설정 범위 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 설정의 범위를 정의합니다.
 * 현재는 제품(product) 범위만 지원합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/10
 */
public enum ConfigScopeEnum {

    /**
     * 제품 범위 설정
     * 제품 전체에 적용되는 설정
     */
    PRODUCT("product");

    /**
     * 설정 범위 값
     */
    private final String scope;

    /**
     * 설정 범위 열거형 생성자
     * 
     * @param scope 설정 범위 값
     */
    ConfigScopeEnum(String scope) {
        this.scope = scope;
    }

    /**
     * 설정 범위 값을 반환합니다.
     * 
     * @return 설정 범위 값
     */
    @JsonValue
    public String getScope() {
        return scope;
    }

    /**
     * 문자열로 설정 범위를 찾습니다.
     * 
     * @param scope 찾을 설정 범위 문자열
     * @return 찾은 설정 범위 열거형
     * @throws CloudSDKException 해당하는 설정 범위를 찾을 수 없는 경우
     */
    @JsonCreator
    public static ConfigScopeEnum find(String scope) {
        return Arrays.stream(values()).filter(scopeEnum -> scopeEnum.scope.equals(scope)).findAny()
                .orElseThrow(() -> new CloudSDKException(ConfigScopeEnum.class, scope));
    }
}
