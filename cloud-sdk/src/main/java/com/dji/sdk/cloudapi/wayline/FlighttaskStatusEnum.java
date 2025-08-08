package com.dji.sdk.cloudapi.wayline;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 비행 작업 상태 열거형
 * 
 * 이 열거형은 웨이라인 비행 작업의 다양한 상태를 정의합니다.
 * 작업의 전송, 진행, 완료, 실패 등의 모든 상태를 관리하며,
 * 각 상태가 작업의 종료 상태인지 여부를 구분합니다.
 * 
 * 주요 구성 요소:
 * - SENT: 전송됨
 * - IN_PROGRESS: 진행 중
 * - OK: 성공
 * - PAUSED: 일시정지
 * - REJECTED: 거부됨
 * - FAILED: 실패
 * - CANCELED: 취소됨
 * - TIMEOUT: 시간 초과
 * - PARTIALLY_DONE: 부분 완료
 * 
 * 이 열거형은 비행 작업의 현재 상태를 추적하고
 * 적절한 처리 로직을 적용하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/8/17
 */
public enum FlighttaskStatusEnum {

    /**
     * 전송됨
     * 
     * 비행 작업이 드론에게 전송되었지만 아직 실행되지 않은 상태입니다.
     * 작업이 대기 중이며 실행 준비가 완료되었습니다.
     */
    SENT("sent", false),

    /**
     * 진행 중
     * 
     * 비행 작업이 현재 실행되고 있는 상태입니다.
     * 드론이 웨이라인에 따라 비행을 진행하고 있습니다.
     */
    IN_PROGRESS("in_progress", false),

    /**
     * 성공
     * 
     * 비행 작업이 성공적으로 완료된 상태입니다.
     * 모든 웨이포인트를 정상적으로 비행했습니다.
     */
    OK("ok", true),

    /**
     * 일시정지
     * 
     * 비행 작업이 일시정지된 상태입니다.
     * 사용자에 의해 또는 시스템에 의해 중단되었으며 재개 가능합니다.
     */
    PAUSED("paused", false),

    /**
     * 거부됨
     * 
     * 비행 작업이 거부된 상태입니다.
     * 안전 조건이나 권한 문제로 인해 작업이 거부되었습니다.
     */
    REJECTED("rejected", true),

    /**
     * 실패
     * 
     * 비행 작업이 실패한 상태입니다.
     * 시스템 오류나 예상치 못한 상황으로 인해 작업이 실패했습니다.
     */
    FAILED("failed", true),

    /**
     * 취소됨
     * 
     * 비행 작업이 취소된 상태입니다.
     * 사용자에 의해 또는 시스템에 의해 작업이 취소되었습니다.
     */
    CANCELED("canceled", true),

    /**
     * 시간 초과
     * 
     * 비행 작업이 시간 초과로 종료된 상태입니다.
     * 설정된 시간 내에 작업이 완료되지 않았습니다.
     */
    TIMEOUT("timeout", true),

    /**
     * 부분 완료
     * 
     * 비행 작업이 부분적으로 완료된 상태입니다.
     * 일부 웨이포인트는 성공했지만 일부는 실패했습니다.
     */
    PARTIALLY_DONE("partially_done", true);

    /**
     * 작업 상태 값
     * 
     * 각 작업 상태를 구분하는 문자열 값입니다.
     */
    private final String status;

    /**
     * 종료 상태 여부
     * 
     * 해당 상태가 작업의 최종 종료 상태인지 여부를 나타냅니다.
     * true: 작업이 완전히 종료됨
     * false: 작업이 계속 진행 가능함
     */
    private final boolean end;

    /**
     * 비행 작업 상태 열거형 생성자
     * 
     * @param status 작업 상태 값
     * @param end 종료 상태 여부
     */
    FlighttaskStatusEnum(String status, boolean end) {
        this.status = status;
        this.end = end;
    }

    /**
     * 작업 상태 값을 반환합니다.
     * 
     * @return 작업 상태 값
     */
    @JsonValue
    public String getStatus() {
        return status;
    }

    /**
     * 종료 상태 여부를 반환합니다.
     * 
     * @return 종료 상태 여부
     */
    public boolean isEnd() {
        return end;
    }

    /**
     * 작업 상태 값으로 비행 작업 상태를 찾습니다.
     * 
     * 주어진 상태 값에 해당하는 열거형을 반환합니다.
     * 해당하는 상태가 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param status 찾을 작업 상태 값
     * @return 해당하는 FlighttaskStatusEnum 열거형
     * @throws CloudSDKException 해당하는 상태가 없을 경우
     */
    @JsonCreator
    public static FlighttaskStatusEnum find(String status) {
        return Arrays.stream(values()).filter(statusEnum -> statusEnum.status.equals(status)).findAny()
                .orElseThrow(() -> new CloudSDKException(FlighttaskStatusEnum.class, status));
    }
}


