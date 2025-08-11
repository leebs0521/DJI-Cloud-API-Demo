package com.dji.sample.manage.service.impl;

import com.dji.sample.common.error.CommonErrorEnum;
import com.dji.sample.common.util.SpringBeanUtilsTest;
import com.dji.sample.manage.model.dto.ProductConfigDTO;
import com.dji.sample.manage.model.enums.CustomizeConfigScopeEnum;
import com.dji.sdk.cloudapi.config.ProductConfigResponse;
import com.dji.sdk.cloudapi.config.RequestsConfigRequest;
import com.dji.sdk.cloudapi.config.api.AbstractConfigService;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.requests.TopicRequestsRequest;
import com.dji.sdk.mqtt.requests.TopicRequestsResponse;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 요청 설정 컨텍스트 서비스 구현체
 * 
 * DJI Cloud API의 설정 요청 처리를 위한 서비스 구현체입니다.
 * 이 클래스는 AbstractConfigService를 상속받아 디바이스 설정 요청을
 * 처리하고 적절한 설정 응답을 반환합니다.
 * 
 * 주요 기능:
 * 1. 설정 요청 처리
 *    - MQTT를 통한 설정 요청 수신
 *    - 설정 범위 검증 및 처리
 *    - 설정 데이터 조회 및 응답
 *    - 오류 처리 및 예외 상황 관리
 * 
 * 2. 설정 범위 관리
 *    - 커스터마이징 설정 범위 검증
 *    - 설정 범위별 적절한 설정 데이터 제공
 *    - 설정 범위 열거형 처리
 * 
 * 3. 제품 설정 응답
 *    - NTP 서버 호스트 설정
 *    - 앱 ID, 앱 키, 앱 라이선스 설정
 *    - 제품별 맞춤 설정 제공
 * 
 * 4. 스프링 빈 관리
 *    - 설정 범위별 스프링 빈 조회
 *    - 동적 빈 인스턴스 생성
 *    - 설정 데이터 접근
 * 
 * 주요 의존성:
 * - AbstractConfigService: DJI 설정 서비스 기본 클래스
 * - CustomizeConfigScopeEnum: 커스터마이징 설정 범위 열거형
 * - ProductConfigDTO: 제품 설정 DTO
 * - SpringBeanUtilsTest: 스프링 빈 유틸리티
 * 
 * 이 클래스는 DJI 디바이스의 설정 요청을
 * 효율적으로 처리하는 서비스입니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/10
 */
@Service
public class RequestConfigContext extends AbstractConfigService {

    /**
     * 설정 요청을 처리합니다.
     * 
     * MQTT를 통해 수신된 설정 요청을 처리하고 적절한 설정 응답을 반환합니다.
     * 설정 범위를 검증하고 해당하는 설정 데이터를 조회하여 응답합니다.
     * 
     * @param request 설정 요청
     * @param headers 메시지 헤더
     * @return 설정 응답
     */
    @Override
    public TopicRequestsResponse<ProductConfigResponse> requestsConfig(TopicRequestsRequest<RequestsConfigRequest> request, MessageHeaders headers) {
        RequestsConfigRequest configReceiver = request.getData();
        // 설정 범위 검증
        Optional<CustomizeConfigScopeEnum> scopeEnumOpt = CustomizeConfigScopeEnum.find(configReceiver.getConfigScope().getScope());
        if (scopeEnumOpt.isEmpty()) {
            return new TopicRequestsResponse().setData(MqttReply.error(CommonErrorEnum.ILLEGAL_ARGUMENT));
        }

        // 설정 범위에 따른 설정 데이터 조회
        ProductConfigDTO config = (ProductConfigDTO) SpringBeanUtilsTest.getBean(scopeEnumOpt.get().getClazz()).getConfig();
        return new TopicRequestsResponse<ProductConfigResponse>().setData(
                new ProductConfigResponse()
                        .setNtpServerHost(config.getNtpServerHost())
                        .setAppId(config.getAppId())
                        .setAppKey(config.getAppKey())
                        .setAppLicense(config.getAppLicense()));
    }
}
