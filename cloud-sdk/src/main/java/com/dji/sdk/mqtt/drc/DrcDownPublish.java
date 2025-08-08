package com.dji.sdk.mqtt.drc;

import com.dji.sdk.mqtt.MqttGatewayPublish;
import com.dji.sdk.mqtt.TopicConst;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * DRC 다운로드 발행 클래스
 * 
 * 이 클래스는 DRC(Direct Remote Control) 다운로드 메시지를
 * 발행하는 기능을 제공합니다.
 * 
 * 주요 기능:
 * - DRC 다운로드 메시지 발행
 * - 반복 발행 지원
 * - 데이터 포함/미포함 메시지 지원
 * - 동적 토픽 생성
 * 
 * 이 클래스는 게이트웨이의 시리얼 번호를 사용하여
 * 해당 게이트웨이의 DRC 다운로드 토픽으로 메시지를 발행합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/24
 */
@Component
public class DrcDownPublish {

    /**
     * MQTT 게이트웨이 발행 서비스
     * 
     * MQTT 메시지 발행을 처리하는 서비스입니다.
     */
    @Resource
    private MqttGatewayPublish gatewayPublish;

    /**
     * 기본 발행 횟수
     * 
     * 메시지 발행 시 기본적으로 사용되는 반복 횟수입니다.
     */
    public static final int DEFAULT_PUBLISH_COUNT = 5;

    /**
     * DRC 다운로드 메시지를 발행합니다.
     * 
     * 데이터 없이 메시지를 발행합니다.
     * 
     * @param sn 게이트웨이 시리얼 번호
     * @param method 메서드 이름
     */
    public void publish(String sn, String method) {
        this.publish(sn, method, null);
    }

    /**
     * DRC 다운로드 메시지를 발행합니다.
     * 
     * 데이터와 함께 메시지를 발행합니다.
     * 
     * @param sn 게이트웨이 시리얼 번호
     * @param method 메서드 이름
     * @param data 발행할 데이터
     */
    public void publish(String sn, String method, Object data) {
        this.publish(sn, method, data, DEFAULT_PUBLISH_COUNT);
    }

    /**
     * DRC 다운로드 메시지를 발행합니다.
     * 
     * 데이터와 함께 지정된 횟수만큼 메시지를 발행합니다.
     * 
     * @param sn 게이트웨이 시리얼 번호
     * @param method 메서드 이름
     * @param data 발행할 데이터
     * @param publishCount 발행 횟수
     */
    public void publish(String sn, String method, Object data, int publishCount) {
        String topic = TopicConst.THING_MODEL_PRE + TopicConst.PRODUCT + Objects.requireNonNull(sn) + TopicConst.DRC + TopicConst.DOWN;
        gatewayPublish.publish(topic,
                new TopicDrcRequest<>()
                        .setMethod(method)
                        .setData(Objects.requireNonNullElse(data, "")),
                publishCount);
    }

}
