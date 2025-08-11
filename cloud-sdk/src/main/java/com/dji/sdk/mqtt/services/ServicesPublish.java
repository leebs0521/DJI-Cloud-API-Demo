package com.dji.sdk.mqtt.services;

import com.dji.sdk.common.Common;
import com.dji.sdk.mqtt.MqttGatewayPublish;
import com.dji.sdk.mqtt.TopicConst;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;

/**
 * MQTT 서비스 발행 클래스
 * 디바이스 서비스 요청을 MQTT로 발행하는 기능을 제공
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/24
 */
@Component
public class ServicesPublish {

    /** MQTT 게이트웨이 발행 서비스 */
    @Resource
    private MqttGatewayPublish gatewayPublish;

    /**
     * 기본 설정으로 서비스 요청을 발행합니다.
     * 
     * @param clazz 응답 데이터 타입
     * @param sn 디바이스 시리얼 번호
     * @param method 서비스 메서드명
     * @return 서비스 응답
     */
    public <T> TopicServicesResponse<ServicesReplyData<T>>  publish(TypeReference<T> clazz, String sn, String method) {
        return this.publish(clazz, sn, method, null);
    }

    /**
     * 데이터를 포함하여 서비스 요청을 발행합니다.
     * 
     * @param clazz 응답 데이터 타입
     * @param sn 디바이스 시리얼 번호
     * @param method 서비스 메서드명
     * @param data 요청 데이터
     * @return 서비스 응답
     */
    public <T> TopicServicesResponse<ServicesReplyData<T>> publish(TypeReference<T> clazz, String sn, String method, Object data) {
        return this.publish(clazz, sn, method, data, MqttGatewayPublish.DEFAULT_RETRY_COUNT);
    }

    /**
     * 재시도 횟수를 지정하여 서비스 요청을 발행합니다.
     * 
     * @param clazz 응답 데이터 타입
     * @param sn 디바이스 시리얼 번호
     * @param method 서비스 메서드명
     * @param data 요청 데이터
     * @param retryCount 재시도 횟수
     * @return 서비스 응답
     */
    public <T> TopicServicesResponse<ServicesReplyData<T>>  publish(TypeReference<T> clazz, String sn, String method, Object data, int retryCount) {
        return this.publish(clazz, sn, method, data, retryCount, MqttGatewayPublish.DEFAULT_RETRY_TIMEOUT);
    }

    /**
     * 타임아웃을 지정하여 서비스 요청을 발행합니다.
     * 
     * @param clazz 응답 데이터 타입
     * @param sn 디바이스 시리얼 번호
     * @param method 서비스 메서드명
     * @param data 요청 데이터
     * @param timeout 타임아웃 (밀리초)
     * @return 서비스 응답
     */
    public <T> TopicServicesResponse<ServicesReplyData<T>>  publish(TypeReference<T> clazz, String sn, String method, Object data, long timeout) {
        return this.publish(clazz, sn, method, data, MqttGatewayPublish.DEFAULT_RETRY_COUNT, timeout);
    }

    /**
     * 재시도 횟수와 타임아웃을 지정하여 서비스 요청을 발행합니다.
     * 
     * @param clazz 응답 데이터 타입
     * @param sn 디바이스 시리얼 번호
     * @param method 서비스 메서드명
     * @param data 요청 데이터
     * @param retryCount 재시도 횟수
     * @param timeout 타임아웃 (밀리초)
     * @return 서비스 응답
     */
    public <T> TopicServicesResponse<ServicesReplyData<T>>  publish(TypeReference<T> clazz, String sn, String method, Object data, int retryCount, long timeout) {
        return this.publish(clazz, sn, method, data, null, retryCount, timeout);
    }

    /**
     * 기본 설정으로 서비스 요청을 발행합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param method 서비스 메서드명
     * @return 서비스 응답
     */
    public TopicServicesResponse<ServicesReplyData> publish(String sn, String method) {
        return this.publish(sn, method, null, null);
    }

    /**
     * 데이터를 포함하여 서비스 요청을 발행합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param method 서비스 메서드명
     * @param data 요청 데이터
     * @return 서비스 응답
     */
    public TopicServicesResponse<ServicesReplyData> publish(String sn, String method, Object data) {
        return this.publish(sn, method, data, null);
    }

    /**
     * 재시도 횟수를 지정하여 서비스 요청을 발행합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param method 서비스 메서드명
     * @param data 요청 데이터
     * @param retryCount 재시도 횟수
     * @return 서비스 응답
     */
    public TopicServicesResponse<ServicesReplyData> publish(String sn, String method, Object data, int retryCount) {
        return this.publish(sn, method, data, null, retryCount);
    }

    /**
     * 타임아웃을 지정하여 서비스 요청을 발행합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param method 서비스 메서드명
     * @param data 요청 데이터
     * @param timeout 타임아웃 (밀리초)
     * @return 서비스 응답
     */
    public TopicServicesResponse<ServicesReplyData> publish(String sn, String method, Object data, long timeout) {
        return this.publish(sn, method, data, null, timeout);
    }

