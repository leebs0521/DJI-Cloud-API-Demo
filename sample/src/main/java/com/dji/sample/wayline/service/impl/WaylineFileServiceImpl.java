package com.dji.sample.wayline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dji.sample.component.oss.model.OssConfiguration;
import com.dji.sample.component.oss.service.impl.OssServiceContext;
import com.dji.sample.wayline.dao.IWaylineFileMapper;
import com.dji.sample.wayline.model.dto.KmzFileProperties;
import com.dji.sample.wayline.model.dto.WaylineFileDTO;
import com.dji.sample.wayline.model.entity.WaylineFileEntity;
import com.dji.sample.wayline.service.IWaylineFileService;
import com.dji.sdk.cloudapi.device.DeviceDomainEnum;
import com.dji.sdk.cloudapi.device.DeviceEnum;
import com.dji.sdk.cloudapi.device.DeviceSubTypeEnum;
import com.dji.sdk.cloudapi.device.DeviceTypeEnum;
import com.dji.sdk.cloudapi.wayline.GetWaylineListRequest;
import com.dji.sdk.cloudapi.wayline.GetWaylineListResponse;
import com.dji.sdk.cloudapi.wayline.WaylineTypeEnum;
import com.dji.sdk.common.Pagination;
import com.dji.sdk.common.PaginationData;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.dji.sample.wayline.model.dto.KmzFileProperties.WAYLINE_FILE_SUFFIX;

/**
 * DJI Cloud API 웨이라인 파일 서비스 구현 클래스
 *
 * 이 클래스는 IWaylineFileService 인터페이스의 실제 구현체로, 웨이라인 파일의
 * 전체 생명주기를 관리합니다. 웨이라인 파일의 업로드, 조회, 다운로드, 삭제,
 * 즐겨찾기 관리 등의 기능을 제공하며, KMZ 파일 형식 검증과 파싱을 수행합니다.
 *
 * 주요 기능:
 * - 웨이라인 파일 목록 조회 (페이징, 필터링, 정렬)
 * - 웨이라인 파일 상세 정보 조회
 * - 웨이라인 파일 업로드 및 KMZ 파일 검증
 * - 웨이라인 파일 다운로드 URL 생성
 * - 웨이라인 파일 삭제 (데이터베이스 + OSS)
 * - 웨이라인 파일 즐겨찾기 관리
 * - 중복 파일명 검사
 *
 * 파일 형식:
 * - KMZ (Zipped KML) 파일 형식 지원
 * - UTF-8 인코딩 필수
 * - WPML (Waypoint Markup Language) 구조 검증
 *
 * 의존성:
 * - IWaylineFileMapper: 데이터베이스 접근
 * - OssServiceContext: OSS 스토리지 관리
 *
 * @author sean
 * @version 0.3
 * @date 2021/12/22
 */
@Service
@Transactional
public class WaylineFileServiceImpl implements IWaylineFileService {

    /**
     * 웨이라인 파일 데이터베이스 접근 매퍼
     */
    @Autowired
    private IWaylineFileMapper mapper;

    /**
     * OSS 스토리지 서비스 컨텍스트
     */
    @Autowired
    private OssServiceContext ossService;

