package com.dji.sample.manage.model.receiver;

import com.dji.sdk.cloudapi.device.OsdDockDrone;
import com.dji.sdk.cloudapi.device.SwitchActionEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

/**
 * 야간 조명 상태 수신기 클래스
 * 
 * DJI Cloud API의 야간 조명 상태 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 야간 조명 상태 관리
 *    - 야간 조명의 켜기/끄기 상태 관리
 *    - SwitchActionEnum을 활용한 상태 분류
 *    - 야간 조명 상태 변경 추적
 * 
 * 2. JSON 역직렬화 지원
 *    - @JsonCreator 어노테이션으로 JSON 역직렬화 지원
 *    - 정수 값으로부터 SwitchActionEnum 자동 변환
 *    - API 요청과의 호환성 보장
 * 
 * 3. 상태 변경 감지
 *    - 현재 OSD 상태와 새로운 상태 비교
 *    - 야간 조명 상태 변경 시에만 발행 가능하도록 제어
 *    - 불필요한 상태 업데이트 방지
 * 
 * 이 클래스는 드론의 야간 조명 기능을
 * 안전하고 효율적으로 관리할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/25
 */
public class NightLightsStateReceiver extends BasicDeviceProperty {

    /**
     * 야간 조명 상태
     * 야간 조명의 켜기/끄기 상태
     */
    private SwitchActionEnum nightLightsState;

    /**
     * 생성자
     * 정수 값으로부터 SwitchActionEnum을 생성합니다.
     * 
     * @param nightLightsState 야간 조명 상태 정수 값
     */
    @JsonCreator
    public NightLightsStateReceiver(Integer nightLightsState) {
        this.nightLightsState = SwitchActionEnum.find(nightLightsState);
    }

    /**
     * 야간 조명 상태의 유효성을 검증합니다.
     * 
     * 야간 조명 상태가 null이 아닌지 확인합니다.
     * 
     * @return 야간 조명 상태의 유효성 여부
     */
    @Override
    public boolean valid() {
        return Objects.nonNull(nightLightsState);
    }

    /**
     * 상태 변경이 필요한지 확인합니다.
     * 
     * 현재 OSD 상태와 새로운 야간 조명 상태를 비교하여
     * 변경이 필요한 경우에만 true를 반환합니다.
     * 
     * @param osd 현재 OSD 상태 정보
     * @return 상태 변경 필요 여부
     */
    @Override
    public boolean canPublish(OsdDockDrone osd) {
        return nightLightsState != osd.getNightLightsState();
    }
}
