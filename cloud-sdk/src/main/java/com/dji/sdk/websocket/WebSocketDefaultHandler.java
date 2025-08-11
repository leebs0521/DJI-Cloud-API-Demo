package com.dji.sdk.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

/**
 * WebSocket 기본 핸들러 클래스
 * 
 * 이 클래스는 WebSocket 연결의 생명주기 이벤트를 처리하는 기본 핸들러입니다.
 * 연결 생성, 연결 종료, 메시지 수신 등의 이벤트를 로깅하고 처리합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/16
 * @version 0.1
 */
public class WebSocketDefaultHandler extends WebSocketHandlerDecorator {

    /** 로깅을 위한 Logger 인스턴스 */
    private static final Logger log = LoggerFactory.getLogger(WebSocketDefaultHandler.class);

    /**
     * WebSocket 기본 핸들러 생성자
     * 
     * @param delegate 원본 WebSocket 핸들러
     */
    public WebSocketDefaultHandler(WebSocketHandler delegate) {
        super(delegate);
    }

    /**
     * WebSocket 연결이 성립된 후 호출됩니다.
     * 
     * @param session WebSocket 세션
     * @throws Exception 연결 처리 중 오류 발생 시
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.debug("{} is connected.", session.getId());
    }

    /**
     * WebSocket 연결이 종료된 후 호출됩니다.
     * 
     * @param session WebSocket 세션
     * @param closeStatus 연결 종료 상태
     * @throws Exception 연결 종료 처리 중 오류 발생 시
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.debug("{} is disconnected.", session.getId());
    }

    /**
     * WebSocket 메시지를 수신했을 때 호출됩니다.
     * 
     * @param session WebSocket 세션
     * @param message 수신된 WebSocket 메시지
     * @throws Exception 메시지 처리 중 오류 발생 시
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("received message: {}, from: {}", message.getPayload(), session.getId());
    }

}