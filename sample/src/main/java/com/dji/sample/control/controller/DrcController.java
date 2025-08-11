package com.dji.sample.control.controller;

import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.control.model.dto.JwtAclDTO;
import com.dji.sample.control.model.param.DrcConnectParam;
import com.dji.sample.control.model.param.DrcModeParam;
import com.dji.sample.control.service.IDrcService;
import com.dji.sdk.cloudapi.control.DrcModeMqttBroker;
import com.dji.sdk.common.HttpResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.dji.sample.component.AuthInterceptor.TOKEN_CLAIM;

/**
 * DRC(Direct Remote Control) 컨트롤러 클래스
 * 
 * DRC 모드 연결 및 제어를 위한 REST API 컨트롤러입니다.
 * 사용자 인증, 디바이스 DRC 모드 진입/종료 등의 기능을 제공합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/1/11
 */
@RestController
@Slf4j
@RequestMapping("${url.control.prefix}${url.control.version}")
public class DrcController {

    /** DRC 서비스 */
    @Autowired
    private IDrcService drcService;

    /**
     * DRC 연결을 설정합니다.
     * 
     * 사용자 인증을 통해 DRC 모드 MQTT 브로커 정보를 반환합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param request HTTP 요청 객체 (토큰 정보 추출용)
     * @param param DRC 연결 파라미터
     * @return DRC 모드 MQTT 브로커 정보
     */
    @PostMapping("/workspaces/{workspace_id}/drc/connect")
    public HttpResultResponse drcConnect(@PathVariable("workspace_id") String workspaceId, HttpServletRequest request, @Valid @RequestBody DrcConnectParam param) {
        // 요청에서 사용자 토큰 클레임 정보 추출
        CustomClaim claims = (CustomClaim) request.getAttribute(TOKEN_CLAIM);

        // 사용자 DRC 인증 및 브로커 정보 반환
        DrcModeMqttBroker brokerDTO = drcService.userDrcAuth(workspaceId, claims.getId(), claims.getUsername(), param);
        return HttpResultResponse.success(brokerDTO);
    }

    /**
     * 디바이스 DRC 모드에 진입합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param param DRC 모드 파라미터
     * @return JWT ACL 정보
     */
    @PostMapping("/workspaces/{workspace_id}/drc/enter")
    public HttpResultResponse drcEnter(@PathVariable("workspace_id") String workspaceId, @Valid @RequestBody DrcModeParam param) {
        // 디바이스 DRC 모드 진입 및 ACL 정보 반환
        JwtAclDTO acl = drcService.deviceDrcEnter(workspaceId, param);

        return HttpResultResponse.success(acl);
    }

    /**
     * 디바이스 DRC 모드에서 종료합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param param DRC 모드 파라미터
     * @return 종료 결과
     */
    @PostMapping("/workspaces/{workspace_id}/drc/exit")
    public HttpResultResponse drcExit(@PathVariable("workspace_id") String workspaceId, @Valid @RequestBody DrcModeParam param) {
        // 디바이스 DRC 모드 종료
        drcService.deviceDrcExit(workspaceId, param);

        return HttpResultResponse.success();
    }

}
