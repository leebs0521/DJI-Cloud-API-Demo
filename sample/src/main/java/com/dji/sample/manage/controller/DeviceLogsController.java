package com.dji.sample.manage.controller;

import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.manage.model.dto.DeviceLogsDTO;
import com.dji.sample.manage.model.param.DeviceLogsCreateParam;
import com.dji.sample.manage.model.param.DeviceLogsGetParam;
import com.dji.sample.manage.model.param.DeviceLogsQueryParam;
import com.dji.sample.manage.service.IDeviceLogsService;
import com.dji.sdk.cloudapi.log.FileUploadUpdateRequest;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.PaginationData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;

import static com.dji.sample.component.AuthInterceptor.TOKEN_CLAIM;

/**
 * 디바이스 로그 관리 컨트롤러
 * 
 * DJI Cloud API의 디바이스 로그 관리 기능을 제공하는 REST API 컨트롤러입니다.
 * 이 컨트롤러는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 로그 업로드 관리
 *    - 디바이스 로그 업로드 요청 생성
 *    - 업로드된 로그 목록 조회
 *    - 로그 업로드 상태 모니터링
 * 
 * 2. 실시간 로그 조회
 *    - 실시간 로그 파일 목록 조회
 *    - 도메인별 로그 필터링
 *    - 로그 파일 메타데이터 관리
 * 
 * 3. 로그 파일 다운로드
 *    - 로그 파일 다운로드 URL 생성
 *    - 로그 파일 직접 다운로드
 *    - 다운로드 권한 관리
 * 
 * 4. 로그 업로드 제어
 *    - 로그 업로드 취소
 *    - 업로드 이력 삭제
 *    - 업로드 상태 업데이트
 * 
 * 모든 API는 사용자 인증을 통해 보안을 보장하며,
 * 워크스페이스별로 로그 접근 권한을 관리합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
@RestController
@Slf4j
@RequestMapping("${url.manage.prefix}${url.manage.version}/workspaces")
public class DeviceLogsController {

    /** 디바이스 로그 서비스 - 로그 관리 비즈니스 로직 */
    @Autowired
    private IDeviceLogsService deviceLogsService;

    /**
     * 쿼리 파라미터에 따라 디바이스 업로드 로그 목록을 페이지네이션으로 조회합니다.
     * 
     * 이 API는 디바이스에서 업로드된 로그 파일들의 목록을 조회합니다.
     * 페이지네이션을 통해 대용량 데이터를 효율적으로 처리할 수 있습니다.
     * 
     * @param param 로그 조회 파라미터 (검색 조건, 페이지네이션 등)
     * @param workspaceId 워크스페이스 ID
     * @param deviceSn 디바이스 시리얼 번호
     * @return 업로드된 로그 목록 (페이지네이션 포함)
     */
    @GetMapping("/{workspace_id}/devices/{device_sn}/logs-uploaded")
    public HttpResultResponse getUploadedLogs(DeviceLogsQueryParam param, @PathVariable("workspace_id") String workspaceId,
                                              @PathVariable("device_sn") String deviceSn) {
        PaginationData<DeviceLogsDTO> data = deviceLogsService.getUploadedLogs(deviceSn, param);
        return HttpResultResponse.success(data);
    }

    /**
     * 실시간으로 업로드 가능한 로그 파일 목록을 조회합니다.
     * 
     * 이 API는 디바이스에서 현재 업로드 가능한 로그 파일들의 목록을 조회합니다.
     * 도메인별로 필터링하여 특정 도메인의 로그만 조회할 수 있습니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param deviceSn 디바이스 시리얼 번호
     * @param param 로그 조회 파라미터 (도메인 목록 등)
     * @return 실시간 로그 파일 목록
     */
    @GetMapping("/{workspace_id}/devices/{device_sn}/logs")
    public HttpResultResponse getLogsBySn(@PathVariable("workspace_id") String workspaceId,
                                          @PathVariable("device_sn") String deviceSn,
                                          DeviceLogsGetParam param) {
        return deviceLogsService.getRealTimeLogs(deviceSn, param.getDomainList());
    }

    /**
     * 게이트웨이에 로그 업로드 요청을 시작합니다.
     * 
     * 이 API는 디바이스에 로그 파일 업로드 명령을 전송합니다.
     * 사용자 인증을 통해 업로드 요청의 권한을 확인합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param deviceSn 디바이스 시리얼 번호
     * @param request HTTP 요청 객체 (토큰 정보 추출용)
     * @param param 로그 업로드 생성 파라미터
     * @return 로그 업로드 요청 결과
     */
    @PostMapping("/{workspace_id}/devices/{device_sn}/logs")
    public HttpResultResponse uploadLogs(@PathVariable("workspace_id") String workspaceId,
                                         @PathVariable("device_sn") String deviceSn,
                                         HttpServletRequest request, @RequestBody DeviceLogsCreateParam param) {

        // 토큰에서 사용자 정보 추출
        CustomClaim customClaim = (CustomClaim)request.getAttribute(TOKEN_CLAIM);

        return deviceLogsService.pushFileUpload(customClaim.getUsername(), deviceSn, param);
    }

    /**
     * 로그 파일 업로드를 취소합니다.
     * 
     * 이 API는 진행 중인 로그 파일 업로드를 취소합니다.
     * 업로드 상태를 업데이트하여 업로드 작업을 중단시킵니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param deviceSn 디바이스 시리얼 번호
     * @param param 파일 업로드 업데이트 요청 파라미터
     * @return 로그 업로드 취소 결과
     */
    @DeleteMapping("/{workspace_id}/devices/{device_sn}/logs")
    public HttpResultResponse cancelUploadedLogs(@PathVariable("workspace_id") String workspaceId,
                                                 @PathVariable("device_sn") String deviceSn,
                                                 @RequestBody FileUploadUpdateRequest param) {

        return deviceLogsService.pushUpdateFile(deviceSn, param);
    }

    /**
     * 업로드 이력을 삭제합니다.
     * 
     * 이 API는 특정 로그 파일의 업로드 이력을 삭제합니다.
     * 삭제된 로그는 복구할 수 없으므로 주의가 필요합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param deviceSn 디바이스 시리얼 번호
     * @param logsId 로그 ID
     * @return 업로드 이력 삭제 결과
     */
    @DeleteMapping("/{workspace_id}/devices/{device_sn}/logs/{logs_id}")
    public HttpResultResponse deleteUploadedLogs(@PathVariable("workspace_id") String workspaceId,
                                                 @PathVariable("device_sn") String deviceSn,
                                                 @PathVariable("logs_id") String logsId) {
        deviceLogsService.deleteLogs(deviceSn, logsId);
        return HttpResultResponse.success();
    }
    
    /**
     * 로그 파일 ID에 따라 파일의 다운로드 주소를 조회하고 직접 다운로드로 리다이렉트합니다.
     * 
     * 이 API는 로그 파일의 다운로드 URL을 생성하고 해당 주소로 리다이렉트합니다.
     * 파일이 존재하지 않거나 접근 권한이 없는 경우 오류를 반환합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param fileId 파일 ID
     * @param logsId 로그 ID
     * @param response HTTP 응답 객체 (리다이렉트용)
     * @return 파일 다운로드 URL 또는 오류 응답
     */
    @GetMapping("/{workspace_id}/logs/{logs_id}/url/{file_id}")
    public HttpResultResponse getFileUrl(@PathVariable(name = "workspace_id") String workspaceId,
                                         @PathVariable(name = "file_id") String fileId,
                                         @PathVariable(name = "logs_id") String logsId, HttpServletResponse response) {

        try {
            // 로그 파일 다운로드 URL 생성
            URL url = deviceLogsService.getLogsFileUrl(logsId, fileId);
            return HttpResultResponse.success(url.toString());
        } catch (Exception e) {
            log.error("Failed to get the logs file download address.");
            e.printStackTrace();
        }
        return HttpResultResponse.error("Failed to get the logs file download address.");
    }
}
