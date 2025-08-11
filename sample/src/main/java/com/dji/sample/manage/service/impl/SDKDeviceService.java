package com.dji.sample.manage.service.impl;

import com.dji.sample.component.websocket.model.BizCodeEnum;
import com.dji.sample.component.websocket.service.IWebSocketMessageService;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.model.dto.DevicePayloadReceiver;
import com.dji.sample.manage.model.enums.DeviceFirmwareStatusEnum;
import com.dji.sample.manage.model.param.DeviceQueryParam;
import com.dji.sample.manage.service.IDeviceDictionaryService;
import com.dji.sample.manage.service.IDevicePayloadService;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sample.manage.service.IDeviceService;
import com.dji.sdk.cloudapi.device.*;
import com.dji.sdk.cloudapi.device.api.AbstractDeviceService;
import com.dji.sdk.cloudapi.tsa.DeviceIconUrl;
import com.dji.sdk.cloudapi.tsa.IconUrlEnum;
import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.common.SDKManager;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.osd.TopicOsdRequest;
import com.dji.sdk.mqtt.state.TopicStateRequest;
import com.dji.sdk.mqtt.status.TopicStatusRequest;
import com.dji.sdk.mqtt.status.TopicStatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * SDK 디바이스 서비스 구현체
 * 
 * DJI Cloud API의 디바이스 관리를 위한 SDK 서비스 구현체입니다.
 * 이 클래스는 AbstractDeviceService를 상속받아 DJI 디바이스의
 * 온라인/오프라인 상태, OSD 데이터, 펌웨어 업데이트 등을 처리합니다.
 * 
 * 주요 기능:
 * 1. 디바이스 온라인/오프라인 관리
 *    - 토폴로지 업데이트 처리
 *    - 게이트웨이-서브 디바이스 연결 관리
 *    - 디바이스 상태 변경 알림
 *    - 온라인 재연결 처리
 * 
 * 2. OSD 데이터 처리
 *    - 도킹 스테이션 OSD 데이터 처리
 *    - 드론 OSD 데이터 처리
 *    - 리모트 컨트롤 OSD 데이터 처리
 *    - RC 드론 OSD 데이터 처리
 * 
 * 3. 펌웨어 버전 관리
 *    - 도킹 스테이션 펌웨어 버전 업데이트
 *    - RC 및 드론 펌웨어 버전 업데이트
 *    - RC 페이로드 펌웨어 버전 업데이트
 *    - 펌웨어 상태 추적
 * 
 * 4. 제어 소스 관리
 *    - 도킹 스테이션 제어 소스 업데이트
 *    - RC 제어 소스 업데이트
 *    - 제어 권한 변경 알림
 *    - 제어 소스 상태 모니터링
 * 
 * 5. 디바이스 데이터 변환
 *    - 게이트웨이 데이터 변환
 *    - 서브 디바이스 데이터 변환
 *    - 디바이스 저장 및 업데이트
 *    - 디바이스 메타데이터 관리
 * 
 * 주요 의존성:
 * - IDeviceRedisService: Redis 캐시 관리
 * - IDeviceService: 디바이스 관리 서비스
 * - IDeviceDictionaryService: 디바이스 사전 관리
 * - IWebSocketMessageService: WebSocket 메시지 전송
 * - IDevicePayloadService: 페이로드 관리 서비스
 * 
 * 이 클래스는 DJI 디바이스의 실시간 상태를
 * 안전하고 효율적으로 관리하는 SDK 서비스입니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/7/4
 */
@Service
@Slf4j
public class SDKDeviceService extends AbstractDeviceService {

    /**
     * 디바이스 Redis 서비스
     * 디바이스 상태 정보를 Redis에 캐싱하여 빠른 조회를 지원
     */
    @Autowired
    private IDeviceRedisService deviceRedisService;

    /**
     * 디바이스 관리 서비스
     * 디바이스 정보의 CRUD 작업을 담당
     */
    @Autowired
    private IDeviceService deviceService;

    /**
     * 디바이스 사전 관리 서비스
     * 디바이스 타입, 모델 정보 등을 관리
     */
    @Autowired
    private IDeviceDictionaryService dictionaryService;

    /**
     * WebSocket 메시지 서비스
     * 실시간으로 클라이언트에게 디바이스 상태 변경을 알림
     */
    @Autowired
    private IWebSocketMessageService webSocketMessageService;

