package com.dji.sdk.cloudapi.flightarea;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 비행 구역 조회 파일 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 비행 구역 조회 시 반환되는 파일 정보를 담는 클래스입니다.
 * 파일 이름, URL, 체크섬, 크기를 포함하여 비행 구역 파일의 상세 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/17
 */
public class FlightAreaGetFile {

    /**
     * 파일 이름 (필수)
     * 비행 구역 파일의 이름 (geofence_로 시작하고 32자리 문자열로 끝나는 .json 파일)
     */
    @NotNull
    @Pattern(regexp = "^geofence_[A-Za-z0-9]{32}.json$")
    private String name;

    /**
     * 파일 URL (필수)
     * 비행 구역 파일의 다운로드 URL
     */
    @NotNull
    private String url;

    /**
     * 파일 SHA256 서명 (필수)
     * 파일의 무결성을 검증하기 위한 SHA256 체크섬
     */
    @NotNull
    private String checksum;

    /**
     * 파일 크기 (필수)
     * 비행 구역 파일의 크기 (바이트)
     */
    @NotNull
    private Integer size;

    /**
     * 기본 생성자
     */
    public FlightAreaGetFile() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FlightAreaGetFile{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", checksum='" + checksum + '\'' +
                ", size=" + size +
                '}';
    }

    /**
     * 파일 이름을 반환합니다.
     * 
     * @return 파일 이름
     */
    public String getName() {
        return name;
    }

    /**
     * 파일 이름을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param name 설정할 파일 이름
     * @return 현재 FlightAreaGetFile 객체
     */
    public FlightAreaGetFile setName(String name) {
        this.name = name;
        return this;
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
     * 파일 URL을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param url 설정할 파일 URL
     * @return 현재 FlightAreaGetFile 객체
     */
    public FlightAreaGetFile setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * 체크섬을 반환합니다.
     * 
     * @return 체크섬
     */
    public String getChecksum() {
        return checksum;
    }

    /**
     * 체크섬을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param checksum 설정할 체크섬
     * @return 현재 FlightAreaGetFile 객체
     */
    public FlightAreaGetFile setChecksum(String checksum) {
        this.checksum = checksum;
        return this;
    }

    /**
     * 파일 크기를 반환합니다.
     * 
     * @return 파일 크기
     */
    public Integer getSize() {
        return size;
    }

    /**
     * 파일 크기를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param size 설정할 파일 크기
     * @return 현재 FlightAreaGetFile 객체
     */
    public FlightAreaGetFile setSize(Integer size) {
        this.size = size;
        return this;
    }
}
