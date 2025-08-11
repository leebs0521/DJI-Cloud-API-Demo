package com.dji.sample.manage.service;

import com.dji.sample.component.mqtt.model.EventsReceiver;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sdk.cloudapi.firmware.OtaProgress;

import java.util.Optional;
import java.util.Set;

/**
 * 디바이스 Redis 캐시 관리 서비스 인터페이스
 * 
 * DJI Cloud API의 디바이스 상태 정보를 Redis에 캐싱하여 관리하는 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 온라인 상태 관리
 *    - 디바이스 온라인/오프라인 상태 확인
 *    - 디바이스 기본 정보 캐싱 및 조회
 *    - 디바이스 상태 정보 삭제
 * 
 * 2. 디바이스 OSD 데이터 관리
 *    - 실시간 OSD(On-Screen Display) 데이터 캐싱
 *    - OSD 데이터 조회 및 삭제
 *    - 디바이스 상태 실시간 모니터링
 * 
 * 3. 펌웨어 업그레이드 진행 상황 관리
 *    - 펌웨어 업그레이드 진행 상황 캐싱
 *    - 업그레이드 진행률 실시간 추적
 *    - 업그레이드 완료 후 캐시 정리
 * 
 * 4. HMS(Health Management System) 키 관리
 *    - 디바이스 HMS 키 저장 및 조회
 *    - HMS 키 세트 관리
 *    - 디바이스별 HMS 키 정리
 * 
 * 5. 게이트웨이 및 서브 디바이스 오프라인 처리
 *    - 게이트웨이 오프라인 시 관련 데이터 정리
 *    - 서브 디바이스 오프라인 시 캐시 정리
 *    - 연결 상태 변경 시 데이터 동기화
 * 
 * 이 인터페이스는 Redis를 활용하여 디바이스의 실시간 상태 정보를
 * 빠르게 조회하고 관리할 수 있도록 지원합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/21
 */
public interface IDeviceRedisService {

    /**
     * 디바이스 온라인 상태 확인
     * 
     * Redis에서 특정 디바이스의 온라인 상태를 확인합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 디바이스 온라인 여부
     */
    Boolean checkDeviceOnline(String sn);

    /**
     * Redis에서 디바이스 기본 정보 조회
     * 
     * Redis에 캐싱된 디바이스의 기본 정보를 조회합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 디바이스 기본 정보 (Optional)
     */
    Optional<DeviceDTO> getDeviceOnline(String sn);

    /**
     * 디바이스 기본 정보를 Redis에 저장
     * 
     * 디바이스의 기본 정보를 Redis에 캐싱하여 저장합니다.
     * 
     * @param device 저장할 디바이스 정보
     */
    void setDeviceOnline(DeviceDTO device);

    /**
     * Redis에서 디바이스 기본 정보 삭제
     * 
     * Redis에 저장된 디바이스의 기본 정보를 삭제합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 삭제 성공 여부
     */
    Boolean delDeviceOnline(String sn);

    /**
     * 디바이스 OSD 실시간 데이터 저장
     * 
     * 디바이스의 OSD(On-Screen Display) 실시간 데이터를
     * Redis에 캐싱하여 저장합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param data 저장할 OSD 데이터
     */
    void setDeviceOsd(String sn, Object data);

    /**
     * 디바이스 OSD 실시간 데이터 조회
     * 
     * Redis에서 디바이스의 OSD 실시간 데이터를 조회합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param clazz 반환할 데이터 타입
     * @param <T> 제네릭 타입
     * @return OSD 데이터 (Optional)
     */
    <T> Optional<T> getDeviceOsd(String sn, Class<T> clazz);

    /**
     * 디바이스 OSD 실시간 데이터 삭제
     * 
     * Redis에서 디바이스의 OSD 실시간 데이터를 삭제합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 삭제 성공 여부
     */
    Boolean delDeviceOsd(String sn);

    /**
     * 디바이스 펌웨어 업그레이드 진행 상황 저장
     * 
     * 디바이스의 펌웨어 업그레이드 진행 상황을
     * Redis에 캐싱하여 저장합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param events 펌웨어 업그레이드 진행 상황 이벤트
     */
    void setFirmwareUpgrading(String sn, EventsReceiver<OtaProgress> events);

    /**
     * 디바이스 펌웨어 업그레이드 진행 상황 조회
     * 
     * Redis에서 디바이스의 펌웨어 업그레이드 진행 상황을 조회합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 펌웨어 업그레이드 진행 상황 (Optional)
     */
    Optional<EventsReceiver<OtaProgress>> getFirmwareUpgradingProgress(String sn);

    /**
     * 디바이스 펌웨어 업그레이드 진행 상황 삭제
     * 
     * Redis에서 디바이스의 펌웨어 업그레이드 진행 상황을 삭제합니다.
     * 업그레이드 완료 후 호출됩니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 삭제 성공 여부
     */
    Boolean delFirmwareUpgrading(String sn);

    /**
     * 디바이스 HMS 키 추가
     * 
     * 디바이스의 HMS(Health Management System) 키를
     * Redis에 추가하여 저장합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param keys 추가할 HMS 키들
     */
    void addEndHmsKeys(String sn, String... keys);

    /**
     * 디바이스의 모든 HMS 키 조회
     * 
     * Redis에서 특정 디바이스의 모든 HMS 키를 조회합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return HMS 키 세트
     */
    Set<String> getAllHmsKeys(String sn);

    /**
     * 디바이스의 모든 HMS 키 삭제
     * 
     * Redis에서 특정 디바이스의 모든 HMS 키를 삭제합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 삭제 성공 여부
     */
    Boolean delHmsKeysBySn(String sn);

    /**
     * 게이트웨이 오프라인 처리
     * 
     * 게이트웨이 디바이스가 오프라인 상태가 되었을 때
     * 관련된 모든 Redis 캐시 데이터를 정리합니다.
     * 
     * @param gatewaySn 게이트웨이 시리얼 번호
     */
    void gatewayOffline(String gatewaySn);

    /**
     * 서브 디바이스 오프라인 처리
     * 
     * 서브 디바이스(항공기)가 오프라인 상태가 되었을 때
     * 관련된 Redis 캐시 데이터를 정리합니다.
     * 
     * @param deviceSn 서브 디바이스 시리얼 번호
     */
    void subDeviceOffline(String deviceSn);
}
