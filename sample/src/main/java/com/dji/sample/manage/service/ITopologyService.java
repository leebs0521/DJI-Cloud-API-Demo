package com.dji.sample.manage.service;

import com.dji.sdk.cloudapi.tsa.TopologyList;

import java.util.List;
import java.util.Optional;

/**
 * 디바이스 토폴로지 관리 서비스 인터페이스
 * 
 * DJI Cloud API의 디바이스 토폴로지 관리를 위한 서비스 인터페이스입니다.
 * 이 인터페이스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 워크스페이스 토폴로지 관리
 *    - 워크스페이스 내 모든 디바이스의 토폴로지 조회
 *    - PILOT 애플리케이션용 토폴로지 정보 제공
 *    - 디바이스 간 연결 관계 관리
 * 
 * 2. 게이트웨이 기반 토폴로지 관리
 *    - 게이트웨이 시리얼 번호 기반 토폴로지 조회
 *    - 게이트웨이-서브 디바이스 관계 관리
 *    - 특정 게이트웨이의 연결 상태 확인
 * 
 * 3. 토폴로지 정보 표준화
 *    - 토폴로지 데이터 형식 통일
 *    - 디바이스 연결 관계의 일관성 보장
 *    - 토폴로지 정보의 정규화
 * 
 * 이 인터페이스는 DJI 디바이스의 연결 관계와 토폴로지를
 * 체계적으로 관리하고 조회할 수 있도록 지원합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/8
 */
public interface ITopologyService {

    /**
     * 워크스페이스 디바이스 토폴로지 조회
     * 
     * 워크스페이스 내의 모든 디바이스 토폴로지 정보를 조회합니다.
     * PILOT 애플리케이션에서 디바이스 연결 관계를 표시할 때 사용됩니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @return 디바이스 토폴로지 목록
     */
    List<TopologyList> getDeviceTopology(String workspaceId);

    /**
     * 게이트웨이 기반 디바이스 토폴로지 조회
     * 
     * 특정 게이트웨이의 시리얼 번호를 기반으로
     * 해당 게이트웨이와 연결된 디바이스들의 토폴로지를 조회합니다.
     * 
     * @param gatewaySn 게이트웨이 시리얼 번호
     * @return 디바이스 토폴로지 정보 (Optional)
     */
    Optional<TopologyList> getDeviceTopologyByGatewaySn(String gatewaySn);
}
