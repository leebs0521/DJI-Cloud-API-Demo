package com.dji.sample.component;

import com.dji.sample.component.redis.RedisConst;
import com.dji.sample.component.redis.RedisOpsUtils;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sample.manage.service.IDeviceService;
import com.dji.sdk.cloudapi.device.DeviceDomainEnum;
import com.dji.sdk.common.SDKManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 애플리케이션 부팅 초기화 클래스
 * 
 * 애플리케이션 시작 시 실행되는 초기화 로직을 담당합니다.
 * Redis에 저장된 디바이스 정보를 기반으로 MQTT 토픽을 재구독하여
 * 프로그램 중단으로 인한 데이터 불일치를 방지합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/24
 * @version 0.1
 */
@Component
public class ApplicationBootInitial implements CommandLineRunner {

    /** 디바이스 서비스 */
    @Autowired
    private IDeviceService deviceService;

    /** 디바이스 Redis 서비스 */
    @Autowired
    private IDeviceRedisService deviceRedisService;

    /**
     * 애플리케이션 시작 시 실행되는 초기화 메서드입니다.
     * 
     * Redis에 존재하는 디바이스들을 구독하여 프로그램 중단으로 인한
     * Pilot 측과의 데이터 불일치를 방지합니다.
     * 
     * @param args 명령행 인수
     * @throws Exception 초기화 중 오류 발생 시
     */
    @Override
    public void run(String... args) throws Exception {
        // 디바이스 온라인 접두사 길이 계산
        int start = RedisConst.DEVICE_ONLINE_PREFIX.length();

        // Redis에서 모든 디바이스 온라인 키를 조회하여 처리
        RedisOpsUtils.getAllKeys(RedisConst.DEVICE_ONLINE_PREFIX + "*")
                .stream()
                // 키에서 디바이스 SN 추출
                .map(key -> key.substring(start))
                // Redis에서 디바이스 온라인 정보 조회
                .map(deviceRedisService::getDeviceOnline)
                .map(Optional::get)
                // 드론이 아닌 디바이스만 필터링 (서브 디바이스만 처리)
                .filter(device -> DeviceDomainEnum.DRONE != device.getDomain())
                // 각 디바이스에 대해 MQTT 토픽 재구독
                .forEach(device -> deviceService.subDeviceOnlineSubscribeTopic(
                        SDKManager.registerDevice(device.getDeviceSn(), device.getChildDeviceSn(), device.getDomain(),
                                device.getType(), device.getSubType(), device.getThingVersion(),
                                // 자식 디바이스의 Thing 버전 조회 (없으면 null)
                                deviceRedisService.getDeviceOnline(device.getChildDeviceSn()).map(DeviceDTO::getThingVersion).orElse(null)))));

    }
}