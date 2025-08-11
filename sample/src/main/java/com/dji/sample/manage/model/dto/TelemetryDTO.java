package com.dji.sample.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 텔레메트리 정보 전송 객체 (DTO) 클래스
 * 
 * DJI Cloud API의 텔레메트리 정보를 전송하기 위한 제네릭 데이터 전송 객체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 텔레메트리 호스트 정보 관리
 *    - 제네릭 타입을 통한 다양한 호스트 정보 관리
 *    - 텔레메트리 데이터의 유연한 타입 지원
 *    - 호스트별 텔레메트리 정보 분류
 * 
 * 2. 디바이스 식별 정보 관리
 *    - 디바이스 시리얼 번호 관리
 *    - 텔레메트리 데이터와 디바이스 연결
 *    - 디바이스별 텔레메트리 추적
 * 
 * 3. 제네릭 타입 지원
 *    - 다양한 텔레메트리 데이터 타입 지원
 *    - 타입 안전성 보장
 *    - 재사용 가능한 텔레메트리 구조 제공
 * 
 * 4. API 통신 지원
 *    - Lombok 어노테이션을 통한 자동 getter/setter 생성
 *    - 빌더 패턴을 통한 편리한 객체 생성
 *    - API 요청/응답에서 사용되는 표준화된 형식 제공
 * 
 * 이 클래스는 다양한 타입의 텔레메트리 데이터를 처리할 수 있으며,
 * 디바이스의 실시간 상태 정보를 전송하는 표준화된
 * 데이터 형식을 제공합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/8
 * @param <T> 텔레메트리 호스트 정보의 타입
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelemetryDTO<T> {

    /**
     * 텔레메트리 호스트 정보
     * 텔레메트리 데이터의 호스트 정보 (제네릭 타입)
     */
    private T host;

    /**
     * 디바이스 시리얼 번호
     * 텔레메트리 데이터를 생성한 디바이스의 시리얼 번호
     */
    private String sn;
}
