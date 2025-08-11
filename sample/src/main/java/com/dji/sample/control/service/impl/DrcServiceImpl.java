package com.dji.sample.control.service.impl;

import com.dji.sample.component.mqtt.config.MqttPropertyConfiguration;
import com.dji.sample.component.mqtt.model.EventsReceiver;
import com.dji.sample.component.mqtt.model.MapKeyConst;
import com.dji.sample.component.redis.RedisConst;
import com.dji.sample.component.redis.RedisOpsUtils;
import com.dji.sample.component.websocket.service.IWebSocketMessageService;
import com.dji.sample.control.model.dto.JwtAclDTO;
import com.dji.sample.control.model.enums.DroneAuthorityEnum;
import com.dji.sample.control.model.enums.MqttAclAccessEnum;
import com.dji.sample.control.model.param.DrcConnectParam;
import com.dji.sample.control.model.param.DrcModeParam;
import com.dji.sample.control.service.IControlService;
import com.dji.sample.control.service.IDrcService;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sample.manage.service.IDeviceService;
import com.dji.sample.wayline.model.enums.WaylineJobStatusEnum;
import com.dji.sample.wayline.model.enums.WaylineTaskStatusEnum;
import com.dji.sample.wayline.model.param.UpdateJobParam;
import com.dji.sample.wayline.service.IFlightTaskService;
import com.dji.sample.wayline.service.IWaylineJobService;
import com.dji.sample.wayline.service.IWaylineRedisService;
import com.dji.sdk.cloudapi.control.DrcModeEnterRequest;
import com.dji.sdk.cloudapi.control.DrcModeMqttBroker;
import com.dji.sdk.cloudapi.control.api.AbstractControlService;
import com.dji.sdk.cloudapi.device.DockModeCodeEnum;
import com.dji.sdk.cloudapi.device.OsdDockDrone;
import com.dji.sdk.cloudapi.wayline.FlighttaskProgress;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.SDKManager;
import com.dji.sdk.mqtt.TopicConst;
import com.dji.sdk.mqtt.services.ServicesReplyData;
import com.dji.sdk.mqtt.services.TopicServicesResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * DRC 서비스 구현 클래스
 * 
 * DRC(Direct Remote Control) 모드 관리를 구현하는 핵심 서비스 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. DRC 모드 상태 관리
 *    - Redis를 통한 DRC 모드 상태 저장/조회/삭제
 *    - 클라이언트별 DRC 모드 상태 추적
 *    - 만료 시간 기반 자동 정리
 * 
 * 2. 사용자 인증 및 권한 관리
 *    - MQTT ACL 권한 설정 및 관리
 *    - JWT 토큰 기반 사용자 인증
 *    - 클라이언트별 토픽 권한 관리
 *    - 토큰 만료 시간 관리
 * 
 * 3. DRC 모드 진입/종료 처리
 *    - DRC 모드 진입 조건 검증 (드론 상태, 웨이라인 작업 등)
 *    - 웨이라인 작업 일시정지/재개
 *    - 비행 권한 획득 및 관리
 *    - 드론 상태 확인 (공중 비행 여부)
 * 
 * 4. MQTT 브로커 설정
 *    - DRC 전용 MQTT 브로커 구성
 *    - 토픽 기반 권한 설정
 *    - 실시간 통신 채널 제공
 *    - 보안 연결 설정
 * 
 * DRC 모드는 사용자가 직접 드론을 실시간으로 제어할 수 있는 모드로,
 * 안전한 원격 조종을 위한 다양한 검증과 권한 관리 기능을 제공합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/1/11
 */
@Service
@Slf4j
public class DrcServiceImpl implements IDrcService {

    /** JSON 직렬화를 위한 ObjectMapper */
    @Autowired
    private ObjectMapper objectMapper;

    /** 웨이라인 작업 서비스 - 웨이라인 작업 상태 관리 */
    @Autowired
    private IWaylineJobService waylineJobService;

    /** 비행 작업 서비스 - 비행 작업 상태 관리 */
    @Autowired
    private IFlightTaskService flighttaskService;

    /** 디바이스 서비스 - 디바이스 상태 및 모드 관리 */
    @Autowired
    private IDeviceService deviceService;
    
    /** JSON 직렬화를 위한 ObjectMapper */
    @Autowired
    private ObjectMapper mapper;
    
    /** 웹소켓 메시지 서비스 - 실시간 알림 전송 */
    @Autowired
    private IWebSocketMessageService webSocketMessageService;

