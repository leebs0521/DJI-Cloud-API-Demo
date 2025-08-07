package com.dji.sdk.cloudapi.device;

/**
 * 디바이스 모델 열거형 클래스
 * 
 * 이 클래스는 디바이스의 기본 모델을 정의합니다.
 * RC(리모트 컨트롤), DOCK(도크), DRONE(드론) 등의 모델을 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/29
 */
public enum DeviceModelEnum {

    /**
     * 리모트 컨트롤
     */
    RC, 
    
    /**
     * 도크
     */
    DOCK, 
    
    /**
     * 드론
     */
    DRONE;
}
