package com.dji.sample.control.model.dto;

import com.dji.sample.common.util.SpringBeanUtilsTest;
import com.dji.sample.control.service.impl.RemoteDebugHandler;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sdk.cloudapi.device.DroneModeCodeEnum;
import com.dji.sdk.cloudapi.device.OsdDockDrone;

/**
 * 복귀 취소 상태 클래스
 * 
 * 드론의 복귀 취소 기능이 가능한 상태인지 확인하는 핸들러입니다.
 * 드론이 자동 복귀 모드일 때만 복귀 취소가 가능합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/4/19
 */
public class ReturnHomeCancelState extends RemoteDebugHandler {

    /**
     * 복귀 취소 명령을 발행할 수 있는지 확인합니다.
     * 
     * 드론이 자동 복귀 모드(RETURN_AUTO)일 때만 true를 반환합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 복귀 취소 명령 발행 가능 여부
     */
    @Override
    public boolean canPublish(String sn) {
        IDeviceRedisService deviceRedisService = SpringBeanUtilsTest.getBean(IDeviceRedisService.class);
        return deviceRedisService.getDeviceOnline(sn)
                .map(DeviceDTO::getChildDeviceSn)
                .flatMap(deviceSn -> deviceRedisService.getDeviceOsd(deviceSn, OsdDockDrone.class))
                .map(osd -> DroneModeCodeEnum.RETURN_AUTO == osd.getModeCode())
                .orElse(false);
    }

}
