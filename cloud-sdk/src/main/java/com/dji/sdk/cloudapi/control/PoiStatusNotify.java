package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.wayline.FlighttaskStatusEnum;

/**
 * POI 상태 알림 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 POI(Point of Interest) 상태 알림을 담는 클래스입니다.
 * POI 상태, 이유, 원형 비행 반지름, 속도 등을 포함하여 POI 상태 알림을 관리합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public class PoiStatusNotify {

    /**
     * POI 상태
     * POI 모드의 현재 상태
     */
    private FlighttaskStatusEnum status;

    /**
     * POI 상태 이유
     * POI 상태의 이유 정보
     */
    private PoiStatusReasonEnum reason;

    /**
     * 원형 비행 반지름
     * POI 모드에서 원형 비행의 반지름 (미터)
     */
    private Float circleRadius;

    /**
     * 원형 비행 속도
     * POI 모드에서 현재 원형 비행 속도 (m/s)
     */
    private Float circleSpeed;

    /**
     * 최대 원형 비행 속도
     * POI 모드에서 최대 원형 비행 속도 (m/s)
     */
    private Float maxCircleSpeed;

    /**
     * 기본 생성자
     */
    public PoiStatusNotify() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "PoiStatusNotify{" +
                "status=" + status +
                ", reason=" + reason +
                ", circleRadius=" + circleRadius +
                ", circleSpeed=" + circleSpeed +
                ", maxCircleSpeed=" + maxCircleSpeed +
                '}';
    }

    /**
     * POI 상태를 반환합니다.
     * 
     * @return POI 상태
     */
    public FlighttaskStatusEnum getStatus() {
        return status;
    }

    /**
     * POI 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param status 설정할 POI 상태
     * @return 현재 PoiStatusNotify 객체
     */
    public PoiStatusNotify setStatus(FlighttaskStatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * POI 상태 이유를 반환합니다.
     * 
     * @return POI 상태 이유
     */
    public PoiStatusReasonEnum getReason() {
        return reason;
    }

    /**
     * POI 상태 이유를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param reason 설정할 POI 상태 이유
     * @return 현재 PoiStatusNotify 객체
     */
    public PoiStatusNotify setReason(PoiStatusReasonEnum reason) {
        this.reason = reason;
        return this;
    }

    /**
     * 원형 비행 반지름을 반환합니다.
     * 
     * @return 원형 비행 반지름 (미터)
     */
    public Float getCircleRadius() {
        return circleRadius;
    }

    /**
     * 원형 비행 반지름을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param circleRadius 설정할 원형 비행 반지름 (미터)
     * @return 현재 PoiStatusNotify 객체
     */
    public PoiStatusNotify setCircleRadius(Float circleRadius) {
        this.circleRadius = circleRadius;
        return this;
    }

    /**
     * 원형 비행 속도를 반환합니다.
     * 
     * @return 원형 비행 속도 (m/s)
     */
    public Float getCircleSpeed() {
        return circleSpeed;
    }

    /**
     * 원형 비행 속도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param circleSpeed 설정할 원형 비행 속도 (m/s)
     * @return 현재 PoiStatusNotify 객체
     */
    public PoiStatusNotify setCircleSpeed(Float circleSpeed) {
        this.circleSpeed = circleSpeed;
        return this;
    }

    /**
     * 최대 원형 비행 속도를 반환합니다.
     * 
     * @return 최대 원형 비행 속도 (m/s)
     */
    public Float getMaxCircleSpeed() {
        return maxCircleSpeed;
    }

    /**
     * 최대 원형 비행 속도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param maxCircleSpeed 설정할 최대 원형 비행 속도 (m/s)
     * @return 현재 PoiStatusNotify 객체
     */
    public PoiStatusNotify setMaxCircleSpeed(Float maxCircleSpeed) {
        this.maxCircleSpeed = maxCircleSpeed;
        return this;
    }
}
