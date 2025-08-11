package com.dji.sample.manage.model.dto;

import com.dji.sample.common.error.CommonErrorEnum;
import com.dji.sdk.cloudapi.livestream.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 라이브스트림 URL 설정 프로퍼티 클래스
 * 
 * DJI Cloud API의 라이브스트림 URL 설정을 관리하는 프로퍼티 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 다중 라이브스트림 프로토콜 지원
 *    - Agora, RTMP, RTSP, GB28181, WHIP 프로토콜 지원
 *    - 각 프로토콜별 전용 URL 설정 관리
 *    - 프로토콜 타입별 동적 URL 선택 기능
 * 
 * 2. Spring Boot 설정 통합
 *    - @ConfigurationProperties를 통한 설정 파일 바인딩
 *    - "livestream.url" 접두사로 설정 그룹화
 *    - 정적 필드를 통한 전역 접근 가능
 * 
 * 3. 라이브스트림 URL 팩토리 기능
 *    - UrlTypeEnum 기반 URL 타입별 객체 반환
 *    - 런타임 중 프로토콜별 URL 동적 선택
 *    - 잘못된 URL 타입에 대한 예외 처리
 * 
 * 4. 설정 주입 및 관리
 *    - Spring의 의존성 주입을 통한 설정 값 자동 바인딩
 *    - setter 메서드를 통한 정적 필드 값 설정
 *    - 타입 안전한 설정 값 관리
 * 
 * 이 클래스는 application.yml 파일의 다음과 같은 설정을 바인딩합니다:
 * livestream:
 *   url:
 *     agora: { ... }
 *     rtmp: { ... }
 *     rtsp: { ... }
 *     gb28181: { ... }
 *     whip: { ... }
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/13
 */
@Data
@ConfigurationProperties("livestream.url")
@Configuration
public class LiveStreamProperty {

    /**
     * Agora 라이브스트림 URL 설정
     * Agora 프로토콜을 사용한 라이브스트림 URL 정보
     */
    private static LivestreamAgoraUrl agora;

    /**
     * RTMP 라이브스트림 URL 설정
     * RTMP 프로토콜을 사용한 라이브스트림 URL 정보
     */
    private static LivestreamRtmpUrl rtmp;

    /**
     * RTSP 라이브스트림 URL 설정
     * RTSP 프로토콜을 사용한 라이브스트림 URL 정보
     */
    private static LivestreamRtspUrl rtsp;

    /**
     * GB28181 라이브스트림 URL 설정
     * GB28181 프로토콜을 사용한 라이브스트림 URL 정보
     */
    private static LivestreamGb28181Url gb28181;

    /**
     * WHIP 라이브스트림 URL 설정
     * WHIP(WebRTC) 프로토콜을 사용한 라이브스트림 URL 정보
     */
    private static LivestreamWhipUrl whip;

    /**
     * Agora URL 설정을 설정합니다.
     * 
     * @param agora 설정할 Agora URL 객체
     */
    public void setAgora(LivestreamAgoraUrl agora) {
        LiveStreamProperty.agora = agora;
    }

    /**
     * RTMP URL 설정을 설정합니다.
     * 
     * @param rtmp 설정할 RTMP URL 객체
     */
    public void setRtmp(LivestreamRtmpUrl rtmp) {
        LiveStreamProperty.rtmp = rtmp;
    }

    /**
     * RTSP URL 설정을 설정합니다.
     * 
     * @param rtsp 설정할 RTSP URL 객체
     */
    public void setRtsp(LivestreamRtspUrl rtsp) {
        LiveStreamProperty.rtsp = rtsp;
    }

    /**
     * GB28181 URL 설정을 설정합니다.
     * 
     * @param gb28181 설정할 GB28181 URL 객체
     */
    public void setGb28181(LivestreamGb28181Url gb28181) {
        LiveStreamProperty.gb28181 = gb28181;
    }

    /**
     * WHIP URL 설정을 설정합니다.
     * 
     * @param webrtc 설정할 WHIP URL 객체
     */
    public void setWhip(LivestreamWhipUrl webrtc) {
        LiveStreamProperty.whip = webrtc;
    }

    /**
     * 지정된 URL 타입에 해당하는 라이브스트림 URL 객체를 반환합니다.
     * 
     * 이 메서드는 UrlTypeEnum을 기반으로 해당하는 라이브스트림 URL 객체를
     * 반환하며, 지원하지 않는 URL 타입의 경우 예외를 발생시킵니다.
     * 
     * @param type 조회할 라이브스트림 URL 타입
     * @return 해당 타입의 라이브스트림 URL 객체
     * @throws RuntimeException 지원하지 않는 URL 타입인 경우
     */
    public static ILivestreamUrl get(UrlTypeEnum type) {
        switch (type) {
            case AGORA:
                return agora;
            case RTMP:
                return rtmp;
            case RTSP:
                return rtsp;
            case GB28181:
                return gb28181;
            case WHIP:
                return whip;
        }
        throw new RuntimeException(CommonErrorEnum.ILLEGAL_ARGUMENT.getMessage());
    }
}
