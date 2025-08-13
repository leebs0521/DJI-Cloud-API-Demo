package com.dji.sample.control.controller;

import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.control.model.dto.JwtAclDTO;
import com.dji.sample.control.model.param.DrcConnectParam;
import com.dji.sample.control.model.param.DrcModeParam;
import com.dji.sample.control.service.IDrcService;
import com.dji.sdk.cloudapi.control.DrcModeMqttBroker;
import com.dji.sdk.common.HttpResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.dji.sample.component.AuthInterceptor.TOKEN_CLAIM;

/**
 * DRC(Direct Remote Control) 컨트롤러 클래스
 * <p>
 * DRC 모드 연결 및 제어를 위한 REST API 컨트롤러입니다.
 * 사용자 인증, 디바이스 DRC 모드 진입/종료 등의 기능을 제공합니다.
 *
 * @author sean
 * @version 1.3
 * @date 2023/1/11
 */
@Tag(name = "[Control] DRC 제어", description = "DRC(Direct Remote Control) 모드 연결 및 제어 API")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("${url.control.prefix}${url.control.version}")
public class DrcController {

    private final IDrcService drcService;

    /**
     * DRC 연결을 설정합니다.
     * <p>
     * 사용자 인증을 통해 DRC 모드 MQTT 브로커 정보를 반환합니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param request     HTTP 요청 객체 (토큰 정보 추출용)
     * @param param       DRC 연결 파라미터
     * @return DRC 모드 MQTT 브로커 정보
     */
    @Operation(summary = "DRC 모드 연결 설정",
            description = "사용자 인증을 통해 DRC 모드 MQTT 브로커 연결 정보를 제공합니다.")
    @PostMapping("/workspaces/{workspace_id}/drc/connect")
    public HttpResultResponse drcConnect(
            @Parameter(hidden = true) HttpServletRequest request,
            @Parameter(description = "워크스페이스 ID") @PathVariable("workspace_id") String workspaceId,
            @Parameter(description = "DRC 연결 파라미터") @Valid @RequestBody DrcConnectParam param
    ) {
        CustomClaim claims = (CustomClaim) request.getAttribute(TOKEN_CLAIM);
        DrcModeMqttBroker brokerDTO = drcService.userDrcAuth(workspaceId, claims.getId(), claims.getUsername(), param);
        return HttpResultResponse.success(brokerDTO);
    }

    /**
     * 디바이스 DRC 모드에 진입합니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param param       DRC 모드 파라미터
     * @return JWT ACL 정보
     */
    @Operation(summary = "DRC 모드 진입",
            description = "디바이스를 DRC 모드로 전환하고 필요한 JWT ACL 정보를 반환합니다.")
    @PostMapping("/workspaces/{workspace_id}/drc/enter")
    public HttpResultResponse drcEnter(
            @Parameter(description = "워크스페이스 ID") @PathVariable("workspace_id") String workspaceId,
            @Parameter(description = "DRC 모드 파라미터") @Valid @RequestBody DrcModeParam param
    ) {
        JwtAclDTO acl = drcService.deviceDrcEnter(workspaceId, param);
        return HttpResultResponse.success(acl);
    }

    /**
     * 디바이스 DRC 모드에서 종료합니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param param       DRC 모드 파라미터
     * @return 종료 결과
     */
    @Operation(summary = "DRC 모드 종료",
            description = "디바이스를 DRC 모드에서 정상 모드로 전환합니다.")
    @PostMapping("/workspaces/{workspace_id}/drc/exit")
    public HttpResultResponse drcExit(
            @Parameter(description = "워크스페이스 ID") @PathVariable("workspace_id") String workspaceId,
            @Parameter(description = "DRC 모드 파라미터") @Valid @RequestBody DrcModeParam param
    ) {
        drcService.deviceDrcExit(workspaceId, param);
        return HttpResultResponse.success();
    }
}
