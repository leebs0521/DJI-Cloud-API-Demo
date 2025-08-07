package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 유지보수 타입 열거형 클래스
 * 
 * 이 클래스는 디바이스의 유지보수 타입을 정의합니다.
 * 드론과 도크의 기본, 정기, 심화 유지보수 등을 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public enum MaintainTypeEnum {

    /**
     * 유지보수 없음 (타입값: 0)
     */
    NO(0),

    /**
     * 드론 기본 유지보수 (타입값: 1)
     */
    DRONE_BASIC(1),

    /**
     * 드론 정기 유지보수 (타입값: 2)
     */
    DRONE_ROUTINE(2),

    /**
     * 드론 심화 유지보수 (타입값: 3)
     */
    DRONE_DEEP(3),

    /**
     * 도크 정기 유지보수 (타입값: 17)
     */
    DOCK_ROUTINE(17),

    /**
     * 도크 심화 유지보수 (타입값: 18)
     */
    DOCK_DEEP(18),
    ;

    /**
     * 유지보수 타입 정수값
     */
    private final int type;

    /**
     * 유지보수 타입 열거형 생성자
     * 
     * @param type 유지보수 타입 정수값
     */
    MaintainTypeEnum(int type) {
        this.type = type;
    }

    /**
     * 유지보수 타입 정수값을 반환합니다.
     * 
     * @return 유지보수 타입 정수값
     */
    @JsonValue
    public int getType() {
        return type;
    }

    /**
     * 정수값으로 유지보수 타입을 찾습니다.
     * 
     * @param type 찾을 유지보수 타입 정수값
     * @return 찾은 유지보수 타입 열거형
     * @throws CloudSDKException 해당하는 타입을 찾을 수 없는 경우
     */
    @JsonCreator
    public static MaintainTypeEnum find(int type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type == type).findAny()
            .orElseThrow(() -> new CloudSDKException(MaintainTypeEnum.class, type));
    }

}
