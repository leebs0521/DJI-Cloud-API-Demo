package com.dji.sample.control.service.impl;

import com.dji.sample.component.websocket.service.IWebSocketMessageService;
import com.dji.sample.control.model.enums.DroneAuthorityEnum;
import com.dji.sample.control.model.enums.RemoteDebugMethodEnum;
import com.dji.sample.control.model.param.*;
import com.dji.sample.control.service.IControlService;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.service.IDevicePayloadService;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sample.manage.service.IDeviceService;
import com.dji.sdk.cloudapi.control.FlyToPointRequest;
import com.dji.sdk.cloudapi.control.PayloadAuthorityGrabRequest;
import com.dji.sdk.cloudapi.control.TakeoffToPointRequest;
import com.dji.sdk.cloudapi.control.api.AbstractControlService;
import com.dji.sdk.cloudapi.debug.DebugMethodEnum;
import com.dji.sdk.cloudapi.debug.api.AbstractDebugService;
import com.dji.sdk.cloudapi.device.DockModeCodeEnum;
import com.dji.sdk.cloudapi.device.DroneModeCodeEnum;
import com.dji.sdk.cloudapi.device.PayloadIndex;
import com.dji.sdk.cloudapi.wayline.api.AbstractWaylineService;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.SDKManager;
import com.dji.sdk.exception.CloudSDKErrorEnum;
import com.dji.sdk.mqtt.services.ServicesReplyData;
import com.dji.sdk.mqtt.services.TopicServicesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * 제어 서비스 구현 클래스
 * 
 * 드론과 도크의 제어 기능을 구현하는 핵심 서비스 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 원격 디버깅 (Remote Debugging)
 *    - 도크의 원격 디버깅 명령 실행
 *    - 복귀 및 복귀 취소 기능
 *    - 디버깅 조건 검증 및 핸들러 관리
 * 
 * 2. 비행 제어 (Flight Control)
 *    - 특정 지점으로 비행 (Fly to Point)
 *    - 특정 지점으로 이륙 (Takeoff to Point)
 *    - 비행 중지 (Stop Flight)
 *    - 비행 권한 획득 및 관리
 * 
 * 3. 페이로드 제어 (Payload Control)
 *    - 카메라, 짐벌 등 페이로드 장치 제어
 *    - 페이로드 권한 획득 및 확인
 *    - 페이로드 명령 실행 조건 검증
 * 
 * 4. 권한 관리 (Authority Management)
 *    - 비행 권한 획득/확인
 *    - 페이로드 권한 획득/확인
 *    - 권한 상태 검증 및 관리
 * 
 * 각 메서드는 실행 전에 적절한 조건 검증을 수행하여 안전한 제어를 보장합니다.
 * SDK를 통해 실제 디바이스와 통신하며, Redis를 통한 상태 관리와
 * 웹소켓을 통한 실시간 알림 기능을 제공합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/7/29
 */
@Service
@Slf4j
public class ControlServiceImpl implements IControlService {

    /** 웹소켓 메시지 서비스 - 실시간 알림 전송용 */
    @Autowired
    private IWebSocketMessageService webSocketMessageService;

    /** 디바이스 서비스 - 디바이스 상태 및 모드 관리 */
    @Autowired
    private IDeviceService deviceService;

    /** 디바이스 Redis 서비스 - 디바이스 온라인 상태 캐시 관리 */
    @Autowired
    private IDeviceRedisService deviceRedisService;

    /** JSON 직렬화를 위한 ObjectMapper - 파라미터 변환용 */
    @Autowired
    private ObjectMapper mapper;

    /** 디바이스 페이로드 서비스 - 페이로드 권한 관리 */
    @Autowired
    private IDevicePayloadService devicePayloadService;

    /** SDK 제어 서비스 - DJI SDK 제어 API 호출 */
    @Autowired
    private AbstractControlService abstractControlService;

    /** SDK 디버깅 서비스 - DJI SDK 디버깅 API 호출 */
    @Autowired
    private AbstractDebugService abstractDebugService;

    /** SDK 웨이라인 서비스 - DJI SDK 웨이라인 API 호출 */
    @Autowired
    @Qualifier("SDKWaylineService")
    private AbstractWaylineService abstractWaylineService;