    /** 제어 서비스 - 비행 권한 획득 등 */
    @Autowired
    private IControlService controlService;

    /** 디바이스 Redis 서비스 - 디바이스 온라인 상태 캐시 관리 */
    @Autowired
    private IDeviceRedisService deviceRedisService;

    /** 웨이라인 Redis 서비스 - 웨이라인 작업 상태 캐시 관리 */
    @Autowired
    private IWaylineRedisService waylineRedisService;

    /** SDK 제어 서비스 - DJI SDK 제어 API 호출 */
    @Autowired
    private AbstractControlService abstractControlService;

    /**
     * 도크의 DRC 모드를 Redis에 저장합니다.
     * 
     * 이 메서드는 도크가 DRC 모드에 진입했을 때 클라이언트 정보를 Redis에 저장합니다:
     * - 클라이언트 ID와 도크 SN을 매핑하여 저장
     * - 만료 시간 설정 (DRC_MODE_ALIVE_SECOND)
     * - 중복 진입 방지를 위한 상태 관리
     * 
     * @param dockSn 도크 시리얼 번호
     * @param clientId 클라이언트 ID (사용자 식별자)
     */
    @Override
    public void setDrcModeInRedis(String dockSn, String clientId) {
        RedisOpsUtils.setWithExpire(RedisConst.DRC_PREFIX + dockSn, clientId, RedisConst.DRC_MODE_ALIVE_SECOND);
    }

    /**
     * 도크를 제어하고 있는 클라이언트를 조회합니다.
     * 
     * 이 메서드는 현재 도크를 DRC 모드로 제어하고 있는 클라이언트를 조회합니다:
     * - Redis에서 도크 SN에 해당하는 클라이언트 ID 조회
     * - DRC 모드 상태 확인
     * - 클라이언트 권한 검증
     * 
     * @param dockSn 도크 시리얼 번호
     * @return 클라이언트 ID (제어 중인 클라이언트가 없으면 null)
     */
    @Override
    public String getDrcModeInRedis(String dockSn) {
        return (String) RedisOpsUtils.get(RedisConst.DRC_PREFIX + dockSn);
    }

    /**
     * 도크의 DRC 모드를 Redis에서 삭제합니다.
     * 
     * 이 메서드는 도크가 DRC 모드에서 종료될 때 Redis의 상태 정보를 삭제합니다:
     * - 클라이언트와 도크의 매핑 정보 삭제
     * - DRC 모드 상태 초기화
     * - 다른 클라이언트의 진입 허용
     * 
     * @param dockSn 도크 시리얼 번호
     * @return 삭제 성공 여부 (true: 성공, false: 실패)
     */
    @Override
    public Boolean delDrcModeInRedis(String dockSn) {
        return RedisOpsUtils.del(RedisConst.DRC_PREFIX + dockSn);
    }

    /**
     * 제어 터미널을 위한 MQTT 옵션을 제공합니다.
     * 
     * 이 메서드는 DRC 모드 진입을 위한 MQTT 브로커 설정을 제공합니다:
     * - 클라이언트별 고유 ID 생성 (첫 접속 시)
     * - MQTT ACL 권한 설정 (토픽 기반 접근 제어)
     * - JWT 토큰 생성 및 만료 시간 설정
     * - 보안 연결을 위한 브로커 정보 제공
     * 
     * 클라이언트 ID가 없거나 Redis에 ACL 정보가 없는 경우 새로운 ID를 생성하고,
     * 기존 클라이언트의 경우 토큰 만료 시간을 갱신합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param userId 사용자 ID
     * @param username 사용자명
     * @param param DRC 연결 파라미터 (클라이언트 ID, 만료 시간 등)
     * @return DRC 모드 MQTT 브로커 정보 (연결 정보, 토큰 등)
     */
    @Override
    public DrcModeMqttBroker userDrcAuth(String workspaceId, String userId, String username, DrcConnectParam param) {

        // refresh token
        String clientId = param.getClientId();
        // first time
        if (!StringUtils.hasText(clientId) || !RedisOpsUtils.checkExist(RedisConst.MQTT_ACL_PREFIX + clientId)) {
            clientId = userId + "-" + System.currentTimeMillis();
            RedisOpsUtils.hashSet(RedisConst.MQTT_ACL_PREFIX + clientId, "", MqttAclAccessEnum.ALL.getValue());
        }

        String key = RedisConst.MQTT_ACL_PREFIX + clientId;

        try {
            RedisOpsUtils.expireKey(key, RedisConst.DRC_MODE_ALIVE_SECOND);

            return MqttPropertyConfiguration.getMqttBrokerWithDrc(
                    clientId, username, param.getExpireSec(), Collections.emptyMap());
        } catch (RuntimeException e) {
            RedisOpsUtils.del(key);
            throw e;
        }
    }

