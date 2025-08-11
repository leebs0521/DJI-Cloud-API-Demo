package com.dji.sample.manage.model.receiver;

import com.dji.sdk.cloudapi.device.OsdDockDrone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 고도 제한 수신기 클래스
 * 
 * DJI Cloud API의 드론 고도 제한 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 고도 제한 값 관리
 *    - 드론의 최대 비행 고도 제한 관리 (미터 단위)
 *    - 고도 제한 값의 유효성 검증
 *    - 고도 제한 상태 변경 추적
 * 
 * 2. 고도 제한 값 검증
 *    - 고도 제한 값의 유효성 검증 (20m-1500m 범위)
 *    - 최소/최대 고도 제한 상수 관리
 *    - 고도 제한 값의 안전성 보장
 * 
 * 3. 상태 변경 감지
 *    - 현재 OSD 상태와 새로운 고도 제한 값 비교
 *    - 고도 제한 값 변경 시에만 발행 가능하도록 제어
 *    - 불필요한 상태 업데이트 방지
 * 
 * 이 클래스는 드론의 고도 제한 기능을
 * 안전하고 효율적으로 관리할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeightLimitReceiver extends BasicDeviceProperty {

    /**
     * 최대 고도 제한
     * 드론의 최대 허용 고도 제한 (1500미터)
     */
    private static final int HEIGHT_LIMIT_MAX = 1500;

    /**
     * 최소 고도 제한
     * 드론의 최소 허용 고도 제한 (20미터)
     */
    private static final int HEIGHT_LIMIT_MIN = 20;

    /**
     * 고도 제한 값
     * 드론의 최대 비행 고도 제한 (미터 단위)
     */
    private Integer heightLimit;

    /**
     * 고도 제한 값의 유효성을 검증합니다.
     * 
     * 고도 제한 값이 유효한 범위 내에 있는지 확인합니다.
     * 
     * @return 고도 제한 값의 유효성 여부
     */
    @Override
    public boolean valid() {
        return Objects.nonNull(this.heightLimit) && this.heightLimit >= HEIGHT_LIMIT_MIN && this.heightLimit <= HEIGHT_LIMIT_MAX;
    }

    /**
     * 상태 변경이 필요한지 확인합니다.
     * 
     * 현재 OSD 상태와 새로운 고도 제한 값을 비교하여
     * 변경이 필요한 경우에만 true를 반환합니다.
     * 
     * @param osd 현재 OSD 상태 정보
     * @return 상태 변경 필요 여부
     */
    @Override
    public boolean canPublish(OsdDockDrone osd) {
        return heightLimit.intValue() != osd.getHeightLimit();
    }
}
