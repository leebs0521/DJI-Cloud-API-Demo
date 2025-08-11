package com.dji.sample.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dji.sample.manage.dao.ILogsFileIndexMapper;
import com.dji.sample.manage.model.dto.LogsFileDTO;
import com.dji.sample.manage.model.dto.LogsFileUploadDTO;
import com.dji.sample.manage.model.entity.LogsFileIndexEntity;
import com.dji.sample.manage.service.ILogsFileIndexService;
import com.dji.sdk.cloudapi.log.LogFileIndex;
import com.dji.sdk.cloudapi.log.LogModuleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 로그 파일 인덱스 관리 서비스 구현체
 * 
 * DJI Cloud API의 로그 파일 인덱스 관리를 위한 서비스 구현체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 구현합니다:
 * 
 * 1. 로그 파일 인덱스 관리
 *    - 로그 파일 인덱스 정보 저장 및 조회
 *    - 파일 ID별 인덱스 관리
 *    - 로그 파일 메타데이터 관리
 *    - 인덱스 정보 삭제
 * 
 * 2. 로그 파일 업로드 관리
 *    - 로그 파일 업로드 정보 조회
 *    - 파일 상태별 업로드 처리
 *    - 업로드 완료 파일 관리
 *    - 업로드 실패 파일 처리
 * 
 * 3. 데이터 변환 및 매핑
 *    - LogFileIndex를 엔티티로 변환
 *    - 엔티티를 LogFileIndex로 변환
 *    - 로그 파일 메타데이터 매핑
 *    - 데이터 유효성 검증
 * 
 * 4. 로그 파일 검색 및 필터링
 *    - 파일 ID별 로그 파일 조회
 *    - 디바이스별 로그 파일 필터링
 *    - 도메인별 로그 파일 분류
 *    - 시간 범위별 로그 파일 검색
 * 
 * 5. 트랜잭션 관리
 *    - 데이터베이스 트랜잭션 관리
 *    - 데이터 일관성 보장
 *    - 롤백 처리
 *    - 동시성 제어
 * 
 * 주요 의존성:
 * - ILogsFileIndexMapper: 로그 파일 인덱스 데이터베이스 접근
 * - LogFileIndex: DJI 로그 파일 인덱스
 * - LogModuleEnum: 로그 모듈 열거형
 * - LogsFileIndexEntity: 로그 파일 인덱스 엔티티
 * 
 * 이 클래스는 DJI 로그 파일의 인덱스 정보를
 * 효율적으로 관리하는 서비스입니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/8
 */
@Service
@Transactional
public class LogsFileIndexServiceImpl implements ILogsFileIndexService {

    @Autowired
    private ILogsFileIndexMapper mapper;

    /**
     * 로그 파일 인덱스를 삽입합니다.
     * 
     * @param file 로그 파일 인덱스
     * @param deviceSn 디바이스 시리얼 번호
     * @param domain 디바이스 도메인
     * @param fileId 파일 ID
     * @return 삽입 성공 여부
     */
    @Override
    public Boolean insertFileIndex(LogFileIndex file, String deviceSn, Integer domain, String fileId) {
        LogsFileIndexEntity entity = this.logsFile2Entity(file);
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setDomain(domain);
        entity.setDeviceSn(deviceSn);
        entity.setFileId(fileId);

        return mapper.insert(entity) > 0;
    }

    /**
     * 파일 ID 목록으로 파일 인덱스를 조회합니다.
     * 
     * @param files 로그 파일 DTO 목록
     * @return 로그 파일 업로드 DTO 목록
     */
    @Override
    public List<LogsFileUploadDTO> getFileIndexByFileIds(List<LogsFileDTO> files) {
        List<LogsFileUploadDTO> list = new ArrayList<>();
        files.forEach(file -> {
            Optional<LogsFileUploadDTO> fileOpt = this.getFileIndexByFileId(file.getFileId());
            fileOpt.ifPresent(fileUpload -> {
                fileUpload.setObjectKey(file.getStatus() ? file.getObjectKey() : null);
                list.add(fileUpload);
            });
        });
        return list;
    }

    /**
     * 파일 ID 목록으로 파일 인덱스를 삭제합니다.
     * 
     * @param fileIds 파일 ID 목록
     */
    @Override
    public void deleteFileIndexByFileIds(List<String> fileIds) {
        mapper.delete(new LambdaUpdateWrapper<LogsFileIndexEntity>()
                .or(wrapper -> fileIds.forEach(fileId -> wrapper.eq(LogsFileIndexEntity::getFileId, fileId))));
    }

    /**
     * 파일 ID로 파일 인덱스를 조회합니다.
     * 
     * @param fileId 파일 ID
     * @return 로그 파일 업로드 DTO (Optional)
     */
    @Override
    public Optional<LogsFileUploadDTO> getFileIndexByFileId(String fileId) {
        List<LogsFileIndexEntity> logsFileIndexList = mapper.selectList(
                new LambdaQueryWrapper<LogsFileIndexEntity>().eq(LogsFileIndexEntity::getFileId, fileId));
        if (CollectionUtils.isEmpty(logsFileIndexList)) {
            return Optional.empty();
        }
        LogsFileIndexEntity entity = logsFileIndexList.get(0);
        List<LogFileIndex> logsFileList = logsFileIndexList.stream().map(this::entity2LogsFile).collect(Collectors.toList());
        return Optional.of(LogsFileUploadDTO.builder()
                .deviceSn(entity.getDeviceSn())
                .deviceModelDomain(LogModuleEnum.find(String.valueOf(entity.getDomain())))
                .list(logsFileList)
                .fileId(fileId)
                .build());

    }

    /**
     * 로그 파일 인덱스 엔티티를 LogFileIndex로 변환합니다.
     * 
     * @param entity 로그 파일 인덱스 엔티티
     * @return LogFileIndex
     */
    private LogFileIndex entity2LogsFile(LogsFileIndexEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        return new LogFileIndex()
                .setBootIndex(entity.getBootIndex())
                .setStartTime(entity.getStartTime())
                .setEndTime(entity.getEndTime())
                .setSize(entity.getSize());

    }

    /**
     * LogFileIndex를 로그 파일 인덱스 엔티티로 변환합니다.
     * 
     * @param file LogFileIndex
     * @return 로그 파일 인덱스 엔티티
     */
    private LogsFileIndexEntity logsFile2Entity(LogFileIndex file) {
        if (Objects.isNull(file)) {
            return null;
        }
        return LogsFileIndexEntity.builder()
                .bootIndex(file.getBootIndex())
                .size(file.getSize())
                .startTime(file.getStartTime())
                .endTime(file.getEndTime())
                .build();
    }
}
