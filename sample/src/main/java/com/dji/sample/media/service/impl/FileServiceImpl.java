package com.dji.sample.media.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dji.sample.component.oss.model.OssConfiguration;
import com.dji.sample.component.oss.service.impl.OssServiceContext;
import com.dji.sample.manage.model.dto.DeviceDictionaryDTO;
import com.dji.sample.manage.service.IDeviceDictionaryService;
import com.dji.sample.media.dao.IFileMapper;
import com.dji.sample.media.model.MediaFileDTO;
import com.dji.sample.media.model.MediaFileEntity;
import com.dji.sample.media.service.IFileService;
import com.dji.sdk.cloudapi.device.DeviceEnum;
import com.dji.sdk.cloudapi.media.MediaSubFileTypeEnum;
import com.dji.sdk.cloudapi.media.MediaUploadCallbackRequest;
import com.dji.sdk.common.Pagination;
import com.dji.sdk.common.PaginationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 파일 서비스 구현체
 * 미디어 파일의 데이터베이스 조작과 관련된 비즈니스 로직을 구현하는 서비스 클래스입니다.
 * 파일의 저장, 조회, 중복 검사, 다운로드 URL 생성 등의 기능을 제공하며,
 * MyBatis-Plus를 사용하여 데이터베이스 접근을 처리합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/9
 */
@Service
@Transactional
public class FileServiceImpl implements IFileService {

    @Autowired
    private IFileMapper mapper;

    @Autowired
    private IDeviceDictionaryService deviceDictionaryService;

    @Autowired
    private OssServiceContext ossService;

    /**
     * 워크스페이스 ID와 파일 지문을 기반으로 미디어 파일 엔티티를 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @param fingerprint 파일 지문
     * @return 미디어 파일 엔티티 (Optional)
     */
    private Optional<MediaFileEntity> getMediaByFingerprint(String workspaceId, String fingerprint) {
        MediaFileEntity fileEntity = mapper.selectOne(new LambdaQueryWrapper<MediaFileEntity>()
                .eq(MediaFileEntity::getWorkspaceId, workspaceId)
                .eq(MediaFileEntity::getFingerprint, fingerprint));
        return Optional.ofNullable(fileEntity);
    }

    /**
     * 워크스페이스 ID와 파일 ID를 기반으로 미디어 파일 엔티티를 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @param fileId 파일 ID
     * @return 미디어 파일 엔티티 (Optional)
     */
    private Optional<MediaFileEntity> getMediaByFileId(String workspaceId, String fileId) {
        MediaFileEntity fileEntity = mapper.selectOne(new LambdaQueryWrapper<MediaFileEntity>()
                .eq(MediaFileEntity::getWorkspaceId, workspaceId)
                .eq(MediaFileEntity::getFileId, fileId));
        return Optional.ofNullable(fileEntity);
    }

    /**
     * 워크스페이스 ID와 파일의 지문(fingerprint)을 기반으로 파일이 이미 존재하는지 확인합니다.
     * @param workspaceId 워크스페이스 ID
     * @param fingerprint 파일 지문 (파일 내용의 해시 값)
     * @return 파일 존재 여부 (true: 존재함, false: 존재하지 않음)
     */
    @Override
    public Boolean checkExist(String workspaceId, String fingerprint) {
        return this.getMediaByFingerprint(workspaceId, fingerprint).isPresent();
    }

    /**
     * 파일의 기본 정보를 데이터베이스에 저장합니다.
     * @param workspaceId 워크스페이스 ID
     * @param file 미디어 업로드 콜백 요청 객체
     * @return 저장된 레코드의 ID
     */
    @Override
    public Integer saveFile(String workspaceId, MediaUploadCallbackRequest file) {
        MediaFileEntity fileEntity = this.fileUploadConvertToEntity(file);
        fileEntity.setWorkspaceId(workspaceId);
        fileEntity.setFileId(UUID.randomUUID().toString());
        return mapper.insert(fileEntity);
    }

    /**
     * 워크스페이스 ID를 기반으로 해당 워크스페이스의 모든 파일 정보를 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @return 미디어 파일 DTO 목록
     */
    @Override
    public List<MediaFileDTO> getAllFilesByWorkspaceId(String workspaceId) {
        return mapper.selectList(new LambdaQueryWrapper<MediaFileEntity>()
                .eq(MediaFileEntity::getWorkspaceId, workspaceId))
                .stream()
                .map(this::entityConvertToDto)
                .collect(Collectors.toList());
    }

