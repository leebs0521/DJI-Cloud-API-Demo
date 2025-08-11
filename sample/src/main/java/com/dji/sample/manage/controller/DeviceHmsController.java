package com.dji.sample.manage.controller;

import com.dji.sample.manage.model.dto.DeviceHmsDTO;
import com.dji.sample.manage.model.param.DeviceHmsQueryParam;
import com.dji.sample.manage.service.IDeviceHmsService;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.PaginationData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 디바이스 HMS 관리 컨트롤러
 * 
 * DJI Cloud API의 디바이스 HMS(Health Management System) 관리 기능을 제공하는 REST API 컨트롤러입니다.
 * 이 컨트롤러는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. HMS 정보 조회
 *    - 디바이스 HMS 정보 페이지네이션 조회
 *    - 특정 디바이스의 HMS 메시지 조회
 *    - HMS 메시지 필터링 및 검색
 * 
 * 2. HMS 상태 관리
 *    - 읽지 않은 HMS 메시지를 읽음 상태로 업데이트
 *    - HMS 메시지 상태 추적
 *    - HMS 알림 관리
 * 
 * 3. HMS 메시지 처리
 *    - 단일 디바이스 HMS 메시지 조회
 *    - HMS 메시지 우선순위 관리
 *    - HMS 메시지 이력 관리
 * 
 * 4. HMS 모니터링
 *    - 디바이스 상태 모니터링
 *    - HMS 경고 및 오류 알림
 *    - 디바이스 건강 상태 추적
 * 
 * HMS는 디바이스의 건강 상태를 모니터링하고 관리하는 시스템으로,
 * 디바이스의 안전한 운영을 위한 중요한 정보를 제공합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/7/7
 */

@RestController
@Slf4j
@RequestMapping("${url.manage.prefix}${url.manage.version}/devices")
public class DeviceHmsController {

    /** 디바이스 HMS 서비스 - HMS 관리 비즈니스 로직 */
    @Autowired
    private IDeviceHmsService deviceHmsService;

    /**
     * 디바이스의 HMS 정보를 페이지네이션으로 조회합니다.
     * 
     * 이 API는 다양한 검색 조건을 사용하여 디바이스의 HMS 정보를 페이지네이션으로 조회합니다.
     * 디바이스 시리얼 번호, 시간 범위, 메시지 타입 등으로 필터링할 수 있습니다.
     * 
     * @param param HMS 조회 파라미터 (검색 조건, 페이지네이션 등)
     * @param workspaceId 워크스페이스 ID
     * @return HMS 정보 목록 (페이지네이션 포함)
     */
    @GetMapping("/{workspace_id}/devices/hms")
    public HttpResultResponse<PaginationData<DeviceHmsDTO>> getHmsInformation(DeviceHmsQueryParam param,
                                                                              @PathVariable("workspace_id") String workspaceId) {
        PaginationData<DeviceHmsDTO> devices = deviceHmsService.getDeviceHmsByParam(param);

        return HttpResultResponse.success(devices);
    }

    /**
     * 읽지 않은 HMS 메시지를 읽음 상태로 업데이트합니다.
     * 
     * 이 API는 특정 디바이스의 읽지 않은 HMS 메시지들을 읽음 상태로 변경합니다.
     * 사용자가 HMS 메시지를 확인했음을 시스템에 알립니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @return HMS 상태 업데이트 결과
     */
    @PutMapping("/{workspace_id}/devices/hms/{device_sn}")
    public HttpResultResponse updateReadHmsByDeviceSn(@PathVariable("device_sn") String deviceSn) {
        deviceHmsService.updateUnreadHms(deviceSn);
        return HttpResultResponse.success();
    }

    /**
     * 단일 디바이스의 HMS 메시지를 조회합니다.
     * 
     * 이 API는 특정 디바이스의 HMS 메시지들을 조회합니다.
     * 업데이트 시간을 기준으로 필터링하여 최신 메시지만 조회할 수 있습니다.
     * 
     * @param deviceSn 디바이스 시리얼 번호
     * @return HMS 메시지 목록
     */
    @GetMapping("/{workspace_id}/devices/hms/{device_sn}")
    public HttpResultResponse<List<DeviceHmsDTO>> getUnreadHmsByDeviceSn(@PathVariable("device_sn") String deviceSn) {
        PaginationData<DeviceHmsDTO> paginationData = deviceHmsService.getDeviceHmsByParam(
                DeviceHmsQueryParam.builder()
                        .deviceSn(new HashSet<>(Set.of(deviceSn)))
                        .updateTime(0L)
                        .build());
        return HttpResultResponse.success(paginationData.getList());
    }
}
