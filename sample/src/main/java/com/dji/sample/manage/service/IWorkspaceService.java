package com.dji.sample.manage.service;


import com.dji.sample.manage.model.dto.WorkspaceDTO;

import java.util.Optional;

/**
 * 워크스페이스 관리 서비스 인터페이스
 * 
 * DJI Cloud API의 워크스페이스 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 워크스페이스 정보 관리
 *    - 워크스페이스 ID 기반 워크스페이스 정보 조회
 *    - 워크스페이스 기본 정보 관리
 *    - 워크스페이스 설정 정보 제공
 * 
 * 2. 워크스페이스 바인딩 관리
 *    - 바인딩 코드 기반 워크스페이스 조회
 *    - 디바이스-워크스페이스 연결 관리
 *    - 워크스페이스 접근 권한 확인
 * 
 * 3. 워크스페이스 식별 및 검증
 *    - 워크스페이스 존재 여부 확인
 *    - 워크스페이스 유효성 검증
 *    - 워크스페이스 접근 권한 검증
 * 
 * 이 인터페이스는 DJI Cloud API의 워크스페이스를
 * 체계적으로 관리하고 조회할 수 있도록 지원합니다.
 */
public interface IWorkspaceService {

    /**
     * 워크스페이스 ID로 워크스페이스 정보 조회
     * 
     * 워크스페이스 ID를 기반으로 해당 워크스페이스의
     * 상세 정보를 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @return 워크스페이스 정보 (Optional)
     */
    Optional<WorkspaceDTO> getWorkspaceByWorkspaceId(String workspaceId);

    /**
     * 바인딩 코드로 워크스페이스 정보 조회
     * 
     * 바인딩 코드를 기반으로 해당하는 워크스페이스의
     * 정보를 조회합니다.
     * 
     * @param bindCode 바인딩 코드
     * @return 워크스페이스 정보 (Optional)
     */
    Optional<WorkspaceDTO> getWorkspaceNameByBindCode(String bindCode);

}
