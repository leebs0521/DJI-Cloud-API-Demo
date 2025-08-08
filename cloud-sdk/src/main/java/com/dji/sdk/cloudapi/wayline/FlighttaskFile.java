package com.dji.sdk.cloudapi.wayline;

import javax.validation.constraints.NotNull;

/**
 * 비행 작업 파일 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업에서 사용할 파일 정보를 정의합니다.
 * 웨이라인 파일의 URL과 무결성을 확인하기 위한 서명을 포함하여
 * 안전하고 신뢰할 수 있는 파일 다운로드를 보장합니다.
 * 
 * 주요 구성 요소:
 * - url: 파일 URL
 * - fingerprint: 파일 서명
 * 
 * 이 클래스는 웨이라인 비행 작업 준비 시 필요한
 * 파일 정보를 제공하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/6
 */
public class FlighttaskFile {

    /**
     * 파일 URL
     * 
     * 웨이라인 파일의 다운로드 URL입니다.
     * 클라우드 스토리지에서 파일에 접근할 수 있는
     * 직접 다운로드 링크를 제공합니다.
     * 
     * 예시: "https://storage.example.com/wayline/waylineFile.kmz"
     */
    @NotNull
    private String url;

    /**
     * 파일 서명
     * 
     * 웨이라인 파일의 무결성을 확인하기 위한 서명값입니다.
     * 파일이 전송 과정에서 변경되지 않았음을 보장하는
     * 해시 값 또는 체크섬을 포함합니다.
     * 
     * 예시: "a1b2c3d4e5f6..."
     */
    @NotNull
    private String fingerprint;

    public FlighttaskFile() {
    }

    @Override
    public String toString() {
        return "FlighttaskFile{" +
                "url='" + url + '\'' +
                ", fingerprint='" + fingerprint + '\'' +
                '}';
    }

    /**
     * 파일 URL을 반환합니다.
     * 
     * @return 파일 URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 파일 URL을 설정합니다.
     * 
     * @param url 파일 URL
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskFile setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * 파일 서명을 반환합니다.
     * 
     * @return 파일 서명
     */
    public String getFingerprint() {
        return fingerprint;
    }

    /**
     * 파일 서명을 설정합니다.
     * 
     * @param fingerprint 파일 서명
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskFile setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
        return this;
    }
}
