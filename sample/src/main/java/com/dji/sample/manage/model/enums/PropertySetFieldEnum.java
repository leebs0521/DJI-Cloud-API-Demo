package com.dji.sample.manage.model.enums;

import com.dji.sample.manage.model.receiver.*;
import com.dji.sdk.cloudapi.property.PropertySetEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 속성 설정 필드 열거형
 * 
 * DJI Cloud API의 디바이스 속성 설정 필드를 정의하는 열거형 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 속성 설정 관리
 *    - 야간 조명 상태, 고도 제한, 거리 제한 상태 관리
 *    - 장애물 회피, RTH 고도, 통신 끊김 동작 관리
 *    - 각 속성별 전용 리시버 클래스 매핑
 * 
 * 2. 속성 설정 필드 매핑
 *    - PropertySetEnum과 리시버 클래스 간의 매핑
 *    - JSON 직렬화를 위한 속성명 제공
 *    - 속성명으로부터 열거형 상수 검색 기능
 * 
 * 3. 디바이스 제어 및 설정
 *    - 디바이스의 다양한 설정 옵션 관리
 *    - 실시간 속성 변경 및 모니터링
 *    - 디바이스 상태 제어 및 안전 기능 관리
 * 
 * 이 열거형은 DJI 디바이스의 다양한 속성 설정을
 * 표준화된 방식으로 관리하고 제어할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/27
 */
public enum PropertySetFieldEnum {

    /**
     * 야간 조명 상태 설정
     * 디바이스의 야간 조명 켜기/끄기 상태
     */
    NIGHT_LIGHTS_STATE(PropertySetEnum.NIGHT_LIGHTS_STATE, NightLightsStateReceiver.class),

    /**
     * 고도 제한 설정
     * 디바이스의 최대 비행 고도 제한
     */
    HEIGHT_LIMIT(PropertySetEnum.HEIGHT_LIMIT, HeightLimitReceiver.class),

    /**
     * 거리 제한 상태 설정
     * 디바이스의 비행 거리 제한 상태
     */
    DISTANCE_LIMIT_STATUS(PropertySetEnum.DISTANCE_LIMIT_STATUS, DistanceLimitStatusReceiver.class),

    /**
     * 장애물 회피 설정
     * 디바이스의 장애물 회피 기능 켜기/끄기
     */
    OBSTACLE_AVOIDANCE(PropertySetEnum.OBSTACLE_AVOIDANCE, ObstacleAvoidanceReceiver.class),

    /**
     * RTH 고도 설정
     * Return to Home 시의 비행 고도
     */
    RTH_ALTITUDE(PropertySetEnum.RTH_ALTITUDE, RthAltitudeReceiver.class),

    /**
     * 통신 끊김 동작 설정
     * 통신이 끊어졌을 때의 동작 방식
     */
    OUT_OF_CONTROL_ACTION(PropertySetEnum.OUT_OF_CONTROL_ACTION, OutOfControlActionReceiver.class),

//    /**
//     * RC 신호 손실 시 웨이라인 종료 동작 설정
//     * RC 신호가 손실되었을 때 웨이라인 실행 중단 여부
//     */
//    EXIT_WAYLINE_WHEN_RC_LOST(PropertySetEnum.EXIT_WAYLINE_WHEN_RC_LOST, .class),
//
//    /**
//     * 열화상 팔레트 스타일 설정
//     * 열화상 카메라의 색상 팔레트 스타일
//     */
//    THERMAL_CURRENT_PALETTE_STYLE(PropertySetEnum.THERMAL_CURRENT_PALETTE_STYLE, .class),
//
//    /**
//     * 열화상 게인 모드 설정
//     * 열화상 카메라의 게인 모드
//     */
//    THERMAL_GAIN_MODE(PropertySetEnum.THERMAL_GAIN_MODE, .class),
//
//    /**
//     * 열화상 등온선 상태 설정
//     * 열화상 카메라의 등온선 기능 상태
//     */
//    THERMAL_ISOTHERM_STATE(PropertySetEnum.THERMAL_ISOTHERM_STATE, .class),
//
//    /**
//     * 열화상 등온선 상한 설정
//     * 열화상 카메라의 등온선 상한 값
//     */
//    THERMAL_ISOTHERM_UPPER_LIMIT(PropertySetEnum.THERMAL_ISOTHERM_UPPER_LIMIT, .class),
//
//    /**
//     * 열화상 등온선 하한 설정
//     * 열화상 카메라의 등온선 하한 값
//     */
//    THERMAL_ISOTHERM_LOWER_LIMIT(PropertySetEnum.THERMAL_ISOTHERM_LOWER_LIMIT, .class),

    ;

    /**
     * 속성 설정 열거형
     * DJI SDK의 PropertySetEnum 인스턴스
     */
    private final PropertySetEnum property;

    /**
     * 리시버 클래스
     * 해당 속성 설정을 처리하는 리시버 클래스
     */
    private final Class<? extends BasicDeviceProperty> clazz;

    /**
     * 생성자
     * @param property 속성 설정 열거형
     * @param clazz 리시버 클래스
     */
    PropertySetFieldEnum(PropertySetEnum property, Class<? extends BasicDeviceProperty> clazz) {
        this.property = property;
        this.clazz = clazz;
    }

    /**
     * 속성 설정 열거형을 반환합니다.
     * @return PropertySetEnum 인스턴스
     */
    public PropertySetEnum getProperty() {
        return property;
    }

    /**
     * JSON 직렬화를 위한 속성명을 반환합니다.
     * @return 속성명 문자열
     */
    @JsonValue
    public String getPropertyName() {
        return property.getProperty();
    }

    /**
     * 리시버 클래스를 반환합니다.
     * @return 리시버 클래스
     */
    public Class<? extends BasicDeviceProperty> getClazz() {
        return clazz;
    }

    /**
     * 속성명으로부터 열거형 상수를 찾습니다.
     * 
     * 주어진 속성명에 해당하는 열거형 상수를 반환합니다.
     * 일치하는 값이 없으면 예외를 발생시킵니다.
     * 
     * @param property 찾을 속성명
     * @return 해당하는 열거형 상수
     * @throws RuntimeException 일치하는 속성명이 없을 경우
     */
    public static PropertySetFieldEnum find(String property) {
        return Arrays.stream(values()).filter(propertyEnum -> propertyEnum.property.getProperty().equals(property)).findAny()
                .orElseThrow();
    }
}
