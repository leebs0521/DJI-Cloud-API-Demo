package com.dji.sample.control.model.param;

import com.dji.sdk.cloudapi.control.CameraTypeEnum;
import com.dji.sdk.cloudapi.control.GimbalResetModeEnum;
import com.dji.sdk.cloudapi.device.CameraModeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 드론 페이로드 파라미터 클래스
 * 
 * 드론의 페이로드(카메라, 짐벌 등) 제어를 위한 파라미터를 정의합니다.
 * 카메라 타입, 줌 팩터, 짐벌 제어, 카메라 모드 등의 설정을 포함합니다.
 * 
 * @author sean 
 * @version 1.4
 * @date 2023/3/1
 */
@Data
@Schema(description = "드론 페이로드 제어 파라미터")
public class DronePayloadParam {

    @Schema(description = "페이로드 인덱스 (형식: \"0-0-0\")")
    @Pattern(regexp = "\\d+-\\d+-\\d+")
    @NotNull
    private String payloadIndex;

    @Schema(description = "카메라 타입 (ZOOM/WIDE/IR)", enumAsRef = true)
    private CameraTypeEnum cameraType;

    @Schema(description = "줌 팩터 (2-200배)")
    @Range(min = 2, max = 200)
    private Float zoomFactor;

    @Schema(description = "카메라 모드 (PHOTO/VIDEO)", enumAsRef = true)
    private CameraModeEnum cameraMode;

    @Schema(description = "짐벌 잠금 모드 (true: 드론과 함께 회전, false: 짐벌만 회전)")
    private Boolean locked;

    @Schema(description = "피치 속도")
    private Double pitchSpeed;

    @Schema(description = "요우 속도 (짐벌 잠금이 해제된 경우에만 유효)")
    private Double yawSpeed;

    @Schema(description = "X 좌표 (0-1 범위, 좌상단 기준)")
    @Range(min = 0, max = 1)
    private Double x;

    @Schema(description = "Y 좌표 (0-1 범위, 좌상단 기준)")
    @Range(min = 0, max = 1)
    private Double y;

    @Schema(description = "짐벌 리셋 모드 (RECENTER/DOWN/RECENTER_PAN/PITCH_DOWN)", enumAsRef = true)
    private GimbalResetModeEnum resetMode;
}
