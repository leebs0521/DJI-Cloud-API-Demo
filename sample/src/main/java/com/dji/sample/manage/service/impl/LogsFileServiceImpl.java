package com.dji.sample.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dji.sample.component.oss.model.OssConfiguration;
import com.dji.sample.component.oss.service.impl.OssServiceContext;
import com.dji.sample.manage.dao.ILogsFileMapper;
import com.dji.sample.manage.model.dto.LogsFileDTO;
import com.dji.sample.manage.model.dto.LogsFileUploadDTO;
import com.dji.sample.manage.model.entity.LogsFileEntity;
import com.dji.sample.manage.service.ILogsFileIndexService;
import com.dji.sample.manage.service.ILogsFileService;
import com.dji.sdk.cloudapi.log.FileUploadProgressFile;
import com.dji.sdk.cloudapi.log.FileUploadStartFile;
import com.dji.sdk.cloudapi.log.FileUploadStatusEnum;
import com.dji.sdk.cloudapi.log.LogFileIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 로그 파일 관리 서비스 구현체
 * 
 * DJI Cloud API의 로그 파일 관리를 위한 서비스 구현체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 구현합니다:
 * 
 * 1. 로그 파일 정보 관리
 *    - 로그 파일 정보 저장 및 조회
 *    - 로그 ID별 파일 목록 관리
 *    - 파일 메타데이터 관리
 *    - 파일 상태 추적
 * 
 * 2. 로그 파일 업로드 관리
 *    - 로그 파일 업로드 시작 처리
 *    - 업로드 진행률 업데이트
 *    - 업로드 완료 상태 관리
 *    - 업로드 실패 처리
 * 
 * 3. OSS 스토리지 연동
 *    - 로그 파일 OSS 업로드
 *    - OSS 파일 URL 생성
 *    - OSS 파일 삭제
 *    - 스토리지 정리
 * 
 * 4. 로그 파일 인덱스 관리
 *    - 로그 파일 인덱스 생성
 *    - 인덱스 정보 조회
 *    - 인덱스 삭제
 *    - 인덱스 업데이트
 * 
 * 5. 데이터 변환 및 매핑
 *    - 엔티티를 DTO로 변환
 *    - 업로드 진행 파일을 엔티티로 변환
 *    - 파일 메타데이터 매핑
 *    - 데이터 유효성 검증
 * 
 * 주요 의존성:
 * - ILogsFileMapper: 로그 파일 데이터베이스 접근
 * - ILogsFileIndexService: 로그 파일 인덱스 관리
 * - OssServiceContext: OSS 스토리지 서비스
 * - FileUploadStartFile: 파일 업로드 시작 정보
 * - FileUploadProgressFile: 파일 업로드 진행 정보
 * 
 * 이 클래스는 DJI 로그 파일을 OSS 스토리지에
 * 안전하고 효율적으로 관리하는 서비스입니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
@Service
@Transactional
public class LogsFileServiceImpl implements ILogsFileService {

    @Autowired
    private ILogsFileMapper mapper;

    @Autowired
    private ILogsFileIndexService logsFileIndexService;

    @Autowired
    private OssServiceContext ossService;

    @Autowired
    private OssServiceContext ossServiceContext;

    /**
     * 로그 ID로 로그 파일 정보를 조회합니다.
     * 
     * @param logsId 로그 ID
     * @return 로그 파일 DTO 목록
     */
    @Override
    public List<LogsFileDTO> getLogsFileInfoByLogsId(String logsId) {
        return mapper.selectList(
                new LambdaQueryWrapper<LogsFileEntity>()
                        .eq(LogsFileEntity::getLogsId, logsId)).stream()
                .map(this::entity2Dto).collect(Collectors.toList());
    }

    /**
     * 로그 파일 엔티티를 DTO로 변환합니다.
     * 
     * @param entity 로그 파일 엔티티
     * @return 로그 파일 DTO
     */
    private LogsFileDTO entity2Dto(LogsFileEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        return LogsFileDTO.builder()
                .deviceSn(entity.getDeviceSn())
                .fileId(entity.getFileId())
                .fingerprint(entity.getFingerprint())
                .logsId(entity.getLogsId())
                .name(entity.getName())
                .objectKey(entity.getObjectKey())
                .size(entity.getSize())
                .status(entity.getStatus())
                .build();
    }

    /**
     * 로그 ID로 로그 파일을 조회합니다.
     * 
     * @param logsId 로그 ID
     * @return 로그 파일 업로드 DTO 목록
     */
    @Override
    public List<LogsFileUploadDTO> getLogsFileByLogsId(String logsId) {
        List<LogsFileDTO> logsFiles = this.getLogsFileInfoByLogsId(logsId);
        if (CollectionUtils.isEmpty(logsFiles)) {
            return new ArrayList<>();
        }
        return logsFileIndexService.getFileIndexByFileIds(logsFiles);
    }