    /**
     * 디바이스 페이로드 서비스
     * 드론의 페이로드(카메라, 센서 등) 정보를 관리
     */
    @Autowired
    private IDevicePayloadService devicePayloadService;

    /**
     * 토폴로지 온라인 업데이트를 처리합니다.
     * 
     * 디바이스가 온라인 상태가 될 때 호출되며, 게이트웨이와 서브 디바이스의
     * 연결을 설정하고 관련 데이터를 저장합니다.
     * 
     * @param request 토폴로지 업데이트 요청
     * @param headers 메시지 헤더
     * @return 상태 응답
     */
    @Override
    public TopicStatusResponse<MqttReply> updateTopoOnline(TopicStatusRequest<UpdateTopo> request, MessageHeaders headers) {
        UpdateTopoSubDevice updateTopoSubDevice = request.getData().getSubDevices().get(0);
        String deviceSn = updateTopoSubDevice.getSn();

        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(deviceSn);
        Optional<DeviceDTO> gatewayOpt = deviceRedisService.getDeviceOnline(request.getFrom());
        GatewayManager gatewayManager = SDKManager.registerDevice(request.getFrom(), deviceSn,
                request.getData().getDomain(), request.getData().getType(),
                request.getData().getSubType(), request.getData().getThingVersion(), updateTopoSubDevice.getThingVersion());

        if (deviceOpt.isPresent() && gatewayOpt.isPresent()) {
            deviceOnlineAgain(deviceOpt.get().getWorkspaceId(), request.getFrom(), deviceSn);
            return new TopicStatusResponse<MqttReply>().setData(MqttReply.success());
        }

        changeSubDeviceParent(deviceSn, request.getFrom());

        DeviceDTO gateway = deviceGatewayConvertToDevice(request.getFrom(), request.getData());
        Optional<DeviceDTO> gatewayEntityOpt = onlineSaveDevice(gateway, deviceSn, null);
        if (gatewayEntityOpt.isEmpty()) {
            log.error("Failed to go online, please check the status data or code logic.");
            return null;
        }
        DeviceDTO subDevice = subDeviceConvertToDevice(updateTopoSubDevice);
        Optional<DeviceDTO> subDeviceEntityOpt = onlineSaveDevice(subDevice, null, gateway.getDeviceSn());
        if (subDeviceEntityOpt.isEmpty()) {
            log.error("Failed to go online, please check the status data or code logic.");
            return null;
        }
        subDevice = subDeviceEntityOpt.get();
        gateway = gatewayEntityOpt.get();
        dockGoOnline(gateway, subDevice);
        deviceService.gatewayOnlineSubscribeTopic(gatewayManager);

        if (!StringUtils.hasText(subDevice.getWorkspaceId())) {
            return new TopicStatusResponse<MqttReply>().setData(MqttReply.success());
        }

        // 드론 디바이스와 관련된 토픽 구독
        deviceService.subDeviceOnlineSubscribeTopic(gatewayManager);
        deviceService.pushDeviceOnlineTopo(gateway.getWorkspaceId(), gateway.getDeviceSn(), subDevice.getDeviceSn());

        log.debug("{} online.", subDevice.getDeviceSn());
        return new TopicStatusResponse<MqttReply>().setData(MqttReply.success());
    }

    /**
     * 토폴로지 오프라인 업데이트를 처리합니다.
     * 
     * 디바이스가 오프라인 상태가 될 때 호출되며, 게이트웨이와 서브 디바이스의
     * 연결을 해제하고 관련 데이터를 업데이트합니다.
     * 
     * @param request 토폴로지 업데이트 요청
     * @param headers 메시지 헤더
     * @return 상태 응답
     */
    @Override
    public TopicStatusResponse<MqttReply> updateTopoOffline(TopicStatusRequest<UpdateTopo> request, MessageHeaders headers) {
        GatewayManager gatewayManager = SDKManager.registerDevice(request.getFrom(), null,
                request.getData().getDomain(), request.getData().getType(),
                request.getData().getSubType(), request.getData().getThingVersion(), null);
        deviceService.gatewayOnlineSubscribeTopic(gatewayManager);
        // Only the remote controller is logged in and the aircraft is not connected.
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(request.getFrom());
        if (deviceOpt.isEmpty()) {
            // When connecting for the first time
            DeviceDTO gatewayDevice = deviceGatewayConvertToDevice(request.getFrom(), request.getData());
            Optional<DeviceDTO> gatewayDeviceOpt = onlineSaveDevice(gatewayDevice, null, null);
            if (gatewayDeviceOpt.isEmpty()) {
                return null;
            }
            deviceService.pushDeviceOnlineTopo(gatewayDeviceOpt.get().getWorkspaceId(), request.getFrom(), null);
            return new TopicStatusResponse<MqttReply>().setData(MqttReply.success());
        }

        String deviceSn = deviceOpt.get().getChildDeviceSn();
        if (!StringUtils.hasText(deviceSn)) {
            return new TopicStatusResponse<MqttReply>().setData(MqttReply.success());
        }

        deviceService.subDeviceOffline(deviceSn);
        return new TopicStatusResponse<MqttReply>().setData(MqttReply.success());
    }

