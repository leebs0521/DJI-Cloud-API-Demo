package com.dji.sample.component.websocket.config;

import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.common.util.JwtUtil;
import com.dji.sample.component.AuthInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;

/**
 * WebSocket 인증 주체 핸들러 클래스
 * 
 * WebSocket 연결 시 JWT 토큰을 검증하고 사용자 정보를 설정하는 핸들러입니다.
 * DefaultHandshakeHandler를 확장하여 커스텀 인증 로직을 구현합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/16
 * @version 0.1
 */
@Slf4j
@Component
public class AuthPrincipalHandler extends DefaultHandshakeHandler {

    /**
     * WebSocket 연결의 오리진 유효성을 검증합니다.
     * 
     * JWT 토큰을 파라미터에서 추출하고 유효성을 검증합니다.
     * 유효한 토큰인 경우 CustomClaim을 요청 속성에 저장합니다.
     * 
     * @param request WebSocket 연결 요청
     * @return 오리진 유효성 여부
     */
    @Override
    protected boolean isValidOrigin(ServerHttpRequest request) {

        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
            // 토큰 파라미터에서 JWT 토큰 추출
            String token = servletRequest.getParameter(AuthInterceptor.PARAM_TOKEN);

            if (!StringUtils.hasText(token)) {
                return false;
            }
            log.debug("token:" + token);
            // JWT 토큰 파싱 및 검증
            Optional<CustomClaim> customClaim = JwtUtil.parseToken(token);
            if (customClaim.isEmpty()) {
                return false;
            }

            // 검증된 클레임을 요청 속성에 저장
            servletRequest.setAttribute(AuthInterceptor.TOKEN_CLAIM, customClaim.get());
            return true;
        }
        return false;

    }

    /**
     * WebSocket 연결의 사용자 주체를 결정합니다.
     * 
     * 주체 이름 형식: {workspaceId}/{userType}/{userId}
     * 
     * @param request WebSocket 연결 요청
     * @param wsHandler WebSocket 핸들러
     * @param attributes 연결 속성
     * @return 사용자 주체
     */
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        if (request instanceof ServletServerHttpRequest) {

            // 저장된 커스텀 클레임 가져오기
            CustomClaim claim = (CustomClaim) ((ServletServerHttpRequest) request).getServletRequest()
                    .getAttribute(AuthInterceptor.TOKEN_CLAIM);

            // 주체 이름을 {workspaceId}/{userType}/{userId} 형식으로 반환
            return () -> claim.getWorkspaceId() + "/" + claim.getUserType() + "/" + claim.getId();
        }
        return () -> null;
    }
}