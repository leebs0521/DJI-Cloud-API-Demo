package com.dji.sample.map.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dji.sample.map.dao.IElementCoordinateMapper;
import com.dji.sample.map.model.entity.ElementCoordinateEntity;
import com.dji.sample.map.service.IElementCoordinateService;
import com.dji.sdk.cloudapi.map.ElementCoordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 요소 좌표 서비스 구현체
 * 지도 요소의 좌표 정보를 관리하는 서비스입니다.
 * @author sean
 * @version 0.2
 * @date 2021/11/29
 */
@Service
@Transactional
public class ElementCoordinateServiceImpl implements IElementCoordinateService {

    @Autowired
    private IElementCoordinateMapper mapper;

    /**
     * 요소 ID로 좌표 목록을 조회합니다.
     * @param elementId 요소 ID
     * @return 좌표 목록
     */
    @Override
    public List<ElementCoordinate> getCoordinateByElementId(String elementId) {
        return mapper.selectList(
                new LambdaQueryWrapper<ElementCoordinateEntity>()
                        .eq(ElementCoordinateEntity::getElementId, elementId))
                .stream()
                .map(this::entityConvertToDto)
                .collect(Collectors.toList());
    }

    /**
     * 좌표 목록을 저장합니다.
     * @param coordinateList 좌표 목록
     * @param elementId 요소 ID
     * @return 저장 성공 여부
     */
    @Override
    public Boolean saveCoordinate(List<ElementCoordinate> coordinateList, String elementId) {
        for (ElementCoordinate coordinate : coordinateList) {
            ElementCoordinateEntity entity = this.dtoConvertToEntity(coordinate);
            entity.setElementId(elementId);

            int insert = mapper.insert(entity);
            if (insert <= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 요소 ID로 모든 좌표를 삭제합니다.
     * @param elementId 요소 ID
     * @return 삭제 성공 여부
     */
    @Override
    public Boolean deleteCoordinateByElementId(String elementId) {
        return mapper.delete(new LambdaUpdateWrapper<ElementCoordinateEntity>()
                .eq(ElementCoordinateEntity::getElementId, elementId)) > 0;
    }

    /**
     * 데이터베이스 엔티티 객체를 좌표 데이터 전송 객체로 변환합니다.
     * @param entity 좌표 엔티티
     * @return 좌표 객체
     */
    private ElementCoordinate entityConvertToDto(ElementCoordinateEntity entity) {
        if (entity == null) {
            return null;
        }

        return new ElementCoordinate()
                .setLongitude(entity.getLongitude())
                .setLatitude(entity.getLatitude())
                .setAltitude(entity.getAltitude());
    }

    /**
     * 받은 좌표 객체를 데이터베이스 엔티티 객체로 변환합니다.
     * @param coordinate 좌표 객체
     * @return 좌표 엔티티
     */
    private ElementCoordinateEntity dtoConvertToEntity(ElementCoordinate coordinate) {
        ElementCoordinateEntity.ElementCoordinateEntityBuilder builder = ElementCoordinateEntity.builder();
        if (coordinate == null) {
            return builder.build();
        }

        return builder
                .altitude(coordinate.getAltitude())
                .latitude(coordinate.getLatitude())
                .longitude(coordinate.getLongitude())
                .build();
    }
}
