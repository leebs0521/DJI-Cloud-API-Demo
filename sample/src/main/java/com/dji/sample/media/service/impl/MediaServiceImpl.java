package com.dji.sample.media.service.impl;

import com.dji.sample.component.oss.model.OssConfiguration;
import com.dji.sample.component.websocket.model.BizCodeEnum;
import com.dji.sample.component.websocket.service.IWebSocketMessageService;
import com.dji.sample.manage.model.dto.DeviceDTO;
import com.dji.sample.manage.model.enums.UserTypeEnum;
import com.dji.sample.manage.service.IDeviceRedisService;
import com.dji.sample.manage.service.IDeviceService;
import com.dji.sample.media.model.MediaFileCountDTO;
import com.dji.sample.media.model.MediaFileDTO;
import com.dji.sample.media.service.IFileService;
import com.dji.sample.media.service.IMediaRedisService;
import com.dji.sample.media.service.IMediaService;
import com.dji.sample.wayline.service.IWaylineJobService;
import com.dji.sdk.cloudapi.media.*;
import com.dji.sdk.cloudapi.media.api.AbstractMediaService;
import com.dji.sdk.mqtt.MqttReply;
import com.dji.sdk.mqtt.events.TopicEventsRequest;
import com.dji.sdk.mqtt.events.TopicEventsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 미디어 서비스 구현체
 * 미디어 파일의 업로드, 저장, 중복 검사 등의 핵심 비즈니스 로직을 구현하는 서비스 클래스입니다.
 * 파일 업로드 콜백 처리, 빠른 업로드 검증, 지문 기반 중복 검사, MQTT 이벤트 처리 등의 기능을 제공하며,
 * DJI SDK의 AbstractMediaService를 상속받아 MQTT 기반 미디어 서비스를 구현합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/9
 */
@Service
@Slf4j
public class MediaServiceImpl extends AbstractMediaService implements IMediaService {

    @Autowired
    private IFileService fileService;

    @Autowired
    private IWaylineJobService waylineJobService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IWebSocketMessageService webSocketMessageService;

    @Autowired
    private IDeviceRedisService deviceRedisService;

    @Autowired
    private IMediaRedisService mediaRedisService;

    /**
     * 파일의 지문(fingerprint)을 통해 해당 파일이 이미 업로드되었는지 확인합니다.
     * 빠른 업로드 검증을 위해 사용되며, 중복 파일 업로드를 방지합니다.
     * @param workspaceId 워크스페이스 ID
     * @param fingerprint 파일 지문 (파일 내용의 해시 값)
     * @return 파일 존재 여부 (true: 이미 업로드됨, false: 업로드되지 않음)
     */
    @Override
    public Boolean fastUpload(String workspaceId, String fingerprint) {
        return fileService.checkExist(workspaceId, fingerprint);
    }

    /**
     * 파일의 기본 정보를 데이터베이스에 저장합니다.
     * 업로드된 미디어 파일의 메타데이터를 영구 저장소에 기록합니다.
     * @param workspaceId 워크스페이스 ID
     * @param file 미디어 업로드 콜백 요청 객체
     * @return 저장된 레코드의 ID
     */
    @Override
    public Integer saveMediaFile(String workspaceId, MediaUploadCallbackRequest file) {
        return fileService.saveFile(workspaceId, file);
    }

    /**
     * 워크스페이스 ID를 기반으로 해당 워크스페이스의 모든 파일의 작은 지문(tiny fingerprint)을 조회합니다.
     * 빠른 중복 검사를 위해 사용되는 축약된 해시 값들을 반환합니다.
     * @param workspaceId 워크스페이스 ID
     * @return 작은 지문 문자열 목록
     */
    @Override
    public List<String> getAllTinyFingerprintsByWorkspaceId(String workspaceId) {
        return fileService.getAllFilesByWorkspaceId(workspaceId)
                .stream()
                .map(MediaFileDTO::getTinnyFingerprint)
                .collect(Collectors.toList());
    }

