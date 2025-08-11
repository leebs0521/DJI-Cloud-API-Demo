package com.dji.sample.map.service;

import com.dji.sdk.cloudapi.map.GetMapElementsResponse;

import java.util.List;
import java.util.Optional;

/**
 * 그룹 서비스 인터페이스
 * 
 * DJI Cloud API의 맵 그룹 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 워크스페이스별 그룹 조회 및 관리 기능을 제공합니다.
 * 
 * 1. 그룹 조회 기능
 *    - 워크스페이스별 모든 그룹 조회 (getAllGroupsByWorkspaceId)
 *    - 커스텀 비행 영역 그룹 조회 (getCustomGroupByWorkspaceId)
 * 
 * 2. 그룹 필터링 기능
 *    - 그룹 ID 기반 필터링
 *    - 분산 처리 여부 기반 필터링
 * 
 * 3. 워크스페이스별 그룹 관리
 *    - 워크스페이스 내 그룹 구조 관리
 *    - 커스텀 그룹 식별 및 관리
 * 
 * 주요 용도:
 * - 워크스페이스별 그룹 구조 조회
 * - 그룹별 맵 요소 분류 관리
 * - 커스텀 비행 영역 그룹 관리
 * - 그룹 기반 접근 제어
 * 
 * 사용 예시:
 * - 워크스페이스 내 모든 그룹 조회
 * - 특정 그룹 필터링
 * - 커스텀 비행 영역 그룹 접근
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/29
 */
public interface IGroupService {

    /**
     * 워크스페이스 ID를 기반으로 해당 워크스페이스의 모든 그룹들을 조회합니다.
     * 그룹 ID가 존재하지 않는 경우 해당 필터 조건을 추가하지 않습니다.
     * 
     * @param workspaceId 조회할 워크스페이스의 고유 식별자
     * @param groupId 필터링할 그룹의 고유 식별자 (선택사항)
     * @param isDistributed 그룹의 분산 처리 여부를 정의하는 데 사용됩니다. 기본값은 true입니다.
     * @return 워크스페이스에 속한 그룹 목록
     */
    List<GetMapElementsResponse> getAllGroupsByWorkspaceId(String workspaceId, String groupId, Boolean isDistributed);

    /**
     * 해당 워크스페이스의 커스텀 비행 영역 그룹을 조회합니다.
     * 
     * @param workspaceId 조회할 워크스페이스의 고유 식별자
     * @return 커스텀 비행 영역 그룹 정보를 포함하는 Optional 객체
     */
    Optional<GetMapElementsResponse> getCustomGroupByWorkspaceId(String workspaceId);
}
