package com.dji.sample.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dji.sample.component.mqtt.model.EventsReceiver;
import com.dji.sample.component.oss.model.OssConfiguration;
import com.dji.sample.component.oss.service.impl.OssServiceContext;
import com.dji.sample.component.redis.RedisConst;
import com.dji.sample.component.redis.RedisOpsUtils;
import com.dji.sample.component.websocket.model.BizCodeEnum;
import com.dji.sample.component.websocket.service.IWebSocketMessageService;
import com.dji.sample.manage.dao.IDeviceFirmwareMapper;
import com.dji.sample.manage.model.dto.*;
import com.dji.sample.manage.model.entity.DeviceFirmwareEntity;
import com.dji.sample.manage.model.enums.UserTypeEnum;
import com.dji.sample.manage.model.param.DeviceFirmwareQueryParam;
import com.dji.sample.manage.model.param.DeviceFirmwareUploadParam;
import com.dji.sample.manage.service.IDeviceFirmwareService;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sample.manage.service.IFirmwareModelService;
import com.dji.sdk.cloudapi.firmware.FirmwareUpgradeTypeEnum;
import com.dji.sdk.cloudapi.firmware.OtaCreateDevice;
import com.dji.sdk.cloudapi.firmware.OtaProgress;
import com.dji.sdk.cloudapi.firmware.OtaProgressStatusEnum;
import com.dji.sdk.cloudapi.firmware.api.AbstractFirmwareService;
import com.dji.sdk.common.Pagination;
import com.dji.sdk.common.PaginationData;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.events.EventsDataRequest;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import com.dji.sdk.mqtt.events.TopicEventsResponse;
import com.dji.sdk.mqtt.services.ServicesReplyData;
import com.dji.sdk.mqtt.services.ServicesSubscribe;
import com.dji.sdk.mqtt.services.TopicServicesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 디바이스 펌웨어 관리 서비스 구현체
 * 
 * DJI Cloud API의 펌웨어 관리를 위한 서비스 구현체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 구현합니다:
 * 
 * 1. 펌웨어 파일 관리
 *    - 펌웨어 파일 업로드 및 검증
 *    - 펌웨어 파일 정보 파싱 및 저장
 *    - OSS 스토리지 연동
 *    - 파일 중복 검사 및 MD5 검증
 * 
 * 2. 펌웨어 업그레이드 관리
 *    - OTA 업그레이드 작업 생성
 *    - 업그레이드 진행률 실시간 모니터링
 *    - 업그레이드 상태 관리
 *    - Redis를 통한 진행률 캐싱
 * 
 * 3. 펌웨어 정보 관리
 *    - 펌웨어 버전 정보 조회
 *    - 최신 펌웨어 릴리즈 노트 관리
 *    - 디바이스별 호환 펌웨어 관리
 *    - 펌웨어 상태 및 메타데이터 관리
 * 
 * 4. 실시간 업그레이드 모니터링
 *    - MQTT를 통한 업그레이드 진행률 수신
 *    - WebSocket을 통한 실시간 상태 전송
 *    - 업그레이드 완료/실패 처리
 *    - 디바이스 상태 업데이트
 * 
 * 5. 데이터베이스 관리
 *    - 펌웨어 정보 CRUD 작업
 *    - 펌웨어 모델 매핑 관리
 *    - 업그레이드 이력 추적
 *    - 트랜잭션 관리
 * 
 * 주요 의존성:
 * - IDeviceFirmwareMapper: 펌웨어 데이터베이스 접근
 * - OssServiceContext: OSS 스토리지 서비스
 * - IWebSocketMessageService: WebSocket 메시지 전송
 * - IDeviceRedisService: Redis 캐시 관리
 * - AbstractFirmwareService: DJI 펌웨어 서비스
 * 
 * 이 클래스는 DJI 디바이스의 펌웨어 관리를
 * 안전하고 효율적으로 처리하는 서비스입니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/8/16
 */
@Service
@Slf4j
public class DeviceFirmwareServiceImpl extends AbstractFirmwareService implements IDeviceFirmwareService {

    /**
     * 펌웨어 데이터베이스 매퍼
     * 펌웨어 정보의 CRUD 작업을 담당
     */
    @Autowired
    private IDeviceFirmwareMapper mapper;

    /**
     * JSON 객체 매퍼
     * JSON 데이터 변환을 담당
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * WebSocket 메시지 서비스
     * 실시간으로 클라이언트에게 펌웨어 상태 변경을 알림
     */
    @Autowired
    private IWebSocketMessageService webSocketMessageService;

