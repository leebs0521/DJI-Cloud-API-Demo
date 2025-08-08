package com.dji.sdk.cloudapi.livestream;

import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * RTSP 라이브스트림 URL을 나타내는 클래스
 * RTSP 프로토콜을 사용한 라이브스트리밍 연결 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/13
 */
public class LivestreamRtspUrl extends BaseModel implements ILivestreamUrl {

    /** RTSP 연결 사용자명 */
    @NotNull
    private String username;

    /** RTSP 연결 비밀번호 */
    @NotNull
    private String password;

    /** RTSP 연결 포트 */
    @NotNull
    private Integer port;

    /**
     * 기본 생성자
     */
    public LivestreamRtspUrl() {
    }

    /**
     * RTSP 연결 정보를 문자열로 반환합니다.
     * 
     * @return RTSP 연결 정보 문자열
     */
    @Override
    public String toString() {
        return "userName=" + username +
                "&password=" + password +
                "&port=" + port;
    }

    /**
     * LivestreamRtspUrl 객체의 복사본을 생성합니다.
     * 
     * @return 복사된 LivestreamRtspUrl 객체
     */
    @Override
    public LivestreamRtspUrl clone() {
        try {
            return (LivestreamRtspUrl) super.clone();
        } catch (CloneNotSupportedException e) {
            return new LivestreamRtspUrl().setUsername(username).setPassword(password).setPort(port);
        }
    }

    /**
     * 사용자명을 반환합니다.
     * 
     * @return RTSP 연결 사용자명
     */
    public String getUsername() {
        return username;
    }

    /**
     * 사용자명을 설정합니다.
     * 
     * @param username 설정할 사용자명
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LivestreamRtspUrl setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * 비밀번호를 반환합니다.
     * 
     * @return RTSP 연결 비밀번호
     */
    public String getPassword() {
        return password;
    }

    /**
     * 비밀번호를 설정합니다.
     * 
     * @param password 설정할 비밀번호
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LivestreamRtspUrl setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * 포트를 반환합니다.
     * 
     * @return RTSP 연결 포트
     */
    public Integer getPort() {
        return port;
    }

    /**
     * 포트를 설정합니다.
     * 
     * @param port 설정할 포트
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LivestreamRtspUrl setPort(Integer port) {
        this.port = port;
        return this;
    }
}
