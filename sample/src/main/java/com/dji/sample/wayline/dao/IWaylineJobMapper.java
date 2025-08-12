package com.dji.sample.wayline.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dji.sample.wayline.model.entity.WaylineJobEntity;

/**
 * DJI Cloud API 웨이라인 작업 데이터 접근 객체 (DAO)
 * 
 * 이 인터페이스는 웨이라인 작업 정보의 데이터베이스 접근을 담당하는 MyBatis-Plus 매퍼입니다.
 * 웨이라인 작업은 미리 정의된 웨이라인 파일을 기반으로 드론이 자동 비행을 수행하는 작업으로,
 * 작업의 생성, 실행, 모니터링, 완료 등의 전체 라이프사이클을 관리합니다.
 * 
 * 주요 기능:
 * - 웨이라인 작업 정보의 CRUD(Create, Read, Update, Delete) 작업
 * - 웨이라인 작업 상태 관리 (대기 중, 실행 중, 완료, 실패, 취소 등)
 * - 웨이라인 작업 진행률 추적 및 업데이트
 * - 웨이라인 작업과 Dock, 드론 간의 연관 관계 관리
 * - 웨이라인 작업 실행 이력 및 로그 관리
 * 
 * 상속 관계:
 * - BaseMapper<WaylineJobEntity>: MyBatis-Plus의 기본 CRUD 메서드들을 상속받음
 *   - insert(): 새로운 웨이라인 작업 정보 삽입
 *   - selectById(): ID로 웨이라인 작업 정보 조회
 *   - updateById(): ID로 웨이라인 작업 정보 업데이트
 *   - deleteById(): ID로 웨이라인 작업 정보 삭제
 *   - selectList(): 조건에 맞는 웨이라인 작업 목록 조회
 *   - selectPage(): 페이징 처리된 웨이라인 작업 목록 조회
 * 
 * 데이터베이스 테이블:
 * - wayline_job: 웨이라인 작업 정보를 저장하는 테이블
 *   - id: 웨이라인 작업 고유 ID (Primary Key)
 *   - name: 웨이라인 작업명
 *   - wayline_file_id: 연관된 웨이라인 파일 ID (Foreign Key)
 *   - workspace_id: 소속 워크스페이스 ID
 *   - username: 작업 생성자 사용자명
 *   - status: 작업 상태 (WAITING, RUNNING, COMPLETED, FAILED, CANCELLED 등)
 *   - progress: 작업 진행률 (0-100%)
 *   - dock_sn: 실행할 Dock의 시리얼 번호
 *   - drone_sn: 실행할 드론의 시리얼 번호
 *   - start_time: 작업 시작 시간
 *   - end_time: 작업 완료 시간
 *   - error_message: 오류 발생 시 오류 메시지
 *   - create_time: 생성 시간
 *   - update_time: 수정 시간
 * 
 * 작업 상태 설명:
 * - WAITING: 작업이 생성되었으나 아직 실행되지 않은 상태
 * - RUNNING: 작업이 현재 실행 중인 상태
 * - PAUSED: 작업이 일시 정지된 상태
 * - COMPLETED: 작업이 성공적으로 완료된 상태
 * - FAILED: 작업 실행 중 오류가 발생하여 실패한 상태
 * - CANCELLED: 사용자에 의해 작업이 취소된 상태
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/1
 */
public interface IWaylineJobMapper extends BaseMapper<WaylineJobEntity> {
}
