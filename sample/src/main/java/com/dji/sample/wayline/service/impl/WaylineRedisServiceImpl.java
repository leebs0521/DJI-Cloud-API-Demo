package com.dji.sample.wayline.service.impl;

import com.dji.sample.component.mqtt.model.EventsReceiver;
import com.dji.sample.component.redis.RedisConst;
import com.dji.sample.component.redis.RedisOpsUtils;
import com.dji.sample.wayline.model.dto.ConditionalWaylineJobKey;
import com.dji.sample.wayline.model.dto.WaylineJobDTO;
import com.dji.sample.wayline.service.IWaylineRedisService;
import com.dji.sdk.cloudapi.wayline.FlighttaskProgress;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;

/**
 * DJI Cloud API 웨이라인 Redis 서비스 구현 클래스
 *
 * 이 클래스는 IWaylineRedisService 인터페이스의 실제 구현체로, Redis를 사용하여 웨이라인 작업의
 * 실시간 상태와 캐시를 관리합니다. 웨이라인 작업의 실행, 일시정지, 차단, 조건부 실행 등의
 * 상태 정보를 Redis에 저장하고 조회하는 기능을 제공합니다.
 *
 * 주요 기능:
 * - 실행 중인 웨이라인 작업 상태 관리
 * - 일시정지된 웨이라인 작업 관리
 * - 차단된 웨이라인 작업 관리
 * - 조건부 웨이라인 작업 스케줄링
 * - 웨이라인 작업 진행률 실시간 캐싱
 *
 * Redis 키 구조:
 * - 실행 중: WAYLINE_JOB_RUNNING_PREFIX + dockSn
 * - 일시정지: WAYLINE_JOB_PAUSED_PREFIX + dockSn
 * - 차단: WAYLINE_JOB_BLOCK_PREFIX + dockSn
 * - 조건부: WAYLINE_JOB_CONDITION_PREFIX + jobId
 * - 조건부 준비: WAYLINE_JOB_CONDITION_PREPARE (Sorted Set)
 *
 * 만료 시간:
 * - 실행/일시정지/차단 작업: DRC_MODE_ALIVE_SECOND (DRC 모드 활성 시간)
 * - 차단 작업: WAYLINE_JOB_BLOCK_TIME (차단 지속 시간)
 * - 조건부 작업: 작업 종료 시간까지
 *
 * @author sean
 * @version 1.4
 * @date 2023/3/24
 */
@Service
public class WaylineRedisServiceImpl implements IWaylineRedisService {

    /**
     * 실행 중인 웨이라인 작업을 Redis에 저장
     *
     * DJI Dock에서 현재 실행 중인 웨이라인 작업의 상태 정보를 Redis에 캐시합니다.
     * 작업 진행률, 상태 업데이트 등의 실시간 정보를 포함하며,
     * DRC 모드 활성 시간 동안 유지됩니다.
     *
     * @param dockSn Dock 시리얼 번호
     * @param data 웨이라인 작업 진행 정보 (EventsReceiver<FlighttaskProgress>)
     */
    @Override
    public void setRunningWaylineJob(String dockSn, EventsReceiver<FlighttaskProgress> data) {
        RedisOpsUtils.setWithExpire(RedisConst.WAYLINE_JOB_RUNNING_PREFIX + dockSn, data, RedisConst.DRC_MODE_ALIVE_SECOND);
    }

    /**
     * 실행 중인 웨이라인 작업 정보를 Redis에서 조회
     *
     * 특정 Dock에서 현재 실행 중인 웨이라인 작업의 상태 정보를 Redis에서 가져옵니다.
     * 작업이 실행 중이지 않거나 만료된 경우 Optional.empty()를 반환합니다.
     *
     * @param dockSn Dock 시리얼 번호
     * @return 실행 중인 웨이라인 작업 정보 (Optional)
     */
    @Override
    public Optional<EventsReceiver<FlighttaskProgress>> getRunningWaylineJob(String dockSn) {
        return Optional.ofNullable((EventsReceiver<FlighttaskProgress>) RedisOpsUtils.get(RedisConst.WAYLINE_JOB_RUNNING_PREFIX + dockSn));
    }

