package com.dji.sample.storage.service.impl;

import com.dji.sample.component.oss.model.OssConfiguration;
import com.dji.sample.component.oss.service.impl.OssServiceContext;
import com.dji.sample.storage.service.IStorageService;
import com.dji.sdk.cloudapi.media.StorageConfigGet;
import com.dji.sdk.cloudapi.media.api.AbstractMediaService;
import com.dji.sdk.cloudapi.storage.StsCredentialsResponse;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.requests.TopicRequestsRequest;
import com.dji.sdk.mqtt.requests.TopicRequestsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

/**
 * DJI Cloud API 스토리지 서비스 구현 클래스
 * 
 * 이 클래스는 IStorageService 인터페이스의 실제 구현체로,
 * DJI Pilot에서 미디어 파일과 웨이라인을 업로드하기 위한
 * 임시 자격 증명을 생성하고 제공합니다.
 * 
 * 주요 기능:
 * - OSS(Object Storage Service) 기반 임시 자격 증명 생성
 * - MQTT를 통한 스토리지 설정 요청 처리
 * - 클라우드 스토리지 접근을 위한 보안 토큰 관리
 * 
 * 상속 관계:
 * - AbstractMediaService: 미디어 서비스 기본 기능 상속
 * - IStorageService: 스토리지 서비스 인터페이스 구현
 * 
 * @author sean
 * @version 0.3
 * @date 2022/3/9
 */
@Service
public class StorageServiceImpl extends AbstractMediaService implements IStorageService {

    /**
     * OSS(Object Storage Service) 서비스 컨텍스트
     * 
     * 이 컴포넌트는 실제 클라우드 스토리지 서비스와의 연동을 담당하며,
     * 임시 자격 증명 생성, 파일 업로드/다운로드 등의 기능을 제공합니다.
     * 
     * 지원하는 스토리지 서비스:
     * - AWS S3
     * - Alibaba Cloud OSS
     * - 기타 호환 가능한 객체 스토리지 서비스
     */
    @Autowired
    private OssServiceContext ossService;

    /**
     * 미디어 파일과 웨이라인 업로드를 위한 임시 자격 증명을 생성합니다.
     * 
     * 이 메서드는 OSS 설정 정보를 기반으로 임시 보안 자격 증명을 생성하고,
     * DJI Pilot에서 안전하게 파일을 업로드할 수 있도록 필요한 모든 정보를 제공합니다.
     * 
     * 생성되는 자격 증명 정보:
     * - endpoint: OSS 서비스 엔드포인트 (예: https://oss-cn-hangzhou.aliyuncs.com)
     * - bucket: 파일이 저장될 OSS 버킷 이름
     * - credentials: 임시 액세스 키, 시크릿 키, 세션 토큰
     * - provider: 스토리지 서비스 제공자 (예: "oss")
     * - objectKeyPrefix: 업로드 파일의 키 접두사 (예: "media/")
     * - region: OSS 서비스 리전 (예: "cn-hangzhou")
     * 
     * @return 임시 자격 증명 정보를 포함한 응답 객체
     */
    @Override
    public StsCredentialsResponse getSTSCredentials() {
        // OSS 설정 정보를 기반으로 임시 자격 증명 응답 객체를 생성
        return new StsCredentialsResponse()
                .setEndpoint(OssConfiguration.endpoint)           // OSS 엔드포인트 설정
                .setBucket(OssConfiguration.bucket)              // OSS 버킷 이름 설정
                .setCredentials(ossService.getCredentials())     // 임시 자격 증명 정보 설정
                .setProvider(OssConfiguration.provider)          // 스토리지 제공자 설정
                .setObjectKeyPrefix(OssConfiguration.objectDirPrefix)  // 파일 키 접두사 설정
                .setRegion(OssConfiguration.region);             // OSS 리전 설정
    }

    /**
     * MQTT를 통한 스토리지 설정 요청을 처리합니다.
     * 
     * 이 메서드는 DJI Pilot에서 MQTT 프로토콜을 통해 스토리지 설정을 요청할 때
     * 호출되며, 임시 자격 증명 정보를 MQTT 응답 형태로 반환합니다.
     * 
     * 처리 과정:
     * 1. MQTT 요청 메시지 수신
     * 2. getSTSCredentials() 메서드를 호출하여 임시 자격 증명 생성
     * 3. 생성된 자격 증명을 MQTT 응답 형태로 래핑
     * 4. DJI Pilot로 응답 전송
     * 
     * @param response 스토리지 설정 요청 정보를 포함한 MQTT 요청 객체
     * @param headers MQTT 메시지 헤더 정보
     * @return 임시 자격 증명 정보를 포함한 MQTT 응답 객체
     */
    @Override
    public TopicRequestsResponse<MqttReply<StsCredentialsResponse>> storageConfigGet(TopicRequestsRequest<StorageConfigGet> response, MessageHeaders headers) {
        // 임시 자격 증명을 생성하고 MQTT 성공 응답으로 래핑하여 반환
        return new TopicRequestsResponse<MqttReply<StsCredentialsResponse>>()
                .setData(MqttReply.success(getSTSCredentials()));
    }
}
