package com.dji.sample.manage.service;

import com.dji.sample.component.websocket.model.BizCodeEnum;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.model.dto.DeviceFirmwareUpgradeDTO;
import com.dji.sample.manage.model.dto.TopologyDeviceDTO;
import com.dji.sample.manage.model.param.DeviceQueryParam;
import com.dji.sdk.cloudapi.device.ControlSourceEnum;
import com.dji.sdk.cloudapi.device.DeviceOsdHost;
import com.dji.sdk.cloudapi.device.DockModeCodeEnum;
import com.dji.sdk.cloudapi.device.DroneModeCodeEnum;
import com.dji.sdk.config.version.GatewayManager;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.PaginationData;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Optional;

/**
 * 디바이스 관리 서비스 인터페이스
 * 
 * DJI Cloud API의 디바이스 관리를 위한 핵심 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 온라인/오프라인 관리
 *    - 게이트웨이 및 서브 디바이스의 온라인/오프라인 상태 관리
 *    - MQTT 토픽 구독/구독 해제 관리
 *    - 디바이스 연결 상태 실시간 모니터링
 * 
 * 2. 디바이스 토폴로지 관리
 *    - 디바이스 간 연결 관계 관리
 *    - 웹 및 PILOT용 토폴로지 정보 제공
 *    - 디바이스 바인딩/언바인딩 관리
 * 
 * 3. 디바이스 속성 및 제어 관리
 *    - 디바이스 속성 설정 및 조회
 *    - 펌웨어 업그레이드 관리
 *    - 비행 제어 권한 관리
 * 
 * 4. 실시간 데이터 전송
 *    - OSD 데이터를 웹 및 PILOT으로 실시간 전송
 *    - WebSocket을 통한 실시간 상태 업데이트
 *    - 디바이스 상태 변경 알림
 * 
 * 이 인터페이스는 DJI 디바이스의 전체 생명주기를
 * 관리하고 모니터링할 수 있도록 지원합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/10
 * @version 0.1
 */
public interface IDeviceService {

    /**
     * 서브 디바이스 오프라인 처리
     * 
     * 서브 디바이스(항공기)가 오프라인 상태가 되었을 때 호출됩니다.
     * 디바이스의 오프라인 상태를 업데이트하고 관련 리소스를 정리합니다.
     * 
     * @param deviceSn 항공기의 시리얼 번호
     */
    void subDeviceOffline(String deviceSn);

    /**
     * 게이트웨이 오프라인 처리
     * 
     * 게이트웨이 디바이스가 오프라인 상태가 되었을 때 호출됩니다.
     * 게이트웨이와 연결된 모든 서브 디바이스의 상태를 업데이트합니다.
     * 
     * @param gatewaySn 게이트웨이의 시리얼 번호
     */
    void gatewayOffline(String gatewaySn);

    /**
     * 게이트웨이 온라인 시 토픽 구독
     * 
     * 게이트웨이 디바이스가 온라인 상태가 되었을 때 호출됩니다.
     * 게이트웨이의 MQTT 토픽을 구독하고 서브 디바이스의 토픽은 구독 해제합니다.
     * 
     * @param gateway 게이트웨이 매니저 객체
     */
    void gatewayOnlineSubscribeTopic(GatewayManager gateway);

    /**
     * 서브 디바이스 온라인 시 토픽 구독
     * 
     * 서브 디바이스(항공기)가 온라인 상태가 되었을 때 호출됩니다.
     * 게이트웨이와 서브 디바이스의 모든 MQTT 토픽을 구독합니다.
     * 
     * @param gateway 게이트웨이 매니저 객체
     */
    void subDeviceOnlineSubscribeTopic(GatewayManager gateway);

    /**
     * 오프라인 시 토픽 구독 해제
     * 
     * 디바이스가 오프라인 상태가 되었을 때 호출됩니다.
     * 게이트웨이와 서브 디바이스의 모든 MQTT 토픽 구독을 해제합니다.
     * 
     * @param gateway 게이트웨이 매니저 객체
     */
    void offlineUnsubscribeTopic(GatewayManager gateway);

