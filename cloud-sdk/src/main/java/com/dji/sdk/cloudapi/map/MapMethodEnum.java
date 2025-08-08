package com.dji.sdk.cloudapi.map;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 지도 메서드 열거형
 * 
 * 이 열거형은 지도 관련 MQTT 메서드들을 정의합니다.
 * 각 메서드는 문자열 값으로 표현되며, JSON 직렬화/역직렬화를 지원합니다.
 * 
 * 지원하는 메서드:
 * - OFFLINE_MAP_UPDATE: 오프라인 맵 업데이트 메서드
 * 
 * 이 열거형은 MQTT 통신에서 지도 관련 메시지를 식별하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public enum MapMethodEnum {

    /**
     * 오프라인 맵 업데이트 메서드
     * 
     * 오프라인 맵 파일을 업데이트하기 위한 메서드입니다.
     * 도킹 스테이션이 오프라인 맵 동기화를 트리거할 때 사용됩니다.
     * 메서드 값: "offline_map_update"
     */
    OFFLINE_MAP_UPDATE("offline_map_update"),

    ;

    /**
     * 메서드의 문자열 값
     */
    private final String method;

    MapMethodEnum(String method) {
        this.method = method;
    }

    /**
     * 메서드의 문자열 값을 반환합니다.
     * JSON 직렬화 시 이 값이 사용됩니다.
     * 
     * @return 메서드 문자열 값
     */
    @JsonValue
    public String getMethod() {
        return method;
    }
}
