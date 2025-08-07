package com.dji.sdk.cloudapi.device;

/**
 * 펌웨어 버전 클래스
 * 
 * 이 클래스는 디바이스의 펌웨어 버전 정보를 담습니다.
 * 펌웨어 버전 문자열을 관리합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/4/28
 */
public class FirmwareVersion {

    /**
     * 펌웨어 버전 문자열
     */
    private String firmwareVersion;

    /**
     * 기본 생성자
     */
    public FirmwareVersion() {
    }

    @Override
    public String toString() {
        return "FirmwareVersion{" +
                "firmwareVersion='" + firmwareVersion + '\'' +
                '}';
    }

    /**
     * 펌웨어 버전을 반환합니다.
     * 
     * @return 펌웨어 버전 문자열
     */
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * 펌웨어 버전을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param firmwareVersion 설정할 펌웨어 버전 문자열
     * @return 현재 FirmwareVersion 객체
     */
    public FirmwareVersion setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
        return this;
    }

}
