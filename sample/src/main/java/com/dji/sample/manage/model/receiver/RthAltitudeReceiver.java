package com.dji.sample.manage.model.receiver;

import com.dji.sdk.cloudapi.device.OsdDockDrone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * RTH 고도 수신기 클래스
 * 
 * DJI Cloud API의 Return to Home(RTH) 고도 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. RTH 고도 관리
 *    - Return to Home 시의 비행 고도 관리 (미터 단위)
 *    - RTH 고도 값의 유효성 검증
 *    - RTH 고도 상태 변경 추적
 * 
 * 2. RTH 고도 값 검증
 *    - RTH 고도 값의 유효성 검증 (20m-500m 범위)
 *    - 최소/최대 RTH 고도 상수 관리
 *    - RTH 고도 값의 안전성 보장
 * 
 * 3. 상태 변경 감지
 *    - 현재 OSD 상태와 새로운 RTH 고도 값 비교
 *    - RTH 고도 값 변경 시에만 발행 가능하도록 제어
 *    - 불필요한 상태 업데이트 방지
 * 
 * 이 클래스는 드론의 Return to Home 기능을
 * 안전하고 효율적으로 관리할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RthAltitudeReceiver extends BasicDeviceProperty {

    /**
     * RTH 고도 값
     * Return to Home 시의 비행 고도 (미터 단위)
     */
    private Integer rthAltitude;

    /**
     * 최대 RTH 고도
     * 드론의 최대 허용 RTH 고도 (500미터)
     */
    private static final int RTH_ALTITUDE_MAX = 500;

    /**
     * 최소 RTH 고도
     * 드론의 최소 허용 RTH 고도 (20미터)
     */
    private static final int RTH_ALTITUDE_MIN = 20;

    /**
     * RTH 고도 값의 유효성을 검증합니다.
     * 
     * RTH 고도 값이 유효한 범위 내에 있는지 확인합니다.
     * 
     * @return RTH 고도 값의 유효성 여부
     */
    @Override
    public boolean valid() {
        return Objects.nonNull(rthAltitude) && rthAltitude >= RTH_ALTITUDE_MIN && rthAltitude <= RTH_ALTITUDE_MAX;
    }

    /**
     * 상태 변경이 필요한지 확인합니다.
     * 
     * 현재 OSD 상태와 새로운 RTH 고도 값을 비교하여
     * 변경이 필요한 경우에만 true를 반환합니다.
     * 
     * @param osd 현재 OSD 상태 정보
     * @return 상태 변경 필요 여부
     */
    @Override
    public boolean canPublish(OsdDockDrone osd) {
        return rthAltitude != osd.getRthAltitude().intValue();
    }
}
