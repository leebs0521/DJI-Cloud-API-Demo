package com.dji.sample.wayline.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dji.sample.wayline.model.entity.WaylineFileEntity;

/**
 * DJI Cloud API 웨이라인 파일 데이터 접근 객체 (DAO)
 * 
 * 이 인터페이스는 웨이라인 파일 정보의 데이터베이스 접근을 담당하는 MyBatis-Plus 매퍼입니다.
 * 웨이라인 파일은 드론의 자동 비행 경로를 정의하는 KMZ 형식의 파일로,
 * 사용자가 업로드한 웨이라인 파일의 메타데이터와 파일 정보를 데이터베이스에 저장하고 관리합니다.
 * 
 * 주요 기능:
 * - 웨이라인 파일 정보의 CRUD(Create, Read, Update, Delete) 작업
 * - 웨이라인 파일 메타데이터 관리 (이름, 크기, 해시값, 템플릿 타입 등)
 * - 웨이라인 파일과 사용자, 워크스페이스 간의 관계 관리
 * - 웨이라인 파일 즐겨찾기 상태 관리
 * 
 * 상속 관계:
 * - BaseMapper<WaylineFileEntity>: MyBatis-Plus의 기본 CRUD 메서드들을 상속받음
 *   - insert(): 새로운 웨이라인 파일 정보 삽입
 *   - selectById(): ID로 웨이라인 파일 정보 조회
 *   - updateById(): ID로 웨이라인 파일 정보 업데이트
 *   - deleteById(): ID로 웨이라인 파일 정보 삭제
 *   - selectList(): 조건에 맞는 웨이라인 파일 목록 조회
 *   - selectPage(): 페이징 처리된 웨이라인 파일 목록 조회
 * 
 * 데이터베이스 테이블:
 * - wayline_file: 웨이라인 파일 정보를 저장하는 테이블
 *   - id: 웨이라인 파일 고유 ID (Primary Key)
 *   - name: 웨이라인 파일명
 *   - object_key: 클라우드 스토리지 객체 키
 *   - username: 업로드한 사용자명
 *   - workspace_id: 소속 워크스페이스 ID
 *   - template_types: 템플릿 타입 목록 (JSON 형태)
 *   - payload_model_keys: 페이로드 모델 키 목록 (JSON 형태)
 *   - drone_model_key: 드론 모델 키
 *   - is_favorite: 즐겨찾기 여부
 *   - create_time: 생성 시간
 *   - update_time: 수정 시간
 * 
 * @author sean
 * @version 0.3
 * @date 2021/12/22
 */
public interface IWaylineFileMapper extends BaseMapper<WaylineFileEntity> {
}
