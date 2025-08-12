package com.dji.sample.wayline.controller;

import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.wayline.model.dto.WaylineJobDTO;
import com.dji.sample.wayline.model.param.CreateJobParam;
import com.dji.sample.wayline.model.param.UpdateJobParam;
import com.dji.sample.wayline.service.IFlightTaskService;
import com.dji.sample.wayline.service.IWaylineJobService;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.PaginationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Set;

import static com.dji.sample.component.AuthInterceptor.TOKEN_CLAIM;

/**
 * DJI Cloud API 웨이라인 작업 관리 컨트롤러
 * 
 * 이 클래스는 DJI Dock에서 웨이라인 기반 자동 비행 작업을 관리하기 위한 REST API 엔드포인트를 제공합니다.
 * 웨이라인 작업은 미리 정의된 비행 경로를 따라 드론이 자동으로 비행하는 작업을 의미합니다.
 * 
 * 주요 기능:
 * - 웨이라인 작업 생성 및 발행
 * - 웨이라인 작업 목록 조회 및 페이징 처리
 * - 웨이라인 작업 취소
 * - 미디어 파일 우선 업로드 설정
 * - 웨이라인 작업 상태 업데이트
 * 
 * API 엔드포인트:
 * - POST /workspaces/{workspace_id}/flight-tasks: 비행 작업 생성
 * - GET /workspaces/{workspace_id}/jobs: 작업 목록 조회
 * - DELETE /workspaces/{workspace_id}/jobs: 작업 취소
 * - POST /workspaces/{workspace_id}/jobs/{job_id}/media-highest: 미디어 우선 업로드
 * - PUT /workspaces/{workspace_id}/jobs/{job_id}: 작업 상태 업데이트
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/1
 */
@RequestMapping("${url.wayline.prefix}${url.wayline.version}/workspaces")
@RestController
public class WaylineJobController {

    /**
     * 웨이라인 작업 서비스 인터페이스
     * 웨이라인 작업 관련 데이터베이스 조작 및 비즈니스 로직을 처리하는 서비스 계층
     */
    @Autowired
    private IWaylineJobService waylineJobService;

    /**
     * 비행 작업 서비스 인터페이스
     * DJI Dock과의 통신을 통해 실제 비행 작업을 제어하는 서비스 계층
     */
    @Autowired
    private IFlightTaskService flighttaskService;

    /**
     * Dock을 위한 웨이라인 작업을 생성하고 발행합니다.
     * 
     * 이 메서드는 DJI Dock에서 웨이라인 기반 자동 비행 작업을 생성하고 실행하기 위해 사용됩니다.
     * 생성된 작업은 Dock에 전송되어 실제 드론 비행을 제어합니다.
     * 
     * 처리 과정:
     * 1. 사용자 인증 정보에서 워크스페이스 ID 추출
     * 2. 작업 생성 파라미터 유효성 검사
     * 3. 비행 작업 서비스를 통해 Dock에 작업 발행
     * 4. 작업 생성 결과 반환
     * 
     * @param request HTTP 요청 객체 (인증 정보 포함)
     * @param param 작업 생성 파라미터 (웨이라인 ID, 비행 설정 등)
     * @param workspaceId 워크스페이스 ID
     * @return 작업 생성 성공 여부를 포함한 HTTP 응답
     * @throws SQLException 데이터베이스 작업 중 발생할 수 있는 예외
     */
    @PostMapping("/{workspace_id}/flight-tasks")
    public HttpResultResponse createJob(HttpServletRequest request, @Valid @RequestBody CreateJobParam param,
                                        @PathVariable(name = "workspace_id") String workspaceId) throws SQLException {
        // 인증 토큰에서 사용자 정보 추출
        CustomClaim customClaim = (CustomClaim)request.getAttribute(TOKEN_CLAIM);
        customClaim.setWorkspaceId(workspaceId);

        // 비행 작업 생성 및 Dock에 발행
        return flighttaskService.publishFlightTask(param, customClaim);
    }

