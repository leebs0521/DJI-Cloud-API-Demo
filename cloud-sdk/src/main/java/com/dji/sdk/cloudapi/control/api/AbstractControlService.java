package com.dji.sdk.cloudapi.control.api;

import com.dji.sdk.annotations.CloudSDKVersion;
import com.dji.sdk.cloudapi.control.*;
import com.dji.sdk.common.BaseModel;
import com.dji.sdk.common.Common;
import com.dji.sdk.common.SpringBeanUtils;
import com.dji.sdk.config.version.CloudSDKVersionEnum;
import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.config.version.GatewayTypeEnum;
import com.dji.sdk.exception.CloudSDKException;
import com.dji.sdk.mqtt.ChannelName;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.drc.DrcDownPublish;
import com.dji.sdk.mqtt.drc.DrcUpData;
import com.dji.sdk.mqtt.drc.TopicDrcRequest;
import com.dji.sdk.mqtt.events.EventsDataRequest;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import com.dji.sdk.mqtt.events.TopicEventsResponse;
import com.dji.sdk.mqtt.services.ServicesPublish;
import com.dji.sdk.mqtt.services.ServicesReplyData;
import com.dji.sdk.mqtt.services.TopicServicesResponse;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 추상 제어 서비스 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 드론 제어 기능을 제공하는 추상 서비스 클래스입니다.
 * 비행 제어, 카메라 제어, 페이로드 제어, DRC 모드 등 다양한 제어 기능을 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/29
 */
public abstract class AbstractControlService {

    /**
     * 서비스 발행 객체
     */
    @Resource
    private ServicesPublish servicesPublish;

    /**
     * DRC 하향 발행 객체
     */
    @Resource
    private DrcDownPublish drcDownPublish;

