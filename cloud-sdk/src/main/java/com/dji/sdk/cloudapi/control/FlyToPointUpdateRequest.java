package com.dji.sdk.cloudapi.control;

import com.dji.sdk.common.BaseModel;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 지정 지점 비행 업데이트 요청 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 진행 중인 지정 지점 비행의 목적지를 업데이트하기 위한 요청을 정의합니다.
 * 최대 속도와 새로운 목적지 좌표를 포함합니다.
 * M30 시리즈는 하나의 지점만 지원합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public class FlyToPointUpdateRequest extends BaseModel {

    /**
     * 최대 속도 (필수)
     * 1 ~ 15 범위, 비행 중 최대 속도 (m/s)
     */
    @Min(1)
    @Max(15)
    @NotNull
    private Integer maxSpeed;

    /**
     * 새로운 목적지 좌표 목록 (필수)
     * 업데이트할 목적지들의 좌표 정보
     * M30 시리즈는 하나의 지점만 지원합니다.
     */
    @Size(min = 1, max = 1)
    @NotNull
    private List<@Valid Point> points;

    /**
     * 기본 생성자
     */
    public FlyToPointUpdateRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FlyToPointUpdateRequest{" +
                "maxSpeed=" + maxSpeed +
                ", points=" + points +
                '}';
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
     * @return 현재 FlyToPointUpdateRequest 객체
     */
    public FlyToPointUpdateRequest setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    /**
     * 새로운 목적지 좌표 목록을 반환합니다.
     * 
     * @return 새로운 목적지 좌표 목록
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * 새로운 목적지 좌표 목록을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param points 설정할 새로운 목적지 좌표 목록
     * @return 현재 FlyToPointUpdateRequest 객체
     */
    public FlyToPointUpdateRequest setPoints(List<Point> points) {
        this.points = points;
        return this;
    }
}
