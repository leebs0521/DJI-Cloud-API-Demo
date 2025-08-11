package com.dji.sample.map.service.impl;

import com.dji.sample.component.websocket.model.BizCodeEnum;
import com.dji.sample.component.websocket.service.IWebSocketMessageService;
import com.dji.sample.map.model.dto.GroupElementDTO;
import com.dji.sample.map.service.IElementCoordinateService;
import com.dji.sample.map.service.IGroupElementService;
import com.dji.sample.map.service.IGroupService;
import com.dji.sample.map.service.IWorkspaceElementService;
import com.dji.sdk.cloudapi.map.*;
import com.dji.sdk.common.HttpResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 워크스페이스 요소 서비스 구현체
 * 워크스페이스 내의 지도 요소들을 관리하는 서비스입니다.
 * @author sean
 * @version 0.2
 * @date 2021/11/30
 */
@Transactional
@Service
public class WorkspaceElementServiceImpl implements IWorkspaceElementService {

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IGroupElementService groupElementService;

    @Autowired
    private IElementCoordinateService elementCoordinateService;

    @Autowired
    private IWebSocketMessageService webSocketMessageService;

    /**
     * 워크스페이스 ID로 모든 그룹과 요소들을 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @param groupId 그룹 ID (선택사항)
     * @param isDistributed 분산 여부 (선택사항)
     * @return 그룹과 요소 목록
     */
    @Override
    public List<GetMapElementsResponse> getAllGroupsByWorkspaceId(String workspaceId, String groupId, Boolean isDistributed) {
        List<GetMapElementsResponse> groupList = groupService.getAllGroupsByWorkspaceId(workspaceId, groupId, isDistributed);
        groupList.forEach(group -> group.setElements(groupElementService.getElementsByGroupId(group.getId())));
        return groupList;
    }

    /**
     * 새로운 지도 요소를 저장합니다.
     * @param workspaceId 워크스페이스 ID
     * @param groupId 그룹 ID
     * @param elementCreate 요소 생성 요청
     * @param notify 웹소켓 알림 여부
     * @return 저장 결과
     */
    @Override
    public HttpResultResponse saveElement(String workspaceId, String groupId, CreateMapElementRequest elementCreate, boolean notify) {
        boolean saveElement = groupElementService.saveElement(groupId, elementCreate);
        if (!saveElement) {
            return HttpResultResponse.error("Failed to save the element.");
        }
        if (notify) {
            // 요소가 생성될 때 이 워크스페이스의 모든 웹소켓 연결에 업데이트 알림을 보냅니다.
            getElementByElementId(elementCreate.getId())
                    .ifPresent(groupElement -> webSocketMessageService.sendBatch(
                            workspaceId, BizCodeEnum.MAP_ELEMENT_CREATE.getCode(),
                            element2CreateWsElement(groupElement)));
        }
        return HttpResultResponse.success();
    }

    /**
     * 지도 요소를 업데이트합니다.
     * @param workspaceId 워크스페이스 ID
     * @param elementId 요소 ID
     * @param elementUpdate 요소 업데이트 요청
     * @param username 사용자명
     * @param notify 웹소켓 알림 여부
     * @return 업데이트 결과
     */
    @Override
    public HttpResultResponse updateElement(String workspaceId, String elementId, UpdateMapElementRequest elementUpdate, String username, boolean notify) {
        boolean updElement = groupElementService.updateElement(elementId, elementUpdate, username);
        if (!updElement) {
            return HttpResultResponse.error("Failed to update the element.");
        }

        if (notify) {
            // 요소가 업데이트될 때 이 워크스페이스의 모든 웹소켓 연결에 업데이트 알림을 보냅니다.
            getElementByElementId(elementId)
                    .ifPresent(groupElement -> webSocketMessageService.sendBatch(
                            workspaceId, BizCodeEnum.MAP_ELEMENT_UPDATE.getCode(),
                            element2UpdateWsElement(groupElement)));
        }
        return HttpResultResponse.success();
    }

    /**
     * 지도 요소를 삭제합니다.
     * @param workspaceId 워크스페이스 ID
     * @param elementId 요소 ID
     * @param notify 웹소켓 알림 여부
     * @return 삭제 결과
     */
    @Override
    public HttpResultResponse deleteElement(String workspaceId, String elementId, boolean notify) {
        Optional<GroupElementDTO> elementOpt = getElementByElementId(elementId);
        boolean delElement = groupElementService.deleteElement(elementId);
        if (!delElement) {
            return HttpResultResponse.error("Failed to delete the element.");
        }

        // 요소 ID에 따라 모든 좌표를 삭제합니다.
        boolean delCoordinate = elementCoordinateService.deleteCoordinateByElementId(elementId);
        if (!delCoordinate) {
            return HttpResultResponse.error("Failed to delete the coordinate.");
        }

        if (notify) {
            // 요소가 삭제될 때 이 워크스페이스의 모든 웹소켓 연결에 업데이트 알림을 보냅니다.
            elementOpt.ifPresent(element ->
                    webSocketMessageService.sendBatch(workspaceId, BizCodeEnum.MAP_ELEMENT_DELETE.getCode(),
                            new MapElementDeleteWsResponse()
                                    .setGroupId(element.getGroupId())
                                    .setId(elementId)));
        }

        return HttpResultResponse.success();
    }

    /**
     * 요소 ID로 요소를 조회합니다.
     * @param elementId 요소 ID
     * @return 요소 정보
     */
    @Override
    public Optional<GroupElementDTO> getElementByElementId(String elementId) {
        return groupElementService.getElementByElementId(elementId);
    }

    /**
     * 그룹 ID로 모든 요소를 삭제합니다.
     * @param workspaceId 워크스페이스 ID
     * @param groupId 그룹 ID
     * @return 삭제 결과
     */
    @Override
    public HttpResultResponse deleteAllElementByGroupId(String workspaceId, String groupId) {
        List<MapGroupElement> groupElementList = groupElementService.getElementsByGroupId(groupId);
        for (MapGroupElement groupElement : groupElementList) {
            HttpResultResponse response = this.deleteElement(workspaceId, groupElement.getId(), true);
            if (HttpResultResponse.CODE_SUCCESS != response.getCode()) {
                return response;
            }
        }

        return HttpResultResponse.success();
    }

    /**
     * 요소를 웹소켓 생성 응답 객체로 변환합니다.
     * @param element 그룹 요소 DTO
     * @return 웹소켓 생성 응답 객체
     */
    public MapElementCreateWsResponse element2CreateWsElement(GroupElementDTO element) {
        if (element == null) {
            return null;
        }
        return new MapElementCreateWsResponse()
                .setId(element.getElementId())
                .setGroupId(element.getGroupId())
                .setName(element.getName())
                .setResource(element.getResource())
                .setUpdateTime(element.getUpdateTime())
                .setCreateTime(element.getCreateTime());
    }

    /**
     * 요소를 웹소켓 업데이트 응답 객체로 변환합니다.
     * @param element 그룹 요소 DTO
     * @return 웹소켓 업데이트 응답 객체
     */
    public MapElementUpdateWsResponse element2UpdateWsElement(GroupElementDTO element) {
        if (element == null) {
            return null;
        }
        return new MapElementUpdateWsResponse()
                .setId(element.getElementId())
                .setGroupId(element.getGroupId())
                .setName(element.getName())
                .setResource(element.getResource())
                .setUpdateTime(element.getUpdateTime())
                .setCreateTime(element.getCreateTime());
    }
}
