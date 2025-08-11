package com.dji.sample.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dji.sample.component.websocket.model.BizCodeEnum;
import com.dji.sample.component.websocket.service.IWebSocketMessageService;
import com.dji.sample.control.model.enums.DroneAuthorityEnum;
import com.dji.sample.manage.dao.IDevicePayloadMapper;
import com.dji.sample.manage.model.dto.*;
import com.dji.sample.manage.model.entity.DevicePayloadEntity;
import com.dji.sample.manage.model.enums.UserTypeEnum;
import com.dji.sample.manage.service.ICapacityCameraService;
import com.dji.sample.manage.service.IDeviceDictionaryService;
import com.dji.sample.manage.service.IDevicePayloadService;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sdk.cloudapi.device.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 디바이스 페이로드 관리 서비스 구현체
 * 
 * DJI Cloud API의 디바이스 페이로드 관리를 위한 서비스 구현체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 구현합니다:
 * 
 * 1. 페이로드 정보 관리
 *    - 페이로드 정보 저장 및 업데이트
 *    - 페이로드 존재 여부 확인
 *    - 페이로드 메타데이터 관리
 *    - 페이로드 이력 추적
 * 
 * 2. 페이로드 제어 관리
 *    - 페이로드 제어 소스 관리
 *    - 페이로드 권한 확인
 *    - 제어 소스 변경 알림
 *    - 페이로드 상태 모니터링
 * 
 * 3. 페이로드 펌웨어 관리
 *    - 페이로드 펌웨어 버전 업데이트
 *    - 펌웨어 호환성 검증
 *    - 펌웨어 상태 추적
 *    - 펌웨어 업그레이드 관리
 * 
 * 4. 페이로드 데이터 변환
 *    - 페이로드 수신 데이터를 엔티티로 변환
 *    - 엔티티를 DTO로 변환
 *    - 페이로드 인덱스 관리
 *    - 페이로드 타입 매핑
 * 
 * 5. 페이로드 정리 관리
 *    - 디바이스별 페이로드 삭제
 *    - 페이로드 시리얼 번호별 삭제
 *    - 페이로드 캐시 정리
 *    - 페이로드 리소스 해제
 * 
 * 주요 의존성:
 * - IDevicePayloadMapper: 페이로드 데이터베이스 접근
 * - IDeviceDictionaryService: 디바이스 사전 관리
 * - ICapacityCameraService: 카메라 용량 관리
 * - IWebSocketMessageService: WebSocket 메시지 전송
 * - IDeviceRedisService: Redis 캐시 관리
 * 
 * 이 클래스는 DJI 디바이스의 페이로드를
 * 안전하고 효율적으로 관리하는 서비스입니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/19
 */
@Slf4j
@Service
@Transactional
public class DevicePayloadServiceImpl implements IDevicePayloadService {

    @Autowired
    private IDevicePayloadMapper mapper;

    @Autowired
    private IDeviceDictionaryService dictionaryService;

    @Autowired
    private ICapacityCameraService capacityCameraService;

    @Autowired
    private IWebSocketMessageService sendMessageService;

    @Autowired
    private IDeviceRedisService deviceRedisService;

    /**
     * 페이로드 존재 여부를 확인합니다.
     * 
     * @param payloadSn 페이로드 시리얼 번호
     * @return 페이로드 ID (존재하지 않으면 -1)
     */
    @Override
    public Integer checkPayloadExist(String payloadSn) {
        DevicePayloadEntity devicePayload = mapper.selectOne(
                new LambdaQueryWrapper<DevicePayloadEntity>()
                        .eq(DevicePayloadEntity::getPayloadSn, payloadSn));
        return devicePayload != null ? devicePayload.getId() : -1;
    }

    /**
     * 단일 페이로드 엔티티를 저장합니다.
     * 
     * @param entity 저장할 페이로드 엔티티
     * @return 저장된 페이로드 ID
     */
    private Integer saveOnePayloadEntity(DevicePayloadEntity entity) {
        int id = this.checkPayloadExist(entity.getPayloadSn());
        // 이미 존재하면 데이터를 직접 업데이트
        if (id > 0) {
            entity.setId(id);
            // 드론 자체의 페이로드는 펌웨어 버전이 없음
            entity.setFirmwareVersion(null);
            return mapper.updateById(entity) > 0 ? entity.getId() : 0;
        }
        return mapper.insert(entity) > 0 ? entity.getId() : 0;
    }

