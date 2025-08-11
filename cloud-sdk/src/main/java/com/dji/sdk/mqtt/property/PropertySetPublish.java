package com.dji.sdk.mqtt.property;

import com.dji.sdk.mqtt.MqttGatewayPublish;
import com.dji.sdk.mqtt.TopicConst;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.UUID;

/**
 * MQTT 프로퍼티 설정 발행 클래스
 * 디바이스 프로퍼티 설정 요청을 MQTT로 발행하는 기능을 제공
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/24
 */
@Component
public class PropertySetPublish {

    /** MQTT 게이트웨이 발행 서비스 */
    @Resource
    private MqttGatewayPublish gatewayPublish;

    /**
     * 기본 설정으로 프로퍼티 설정 요청을 발행합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param data 설정할 프로퍼티 데이터
     * @return 프로퍼티 설정 응답 결과
     */
    public PropertySetReplyResultEnum publish(String sn, Object data) {
        return this.publish(sn, data, MqttGatewayPublish.DEFAULT_RETRY_COUNT);
    }

    /**
     * 재시도 횟수를 지정하여 프로퍼티 설정 요청을 발행합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param data 설정할 프로퍼티 데이터
     * @param retryCount 재시도 횟수
     * @return 프로퍼티 설정 응답 결과
     */
    public PropertySetReplyResultEnum publish(String sn, Object data, int retryCount) {
        return this.publish(sn, data, retryCount, MqttGatewayPublish.DEFAULT_RETRY_TIMEOUT);
    }

    /**
     * 재시도 횟수와 타임아웃을 지정하여 프로퍼티 설정 요청을 발행합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param data 설정할 프로퍼티 데이터
     * @param retryCount 재시도 횟수
     * @param timeout 타임아웃 (밀리초)
     * @return 프로퍼티 설정 응답 결과
     */
    public PropertySetReplyResultEnum publish(String sn, Object data, int retryCount, long timeout) {
        // 프로퍼티 설정 토픽 생성
        String topic = TopicConst.THING_MODEL_PRE + TopicConst.PRODUCT + Objects.requireNonNull(sn) + TopicConst.PROPERTY_SUF + TopicConst.SET_SUF;
        // MQTT로 프로퍼티 설정 요청 발행
        return gatewayPublish.publishWithReply(
                PropertySetReplyResultEnum.class, topic, new TopicPropertySetRequest<>()
                        .setTid(UUID.randomUUID().toString())
                        .setBid(null)
                        .setTimestamp(System.currentTimeMillis())
                        .setData(Objects.requireNonNull(data)), retryCount, timeout).getData();
    }

}