    /**
     * OSS 스토리지 서비스 컨텍스트
     * 펌웨어 파일을 OSS에 업로드하고 관리
     */
    @Autowired
    private OssServiceContext ossServiceContext;

    /**
     * 펌웨어 모델 서비스
     * 펌웨어 모델 정보를 관리
     */
    @Autowired
    private IFirmwareModelService firmwareModelService;

    /**
     * 디바이스 Redis 서비스
     * 디바이스 상태 정보를 Redis에 캐싱하여 빠른 조회를 지원
     */
    @Autowired
    private IDeviceRedisService deviceRedisService;

    /**
     * 워크스페이스, 디바이스명, 버전으로 펌웨어를 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param deviceName 디바이스명
     * @param version 펌웨어 버전
     * @return 펌웨어 정보 (Optional)
     */
    @Override
    public Optional<DeviceFirmwareDTO> getFirmware(String workspaceId, String deviceName, String version) {
        return Optional.ofNullable(entity2Dto(mapper.selectOne(
                new LambdaQueryWrapper<DeviceFirmwareEntity>()
                        .eq(DeviceFirmwareEntity::getWorkspaceId, workspaceId)
                        .eq(DeviceFirmwareEntity::getFirmwareVersion, version)
                        .eq(DeviceFirmwareEntity::getStatus, true),
                deviceName)));
    }

    /**
     * 디바이스의 최신 펌웨어 릴리즈 노트를 조회합니다.
     * 
     * @param deviceName 디바이스명
     * @return 최신 펌웨어 릴리즈 노트 (Optional)
     */
    @Override
    public Optional<DeviceFirmwareNoteDTO> getLatestFirmwareReleaseNote(String deviceName) {
        return Optional.ofNullable(entity2NoteDto(mapper.selectOne(
                Wrappers.lambdaQuery(DeviceFirmwareEntity.class)
                        .eq(DeviceFirmwareEntity::getStatus, true)
                        .orderByDesc(DeviceFirmwareEntity::getReleaseDate, DeviceFirmwareEntity::getFirmwareVersion),
                deviceName)));
    }

    /**
     * 디바이스 OTA 펌웨어 정보를 생성합니다.
     * 
     * 업그레이드할 디바이스 목록을 받아서 OTA 작업에 필요한 펌웨어 정보를 구성합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param upgradeDTOS 업그레이드할 디바이스 목록
     * @return OTA 디바이스 목록
     */
    @Override
    public List<OtaCreateDevice> getDeviceOtaFirmware(String workspaceId, List<DeviceFirmwareUpgradeDTO> upgradeDTOS) {
        List<OtaCreateDevice> deviceOtaList = new ArrayList<>();
        upgradeDTOS.forEach(upgradeDevice -> {
            // 디바이스 온라인 상태 확인
            boolean exist = deviceRedisService.checkDeviceOnline(upgradeDevice.getSn());
            if (!exist) {
                throw new IllegalArgumentException("Device is offline.");
            }
            // 펌웨어 정보 조회
            Optional<DeviceFirmwareDTO> firmwareOpt = this.getFirmware(
                    workspaceId, upgradeDevice.getDeviceName(), upgradeDevice.getProductVersion());
            if (firmwareOpt.isEmpty()) {
                throw new IllegalArgumentException("This firmware version does not exist or is not available.");
            }
            // OTA 디바이스 정보 구성
            OtaCreateDevice ota = dto2OtaCreateDto(firmwareOpt.get());
            ota.setSn(upgradeDevice.getSn());
            ota.setFirmwareUpgradeType(FirmwareUpgradeTypeEnum.find(upgradeDevice.getFirmwareUpgradeType()));
            deviceOtaList.add(ota);
        });
        return deviceOtaList;
    }

