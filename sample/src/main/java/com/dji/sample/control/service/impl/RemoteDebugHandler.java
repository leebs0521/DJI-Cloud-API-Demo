package com.dji.sample.control.service.impl;

import com.dji.sample.common.util.SpringBeanUtilsTest;
import com.dji.sample.manage.service.IDeviceService;
import com.dji.sdk.cloudapi.device.DockModeCodeEnum;

/**
 * 원격 디버깅 핸들러 클래스
 * 
 * 원격 디버깅 명령의 실행 조건을 검증하는 핸들러 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 원격 디버깅 조건 검증
 *    - 도크 모드 확인 (REMOTE_DEBUGGING 모드인지)
 *    - 디버깅 명령 실행 가능 여부 확인
 * 
 * 2. 도크 상태 확인
 *    - 도크가 원격 디버깅 모드에 있는지 확인
 *    - 디버깅 명령의 안전성 검증
 * 
 * 3. 안전한 원격 디버깅 보장
 *    - 원격 디버깅 모드가 아닐 때 디버깅 명령 방지
 *    - 도크 상태 충돌 방지
 *    - 디버깅 명령의 적절한 실행 시점 보장
 * 
 * 원격 디버깅은 도크가 REMOTE_DEBUGGING 모드에 있을 때만 가능합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/27
 */
public class RemoteDebugHandler {

    /**
     * 원격 디버깅 파라미터의 유효성을 검사합니다.
     * 
     * 이 메서드는 원격 디버깅 명령의 파라미터가 유효한지 검사합니다.
     * 기본적으로 true를 반환하며, 하위 클래스에서 필요에 따라
     * 구체적인 검증 로직을 구현할 수 있습니다.
     * 
     * @return 파라미터 유효성 여부 (기본값: true)
     */
    public boolean valid() {
        return true;
    }

    /**
     * 원격 디버깅 명령 실행 가능 여부를 확인합니다.
     * 
     * 이 메서드는 도크가 원격 디버깅 모드에 있는지 확인하여
     * 디버깅 명령 실행이 가능한지 검증합니다.
     * 
     * @param sn 디바이스 시리얼 번호 (도크 SN)
     * @return 원격 디버깅 실행 가능 여부 (true: 가능, false: 불가능)
     */
    public boolean canPublish(String sn) {
        IDeviceService deviceService = SpringBeanUtilsTest.getBean(IDeviceService.class);
        DockModeCodeEnum dockMode = deviceService.getDockMode(sn);
        return DockModeCodeEnum.REMOTE_DEBUGGING == dockMode;
    }
}