    /**
     * 도킹 스테이션 OSD 데이터를 처리합니다.
     * 
     * 도킹 스테이션의 실시간 상태 정보(온보드 시스템 데이터)를
     * 처리하고 WebSocket을 통해 클라이언트에게 전송합니다.
     * 
     * @param request 도킹 스테이션 OSD 데이터 요청
     * @param headers 메시지 헤더 정보
     */
    @Override
    public void osdDock(TopicOsdRequest<OsdDock> request, MessageHeaders headers) {
        String from = request.getFrom();
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(from);
        if (deviceOpt.isEmpty() || !StringUtils.hasText(deviceOpt.get().getWorkspaceId())) {
            deviceOpt = deviceService.getDeviceBySn(from);
            if (deviceOpt.isEmpty()) {
                log.error("Please restart the drone.");
                return;
            }
        }

        DeviceDTO device = deviceOpt.get();
        if (!StringUtils.hasText(device.getWorkspaceId())) {
            log.error("Please bind the dock first.");
        }
        if (StringUtils.hasText(device.getChildDeviceSn())) {
            deviceService.getDeviceBySn(device.getChildDeviceSn()).ifPresent(device::setChildren);
        }

        deviceRedisService.setDeviceOnline(device);
        fillDockOsd(from, request.getData());

        deviceService.pushOsdDataToWeb(device.getWorkspaceId(), BizCodeEnum.DOCK_OSD, from, request.getData());
    }

    /**
     * 도킹 스테이션 드론 OSD 데이터를 처리합니다.
     * 
     * 도킹 스테이션에 연결된 드론의 실시간 상태 정보를
     * 처리하고 WebSocket을 통해 클라이언트에게 전송합니다.
     * 
     * @param request 도킹 스테이션 드론 OSD 데이터 요청
     * @param headers 메시지 헤더 정보
     */
    @Override
    public void osdDockDrone(TopicOsdRequest<OsdDockDrone> request, MessageHeaders headers) {
        String from = request.getFrom();
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(from);
        if (deviceOpt.isEmpty()) {
            deviceOpt = deviceService.getDeviceBySn(from);
            if (deviceOpt.isEmpty()) {
                log.error("Please restart the drone.");
                return;
            }
        }

        if (!StringUtils.hasText(deviceOpt.get().getWorkspaceId())) {
            log.error("Please restart the drone.");
        }

        DeviceDTO device = deviceOpt.get();
        deviceRedisService.setDeviceOnline(device);
        deviceRedisService.setDeviceOsd(from, request.getData());

        deviceService.pushOsdDataToWeb(device.getWorkspaceId(), BizCodeEnum.DEVICE_OSD, from, request.getData());
    }

    /**
     * 리모트 컨트롤 OSD 데이터를 처리합니다.
     * 
     * 리모트 컨트롤의 실시간 상태 정보를 처리하고
     * WebSocket을 통해 클라이언트에게 전송합니다.
     * 
     * @param request 리모트 컨트롤 OSD 데이터 요청
     * @param headers 메시지 헤더 정보
     */
    @Override
    public void osdRemoteControl(TopicOsdRequest<OsdRemoteControl> request, MessageHeaders headers) {
        String from = request.getFrom();
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(from);
        if (deviceOpt.isEmpty()) {
            deviceOpt = deviceService.getDeviceBySn(from);
            if (deviceOpt.isEmpty()) {
                log.error("Please restart the drone.");
                return;
            }
        }
        DeviceDTO device = deviceOpt.get();
        if (StringUtils.hasText(device.getChildDeviceSn())) {
            deviceService.getDeviceBySn(device.getChildDeviceSn()).ifPresent(device::setChildren);
        }
        deviceRedisService.setDeviceOnline(device);

        OsdRemoteControl data = request.getData();
        deviceService.pushOsdDataToPilot(device.getWorkspaceId(), from,
                new DeviceOsdHost()
                        .setLatitude(data.getLatitude())
                        .setLongitude(data.getLongitude())
                        .setHeight(data.getHeight()));
        deviceService.pushOsdDataToWeb(device.getWorkspaceId(), BizCodeEnum.RC_OSD, from, data);

    }

