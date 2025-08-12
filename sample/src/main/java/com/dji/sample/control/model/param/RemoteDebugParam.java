package com.dji.sample.control.model.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * 원격 디버깅 파라미터 클래스
 * 
 * 원격 디버깅 작업을 위한 파라미터를 정의합니다.
 * 디버깅 액션을 지정합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/14
 */
@Data
@Schema(description = "원격 디버깅 파라미터")
public class RemoteDebugParam {

    @Schema(description = "디버깅 액션 코드")
    @NotNull
    private Integer action;
}
