package com.dji.sample.control.model.param;

import com.dji.sample.component.redis.RedisConst;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * DRC 연결 파라미터 클래스
 * 
 * DRC(Direct Remote Control) 연결을 설정하는 파라미터를 정의합니다.
 * 클라이언트 ID와 만료 시간을 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/1/11
 */
@Data
@Schema(description = "DRC 연결 파라미터")
public class DrcConnectParam {

    /** 클라이언트 ID */
    @Schema(description = "클라이언트 ID")
    private String clientId;

    /** 만료 시간 (초, 1800-86400초, 기본값: DRC_MODE_ALIVE_SECOND) */
    @Schema(description = "만료 시간 (초)")
    @Range(min = 1800, max = 86400)
    private long expireSec = RedisConst.DRC_MODE_ALIVE_SECOND;
}
