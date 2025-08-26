package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 비행 임무 단계 코드 열거형 클래스
 * 
 * 이 클래스는 비행 임무의 단계를 정의합니다.
 * 임무 준비, 임무 실행, 상태 복구, 사용자 정의 비행 영역 업데이트 등의 단계를 나타냅니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/30
 */
public enum FlighttaskStepCodeEnum {

    /**
     * 임무 준비 (코드값: 0)
     */
    TASK_PREPARING(0),

    /**
     * 임무 실행 (코드값: 1)
     */
    TASK_OPERATING(1),

    /**
     * 상태 복구 (코드값: 2)
     */
    STATE_RECOVERING(2),

    /**
     * 사용자 정의 비행 영역 업데이트 (코드값: 3)
     */
    CUSTOM_FLIGHT_AREA_UPDATING(3),

    /**
     * 오프라인 지도 업데이트 (코드값: 4)
     */
    OFFLINE_MAP_UPDATING(4),

    /**
     * 대기 (코드값: 5)
     */
    IDLE(5),

    /**
     * 항공기 이상
     */
    AIRCRAFT_ERROR(255),

    /**
     * 알 수 없음 (코드값: 256)
     */
    UNKNOWN(256),
    ;

    /**
     * 비행 임무 단계 코드 정수값
     */
    private final int code;

    /**
     * 비행 임무 단계 코드 열거형 생성자
     * 
     * @param code 비행 임무 단계 코드 정수값
     */
    FlighttaskStepCodeEnum(int code) {
        this.code = code;
    }

    /**
     * 비행 임무 단계 코드 정수값을 반환합니다.
     * 
     * @return 비행 임무 단계 코드 정수값
     */
    @JsonValue
    public int getCode() {
        return code;
    }

    /**
     * 정수값으로 비행 임무 단계 코드를 찾습니다.
     * 
     * @param code 찾을 비행 임무 단계 코드 정수값
     * @return 찾은 비행 임무 단계 코드 열거형
     * @throws CloudSDKException 해당하는 코드를 찾을 수 없는 경우
     */
    @JsonCreator
    public static FlighttaskStepCodeEnum find(int code) {
        return Arrays.stream(values()).filter(codeEnum -> codeEnum.code == code).findAny()
            .orElseThrow(() -> new CloudSDKException(FlighttaskStepCodeEnum.class, code));
    }

}
