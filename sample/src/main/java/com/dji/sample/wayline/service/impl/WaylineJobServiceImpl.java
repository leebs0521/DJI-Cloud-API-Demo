package com.dji.sample.wayline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dji.sample.component.mqtt.model.EventsReceiver;
import com.dji.sample.component.redis.RedisConst;
import com.dji.sample.component.redis.RedisOpsUtils;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sample.manage.service.IDeviceService;
import com.dji.sample.media.model.MediaFileCountDTO;
import com.dji.sample.media.service.IFileService;
import com.dji.sample.wayline.dao.IWaylineJobMapper;
import com.dji.sample.wayline.model.dto.WaylineJobDTO;
import com.dji.sample.wayline.model.entity.WaylineJobEntity;
import com.dji.sample.wayline.model.enums.WaylineJobStatusEnum;
import com.dji.sample.wayline.model.param.CreateJobParam;
import com.dji.sample.wayline.service.IWaylineFileService;
import com.dji.sample.wayline.service.IWaylineJobService;
import com.dji.sample.wayline.service.IWaylineRedisService;
import com.dji.sdk.cloudapi.device.DockModeCodeEnum;
import com.dji.sdk.cloudapi.device.DroneModeCodeEnum;
import com.dji.sdk.cloudapi.device.OsdDock;
import com.dji.sdk.cloudapi.device.OsdDockDrone;
import com.dji.sdk.cloudapi.wayline.*;
import com.dji.sdk.common.Pagination;
import com.dji.sdk.common.PaginationData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * DJI Cloud API 웨이라인 작업 서비스 구현 클래스
 *
 * 이 클래스는 IWaylineJobService 인터페이스의 실제 구현체로, 웨이라인 작업의
 * 전체 생명주기를 관리합니다. 웨이라인 작업의 생성, 조회, 업데이트, 상태 관리,
 * 미디어 파일 카운트 동기화 등의 기능을 제공합니다.
 *
 * 주요 기능:
 * - 웨이라인 작업 생성 (즉시 실행, 부모 작업 기반)
 * - 웨이라인 작업 조회 (단일, 목록, 조건부)
 * - 웨이라인 작업 상태 관리 및 업데이트
 * - Dock 및 드론 상태 기반 웨이라인 상태 판단
 * - 미디어 파일 업로드 진행률 추적
 * - Redis 캐시를 통한 실시간 상태 관리
 *
 * 작업 상태:
 * - PENDING: 대기 중
 * - IN_PROGRESS: 실행 중
 * - PAUSED: 일시정지
 * - COMPLETED: 완료
 * - FAILED: 실패
 * - UNKNOWN: 알 수 없음
 *
 * 의존성:
 * - IWaylineJobMapper: 데이터베이스 접근
 * - IWaylineFileService: 웨이라인 파일 관리
 * - IDeviceService: 디바이스 관리
 * - IFileService: 미디어 파일 관리
 * - IDeviceRedisService: 디바이스 Redis 캐시
 * - IWaylineRedisService: 웨이라인 Redis 캐시
 *
 * @author sean
 * @version 1.1
 * @date 2022/6/1
 */
@Service
@Transactional
@Slf4j
public class WaylineJobServiceImpl implements IWaylineJobService {

    /**
     * 웨이라인 작업 데이터베이스 접근 매퍼
     */
    @Autowired
    private IWaylineJobMapper mapper;

    /**
     * 웨이라인 파일 서비스
     */
    @Autowired
    private IWaylineFileService waylineFileService;

    /**
     * 디바이스 서비스
     */
    @Autowired
    private IDeviceService deviceService;

    /**
     * JSON 직렬화/역직렬화를 위한 ObjectMapper
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 파일 서비스 (미디어 파일 관리)
     */
    @Autowired
    private IFileService fileService;

    /**
     * 디바이스 Redis 서비스
     */
    @Autowired
    private IDeviceRedisService deviceRedisService;

    /**
     * 웨이라인 Redis 서비스
     */
    @Autowired
    private IWaylineRedisService waylineRedisService;

