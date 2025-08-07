package com.dji.sdk.cloudapi.config;

import com.dji.sdk.common.BaseModel;

import javax.validation.constraints.NotNull;

/**
 * 제품 설정 응답 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 제품 설정 정보를 응답할 때 사용됩니다.
 * NTP 서버 호스트, 앱 ID, 앱 키, 앱 라이선스 등의 설정 정보를 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/10
 */
public class ProductConfigResponse extends BaseModel {

    /**
     * NTP 서버 호스트
     * 네트워크 시간 프로토콜 서버의 호스트 주소
     */
    private String ntpServerHost;

    /**
     * 앱 ID (필수)
     * 애플리케이션의 고유 식별자
     */
    @NotNull
    private String appId;

    /**
     * 앱 키 (필수)
     * 애플리케이션의 인증 키
     */
    @NotNull
    private String appKey;

    /**
     * 앱 라이선스 (필수)
     * 애플리케이션의 라이선스 정보
     */
    @NotNull
    private String appLicense;

    /**
     * 기본 생성자
     */
    public ProductConfigResponse() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "ProductConfigResponse{" +
                "ntpServerHost='" + ntpServerHost + '\'' +
                ", appId='" + appId + '\'' +
                ", appKey='" + appKey + '\'' +
                ", appLicense='" + appLicense + '\'' +
                '}';
    }

    /**
     * NTP 서버 호스트를 반환합니다.
     * 
     * @return NTP 서버 호스트
     */
    public String getNtpServerHost() {
        return ntpServerHost;
    }

    /**
     * NTP 서버 호스트를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param ntpServerHost 설정할 NTP 서버 호스트
     * @return 현재 ProductConfigResponse 객체
     */
    public ProductConfigResponse setNtpServerHost(String ntpServerHost) {
        this.ntpServerHost = ntpServerHost;
        return this;
    }

    /**
     * 앱 ID를 반환합니다.
     * 
     * @return 앱 ID
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 앱 ID를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param appId 설정할 앱 ID
     * @return 현재 ProductConfigResponse 객체
     */
    public ProductConfigResponse setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    /**
     * 앱 키를 반환합니다.
     * 
     * @return 앱 키
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * 앱 키를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param appKey 설정할 앱 키
     * @return 현재 ProductConfigResponse 객체
     */
    public ProductConfigResponse setAppKey(String appKey) {
        this.appKey = appKey;
        return this;
    }

    /**
     * 앱 라이선스를 반환합니다.
     * 
     * @return 앱 라이선스
     */
    public String getAppLicense() {
        return appLicense;
    }

    /**
     * 앱 라이선스를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param appLicense 설정할 앱 라이선스
     * @return 현재 ProductConfigResponse 객체
     */
    public ProductConfigResponse setAppLicense(String appLicense) {
        this.appLicense = appLicense;
        return this;
    }
}
