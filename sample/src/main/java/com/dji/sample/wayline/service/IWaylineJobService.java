package com.dji.sample.wayline.service;

import com.dji.sample.wayline.model.dto.WaylineJobDTO;
import com.dji.sample.wayline.model.enums.WaylineJobStatusEnum;
import com.dji.sample.wayline.model.param.CreateJobParam;
import com.dji.sdk.common.PaginationData;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * DJI Cloud API 웨이라인 작업 서비스 인터페이스
 * 
 * 이 인터페이스는 웨이라인 작업의 전체 라이프사이클을 관리하는 서비스 계층의 계약을 정의합니다.
 * 웨이라인 작업의 생성, 조회, 업데이트, 상태 관리 등의 기능을 제공하며,
 * 데이터베이스와의 연동을 통해 웨이라인 작업 정보를 효율적으로 관리합니다.
 * 
 * 주요 기능:
 * - 웨이라인 작업 생성 및 저장
 * - 부모 작업 기반 하위 작업 생성
 * - 조건별 웨이라인 작업 조회
 * - 웨이라인 작업 상세 정보 조회
 * - 웨이라인 작업 데이터 업데이트
 * - 워크스페이스별 웨이라인 작업 페이징 조회
 * - Dock의 웨이라인 실행 상태 조회
 * 
 * 사용 시나리오:
 * - 웨이라인 작업 생성 및 관리
 * - 웨이라인 작업 상태 추적
 * - 웨이라인 작업 이력 관리
 * - Dock 작업 상태 모니터링
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/1
 */
public interface IWaylineJobService {

    /**
     * 데이터베이스에 웨이라인 작업을 생성합니다.
     * 
     * @param param 웨이라인 작업 생성 파라미터
     * @param workspaceId 사용자 워크스페이스 정보
     * @param username 사용자 정보
     * @param beginTime 작업 시작 시간
     * @param endTime 작업 종료 시간
     * @return 생성된 웨이라인 작업 정보 (Optional로 래핑되어 있음)
     */
    Optional<WaylineJobDTO> createWaylineJob(CreateJobParam param, String workspaceId, String username, Long beginTime, Long endTime);

    /**
     * 부모 작업의 정보를 기반으로 하위 작업을 생성합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param parentId 부모 작업 ID
     * @return 생성된 하위 웨이라인 작업 정보 (Optional로 래핑되어 있음)
     */
    Optional<WaylineJobDTO> createWaylineJobByParent(String workspaceId, String parentId);

    /**
     * 조건에 따라 웨이라인 작업들을 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param jobIds 조회할 작업 ID 목록
     * @param status 조회할 작업 상태
     * @return 조건에 맞는 웨이라인 작업 목록
     */
    List<WaylineJobDTO> getJobsByConditions(String workspaceId, Collection<String> jobIds, WaylineJobStatusEnum status);

    /**
     * 작업 ID를 기반으로 작업 정보를 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param jobId 조회할 작업 ID
     * @return 웨이라인 작업 정보 (Optional로 래핑되어 있음)
     */
    Optional<WaylineJobDTO> getJobByJobId(String workspaceId, String jobId);

    /**
     * 작업 데이터를 업데이트합니다.
     * 
     * @param dto 업데이트할 웨이라인 작업 정보
     * @return 업데이트 성공 여부
     */
    Boolean updateJob(WaylineJobDTO dto);

    /**
     * 이 워크스페이스의 모든 작업을 페이징 처리하여 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param page 페이지 번호
     * @param pageSize 페이지 크기
     * @return 페이징 처리된 웨이라인 작업 목록
     */
    PaginationData<WaylineJobDTO> getJobsByWorkspaceId(String workspaceId, long page, long pageSize);

    /**
     * Dock의 웨이라인 실행 상태를 조회합니다.
     * 
     * @param dockSn Dock 시리얼 번호
     * @return Dock의 웨이라인 작업 상태
     */
    WaylineJobStatusEnum getWaylineState(String dockSn);
}
