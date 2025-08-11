package com.dji.sample.control.model.dto;

import com.dji.sample.common.util.SpringBeanUtilsTest;
import com.dji.sample.control.service.impl.RemoteDebugHandler;
import com.dji.sample.manage.service.IDeviceService;
import com.dji.sdk.cloudapi.device.DockModeCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 원격 디버깅 열기 상태 클래스
 * 
 * 원격 디버깅 기능을 열 수 있는 상태인지 확인하는 핸들러입니다.
 * 도크가 IDLE 모드일 때만 원격 디버깅을 열 수 있습니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/4/14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RemoteDebugOpenState extends RemoteDebugHandler {

    /**
     * 원격 디버깅 열기 명령을 발행할 수 있는지 확인합니다.
     * 
     * 도크가 IDLE 모드일 때만 true를 반환합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 원격 디버깅 열기 명령 발행 가능 여부
     */
    @Override
    public boolean canPublish(String sn) {
        IDeviceService deviceService = SpringBeanUtilsTest.getBean(IDeviceService.class);
        DockModeCodeEnum dockMode = deviceService.getDockMode(sn);
        return DockModeCodeEnum.IDLE == dockMode;
    }
}
