package com.dji.sample.manage.model.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * NTP(Network Time Protocol) 서버 설정 프로퍼티 클래스
 * 
 * DJI Cloud API 애플리케이션의 NTP 서버 설정을 관리하는 프로퍼티 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. NTP 서버 설정 관리
 *    - NTP 서버 호스트 주소 관리
 *    - 시간 동기화 서버 정보 관리
 *    - 네트워크 시간 프로토콜 설정 관리
 * 
 * 2. Spring Boot 설정 통합
 *    - @ConfigurationProperties를 통한 설정 파일 바인딩
 *    - application.yml/properties 파일에서 NTP 서버 정보 로드
 *    - "ntp.server" 접두사로 설정 그룹화
 * 
 * 3. 정적 필드 관리
 *    - NTP 서버 정보를 정적 필드로 저장하여 전역 접근 가능
 *    - 애플리케이션 전체에서 NTP 서버 정보 공유
 *    - 런타임 중 NTP 서버 정보 변경 가능
 * 
 * 4. 설정 주입 기능
 *    - Spring의 의존성 주입을 통한 설정 값 자동 바인딩
 *    - setter 메서드를 통한 정적 필드 값 설정
 *    - 타입 안전한 설정 값 관리
 * 
 * 이 클래스는 application.yml 파일의 다음과 같은 설정을 바인딩합니다:
 * ntp:
 *   server:
 *     host: "ntp.example.com"
 * 
 * NTP 서버는 디바이스와 애플리케이션 간의 시간 동기화를 위해
 * 사용되며, 정확한 시간 정보를 제공하는 역할을 담당합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/10
 */
@Component
@ConfigurationProperties("ntp.server")
public class NtpServerProperties {

    /**
     * NTP 서버 호스트 주소
     * 시간 동기화를 위한 NTP 서버의 호스트명 또는 IP 주소
     */
    public static String host;

    /**
     * NTP 서버 호스트 주소를 설정합니다.
     * 
     * @param host 설정할 NTP 서버 호스트 주소
     */
    public void setHost(String host) {
        NtpServerProperties.host = host;
    }
}
