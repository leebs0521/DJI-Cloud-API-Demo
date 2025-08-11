package com.dji.sample.map.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dji.sample.map.model.entity.FlightAreaPropertyEntity;

/**
 * 비행 영역 속성 데이터 접근 객체 (DAO) 인터페이스
 * 
 * DJI Cloud API의 비행 영역 속성 관리를 위한 데이터베이스 접근 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 비행 영역 속성 데이터 CRUD 작업
 *    - 비행 영역 속성 정보 생성 (Create)
 *    - 비행 영역 속성 정보 조회 (Read)
 *    - 비행 영역 속성 정보 수정 (Update)
 *    - 비행 영역 속성 정보 삭제 (Delete)
 * 
 * 2. MyBatis-Plus 기반 데이터 접근
 *    - BaseMapper를 상속받아 기본 CRUD 기능 제공
 *    - 자동 생성된 SQL 쿼리 활용
 *    - 타입 안전한 데이터베이스 접근
 *    - 성능 최적화된 쿼리 실행
 * 
 * 3. 비행 영역 속성 관리
 *    - 비행 영역의 상세 속성 정보 저장
 *    - 비행 영역 타입 및 분류 관리
 *    - 비행 영역 설정 및 옵션 관리
 *    - 비행 영역 메타데이터 관리
 * 
 * 4. 비행 안전 설정 지원
 *    - 비행 제한 영역 설정 관리
 *    - 비행 허용 영역 설정 관리
 *    - 비행 영역 우선순위 관리
 *    - 비행 영역 유효기간 관리
 * 
 * 주요 기능:
 * - 비행 영역 속성 저장
 * - 비행 영역 설정 관리
 * - 비행 안전 규칙 관리
 * - 비행 영역 메타데이터 관리
 * - 비행 영역 분류 및 태깅
 * 
 * 의존성:
 * - BaseMapper<FlightAreaPropertyEntity>: MyBatis-Plus 기본 매퍼
 * - FlightAreaPropertyEntity: 비행 영역 속성 데이터베이스 엔티티
 * 
 * 이 인터페이스는 DJI Cloud API의 비행 영역 속성 데이터를
 * 효율적으로 관리하는 DAO입니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/21
 */
public interface IFlightAreaPropertyMapper extends BaseMapper<FlightAreaPropertyEntity> {
}
