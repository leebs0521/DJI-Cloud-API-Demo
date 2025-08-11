package com.dji.sdk.mqtt.status;

import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.common.SDKManager;
import com.dji.sdk.mqtt.IMqttTopicService;
import com.dji.sdk.mqtt.TopicConst;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * MQTT 상태 구독 관리 클래스
 * 게이트웨이의 상태 토픽을 구독/구독 해제하는 기능을 제공
 * 
 * @author sean.zhou
 * @date 2021/11/10
 * @version 0.1
 */
@Component
public class StatusSubscribe {

    /** 상태 토픽 패턴 */
    public static final String TOPIC = TopicConst.BASIC_PRE + TopicConst.PRODUCT + "%s" + TopicConst.STATUS_SUF;

    /** MQTT 토픽 서비스 */
    @Resource
    private IMqttTopicService topicService;

    /**
     * 게이트웨이의 상태 토픽을 구독합니다.
     * 
     * @param gateway 게이트웨이 관리자
     */
    public void subscribe(GatewayManager gateway) {
        // 디바이스를 SDK 매니저에 등록
        SDKManager.registerDevice(gateway);
        // 게이트웨이 상태 토픽 구독
        topicService.subscribe(String.format(TOPIC, gateway.getGatewaySn()));
    }

    /**
     * 와일드카드를 사용하여 모든 상태 토픽을 구독합니다.
     * 모든 디바이스의 상태를 수신할 수 있습니다.
     */
    public void subscribeWildcardsStatus() {
        topicService.subscribe(String.format(TOPIC, "+"));
    }

    /**
     * 게이트웨이의 상태 토픽 구독을 해제합니다.
     * 
     * @param gateway 게이트웨이 관리자
     */
    public void unsubscribe(GatewayManager gateway) {
        // SDK 매니저에서 디바이스 로그아웃
        SDKManager.logoutDevice(gateway.getGatewaySn());
        // 게이트웨이 상태 토픽 구독 해제
        topicService.unsubscribe(String.format(TOPIC, gateway.getGatewaySn()));
    }

}
