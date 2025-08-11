package com.dji.sample.map.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dji.sample.map.dao.IGroupElementMapper;
import com.dji.sample.map.model.dto.GroupElementDTO;
import com.dji.sample.map.model.entity.GroupElementEntity;
import com.dji.sdk.cloudapi.map.ElementTypeEnum;
import com.dji.sample.map.service.IElementCoordinateService;
import com.dji.sample.map.service.IGroupElementService;
import com.dji.sdk.cloudapi.map.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 그룹 요소 서비스 구현체
 * 
 * DJI Cloud API의 그룹 요소 관리를 위한 서비스 구현 클래스입니다.
 * 이 클래스는 그룹에 속한 맵 요소들의 CRUD 작업을 처리합니다.
 * 
 * 1. 그룹 요소 조회 기능
 *    - 그룹별 요소 목록 조회 및 좌표 정보 포함
 *    - 개별 요소 상세 조회
 * 
 * 2. 그룹 요소 관리 기능
 *    - 요소 생성 및 그룹에 저장
 *    - 요소 수정 및 좌표 정보 업데이트
 *    - 요소 삭제
 * 
 * 3. 데이터 변환 기능
 *    - 엔티티와 DTO 간 변환
 *    - 좌표 정보 추가 및 관리
 * 
 * 4. 트랜잭션 관리
 *    - @Transactional 어노테이션으로 트랜잭션 보장
 *    - 요소와 좌표 정보의 일관성 유지
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
@Service
@Transactional
public class GroupElementServiceImpl implements IGroupElementService {

    /**
     * 그룹 요소 데이터 접근 객체
     * 
     * 그룹 요소 엔티티의 데이터베이스 접근을 담당합니다.
     * MyBatis-Plus를 사용하여 CRUD 작업을 수행합니다.
     */
    @Autowired
    private IGroupElementMapper mapper;

    /**
     * 요소 좌표 서비스
     * 
     * 맵 요소의 좌표 정보 관리를 담당합니다.
     * 요소 생성/수정 시 좌표 정보를 함께 처리합니다.
     */
    @Autowired
    private IElementCoordinateService elementCoordinateService;

    /**
     * 그룹 ID를 기반으로 해당 그룹의 모든 요소들을 조회합니다.
     * 각 요소의 좌표 정보도 함께 포함하여 반환합니다.
     * 
     * @param groupId 조회할 그룹의 고유 식별자
     * @return 그룹에 속한 맵 요소 목록 (좌표 정보 포함)
     */
    @Override
    public List<MapGroupElement> getElementsByGroupId(String groupId) {
        // 그룹 ID로 요소 엔티티 목록 조회
        List<GroupElementEntity> elementList = mapper.selectList(
                new LambdaQueryWrapper<GroupElementEntity>()
                        .eq(GroupElementEntity::getGroupId, groupId));

        List<MapGroupElement> groupElementList = new ArrayList<>();
        for (GroupElementEntity elementEntity : elementList) {
            // 엔티티를 DTO로 변환
            MapGroupElement groupElement = this.entityConvertToDto(elementEntity);
            groupElementList.add(groupElement);

            // 요소에 좌표 정보 추가
            this.addCoordinateToElement(groupElement, elementEntity);
        }
        return groupElementList;
    }

    /**
     * 새로운 맵 요소를 생성하고 지정된 그룹에 저장합니다.
     * 요소의 좌표 정보도 함께 저장합니다.
     * 
     * @param groupId 요소를 저장할 그룹의 고유 식별자
     * @param elementCreate 생성할 맵 요소의 정보
     * @return 저장 성공 여부
     */
    @Override
    public Boolean saveElement(String groupId, CreateMapElementRequest elementCreate) {
        // 기존 요소 존재 여부 확인
        Optional<GroupElementEntity> groupElementOpt = this.getEntityByElementId(elementCreate.getId());

        if (groupElementOpt.isPresent()) {
            return false;
        }
        
        // DTO를 엔티티로 변환하고 그룹 ID 설정
        GroupElementEntity groupElement = this.createDtoConvertToEntity(elementCreate);
        groupElement.setGroupId(groupId);

        // 요소 엔티티 저장
        boolean saveElement = mapper.insert(groupElement) > 0;
        if (!saveElement) {
            return false;
        }
        
        // 요소의 좌표 정보 저장
        return elementCoordinateService.saveCoordinate(
                elementCreate.getResource().getContent().getGeometry().convertToList(), elementCreate.getId());
    }

    /**
     * 요소 ID를 기반으로 요소 정보를 조회하고 업데이트합니다.
     * 기존 좌표 정보를 삭제하고 새로운 좌표 정보를 저장합니다.
     * 
     * @param elementId 수정할 요소의 고유 식별자
     * @param elementUpdate 업데이트할 요소 정보
     * @param username 요소를 수정하는 사용자의 이름
     * @return 수정 성공 여부
     */
    @Override
    public Boolean updateElement(String elementId, UpdateMapElementRequest elementUpdate, String username) {
        // 기존 요소 존재 여부 확인
        Optional<GroupElementEntity> groupElementOpt = this.getEntityByElementId(elementId);
        if (groupElementOpt.isEmpty()) {
            return false;
        }

        // 엔티티 업데이트
        GroupElementEntity groupElement = groupElementOpt.get();
        groupElement.setUsername(username);
        this.updateEntityWithDto(elementUpdate, groupElement);
        
        boolean update = mapper.updateById(groupElement) > 0;
        if (!update) {
            return false;
        }
        
        // 기존 좌표 정보 삭제
        boolean delCoordinate = elementCoordinateService.deleteCoordinateByElementId(elementId);
        // 새로운 좌표 정보 저장
        boolean saveCoordinate = elementCoordinateService.saveCoordinate(
                elementUpdate.getContent().getGeometry().convertToList(), elementId);
        return delCoordinate & saveCoordinate;
    }

