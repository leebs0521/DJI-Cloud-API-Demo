package com.dji.sdk.cloudapi.organization.api;

import com.dji.sdk.cloudapi.organization.*;
import com.dji.sdk.mqtt.ChannelName;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.requests.TopicRequestsRequest;
import com.dji.sdk.mqtt.requests.TopicRequestsResponse;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

/**
 * 조직 서비스 추상 클래스
 * 
 * 이 클래스는 조직 관련 MQTT 기반 서비스의 기본 기능을 제공합니다.
 * 공항(도크)의 디바이스 바인딩 상태 조회, 조직 정보 조회, 디바이스 조직 바인딩 등의
 * 기능을 정의합니다.
 * 
 * 주요 기능:
 * - 공항 디바이스 바인딩 상태 조회
 * - 공항 조직 정보 조회
 * - 디바이스 조직 바인딩
 * 
 * 이 클래스는 공항과 조직 간의 디바이스 관리 기능을 제공합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public abstract class AbstractOrganizationService {

    /**
     * 공항 바인딩 상태 조회
     * 
     * 공항(도크)에 연결된 디바이스들의 조직 바인딩 상태를 조회합니다.
     * 공항에 연결된 모든 디바이스의 바인딩 상태를 일괄적으로 확인할 수 있습니다.
     * 
     * 사용 시나리오:
     * - 공항에 연결된 디바이스들의 바인딩 상태 확인
     * - 조직 관리자가 디바이스 바인딩 현황 파악
     * - 바인딩되지 않은 디바이스 식별 및 관리
     * 
     * @param request 공항 바인딩 상태 요청 데이터
     * @param headers MQTT 메시지 헤더
     * @return 공항 바인딩 상태 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_REQUESTS_AIRPORT_BIND_STATUS, outputChannel = ChannelName.OUTBOUND_REQUESTS)
    public TopicRequestsResponse<MqttReply<AirportBindStatusResponse>> airportBindStatus(
            TopicRequestsRequest<AirportBindStatusRequest> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("airportBindStatus not implemented");
    }

    /**
     * 공항 조직 정보 조회
     * 
     * 공항(도크)이 속한 조직의 정보를 조회합니다.
     * 디바이스 바인딩 과정에서 조직 정보를 확인할 때 사용됩니다.
     * 
     * 사용 시나리오:
     * - 디바이스 바인딩 전 조직 정보 확인
     * - 공항이 속한 조직의 상세 정보 조회
     * - 조직 관리 및 권한 확인
     * 
     * @param request 공항 조직 조회 요청 데이터
     * @param headers MQTT 메시지 헤더
     * @return 공항 조직 조회 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_REQUESTS_AIRPORT_ORGANIZATION_GET, outputChannel = ChannelName.OUTBOUND_REQUESTS)
    public TopicRequestsResponse<MqttReply<AirportOrganizationGetResponse>> airportOrganizationGet(
            TopicRequestsRequest<AirportOrganizationGetRequest> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("airportOrganizationGet not implemented");
    }

    /**
     * 디바이스 조직 바인딩
     * 
     * 공항(도크)에 연결된 디바이스들을 특정 조직에 바인딩합니다.
     * 여러 디바이스를 일괄적으로 조직에 등록할 수 있습니다.
     * 
     * 사용 시나리오:
     * - 공항에 연결된 모든 디바이스를 조직에 일괄 바인딩
     * - 새로운 디바이스의 조직 등록
     * - 조직 내 디바이스 관리 및 권한 설정
     * 
     * @param request 공항 조직 바인딩 요청 데이터
     * @param headers MQTT 메시지 헤더
     * @return 공항 조직 바인딩 응답
     */
    @ServiceActivator(inputChannel = ChannelName.INBOUND_REQUESTS_AIRPORT_ORGANIZATION_BIND, outputChannel = ChannelName.OUTBOUND_REQUESTS)
    public TopicRequestsResponse<MqttReply<AirportOrganizationBindResponse>> airportOrganizationBind(
            TopicRequestsRequest<AirportOrganizationBindRequest> request, MessageHeaders headers) {
        throw new UnsupportedOperationException("airportOrganizationBind not implemented");
    }
}