    /**
     * 입력받은 작은 지문(tiny fingerprint) 데이터를 기반으로 이미 존재하는 지문들을 조회합니다.
     * 업로드 전 중복 파일 검사를 위해 사용됩니다.
     * @param workspaceId 워크스페이스 ID
     * @param tinyFingerprints 검사할 작은 지문 목록
     * @return 이미 존재하는 작은 지문 목록
     */
    @Override
    public List<String> getExistTinyFingerprints(String workspaceId, List<String> tinyFingerprints) {
        List<String> tinyFingerprintList = this.getAllTinyFingerprintsByWorkspaceId(workspaceId);
        return tinyFingerprints
                .stream()
                .filter(tinyFingerprintList::contains)
                .collect(Collectors.toList());

    }

    /**
     * 파일 업로드 콜백을 처리합니다.
     * MQTT를 통해 전송된 파일 업로드 완료 이벤트를 처리하며, 파일 정보를 데이터베이스에 저장하고
     * 업로드 진행 상황을 업데이트합니다.
     * @param request MQTT 이벤트 요청
     * @param headers 메시지 헤더
     * @return MQTT 이벤트 응답
     */
    @Override
    public TopicEventsResponse<MqttReply> fileUploadCallback(TopicEventsRequest<FileUploadCallback> request, MessageHeaders headers) {
        FileUploadCallback callback = request.getData();

        if (MqttReply.CODE_SUCCESS != callback.getResult()) {
            log.error("Media file upload failed!");
            return null;
        }

        String jobId = callback.getFile().getExt().getFlightId();

        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(request.getGateway());
        MediaFileCountDTO mediaFileCount = mediaRedisService.getMediaCount(request.getGateway(), jobId);
        // duplicate data
        if (deviceOpt.isEmpty()
                || (Objects.nonNull(mediaFileCount) && request.getBid().equals(mediaFileCount.getBid())
                && request.getTid().equals(mediaFileCount.getTid()))) {
            return new TopicEventsResponse<MqttReply>().setData(MqttReply.success());
        }

        DeviceDTO device = deviceOpt.get();
        boolean isSave = parseMediaFile(callback, device);
        if (!isSave) {
            log.error("Failed to save the file to the database, please check the data manually.");
            return null;
        }

        notifyUploadedCount(mediaFileCount, request, jobId, device);
        return new TopicEventsResponse<MqttReply>().setData(MqttReply.success());
    }

    /**
     * 최고 우선순위 비행 작업 미디어 업로드를 처리합니다.
     * 중요한 비행 작업의 미디어 파일을 우선적으로 업로드하기 위한 기능입니다.
     * @param request MQTT 이벤트 요청
     * @param headers 메시지 헤더
     * @return MQTT 이벤트 응답
     */
    @Override
    public TopicEventsResponse<MqttReply> highestPriorityUploadFlightTaskMedia(
            TopicEventsRequest<HighestPriorityUploadFlightTaskMedia> request, MessageHeaders headers) {
        String jobId = request.getData().getFlightId();
        if (!StringUtils.hasText(jobId)) {
            return null;
        }

        MediaFileCountDTO countDTO = mediaRedisService.getMediaHighestPriority(request.getGateway());
        if (Objects.nonNull(countDTO)) {
            if (jobId.equals(countDTO.getJobId())) {
                mediaRedisService.setMediaHighestPriority(request.getGateway(), countDTO);
                return new TopicEventsResponse<MqttReply>().setData(MqttReply.success());
            }
            countDTO.setPreJobId(countDTO.getJobId());
        }
        countDTO.setJobId(jobId);
        mediaRedisService.setMediaHighestPriority(request.getGateway(), countDTO);

        Optional<DeviceDTO> deviceOpt = deviceRedisService.getDeviceOnline(request.getGateway());
        if (deviceOpt.isEmpty()) {
            return null;
        }

        webSocketMessageService.sendBatch(deviceOpt.get().getWorkspaceId(), UserTypeEnum.WEB.getVal(),
                BizCodeEnum.HIGHEST_PRIORITY_UPLOAD_FLIGHT_TASK_MEDIA.getCode(), countDTO);

        return new TopicEventsResponse<MqttReply>().setData(MqttReply.success());
    }

