package com.dji.sample.manage.model.enums;

import com.dji.sample.manage.service.IRequestsConfigService;
import com.dji.sample.manage.service.impl.ConfigProductServiceImpl;
import com.dji.sdk.cloudapi.config.ConfigScopeEnum;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * 커스터마이즈 설정 범위 열거형
 * 
 * DJI Cloud API의 커스터마이즈 설정 범위를 정의하는 열거형 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 설정 범위 정의
 *    - 제품 설정 범위 (PRODUCT)
 *    - 각 범위별 전용 서비스 클래스 매핑
 *    - 설정 범위와 서비스 구현체 간의 연결
 * 
 * 2. 설정 범위 관리
 *    - ConfigScopeEnum과 서비스 클래스 간의 매핑
 *    - 설정 범위별 전용 처리 로직 제공
 *    - 설정 범위별 서비스 인스턴스 생성 및 관리
 * 
 * 3. 설정 서비스 분리
 *    - 설정 범위별 독립적인 서비스 구현
 *    - 설정 처리 로직의 모듈화 및 확장성
 *    - 설정 범위별 권한 및 접근 제어
 * 
 * 이 열거형은 DJI Cloud API의 설정 관리 시스템에서
 * 다양한 설정 범위를 표준화된 방식으로
 * 관리하고 처리할 수 있도록 지원합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/10
 */
@Getter
public enum CustomizeConfigScopeEnum {

    /**
     * 제품 설정 범위
     * 제품 관련 설정을 처리하는 범위
     */
    PRODUCT(ConfigScopeEnum.PRODUCT, ConfigProductServiceImpl.class);

    /**
     * 설정 범위 열거형
     * DJI SDK의 ConfigScopeEnum 인스턴스
     */
    ConfigScopeEnum scope;

    /**
     * 설정 서비스 클래스
     * 해당 설정 범위를 처리하는 서비스 클래스
     */
    Class<? extends IRequestsConfigService> clazz;

    /**
     * 생성자
     * @param scope 설정 범위 열거형
     * @param clazz 설정 서비스 클래스
     */
    CustomizeConfigScopeEnum(ConfigScopeEnum scope, Class<? extends IRequestsConfigService> clazz) {
        this.scope = scope;
        this.clazz = clazz;
    }

    /**
     * 설정 범위 문자열로부터 열거형 상수를 찾습니다.
     * 
     * 주어진 설정 범위 문자열에 해당하는 열거형 상수를 반환합니다.
     * 일치하는 값이 없으면 빈 Optional을 반환합니다.
     * 
     * @param scope 찾을 설정 범위 문자열
     * @return 해당하는 열거형 상수의 Optional 또는 빈 Optional
     */
    public static Optional<CustomizeConfigScopeEnum> find(String scope) {
        return Arrays.stream(CustomizeConfigScopeEnum.values()).filter(scopeEnum -> scopeEnum.scope.getScope().equals(scope)).findAny();
    }
}
