package com.dji.sdk.cloudapi.livestream;

import com.dji.sdk.cloudapi.device.VideoId;
import com.dji.sdk.common.BaseModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 라이브스트림 푸시 시작 요청을 나타내는 클래스
 * 라이브스트림을 시작하기 위한 요청 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/23
 */
public class LiveStartPushRequest extends BaseModel {

    /** URL 타입 */
    @NotNull
    private UrlTypeEnum urlType;

    /**
     * 스트리밍 URL 정보
     * RTMP: (rtmp://xxxxxxx) 예시: rtmp://192.168.1.1:8080/live
     * RTSP：(userName&password&port) 예시: userName=dji-cloud-api&password=123456&port=8080
     * GB28181：(serverIP&serverPort&serverID&agentID&agentPassword&localPort&channel)
     *  예시: serverIP=192.168.1.1&serverPort=8080&serverID=34000000000000000000&agentID=
     *  300000000010000000000&agentPassword=0000000&localPort=7060&channel=340000000000000000000
     * AGORA：(channel&sn&token&uid)
     *  예시: channel=1ZNDH360010162_39-0-7&sn=1ZNDH360010162&token=006dca67721582a48768ec4d8
     *  17b7b25a86IAB4cw2JgN6iX8BpTPdc3e4S1Iendz94IFJ56aSXKvzAJei27MqF2zyCIgCLIIoBt41+YAQAAQC3jX
     *  5gAgC3jX5gAwC3jX5gBAC3jX5g&uid=50000
     *  주의: 성왕에서 생성된 토큰에는 '+' ' ' 같은 특수문자가 포함될 수 있으며,
     *  URL 인코딩을 해야 하며, 그렇지 않으면 PILOT 측에서 파싱 오류가 발생할 수 있습니다
     */
    @NotNull
    @Valid
    private ILivestreamUrl url;

    /**
     * 비디오 ID
     * 형식: #{uav_sn}/#{camera_id}/#{video_index}
     * 드론 시리얼 번호/페이로드 및 장착 위치 열거형 값/페이로드 렌즈 번호
     */
    @NotNull
    private VideoId videoId;

    /** 비디오 품질 */
    @NotNull
    private VideoQualityEnum videoQuality;

    /**
     * 기본 생성자
     */
    public LiveStartPushRequest() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "LiveStartPushRequest{" +
                "urlType=" + urlType +
                ", url=" + url +
                ", videoId=" + videoId +
                ", videoQuality=" + videoQuality +
                '}';
    }

    /**
     * URL 타입을 반환합니다.
     * 
     * @return URL 타입
     */
    public UrlTypeEnum getUrlType() {
        return urlType;
    }

    /**
     * URL 타입을 설정합니다.
     * 
     * @param urlType 설정할 URL 타입
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LiveStartPushRequest setUrlType(UrlTypeEnum urlType) {
        this.urlType = urlType;
        return this;
    }

    /**
     * 스트리밍 URL을 반환합니다.
     * 
     * @return 스트리밍 URL
     */
    public ILivestreamUrl getUrl() {
        return url;
    }

    /**
     * 스트리밍 URL을 설정합니다.
     * 
     * @param url 설정할 스트리밍 URL
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LiveStartPushRequest setUrl(ILivestreamUrl url) {
        this.url = url;
        return this;
    }

    /**
     * 비디오 ID를 반환합니다.
     * 
     * @return 비디오 ID
     */
    public VideoId getVideoId() {
        return videoId;
    }

    /**
     * 비디오 ID를 설정합니다.
     * 
     * @param videoId 설정할 비디오 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LiveStartPushRequest setVideoId(VideoId videoId) {
        this.videoId = videoId;
        return this;
    }

    /**
     * 비디오 품질을 반환합니다.
     * 
     * @return 비디오 품질
     */
    public VideoQualityEnum getVideoQuality() {
        return videoQuality;
    }

    /**
     * 비디오 품질을 설정합니다.
     * 
     * @param videoQuality 설정할 비디오 품질
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LiveStartPushRequest setVideoQuality(VideoQualityEnum videoQuality) {
        this.videoQuality = videoQuality;
        return this;
    }
}
