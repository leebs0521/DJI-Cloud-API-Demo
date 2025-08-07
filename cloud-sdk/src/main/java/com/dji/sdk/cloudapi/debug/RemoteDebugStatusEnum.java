package com.dji.sdk.cloudapi.debug;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 원격 디버그 상태 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 원격 디버그의 상태를 정의합니다.
 * 전송됨, 진행 중, 성공, 일시정지, 거부됨, 실패, 취소됨, 타임아웃 등의 상태를 포함합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/8/17
 */
public enum RemoteDebugStatusEnum {

    /**
     * 전송됨
     * 원격 디버그 요청이 전송된 상태
     */
    SENT("sent", false),

    /**
     * 진행 중
     * 원격 디버그가 진행 중인 상태
     */
    IN_PROGRESS("in_progress", false),

    /**
     * 성공
     * 원격 디버그가 성공적으로 완료된 상태
     */
    OK("ok", true),

    /**
     * 일시정지
     * 원격 디버그가 일시정지된 상태
     */
    PAUSED("paused", false),

    /**
     * 거부됨
     * 원격 디버그 요청이 거부된 상태
     */
    REJECTED("rejected", true),

    /**
     * 실패
     * 원격 디버그가 실패한 상태
     */
    FAILED("failed", true),

    /**
     * 취소됨
     * 원격 디버그가 취소된 상태
     */
    CANCELED("canceled", true),

    /**
     * 타임아웃
     * 원격 디버그가 타임아웃된 상태
     */
    TIMEOUT("timeout", true);

    /**
     * 상태 문자열
     */
    private final String status;

    /**
     * 종료 여부
     * 해당 상태가 디버그 작업의 종료를 의미하는지 여부
     */
    private final boolean end;

    /**
     * 원격 디버그 상태 열거형 생성자
     * 
     * @param status 상태 문자열
     * @param end 종료 여부
     */
    RemoteDebugStatusEnum(String status, boolean end) {
        this.status = status;
        this.end = end;
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
     * 종료 여부를 반환합니다.
     * 
     * @return 종료 여부
     */
    public boolean isEnd() {
        return end;
    }

    /**
     * 문자열로 원격 디버그 상태를 찾습니다.
     * 
     * @param status 찾을 상태 문자열
     * @return 찾은 원격 디버그 상태 열거형
     * @throws CloudSDKException 해당하는 상태를 찾을 수 없는 경우
     */
    @JsonCreator
    public static RemoteDebugStatusEnum find(String status) {
        return Arrays.stream(values()).filter(statusEnum -> statusEnum.status.equals(status)).findAny()
                .orElseThrow(() -> new CloudSDKException(RemoteDebugStatusEnum.class, status));
    }
}