    /**
     * 페이로드 DTO 목록을 저장합니다.
     * 
     * @param device 디바이스 정보
     * @param payloadReceiverList 페이로드 수신자 목록
     * @return 저장 성공 여부
     */
    @Override
    public Boolean savePayloadDTOs(DeviceDTO device, List<DevicePayloadReceiver> payloadReceiverList) {
        Map<String, ControlSourceEnum> controlMap = CollectionUtils.isEmpty(device.getPayloadsList()) ?
                Collections.emptyMap() : device.getPayloadsList().stream()
                    .collect(Collectors.toMap(DevicePayloadDTO::getPayloadSn, DevicePayloadDTO::getControlSource));

        for (DevicePayloadReceiver payloadReceiver : payloadReceiverList) {
            payloadReceiver.setDeviceSn(device.getDeviceSn());
            int payloadId = this.saveOnePayloadDTO(payloadReceiver);
            if (payloadId <= 0) {
                log.error("Payload data saving failed.");
                return false;
            }
            // 제어 소스가 변경된 경우 알림 전송
            if (controlMap.get(payloadReceiver.getSn()) != payloadReceiver.getControlSource()) {
                sendMessageService.sendBatch(device.getWorkspaceId(), UserTypeEnum.WEB.getVal(),
                                    BizCodeEnum.CONTROL_SOURCE_CHANGE.getCode(),
                                    DeviceAuthorityDTO.builder()
                                            .controlSource(payloadReceiver.getControlSource())
                                            .sn(payloadReceiver.getSn())
                                            .type(DroneAuthorityEnum.PAYLOAD)
                                            .build());
            }
        }

        List<DevicePayloadDTO> payloads = this.getDevicePayloadEntitiesByDeviceSn(device.getDeviceSn());
        device.setPayloadsList(payloads);
        deviceRedisService.setDeviceOnline(device);
        return true;
    }

    /**
     * 단일 페이로드 DTO를 저장합니다.
     * 
     * @param payloadReceiver 페이로드 수신자
     * @return 저장된 페이로드 ID
     */
    @Override
    public Integer saveOnePayloadDTO(DevicePayloadReceiver payloadReceiver) {
        return this.saveOnePayloadEntity(receiverConvertToEntity(payloadReceiver));
    }

