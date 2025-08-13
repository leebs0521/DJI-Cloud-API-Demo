package com.dji.sample.map.controller;

import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.component.websocket.service.IWebSocketMessageService;
import com.dji.sample.map.service.IWorkspaceElementService;
import com.dji.sdk.cloudapi.map.CreateMapElementRequest;
import com.dji.sdk.cloudapi.map.CreateMapElementResponse;
import com.dji.sdk.cloudapi.map.GetMapElementsResponse;
import com.dji.sdk.cloudapi.map.UpdateMapElementRequest;
import com.dji.sdk.cloudapi.map.api.IHttpMapService;
import com.dji.sdk.common.HttpResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static com.dji.sample.component.AuthInterceptor.TOKEN_CLAIM;

/**
 * 워크스페이스 요소 관리 컨트롤러
 * <p>
 * DJI Cloud API의 워크스페이스 맵 요소 관리를 위한 REST API 컨트롤러입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * <p>
 * 1. 맵 요소 CRUD 관리
 * - 맵 요소 생성 (점, 선, 다각형 등)
 * - 맵 요소 조회 (그룹별 요소 목록)
 * - 맵 요소 수정 (기존 요소 업데이트)
 * - 맵 요소 삭제 (개별 요소 및 그룹 삭제)
 * <p>
 * 2. 맵 요소 그룹 관리
 * - 그룹별 맵 요소 목록 조회
 * - 그룹 전체 요소 삭제
 * - 분산 환경 지원
 * - 그룹 기반 요소 분류
 * <p>
 * 3. PILOT/Web 연동
 * - PILOT 앱과의 실시간 동기화
 * - Web 클라이언트와의 맵 요소 공유
 * - WebSocket을 통한 실시간 업데이트
 * - 초기 연결 시 요소 목록 제공
 * <p>
 * 4. 사용자 인증 및 권한 관리
 * - JWT 토큰 기반 사용자 인증
 * - 요소 생성자 정보 관리
 * - 워크스페이스별 접근 권한 검증
 * - 사용자별 요소 소유권 관리
 * <p>
 * 5. 실시간 데이터 동기화
 * - WebSocket을 통한 실시간 알림
 * - 맵 요소 변경 사항 실시간 전파
 * - 다중 클라이언트 동기화
 * - 데이터 일관성 보장
 * <p>
 * 주요 의존성:
 * - IWorkspaceElementService: 워크스페이스 요소 관리 서비스
 * - IWebSocketMessageService: WebSocket 메시지 전송 서비스
 * - IHttpMapService: DJI 맵 서비스 인터페이스
 * - CreateMapElementRequest: 맵 요소 생성 요청
 * - UpdateMapElementRequest: 맵 요소 수정 요청
 * - CustomClaim: JWT 토큰 클레임 정보
 * <p>
 * API 엔드포인트:
 * - DELETE /workspaces/{workspace_id}/element-groups/{group_id}/elements: 그룹 전체 요소 삭제
 * - GET /workspaces/{workspace_id}/element-groups/{group_id}/elements: 그룹 요소 목록 조회
 * - POST /workspaces/{workspace_id}/element-groups/{group_id}/elements: 맵 요소 생성
 * - PUT /workspaces/{workspace_id}/elements/{element_id}: 맵 요소 수정
 * - DELETE /workspaces/{workspace_id}/elements/{element_id}: 맵 요소 삭제
 * <p>
 * 이 클래스는 DJI Cloud API의 워크스페이스 맵 요소를
 * RESTful API로 관리하는 컨트롤러입니다.
 *
 * @author sean
 * @version 0.2
 * @date 2021/11/29
 */
@Tag(name = "워크스페이스 맵 요소 관리", description = "워크스페이스 맵 요소 관리 API")
@RequiredArgsConstructor
@RestController
public class WorkspaceElementController implements IHttpMapService {

    private final IWorkspaceElementService elementService;
    private final IWebSocketMessageService sendMessageService;

    /**
     * 그룹 ID를 기반으로 해당 그룹의 모든 요소 정보를 삭제합니다.
     * <p>
     * 지정된 워크스페이스의 특정 그룹에 속한 모든 맵 요소를
     * 한 번에 삭제합니다.
     *
     * @param workspaceId 워크스페이스 ID (경로 변수)
     * @param groupId     삭제할 요소 그룹 ID (경로 변수)
     * @return 삭제 결과를 포함한 HTTP 응답
     */
    @Operation(summary = "그룹 전체 요소 삭제",
            description = "지정된 워크스페이스와 그룹에 속한 모든 맵 요소를 삭제합니다.")
    @DeleteMapping("${url.map.prefix}${url.map.version}/workspaces/{workspace_id}/element-groups/{group_id}/elements")
    public HttpResultResponse deleteAllElementByGroupId(
            @PathVariable("workspace_id") @Parameter(description = "워크스페이스 ID") String workspaceId,
            @PathVariable("group_id") @Parameter(description = "맵 요소 그룹 ID") String groupId,
            @Parameter(hidden = true) HttpServletRequest request,
            @Parameter(hidden = true) HttpServletResponse response
    ) {
        return elementService.deleteAllElementByGroupId(workspaceId, groupId);
    }

