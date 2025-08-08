package com.dji.sdk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Cloud SDK MVC 설정 클래스
 * 
 * 이 클래스는 Spring MVC의 설정을 커스터마이징하여
 * Cloud SDK에 필요한 기능들을 추가합니다.
 * 
 * 주요 기능:
 * - GetSnakeArgumentProcessor를 통한 snake_case 파라미터 처리
 * - HTTP 요청의 파라미터를 자동으로 camelCase로 변환
 * 
 * 이 클래스는 Spring Boot 애플리케이션에서
 * 자동으로 설정되어 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/23
 */
@Configuration
public class CloudSDKMvcConfigurer implements WebMvcConfigurer {

    /**
     * 인수 리졸버를 추가합니다.
     * 
     * GetSnakeArgumentProcessor를 등록하여 HTTP 요청의
     * snake_case 파라미터를 camelCase로 자동 변환합니다.
     * 
     * @param resolvers 인수 리졸버 목록
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new GetSnakeArgumentProcessor(true));
    }
}