    /**
     * 워크스페이스의 모든 미디어 파일을 페이징 처리하여 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @param page 페이지 번호
     * @param pageSize 페이지 크기
     * @return 페이징 처리된 미디어 파일 데이터
     */
    @Override
    public PaginationData<MediaFileDTO> getMediaFilesPaginationByWorkspaceId(String workspaceId, long page, long pageSize) {
        Page<MediaFileEntity> pageData = mapper.selectPage(
                new Page<MediaFileEntity>(page, pageSize),
                new LambdaQueryWrapper<MediaFileEntity>()
                        .eq(MediaFileEntity::getWorkspaceId, workspaceId)
                        .orderByDesc(MediaFileEntity::getId));
        List<MediaFileDTO> records = pageData.getRecords()
                .stream()
                .map(this::entityConvertToDto)
                .collect(Collectors.toList());

        return new PaginationData<MediaFileDTO>(records, new Pagination(pageData.getCurrent(), pageData.getSize(), pageData.getTotal()));
    }

    /**
     * 파일의 다운로드 주소를 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @param fileId 파일 ID
     * @return 파일 다운로드 URL
     */
    @Override
    public URL getObjectUrl(String workspaceId, String fileId) {
        Optional<MediaFileEntity> mediaFileOpt = getMediaByFileId(workspaceId, fileId);
        if (mediaFileOpt.isEmpty()) {
            throw new IllegalArgumentException("{} doesn't exist.");
        }

        return ossService.getObjectUrl(OssConfiguration.bucket, mediaFileOpt.get().getObjectKey());
    }

    /**
     * 특정 작업의 모든 미디어 파일을 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @param jobId 작업 ID
     * @return 해당 작업의 미디어 파일 DTO 목록
     */
    @Override
    public List<MediaFileDTO> getFilesByWorkspaceAndJobId(String workspaceId, String jobId) {
        return mapper.selectList(new LambdaQueryWrapper<MediaFileEntity>()
                .eq(MediaFileEntity::getWorkspaceId, workspaceId)
                .eq(MediaFileEntity::getJobId, jobId))
                .stream()
                .map(this::entityConvertToDto).collect(Collectors.toList());
    }

    /**
     * 받은 파일 객체를 데이터베이스 엔티티 객체로 변환합니다.
     * 업로드된 파일의 메타데이터를 데이터베이스에 저장하기 위한 형태로 변환합니다.
     * @param file 미디어 업로드 콜백 요청 객체
     * @return 미디어 파일 엔티티
     */
    private MediaFileEntity fileUploadConvertToEntity(MediaUploadCallbackRequest file) {
        MediaFileEntity.MediaFileEntityBuilder builder = MediaFileEntity.builder();

        if (file != null) {
            builder.fileName(file.getName())
                    .filePath(file.getPath())
                    .fingerprint(file.getFingerprint())
                    .objectKey(file.getObjectKey())
                    .subFileType(Optional.ofNullable(file.getSubFileType()).map(MediaSubFileTypeEnum::getType).orElse(null))
                    .isOriginal(file.getExt().getOriginal())
                    .jobId(file.getExt().getFileGroupId())
                    .drone(file.getExt().getSn())
                    .tinnyFingerprint(file.getExt().getTinnyFingerprint())
                    .payload(file.getExt().getPayloadModelKey().getDevice());

            // domain-type-subType
            DeviceEnum payloadModelKey = file.getExt().getPayloadModelKey();
            Optional<DeviceDictionaryDTO> payloadDict = deviceDictionaryService
                    .getOneDictionaryInfoByTypeSubType(payloadModelKey.getDomain().getDomain(),
                            payloadModelKey.getType().getType(), payloadModelKey.getSubType().getSubType());
            payloadDict.ifPresent(payload -> builder.payload(payload.getDeviceName()));
        }
        return builder.build();
    }

    /**
     * 데이터베이스 엔티티 객체를 파일 데이터 전송 객체로 변환합니다.
     * 데이터베이스에서 조회한 엔티티를 클라이언트에 전송하기 위한 DTO로 변환합니다.
     * @param entity 미디어 파일 엔티티
     * @return 미디어 파일 DTO
     */
    private MediaFileDTO entityConvertToDto(MediaFileEntity entity) {
        MediaFileDTO.MediaFileDTOBuilder builder = MediaFileDTO.builder();

        if (entity != null) {
            builder.fileName(entity.getFileName())
                    .fileId(entity.getFileId())
                    .filePath(entity.getFilePath())
                    .isOriginal(entity.getIsOriginal())
                    .fingerprint(entity.getFingerprint())
                    .objectKey(entity.getObjectKey())
                    .tinnyFingerprint(entity.getTinnyFingerprint())
                    .payload(entity.getPayload())
                    .createTime(LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(entity.getCreateTime()), ZoneId.systemDefault()))
                    .drone(entity.getDrone())
                    .jobId(entity.getJobId());

        }

        return builder.build();
    }

}
