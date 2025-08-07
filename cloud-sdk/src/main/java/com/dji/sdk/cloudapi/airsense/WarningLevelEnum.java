package com.dji.sdk.cloudapi.airsense;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 위험도 레벨 열거형 클래스
 * 
 * 이 클래스는 AirSense 시스템에서 감지된 항공기의 위험도를 정의합니다.
 * 숫자가 높을수록 더 위험하며, 3 이상인 경우 회피 조치가 권장됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/16
 */
public enum WarningLevelEnum {

    /**
     * 위험도 레벨 0
     * 위험하지 않음
     */
    ZERO(0),

    /**
     * 위험도 레벨 1
     * 낮은 위험도
     */
    ONE(1),

    /**
     * 위험도 레벨 2
     * 중간 위험도
     */
    TWO(2),

    /**
     * 위험도 레벨 3
     * 높은 위험도 (회피 조치 권장)
     */
    THREE(3),

    /**
     * 위험도 레벨 4
     * 매우 높은 위험도 (즉시 회피 조치 필요)
     */
    FOUR(4),

    ;

    /**
     * 위험도 레벨 값
     */
    private final int level;

    /**
     * 위험도 레벨 열거형 생성자
     * 
     * @param level 위험도 레벨 값
     */
    WarningLevelEnum(int level) {
        this.level = level;
    }

    /**
     * 위험도 레벨 값을 반환합니다.
     * 
     * @return 위험도 레벨 값
     */
    @JsonValue
    public int getLevel() {
        return level;
    }

    /**
     * 정수 값으로 위험도 레벨을 찾습니다.
     * 
     * @param level 찾을 위험도 레벨 값
     * @return 찾은 위험도 레벨 열거형
     * @throws CloudSDKException 해당하는 위험도 레벨을 찾을 수 없는 경우
     */
    @JsonCreator
    public static WarningLevelEnum find(int level) {
        return Arrays.stream(values()).filter(levelEnum -> levelEnum.level == level).findAny()
            .orElseThrow(() -> new CloudSDKException(WarningLevelEnum.class, level));
    }

}
