package com.dji.sample.map.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dji.sample.map.model.entity.GroupEntity;

/**
 * 그룹 데이터 접근 객체 (DAO) 인터페이스
 * 
 * DJI Cloud API의 맵 그룹 관리를 위한 데이터베이스 접근 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 그룹 데이터 CRUD 작업
 *    - 그룹 정보 생성 (Create)
 *    - 그룹 정보 조회 (Read)
 *    - 그룹 정보 수정 (Update)
 *    - 그룹 정보 삭제 (Delete)
 * 
 * 2. MyBatis-Plus 기반 데이터 접근
 *    - BaseMapper를 상속받아 기본 CRUD 기능 제공
 *    - 자동 생성된 SQL 쿼리 활용
 *    - 타입 안전한 데이터베이스 접근
 *    - 성능 최적화된 쿼리 실행
 * 
 * 3. 그룹 엔티티 관리
 *    - GroupEntity 객체와 데이터베이스 테이블 매핑
 *    - 그룹 메타데이터 관리
 *    - 그룹 속성 정보 저장 및 조회
 *    - 그룹 상태 정보 관리
 * 
 * 4. 맵 요소 그룹화 지원
 *    - 맵 요소들의 논리적 그룹화
 *    - 그룹별 요소 분류 및 관리
 *    - 그룹 기반 요소 접근 제어
 *    - 그룹별 권한 관리
 * 
 * 주요 기능:
 * - 그룹 생성 및 등록
 * - 그룹 정보 조회 및 검색
 * - 그룹 정보 수정 및 업데이트
 * - 그룹 삭제 및 정리
 * - 그룹별 요소 관리
 * 
 * 의존성:
 * - BaseMapper<GroupEntity>: MyBatis-Plus 기본 매퍼
 * - GroupEntity: 그룹 데이터베이스 엔티티
 * 
 * 이 인터페이스는 DJI Cloud API의 맵 그룹 데이터를
 * 효율적으로 관리하는 DAO입니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/29
 */
public interface IGroupMapper extends BaseMapper<GroupEntity> {
}
