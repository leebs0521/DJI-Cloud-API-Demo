package com.dji.sdk.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

/**
 * WebSocket 기본 핸들러 데코레이터 팩토리 클래스
 * 
 * 이 클래스는 WebSocket 핸들러에 추가 기능을 제공하는 데코레이터를 생성합니다.
 * WebSocketDefaultHandler를 사용하여 메시지 처리 전후에 추가 로직을 수행할 수 있도록 합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/16
 * @version 0.1
 */
@Component
public class WebSocketDefaultFactory implements WebSocketHandlerDecoratorFactory {

    /**
     * WebSocket 핸들러를 데코레이터로 감쌉니다.
     * 
     * @param handler 원본 WebSocket 핸들러
     * @return 데코레이터로 감싸진 WebSocket 핸들러
     */
    @Override
    public WebSocketHandler decorate(WebSocketHandler handler) {
        return new WebSocketDefaultHandler(handler);
    }
}