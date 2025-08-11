package com.dji.sample.component.websocket.config;

import com.dji.sample.component.websocket.service.IWebSocketManageService;
import com.dji.sdk.websocket.WebSocketDefaultHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.security.Principal;

/**
 * 커스텀 WebSocket 핸들러 클래스
 * 
 * WebSocket 연결의 생명주기 이벤트를 처리하는 커스텀 핸들러입니다.
 * 연결 생성, 연결 종료, 메시지 수신 등의 이벤트를 처리하고 WebSocket 세션을 관리합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/16
 * @version 0.1
 */
@Slf4j
public class MyWebSocketHandler extends WebSocketDefaultHandler {

    /** WebSocket 관리 서비스 */
    private IWebSocketManageService webSocketManageService;

    /**
     * 커스텀 WebSocket 핸들러 생성자
     * 
     * @param delegate 원본 WebSocket 핸들러
     * @param webSocketManageService WebSocket 관리 서비스
     */
    MyWebSocketHandler(WebSocketHandler delegate, IWebSocketManageService webSocketManageService) {
        super(delegate);
        this.webSocketManageService = webSocketManageService;
    }

    /**
     * WebSocket 연결이 성립된 후 호출됩니다.
     * 
     * 사용자 주체 정보를 확인하고 WebSocket 세션을 관리 서비스에 등록합니다.
     * 유효하지 않은 주체인 경우 연결을 종료합니다.
     * 
     * @param session WebSocket 세션
     * @throws Exception 연결 처리 중 오류 발생 시
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Principal principal = session.getPrincipal();
        if (StringUtils.hasText(principal.getName())) {
            // WebSocket 세션을 관리 서비스에 등록
            webSocketManageService.put(principal.getName(), new MyConcurrentWebSocketSession(session));
            log.debug("{} is connected. ID: {}. WebSocketSession[current count: {}]",
                    principal.getName(), session.getId(), webSocketManageService.getConnectedCount());
            return;
        }
        // 유효하지 않은 주체인 경우 연결 종료
        session.close();
    }

    /**
     * WebSocket 연결이 종료된 후 호출됩니다.
     * 
     * WebSocket 세션을 관리 서비스에서 제거합니다.
     * 
     * @param session WebSocket 세션
     * @param closeStatus 연결 종료 상태
     * @throws Exception 연결 종료 처리 중 오류 발생 시
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        Principal principal = session.getPrincipal();
        if (StringUtils.hasText(principal.getName())) {
            // WebSocket 세션을 관리 서비스에서 제거
            webSocketManageService.remove(principal.getName(), session.getId());
            log.debug("{} is disconnected. ID: {}. WebSocketSession[current count: {}]",
                    principal.getName(), session.getId(), webSocketManageService.getConnectedCount());
        }

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
        log.debug("received message: {}", message.getPayload());
    }

}