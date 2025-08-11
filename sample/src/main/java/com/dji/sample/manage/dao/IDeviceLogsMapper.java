package com.dji.sample.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dji.sample.manage.model.entity.DeviceLogsEntity;

/**
 * 디바이스 로그 데이터 접근 객체 (DAO) 인터페이스
 * 
 * DJI Cloud API의 디바이스 로그 정보를 데이터베이스에서 관리하는 DAO 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 정보 관리
 *    - 로그 정보 조회, 삽입, 수정, 삭제 (CRUD)
 *    - 로그 ID 기반 조회
 *    - 디바이스별 로그 목록 관리
 * 
 * 2. 로그 업로드 관리
 *    - 로그 파일 업로드 상태 추적
 *    - 업로드 진행률 및 상태 관리
 *    - 업로드 완료/실패 이력 관리
 * 
 * 3. 로그 메타데이터 관리
 *    - 로그 파일 크기, 생성 시간 등 메타데이터 관리
 *    - 로그 파일 도메인별 분류
 *    - 로그 파일 다운로드 URL 관리
 * 
 * 4. MyBatis-Plus 통합
 *    - MyBatis-Plus BaseMapper 상속으로 기본 CRUD 기능 제공
 *    - 조건부 쿼리 및 페이지네이션 지원
 *    - 자동 SQL 생성 및 실행
 * 
 * 이 인터페이스는 DeviceLogsEntity와 연동되어 로그 테이블의
 * 모든 데이터베이스 작업을 처리하며, 디바이스 로그 파일의
 * 업로드 및 관리를 위한 데이터 접근을 제공합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
public interface IDeviceLogsMapper extends BaseMapper<DeviceLogsEntity> {
}
