package com.dji.sample.control.model.dto;

import com.dji.sample.control.service.impl.RemoteDebugHandler;
import com.dji.sdk.cloudapi.device.BatteryStoreModeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 배터리 저장 모드 클래스
 * 
 * 도크의 배터리 저장 모드 제어를 위한 원격 디버깅 핸들러입니다.
 * 배터리의 저장 모드를 설정할 수 있습니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatteryStoreMode extends RemoteDebugHandler {

    /** 배터리 저장 모드 액션 */
    private BatteryStoreModeEnum action;
}
