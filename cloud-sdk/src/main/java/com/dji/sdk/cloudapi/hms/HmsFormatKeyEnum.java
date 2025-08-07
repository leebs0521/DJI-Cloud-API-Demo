package com.dji.sdk.cloudapi.hms;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * HMS 포맷 키 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 HMS(Health Management System) 메시지 포맷팅에 사용되는
 * 키들을 정의합니다. 알람 ID, 컴포넌트 인덱스, 배터리 인덱스, 도크 커버 인덱스,
 * 충전 로드 인덱스 등을 포함하여 HMS 메시지의 동적 포맷팅을 지원합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/7/7
 */
public enum HmsFormatKeyEnum {

    /**
     * 알람 ID
     * HMS 알람의 고유 식별자
     */
    ALARM_ID("%alarmid"),

    /**
     * 컴포넌트 인덱스
     * HMS가 발생한 컴포넌트의 인덱스
     */
    COMPONENT_INDEX("%component_index"),

    /**
     * 인덱스
     * 일반적인 인덱스 값
     */
    INDEX("%index"),

    /**
     * 배터리 인덱스
     * 배터리 관련 HMS의 인덱스
     */
    BATTERY_INDEX("%battery_index"),

    /**
     * 도크 커버 인덱스
     * 도크 커버 관련 HMS의 인덱스
     */
    DOCK_COVER_INDEX("%dock_cover_index"),

    /**
     * 충전 로드 인덱스
     * 충전 로드 관련 HMS의 인덱스
     */
    CHARGING_ROD_INDEX("%charging_rod_index");

    /**
     * 포맷 키
     * 각 포맷 키에 대한 고유한 문자열
     */
    private final String key;

    /**
     * HMS 포맷 키 열거형 생성자
     * 
     * @param key 포맷 키
     */
    HmsFormatKeyEnum(String key) {
        this.key = key;
    }

    /**
     * 포맷 키를 반환합니다.
     * JSON 직렬화 시 사용됩니다.
     * 
     * @return 포맷 키
     */
    @JsonValue
    public String getKey() {
        return key;
    }

    /**
     * 문자열로 HMS 포맷 키를 찾습니다.
     * 
     * @param key 찾을 포맷 키
     * @return 찾은 HMS 포맷 키 열거형
     * @throws CloudSDKException 해당하는 키를 찾을 수 없는 경우
     */
    public static HmsFormatKeyEnum find(String key) {
        return Arrays.stream(HmsFormatKeyEnum.values()).filter(format -> format.getKey().equals(key)).findAny()
                .orElseThrow(() -> new CloudSDKException(HmsFormatKeyEnum.class, key));
    }
}
