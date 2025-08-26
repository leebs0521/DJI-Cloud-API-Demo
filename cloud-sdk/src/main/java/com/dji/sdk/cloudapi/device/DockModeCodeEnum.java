package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 도크 모드 코드 열거형 클래스
 * <p>
 * 이 클래스는 도크의 동작 모드를 정의합니다.
 * 대기, 디버깅, 원격 디버깅, 업그레이드, 작업 중 등의 모드를 나타냅니다.
 *
 * @author sean
 * @version 1.4
 * @date 2023/2/28
 */
public enum DockModeCodeEnum {

    /**
     * 대기 (모드값: 0)
     */
    IDLE(0),

    /**
     * 디버깅 (모드값: 1)
     */
    DEBUGGING(1),

    /**
     * 원격 디버깅 (모드값: 2)
     */
    REMOTE_DEBUGGING(2),

    /**
     * 업그레이드 (모드값: 3)
     */
    UPGRADING(3),

    /**
     * 작업 중 (모드값: 4)
     */
    WORKING(4),

    /**
     * 보정 필요 (모드값: 5) TODO 경고 필요
     */
    CALIBRATED(5),

    ;

    /**
     * 도크 모드 코드 정수값
     */
    private final int code;

    /**
     * 도크 모드 코드 열거형 생성자
     *
     * @param code 도크 모드 코드 정수값
     */
    DockModeCodeEnum(int code) {
        this.code = code;
    }

    /**
     * 도크 모드 코드 정수값을 반환합니다.
     *
     * @return 도크 모드 코드 정수값
     */
    @JsonValue
    public int getCode() {
        return code;
    }

    /**
     * 정수값으로 도크 모드 코드를 찾습니다.
     *
     * @param code 찾을 도크 모드 코드 정수값
     * @return 찾은 도크 모드 코드 열거형
     * @throws CloudSDKException 해당하는 코드를 찾을 수 없는 경우
     */
    @JsonCreator
    public static DockModeCodeEnum find(int code) {
        return Arrays.stream(values()).filter(modeCode -> modeCode.code == code).findAny()
                .orElseThrow(() -> new CloudSDKException(DockModeCodeEnum.class, code));
    }
}
