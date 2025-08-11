package com.dji.sample.map.service;

import com.dji.sample.map.model.dto.GroupElementDTO;
import com.dji.sdk.cloudapi.map.CreateMapElementRequest;
import com.dji.sdk.cloudapi.map.MapGroupElement;
import com.dji.sdk.cloudapi.map.UpdateMapElementRequest;

import java.util.List;
import java.util.Optional;

/**
 * 그룹 요소 서비스 인터페이스
 * 
 * DJI Cloud API의 그룹 요소 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 그룹에 속한 맵 요소들의 CRUD 작업을 제공합니다.
 * 
 * 1. 그룹 요소 조회 기능
 *    - 그룹별 요소 목록 조회 (getElementsByGroupId)
 *    - 개별 요소 상세 조회 (getElementByElementId)
 * 
 * 2. 그룹 요소 관리 기능
 *    - 요소 생성 및 저장 (saveElement)
 *    - 요소 수정 (updateElement)
 *    - 요소 삭제 (deleteElement)
 * 
 * 3. 그룹-요소 관계 관리
 *    - 그룹에 요소 추가
 *    - 그룹별 요소 분류
 *    - 요소별 그룹 소속 관리
 * 
 * 주요 용도:
 * - 그룹별 맵 요소 CRUD 작업 처리
 * - 그룹-요소 관계 관리
 * - 맵 요소 분류 및 정리
 * - 그룹별 요소 접근 제어
 * 
 * 사용 예시:
 * - 그룹에 맵 요소 추가/제거
 * - 그룹별 요소 목록 관리
 * - 요소별 그룹 소속 변경
 * 
 * @author sean
 * @version 0.2
 * @date 2021/11/29
 */
public interface IGroupElementService {

    /**
     * 그룹 ID를 기반으로 해당 그룹의 모든 요소들을 조회합니다.
     * 
     * @param groupId 조회할 그룹의 고유 식별자
     * @return 그룹에 속한 맵 요소 목록
     */
    List<MapGroupElement> getElementsByGroupId(String groupId);

    /**
     * 새로운 맵 요소를 생성하고 지정된 그룹에 저장합니다.
     * 
     * @param groupId 요소를 저장할 그룹의 고유 식별자
     * @param elementCreate 생성할 맵 요소의 정보
     * @return 저장 성공 여부
     */
    Boolean saveElement(String groupId, CreateMapElementRequest elementCreate);

    /**
     * 요소 ID를 기반으로 요소 정보를 조회하고 업데이트합니다.
     * 
     * @param elementId 수정할 요소의 고유 식별자
     * @param elementUpdate 업데이트할 요소 정보
     * @param username 요소를 수정하는 사용자의 이름
     * @return 수정 성공 여부
     */
    Boolean updateElement(String elementId, UpdateMapElementRequest elementUpdate, String username);

    /**
     * 요소 ID를 기반으로 해당 요소를 삭제합니다.
     * 
     * @param elementId 삭제할 요소의 고유 식별자
     * @return 삭제 성공 여부
     */
    Boolean deleteElement(String elementId);

    /**
     * 요소 ID를 기반으로 요소 정보를 조회합니다.
     * 요소의 좌표 정보를 포함한 상세 정보를 반환합니다.
     * 
     * @param elementId 조회할 요소의 고유 식별자
     * @return 요소의 상세 정보를 포함하는 Optional 객체
     */
    Optional<GroupElementDTO> getElementByElementId(String elementId);
}
