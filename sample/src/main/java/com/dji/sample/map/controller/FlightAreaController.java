package com.dji.sample.map.controller;

import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.map.model.dto.FlightAreaDTO;
import com.dji.sample.map.model.param.PostFlightAreaParam;
import com.dji.sample.map.model.param.PutFlightAreaParam;
import com.dji.sample.map.model.param.SyncFlightAreaParam;
import com.dji.sample.map.service.IFlightAreaService;
import com.dji.sdk.common.HttpResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static com.dji.sample.component.AuthInterceptor.TOKEN_CLAIM;

/**
 * 비행 영역 관리 컨트롤러
 * <p>
 * DJI Cloud API의 비행 영역 관리를 위한 REST API 컨트롤러입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * <p>
 * 1. 비행 영역 CRUD 관리
 * - 비행 영역 목록 조회 (GET)
 * - 비행 영역 생성 (POST)
 * - 비행 영역 수정 (PUT)
 * - 비행 영역 삭제 (DELETE)
 * <p>
 * 2. 비행 영역 동기화 관리
 * - 디바이스별 비행 영역 동기화
 * - 다중 디바이스 비행 영역 업데이트
 * - 실시간 비행 영역 정보 전파
 * - 디바이스 간 비행 영역 일관성 보장
 * <p>
 * 3. 워크스페이스 기반 관리
 * - 워크스페이스별 비행 영역 분리
 * - 조직별 비행 영역 독립 관리
 * - 멀티 테넌트 지원
 * - 권한 기반 접근 제어
 * <p>
 * 4. 사용자 인증 및 권한 관리
 * - JWT 토큰 기반 사용자 인증
 * - 사용자별 비행 영역 생성 권한
 * - 워크스페이스별 접근 권한 검증
 * - 보안된 API 엔드포인트 제공
 * <p>
 * 5. 데이터 검증 및 유효성 검사
 * - 요청 데이터 유효성 검증
 * - 비행 영역 파라미터 검증
 * - 동기화 파라미터 검증
 * - 입력 데이터 정규화
 * <p>
 * 주요 의존성:
 * - IFlightAreaService: 비행 영역 관리 서비스
 * - FlightAreaDTO: 비행 영역 데이터 전송 객체
 * - PostFlightAreaParam: 비행 영역 생성 파라미터
 * - PutFlightAreaParam: 비행 영역 수정 파라미터
 * - SyncFlightAreaParam: 비행 영역 동기화 파라미터
 * - CustomClaim: JWT 토큰 클레임 정보
 * <p>
 * API 엔드포인트:
 * - GET /workspaces/{workspace_id}/flight-areas: 비행 영역 목록 조회
 * - POST /workspaces/{workspace_id}/flight-area: 비행 영역 생성
 * - DELETE /workspaces/{workspace_id}/flight-area/{area_id}: 비행 영역 삭제
 * - PUT /workspaces/{workspace_id}/flight-area/{area_id}: 비행 영역 수정
 * - POST /workspaces/{workspace_id}/flight-area/sync: 비행 영역 동기화
 * <p>
 * 이 클래스는 DJI Cloud API의 비행 영역을
 * RESTful API로 관리하는 컨트롤러입니다.
 *
 * @author sean
 * @version 1.9
 * @date 2023/11/21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("${url.map.prefix}${url.map.version}/workspaces")
@Tag(name = "[Map] 비행 영역 관리", description = "비행 영역 관리 API")
public class FlightAreaController {

    private final IFlightAreaService flightAreaService;

    /**
     * 워크스페이스의 비행 영역 목록을 조회합니다.
     * <p>
     * 지정된 워크스페이스에 속한 모든 비행 영역 정보를
     * 조회하여 반환합니다.
     *
     * @param workspaceId 워크스페이스 ID (경로 변수)
     * @return 비행 영역 목록을 포함한 HTTP 응답
     */
    @Operation(summary = "비행 영역 목록 조회", description = "워크스페이스의 모든 비행 영역 정보를 조회합니다.")
    @GetMapping("/{workspace_id}/flight-areas")
    public HttpResultResponse<List<FlightAreaDTO>> getFlightAreas(
            @Parameter(description = "워크스페이스 ID") @PathVariable(name = "workspace_id") String workspaceId
    ) {
        return HttpResultResponse.success(flightAreaService.getFlightAreaList(workspaceId));
    }

    /**
     * 새로운 비행 영역을 생성합니다.
     * <p>
     * 지정된 워크스페이스에 새로운 비행 영역을 생성합니다.
     * 요청한 사용자의 정보를 포함하여 비행 영역을 생성하며,
     * 입력 데이터의 유효성을 검증합니다.
     *
     * @param workspaceId 워크스페이스 ID (경로 변수)
     * @param param       비행 영역 생성 파라미터 (요청 본문)
     * @param req         HTTP 요청 객체 (토큰 정보 추출용)
     * @return 생성 결과를 포함한 HTTP 응답
     */
    @Operation(summary = "비행 영역 생성", description = "새로운 비행 영역을 생성합니다.")
    @PostMapping("/{workspace_id}/flight-area")
    public HttpResultResponse createFlightArea(
            @Parameter(description = "워크스페이스 ID") @PathVariable(name = "workspace_id") String workspaceId,
            @Parameter(description = "비행 영역 생성 파라미터") @Valid @RequestBody PostFlightAreaParam param,
            @Parameter(hidden = true) HttpServletRequest req
    ) {
        // JWT 토큰에서 사용자 정보 추출
        CustomClaim claims = (CustomClaim) req.getAttribute(TOKEN_CLAIM);

        // 비행 영역 생성 서비스 호출
        flightAreaService.createFlightArea(workspaceId, claims.getUsername(), param);
        return HttpResultResponse.success();
    }

    /**
     * 비행 영역을 삭제합니다.
     * <p>
     * 지정된 워크스페이스의 특정 비행 영역을 삭제합니다.
     *
     * @param workspaceId 워크스페이스 ID (경로 변수)
     * @param areaId      삭제할 비행 영역 ID (경로 변수)
     * @return 삭제 결과를 포함한 HTTP 응답
     */
    @Operation(summary = "비행 영역 삭제", description = "지정된 비행 영역을 삭제합니다.")
    @DeleteMapping("/{workspace_id}/flight-area/{area_id}")
    public HttpResultResponse deleteFlightArea(
            @Parameter(description = "워크스페이스 ID") @PathVariable(name = "workspace_id") String workspaceId,
            @Parameter(description = "비행 영역 ID") @PathVariable(name = "area_id") String areaId
    ) {
        flightAreaService.deleteFlightArea(workspaceId, areaId);
        return HttpResultResponse.success();
    }

    /**
     * 비행 영역을 수정합니다.
     * <p>
     * 지정된 워크스페이스의 특정 비행 영역 정보를 수정합니다.
     *
     * @param workspaceId 워크스페이스 ID (경로 변수)
     * @param areaId      수정할 비행 영역 ID (경로 변수)
     * @param param       비행 영역 수정 파라미터 (요청 본문)
     * @return 수정 결과를 포함한 HTTP 응답
     */
    @Operation(summary = "비행 영역 수정", description = "지정된 비행 영역의 정보를 수정합니다.")
    @PutMapping("/{workspace_id}/flight-area/{area_id}")
    public HttpResultResponse updateFlightArea(
            @Parameter(description = "워크스페이스 ID") @PathVariable(name = "workspace_id") String workspaceId,
            @Parameter(description = "비행 영역 ID") @PathVariable(name = "area_id") String areaId,
            @Parameter(description = "비행 영역 수정 파라미터") @RequestBody PutFlightAreaParam param
    ) {
        flightAreaService.updateFlightArea(workspaceId, areaId, param);
        return HttpResultResponse.success();
    }

    /**
     * 비행 영역을 디바이스와 동기화합니다.
     * <p>
     * 지정된 워크스페이스의 비행 영역 정보를
     * 특정 디바이스들과 동기화합니다.
     *
     * @param workspaceId 워크스페이스 ID (경로 변수)
     * @param param       비행 영역 동기화 파라미터 (요청 본문)
     * @return 동기화 결과를 포함한 HTTP 응답
     */
    @Operation(summary = "비행 영역 동기화", description = "비행 영역 정보를 디바이스와 동기화합니다.")
    @PostMapping("/{workspace_id}/flight-area/sync")
    public HttpResultResponse syncFlightArea(
            @Parameter(description = "워크스페이스 ID") @PathVariable(name = "workspace_id") String workspaceId,
            @Parameter(description = "비행 영역 동기화 파라미터") @RequestBody @Valid SyncFlightAreaParam param
    ) {
        flightAreaService.syncFlightArea(workspaceId, param.getDeviceSns());
        return HttpResultResponse.success();
    }

}
