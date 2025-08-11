package com.dji.sample.manage.service.impl;

import com.dji.sample.manage.model.common.AppLicenseProperties;
import com.dji.sample.manage.model.common.NtpServerProperties;
import com.dji.sample.manage.model.dto.ProductConfigDTO;
import com.dji.sample.manage.service.IRequestsConfigService;
import org.springframework.stereotype.Service;

/**
 * 제품 설정 관리 서비스 구현체
 * 
 * DJI Cloud API의 제품 설정 관리를 위한 서비스 구현체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 구현합니다:
 * 
 * 1. 제품 설정 정보 제공
 *    - NTP 서버 호스트 정보 제공
 *    - 애플리케이션 라이선스 정보 제공
 *    - 라이선스 ID 및 키 정보 제공
 *    - 라이선스 데이터 제공
 * 
 * 2. 설정 데이터 통합
 *    - 다양한 설정 정보를 하나의 DTO로 통합
 *    - 설정 정보의 일관성 보장
 *    - 설정 데이터의 구조화된 제공
 *    - 설정 정보의 중앙화된 관리
 * 
 * 3. 설정 속성 관리
 *    - NtpServerProperties를 통한 NTP 서버 설정
 *    - AppLicenseProperties를 통한 라이선스 설정
 *    - 설정 속성의 안전한 접근
 *    - 설정 정보의 캡슐화
 * 
 * 4. 설정 요청 처리
 *    - IRequestsConfigService 인터페이스 구현
 *    - 설정 정보 요청에 대한 응답 처리
 *    - 설정 데이터의 즉시 제공
 *    - 설정 정보의 동적 조회
 * 
 * 주요 의존성:
 * - IRequestsConfigService: 설정 요청 서비스 인터페이스
 * - ProductConfigDTO: 제품 설정 데이터 전송 객체
 * - NtpServerProperties: NTP 서버 설정 속성
 * - AppLicenseProperties: 애플리케이션 라이선스 설정 속성
 * 
 * 이 클래스는 DJI Cloud API의 제품 설정 정보를
 * 효율적으로 제공하는 서비스입니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/10
 */
@Service
public class ConfigProductServiceImpl implements IRequestsConfigService {

    /**
     * 제품 설정 정보를 조회합니다.
     * 
     * NTP 서버 호스트, 애플리케이션 라이선스 ID, 키, 라이선스 데이터를
     * 포함한 제품 설정 정보를 ProductConfigDTO 형태로 반환합니다.
     * 이 설정 정보는 DJI Cloud API의 제품 인증 및 동기화에 사용됩니다.
     * 
     * @return 제품 설정 정보를 담은 ProductConfigDTO 객체
     */
    @Override
    public Object getConfig() {
        // NTP 서버 호스트, 라이선스 ID, 키, 라이선스 데이터를 포함한 설정 정보 생성
        return new ProductConfigDTO(
                NtpServerProperties.host,        // NTP 서버 호스트 주소
                AppLicenseProperties.id,         // 애플리케이션 라이선스 ID
                AppLicenseProperties.key,        // 애플리케이션 라이선스 키
                AppLicenseProperties.license     // 애플리케이션 라이선스 데이터
        );
    }
}