    /**
     * 로그 파일을 삽입합니다.
     * 
     * @param file 파일 업로드 시작 정보
     * @param logsId 로그 ID
     * @return 삽입 성공 여부
     */
    @Override
    public Boolean insertFile(FileUploadStartFile file, String logsId) {
        LogsFileEntity entity = LogsFileEntity.builder()
                .logsId(logsId)
                .fileId(UUID.randomUUID().toString())
                .objectKey(file.getObjectKey())
                .status(false)
                .deviceSn(file.getDeviceSn())
                .build();
        boolean insert = mapper.insert(entity) > 0;
        if (!insert) {
            return false;
        }
        // 로그 파일 인덱스 삽입
        for (LogFileIndex logsFile : file.getList()) {
            insert = logsFileIndexService.insertFileIndex(logsFile, file.getDeviceSn(), Integer.valueOf(file.getModule().getDomain()), entity.getFileId());
            if (!insert) {
                return false;
            }
        }
        return true;
    }

    /**
     * 로그 ID로 파일을 삭제합니다.
     * 
     * @param logsId 로그 ID
     */
    @Override
    public void deleteFileByLogsId(String logsId) {
        List<LogsFileDTO> logsFiles = this.getLogsFileInfoByLogsId(logsId);
        if (CollectionUtils.isEmpty(logsFiles)) {
            return;
        }
        mapper.delete(new LambdaUpdateWrapper<LogsFileEntity>().eq(LogsFileEntity::getLogsId, logsId));
        List<String> fileIds = new ArrayList<>();
        logsFiles.forEach(file -> {
            // 업로드 완료된 파일은 OSS에서도 삭제
            if (file.getStatus()) {
                ossService.deleteObject(OssConfiguration.bucket, file.getObjectKey());
            }
            fileIds.add(file.getFileId());
        });

        logsFileIndexService.deleteFileIndexByFileIds(fileIds);
    }

    /**
     * 로그 파일을 업데이트합니다.
     * 
     * @param logsId 로그 ID
     * @param fileReceiver 파일 업로드 진행 정보
     */
    @Override
    public void updateFile(String logsId, FileUploadProgressFile fileReceiver) {
        List<LogsFileDTO> logsFiles = this.getLogsFileInfoByLogsId(logsId);
        if (CollectionUtils.isEmpty(logsFiles)) {
            return;
        }
        mapper.update(receiver2Entity(fileReceiver),
                new LambdaUpdateWrapper<LogsFileEntity>().eq(LogsFileEntity::getLogsId, logsId)
                        .eq(LogsFileEntity::getDeviceSn, fileReceiver.getDeviceSn()));
    }

    /**
     * 로그 파일 업로드 상태를 업데이트합니다.
     * 
     * @param logsId 로그 ID
     * @param isUploaded 업로드 완료 여부
     */
    @Override
    public void updateFileUploadStatus(String logsId, Boolean isUploaded) {
        mapper.update(LogsFileEntity.builder().status(isUploaded).build(),
                new LambdaUpdateWrapper<LogsFileEntity>().eq(LogsFileEntity::getLogsId, logsId));
    }

    /**
     * 로그 파일 URL을 조회합니다.
     * 
     * @param logsId 로그 ID
     * @param fileId 파일 ID
     * @return 로그 파일 URL
     */
    @Override
    public URL getLogsFileUrl(String logsId, String fileId) {
        LogsFileEntity logsFile = mapper.selectOne(new LambdaQueryWrapper<LogsFileEntity>()
                .eq(LogsFileEntity::getLogsId, logsId).eq(LogsFileEntity::getFileId, fileId));
        if (Objects.isNull(logsFile)) {
            return null;
        }
        return ossService.getObjectUrl(OssConfiguration.bucket, logsFile.getObjectKey());
    }

    /**
     * 파일 업로드 진행 정보를 엔티티로 변환합니다.
     * 
     * @param receiver 파일 업로드 진행 정보
     * @return 로그 파일 엔티티
     */
    private LogsFileEntity receiver2Entity(FileUploadProgressFile receiver) {
        if (Objects.isNull(receiver)) {
            return null;
        }
        return LogsFileEntity.builder()
                .fingerprint(receiver.getFingerprint())
                .size(receiver.getSize())
                .status(Objects.nonNull(receiver.getProgress())
                        && FileUploadStatusEnum.OK == receiver.getProgress().getStatus())
                .name(receiver.getKey().substring(receiver.getKey().lastIndexOf("/") + 1)).build();
    }
}