    /**
     * RC 드론 OSD 데이터를 처리합니다.
     * 
     * 리모트 컨트롤에 연결된 드론의 실시간 상태 정보를
     * 처리하고 WebSocket을 통해 클라이언트에게 전송합니다.
     * 
     * @param request RC 드론 OSD 데이터 요청
     * @param headers 메시지 헤더 정보
     */
    @Override
    public void osdRcDrone(TopicOsdRequest<OsdRcDrone> request, MessageHeaders headers) {
        String from = request.getFrom();
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(from);
        if (deviceOpt.isEmpty()) {
            deviceOpt = deviceService.getDeviceBySn(from);
            if (deviceOpt.isEmpty()) {
                log.error("Please restart the drone.");
                return;
            }
        }
        DeviceDTO device = deviceOpt.get();
        if (!StringUtils.hasText(device.getWorkspaceId())) {
            log.error("Please bind the drone first.");
        }

        deviceRedisService.setDeviceOnline(device);

        OsdRcDrone data = request.getData();
        deviceService.pushOsdDataToPilot(device.getWorkspaceId(), from,
                new DeviceOsdHost()
                        .setLatitude(data.getLatitude())
                        .setLongitude(data.getLongitude())
                        .setElevation(data.getElevation())
                        .setHeight(data.getHeight())
                        .setAttitudeHead(data.getAttitudeHead())
                        .setElevation(data.getElevation())
                        .setHorizontalSpeed(data.getHorizontalSpeed())
                        .setVerticalSpeed(data.getVerticalSpeed()));
        deviceService.pushOsdDataToWeb(device.getWorkspaceId(), BizCodeEnum.DEVICE_OSD, from, data);
    }

    /**
     * 도킹 스테이션 펌웨어 버전을 업데이트합니다.
     * 
     * 도킹 스테이션의 펌웨어 버전 정보를 업데이트하고
     * 디바이스 정보를 갱신합니다.
     * 
     * @param request 도킹 스테이션 펌웨어 버전 업데이트 요청
     * @param headers 메시지 헤더 정보
     */
    @Override
    public void dockFirmwareVersionUpdate(TopicStateRequest<DockFirmwareVersion> request, MessageHeaders headers) {
        // If the reported version is empty, it will not be processed to prevent misleading page.
        if (!StringUtils.hasText(request.getData().getFirmwareVersion())) {
            return;
        }

        DeviceDTO device = DeviceDTO.builder()
                .deviceSn(request.getFrom())
                .firmwareVersion(request.getData().getFirmwareVersion())
                .firmwareStatus(request.getData().getNeedCompatibleStatus() ?
                        DeviceFirmwareStatusEnum.UNKNOWN : DeviceFirmwareStatusEnum.CONSISTENT_UPGRADE)
                .build();
        boolean isUpd = deviceService.updateDevice(device);
        if (!isUpd) {
            log.error("Data update of firmware version failed. SN: {}", request.getFrom());
        }
    }

    /**
     * RC 및 드론 펌웨어 버전을 업데이트합니다.
     * 
     * 리모트 컨트롤과 드론의 펌웨어 버전 정보를 업데이트하고
     * 디바이스 정보를 갱신합니다.
     * 
     * @param request RC 및 드론 펌웨어 버전 업데이트 요청
     * @param headers 메시지 헤더 정보
     */
    @Override
    public void rcAndDroneFirmwareVersionUpdate(TopicStateRequest<FirmwareVersion> request, MessageHeaders headers) {
        // If the reported version is empty, it will not be processed to prevent misleading page.
        if (!StringUtils.hasText(request.getData().getFirmwareVersion())) {
            return;
        }

        DeviceDTO device = DeviceDTO.builder()
                .deviceSn(request.getFrom())
                .firmwareVersion(request.getData().getFirmwareVersion())
                .build();
        boolean isUpd = deviceService.updateDevice(device);
        if (!isUpd) {
            log.error("Data update of firmware version failed. SN: {}", request.getFrom());
        }
    }

