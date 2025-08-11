package com.dji.sample.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dji.sample.common.error.CommonErrorEnum;
import com.dji.sample.component.mqtt.model.EventsReceiver;
import com.dji.sample.component.websocket.model.BizCodeEnum;
import com.dji.sample.component.websocket.service.IWebSocketMessageService;
import com.dji.sample.control.model.enums.DroneAuthorityEnum;
import com.dji.sample.manage.dao.IDeviceMapper;
import com.dji.sample.manage.model.dto.*;
import com.dji.sample.manage.model.entity.DeviceEntity;
import com.dji.sample.manage.model.enums.DeviceFirmwareStatusEnum;
import com.dji.sample.manage.model.enums.PropertySetFieldEnum;
import com.dji.sample.manage.model.enums.UserTypeEnum;
import com.dji.sample.manage.model.param.DeviceQueryParam;
import com.dji.sample.manage.model.receiver.BasicDeviceProperty;
import com.dji.sample.manage.service.*;
import com.dji.sdk.cloudapi.device.*;
import com.dji.sdk.cloudapi.firmware.*;
import com.dji.sdk.cloudapi.firmware.api.AbstractFirmwareService;
import com.dji.sdk.cloudapi.property.api.AbstractPropertyService;
import com.dji.sdk.cloudapi.tsa.DeviceIconUrl;
import com.dji.sdk.cloudapi.tsa.TopologyDeviceModel;
import com.dji.sdk.common.*;
import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.exception.CloudSDKException;
import com.dji.sdk.mqtt.IMqttTopicService;
import com.dji.sdk.mqtt.MqttGatewayPublish;
import com.dji.sdk.mqtt.events.EventsSubscribe;
import com.dji.sdk.mqtt.osd.OsdSubscribe;
import com.dji.sdk.mqtt.property.PropertySetReplyResultEnum;
import com.dji.sdk.mqtt.property.PropertySetSubscribe;
import com.dji.sdk.mqtt.requests.RequestsSubscribe;
import com.dji.sdk.mqtt.services.ServicesReplyData;
import com.dji.sdk.mqtt.services.ServicesSubscribe;
import com.dji.sdk.mqtt.services.TopicServicesResponse;
import com.dji.sdk.mqtt.state.StateSubscribe;
import com.dji.sdk.mqtt.status.StatusSubscribe;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 디바이스 관리 서비스 구현체
 * 
 * DJI Cloud API의 디바이스 관리를 위한 핵심 서비스 구현체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 구현합니다:
 * 
 * 1. 디바이스 생명주기 관리
 *    - 디바이스 온라인/오프라인 상태 관리
 *    - MQTT 토픽 구독/구독 해제 처리
 *    - 디바이스 연결 상태 실시간 모니터링
 *    - Redis를 통한 디바이스 상태 캐싱
 * 
 * 2. 디바이스 토폴로지 관리
 *    - 게이트웨이-서브 디바이스 관계 관리
 *    - 웹 및 PILOT용 토폴로지 정보 제공
 *    - 디바이스 바인딩/언바인딩 처리
 *    - 실시간 토폴로지 변경 알림
 * 
 * 3. 디바이스 속성 및 제어 관리
 *    - 디바이스 속성 설정 및 조회
 *    - 펌웨어 업그레이드 작업 관리
 *    - 비행 제어 권한 관리
 *    - 도킹 스테이션 모드 관리
 * 
 * 4. 실시간 데이터 전송
 *    - OSD 데이터를 웹 및 PILOT으로 실시간 전송
 *    - WebSocket을 통한 실시간 상태 업데이트
 *    - 디바이스 상태 변경 알림
 *    - MQTT를 통한 디바이스 제어 명령 전송
 * 
 * 5. 데이터베이스 관리
 *    - 디바이스 정보 CRUD 작업
 *    - 펌웨어 상태 관리
 *    - 디바이스 이력 추적
 *    - 트랜잭션 관리
 * 
 * 주요 의존성:
 * - MqttGatewayPublish: MQTT 메시지 발행
 * - IDeviceMapper: 데이터베이스 접근
 * - IWebSocketMessageService: WebSocket 메시지 전송
 * - IDeviceRedisService: Redis 캐시 관리
 * - 다양한 MQTT 구독 서비스들
 * 
 * 이 클래스는 DJI 디바이스의 전체 생명주기를
 * 안전하고 효율적으로 관리하는 핵심 서비스입니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/10
 */
@Service
@Slf4j
@Transactional
public class DeviceServiceImpl implements IDeviceService {

    /**
     * MQTT 게이트웨이 메시지 발행 서비스
     * MQTT를 통해 디바이스에게 명령을 전송
     */
    @Autowired
    private MqttGatewayPublish messageSender;

    /**
     * 디바이스 데이터베이스 매퍼
     * 디바이스 정보의 CRUD 작업을 담당
     */
    @Autowired
    private IDeviceMapper mapper;

    /**
     * 디바이스 사전 관리 서비스
     * 디바이스 타입, 모델 정보 등을 관리
     */
    @Autowired
    private IDeviceDictionaryService dictionaryService;