    /**
     * 디버깅 조건을 확인하고 적절한 핸들러를 생성합니다.
     * 
     * 이 메서드는 원격 디버깅 명령 실행 전에 다음을 수행합니다:
     * 1. 디버깅 메서드에 해당하는 핸들러 클래스 인스턴스 생성
     * 2. 파라미터를 핸들러 객체로 변환
     * 3. 핸들러의 canPublish 메서드를 통해 실행 가능 여부 확인
     * 
     * 핸들러 클래스가 정의되지 않은 경우 기본 핸들러를 사용하며,
     * 파라미터가 null인 경우 빈 객체로 변환합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param param 원격 디버깅 파라미터 (null일 수 있음)
     * @param controlMethodEnum 원격 디버깅 메서드 열거형
     * @return 검증된 원격 디버깅 핸들러 객체
     * @throws RuntimeException 현재 상태에서 해당 기능을 지원하지 않는 경우
     */
    private RemoteDebugHandler checkDebugCondition(String sn, RemoteDebugParam param, RemoteDebugMethodEnum controlMethodEnum) {
        // 디버깅 메서드에 해당하는 핸들러 클래스가 있는 경우 해당 클래스로 변환, 없으면 기본 핸들러 사용
        RemoteDebugHandler handler = Objects.nonNull(controlMethodEnum.getClazz()) ?
                mapper.convertValue(Objects.nonNull(param) ? param : new Object(), controlMethodEnum.getClazz())
                : new RemoteDebugHandler();
        
        // 핸들러의 canPublish 메서드를 통해 현재 상태에서 실행 가능한지 확인
        if (!handler.canPublish(sn)) {
            throw new RuntimeException("The current state of the dock does not support this function.");
        }
        return handler;
    }

    /**
     * 도크를 원격으로 디버깅합니다.
     * 
     * 이 메서드는 다음과 같은 단계로 원격 디버깅을 수행합니다:
     * 1. 디버깅 조건 검증 (checkDebugCondition)
     * 2. 도크 온라인 상태 확인
     * 3. 디버깅 메서드에 따른 분기 처리:
     *    - RETURN_HOME: 복귀 명령 실행
     *    - RETURN_HOME_CANCEL: 복귀 취소 명령 실행
     *    - 기타: 일반 디버깅 명령 실행
     * 4. 결과 검증 및 응답 반환
     * 
     * 복귀 관련 명령은 웨이라인 서비스를 통해 실행되며,
     * 기타 디버깅 명령은 디버깅 서비스를 통해 실행됩니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param controlMethodEnum 원격 디버깅 메서드 열거형
     * @param param 원격 디버깅 파라미터
     * @return HTTP 응답 결과 (성공/실패)
     */
    @Override
    public HttpResultResponse controlDockDebug(String sn, RemoteDebugMethodEnum controlMethodEnum, RemoteDebugParam param) {
        // 디버깅 메서드 열거형에서 SDK 디버깅 메서드 열거형 추출
        DebugMethodEnum methodEnum = controlMethodEnum.getDebugMethodEnum();
        
        // 디버깅 조건 검증 및 핸들러 생성
        RemoteDebugHandler data = checkDebugCondition(sn, param, controlMethodEnum);

        // 도크 온라인 상태 확인
        boolean isExist = deviceRedisService.checkDeviceOnline(sn);
        if (!isExist) {
            return HttpResultResponse.error("The dock is offline.");
        }
        
        // 디버깅 메서드에 따른 분기 처리
        TopicServicesResponse response;
        switch (controlMethodEnum) {
            case RETURN_HOME:
                // 복귀 명령 실행
                response = abstractWaylineService.returnHome(SDKManager.getDeviceSDK(sn));
                break;
            case RETURN_HOME_CANCEL:
                // 복귀 취소 명령 실행
                response = abstractWaylineService.returnHomeCancel(SDKManager.getDeviceSDK(sn));
                break;
            default:
                // 일반 디버깅 명령 실행
                response = abstractDebugService.remoteDebug(SDKManager.getDeviceSDK(sn), methodEnum,
                        Objects.nonNull(methodEnum.getClazz()) ? mapper.convertValue(data, methodEnum.getClazz()) : null);
        }
        
        // 응답 결과 검증
        ServicesReplyData serviceReply = (ServicesReplyData) response.getData();
        if (!serviceReply.getResult().isSuccess()) {
            return HttpResultResponse.error(serviceReply.getResult());
        }
        return HttpResultResponse.success();
    }

