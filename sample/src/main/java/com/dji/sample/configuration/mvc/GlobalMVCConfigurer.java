package com.dji.sample.configuration.mvc;

import com.dji.sample.component.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 전역 MVC 설정 클래스
 * 
 * Spring MVC의 전역 설정을 관리하는 클래스입니다.
 * 인터셉터 등록, 경로 패턴 설정 등을 통해 애플리케이션의 요청 처리 방식을 구성합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/16
 * @version 0.1
 */
@Configuration
public class GlobalMVCConfigurer implements WebMvcConfigurer {

    /** 인증 인터셉터 */
    @Autowired
    private AuthInterceptor authInterceptor;

    /** 인터셉터에서 제외할 경로 목록 */
    private static List<String> excludePaths = new ArrayList<>();

    /** 관리 API 접두사 */
    @Value("${url.manage.prefix}")
    private String managePrefix;

    /** 관리 API 버전 */
    @Value("${url.manage.version}")
    private String manageVersion;

    /**
     * 인터셉터를 등록하는 메서드입니다.
     * 
     * 인증 인터셉터를 모든 요청에 적용하되, 특정 경로는 제외합니다.
     * 로그인, 토큰 갱신, Swagger 문서 등의 경로는 인증 없이 접근 가능하도록 설정합니다.
     * 
     * @param registry 인터셉터 레지스트리
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 로그인 인터페이스 제외
        excludePaths.add("/" + managePrefix + manageVersion + "/login");
        
        // 토큰 갱신 인터페이스 제외
        excludePaths.add("/" + managePrefix + manageVersion + "/token/refresh");
        
        // Swagger UI 관련 경로 제외
        excludePaths.add("/swagger-ui.html");
        excludePaths.add("/swagger-ui/**");
        
        // OpenAPI 3.0 문서 경로 제외
        excludePaths.add("/v3/**");
        
        // UI 관련 경로 제외
        excludePaths.add("/ui/**");
        
        // 모든 요청 인터페이스에 대해 인증 인터셉터 적용
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")  // 모든 경로에 적용
                .excludePathPatterns(excludePaths);  // 제외 경로 설정
    }
}
