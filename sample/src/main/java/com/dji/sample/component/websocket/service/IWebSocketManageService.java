package com.dji.sample.component.websocket.service;

import com.dji.sample.component.websocket.config.MyConcurrentWebSocketSession;

import java.util.Collection;

/**
 * WebSocket 관리 서비스 인터페이스
 * 
 * WebSocket 세션의 생명주기를 관리하는 서비스 인터페이스입니다.
 * 세션 등록, 제거, 조회 등의 기능을 제공합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/4/25
 */
public interface IWebSocketManageService {

    /**
     * WebSocket 세션을 등록합니다.
     * 
     * @param key 세션 키 (형식: {workspaceId}/{userType}/{userId})
     * @param val WebSocket 세션
     */
    void put(String key, MyConcurrentWebSocketSession val);

    /**
     * WebSocket 세션을 제거합니다.
     * 
     * @param key 세션 키 (형식: {workspaceId}/{userType}/{userId})
     * @param sessionId 세션 ID
     */
    void remove(String key, String sessionId);

    /**
     * 워크스페이스 ID로 WebSocket 세션들을 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @return WebSocket 세션 컬렉션
     */
    Collection<MyConcurrentWebSocketSession> getValueWithWorkspace(String workspaceId);

    /**
     * 워크스페이스 ID와 사용자 타입으로 WebSocket 세션들을 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param userType 사용자 타입
     * @return WebSocket 세션 컬렉션
     */
    Collection<MyConcurrentWebSocketSession> getValueWithWorkspaceAndUserType(String workspaceId, Integer userType);

    /**
     * 현재 연결된 WebSocket 세션 수를 반환합니다.
     * 
     * @return 연결된 세션 수
     */
    Long getConnectedCount();
}
