package com.dji.sample.map.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dji.sample.component.oss.model.OssConfiguration;
import com.dji.sample.component.oss.service.impl.OssServiceContext;
import com.dji.sample.map.dao.IFlightAreaFileMapper;
import com.dji.sample.map.model.dto.FlightAreaDTO;
import com.dji.sample.map.model.dto.FlightAreaFileDTO;
import com.dji.sample.map.model.entity.FlightAreaFileEntity;
import com.dji.sample.map.service.IFlightAreaFileService;
import com.dji.sample.map.service.IFlightAreaPropertyServices;
import com.dji.sample.map.service.IGroupService;
import com.dji.sample.map.service.IWorkspaceElementService;
import com.dji.sdk.cloudapi.flightarea.*;
import com.dji.sdk.cloudapi.map.ElementCircleGeometry;
import com.dji.sdk.cloudapi.map.ElementPointGeometry;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 비행 영역 파일 서비스 구현체
 * 비행 영역 파일의 생성, 저장, 조회를 관리하는 서비스입니다.
 * @author sean
 * @version 1.9
 * @date 2023/11/22
 */
@Service
@Transactional
public class FlightAreaFileServiceImpl implements IFlightAreaFileService {

    @Autowired
    private IFlightAreaFileMapper mapper;

    @Autowired
    private IWorkspaceElementService workspaceElementService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private OssServiceContext ossServiceContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IFlightAreaPropertyServices flightAreaPropertyServices;

    /**
     * 파일 ID로 비행 영역 파일을 조회합니다.
     * @param fileId 파일 ID
     * @return 비행 영역 파일 정보
     */
    @Override
    public Optional<FlightAreaFileDTO> getFlightAreaFileByFileId(String fileId) {
        return Optional.ofNullable(mapper.selectOne(Wrappers.lambdaQuery(FlightAreaFileEntity.class)
                        .eq(FlightAreaFileEntity::getFileId, fileId)))
                .map(this::entity2Dto);
    }

    /**
     * 비행 영역 파일을 저장합니다.
     * @param file 비행 영역 파일 정보
     * @return 저장된 파일의 ID
     */
    @Override
    public Integer saveFlightAreaFile(FlightAreaFileDTO file) {
        FlightAreaFileEntity entity = dto2Entity(file);
        int id = mapper.insert(entity);
        return id > 0 ? entity.getId() : id;
    }

    /**
     * 워크스페이스 ID로 최신 파일을 최신이 아닌 상태로 설정합니다.
     * @param workspaceId 워크스페이스 ID
     * @return 업데이트된 레코드 수
     */
    @Override
    public Integer setNonLatestByWorkspaceId(String workspaceId) {
        return mapper.update(FlightAreaFileEntity.builder().latest(false).build(),
                Wrappers.lambdaUpdate(FlightAreaFileEntity.class)
                        .eq(FlightAreaFileEntity::getWorkspaceId, workspaceId)
                        .eq(FlightAreaFileEntity::getLatest, true));
    }

    /**
     * 워크스페이스 ID로 최신 비행 영역 파일을 조회합니다.
     * @param workspaceId 워크스페이스 ID
     * @return 최신 비행 영역 파일 정보
     */
    @Override
    public Optional<FlightAreaFileDTO> getLatestByWorkspaceId(String workspaceId) {
        return Optional.ofNullable(mapper.selectOne(Wrappers.lambdaQuery(FlightAreaFileEntity.class)
                        .eq(FlightAreaFileEntity::getWorkspaceId, workspaceId)
                        .eq(FlightAreaFileEntity::getLatest, true)
                        .orderByDesc(FlightAreaFileEntity::getUpdateTime)
                        .last(" limit 1")))
                .map(this::entity2Dto);
    }