    /**
     * 웨이라인 작업을 데이터베이스에 삽입
     *
     * 웨이라인 작업 엔티티를 데이터베이스에 저장하고
     * 성공 시 DTO로 변환하여 반환합니다.
     *
     * @param jobEntity 저장할 웨이라인 작업 엔티티
     * @return 저장된 웨이라인 작업 DTO (Optional)
     */
    private Optional<WaylineJobDTO> insertWaylineJob(WaylineJobEntity jobEntity) {
        int id = mapper.insert(jobEntity);
        if (id <= 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(this.entity2Dto(jobEntity));
    }

    /**
     * 새로운 웨이라인 작업 생성
     *
     * 사용자가 지정한 파라미터를 기반으로 새로운 웨이라인 작업을 생성합니다.
     * 즉시 실행 작업으로 분류되며, 백엔드에서 시간을 할당합니다.
     *
     * @param param 작업 생성 파라미터
     * @param workspaceId 워크스페이스 ID
     * @param username 작업 생성자
     * @param beginTime 작업 시작 시간 (Unix timestamp)
     * @param endTime 작업 종료 시간 (Unix timestamp)
     * @return 생성된 웨이라인 작업 DTO (Optional)
     */
    @Override
    public Optional<WaylineJobDTO> createWaylineJob(CreateJobParam param, String workspaceId, String username, Long beginTime, Long endTime) {
        if (Objects.isNull(param)) {
            return Optional.empty();
        }
        // 즉시 실행 작업으로 백엔드에서 시간 할당
        WaylineJobEntity jobEntity = WaylineJobEntity.builder()
                .name(param.getName())
                .dockSn(param.getDockSn())
                .fileId(param.getFileId())
                .username(username)
                .workspaceId(workspaceId)
                .jobId(UUID.randomUUID().toString())
                .beginTime(beginTime)
                .endTime(endTime)
                .status(WaylineJobStatusEnum.PENDING.getVal())
                .taskType(param.getTaskType().getType())
                .waylineType(param.getWaylineType().getValue())
                .outOfControlAction(param.getOutOfControlAction().getAction())
                .rthAltitude(param.getRthAltitude())
                .mediaCount(0)
                .build();

        return insertWaylineJob(jobEntity);
    }

    /**
     * 부모 작업을 기반으로 웨이라인 작업 생성
     *
     * 기존 웨이라인 작업을 복제하여 새로운 작업을 생성합니다.
     * 오류 코드와 완료 시간을 초기화하고 상태를 PENDING으로 설정합니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param parentId 부모 작업 ID
     * @return 생성된 웨이라인 작업 DTO (Optional)
     */
    @Override
    public Optional<WaylineJobDTO> createWaylineJobByParent(String workspaceId, String parentId) {
        Optional<WaylineJobDTO> parentJobOpt = this.getJobByJobId(workspaceId, parentId);
        if (parentJobOpt.isEmpty()) {
            return Optional.empty();
        }
        WaylineJobEntity jobEntity = this.dto2Entity(parentJobOpt.get());
        jobEntity.setJobId(UUID.randomUUID().toString());
        jobEntity.setErrorCode(null);
        jobEntity.setCompletedTime(null);
        jobEntity.setExecuteTime(null);
        jobEntity.setStatus(WaylineJobStatusEnum.PENDING.getVal());
        jobEntity.setParentId(parentId);

        return this.insertWaylineJob(jobEntity);
    }

    /**
     * 조건에 따른 웨이라인 작업 목록 조회
     *
     * 워크스페이스 ID, 작업 ID 목록, 상태를 조건으로 하여
     * 웨이라인 작업 목록을 조회합니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param jobIds 작업 ID 컬렉션
     * @param status 작업 상태
     * @return 웨이라인 작업 DTO 목록
     */
    public List<WaylineJobDTO> getJobsByConditions(String workspaceId, Collection<String> jobIds, WaylineJobStatusEnum status) {
        return mapper.selectList(
                new LambdaQueryWrapper<WaylineJobEntity>()
                        .eq(WaylineJobEntity::getWorkspaceId, workspaceId)
                        .eq(Objects.nonNull(status), WaylineJobEntity::getStatus, status.getVal())
                        .and(!CollectionUtils.isEmpty(jobIds),
                                wrapper -> jobIds.forEach(id -> wrapper.eq(WaylineJobEntity::getJobId, id).or())))
                .stream()
                .map(this::entity2Dto)
                .collect(Collectors.toList());
    }

    /**
     * 작업 ID로 웨이라인 작업 조회
     *
     * 워크스페이스 ID와 작업 ID를 사용하여 특정 웨이라인 작업을 조회합니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param jobId 작업 ID
     * @return 웨이라인 작업 DTO (Optional)
     */
    @Override
    public Optional<WaylineJobDTO> getJobByJobId(String workspaceId, String jobId) {
        WaylineJobEntity jobEntity = mapper.selectOne(
                new LambdaQueryWrapper<WaylineJobEntity>()
                        .eq(WaylineJobEntity::getWorkspaceId, workspaceId)
                        .eq(WaylineJobEntity::getJobId, jobId));
        return Optional.ofNullable(entity2Dto(jobEntity));
    }

    /**
     * 웨이라인 작업 업데이트
     *
     * 웨이라인 작업 DTO의 정보를 데이터베이스에 업데이트합니다.
     *
     * @param dto 업데이트할 웨이라인 작업 DTO
     * @return 업데이트 성공 여부
     */
    @Override
    public Boolean updateJob(WaylineJobDTO dto) {
        return mapper.update(this.dto2Entity(dto),
                new LambdaUpdateWrapper<WaylineJobEntity>()
                        .eq(WaylineJobEntity::getJobId, dto.getJobId())) > 0;
    }

    /**
     * 워크스페이스별 웨이라인 작업 목록 조회 (페이징)
     *
     * 특정 워크스페이스의 웨이라인 작업 목록을 페이징 처리하여 조회합니다.
     * ID 기준 내림차순으로 정렬됩니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param page 페이지 번호
     * @param pageSize 페이지 크기
     * @return 페이징된 웨이라인 작업 목록
     */
    @Override
    public PaginationData<WaylineJobDTO> getJobsByWorkspaceId(String workspaceId, long page, long pageSize) {
        Page<WaylineJobEntity> pageData = mapper.selectPage(
                new Page<WaylineJobEntity>(page, pageSize),
                new LambdaQueryWrapper<WaylineJobEntity>()
                        .eq(WaylineJobEntity::getWorkspaceId, workspaceId)
                        .orderByDesc(WaylineJobEntity::getId));
        List<WaylineJobDTO> records = pageData.getRecords()
                .stream()
                .map(this::entity2Dto)
                .collect(Collectors.toList());

        return new PaginationData<WaylineJobDTO>(records, new Pagination(pageData.getCurrent(), pageData.getSize(), pageData.getTotal()));
    }

    /**
     * 웨이라인 작업 DTO를 엔티티로 변환
     *
     * WaylineJobDTO를 WaylineJobEntity로 변환하여
     * 데이터베이스 저장에 적합한 형태로 만듭니다.
     *
     * 변환 내용:
     * - LocalDateTime을 Unix timestamp로 변환
     * - Enum 타입을 정수값으로 변환
     * - 기타 메타데이터 복사
     *
     * @param dto 웨이라인 작업 DTO
     * @return 웨이라인 작업 엔티티
     */
    private WaylineJobEntity dto2Entity(WaylineJobDTO dto) {
        WaylineJobEntity.WaylineJobEntityBuilder builder = WaylineJobEntity.builder();
        if (dto == null) {
            return builder.build();
        }
        if (Objects.nonNull(dto.getBeginTime())) {
            builder.beginTime(dto.getBeginTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        }
        if (Objects.nonNull(dto.getEndTime())) {
            builder.endTime(dto.getEndTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        }
        if (Objects.nonNull(dto.getExecuteTime())) {
            builder.executeTime(dto.getExecuteTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        }
        if (Objects.nonNull(dto.getCompletedTime())) {
            builder.completedTime(dto.getCompletedTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        }
        return builder.status(dto.getStatus())
                .mediaCount(dto.getMediaCount())
                .name(dto.getJobName())
                .errorCode(dto.getCode())
                .jobId(dto.getJobId())
                .fileId(dto.getFileId())
                .dockSn(dto.getDockSn())
                .workspaceId(dto.getWorkspaceId())
                .taskType(Optional.ofNullable(dto.getTaskType()).map(TaskTypeEnum::getType).orElse(null))
                .waylineType(Optional.ofNullable(dto.getWaylineType()).map(WaylineTypeEnum::getValue).orElse(null))
                .username(dto.getUsername())
                .rthAltitude(dto.getRthAltitude())
                .outOfControlAction(Optional.ofNullable(dto.getOutOfControlAction())
                        .map(OutOfControlActionEnum::getAction).orElse(null))
                .parentId(dto.getParentId())
                .build();
    }

    /**
     * Dock의 웨이라인 상태 조회
     *
     * 특정 Dock의 현재 웨이라인 상태를 판단합니다.
     * Dock과 드론의 OSD 정보를 기반으로 상태를 결정합니다.
     *
     * 상태 판단 로직:
     * 1. Dock이 온라인이고 자식 디바이스(드론)가 연결되어 있는지 확인
     * 2. Dock이 WORKING 모드인지 확인
     * 3. 드론이 웨이라인/수동/자동 이륙 모드인지 확인
     * 4. Redis에서 일시정지/실행 중인 작업 정보 확인
     *
     * @param dockSn Dock 시리얼 번호
     * @return 웨이라인 작업 상태
     */
    public WaylineJobStatusEnum getWaylineState(String dockSn) {
        Optional<DeviceDTO> dockOpt = deviceRedisService.getDeviceOnline(dockSn);
        if (dockOpt.isEmpty() || !StringUtils.hasText(dockOpt.get().getChildDeviceSn())) {
            return WaylineJobStatusEnum.UNKNOWN;
        }
        Optional<OsdDock> dockOsdOpt = deviceRedisService.getDeviceOsd(dockSn, OsdDock.class);
        Optional<OsdDockDrone> deviceOsdOpt = deviceRedisService.getDeviceOsd(dockOpt.get().getChildDeviceSn(), OsdDockDrone.class);
        if (dockOsdOpt.isEmpty() || deviceOsdOpt.isEmpty() || DockModeCodeEnum.WORKING != dockOsdOpt.get().getModeCode()) {
            return WaylineJobStatusEnum.UNKNOWN;
        }

        OsdDockDrone osdDevice = deviceOsdOpt.get();
        if (DroneModeCodeEnum.WAYLINE == osdDevice.getModeCode()
                || DroneModeCodeEnum.MANUAL == osdDevice.getModeCode()
                || DroneModeCodeEnum.TAKEOFF_AUTO == osdDevice.getModeCode()) {
            if (StringUtils.hasText(waylineRedisService.getPausedWaylineJobId(dockSn))) {
                return WaylineJobStatusEnum.PAUSED;
            }
            if (waylineRedisService.getRunningWaylineJob(dockSn).isPresent()) {
                return WaylineJobStatusEnum.IN_PROGRESS;
            }
        }
        return WaylineJobStatusEnum.UNKNOWN;
    }

    /**
     * 웨이라인 작업 엔티티를 DTO로 변환
     *
     * WaylineJobEntity를 WaylineJobDTO로 변환하여
     * API 응답에 적합한 형태로 만듭니다.
     *
     * 변환 내용:
     * - Unix timestamp를 LocalDateTime으로 변환
     * - 정수값을 Enum 타입으로 변환
     * - 웨이라인 파일명과 Dock명 조회
     * - Redis에서 실시간 진행률 정보 조회
     * - 미디어 파일 업로드 진행률 동기화
     *
     * @param entity 웨이라인 작업 엔티티
     * @return 웨이라인 작업 DTO
     */
    private WaylineJobDTO entity2Dto(WaylineJobEntity entity) {
        if (entity == null) {
            return null;
        }

        WaylineJobDTO.WaylineJobDTOBuilder builder = WaylineJobDTO.builder()
                .jobId(entity.getJobId())
                .jobName(entity.getName())
                .fileId(entity.getFileId())
                .fileName(waylineFileService.getWaylineByWaylineId(entity.getWorkspaceId(), entity.getFileId())
                        .orElse(new GetWaylineListResponse()).getName())
                .dockSn(entity.getDockSn())
                .dockName(deviceService.getDeviceBySn(entity.getDockSn())
                        .orElse(DeviceDTO.builder().build()).getNickname())
                .username(entity.getUsername())
                .workspaceId(entity.getWorkspaceId())
                .status(WaylineJobStatusEnum.IN_PROGRESS.getVal() == entity.getStatus() &&
                        entity.getJobId().equals(waylineRedisService.getPausedWaylineJobId(entity.getDockSn())) ?
                                WaylineJobStatusEnum.PAUSED.getVal() : entity.getStatus())
                .code(entity.getErrorCode())
                .beginTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getBeginTime()), ZoneId.systemDefault()))
                .endTime(Objects.nonNull(entity.getEndTime()) ?
                        LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getEndTime()), ZoneId.systemDefault()) : null)
                .executeTime(Objects.nonNull(entity.getExecuteTime()) ?
                        LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getExecuteTime()), ZoneId.systemDefault()) : null)
                .completedTime(WaylineJobStatusEnum.find(entity.getStatus()).getEnd() ?
                        LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getUpdateTime()), ZoneId.systemDefault()) : null)
                .taskType(TaskTypeEnum.find(entity.getTaskType()))
                .waylineType(WaylineTypeEnum.find(entity.getWaylineType()))
                .rthAltitude(entity.getRthAltitude())
                .outOfControlAction(OutOfControlActionEnum.find(entity.getOutOfControlAction()))
                .mediaCount(entity.getMediaCount());

        if (Objects.nonNull(entity.getEndTime())) {
            builder.endTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getEndTime()), ZoneId.systemDefault()));
        }
        if (WaylineJobStatusEnum.IN_PROGRESS.getVal() == entity.getStatus()) {
            builder.progress(waylineRedisService.getRunningWaylineJob(entity.getDockSn())
                    .map(EventsReceiver::getOutput)
                    .map(FlighttaskProgress::getProgress)
                    .map(FlighttaskProgressData::getPercent)
                    .orElse(null));
        }

        if (entity.getMediaCount() == 0) {
            return builder.build();
        }

        // 미디어 파일 개수 동기화
        String key = RedisConst.MEDIA_HIGHEST_PRIORITY_PREFIX + entity.getDockSn();
        String countKey = RedisConst.MEDIA_FILE_PREFIX + entity.getDockSn();
        Object mediaFileCount = RedisOpsUtils.hashGet(countKey, entity.getJobId());
        if (Objects.nonNull(mediaFileCount)) {
            builder.uploadedCount(((MediaFileCountDTO) mediaFileCount).getUploadedCount())
                    .uploading(RedisOpsUtils.checkExist(key) && entity.getJobId().equals(((MediaFileCountDTO)RedisOpsUtils.get(key)).getJobId()));
            return builder.build();
        }

        int uploadedSize = fileService.getFilesByWorkspaceAndJobId(entity.getWorkspaceId(), entity.getJobId()).size();
        // 이 작업의 모든 미디어가 업로드됨
        if (uploadedSize >= entity.getMediaCount()) {
            return builder.uploadedCount(uploadedSize).build();
        }
        RedisOpsUtils.hashSet(countKey, entity.getJobId(),
                MediaFileCountDTO.builder()
                        .jobId(entity.getJobId())
                        .mediaCount(entity.getMediaCount())
                        .uploadedCount(uploadedSize).build());
        return builder.build();
    }
}