    /**
     * 재시도 횟수와 타임아웃을 지정하여 서비스 요청을 발행합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param method 서비스 메서드명
     * @param data 요청 데이터
     * @param retryCount 재시도 횟수
     * @param timeout 타임아웃 (밀리초)
     * @return 서비스 응답
     */
    public TopicServicesResponse<ServicesReplyData> publish(String sn, String method, Object data, int retryCount, long timeout) {
        return this.publish(sn, method, data, null, retryCount, timeout);
    }

    /**
     * 비즈니스 ID를 포함하여 서비스 요청을 발행합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param method 서비스 메서드명
     * @param data 요청 데이터
     * @param bid 비즈니스 ID
     * @return 서비스 응답
     */
    public TopicServicesResponse<ServicesReplyData> publish(String sn, String method, Object data, String bid) {
        return this.publish(sn, method, data, bid, MqttGatewayPublish.DEFAULT_RETRY_COUNT);
    }

    /**
     * 비즈니스 ID와 재시도 횟수를 포함하여 서비스 요청을 발행합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param method 서비스 메서드명
     * @param data 요청 데이터
     * @param bid 비즈니스 ID
     * @param retryCount 재시도 횟수
     * @return 서비스 응답
     */
    public TopicServicesResponse<ServicesReplyData> publish(String sn, String method, Object data, String bid, int retryCount) {
        return this.publish(sn, method, data, bid, retryCount, MqttGatewayPublish.DEFAULT_RETRY_TIMEOUT);
    }

    /**
     * 비즈니스 ID와 타임아웃을 포함하여 서비스 요청을 발행합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param method 서비스 메서드명
     * @param data 요청 데이터
     * @param bid 비즈니스 ID
     * @param timeout 타임아웃 (밀리초)
     * @return 서비스 응답
     */
    public TopicServicesResponse<ServicesReplyData> publish(String sn, String method, Object data, String bid, long timeout) {
        return this.publish(sn, method, data, bid, MqttGatewayPublish.DEFAULT_RETRY_COUNT, timeout);
    }

    /**
     * 비즈니스 ID, 재시도 횟수, 타임아웃을 포함하여 서비스 요청을 발행합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param method 서비스 메서드명
     * @param data 요청 데이터
     * @param bid 비즈니스 ID
     * @param retryCount 재시도 횟수
     * @param timeout 타임아웃 (밀리초)
     * @return 서비스 응답
     */
    public TopicServicesResponse<ServicesReplyData> publish(String sn, String method, Object data, String bid, int retryCount, long timeout) {
        return (TopicServicesResponse) this.publish(null, sn, method, data, bid, retryCount, timeout);
    }

    /**
     * 모든 매개변수를 포함하여 서비스 요청을 발행합니다.
     * 
     * @param clazz 응답 데이터 타입
     * @param sn 디바이스 시리얼 번호
     * @param method 서비스 메서드명
     * @param data 요청 데이터
     * @param bid 비즈니스 ID
     * @param retryCount 재시도 횟수
     * @param timeout 타임아웃 (밀리초)
     * @return 서비스 응답
     */
    public <T> TopicServicesResponse<ServicesReplyData<T>> publish(
            TypeReference<T> clazz, String sn, String method, Object data, String bid, int retryCount, long timeout) {
        // 서비스 토픽 생성
        String topic = TopicConst.THING_MODEL_PRE + TopicConst.PRODUCT + Objects.requireNonNull(sn) + TopicConst.SERVICES_SUF;
        // MQTT로 서비스 요청 발행
        TopicServicesResponse response = (TopicServicesResponse) gatewayPublish.publishWithReply(
                ServicesReplyReceiver.class, topic, new TopicServicesRequest<>()
                        .setTid(UUID.randomUUID().toString())
                        .setBid(bid)
                        .setTimestamp(System.currentTimeMillis())
                        .setMethod(method)
                        .setData(Objects.requireNonNullElse(data, "")), retryCount, timeout);
        ServicesReplyReceiver replyReceiver = (ServicesReplyReceiver) response.getData();
        ServicesReplyData<T> reply = new ServicesReplyData<T>().setResult(replyReceiver.getResult());
        if (Objects.isNull(clazz)) {
            // 타입이 지정되지 않은 경우 기본 처리
            reply.setOutput((T) Objects.requireNonNullElse(
                    replyReceiver.getOutput(), Objects.requireNonNullElse(replyReceiver.getInfo(), "")));
            return response.setData(reply);
        }
        // 타입이 지정된 경우 JSON 변환 처리
        ObjectMapper mapper = Common.getObjectMapper();
        if (Objects.nonNull(replyReceiver.getInfo())) {
            reply.setOutput(mapper.convertValue(replyReceiver.getInfo(), clazz));
        }
        if (Objects.nonNull(replyReceiver.getOutput())) {
            reply.setOutput(mapper.convertValue(replyReceiver.getOutput(), clazz));
        }
        return response.setData(reply);
    }

}
