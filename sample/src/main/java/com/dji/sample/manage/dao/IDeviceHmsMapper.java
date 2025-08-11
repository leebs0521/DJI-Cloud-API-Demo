package com.dji.sample.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dji.sample.manage.model.entity.DeviceHmsEntity;

/**
 * 디바이스 HMS 데이터 접근 객체 (DAO) 인터페이스
 * 
 * DJI Cloud API의 디바이스 HMS(Health Management System) 정보를 데이터베이스에서 관리하는 DAO 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. HMS 정보 관리
 *    - HMS 정보 조회, 삽입, 수정, 삭제 (CRUD)
 *    - HMS 메시지 ID 기반 조회
 *    - 디바이스별 HMS 메시지 목록 관리
 * 
 * 2. HMS 상태 관리
 *    - HMS 메시지 읽음/읽지 않음 상태 관리
 *    - HMS 메시지 우선순위 및 중요도 관리
 *    - HMS 메시지 처리 상태 추적
 * 
 * 3. HMS 메시지 분류 관리
 *    - HMS 메시지 타입별 분류 (경고, 오류, 정보 등)
 *    - HMS 메시지 도메인별 필터링
 *    - HMS 메시지 시간별 정렬 및 조회
 * 
 * 4. MyBatis-Plus 통합
 *    - MyBatis-Plus BaseMapper 상속으로 기본 CRUD 기능 제공
 *    - 조건부 쿼리 및 페이지네이션 지원
 *    - 자동 SQL 생성 및 실행
 * 
 * 이 인터페이스는 DeviceHmsEntity와 연동되어 HMS 테이블의
 * 모든 데이터베이스 작업을 처리하며, 디바이스의 건강 상태
 * 모니터링 및 알림 관리를 위한 데이터 접근을 제공합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/7/6
 */
public interface IDeviceHmsMapper extends BaseMapper<DeviceHmsEntity> {
}
