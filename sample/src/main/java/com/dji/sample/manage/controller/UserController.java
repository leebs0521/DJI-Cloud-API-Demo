package com.dji.sample.manage.controller;

import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.manage.model.dto.UserListDTO;
import com.dji.sample.manage.service.IUserService;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.PaginationData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.dji.sample.component.AuthInterceptor.TOKEN_CLAIM;

@Tag(name = "[Manage] 사용자 관리", description = "사용자 정보 조회 및 관리 API")
@RequiredArgsConstructor
@RequestMapping("${url.manage.prefix}${url.manage.version}/users")
@RestController
public class UserController {

    private final IUserService userService;

    @Operation(summary = "현재 사용자 정보 조회",
            description = "현재 로그인한 사용자의 상세 정보를 조회합니다. 토큰에서 추출한 사용자명과 워크스페이스 ID를 사용하여 사용자 정보를 데이터베이스에서 조회합니다.")
    @GetMapping("/current")
    public HttpResultResponse getCurrentUserInfo(
            @Parameter(hidden = true) HttpServletRequest request
    ) {
        CustomClaim customClaim = (CustomClaim) request.getAttribute(TOKEN_CLAIM);
        return userService.getUserByUsername(customClaim.getUsername(), customClaim.getWorkspaceId());
    }

    @Operation(summary = "워크스페이스 사용자 목록 조회",
            description = "특정 워크스페이스에 속한 모든 사용자들의 목록을 페이지 단위로 조회합니다.")
    @GetMapping("/{workspace_id}/users")
    public HttpResultResponse<PaginationData<UserListDTO>> getUsers(
            @Parameter(description = "현재 페이지 번호") @RequestParam(defaultValue = "1") Long page,
            @Parameter(description = "페이지당 항목 수") @RequestParam(value = "page_size", defaultValue = "50") Long pageSize,
            @Parameter(description = "워크스페이스 ID") @PathVariable("workspace_id") String workspaceId
    ) {
        PaginationData<UserListDTO> paginationData = userService.getUsersByWorkspaceId(page, pageSize, workspaceId);
        return HttpResultResponse.success(paginationData);
    }

    @Operation(summary = "사용자 MQTT 계정 정보 수정",
            description = "사용자의 MQTT 계정 정보를 수정합니다. 보안상 사용자명, 비밀번호 등 핵심 정보는 수정할 수 없으며, MQTT 연결 관련 설정만 변경 가능합니다.")
    @PutMapping("/{workspace_id}/users/{user_id}")
    public HttpResultResponse updateUser(
            @Parameter(description = "수정할 사용자 정보 (MQTT 계정 정보만 유효)") @RequestBody UserListDTO user,
            @Parameter(description = "워크스페이스 ID") @PathVariable("workspace_id") String workspaceId,
            @Parameter(description = "사용자 ID") @PathVariable("user_id") String userId
    ) {
        userService.updateUser(workspaceId, userId, user);
        return HttpResultResponse.success();
    }
}