    /**
     * RC 페이로드 펌웨어 버전을 업데이트합니다.
     * 
     * 리모트 컨트롤의 페이로드(카메라, 센서 등) 펌웨어 버전 정보를
     * 업데이트하고 디바이스 정보를 갱신합니다.
     * 
     * @param request RC 페이로드 펌웨어 버전 업데이트 요청
     * @param headers 메시지 헤더 정보
     */
    @Override
    public void rcPayloadFirmwareVersionUpdate(TopicStateRequest<PayloadFirmwareVersion> request, MessageHeaders headers) {
        // If the reported version is empty, it will not be processed to prevent misleading page.
        if (!StringUtils.hasText(request.getData().getFirmwareVersion())) {
            return;
        }

        boolean isUpd = devicePayloadService.updateFirmwareVersion(request.getFrom(), request.getData());
        if (!isUpd) {
            log.error("Data update of payload firmware version failed. SN: {}", request.getFrom());
        }
    }

    /**
     * 도킹 스테이션 제어 소스를 업데이트합니다.
     * 
     * 도킹 스테이션의 제어 소스 정보를 업데이트하고
     * WebSocket을 통해 실시간 상태 변경을 알립니다.
     * 
     * @param request 도킹 스테이션 제어 소스 업데이트 요청
     * @param headers 메시지 헤더 정보
     */
    @Override
    public void dockControlSourceUpdate(TopicStateRequest<DockDroneControlSource> request, MessageHeaders headers) {
        // If the control source is empty, it will not be processed.
        if (ControlSourceEnum.UNKNOWN == request.getData().getControlSource()) {
            return;
        }
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(request.getFrom());
        if (deviceOpt.isEmpty()) {
            return;
        }
        Optional<DeviceDTO> dockOpt = deviceRedisService.getDeviceOnline(request.getGateway());
        if (dockOpt.isEmpty()) {
            return;
        }

        deviceService.updateFlightControl(dockOpt.get(), request.getData().getControlSource());
        devicePayloadService.updatePayloadControl(deviceOpt.get(),
                request.getData().getPayloads().stream()
                        .map(p -> DevicePayloadReceiver.builder()
                                .controlSource(p.getControlSource())
                                .payloadIndex(p.getPayloadIndex())
                                .sn(p.getSn())
                                .deviceSn(request.getFrom())
                                .build()).collect(Collectors.toList()));
    }

    /**
     * RC 제어 소스를 업데이트합니다.
     * 
     * 리모트 컨트롤의 제어 소스 정보를 업데이트하고
     * WebSocket을 통해 실시간 상태 변경을 알립니다.
     * 
     * @param request RC 제어 소스 업데이트 요청
     * @param headers 메시지 헤더 정보
     */
    @Override
    public void rcControlSourceUpdate(TopicStateRequest<RcDroneControlSource> request, MessageHeaders headers) {
        // If the control source is empty, it will not be processed.
        if (ControlSourceEnum.UNKNOWN == request.getData().getControlSource()) {
            return;
        }
        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(request.getFrom());
        if (deviceOpt.isEmpty()) {
            return;
        }
        Optional<DeviceDTO> dockOpt = deviceRedisService.getDeviceOnline(request.getGateway());
        if (dockOpt.isEmpty()) {
            return;
        }

        deviceService.updateFlightControl(dockOpt.get(), request.getData().getControlSource());
        devicePayloadService.updatePayloadControl(deviceOpt.get(),
                request.getData().getPayloads().stream()
                        .map(p -> DevicePayloadReceiver.builder()
                                .controlSource(p.getControlSource())
                                .payloadIndex(p.getPayloadIndex())
                                .sn(p.getSn())
                                .deviceSn(request.getFrom())
                                .build()).collect(Collectors.toList()));
    }

