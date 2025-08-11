package com.dji.sample.map.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dji.sample.map.model.entity.ElementCoordinateEntity;

/**
 * 요소 좌표 데이터 접근 객체 (DAO) 인터페이스
 * 
 * DJI Cloud API의 맵 요소 좌표 관리를 위한 데이터베이스 접근 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 요소 좌표 데이터 CRUD 작업
 *    - 요소 좌표 정보 생성 (Create)
 *    - 요소 좌표 정보 조회 (Read)
 *    - 요소 좌표 정보 수정 (Update)
 *    - 요소 좌표 정보 삭제 (Delete)
 * 
 * 2. MyBatis-Plus 기반 데이터 접근
 *    - BaseMapper를 상속받아 기본 CRUD 기능 제공
 *    - 자동 생성된 SQL 쿼리 활용
 *    - 타입 안전한 데이터베이스 접근
 *    - 성능 최적화된 쿼리 실행
 * 
 * 3. 맵 요소 좌표 관리
 *    - 점, 선, 다각형 등의 맵 요소 좌표 저장
 *    - 요소별 좌표 시퀀스 관리
 *    - 좌표계 정보 관리 (WGS84, UTM 등)
 *    - 좌표 정밀도 및 정확도 관리
 * 
 * 4. 지리적 데이터 처리 지원
 *    - 위도/경도 좌표 저장 및 조회
 *    - 고도 정보 관리
 *    - 좌표 변환 및 투영 지원
 *    - 지리적 계산 및 분석 지원
 * 
 * 주요 기능:
 * - 맵 요소 좌표 저장
 * - 요소별 좌표 목록 조회
 * - 좌표 순서 및 시퀀스 관리
 * - 좌표계 변환 지원
 * - 지리적 데이터 검증
 * 
 * 의존성:
 * - BaseMapper<ElementCoordinateEntity>: MyBatis-Plus 기본 매퍼
 * - ElementCoordinateEntity: 요소 좌표 데이터베이스 엔티티
 * 
 * 이 인터페이스는 DJI Cloud API의 맵 요소 좌표 데이터를
 * 효율적으로 관리하는 DAO입니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/29
 */
public interface IElementCoordinateMapper extends BaseMapper<ElementCoordinateEntity> {
}
