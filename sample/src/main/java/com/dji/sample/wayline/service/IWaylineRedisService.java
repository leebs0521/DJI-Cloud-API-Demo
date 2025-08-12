package com.dji.sample.wayline.service;

import com.dji.sample.component.mqtt.model.EventsReceiver;
import com.dji.sample.wayline.model.dto.ConditionalWaylineJobKey;
import com.dji.sample.wayline.model.dto.WaylineJobDTO;
import com.dji.sdk.cloudapi.wayline.FlighttaskProgress;

import java.util.Optional;

/**
 * DJI Cloud API 웨이라인 Redis 서비스 인터페이스
 * 
 * 이 인터페이스는 웨이라인 작업의 실시간 상태와 캐시를 관리하는 Redis 서비스 계층의 계약을 정의합니다.
 * 웨이라인 작업의 실행 상태, 일시 정지 상태, 차단 상태, 조건부 작업 등을 Redis에 저장하고 관리하며,
 * 빠른 조회와 실시간 상태 업데이트를 지원합니다.
 * 
 * 주요 기능:
 * - 실행 중인 웨이라인 작업 상태 Redis 저장/조회/삭제
 * - 일시 정지된 웨이라인 작업 관리
 * - 차단된 웨이라인 작업 관리
 * - 조건부 웨이라인 작업 관리
 * - 조건부 웨이라인 작업 준비 상태 관리
 * - 조건부 웨이라인 작업 시간 관리
 * 
 * 사용 시나리오:
 * - 웨이라인 작업 실시간 상태 추적
 * - 작업 상태 캐싱을 통한 성능 최적화
 * - 조건부 작업 실행 관리
 * - 작업 상태 복구 및 재시도
 * 
 * Redis 키 구조:
 * - 실행 중 작업: "running_wayline_job:{dockSn}"
 * - 일시 정지 작업: "paused_wayline_job:{dockSn}"
 * - 차단 작업: "blocked_wayline_job:{dockSn}"
 * - 조건부 작업: "conditional_wayline_job:{jobId}"
 * - 준비 조건부 작업: "prepare_conditional_wayline_job:{workspaceId}:{dockSn}:{jobId}"
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/24
 */
public interface IWaylineRedisService {

    /**
     * Dock이 수행하는 웨이라인 작업의 상태를 Redis에 저장합니다.
     * 
     * @param dockSn Dock 시리얼 번호
     * @param data 저장할 웨이라인 작업 진행 상태 데이터
     */
    void setRunningWaylineJob(String dockSn, EventsReceiver<FlighttaskProgress> data);

    /**
     * Redis에서 Dock이 수행하는 웨이라인 작업의 상태를 조회합니다.
     * 
     * @param dockSn Dock 시리얼 번호
     * @return 웨이라인 작업 진행 상태 데이터 (Optional로 래핑되어 있음)
     */
    Optional<EventsReceiver<FlighttaskProgress>> getRunningWaylineJob(String dockSn);

    /**
     * Redis에서 Dock 작업의 웨이라인 작업 상태를 삭제합니다.
     * 
     * @param dockSn Dock 시리얼 번호
     * @return 삭제 성공 여부
     */
    Boolean delRunningWaylineJob(String dockSn);

    /**
     * Dock이 일시 정지한 웨이라인 작업을 Redis에 저장합니다.
     * 
     * @param dockSn Dock 시리얼 번호
     * @param jobId 일시 정지된 웨이라인 작업 ID
     */
    void setPausedWaylineJob(String dockSn, String jobId);

    /**
     * Redis에서 Dock이 일시 정지한 웨이라인 작업 ID를 조회합니다.
     * 
     * @param dockSn Dock 시리얼 번호
     * @return 일시 정지된 웨이라인 작업 ID
     */
    String getPausedWaylineJobId(String dockSn);

    /**
     * Redis에서 Dock이 일시 정지한 웨이라인 작업을 삭제합니다.
     * 
     * @param dockSn Dock 시리얼 번호
     * @return 삭제 성공 여부
     */
    Boolean delPausedWaylineJob(String dockSn);

    /**
     * Dock이 차단한 웨이라인 작업을 Redis에 저장합니다.
     * 
     * @param dockSn Dock 시리얼 번호
     * @param jobId 차단된 웨이라인 작업 ID
     */
    void setBlockedWaylineJob(String dockSn, String jobId);

    /**
     * Redis에서 Dock이 차단한 웨이라인 작업 ID를 조회합니다.
     * 
     * @param dockSn Dock 시리얼 번호
     * @return 차단된 웨이라인 작업 ID
     */
    String getBlockedWaylineJobId(String dockSn);

    /**
     * Dock의 조건부 웨이라인 작업을 Redis에 저장합니다.
     * 
     * @param waylineJob 저장할 조건부 웨이라인 작업 정보
     */
    void setConditionalWaylineJob(WaylineJobDTO waylineJob);

    /**
     * Redis에서 Dock의 조건부 웨이라인 작업 ID를 조회합니다.
     * 
     * @param jobId 조회할 웨이라인 작업 ID
     * @return 조건부 웨이라인 작업 정보 (Optional로 래핑되어 있음)
     */
    Optional<WaylineJobDTO> getConditionalWaylineJob(String jobId);

    /**
     * Redis에서 Dock의 조건부 웨이라인 작업을 삭제합니다.
     * 
     * @param jobId 삭제할 웨이라인 작업 ID
     * @return 삭제 성공 여부
     */
    Boolean delConditionalWaylineJob(String jobId);

    /**
     * 조건부 웨이라인 작업을 준비 상태로 Redis에 추가합니다.
     * 
     * @param waylineJob 준비할 조건부 웨이라인 작업 정보
     * @return 추가 성공 여부
     */
    Boolean addPrepareConditionalWaylineJob(WaylineJobDTO waylineJob);

    /**
     * 가장 가까운 조건부 웨이라인 작업을 조회합니다.
     * 
     * @return 가장 가까운 조건부 웨이라인 작업 키 (Optional로 래핑되어 있음)
     */
    Optional<ConditionalWaylineJobKey> getNearestConditionalWaylineJob();

    /**
     * 조건부 웨이라인 작업의 시간을 조회합니다.
     * 
     * @param jobKey 조건부 웨이라인 작업 키
     * @return 조건부 웨이라인 작업 시간 (Double 타입)
     */
    Double getConditionalWaylineJobTime(ConditionalWaylineJobKey jobKey);

    /**
     * 준비 상태의 조건부 웨이라인 작업을 Redis에서 제거합니다.
     * 
     * @param jobKey 제거할 조건부 웨이라인 작업 키
     * @return 제거 성공 여부
     */
    Boolean removePrepareConditionalWaylineJob(ConditionalWaylineJobKey jobKey);
}