    /**
     * 조건에 따른 디바이스 조회
     * 
     * 다양한 쿼리 조건에 따라 디바이스 목록을 조회합니다.
     * 워크스페이스, 도메인, 상태 등 다양한 필터링 조건을 지원합니다.
     * 
     * @param param 디바이스 쿼리 파라미터
     * @return 디바이스 목록
     */
    List<DeviceDTO> getDevicesByParams(DeviceQueryParam param);

    /**
     * 웹용 디바이스 토폴로지 조회
     * 
     * 웹 인터페이스에서 사용하는 워크스페이스 내 모든 디바이스의
     * 토폴로지 정보를 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @return 디바이스 토폴로지 목록
     */
    List<DeviceDTO> getDevicesTopoForWeb(String workspaceId);

    /**
     * 디바이스 토폴로지 정보 결합
     * 
     * 디바이스의 리모트 컨트롤러와 페이로드 정보를
     * 토폴로지 구조에 결합하여 완전한 디바이스 정보를 구성합니다.
     * 
     * @param device 디바이스 정보
     */
    void spliceDeviceTopo(DeviceDTO device);

    /**
     * PILOT용 디바이스 토폴로지 조회
     * 
     * PILOT 애플리케이션에서 사용하는 특정 디바이스의
     * 토폴로지 정보를 조회합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 디바이스 토폴로지 정보 (Optional)
     */
    Optional<TopologyDeviceDTO> getDeviceTopoForPilot(String sn);

    /**
     * 디바이스를 토폴로지 DTO로 변환
     * 
     * 개별 디바이스 정보를 토폴로지 객체로 변환합니다.
     * PILOT 애플리케이션에서 사용하는 형식으로 데이터를 변환합니다.
     * 
     * @param device 디바이스 정보
     * @return 토폴로지 디바이스 DTO
     */
    TopologyDeviceDTO deviceConvertToTopologyDTO(DeviceDTO device);

    /**
     * 디바이스 오프라인 토폴로지 푸시
     * 
     * 디바이스가 오프라인 상태가 되었을 때 워크스페이스 내의
     * 모든 PILOT 클라이언트에게 WebSocket을 통해 알림을 전송합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param deviceSn 디바이스 시리얼 번호
     */
    void pushDeviceOfflineTopo(String workspaceId, String deviceSn);

    /**
     * 디바이스 온라인 토폴로지 푸시
     * 
     * 디바이스가 온라인 상태가 되었을 때 워크스페이스 내의
     * 모든 PILOT 클라이언트에게 WebSocket을 통해 알림을 전송합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @param deviceSn 디바이스 시리얼 번호
     */
    void pushDeviceOnlineTopo(String workspaceId, String gatewaySn, String deviceSn);

    /**
     * 디바이스 정보 업데이트
     * 
     * 디바이스의 기본 정보를 업데이트합니다.
     * 
     * @param deviceDTO 업데이트할 디바이스 정보
     * @return 업데이트 성공 여부
     */
    Boolean updateDevice(DeviceDTO deviceDTO);

    /**
     * 디바이스 바인딩
     * 
     * 디바이스를 조직 및 사용자에게 바인딩합니다.
     * 
     * @param device 바인딩할 디바이스 정보
     * @return 바인딩 성공 여부
     */
    Boolean bindDevice(DeviceDTO device);

    /**
     * 도메인별 바인딩된 디바이스 조회
     * 
     * 특정 워크스페이스에서 도메인별로 바인딩된 디바이스 목록을
     * 페이지네이션과 함께 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param page 페이지 번호
     * @param pageSize 페이지 크기
     * @param domain 디바이스 도메인 (AIRCRAFT=0, DOCK=1, RC=2)
     * @return 페이지네이션된 디바이스 목록
     */
    PaginationData<DeviceDTO> getBoundDevicesWithDomain(String workspaceId, Long page, Long pageSize, Integer domain);

    /**
     * 디바이스 언바인딩
     * 
     * 디바이스의 바인딩을 해제합니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     */
    void unbindDevice(String deviceSn);

