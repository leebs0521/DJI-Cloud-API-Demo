package com.dji.sdk.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;
import org.springframework.web.socket.server.HandshakeHandler;

/**
 * WebSocket 설정 클래스
 * 
 * 이 클래스는 WebSocket 메시지 브로커를 활성화하고 STOMP 엔드포인트를 설정합니다.
 * 클라이언트와 서버 간의 실시간 양방향 통신을 위한 WebSocket 연결을 구성합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/17
 * @version 0.1
 */
@EnableWebSocketMessageBroker
@Configuration
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    /** WebSocket 핸드셰이크 핸들러 (선택적) */
    @Autowired(required = false)
    private HandshakeHandler handshakeHandler;

    /** WebSocket 핸들러 데코레이터 팩토리 */
    @Autowired
    private WebSocketHandlerDecoratorFactory webSocketHandlerDecoratorFactory;

    /**
     * STOMP 엔드포인트를 등록합니다.
     * 
     * WebSocket 연결 주소를 설정하고 허용된 오리진 패턴을 지정합니다.
     * 
     * @param registry STOMP 엔드포인트 레지스트리
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket 연결 주소 설정
        registry.addEndpoint("/api/v1/ws").setAllowedOriginPatterns("*")
                .setHandshakeHandler(handshakeHandler);
    }

    /**
     * WebSocket 전송 설정을 구성합니다.
     * 
     * WebSocket 핸들러 데코레이터 팩토리를 추가하여
     * 메시지 처리 전후에 추가 로직을 수행할 수 있도록 합니다.
     * 
     * @param registry WebSocket 전송 레지스트리
     */
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        registry.addDecoratorFactory(webSocketHandlerDecoratorFactory);
    }

}