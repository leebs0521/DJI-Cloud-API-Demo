package com.dji.sample.component.websocket.config;

import com.dji.sample.component.websocket.service.IWebSocketManageService;
import com.dji.sdk.websocket.WebSocketDefaultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;

/**
 * 커스텀 WebSocket 팩토리 클래스
 * 
 * WebSocket 핸들러에 추가 기능을 제공하는 데코레이터를 생성합니다.
 * MyWebSocketHandler를 사용하여 WebSocket 연결 관리 기능을 추가합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/16
 * @version 0.1
 */
@Component
@Primary
public class MyWebSocketFactory extends WebSocketDefaultFactory {

    /** WebSocket 관리 서비스 */
    @Autowired
    private IWebSocketManageService webSocketManageService;

    /**
     * WebSocket 핸들러를 데코레이터로 감쌉니다.
     * 
     * @param handler 원본 WebSocket 핸들러
     * @return 데코레이터로 감싸진 WebSocket 핸들러
     */
    @Override
    public WebSocketHandler decorate(WebSocketHandler handler) {
        return new MyWebSocketHandler(handler, webSocketManageService);
    }
}