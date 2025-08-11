package com.dji.sample.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dji.sample.manage.model.entity.DeviceEntity;

/**
 * 디바이스 데이터 접근 객체 (DAO) 인터페이스
 * 
 * DJI Cloud API의 디바이스 정보를 데이터베이스에서 관리하는 DAO 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 정보 관리
 *    - 디바이스 정보 조회, 삽입, 수정, 삭제 (CRUD)
 *    - 디바이스 시리얼 번호 기반 조회
 *    - 워크스페이스별 디바이스 목록 조회
 * 
 * 2. 디바이스 상태 관리
 *    - 디바이스 온라인/오프라인 상태 추적
 *    - 디바이스 바인딩 상태 관리
 *    - 디바이스 모드 및 상태 정보 관리
 * 
 * 3. 디바이스 메타데이터 관리
 *    - 디바이스 이름, 설명 등 메타데이터 관리
 *    - 디바이스 타입 및 모델 정보 관리
 *    - 디바이스 설정 정보 관리
 * 
 * 4. MyBatis-Plus 통합
 *    - MyBatis-Plus BaseMapper 상속으로 기본 CRUD 기능 제공
 *    - 조건부 쿼리 및 페이지네이션 지원
 *    - 자동 SQL 생성 및 실행
 * 
 * 이 인터페이스는 DeviceEntity와 연동되어 디바이스 테이블의
 * 모든 데이터베이스 작업을 처리합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/10
 * @version 0.1
 */
public interface IDeviceMapper extends BaseMapper<DeviceEntity> {

}