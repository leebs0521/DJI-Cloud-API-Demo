package com.dji.sdk.mqtt.osd;

import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.common.SDKManager;
import com.dji.sdk.mqtt.IMqttTopicService;
import com.dji.sdk.mqtt.TopicConst;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * MQTT OSD 구독 관리 클래스
 * 게이트웨이와 드론의 OSD 토픽을 구독/구독 해제하는 기능을 제공
 * 
 * @author sean.zhou
 * @date 2021/11/10
 * @version 0.1
 */
@Component
public class OsdSubscribe {

    /** OSD 토픽 패턴 */
    public static final String TOPIC = TopicConst.THING_MODEL_PRE + TopicConst.PRODUCT + "%s" + TopicConst.OSD_SUF;

    /** MQTT 토픽 서비스 */
    @Resource
    private IMqttTopicService topicService;

    /**
     * 게이트웨이와 드론의 OSD 토픽을 구독합니다.
     * 
     * @param gateway 게이트웨이 관리자
     * @param unsubscribeSubDevice 서브 디바이스 구독 해제 여부
     */
    public void subscribe(GatewayManager gateway, boolean unsubscribeSubDevice) {
        // 디바이스를 SDK 매니저에 등록
        SDKManager.registerDevice(gateway);
        // 게이트웨이 OSD 토픽 구독
        topicService.subscribe(String.format(TOPIC, gateway.getGatewaySn()));
        if (unsubscribeSubDevice) {
            // 서브 디바이스 구독 해제가 요청된 경우
            topicService.unsubscribe(String.format(TOPIC, gateway.getDroneSn()));
            return;
        }
        // 드론이 존재하는 경우 드론 OSD 토픽도 구독
        if (null != gateway.getDroneSn()) {
            topicService.subscribe(String.format(TOPIC, gateway.getDroneSn()));
        }
    }

    /**
     * 게이트웨이와 드론의 OSD 토픽 구독을 해제합니다.
     * 
     * @param gateway 게이트웨이 관리자
     */
    public void unsubscribe(GatewayManager gateway) {
        // SDK 매니저에서 디바이스 로그아웃
        SDKManager.logoutDevice(gateway.getGatewaySn());
        // 게이트웨이 OSD 토픽 구독 해제
        topicService.unsubscribe(String.format(TOPIC, gateway.getGatewaySn()));
        // 드론이 존재하는 경우 드론 OSD 토픽도 구독 해제
        if (null != gateway.getDroneSn()) {
            topicService.unsubscribe(String.format(TOPIC, gateway.getDroneSn()));
        }
    }
}
