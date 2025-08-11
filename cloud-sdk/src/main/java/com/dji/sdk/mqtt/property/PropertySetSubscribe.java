package com.dji.sdk.mqtt.property;

import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.mqtt.IMqttTopicService;
import com.dji.sdk.mqtt.TopicConst;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * MQTT 프로퍼티 설정 구독 관리 클래스
 * 프로퍼티 설정 응답 토픽을 구독/구독 해제하는 기능을 제공
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/24
 */
@Component
public class PropertySetSubscribe {

    /** 프로퍼티 설정 응답 토픽 패턴 */
    public static final String TOPIC = TopicConst.THING_MODEL_PRE + TopicConst.PRODUCT + "%s" + TopicConst.PROPERTY_SUF + TopicConst.SET_SUF + TopicConst._REPLY_SUF;

    /** MQTT 토픽 서비스 */
    @Resource
    private IMqttTopicService topicService;

    /**
     * 게이트웨이의 프로퍼티 설정 응답 토픽을 구독합니다.
     * 
     * @param gateway 게이트웨이 관리자
     */
    public void subscribe(GatewayManager gateway) {
        topicService.subscribe(String.format(TOPIC, gateway.getGatewaySn()));
    }

    /**
     * 게이트웨이의 프로퍼티 설정 응답 토픽 구독을 해제합니다.
     * 
     * @param gateway 게이트웨이 관리자
     */
    public void unsubscribe(GatewayManager gateway) {
        topicService.unsubscribe(String.format(TOPIC, gateway.getGatewaySn()));
    }
}
