package com.dji.sdk.cloudapi.hms;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * HMS 레벨 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 HMS(Health Management System)의 심각도 레벨을 정의합니다.
 * 정보(INFORM), 알림(NOTICE), 경보(ALARM) 세 가지 레벨로 구분하여
 * HMS의 중요도와 대응 우선순위를 결정합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/27
 */
public enum HmsLevelEnum {

    /**
     * 정보
     * 일반적인 정보성 HMS 메시지
     */
    INFORM(0),

    /**
     * 알림
     * 주의가 필요한 알림성 HMS 메시지
     */
    NOTICE(1),

    /**
     * 경보
     * 즉시 대응이 필요한 긴급한 HMS 메시지
     */
    ALARM(2);

    /**
     * HMS 레벨
     * 각 레벨에 대한 고유한 숫자 코드
     */
    private final int level;

    /**
     * HMS 레벨 열거형 생성자
     * 
     * @param level HMS 레벨
     */
    HmsLevelEnum(int level) {
        this.level = level;
    }

    /**
     * HMS 레벨을 반환합니다.
     * JSON 직렬화 시 사용됩니다.
     * 
     * @return HMS 레벨
     */
    @JsonValue
    public int getLevel() {
        return level;
    }

    /**
     * 숫자로 HMS 레벨을 찾습니다.
     * JSON 역직렬화 시 사용됩니다.
     * 
     * @param level 찾을 HMS 레벨
     * @return 찾은 HMS 레벨 열거형
     * @throws CloudSDKException 해당하는 레벨을 찾을 수 없는 경우
     */
    @JsonCreator
    public static HmsLevelEnum find(int level) {
        return Arrays.stream(values()).filter(levelEnum -> levelEnum.level == level).findAny()
                .orElseThrow(() -> new CloudSDKException(HmsLevelEnum.class, level));
    }
}
