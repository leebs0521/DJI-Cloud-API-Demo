package com.dji.sdk.mqtt.drc;

import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.mqtt.IMqttTopicService;
import com.dji.sdk.mqtt.TopicConst;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * DRC 업로드 구독 클래스
 * 
 * 이 클래스는 DRC(Direct Remote Control) 업로드 토픽을
 * 구독하는 기능을 제공합니다.
 * 
 * 주요 기능:
 * - DRC 업로드 토픽 구독
 * - 게이트웨이별 토픽 생성
 * - 동적 토픽 패턴 지원
 * 
 * 이 클래스는 게이트웨이의 시리얼 번호를 사용하여
 * 해당 게이트웨이의 DRC 업로드 토픽을 구독합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/10
 * @version 0.1
 */
@Component
public class DrcUpSubscribe {

    /**
     * MQTT 토픽 서비스
     * 
     * 토픽 구독을 처리하는 서비스입니다.
     */
    @Resource
    private IMqttTopicService topicService;

    /**
     * DRC 업로드 토픽을 구독합니다.
     * 
     * 지정된 게이트웨이의 DRC 업로드 토픽을 구독합니다.
     * 토픽 패턴: thing/product/{gatewaySn}/drc/up
     * 
     * @param gateway 구독할 게이트웨이
     */
    public void subscribe(GatewayManager gateway) {
        String drc = TopicConst.THING_MODEL_PRE + TopicConst.PRODUCT + "%s" + TopicConst.DRC + TopicConst.UP;
        topicService.subscribe(String.format(drc, gateway.getGatewaySn()));
    }
}
