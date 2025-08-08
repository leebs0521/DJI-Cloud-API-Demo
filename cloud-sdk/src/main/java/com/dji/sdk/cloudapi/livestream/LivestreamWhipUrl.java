package com.dji.sdk.cloudapi.livestream;

import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * WHIP 라이브스트림 URL을 나타내는 클래스
 * WHIP(WebRTC-HTTP Ingestion Protocol) 프로토콜을 사용한 라이브스트리밍 URL을 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/13
 */
public class LivestreamWhipUrl extends BaseModel implements ILivestreamUrl {

    /** WHIP 스트리밍 URL */
    @NotNull
    private String url;

    /**
     * 기본 생성자
     */
    public LivestreamWhipUrl() {
    }

    /**
     * WHIP URL을 문자열로 반환합니다.
     * 
     * @return WHIP URL 문자열
     */
    @Override
    public String toString() {
        return url;
    }

    /**
     * LivestreamWhipUrl 객체의 복사본을 생성합니다.
     * 
     * @return 복사된 LivestreamWhipUrl 객체
     */
    @Override
    public LivestreamWhipUrl clone() {
        try {
            return (LivestreamWhipUrl) super.clone();
        } catch (CloneNotSupportedException e) {
            return new LivestreamWhipUrl().setUrl(url);
        }
    }

    /**
     * WHIP URL을 반환합니다.
     * 
     * @return WHIP URL 문자열
     */
    public String getUrl() {
        return url;
    }

    /**
     * WHIP URL을 설정합니다.
     * 
     * @param url 설정할 WHIP URL
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LivestreamWhipUrl setUrl(String url) {
        this.url = url;
        return this;
    }
}
