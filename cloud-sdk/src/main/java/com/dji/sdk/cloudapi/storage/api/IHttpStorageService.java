package com.dji.sdk.cloudapi.storage.api;

import com.dji.sdk.cloudapi.storage.StsCredentialsResponse;
import com.dji.sdk.common.HttpResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HTTP 스토리지 서비스 인터페이스
 * 
 * 이 인터페이스는 클라우드 스토리지 서비스와 관련된 HTTP API를 정의합니다.
 * DJI Pilot에서 미디어 파일과 웨이라인 파일을 업로드하기 위한
 * 임시 자격 증명을 제공하는 기능을 포함합니다.
 * 
 * 주요 기능:
 * - 임시 자격 증명 토큰 발급
 * - 클라우드 스토리지 서비스 접근 권한 제공
 * - 파일 업로드를 위한 인증 정보 관리
 * 
 * 이 인터페이스는 클라우드 스토리지 서비스와의 안전한 통신을 위한
 * RESTful API 엔드포인트를 제공합니다.
 * 
 * @author sean
 * @version 0.3
 * @date 2021/12/29
 */
public interface IHttpStorageService {

    /**
     * API 경로 접두사
     * 
     * 스토리지 관련 API의 기본 경로를 정의합니다.
     * 모든 스토리지 API는 이 접두사로 시작합니다.
     */
    String PREFIX = "storage/api/v1";

    /**
     * DJI Pilot에서 미디어와 웨이라인 업로드를 위한 임시 자격 증명을 가져옵니다.
     * 
     * 클라우드 스토리지 서비스에 파일을 업로드하기 위한 임시 자격 증명을 발급합니다.
     * 이 자격 증명은 일정 시간 후 자동으로 만료되어 보안을 보장합니다.
     * 
     * @param workspaceId 워크스페이스 ID (UUID 형식)
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 임시 자격 증명 응답 데이터
     */
    @Operation(summary = "STS 토큰 가져오기", description = "DJI Pilot에서 미디어와 웨이라인 업로드를 위한 임시 자격 증명을 가져옵니다.",
            parameters = {
                @Parameter(name = "workspace_id", description = "워크스페이스 ID", schema = @Schema(format = "uuid"))
    })
    @PostMapping(PREFIX + "/workspaces/{workspace_id}/sts")
    HttpResultResponse<StsCredentialsResponse> getTemporaryCredential(
            @PathVariable(name = "workspace_id") String workspaceId,
            HttpServletRequest req, HttpServletResponse rsp);

}
