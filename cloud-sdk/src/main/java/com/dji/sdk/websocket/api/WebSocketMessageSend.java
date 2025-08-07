package com.dji.sdk.websocket.api;

import com.dji.sdk.common.Common;
import com.dji.sdk.exception.CloudSDKErrorEnum;
import com.dji.sdk.exception.CloudSDKException;
import com.dji.sdk.websocket.ConcurrentWebSocketSession;
import com.dji.sdk.websocket.WebSocketMessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.Collection;

/**
 * WebSocket 메시지 전송 클래스
 * 
 * 이 클래스는 WebSocket을 통해 메시지를 전송하는 기능을 제공합니다.
 * 단일 세션과 다중 세션에 대한 메시지 전송을 지원하며,
 * 세션 상태 검증과 예외 처리를 포함합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/24
 */
public class WebSocketMessageSend {

    /**
     * 로깅을 위한 Logger 인스턴스
     */
    private static final Logger log = LoggerFactory.getLogger(WebSocketMessageSend.class);

    /**
     * 단일 WebSocket 세션에 메시지를 전송합니다.
     * 
     * 세션이 null이거나 닫혀있는 경우 전송하지 않고 로그를 남깁니다.
     * 메시지 전송 중 IOException이 발생하면 CloudSDKException을 발생시킵니다.
     * 
     * @param session 메시지를 전송할 WebSocket 세션
     * @param message 전송할 WebSocket 메시지
     */
    public void sendMessage(ConcurrentWebSocketSession session, WebSocketMessageResponse message) {
        if (session == null) {
            return;
        }

        try {
            if (!session.isOpen()) {
                session.close();
                log.info("This session is closed.");
                return;
            }

            session.sendMessage(new TextMessage(Common.getObjectMapper().writeValueAsBytes(message)));
        } catch (IOException e) {
            throw new CloudSDKException(CloudSDKErrorEnum.WEBSOCKET_PUBLISH_ABNORMAL, e.getLocalizedMessage());
        }
    }

    /**
     * 여러 WebSocket 세션에 동일한 메시지를 일괄 전송합니다.
     * 
     * 세션 컬렉션이 비어있는 경우 전송하지 않습니다.
     * 각 세션의 상태를 검증하고, 닫혀있는 세션은 종료합니다.
     * 메시지 전송 중 IOException이 발생하면 CloudSDKException을 발생시킵니다.
     * 
     * @param sessions 메시지를 전송할 WebSocket 세션 컬렉션
     * @param message 전송할 WebSocket 메시지
     */
    public void sendBatch(Collection<ConcurrentWebSocketSession> sessions, WebSocketMessageResponse message) {
        if (sessions.isEmpty()) {
            return;
        }

        try {

            TextMessage data = new TextMessage(Common.getObjectMapper().writeValueAsBytes(message));

            for (ConcurrentWebSocketSession session : sessions) {
                if (!session.isOpen()) {
                    session.close();
                    log.info("This session is closed.");
                    return;
                }
                session.sendMessage(data);
            }

        } catch (IOException e) {
            throw new CloudSDKException(CloudSDKErrorEnum.WEBSOCKET_PUBLISH_ABNORMAL, e.getLocalizedMessage());
        }
    }
}