    /**
     * 시리얼 번호로 디바이스 조회
     * 
     * 디바이스의 시리얼 번호를 기반으로 디바이스 정보를 조회합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 디바이스 정보 (Optional)
     */
    Optional<DeviceDTO> getDeviceBySn(String sn);

    /**
     * 디바이스 펌웨어 업그레이드 작업 생성
     * 
     * 디바이스의 펌웨어 업그레이드를 위한 작업을 생성합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param upgradeDTOS 업그레이드할 디바이스 목록
     * @return HTTP 응답 결과
     */
    HttpResultResponse createDeviceOtaJob(String workspaceId, List<DeviceFirmwareUpgradeDTO> upgradeDTOS);

    /**
     * 디바이스 속성 설정
     * 
     * 드론의 속성 파라미터를 설정합니다.
     * RTH 고도, 장애물 회피, 고도 제한 등의 속성을 설정할 수 있습니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param dockSn 도킹 스테이션 시리얼 번호
     * @param param 설정할 속성 파라미터 (JSON 형태)
     * @return 설정 결과 코드
     */
    int devicePropertySet(String workspaceId, String dockSn, JsonNode param);

    /**
     * 도킹 스테이션 모드 조회
     * 
     * 도킹 스테이션의 현재 작업 상태를 조회합니다.
     * 
     * @param dockSn 도킹 스테이션 시리얼 번호
     * @return 도킹 스테이션 모드 코드
     */
    DockModeCodeEnum getDockMode(String dockSn);

    /**
     * 항공기 모드 조회
     * 
     * 항공기의 현재 작업 상태를 조회합니다.
     * 
     * @param deviceSn 항공기 시리얼 번호
     * @return 항공기 모드 코드
     */
    DroneModeCodeEnum getDeviceMode(String deviceSn);

    /**
     * 도킹 스테이션 DRC 모드 확인
     * 
     * 도킹 스테이션이 DRC(Data Relay Control) 모드인지 확인합니다.
     * 
     * @param dockSn 도킹 스테이션 시리얼 번호
     * @return DRC 모드 여부
     */
    Boolean checkDockDrcMode(String dockSn);

    /**
     * 비행 제어 권한 확인
     * 
     * 디바이스가 비행 제어 권한을 가지고 있는지 확인합니다.
     * 
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @return 비행 제어 권한 여부
     */
    Boolean checkAuthorityFlight(String gatewaySn);

    /**
     * 디바이스 저장
     * 
     * 새로운 디바이스를 데이터베이스에 저장합니다.
     * 
     * @param device 저장할 디바이스 정보
     * @return 저장된 디바이스 ID
     */
    Integer saveDevice(DeviceDTO device);

    /**
     * 디바이스 저장 또는 업데이트
     * 
     * 디바이스가 존재하면 업데이트하고, 존재하지 않으면 새로 저장합니다.
     * 
     * @param device 저장/업데이트할 디바이스 정보
     * @return 작업 성공 여부
     */
    Boolean saveOrUpdateDevice(DeviceDTO device);

    /**
     * PILOT으로 OSD 데이터 푸시
     * 
     * 디바이스의 OSD(On-Screen Display) 데이터를
     * PILOT 클라이언트에게 실시간으로 전송합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param sn 디바이스 시리얼 번호
     * @param data OSD 데이터
     */
    void pushOsdDataToPilot(String workspaceId, String sn, DeviceOsdHost data);

    /**
     * 웹으로 OSD 데이터 푸시
     * 
     * 디바이스의 OSD 데이터를 웹 클라이언트에게
     * WebSocket을 통해 실시간으로 전송합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param codeEnum 비즈니스 코드 열거형
     * @param sn 디바이스 시리얼 번호
     * @param data 전송할 데이터
     */
    void pushOsdDataToWeb(String workspaceId, BizCodeEnum codeEnum, String sn, Object data);

    /**
     * 비행 제어 업데이트
     * 
     * 게이트웨이의 비행 제어 소스를 업데이트합니다.
     * 
     * @param gateway 게이트웨이 디바이스 정보
     * @param controlSource 제어 소스 열거형
     */
    void updateFlightControl(DeviceDTO gateway, ControlSourceEnum controlSource);
}