    /**
     * 실행 중인 웨이라인 작업 정보를 Redis에서 삭제
     *
     * 웨이라인 작업이 완료되거나 취소될 때 Redis에서 해당 작업 정보를 제거합니다.
     *
     * @param dockSn Dock 시리얼 번호
     * @return 삭제 성공 여부
     */
    @Override
    public Boolean delRunningWaylineJob(String dockSn) {
        return RedisOpsUtils.del(RedisConst.WAYLINE_JOB_RUNNING_PREFIX + dockSn);
    }

    /**
     * 일시정지된 웨이라인 작업을 Redis에 저장
     *
     * 사용자가 웨이라인 작업을 일시정지했을 때 해당 작업 ID를 Redis에 저장합니다.
     * 작업 재개 시 이 정보를 사용하여 일시정지된 작업을 식별합니다.
     *
     * @param dockSn Dock 시리얼 번호
     * @param jobId 일시정지된 작업 ID
     */
    @Override
    public void setPausedWaylineJob(String dockSn, String jobId) {
        RedisOpsUtils.setWithExpire(RedisConst.WAYLINE_JOB_PAUSED_PREFIX + dockSn, jobId, RedisConst.DRC_MODE_ALIVE_SECOND);
    }

    /**
     * 일시정지된 웨이라인 작업 ID를 Redis에서 조회
     *
     * 특정 Dock에서 일시정지된 웨이라인 작업의 ID를 가져옵니다.
     * 일시정지된 작업이 없거나 만료된 경우 null을 반환합니다.
     *
     * @param dockSn Dock 시리얼 번호
     * @return 일시정지된 작업 ID (String)
     */
    @Override
    public String getPausedWaylineJobId(String dockSn) {
        return (String) RedisOpsUtils.get(RedisConst.WAYLINE_JOB_PAUSED_PREFIX + dockSn);
    }

    /**
     * 일시정지된 웨이라인 작업 정보를 Redis에서 삭제
     *
     * 웨이라인 작업이 재개되거나 완료될 때 일시정지 정보를 Redis에서 제거합니다.
     *
     * @param dockSn Dock 시리얼 번호
     * @return 삭제 성공 여부
     */
    @Override
    public Boolean delPausedWaylineJob(String dockSn) {
        return RedisOpsUtils.del(RedisConst.WAYLINE_JOB_PAUSED_PREFIX + dockSn);
    }

    /**
     * 차단된 웨이라인 작업을 Redis에 저장
     *
     * 오류나 안전 문제로 인해 차단된 웨이라인 작업의 ID를 Redis에 저장합니다.
     * 차단된 작업은 지정된 시간 동안 재실행이 제한됩니다.
     *
     * @param dockSn Dock 시리얼 번호
     * @param jobId 차단된 작업 ID
     */
    @Override
    public void setBlockedWaylineJob(String dockSn, String jobId) {
        RedisOpsUtils.setWithExpire(RedisConst.WAYLINE_JOB_BLOCK_PREFIX + dockSn, jobId, RedisConst.WAYLINE_JOB_BLOCK_TIME);
    }

    /**
     * 차단된 웨이라인 작업 ID를 Redis에서 조회
     *
     * 특정 Dock에서 차단된 웨이라인 작업의 ID를 가져옵니다.
     * 차단 기간이 만료되면 null을 반환합니다.
     *
     * @param dockSn Dock 시리얼 번호
     * @return 차단된 작업 ID (String)
     */
    @Override
    public String getBlockedWaylineJobId(String dockSn) {
        return (String) RedisOpsUtils.get(RedisConst.WAYLINE_JOB_BLOCK_PREFIX + dockSn);
    }

    /**
     * 조건부 웨이라인 작업을 Redis에 저장
     *
     * 특정 조건이 만족될 때까지 대기하는 웨이라인 작업을 Redis에 저장합니다.
     * 작업의 종료 시간까지 유지되며, 조건이 만족되면 자동으로 실행됩니다.
     *
     * @param waylineJob 조건부 웨이라인 작업 정보
     * @throws RuntimeException 작업 ID가 null인 경우
     */
    @Override
    public void setConditionalWaylineJob(WaylineJobDTO waylineJob) {
        if (!StringUtils.hasText(waylineJob.getJobId())) {
            throw new RuntimeException("Job id can't be null.");
        }
        RedisOpsUtils.setWithExpire(RedisConst.WAYLINE_JOB_CONDITION_PREFIX + waylineJob.getJobId(), waylineJob,
                (Duration.between(waylineJob.getEndTime(), LocalDateTime.now()).getSeconds()));
    }