    /**
     * 디바이스 시리얼 번호로 페이로드 엔티티 목록을 조회합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @return 페이로드 DTO 목록
     */
    @Override
    public List<DevicePayloadDTO> getDevicePayloadEntitiesByDeviceSn(String deviceSn) {
        return mapper.selectList(
                new LambdaQueryWrapper<DevicePayloadEntity>()
                        .eq(DevicePayloadEntity::getDeviceSn, deviceSn))
                .stream()
                .map(this::payloadEntityConvertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * 디바이스 시리얼 번호 목록으로 페이로드를 삭제합니다.
     * 
     * @param deviceSns 디바이스 시리얼 번호 목록
     */
    @Override
    public void deletePayloadsByDeviceSn(List<String> deviceSns) {
        deviceSns.forEach(deviceSn -> {
            mapper.delete(
                    new LambdaQueryWrapper<DevicePayloadEntity>()
                            .eq(DevicePayloadEntity::getDeviceSn, deviceSn));
            capacityCameraService.deleteCapacityCameraByDeviceSn(deviceSn);
        });
    }

    /**
     * 페이로드 펌웨어 버전을 업데이트합니다.
     * 
     * @param droneSn 드론 시리얼 번호
     * @param receiver 펌웨어 버전 수신자
     * @return 업데이트 성공 여부
     */
    @Override
    public Boolean updateFirmwareVersion(String droneSn, PayloadFirmwareVersion receiver) {
        return mapper.update(DevicePayloadEntity.builder()
                        .firmwareVersion(receiver.getFirmwareVersion()).build(),
                new LambdaUpdateWrapper<DevicePayloadEntity>()
                        .eq(DevicePayloadEntity::getDeviceSn, droneSn)
                        .eq(DevicePayloadEntity::getPayloadSn, droneSn + "-" + receiver.getPosition().getPosition())
        ) > 0;
    }

    /**
     * 디바이스의 페이로드 데이터를 처리합니다.
     * 
     * @param drone 드론 디바이스
     * @param payloads 페이로드 목록
     */
    public void updatePayloadControl(DeviceDTO drone, List<DevicePayloadReceiver> payloads) {
        boolean match = payloads.stream().peek(p -> p.setSn(Objects.requireNonNullElse(p.getSn(),
                p.getDeviceSn() + "-" + p.getPayloadIndex().getPosition().getPosition())))
                .anyMatch(p -> ControlSourceEnum.UNKNOWN == p.getControlSource());
        if (match) {
            return;
        }

        if (payloads.isEmpty()) {
            drone.setPayloadsList(null);
            this.deletePayloadsByDeviceSn(List.of(drone.getDeviceSn()));
            deviceRedisService.setDeviceOnline(drone);
            return;
        }

        // 저장되지 않은 페이로드 정보 필터링
        Set<String> payloadSns = this.getDevicePayloadEntitiesByDeviceSn(drone.getDeviceSn())
                .stream().map(DevicePayloadDTO::getPayloadSn).collect(Collectors.toSet());

        Set<String> newPayloadSns = payloads.stream().map(DevicePayloadReceiver::getSn).collect(Collectors.toSet());
        payloadSns.removeAll(newPayloadSns);
        this.deletePayloadsByPayloadsSn(payloadSns);

        // 새로운 페이로드 정보 저장
        boolean isSave = this.savePayloadDTOs(drone, payloads);
        log.debug("The result of saving the payloads is {}.", isSave);
    }

    /**
     * 페이로드 시리얼 번호 목록으로 페이로드를 삭제합니다.
     * 
     * @param payloadSns 페이로드 시리얼 번호 목록
     */
    @Override
    public void deletePayloadsByPayloadsSn(Collection<String> payloadSns) {
        if (CollectionUtils.isEmpty(payloadSns)) {
            return;
        }
        mapper.delete(new LambdaUpdateWrapper<DevicePayloadEntity>()
                .or(wrapper -> payloadSns.forEach(sn -> wrapper.eq(DevicePayloadEntity::getPayloadSn, sn))));
    }

    /**
     * 페이로드 권한을 확인합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @param payloadIndex 페이로드 인덱스
     * @return 페이로드 권한 여부
     */
    @Override
    public Boolean checkAuthorityPayload(String deviceSn, String payloadIndex) {
        return deviceRedisService.getDeviceOnline(deviceSn).flatMap(device ->
                Optional.of(DeviceDomainEnum.DRONE == device.getDomain()
                        && !CollectionUtils.isEmpty(device.getPayloadsList())
                        && ControlSourceEnum.A ==
                        device.getPayloadsList().stream()
                                .filter(payload -> payloadIndex.equals(payload.getPayloadIndex().toString()))
                                .map(DevicePayloadDTO::getControlSource).findAny()
                                .orElse(ControlSourceEnum.B)))
                .orElse(true);
    }

    /**
     * 데이터베이스 엔티티 객체를 페이로드 DTO로 변환합니다.
     * 
     * @param entity 페이로드 엔티티
     * @return 페이로드 DTO
     */
    private DevicePayloadDTO payloadEntityConvertToDTO(DevicePayloadEntity entity) {
        DevicePayloadDTO.DevicePayloadDTOBuilder builder = DevicePayloadDTO.builder();
        if (entity != null) {
            builder.payloadSn(entity.getPayloadSn())
                    .payloadName(entity.getPayloadName())
                    .payloadDesc(entity.getPayloadDesc())
                    .index(entity.getPayloadIndex())
                    .payloadIndex(new PayloadIndex()
                            .setType(DeviceTypeEnum.find(entity.getPayloadType()))
                            .setSubType(DeviceSubTypeEnum.find(entity.getSubType()))
                            .setPosition(PayloadPositionEnum.find(entity.getPayloadIndex())))
                    .controlSource(ControlSourceEnum.find(entity.getControlSource()));
        }
        return builder.build();
    }

    /**
     * 수신된 페이로드 객체를 데이터베이스 엔티티 객체로 변환합니다.
     * 
     * @param dto 페이로드 수신자
     * @return 페이로드 엔티티
     */
    private DevicePayloadEntity receiverConvertToEntity(DevicePayloadReceiver dto) {
        if (dto == null) {
            return new DevicePayloadEntity();
        }
        DevicePayloadEntity.DevicePayloadEntityBuilder builder = DevicePayloadEntity.builder();

        // cameraIndex는 드론에 매달린 페이로드의 타입, 서브타입, 인덱스로 구성됩니다.
        // type-subType-index
        Optional<DeviceDictionaryDTO> dictionaryOpt = dictionaryService.getOneDictionaryInfoByTypeSubType(
                DeviceDomainEnum.PAYLOAD.getDomain(), dto.getPayloadIndex().getType().getType(),
                dto.getPayloadIndex().getSubType().getSubType());
        dictionaryOpt.ifPresent(dictionary ->
                builder.payloadName(dictionary.getDeviceName())
                        .payloadDesc(dictionary.getDeviceDesc()));

        builder.payloadType(dto.getPayloadIndex().getType().getType())
                .subType(dto.getPayloadIndex().getSubType().getSubType())
                .payloadIndex(dto.getPayloadIndex().getPosition().getPosition())
                .controlSource(dto.getControlSource().getControlSource());

        return builder
                .payloadSn(dto.getSn())
                .deviceSn(dto.getDeviceSn())
                .build();
    }

    /**
     * 페이로드 수신자를 DTO로 변환합니다.
     * 
     * @param receiver 페이로드 수신자
     * @return 페이로드 DTO
     */
    private DevicePayloadDTO receiver2Dto(DevicePayloadReceiver receiver) {
        DevicePayloadDTO.DevicePayloadDTOBuilder builder = DevicePayloadDTO.builder();
        if (receiver == null) {
            return builder.build();
        }
        return builder.payloadSn(receiver.getSn())
                .payloadIndex(receiver.getPayloadIndex())
                .controlSource(receiver.getControlSource())
                .build();
    }

}