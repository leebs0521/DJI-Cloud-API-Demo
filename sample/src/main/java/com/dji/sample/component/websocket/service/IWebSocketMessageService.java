package com.dji.sample.component.websocket.service;

import com.dji.sample.component.websocket.config.MyConcurrentWebSocketSession;
import com.dji.sdk.websocket.WebSocketMessageResponse;

import java.util.Collection;

/**
 * WebSocket 메시지 서비스 인터페이스
 * 
 * WebSocket을 통해 메시지를 전송하는 서비스 인터페이스입니다.
 * 단일 세션과 다중 세션에 대한 메시지 전송 기능을 제공합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/24
 * @version 0.1
 */
public interface IWebSocketMessageService {

    /**
     * 특정 연결에 메시지를 전송합니다.
     * 
     * @param session WebSocket 연결 객체
     * @param message 전송할 메시지
     */
    void sendMessage(MyConcurrentWebSocketSession session, WebSocketMessageResponse message);

    /**
     * 여러 연결에 동일한 메시지를 전송합니다.
     * 
     * @param sessions WebSocket 연결 객체 컬렉션
     * @param message 전송할 메시지
     */
    void sendBatch(Collection<MyConcurrentWebSocketSession> sessions, WebSocketMessageResponse message);

    /**
     * 워크스페이스 ID와 사용자 타입으로 메시지를 일괄 전송합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param userType 사용자 타입
     * @param bizCode 비즈니스 코드
     * @param data 전송할 데이터
     */
    void sendBatch(String workspaceId, Integer userType, String bizCode, Object data);

    /**
     * 워크스페이스 ID로 메시지를 일괄 전송합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param bizCode 비즈니스 코드
     * @param data 전송할 데이터
     */
    void sendBatch(String workspaceId, String bizCode, Object data);
}
