package com.dji.sample.component;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.dji.sample.component.AuthInterceptor.PARAM_TOKEN;

/**
 * CORS 필터 클래스
 * 
 * Cross-Origin Resource Sharing (CORS) 정책을 처리하는 필터입니다.
 * 웹 브라우저의 동일 출처 정책을 우회하여 다른 도메인에서의 API 접근을 허용합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/22
 */
@Component
public class CorsFilter implements Filter {

    /**
     * CORS 헤더를 설정하고 요청을 처리하는 필터 메서드입니다.
     * 
     * 모든 HTTP 응답에 CORS 관련 헤더를 추가하여
     * 클라이언트의 크로스 오리진 요청을 허용합니다.
     * 
     * @param request 서블릿 요청 객체
     * @param response 서블릿 응답 객체
     * @param filterChain 필터 체인
     * @throws IOException 입출력 오류 발생 시
     * @throws ServletException 서블릿 오류 발생 시
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        
        // CORS 자격 증명 허용 (쿠키, 인증 헤더 등)
        res.addHeader("Access-Control-Allow-Credentials", "true");
        
        // 모든 출처에서의 접근 허용
        res.addHeader("Access-Control-Allow-Origin", "*");
        
        // 허용할 HTTP 메서드 설정
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        
        // 허용할 HTTP 헤더 설정
        res.addHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers," +
                "Authorization, Content-Length, X-CSRF-Token, Token,session,X_Requested_With,Accept, "+
                        "Origin, Host, Connection, Accept-Encoding, Accept-Language,DNT, X-CustomHeader, Keep-Alive," +
                        " User-Agent, X-Requested-With, If-Modified-Since, Cache-Control, Content-Type, Pragma," + PARAM_TOKEN);
        
        // OPTIONS 메서드 (preflight 요청) 처리
        if (((HttpServletRequest) request).getMethod().equals("OPTIONS")) {
            return;
        }
        
        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }
}