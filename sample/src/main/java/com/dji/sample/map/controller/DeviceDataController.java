package com.dji.sample.map.controller;

import com.dji.sample.map.model.dto.DeviceDataStatusDTO;
import com.dji.sample.map.service.IDeviceDataService;
import com.dji.sdk.common.HttpResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 디바이스 데이터 관리 컨트롤러
 * ...
 */
@Tag(name = "디바이스 데이터 관리", description = "디바이스 데이터 상태 조회 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("${url.map.prefix}${url.map.version}/workspaces")
public class DeviceDataController {

    private final IDeviceDataService deviceDataService;

    /**
     * 워크스페이스별 디바이스 비행 영역 상태 조회
     * ...
     */
    @Operation(summary = "워크스페이스별 디바이스 비행 영역 상태 조회",
            description = "지정된 워크스페이스의 모든 디바이스에 대한 비행 영역 상태 정보를 조회합니다")
    @GetMapping("/{workspace_id}/device-status")
    public HttpResultResponse<List<DeviceDataStatusDTO>> getDeviceFlightAreaStatus(
            @Parameter(description = "워크스페이스 ID") @PathVariable(name = "workspace_id") String workspaceId
    ) {
        return HttpResultResponse.success(deviceDataService.getDevicesDataStatus(workspaceId));
    }
}
