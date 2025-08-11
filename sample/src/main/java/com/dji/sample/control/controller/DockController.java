package com.dji.sample.control.controller;

import com.dji.sample.control.model.enums.DroneAuthorityEnum;
import com.dji.sample.control.model.enums.RemoteDebugMethodEnum;
import com.dji.sample.control.model.param.*;
import com.dji.sample.control.service.IControlService;
import com.dji.sdk.common.HttpResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 도크 제어 컨트롤러 클래스
 * 
 * 도크(Dock) 디바이스에 대한 제어 기능을 제공하는 REST API 컨트롤러입니다.
 * 드론 비행 제어, 페이로드 제어, 원격 디버깅 등의 기능을 포함합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/7/29
 */
@RestController
@Slf4j
@RequestMapping("${url.control.prefix}${url.control.version}/devices")
public class DockController {

    /** 제어 서비스 */
    @Autowired
    private IControlService controlService;

    /**
     * 원격 디버깅 작업을 생성합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param serviceIdentifier 서비스 식별자
     * @param param 원격 디버깅 파라미터
     * @return 제어 작업 생성 결과
     */
    @PostMapping("/{sn}/jobs/{service_identifier}")
    public HttpResultResponse createControlJob(@PathVariable String sn,
                                               @PathVariable("service_identifier") String serviceIdentifier,
                                               @Valid @RequestBody(required = false) RemoteDebugParam param) {
        return controlService.controlDockDebug(sn, RemoteDebugMethodEnum.find(serviceIdentifier), param);
    }

    /**
     * 특정 지점으로 비행하는 작업을 생성합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param param 특정 지점 비행 파라미터
     * @return 비행 작업 생성 결과
     */
    @PostMapping("/{sn}/jobs/fly-to-point")
    public HttpResultResponse flyToPoint(@PathVariable String sn, @Valid @RequestBody FlyToPointParam param) {
        return controlService.flyToPoint(sn, param);
    }

    /**
     * 특정 지점으로 비행하는 작업을 중지합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 비행 작업 중지 결과
     */
    @DeleteMapping("/{sn}/jobs/fly-to-point")
    public HttpResultResponse flyToPointStop(@PathVariable String sn) {
        return controlService.flyToPointStop(sn);
    }

    /**
     * 특정 지점으로 이륙하는 작업을 생성합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param param 특정 지점 이륙 파라미터
     * @return 이륙 작업 생성 결과
     */
    @PostMapping("/{sn}/jobs/takeoff-to-point")
    public HttpResultResponse takeoffToPoint(@PathVariable String sn, @Valid @RequestBody TakeoffToPointParam param) {
        return controlService.takeoffToPoint(sn, param);
    }

    /**
     * 비행 권한을 획득합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @return 비행 권한 획득 결과
     */
    @PostMapping("/{sn}/authority/flight")
    public HttpResultResponse seizeFlightAuthority(@PathVariable String sn) {
        return controlService.seizeAuthority(sn, DroneAuthorityEnum.FLIGHT, null);
    }

    /**
     * 페이로드 권한을 획득합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param param 드론 페이로드 파라미터
     * @return 페이로드 권한 획득 결과
     */
    @PostMapping("/{sn}/authority/payload")
    public HttpResultResponse seizePayloadAuthority(@PathVariable String sn, @Valid @RequestBody DronePayloadParam param) {
        return controlService.seizeAuthority(sn, DroneAuthorityEnum.PAYLOAD, param);
    }

    /**
     * 페이로드 명령을 전송합니다.
     * 
     * @param sn 디바이스 시리얼 번호
     * @param param 페이로드 명령 파라미터
     * @return 페이로드 명령 전송 결과
     * @throws Exception 명령 전송 중 오류 발생 시
     */
    @PostMapping("/{sn}/payload/commands")
    public HttpResultResponse payloadCommands(@PathVariable String sn, @Valid @RequestBody PayloadCommandsParam param) throws Exception {
        param.setSn(sn);
        return controlService.payloadCommands(param);
    }

}
