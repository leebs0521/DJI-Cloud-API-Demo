package com.dji.sample.map.service;

import com.dji.sample.map.model.dto.DeviceFlightAreaDTO;

import java.util.Optional;

/**
 * 디바이스 비행 영역 서비스 인터페이스
 * 
 * DJI Cloud API의 디바이스 비행 영역 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 디바이스와 비행 영역 간의 관계 및 동기화 상태를 관리하는 기능을 제공합니다.
 * 
 * 1. 디바이스 비행 영역 조회 기능
 *    - 디바이스별 비행 영역 정보 조회 (getDeviceFlightAreaByDevice)
 * 
 * 2. 디바이스 비행 영역 관리 기능
 *    - 디바이스 파일 정보 업데이트 (updateDeviceFile)
 *    - 디바이스 파일 정보 업데이트 또는 저장 (updateOrSaveDeviceFile)
 * 
 * 3. 디바이스-비행영역 관계 관리
 *    - 디바이스와 비행 영역 간의 매핑 관리
 *    - 비행 영역 동기화 상태 추적
 * 
 * 주요 용도:
 * - 디바이스별 비행 영역 할당 관리
 * - 비행 영역 동기화 상태 추적
 * - 디바이스 파일 정보 관리
 * - 비행 안전 규칙 적용
 * 
 * 사용 예시:
 * - 디바이스별 비행 영역 정보 조회
 * - 비행 영역 동기화 상태 업데이트
 * - 디바이스 파일 정보 관리
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/23
 */
public interface IDeviceFlightAreaService {

    /**
     * 워크스페이스 ID와 디바이스 시리얼 번호를 기반으로 디바이스의 비행 영역 정보를 조회합니다.
     * 
     * @param workspaceId 디바이스가 속한 워크스페이스의 고유 식별자
     * @param deviceSn 조회할 디바이스의 시리얼 번호
     * @return 디바이스 비행 영역 정보를 포함하는 Optional 객체
     */
    Optional<DeviceFlightAreaDTO> getDeviceFlightAreaByDevice(String workspaceId, String deviceSn);

    /**
     * 디바이스 파일 정보를 업데이트합니다.
     * 
     * @param deviceFile 업데이트할 디바이스 파일 정보
     * @return 업데이트 성공 여부
     */
    Boolean updateDeviceFile(DeviceFlightAreaDTO deviceFile);

    /**
     * 디바이스 파일 정보를 업데이트하거나 저장합니다.
     * 기존 정보가 있으면 업데이트하고, 없으면 새로 저장합니다.
     * 
     * @param deviceFile 업데이트 또는 저장할 디바이스 파일 정보
     * @return 업데이트 또는 저장 성공 여부
     */
    Boolean updateOrSaveDeviceFile(DeviceFlightAreaDTO deviceFile);
}
