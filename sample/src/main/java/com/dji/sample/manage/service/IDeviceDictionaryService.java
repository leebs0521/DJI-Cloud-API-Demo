package com.dji.sample.manage.service;

import com.dji.sample.manage.model.dto.DeviceDictionaryDTO;

import java.util.Optional;

/**
 * 디바이스 사전 관리 서비스 인터페이스
 * 
 * DJI Cloud API의 디바이스 사전 정보 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 타입 정보 관리
 *    - 도메인, 디바이스 타입, 서브 타입 기반 디바이스 정보 조회
 *    - 디바이스 사전 데이터 관리
 *    - 디바이스 분류 정보 제공
 * 
 * 2. 디바이스 분류 체계 관리
 *    - 디바이스 도메인별 분류 (AIRCRAFT, DOCK, RC 등)
 *    - 디바이스 타입별 분류 (M30, M300, M350 등)
 *    - 디바이스 서브 타입별 세부 분류
 * 
 * 3. 디바이스 정보 표준화
 *    - 디바이스 정보의 일관성 보장
 *    - 디바이스 분류 체계의 표준화
 *    - 디바이스 정보의 정규화
 * 
 * 이 인터페이스는 DJI 디바이스의 분류 정보를
 * 체계적으로 관리하고 조회할 수 있도록 지원합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/15
 */
public interface IDeviceDictionaryService {

    /**
     * 디바이스 타입 정보 조회
     * 
     * 도메인, 디바이스 타입, 서브 타입을 기반으로
     * 해당하는 디바이스의 사전 정보를 조회합니다.
     * 
     * @param domain 디바이스 도메인 (0: AIRCRAFT, 1: DOCK, 2: RC 등)
     * @param deviceType 디바이스 타입 (0: M30, 1: M300, 2: M350 등)
     * @param subType 디바이스 서브 타입 (세부 모델 구분)
     * @return 디바이스 사전 정보 (Optional)
     */
    Optional<DeviceDictionaryDTO> getOneDictionaryInfoByTypeSubType(Integer domain, Integer deviceType, Integer subType);

}
