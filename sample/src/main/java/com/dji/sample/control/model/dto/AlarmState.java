package com.dji.sample.control.model.dto;

import com.dji.sample.control.service.impl.RemoteDebugHandler;
import com.dji.sdk.cloudapi.device.SwitchActionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 알람 상태 클래스
 * 
 * 도크의 알람 제어를 위한 원격 디버깅 핸들러입니다.
 * 알람의 켜기/끄기 상태를 설정할 수 있습니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmState extends RemoteDebugHandler {

    /** 알람 액션 */
    private SwitchActionEnum action;

}