    /**
     * 요소 ID를 기반으로 해당 요소를 삭제합니다.
     * 
     * @param elementId 삭제할 요소의 고유 식별자
     * @return 삭제 성공 여부
     */
    @Override
    public Boolean deleteElement(String elementId) {
        Optional<GroupElementEntity> groupElementOpt = this.getEntityByElementId(elementId);
        if (groupElementOpt.isEmpty()) {
            return true;
        }

        GroupElementEntity groupElement = groupElementOpt.get();
        return mapper.deleteById(groupElement.getId()) > 0;
    }


    @Override
    public Optional<GroupElementDTO> getElementByElementId(String elementId) {
        Optional<GroupElementEntity> elementEntityOpt = this.getEntityByElementId(elementId);
        if (elementEntityOpt.isEmpty()) {
            return Optional.empty();
        }
        GroupElementEntity elementEntity = elementEntityOpt.get();
        MapGroupElement groupElement = this.entityConvertToDto(elementEntity);

        this.addCoordinateToElement(groupElement, elementEntity);
        return Optional.ofNullable(groupElement2Dto(groupElement, elementEntity.getGroupId()));
    }

    private GroupElementDTO groupElement2Dto(MapGroupElement element, String groupId) {
        if (null == element) {
            return null;
        }
        return GroupElementDTO.builder()
                .elementId(element.getId())
                .groupId(groupId)
                .updateTime(element.getUpdateTime())
                .createTime(element.getCreateTime())
                .name(element.getName())
                .resource(element.getResource())
                .build();
    }

    /**
     * Adds the received coordinate data to the element object.
     * @param element
     * @param elementEntity
     */
    private void addCoordinateToElement(MapGroupElement element, GroupElementEntity elementEntity) {
        Optional<ElementGeometryType> coordinateOpt = ElementTypeEnum.findType(elementEntity.getElementType());
        if (coordinateOpt.isEmpty()) {
            return;
        }
        element.getResource()
                .setContent(new ElementContent()
                        .setProperties(new ElementProperty()
                                .setClampToGround(elementEntity.getClampToGround())
                                .setColor(elementEntity.getColor()))
                        .setGeometry(coordinateOpt.get()));

        coordinateOpt.get().adapterCoordinateType(
                elementCoordinateService.getCoordinateByElementId(elementEntity.getElementId()));
    }

    /**
     * Query an element based on the element id。
     * @param elementId
     * @return
     */
    private Optional<GroupElementEntity> getEntityByElementId(String elementId) {
        return Optional.ofNullable(mapper.selectOne(
                new LambdaQueryWrapper<GroupElementEntity>()
                        .eq(GroupElementEntity::getElementId, elementId)));
    }

    /**
     * Convert database entity objects into element data transfer object.
     * @param entity
     * @return
     */
    private MapGroupElement entityConvertToDto(GroupElementEntity entity) {
        if (entity == null) {
            return null;
        }

        return new MapGroupElement()
                .setId(entity.getElementId())
                .setName(entity.getElementName())
                .setCreateTime(entity.getCreateTime())
                .setUpdateTime(entity.getUpdateTime())
                .setResource(new ElementResource()
                        .setType(ElementResourceTypeEnum.find(entity.getElementType()))
                        .setUsername(entity.getUsername()));
    }

    /**
     * Convert the received element object into a database entity object.
     * @param elementCreate
     * @return
     */
    private GroupElementEntity createDtoConvertToEntity(CreateMapElementRequest elementCreate) {
        ElementProperty properties = elementCreate.getResource().getContent().getProperties();
        return GroupElementEntity.builder()
                .elementId(elementCreate.getId())
                .elementName(elementCreate.getName())
                .username(elementCreate.getResource().getUsername())
                .elementType(ElementTypeEnum.findVal(elementCreate.getResource().getContent().getGeometry().getType()))
                .clampToGround(properties.getClampToGround() != null && properties.getClampToGround())
                .color(properties.getColor())
                .build();
    }

    /**
     * Add the content that needs to be updated to the entity object to be updated.
     * @param elementUpdate
     * @param groupElement
     */
    private void updateEntityWithDto(UpdateMapElementRequest elementUpdate, GroupElementEntity groupElement) {
        if (elementUpdate == null || groupElement == null) {
            return;
        }

        groupElement.setElementName(elementUpdate.getName());
        groupElement.setElementType(ElementTypeEnum.findVal(elementUpdate.getContent().getGeometry().getType()));
        groupElement.setColor(elementUpdate.getContent().getProperties().getColor());

        Boolean clampToGround = elementUpdate.getContent().getProperties().getClampToGround();
        groupElement.setClampToGround(clampToGround);
    }
}
