package com.dji.sample.map.service;

import com.dji.sample.map.model.dto.GroupElementDTO;
import com.dji.sdk.cloudapi.map.*;
import com.dji.sdk.common.HttpResultResponse;

import java.util.List;
import java.util.Optional;

/**
 * 워크스페이스 요소 서비스 인터페이스
 * 
 * DJI Cloud API의 워크스페이스 요소 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 워크스페이스 내 맵 요소들의 CRUD 작업과 실시간 알림 기능을 제공합니다.
 * 
 * 1. 워크스페이스 요소 조회 기능
 *    - 워크스페이스별 그룹 및 요소 조회 (getAllGroupsByWorkspaceId)
 *    - 개별 요소 상세 조회 (getElementByElementId)
 * 
 * 2. 워크스페이스 요소 관리 기능
 *    - 요소 생성 및 저장 (saveElement)
 *    - 요소 수정 (updateElement)
 *    - 요소 삭제 (deleteElement)
 *    - 그룹별 모든 요소 삭제 (deleteAllElementByGroupId)
 * 
 * 3. 실시간 알림 기능
 *    - WebSocket을 통한 실시간 요소 변경 알림
 *    - 다중 사용자 간 요소 동기화
 * 
 * 4. WebSocket 응답 변환 기능
 *    - 요소 생성 WebSocket 응답 변환 (element2CreateWsElement)
 *    - 요소 수정 WebSocket 응답 변환 (element2UpdateWsElement)
 * 
 * 주요 용도:
 * - 워크스페이스별 맵 요소 CRUD 작업 처리
 * - 실시간 요소 변경 알림 및 동기화
 * - 그룹별 요소 일괄 관리
 * - WebSocket 통신 지원
 * 
 * 사용 예시:
 * - 워크스페이스 내 맵 요소 생성/수정/삭제
 * - 실시간 협업을 위한 요소 동기화
 * - 그룹별 요소 일괄 처리
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/30
 */
public interface IWorkspaceElementService {

    /**
     * 워크스페이스 ID를 기반으로 해당 워크스페이스의 모든 그룹들을 조회합니다.
     * 그룹 내 요소들의 정보와 요소 내 좌표 정보를 포함합니다.
     * 
     * @param workspaceId 조회할 워크스페이스의 고유 식별자
     * @param groupId 필터링할 그룹의 고유 식별자 (선택사항)
     * @param isDistributed 그룹의 분산 처리 여부
     * @return 워크스페이스에 속한 그룹 및 요소 목록
     */
    List<GetMapElementsResponse> getAllGroupsByWorkspaceId(String workspaceId, String groupId, Boolean isDistributed);

    /**
     * 모든 요소들을 저장합니다. 그룹 내 요소들의 정보와 요소 내 좌표 정보를 포함합니다.
     * 
     * @param workspaceId 요소를 저장할 워크스페이스의 고유 식별자
     * @param groupId 요소를 저장할 그룹의 고유 식별자
     * @param elementCreate 생성할 맵 요소의 정보
     * @param notify 실시간 알림 여부
     * @return 저장 결과 응답
     */
    HttpResultResponse saveElement(String workspaceId, String groupId, CreateMapElementRequest elementCreate, boolean notify);

    /**
     * 요소 ID를 기반으로 요소 정보를 업데이트합니다.
     * 그룹 내 요소들의 정보와 요소 내 좌표 정보를 포함합니다.
     * 
     * @param workspaceId 요소가 속한 워크스페이스의 고유 식별자
     * @param elementId 수정할 요소의 고유 식별자
     * @param elementUpdate 업데이트할 요소 정보
     * @param username 요소를 수정하는 사용자의 이름
     * @param notify 실시간 알림 여부
     * @return 수정 결과 응답
     */
    HttpResultResponse updateElement(String workspaceId, String elementId, UpdateMapElementRequest elementUpdate, String username, boolean notify);

    /**
     * 요소 ID를 기반으로 요소 정보를 삭제합니다.
     * 그룹 내 요소들의 정보와 요소 내 좌표 정보를 포함합니다.
     * 
     * @param workspaceId 요소가 속한 워크스페이스의 고유 식별자
     * @param elementId 삭제할 요소의 고유 식별자
     * @param notify 실시간 알림 여부
     * @return 삭제 결과 응답
     */
    HttpResultResponse deleteElement(String workspaceId, String elementId, boolean notify);

    /**
     * 요소 ID를 기반으로 요소 정보를 조회합니다.
     * 그룹 내 요소들의 정보와 요소 내 좌표 정보를 포함합니다.
     * 
     * @param elementId 조회할 요소의 고유 식별자
     * @return 요소의 상세 정보를 포함하는 Optional 객체
     */
    Optional<GroupElementDTO> getElementByElementId(String elementId);

    /**
     * 그룹 ID를 기반으로 모든 요소 정보를 삭제합니다.
     * 그룹 내 요소들의 정보와 요소 내 좌표 정보를 포함합니다.
     * 
     * @param workspaceId 요소들이 속한 워크스페이스의 고유 식별자
     * @param groupId 삭제할 그룹의 고유 식별자
     * @return 삭제 결과 응답
     */
    HttpResultResponse deleteAllElementByGroupId(String workspaceId, String groupId);

    /**
     * 요소 정보를 WebSocket 생성 응답으로 변환합니다.
     * 
     * @param element 변환할 요소 정보
     * @return WebSocket 생성 응답 객체
     */
    MapElementCreateWsResponse element2CreateWsElement(GroupElementDTO element);

    /**
     * 요소 정보를 WebSocket 수정 응답으로 변환합니다.
     * 
     * @param element 변환할 요소 정보
     * @return WebSocket 수정 응답 객체
     */
    MapElementUpdateWsResponse element2UpdateWsElement(GroupElementDTO element);
}