package com.dji.sample.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dji.sample.manage.model.entity.DeviceDictionaryEntity;

/**
 * 디바이스 사전 데이터 접근 객체 (DAO) 인터페이스
 * 
 * DJI Cloud API의 디바이스 사전 정보를 데이터베이스에서 관리하는 DAO 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 사전 정보 관리
 *    - 디바이스 사전 정보 조회, 삽입, 수정, 삭제 (CRUD)
 *    - 디바이스 사전 ID 기반 조회
 *    - 디바이스 타입별 사전 정보 관리
 * 
 * 2. 디바이스 표준화 관리
 *    - 디바이스 모델명 표준화 및 정규화
 *    - 디바이스 제조사 정보 관리
 *    - 디바이스 사양 및 스펙 정보 관리
 * 
 * 3. 디바이스 분류 및 카테고리 관리
 *    - 디바이스 카테고리별 분류 관리
 *    - 디바이스 등급 및 성능 등급 관리
 *    - 디바이스 호환성 정보 관리
 * 
 * 4. MyBatis-Plus 통합
 *    - MyBatis-Plus BaseMapper 상속으로 기본 CRUD 기능 제공
 *    - 조건부 쿼리 및 페이지네이션 지원
 *    - 자동 SQL 생성 및 실행
 * 
 * 이 인터페이스는 DeviceDictionaryEntity와 연동되어 디바이스 사전 테이블의
 * 모든 데이터베이스 작업을 처리하며, 디바이스 정보의
 * 표준화 및 분류 관리를 위한 데이터 접근을 제공합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/15
 * @version 0.1
 */
public interface IDeviceDictionaryMapper extends BaseMapper<DeviceDictionaryEntity> {
}
