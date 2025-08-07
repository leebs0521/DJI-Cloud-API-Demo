package com.dji.sdk.mqtt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * MQTT 클라이언트 설정 클래스
 * 
 * 이 클래스는 MQTT 인바운드/아웃바운드 메시지 처리를 위한 Spring Integration 설정을 제공합니다.
 * MQTT 브로커와의 연결, 메시지 변환, 채널 설정 등을 담당합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/10
 * @version 0.1
 */
@Configuration
@IntegrationComponentScan
public class MqttConfiguration {

    /**
     * 로깅을 위한 Logger 인스턴스
     */
    private static final Logger log = LoggerFactory.getLogger(MqttConfiguration.class);

    /**
     * 인바운드 토픽 설정 (application.yml에서 주입)
     * 쉼표로 구분된 여러 토픽을 지원합니다.
     */
    @Value("${cloud-sdk.mqtt.inbound-topic: }")
    private String inboundTopic;

    /**
     * MQTT 클라이언트 팩토리 (Spring Boot 자동 설정)
     */
    @Resource
    private MqttPahoClientFactory mqttClientFactory;

    /**
     * 인바운드 메시지를 처리할 채널
     */
    @Resource(name = ChannelName.INBOUND)
    private MessageChannel inboundChannel;

    /**
     * MQTT 인바운드 메시지 채널 어댑터를 생성합니다.
     * 
     * 이 Bean은 MQTT 브로커로부터 메시지를 수신하고 Spring Integration 채널로 전달합니다.
     * UUID를 클라이언트 ID로 사용하여 고유한 연결을 보장합니다.
     * 
     * @return MqttPahoMessageDrivenChannelAdapter 인스턴스
     */
    @Bean
    public MqttPahoMessageDrivenChannelAdapter mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
                UUID.randomUUID().toString(), mqttClientFactory, inboundTopic.split(","));
        DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
        // use byte types uniformly
        converter.setPayloadAsBytes(true);
        adapter.setConverter(converter);
        adapter.setQos(1);
        adapter.setOutputChannel(inboundChannel);
        return adapter;
    }

    /**
     * MQTT 아웃바운드 메시지 핸들러를 생성합니다.
     * 
     * 이 Bean은 Spring Integration 채널로부터 메시지를 받아 MQTT 브로커로 전송합니다.
     * 비동기 전송을 지원하며 QoS 0을 기본값으로 사용합니다.
     * 
     * @return MqttPahoMessageHandler 인스턴스
     */
    @Bean
    @ServiceActivator(inputChannel = ChannelName.OUTBOUND)
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(
                UUID.randomUUID().toString(), mqttClientFactory);
        DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
        // use byte types uniformly
        converter.setPayloadAsBytes(true);

        messageHandler.setAsync(true);
        messageHandler.setDefaultQos(0);
        messageHandler.setConverter(converter);
        return messageHandler;
    }



    /**
     * 기본 채널 메시지 핸들러를 정의합니다.
     * 
     * 처리되지 않은 메시지나 특별한 처리가 필요 없는 메시지를 로깅하는 용도로 사용됩니다.
     * 
     * @return 기본 메시지 핸들러
     */
    @Bean
    @ServiceActivator(inputChannel = ChannelName.DEFAULT)
    public MessageHandler defaultInboundHandler() {
        return message -> {
            log.info("The default channel does not handle messages." +
                    "\nTopic: " + message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC) +
                    "\nPayload: " + message.getPayload() + "\n");
        };
    }
}
