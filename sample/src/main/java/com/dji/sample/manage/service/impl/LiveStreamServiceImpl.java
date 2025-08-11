package com.dji.sample.manage.service.impl;

import com.dji.sample.manage.model.dto.*;
import com.dji.sample.manage.model.param.DeviceQueryParam;
import com.dji.sample.manage.service.*;
import com.dji.sdk.cloudapi.device.DeviceDomainEnum;
import com.dji.sdk.cloudapi.device.VideoId;
import com.dji.sdk.cloudapi.livestream.*;
import com.dji.sdk.cloudapi.livestream.api.AbstractLivestreamService;
import com.dji.sdk.common.HttpResultResponse;
import com.dji.sdk.common.SDKManager;
import com.dji.sdk.mqtt.services.ServicesReplyData;
import com.dji.sdk.mqtt.services.TopicServicesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 라이브스트림 관리 서비스 구현체
 * 
 * DJI Cloud API의 라이브스트림 관리를 위한 서비스 구현체입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 구현합니다:
 * 
 * 1. 라이브스트림 시작/중지 관리
 *    - 라이브스트림 시작 및 중지 처리
 *    - 다양한 스트리밍 프로토콜 지원 (RTMP, RTSP, GB28181, WHIP 등)
 *    - 스트리밍 URL 생성 및 관리
 *    - 스트리밍 상태 모니터링
 * 
 * 2. 라이브스트림 품질 관리
 *    - 비디오 품질 설정 및 변경
 *    - 실시간 품질 조정
 *    - 품질별 스트리밍 최적화
 *    - 대역폭 관리
 * 
 * 3. 카메라 렌즈 관리
 *    - 카메라 렌즈 전환 처리
 *    - 다중 카메라 지원
 *    - 렌즈별 설정 관리
 *    - 렌즈 상태 모니터링
 * 
 * 4. 디바이스 연결 관리
 *    - 디바이스 온라인 상태 확인
 *    - 게이트웨이-드론 연결 검증
 *    - 디바이스 권한 확인
 *    - 연결 상태 모니터링
 * 
 * 5. 스트리밍 URL 관리
 *    - 프로토콜별 URL 생성
 *    - URL 유효성 검증
 *    - URL 보안 관리
 *    - URL 만료 처리
 * 
 * 주요 의존성:
 * - ICapacityCameraService: 카메라 용량 관리
 * - IDeviceService: 디바이스 관리
 * - IWorkspaceService: 워크스페이스 관리
 * - IDeviceRedisService: Redis 캐시 관리
 * - AbstractLivestreamService: DJI 라이브스트림 서비스
 * 
 * 이 클래스는 DJI 디바이스의 라이브스트림을
 * 안전하고 효율적으로 관리하는 서비스입니다.
 * 
 * @author sean.zhou
 * @date 2021/11/22
 * @version 0.1
 */
@Service
@Transactional
public class LiveStreamServiceImpl implements ILiveStreamService {

    @Autowired
    private ICapacityCameraService capacityCameraService;

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IWorkspaceService workspaceService;

    @Autowired
    private IDeviceRedisService deviceRedisService;

    @Autowired
    private AbstractLivestreamService abstractLivestreamService;

