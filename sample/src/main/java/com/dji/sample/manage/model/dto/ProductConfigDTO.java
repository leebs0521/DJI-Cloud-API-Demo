package com.dji.sample.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 제품 설정 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 제품 설정 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. NTP 서버 설정 관리
 *    - NTP 서버 호스트 주소 관리
 *    - 시간 동기화 서버 정보 관리
 *    - 네트워크 시간 프로토콜 설정 관리
 * 
 * 2. 애플리케이션 인증 정보 관리
 *    - 애플리케이션 ID 관리
 *    - 애플리케이션 키 관리
 *    - 애플리케이션 라이선스 관리
 * 
 * 3. 제품 설정 통합 관리
 *    - 제품별 설정 정보 통합 관리
 *    - 설정 정보의 일관성 보장
 *    - 제품 설정 변경 추적
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 *    - 제품 설정 요청의 표준화된 데이터 구조 제공
 * 
 * 이 클래스는 제품의 기본 설정 정보를 포함하며, NTP 서버 설정과
 * 애플리케이션 인증 정보를 관리하는 표준화된 데이터 형식을 제공합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductConfigDTO {

    /**
     * NTP 서버 호스트
     * 시간 동기화를 위한 NTP 서버의 호스트명 또는 IP 주소
     */
    private String ntpServerHost;

    /**
     * 애플리케이션 ID
     * DJI Cloud API에서 발급받은 고유한 애플리케이션 식별자
     */
    private String appId;

    /**
     * 애플리케이션 키
     * 애플리케이션 인증을 위한 키
     */
    private String appKey;

    /**
     * 애플리케이션 라이선스
     * 애플리케이션 사용 권한을 나타내는 라이선스 정보
     */
    private String appLicense;
}
