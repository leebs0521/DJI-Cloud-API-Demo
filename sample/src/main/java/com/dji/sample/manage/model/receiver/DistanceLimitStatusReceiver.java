package com.dji.sample.manage.model.receiver;

import com.dji.sdk.cloudapi.device.DockDistanceLimitStatus;
import com.dji.sdk.cloudapi.device.OsdDockDrone;
import com.dji.sdk.cloudapi.device.SwitchActionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 거리 제한 상태 수신기 클래스
 * 
 * DJI Cloud API의 드론 거리 제한 상태 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 거리 제한 상태 관리
 *    - 거리 제한 스위치 상태 관리 (켜기/끄기)
 *    - 거리 제한 값 관리 (미터 단위)
 *    - 거리 제한 상태 변경 추적
 * 
 * 2. 거리 제한 값 검증
 *    - 거리 제한 값의 유효성 검증 (15m-8000m 범위)
 *    - 최소/최대 거리 제한 상수 관리
 *    - 거리 제한 값의 안전성 보장
 * 
 * 3. 상태 변경 감지
 *    - 현재 상태와 새로운 상태 비교
 *    - 상태 변경 시에만 발행 가능하도록 제어
 *    - 불필요한 상태 업데이트 방지
 * 
 * 이 클래스는 드론의 거리 제한 기능을
 * 안전하고 효율적으로 관리할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistanceLimitStatusReceiver extends BasicDeviceProperty {

    /**
     * 거리 제한 상태
     * 거리 제한 기능의 켜기/끄기 상태
     */
    private SwitchActionEnum state;

    /**
     * 거리 제한 값
     * 드론의 최대 비행 거리 제한 (미터 단위)
     */
    private Integer distanceLimit;

    /**
     * 최대 거리 제한
     * 드론의 최대 허용 거리 제한 (8000미터)
     */
    private static final int DISTANCE_MAX = 8000;

    /**
     * 최소 거리 제한
     * 드론의 최소 허용 거리 제한 (15미터)
     */
    private static final int DISTANCE_MIN = 15;

    /**
     * 거리 제한 값의 유효성을 검증합니다.
     * 
     * 거리 제한 값이 유효한 범위 내에 있는지 확인합니다.
     * state와 distanceLimit이 모두 null이면 false를 반환합니다.
     * 
     * @return 거리 제한 값의 유효성 여부
     */
    @Override
    public boolean valid() {
        if (Objects.isNull(state) && Objects.isNull(distanceLimit)) {
            return false;
        }
        if (Objects.nonNull(distanceLimit)) {
            return distanceLimit >= DISTANCE_MIN && distanceLimit <= DISTANCE_MAX;
        }
        return true;
    }

    /**
     * 상태 변경이 필요한지 확인합니다.
     * 
     * 현재 OSD 상태와 새로운 상태를 비교하여
     * 변경이 필요한 경우에만 true를 반환합니다.
     * 
     * @param osd 현재 OSD 상태 정보
     * @return 상태 변경 필요 여부
     */
    @Override
    public boolean canPublish(OsdDockDrone osd) {
        DockDistanceLimitStatus distanceLimitStatus = osd.getDistanceLimitStatus();
        return (Objects.nonNull(distanceLimitStatus.getState()) && distanceLimitStatus.getState() != this.state)
                || (Objects.nonNull(distanceLimitStatus.getDistanceLimit())
                        && distanceLimitStatus.getDistanceLimit().intValue() != this.distanceLimit);
    }
}