    /**
     * MQTT 토픽 서비스
     * MQTT 토픽 구독/구독 해제를 관리
     */
    @Autowired
    private IMqttTopicService topicService;

    /**
     * 워크스페이스 관리 서비스
     * 조직 및 워크스페이스 정보를 관리
     */
    @Autowired
    private IWorkspaceService workspaceService;

    /**
     * 디바이스 페이로드 서비스
     * 드론의 페이로드(카메라, 센서 등) 정보를 관리
     */
    @Autowired
    private IDevicePayloadService payloadService;

    /**
     * WebSocket 메시지 서비스
     * 실시간으로 클라이언트에게 디바이스 상태 변경을 알림
     */
    @Autowired
    private IWebSocketMessageService webSocketMessageService;

    /**
     * JSON 객체 매퍼
     * JSON 데이터 변환을 담당
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 디바이스 펌웨어 서비스
     * 디바이스 펌웨어 업그레이드 작업을 관리
     */
    @Autowired
    private IDeviceFirmwareService deviceFirmwareService;

    /**
     * 용량 카메라 서비스
     * 카메라 용량 및 설정을 관리
     */
    @Autowired
    private ICapacityCameraService capacityCameraService;

    /**
     * 디바이스 Redis 서비스
     * 디바이스 상태 정보를 Redis에 캐싱하여 빠른 조회를 지원
     */
    @Autowired
    private IDeviceRedisService deviceRedisService;

    /**
     * 상태 구독 서비스
     * 디바이스 상태 변경을 구독
     */
    @Autowired
    private StatusSubscribe statusSubscribe;

    /**
     * 상태 변경 구독 서비스
     * 디바이스 상태 변경 이벤트를 구독
     */
    @Autowired
    private StateSubscribe stateSubscribe;

    /**
     * OSD 구독 서비스
     * 디바이스 OSD 데이터를 구독
     */
    @Autowired
    private OsdSubscribe osdSubscribe;

    /**
     * 서비스 구독 서비스
     * 디바이스 서비스 호출을 구독
     */
    @Autowired
    private ServicesSubscribe servicesSubscribe;

    /**
     * 이벤트 구독 서비스
     * 디바이스 이벤트를 구독
     */
    @Autowired
    private EventsSubscribe eventsSubscribe;

    /**
     * 요청 구독 서비스
     * 디바이스 요청을 구독
     */
    @Autowired
    private RequestsSubscribe requestsSubscribe;

    /**
     * 속성 설정 구독 서비스
     * 디바이스 속성 설정을 구독
     */
    @Autowired
    private PropertySetSubscribe propertySetSubscribe;

    /**
     * 속성 서비스
     * 디바이스 속성 관리
     */
    @Autowired
    private AbstractPropertyService abstractPropertyService;

    /**
     * 펌웨어 서비스
     * 디바이스 펌웨어 관리
     */
    @Autowired
    private AbstractFirmwareService abstractFirmwareService;

    /**
     * 서브 디바이스(드론) 오프라인 처리를 수행합니다.
     * 
     * Redis 캐시에서 디바이스 정보를 확인하고, 게이트웨이 토픽 구독을 해제한 후
     * 디바이스 오프라인 상태를 설정하고 토폴로지 정보를 업데이트합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     */
    @Override
    public void subDeviceOffline(String deviceSn) {
        // Redis 캐시에 디바이스 정보가 없으면 이미 오프라인 상태로 간주
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(deviceSn);
        if (deviceOpt.isEmpty()) {
            log.debug("The drone is already offline.");
            return;
        }
        try {
            // 게이트웨이 온라인 토픽 구독 해제
            gatewayOnlineSubscribeTopic(SDKManager.getDeviceSDK(String.valueOf(deviceOpt.get().getParentSn())));
        } catch (CloudSDKException e) {
            log.debug("The gateway is already offline.", e);
        }
        // 서브 디바이스 오프라인 상태 설정
        deviceRedisService.subDeviceOffline(deviceSn);
        // 현재 워크스페이스의 최신 디바이스 토폴로지 정보 발행
        pushDeviceOfflineTopo(deviceOpt.get().getWorkspaceId(), deviceSn);
        log.debug("{} offline.", deviceSn);
    }

    /**
     * 게이트웨이 오프라인 처리를 수행합니다.
     * 
     * 게이트웨이와 연결된 모든 서브 디바이스들을 오프라인 처리하고,
     * MQTT 토픽 구독을 해제합니다.
     * 
     * @param gatewaySn 게이트웨이 시리얼 번호
     */
    @Override
    public void gatewayOffline(String gatewaySn) {
        // Redis 캐시에 게이트웨이 정보가 없으면 이미 오프라인 상태로 간주
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(gatewaySn);
        if (deviceOpt.isEmpty()) {
            log.debug("The gateway is already offline.");
            return;
        }

        // 서브 디바이스 오프라인 처리
        deviceRedisService.subDeviceOffline(deviceOpt.get().getChildDeviceSn());
        // 게이트웨이 오프라인 처리
        deviceRedisService.gatewayOffline(gatewaySn);
        // MQTT 토픽 구독 해제
        offlineUnsubscribeTopic(SDKManager.getDeviceSDK(gatewaySn));
        // 현재 워크스페이스의 최신 디바이스 토폴로지 정보 발행
        pushDeviceOfflineTopo(deviceOpt.get().getWorkspaceId(), gatewaySn);
        log.debug("{} offline.", gatewaySn);
    }

