package com.dji.sample.map.service.impl;

import com.dji.sample.common.error.CommonErrorEnum;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.model.param.DeviceQueryParam;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sample.manage.service.IDeviceService;
import com.dji.sample.map.model.dto.DeviceDataStatusDTO;
import com.dji.sample.map.model.dto.DeviceFlightAreaDTO;
import com.dji.sample.map.service.IDeviceDataService;
import com.dji.sample.map.service.IDeviceFlightAreaService;
import com.dji.sdk.cloudapi.device.DeviceDomainEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 디바이스 데이터 서비스 구현체
 * 디바이스의 데이터 상태와 비행 영역 정보를 관리하는 서비스입니다.
 * @author sean
 * @version 1.9
 * @date 2023/11/24
 */
@Service
public class DeviceDataServiceImpl implements IDeviceDataService {

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IDeviceRedisService deviceRedisService;

    @Autowired
    private IDeviceFlightAreaService deviceFlightAreaService;

    /**
     * 워크스페이스의 모든 디바이스 데이터 상태를 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @return 디바이스 데이터 상태 목록
     */
    @Override
    public List<DeviceDataStatusDTO> getDevicesDataStatus(String workspaceId) {
        List<DeviceDTO> devices = deviceService.getDevicesByParams(DeviceQueryParam.builder()
                .domains(List.of(DeviceDomainEnum.DOCK.getDomain())).workspaceId(workspaceId).build());
        if (CollectionUtils.isEmpty(devices)) {
            throw new RuntimeException(CommonErrorEnum.ILLEGAL_ARGUMENT.getMessage());
        }
        return devices.stream().map(device -> DeviceDataStatusDTO.builder()
                        .deviceName(device.getDeviceName())
                        .deviceSn(device.getDeviceSn())
                        .nickname(device.getNickname())
                        .online(deviceRedisService.checkDeviceOnline(device.getDeviceSn()))
                        .flightAreaStatus(getDeviceStatus(workspaceId, device.getDeviceSn()).orElse(null))
                        .build())
                .filter(device -> Objects.nonNull(device.getFlightAreaStatus()))
                .collect(Collectors.toList());
    }

    /**
     * 특정 디바이스의 비행 영역 상태를 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @param deviceSn 디바이스 시리얼 번호
     * @return 디바이스 비행 영역 정보
     */
    @Override
    public Optional<DeviceFlightAreaDTO> getDeviceStatus(String workspaceId, String deviceSn) {
        return deviceFlightAreaService.getDeviceFlightAreaByDevice(workspaceId, deviceSn)
                .map(area -> DeviceFlightAreaDTO.builder().syncStatus(area.getSyncStatus()).syncCode(area.getSyncCode()).syncMsg(area.getSyncMsg()).build());
    }
}
