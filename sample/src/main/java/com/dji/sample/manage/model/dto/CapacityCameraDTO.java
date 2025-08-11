package com.dji.sample.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 용량 카메라 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 용량 카메라 정보를 전송하기 위한 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 카메라 기본 정보 관리
 *    - 카메라 ID 및 이름 관리
 *    - 카메라 인덱스 및 타입 관리
 *    - 카메라 식별자 및 메타데이터 관리
 * 
 * 2. 디바이스 연결 정보 관리
 *    - 디바이스 시리얼 번호 관리
 *    - 카메라와 디바이스 연결 정보
 *    - 디바이스별 카메라 관리
 * 
 * 3. 비디오 스트림 정보 관리
 *    - 카메라별 비디오 스트림 목록 관리
 *    - 비디오 스트림 정보 제공
 *    - 카메라별 비디오 용량 관리
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - 빌더 패턴을 통한 편리한 객체 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 * 
 * 이 클래스는 카메라의 용량 정보와 비디오 스트림 정보를
 * 관리하며, 카메라별 비디오 용량을 추적하는 표준화된
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
public class CapacityCameraDTO {

    /**
     * 카메라 ID
     * 카메라를 고유하게 식별하는 ID
     */
    private String id;

    /**
     * 디바이스 시리얼 번호
     * 카메라가 연결된 디바이스의 시리얼 번호
     */
    private String deviceSn;

    /**
     * 카메라 이름
     * 카메라의 표시 이름
     */
    private String name;

    /**
     * 카메라 인덱스
     * 카메라의 순서 또는 위치 인덱스
     */
    private String index;

    /**
     * 카메라 타입
     * 카메라의 타입 정보
     */
    private String type;

    /**
     * 비디오 스트림 목록
     * 카메라에서 제공하는 비디오 스트림들의 목록
     */
    private List<CapacityVideoDTO> videosList;
}