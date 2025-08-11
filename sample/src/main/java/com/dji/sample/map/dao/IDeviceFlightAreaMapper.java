package com.dji.sample.map.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dji.sample.map.model.entity.DeviceFlightAreaEntity;

/**
 * 디바이스 비행 영역 데이터 접근 객체 (DAO) 인터페이스
 * 
 * DJI Cloud API의 디바이스 비행 영역 관리를 위한 데이터베이스 접근 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 비행 영역 데이터 CRUD 작업
 *    - 디바이스 비행 영역 정보 생성 (Create)
 *    - 디바이스 비행 영역 정보 조회 (Read)
 *    - 디바이스 비행 영역 정보 수정 (Update)
 *    - 디바이스 비행 영역 정보 삭제 (Delete)
 * 
 * 2. MyBatis-Plus 기반 데이터 접근
 *    - BaseMapper를 상속받아 기본 CRUD 기능 제공
 *    - 자동 생성된 SQL 쿼리 활용
 *    - 타입 안전한 데이터베이스 접근
 *    - 성능 최적화된 쿼리 실행
 * 
 * 3. 디바이스-비행영역 매핑 관리
 *    - 디바이스와 비행 영역 간의 관계 관리
 *    - 디바이스별 비행 영역 할당 정보 저장
 *    - 비행 영역별 디바이스 목록 관리
 *    - 디바이스 비행 영역 상태 추적
 * 
 * 4. 비행 안전 관리 지원
 *    - 디바이스별 비행 제한 영역 관리
 *    - 비행 영역 동기화 상태 추적
 *    - 디바이스 비행 권한 관리
 *    - 비행 영역 위반 감지 지원
 * 
 * 주요 기능:
 * - 디바이스 비행 영역 할당
 * - 디바이스별 비행 영역 조회
 * - 비행 영역 동기화 상태 관리
 * - 디바이스 비행 권한 검증
 * - 비행 영역 위반 이력 관리
 * 
 * 의존성:
 * - BaseMapper<DeviceFlightAreaEntity>: MyBatis-Plus 기본 매퍼
 * - DeviceFlightAreaEntity: 디바이스 비행 영역 데이터베이스 엔티티
 * 
 * 이 인터페이스는 DJI Cloud API의 디바이스 비행 영역 데이터를
 * 효율적으로 관리하는 DAO입니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/23
 */
public interface IDeviceFlightAreaMapper extends BaseMapper<DeviceFlightAreaEntity> {
}
