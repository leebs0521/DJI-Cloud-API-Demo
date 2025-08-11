package com.dji.sample.manage.controller;

import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.model.dto.DeviceFirmwareUpgradeDTO;
import com.dji.sample.manage.service.IDeviceService;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.PaginationData;
import com.dji.sdk.exception.CloudSDKErrorEnum;
import com.dji.sdk.mqtt.property.PropertySetReplyResultEnum;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 디바이스 관리 컨트롤러
 * 
 * DJI Cloud API의 디바이스 관리 기능을 제공하는 REST API 컨트롤러입니다.
 * 이 컨트롤러는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 디바이스 조회 및 관리
 *    - 워크스페이스별 온라인 디바이스 토폴로지 조회
 *    - 디바이스 정보 조회 및 수정
 *    - 바인딩된 디바이스 목록 조회
 * 
 * 2. 디바이스 바인딩 관리
 *    - 디바이스를 워크스페이스에 바인딩
 *    - 디바이스 바인딩 해제
 *    - 바인딩 상태 관리
 * 
 * 3. 펌웨어 업그레이드 관리
 *    - OTA(Over-The-Air) 펌웨어 업그레이드 작업 생성
 *    - 펌웨어 업그레이드 상태 관리
 * 
 * 4. 디바이스 속성 설정
 *    - 드론 속성 파라미터 설정
 *    - 속성 설정 결과 확인
 * 
 * 모든 API는 RESTful 설계 원칙을 따르며, HTTP 상태 코드와
 * 표준화된 응답 형식을 사용합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/15
 */
@RestController
@Slf4j
@RequestMapping("${url.manage.prefix}${url.manage.version}/devices")
public class DeviceController {

    /** 디바이스 서비스 - 디바이스 관리 비즈니스 로직 */
    @Autowired
    private IDeviceService deviceService;

    /**
     * 워크스페이스의 모든 온라인 디바이스 토폴로지 목록을 조회합니다.
     * 
     * 이 API는 지정된 워크스페이스에 속한 모든 온라인 디바이스의
     * 토폴로지 정보를 조회합니다. 토폴로지는 디바이스 간의 연결 관계와
     * 계층 구조를 나타냅니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @return 디바이스 토폴로지 목록 (성공 시) 또는 오류 응답
     */
    @GetMapping("/{workspace_id}/devices")
    public HttpResultResponse<List<DeviceDTO>> getDevices(@PathVariable("workspace_id") String workspaceId) {
        List<DeviceDTO> devicesList = deviceService.getDevicesTopoForWeb(workspaceId);

        return HttpResultResponse.success(devicesList);
    }

    /**
     * 디바이스를 워크스페이스에 바인딩합니다.
     * 
     * 디바이스를 워크스페이스에 바인딩하면 해당 워크스페이스에서만
     * 디바이스 데이터를 볼 수 있습니다. 바인딩은 디바이스 관리의
     * 기본 단계로, 바인딩 후에 디바이스 제어 및 모니터링이 가능합니다.
     * 
     * @param device 디바이스 정보 (바인딩할 디바이스 정보)
     * @param deviceSn 디바이스 시리얼 번호
     * @return 바인딩 성공 여부 (성공 시) 또는 오류 응답
     */
    @PostMapping("/{device_sn}/binding")
    public HttpResultResponse bindDevice(@RequestBody DeviceDTO device, @PathVariable("device_sn") String deviceSn) {
        device.setDeviceSn(deviceSn);
        boolean isUpd = deviceService.bindDevice(device);
        return isUpd ? HttpResultResponse.success() : HttpResultResponse.error();
    }

    /**
     * 디바이스 시리얼 번호로 디바이스 정보를 조회합니다.
     * 
     * 이 API는 특정 디바이스의 상세 정보를 조회합니다.
     * 디바이스가 존재하지 않는 경우 오류를 반환합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param deviceSn 디바이스 시리얼 번호
     * @return 디바이스 정보 (성공 시) 또는 오류 응답
     */
    @GetMapping("/{workspace_id}/devices/{device_sn}")
    public HttpResultResponse getDevice(@PathVariable("workspace_id") String workspaceId,
                                        @PathVariable("device_sn") String deviceSn) {
        Optional<DeviceDTO> deviceOpt = deviceService.getDeviceBySn(deviceSn);
        return deviceOpt.isEmpty() ? HttpResultResponse.error("device not found.") : HttpResultResponse.success(deviceOpt.get());
    }

