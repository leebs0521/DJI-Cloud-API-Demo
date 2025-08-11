package com.dji.sdk.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SpringDocConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger API 문서 설정 클래스
 * CloudSDK API 문서화를 위한 OpenAPI 3.0 설정을 제공
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/14
 */
@Configuration
@OpenAPIDefinition(security = {@SecurityRequirement(name = "default")})
public class SwaggerConfig {

    /**
     * OpenAPI 설정을 생성합니다.
     * API 문서의 기본 정보와 라이선스를 설정합니다.
     * 
     * @return OpenAPI 설정 객체
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("CloudSDK API").description("All HTTP interfaces encapsulated by CloudSDK.")
                        .license(new License().name("LICENSE").url("https://github.com/dji-sdk/DJI-Cloud-API-Demo/blob/main/LICENSE"))
                        .version("1.0.0")).components(components());
    }

    /**
     * 보안 스키마를 생성합니다.
     * API 키 인증 방식을 설정합니다.
     * 
     * @return SecurityScheme 설정 객체
     */
    @Bean
    public SecurityScheme securityScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name("x-auth-token");
    }

    /**
     * OpenAPI 컴포넌트를 생성합니다.
     * 보안 스키마를 컴포넌트에 추가합니다.
     * 
     * @return Components 설정 객체
     */
    @Bean
    public Components components() {
        return new Components()
                .addSecuritySchemes("default", securityScheme());
    }

    /**
     * CloudSDK API 그룹을 생성합니다.
     * com.dji 패키지 하위의 모든 API를 스캔합니다.
     * 
     * @return GroupedOpenApi 설정 객체
     */
    @Bean
    public GroupedOpenApi sdkOpenApi() {
        return GroupedOpenApi.builder().group("CloudSDK")
                .packagesToScan("com.dji").build();
    }

    /**
     * SpringDoc 설정 속성을 구성합니다.
     * 기본 파라미터 객체 플랫화 비활성화, 폼 데이터 지원 활성화, 기본 응답 타입을 JSON으로 설정합니다.
     * 
     * @param properties SpringDoc 설정 속성
     * @return 구성된 SpringDocConfigProperties 객체
     */
    @Bean
    public SpringDocConfigProperties springDocConfigProperties(SpringDocConfigProperties properties) {
        // 기본 파라미터 객체 플랫화 비활성화
        properties.setDefaultFlatParamObject(false);
        // 폼 데이터 지원 활성화
        properties.setDefaultSupportFormData(true);
        // 기본 응답 미디어 타입을 JSON으로 설정
        properties.setDefaultProducesMediaType("application/json");
        return properties;
    }
}
