package com.dji.sample.manage.controller;

import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.manage.model.dto.CapacityDeviceDTO;
import com.dji.sample.manage.model.dto.LiveTypeDTO;
import com.dji.sample.manage.service.ILiveStreamService;
import com.dji.sdk.common.HttpResultResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.dji.sample.component.AuthInterceptor.TOKEN_CLAIM;

/**
 * 라이브 스트리밍 관리 컨트롤러
 * <p>
 * DJI Cloud API의 라이브 스트리밍 기능을 제공하는 REST API 컨트롤러입니다.
 * 이 컨트롤러는 다음과 같은 주요 기능들을 제공합니다:
 * <p>
 * 1. 라이브 스트리밍 기능 관리
 * - 라이브 스트리밍 시작/중지
 * - 라이브 스트리밍 품질 설정
 * - 라이브 스트리밍 렌즈 변경
 * <p>
 * 2. 라이브 스트리밍 용량 조회
 * - 현재 사용자의 워크스페이스 내 모든 드론의 라이브 기능 조회
 * - 라이브 스트리밍 지원 여부 확인
 * - 라이브 스트리밍 설정 정보 관리
 * <p>
 * 3. 라이브 스트리밍 제어
 * - 웹 클라이언트에서 전달받은 파라미터로 라이브 스트리밍 제어
 * - 실시간 라이브 스트리밍 상태 모니터링
 * - 라이브 스트리밍 오류 처리
 * <p>
 * 4. 라이브 스트리밍 설정
 * - 라이브 스트리밍 품질 조정
 * - 카메라 렌즈 전환
 * - 라이브 스트리밍 파라미터 최적화
 * <p>
 * 모든 API는 사용자 인증을 통해 보안을 보장하며,
 * 워크스페이스별로 라이브 스트리밍 접근 권한을 관리합니다.
 *
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/19
 */

@Tag(name = "[Manage] 라이브 스트리밍", description = "라이브 스트리밍 관리 API")
@RequiredArgsConstructor
@Slf4j
@RequestMapping("${url.manage.prefix}${url.manage.version}/live")
@RestController
public class LiveStreamController {

    private final ILiveStreamService liveStreamService;
    private final ObjectMapper mapper;

    /**
     * 현재 사용자의 워크스페이스에 있는 모든 드론의 라이브 기능 데이터를 데이터베이스에서 조회합니다.
     * <p>
     * 이 API는 현재 사용자가 속한 워크스페이스의 모든 드론이
     * 라이브 스트리밍을 지원하는지 확인하고 관련 설정 정보를 조회합니다.
     *
     * @param request HTTP 요청 객체 (토큰 정보 추출용)
     * @return 라이브 기능 정보 목록
     */
    @Operation(summary = "라이브 스트리밍 용량 조회",
            description = "현재 사용자의 워크스페이스에 있는 모든 드론의 라이브 기능 데이터를 조회합니다.")
    @GetMapping("/capacity")
    public HttpResultResponse<List<CapacityDeviceDTO>> getLiveCapacity(
            @Parameter(hidden = true) HttpServletRequest request
    ) {
        CustomClaim customClaim = (CustomClaim) request.getAttribute(TOKEN_CLAIM);
        List<CapacityDeviceDTO> liveCapacity = liveStreamService.getLiveCapacity(customClaim.getWorkspaceId());
        return HttpResultResponse.success(liveCapacity);
    }


    /**
     * 웹 클라이언트에서 전달받은 파라미터에 따라 라이브 스트리밍을 시작합니다.
     * <p>
     * 이 API는 라이브 스트리밍 시작 요청을 처리합니다.
     * 드론의 카메라를 활성화하고 실시간 비디오 스트림을 시작합니다.
     *
     * @param liveParam 라이브 스트리밍 파라미터 (드론 정보, 스트림 설정 등)
     * @return 라이브 스트리밍 시작 결과
     */
    @Operation(summary = "라이브 스트리밍 시작",
            description = "웹 클라이언트에서 전달받은 파라미터에 따라 라이브 스트리밍을 시작합니다.")
    @PostMapping("/streams/start")
    public HttpResultResponse liveStart(
            @Parameter(description = "라이브 스트리밍 시작 파라미터") @RequestBody LiveTypeDTO liveParam
    ) {
        return liveStreamService.liveStart(liveParam);
    }

    /**
     * 웹 클라이언트에서 전달받은 파라미터에 따라 라이브 스트리밍을 중지합니다.
     * <p>
     * 이 API는 진행 중인 라이브 스트리밍을 중지합니다.
     * 비디오 스트림을 종료하고 관련 리소스를 정리합니다.
     *
     * @param liveParam 라이브 스트리밍 파라미터 (비디오 ID 등)
     * @return 라이브 스트리밍 중지 결과
     */
    @Operation(summary = "라이브 스트리밍 중지",
            description = "웹 클라이언트에서 전달받은 파라미터에 따라 라이브 스트리밍을 중지합니다.")
    @PostMapping("/streams/stop")
    public HttpResultResponse liveStop(
            @Parameter(description = "라이브 스트리밍 중지 파라미터") @RequestBody LiveTypeDTO liveParam
    ) {
        return liveStreamService.liveStop(liveParam.getVideoId());
    }

    /**
     * 웹 클라이언트에서 전달받은 파라미터에 따라 라이브 스트리밍의 품질을 설정합니다.
     * <p>
     * 이 API는 라이브 스트리밍의 비디오 품질을 동적으로 조정합니다.
     * 네트워크 상황이나 사용자 요구에 따라 품질을 변경할 수 있습니다.
     *
     * @param liveParam 라이브 스트리밍 파라미터 (품질 설정 등)
     * @return 라이브 스트리밍 품질 설정 결과
     */
    @Operation(summary = "라이브 스트리밍 품질 설정",
            description = "웹 클라이언트에서 전달받은 파라미터에 따라 라이브 스트리밍의 품질을 설정합니다.")
    @PostMapping("/streams/update")
    public HttpResultResponse liveSetQuality(
            @Parameter(description = "라이브 스트리밍 품질 설정 파라미터") @RequestBody LiveTypeDTO liveParam
    ) {
        return liveStreamService.liveSetQuality(liveParam);
    }

    /**
     * 라이브 스트리밍의 카메라 렌즈를 변경합니다.
     * <p>
     * 이 API는 라이브 스트리밍 중에 카메라 렌즈를 전환합니다.
     * 다양한 각도나 줌 레벨로 실시간 시점을 변경할 수 있습니다.
     *
     * @param liveParam 라이브 스트리밍 파라미터 (렌즈 설정 등)
     * @return 라이브 스트리밍 렌즈 변경 결과
     */
    @Operation(summary = "라이브 스트리밍 렌즈 변경",
            description = "라이브 스트리밍의 카메라 렌즈를 변경합니다.")
    @PostMapping("/streams/switch")
    public HttpResultResponse liveLensChange(
            @Parameter(description = "라이브 스트리밍 렌즈 변경 파라미터") @RequestBody LiveTypeDTO liveParam
    ) {
        return liveStreamService.liveLensChange(liveParam);
    }
}
