package com.dji.sample.map.controller;

import com.dji.sample.map.model.dto.DeviceDataStatusDTO;
import com.dji.sample.map.service.IDeviceDataService;
import com.dji.sdk.common.HttpResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 디바이스 데이터 컨트롤러
 * 
 * DJI Cloud API의 디바이스 데이터 관리를 위한 REST API 컨트롤러입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 상태 정보 조회
 *    - 워크스페이스별 디바이스 상태 정보 제공
 *    - 디바이스 비행 영역 상태 조회
 *    - 실시간 디바이스 데이터 상태 모니터링
 *    - 디바이스 상태 정보의 RESTful API 제공
 * 
 * 2. 워크스페이스 기반 데이터 관리
 *    - 워크스페이스 ID 기반 디바이스 데이터 조회
 *    - 워크스페이스별 디바이스 상태 집계
 *    - 조직별 디바이스 데이터 분리 관리
 *    - 멀티 테넌트 지원
 * 
 * 3. RESTful API 설계
 *    - 표준 HTTP 메서드 사용
 *    - RESTful URL 구조 설계
 *    - JSON 기반 데이터 교환
 *    - HTTP 상태 코드 기반 응답
 * 
 * 4. 디바이스 데이터 서비스 연동
 *    - IDeviceDataService를 통한 비즈니스 로직 처리
 *    - 디바이스 데이터 상태 조회 서비스 호출
 *    - 서비스 계층과의 분리된 구조
 *    - 의존성 주입을 통한 느슨한 결합
 * 
 * 주요 의존성:
 * - IDeviceDataService: 디바이스 데이터 관리 서비스
 * - DeviceDataStatusDTO: 디바이스 데이터 상태 전송 객체
 * - HttpResultResponse: HTTP 응답 래퍼 클래스
 * 
 * API 엔드포인트:
 * - GET /workspaces/{workspace_id}/device-status: 워크스페이스별 디바이스 상태 조회
 * 
 * 이 클래스는 DJI Cloud API의 디바이스 데이터를
 * RESTful API로 제공하는 컨트롤러입니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/11/24
 */
@RestController
@RequestMapping("${url.map.prefix}${url.map.version}/workspaces")
public class DeviceDataController {

    /**
     * 디바이스 데이터 관리 서비스
     * 디바이스 데이터 상태 조회 및 관리를 담당
     */
    @Autowired
    private IDeviceDataService deviceDataService;

    /**
     * 워크스페이스별 디바이스 비행 영역 상태를 조회합니다.
     * 
     * 지정된 워크스페이스의 모든 디바이스에 대한
     * 비행 영역 상태 정보를 조회하여 반환합니다.
     * 이 API는 디바이스의 현재 상태와 비행 영역 정보를
     * 실시간으로 모니터링할 수 있도록 지원합니다.
     * 
     * @param workspaceId 워크스페이스 ID (경로 변수)
     * @return 디바이스 데이터 상태 정보 리스트를 포함한 HTTP 응답
     */
    @GetMapping("/{workspace_id}/device-status")
    public HttpResultResponse<List<DeviceDataStatusDTO>> getDeviceFlightAreaStatus(@PathVariable(name = "workspace_id") String workspaceId) {
        // 디바이스 데이터 서비스를 통해 워크스페이스별 디바이스 상태 정보 조회
        return HttpResultResponse.success(deviceDataService.getDevicesDataStatus(workspaceId));
    }
}
