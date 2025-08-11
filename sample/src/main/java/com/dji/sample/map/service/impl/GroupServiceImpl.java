package com.dji.sample.map.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dji.sample.map.dao.IGroupMapper;
import com.dji.sample.map.model.entity.GroupEntity;
import com.dji.sample.map.service.IGroupElementService;
import com.dji.sample.map.service.IGroupService;
import com.dji.sdk.cloudapi.map.GetMapElementsResponse;
import com.dji.sdk.cloudapi.map.GroupTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 그룹 서비스 구현체
 * @author sean
 * @version 0.2
 * @date 2021/11/29
 */
@Service
@Transactional
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private IGroupMapper mapper;

    @Autowired
    private IGroupElementService groupElementService;

    /**
     * 워크스페이스 ID로 모든 그룹을 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @param groupId 그룹 ID (선택사항)
     * @param isDistributed 분산 여부 (선택사항)
     * @return 그룹 목록
     */
    @Override
    public List<GetMapElementsResponse> getAllGroupsByWorkspaceId(String workspaceId, String groupId, Boolean isDistributed) {

        return mapper.selectList(
                new LambdaQueryWrapper<GroupEntity>()
                        .eq(GroupEntity::getWorkspaceId, workspaceId)
                        .eq(StringUtils.hasText(groupId), GroupEntity::getGroupId, groupId)
                        .eq(isDistributed != null, GroupEntity::getIsDistributed, isDistributed))
                .stream()
                .map(this::entityConvertToDto)
                .collect(Collectors.toList());
    }

    /**
     * 워크스페이스 ID로 커스텀 그룹을 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @return 커스텀 그룹 정보
     */
    @Override
    public Optional<GetMapElementsResponse> getCustomGroupByWorkspaceId(String workspaceId) {
        return Optional.ofNullable(mapper.selectOne(
                Wrappers.lambdaQuery(GroupEntity.class)
                        .eq(GroupEntity::getWorkspaceId, workspaceId)
                        .eq(GroupEntity::getGroupType, GroupTypeEnum.CUSTOM.getType())
                        .last(" limit 1")))
                .map(this::entityConvertToDto);
    }

    /**
     * 데이터베이스 엔티티 객체를 그룹 데이터 전송 객체로 변환합니다.
     * @param entity 그룹 엔티티
     * @return 그룹 응답 객체
     */
    private GetMapElementsResponse entityConvertToDto(GroupEntity entity) {
        if (entity == null) {
            return null;
        }

        return new GetMapElementsResponse()
                .setId(entity.getGroupId())
                .setName(entity.getGroupName())
                .setType(GroupTypeEnum.find(entity.getGroupType()))
                .setLock(entity.getIsLock());
    }
}
