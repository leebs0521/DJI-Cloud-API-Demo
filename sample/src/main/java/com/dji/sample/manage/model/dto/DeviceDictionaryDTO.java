package com.dji.sample.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 디바이스 사전 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 디바이스 사전 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 분류 정보 관리
 *    - 디바이스 도메인 정보 관리
 *    - 디바이스 타입 및 서브타입 관리
 *    - 디바이스 계층적 분류 구조 관리
 * 
 * 2. 디바이스 식별 정보 관리
 *    - 디바이스 이름 및 설명 관리
 *    - 디바이스 표준화된 식별 정보 제공
 *    - 디바이스 메타데이터 관리
 * 
 * 3. 디바이스 표준화 지원
 *    - 디바이스 정보의 표준화 및 정규화
 *    - 디바이스 분류 체계 관리
 *    - 디바이스 정보 일관성 보장
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - 빌더 패턴을 통한 편리한 객체 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 * 
 * 이 클래스는 디바이스 정보의 표준화 및 분류를 위한 모든 정보를
 * 포함하며, 디바이스 사전 데이터를 관리하는 표준화된
 * 데이터 형식을 제공합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/23
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeviceDictionaryDTO {

    /**
     * 디바이스 도메인
     * 디바이스가 속한 도메인 (예: AIRCRAFT=0, DOCK=1 등)
     */
    private Integer domain;

    /**
     * 디바이스 타입
     * 디바이스의 주요 타입 (예: AIRCRAFT=0, DOCK=1, RC=2 등)
     */
    private Integer deviceType;

    /**
     * 디바이스 서브타입
     * 디바이스의 세부 타입 (예: M30=0, M300=1 등)
     */
    private Integer subType;

    /**
     * 디바이스 이름
     * 디바이스의 표준화된 모델명
     */
    private String deviceName;

    /**
     * 디바이스 설명
     * 디바이스에 대한 추가 설명 정보
     */
    private String deviceDesc;
}