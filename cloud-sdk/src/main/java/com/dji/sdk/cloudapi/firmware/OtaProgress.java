package com.dji.sdk.cloudapi.firmware;

/**
 * OTA 진행 상황 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 OTA(Over-The-Air) 펌웨어 업데이트의 진행 상황을 담는 클래스입니다.
 * OTA 상태, 진행 데이터, 확장 정보를 포함하여 OTA 펌웨어 업데이트의 진행 상황을 관리합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/7/29
 */
public class OtaProgress {

    /**
     * OTA 진행 상태
     * 현재 OTA 펌웨어 업데이트의 상태 정보
     */
    private OtaProgressStatusEnum status;

    /**
     * OTA 진행 데이터
     * OTA 펌웨어 업데이트의 진행 상황 데이터
     */
    private OtaProgressData progress;

    /**
     * OTA 진행 확장 정보
     * OTA 펌웨어 업데이트의 추가 정보
     */
    private OtaProgressExt ext;

    /**
     * 기본 생성자
     */
    public OtaProgress() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "OtaProgress{" +
                "status=" + status +
                ", progress=" + progress +
                ", ext=" + ext +
                '}';
    }

    /**
     * OTA 진행 상태를 반환합니다.
     * 
     * @return OTA 진행 상태
     */
    public OtaProgressStatusEnum getStatus() {
        return status;
    }

    /**
     * OTA 진행 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param status 설정할 OTA 진행 상태
     * @return 현재 OtaProgress 객체
     */
    public OtaProgress setStatus(OtaProgressStatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * OTA 진행 데이터를 반환합니다.
     * 
     * @return OTA 진행 데이터
     */
    public OtaProgressData getProgress() {
        return progress;
    }

    /**
     * OTA 진행 데이터를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param progress 설정할 OTA 진행 데이터
     * @return 현재 OtaProgress 객체
     */
    public OtaProgress setProgress(OtaProgressData progress) {
        this.progress = progress;
        return this;
    }

    /**
     * OTA 진행 확장 정보를 반환합니다.
     * 
     * @return OTA 진행 확장 정보
     */
    public OtaProgressExt getExt() {
        return ext;
    }

    /**
     * OTA 진행 확장 정보를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param ext 설정할 OTA 진행 확장 정보
     * @return 현재 OtaProgress 객체
     */
    public OtaProgress setExt(OtaProgressExt ext) {
        this.ext = ext;
        return this;
    }
}
