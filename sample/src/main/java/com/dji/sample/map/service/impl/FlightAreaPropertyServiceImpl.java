package com.dji.sample.map.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dji.sample.map.dao.IFlightAreaPropertyMapper;
import com.dji.sample.map.model.dto.FlightAreaPropertyDTO;
import com.dji.sample.map.model.dto.FlightAreaPropertyUpdate;
import com.dji.sample.map.model.entity.FlightAreaPropertyEntity;
import com.dji.sample.map.service.IFlightAreaPropertyServices;
import com.dji.sdk.cloudapi.flightarea.GeofenceTypeEnum;
import com.dji.sdk.cloudapi.flightarea.GeometrySubTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 비행 영역 속성 서비스 구현체
 * 비행 영역의 속성 정보를 관리하는 서비스입니다.
 * @author sean
 * @version 1.9
 * @date 2023/11/22
 */
@Service
@Transactional
public class FlightAreaPropertyServiceImpl implements IFlightAreaPropertyServices {

    @Autowired
    private IFlightAreaPropertyMapper mapper;

    /**
     * 요소 ID 목록으로 속성을 조회합니다.
     * @param elementIds 요소 ID 목록
     * @return 비행 영역 속성 목록
     */
    @Override
    public List<FlightAreaPropertyDTO> getPropertyByElementIds(List<String> elementIds) {
        if (CollectionUtils.isEmpty(elementIds)) {
            return Collections.emptyList();
        }
        return mapper.selectList(
                Wrappers.lambdaQuery(FlightAreaPropertyEntity.class)
                        .in(FlightAreaPropertyEntity::getElementId, elementIds)).stream()
                .map(this::fillProperty).collect(Collectors.toList());
    }

    /**
     * 비행 영역 속성을 저장합니다.
     * @param property 비행 영역 속성 정보
     * @return 저장된 속성의 ID
     */
    @Override
    public Integer saveProperty(FlightAreaPropertyDTO property) {
        FlightAreaPropertyEntity entity = dto2Entity(property);
        int id = mapper.insert(entity);
        return id > 0 ? entity.getId() : id;
    }

    /**
     * 요소 ID로 속성을 삭제합니다.
     * @param elementId 요소 ID
     * @return 삭제된 레코드 수
     */
    @Override
    public Integer deleteProperty(String elementId) {
        return mapper.delete(Wrappers.lambdaUpdate(FlightAreaPropertyEntity.class).eq(FlightAreaPropertyEntity::getElementId, elementId));
    }

    /**
     * 비행 영역 속성을 업데이트합니다.
     * @param property 비행 영역 속성 업데이트 정보
     * @return 업데이트된 레코드 수
     */
    @Override
    public Integer updateProperty(FlightAreaPropertyUpdate property) {
        return mapper.update(update2Entity(property),
                Wrappers.lambdaUpdate(FlightAreaPropertyEntity.class).eq(FlightAreaPropertyEntity::getElementId, property.getElementId()));
    }

    /**
     * 엔티티를 DTO로 변환하여 속성을 채웁니다.
     * @param entity 비행 영역 속성 엔티티
     * @return 비행 영역 속성 DTO
     */
    private FlightAreaPropertyDTO fillProperty(FlightAreaPropertyEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        FlightAreaPropertyDTO.FlightAreaPropertyDTOBuilder builder = FlightAreaPropertyDTO.builder()
                .elementId(entity.getElementId())
                .status(entity.getEnable())
                .type(GeofenceTypeEnum.find(entity.getType()))
                .subType(Optional.ofNullable(entity.getSubType()).map(GeometrySubTypeEnum::find).orElse(null))
                .radius(entity.getRadius().floatValue() / 100);

        return builder.build();
    }

    /**
     * DTO를 엔티티로 변환합니다.
     * @param dto 비행 영역 속성 DTO
     * @return 비행 영역 속성 엔티티
     */
    private FlightAreaPropertyEntity dto2Entity(FlightAreaPropertyDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        return FlightAreaPropertyEntity.builder()
                .elementId(dto.getElementId())
                .enable(dto.getStatus())
                .subType(Optional.ofNullable(dto.getSubType()).map(GeometrySubTypeEnum::getSubType).orElse(null))
                .type(dto.getType().getType())
                .radius(Optional.ofNullable(dto.getRadius()).map(radius -> radius * 100).map(Float::intValue).orElse(null))
                .build();
    }

    /**
     * 업데이트 객체를 엔티티로 변환합니다.
     * @param property 비행 영역 속성 업데이트 정보
     * @return 비행 영역 속성 엔티티
     */
    private FlightAreaPropertyEntity update2Entity(FlightAreaPropertyUpdate property) {
        if (Objects.isNull(property)) {
            return null;
        }
        return FlightAreaPropertyEntity.builder()
                .radius(Optional.ofNullable(property.getRadius()).map(radius -> radius * 100).map(Float::intValue).orElse(null))
                .enable(property.getStatus())
                .elementId(property.getElementId())
                .build();
    }
}