    /**
     * 웨이라인 파일 목록을 조건에 따라 조회
     *
     * 워크스페이스 ID와 다양한 필터 조건을 사용하여 웨이라인 파일 목록을
     * 페이징 처리하여 조회합니다. 즐겨찾기, 템플릿 타입, 페이로드 모델,
     * 드론 모델, 키워드 검색, 정렬 등의 조건을 지원합니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param param 조회 조건 (페이징, 필터링, 정렬)
     * @return 페이징된 웨이라인 파일 목록
     */
    @Override
    public PaginationData<GetWaylineListResponse> getWaylinesByParam(String workspaceId, GetWaylineListRequest param) {
        // 페이징 쿼리 실행
        Page<WaylineFileEntity> page = mapper.selectPage(
                new Page<WaylineFileEntity>(param.getPage(), param.getPageSize()),
                new LambdaQueryWrapper<WaylineFileEntity>()
                        .eq(WaylineFileEntity::getWorkspaceId, workspaceId)
                        .eq(Objects.nonNull(param.getFavorited()), WaylineFileEntity::getFavorited, param.getFavorited())
                        .and(param.getTemplateType() != null, wrapper ->  {
                                for (WaylineTypeEnum type : param.getTemplateType()) {
                                    wrapper.like(WaylineFileEntity::getTemplateTypes, type.getValue()).or();
                                }
                        })
                        .and(param.getPayloadModelKey() != null, wrapper ->  {
                                for (DeviceEnum type : param.getPayloadModelKey()) {
                                    wrapper.like(WaylineFileEntity::getPayloadModelKeys, type.getType()).or();
                                }
                        })
                        .and(param.getDroneModelKeys() != null, wrapper ->  {
                                for (DeviceEnum type : param.getDroneModelKeys()) {
                                    wrapper.eq(WaylineFileEntity::getDroneModelKey, type.getType()).or();
                                }
                        })
                        .like(Objects.nonNull(param.getKey()), WaylineFileEntity::getName, param.getKey())
                        // SQL 인젝션 위험이 있음
                        .last(Objects.nonNull(param.getOrderBy()), " order by " + param.getOrderBy().toString()));

        // 페이징 쿼리 결과를 커스텀 페이징 객체로 래핑
        List<GetWaylineListResponse> records = page.getRecords()
                .stream()
                .map(this::entityConvertToDTO)
                .collect(Collectors.toList());

        return new PaginationData<>(records, new Pagination(page.getCurrent(), page.getSize(), page.getTotal()));
    }

    /**
     * 웨이라인 ID로 특정 웨이라인 파일 정보 조회
     *
     * 워크스페이스 ID와 웨이라인 ID를 사용하여 특정 웨이라인 파일의
     * 상세 정보를 조회합니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param waylineId 웨이라인 ID
     * @return 웨이라인 파일 정보 (Optional)
     */
    @Override
    public Optional<GetWaylineListResponse> getWaylineByWaylineId(String workspaceId, String waylineId) {
        return Optional.ofNullable(
                this.entityConvertToDTO(
                        mapper.selectOne(
                                new LambdaQueryWrapper<WaylineFileEntity>()
                                    .eq(WaylineFileEntity::getWorkspaceId, workspaceId)
                                    .eq(WaylineFileEntity::getWaylineId, waylineId))));
    }

    /**
     * 웨이라인 파일 다운로드 URL 생성
     *
     * 웨이라인 ID를 사용하여 OSS에 저장된 웨이라인 파일의
     * 다운로드 URL을 생성합니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param waylineId 웨이라인 ID
     * @return 웨이라인 파일 다운로드 URL
     * @throws SQLException 웨이라인 파일이 존재하지 않는 경우
     */
    @Override
    public URL getObjectUrl(String workspaceId, String waylineId) throws SQLException {
        Optional<GetWaylineListResponse> waylineOpt = this.getWaylineByWaylineId(workspaceId, waylineId);
        if (waylineOpt.isEmpty()) {
            throw new SQLException(waylineId + " does not exist.");
        }
        return ossService.getObjectUrl(OssConfiguration.bucket, waylineOpt.get().getObjectKey());
    }

