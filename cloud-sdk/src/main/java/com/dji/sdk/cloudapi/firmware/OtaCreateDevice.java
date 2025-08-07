package com.dji.sdk.cloudapi.firmware;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * OTA 생성 디바이스 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 OTA(Over-The-Air) 펌웨어 업데이트를 생성할 때 사용되는 디바이스 정보를 정의합니다.
 * 시리얼 번호, 제품 버전, 파일 정보, 펌웨어 업그레이드 타입 등을 포함합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/8/16
 */
public class OtaCreateDevice {

    /**
     * 시리얼 번호 (필수)
     * 디바이스의 고유 시리얼 번호
     */
    @NotNull
    private String sn;

    /**
     * 제품 버전 (필수)
     * 제품의 버전 정보 (형식: XX.XX.XXXX)
     */
    @NotNull
    @Pattern(regexp = "^\\d{2}\\.\\d{2}\\.\\d{4}$")
    private String productVersion;

    /**
     * 파일 URL (필수)
     * 펌웨어 파일의 다운로드 URL
     */
    @NotNull
    private String fileUrl;

    /**
     * MD5 해시 (필수)
     * 펌웨어 파일의 MD5 해시값
     */
    @NotNull
    private String md5;

    /**
     * 파일 크기 (필수)
     * 펌웨어 파일의 크기 (바이트)
     */
    @NotNull
    private Long fileSize;

    /**
     * 펌웨어 업그레이드 타입 (필수)
     * 펌웨어 업그레이드의 타입
     */
    @NotNull
    private FirmwareUpgradeTypeEnum firmwareUpgradeType;

    /**
     * 파일 이름 (필수)
     * 펌웨어 파일의 이름
     */
    @NotNull
    private String fileName;

    /**
     * 기본 생성자
     */
    public OtaCreateDevice() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "OtaCreateDevice{" +
                "sn='" + sn + '\'' +
                ", productVersion='" + productVersion + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", md5='" + md5 + '\'' +
                ", fileSize=" + fileSize +
                ", firmwareUpgradeType=" + firmwareUpgradeType +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    /**
     * 시리얼 번호를 반환합니다.
     * 
     * @return 시리얼 번호
     */
    public String getSn() {
        return sn;
    }

    /**
     * 시리얼 번호를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param sn 설정할 시리얼 번호
     * @return 현재 OtaCreateDevice 객체
     */
    public OtaCreateDevice setSn(String sn) {
        this.sn = sn;
        return this;
    }

    /**
     * 제품 버전을 반환합니다.
     * 
     * @return 제품 버전
     */
    public String getProductVersion() {
        return productVersion;
    }

    /**
     * 제품 버전을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param productVersion 설정할 제품 버전
     * @return 현재 OtaCreateDevice 객체
     */
    public OtaCreateDevice setProductVersion(String productVersion) {
        this.productVersion = productVersion;
        return this;
    }

    /**
     * 파일 URL을 반환합니다.
     * 
     * @return 파일 URL
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * 파일 URL을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param fileUrl 설정할 파일 URL
     * @return 현재 OtaCreateDevice 객체
     */
    public OtaCreateDevice setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
        return this;
    }

    /**
     * MD5 해시를 반환합니다.
     * 
     * @return MD5 해시
     */
    public String getMd5() {
        return md5;
    }

    /**
     * MD5 해시를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param md5 설정할 MD5 해시
     * @return 현재 OtaCreateDevice 객체
     */
    public OtaCreateDevice setMd5(String md5) {
        this.md5 = md5;
        return this;
    }

    /**
     * 파일 크기를 반환합니다.
     * 
     * @return 파일 크기
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * 파일 크기를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param fileSize 설정할 파일 크기
     * @return 현재 OtaCreateDevice 객체
     */
    public OtaCreateDevice setFileSize(Long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    /**
     * 펌웨어 업그레이드 타입을 반환합니다.
     * 
     * @return 펌웨어 업그레이드 타입
     */
    public FirmwareUpgradeTypeEnum getFirmwareUpgradeType() {
        return firmwareUpgradeType;
    }

    /**
     * 펌웨어 업그레이드 타입을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param firmwareUpgradeType 설정할 펌웨어 업그레이드 타입
     * @return 현재 OtaCreateDevice 객체
     */
    public OtaCreateDevice setFirmwareUpgradeType(FirmwareUpgradeTypeEnum firmwareUpgradeType) {
        this.firmwareUpgradeType = firmwareUpgradeType;
        return this;
    }

    /**
     * 파일 이름을 반환합니다.
     * 
     * @return 파일 이름
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 파일 이름을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param fileName 설정할 파일 이름
     * @return 현재 OtaCreateDevice 객체
     */
    public OtaCreateDevice setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }
}
