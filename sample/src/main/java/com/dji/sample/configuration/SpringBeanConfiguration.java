package com.dji.sample.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Spring Bean 설정 클래스
 * 
 * 애플리케이션에서 사용할 Spring Bean들을 설정하는 클래스입니다.
 * Jackson ObjectMapper의 커스텀 설정을 통해 JSON 직렬화/역직렬화 동작을 구성합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/16
 * @version 0.1
 */
@Configuration
public class SpringBeanConfiguration {

    /**
     * 커스텀 ObjectMapper를 생성합니다.
     * 
     * Jackson ObjectMapper에 애플리케이션에 특화된 설정을 적용합니다.
     * 날짜/시간 처리, 속성 네이밍 전략, null 값 처리 등을 구성합니다.
     * 
     * @param builder Jackson2ObjectMapperBuilder
     * @return 구성된 ObjectMapper 객체
     */
    @Bean
//    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        
        // 스네이크 케이스 네이밍 전략 설정 (camelCase -> snake_case)
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        
        // null 값 제외 설정
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Java 8 시간 모듈 설정
        JavaTimeModule timeModule = new JavaTimeModule();
        // LocalDateTime 직렬화/역직렬화 포맷 설정
        timeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        timeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // 중복 모듈 등록 무시 설정
        objectMapper.disable(MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS);
        
        // 시간 모듈 등록
        objectMapper.registerModules(timeModule);
        
        // 알 수 없는 속성 무시 설정
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        
        // 빈 객체 직렬화 실패 방지 설정
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        
        // 단일 따옴표 허용 설정
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        
        // 대소문자 구분 없이 속성 매핑 설정
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        
        // 빈 문자열을 null 객체로 처리 설정
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        
        // null 값 직렬화 커스텀 처리 (빈 문자열로 변환)
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString("");
            }
        });
        
        return objectMapper;
    }
}
