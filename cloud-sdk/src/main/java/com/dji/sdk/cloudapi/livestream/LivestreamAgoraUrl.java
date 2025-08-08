package com.dji.sdk.cloudapi.livestream;

import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * Agora 라이브스트림 URL을 나타내는 클래스
 * Agora 스트리밍 서비스를 사용한 라이브스트리밍 URL을 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/13
 */
public class LivestreamAgoraUrl extends BaseModel implements ILivestreamUrl {

    /** Agora 채널명 */
    @NotNull
    private String channel;

    /** 장치 시리얼 번호 */
    @NotNull
    private String sn;

    /** Agora 토큰 */
    @NotNull
    private String token;

    /** 사용자 ID */
    @NotNull
    private Integer uid;

    /**
     * 기본 생성자
     */
    public LivestreamAgoraUrl() {
    }

    /**
     * Agora URL을 문자열로 반환합니다.
     * 토큰은 URL 인코딩을 적용합니다.
     * 
     * @return Agora URL 문자열
     */
    @Override
    public String toString() {
        return "channel=" + channel +
                "&sn=" + sn +
                "&token=" + URLEncoder.encode(token, Charset.defaultCharset()) +
                "&uid=" + uid;
    }

    /**
     * LivestreamAgoraUrl 객체의 복사본을 생성합니다.
     * 
     * @return 복사된 LivestreamAgoraUrl 객체
     */
    @Override
    public LivestreamAgoraUrl clone() {
        try {
            return (LivestreamAgoraUrl) super.clone();
        } catch (CloneNotSupportedException e) {
            return new LivestreamAgoraUrl().setSn(sn).setToken(token).setChannel(channel).setUid(uid);
        }
    }

    /**
     * Agora 채널명을 반환합니다.
     * 
     * @return Agora 채널명
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Agora 채널명을 설정합니다.
     * 
     * @param channel 설정할 Agora 채널명
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LivestreamAgoraUrl setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    /**
     * 장치 시리얼 번호를 반환합니다.
     * 
     * @return 장치 시리얼 번호
     */
    public String getSn() {
        return sn;
    }

    /**
     * 장치 시리얼 번호를 설정합니다.
     * 
     * @param sn 설정할 장치 시리얼 번호
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LivestreamAgoraUrl setSn(String sn) {
        this.sn = sn;
        return this;
    }

    /**
     * Agora 토큰을 반환합니다.
     * 
     * @return Agora 토큰
     */
    public String getToken() {
        return token;
    }

    /**
     * Agora 토큰을 설정합니다.
     * 
     * @param token 설정할 Agora 토큰
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LivestreamAgoraUrl setToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * 사용자 ID를 반환합니다.
     * 
     * @return 사용자 ID
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 사용자 ID를 설정합니다.
     * 
     * @param uid 설정할 사용자 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LivestreamAgoraUrl setUid(Integer uid) {
        this.uid = uid;
        return this;
    }
}
