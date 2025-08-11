package com.dji.sample.manage.model.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 애플리케이션 라이선스 설정 프로퍼티 클래스
 * 
 * DJI Cloud API 애플리케이션의 라이선스 정보를 관리하는 설정 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 라이선스 정보 관리
 *    - 애플리케이션 ID 관리
 *    - 라이선스 키 관리
 *    - 라이선스 문자열 관리
 * 
 * 2. Spring Boot 설정 통합
 *    - @ConfigurationProperties를 통한 설정 파일 바인딩
 *    - application.yml/properties 파일에서 라이선스 정보 로드
 *    - "cloud-api.app" 접두사로 설정 그룹화
 * 
 * 3. 정적 필드 관리
 *    - 라이선스 정보를 정적 필드로 저장하여 전역 접근 가능
 *    - 애플리케이션 전체에서 라이선스 정보 공유
 *    - 런타임 중 라이선스 정보 변경 가능
 * 
 * 4. 설정 주입 기능
 *    - Spring의 의존성 주입을 통한 설정 값 자동 바인딩
 *    - setter 메서드를 통한 정적 필드 값 설정
 *    - 타입 안전한 설정 값 관리
 * 
 * 이 클래스는 application.yml 파일의 다음과 같은 설정을 바인딩합니다:
 * cloud-api:
 *   app:
 *     id: "애플리케이션_ID"
 *     key: "라이선스_키"
 *     license: "라이선스_문자열"
 * 
 * @author sean
 * @version 1.3.1
 * @date 2023/1/5
 */
@Component
@ConfigurationProperties("cloud-api.app")
public class AppLicenseProperties {

    /**
     * 애플리케이션 ID
     * DJI Cloud API에서 발급받은 고유한 애플리케이션 식별자
     */
    public static String id;

    /**
     * 라이선스 키
     * 애플리케이션 인증을 위한 라이선스 키
     */
    public static String key;

    /**
     * 라이선스 문자열
     * 애플리케이션 사용 권한을 나타내는 라이선스 정보
     */
    public static String license;

    /**
     * 애플리케이션 ID를 설정합니다.
     * 
     * @param id 설정할 애플리케이션 ID
     */
    public void setId(String id) {
        AppLicenseProperties.id = id;
    }

    /**
     * 라이선스 키를 설정합니다.
     * 
     * @param key 설정할 라이선스 키
     */
    public void setKey(String key) {
        AppLicenseProperties.key = key;
    }

    /**
     * 라이선스 문자열을 설정합니다.
     * 
     * @param license 설정할 라이선스 문자열
     */
    public void setLicense(String license) {
        AppLicenseProperties.license = license;
    }
}
