package com.dji.sdk.cloudapi.wayline;

import javax.validation.constraints.NotNull;

/**
 * 비행 작업 생성 파일 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업 생성 시 필요한
 * 파일 정보를 정의합니다.
 * 
 * 주요 구성 요소:
 * - url: 파일 URL
 * - sign: MD5 서명
 * 
 * 이 클래스는 비행 작업 생성 시 필요한 웨이라인 파일의
 * 다운로드 URL과 무결성 검증을 위한 MD5 서명을 제공합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/6/6
 */
public class FlighttaskCreateFile {

    /**
     * 파일 URL
     * 
     * 웨이라인 파일의 다운로드 URL입니다.
     * 클라우드에서 웨이라인 파일을 다운로드하는 데 사용됩니다.
     */
    @NotNull
    private String url;

    /**
     * MD5 서명
     * 
     * 웨이라인 파일의 MD5 해시 값입니다.
     * 파일의 무결성을 검증하는 데 사용됩니다.
     */
    @NotNull
    private String sign;

    public FlighttaskCreateFile() {}

    @Override
    public String toString() {
        return "FlighttaskCreateFile{" +
                "url='" + url + '\'' +
                ", sign='" + sign + '\'' +
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
    public FlighttaskCreateFile setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * MD5 서명을 반환합니다.
     * 
     * @return MD5 서명
     */
    public String getSign() {
        return sign;
    }

    /**
     * MD5 서명을 설정합니다.
     * 
     * @param sign MD5 서명
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskCreateFile setSign(String sign) {
        this.sign = sign;
        return this;
    }
}