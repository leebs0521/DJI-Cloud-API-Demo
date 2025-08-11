package com.dji.sample.control.model.param;

import com.dji.sdk.cloudapi.control.CommanderFlightModeEnum;
import com.dji.sdk.cloudapi.control.CommanderModeLostActionEnum;
import com.dji.sdk.cloudapi.device.ExitWaylineWhenRcLostEnum;
import com.dji.sdk.cloudapi.device.RcLostActionEnum;
import com.dji.sdk.cloudapi.wayline.RthModeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 특정 지점으로 이륙하는 파라미터 클래스
 * 
 * 드론이 특정 지점으로 이륙할 때 사용하는 파라미터를 정의합니다.
 * 목표 좌표, 고도, 속도, 안전 설정 등을 포함합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/1
 */
@Data
public class TakeoffToPointParam {

    /** 비행 작업 ID */
    private String flightId;

    /** 목표 경도 (-180 ~ 180) */
    @Range(min = -180, max = 180)
    @NotNull
    private Double targetLongitude;

    /** 목표 위도 (-90 ~ 90) */
    @Range(min = -90, max = 90)
    @NotNull
    private Double targetLatitude;

    /** 목표 고도 (2 ~ 10000m) */
    @Range(min = 2, max = 10000)
    @NotNull
    private Double targetHeight;

    /** 안전 이륙 고도 (2 ~ 1500m) */
    @Range(min = 2, max = 1500)
    @NotNull
    private Double securityTakeoffHeight;

    /** 복귀 고도 (2 ~ 1500m) */
    @Range(min = 2, max = 1500)
    @NotNull
    private Double rthAltitude;

    /** RC 신호 손실 시 동작 */
    @NotNull
    private RcLostActionEnum rcLostAction;

    /** RC 신호 손실 시 웨이라인 종료 여부 */
    @NotNull
    private ExitWaylineWhenRcLostEnum exitWaylineWhenRcLost;

    /** 최대 속도 (1 ~ 15 m/s) */
    @Range(min = 1, max = 15)
    @NotNull
    private Double maxSpeed;

    /** 복귀 모드 */
    private RthModeEnum rthMode;

    /** 커맨더 모드 손실 시 동작 */
    private CommanderModeLostActionEnum commanderModeLostAction;

    /** 커맨더 비행 모드 */
    private CommanderFlightModeEnum commanderFlightMode;

    /** 커맨더 비행 고도 (2 ~ 3000m) */
    @Min(2)
    @Max(3000)
    private Float commanderFlightHeight;
}
