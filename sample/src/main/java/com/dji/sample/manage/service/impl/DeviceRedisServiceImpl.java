package com.dji.sample.manage.service.impl;

import com.dji.sample.component.mqtt.model.EventsReceiver;
import com.dji.sample.component.redis.RedisConst;
import com.dji.sample.component.redis.RedisOpsUtils;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.service.ICapacityCameraService;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sdk.cloudapi.firmware.OtaProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 디바이스 Redis 캐시 관리 서비스 구현체
 * 
 * DJI Cloud API의 디바이스 Redis 캐시 관리를 위한 서비스 구현체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 구현합니다:
 * 
 * 1. 디바이스 온라인 상태 관리
 *    - 디바이스 온라인/오프라인 상태 캐싱
 *    - 디바이스 상태 만료 시간 관리
 *    - 디바이스 상태 조회 및 업데이트
 *    - 디바이스 상태 삭제
 * 
 * 2. 디바이스 OSD 데이터 관리
 *    - 디바이스 OSD 데이터 캐싱
 *    - OSD 데이터 조회 및 업데이트
 *    - OSD 데이터 만료 처리
 *    - OSD 데이터 삭제
 * 
 * 3. 펌웨어 업그레이드 관리
 *    - 펌웨어 업그레이드 진행률 캐싱
 *    - 업그레이드 상태 조회 및 업데이트
 *    - 업그레이드 완료 시 캐시 삭제
 *    - 업그레이드 이력 관리
 * 
 * 4. HMS 메시지 관리
 *    - HMS 메시지 키 관리
 *    - 읽지 않은 HMS 메시지 추적
 *    - HMS 메시지 만료 처리
 *    - HMS 메시지 정리
 * 
 * 5. 디바이스 오프라인 처리
 *    - 게이트웨이 오프라인 처리
 *    - 서브 디바이스 오프라인 처리
 *    - 관련 캐시 데이터 정리
 *    - 리소스 해제
 * 
 * 주요 의존성:
 * - RedisOpsUtils: Redis 작업 유틸리티
 * - RedisConst: Redis 상수 정의
 * - ICapacityCameraService: 카메라 용량 관리
 * - EventsReceiver: 이벤트 수신자
 * - DeviceDTO: 디바이스 DTO
 * 
 * 이 클래스는 DJI 디바이스의 Redis 캐시를
 * 효율적으로 관리하는 서비스입니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/21
 */
@Service
public class DeviceRedisServiceImpl implements IDeviceRedisService {

    @Autowired
    private ICapacityCameraService capacityCameraService;

    /**
     * 디바이스 온라인 상태를 확인합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 온라인 상태 여부
     */
    @Override
    public Boolean checkDeviceOnline(String sn) {
        String key = RedisConst.DEVICE_ONLINE_PREFIX + sn;
        return RedisOpsUtils.checkExist(key) && RedisOpsUtils.getExpire(key) > 0;
    }

    /**
     * 온라인 디바이스 정보를 조회합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 디바이스 정보 (Optional)
     */
    @Override
    public Optional<DeviceDTO> getDeviceOnline(String sn) {
        return Optional.ofNullable((DeviceDTO) RedisOpsUtils.get(RedisConst.DEVICE_ONLINE_PREFIX + sn));
    }

    /**
     * 디바이스를 온라인 상태로 설정합니다.
     * 
     * @param device 디바이스 정보
     */
    @Override
    public void setDeviceOnline(DeviceDTO device) {
        RedisOpsUtils.setWithExpire(RedisConst.DEVICE_ONLINE_PREFIX + device.getDeviceSn(), device, RedisConst.DEVICE_ALIVE_SECOND);
    }

    /**
     * 온라인 디바이스 정보를 삭제합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 삭제 성공 여부
     */
    @Override
    public Boolean delDeviceOnline(String sn) {
        return RedisOpsUtils.del(RedisConst.DEVICE_ONLINE_PREFIX + sn);
    }

