package com.dji.sample.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dji.sample.manage.model.entity.DevicePayloadEntity;

/**
 * 디바이스 페이로드 데이터 접근 객체 (DAO) 인터페이스
 * 
 * DJI Cloud API의 디바이스 페이로드 정보를 데이터베이스에서 관리하는 DAO 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 페이로드 정보 관리
 *    - 페이로드 정보 조회, 삽입, 수정, 삭제 (CRUD)
 *    - 페이로드 인덱스 기반 조회
 *    - 디바이스별 페이로드 목록 관리
 * 
 * 2. 페이로드 권한 관리
 *    - 페이로드 제어 권한 상태 추적
 *    - 권한 획득/해제 이력 관리
 *    - 권한 상태 변경 로그 관리
 * 
 * 3. 페이로드 장치 정보 관리
 *    - 카메라, 짐벌 등 페이로드 장치 정보 관리
 *    - 페이로드 장치 상태 및 설정 정보 관리
 *    - 페이로드 장치 메타데이터 관리
 * 
 * 4. MyBatis-Plus 통합
 *    - MyBatis-Plus BaseMapper 상속으로 기본 CRUD 기능 제공
 *    - 조건부 쿼리 및 페이지네이션 지원
 *    - 자동 SQL 생성 및 실행
 * 
 * 이 인터페이스는 DevicePayloadEntity와 연동되어 페이로드 테이블의
 * 모든 데이터베이스 작업을 처리하며, 디바이스의 페이로드 장치
 * 제어 및 권한 관리를 위한 데이터 접근을 제공합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/19
 * @version 0.1
 */
public interface IDevicePayloadMapper extends BaseMapper<DevicePayloadEntity> {
}
