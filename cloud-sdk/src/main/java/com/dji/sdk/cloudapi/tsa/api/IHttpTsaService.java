package com.dji.sdk.cloudapi.tsa.api;

import com.dji.sdk.cloudapi.tsa.TopologyResponse;
import com.dji.sdk.common.HttpResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TSA(Threat and Situation Awareness) HTTP 서비스 인터페이스
 * 
 * 이 인터페이스는 TSA 시스템과 관련된 HTTP API를 정의합니다.
 * 디바이스 토폴로지 정보를 조회하고 관리하는 기능을 포함합니다.
 * 
 * 주요 기능:
 * - 디바이스 토폴로지 리스트 조회
 * - 실시간 디바이스 상태 모니터링
 * - 워크스페이스별 디바이스 관리
 * 
 * 이 인터페이스는 TSA 시스템의 디바이스 토폴로지 정보를
 * RESTful API 엔드포인트로 제공합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/8
 */
//@Tag(name = "TSA 인터페이스")
public interface IHttpTsaService {

    /**
     * API 경로 접두사
     * 
     * TSA 관련 API의 기본 경로를 정의합니다.
     * 모든 TSA API는 이 접두사로 시작합니다.
     */
    String PREFIX = "manage/api/v1";

    /**
     * 현재 사용자 워크스페이스의 모든 디바이스 토폴로지 리스트를 파일럿 표시용으로 가져옵니다.
     * 
     * DJI Pilot 2에서 디바이스 토폴로지 정보를 조회하기 위한 API입니다.
     * 첫 번째 연결 시와 디바이스 상태 변경 시 호출됩니다.
     * 
     * @param workspaceId 워크스페이스 ID (UUID 형식)
     * @param req HTTP 요청 객체
     * @param rsp HTTP 응답 객체
     * @return 디바이스 토폴로지 응답 데이터
     */
    @Operation(summary = "디바이스 토폴로지 리스트 조회", description = "현재 사용자 워크스페이스의 모든 디바이스 토폴로지 리스트를 파일럿 표시용으로 가져옵니다. " +
            "첫 번째 연결 시, DJI Pilot 2는 이 인터페이스를 호출하여 모든 디바이스의 토폴로지 리스트를 가져옵니다. " +
            "또한, Pilot이 디바이스의 온라인, 오프라인, 업데이트를 알리는 웹소켓 명령을 받으면 " +
            "디바이스 토폴로지 리스트를 업데이트하기 위해 이 인터페이스를 호출합니다.",
            parameters = {
                    @Parameter(name = "workspace_id", description = "워크스페이스 ID", schema = @Schema(format = "uuid")),
            })
    @GetMapping(PREFIX + "/workspaces/{workspace_id}/devices/topologies")
    HttpResultResponse<TopologyResponse> obtainDeviceTopologyList(
            @PathVariable(name = "workspace_id") String workspaceId,
            HttpServletRequest req, HttpServletResponse rsp);

}
