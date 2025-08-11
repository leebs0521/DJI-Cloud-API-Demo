package com.dji.sample.control.service.impl;

import com.dji.sample.common.util.SpringBeanUtilsTest;
import com.dji.sample.control.model.param.DronePayloadParam;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.service.IDevicePayloadService;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sdk.cloudapi.device.OsdCamera;
import com.dji.sdk.cloudapi.device.OsdDockDrone;

import java.util.Optional;

/**
 * 페이로드 명령 핸들러 추상 클래스
 * 
 * 페이로드 제어 명령의 실행 조건을 검증하고 실행 가능 여부를 확인하는
 * 추상 클래스입니다. 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 페이로드 명령 검증
 *    - 파라미터 유효성 검사
 *    - 실행 조건 확인
 *    - 권한 상태 검증
 * 
 * 2. 디바이스 상태 확인
 *    - 도크 온라인 상태 확인
 *    - 드론 온라인 상태 확인
 *    - 페이로드 장치 상태 확인
 * 
 * 3. 권한 관리
 *    - 페이로드 권한 확인
 *    - 권한 부족 시 예외 발생
 *    - 권한 상태 검증
 * 
 * 4. 카메라 정보 관리
 *    - 페이로드 인덱스별 카메라 정보 조회
 *    - 카메라 상태 확인
 *    - OSD 정보 활용
 * 
 * 이 클래스를 상속받는 구체적인 핸들러 클래스들은
 * 각각의 페이로드 명령에 특화된 검증 로직을 구현합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/4/23
 */
public abstract class PayloadCommandsHandler {

    /** 페이로드 파라미터 */
    DronePayloadParam param;

    /** 카메라 OSD 정보 */
    OsdCamera osdCamera;

    /**
     * 페이로드 명령 핸들러 생성자
     * 
     * @param param 페이로드 파라미터
     */
    PayloadCommandsHandler(DronePayloadParam param) {
        this.param = param;
    }

    /**
     * 파라미터 유효성을 검사합니다.
     * 
     * 이 메서드는 페이로드 명령의 파라미터가 유효한지 검사합니다.
     * 기본적으로 true를 반환하며, 하위 클래스에서 필요에 따라
     * 구체적인 검증 로직을 구현할 수 있습니다.
     * 
     * @return 파라미터 유효성 여부 (기본값: true)
     */
    public boolean valid() {
        return true;
    }

    /**
     * 페이로드 명령 실행 가능 여부를 확인합니다.
     * 
     * 이 메서드는 다음을 수행합니다:
     * 1. 디바이스 온라인 상태 확인
     * 2. 페이로드 인덱스에 해당하는 카메라 정보 조회
     * 3. 카메라 OSD 정보 확인
     * 
     * 디바이스가 오프라인이거나 카메라 정보를 찾을 수 없는 경우
     * RuntimeException을 발생시킵니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @return 실행 가능 여부 (true: 가능, false: 불가능)
     * @throws RuntimeException 디바이스 오프라인 또는 카메라 정보 없음
     */
    public boolean canPublish(String deviceSn) {
        // 디바이스 OSD 정보 조회
        Optional<OsdDockDrone> deviceOpt = SpringBeanUtilsTest.getBean(IDeviceRedisService.class)
                .getDeviceOsd(deviceSn, OsdDockDrone.class);
        if (deviceOpt.isEmpty()) {
            throw new RuntimeException("The device is offline.");
        }
        
        // 페이로드 인덱스에 해당하는 카메라 정보 조회
        osdCamera = deviceOpt.get().getCameras().stream()
                .filter(osdCamera -> param.getPayloadIndex().equals(osdCamera.getPayloadIndex().toString()))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Did not receive osd information about the camera, please check the cache data."));
        return true;
    }

    /**
     * 도크 온라인 상태를 확인하고 자식 디바이스 SN을 반환합니다.
     * 
     * 이 메서드는 도크의 온라인 상태를 확인하고,
     * 온라인인 경우 자식 디바이스(드론)의 시리얼 번호를 반환합니다.
     * 
     * @param dockSn 도크 시리얼 번호
     * @return 자식 디바이스(드론) 시리얼 번호
     * @throws RuntimeException 도크가 오프라인인 경우
     */
    private String checkDockOnline(String dockSn) {
        Optional<DeviceDTO> deviceOpt = SpringBeanUtilsTest.getBean(IDeviceRedisService.class).getDeviceOnline(dockSn);
        if (deviceOpt.isEmpty()) {
            throw new RuntimeException("The dock is offline.");
        }
        return deviceOpt.get().getChildDeviceSn();
    }

    /**
     * 디바이스 온라인 상태를 확인합니다.
     * 
     * 이 메서드는 지정된 디바이스가 온라인 상태인지 확인합니다.
     * 오프라인인 경우 RuntimeException을 발생시킵니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @throws RuntimeException 디바이스가 오프라인인 경우
     */
    private void checkDeviceOnline(String deviceSn) {
        boolean isOnline = SpringBeanUtilsTest.getBean(IDeviceRedisService.class).checkDeviceOnline(deviceSn);
        if (!isOnline) {
            throw new RuntimeException("The device is offline.");
        }
    }

    /**
     * 페이로드 권한을 확인합니다.
     * 
     * 이 메서드는 지정된 디바이스가 해당 페이로드에 대한
     * 제어 권한을 보유하고 있는지 확인합니다.
     * 권한이 없는 경우 RuntimeException을 발생시킵니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @throws RuntimeException 페이로드 제어 권한이 없는 경우
     */
    private void checkAuthority(String deviceSn) {
        boolean hasAuthority = SpringBeanUtilsTest.getBean(IDevicePayloadService.class)
                .checkAuthorityPayload(deviceSn, param.getPayloadIndex());
        if (!hasAuthority) {
            throw new RuntimeException("The device does not have payload control authority.");
        }
    }

    /**
     * 페이로드 명령 실행 조건을 검증합니다.
     * 
     * 이 메서드는 페이로드 명령 실행 전에 모든 조건을 검증합니다:
     * 1. 파라미터 유효성 검사 (valid)
     * 2. 도크 온라인 상태 확인
     * 3. 드론 온라인 상태 확인
     * 4. 페이로드 권한 확인
     * 5. 실행 가능 여부 확인 (canPublish)
     * 
     * 모든 조건이 만족되지 않으면 RuntimeException을 발생시켜
     * 안전하지 않은 페이로드 제어를 방지합니다.
     * 
     * @param dockSn 도크 시리얼 번호
     * @throws RuntimeException 조건을 만족하지 않는 경우
     */
    public final void checkCondition(String dockSn) {
        // 파라미터 유효성 검사
        if (!valid()) {
            throw new RuntimeException("illegal argument");
        }

        // 도크 온라인 상태 확인 및 자식 디바이스 SN 획득
        String deviceSn = checkDockOnline(dockSn);
        
        // 드론 온라인 상태 확인
        checkDeviceOnline(deviceSn);
        
        // 페이로드 권한 확인
        checkAuthority(deviceSn);

        // 실행 가능 여부 확인
        if (!canPublish(deviceSn)) {
            throw new RuntimeException("The current state of the drone does not support this function, please try again later.");
        }
    }

}
