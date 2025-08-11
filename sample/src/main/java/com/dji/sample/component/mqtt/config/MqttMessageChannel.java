package com.dji.sample.component.mqtt.config;

import com.dji.sdk.mqtt.ChannelName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.messaging.MessageChannel;

import java.util.concurrent.Executor;

/**
 * 모든 채널의 정의 클래스
 * MQTT 메시지 처리를 위한 다양한 채널들을 정의합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/10
 * @version 0.1
 */
@Configuration
public class MqttMessageChannel {

    /** 스레드 풀 실행자 */
    @Autowired
    private Executor threadPool;

    /**
     * 인바운드 채널을 생성합니다.
     * 스레드 풀을 사용하여 비동기 메시지 처리를 지원합니다.
     * 
     * @return 인바운드 메시지 채널
     */
    @Bean(name = ChannelName.INBOUND)
    public MessageChannel inboundChannel() {
        return new ExecutorChannel(threadPool);
    }

    /**
     * 기본 채널을 생성합니다.
     * 동기 메시지 처리를 위한 직접 채널입니다.
     * 
     * @return 기본 메시지 채널
     */
    @Bean(name = ChannelName.DEFAULT)
    public MessageChannel defaultChannel() {
        return new DirectChannel();
    }

    /**
     * 상태 채널을 생성합니다.
     * 디바이스 상태 정보를 처리하는 채널입니다.
     * 
     * @return 상태 메시지 채널
     */
    @Bean(name = ChannelName.INBOUND_STATUS)
    public MessageChannel statusChannel() {
        return new DirectChannel();
    }

    /**
     * 상태 데이터 채널을 생성합니다.
     * 디바이스 상태 데이터를 처리하는 채널입니다.
     * 
     * @return 상태 데이터 메시지 채널
     */
    @Bean(name = ChannelName.INBOUND_STATE)
    public MessageChannel stateChannel() {
        return new DirectChannel();
    }

    /**
     * 서비스 응답 채널을 생성합니다.
     * 서비스 요청에 대한 응답을 처리하는 채널입니다.
     * 
     * @return 서비스 응답 메시지 채널
     */
    @Bean(name = ChannelName.INBOUND_SERVICES_REPLY)
    public MessageChannel serviceReplyChannel() {
        return new DirectChannel();
    }

    /**
     * OSD 채널을 생성합니다.
     * On-Screen Display 데이터를 처리하는 채널입니다.
     * 스레드 풀을 사용하여 비동기 처리를 지원합니다.
     * 
     * @return OSD 메시지 채널
     */
    @Bean(name = ChannelName.INBOUND_OSD)
    public MessageChannel osdChannel() {
        return new ExecutorChannel(threadPool);
    }

    /**
     * 요청 채널을 생성합니다.
     * 디바이스 요청을 처리하는 채널입니다.
     * 
     * @return 요청 메시지 채널
     */
    @Bean(name = ChannelName.INBOUND_REQUESTS)
    public MessageChannel requestsChannel() {
        return new DirectChannel();
    }

    /**
     * 이벤트 채널을 생성합니다.
     * 디바이스 이벤트를 처리하는 채널입니다.
     * 
     * @return 이벤트 메시지 채널
     */
    @Bean(name = ChannelName.INBOUND_EVENTS)
    public MessageChannel eventsChannel() {
        return new DirectChannel();
    }

    /**
     * 프로퍼티 설정 응답 채널을 생성합니다.
     * 프로퍼티 설정 요청에 대한 응답을 처리하는 채널입니다.
     * 
     * @return 프로퍼티 설정 응답 메시지 채널
     */
    @Bean(name = ChannelName.INBOUND_PROPERTY_SET_REPLY)
    public MessageChannel propertySetReply() {
        return new DirectChannel();
    }

    /**
     * DRC 업로드 채널을 생성합니다.
     * DRC(Direct Remote Control) 업로드 데이터를 처리하는 채널입니다.
     * 
     * @return DRC 업로드 메시지 채널
     */
    @Bean(name = ChannelName.INBOUND_DRC_UP)
    public MessageChannel drcUp() {
        return new DirectChannel();
    }

}
