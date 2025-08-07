package com.dji.sdk.cloudapi.flightarea;

/**
 * 비행 구역 파일 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 비행 구역 파일의 정보를 담는 클래스입니다.
 * 파일 이름과 체크섬을 포함하여 비행 구역 파일의 기본 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/17
 */
public class FlightAreaFile {

    /**
     * 커스텀 비행 구역 파일 이름
     * 비행 구역 파일의 이름
     */
    private String name;

    /**
     * 파일 SHA256 서명
     * 파일의 무결성을 검증하기 위한 SHA256 체크섬
     */
    private String checksum;

    /**
     * 기본 생성자
     */
    public FlightAreaFile() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FlightAreaFile{" +
                "name='" + name + '\'' +
                ", checksum='" + checksum + '\'' +
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
     * @return 현재 FlightAreaFile 객체
     */
    public FlightAreaFile setName(String name) {
        this.name = name;
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
     * @return 현재 FlightAreaFile 객체
     */
    public FlightAreaFile setChecksum(String checksum) {
        this.checksum = checksum;
        return this;
    }
}
