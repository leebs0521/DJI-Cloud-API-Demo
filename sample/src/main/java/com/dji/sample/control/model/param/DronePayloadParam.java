package com.dji.sample.control.model.param;

import com.dji.sdk.cloudapi.control.CameraTypeEnum;
import com.dji.sdk.cloudapi.control.GimbalResetModeEnum;
import com.dji.sdk.cloudapi.device.CameraModeEnum;
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
public class DronePayloadParam {

    /** 페이로드 인덱스 (형식: "0-0-0") */
    @Pattern(regexp = "\\d+-\\d+-\\d+")
    @NotNull
    private String payloadIndex;

    /** 카메라 타입 */
    private CameraTypeEnum cameraType;

    /** 줌 팩터 (2-200배) */
    @Range(min = 2, max = 200)
    private Float zoomFactor;

    /** 카메라 모드 */
    private CameraModeEnum cameraMode;

    /**
     * 짐벌 잠금 모드
     * true: 짐벌을 잠그고 드론과 함께 회전
     * false: 짐벌만 회전하고 드론은 회전하지 않음
     */
    private Boolean locked;

    /** 피치 속도 */
    private Double pitchSpeed;

    /**
     * 요우 속도
     * locked가 false일 때만 유효
     */
    private Double yawSpeed;

    /**
     * X 좌표 (0-1, 좌상단 모서리를 중심점으로)
     */
    @Range(min = 0, max = 1)
    private Double x;

    /**
     * Y 좌표 (0-1, 좌상단 모서리를 중심점으로)
     */
    @Range(min = 0, max = 1)
    private Double y;

    /** 짐벌 리셋 모드 */
    private GimbalResetModeEnum resetMode;
}
