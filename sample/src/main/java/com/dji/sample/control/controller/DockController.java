package com.dji.sample.control.controller;

import com.dji.sample.control.model.enums.DroneAuthorityEnum;
import com.dji.sample.control.model.enums.RemoteDebugMethodEnum;
import com.dji.sample.control.model.param.*;
import com.dji.sample.control.service.IControlService;
import com.dji.sdk.common.HttpResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 도크 제어 컨트롤러 클래스
 */
@RestController
@Slf4j
@RequestMapping("${url.control.prefix}${url.control.version}/devices")
@Tag(name = "도크 제어", description = "도크(Dock) 디바이스의 비행, 페이로드, 원격 디버깅 등을 제어하는 API")
public class DockController {

    @Autowired
    private IControlService controlService;

    @Operation(
        summary = "원격 디버깅 작업 생성",
        description = "도크 디바이스에 대한 원격 디버깅 작업을 생성합니다.")
    @PostMapping("/{sn}/jobs/{service_identifier}")
    public HttpResultResponse createControlJob(
            @Parameter(description = "디바이스 시리얼 번호") @PathVariable String sn,
            @Parameter(description = "서비스 식별자") @PathVariable("service_identifier") String serviceIdentifier,
            @Parameter(description = "원격 디버깅 파라미터") @Valid @RequestBody(required = false) RemoteDebugParam param) {
        return controlService.controlDockDebug(sn, RemoteDebugMethodEnum.find(serviceIdentifier), param);
    }

    @Operation(
        summary = "특정 지점으로 비행",
        description = "드론을 특정 지점으로 비행시킵니다.")
    @PostMapping("/{sn}/jobs/fly-to-point")
    public HttpResultResponse flyToPoint(
            @Parameter(description = "디바이스 시리얼 번호") @PathVariable String sn,
            @Parameter(description = "비행 목표 지점 파라미터") @Valid @RequestBody FlyToPointParam param) {
        return controlService.flyToPoint(sn, param);
    }

    @Operation(
        summary = "비행 중지",
        description = "현재 진행 중인 비행을 중지합니다.")
    @DeleteMapping("/{sn}/jobs/fly-to-point")
    public HttpResultResponse flyToPointStop(
            @Parameter(description = "디바이스 시리얼 번호") @PathVariable String sn) {
        return controlService.flyToPointStop(sn);
    }

    @Operation(
        summary = "특정 지점으로 이륙",
        description = "드론을 특정 지점으로 이륙시킵니다.")
    @PostMapping("/{sn}/jobs/takeoff-to-point")
    public HttpResultResponse takeoffToPoint(
            @Parameter(description = "디바이스 시리얼 번호") @PathVariable String sn,
            @Parameter(description = "이륙 목표 지점 파라미터") @Valid @RequestBody TakeoffToPointParam param) {
        return controlService.takeoffToPoint(sn, param);
    }

    @Operation(
        summary = "비행 권한 획득",
        description = "드론의 비행 제어 권한을 획득합니다.")
    @PostMapping("/{sn}/authority/flight")
    public HttpResultResponse seizeFlightAuthority(
            @Parameter(description = "디바이스 시리얼 번호") @PathVariable String sn) {
        return controlService.seizeAuthority(sn, DroneAuthorityEnum.FLIGHT, null);
    }

    @Operation(
        summary = "페이로드 권한 획득",
        description = "드론의 페이로드(카메라, 짐벌 등) 제어 권한을 획득합니다.")
    @PostMapping("/{sn}/authority/payload")
    public HttpResultResponse seizePayloadAuthority(
            @Parameter(description = "디바이스 시리얼 번호") @PathVariable String sn,
            @Parameter(description = "페이로드 파라미터") @Valid @RequestBody DronePayloadParam param) {
        return controlService.seizeAuthority(sn, DroneAuthorityEnum.PAYLOAD, param);
    }

    @Operation(
        summary = "페이로드 명령 전송",
        description = "드론의 페이로드(카메라, 짐벌 등)에 제어 명령을 전송합니다.")
    @PostMapping("/{sn}/payload/commands")
    public HttpResultResponse payloadCommands(
            @Parameter(description = "디바이스 시리얼 번호") @PathVariable String sn,
            @Parameter(description = "페이로드 명령 파라미터") @Valid @RequestBody PayloadCommandsParam param) throws Exception {
        param.setSn(sn);
        return controlService.payloadCommands(param);
    }
}