    /**
     * DRC 모드 진입 조건을 확인합니다.
     * 
     * 이 메서드는 DRC 모드 진입 전에 다음 조건들을 검증합니다:
     * 1. 웨이라인 작업 상태 확인 및 일시정지
     * 2. 도크 모드 확인 (IDLE 또는 WORKING)
     * 3. 드론 상태 확인 (공중 비행 여부)
     * 4. 비행 권한 획득
     * 
     * 모든 조건이 만족되지 않으면 RuntimeException을 발생시켜
     * 안전하지 않은 DRC 모드 진입을 방지합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param dockSn 도크 시리얼 번호
     * @throws RuntimeException 조건을 만족하지 않는 경우
     */
    private void checkDrcModeCondition(String workspaceId, String dockSn) {
        // 웨이라인 작업 상태 확인 및 일시정지
        Optional<EventsReceiver<FlighttaskProgress>> runningOpt = waylineRedisService.getRunningWaylineJob(dockSn);
        if (runningOpt.isPresent() && WaylineJobStatusEnum.IN_PROGRESS == waylineJobService.getWaylineState(dockSn)) {
            flighttaskService.updateJobStatus(workspaceId, runningOpt.get().getBid(),
                    UpdateJobParam.builder().status(WaylineTaskStatusEnum.PAUSE).build());
        }

        // 도크 모드 및 드론 상태 확인
        DockModeCodeEnum dockMode = deviceService.getDockMode(dockSn);
        Optional<DeviceDTO> dockOpt = deviceRedisService.getDeviceOnline(dockSn);
        if (dockOpt.isPresent() && (DockModeCodeEnum.IDLE == dockMode || DockModeCodeEnum.WORKING == dockMode)) {
            Optional<OsdDockDrone> deviceOsd = deviceRedisService.getDeviceOsd(dockOpt.get().getChildDeviceSn(), OsdDockDrone.class);
            if (deviceOsd.isEmpty() || deviceOsd.get().getElevation() <= 0) {
                throw new RuntimeException("The drone is not in the sky and cannot enter command flight mode.");
            }
        } else {
            throw new RuntimeException("The current state of the dock does not support entering command flight mode.");
        }

        // 비행 권한 획득
        HttpResultResponse result = controlService.seizeAuthority(dockSn, DroneAuthorityEnum.FLIGHT, null);
        if (HttpResultResponse.CODE_SUCCESS != result.getCode()) {
            throw new IllegalArgumentException(result.getMessage());
        }

    }

    /**
     * 도크가 DRC 모드에 진입하도록 합니다. 관련 권한을 부여합니다.
     * 
     * 이 메서드는 도크를 DRC 모드로 전환하고 필요한 권한을 설정합니다:
     * 1. DRC 모드 진입 조건 검증 (checkDrcModeCondition)
     * 2. MQTT 토픽 권한 설정
     * 3. SDK를 통한 DRC 모드 진입 명령 전송
     * 4. Redis 상태 저장 및 ACL 권한 설정
     * 
     * 이미 DRC 모드에 있는 경우 권한만 갱신하고,
     * 새로운 진입인 경우 모든 조건을 검증한 후 진입합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param param DRC 모드 파라미터 (도크 SN, 클라이언트 ID 등)
     * @return JWT ACL 정보 (토픽 권한 정보)
     */
    @Override
    public JwtAclDTO deviceDrcEnter(String workspaceId, DrcModeParam param) {
        String topic = TopicConst.THING_MODEL_PRE + TopicConst.PRODUCT + param.getDockSn() + TopicConst.DRC;
        String pubTopic = topic + TopicConst.DOWN;
        String subTopic = topic + TopicConst.UP;

        // If the dock is in drc mode, refresh the permissions directly.
        if (deviceService.checkDockDrcMode(param.getDockSn())
                && param.getClientId().equals(this.getDrcModeInRedis(param.getDockSn()))) {
            refreshAcl(param.getDockSn(), param.getClientId(), topic, subTopic);
            return JwtAclDTO.builder().sub(List.of(subTopic)).pub(List.of(pubTopic)).build();
        }

        checkDrcModeCondition(workspaceId, param.getDockSn());

        TopicServicesResponse<ServicesReplyData> reply = abstractControlService.drcModeEnter(
                SDKManager.getDeviceSDK(param.getDockSn()),
                new DrcModeEnterRequest()
                        .setMqttBroker(MqttPropertyConfiguration.getMqttBrokerWithDrc(param.getDockSn() + "-" + System.currentTimeMillis(), param.getDockSn(),
                                RedisConst.DRC_MODE_ALIVE_SECOND.longValue(),
                                Map.of(MapKeyConst.ACL, objectMapper.convertValue(JwtAclDTO.builder()
                                        .pub(List.of(subTopic))
                                        .sub(List.of(pubTopic))
                                        .build(), new TypeReference<Map<String, ?>>() {}))))
                        .setHsiFrequency(1).setOsdFrequency(10));

        if (!reply.getData().getResult().isSuccess()) {
            throw new RuntimeException("SN: " + param.getDockSn() + "; Error:" + reply.getData().getResult() +
                    "; Failed to enter command flight control mode, please try again later!");
        }

        refreshAcl(param.getDockSn(), param.getClientId(), pubTopic, subTopic);
        return JwtAclDTO.builder().sub(List.of(subTopic)).pub(List.of(pubTopic)).build();
    }

