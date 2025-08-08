package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 준비 조건 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업이 실행 가능한 조건들을 정의합니다.
 * 배터리 용량과 실행 가능 시간 범위를 포함하여
 * 안전한 비행 작업 실행을 보장합니다.
 * 
 * 주요 구성 요소:
 * - batteryCapacity: 배터리 용량 임계값
 * - beginTime: 작업 실행 가능 기간 시작 시간
 * - endTime: 작업 실행 가능 기간 종료 시간
 * 
 * 이 클래스는 비행 작업 실행 전에 필요한 조건들을
 * 확인하여 안전한 실행을 보장하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/23
 */
public class ReadyConditions extends BaseModel {

    /**
     * 배터리 용량
     * 
     * 실행 가능한 작업의 항공기 배터리 백분율 임계값입니다.
     * 작업이 시작될 때 항공기 배터리가 batteryCapacity보다 커야 합니다.
     * 
     * 제약 조건: 0 ~ 100 사이의 값
     */
    @NotNull
    @Min(0)
    @Max(100)
    private Integer batteryCapacity;

    /**
     * 작업 실행 가능 기간 시작 시간
     * 
     * 작업 실행 가능 기간의 시작 밀리초 타임스탬프입니다.
     * 작업 실행 시간은 beginTime보다 늦어야 합니다.
     */
    @NotNull
    private Long beginTime;

    /**
     * 작업 실행 가능 기간 종료 시간
     * 
     * 작업 실행 가능 기간의 종료 밀리초 타임스탬프입니다.
     * 작업 실행 시간은 endTime보다 빨라야 합니다.
     */
    @NotNull
    private Long endTime;

    public ReadyConditions() {}

    @Override
    public String toString() {
        return "ReadyConditions{" +
                "batteryCapacity=" + batteryCapacity +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }

    /**
     * 배터리 용량을 반환합니다.
     * 
     * @return 배터리 용량 (0 ~ 100)
     */
    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    /**
     * 배터리 용량을 설정합니다.
     * 
     * @param batteryCapacity 배터리 용량 (0 ~ 100)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ReadyConditions setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
        return this;
    }

    /**
     * 작업 실행 가능 기간 시작 시간을 반환합니다.
     * 
     * @return 시작 시간 (밀리초 타임스탬프)
     */
    public Long getBeginTime() {
        return beginTime;
    }

    /**
     * 작업 실행 가능 기간 시작 시간을 설정합니다.
     * 
     * @param beginTime 시작 시간 (밀리초 타임스탬프)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ReadyConditions setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    /**
     * 작업 실행 가능 기간 종료 시간을 반환합니다.
     * 
     * @return 종료 시간 (밀리초 타임스탬프)
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * 작업 실행 가능 기간 종료 시간을 설정합니다.
     * 
     * @param endTime 종료 시간 (밀리초 타임스탬프)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ReadyConditions setEndTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }
}