    /**
     * 지정 지점 비행 진행 상황 이벤트 알림
     * 
     * @param request 데이터
     * @param headers 메시지 헤더
     * @return 이벤트 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_FLY_TO_POINT_PROGRESS, outputChannel = ChannelName.OUTBOUND_EVENTS)
    public TopicEventsResponse<MqttReply> flyToPointProgress(TopicEventsRequest<FlyToPointProgress> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("flyToPointProgress not implemented");
    }

    /**
     * 원클릭 이륙 결과 이벤트 알림
     * 
     * @param request 데이터
     * @param headers 메시지 헤더
     * @return 이벤트 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_TAKEOFF_TO_POINT_PROGRESS, outputChannel = ChannelName.OUTBOUND_EVENTS)
    public TopicEventsResponse<MqttReply> takeoffToPointProgress(TopicEventsRequest<TakeoffToPointProgress> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("takeoffToPointProgress not implemented");
    }

    /**
     * DRC 링크 상태 알림
     * 
     * @param request 데이터
     * @param headers 메시지 헤더
     * @return 이벤트 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_DRC_STATUS_NOTIFY, outputChannel = ChannelName.OUTBOUND_EVENTS)
    public TopicEventsResponse<MqttReply> drcStatusNotify(TopicEventsRequest<DrcStatusNotify> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("drcStatusNotify not implemented");
    }

    /**
     * 조이스틱 제어 무효화 이유 알림
     * 
     * @param request 데이터
     * @param headers 메시지 헤더
     * @return 이벤트 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_JOYSTICK_INVALID_NOTIFY, outputChannel = ChannelName.OUTBOUND_EVENTS)
    public TopicEventsResponse<MqttReply> joystickInvalidNotify(TopicEventsRequest<JoystickInvalidNotify> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("joystickInvalidNotify not implemented");
    }

    /**
     * 비행 제어 권한 획득
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> flightAuthorityGrab(GatewayManager gateway) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.FLIGHT_AUTHORITY_GRAB.getMethod());
    }

    /**
     * 페이로드 제어 권한 획득
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> payloadAuthorityGrab(GatewayManager gateway, PayloadAuthorityGrabRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.PAYLOAD_AUTHORITY_GRAB.getMethod(),
                request);
    }

    /**
     * 라이브 비행 제어 모드 진입
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> drcModeEnter(GatewayManager gateway, DrcModeEnterRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.DRC_MODE_ENTER.getMethod(),
                request);
    }

    /**
     * 라이브 비행 제어 모드 종료
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> drcModeExit(GatewayManager gateway) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.DRC_MODE_EXIT.getMethod());
    }

    /**
     * 원클릭 이륙
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> takeoffToPoint(GatewayManager gateway, TakeoffToPointRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.TAKEOFF_TO_POINT.getMethod(),
                request);
    }

    /**
     * 비행 목표 지점 이동
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> flyToPoint(GatewayManager gateway, FlyToPointRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.FLY_TO_POINT.getMethod(),
                request);
    }

    /**
     * 빠른 목표 지점 업데이트
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_2, exclude = GatewayTypeEnum.RC, include = GatewayTypeEnum.DOCK)
    public TopicServicesResponse<ServicesReplyData> flyToPointUpdate(GatewayManager gateway, FlyToPointUpdateRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.FLY_TO_POINT_UPDATE.getMethod(),
                request);
    }

    /**
     * 비행 목표 지점 작업 종료
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> flyToPointStop(GatewayManager gateway) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.FLY_TO_POINT_STOP.getMethod());
    }

    /**
     * 페이로드 제어 - 카메라 모드 스위치
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> cameraModeSwitch(GatewayManager gateway, CameraModeSwitchRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.CAMERA_MODE_SWITCH.getMethod(),
                request);
    }

    /**
     * 페이로드 제어 - 단일 사진 촬영
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> cameraPhotoTake(GatewayManager gateway, CameraPhotoTakeRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.CAMERA_PHOTO_TAKE.getMethod(),
                request);
    }

    /**
     * 페이로드 제어 - 사진 촬영 중지
     * 현재 파노라마 사진 모드만 지원합니다.
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_2, exclude = GatewayTypeEnum.RC, include = GatewayTypeEnum.DOCK)
    public TopicServicesResponse<ServicesReplyData> cameraPhotoStop(GatewayManager gateway, CameraPhotoStopRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.CAMERA_PHOTO_STOP.getMethod(),
                request);
    }

    /**
     * 카메라 사진 진행 상황 정보 이벤트 알림
     * 현재 파노라마 사진 모드만 지원합니다.
     * @param request 데이터
     * @param headers 메시지 헤더
     * @return 이벤트 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_CAMERA_PHOTO_TAKE_PROGRESS, outputChannel = ChannelName.OUTBOUND_EVENTS)
    public TopicEventsResponse<MqttReply> cameraPhotoTakeProgress(TopicEventsRequest<EventsDataRequest<CameraPhotoTakeProgress>> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("cameraPhotoTakeProgress not implemented");
    }

    /**
     * 페이로드 제어 - 녹화 시작
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> cameraRecordingStart(GatewayManager gateway, CameraRecordingStartRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.CAMERA_RECORDING_START.getMethod(),
                request);
    }

    /**
     * 페이로드 제어 - 녹화 중지
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> cameraRecordingStop(GatewayManager gateway, CameraRecordingStopRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.CAMERA_RECORDING_STOP.getMethod(),
                request);
    }

    /**
     * 페이로드 제어 - 더블 탭으로 AIM 변경
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> cameraAim(GatewayManager gateway, CameraAimRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.CAMERA_AIM.getMethod(),
                request);
    }

    /**
     * 페이로드 제어 - 줌
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> cameraFocalLengthSet(GatewayManager gateway, CameraFocalLengthSetRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.CAMERA_FOCAL_LENGTH_SET.getMethod(),
                request);
    }

    /**
     * 페이로드 제어 - 짐버 초기화
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> gimbalReset(GatewayManager gateway, GimbalResetRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.GIMBAL_RESET.getMethod(),
                request);
    }

    /**
     * `lookat` 기능은 항공기가 현재 방향에서 지정된 실제 위도, 경도, 고도 위치를 바라보도록 자신을 회전시키는 기능을 의미합니다.
     * M30/M30T 모델의 경우 `lookat` 기능을 사용할 때 짐버를 잠그는 방법을 권장합니다.
     * 짐버가 한계에 도달하면 `lookat` 기능이 비정상적으로 동작할 수 있습니다.
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0, exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> cameraLookAt(GatewayManager gateway, CameraLookAtRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.CAMERA_LOOK_AT.getMethod(),
                request);
    }

    /**
     * 페이로드 제어 - 화면 분할
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0, exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> cameraScreenSplit(GatewayManager gateway, CameraScreenSplitRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.CAMERA_SCREEN_SPLIT.getMethod(),
                request);
    }

    /**
     * 페이로드 제어 - 사진 저장 설정
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0, exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> photoStorageSet(GatewayManager gateway, PhotoStorageSetRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.PHOTO_STORAGE_SET.getMethod(),
                request);
    }

    /**
     * 페이로드 제어 - 동영상 저장 설정
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_0, exclude = GatewayTypeEnum.RC)
    public TopicServicesResponse<ServicesReplyData> videoStorageSet(GatewayManager gateway, VideoStorageSetRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.VIDEO_STORAGE_SET.getMethod(),
                request);
    }

    /**
     * 페이로드 제어 - 카메라 노출 설정
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_2, include = GatewayTypeEnum.DOCK)
    public TopicServicesResponse<ServicesReplyData> cameraExposureSet(GatewayManager gateway, CameraExposureSetRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.CAMERA_EXPOSURE_SET.getMethod(),
                request);
    }

    /**
     * 페이로드 제어 - 카메라 노출 모드 설정
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_2, include = GatewayTypeEnum.DOCK)
    public TopicServicesResponse<ServicesReplyData> cameraExposureModeSet(GatewayManager gateway, CameraExposureModeSetRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.CAMERA_EXPOSURE_MODE_SET.getMethod(),
                request);
    }

    /**
     * 페이로드 제어 - 카메라 초점 모드 설정
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_2, include = GatewayTypeEnum.DOCK)
    public TopicServicesResponse<ServicesReplyData> cameraFocusModeSet(GatewayManager gateway, CameraFocusModeSetRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.CAMERA_FOCUS_MODE_SET.getMethod(),
                request);
    }

    /**
     * 페이로드 제어 - 카메라 초점 값 설정
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_2, include = GatewayTypeEnum.DOCK)
    public TopicServicesResponse<ServicesReplyData> cameraFocusValueSet(GatewayManager gateway, CameraFocusValueSetRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.CAMERA_FOCUS_VALUE_SET.getMethod(),
                request);
    }

    /**
     * 페이로드 제어 - IR 측광 모드 설정
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_2, include = GatewayTypeEnum.DOCK)
    public TopicServicesResponse<ServicesReplyData> irMeteringModeSet(GatewayManager gateway, IrMeteringModeSetRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.IR_METERING_MODE_SET.getMethod(),
                request);
    }

    /**
     * 페이로드 제어 - IR 측광 지점 설정
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_2, include = GatewayTypeEnum.DOCK)
    public TopicServicesResponse<ServicesReplyData> irMeteringPointSet(GatewayManager gateway, IrMeteringPointSetRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.IR_METERING_POINT_SET.getMethod(),
                request);
    }

    /**
     * 페이로드 제어 - IR 측광 영역 설정
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_2, include = GatewayTypeEnum.DOCK)
    public TopicServicesResponse<ServicesReplyData> irMeteringAreaSet(GatewayManager gateway, IrMeteringAreaSetRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.IR_METERING_AREA_SET.getMethod(),
                request);
    }

    /**
     * 페이로드 제어 - 카메라 포인트 초점
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_2, include = GatewayTypeEnum.DOCK)
    public TopicServicesResponse<ServicesReplyData> cameraPointFocusAction(GatewayManager gateway, CameraPointFocusActionRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.CAMERA_POINT_FOCUS_ACTION.getMethod(),
                request);
    }

    /**
     * 페이로드 제어
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    public TopicServicesResponse<ServicesReplyData> payloadControl(GatewayManager gateway, PayloadControlMethodEnum methodEnum, BaseModel request) {
        try {
            AbstractControlService abstractControlService = SpringBeanUtils.getBean(this.getClass());
            Method method = abstractControlService.getClass().getDeclaredMethod(
                    Common.convertSnake(methodEnum.getPayloadMethod().getMethod()),GatewayManager.class, methodEnum.getClazz());
            return (TopicServicesResponse<ServicesReplyData>) method.invoke(abstractControlService, gateway, request);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new CloudSDKException(e);
        } catch (InvocationTargetException e) {
            throw new CloudSDKException(e.getTargetException());
        }
    }

    /**
     * 지점 주변 정보 이벤트 알림
     * 
     * @param request 데이터
     * @param headers 메시지 헤더
     * @return 이벤트 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_POI_STATUS_NOTIFY, outputChannel = ChannelName.OUTBOUND_EVENTS)
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_2, include = GatewayTypeEnum.DOCK)
    public TopicEventsResponse<MqttReply> poiStatusNotify(TopicEventsRequest<PoiStatusNotify> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("poiStatusNotify not implemented");
    }

    /**
     * 지점 주변 모드 진입
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_2, exclude = GatewayTypeEnum.RC, include = GatewayTypeEnum.DOCK)
    public TopicServicesResponse<ServicesReplyData> poiModeEnter(GatewayManager gateway, PoiModeEnterRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.POI_MODE_ENTER.getMethod(),
                request);
    }

    /**
     * 지점 주변 모드 종료
     * 
     * @param gateway 게이트웨이 관리자
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_2, exclude = GatewayTypeEnum.RC, include = GatewayTypeEnum.DOCK)
    public TopicServicesResponse<ServicesReplyData> poiModeExit(GatewayManager gateway) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.POI_MODE_EXIT.getMethod());
    }

    /**
     * 지점 속도 설정
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     * @return 서비스 응답
     */
    @CloudSDKVersion(since = CloudSDKVersionEnum.V1_0_2, exclude = GatewayTypeEnum.RC, include = GatewayTypeEnum.DOCK)
    public TopicServicesResponse<ServicesReplyData> poiCircleSpeedSet(GatewayManager gateway, PoiCircleSpeedSetRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.POI_CIRCLE_SPEED_SET.getMethod(),
                request);
    }

    /**
     * DRC-비행 제어
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    protected void droneControlDown(GatewayManager gateway, DroneControlRequest request) {
        drcDownPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.DRONE_CONTROL.getMethod(),
                request);
    }

    /**
     * DRC 드론 제어 결과 상향 알림
     * 
     * @param request 데이터
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_DRC_UP_DRONE_CONTROL)
    public void droneControlUp(TopicDrcRequest<DrcUpData<DroneControlResponse>> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("droneControlUp not implemented");
    }

    /**
     * DRC-드론 비상 정지
     * 
     * @param gateway 게이트웨이 관리자
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public void droneEmergencyStopDown(GatewayManager gateway) {
        drcDownPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.DRONE_EMERGENCY_STOP.getMethod());
    }

    /**
     * DRC 드론 비상 정지 결과 상향 알림
     * 
     * @param request 데이터
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_DRC_UP_DRONE_EMERGENCY_STOP)
    public void droneEmergencyStopUp(TopicDrcRequest<DrcUpData> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("droneEmergencyStopUp not implemented");
    }


    /**
     * DRC-하트비트
     * 
     * @param gateway 게이트웨이 관리자
     * @param request  데이터
     */
    @CloudSDKVersion(exclude = GatewayTypeEnum.RC)
    public void heartBeatDown(GatewayManager gateway, HeartBeatRequest request) {
        drcDownPublish.publish(
                gateway.getGatewaySn(),
                ControlMethodEnum.HEART_BEAT.getMethod(),
                request);
    }

    /**
     * DRC 하트비트 결과 상향 알림
     * 
     * @param request 데이터
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_DRC_UP_HEART_BEAT)
    public void heartBeatUp(TopicDrcRequest<HeartBeatRequest> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("heartBeatUp not implemented");
    }

    /**
     * DRC 장애물 회피 정보 푸시
     * 
     * @param request 데이터
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_DRC_UP_HSI_INFO_PUSH)
    public void hsiInfoPush(TopicDrcRequest<HsiInfoPush> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("hsiInfoPush not implemented");
    }

    /**
     * DRC 영상 전송 링크 지연 정보 푸시
     * 
     * @param request 데이터
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_DRC_UP_DELAY_INFO_PUSH)
    public void delayInfoPush(TopicDrcRequest<DelayInfoPush> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("delayInfoPush not implemented");
    }

    /**
     * DRC 고주파 OSD 정보 푸시
     * 
     * @param request 데이터
     * @param headers 메시지 헤더
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_DRC_UP_OSD_INFO_PUSH)
    public void osdInfoPush(TopicDrcRequest<OsdInfoPush> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("osdInfoPush not implemented");
    }


}
