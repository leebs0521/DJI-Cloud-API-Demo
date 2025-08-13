package com.dji.sample.manage.controller;

import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.manage.model.dto.WorkspaceDTO;
import com.dji.sample.manage.service.IWorkspaceService;
import com.dji.sdk.common.HttpResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.dji.sample.component.AuthInterceptor.TOKEN_CLAIM;

@Tag(name = "[Manage] 워크스페이스 관리", description = "워크스페이스 정보 조회 및 관리 API")
@RequiredArgsConstructor
@RequestMapping("${url.manage.prefix}${url.manage.version}/workspaces")
@RestController
public class WorkspaceController {

    private final IWorkspaceService workspaceService;

    @Operation(summary = "현재 워크스페이스 정보 조회",
            description = "현재 로그인한 사용자가 속한 워크스페이스의 상세 정보를 조회합니다. " +
                    "토큰에서 추출한 워크스페이스 ID를 사용하여 워크스페이스 정보를 데이터베이스에서 조회합니다.")
    @GetMapping("/current")
    public HttpResultResponse getCurrentWorkspace(
            @Parameter(hidden = true) HttpServletRequest request
    ) {
        CustomClaim customClaim = (CustomClaim) request.getAttribute(TOKEN_CLAIM);
        Optional<WorkspaceDTO> workspaceOpt = workspaceService.getWorkspaceByWorkspaceId(customClaim.getWorkspaceId());
        return workspaceOpt.isEmpty() ? HttpResultResponse.error() : HttpResultResponse.success(workspaceOpt.get());
    }
}