    /**
     * 미디어 파일을 파싱하여 데이터베이스에 저장합니다.
     * 업로드된 파일의 메타데이터를 추출하고 데이터베이스에 저장하기 위한 형태로 변환합니다.
     * @param callback 파일 업로드 콜백 데이터
     * @param device 디바이스 정보
     * @return 저장 성공 여부
     */
    private Boolean parseMediaFile(FileUploadCallback callback, DeviceDTO device) {
        MediaUploadCallbackRequest file = convert2callbackRequest(callback.getFile());
        // Set the drone sn that shoots the media
        file.getExt().setSn(device.getChildDeviceSn());

        // set path
        String objectKey = file.getObjectKey();
        file.setPath(objectKey.substring(Optional.of(objectKey.indexOf(OssConfiguration.objectDirPrefix))
                .filter(index -> index > 0).map(index -> index++).orElse(0),
                objectKey.lastIndexOf("/")));

        return fileService.saveFile(device.getWorkspaceId(), file) > 0;
    }

    /**
     * 업로드된 파일 개수를 알림으로 전송합니다.
     * 파일 업로드 진행 상황을 웹소켓을 통해 클라이언트에 실시간으로 알립니다.
     * @param mediaFileCount 미디어 파일 카운트 정보
     * @param request MQTT 이벤트 요청
     * @param jobId 작업 ID
     * @param dock 도크 디바이스 정보
     */
    private void notifyUploadedCount(MediaFileCountDTO mediaFileCount, TopicEventsRequest<FileUploadCallback> request, String jobId, DeviceDTO dock) {
        // Do not notify when files that do not belong to the route are uploaded.
        if (Objects.isNull(mediaFileCount)) {
            return;
        }
        mediaFileCount.setBid(request.getBid());
        mediaFileCount.setTid(request.getTid());
        mediaFileCount.setUploadedCount(mediaFileCount.getUploadedCount() + 1);

        // After all the files of the job are uploaded, delete the media file key.
        if (mediaFileCount.getUploadedCount() >= mediaFileCount.getMediaCount()) {
            mediaRedisService.delMediaCount(request.getGateway(), jobId);

            // After uploading, delete the key with the highest priority.
            MediaFileCountDTO fileCount = mediaRedisService.getMediaHighestPriority(request.getGateway());
            if (Objects.nonNull(fileCount) && jobId.equals(fileCount.getJobId())) {
                mediaRedisService.delMediaHighestPriority(request.getGateway());
            }
        } else {
            mediaRedisService.setMediaCount(request.getGateway(), jobId, mediaFileCount);
        }

        webSocketMessageService.sendBatch(dock.getWorkspaceId(), UserTypeEnum.WEB.getVal(),
                BizCodeEnum.FILE_UPLOAD_CALLBACK.getCode(), mediaFileCount);
    }

    /**
     * 파일 업로드 콜백 파일을 미디어 업로드 콜백 요청으로 변환합니다.
     * MQTT 이벤트에서 받은 파일 정보를 서비스에서 사용할 수 있는 형태로 변환합니다.
     * @param file 파일 업로드 콜백 파일 객체
     * @return 미디어 업로드 콜백 요청 객체
     */
    private MediaUploadCallbackRequest convert2callbackRequest(FileUploadCallbackFile file) {
        if (Objects.isNull(file)) {
            return null;
        }
        return new MediaUploadCallbackRequest()
                .setExt(Optional.ofNullable(file.getExt())
                        .map(ext -> new MediaFileExtension()
                                .setDroneModelKey(ext.getDroneModelKey())
                                .setFileGroupId(ext.getFlightId())
                                .setOriginal(ext.getOriginal())
                                .setPayloadModelKey(ext.getPayloadModelKey()))
                        .orElse(new MediaFileExtension()))
                .setMetadata(Optional.ofNullable(file.getMetadata())
                        .map(data -> new MediaFileMetadata()
                                .setAbsoluteAltitude(data.getAbsoluteAltitude())
                                .setGimbalYawDegree(data.getGimbalYawDegree())
                                .setRelativeAltitude(data.getRelativeAltitude())
                                .setShootPosition(data.getShootPosition())
                                .setCreatedTime(data.getCreatedTime()))
                        .get())
                .setName(file.getName())
                .setObjectKey(file.getObjectKey())
                .setPath(file.getPath());
    }
}
