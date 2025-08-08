package com.dji.sdk.cloudapi.log.api;

import com.dji.sdk.cloudapi.log.*;
import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.mqtt.ChannelName;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.events.EventsDataRequest;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import com.dji.sdk.mqtt.events.TopicEventsResponse;
import com.dji.sdk.mqtt.services.ServicesPublish;
import com.dji.sdk.mqtt.services.ServicesReplyData;
import com.dji.sdk.mqtt.services.TopicServicesResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import javax.annotation.Resource;

/**
 * 로그 서비스 추상 클래스
 * 로그 파일 업로드 관련 기능을 제공하는 서비스의 기본 구현을 정의합니다.
 * MQTT를 통한 로그 파일 업로드 작업을 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/28
 */
public abstract class AbstractLogService {

    /** MQTT 서비스 발행 객체 */
    @Resource
    private ServicesPublish servicesPublish;

    /**
     * 파일 업로드 진행 상황을 알립니다.
     * 업로드 진행 상황을 이벤트로 전송합니다.
     * 
     * @param request 업로드 진행 상황 데이터
     * @param headers 메시지 헤더
     * @return 이벤트 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_EVENTS_FILEUPLOAD_PROGRESS, outputChannel = ChannelName.OUTBOUND_EVENTS)
    public TopicEventsResponse<MqttReply> fileuploadProgress(TopicEventsRequest<EventsDataRequest<FileUploadProgress>> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("fileuploadProgress not implemented");
    }

    /**
     * 업로드 가능한 장치의 파일 목록을 가져옵니다.
     * 장치에서 업로드할 수 있는 로그 파일 목록을 조회합니다.
     * 
     * @param gateway 게이트웨이 관리자
     * @param request 파일 업로드 목록 요청 데이터
     * @return 서비스 응답
     */
    public TopicServicesResponse<ServicesReplyData<FileUploadListResponse>> fileuploadList(GatewayManager gateway, FileUploadListRequest request) {
        return servicesPublish.publish(
                new TypeReference<FileUploadListResponse>() {},
                gateway.getGatewaySn(),
                LogMethodEnum.FILE_UPLOAD_LIST.getMethod(),
                request);
    }

    /**
     * 로그 파일 업로드를 시작합니다.
     * 선택된 로그 파일들의 업로드 작업을 시작합니다.
     * 
     * @param gateway 게이트웨이 관리자
     * @param request 파일 업로드 시작 요청 데이터
     * @return 서비스 응답
     */
    public TopicServicesResponse<ServicesReplyData> fileuploadStart(GatewayManager gateway, FileUploadStartRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                LogMethodEnum.FILE_UPLOAD_START.getMethod(),
                request);
    }

    /**
     * 업로드 상태를 업데이트합니다.
     * 진행 중인 업로드 작업의 상태를 변경합니다.
     * 
     * @param gateway 게이트웨이 관리자
     * @param request 파일 업로드 업데이트 요청 데이터
     * @return 서비스 응답
     */
    public TopicServicesResponse<ServicesReplyData> fileuploadUpdate(GatewayManager gateway, FileUploadUpdateRequest request) {
        return servicesPublish.publish(
                gateway.getGatewaySn(),
                LogMethodEnum.FILE_UPLOAD_UPDATE.getMethod(),
                request);
    }

}
