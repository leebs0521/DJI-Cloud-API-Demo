package com.dji.sample.control.model.param;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * 디바이스 DRC 정보 파라미터 클래스
 * 
 * DRC(Direct Remote Control) 모드에서 디바이스 정보를 설정하는 파라미터를 정의합니다.
 * OSD(On-Screen Display) 및 HSI(Horizontal Situation Indicator) 주파수를 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/2/2
 */
@Data
public class DeviceDrcInfoParam {

    /** OSD 주파수 (1-30Hz, 기본값: 10Hz) */
    @Range(min = 1, max = 30)
    private Integer osdFrequency = 10;

    /** HSI 주파수 (1-30Hz, 기본값: 1Hz) */
    @Range(min = 1, max = 30)
    private Integer hsiFrequency = 1;
}