    /**
     * 비행 영역 파일을 패키징합니다.
     * @param workspaceId 워크스페이스 ID
     * @param flightAreas 비행 영역 목록
     * @return 비행 영역 파일 정보
     */
    @Override
    public FlightAreaFileDTO packageFlightAreaFile(String workspaceId, List<FlightAreaDTO> flightAreas) {
        Optional<FlightAreaFileDTO> fileOpt = getLatestByWorkspaceId(workspaceId);
        if (fileOpt.isPresent()) {
            return fileOpt.get();
        }
        FlightAreaFileDTO file = generateFlightAreaFile(workspaceId, flightAreas);
        int id = saveFlightAreaFile(file);
        if (id <= 0) {
            throw new RuntimeException("Failed to save the flight area file.");
        }
        return file;
    }

    /**
     * 비행 영역 파일을 생성합니다.
     * @param workspaceId 워크스페이스 ID
     * @param flightAreas 비행 영역 목록
     * @return 생성된 비행 영역 파일 정보
     */
    private FlightAreaFileDTO generateFlightAreaFile(String workspaceId, List<FlightAreaDTO> flightAreas) {

        FlightAreaJson flightAreaJson = new FlightAreaJson()
                .setFeatures(flightAreas.stream()
                        .map(this::generateFlightAreaFeature)
                        .collect(Collectors.toList()));
        try (ByteArrayOutputStream os = new ByteArrayOutputStream(64);
             JsonGenerator generator = objectMapper.createGenerator(os);) {
            generator.writePOJO(flightAreaJson);
            try (ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray())) {
                String name = String.format("geofence_%s.json", org.springframework.util.DigestUtils.md5DigestAsHex(is));
                is.reset();
                String objectKey = OssConfiguration.objectDirPrefix + "/" + name;
                ossServiceContext.putObject(OssConfiguration.bucket, objectKey, is);
                return FlightAreaFileDTO.builder()
                        .name(name)
                        .objectKey(objectKey)
                        .fileId(UUID.randomUUID().toString())
                        .size(os.size())
                        .workspaceId(workspaceId)
                        .sign(DigestUtils.sha256Hex(os.toByteArray()))
                        .latest(true)
                        .build();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 비행 영역 특성을 생성합니다.
     * @param area 비행 영역 정보
     * @return 비행 영역 특성
     */
    private FlightAreaFeature generateFlightAreaFeature(FlightAreaDTO area) {
        GeometrySubTypeEnum subType = null;
        Float radius = 0f;
        if (area.getContent().getGeometry() instanceof ElementCircleGeometry) {
            ElementCircleGeometry geometry = (ElementCircleGeometry) area.getContent().getGeometry();
            subType = GeometrySubTypeEnum.CIRCLE;
            radius = geometry.getRadius();
            area.getContent().setGeometry(new ElementPointGeometry().setCoordinates(geometry.getCoordinates()));
        }
        return new FlightAreaFeature()
                .setGeofenceType(area.getType())
                .setId(area.getAreaId())
                .setProperties(new FeatureProperty()
                        .setSubType(subType)
                        .setRadius(radius)
                        .setEnable(area.getStatus()))
                .setGeometry(objectMapper.convertValue(area.getContent().getGeometry(), FlightAreaGeometry.class));
    }

    /**
     * 엔티티를 DTO로 변환합니다.
     * @param entity 비행 영역 파일 엔티티
     * @return 비행 영역 파일 DTO
     */
    private FlightAreaFileDTO entity2Dto(FlightAreaFileEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        return FlightAreaFileDTO.builder()
                .fileId(entity.getFileId())
                .name(entity.getName())
                .objectKey(entity.getObjectKey())
                .sign(entity.getSign())
                .size(entity.getSize())
                .workspaceId(entity.getWorkspaceId())
                .latest(entity.getLatest())
                .build();
    }

    /**
     * DTO를 엔티티로 변환합니다.
     * @param dto 비행 영역 파일 DTO
     * @return 비행 영역 파일 엔티티
     */
    private FlightAreaFileEntity dto2Entity(FlightAreaFileDTO dto) {
        if (dto == null) {
            return null;
        }
        return FlightAreaFileEntity.builder()
                .fileId(dto.getFileId())
                .size(dto.getSize())
                .name(dto.getName())
                .sign(dto.getSign())
                .objectKey(dto.getObjectKey())
                .workspaceId(dto.getWorkspaceId())
                .latest(dto.getLatest())
                .build();
    }
}
