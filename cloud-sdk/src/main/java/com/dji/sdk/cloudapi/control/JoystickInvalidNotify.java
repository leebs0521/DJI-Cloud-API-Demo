package com.dji.sdk.cloudapi.control;

/**
 * 조이스틱 무효 알림 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 조이스틱이 무효화되었을 때의 알림을 담는 클래스입니다.
 * 조이스틱 무효화 이유를 포함하여 조이스틱 무효화 알림을 관리합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/14
 */
public class JoystickInvalidNotify {

    /**
     * 조이스틱 무효화 이유
     * 조이스틱이 무효화된 이유
     */
    private JoystickInvalidReasonEnum reason;

    /**
     * 기본 생성자
     */
    public JoystickInvalidNotify() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "JoystickInvalidNotify{" +
                "reason=" + reason +
                '}';
    }

    /**
     * 조이스틱 무효화 이유를 반환합니다.
     * 
     * @return 조이스틱 무효화 이유
     */
    public JoystickInvalidReasonEnum getReason() {
        return reason;
    }

    /**
     * 조이스틱 무효화 이유를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param reason 설정할 조이스틱 무효화 이유
     * @return 현재 JoystickInvalidNotify 객체
     */
    public JoystickInvalidNotify setReason(JoystickInvalidReasonEnum reason) {
        this.reason = reason;
        return this;
    }
}
