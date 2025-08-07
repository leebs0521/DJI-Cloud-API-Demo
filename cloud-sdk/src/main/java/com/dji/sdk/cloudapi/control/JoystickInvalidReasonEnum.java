package com.dji.sdk.cloudapi.control;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 조이스틱 무효화 이유 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 조이스틱이 무효화되는 이유를 정의합니다.
 * RC 연결 손실, 배터리 부족, 경계 근접 등의 상황을 포함합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/14
 */
public enum JoystickInvalidReasonEnum {

    /**
     * RC 연결 손실
     * 원격 조종기가 연결이 끊어짐
     */
    RC_LOST(0, "The remote controller is lost."),

    /**
     * 배터리 부족으로 복귀
     * 배터리가 부족하여 드론이 자동으로 복귀함
     */
    BATTERY_LOW_GO_HOME(1, "Due to low battery, the drone automatically returned home."),

    /**
     * 배터리 심각 부족으로 착륙
     * 배터리가 심각하게 부족하여 드론이 자동으로 착륙함
     */
    BATTERY_SUPER_LOW_LANDING(2, "Due to the serious low battery, the drone landed automatically."),

    /**
     * 경계 근접
     * 드론이 비행 금지 구역 근처에 있음
     */
    NEAR_BOUNDARY(3, "The drone is near a not-fly zone."),

    /**
     * RC 권한 획득
     * 원격 조종기가 제어 권한을 획득함
     */
    RC_AUTHORITY(4, "The remote controller grabs control authority.");

    /**
     * 무효화 이유 값
     */
    private final int reason;

    /**
     * 무효화 이유 메시지
     */
    private final String message;

    /**
     * 조이스틱 무효화 이유 열거형 생성자
     * 
     * @param reason 무효화 이유 값
     * @param message 무효화 이유 메시지
     */
    JoystickInvalidReasonEnum(int reason, String message) {
        this.reason = reason;
        this.message = message;
    }

    /**
     * 무효화 이유 값을 반환합니다.
     * 
     * @return 무효화 이유 값
     */
    @JsonValue
    public int getVal() {
        return reason;
    }

    /**
     * 무효화 이유 메시지를 반환합니다.
     * 
     * @return 무효화 이유 메시지
     */
    public String getMessage() {
        return message;
    }

    /**
     * 정수 값으로 조이스틱 무효화 이유를 찾습니다.
     * 
     * @param reason 찾을 무효화 이유 값
     * @return 찾은 조이스틱 무효화 이유 열거형
     * @throws CloudSDKException 해당하는 무효화 이유를 찾을 수 없는 경우
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static JoystickInvalidReasonEnum find(int reason) {
        return Arrays.stream(values()).filter(reasonEnum -> reasonEnum.reason == reason).findAny()
                .orElseThrow(() -> new CloudSDKException(JoystickInvalidReasonEnum.class, reason));
    }
}
