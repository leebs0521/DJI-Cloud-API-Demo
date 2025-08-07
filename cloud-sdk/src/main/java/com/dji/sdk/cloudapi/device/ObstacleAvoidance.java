package com.dji.sdk.cloudapi.device;

/**
 * 장애물 회피 클래스
 * 
 * 이 클래스는 드론의 장애물 회피 설정을 담습니다.
 * 수평, 상향, 하향 방향의 장애물 회피 상태를 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/27
 */
public class ObstacleAvoidance {

    /**
     * 수평 방향 장애물 회피 상태
     */
    private SwitchActionEnum horizon;

    /**
     * 상향 방향 장애물 회피 상태
     */
    private SwitchActionEnum upside;

    /**
     * 하향 방향 장애물 회피 상태
     */
    private SwitchActionEnum downside;

    /**
     * 기본 생성자
     */
    public ObstacleAvoidance() {
    }

    @Override
    public String toString() {
        return "ObstacleAvoidanceSet{" +
                "horizon=" + horizon +
                ", upside=" + upside +
                ", downside=" + downside +
                '}';
    }

    /**
     * 수평 방향 장애물 회피 상태를 반환합니다.
     * 
     * @return 수평 방향 장애물 회피 상태
     */
    public SwitchActionEnum getHorizon() {
        return horizon;
    }

    /**
     * 수평 방향 장애물 회피 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param horizon 설정할 수평 방향 장애물 회피 상태
     * @return 현재 ObstacleAvoidance 객체
     */
    public ObstacleAvoidance setHorizon(SwitchActionEnum horizon) {
        this.horizon = horizon;
        return this;
    }

    /**
     * 상향 방향 장애물 회피 상태를 반환합니다.
     * 
     * @return 상향 방향 장애물 회피 상태
     */
    public SwitchActionEnum getUpside() {
        return upside;
    }

    /**
     * 상향 방향 장애물 회피 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param upside 설정할 상향 방향 장애물 회피 상태
     * @return 현재 ObstacleAvoidance 객체
     */
    public ObstacleAvoidance setUpside(SwitchActionEnum upside) {
        this.upside = upside;
        return this;
    }

    /**
     * 하향 방향 장애물 회피 상태를 반환합니다.
     * 
     * @return 하향 방향 장애물 회피 상태
     */
    public SwitchActionEnum getDownside() {
        return downside;
    }

    /**
     * 하향 방향 장애물 회피 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param downside 설정할 하향 방향 장애물 회피 상태
     * @return 현재 ObstacleAvoidance 객체
     */
    public ObstacleAvoidance setDownside(SwitchActionEnum downside) {
        this.downside = downside;
        return this;
    }
}
