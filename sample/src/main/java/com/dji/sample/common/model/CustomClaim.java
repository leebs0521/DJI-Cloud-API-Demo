package com.dji.sample.common.model;

import com.auth0.jwt.interfaces.Claim;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JWT 토큰에 사용자 정의 정보를 저장하기 위한 커스텀 클레임 클래스
 * 
 * 이 클래스는 JWT 토큰에 포함될 사용자 정보를 정의하고,
 * Map 형태와 객체 형태 간의 변환 기능을 제공합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/16
 * @version 0.1
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class CustomClaim {

    /** 계정 ID */
    private String id;

    /** 사용자명 */
    private String username;

    /** 사용자 타입 */
    @JsonAlias("user_type")
    private Integer userType;

    /** 워크스페이스 ID */
    @JsonAlias("workspace_id")
    private String workspaceId;

    /**
     * 커스텀 클레임 데이터를 Map 형태로 변환합니다.
     * 
     * 리플렉션을 사용하여 클래스의 모든 필드를 Map으로 변환합니다.
     * JsonAlias 어노테이션이 있는 경우 해당 값을 키로 사용합니다.
     * 
     * @return 변환된 ConcurrentHashMap 객체
     */
    public ConcurrentHashMap<String, String> convertToMap() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(4);
        try {
            Field[] declaredFields = this.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                JsonAlias annotation = field.getAnnotation(JsonAlias.class);
                field.setAccessible(true);
                // 키 값은 언더스코어 형태로 명명됩니다
                map.put(annotation != null ? annotation.value()[0] : field.getName(),
                        field.get(this).toString());
            }
        } catch (IllegalAccessException e) {
            log.info("CustomClaim converts failed. {}", this.toString());
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Map의 데이터를 커스텀 클레임 객체로 변환합니다.
     * 
     * JWT 토큰의 클레임 데이터를 파싱하여 객체의 필드에 설정합니다.
     * JsonAlias 어노테이션을 통해 JSON 필드명과 Java 필드명을 매핑합니다.
     * 
     * @param claimMap JWT 클레임 데이터가 담긴 Map
     */
    public CustomClaim (Map<String, Claim> claimMap) {
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            JsonAlias annotation = field.getAnnotation(JsonAlias.class);

            Claim value = claimMap.get(annotation == null ? field.getName() : annotation.value()[0]);
            try {
                Class<?> type = field.getType();
                if (Integer.class.equals(type)) {
                    field.set(this, Integer.valueOf(value.asString()));
                    continue;
                }
                if (String.class.equals(type)) {
                    field.set(this, value.asString());
                    continue;
                }
            } catch (IllegalAccessException e) {
                log.info("Claim parses failed. {}", claimMap.toString());
                e.printStackTrace();
            }
        }
    }
}