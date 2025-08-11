package com.dji.sample.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 용량 디바이스 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 용량 디바이스 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 기본 정보 관리
 *    - 디바이스 시리얼 번호 관리
 *    - 디바이스 이름 관리
 *    - 디바이스 식별자 및 메타데이터 관리
 * 
 * 2. 카메라 목록 관리
 *    - 디바이스에 연결된 카메라 목록 관리
 *    - 카메라 정보 제공
 *    - 디바이스별 카메라 용량 관리
 * 
 * 3. 용량 정보 통합 관리
 *    - 디바이스 전체 용량 정보 관리
 *    - 카메라별 용량 정보 통합
 *    - 디바이스 용량 추적 및 모니터링
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - 빌더 패턴을 통한 편리한 객체 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 * 
 * 이 클래스는 디바이스의 용량 정보와 카메라 목록을
 * 관리하며, 디바이스별 용량을 추적하는 표준화된
 * 데이터 형식을 제공합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/22
 * @version 0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CapacityDeviceDTO {

    /**
     * 디바이스 시리얼 번호
     * 디바이스를 고유하게 식별하는 시리얼 번호
     */
    private String sn;

    /**
     * 디바이스 이름
     * 디바이스의 표시 이름
     */
    private String name;

    /**
     * 카메라 목록
     * 디바이스에 연결된 카메라들의 목록
     */
    private List<CapacityCameraDTO> camerasList;
}