package com.dji.sample.manage.controller;

import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.manage.model.dto.WorkspaceDTO;
import com.dji.sample.manage.service.IWorkspaceService;
import com.dji.sdk.common.HttpResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.dji.sample.component.AuthInterceptor.TOKEN_CLAIM;

/**
 * 워크스페이스 관리 컨트롤러
 * 
 * DJI Cloud API의 워크스페이스 관리 기능을 제공하는 REST API 컨트롤러입니다.
 * 이 컨트롤러는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 워크스페이스 정보 조회
 *    - 현재 사용자가 속한 워크스페이스 정보 조회
 *    - 워크스페이스 메타데이터 관리
 *    - 워크스페이스 상태 확인
 * 
 * 2. 워크스페이스 접근 관리
 *    - 사용자별 워크스페이스 접근 권한 확인
 *    - 워크스페이스 멤버십 관리
 *    - 워크스페이스 설정 관리
 * 
 * 3. 워크스페이스 보안
 *    - 사용자 인증 토큰 검증
 *    - 워크스페이스별 접근 권한 확인
 *    - 워크스페이스 정보 보안 처리
 * 
 * 4. 워크스페이스 컨텍스트
 *    - 현재 워크스페이스 컨텍스트 제공
 *    - 워크스페이스별 리소스 관리
 *    - 워크스페이스 설정 추적
 * 
 * 워크스페이스는 DJI Cloud API에서 사용자들이 디바이스를 관리하고
 * 작업을 수행하는 논리적 공간을 의미합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/23
 */
@RestController
@RequestMapping("${url.manage.prefix}${url.manage.version}/workspaces")
public class WorkspaceController {

    /** 워크스페이스 서비스 - 워크스페이스 관리 비즈니스 로직 */
    @Autowired
    private IWorkspaceService workspaceService;

    /**
     * 현재 사용자가 속한 워크스페이스의 정보를 조회합니다.
     * 
     * 이 API는 현재 로그인한 사용자가 속한 워크스페이스의
     * 상세 정보를 조회합니다. 토큰에서 추출한 워크스페이스 ID를
     * 사용하여 워크스페이스 정보를 데이터베이스에서 조회합니다.
     * 
     * 워크스페이스가 존재하지 않는 경우 오류를 반환합니다.
     * 
     * @param request HTTP 요청 객체 (토큰 정보 추출용)
     * @return 워크스페이스 정보 (성공 시) 또는 오류 응답
     */
    @GetMapping("/current")
    public HttpResultResponse getCurrentWorkspace(HttpServletRequest request) {
        CustomClaim customClaim = (CustomClaim)request.getAttribute(TOKEN_CLAIM);
        Optional<WorkspaceDTO> workspaceOpt = workspaceService.getWorkspaceByWorkspaceId(customClaim.getWorkspaceId());

        return workspaceOpt.isEmpty() ? HttpResultResponse.error() : HttpResultResponse.success(workspaceOpt.get());
    }
}