    /**
     * 특정 지점 비행 조건을 확인합니다.
     * 
     * 이 메서드는 특정 지점으로 비행하기 전에 다음 조건들을 검증합니다:
     * 1. 도크 온라인 상태 확인
     * 2. 드론 모드가 MANUAL인지 확인 (수동 모드에서만 비행 가능)
     * 3. 비행 권한 획득 시도
     * 
     * 모든 조건이 만족되지 않으면 RuntimeException을 발생시켜
     * 안전하지 않은 비행을 방지합니다.
     * 
     * @param dockSn 도크 시리얼 번호
     * @throws RuntimeException 조건을 만족하지 않는 경우
     */
    private void checkFlyToCondition(String dockSn) {
        // TODO: 디바이스 펌웨어 버전 호환성 검사 추가 필요
        
        // 도크 온라인 상태 확인
        Optional<DeviceDTO> dockOpt = deviceRedisService.getDeviceOnline(dockSn);
        if (dockOpt.isEmpty()) {
            throw new RuntimeException("The dock is offline, please restart the dock.");
        }

        // 드론 모드 확인 (MANUAL 모드에서만 특정 지점 비행 가능)
        DroneModeCodeEnum deviceMode = deviceService.getDeviceMode(dockOpt.get().getChildDeviceSn());
        if (DroneModeCodeEnum.MANUAL != deviceMode) {
            throw new RuntimeException("The current state of the drone does not support this function, please try again later.");
        }

        // 비행 권한 획득 시도
        HttpResultResponse result = seizeAuthority(dockSn, DroneAuthorityEnum.FLIGHT, null);
        if (HttpResultResponse.CODE_SUCCESS != result.getCode()) {
            throw new IllegalArgumentException(result.getMessage());
        }
    }

    /**
     * 드론을 목표 지점으로 비행시킵니다.
     * 
     * 이 메서드는 다음과 같은 단계로 특정 지점 비행을 수행합니다:
     * 1. 비행 조건 검증 (checkFlyToCondition)
     * 2. 고유한 비행 ID 생성
     * 3. SDK를 통한 비행 명령 실행
     * 4. 결과 검증 및 응답 반환
     * 
     * 비행 ID는 UUID를 사용하여 고유성을 보장하며,
     * 비행 진행 상황 추적을 위해 사용됩니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param param 특정 지점 비행 파라미터 (목표 지점, 속도 등)
     * @return HTTP 응답 결과 (성공/실패)
     */
    @Override
    public HttpResultResponse flyToPoint(String sn, FlyToPointParam param) {
        // 비행 조건 검증
        checkFlyToCondition(sn);

        // 고유한 비행 ID 생성 (UUID 사용)
        param.setFlyToId(UUID.randomUUID().toString());
        
        // SDK를 통한 비행 명령 실행
        TopicServicesResponse<ServicesReplyData> response = abstractControlService.flyToPoint(
                SDKManager.getDeviceSDK(sn), mapper.convertValue(param, FlyToPointRequest.class));
        
        // 응답 결과 검증 및 반환
        ServicesReplyData reply = response.getData();
        return reply.getResult().isSuccess() ?
                HttpResultResponse.success()
                : HttpResultResponse.error("Flying to the target point failed. " + reply.getResult());
    }

    /**
     * 드론의 목표 지점 비행 미션을 종료합니다.
     * 
     * 이 메서드는 현재 진행 중인 특정 지점 비행을 중지시킵니다.
     * 드론은 현재 위치에서 정지하며, 안전한 상태로 전환됩니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return HTTP 응답 결과 (성공/실패)
     */
    @Override
    public HttpResultResponse flyToPointStop(String sn) {
        // SDK를 통한 비행 중지 명령 실행
        TopicServicesResponse<ServicesReplyData> response = abstractControlService.flyToPointStop(SDKManager.getDeviceSDK(sn));
        
        // 응답 결과 검증 및 반환
        ServicesReplyData reply = response.getData();
        return reply.getResult().isSuccess() ?
                HttpResultResponse.success()
                : HttpResultResponse.error("The drone flying to the target point failed to stop. " + reply.getResult());
    }

