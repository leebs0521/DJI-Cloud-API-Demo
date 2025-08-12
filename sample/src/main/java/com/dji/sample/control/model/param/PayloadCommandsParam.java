package com.dji.sample.control.model.param;

import com.dji.sample.control.model.enums.PayloadCommandsEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 페이로드 명령 파라미터 클래스
 * 
 * 드론의 페이로드에 명령을 전송할 때 사용하는 파라미터를 정의합니다.
 * 명령 타입과 페이로드 데이터를 포함합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/2
 */
@Data
@Schema(description = "페이로드 명령 파라미터")
public class PayloadCommandsParam {

    @Schema(description = "디바이스 시리얼 번호")
    private String sn;

    @NotNull
    @Valid
    @Schema(description = "페이로드 명령 (예: 카메라 모드 전환, 사진 촬영, 녹화 등)", enumAsRef = true)
    private PayloadCommandsEnum cmd;

    @Valid
    @NotNull
    @Schema(description = "페이로드 데이터 (페이로드 인덱스 및 명령별 상세 파라미터)", enumAsRef = true)
    private DronePayloadParam data;
}
