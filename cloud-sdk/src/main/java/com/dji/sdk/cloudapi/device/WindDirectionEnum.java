package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 풍향 열거형 클래스
 * 
 * 이 클래스는 풍향을 정의합니다.
 * 각 방향은 정수값으로 표현되며, 8방향과 무풍 상태를 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public enum WindDirectionEnum {

    /**
     * 무풍 (정수값: 0)
     */
    NO(0),

    /**
     * 북쪽 (정수값: 1)
     */
    NORTH(1),

    /**
     * 북동쪽 (정수값: 2)
     */
    NORTHEAST(2),

    /**
     * 동쪽 (정수값: 3)
     */
    EAST(3),

    /**
     * 남동쪽 (정수값: 4)
     */
    SOUTHEAST(4),

    /**
     * 남쪽 (정수값: 5)
     */
    SOUTH(5),

    /**
     * 남서쪽 (정수값: 6)
     */
    SOUTHWEST(6),

    /**
     * 서쪽 (정수값: 7)
     */
    WEST(7),

    /**
     * 북서쪽 (정수값: 8)
     */
    NORTHWEST(8),
    ;

    /**
     * 풍향 정수값
     */
    private final int direction;

    /**
     * 풍향 열거형 생성자
     * 
     * @param direction 풍향 정수값
     */
    WindDirectionEnum(int direction) {
        this.direction = direction;
    }

    /**
     * 풍향 정수값을 반환합니다.
     * 
     * @return 풍향 정수값
     */
    @JsonValue
    public int getDirection() {
        return direction;
    }

    /**
     * 정수값으로 풍향을 찾습니다.
     * 
     * @param direction 찾을 풍향 정수값
     * @return 찾은 풍향 열거형
     * @throws CloudSDKException 해당하는 풍향을 찾을 수 없는 경우
     */
    @JsonCreator
    public static WindDirectionEnum find(int direction) {
        return Arrays.stream(values()).filter(directionEnum -> directionEnum.direction == direction).findAny()
            .orElseThrow(() -> new CloudSDKException(WindDirectionEnum.class, direction));
    }

}
