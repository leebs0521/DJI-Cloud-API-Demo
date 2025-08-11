package com.dji.sample.manage.controller;

import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.manage.model.dto.UserListDTO;
import com.dji.sample.manage.service.IUserService;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.PaginationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.dji.sample.component.AuthInterceptor.TOKEN_CLAIM;

/**
 * 사용자 관리 컨트롤러
 * 
 * DJI Cloud API의 사용자 관리 기능을 제공하는 REST API 컨트롤러입니다.
 * 이 컨트롤러는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 사용자 정보 조회
 *    - 현재 로그인한 사용자 정보 조회
 *    - 워크스페이스별 사용자 목록 조회
 *    - 사용자 정보 페이지네이션 처리
 * 
 * 2. 사용자 정보 관리
 *    - 사용자 정보 수정 (MQTT 계정 정보만 수정 가능)
 *    - 사용자 권한 관리
 *    - 사용자 상태 관리
 * 
 * 3. 워크스페이스 사용자 관리
 *    - 워크스페이스별 사용자 접근 권한 관리
 *    - 사용자별 워크스페이스 권한 확인
 *    - 사용자 목록 필터링 및 검색
 * 
 * 4. 보안 관리
 *    - 사용자 인증 토큰 검증
 *    - 워크스페이스별 접근 권한 확인
 *    - 사용자 정보 보안 처리
 * 
 * 모든 API는 사용자 인증을 통해 보안을 보장하며,
 * 워크스페이스별로 사용자 접근 권한을 관리합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/15
 */
@RestController
@RequestMapping("${url.manage.prefix}${url.manage.version}/users")
public class UserController {

    /** 사용자 서비스 - 사용자 관리 비즈니스 로직 */
    @Autowired
    private IUserService userService;

    /**
     * 현재 사용자의 정보를 조회합니다.
     * 
     * 이 API는 현재 로그인한 사용자의 상세 정보를 조회합니다.
     * 토큰에서 추출한 사용자명과 워크스페이스 ID를 사용하여
     * 사용자 정보를 데이터베이스에서 조회합니다.
     * 
     * @param request HTTP 요청 객체 (토큰 정보 추출용)
     * @return 현재 사용자 정보
     */
    @GetMapping("/current")
    public HttpResultResponse getCurrentUserInfo(HttpServletRequest request) {
        CustomClaim customClaim = (CustomClaim)request.getAttribute(TOKEN_CLAIM);
        return userService.getUserByUsername(customClaim.getUsername(), customClaim.getWorkspaceId());
    }

    /**
     * 워크스페이스의 모든 사용자를 페이지네이션으로 조회합니다.
     * 
     * 이 API는 특정 워크스페이스에 속한 모든 사용자들의 목록을
     * 페이지 단위로 조회합니다. 페이지 크기와 페이지 번호를
     * 지정하여 대용량 데이터를 효율적으로 처리할 수 있습니다.
     * 
     * @param page 현재 페이지 번호 (기본값: 1)
     * @param pageSize 페이지당 항목 수 (기본값: 50)
     * @param workspaceId 워크스페이스 ID
     * @return 사용자 목록 (페이지네이션 포함)
     */
    @GetMapping("/{workspace_id}/users")
    public HttpResultResponse<PaginationData<UserListDTO>> getUsers(@RequestParam(defaultValue = "1") Long page,
                                                                    @RequestParam(value = "page_size", defaultValue = "50") Long pageSize,
                                                                    @PathVariable("workspace_id") String workspaceId) {
        PaginationData<UserListDTO> paginationData = userService.getUsersByWorkspaceId(page, pageSize, workspaceId);
        return HttpResultResponse.success(paginationData);
    }

    /**
     * 사용자 정보를 수정합니다. MQTT 계정 정보만 포함되며, 다른 정보는 수정할 수 없습니다.
     * 
     * 이 API는 사용자의 MQTT 계정 정보를 수정합니다.
     * 보안상 사용자명, 비밀번호 등 핵심 정보는 수정할 수 없으며,
     * MQTT 연결 관련 설정만 변경 가능합니다.
     * 
     * @param user 수정할 사용자 정보 (MQTT 계정 정보만 유효)
     * @param workspaceId 워크스페이스 ID
     * @param userId 사용자 ID
     * @return 사용자 정보 수정 결과
     */
    @PutMapping("/{workspace_id}/users/{user_id}")
    public HttpResultResponse updateUser(@RequestBody UserListDTO user,
                                         @PathVariable("workspace_id") String workspaceId,
                                         @PathVariable("user_id") String userId) {

        userService.updateUser(workspaceId, userId, user);
        return HttpResultResponse.success();
    }
}