    /**
     * 이륙 조건을 확인합니다.
     * 
     * 이 메서드는 특정 지점으로 이륙하기 전에 다음 조건들을 검증합니다:
     * 1. 도크 온라인 상태 확인
     * 2. 도크 모드가 IDLE인지 확인 (대기 모드에서만 이륙 가능)
     * 3. 비행 권한 획득 시도
     * 
     * 모든 조건이 만족되지 않으면 RuntimeException을 발생시켜
     * 안전하지 않은 이륙을 방지합니다.
     * 
     * @param dockSn 도크 시리얼 번호
     * @throws RuntimeException 조건을 만족하지 않는 경우
     */
    private void checkTakeoffCondition(String dockSn) {
        // 도크 온라인 상태 및 모드 확인
        Optional<DeviceDTO> dockOpt = deviceRedisService.getDeviceOnline(dockSn);
        if (dockOpt.isEmpty() || DockModeCodeEnum.IDLE != deviceService.getDockMode(dockSn)) {
            throw new RuntimeException("The current state does not support takeoff.");
        }

        // 비행 권한 획득 시도
        HttpResultResponse result = seizeAuthority(dockSn, DroneAuthorityEnum.FLIGHT, null);
        if (HttpResultResponse.CODE_SUCCESS != result.getCode()) {
            throw new IllegalArgumentException(result.getMessage());
        }
    }

    /**
     * 드론을 특정 지점으로 이륙시킵니다.
     * 
     * 이 메서드는 다음과 같은 단계로 특정 지점 이륙을 수행합니다:
     * 1. 이륙 조건 검증 (checkTakeoffCondition)
     * 2. 고유한 비행 ID 생성
     * 3. SDK를 통한 이륙 명령 실행
     * 4. 결과 검증 및 응답 반환
     * 
     * 비행 ID는 UUID를 사용하여 고유성을 보장하며,
     * 이륙 진행 상황 추적을 위해 사용됩니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param param 특정 지점 이륙 파라미터 (목표 좌표, 고도, 속도 등)
     * @return HTTP 응답 결과 (성공/실패)
     */
    @Override
    public HttpResultResponse takeoffToPoint(String sn, TakeoffToPointParam param) {
        // 이륙 조건 검증
        checkTakeoffCondition(sn);

        // 고유한 비행 ID 생성 (UUID 사용)
        param.setFlightId(UUID.randomUUID().toString());
        
        // SDK를 통한 이륙 명령 실행
        TopicServicesResponse<ServicesReplyData> response = abstractControlService.takeoffToPoint(
                SDKManager.getDeviceSDK(sn), mapper.convertValue(param, TakeoffToPointRequest.class));
        
        // 응답 결과 검증 및 반환
        ServicesReplyData reply = response.getData();
        return reply.getResult().isSuccess() ?
                HttpResultResponse.success()
                : HttpResultResponse.error("The drone failed to take off. " + reply.getResult());
    }

    /**
     * 드론의 비행 제어 권한 또는 페이로드 제어 권한을 획득합니다.
     * 
     * 이 메서드는 권한 타입에 따라 다음과 같이 동작합니다:
     * 
     * FLIGHT 권한:
     * - 이미 권한을 보유하고 있는지 확인
     * - 권한이 없으면 SDK를 통해 권한 획득 시도
     * 
     * PAYLOAD 권한:
     * - 페이로드 권한 확인 (checkPayloadAuthority)
     * - 권한이 없으면 SDK를 통해 페이로드 권한 획득 시도
     * 
     * 권한이 이미 보유하고 있는 경우 즉시 성공을 반환하여
     * 불필요한 권한 획득 요청을 방지합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param authority 권한 타입 (FLIGHT 또는 PAYLOAD)
     * @param param 드론 페이로드 파라미터 (PAYLOAD 권한 시 필요)
     * @return HTTP 응답 결과 (성공/실패)
     */
    @Override
    public HttpResultResponse seizeAuthority(String sn, DroneAuthorityEnum authority, DronePayloadParam param) {
        TopicServicesResponse<ServicesReplyData> response;
        
        switch (authority) {
            case FLIGHT:
                // 이미 비행 권한을 보유하고 있는지 확인
                if (deviceService.checkAuthorityFlight(sn)) {
                    return HttpResultResponse.success();
                }
                // SDK를 통해 비행 권한 획득
                response = abstractControlService.flightAuthorityGrab(SDKManager.getDeviceSDK(sn));
                break;
                
            case PAYLOAD:
                // 페이로드 권한 확인
                if (checkPayloadAuthority(sn, param.getPayloadIndex())) {
                    return HttpResultResponse.success();
                }
                // SDK를 통해 페이로드 권한 획득
                response = abstractControlService.payloadAuthorityGrab(SDKManager.getDeviceSDK(sn),
                        new PayloadAuthorityGrabRequest().setPayloadIndex(new PayloadIndex(param.getPayloadIndex())));
                break;
                
            default:
                return HttpResultResponse.error(CloudSDKErrorEnum.INVALID_PARAMETER);
        }

        // 응답 결과 검증 및 반환
        ServicesReplyData serviceReply = response.getData();
        return serviceReply.getResult().isSuccess() ?
                HttpResultResponse.success()
                : HttpResultResponse.error(serviceReply.getResult());
    }

