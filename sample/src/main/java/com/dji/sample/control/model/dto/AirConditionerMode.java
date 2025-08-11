package com.dji.sample.control.model.dto;

import com.dji.sample.control.service.impl.RemoteDebugHandler;
import com.dji.sdk.cloudapi.device.AirConditionerStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 에어컨 모드 클래스
 * 
 * 도크의 에어컨 제어를 위한 원격 디버깅 핸들러입니다.
 * 에어컨의 상태를 설정할 수 있습니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirConditionerMode extends RemoteDebugHandler {

    /** 에어컨 액션 */
    private AirConditionerStateEnum action;
}
