package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.wayline.WaylineErrorCodeEnum;

/**
 * 지정 지점 비행 진행 상황 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 지정 지점 비행의 진행 상황을 담는 클래스입니다.
 * 비행 결과, 상태, 비행 ID, 웨이포인트 인덱스 등을 포함합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/14
 */
public class FlyToPointProgress {

    /**
     * 비행 결과
     * 지정 지점 비행의 결과 코드
     */
    private WaylineErrorCodeEnum result;

    /**
     * 비행 상태
     * 지정 지점 비행의 현재 상태
     */
    private FlyToStatusEnum status;

    /**
     * 비행 ID
     * 지정 지점 비행의 고유 식별자
     */
    private String flyToId;

    /**
     * 웨이포인트 인덱스
     * 현재 웨이포인트의 인덱스
     */
    private Integer wayPointIndex;

    /**
     * 기본 생성자
     */
    public FlyToPointProgress() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FlyToPointProgress{" +
                "result=" + result +
                ", status=" + status +
                ", flyToId='" + flyToId + '\'' +
                ", wayPointIndex=" + wayPointIndex +
                '}';
    }

    /**
     * 비행 결과를 반환합니다.
     * 
     * @return 비행 결과
     */
    public WaylineErrorCodeEnum getResult() {
        return result;
    }

    /**
     * 비행 결과를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param result 설정할 비행 결과
     * @return 현재 FlyToPointProgress 객체
     */
    public FlyToPointProgress setResult(WaylineErrorCodeEnum result) {
        this.result = result;
        return this;
    }

    /**
     * 비행 상태를 반환합니다.
     * 
     * @return 비행 상태
     */
    public FlyToStatusEnum getStatus() {
        return status;
    }

    /**
     * 비행 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param status 설정할 비행 상태
     * @return 현재 FlyToPointProgress 객체
     */
    public FlyToPointProgress setStatus(FlyToStatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * 비행 ID를 반환합니다.
     * 
     * @return 비행 ID
     */
    public String getFlyToId() {
        return flyToId;
    }

    /**
     * 비행 ID를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param flyToId 설정할 비행 ID
     * @return 현재 FlyToPointProgress 객체
     */
    public FlyToPointProgress setFlyToId(String flyToId) {
        this.flyToId = flyToId;
        return this;
    }

    /**
     * 웨이포인트 인덱스를 반환합니다.
     * 
     * @return 웨이포인트 인덱스
     */
    public Integer getWayPointIndex() {
        return wayPointIndex;
    }

    /**
     * 웨이포인트 인덱스를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param wayPointIndex 설정할 웨이포인트 인덱스
     * @return 현재 FlyToPointProgress 객체
     */
    public FlyToPointProgress setWayPointIndex(Integer wayPointIndex) {
        this.wayPointIndex = wayPointIndex;
        return this;
    }
}
