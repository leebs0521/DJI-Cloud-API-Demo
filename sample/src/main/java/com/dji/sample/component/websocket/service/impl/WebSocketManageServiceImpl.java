package com.dji.sample.component.websocket.service.impl;

import com.dji.sample.component.redis.RedisConst;
import com.dji.sample.component.redis.RedisOpsUtils;
import com.dji.sample.component.websocket.config.MyConcurrentWebSocketSession;
import com.dji.sample.component.websocket.service.IWebSocketManageService;
import com.dji.sample.manage.model.enums.UserTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * WebSocket 관리 서비스 구현 클래스
 * 
 * WebSocket 세션의 생명주기를 관리하는 서비스 구현체입니다.
 * Redis를 사용하여 세션 정보를 저장하고 조회합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/4/25
 */
@Slf4j
@Service
public class WebSocketManageServiceImpl implements IWebSocketManageService {

    /** WebSocket 세션 저장소 */
    private static final ConcurrentHashMap<String, MyConcurrentWebSocketSession> SESSIONS = new ConcurrentHashMap<>(16);

    @Override
    public void put(String key, MyConcurrentWebSocketSession val) {
        String[] name = key.split("/");
        if (name.length != 3) {
            log.debug("The key is out of format. [{workspaceId}/{userType}/{userId}]");
            return;
        }
        String sessionId = val.getId();
        // 워크스페이스별 세션 정보 저장
        String workspaceKey = RedisConst.WEBSOCKET_PREFIX + name[0];
        // 사용자 타입별 세션 정보 저장
        String userTypeKey = RedisConst.WEBSOCKET_PREFIX + UserTypeEnum.find(Integer.parseInt(name[1])).getDesc();
        RedisOpsUtils.hashSet(workspaceKey, sessionId, name[2]);
        RedisOpsUtils.hashSet(userTypeKey, sessionId, name[2]);
        SESSIONS.put(sessionId, val);
        // Redis 키 만료 시간 설정
        RedisOpsUtils.expireKey(workspaceKey, RedisConst.WEBSOCKET_ALIVE_SECOND);
        RedisOpsUtils.expireKey(userTypeKey, RedisConst.WEBSOCKET_ALIVE_SECOND);
    }

    @Override
    public void remove(String key, String sessionId) {
        String[] name = key.split("/");
        if (name.length != 3) {
            log.debug("The key is out of format. [{workspaceId}/{userType}/{userId}]");
            return;
        }
        // Redis에서 세션 정보 제거
        RedisOpsUtils.hashDel(RedisConst.WEBSOCKET_PREFIX + name[0], new String[] {sessionId});
        RedisOpsUtils.hashDel(RedisConst.WEBSOCKET_PREFIX + UserTypeEnum.find(Integer.parseInt(name[1])).getDesc(), new String[] {sessionId});
        SESSIONS.remove(sessionId);
    }

    @Override
    public Collection<MyConcurrentWebSocketSession> getValueWithWorkspace(String workspaceId) {
        if (!StringUtils.hasText(workspaceId)) {
            return Collections.emptySet();
        }
        String key = RedisConst.WEBSOCKET_PREFIX + workspaceId;

        // 워크스페이스별 세션 ID들을 조회하여 실제 세션 객체 반환
        return RedisOpsUtils.hashKeys(key)
                .stream()
                .map(SESSIONS::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<MyConcurrentWebSocketSession> getValueWithWorkspaceAndUserType(String workspaceId, Integer userType) {
        String key = RedisConst.WEBSOCKET_PREFIX + UserTypeEnum.find(userType).getDesc();
        // 사용자 타입별 세션 ID들을 조회하여 워크스페이스에 속한 세션만 필터링
        return RedisOpsUtils.hashKeys(key)
                .stream()
                .map(SESSIONS::get)
                .filter(getValueWithWorkspace(workspaceId)::contains)
                .collect(Collectors.toSet());
    }

    @Override
    public Long getConnectedCount() {
        return SESSIONS.mappingCount();
    }
}