    /**
     * 게이트웨이 온라인 시 MQTT 토픽을 구독합니다.
     * 
     * 게이트웨이의 상태, 상태 변경, OSD 데이터, 서비스, 이벤트, 요청, 속성 설정 등의
     * 모든 MQTT 토픽을 구독합니다.
     * 
     * @param gateway 게이트웨이 매니저 객체
     */
    @Override
    public void gatewayOnlineSubscribeTopic(GatewayManager gateway) {
        statusSubscribe.subscribe(gateway);
        stateSubscribe.subscribe(gateway, true);
        osdSubscribe.subscribe(gateway, true);
        servicesSubscribe.subscribe(gateway);
        eventsSubscribe.subscribe(gateway, true);
        requestsSubscribe.subscribe(gateway);
        propertySetSubscribe.subscribe(gateway);
    }

    /**
     * 서브 디바이스 온라인 시 MQTT 토픽을 구독합니다.
     * 
     * 서브 디바이스의 상태 정보를 구독하되, 상태 변경과 OSD 데이터는 구독하지 않습니다.
     * 
     * @param gateway 게이트웨이 매니저 객체
     */
    @Override
    public void subDeviceOnlineSubscribeTopic(GatewayManager gateway) {
        statusSubscribe.subscribe(gateway);
        stateSubscribe.subscribe(gateway, false);
        osdSubscribe.subscribe(gateway, false);
        servicesSubscribe.subscribe(gateway);
        eventsSubscribe.subscribe(gateway, false);
        requestsSubscribe.subscribe(gateway);
        propertySetSubscribe.subscribe(gateway);
    }

    /**
     * 디바이스 오프라인 시 모든 MQTT 토픽 구독을 해제합니다.
     * 
     * @param gateway 게이트웨이 매니저 객체
     */
    @Override
    public void offlineUnsubscribeTopic(GatewayManager gateway) {
        statusSubscribe.unsubscribe(gateway);
        stateSubscribe.unsubscribe(gateway);
        osdSubscribe.unsubscribe(gateway);
        servicesSubscribe.unsubscribe(gateway);
        eventsSubscribe.unsubscribe(gateway);
        requestsSubscribe.unsubscribe(gateway);
        propertySetSubscribe.unsubscribe(gateway);
    }