    /**
     * 웨이라인 파일 메타데이터를 데이터베이스에 저장
     *
     * 웨이라인 파일의 메타데이터를 데이터베이스에 저장합니다.
     * 파일 서명(MD5)이 없는 경우 OSS에서 파일을 읽어서 계산합니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param metadata 웨이라인 파일 메타데이터
     * @return 저장된 레코드의 ID
     */
    @Override
    public Integer saveWaylineFile(String workspaceId, WaylineFileDTO metadata) {
        WaylineFileEntity file = this.dtoConvertToEntity(metadata);
        file.setWaylineId(UUID.randomUUID().toString());
        file.setWorkspaceId(workspaceId);

        // 파일 서명이 없는 경우 OSS에서 파일을 읽어서 MD5 계산
        if (!StringUtils.hasText(file.getSign())) {
            try (InputStream object = ossService.getObject(OssConfiguration.bucket, metadata.getObjectKey())) {
                if (object.available() == 0) {
                    throw new RuntimeException("The file " + metadata.getObjectKey() +
                            " does not exist in the bucket[" + OssConfiguration.bucket + "].");
                }
                file.setSign(DigestUtils.md5DigestAsHex(object));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int insertId = mapper.insert(file);
        return insertId > 0 ? file.getId() : insertId;
    }

    /**
     * 웨이라인 파일 즐겨찾기 상태 변경
     *
     * 지정된 웨이라인 파일들의 즐겨찾기 상태를 일괄 변경합니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param waylineIds 웨이라인 ID 목록
     * @param isFavorite 즐겨찾기 여부 (null인 경우 변경하지 않음)
     * @return 업데이트 성공 여부
     */
    @Override
    public Boolean markFavorite(String workspaceId, List<String> waylineIds, Boolean isFavorite) {
        if (waylineIds.isEmpty()) {
            return false;
        }
        if (isFavorite == null) {
            return true;
        }
        return mapper.update(null, new LambdaUpdateWrapper<WaylineFileEntity>()
                .set(WaylineFileEntity::getFavorited, isFavorite)
                .eq(WaylineFileEntity::getWorkspaceId, workspaceId)
                .in(WaylineFileEntity::getWaylineId, waylineIds)) > 0;
    }

    /**
     * 중복 파일명 조회
     *
     * 워크스페이스 내에서 이미 존재하는 파일명들을 조회합니다.
     * 파일 업로드 시 중복 검사를 위해 사용됩니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param names 검사할 파일명 목록
     * @return 중복된 파일명 목록
     */
    @Override
    public List<String> getDuplicateNames(String workspaceId, List<String> names) {
        return mapper.selectList(new LambdaQueryWrapper<WaylineFileEntity>()
                .eq(WaylineFileEntity::getWorkspaceId, workspaceId)
                .in(WaylineFileEntity::getName, names))
                .stream()
                .map(WaylineFileEntity::getName)
                .collect(Collectors.toList());
    }

    /**
     * 웨이라인 파일 삭제
     *
     * 웨이라인 ID를 사용하여 웨이라인 파일을 완전히 삭제합니다.
     * 데이터베이스에서 메타데이터를 삭제하고 OSS에서 실제 파일도 삭제합니다.
     *
     * @param workspaceId 워크스페이스 ID
     * @param waylineId 웨이라인 ID
     * @return 삭제 성공 여부
     */
    @Override
    public Boolean deleteByWaylineId(String workspaceId, String waylineId) {
        Optional<GetWaylineListResponse> waylineOpt = this.getWaylineByWaylineId(workspaceId, waylineId);
        if (waylineOpt.isEmpty()) {
            return true;
        }
        GetWaylineListResponse wayline = waylineOpt.get();
        boolean isDel = mapper.delete(new LambdaUpdateWrapper<WaylineFileEntity>()
                    .eq(WaylineFileEntity::getWorkspaceId, workspaceId)
                    .eq(WaylineFileEntity::getWaylineId, waylineId))
                > 0;
        if (!isDel) {
            return false;
        }
        return ossService.deleteObject(OssConfiguration.bucket, wayline.getObjectKey());
    }

    /**
     * KMZ 파일 업로드 및 가져오기
     *
     * KMZ 파일을 업로드하고 검증한 후 웨이라인 파일로 가져옵니다.
     * 파일 형식 검증, WPML 파싱, 메타데이터 추출을 수행합니다.
     *
     * @param file 업로드된 KMZ 파일
     * @param workspaceId 워크스페이스 ID
     * @param creator 파일 생성자
     * @throws RuntimeException 파일 형식이 올바르지 않은 경우
     */
    @Override
    public void importKmzFile(MultipartFile file, String workspaceId, String creator) {
        Optional<WaylineFileDTO> waylineFileOpt = validKmzFile(file);
        if (waylineFileOpt.isEmpty()) {
            throw new RuntimeException("The file format is incorrect.");
        }

        try {
            WaylineFileDTO waylineFile = waylineFileOpt.get();
            waylineFile.setUsername(creator);

            // OSS에 파일 업로드
            ossService.putObject(OssConfiguration.bucket, waylineFile.getObjectKey(), file.getInputStream());
            // 데이터베이스에 메타데이터 저장
            this.saveWaylineFile(workspaceId, waylineFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * KMZ 파일 형식 검증 및 파싱
     *
     * 업로드된 KMZ 파일의 형식을 검증하고 WPML 구조를 파싱하여
     * 웨이라인 파일 메타데이터를 추출합니다.
     *
     * 검증 항목:
     * - 파일 확장자 (.kmz)
     * - UTF-8 인코딩
     * - WPML 구조 (드론 정보, 페이로드 정보)
     * - 필수 XML 태그 존재 여부
     *
     * @param file 검증할 KMZ 파일
     * @return 파싱된 웨이라인 파일 메타데이터 (Optional)
     * @throws RuntimeException 파일 형식이 올바르지 않은 경우
     */
    private Optional<WaylineFileDTO> validKmzFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (Objects.nonNull(filename) && !filename.endsWith(WAYLINE_FILE_SUFFIX)) {
            throw new RuntimeException("The file format is incorrect.");
        }
        try (ZipInputStream unzipFile = new ZipInputStream(file.getInputStream(), StandardCharsets.UTF_8)) {

            ZipEntry nextEntry = unzipFile.getNextEntry();
            while (Objects.nonNull(nextEntry)) {
                boolean isWaylines = (KmzFileProperties.FILE_DIR_FIRST + "/" + KmzFileProperties.FILE_DIR_SECOND_TEMPLATE).equals(nextEntry.getName());
                if (!isWaylines) {
                    nextEntry = unzipFile.getNextEntry();
                    continue;
                }
                SAXReader reader = new SAXReader();
                Document document = reader.read(unzipFile);
                if (!StandardCharsets.UTF_8.name().equals(document.getXMLEncoding())) {
                    throw new RuntimeException("The file encoding format is incorrect.");
                }

                // 드론 정보와 페이로드 정보 노드 검증
                Node droneNode = document.selectSingleNode("//" + KmzFileProperties.TAG_WPML_PREFIX + KmzFileProperties.TAG_DRONE_INFO);
                Node payloadNode = document.selectSingleNode("//" + KmzFileProperties.TAG_WPML_PREFIX + KmzFileProperties.TAG_PAYLOAD_INFO);
                if (Objects.isNull(droneNode) || Objects.isNull(payloadNode)) {
                    throw new RuntimeException("The file format is incorrect.");
                }

                // 드론 및 페이로드 타입 정보 추출
                DeviceTypeEnum type = DeviceTypeEnum.find(Integer.parseInt(droneNode.valueOf(KmzFileProperties.TAG_WPML_PREFIX + KmzFileProperties.TAG_DRONE_ENUM_VALUE)));
                DeviceSubTypeEnum subType = DeviceSubTypeEnum.find(Integer.parseInt(droneNode.valueOf(KmzFileProperties.TAG_WPML_PREFIX + KmzFileProperties.TAG_DRONE_SUB_ENUM_VALUE)));
                DeviceTypeEnum payloadType = DeviceTypeEnum.find(Integer.parseInt(payloadNode.valueOf(KmzFileProperties.TAG_WPML_PREFIX + KmzFileProperties.TAG_PAYLOAD_ENUM_VALUE)));
                DeviceSubTypeEnum payloadSubType = DeviceSubTypeEnum.find(Integer.parseInt(payloadNode.valueOf(KmzFileProperties.TAG_WPML_PREFIX + KmzFileProperties.TAG_PAYLOAD_SUB_ENUM_VALUE)));
                String templateType = document.valueOf("//" + KmzFileProperties.TAG_WPML_PREFIX + KmzFileProperties.TAG_TEMPLATE_TYPE);

                // 웨이라인 파일 DTO 생성
                return Optional.of(WaylineFileDTO.builder()
                        .droneModelKey(DeviceEnum.find(DeviceDomainEnum.DRONE, type, subType).getDevice())
                        .payloadModelKeys(List.of(DeviceEnum.find(DeviceDomainEnum.PAYLOAD, payloadType, payloadSubType).getDevice()))
                        .objectKey(OssConfiguration.objectDirPrefix + File.separator + filename)
                        .name(filename.substring(0, filename.lastIndexOf(WAYLINE_FILE_SUFFIX)))
                        .sign(DigestUtils.md5DigestAsHex(file.getInputStream()))
                        .templateTypes(List.of(WaylineTypeEnum.find(templateType).getValue()))
                        .build());
            }

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * 데이터베이스 엔티티 객체를 웨이라인 데이터 전송 객체로 변환
     *
     * WaylineFileEntity를 GetWaylineListResponse로 변환하여
     * API 응답에 적합한 형태로 만듭니다.
     *
     * 변환 내용:
     * - 드론 모델 키를 DeviceEnum으로 변환
     * - 페이로드 모델 키들을 DeviceEnum 리스트로 변환
     * - 템플릿 타입들을 WaylineTypeEnum 리스트로 변환
     * - 기타 메타데이터 복사
     *
     * @param entity 데이터베이스 엔티티 객체
     * @return 웨이라인 데이터 전송 객체
     */
    private GetWaylineListResponse entityConvertToDTO(WaylineFileEntity entity) {
        if (entity == null) {
            return null;
        }
        return new GetWaylineListResponse()
                .setDroneModelKey(DeviceEnum.find(entity.getDroneModelKey()))
                .setFavorited(entity.getFavorited())
                .setName(entity.getName())
                .setPayloadModelKeys(entity.getPayloadModelKeys() != null ?
                        Arrays.stream(entity.getPayloadModelKeys().split(",")).map(DeviceEnum::find).collect(Collectors.toList()) : null)
                .setTemplateTypes(Arrays.stream(entity.getTemplateTypes().split(","))
                        .map(Integer::parseInt).map(WaylineTypeEnum::find)
                        .collect(Collectors.toList()))
                .setUsername(entity.getUsername())
                .setObjectKey(entity.getObjectKey())
                .setSign(entity.getSign())
                .setUpdateTime(entity.getUpdateTime())
                .setCreateTime(entity.getCreateTime())
                .setId(entity.getWaylineId());

    }

    /**
     * 수신된 웨이라인 객체를 데이터베이스 엔티티 객체로 변환
     *
     * WaylineFileDTO를 WaylineFileEntity로 변환하여
     * 데이터베이스 저장에 적합한 형태로 만듭니다.
     *
     * 변환 내용:
     * - 페이로드 모델 키 리스트를 쉼표로 구분된 문자열로 변환
     * - 템플릿 타입 리스트를 쉼표로 구분된 문자열로 변환
     * - 기타 메타데이터 복사
     *
     * @param file 웨이라인 파일 DTO
     * @return 데이터베이스 엔티티 객체
     */
    private WaylineFileEntity dtoConvertToEntity(WaylineFileDTO file) {
        WaylineFileEntity.WaylineFileEntityBuilder builder = WaylineFileEntity.builder();

        if (file != null) {
            builder.droneModelKey(file.getDroneModelKey())
                    .name(file.getName())
                    .username(file.getUsername())
                    .objectKey(file.getObjectKey())
                    // 여러 페이로드 데이터를 ","로 구분
                    .payloadModelKeys(String.join(",", file.getPayloadModelKeys()))
                    .templateTypes(file.getTemplateTypes().stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(",")))
                    .favorited(file.getFavorited())
                    .sign(file.getSign())
                    .build();
        }

        return builder.build();
    }
}
