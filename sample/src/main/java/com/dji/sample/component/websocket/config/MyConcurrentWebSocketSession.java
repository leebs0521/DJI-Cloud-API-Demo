package com.dji.sample.component.websocket.config;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.ConcurrentWebSocketSessionDecorator;

/**
 * 커스텀 동시성 WebSocket 세션 클래스
 * 
 * WebSocket 세션에 동시성 제어 기능을 추가하는 데코레이터입니다.
 * 메시지 전송 시간 제한과 버퍼 크기 제한을 설정하여 안정적인 WebSocket 통신을 보장합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/24
 */
public class MyConcurrentWebSocketSession extends ConcurrentWebSocketSessionDecorator {

    /** 전송 버퍼 크기 제한 (1MB) */
    private static final int SEND_BUFFER_SIZE_LIMIT = 1024 * 1024;

    /** 전송 시간 제한 (1초) */
    private static final int SEND_TIME_LIMIT = 1000;

    /**
     * 커스텀 동시성 WebSocket 세션 생성자
     * 
     * @param delegate 원본 WebSocket 세션
     * @param sendTimeLimit 전송 시간 제한 (밀리초)
     * @param bufferSizeLimit 전송 버퍼 크기 제한 (바이트)
     */
    private MyConcurrentWebSocketSession(WebSocketSession delegate, int sendTimeLimit, int bufferSizeLimit) {
        super(delegate, sendTimeLimit, bufferSizeLimit);
    }

    /**
     * 기본 설정으로 커스텀 동시성 WebSocket 세션을 생성합니다.
     * 
     * @param delegate 원본 WebSocket 세션
     */
    MyConcurrentWebSocketSession(WebSocketSession delegate) {
        this(delegate, SEND_TIME_LIMIT, SEND_BUFFER_SIZE_LIMIT);
    }

}