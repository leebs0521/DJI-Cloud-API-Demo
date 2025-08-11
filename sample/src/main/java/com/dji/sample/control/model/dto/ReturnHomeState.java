package com.dji.sample.control.model.dto;

import com.dji.sample.common.util.SpringBeanUtilsTest;
import com.dji.sample.control.service.impl.RemoteDebugHandler;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sdk.cloudapi.device.DroneModeCodeEnum;
import com.dji.sdk.cloudapi.device.OsdDockDrone;

/**
 * 복귀 상태 클래스
 * 
 * 드론의 복귀 기능이 가능한 상태인지 확인하는 핸들러입니다.
 * 드론이 비행 중이고 특정 모드일 때만 복귀가 가능합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/4/19
 */
public class ReturnHomeState extends RemoteDebugHandler {

    /**
     * 복귀 명령을 발행할 수 있는지 확인합니다.
     * 
     * 드론이 비행 중이고(고도 > 0) 복귀 가능한 모드일 때만 true를 반환합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 복귀 명령 발행 가능 여부
     */
    @Override
    public boolean canPublish(String sn) {
        IDeviceRedisService deviceRedisService = SpringBeanUtilsTest.getBean(IDeviceRedisService.class);
        return deviceRedisService.getDeviceOnline(sn)
                .map(DeviceDTO::getChildDeviceSn)
                .flatMap(deviceSn -> deviceRedisService.getDeviceOsd(deviceSn, OsdDockDrone.class))
                .map(osd -> osd.getElevation() > 0 && modeCodeCanReturnHome(osd.getModeCode()))
                .orElse(false);
    }

    /**
     * 복귀 가능한 모드 코드인지 확인합니다.
     * 
     * @param modeCode 드론 모드 코드
     * @return 복귀 가능 여부
     */
    private boolean modeCodeCanReturnHome(DroneModeCodeEnum modeCode) {
        return DroneModeCodeEnum.TAKEOFF_FINISHED == modeCode || DroneModeCodeEnum.TAKEOFF_AUTO == modeCode
                || DroneModeCodeEnum.WAYLINE == modeCode || DroneModeCodeEnum.PANORAMIC_SHOT == modeCode
                || DroneModeCodeEnum.ACTIVE_TRACK == modeCode || DroneModeCodeEnum.APAS == modeCode
                || DroneModeCodeEnum.VIRTUAL_JOYSTICK == modeCode || DroneModeCodeEnum.MANUAL == modeCode;
    }
}
