package com.dji.sample.map.service;

import com.dji.sample.map.model.dto.DeviceDataStatusDTO;
import com.dji.sample.map.model.dto.DeviceFlightAreaDTO;

import java.util.List;
import java.util.Optional;

/**
 * 디바이스 데이터 서비스 인터페이스
 * 
 * DJI Cloud API의 디바이스 데이터 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 디바이스의 상태 정보와 비행 영역 상태를 조회하는 기능을 제공합니다.
 * 
 * 1. 디바이스 상태 조회 기능
 *    - 워크스페이스별 모든 디바이스 상태 조회 (getDevicesDataStatus)
 *    - 개별 디바이스 상태 조회 (getDeviceStatus)
 * 
 * 2. 디바이스 데이터 관리
 *    - 디바이스 기본 정보 및 상태 관리
 *    - 디바이스별 비행 영역 상태 관리
 * 
 * 3. 워크스페이스별 디바이스 관리
 *    - 워크스페이스 내 디바이스 목록 관리
 *    - 디바이스별 상태 추적
 * 
 * 주요 용도:
 * - 디바이스 상태 모니터링
 * - 디바이스별 비행 영역 상태 조회
 * - 워크스페이스별 디바이스 관리
 * - 디바이스 데이터 대시보드 제공
 * 
 * 사용 예시:
 * - 디바이스 목록 및 상태 조회
 * - 개별 디바이스 상세 정보 조회
 * - 디바이스별 비행 영역 상태 확인
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/24
 */
public interface IDeviceDataService {

    /**
     * 워크스페이스 ID를 기반으로 해당 워크스페이스의 모든 디바이스 상태를 조회합니다.
     * 
     * @param workspaceId 조회할 워크스페이스의 고유 식별자
     * @return 디바이스 상태 정보 목록
     */
    List<DeviceDataStatusDTO> getDevicesDataStatus(String workspaceId);

    /**
     * 워크스페이스 ID와 디바이스 시리얼 번호를 기반으로 특정 디바이스의 상태를 조회합니다.
     * 
     * @param workspaceId 디바이스가 속한 워크스페이스의 고유 식별자
     * @param deviceSn 조회할 디바이스의 시리얼 번호
     * @return 디바이스 비행 영역 상태 정보를 포함하는 Optional 객체
     */
    Optional<DeviceFlightAreaDTO> getDeviceStatus(String workspaceId, String deviceSn);
}
