package com.dji.sample.component;

import com.dji.sample.common.error.CommonErrorEnum;
import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.common.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * 인증 인터셉터 클래스
 * 
 * HTTP 요청에 대한 JWT 토큰 인증을 처리하는 인터셉터입니다.
 * 모든 요청에 대해 토큰의 유효성을 검증하고, 검증된 사용자 정보를 요청 속성에 저장합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/16
 * @version 0.1
 */
@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    /** 토큰 파라미터 이름 */
    public static final String PARAM_TOKEN = "x-auth-token";

    /** 토큰 클레임 요청 속성 이름 */
    public static final String TOKEN_CLAIM = "customClaim";

    /**
     * 요청 처리 전에 실행되는 메서드입니다.
     * 
     * JWT 토큰을 검증하고 유효한 경우 사용자 정보를 요청 속성에 저장합니다.
     * 
     * @param request HTTP 요청 객체
     * @param response HTTP 응답 객체
     * @param handler 요청 핸들러
     * @return 요청 처리 계속 여부
     * @throws Exception 인증 처리 중 오류 발생 시
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        log.debug("request uri: {}, IP: {}", uri, request.getRemoteAddr());
        
        // OPTIONS 메서드는 직접 통과 (CORS preflight 요청)
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            response.setStatus(HttpStatus.OK.value());
            return false;
        }
        
        // 요청 헤더에서 토큰 추출
        String token = request.getHeader(PARAM_TOKEN);
        
        // 토큰 존재 여부 확인
        if (!StringUtils.hasText(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            log.error(CommonErrorEnum.NO_TOKEN.getMessage());
            return false;
        }

        // 현재 토큰의 유효성 검증
        Optional<CustomClaim> customClaimOpt = JwtUtil.parseToken(token);
        if (customClaimOpt.isEmpty()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        // 토큰에서 추출한 커스텀 데이터를 요청 속성에 저장
        request.setAttribute(TOKEN_CLAIM, customClaimOpt.get());
        return true;
    }

    /**
     * 요청 처리 후에 실행되는 메서드입니다.
     * 
     * 요청이 끝난 후 요청 속성에 저장된 커스텀 데이터를 삭제합니다.
     * 
     * @param request HTTP 요청 객체
     * @param response HTTP 응답 객체
     * @param handler 요청 핸들러
     * @param modelAndView 모델 앤 뷰 (사용되지 않음)
     * @throws Exception 후처리 중 오류 발생 시
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 요청 종료 후 요청 속성의 커스텀 데이터 삭제
        request.removeAttribute(TOKEN_CLAIM);
    }
}
