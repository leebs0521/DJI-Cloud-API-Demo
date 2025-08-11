package com.dji.sample.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dji.sample.manage.model.entity.UserEntity;

/**
 * 사용자 데이터 접근 객체 (DAO) 인터페이스
 * 
 * DJI Cloud API의 사용자 정보를 데이터베이스에서 관리하는 DAO 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 사용자 정보 관리
 *    - 사용자 정보 조회, 삽입, 수정, 삭제 (CRUD)
 *    - 사용자 ID 기반 조회
 *    - 사용자 계정 정보 관리
 * 
 * 2. 사용자 인증 및 권한 관리
 *    - 사용자 로그인 정보 관리
 *    - 사용자 권한 및 역할 관리
 *    - 사용자 접근 권한 추적
 * 
 * 3. 사용자 프로필 관리
 *    - 사용자 개인정보 관리
 *    - 사용자 설정 및 환경설정 관리
 *    - 사용자 활동 이력 관리
 * 
 * 4. MyBatis-Plus 통합
 *    - MyBatis-Plus BaseMapper 상속으로 기본 CRUD 기능 제공
 *    - 조건부 쿼리 및 페이지네이션 지원
 *    - 자동 SQL 생성 및 실행
 * 
 * 이 인터페이스는 UserEntity와 연동되어 사용자 테이블의
 * 모든 데이터베이스 작업을 처리하며, 시스템 사용자의
 * 계정 및 권한 관리를 위한 데이터 접근을 제공합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/1/1
 */
public interface IUserMapper extends BaseMapper<UserEntity> {

}
