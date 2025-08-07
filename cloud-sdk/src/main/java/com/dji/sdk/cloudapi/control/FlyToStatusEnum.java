package com.dji.sdk.cloudapi.control;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 지정 지점 비행 상태 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 지정 지점 비행 작업의 상태를 정의합니다.
 * 진행 중, 실패, 성공, 취소 등의 상태를 포함합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/14
 */
public enum FlyToStatusEnum {

    /**
     * 웨이라인 진행 중
     * 지정 지점 비행 작업이 진행 중입니다.
     */
    WAYLINE_PROGRESS("wayline_progress", "The FlyTo job is in progress."),

    /**
     * 웨이라인 실패
     * 지정 지점 비행 작업 실행에 실패했습니다.
     */
    WAYLINE_FAILED("wayline_failed", "The FlyTo job execution failed."),

    /**
     * 웨이라인 성공
     * 지정 지점 비행 작업이 성공적으로 실행되었습니다.
     */
    WAYLINE_OK("wayline_ok", "The FlyTo job executed successfully."),

    /**
     * 웨이라인 취소
     * 지정 지점 비행 작업이 취소되었습니다.
     */
    WAYLINE_CANCEL("wayline_cancel", "The FlyTo job is closed.");

    /**
     * 상태 문자열
     */
    private final String status;

    /**
     * 상태 메시지
     */
    private final String message;

    /**
     * 지정 지점 비행 상태 열거형 생성자
     * 
     * @param status 상태 문자열
     * @param message 상태 메시지
     */
    FlyToStatusEnum(String status, String message) {
        this.status = status;
        this.message = message;
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
     * 상태 문자열을 반환합니다.
     * 
     * @return 상태 문자열
     */
    @JsonValue
    public String getStatus() {
        return status;
    }

    /**
     * 문자열로 지정 지점 비행 상태를 찾습니다.
     * 
     * @param status 찾을 상태 문자열
     * @return 찾은 지정 지점 비행 상태 열거형
     * @throws CloudSDKException 해당하는 상태를 찾을 수 없는 경우
     */
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static FlyToStatusEnum find(String status) {
        return Arrays.stream(values()).filter(statusEnum -> statusEnum.status.equals(status)).findAny()
                .orElseThrow(() -> new CloudSDKException(FlyToStatusEnum.class, status));
    }
}
