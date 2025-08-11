package com.dji.sample.control.model.param;

import com.dji.sample.component.redis.RedisConst;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * DRC 모드 파라미터 클래스
 * 
 * DRC(Direct Remote Control) 모드 진입/종료를 위한 파라미터를 정의합니다.
 * 클라이언트 ID, 도크 시리얼 번호, 만료 시간, 디바이스 정보를 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/1/11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrcModeParam {

    /** 클라이언트 ID */
    @NotBlank
    private String clientId;

    /** 도크 시리얼 번호 */
    @NotBlank
    private String dockSn;

    /** 만료 시간 (초, 1800-86400초, 기본값: DRC_MODE_ALIVE_SECOND) */
    @Range(min = 1800, max = 86400)
    @Builder.Default
    private long expireSec = RedisConst.DRC_MODE_ALIVE_SECOND;

    /** 디바이스 DRC 정보 */
    @Valid
    @Builder.Default
    private DeviceDrcInfoParam deviceInfo = new DeviceDrcInfoParam();
}