    /**
     * 조건부 웨이라인 작업 정보를 Redis에서 조회
     *
     * 특정 작업 ID에 해당하는 조건부 웨이라인 작업 정보를 가져옵니다.
     * 작업이 만료되거나 존재하지 않는 경우 Optional.empty()를 반환합니다.
     *
     * @param jobId 작업 ID
     * @return 조건부 웨이라인 작업 정보 (Optional)
     */
    @Override
    public Optional<WaylineJobDTO> getConditionalWaylineJob(String jobId) {
        return Optional.ofNullable((WaylineJobDTO) RedisOpsUtils.get(RedisConst.WAYLINE_JOB_CONDITION_PREFIX + jobId));
    }

    /**
     * 조건부 웨이라인 작업 정보를 Redis에서 삭제
     *
     * 조건부 웨이라인 작업이 실행되거나 취소될 때 Redis에서 해당 정보를 제거합니다.
     *
     * @param jobId 작업 ID
     * @return 삭제 성공 여부
     */
    @Override
    public Boolean delConditionalWaylineJob(String jobId) {
        return RedisOpsUtils.del(RedisConst.WAYLINE_JOB_CONDITION_PREFIX + jobId);
    }

    /**
     * 조건부 웨이라인 작업을 준비 큐에 추가
     *
     * 조건부 웨이라인 작업을 Redis의 정렬된 집합(Sorted Set)에 추가하여
     * 실행 시간 순으로 정렬된 준비 큐를 관리합니다.
     * 값 형식: "{workspace_id}:{dock_sn}:{job_id}"
     * 점수: 작업 시작 시간 (Unix timestamp)
     *
     * @param waylineJob 조건부 웨이라인 작업 정보
     * @return 추가 성공 여부 (시작 시간이 null인 경우 false)
     */
    @Override
    public Boolean addPrepareConditionalWaylineJob(WaylineJobDTO waylineJob) {
        if (Objects.isNull(waylineJob.getBeginTime())) {
            return false;
        }
        // value: {workspace_id}:{dock_sn}:{job_id}
        return RedisOpsUtils.zAdd(RedisConst.WAYLINE_JOB_CONDITION_PREPARE,
                waylineJob.getWorkspaceId() + RedisConst.DELIMITER + waylineJob.getDockSn() + RedisConst.DELIMITER + waylineJob.getJobId(),
                waylineJob.getBeginTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    /**
     * 가장 가까운 조건부 웨이라인 작업을 조회
     *
     * Redis의 정렬된 집합에서 가장 가까운 실행 시간의 조건부 웨이라인 작업을 가져옵니다.
     * 스케줄러가 주기적으로 호출하여 실행 가능한 작업을 찾습니다.
     *
     * @return 가장 가까운 조건부 웨이라인 작업 키 (Optional)
     */
    @Override
    public Optional<ConditionalWaylineJobKey> getNearestConditionalWaylineJob() {
        return Optional.ofNullable(RedisOpsUtils.zGetMin(RedisConst.WAYLINE_JOB_CONDITION_PREPARE))
                .map(Object::toString).map(ConditionalWaylineJobKey::new);
    }

    /**
     * 조건부 웨이라인 작업의 실행 시간을 조회
     *
     * 특정 조건부 웨이라인 작업의 예정된 실행 시간을 Redis에서 가져옵니다.
     * Unix timestamp 형태로 반환됩니다.
     *
     * @param jobKey 조건부 웨이라인 작업 키
     * @return 실행 시간 (Unix timestamp)
     */
    @Override
    public Double getConditionalWaylineJobTime(ConditionalWaylineJobKey jobKey) {
        return RedisOpsUtils.zScore(RedisConst.WAYLINE_JOB_CONDITION_PREPARE, jobKey.getKey());
    }

    /**
     * 조건부 웨이라인 작업을 준비 큐에서 제거
     *
     * 조건부 웨이라인 작업이 실행되거나 취소될 때 준비 큐에서 해당 작업을 제거합니다.
     *
     * @param jobKey 조건부 웨이라인 작업 키
     * @return 제거 성공 여부
     */
    @Override
    public Boolean removePrepareConditionalWaylineJob(ConditionalWaylineJobKey jobKey) {
        return RedisOpsUtils.zRemove(RedisConst.WAYLINE_JOB_CONDITION_PREPARE, jobKey.getKey());
    }
}