    /**
     * OTA 업그레이드 진행률을 처리합니다.
     * 
     * MQTT를 통해 수신된 업그레이드 진행률 정보를 처리하고
     * WebSocket을 통해 웹 클라이언트로 전송합니다.
     * 
     * @param request MQTT 이벤트 요청
     * @param headers 메시지 헤더
     * @return MQTT 응답
     */
    @Override
    public TopicEventsResponse<MqttReply> otaProgress(TopicEventsRequest<EventsDataRequest<OtaProgress>> request, MessageHeaders headers) {
        String sn  = request.getGateway();

        EventsReceiver<OtaProgress> eventsReceiver = new EventsReceiver<OtaProgress>()
                .setBid(request.getBid())
                .setOutput(request.getData().getOutput())
                .setResult(request.getData().getResult());

        log.info("SN: {}, {} ===> Upgrading progress: {}",
                sn, request.getMethod(), eventsReceiver.getOutput().getProgress());

        if (!eventsReceiver.getResult().isSuccess()) {
            log.error("SN: {}, {} ===> Error: {}", sn, request.getMethod(), eventsReceiver.getResult());
        }

        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(sn);
        if (deviceOpt.isEmpty()) {
            return null;
        }

        OtaProgressStatusEnum statusEnum = eventsReceiver.getOutput().getStatus();
        DeviceDTO device = deviceOpt.get();
        // 게이트웨이와 서브 디바이스 모두에 진행률 처리
        handleProgress(device.getWorkspaceId(), sn, eventsReceiver, statusEnum.isEnd());
        handleProgress(device.getWorkspaceId(), device.getChildDeviceSn(), eventsReceiver, statusEnum.isEnd());

        return new TopicEventsResponse<MqttReply>().setData(MqttReply.success());
    }

    /**
     * 업그레이드 진행률을 처리합니다.
     * 
     * Redis에 진행률을 캐싱하고 WebSocket을 통해 웹 클라이언트로 전송합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param sn 디바이스 시리얼 번호
     * @param events 진행률 이벤트
     * @param isEnd 업그레이드 완료 여부
     */
    private void handleProgress(String workspaceId, String sn, EventsReceiver<OtaProgress> events, boolean isEnd) {
        boolean upgrade = deviceRedisService.getFirmwareUpgradingProgress(sn).isPresent();
        if (!upgrade) {
            return;
        }
        if (isEnd) {
            // 업그레이드 완료 시 캐시 삭제
            deviceRedisService.delFirmwareUpgrading(sn);
        } else {
            // Redis에 업그레이드 진행률 업데이트
            deviceRedisService.setFirmwareUpgrading(sn, events);
        }
        events.setSn(sn);
        // WebSocket을 통해 웹 클라이언트로 진행률 전송
        webSocketMessageService.sendBatch(workspaceId, UserTypeEnum.WEB.getVal(), BizCodeEnum.OTA_PROGRESS.getCode(), events);
    }

    /**
     * 파일 존재 여부를 확인합니다.
     * 
     * Redis 캐시와 데이터베이스에서 파일 MD5를 확인합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param fileMd5 파일 MD5 해시
     * @return 파일 존재 여부
     */
    @Override
    public Boolean checkFileExist(String workspaceId, String fileMd5) {
        return RedisOpsUtils.checkExist(RedisConst.FILE_UPLOADING_PREFIX + workspaceId + fileMd5) ||
                mapper.selectCount(new LambdaQueryWrapper<DeviceFirmwareEntity>()
                    .eq(DeviceFirmwareEntity::getWorkspaceId, workspaceId)
                    .eq(DeviceFirmwareEntity::getFileMd5, fileMd5))
                > 0;
    }

    /**
     * 펌웨어 목록을 페이지네이션으로 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param param 조회 파라미터
     * @return 페이지네이션된 펌웨어 목록
     */
    @Override
    public PaginationData<DeviceFirmwareDTO> getAllFirmwarePagination(String workspaceId, DeviceFirmwareQueryParam param) {
        Page<DeviceFirmwareEntity> page = mapper.selectPage(new Page<>(param.getPage(), param.getPageSize()),
                new LambdaQueryWrapper<DeviceFirmwareEntity>()
                        .eq(DeviceFirmwareEntity::getWorkspaceId, workspaceId)
                        .eq(Objects.nonNull(param.getStatus()), DeviceFirmwareEntity::getStatus, param.getStatus())
                        .like(StringUtils.hasText(param.getProductVersion()), DeviceFirmwareEntity::getFirmwareVersion, param.getProductVersion())
                        .orderByDesc(DeviceFirmwareEntity::getReleaseDate), param.getDeviceName());

        List<DeviceFirmwareDTO> data = page.getRecords().stream().map(this::entity2Dto).collect(Collectors.toList());
        return new PaginationData<DeviceFirmwareDTO>(data, new Pagination(page.getCurrent(), page.getSize(), page.getTotal()));
    }

