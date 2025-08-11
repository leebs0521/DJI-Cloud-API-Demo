package com.dji.sample.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dji.sample.manage.model.entity.FirmwareModelEntity;

/**
 * 펌웨어 모델 데이터 접근 객체 (DAO) 인터페이스
 * 
 * DJI Cloud API의 펌웨어 모델 정보를 데이터베이스에서 관리하는 DAO 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 펌웨어 모델 정보 관리
 *    - 펌웨어 모델 정보 조회, 삽입, 수정, 삭제 (CRUD)
 *    - 펌웨어 ID 기반 모델 조회
 *    - 디바이스 모델별 펌웨어 호환성 관리
 * 
 * 2. 펌웨어-모델 관계 관리
 *    - 펌웨어와 디바이스 모델 간의 매핑 관계 관리
 *    - 펌웨어 지원 디바이스 목록 관리
 *    - 펌웨어 호환성 정보 관리
 * 
 * 3. 펌웨어 모델 검색 및 필터링
 *    - 디바이스 모델명 기반 펌웨어 검색
 *    - 펌웨어 버전별 모델 지원 정보 조회
 *    - 펌웨어 모델별 정렬 및 조회
 * 
 * 4. MyBatis-Plus 통합
 *    - MyBatis-Plus BaseMapper 상속으로 기본 CRUD 기능 제공
 *    - 조건부 쿼리 및 페이지네이션 지원
 *    - 자동 SQL 생성 및 실행
 * 
 * 이 인터페이스는 FirmwareModelEntity와 연동되어 펌웨어 모델 테이블의
 * 모든 데이터베이스 작업을 처리하며, 펌웨어와 디바이스 모델 간의
 * 호환성 정보를 관리하는 데이터 접근을 제공합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/12/21
 */
public interface IFirmwareModelMapper extends BaseMapper<FirmwareModelEntity> {
}
