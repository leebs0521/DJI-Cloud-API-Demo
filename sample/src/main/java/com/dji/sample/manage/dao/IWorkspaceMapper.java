package com.dji.sample.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dji.sample.manage.model.entity.WorkspaceEntity;

/**
 * 워크스페이스 데이터 접근 객체 (DAO) 인터페이스
 * 
 * DJI Cloud API의 워크스페이스 정보를 데이터베이스에서 관리하는 DAO 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 워크스페이스 정보 관리
 *    - 워크스페이스 정보 조회, 삽입, 수정, 삭제 (CRUD)
 *    - 워크스페이스 ID 기반 조회
 *    - 워크스페이스 계층 구조 관리
 * 
 * 2. 워크스페이스 구성 관리
 *    - 워크스페이스 내 디바이스 그룹 관리
 *    - 워크스페이스 권한 및 접근 제어
 *    - 워크스페이스 설정 및 환경설정 관리
 * 
 * 3. 워크스페이스 멤버 관리
 *    - 워크스페이스 멤버 추가/제거
 *    - 멤버별 권한 및 역할 관리
 *    - 워크스페이스 활동 이력 관리
 * 
 * 4. MyBatis-Plus 통합
 *    - MyBatis-Plus BaseMapper 상속으로 기본 CRUD 기능 제공
 *    - 조건부 쿼리 및 페이지네이션 지원
 *    - 자동 SQL 생성 및 실행
 * 
 * 이 인터페이스는 WorkspaceEntity와 연동되어 워크스페이스 테이블의
 * 모든 데이터베이스 작업을 처리하며, 사용자 그룹의
 * 작업 공간 관리를 위한 데이터 접근을 제공합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/1/1
 */
public interface IWorkspaceMapper extends BaseMapper<WorkspaceEntity> {

}
