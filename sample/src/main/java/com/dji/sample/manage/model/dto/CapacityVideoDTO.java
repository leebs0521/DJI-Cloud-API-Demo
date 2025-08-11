package com.dji.sample.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 용량 비디오 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 용량 비디오 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 비디오 기본 정보 관리
 *    - 비디오 ID 및 인덱스 관리
 *    - 비디오 타입 관리
 *    - 비디오 식별자 및 메타데이터 관리
 * 
 * 2. 비디오 스위치 타입 관리
 *    - 비디오 스위치 타입 목록 관리
 *    - 비디오 전환 가능한 타입들 제공
 *    - 비디오 스위치 옵션 관리
 * 
 * 3. 비디오 용량 정보 관리
 *    - 비디오 스트림 용량 정보 관리
 *    - 비디오 품질별 용량 관리
 *    - 비디오 용량 추적 및 모니터링
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - 빌더 패턴을 통한 편리한 객체 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 * 
 * 이 클래스는 비디오 스트림의 용량 정보와 스위치 옵션을
 * 관리하며, 비디오별 용량을 추적하는 표준화된
 * 데이터 형식을 제공합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/22
 * @version 0.1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CapacityVideoDTO {

    /**
     * 비디오 ID
     * 비디오 스트림을 고유하게 식별하는 ID
     */
    private String id;

    /**
     * 비디오 인덱스
     * 비디오 스트림의 순서 또는 위치 인덱스
     */
    private String index;

    /**
     * 비디오 타입
     * 비디오 스트림의 타입 정보
     */
    private String type;

    /**
     * 비디오 스위치 타입 목록
     * 이 비디오에서 전환 가능한 비디오 타입들의 목록
     */
    private List<String> switchVideoTypes;
}