    /**
     * 펌웨어 파일을 업로드하고 정보를 저장합니다.
     * 
     * 파일을 검증하고 OSS에 업로드한 후 데이터베이스에 정보를 저장합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @param creator 생성자
     * @param param 업로드 파라미터
     * @param file 업로드할 파일
     */
    @Override
    public void importFirmwareFile(String workspaceId, String creator, DeviceFirmwareUploadParam param, MultipartFile file) {
        String key = RedisConst.FILE_UPLOADING_PREFIX + workspaceId;
        String existKey = key + file.getOriginalFilename();
        if (RedisOpsUtils.getExpire(existKey) > 0) {
            throw new RuntimeException("Please try again later.");
        }
        RedisOpsUtils.setWithExpire(existKey, true, RedisConst.DEVICE_ALIVE_SECOND);
        try (InputStream is = file.getInputStream()) {
            long size = is.available();
            String md5 = DigestUtils.md5DigestAsHex(is);
            key += md5;
            boolean exist = checkFileExist(workspaceId, md5);
            if (exist) {
                throw new RuntimeException("The file already exists.");
            }
            RedisOpsUtils.set(key, System.currentTimeMillis());
            // 펌웨어 파일 검증
            Optional<DeviceFirmwareDTO> firmwareOpt = verifyFirmwareFile(file);
            if (firmwareOpt.isEmpty()) {
                throw new RuntimeException("The file format is incorrect.");
            }

            String firmwareId = UUID.randomUUID().toString();
            String objectKey = OssConfiguration.objectDirPrefix + File.separator + firmwareId + FirmwareFileProperties.FIRMWARE_FILE_SUFFIX;

            // OSS에 파일 업로드
            ossServiceContext.putObject(OssConfiguration.bucket, objectKey, file.getInputStream());
            log.info("upload success. {}", file.getOriginalFilename());
            
            // 펌웨어 정보 구성
            DeviceFirmwareDTO firmware = DeviceFirmwareDTO.builder()
                    .releaseNote(param.getReleaseNote())
                    .firmwareStatus(param.getStatus())
                    .fileMd5(md5)
                    .objectKey(objectKey)
                    .fileName(file.getOriginalFilename())
                    .workspaceId(workspaceId)
                    .username(creator)
                    .fileSize(size)
                    .productVersion(firmwareOpt.get().getProductVersion())
                    .releasedTime(firmwareOpt.get().getReleasedTime())
                    .firmwareId(firmwareId)
                    .build();

            saveFirmwareInfo(firmware, param.getDeviceName());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            RedisOpsUtils.del(key);
        }
    }

    /**
     * 펌웨어 정보를 저장합니다.
     * 
     * @param firmware 펌웨어 정보
     * @param deviceNames 디바이스명 목록
     */
    @Override
    public void saveFirmwareInfo(DeviceFirmwareDTO firmware, List<String> deviceNames) {
        DeviceFirmwareEntity entity = dto2Entity(firmware);
        mapper.insert(entity);
        firmwareModelService.saveFirmwareDeviceName(
                FirmwareModelDTO.builder().firmwareId(entity.getFirmwareId()).deviceNames(deviceNames).build());
    }

    /**
     * 펌웨어 정보를 업데이트합니다.
     * 
     * @param firmware 업데이트할 펌웨어 정보
     */
    @Override
    public void updateFirmwareInfo(DeviceFirmwareDTO firmware) {
        mapper.update(dto2Entity(firmware),
                new LambdaUpdateWrapper<DeviceFirmwareEntity>()
                        .eq(DeviceFirmwareEntity::getFirmwareId, firmware.getFirmwareId()));
    }

