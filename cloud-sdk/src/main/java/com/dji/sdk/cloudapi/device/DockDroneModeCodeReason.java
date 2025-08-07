package com.dji.sdk.cloudapi.device;

/**
 * 도크 드론 모드 코드 이유 클래스
 * 
 * 이 클래스는 도크에 연결된 드론의 모드 코드 이유 정보를 담습니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/20
 */
public class DockDroneModeCodeReason {

    /**
     * 모드 코드 이유
     */
    private ModeCodeReasonEnum modeCodeReason;

    /**
     * 기본 생성자
     */
    public DockDroneModeCodeReason() {
    }

    @Override
    public String toString() {
        return "DockDroneModeCodeReason{" +
                "modeCodeReason=" + modeCodeReason +
                '}';
    }

    /**
     * 모드 코드 이유를 반환합니다.
     * 
     * @return 모드 코드 이유
     */
    public ModeCodeReasonEnum getModeCodeReason() {
        return modeCodeReason;
    }

    /**
     * 모드 코드 이유를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param modeCodeReason 설정할 모드 코드 이유
     * @return 현재 DockDroneModeCodeReason 객체
     */
    public DockDroneModeCodeReason setModeCodeReason(ModeCodeReasonEnum modeCodeReason) {
        this.modeCodeReason = modeCodeReason;
        return this;
    }
}
