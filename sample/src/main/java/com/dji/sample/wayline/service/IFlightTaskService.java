package com.dji.sample.wayline.service;

import com.dji.sample.common.model.CustomClaim;
import com.dji.sample.wayline.model.dto.ConditionalWaylineJobKey;
import com.dji.sample.wayline.model.dto.WaylineJobDTO;
import com.dji.sample.wayline.model.param.CreateJobParam;
import com.dji.sample.wayline.model.param.UpdateJobParam;
import com.dji.sdk.common.HttpResultResponse;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * DJI Cloud API 비행 작업 서비스 인터페이스
 * 
 * 이 인터페이스는 DJI Dock에 웨이라인 미션을 발행하고 관리하는 서비스 계층의 계약을 정의합니다.
 * 웨이라인 작업의 실행, 취소, 상태 제어 등의 기능을 제공하며,
 * DJI Cloud API와의 연동을 통해 실제 드론 비행 작업을 관리합니다.
 * 
 * 주요 기능:
 * - 웨이라인 미션을 Dock에 발행
 * - 웨이라인 작업 즉시 실행
 * - 웨이라인 작업 취소 및 중단
 * - 미디어 파일 업로드 우선순위 설정
 * - 웨이라인 작업 상태 수동 제어
 * - 조건부 웨이라인 작업 재시도
 * 
 * 사용 시나리오:
 * - DJI Pilot에서 웨이라인 작업 실행
 * - 자동화된 웨이라인 작업 스케줄링
 * - 웨이라인 작업 모니터링 및 제어
 * - 비상 상황 시 작업 중단
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/9
 */
public interface IFlightTaskService {

    /**
     * 웨이라인 미션을 Dock에 발행합니다.
     * 
     * @param param 웨이라인 작업 생성 파라미터
     * @param customClaim 사용자 정보 (권한, 워크스페이스 등)
     * @return HTTP 응답 결과
     * @throws SQLException 데이터베이스 오류 발생 시
     */
    HttpResultResponse publishFlightTask(CreateJobParam param, CustomClaim customClaim) throws SQLException;

    /**
     * 웨이라인 미션을 Dock에 발행합니다.
     * 
     * @param waylineJob 발행할 웨이라인 작업 정보
     * @return HTTP 응답 결과
     * @throws SQLException 데이터베이스 오류 발생 시
     */
    HttpResultResponse publishOneFlightTask(WaylineJobDTO waylineJob) throws SQLException;

    /**
     * 작업을 즉시 실행합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param jobId 실행할 작업 ID
     * @return 실행 성공 여부
     * @throws SQLException 데이터베이스 오류 발생 시
     */
    Boolean executeFlightTask(String workspaceId, String jobId);

    /**
     * 작업 ID를 기반으로 작업을 취소합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param jobIds 취소할 작업 ID 목록
     * @throws SQLException 데이터베이스 오류 발생 시
     */
    void cancelFlightTask(String workspaceId, Collection<String> jobIds);

    /**
     * 발행되었지만 아직 실행되지 않은 Dock 작업들을 취소합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param dockSn Dock 시리얼 번호
     * @param jobIds 취소할 작업 ID 목록
     */
    void publishCancelTask(String workspaceId, String dockSn, List<String> jobIds);

    /**
     * 이 작업의 미디어 파일을 즉시 업로드하도록 우선순위를 설정합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param jobId 미디어 업로드 우선순위를 설정할 작업 ID
     */
    void uploadMediaHighestPriority(String workspaceId, String jobId);

    /**
     * 웨이라인 작업의 실행 상태를 수동으로 제어합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param jobId 제어할 작업 ID
     * @param param 작업 상태 업데이트 파라미터 (PAUSE, RESUME 등)
     */
    void updateJobStatus(String workspaceId, String jobId, UpdateJobParam param);

    /**
     * 조건부 웨이라인 작업을 재시도합니다.
     * 
     * @param jobKey 조건부 웨이라인 작업 키
     * @param waylineJob 재시도할 웨이라인 작업 정보
     */
    void retryPrepareJob(ConditionalWaylineJobKey jobKey, WaylineJobDTO waylineJob);
}