    /**
     * 디바이스 OSD 데이터를 설정합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param data OSD 데이터
     */
    @Override
    public void setDeviceOsd(String sn, Object data) {
        RedisOpsUtils.setWithExpire(RedisConst.OSD_PREFIX + sn, data, RedisConst.DEVICE_ALIVE_SECOND);
    }

    /**
     * 디바이스 OSD 데이터를 조회합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param clazz 데이터 타입 클래스
     * @param <T> 데이터 타입
     * @return OSD 데이터 (Optional)
     */
    @Override
    public <T> Optional<T> getDeviceOsd(String sn, Class<T> clazz) {
        return Optional.ofNullable(clazz.cast(RedisOpsUtils.get(RedisConst.OSD_PREFIX + sn)));
    }

    /**
     * 디바이스 OSD 데이터를 삭제합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 삭제 성공 여부
     */
    @Override
    public Boolean delDeviceOsd(String sn) {
        return RedisOpsUtils.del(RedisConst.OSD_PREFIX + sn);
    }

    /**
     * 펌웨어 업그레이드 진행률을 설정합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param events 업그레이드 이벤트
     */
    @Override
    public void setFirmwareUpgrading(String sn, EventsReceiver<OtaProgress> events) {
        RedisOpsUtils.setWithExpire(RedisConst.FIRMWARE_UPGRADING_PREFIX + sn, events, RedisConst.DEVICE_ALIVE_SECOND * 20);
    }

    /**
     * 펌웨어 업그레이드 진행률을 조회합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 업그레이드 진행률 (Optional)
     */
    @Override
    public Optional<EventsReceiver<OtaProgress>> getFirmwareUpgradingProgress(String sn) {
        return Optional.ofNullable((EventsReceiver<OtaProgress>) RedisOpsUtils.get(RedisConst.FIRMWARE_UPGRADING_PREFIX + sn));
    }

    /**
     * 펌웨어 업그레이드 진행률을 삭제합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 삭제 성공 여부
     */
    @Override
    public Boolean delFirmwareUpgrading(String sn) {
        return RedisOpsUtils.del(RedisConst.FIRMWARE_UPGRADING_PREFIX + sn);
    }

    /**
     * HMS 메시지 키를 추가합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param keys HMS 메시지 키들
     */
    @Override
    public void addEndHmsKeys(String sn, String... keys) {
        RedisOpsUtils.listRPush(RedisConst.HMS_PREFIX + sn, keys);
    }

    /**
     * 모든 HMS 메시지 키를 조회합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return HMS 메시지 키 집합
     */
    @Override
    public Set<String> getAllHmsKeys(String sn) {
        return RedisOpsUtils.listGetAll(RedisConst.HMS_PREFIX + sn).stream()
                .map(String::valueOf).collect(Collectors.toSet());
    }

    /**
     * 디바이스 시리얼 번호로 HMS 메시지 키를 삭제합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 삭제 성공 여부
     */
    @Override
    public Boolean delHmsKeysBySn(String sn) {
        return RedisOpsUtils.del(RedisConst.HMS_PREFIX + sn);
    }

    /**
     * 게이트웨이 오프라인 처리를 수행합니다.
     * 
     * 게이트웨이와 관련된 모든 캐시 데이터를 정리합니다.
     * 
     * @param gatewaySn 게이트웨이 시리얼 번호
     */
    @Override
    public void gatewayOffline(String gatewaySn) {
        delDeviceOnline(gatewaySn);
        delHmsKeysBySn(gatewaySn);
        capacityCameraService.deleteCapacityCameraByDeviceSn(gatewaySn);
    }

    /**
     * 서브 디바이스 오프라인 처리를 수행합니다.
     * 
     * 서브 디바이스와 관련된 모든 캐시 데이터를 정리합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     */
    @Override
    public void subDeviceOffline(String deviceSn) {
        delDeviceOnline(deviceSn);
        delDeviceOsd(deviceSn);
        delHmsKeysBySn(deviceSn);
        capacityCameraService.deleteCapacityCameraByDeviceSn(deviceSn);
    }
}