    /**
     * 조건에 따라 디바이스 목록을 조회합니다.
     * 
     * 다양한 검색 조건을 사용하여 디바이스를 필터링하고 조회합니다.
     * 
     * @param param 디바이스 조회 파라미터
     * @return 조건에 맞는 디바이스 목록
     */
    @Override
    public List<DeviceDTO> getDevicesByParams(DeviceQueryParam param) {
        return mapper.selectList(
                new LambdaQueryWrapper<DeviceEntity>()
                        .eq(StringUtils.hasText(param.getDeviceSn()),
                                DeviceEntity::getDeviceSn, param.getDeviceSn())
                        .eq(param.getDeviceType() != null,
                                DeviceEntity::getDeviceType, param.getDeviceType())
                        .eq(param.getSubType() != null,
                                DeviceEntity::getSubType, param.getSubType())
                        .eq(StringUtils.hasText(param.getChildSn()),
                                DeviceEntity::getChildSn, param.getChildSn())
                        .and(!CollectionUtils.isEmpty(param.getDomains()), wrapper -> {
                            for (Integer domain : param.getDomains()) {
                                wrapper.eq(DeviceEntity::getDomain, domain).or();
                            }
                        })
                        .eq(StringUtils.hasText(param.getWorkspaceId()),
                                DeviceEntity::getWorkspaceId, param.getWorkspaceId())
                        .eq(param.getBoundStatus() != null, DeviceEntity::getBoundStatus, param.getBoundStatus())
                        .orderBy(param.isOrderBy(),
                                param.isAsc(), DeviceEntity::getId))
                .stream()
                .map(this::deviceEntityConvertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * 웹용 디바이스 토폴로지를 조회합니다.
     * 
     * 워크스페이스 내의 모든 게이트웨이 디바이스를 조회하고,
     * 온라인 상태인 디바이스들의 토폴로지 정보를 구성합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @return 웹용 디바이스 토폴로지 목록
     */
    @Override
    public List<DeviceDTO> getDevicesTopoForWeb(String workspaceId) {
        List<DeviceDTO> devicesList = this.getDevicesByParams(
                DeviceQueryParam.builder()
                        .workspaceId(workspaceId)
                        .domains(List.of(DeviceDomainEnum.REMOTER_CONTROL.getDomain(), DeviceDomainEnum.DOCK.getDomain()))
                        .build());

        devicesList.stream()
                .filter(gateway -> DeviceDomainEnum.DOCK == gateway.getDomain() ||
                        deviceRedisService.checkDeviceOnline(gateway.getDeviceSn()))
                .forEach(this::spliceDeviceTopo);

        return devicesList;
    }

    /**
     * 디바이스 토폴로지 정보를 구성합니다.
     * 
     * 게이트웨이 디바이스의 상태를 확인하고, 연결된 서브 디바이스와 페이로드 정보를
     * 포함한 완전한 토폴로지 구조를 만듭니다.
     * 
     * @param gateway 게이트웨이 디바이스 DTO
     */
    @Override
    public void spliceDeviceTopo(DeviceDTO gateway) {

        gateway.setStatus(deviceRedisService.checkDeviceOnline(gateway.getDeviceSn()));

        // 서브 디바이스 정보 조회
        if (!StringUtils.hasText(gateway.getChildDeviceSn())) {
            return;
        }

        DeviceDTO subDevice = getDevicesByParams(DeviceQueryParam.builder().deviceSn(gateway.getChildDeviceSn()).build()).get(0);
        subDevice.setStatus(deviceRedisService.checkDeviceOnline(subDevice.getDeviceSn()));
        gateway.setChildren(subDevice);

        // 페이로드 정보 조회
        subDevice.setPayloadsList(payloadService.getDevicePayloadEntitiesByDeviceSn(gateway.getChildDeviceSn()));
    }

    /**
     * PILOT용 디바이스 토폴로지를 조회합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return PILOT용 토폴로지 디바이스 정보 (Optional)
     */
    @Override
    public Optional<TopologyDeviceDTO> getDeviceTopoForPilot(String sn) {
        if (!StringUtils.hasText(sn)) {
            return Optional.empty();
        }
        List<TopologyDeviceDTO> topologyDeviceList = this.getDevicesByParams(
                DeviceQueryParam.builder()
                        .deviceSn(sn)
                        .build())
                .stream()
                .map(this::deviceConvertToTopologyDTO)
                .collect(Collectors.toList());
        if (topologyDeviceList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(topologyDeviceList.get(0));
    }

    /**
     * 디바이스 DTO를 토폴로지 DTO로 변환합니다.
     * 
     * @param device 디바이스 DTO
     * @return 토폴로지 디바이스 DTO
     */
    @Override
    public TopologyDeviceDTO deviceConvertToTopologyDTO(DeviceDTO device) {
        if (device == null) {
            return null;
        }
        return new TopologyDeviceDTO()
                    .setSn(device.getDeviceSn())
                    .setDeviceCallsign(device.getNickname())
                    .setDeviceModel(new TopologyDeviceModel()
                            .setDomain(device.getDomain())
                            .setSubType(device.getSubType())
                            .setType(device.getType())
                            .setDeviceModelKey(DeviceEnum.find(device.getDomain(), device.getType(), device.getSubType())))
                    .setIconUrls(device.getIconUrl())
                    .setOnlineStatus(deviceRedisService.checkDeviceOnline(device.getDeviceSn()))
                    .setUserCallsign(device.getNickname())
                    .setBoundStatus(device.getBoundStatus())
                    .setModel(device.getDeviceName())
                    .setUserId(device.getUserId())
                    .setDomain(device.getDomain())
                    .setGatewaySn(device.getParentSn());
    }

    /**
     * 디바이스 오프라인 토폴로지 정보를 WebSocket으로 전송합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param deviceSn 디바이스 시리얼 번호
     */
    @Override
    public void pushDeviceOfflineTopo(String workspaceId, String deviceSn) {
        webSocketMessageService.sendBatch(
                workspaceId, null, com.dji.sdk.websocket.BizCodeEnum.DEVICE_OFFLINE.getCode(),
                new TopologyDeviceDTO().setSn(deviceSn).setOnlineStatus(false));
    }

    /**
     * 디바이스 온라인 토폴로지 정보를 WebSocket으로 전송합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @param deviceSn 디바이스 시리얼 번호
     */
    @Override
    public void pushDeviceOnlineTopo(String workspaceId, String gatewaySn, String deviceSn) {
        webSocketMessageService.sendBatch(
                workspaceId, null, com.dji.sdk.websocket.BizCodeEnum.DEVICE_ONLINE.getCode(),
                getDeviceTopoForPilot(deviceSn).orElseGet(TopologyDeviceDTO::new).setGatewaySn(gatewaySn));
    }

    /**
     * OSD 데이터를 PILOT으로 전송합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param sn 디바이스 시리얼 번호
     * @param data OSD 데이터
     */
    @Override
    public void pushOsdDataToPilot(String workspaceId, String sn, DeviceOsdHost data) {
        webSocketMessageService.sendBatch(
                workspaceId, UserTypeEnum.PILOT.getVal(), com.dji.sdk.websocket.BizCodeEnum.DEVICE_OSD.getCode(),
                new DeviceOsdWsResponse()
                        .setSn(sn)
                        .setHost(data));
    }

    /**
     * OSD 데이터를 웹으로 전송합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param codeEnum 비즈니스 코드 열거형
     * @param sn 디바이스 시리얼 번호
     * @param data 전송할 데이터
     */
    @Override
    public void pushOsdDataToWeb(String workspaceId, BizCodeEnum codeEnum, String sn, Object data) {
        webSocketMessageService.sendBatch(
                workspaceId, UserTypeEnum.WEB.getVal(), codeEnum.getCode(), TelemetryDTO.builder().sn(sn).host(data).build());
    }

    /**
     * 디바이스 정보를 저장하거나 업데이트합니다.
     * 
     * 디바이스가 이미 존재하면 업데이트하고, 존재하지 않으면 새로 저장합니다.
     * 
     * @param device 저장/업데이트할 디바이스 정보
     * @return 저장/업데이트 성공 여부
     */
    public Boolean saveOrUpdateDevice(DeviceDTO device) {
        int count = mapper.selectCount(
                new LambdaQueryWrapper<DeviceEntity>()
                        .eq(DeviceEntity::getDeviceSn, device.getDeviceSn()));
        return count > 0 ? updateDevice(device) : saveDevice(device) > 0;
    }

    /**
     * 디바이스 정보를 저장합니다.
     * 
     * @param device 저장할 디바이스 정보
     * @return 저장된 디바이스의 ID
     */
    public Integer saveDevice(DeviceDTO device) {
        DeviceEntity entity = deviceDTO2Entity(device);
        return mapper.insert(entity) > 0 ? entity.getId() : -1;
    }

    /**
     * 데이터베이스 엔티티 객체를 디바이스 DTO로 변환합니다.
     * 
     * @param entity 디바이스 엔티티
     * @return 디바이스 DTO
     */
    private DeviceDTO deviceEntityConvertToDTO(DeviceEntity entity) {
        if (entity == null) {
            return null;
        }
        DeviceDTO.DeviceDTOBuilder builder = DeviceDTO.builder();
        try {
            builder
                    .deviceSn(entity.getDeviceSn())
                    .childDeviceSn(entity.getChildSn())
                    .deviceName(entity.getDeviceName())
                    .deviceDesc(entity.getDeviceDesc())
                    .controlSource(ControlSourceEnum.find(entity.getDeviceIndex()))
                    .workspaceId(entity.getWorkspaceId())
                    .type(DeviceTypeEnum.find(entity.getDeviceType()))
                    .subType(DeviceSubTypeEnum.find(entity.getSubType()))
                    .domain(DeviceDomainEnum.find(entity.getDomain()))
                    .iconUrl(new DeviceIconUrl()
                            .setNormalIconUrl(entity.getUrlNormal())
                            .setSelectIconUrl(entity.getUrlSelect()))
                    .boundStatus(entity.getBoundStatus())
                    .loginTime(entity.getLoginTime() != null ?
                            LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getLoginTime()), ZoneId.systemDefault())
                            : null)
                    .boundTime(entity.getBoundTime() != null ?
                            LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getBoundTime()), ZoneId.systemDefault())
                            : null)
                    .nickname(entity.getNickname())
                    .firmwareVersion(entity.getFirmwareVersion())
                    .workspaceName(entity.getWorkspaceId() != null ?
                            workspaceService.getWorkspaceByWorkspaceId(entity.getWorkspaceId())
                                    .map(WorkspaceDTO::getWorkspaceName).orElse("") : "")
                    .firmwareStatus(DeviceFirmwareStatusEnum.NOT_UPGRADE)
                    .thingVersion(entity.getVersion()).build();
        } catch (CloudSDKException e) {
            log.error(e.getLocalizedMessage() + "Entity: {}", entity);
        }
        DeviceDTO deviceDTO = builder.build();
        addFirmwareStatus(deviceDTO, entity);
        return deviceDTO;
    }

    /**
     * 디바이스에 펌웨어 상태 정보를 추가합니다.
     * 
     * 현재 펌웨어 버전과 최신 펌웨어 버전을 비교하여 업그레이드 상태를 결정합니다.
     * 
     * @param deviceDTO 디바이스 DTO
     * @param entity 디바이스 엔티티
     */
    private void addFirmwareStatus(DeviceDTO deviceDTO, DeviceEntity entity) {
        if (!StringUtils.hasText(entity.getFirmwareVersion())) {
            return;
        }
        // 디바이스가 펌웨어 업그레이드 중인지 확인
        Optional<EventsReceiver<OtaProgress>> progressOpt = deviceRedisService.getFirmwareUpgradingProgress(entity.getDeviceSn());
        if (progressOpt.isPresent()) {
            deviceDTO.setFirmwareStatus(DeviceFirmwareStatusEnum.UPGRADING);
            deviceDTO.setFirmwareProgress(progressOpt.map(EventsReceiver::getOutput)
                            .map(OtaProgress::getProgress)
                            .map(OtaProgressData::getPercent)
                            .orElse(0));
            return;
        }

        // 디바이스 모델의 최신 펌웨어 버전을 조회하고 현재 펌웨어 버전과 비교하여
        // 업그레이드가 필요한지 확인
        Optional<DeviceFirmwareNoteDTO> firmwareReleaseNoteOpt = deviceFirmwareService.getLatestFirmwareReleaseNote(entity.getDeviceName());
        if (firmwareReleaseNoteOpt.isEmpty()) {
            deviceDTO.setFirmwareStatus(DeviceFirmwareStatusEnum.NOT_UPGRADE);
            return;
        }
        if (entity.getFirmwareVersion().equals(firmwareReleaseNoteOpt.get().getProductVersion())) {
            deviceDTO.setFirmwareStatus(entity.getCompatibleStatus() ?
                    DeviceFirmwareStatusEnum.NOT_UPGRADE :
                    DeviceFirmwareStatusEnum.CONSISTENT_UPGRADE);
            return;
        }
        deviceDTO.setFirmwareStatus(DeviceFirmwareStatusEnum.NORMAL_UPGRADE);
    }

    /**
     * 디바이스 정보를 업데이트합니다.
     * 
     * @param deviceDTO 업데이트할 디바이스 정보
     * @return 업데이트 성공 여부
     */
    @Override
    public Boolean updateDevice(DeviceDTO deviceDTO) {
        int update = mapper.update(this.deviceDTO2Entity(deviceDTO),
                new LambdaUpdateWrapper<DeviceEntity>().eq(DeviceEntity::getDeviceSn, deviceDTO.getDeviceSn()));
        return update > 0;
    }

    /**
     * 디바이스를 바인딩합니다.
     * 
     * 디바이스를 워크스페이스에 바인딩하고 Redis 캐시를 업데이트하며,
     * 토폴로지 정보를 WebSocket으로 전송합니다.
     * 
     * @param device 바인딩할 디바이스 정보
     * @return 바인딩 성공 여부
     */
    @Override
    public Boolean bindDevice(DeviceDTO device) {
        device.setBoundStatus(true);
        device.setBoundTime(LocalDateTime.now());

        boolean isUpd = this.updateDevice(device);
        if (!isUpd) {
            return false;
        }

        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(device.getDeviceSn());
        if (deviceOpt.isEmpty()) {
            return false;
        }

        DeviceDTO redisDevice = deviceOpt.get();
        redisDevice.setWorkspaceId(device.getWorkspaceId());
        deviceRedisService.setDeviceOnline(redisDevice);

        String gatewaySn, deviceSn;
        if (DeviceDomainEnum.REMOTER_CONTROL == redisDevice.getDomain()) {
            gatewaySn = device.getDeviceSn();
            deviceSn = redisDevice.getChildDeviceSn();
        } else {
            gatewaySn = redisDevice.getParentSn();
            deviceSn = device.getDeviceSn();
        }

        pushDeviceOnlineTopo(device.getWorkspaceId(), gatewaySn, deviceSn);
        subDeviceOnlineSubscribeTopic(SDKManager.getDeviceSDK(gatewaySn));
        return true;
    }

    /**
     * 도메인별로 바인딩된 디바이스 목록을 페이지네이션으로 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param page 페이지 번호
     * @param pageSize 페이지 크기
     * @param domain 디바이스 도메인
     * @return 페이지네이션된 바인딩된 디바이스 목록
     */
    @Override
    public PaginationData<DeviceDTO> getBoundDevicesWithDomain(String workspaceId, Long page,
                                                               Long pageSize, Integer domain) {

        Page<DeviceEntity> pagination = mapper.selectPage(new Page<>(page, pageSize),
                new LambdaQueryWrapper<DeviceEntity>()
                        .eq(DeviceEntity::getDomain, domain)
                        .eq(DeviceEntity::getWorkspaceId, workspaceId)
                        .eq(DeviceEntity::getBoundStatus, true));
        List<DeviceDTO> devicesList = pagination.getRecords().stream().map(this::deviceEntityConvertToDTO)
                .peek(device -> {
                    device.setStatus(deviceRedisService.checkDeviceOnline(device.getDeviceSn()));
                    if (StringUtils.hasText(device.getChildDeviceSn())) {
                        Optional<DeviceDTO> childOpt = this.getDeviceBySn(device.getChildDeviceSn());
                        childOpt.ifPresent(child -> {
                            child.setStatus(deviceRedisService.checkDeviceOnline(child.getDeviceSn()));
                            child.setWorkspaceName(device.getWorkspaceName());
                            device.setChildren(child);
                        });
                    }
                })
                .collect(Collectors.toList());
        return new PaginationData<DeviceDTO>(devicesList, new Pagination(pagination.getCurrent(), pagination.getSize(), pagination.getTotal()));
    }

    /**
     * 디바이스 바인딩을 해제합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     */
    @Override
    public void unbindDevice(String deviceSn) {

        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(deviceSn);
        if (deviceOpt.isPresent()) {
            subDeviceOffline(deviceSn);
        } else {
            deviceOpt = getDeviceBySn(deviceSn);
        }
        if (deviceOpt.isEmpty()) {
            return;
        }
        DeviceDTO device = DeviceDTO.builder()
                .deviceSn(deviceSn)
                .workspaceId("")
                .userId("")
                .boundStatus(false)
                .build();
        this.updateDevice(device);
    }

    /**
     * 시리얼 번호로 디바이스를 조회합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 디바이스 정보 (Optional)
     */
    @Override
    public Optional<DeviceDTO> getDeviceBySn(String sn) {
        List<DeviceDTO> devicesList = this.getDevicesByParams(DeviceQueryParam.builder().deviceSn(sn).build());
        if (devicesList.isEmpty()) {
            return Optional.empty();
        }
        DeviceDTO device = devicesList.get(0);
        device.setStatus(deviceRedisService.checkDeviceOnline(sn));
        return Optional.of(device);
    }

    /**
     * 디바이스 OTA 작업을 생성합니다.
     * 
     * 펌웨어 업그레이드를 위한 OTA 작업을 생성하고 디바이스 상태를 업데이트합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param upgradeDTOS 업그레이드할 디바이스 목록
     * @return OTA 작업 생성 결과
     */
    @Override
    public HttpResultResponse createDeviceOtaJob(String workspaceId, List<DeviceFirmwareUpgradeDTO> upgradeDTOS) {
        List<OtaCreateDevice> deviceOtaFirmwares = deviceFirmwareService.getDeviceOtaFirmware(workspaceId, upgradeDTOS);
        if (deviceOtaFirmwares.isEmpty()) {
            return HttpResultResponse.error();
        }

        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(deviceOtaFirmwares.get(0).getSn());
        if (deviceOpt.isEmpty()) {
            throw new RuntimeException("Device is offline.");
        }
        DeviceDTO device = deviceOpt.get();
        String gatewaySn = DeviceDomainEnum.DOCK == device.getDomain() ? device.getDeviceSn() : device.getParentSn();

        checkOtaConditions(gatewaySn);

        TopicServicesResponse<ServicesReplyData<OtaCreateResponse>> response = abstractFirmwareService.otaCreate(
                SDKManager.getDeviceSDK(gatewaySn), new OtaCreateRequest().setDevices(deviceOtaFirmwares));
        ServicesReplyData<OtaCreateResponse> serviceReply = response.getData();
        String bid = response.getBid();
        if (!serviceReply.getResult().isSuccess()) {
            return HttpResultResponse.error(serviceReply.getResult());
        }

        // 업데이트가 필요한 디바이스 상태를 기록
        deviceOtaFirmwares.forEach(deviceOta -> deviceRedisService.setFirmwareUpgrading(deviceOta.getSn(),
                EventsReceiver.<OtaProgress>builder().bid(bid).sn(deviceOta.getSn()).build()));
        return HttpResultResponse.success();
    }

    /**
     * 펌웨어 업그레이드 가능 여부를 확인합니다.
     * 
     * 도킹 스테이션의 비상 정지 상태와 모드를 확인하여 업그레이드 가능 여부를 판단합니다.
     * 
     * @param dockSn 도킹 스테이션 시리얼 번호
     */
    private void checkOtaConditions(String dockSn) {
        Optional<OsdDock> deviceOpt = deviceRedisService.getDeviceOsd(dockSn, OsdDock.class);
        if (deviceOpt.isEmpty()) {
            throw new RuntimeException("Dock is offline.");
        }
        boolean emergencyStopState = deviceOpt.get().getEmergencyStopState();
        if (emergencyStopState) {
            throw new RuntimeException("The emergency stop button of the dock is pressed and can't be upgraded.");
        }

        DockModeCodeEnum dockMode = this.getDockMode(dockSn);
        if (DockModeCodeEnum.IDLE != dockMode) {
            throw new RuntimeException("The current status of the dock can't be upgraded.");
        }
    }

    /**
     * 디바이스 속성을 설정합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param dockSn 도킹 스테이션 시리얼 번호
     * @param param 설정할 속성 파라미터
     * @return 속성 설정 결과
     */
    @Override
    public int devicePropertySet(String workspaceId, String dockSn, JsonNode param) {
        String property = param.fieldNames().next();
        PropertySetFieldEnum propertyEnum = PropertySetFieldEnum.find(property);

        Optional<DeviceDTO> dockOpt = deviceRedisService.getDeviceOnline(dockSn);
        if (dockOpt.isEmpty()) {
            throw new RuntimeException("Dock is offline.");
        }
        String childSn = dockOpt.get().getChildDeviceSn();
        Optional<OsdDockDrone> osdOpt = deviceRedisService.getDeviceOsd(childSn, OsdDockDrone.class);
        if (osdOpt.isEmpty()) {
            throw new RuntimeException("Device is offline.");
        }

        // 데이터 유효성 확인
        BasicDeviceProperty basicDeviceProperty = objectMapper.convertValue(param.get(property), propertyEnum.getClazz());
        boolean valid = basicDeviceProperty.valid();
        if (!valid) {
            throw new IllegalArgumentException(CommonErrorEnum.ILLEGAL_ARGUMENT.getMessage());
        }
        boolean isPublish = basicDeviceProperty.canPublish(osdOpt.get());
        if (!isPublish) {
            return PropertySetReplyResultEnum.SUCCESS.getResult();
        }
        BaseModel baseModel = objectMapper.convertValue(param, propertyEnum.getProperty().getClazz());
        PropertySetReplyResultEnum result = abstractPropertyService.propertySet(
                SDKManager.getDeviceSDK(dockSn), propertyEnum.getProperty(), baseModel);
        return result.getResult();
    }

    /**
     * 도킹 스테이션의 모드를 조회합니다.
     * 
     * @param dockSn 도킹 스테이션 시리얼 번호
     * @return 도킹 스테이션 모드
     */
    @Override
    public DockModeCodeEnum getDockMode(String dockSn) {
        return deviceRedisService.getDeviceOsd(dockSn, OsdDock.class)
                .map(OsdDock::getModeCode).orElse(null);
    }

    /**
     * 디바이스의 모드를 조회합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @return 디바이스 모드
     */
    @Override
    public DroneModeCodeEnum getDeviceMode(String deviceSn) {
        return deviceRedisService.getDeviceOsd(deviceSn, OsdDockDrone.class)
                .map(OsdDockDrone::getModeCode).orElse(DroneModeCodeEnum.DISCONNECTED);
    }

    /**
     * 도킹 스테이션의 DRC 모드 상태를 확인합니다.
     * 
     * @param dockSn 도킹 스테이션 시리얼 번호
     * @return DRC 모드 활성화 여부
     */
    @Override
    public Boolean checkDockDrcMode(String dockSn) {
        return deviceRedisService.getDeviceOsd(dockSn, OsdDock.class)
                .map(OsdDock::getDrcState)
                .orElse(DrcStateEnum.DISCONNECTED) != DrcStateEnum.DISCONNECTED;
    }

    /**
     * 비행 제어 권한을 확인합니다.
     * 
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @return 비행 제어 권한 여부
     */
    @Override
    public Boolean checkAuthorityFlight(String gatewaySn) {
        return deviceRedisService.getDeviceOnline(gatewaySn).flatMap(gateway ->
                Optional.of((DeviceDomainEnum.DOCK == gateway.getDomain()
                        || DeviceDomainEnum.REMOTER_CONTROL == gateway.getDomain())
                    && ControlSourceEnum.A == gateway.getControlSource()))
                .orElse(true);
    }

    /**
     * 비행 제어 권한을 업데이트합니다.
     * 
     * @param gateway 게이트웨이 디바이스
     * @param controlSource 제어 소스
     */
    @Override
    public void updateFlightControl(DeviceDTO gateway, ControlSourceEnum controlSource) {
        if (controlSource == gateway.getControlSource()) {
            return;
        }
        gateway.setControlSource(controlSource);
        deviceRedisService.setDeviceOnline(gateway);

        webSocketMessageService.sendBatch(gateway.getWorkspaceId(), UserTypeEnum.WEB.getVal(),
                BizCodeEnum.CONTROL_SOURCE_CHANGE.getCode(),
                DeviceAuthorityDTO.builder()
                        .controlSource(gateway.getControlSource())
                        .sn(gateway.getDeviceSn())
                        .type(DroneAuthorityEnum.FLIGHT)
                        .build());
    }

    /**
     * 디바이스 DTO를 데이터베이스 엔티티 객체로 변환합니다.
     * 
     * @param dto 디바이스 DTO
     * @return 디바이스 엔티티
     */
    private DeviceEntity deviceDTO2Entity(DeviceDTO dto) {
        DeviceEntity.DeviceEntityBuilder builder = DeviceEntity.builder();
        if (dto == null) {
            return builder.build();
        }

        return builder.deviceSn(dto.getDeviceSn())
                .deviceIndex(Optional.ofNullable(dto.getControlSource())
                        .map(ControlSourceEnum::getControlSource).orElse(null))
                .deviceName(dto.getDeviceName())
                .version(dto.getThingVersion())
                .userId(dto.getUserId())
                .nickname(dto.getNickname())
                .workspaceId(dto.getWorkspaceId())
                .boundStatus(dto.getBoundStatus())
                .domain(Optional.ofNullable(dto.getDomain()).map(DeviceDomainEnum::getDomain).orElse(null))
                .deviceType(Optional.ofNullable(dto.getType()).map(DeviceTypeEnum::getType).orElse(null))
                .subType(Optional.ofNullable(dto.getSubType()).map(DeviceSubTypeEnum::getSubType).orElse(null))
                .loginTime(dto.getLoginTime() != null ?
                        dto.getLoginTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() : null)
                .boundTime(dto.getBoundTime() != null ?
                        dto.getBoundTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() : null)
                .childSn(dto.getChildDeviceSn())
                .firmwareVersion(dto.getFirmwareVersion())
                .compatibleStatus(dto.getFirmwareStatus() == null ? null :
                        DeviceFirmwareStatusEnum.CONSISTENT_UPGRADE != dto.getFirmwareStatus())
                .deviceDesc(dto.getDeviceDesc())
                .build();
    }
}