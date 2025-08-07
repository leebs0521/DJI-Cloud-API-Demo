package com.dji.sdk.cloudapi.hms;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * HMS 모듈 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 HMS(Health Management System)가 발생한 모듈을 정의합니다.
 * 비행 미션, 디바이스 관리, 미디어, HMS 등 다양한 모듈에서 발생할 수 있는
 * HMS를 구분하여 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/27
 */
public enum HmsModuleEnum {

    /**
     * 비행 미션
     * 비행 미션 관련 HMS
     */
    FLIGHT_MISSION(0),

    /**
     * 디바이스 관리
     * 디바이스 관리 관련 HMS
     */
    DEVICE_MANAGEMENT(1),

    /**
     * 미디어
     * 미디어 관련 HMS
     */
    MEDIA(2),

    /**
     * HMS
     * HMS 자체 관련 HMS
     */
    HMS(3);

    /**
     * HMS 모듈
     * 각 모듈에 대한 고유한 숫자 코드
     */
    private final int module;

    /**
     * HMS 모듈 열거형 생성자
     * 
     * @param module HMS 모듈
     */
    HmsModuleEnum(int module) {
        this.module = module;
    }

    /**
     * HMS 모듈을 반환합니다.
     * JSON 직렬화 시 사용됩니다.
     * 
     * @return HMS 모듈
     */
    @JsonValue
    public int getModule() {
        return module;
    }

    /**
     * 숫자로 HMS 모듈을 찾습니다.
     * JSON 역직렬화 시 사용됩니다.
     * 
     * @param module 찾을 HMS 모듈
     * @return 찾은 HMS 모듈 열거형
     * @throws CloudSDKException 해당하는 모듈을 찾을 수 없는 경우
     */
    @JsonCreator
    public static HmsModuleEnum find(int module) {
        return Arrays.stream(values()).filter(moduleEnum -> moduleEnum.module == module).findAny()
                .orElseThrow(() -> new CloudSDKException(HmsModuleEnum.class, module));
    }
}
