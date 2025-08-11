package com.dji.sample.component;

import com.dji.sample.component.redis.RedisConst;
import com.dji.sample.component.redis.RedisOpsUtils;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.service.IDeviceService;
import com.dji.sdk.cloudapi.device.DeviceDomainEnum;
import com.dji.sdk.mqtt.IMqttTopicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 전역 스케줄 서비스 클래스
 * 
 * 애플리케이션에서 주기적으로 실행되어야 하는 작업들을 관리하는 스케줄 서비스입니다.
 * 디바이스 상태 모니터링, MQTT 토픽 구독 상태 확인 등의 작업을 수행합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/24
 * @version 0.1
 */
@Component
@Slf4j
public class GlobalScheduleService {

    /** 디바이스 서비스 */
    @Autowired
    private IDeviceService deviceService;

    /** MQTT 토픽 서비스 */
    @Autowired
    private IMqttTopicService topicService;

    /** JSON 직렬화를 위한 ObjectMapper */
    @Autowired
    private ObjectMapper mapper;

    /**
     * 디바이스 상태를 30초마다 확인하는 스케줄 작업입니다.
     * 
     * Redis에 저장된 디바이스 온라인 정보의 만료 시간을 확인하여,
     * 만료된 디바이스를 오프라인으로 처리합니다.
     * 캐시 사용을 권장합니다.
     * 
     * 실행 주기: 초기 지연 10초 후, 30초마다 실행
     */
    @Scheduled(initialDelay = 10, fixedRate = 30, timeUnit = TimeUnit.SECONDS)
    private void deviceStatusListen() {
        // 디바이스 온라인 접두사 길이 계산
        int start = RedisConst.DEVICE_ONLINE_PREFIX.length();

        // Redis에서 모든 디바이스 온라인 키를 조회하여 처리
        RedisOpsUtils.getAllKeys(RedisConst.DEVICE_ONLINE_PREFIX + "*").forEach(key -> {
            // 키의 남은 만료 시간 조회
            long expire = RedisOpsUtils.getExpire(key);
            
            // 만료 시간이 30초 이하인 경우 디바이스를 오프라인으로 처리
            if (expire <= 30) {
                // Redis에서 디바이스 정보 조회
                DeviceDTO device = (DeviceDTO) RedisOpsUtils.get(key);
                if (null == device) {
                    return;
                }
                
                // 디바이스 타입에 따라 적절한 오프라인 처리 수행
                if (DeviceDomainEnum.DRONE == device.getDomain()) {
                    // 드론 디바이스 오프라인 처리
                    deviceService.subDeviceOffline(key.substring(start));
                } else {
                    // 게이트웨이 디바이스 오프라인 처리
                    deviceService.gatewayOffline(key.substring(start));
                }
                
                // Redis에서 디바이스 온라인 정보 삭제
                RedisOpsUtils.del(key);
            }
        });

        // 현재 구독 중인 MQTT 토픽 목록을 로그에 기록
        log.info("Subscriptions: {}", Arrays.toString(topicService.getSubscribedTopic()));
    }

}