package com.dji.sdk.cloudapi.control;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * POI 상태 이유 열거형 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 POI(Point of Interest) 모드의 상태 이유를 정의합니다.
 * 정상 상태와 다양한 오류 상황을 포함합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public enum PoiStatusReasonEnum {

    /**
     * 정상
     * POI 모드가 정상적으로 작동 중
     */
    NORMAL(0),

    /**
     * 부적합한 페이로드
     * POI 모드에 적합하지 않은 페이로드가 설치됨
     */
    UNADAPTED_PAYLOAD(1),

    /**
     * 지원하지 않는 카메라 모드
     * POI 모드에서 지원하지 않는 카메라 모드
     */
    CAMERA_MODE_NOT_SUPPORTED(2),

    /**
     * 잘못된 명령
     * POI 모드에서 잘못된 명령이 실행됨
     */
    ILLEGAL_CMD(3),

    /**
     * 위치 측정 실패
     * 드론의 위치 측정에 실패함
     */
    POSITIONING_FAILED(4),

    /**
     * 지상에 있음
     * 드론이 지상에 있어 POI 모드 실행 불가
     */
    ON_THE_GROUND(5),

    /**
     * 드론 모드 오류
     * 드론의 모드에 오류가 발생함
     */
    DRONE_MODE_ERROR(6),

    /**
     * 사용 불가능한 모드
     * 현재 상황에서 POI 모드를 사용할 수 없음
     */
    NOT_AVAILABLE_MODE(7),

    /**
     * RC 연결 해제
     * RC(Remote Control) 연결이 끊어짐
     */
    RC_DISCONNECTED(8),

    ;

    /**
     * 상태 이유 값
     */
    private final int reason;

    /**
     * POI 상태 이유 열거형 생성자
     * 
     * @param reason 상태 이유 값
     */
    PoiStatusReasonEnum(int reason) {
        this.reason = reason;
    }

    /**
     * 상태 이유 값을 반환합니다.
     * 
     * @return 상태 이유 값
     */
    @JsonValue
    public int getReason() {
        return reason;
    }

    /**
     * 정수 값으로 POI 상태 이유를 찾습니다.
     * 
     * @param reason 찾을 상태 이유 값
     * @return 찾은 POI 상태 이유 열거형
     * @throws CloudSDKException 해당하는 상태 이유를 찾을 수 없는 경우
     */
    @JsonCreator
    public static PoiStatusReasonEnum find(int reason) {
        return Arrays.stream(values()).filter(reasonEnum -> reasonEnum.reason == reason).findAny()
            .orElseThrow(() -> new CloudSDKException(PoiStatusReasonEnum.class, reason));
    }

}