    /**
     * 워크스페이스의 바인딩된 디바이스 목록을 페이지네이션으로 조회합니다.
     * 
     * 이 API는 워크스페이스에 바인딩된 디바이스들을 페이지 단위로 조회합니다.
     * 도메인 필터링을 통해 특정 도메인의 디바이스만 조회할 수 있습니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param domain 도메인 필터 (선택적)
     * @param page 현재 페이지 번호 (기본값: 1)
     * @param pageSize 페이지당 항목 수 (기본값: 50)
     * @return 바인딩된 디바이스 목록 (페이지네이션 포함)
     */
    @GetMapping("/{workspace_id}/devices/bound")
    public HttpResultResponse<PaginationData<DeviceDTO>> getBoundDevicesWithDomain(
            @PathVariable("workspace_id") String workspaceId, Integer domain,
            @RequestParam(defaultValue = "1") Long page,
            @RequestParam(value = "page_size", defaultValue = "50") Long pageSize) {
        PaginationData<DeviceDTO> devices = deviceService.getBoundDevicesWithDomain(workspaceId, page, pageSize, domain);

        return HttpResultResponse.success(devices);
    }

    /**
     * 디바이스의 바인딩 상태를 해제합니다.
     * 
     * 디바이스 바인딩을 해제하면 해당 워크스페이스에서
     * 디바이스에 대한 접근 권한이 제거됩니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @return 바인딩 해제 성공 여부
     */
    @DeleteMapping("/{device_sn}/unbinding")
    public HttpResultResponse unbindingDevice(@PathVariable("device_sn") String deviceSn) {
        deviceService.unbindDevice(deviceSn);
        return HttpResultResponse.success();
    }

    /**
     * 디바이스 정보를 업데이트합니다.
     * 
     * 디바이스의 메타데이터 정보를 수정합니다.
     * 디바이스 시리얼 번호는 변경할 수 없습니다.
     * 
     * @param device 업데이트할 디바이스 정보
     * @param workspaceId 워크스페이스 ID
     * @param deviceSn 디바이스 시리얼 번호
     * @return 업데이트 성공 여부
     */
    @PutMapping("/{workspace_id}/devices/{device_sn}")
    public HttpResultResponse updateDevice(@RequestBody DeviceDTO device,
                                           @PathVariable("workspace_id") String workspaceId,
                                           @PathVariable("device_sn") String deviceSn) {
        device.setDeviceSn(deviceSn);
        boolean isUpd = deviceService.updateDevice(device);
        return isUpd ? HttpResultResponse.success() : HttpResultResponse.error();
    }

    /**
     * 오프라인 펌웨어 업그레이드 작업을 생성합니다.
     * 
     * 이 API는 OTA(Over-The-Air) 펌웨어 업그레이드 작업을 생성합니다.
     * 여러 디바이스에 대한 펌웨어 업그레이드를 일괄 처리할 수 있습니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param upgradeDTOS 펌웨어 업그레이드 정보 목록
     * @return 업그레이드 작업 생성 결과
     */
    @PostMapping("/{workspace_id}/devices/ota")
    public HttpResultResponse createOtaJob(@PathVariable("workspace_id") String workspaceId,
                                           @RequestBody List<DeviceFirmwareUpgradeDTO> upgradeDTOS) {
        return deviceService.createDeviceOtaJob(workspaceId, upgradeDTOS);
    }

    /**
     * 드론의 속성 파라미터를 설정합니다.
     * 
     * 이 API는 드론의 다양한 속성 파라미터를 설정합니다.
     * 한 번에 하나의 속성만 설정할 수 있으며, 설정 결과를 확인합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param dockSn 도크 시리얼 번호
     * @param param 설정할 속성 파라미터 (JSON 형태)
     * @return 속성 설정 성공 여부
     */
    @PutMapping("/{workspace_id}/devices/{device_sn}/property")
    public HttpResultResponse devicePropertySet(@PathVariable("workspace_id") String workspaceId,
                                                @PathVariable("device_sn") String dockSn,
                                                @RequestBody JsonNode param) {
        if (param.size() != 1) {
            return HttpResultResponse.error(CloudSDKErrorEnum.INVALID_PARAMETER);
        }

        int result = deviceService.devicePropertySet(workspaceId, dockSn, param);
        return PropertySetReplyResultEnum.SUCCESS.getResult() == result ?
                HttpResultResponse.success() : HttpResultResponse.error(result, String.valueOf(result));
    }
}