package com.dji.sdk.cloudapi.wayline.api;

import com.dji.sdk.annotations.CloudSDKVersion;
import com.dji.sdk.cloudapi.wayline.*;
import com.dji.sdk.config.version.CloudSDKVersionEnum;
import com.dji.sdk.common.Common;
import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.config.version.GatewayTypeEnum;
import com.dji.sdk.exception.CloudSDKErrorEnum;
import com.dji.sdk.exception.CloudSDKException;
import com.dji.sdk.mqtt.ChannelName;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.events.EventsDataRequest;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import com.dji.sdk.mqtt.events.TopicEventsResponse;
import com.dji.sdk.mqtt.requests.TopicRequestsRequest;
import com.dji.sdk.mqtt.requests.TopicRequestsResponse;
import com.dji.sdk.mqtt.services.ServicesPublish;
import com.dji.sdk.mqtt.services.ServicesReplyData;
import com.dji.sdk.mqtt.services.TopicServicesResponse;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Resource;

/**
 * 웨이라인 서비스 추상 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업과 관련된 서비스 기능을 제공하는 추상 클래스입니다.
 * 웨이라인 작업의 생성, 준비, 실행, 취소, 일시정지, 복구 등의 기능을 포함하며,
 * MQTT를 통한 실시간 통신을 지원합니다.
 * 
 * 주요 기능:
 * - 웨이라인 작업 생성 및 관리
 * - 비행 작업 실행 및 제어
 * - 작업 진행 상황 모니터링
 * - 홈포인트 귀환 제어
 * - 실시간 이벤트 처리
 * 
 * 이 클래스는 웨이라인 비행 작업의 전체 생명주기를 관리하는
 * 기본 서비스 구현을 제공합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/19
 */
public abstract class AbstractWaylineService {

    /**
     * 서비스 발행 객체
     * 
     * MQTT를 통해 서비스 요청을 발행하는 객체입니다.
     */
    @Resource
    private ServicesPublish servicesPublish;

    /**
     * 디바이스가 Return to Home(RTH) 상태에서 벗어났음을 알리는 알림
     * 
     * 드론이 홈포인트 귀환 상태에서 벗어났을 때 호출되는 이벤트 처리 메서드입니다.
     * 
     * @param request 이벤트 요청 데이터
     * @param headers 메시지 헤더
     * @return 이벤트 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_DEVICE_EXIT_HOMING_NOTIFY, outputChannel = ChannelName.OUTBOUND_EVENTS)
    public TopicEventsResponse<MqttReply> deviceExitHomingNotify(TopicEventsRequest<DeviceExitHomingNotify> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("deviceExitHomingNotify not implemented");
    }

    /**
     * 웨이라인 작업 진행 상황 보고
     * 
     * 웨이라인 작업의 진행 상황을 실시간으로 보고하는 이벤트 처리 메서드입니다.
     * 
     * @param request 이벤트 요청 데이터
     * @param headers 메시지 헤더
     * @return 이벤트 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_FLIGHTTASK_PROGRESS, outputChannel = ChannelName.OUTBOUND_EVENTS)
    public TopicEventsResponse<MqttReply> flighttaskProgress(TopicEventsRequest<EventsDataRequest<FlighttaskProgress>> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("flighttaskProgress not implemented");
    }

    /**
     * 작업 준비 완료 알림
     * 
     * 웨이라인 작업이 준비 완료되었음을 알리는 이벤트 처리 메서드입니다.
     * 
     * @param request 이벤트 요청 데이터
     * @param headers 메시지 헤더
     * @return 이벤트 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_FLIGHTTASK_READY, outputChannel = ChannelName.OUTBOUND_EVENTS)
    public TopicEventsResponse<MqttReply> flighttaskReady(TopicEventsRequest<FlighttaskReady> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("flighttaskReady not implemented");
    }

    /**
     * 웨이라인 작업 생성 (사용 중단됨)
     * 
     * 새로운 웨이라인 작업을 생성하는 메서드입니다.
     * V0_0_1 버전부터 사용 중단되었습니다.
     * 
     * @param gateway 게이트웨이 관리자
     * @param request 작업 생성 요청
     * @return 서비스 응답
     */
    @CloudSDKVersion(deprecated = CloudSDKVersionEnum.V0_0_1, exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> flighttaskCreate(GatewayManager gateway, FlighttaskCreateRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                WaylineMethodEnum.FLIGHTTASK_CREATE.getMethod(),
                request);
    }