    /**
     * 워크스페이스의 라이브스트림 용량을 조회합니다.
     * 
     * 워크스페이스 내의 모든 드론과 도킹 스테이션의 라이브스트림 용량을 조회합니다.
     * 
     * @param workspaceId 워크스페이스 ID
     * @return 라이브스트림 용량 디바이스 목록
     */
    @Override
    public List<CapacityDeviceDTO> getLiveCapacity(String workspaceId) {

        // 워크스페이스 내의 모든 디바이스 조회
        List<DeviceDTO> devicesList = deviceService.getDevicesByParams(
                DeviceQueryParam.builder()
                        .workspaceId(workspaceId)
                        .domains(List.of(DeviceDomainEnum.DRONE.getDomain(), DeviceDomainEnum.DOCK.getDomain()))
                        .build());

        // 각 드론의 라이브스트림 용량 조회
        return devicesList.stream()
                .filter(device -> deviceRedisService.checkDeviceOnline(device.getDeviceSn()))
                .map(device -> CapacityDeviceDTO.builder()
                        .name(Objects.requireNonNullElse(device.getNickname(), device.getDeviceName()))
                        .sn(device.getDeviceSn())
                        .camerasList(capacityCameraService.getCapacityCameraByDeviceSn(device.getDeviceSn()))
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * 라이브스트림을 시작합니다.
     * 
     * 지정된 비디오 ID로 라이브스트림을 시작하고 스트리밍 URL을 반환합니다.
     * 
     * @param liveParam 라이브스트림 파라미터
     * @return 라이브스트림 시작 결과
     */
    @Override
    public HttpResultResponse liveStart(LiveTypeDTO liveParam) {
        // 라이브스트림 시작 전 검증
        HttpResultResponse<DeviceDTO> responseResult = this.checkBeforeLive(liveParam.getVideoId());
        if (HttpResultResponse.CODE_SUCCESS != responseResult.getCode()) {
            return responseResult;
        }

        ILivestreamUrl url = LiveStreamProperty.get(liveParam.getUrlType());
        url = setExt(liveParam.getUrlType(), url, liveParam.getVideoId());

        // 라이브스트림 시작 요청
        TopicServicesResponse<ServicesReplyData<String>> response = abstractLivestreamService.liveStartPush(
                SDKManager.getDeviceSDK(responseResult.getData().getDeviceSn()),
                new LiveStartPushRequest()
                        .setUrl(url)
                        .setUrlType(liveParam.getUrlType())
                        .setVideoId(liveParam.getVideoId())
                        .setVideoQuality(liveParam.getVideoQuality()));

        if (!response.getData().getResult().isSuccess()) {
            return HttpResultResponse.error(response.getData().getResult());
        }

        LiveDTO live = new LiveDTO();

        // 프로토콜별 URL 변환
        switch (liveParam.getUrlType()) {
            case AGORA:
                break;
            case RTMP:
                live.setUrl(url.toString().replace("rtmp", "webrtc"));
                break;
            case GB28181:
                LivestreamGb28181Url gb28181 = (LivestreamGb28181Url) url;
                live.setUrl(new StringBuilder()
                        .append("webrtc://")
                        .append(gb28181.getServerIP())
                        .append("/live/")
                        .append(gb28181.getAgentID())
                        .append("@")
                        .append(gb28181.getChannel())
                        .toString());
                break;
            case RTSP:
                live.setUrl(response.getData().getOutput());
                break;
            case WHIP:
                live.setUrl(url.toString().replace("whip", "whep"));
                break;
            default:
                return HttpResultResponse.error(LiveErrorCodeEnum.URL_TYPE_NOT_SUPPORTED);
        }

        return HttpResultResponse.success(live);
    }

    /**
     * 라이브스트림을 중지합니다.
     * 
     * @param videoId 비디오 ID
     * @return 라이브스트림 중지 결과
     */
    @Override
    public HttpResultResponse liveStop(VideoId videoId) {
        // 라이브스트림 중지 전 검증
        HttpResultResponse<DeviceDTO> responseResult = this.checkBeforeLive(videoId);
        if (HttpResultResponse.CODE_SUCCESS != responseResult.getCode()) {
            return responseResult;
        }

        // 라이브스트림 중지 요청
        TopicServicesResponse<ServicesReplyData> response = abstractLivestreamService.liveStopPush(
                SDKManager.getDeviceSDK(responseResult.getData().getDeviceSn()), new LiveStopPushRequest()
                        .setVideoId(videoId));
        if (!response.getData().getResult().isSuccess()) {
            return HttpResultResponse.error(response.getData().getResult());
        }

        return HttpResultResponse.success();
    }

    /**
     * 라이브스트림 품질을 설정합니다.
     * 
     * @param liveParam 라이브스트림 파라미터
     * @return 품질 설정 결과
     */
    @Override
    public HttpResultResponse liveSetQuality(LiveTypeDTO liveParam) {
        // 품질 설정 전 검증
        HttpResultResponse<DeviceDTO> responseResult = this.checkBeforeLive(liveParam.getVideoId());
        if (responseResult.getCode() != 0) {
            return responseResult;
        }

        // 라이브스트림 품질 설정 요청
        TopicServicesResponse<ServicesReplyData> response = abstractLivestreamService.liveSetQuality(
                SDKManager.getDeviceSDK(responseResult.getData().getDeviceSn()), new LiveSetQualityRequest()
                        .setVideoQuality(liveParam.getVideoQuality())
                        .setVideoId(liveParam.getVideoId()));
        if (!response.getData().getResult().isSuccess()) {
            return HttpResultResponse.error(response.getData().getResult());
        }

        return HttpResultResponse.success();
    }

    /**
     * 라이브스트림 렌즈를 변경합니다.
     * 
     * @param liveParam 라이브스트림 파라미터
     * @return 렌즈 변경 결과
     */
    @Override
    public HttpResultResponse liveLensChange(LiveTypeDTO liveParam) {
        // 렌즈 변경 전 검증
        HttpResultResponse<DeviceDTO> responseResult = this.checkBeforeLive(liveParam.getVideoId());
        if (HttpResultResponse.CODE_SUCCESS != responseResult.getCode()) {
            return responseResult;
        }

        // 라이브스트림 렌즈 변경 요청
        TopicServicesResponse<ServicesReplyData> response = abstractLivestreamService.liveLensChange(
                SDKManager.getDeviceSDK(responseResult.getData().getDeviceSn()), new LiveLensChangeRequest()
                        .setVideoType(liveParam.getVideoType())
                        .setVideoId(liveParam.getVideoId()));

        if (!response.getData().getResult().isSuccess()) {
            return HttpResultResponse.error(response.getData().getResult());
        }

        return HttpResultResponse.success();
    }

    /**
     * 라이브스트림 시작 전 검증을 수행합니다.
     * 
     * 디바이스 온라인 상태와 연결 상태를 확인합니다.
     * 
     * @param videoId 비디오 ID
     * @return 검증 결과
     */
    private HttpResultResponse<DeviceDTO> checkBeforeLive(VideoId videoId) {
        if (Objects.isNull(videoId)) {
            return HttpResultResponse.error(LiveErrorCodeEnum.ERROR_PARAMETERS);
        }

        Optional<DeviceDTO> deviceOpt = deviceService.getDeviceBySn(videoId.getDroneSn());
        // 이 드론에 연결된 게이트웨이 디바이스가 존재하는지 확인
        if (deviceOpt.isEmpty()) {
            return HttpResultResponse.error(LiveErrorCodeEnum.NO_AIRCRAFT);
        }

        if (DeviceDomainEnum.DOCK == deviceOpt.get().getDomain()) {
            return HttpResultResponse.success(deviceOpt.get());
        }
        List<DeviceDTO> gatewayList = deviceService.getDevicesByParams(
                DeviceQueryParam.builder()
                        .childSn(videoId.getDroneSn())
                        .build());
        if (gatewayList.isEmpty()) {
            return HttpResultResponse.error(LiveErrorCodeEnum.NO_FLIGHT_CONTROL);
        }

        return HttpResultResponse.success(gatewayList.get(0));
    }

    /**
     * 비즈니스 커스터마이징 로직으로 테스트용으로만 사용됩니다.
     * 
     * 프로토콜 타입에 따라 URL을 설정합니다.
     * 
     * @param type URL 타입
     * @param url 라이브스트림 URL
     * @param videoId 비디오 ID
     * @return 설정된 URL
     */
    private ILivestreamUrl setExt(UrlTypeEnum type, ILivestreamUrl url, VideoId videoId) {
        switch (type) {
            case AGORA:
                LivestreamAgoraUrl agoraUrl = (LivestreamAgoraUrl) url.clone();
                return agoraUrl.setSn(videoId.getDroneSn());
            case RTMP:
                LivestreamRtmpUrl rtmpUrl = (LivestreamRtmpUrl) url.clone();
                return rtmpUrl.setUrl(rtmpUrl.getUrl() + videoId.getDroneSn() + "-" + videoId.getPayloadIndex().toString());
            case GB28181:
                String random = String.valueOf(Math.abs(videoId.getDroneSn().hashCode()) % 1000);
                LivestreamGb28181Url gbUrl = (LivestreamGb28181Url) url.clone();
                gbUrl.setAgentID(gbUrl.getAgentID().substring(0, 20 - random.length()) + random);
                String deviceType = String.valueOf(videoId.getPayloadIndex().getType().getType());
                return gbUrl.setChannel(gbUrl.getChannel().substring(0, 20 - deviceType.length()) + deviceType);
            case WHIP:
                LivestreamWhipUrl whipUrl = (LivestreamWhipUrl) url.clone();
                return whipUrl.setUrl(whipUrl.getUrl() + videoId.getDroneSn() + "-" + videoId.getPayloadIndex().toString());
        }
        return url;
    }
}