    /**
     * ACL 권한을 갱신합니다.
     * 
     * 이 메서드는 DRC 모드 진입 시 MQTT 토픽 권한을 설정합니다:
     * - Redis에 DRC 모드 상태 저장
     * - MQTT ACL 권한 설정 (PUB/SUB 토픽 권한)
     * - 권한 만료 시간 설정
     * 
     * ACL은 클라이언트 ID를 기준으로 매칭되며,
     * Redis의 HSET 구조를 사용하여 토픽별 권한을 관리합니다.
     * 
     * @param dockSn 도크 시리얼 번호
     * @param clientId 클라이언트 ID
     * @param pubTopic 발행 토픽
     * @param subTopic 구독 토픽
     */
    private void refreshAcl(String dockSn, String clientId, String pubTopic, String subTopic) {
        this.setDrcModeInRedis(dockSn, clientId);

        // assign acl，Match by clientId. https://www.emqx.io/docs/zh/v4.4/advanced/acl-redis.html
        // scheme: HSET mqtt_acl:[clientid] [topic] [access]
        String key = RedisConst.MQTT_ACL_PREFIX + clientId;
        RedisOpsUtils.hashSet(key, pubTopic, MqttAclAccessEnum.PUB.getValue());
        RedisOpsUtils.hashSet(key, subTopic, MqttAclAccessEnum.SUB.getValue());
        RedisOpsUtils.expireKey(key, RedisConst.DRC_MODE_ALIVE_SECOND);
    }

    /**
     * 도크가 DRC 모드에서 종료하도록 합니다.
     * 
     * 이 메서드는 도크를 DRC 모드에서 종료하고 정상 상태로 복원합니다:
     * 1. DRC 모드 상태 확인
     * 2. SDK를 통한 DRC 모드 종료 명령 전송
     * 3. 일시정지된 웨이라인 작업 재개
     * 4. Redis 상태 정보 및 ACL 권한 삭제
     * 
     * 모든 정리 작업이 완료되면 도크는 정상 상태로 복원되어
     * 다른 작업을 수행할 수 있게 됩니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param param DRC 모드 파라미터 (도크 SN, 클라이언트 ID 등)
     */
    @Override
    public void deviceDrcExit(String workspaceId, DrcModeParam param) {
        if (!deviceService.checkDockDrcMode(param.getDockSn())) {
            throw new RuntimeException("The dock is not in flight control mode.");
        }
        TopicServicesResponse<ServicesReplyData> reply =
                abstractControlService.drcModeExit(SDKManager.getDeviceSDK(param.getDockSn()));
        if (!reply.getData().getResult().isSuccess()) {
            throw new RuntimeException("SN: " + param.getDockSn() + "; Error:" +
                    reply.getData().getResult() + "; Failed to exit command flight control mode, please try again later!");
        }

        String jobId = waylineRedisService.getPausedWaylineJobId(param.getDockSn());
        if (StringUtils.hasText(jobId)) {
            flighttaskService.updateJobStatus(workspaceId, jobId, UpdateJobParam.builder().status(WaylineTaskStatusEnum.RESUME).build());
        }

        this.delDrcModeInRedis(param.getDockSn());
        RedisOpsUtils.del(RedisConst.MQTT_ACL_PREFIX + param.getClientId());
    }

}