    /**
     * 웨이라인 작업 발행
     * 
     * 웨이라인 작업을 준비하고 발행하는 메서드입니다.
     * 작업 실행 전에 필요한 조건들을 확인합니다.
     * 
     * @param gateway 게이트웨이 관리자
     * @param request 작업 준비 요청
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> flighttaskPrepare(GatewayManager gateway, FlighttaskPrepareRequest request) {
        validPrepareParam(request);
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                WaylineMethodEnum.FLIGHTTASK_PREPARE.getMethod(),
                request,
                request.getFlightId());
    }

    /**
     * 웨이라인 작업 실행
     * 
     * 준비된 웨이라인 작업을 실제로 실행하는 메서드입니다.
     * 드론이 웨이라인에 따라 자동 비행을 시작합니다.
     * 
     * @param gateway 게이트웨이 관리자
     * @param request 작업 실행 요청
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> flighttaskExecute(GatewayManager gateway, FlighttaskExecuteRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                WaylineMethodEnum.FLIGHTTASK_EXECUTE.getMethod(),
                request,
                request.getFlightId());
    }

    /**
     * 웨이라인 작업 취소
     * 
     * 진행 중인 웨이라인 작업을 취소하는 메서드입니다.
     * 비행을 중단하고 안전하게 착륙합니다.
     * 
     * @param gateway 게이트웨이 관리자
     * @param request 작업 취소 요청
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> flighttaskUndo(GatewayManager gateway, FlighttaskUndoRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                WaylineMethodEnum.FLIGHTTASK_UNDO.getMethod(),
                request,
                request.getFlightId());
    }

    /**
     * 웨이라인 작업 일시정지
     * 
     * 진행 중인 웨이라인 작업을 일시정지하는 메서드입니다.
     * 현재 위치에서 호버링하며 작업을 일시 중단합니다.
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> flighttaskPause(GatewayManager gateway) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                WaylineMethodEnum.FLIGHTTASK_PAUSE.getMethod());
    }

    /**
     * 웨이라인 작업 복구
     * 
     * 일시정지된 웨이라인 작업을 복구하는 메서드입니다.
     * 중단된 지점부터 비행을 재개합니다.
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> flighttaskRecovery(GatewayManager gateway) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                WaylineMethodEnum.FLIGHTTASK_RECOVERY.getMethod());
    }

    /**
     * 홈포인트 귀환
     * 
     * 드론을 홈포인트로 귀환시키는 메서드입니다.
     * 현재 위치에서 안전하게 홈포인트로 돌아갑니다.
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> returnHome(GatewayManager gateway) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                WaylineMethodEnum.RETURN_HOME.getMethod());
    }

    /**
     * 홈포인트 귀환 취소
     * 
     * 진행 중인 홈포인트 귀환을 취소하는 메서드입니다.
     * 귀환을 중단하고 현재 위치에서 호버링합니다.
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> returnHomeCancel(GatewayManager gateway) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                WaylineMethodEnum.RETURN_HOME_CANCEL.getMethod());
    }

    /**
     * 웨이라인 작업 리소스 조회
     * 
     * 웨이라인 작업에 필요한 리소스 정보를 조회하는 메서드입니다.
     * 
     * @param request 리소스 조회 요청
     * @param headers 메시지 헤더
     * @return 요청 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_REQUESTS_FLIGHTTASK_RESOURCE_GET, outputChannel = ChannelName.OUTBOUND_REQUESTS)
    public TopicRequestsResponse<MqttReply<FlighttaskResourceGetResponse>> flighttaskResourceGet(TopicRequestsRequest<FlighttaskResourceGetRequest> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("flighttaskResourceGet not implemented");
    }

    /**
     * 홈포인트 귀환 정보
     * 
     * 홈포인트 귀환 관련 정보를 처리하는 메서드입니다.
     * V1_0_0 버전부터 지원됩니다.
     * 
     * @param request 홈포인트 귀환 정보 요청
     * @param headers 메시지 헤더
     * @return 요청 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_RETURN_HOME_INFO, outputChannel = ChannelName.OUTBOUND_EVENTS)
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0)
    public TopicRequestsResponse<MqttReply> returnHomeInfo(TopicRequestsRequest<ReturnHomeInfo> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("returnHomeInfo not implemented");
    }

    /**
     * 작업 준비 매개변수 유효성 검사
     * 
     * 웨이라인 작업 준비 요청의 매개변수를 검증하는 메서드입니다.
     * 
     * @param request 작업 준비 요청
     */
    private void validPrepareParam(FlighttaskPrepareRequest request) {
        if (Common.isBlank(request.getFlightId())) {
            throw new CloudSDKException(CloudSDKErrorEnum.INVALID_PARAMETER);
        }
    }
}