    /**
     * 워크스페이스의 모든 웨이라인 작업 목록을 페이징 처리하여 조회합니다.
     * 
     * 이 메서드는 현재 워크스페이스에 생성된 모든 웨이라인 작업의 목록을 반환합니다.
     * 페이징 처리를 통해 대량의 작업 데이터를 효율적으로 조회할 수 있습니다.
     * 
     * 반환되는 작업 정보:
     * - 작업 ID 및 이름
     * - 작업 상태 (대기 중, 실행 중, 완료, 실패 등)
     * - 생성 시간 및 수정 시간
     * - 관련 웨이라인 파일 정보
     * - 작업 진행률
     * 
     * @param page 조회할 페이지 번호 (기본값: 1)
     * @param pageSize 페이지당 항목 수 (기본값: 10)
     * @param workspaceId 워크스페이스 ID
     * @return 웨이라인 작업 목록과 페이징 정보를 포함한 HTTP 응답
     */
    @GetMapping("/{workspace_id}/jobs")
    public HttpResultResponse<PaginationData<WaylineJobDTO>> getJobs(@RequestParam(defaultValue = "1") Long page,
                                                                     @RequestParam(name = "page_size", defaultValue = "10") Long pageSize,
                                                                     @PathVariable(name = "workspace_id") String workspaceId) {
        // 웨이라인 작업 목록 조회 및 페이징 처리
        PaginationData<WaylineJobDTO> data = waylineJobService.getJobsByWorkspaceId(workspaceId, page, pageSize);
        return HttpResultResponse.success(data);
    }

    /**
     * 지정된 웨이라인 작업들을 취소하는 명령을 발행합니다.
     * 
     * 이 메서드는 실행 중이거나 대기 중인 웨이라인 작업들을 취소하기 위해 사용됩니다.
     * 취소 명령은 Dock에 전송되어 실제 드론 비행을 중단시킵니다.
     * 
     * 취소 가능한 작업 상태:
     * - 대기 중 (WAITING): 아직 실행되지 않은 작업
     * - 실행 중 (RUNNING): 현재 실행 중인 작업
     * - 일시 정지 (PAUSED): 일시 정지된 작업
     * 
     * @param jobIds 취소할 웨이라인 작업 ID 집합
     * @param workspaceId 워크스페이스 ID
     * @return 작업 취소 명령 발행 성공 여부를 포함한 HTTP 응답
     * @throws SQLException 데이터베이스 작업 중 발생할 수 있는 예외
     */
    @DeleteMapping("/{workspace_id}/jobs")
    public HttpResultResponse publishCancelJob(@RequestParam(name = "job_id") Set<String> jobIds,
                                               @PathVariable(name = "workspace_id") String workspaceId) throws SQLException {
        // 웨이라인 작업 취소 명령을 Dock에 발행
        flighttaskService.cancelFlightTask(workspaceId, jobIds);
        return HttpResultResponse.success();
    }

    /**
     * 지정된 웨이라인 작업의 미디어 파일을 즉시 업로드하도록 우선순위를 설정합니다.
     * 
     * 이 메서드는 웨이라인 작업 실행 중 생성된 미디어 파일(사진, 비디오)을
     * 즉시 클라우드 스토리지에 업로드하도록 우선순위를 높이는 설정을 수행합니다.
     * 
     * 일반적으로 미디어 파일은 작업 완료 후 배치로 업로드되지만,
     * 이 설정을 통해 실시간으로 미디어 파일을 확인할 수 있습니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param jobId 미디어 우선 업로드를 설정할 웨이라인 작업 ID
     * @return 미디어 우선 업로드 설정 성공 여부를 포함한 HTTP 응답
     */
    @PostMapping("/{workspace_id}/jobs/{job_id}/media-highest")
    public HttpResultResponse uploadMediaHighestPriority(@PathVariable(name = "workspace_id") String workspaceId,
                                                         @PathVariable(name = "job_id") String jobId) {
        // 웨이라인 작업의 미디어 파일 우선 업로드 설정
        flighttaskService.uploadMediaHighestPriority(workspaceId, jobId);
        return HttpResultResponse.success();
    }

    /**
     * 웨이라인 작업의 상태를 업데이트합니다.
     * 
     * 이 메서드는 웨이라인 작업의 상태 정보를 수정하기 위해 사용됩니다.
     * 작업 상태, 진행률, 오류 정보 등을 업데이트할 수 있습니다.
     * 
     * 업데이트 가능한 정보:
     * - 작업 상태 (대기 중, 실행 중, 완료, 실패 등)
     * - 작업 진행률 (0-100%)
     * - 작업 설명 또는 메모
     * - 오류 메시지 또는 로그
     * 
     * @param workspaceId 워크스페이스 ID
     * @param jobId 업데이트할 웨이라인 작업 ID
     * @param param 작업 업데이트 파라미터 (상태, 진행률 등)
     * @return 작업 상태 업데이트 성공 여부를 포함한 HTTP 응답
     */
    @PutMapping("/{workspace_id}/jobs/{job_id}")
    public HttpResultResponse updateJobStatus(@PathVariable(name = "workspace_id") String workspaceId,
                                              @PathVariable(name = "job_id") String jobId,
                                              @Valid @RequestBody UpdateJobParam param) {
        // 웨이라인 작업 상태 업데이트
        flighttaskService.updateJobStatus(workspaceId, jobId, param);
        return HttpResultResponse.success();
    }
}
