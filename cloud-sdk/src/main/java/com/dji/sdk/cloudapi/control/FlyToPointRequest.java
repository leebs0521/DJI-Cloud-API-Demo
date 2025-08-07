package com.dji.sdk.cloudapi.control;

import com.dji.sdk.common.BaseModel;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

/**
 * 지정 지점 비행 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 드론을 특정 지점으로 비행시키기 위한 요청을 정의합니다.
 * 비행 ID, 최대 속도, 목적지 좌표 목록을 포함합니다.
 * M30 시리즈는 하나의 지점만 지원합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/2/14
 */
public class FlyToPointRequest extends BaseModel {

    /**
     * 비행 ID (필수)
     * 비행 작업의 고유 식별자
     * 특수 문자 제한: < > : " / | ? * . _ \
     */
    @Pattern(regexp = "^[^<>:\"/|?*._\\\\]+$")
    @NotNull
    private String flyToId;

    /**
     * 최대 속도 (필수)
     * 1 ~ 15 범위, 비행 중 최대 속도 (m/s)
     */
    @Min(1)
    @Max(15)
    @NotNull
    private Integer maxSpeed;

    /**
     * 목적지 좌표 목록 (필수)
     * 비행할 목적지들의 좌표 정보
     * M30 시리즈는 하나의 지점만 지원합니다.
     */
    @Size(min = 1)
    @NotNull
    private List<@Valid Point> points;

    /**
     * 기본 생성자
     */
    public FlyToPointRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FlyToPointRequest{" +
                "flyToId='" + flyToId + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", points=" + points +
                '}';
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
     * @return 현재 FlyToPointRequest 객체
     */
    public FlyToPointRequest setFlyToId(String flyToId) {
        this.flyToId = flyToId;
        return this;
    }

    /**
     * 최대 속도를 반환합니다.
     * 
     * @return 최대 속도 (m/s)
     */
    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * 최대 속도를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param maxSpeed 설정할 최대 속도 (m/s)
     * @return 현재 FlyToPointRequest 객체
     */
    public FlyToPointRequest setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    /**
     * 목적지 좌표 목록을 반환합니다.
     * 
     * @return 목적지 좌표 목록
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * 목적지 좌표 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param points 설정할 목적지 좌표 목록
     * @return 현재 FlyToPointRequest 객체
     */
    public FlyToPointRequest setPoints(List<Point> points) {
        this.points = points;
        return this;
    }
}
