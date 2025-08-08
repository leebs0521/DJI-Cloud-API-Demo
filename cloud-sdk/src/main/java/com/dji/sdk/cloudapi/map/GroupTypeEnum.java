package com.dji.sdk.cloudapi.map;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;

/**
 * 지도 요소 그룹 타입 열거형
 * 
 * 이 열거형은 지도 요소 그룹의 타입을 정의합니다.
 * 각 타입은 숫자 값으로 표현되며, JSON 직렬화/역직렬화를 지원합니다.
 * 
 * 지원하는 타입:
 * - CUSTOM(0): 사용자 정의 요소 그룹
 * - DEFAULT(1): 기본 요소 그룹
 * - SHARED(2): APP 공유 요소 그룹
 * 
 * APP 공유 요소 그룹(type=2)은 APP 요소 그룹으로,
 * PILOT이 기본적으로 지도 요소를 이 요소 그룹에 추가합니다.
 * 같은 워크스페이스에는 반드시 하나의 APP 공유 요소 그룹이 있어야 하며,
 * 하나만 존재하는 것이 권장됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/15
 */
@Schema(description = "<p>0: custom element group <p/><p>1: default element group <p/><p>2: APP shared element group " +
        "(type=2 is an APP element group, PILOT will add map elements to this element group by default, " +
        "and there must be an APP shared element group. " +
        "It is recommended that in the same workspace, there are And there is only one APP shared element group)<p/>",
        enumAsRef = true, type = "int", allowableValues = {"0", "1", "2"})
public enum GroupTypeEnum {

    /**
     * 사용자 정의 요소 그룹
     * 
     * 사용자가 직접 생성하고 관리하는 요소 그룹입니다.
     * 사용자가 자유롭게 요소를 추가, 수정, 삭제할 수 있습니다.
     * 값: 0
     */
    CUSTOM(0),

    /**
     * 기본 요소 그룹
     * 
     * 시스템에서 기본적으로 제공하는 요소 그룹입니다.
     * 시스템 레이어나 기본 설정 요소들이 포함됩니다.
     * 값: 1
     */
    DEFAULT(1),

    /**
     * APP 공유 요소 그룹
     * 
     * APP에서 공유되는 요소 그룹으로, PILOT이 기본적으로
     * 지도 요소를 이 그룹에 추가합니다.
     * 같은 워크스페이스에는 반드시 하나의 APP 공유 요소 그룹이 있어야 합니다.
     * 값: 2
     */
    SHARED(2);

    /**
     * 그룹 타입의 숫자 값
     */
    private final int type;

    GroupTypeEnum(int type) {
        this.type = type;
    }

    /**
     * 그룹 타입의 숫자 값을 반환합니다.
     * JSON 직렬화 시 이 값이 사용됩니다.
     * 
     * @return 그룹 타입 값 (0, 1, 2)
     */
    @JsonValue
    public int getType() {
        return type;
    }

    /**
     * 그룹 타입 값으로 열거형을 찾습니다.
     * 
     * 이 메서드는 숫자 값에 해당하는 열거형을 반환합니다.
     * JSON 역직렬화 시 사용되며, @JsonCreator 어노테이션이 적용되어 있습니다.
     * 
     * @param type 그룹 타입 값 (0, 1, 2)
     * @return 해당하는 열거형
     * @throws CloudSDKException 해당하는 타입이 없는 경우
     */
    @JsonCreator
    public static GroupTypeEnum find(int type) {
        return Arrays.stream(values()).filter(typeEnum -> typeEnum.type == type).findAny()
            .orElseThrow(() -> new CloudSDKException(GroupTypeEnum.class, type));
    }
}
