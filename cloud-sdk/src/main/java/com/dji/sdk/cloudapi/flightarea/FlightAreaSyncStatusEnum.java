package com.dji.sdk.cloudapi.flightarea;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 비행 구역 동기화 상태 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 비행 구역 동기화의 현재 상태를 정의합니다.
 * 클라우드에서 디바이스로 비행 구역 파일을 동기화하는 과정에서
 * 발생할 수 있는 다양한 상태와 해당 상태가 종료 상태인지 여부를 포함합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/17
 */
public enum FlightAreaSyncStatusEnum {

    /**
     * 동기화 대기
     * 동기화 작업이 대기 상태이며, 아직 시작되지 않음
     */
    WAIT_SYNC("wait_sync", false),

    /**
     * 전환 실패
     * 동기화 과정에서 전환 작업이 실패한 상태
     */
    SWITCH_FAIL("switch_fail", false),

    /**
     * 동기화 중
     * 현재 동기화 작업이 진행 중인 상태
     */
    SYNCHRONIZING("synchronizing", false),

    /**
     * 동기화 완료
     * 동기화 작업이 성공적으로 완료된 상태
     */
    SYNCHRONIZED("synchronized", true),

    /**
     * 동기화 실패
     * 동기화 작업이 실패한 상태
     */
    FAIL("fail", true),

    ;

    /**
     * 동기화 상태
     * 각 상태에 대한 고유한 문자열 식별자
     */
    private final String status;

    /**
     * 종료 여부
     * 해당 상태가 동기화 과정의 종료 상태인지 여부
     * true: 종료 상태 (SYNCHRONIZED, FAIL)
     * false: 진행 중 상태 (WAIT_SYNC, SWITCH_FAIL, SYNCHRONIZING)
     */
    private final boolean end;

    /**
     * 비행 구역 동기화 상태 열거형 생성자
     * 
     * @param status 동기화 상태
     * @param end 종료 여부
     */
    FlightAreaSyncStatusEnum(String status, boolean end) {
        this.status = status;
        this.end = end;
    }

    /**
     * 동기화 상태를 반환합니다.
     * JSON 직렬화 시 사용됩니다.
     * 
     * @return 동기화 상태 문자열
     */
    @JsonValue
    public String getStatus() {
        return status;
    }

    /**
     * 종료 여부를 반환합니다.
     * 
     * @return 종료 여부 (true: 종료 상태, false: 진행 중 상태)
     */
    public boolean isEnd() {
        return end;
    }

    /**
     * 문자열로 비행 구역 동기화 상태를 찾습니다.
     * JSON 역직렬화 시 사용됩니다.
     * 
     * @param status 찾을 동기화 상태 문자열
     * @return 찾은 비행 구역 동기화 상태 열거형
     * @throws CloudSDKException 해당하는 상태를 찾을 수 없는 경우
     */
    @JsonCreator
    public static FlightAreaSyncStatusEnum find(String status) {
        return Arrays.stream(values()).filter(statusEnum -> statusEnum.status.equals(status)).findAny()
            .orElseThrow(() -> new CloudSDKException(FlightAreaSyncStatusEnum.class, status));
    }
}