    /**
     * 워크스페이스의 맵 요소 목록을 조회합니다.
     * <p>
     * PILOT 앱의 첫 번째 연결 시 이 HTTP 요청을 통해 그룹 요소 목록을 얻습니다.
     * 또한 PILOT이 WebSocket을 통해 그룹 새로고침 지시를 받으면,
     * 동일한 인터페이스를 통해 그룹 요소 목록을 요청합니다.
     *
     * @param workspaceId   워크스페이스 ID
     * @param groupId       조회할 그룹 ID
     * @param isDistributed 분산 환경 여부
     * @param req           HTTP 요청 객체
     * @param rsp           HTTP 응답 객체
     * @return 맵 요소 목록을 포함한 HTTP 응답
     */
    @Override
    public HttpResultResponse<List<GetMapElementsResponse>> getMapElements(
            String workspaceId,
            String groupId,
            Boolean isDistributed,
            HttpServletRequest req,
            HttpServletResponse rsp
    ) {
        // 워크스페이스 ID와 그룹 ID를 기반으로 모든 그룹 요소 목록 조회
        List<GetMapElementsResponse> groupsList = elementService.getAllGroupsByWorkspaceId(workspaceId, groupId, isDistributed);
        return HttpResultResponse.<List<GetMapElementsResponse>>success(groupsList);
    }

    /**
     * 사용자가 PILOT/Web에서 점, 선, 다각형을 그릴 때 호출됩니다.
     * <p>
     * 맵에 그려진 요소 정보를 데이터베이스에 저장합니다.
     * 요소 생성자 정보를 포함하여 저장하며,
     * 실시간으로 다른 클라이언트에게 알림을 전송합니다.
     *
     * @param workspaceId   워크스페이스 ID
     * @param groupId       요소가 속할 그룹 ID
     * @param elementCreate 맵 요소 생성 요청 객체
     * @param req           HTTP 요청 객체 (토큰 정보 추출용)
     * @param rsp           HTTP 응답 객체
     * @return 생성된 맵 요소 정보를 포함한 HTTP 응답
     */
    @Override
    public HttpResultResponse<CreateMapElementResponse> createMapElement(
            String workspaceId,
            String groupId,
            @Valid CreateMapElementRequest elementCreate,
            HttpServletRequest req,
            HttpServletResponse rsp
    ) {
        // JWT 토큰에서 사용자 정보 추출
        CustomClaim claims = (CustomClaim) req.getAttribute(TOKEN_CLAIM);

        // 요소의 생성자 정보 설정
        elementCreate.getResource().setUsername(claims.getUsername());

        // 맵 요소 저장 서비스 호출
        HttpResultResponse response = elementService.saveElement(workspaceId, groupId, elementCreate, true);
        if (response.getCode() != HttpResultResponse.CODE_SUCCESS) {
            return response;
        }

        // 생성된 요소의 ID를 포함한 응답 반환
        return HttpResultResponse.success(new CreateMapElementResponse().setId(elementCreate.getId()));
    }

    /**
     * 사용자가 PILOT/Web에서 점, 선, 다각형을 편집할 때 호출됩니다.
     * <p>
     * 기존 맵 요소의 정보를 데이터베이스에서 업데이트합니다.
     * 사용자 권한을 검증하고 실시간으로 변경 사항을 전파합니다.
     *
     * @param workspaceId   워크스페이스 ID
     * @param elementId     수정할 요소 ID
     * @param elementUpdate 맵 요소 수정 요청 객체
     * @param req           HTTP 요청 객체 (토큰 정보 추출용)
     * @param rsp           HTTP 응답 객체
     * @return 수정 결과를 포함한 HTTP 응답
     */
    @Override
    public HttpResultResponse updateMapElement(
            String workspaceId,
            String elementId,
            @Valid UpdateMapElementRequest elementUpdate,
            HttpServletRequest req,
            HttpServletResponse rsp
    ) {
        // JWT 토큰에서 사용자 정보 추출
        CustomClaim claims = (CustomClaim) req.getAttribute(TOKEN_CLAIM);

        // 맵 요소 수정 서비스 호출
        HttpResultResponse response = elementService.updateElement(workspaceId, elementId, elementUpdate, claims.getUsername(), true);
        if (response.getCode() != HttpResultResponse.CODE_SUCCESS) {
            return response;
        }

        return response;
    }

    /**
     * 사용자가 PILOT/Web에서 점, 선, 다각형을 삭제할 때 호출됩니다.
     * <p>
     * 데이터베이스에서 해당 맵 요소 정보를 삭제합니다.
     * 실시간으로 다른 클라이언트에게 삭제 알림을 전송합니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param elementId   삭제할 요소 ID
     * @param req         HTTP 요청 객체
     * @param rsp         HTTP 응답 객체
     * @return 삭제 결과를 포함한 HTTP 응답
     */
    @Override
    public HttpResultResponse deleteMapElement(
            String workspaceId,
            String elementId,
            HttpServletRequest req,
            HttpServletResponse rsp
    ) {
        return elementService.deleteElement(workspaceId, elementId, true);
    }
}