    /**
     * 도킹 스테이션과 서브 디바이스가 온라인 상태가 되었을 때 처리합니다.
     * 
     * 게이트웨이(도킹 스테이션)와 서브 디바이스(드론)의 연결을
     * 설정하고 WebSocket을 통해 실시간 상태 변경을 알립니다.
     * 
     * @param gateway 게이트웨이 디바이스 정보
     * @param subDevice 서브 디바이스 정보
     */
    private void dockGoOnline(DeviceDTO gateway, DeviceDTO subDevice) {
        if (DeviceDomainEnum.DOCK != gateway.getDomain()) {
            return;
        }
        if (!StringUtils.hasText(gateway.getWorkspaceId())) {
            log.error("The dock is not bound, please bind it first and then go online.");
            return;
        }
        if (!Objects.requireNonNullElse(subDevice.getBoundStatus(), false)) {
            // Directly bind the drone of the dock to the same workspace as the dock.
            deviceService.bindDevice(DeviceDTO.builder().deviceSn(subDevice.getDeviceSn()).workspaceId(gateway.getWorkspaceId()).build());
            subDevice.setWorkspaceId(gateway.getWorkspaceId());
        }
        deviceRedisService.setDeviceOnline(subDevice);
    }

    /**
     * 서브 디바이스의 부모 디바이스를 변경합니다.
     * 
     * 서브 디바이스의 부모 디바이스 정보를 업데이트하고
     * 데이터베이스에 저장합니다.
     * 
     * @param deviceSn 서브 디바이스 시리얼 번호
     * @param gatewaySn 게이트웨이 시리얼 번호
     */
    private void changeSubDeviceParent(String deviceSn, String gatewaySn) {
        List<DeviceDTO> gatewaysList = deviceService.getDevicesByParams(
                DeviceQueryParam.builder()
                        .childSn(deviceSn)
                        .build());
        gatewaysList.stream()
                .filter(gateway -> !gateway.getDeviceSn().equals(gatewaySn))
                .forEach(gateway -> {
                    gateway.setChildDeviceSn("");
                    deviceService.updateDevice(gateway);
                    deviceRedisService.getDeviceOnline(gateway.getDeviceSn())
                            .ifPresent(device -> {
                                device.setChildDeviceSn(null);
                                deviceRedisService.setDeviceOnline(device);
                            });
                });
    }

    /**
     * 디바이스가 다시 온라인 상태가 되었을 때 처리합니다.
     * 
     * 디바이스가 재연결되었을 때 토픽을 다시 구독하고
     * 디바이스 상태를 업데이트합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @param deviceSn 디바이스 시리얼 번호
     */
    public void deviceOnlineAgain(String workspaceId, String gatewaySn, String deviceSn) {
        DeviceDTO device = DeviceDTO.builder().loginTime(LocalDateTime.now()).deviceSn(deviceSn).build();
        DeviceDTO gateway = DeviceDTO.builder()
                .loginTime(LocalDateTime.now())
                .deviceSn(gatewaySn)
                .childDeviceSn(deviceSn).build();
        deviceService.updateDevice(gateway);
        deviceService.updateDevice(device);
        gateway = deviceRedisService.getDeviceOnline(gatewaySn).map(g -> {
            g.setChildDeviceSn(deviceSn);
            return g;
        }).get();
        device = deviceRedisService.getDeviceOnline(deviceSn).map(d -> {
            d.setParentSn(gatewaySn);
            return d;
        }).get();
        deviceRedisService.setDeviceOnline(gateway);
        deviceRedisService.setDeviceOnline(device);
        if (StringUtils.hasText(workspaceId)) {
            deviceService.subDeviceOnlineSubscribeTopic(SDKManager.getDeviceSDK(gatewaySn));
        }

        log.warn("{} is already online.", deviceSn);
    }

    /**
     * 게이트웨이 디바이스 객체를 데이터베이스 엔티티 객체로 변환합니다.
     * 
     * UpdateTopo 객체를 DeviceDTO로 변환하여
     * 데이터베이스에 저장할 수 있는 형태로 만듭니다.
     * 
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @param gateway 게이트웨이 토폴로지 정보
     * @return 변환된 DeviceDTO 객체
     */
    private DeviceDTO deviceGatewayConvertToDevice(String gatewaySn, UpdateTopo gateway) {
        if (null == gateway) {
            throw new IllegalArgumentException();
        }
        return DeviceDTO.builder()
                .deviceSn(gatewaySn)
                .subType(gateway.getSubType())
                .type(gateway.getType())
                .thingVersion(gateway.getThingVersion())
                .domain(gateway.getDomain())
                .controlSource(gateway.getSubDevices().isEmpty() ? null :
                        ControlSourceEnum.find(gateway.getSubDevices().get(0).getIndex().getControlSource()))
                .build();
    }

