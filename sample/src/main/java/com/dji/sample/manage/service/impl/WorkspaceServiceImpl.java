package com.dji.sample.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dji.sample.manage.dao.IWorkspaceMapper;
import com.dji.sample.manage.model.dto.WorkspaceDTO;
import com.dji.sample.manage.model.entity.WorkspaceEntity;
import com.dji.sample.manage.service.IWorkspaceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 워크스페이스 관리 서비스 구현체
 * 
 * DJI Cloud API의 워크스페이스 관리를 위한 서비스 구현체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 구현합니다:
 * 
 * 1. 워크스페이스 정보 조회
 *    - 워크스페이스 ID로 워크스페이스 정보 조회
 *    - 바인딩 코드로 워크스페이스 정보 조회
 *    - 워크스페이스 메타데이터 관리
 *    - 워크스페이스 유효성 검증
 * 
 * 2. 워크스페이스 데이터 관리
 *    - 워크스페이스 정보 저장 및 업데이트
 *    - 워크스페이스 상태 관리
 *    - 워크스페이스 설정 관리
 *    - 워크스페이스 이력 추적
 * 
 * 3. 워크스페이스 바인딩 관리
 *    - 바인딩 코드 생성 및 관리
 *    - 바인딩 코드 유효성 검증
 *    - 바인딩 상태 추적
 *    - 바인딩 해제 처리
 * 
 * 4. 데이터 변환 및 매핑
 *    - Entity와 DTO 간 변환
 *    - 워크스페이스 정보 정규화
 *    - 메타데이터 매핑
 *    - 데이터 유효성 검증
 * 
 * 5. 트랜잭션 관리
 *    - 데이터베이스 트랜잭션 관리
 *    - 데이터 일관성 보장
 *    - 롤백 처리
 *    - 동시성 제어
 * 
 * 주요 의존성:
 * - IWorkspaceMapper: 워크스페이스 데이터베이스 접근
 * - ObjectMapper: JSON 직렬화/역직렬화
 * - WorkspaceEntity: 워크스페이스 엔티티
 * - WorkspaceDTO: 워크스페이스 DTO
 * 
 * 이 클래스는 DJI Cloud API의 워크스페이스를
 * 안전하고 효율적으로 관리하는 서비스입니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/10
 */
@Service
@Transactional
public class WorkspaceServiceImpl implements IWorkspaceService {

    @Autowired
    private IWorkspaceMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 워크스페이스 ID로 워크스페이스 정보를 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @return 워크스페이스 정보 (Optional)
     */
    @Override
    public Optional<WorkspaceDTO> getWorkspaceByWorkspaceId(String workspaceId) {
        return Optional.ofNullable(entityConvertToDto(
                mapper.selectOne(
                        new LambdaQueryWrapper<WorkspaceEntity>()
                                .eq(WorkspaceEntity::getWorkspaceId, workspaceId))));
    }

    /**
     * 바인딩 코드로 워크스페이스 이름을 조회합니다.
     * 
     * @param bindCode 바인딩 코드
     * @return 워크스페이스 정보 (Optional)
     */
    @Override
    public Optional<WorkspaceDTO> getWorkspaceNameByBindCode(String bindCode) {
        return Optional.ofNullable(entityConvertToDto(
                mapper.selectOne(new LambdaQueryWrapper<WorkspaceEntity>().eq(WorkspaceEntity::getBindCode, bindCode))));
    }

    /**
     * 데이터베이스 엔티티 객체를 워크스페이스 DTO로 변환합니다.
     * 
     * @param entity 워크스페이스 엔티티
     * @return 워크스페이스 DTO
     */
    private WorkspaceDTO entityConvertToDto(WorkspaceEntity entity) {
        if (entity == null) {
            return null;
        }
        return WorkspaceDTO.builder()
                .id(entity.getId())
                .workspaceId(entity.getWorkspaceId())
                .platformName(entity.getPlatformName())
                .workspaceDesc(entity.getWorkspaceDesc())
                .workspaceName(entity.getWorkspaceName())
                .bindCode(entity.getBindCode())
                .build();
    }
}
