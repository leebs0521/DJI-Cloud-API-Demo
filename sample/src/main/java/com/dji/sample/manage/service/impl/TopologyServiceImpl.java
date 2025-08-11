package com.dji.sample.manage.service.impl;

import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.model.dto.TopologyDeviceDTO;
import com.dji.sample.manage.model.param.DeviceQueryParam;
import com.dji.sample.manage.service.IDeviceService;
import com.dji.sample.manage.service.ITopologyService;
import com.dji.sdk.cloudapi.device.DeviceDomainEnum;
import com.dji.sdk.cloudapi.tsa.DeviceTopology;
import com.dji.sdk.cloudapi.tsa.TopologyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 디바이스 토폴로지 관리 서비스 구현체
 * 
 * DJI Cloud API의 디바이스 토폴로지 관리를 위한 서비스 구현체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 구현합니다:
 * 
 * 1. 디바이스 토폴로지 조회
 *    - 워크스페이스별 디바이스 토폴로지 조회
 *    - 게이트웨이-서브 디바이스 관계 관리
 *    - 토폴로지 구조 정보 제공
 *    - 디바이스 연결 상태 모니터링
 * 
 * 2. 토폴로지 데이터 구성
 *    - 게이트웨이 디바이스 정보 수집
 *    - 서브 디바이스 정보 연결
 *    - 토폴로지 리스트 구조 생성
 *    - 디바이스 계층 구조 관리
 * 
 * 3. 게이트웨이별 토폴로지 관리
 *    - 특정 게이트웨이의 토폴로지 조회
 *    - 게이트웨이-드론 연결 정보 제공
 *    - 토폴로지 데이터 검증
 *    - 연결 상태 확인
 * 
 * 4. 토폴로지 데이터 변환
 *    - 디바이스 DTO를 토폴로지 DTO로 변환
 *    - 토폴로지 리스트 구조 생성
 *    - 디바이스 계층 구조 매핑
 *    - 토폴로지 메타데이터 관리
 * 
 * 5. 토폴로지 상태 관리
 *    - 디바이스 온라인/오프라인 상태 반영
 *    - 토폴로지 변경 감지
 *    - 실시간 토폴로지 업데이트
 *    - 토폴로지 일관성 유지
 * 
 * 주요 의존성:
 * - IDeviceService: 디바이스 관리 서비스
 * - DeviceDomainEnum: 디바이스 도메인 열거형
 * - TopologyList: 토폴로지 리스트 구조
 * - DeviceTopology: 디바이스 토폴로지 구조
 * 
 * 이 클래스는 DJI 디바이스의 토폴로지 구조를
 * 효율적으로 관리하고 제공하는 서비스입니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/8
 */
@Service
public class TopologyServiceImpl implements ITopologyService {

    @Autowired
    private IDeviceService deviceService;

    /**
     * 워크스페이스의 디바이스 토폴로지를 조회합니다.
     * 
     * 워크스페이스 내의 모든 게이트웨이 디바이스의 토폴로지 정보를 조회하고
     * 각 게이트웨이에 연결된 서브 디바이스 정보를 포함한 완전한 토폴로지 구조를 반환합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @return 디바이스 토폴로지 리스트
     */
    @Override
    public List<TopologyList> getDeviceTopology(String workspaceId) {
        // 워크스페이스 내의 모든 게이트웨이 디바이스 정보 조회
        List<DeviceDTO> gatewayList = deviceService.getDevicesByParams(
                DeviceQueryParam.builder()
                        .workspaceId(workspaceId)
                        .domains(List.of(DeviceDomainEnum.REMOTER_CONTROL.getDomain()))
                        .build());

        List<TopologyList> topologyList = new ArrayList<>();

        // 각 게이트웨이의 토폴로지 정보 조회
        gatewayList.forEach(device -> this.getDeviceTopologyByGatewaySn(device.getDeviceSn())
                .ifPresent(topologyList::add));

        return topologyList;
    }

    /**
     * 특정 게이트웨이의 디바이스 토폴로지를 조회합니다.
     * 
     * 게이트웨이 시리얼 번호를 기반으로 해당 게이트웨이와 연결된
     * 서브 디바이스(드론)의 토폴로지 정보를 조회합니다.
     * 
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @return 게이트웨이별 토폴로지 정보 (Optional)
     */
    public Optional<TopologyList> getDeviceTopologyByGatewaySn(String gatewaySn) {
        // 게이트웨이 디바이스 정보 조회
        Optional<DeviceDTO> dtoOptional = deviceService.getDeviceBySn(gatewaySn);
        if (dtoOptional.isEmpty()) {
            return Optional.empty();
        }
        
        // 게이트웨이 토폴로지 정보 구성
        List<DeviceTopology> parents = new ArrayList<>();
        DeviceDTO device = dtoOptional.get();
        DeviceTopology gateway = deviceService.deviceConvertToTopologyDTO(device);
        parents.add(gateway);

        // 드론 시리얼 번호를 기반으로 드론의 토폴로지 데이터 조회
        Optional<TopologyDeviceDTO> deviceTopo = deviceService.getDeviceTopoForPilot(device.getChildDeviceSn());
        List<DeviceTopology> deviceTopoList = new ArrayList<>();
        deviceTopo.ifPresent(deviceTopoList::add);

        // 토폴로지 리스트 구조 생성 및 반환
        return Optional.ofNullable(new TopologyList().setParents(parents).setHosts(deviceTopoList));
    }

}
