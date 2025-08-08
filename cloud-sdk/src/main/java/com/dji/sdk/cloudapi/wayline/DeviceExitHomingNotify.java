package com.dji.sdk.cloudapi.wayline;

/**
 * 디바이스 홈포인트 귀환 종료 알림 클래스
 * 
 * 이 클래스는 드론이 홈포인트 귀환(RTH) 상태에서 벗어났을 때
 * 발생하는 알림 데이터를 정의합니다.
 * 
 * 주요 구성 요소:
 * - action: 종료 액션
 * - sn: 시리얼 번호
 * - reason: 종료 이유
 * 
 * 이 클래스는 드론이 홈포인트 귀환을 중단하고
 * 다른 비행 모드로 전환했을 때 알림을 받는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/6
 */
public class DeviceExitHomingNotify {

    /**
     * 종료 액션
     * 
     * 홈포인트 귀환 종료 시 수행할 액션을 나타냅니다.
     * 호버링, 착륙, 웨이라인 복구 등의 액션을 정의합니다.
     */
    private ExitingRTHActionEnum action;

    /**
     * 시리얼 번호
     * 
     * 홈포인트 귀환을 종료한 디바이스의 시리얼 번호입니다.
     * 디바이스를 고유하게 식별하는 데 사용됩니다.
     */
    private String sn;

    /**
     * 종료 이유
     * 
     * 홈포인트 귀환이 종료된 이유를 나타냅니다.
     * 사용자 명령, 시스템 오류, 안전 조건 등의 이유를 정의합니다.
     */
    private ExitingRTHReasonEnum reason;

    public DeviceExitHomingNotify() {
    }

    @Override
    public String toString() {
        return "DeviceExitHomingNotify{" +
                "action=" + action +
                ", sn='" + sn + '\'' +
                ", reason=" + reason +
                '}';
    }

    /**
     * 종료 액션을 반환합니다.
     * 
     * @return 종료 액션
     */
    public ExitingRTHActionEnum getAction() {
        return action;
    }

    /**
     * 종료 액션을 설정합니다.
     * 
     * @param action 종료 액션
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DeviceExitHomingNotify setAction(ExitingRTHActionEnum action) {
        this.action = action;
        return this;
    }

    /**
     * 시리얼 번호를 반환합니다.
     * 
     * @return 시리얼 번호
     */
    public String getSn() {
        return sn;
    }

    /**
     * 시리얼 번호를 설정합니다.
     * 
     * @param sn 시리얼 번호
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DeviceExitHomingNotify setSn(String sn) {
        this.sn = sn;
        return this;
    }

    /**
     * 종료 이유를 반환합니다.
     * 
     * @return 종료 이유
     */
    public ExitingRTHReasonEnum getReason() {
        return reason;
    }

    /**
     * 종료 이유를 설정합니다.
     * 
     * @param reason 종료 이유
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public DeviceExitHomingNotify setReason(ExitingRTHReasonEnum reason) {
        this.reason = reason;
        return this;
    }
}
