package com.dji.sdk.cloudapi.control;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 이륙 상태 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 드론의 이륙 작업 상태를 정의합니다.
 * 작업 준비, 진행 중, 실패, 성공, 취소, 완료 등의 상태를 포함합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/17
 */
public enum TakeoffStatusEnum {

    /**
     * 작업 준비
     * 드론이 이륙을 준비하고 있습니다.
     */
    TASK_READY("task_ready", "The drone is preparing to take off."),

    /**
     * 웨이라인 진행 중
     * 드론이 이륙하고 있습니다.
     */
    WAYLINE_PROGRESS("wayline_progress", "The drone is taking off."),

    /**
     * 웨이라인 실패
     * 드론 이륙에 실패했습니다.
     */
    WAYLINE_FAILED("wayline_failed", "The drone failed to take off."),

    /**
     * 웨이라인 성공
     * 드론이 성공적으로 이륙했습니다.
     */
    WAYLINE_OK("wayline_ok", "The drone took off successfully."),

    /**
     * 웨이라인 취소
     * 드론 이륙 작업이 취소되었습니다.
     */
    WAYLINE_CANCEL("wayline_cancel", "The drone takeoff job has been cancelled."),

    /**
     * 작업 완료
     * 드론 이륙 작업이 완료되었습니다.
     */
    TASK_FINISH("task_finish", "The drone takeoff job is completed.");

    /**
     * 상태 문자열
     */
    private final String status;

    /**
     * 상태 메시지
     */
    private final String message;

    /**
     * 이륙 상태 열거형 생성자
     * 
     * @param status 상태 문자열
     * @param message 상태 메시지
     */
    TakeoffStatusEnum(String status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * 상태 문자열을 반환합니다.
     * 
     * @return 상태 문자열
     */
    @JsonValue
    public String getStatus() {
        return status;
    }

    /**
     * 상태 메시지를 반환합니다.
     * 
     * @return 상태 메시지
     */
    public String getMessage() {
        return message;
    }

    /**
     * 문자열로 이륙 상태를 찾습니다.
     * 
     * @param status 찾을 상태 문자열
     * @return 찾은 이륙 상태 열거형
     * @throws CloudSDKException 해당하는 상태를 찾을 수 없는 경우
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static TakeoffStatusEnum find(String status) {
        return Arrays.stream(values()).filter(statusEnum -> statusEnum.status.equals(status)).findAny()
                .orElseThrow(() -> new CloudSDKException(TakeoffStatusEnum.class, status));
    }
}
