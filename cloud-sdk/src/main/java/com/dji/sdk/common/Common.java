package com.dji.sdk.common;

import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.exception.CloudSDKErrorEnum;
import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 공통 유틸리티 클래스
 * 
 * 이 클래스는 SDK에서 공통적으로 사용되는
 * 유틸리티 기능들을 제공합니다.
 * 
 * 주요 기능:
 * - JSON 직렬화/역직렬화 설정
 * - 모델 유효성 검사
 * - 문자열 변환 (snake_case)
 * 
 * 이 클래스는 SDK의 모든 모듈에서
 * 공통적으로 사용되는 기능들을 중앙화합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/23
 */
public class Common {

    /**
     * JSON 매퍼 빌더
     * 
     * JSON 직렬화/역직렬화를 위한 ObjectMapper 빌더입니다.
     */
    private static final JsonMapper.Builder MAPPER_BUILDER = JsonMapper.builder();

    /**
     * 정적 초기화 블록
     * 
     * JSON 매퍼의 설정을 초기화합니다.
     * - 날짜/시간 형식 설정
     * - 속성 명명 전략 (snake_case)
     * - 직렬화 옵션 설정
     * - 역직렬화 옵션 설정
     */
    static {
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        timeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        MAPPER_BUILDER.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .serializationInclusion(JsonInclude.Include.NON_ABSENT)
                .disable(MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS)
                .addModule(timeModule)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
                .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    }

    /**
     * 모델 유효성 검사를 수행합니다.
     * 
     * BaseModel의 기본 유효성 검사를 실행합니다.
     * 
     * @param model 검사할 모델
     * @throws CloudSDKException 모델이 null이거나 유효성 검사 실패 시
     */
    public static void validateModel(BaseModel model) {
        if (null == model) {
            throw new CloudSDKException(CloudSDKErrorEnum.INVALID_PARAMETER, "Param must not be null.");
        }
        model.valid();
    }

    /**
     * Gateway를 고려한 모델 유효성 검사를 수행합니다.
     * 
     * BaseModel의 Gateway 기반 유효성 검사를 실행합니다.
     * 
     * @param model 검사할 모델
     * @param gateway Gateway 관리자
     * @throws CloudSDKException 모델이 null이거나 유효성 검사 실패 시
     */
    public static void validateModel(BaseModel model, GatewayManager gateway) {
        if (null == model) {
            throw new CloudSDKException(CloudSDKErrorEnum.INVALID_PARAMETER, "Param must not be null.");
        }
        model.valid(gateway);
    }

    /**
     * 설정된 ObjectMapper를 반환합니다.
     * 
     * 정적 초기화 블록에서 설정된 JSON 매퍼를 반환합니다.
     * 
     * @return 설정된 ObjectMapper 인스턴스
     */
    public static ObjectMapper getObjectMapper() {
        return MAPPER_BUILDER.build();
    }

    /**
     * snake_case 문자열을 camelCase로 변환합니다.
     * 
     * 언더스코어(_)로 구분된 문자열을 카멜케이스로 변환합니다.
     * 예: "user_name" -> "userName"
     * 
     * @param key 변환할 snake_case 문자열
     * @return 변환된 camelCase 문자열
     */
    public static String convertSnake(String key) {
        StringBuilder sb = new StringBuilder();
        boolean isChange = false;
        for (char c : key.toCharArray()) {
            if (c == '_') {
                isChange = true;
                continue;
            }
            if (isChange) {
                sb.append((char)(c - 32));
                isChange = false;
                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
