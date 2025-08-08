package com.dji.sdk.cloudapi.property;

import com.dji.sdk.cloudapi.device.ObstacleAvoidance;
import com.dji.sdk.common.BaseModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 장애물 회피 설정 클래스
 * 
 * 이 클래스는 드론의 장애물 회피 기능을 설정하기 위한 데이터를 정의합니다.
 * BaseModel을 상속받아 유효성 검사를 지원하며, 안전한 비행을 위한
 * 장애물 회피 시스템을 관리합니다.
 * 
 * 주요 구성 요소:
 * - obstacleAvoidance: 장애물 회피 설정 객체
 * 
 * 이 클래스는 드론의 장애물 회피 기능을 활성화하거나 비활성화하는 데 사용됩니다.
 * 안전한 자동 비행을 위해 장애물 감지 및 회피 기능을 제어할 수 있습니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/27
 */
public class ObstacleAvoidanceSet extends BaseModel {

    /**
     * 장애물 회피 설정
     * 
     * 드론의 장애물 회피 기능을 설정하는 객체입니다.
     * ObstacleAvoidance 클래스를 사용하여 다양한 장애물 회피 옵션을 설정할 수 있습니다.
     * 
     * 설정 가능한 옵션:
     * - 활성화/비활성화
     * - 감지 거리 설정
     * - 회피 동작 설정
     * - 특정 방향별 회피 설정
     */
    @Valid
    @NotNull
    private ObstacleAvoidance obstacleAvoidance;

    public ObstacleAvoidanceSet() {
    }

    @Override
    public String toString() {
        return "ObstacleAvoidanceSet{" +
                "obstacleAvoidance=" + obstacleAvoidance +
                '}';
    }

    /**
     * 장애물 회피 설정을 반환합니다.
     * 
     * @return 장애물 회피 설정 객체
     */
    public ObstacleAvoidance getObstacleAvoidance() {
        return obstacleAvoidance;
    }

    /**
     * 장애물 회피 설정을 설정합니다.
     * 
     * @param obstacleAvoidance 장애물 회피 설정 객체
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public ObstacleAvoidanceSet setObstacleAvoidance(ObstacleAvoidance obstacleAvoidance) {
        this.obstacleAvoidance = obstacleAvoidance;
        return this;
    }
}
