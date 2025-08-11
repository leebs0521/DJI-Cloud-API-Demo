package com.dji.sample.map.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dji.sample.map.dao.IDeviceFlightAreaMapper;
import com.dji.sample.map.model.dto.DeviceFlightAreaDTO;
import com.dji.sample.map.model.entity.DeviceFlightAreaEntity;
import com.dji.sample.map.service.IDeviceFlightAreaService;
import com.dji.sdk.cloudapi.flightarea.FlightAreaSyncReasonEnum;
import com.dji.sdk.cloudapi.flightarea.FlightAreaSyncStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * 디바이스 비행 영역 서비스 구현체
 * 디바이스와 비행 영역 간의 동기화 상태를 관리하는 서비스입니다.
 * @author sean
 * @version 1.9
 * @date 2023/11/23
 */
@Service
@Transactional
public class DeviceFlightAreaServiceImpl implements IDeviceFlightAreaService {

    @Autowired
    private IDeviceFlightAreaMapper mapper;

    /**
     * 디바이스의 비행 영역 정보를 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @param deviceSn 디바이스 시리얼 번호
     * @return 디바이스 비행 영역 정보
     */
    @Override
    public Optional<DeviceFlightAreaDTO> getDeviceFlightAreaByDevice(String workspaceId, String deviceSn) {
        return Optional.ofNullable(mapper.selectOne(Wrappers.lambdaQuery(DeviceFlightAreaEntity.class)
                        .eq(DeviceFlightAreaEntity::getWorkspaceId, workspaceId)
                        .eq(DeviceFlightAreaEntity::getDeviceSn, deviceSn)))
                .map(this::entity2Dto);
    }

    /**
     * 디바이스 파일 정보를 업데이트합니다.
     * @param deviceFile 디바이스 파일 정보
     * @return 업데이트 성공 여부
     */
    @Override
    public Boolean updateDeviceFile(DeviceFlightAreaDTO deviceFile) {
        return mapper.update(dto2Entity(deviceFile),
                Wrappers.lambdaUpdate(DeviceFlightAreaEntity.class)
                        .eq(DeviceFlightAreaEntity::getWorkspaceId, deviceFile.getWorkspaceId())
                        .eq(DeviceFlightAreaEntity::getDeviceSn, deviceFile.getDeviceSn())) > 0;
    }

    /**
     * 디바이스 파일 정보를 업데이트하거나 새로 저장합니다.
     * @param deviceFile 디바이스 파일 정보
     * @return 저장/업데이트 성공 여부
     */
    @Override
    public Boolean updateOrSaveDeviceFile(DeviceFlightAreaDTO deviceFile) {
        if (getDeviceFlightAreaByDevice(deviceFile.getWorkspaceId(), deviceFile.getDeviceSn()).isPresent()) {
            return updateDeviceFile(deviceFile);
        }
        DeviceFlightAreaEntity entity = dto2Entity(deviceFile);
        entity.setFileId(UUID.randomUUID().toString());
        return mapper.insert(entity) > 0;
    }

    /**
     * DTO를 엔티티로 변환합니다.
     * @param dto 디바이스 비행 영역 DTO
     * @return 디바이스 비행 영역 엔티티
     */
    private DeviceFlightAreaEntity dto2Entity(DeviceFlightAreaDTO dto) {
        if (Objects.isNull(dto)) {
            return null;
        }
        return DeviceFlightAreaEntity.builder()
                .deviceSn(dto.getDeviceSn())
                .workspaceId(dto.getWorkspaceId())
                .fileId(dto.getFileId())
                .syncCode(dto.getSyncCode().getReason())
                .syncStatus(dto.getSyncStatus().getStatus())
                .build();
    }

    /**
     * 엔티티를 DTO로 변환합니다.
     * @param entity 디바이스 비행 영역 엔티티
     * @return 디바이스 비행 영역 DTO
     */
    private DeviceFlightAreaDTO entity2Dto(DeviceFlightAreaEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        FlightAreaSyncReasonEnum syncCodeEnum = FlightAreaSyncReasonEnum.find(entity.getSyncCode());
        return DeviceFlightAreaDTO.builder()
                .deviceSn(entity.getDeviceSn())
                .workspaceId(entity.getWorkspaceId())
                .fileId(entity.getFileId())
                .syncCode(syncCodeEnum)
                .syncStatus(FlightAreaSyncStatusEnum.find(entity.getSyncStatus()))
                .syncMsg(syncCodeEnum.getMsg())
                .build();
    }


}
