package com.dji.sdk.cloudapi.livestream;

import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * GB28181 라이브스트림 URL을 나타내는 클래스
 * GB28181 표준 프로토콜을 사용한 라이브스트리밍 URL을 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/13
 */
public class LivestreamGb28181Url extends BaseModel implements ILivestreamUrl {

    /** 서버 IP 주소 */
    @NotNull
    private String serverIP;

    /** 서버 포트 */
    @NotNull
    private Integer serverPort;

    /** 서버 ID */
    @NotNull
    private String serverID;

    /** 에이전트 ID */
    @NotNull
    private String agentID;

    /** 에이전트 비밀번호 */
    @NotNull
    private String agentPassword;

    /** 로컬 포트 */
    @NotNull
    private Integer localPort;

    /** 채널 */
    @NotNull
    private String channel;

    /**
     * 기본 생성자
     */
    public LivestreamGb28181Url() {
    }

    /**
     * GB28181 URL을 문자열로 반환합니다.
     * 
     * @return GB28181 URL 문자열
     */
    @Override
    public String toString() {
        return "serverIP=" + serverIP +
                "&serverPort=" + serverPort +
                "&serverID=" + serverID +
                "&agentID=" + agentID +
                "&agentPassword=" + agentPassword +
                "&localPort=" + localPort +
                "&channel=" + channel;
    }

    /**
     * LivestreamGb28181Url 객체의 복사본을 생성합니다.
     * 
     * @return 복사된 LivestreamGb28181Url 객체
     */
    @Override
    public LivestreamGb28181Url clone() {
        try {
            return (LivestreamGb28181Url) super.clone();
        } catch (CloneNotSupportedException e) {
            return new LivestreamGb28181Url()
                    .setServerIP(serverIP)
                    .setServerPort(serverPort)
                    .setServerID(serverID)
                    .setAgentID(agentID)
                    .setAgentPassword(agentPassword)
                    .setLocalPort(localPort)
                    .setChannel(channel);
        }
    }

    /**
     * 서버 IP 주소를 반환합니다.
     * 
     * @return 서버 IP 주소
     */
    public String getServerIP() {
        return serverIP;
    }

    /**
     * 서버 IP 주소를 설정합니다.
     * 
     * @param serverIP 설정할 서버 IP 주소
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LivestreamGb28181Url setServerIP(String serverIP) {
        this.serverIP = serverIP;
        return this;
    }

    /**
     * 서버 포트를 반환합니다.
     * 
     * @return 서버 포트
     */
    public Integer getServerPort() {
        return serverPort;
    }

    /**
     * 서버 포트를 설정합니다.
     * 
     * @param serverPort 설정할 서버 포트
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LivestreamGb28181Url setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
        return this;
    }

    /**
     * 서버 ID를 반환합니다.
     * 
     * @return 서버 ID
     */
    public String getServerID() {
        return serverID;
    }

    /**
     * 서버 ID를 설정합니다.
     * 
     * @param serverID 설정할 서버 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LivestreamGb28181Url setServerID(String serverID) {
        this.serverID = serverID;
        return this;
    }

    /**
     * 에이전트 ID를 반환합니다.
     * 
     * @return 에이전트 ID
     */
    public String getAgentID() {
        return agentID;
    }

    /**
     * 에이전트 ID를 설정합니다.
     * 
     * @param agentID 설정할 에이전트 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LivestreamGb28181Url setAgentID(String agentID) {
        this.agentID = agentID;
        return this;
    }

    /**
     * 에이전트 비밀번호를 반환합니다.
     * 
     * @return 에이전트 비밀번호
     */
    public String getAgentPassword() {
        return agentPassword;
    }

    /**
     * 에이전트 비밀번호를 설정합니다.
     * 
     * @param agentPassword 설정할 에이전트 비밀번호
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LivestreamGb28181Url setAgentPassword(String agentPassword) {
        this.agentPassword = agentPassword;
        return this;
    }

    /**
     * 로컬 포트를 반환합니다.
     * 
     * @return 로컬 포트
     */
    public Integer getLocalPort() {
        return localPort;
    }

    /**
     * 로컬 포트를 설정합니다.
     * 
     * @param localPort 설정할 로컬 포트
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LivestreamGb28181Url setLocalPort(Integer localPort) {
        this.localPort = localPort;
        return this;
    }

    /**
     * 채널을 반환합니다.
     * 
     * @return 채널
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 채널을 설정합니다.
     * 
     * @param channel 설정할 채널
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LivestreamGb28181Url setChannel(String channel) {
        this.channel = channel;
        return this;
    }
}
