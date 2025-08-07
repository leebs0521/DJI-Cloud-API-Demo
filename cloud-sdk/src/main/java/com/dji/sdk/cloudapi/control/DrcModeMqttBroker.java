package com.dji.sdk.cloudapi.control;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * DRC 모드 MQTT 브로커 설정 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 DRC 모드에서 사용할 MQTT 브로커의 설정 정보를 정의합니다.
 * 브로커 주소, 인증 정보, 클라이언트 ID, 만료 시간, TLS 설정 등을 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/1/11
 */
public class DrcModeMqttBroker {

    /**
     * MQTT 브로커 주소 (필수)
     * MQTT 브로커의 서버 주소
     */
    @NotNull
    private String address;

    /**
     * MQTT 브로커 사용자명 (필수)
     * MQTT 브로커에 접속할 사용자명
     */
    @NotNull
    private String username;

    /**
     * MQTT 브로커 비밀번호 (필수)
     * MQTT 브로커에 접속할 비밀번호
     */
    @NotNull
    private String password;

    /**
     * MQTT 클라이언트 ID (필수)
     * MQTT 브로커에 접속할 클라이언트 식별자
     */
    @NotNull
    private String clientId;

    /**
     * 만료 시간 (필수)
     * 1234567890 ~ 9999999999 범위, MQTT 연결의 만료 시간 (타임스탬프)
     */
    @NotNull
    @Min(1234567890)
    @Max(9999999999L)
    private Long expireTime;

    /**
     * TLS 활성화 여부 (필수)
     * MQTT 연결 시 TLS 암호화 사용 여부
     */
    @NotNull
    private Boolean enableTls;

    /**
     * 기본 생성자
     */
    public DrcModeMqttBroker() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "DrcModeMqttBroker{" +
                "address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", clientId='" + clientId + '\'' +
                ", expireTime=" + expireTime +
                ", enableTls=" + enableTls +
                '}';
    }

    /**
     * MQTT 브로커 주소를 반환합니다.
     * 
     * @return MQTT 브로커 주소
     */
    public String getAddress() {
        return address;
    }

    /**
     * MQTT 브로커 주소를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param address 설정할 MQTT 브로커 주소
     * @return 현재 DrcModeMqttBroker 객체
     */
    public DrcModeMqttBroker setAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * MQTT 브로커 사용자명을 반환합니다.
     * 
     * @return MQTT 브로커 사용자명
     */
    public String getUsername() {
        return username;
    }

    /**
     * MQTT 브로커 사용자명을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param username 설정할 MQTT 브로커 사용자명
     * @return 현재 DrcModeMqttBroker 객체
     */
    public DrcModeMqttBroker setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * MQTT 브로커 비밀번호를 반환합니다.
     * 
     * @return MQTT 브로커 비밀번호
     */
    public String getPassword() {
        return password;
    }

    /**
     * MQTT 브로커 비밀번호를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param password 설정할 MQTT 브로커 비밀번호
     * @return 현재 DrcModeMqttBroker 객체
     */
    public DrcModeMqttBroker setPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * MQTT 클라이언트 ID를 반환합니다.
     * 
     * @return MQTT 클라이언트 ID
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * MQTT 클라이언트 ID를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param clientId 설정할 MQTT 클라이언트 ID
     * @return 현재 DrcModeMqttBroker 객체
     */
    public DrcModeMqttBroker setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    /**
     * 만료 시간을 반환합니다.
     * 
     * @return 만료 시간 (타임스탬프)
     */
    public Long getExpireTime() {
        return expireTime;
    }

    /**
     * 만료 시간을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param expireTime 설정할 만료 시간 (타임스탬프)
     * @return 현재 DrcModeMqttBroker 객체
     */
    public DrcModeMqttBroker setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    /**
     * TLS 활성화 여부를 반환합니다.
     * 
     * @return TLS 활성화 여부
     */
    public Boolean getEnableTls() {
        return enableTls;
    }

    /**
     * TLS 활성화 여부를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param enableTls 설정할 TLS 활성화 여부
     * @return 현재 DrcModeMqttBroker 객체
     */
    public DrcModeMqttBroker setEnableTls(Boolean enableTls) {
        this.enableTls = enableTls;
        return this;
    }
}