    /**
     * 드론 디바이스 객체를 데이터베이스 엔티티 객체로 변환합니다.
     * 
     * UpdateTopoSubDevice 객체를 DeviceDTO로 변환하여
     * 데이터베이스에 저장할 수 있는 형태로 만듭니다.
     * 
     * @param device 서브 디바이스 토폴로지 정보
     * @return 변환된 DeviceDTO 객체
     */
    private DeviceDTO subDeviceConvertToDevice(UpdateTopoSubDevice device) {
        if (null == device) {
            throw new IllegalArgumentException();
        }
        return DeviceDTO.builder()
                .deviceSn(device.getSn())
                .type(device.getType())
                .subType(device.getSubType())
                .thingVersion(device.getThingVersion())
                .domain(device.getDomain())
                .build();
    }

    /**
     * 온라인 디바이스를 저장하거나 업데이트합니다.
     * 
     * 디바이스 정보를 데이터베이스에 저장하고,
     * 부모-자식 관계를 설정합니다.
     * 
     * @param device 저장할 디바이스 정보
     * @param childSn 자식 디바이스 시리얼 번호
     * @param parentSn 부모 디바이스 시리얼 번호
     * @return 저장된 디바이스 정보 (Optional)
     */
    private Optional<DeviceDTO> onlineSaveDevice(DeviceDTO device, String childSn, String parentSn) {

        device.setChildDeviceSn(childSn);
        device.setLoginTime(LocalDateTime.now());

        Optional<DeviceDTO> deviceOpt = deviceService.getDeviceBySn(device.getDeviceSn());

        if (deviceOpt.isEmpty()) {
            device.setIconUrl(new DeviceIconUrl());
            // Set the icon of the gateway device displayed in the pilot's map, required in the TSA module.
            device.getIconUrl().setNormalIconUrl(IconUrlEnum.NORMAL_PERSON.getUrl());
            // Set the icon of the gateway device displayed in the pilot's map when it is selected, required in the TSA module.
            device.getIconUrl().setSelectIconUrl(IconUrlEnum.SELECT_PERSON.getUrl());
            device.setBoundStatus(false);

            // Query the model information of this gateway device.
            dictionaryService.getOneDictionaryInfoByTypeSubType(
                    device.getDomain().getDomain(), device.getType().getType(), device.getSubType().getSubType())
                    .ifPresent(entity -> {
                        device.setDeviceName(entity.getDeviceName());
                        device.setNickname(entity.getDeviceName());
                        device.setDeviceDesc(entity.getDeviceDesc());
                    });
        }
        boolean success = deviceService.saveOrUpdateDevice(device);
        if (!success) {
            return Optional.empty();
        }

        deviceOpt = deviceService.getDeviceBySn(device.getDeviceSn());
        DeviceDTO redisDevice = deviceOpt.get();
        redisDevice.setStatus(true);
        redisDevice.setParentSn(parentSn);

        deviceRedisService.setDeviceOnline(redisDevice);
        return deviceOpt;
    }

    /**
     * 도킹 스테이션 OSD 데이터를 Redis에 저장합니다.
     * 
     * 도킹 스테이션의 실시간 상태 정보를 Redis에 캐싱하여
     * 빠른 조회를 지원합니다.
     * 
     * @param dockSn 도킹 스테이션 시리얼 번호
     * @param dock 도킹 스테이션 OSD 데이터
     */
    private void fillDockOsd(String dockSn, OsdDock dock) {
        Optional<OsdDock> oldDockOpt = deviceRedisService.getDeviceOsd(dockSn, OsdDock.class);
        if (Objects.nonNull(dock.getJobNumber())) {
            return;
        }
        if (oldDockOpt.isEmpty()) {
            deviceRedisService.setDeviceOsd(dockSn, dock);
            return;
        }
        OsdDock oldDock = oldDockOpt.get();
        if (Objects.nonNull(dock.getModeCode())) {
            dock.setDrcState(oldDock.getDrcState());
            deviceRedisService.setDeviceOsd(dockSn, dock);
            return;
        }
        if (Objects.nonNull(dock.getDrcState()) ) {
            oldDock.setDrcState(dock.getDrcState());
            deviceRedisService.setDeviceOsd(dockSn, oldDock);
        }
    }
}
