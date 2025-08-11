package com.dji.sample.manage.model.receiver;

import com.dji.sdk.cloudapi.device.ObstacleAvoidance;
import com.dji.sdk.cloudapi.device.OsdDockDrone;
import com.dji.sdk.cloudapi.device.SwitchActionEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

/**
 * 장애물 회피 수신기 클래스
 * 
 * DJI Cloud API의 장애물 회피 상태 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 장애물 회피 방향별 상태 관리
 *    - 수평 방향 장애물 회피 상태 관리
 *    - 상방향 장애물 회피 상태 관리
 *    - 하방향 장애물 회피 상태 관리
 * 
 * 2. 장애물 회피 상태 검증
 *    - 각 방향별 장애물 회피 상태의 유효성 검증
 *    - 최소 하나의 방향이 활성화되어야 함
 *    - 장애물 회피 기능의 안전성 보장
 * 
 * 3. 상태 변경 감지
 *    - 현재 OSD 상태와 새로운 상태 비교
 *    - 장애물 회피 상태 변경 시에만 발행 가능하도록 제어
 *    - 불필요한 상태 업데이트 방지
 * 
 * 이 클래스는 드론의 장애물 회피 기능을
 * 안전하고 효율적으로 관리할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/27
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ObstacleAvoidanceReceiver extends BasicDeviceProperty {

    /**
     * 수평 방향 장애물 회피
     * 수평 방향의 장애물 회피 기능 상태
     */
    private SwitchActionEnum horizon;

    /**
     * 상방향 장애물 회피
     * 상방향의 장애물 회피 기능 상태
     */
    private SwitchActionEnum upside;

    /**
     * 하방향 장애물 회피
     * 하방향의 장애물 회피 기능 상태
     */
    private SwitchActionEnum downside;

    /**
     * 장애물 회피 상태의 유효성을 검증합니다.
     * 
     * 최소 하나의 방향에서 장애물 회피가 활성화되어야 합니다.
     * 
     * @return 장애물 회피 상태의 유효성 여부
     */
    public boolean valid() {
        return Objects.nonNull(this.horizon) || Objects.nonNull(this.upside) || Objects.nonNull(this.downside);
    }

    /**
     * 상태 변경이 필요한지 확인합니다.
     * 
     * 현재 OSD 상태와 새로운 장애물 회피 상태를 비교하여
     * 변경이 필요한 경우에만 true를 반환합니다.
     * 
     * @param osd 현재 OSD 상태 정보
     * @return 상태 변경 필요 여부
     */
    @Override
    public boolean canPublish(OsdDockDrone osd) {
        ObstacleAvoidance obstacleAvoidance = osd.getObstacleAvoidance();
        return (Objects.nonNull(obstacleAvoidance.getHorizon()) && horizon != obstacleAvoidance.getHorizon())
                || (Objects.nonNull(obstacleAvoidance.getUpside()) && upside != obstacleAvoidance.getUpside())
                || (Objects.nonNull(obstacleAvoidance.getDownside()) && downside != obstacleAvoidance.getDownside());
    }
}
