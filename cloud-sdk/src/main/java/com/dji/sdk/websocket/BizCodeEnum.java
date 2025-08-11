package com.dji.sdk.websocket;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * WebSocket 비즈니스 코드 열거형 클래스
 * 
 * 이 클래스는 WebSocket을 통해 전송되는 메시지의 비즈니스 타입을 정의합니다.
 * Pilot2 클라이언트가 이 비즈니스 코드를 받아서 해당 값에 따라 적절한 처리를 수행합니다.
 * 
 * @author sean
 * @version 0.1
 * @date 2021/11/26
 */
@Schema(enumAsRef = true, description = "Pilot2 will receive these bizCode, and then do corresponding processing according to the value.")
public enum BizCodeEnum {

    /** 디바이스 온라인 알림 */
    DEVICE_ONLINE("device_online"),

    /** 디바이스 오프라인 알림 */
    DEVICE_OFFLINE("device_offline"),

    /** 디바이스 토폴로지 업데이트 알림 */
    DEVICE_UPDATE_TOPO("device_update_topo"),

    /** 디바이스 OSD (On-Screen Display) 정보 알림 */
    DEVICE_OSD("device_osd"),

    /** 맵 요소 생성 알림 */
    MAP_ELEMENT_CREATE("map_element_create"),

    /** 맵 요소 업데이트 알림 */
    MAP_ELEMENT_UPDATE("map_element_update"),

    /** 맵 요소 삭제 알림 */
    MAP_ELEMENT_DELETE("map_element_delete"),

    /** 맵 그룹 새로고침 알림 */
    MAP_GROUP_REFRESH("map_group_refresh");

    /** 비즈니스 코드 문자열 */
    private final String code;

    /**
     * 비즈니스 코드 열거형 생성자
     * 
     * @param code 비즈니스 코드 문자열
     */
    BizCodeEnum(String code) {
        this.code = code;
    }

    /**
     * 비즈니스 코드를 반환합니다.
     * 
     * @return 비즈니스 코드 문자열
     */
    @JsonValue
    public String getCode() {
        return code;
    }


}
