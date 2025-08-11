package com.dji.sample.manage.controller;

import com.dji.sample.manage.service.ITopologyService;
import com.dji.sdk.cloudapi.tsa.TopologyList;
import com.dji.sdk.cloudapi.tsa.TopologyResponse;
import com.dji.sdk.cloudapi.tsa.api.IHttpTsaService;
import com.dji.sdk.common.HttpResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 토폴로지 관리 컨트롤러
 * 
 * DJI Cloud API의 디바이스 토폴로지 관리 기능을 제공하는 REST API 컨트롤러입니다.
 * 이 컨트롤러는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 토폴로지 조회
 *    - 현재 사용자 워크스페이스의 모든 디바이스 토폴로지 목록 조회
 *    - 디바이스 간 연결 관계 및 계층 구조 정보 제공
 *    - 토폴로지 데이터 실시간 업데이트
 * 
 * 2. 토폴로지 시각화 지원
 *    - Pilot 애플리케이션용 토폴로지 데이터 제공
 *    - 디바이스 네트워크 구조 시각화
 *    - 토폴로지 변경 사항 추적
 * 
 * 3. 디바이스 관계 관리
 *    - 도크와 드론 간의 연결 관계 관리
 *    - 디바이스 계층 구조 정보 제공
 *    - 디바이스 상태 기반 토폴로지 업데이트
 * 
 * 4. 토폴로지 데이터 표준화
 *    - DJI SDK 표준 토폴로지 응답 형식 준수
 *    - 토폴로지 데이터 일관성 보장
 *    - 토폴로지 API 호환성 유지
 * 
 * 토폴로지는 디바이스 간의 연결 관계와 네트워크 구조를 나타내며,
 * 디바이스 관리 및 모니터링에 중요한 정보를 제공합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/8
 */
@RestController
public class TopologyController implements IHttpTsaService {

    /** 토폴로지 서비스 - 토폴로지 관리 비즈니스 로직 */
    @Autowired
    private ITopologyService topologyService;

    /**
     * 현재 사용자 워크스페이스의 모든 디바이스 토폴로지 목록을 Pilot 표시용으로 조회합니다.
     * 
     * 이 API는 현재 사용자가 속한 워크스페이스의 모든 디바이스에 대한
     * 토폴로지 정보를 조회합니다. Pilot 애플리케이션에서 디바이스 네트워크 구조를
     * 시각화하는 데 사용되는 표준화된 토폴로지 데이터를 제공합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 디바이스 토폴로지 목록 (DJI SDK 표준 응답 형식)
     */
    @Override
    public HttpResultResponse<TopologyResponse> obtainDeviceTopologyList(String workspaceId, HttpServletRequest req, HttpServletResponse rsp) {
        List<TopologyList> topologyList = topologyService.getDeviceTopology(workspaceId);
        return HttpResultResponse.success(new TopologyResponse().setList(topologyList));
    }
}
