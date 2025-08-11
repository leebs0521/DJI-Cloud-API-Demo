package com.dji.sample.component.websocket.service.impl;

import com.dji.sample.component.websocket.config.MyConcurrentWebSocketSession;
import com.dji.sample.component.websocket.service.IWebSocketManageService;
import com.dji.sample.component.websocket.service.IWebSocketMessageService;
import com.dji.sdk.websocket.WebSocketMessageResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

/**
 * WebSocket 메시지 서비스 구현 클래스
 * 
 * WebSocket을 통해 메시지를 전송하는 서비스 구현체입니다.
 * JSON 직렬화를 사용하여 메시지를 전송하고 세션 상태를 검증합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/24
 */
@Service
@Slf4j
public class WebSocketMessageServiceImpl implements IWebSocketMessageService {

    /** JSON 직렬화를 위한 ObjectMapper */
    @Autowired
    private ObjectMapper mapper;

    /** WebSocket 관리 서비스 */
    @Autowired
    private IWebSocketManageService webSocketManageService;

    @Override
    public void sendMessage(MyConcurrentWebSocketSession session, WebSocketMessageResponse message) {
        if (session == null) {
            return;
        }

        try {
            // 세션이 열려있는지 확인
            if (!session.isOpen()) {
                session.close();
                log.debug("This session is closed.");
                return;
            }

            // 메시지를 JSON으로 직렬화하여 전송
            session.sendMessage(new TextMessage(mapper.writeValueAsBytes(message)));
        } catch (IOException e) {
            log.info("Failed to publish the message. {}", message.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void sendBatch(Collection<MyConcurrentWebSocketSession> sessions, WebSocketMessageResponse message) {
        if (sessions.isEmpty()) {
            return;
        }

        try {
            // 메시지를 JSON으로 직렬화
            TextMessage data = new TextMessage(mapper.writeValueAsBytes(message));

            for (MyConcurrentWebSocketSession session : sessions) {
                // 각 세션이 열려있는지 확인
                if (!session.isOpen()) {
                    session.close();
                    log.debug("This session is closed.");
                    return;
                }
                session.sendMessage(data);
            }

        } catch (IOException e) {
            log.info("Failed to publish the message. {}", message.toString());

            e.printStackTrace();
        }
    }

    @Override
    public void sendBatch(String workspaceId, Integer userType, String bizCode, Object data) {
        if (!StringUtils.hasText(workspaceId)) {
            throw new RuntimeException("Workspace ID does not exist.");
        }
        // 워크스페이스와 사용자 타입에 따른 세션 조회
        Collection<MyConcurrentWebSocketSession> sessions = Objects.isNull(userType) ?
                webSocketManageService.getValueWithWorkspace(workspaceId) :
                webSocketManageService.getValueWithWorkspaceAndUserType(workspaceId, userType);

        // WebSocket 메시지 응답 객체 생성 및 일괄 전송
        this.sendBatch(sessions, new WebSocketMessageResponse()
                        .setData(Objects.requireNonNullElse(data, ""))
                        .setTimestamp(System.currentTimeMillis())
                        .setBizCode(bizCode));
    }

    @Override
    public void sendBatch(String workspaceId, String bizCode, Object data) {
        this.sendBatch(workspaceId, null, bizCode, data);
    }
}