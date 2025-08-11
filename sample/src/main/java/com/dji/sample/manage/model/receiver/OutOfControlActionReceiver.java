package com.dji.sample.manage.model.receiver;

import com.dji.sdk.cloudapi.device.OsdDockDrone;
import com.dji.sdk.cloudapi.device.RcLostActionEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

/**
 * 통신 끊김 동작 수신기 클래스
 * 
 * DJI Cloud API의 통신 끊김 시 동작 정보를 수신하는 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. RC 신호 손실 동작 관리
 *    - RC 신호 손실 시 수행할 동작 관리
 *    - RcLostActionEnum을 활용한 동작 분류
 *    - 통신 끊김 동작 변경 추적
 * 
 * 2. JSON 역직렬화 지원
 *    - @JsonCreator 어노테이션으로 JSON 역직렬화 지원
 *    - 정수 값으로부터 RcLostActionEnum 자동 변환
 *    - API 요청과의 호환성 보장
 * 
 * 3. 상태 변경 감지
 *    - 현재 OSD 상태와 새로운 상태 비교
 *    - 통신 끊김 동작 변경 시에만 발행 가능하도록 제어
 *    - 불필요한 상태 업데이트 방지
 * 
 * 이 클래스는 드론의 통신 끊김 시 안전 동작을
 * 안전하고 효율적으로 관리할 수 있도록
 * 지원합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/3
 */
public class OutOfControlActionReceiver extends BasicDeviceProperty {

    /**
     * RC 신호 손실 동작
     * RC 신호가 손실되었을 때 수행할 동작
     */
    private RcLostActionEnum rcLostAction;

    /**
     * 생성자
     * 정수 값으로부터 RcLostActionEnum을 생성합니다.
     * 
     * @param rcLostAction RC 신호 손실 동작 정수 값
     */
    @JsonCreator
    public OutOfControlActionReceiver(Integer rcLostAction) {
        this.rcLostAction = RcLostActionEnum.find(rcLostAction);
    }

    /**
     * RC 신호 손실 동작의 유효성을 검증합니다.
     * 
     * RC 신호 손실 동작이 null이 아닌지 확인합니다.
     * 
     * @return RC 신호 손실 동작의 유효성 여부
     */
    @Override
    public boolean valid() {
        return Objects.nonNull(rcLostAction);
    }

    /**
     * 상태 변경이 필요한지 확인합니다.
     * 
     * 현재 OSD 상태와 새로운 RC 신호 손실 동작을 비교하여
     * 변경이 필요한 경우에만 true를 반환합니다.
     * 
     * @param osd 현재 OSD 상태 정보
     * @return 상태 변경 필요 여부
     */
    @Override
    public boolean canPublish(OsdDockDrone osd) {
        return rcLostAction != osd.getRcLostAction();
    }
}