    /**
     * 페이로드 권한을 확인합니다.
     * 
     * 이 메서드는 다음을 수행합니다:
     * 1. 도크 온라인 상태 확인
     * 2. 자식 디바이스(드론)의 페이로드 권한 확인
     * 
     * 도크가 오프라인인 경우 RuntimeException을 발생시켜
     * 안전하지 않은 권한 확인을 방지합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param payloadIndex 페이로드 인덱스 (예: "0-0-0")
     * @return 페이로드 권한 보유 여부
     * @throws RuntimeException 도크가 오프라인인 경우
     */
    private Boolean checkPayloadAuthority(String sn, String payloadIndex) {
        // 도크 온라인 상태 확인
        Optional<DeviceDTO> dockOpt = deviceRedisService.getDeviceOnline(sn);
        if (dockOpt.isEmpty()) {
            throw new RuntimeException("The dock is offline, please restart the dock.");
        }
        
        // 자식 디바이스(드론)의 페이로드 권한 확인
        return devicePayloadService.checkAuthorityPayload(dockOpt.get().getChildDeviceSn(), payloadIndex);
    }

    /**
     * 드론의 페이로드를 제어합니다.
     * 
     * 이 메서드는 다음과 같은 단계로 페이로드 제어를 수행합니다:
     * 1. 페이로드 명령에 해당하는 핸들러 클래스 인스턴스 생성
     * 2. 핸들러를 통한 실행 조건 검증 (checkCondition)
     * 3. SDK를 통한 페이로드 제어 명령 실행
     * 4. 결과 검증 및 응답 반환
     * 
     * 지원하는 페이로드 명령:
     * - 카메라 모드 전환
     * - 사진 촬영
     * - 녹화 시작/중지
     * - 카메라 조준
     * - 초점 거리 설정
     * - 짐벌 리셋
     * 
     * 핸들러는 리플렉션을 통해 동적으로 생성되며,
     * 각 명령별로 적절한 조건 검증을 수행합니다.
     * 
     * @param param 페이로드 명령 파라미터 (명령 타입, 페이로드 데이터 등)
     * @return HTTP 응답 결과 (성공/실패)
     * @throws Exception 리플렉션을 통한 핸들러 생성 중 오류 발생 시
     */
    @Override
    public HttpResultResponse payloadCommands(PayloadCommandsParam param) throws Exception {
        // 페이로드 명령에 해당하는 핸들러 클래스 인스턴스 생성 및 조건 검증
        param.getCmd().getClazz()
                .getDeclaredConstructor(DronePayloadParam.class)
                .newInstance(param.getData())
                .checkCondition(param.getSn());

        // SDK를 통한 페이로드 제어 명령 실행
        TopicServicesResponse<ServicesReplyData> response = abstractControlService.payloadControl(
                SDKManager.getDeviceSDK(param.getSn()), param.getCmd().getCmd(),
                mapper.convertValue(param.getData(), param.getCmd().getCmd().getClazz()));

        // 응답 결과 검증 및 반환
        ServicesReplyData serviceReply = response.getData();
        return serviceReply.getResult().isSuccess() ?
                HttpResultResponse.success()
                : HttpResultResponse.error(serviceReply.getResult());
    }
}
