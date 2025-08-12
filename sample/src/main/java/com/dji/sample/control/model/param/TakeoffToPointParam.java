package com.dji.sample.control.model.param;

import com.dji.sdk.cloudapi.control.CommanderFlightModeEnum;
import com.dji.sdk.cloudapi.control.CommanderModeLostActionEnum;
import com.dji.sdk.cloudapi.device.ExitWaylineWhenRcLostEnum;
import com.dji.sdk.cloudapi.device.RcLostActionEnum;
import com.dji.sdk.cloudapi.wayline.RthModeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 특정 지점으로 이륙하는 파라미터 클래스
 */
@Data
@Schema(description = "특정 지점으로 이륙하기 위한 파라미터")
public class TakeoffToPointParam {

    @Schema(description = "비행 작업 ID")
    private String flightId;

    @Schema(description = "목표 경도 (-180 ~ 180)")
    @Range(min = -180, max = 180)
    @NotNull
    private Double targetLongitude;

    @Schema(description = "목표 위도 (-90 ~ 90)")
    @Range(min = -90, max = 90)
    @NotNull
    private Double targetLatitude;

    @Schema(description = "목표 고도 (2 ~ 10000m)")
    @Range(min = 2, max = 10000)
    @NotNull
    private Double targetHeight;

    @Schema(description = "안전 이륙 고도 (2 ~ 1500m)")
    @Range(min = 2, max = 1500)
    @NotNull
    private Double securityTakeoffHeight;

    @Schema(description = "복귀 고도 (2 ~ 1500m)")
    @Range(min = 2, max = 1500)
    @NotNull
    private Double rthAltitude;

    @Schema(description = "RC 신호 손실 시 동작", enumAsRef = true)
    @NotNull
    private RcLostActionEnum rcLostAction;

    @Schema(description = "RC 신호 손실 시 웨이라인 종료 여부", enumAsRef = true)
    @NotNull
    private ExitWaylineWhenRcLostEnum exitWaylineWhenRcLost;

    @Schema(description = "최대 속도 (1 ~ 15 m/s)")
    @Range(min = 1, max = 15)
    @NotNull
    private Double maxSpeed;

    @Schema(description = "복귀 모드", enumAsRef = true)
    private RthModeEnum rthMode;

    @Schema(description = "커맨더 모드 손실 시 동작", enumAsRef = true)
    private CommanderModeLostActionEnum commanderModeLostAction;

    @Schema(description = "커맨더 비행 모드", enumAsRef = true)
    private CommanderFlightModeEnum commanderFlightMode;

    @Schema(description = "커맨더 비행 고도 (2 ~ 3000m)")
    @Min(2)
    @Max(3000)
    private Float commanderFlightHeight;
}
