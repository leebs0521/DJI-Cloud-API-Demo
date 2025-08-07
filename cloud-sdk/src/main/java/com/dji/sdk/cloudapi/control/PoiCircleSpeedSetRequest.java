package com.dji.sdk.cloudapi.control;

import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * POI 원형 비행 속도 설정 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 POI 모드의 원형 비행 속도를 설정하기 위한 요청을 정의합니다.
 * POI 모드에서 드론이 관심 지점을 중심으로 원형 비행할 때의 속도를 설정합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public class PoiCircleSpeedSetRequest extends BaseModel {

    /**
     * 원형 비행 속도 (필수)
     * POI 모드에서 원형 비행 시 드론의 속도
     */
    @NotNull
    private Float circleSpeed;

    /**
     * 기본 생성자
     */
    public PoiCircleSpeedSetRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "PoiCircleSpeedSetRequest{" +
                "circleSpeed=" + circleSpeed +
                '}';
    }

    /**
     * 원형 비행 속도를 반환합니다.
     * 
     * @return 원형 비행 속도
     */
    public Float getCircleSpeed() {
        return circleSpeed;
    }

    /**
     * 원형 비행 속도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param circleSpeed 설정할 원형 비행 속도
     * @return 현재 PoiCircleSpeedSetRequest 객체
     */
    public PoiCircleSpeedSetRequest setCircleSpeed(Float circleSpeed) {
        this.circleSpeed = circleSpeed;
        return this;
    }
}