    /**
     * 펌웨어 파일 정보를 파싱합니다.
     * 
     * ZIP 파일 내의 설정 파일을 읽어서 펌웨어 버전과 릴리즈 날짜를 추출합니다.
     * 
     * @param file 펌웨어 파일
     * @return 펌웨어 정보 (Optional)
     */
    private Optional<DeviceFirmwareDTO> verifyFirmwareFile(MultipartFile file) {
        try (ZipInputStream unzipFile = new ZipInputStream(file.getInputStream(), StandardCharsets.UTF_8)) {
            ZipEntry nextEntry = unzipFile.getNextEntry();
            while (Objects.nonNull(nextEntry)) {
                String configName = nextEntry.getName();
                if (!configName.contains(File.separator) && configName.endsWith(FirmwareFileProperties.FIRMWARE_CONFIG_FILE_SUFFIX + FirmwareFileProperties.FIRMWARE_SIG_FILE_SUFFIX)) {
                    String[] filenameArr = configName.split(FirmwareFileProperties.FIRMWARE_FILE_DELIMITER);
                    String date = filenameArr[FirmwareFileProperties.FILENAME_RELEASE_DATE_INDEX];
                    int index = date.indexOf(".");
                    if (index != -1) {
                        date = date.substring(0, index);
                    }
                    return Optional.of(DeviceFirmwareDTO.builder()
                            .releasedTime(LocalDate.parse(
                                    date,
                                    DateTimeFormatter.ofPattern(FirmwareFileProperties.FILENAME_RELEASE_DATE_FORMAT)))
                            // 문자열 v를 제거
                            .productVersion(filenameArr[FirmwareFileProperties.FILENAME_VERSION_INDEX].substring(1))
                            .build());
                }
                nextEntry = unzipFile.getNextEntry();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * 펌웨어 DTO를 엔티티로 변환합니다.
     * 
     * @param dto 펌웨어 DTO
     * @return 펌웨어 엔티티
     */
    private DeviceFirmwareEntity dto2Entity(DeviceFirmwareDTO dto) {
        if (dto == null) {
            return null;
        }
        return DeviceFirmwareEntity.builder()
                .fileName(dto.getFileName())
                .fileMd5(dto.getFileMd5())
                .fileSize(dto.getFileSize())
                .firmwareId(dto.getFirmwareId())
                .firmwareVersion(dto.getProductVersion())
                .objectKey(dto.getObjectKey())
                .releaseDate(Objects.nonNull(dto.getReleasedTime()) ?
                        dto.getReleasedTime().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli() : null)
                .releaseNote(dto.getReleaseNote())
                .status(dto.getFirmwareStatus())
                .workspaceId(dto.getWorkspaceId())
                .username(dto.getUsername())
                .build();
    }

    /**
     * 펌웨어 엔티티를 릴리즈 노트 DTO로 변환합니다.
     * 
     * @param entity 펌웨어 엔티티
     * @return 릴리즈 노트 DTO
     */
    private DeviceFirmwareNoteDTO entity2NoteDto (DeviceFirmwareEntity entity) {
        if (entity == null) {
            return null;
        }
        return DeviceFirmwareNoteDTO.builder()
                .deviceName(entity.getDeviceName())
                .productVersion(entity.getFirmwareVersion())
                .releasedTime(LocalDate.ofInstant(Instant.ofEpochMilli(entity.getReleaseDate()), ZoneId.systemDefault()))
                .releaseNote(entity.getReleaseNote())
                .build();
    }

    /**
     * 펌웨어 엔티티를 DTO로 변환합니다.
     * 
     * @param entity 펌웨어 엔티티
     * @return 펌웨어 DTO
     */
    private DeviceFirmwareDTO entity2Dto (DeviceFirmwareEntity entity) {
        if (entity == null) {
            return null;
        }
        return DeviceFirmwareDTO.builder()
                .deviceName(Arrays.asList(entity.getDeviceName().split(",")))
                .fileMd5(entity.getFileMd5())
                .fileSize(entity.getFileSize())
                .objectKey(entity.getObjectKey())
                .firmwareId(entity.getFirmwareId())
                .fileName(entity.getFileName())
                .productVersion(entity.getFirmwareVersion())
                .releasedTime(LocalDate.ofInstant(Instant.ofEpochMilli(entity.getReleaseDate()), ZoneId.systemDefault()))
                .releaseNote(entity.getReleaseNote())
                .firmwareStatus(entity.getStatus())
                .workspaceId(entity.getWorkspaceId())
                .username(entity.getUsername())
                .build();
    }

    /**
     * 펌웨어 DTO를 OTA 생성 DTO로 변환합니다.
     * 
     * @param dto 펌웨어 DTO
     * @return OTA 생성 DTO
     */
    private OtaCreateDevice dto2OtaCreateDto(DeviceFirmwareDTO dto) {
        if (dto == null) {
            return null;
        }
        return new OtaCreateDevice()
                .setFileSize(dto.getFileSize())
                .setFileUrl(ossServiceContext.getObjectUrl(OssConfiguration.bucket, dto.getObjectKey()).toString())
                .setFileName(dto.getFileName())
                .setMd5(dto.getFileMd5())
                .setProductVersion(dto.getProductVersion());
    }
}
