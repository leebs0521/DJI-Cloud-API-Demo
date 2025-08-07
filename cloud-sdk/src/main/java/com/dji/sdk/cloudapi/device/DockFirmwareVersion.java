package com.dji.sdk.cloudapi.device;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 도크 펌웨어 버전 클래스
 * 
 * 이 클래스는 도크의 펌웨어 버전 정보를 담습니다.
 * 펌웨어 버전, 호환성 상태, 펌웨어 업그레이드 상태 등을 포함합니다.
 * 
 * @author sean
 * @version 1.0
 * @date 2022/4/28
 */
public class DockFirmwareVersion {

    /**
     * 펌웨어 버전 문자열
     */
    private String firmwareVersion;

    /**
     * 호환성 상태 (true: 호환성 확인 필요, false: 호환성 확인 불필요)
     */
    @JsonProperty("compatible_status")
    private Boolean needCompatibleStatus;

    /**
     * 펌웨어 업그레이드 상태 (true: 업그레이드 중, false: 업그레이드 완료)
     */
    private Boolean firmwareUpgradeStatus;

    /**
     * 기본 생성자
     */
    public DockFirmwareVersion() {
    }

    @Override
    public String toString() {
        return "DockFirmwareVersion{" +
                "firmwareVersion='" + firmwareVersion + '\'' +
                ", compatibleStatus=" + needCompatibleStatus +
                ", firmwareUpgradeStatus=" + firmwareUpgradeStatus +
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
     * @return 현재 DockFirmwareVersion 객체
     */
    public DockFirmwareVersion setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
        return this;
    }

    /**
     * 호환성 상태를 반환합니다.
     * 
     * @return 호환성 상태 (true: 호환성 확인 필요, false: 호환성 확인 불필요)
     */
    public Boolean getNeedCompatibleStatus() {
        return needCompatibleStatus;
    }

    /**
     * 호환성 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param needCompatibleStatus 설정할 호환성 상태
     * @return 현재 DockFirmwareVersion 객체
     */
    public DockFirmwareVersion setNeedCompatibleStatus(Boolean needCompatibleStatus) {
        this.needCompatibleStatus = needCompatibleStatus;
        return this;
    }

    /**
     * 펌웨어 업그레이드 상태를 반환합니다.
     * 
     * @return 펌웨어 업그레이드 상태 (true: 업그레이드 중, false: 업그레이드 완료)
     */
    public Boolean getFirmwareUpgradeStatus() {
        return firmwareUpgradeStatus;
    }

    /**
     * 펌웨어 업그레이드 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param firmwareUpgradeStatus 설정할 펌웨어 업그레이드 상태
     * @return 현재 DockFirmwareVersion 객체
     */
    public DockFirmwareVersion setFirmwareUpgradeStatus(Boolean firmwareUpgradeStatus) {
        this.firmwareUpgradeStatus = firmwareUpgradeStatus;
        return this;
    }
}
