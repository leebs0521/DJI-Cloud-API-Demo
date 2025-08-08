package com.dji.sdk.cloudapi.log;

/**
 * 파일 업로드 진행 상황 파일 정보를 나타내는 클래스
 * 개별 파일의 업로드 진행 상황과 메타데이터를 관리합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/9
 */
public class FileUploadProgressFile {

    /** 로그 모듈 (드론/독) */
    private LogModuleEnum module;

    /** 파일 크기 (바이트) */
    private Long size;

    /** 장치 시리얼 번호 */
    private String deviceSn;

    /** 파일 키 */
    private String key;

    /** 파일 지문 (해시값) */
    private String fingerprint;

    /** 로그 파일 진행 상황 */
    private LogFileProgress progress;

    /**
     * 기본 생성자
     */
    public FileUploadProgressFile() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FileUploadProgressFile{" +
                "module=" + module +
                ", size=" + size +
                ", deviceSn='" + deviceSn + '\'' +
                ", key='" + key + '\'' +
                ", fingerprint='" + fingerprint + '\'' +
                ", progress=" + progress +
                '}';
    }

    /**
     * 로그 모듈을 반환합니다.
     * 
     * @return 로그 모듈
     */
    public LogModuleEnum getModule() {
        return module;
    }

    /**
     * 로그 모듈을 설정합니다.
     * 
     * @param module 설정할 로그 모듈
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadProgressFile setModule(LogModuleEnum module) {
        this.module = module;
        return this;
    }

    /**
     * 파일 크기를 반환합니다.
     * 
     * @return 파일 크기 (바이트)
     */
    public Long getSize() {
        return size;
    }

    /**
     * 파일 크기를 설정합니다.
     * 
     * @param size 설정할 파일 크기 (바이트)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadProgressFile setSize(Long size) {
        this.size = size;
        return this;
    }

    /**
     * 장치 시리얼 번호를 반환합니다.
     * 
     * @return 장치 시리얼 번호
     */
    public String getDeviceSn() {
        return deviceSn;
    }

    /**
     * 장치 시리얼 번호를 설정합니다.
     * 
     * @param deviceSn 설정할 장치 시리얼 번호
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadProgressFile setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
        return this;
    }

    /**
     * 파일 키를 반환합니다.
     * 
     * @return 파일 키
     */
    public String getKey() {
        return key;
    }

    /**
     * 파일 키를 설정합니다.
     * 
     * @param key 설정할 파일 키
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadProgressFile setKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * 파일 지문을 반환합니다.
     * 
     * @return 파일 지문 (해시값)
     */
    public String getFingerprint() {
        return fingerprint;
    }

    /**
     * 파일 지문을 설정합니다.
     * 
     * @param fingerprint 설정할 파일 지문 (해시값)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadProgressFile setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
        return this;
    }

    /**
     * 로그 파일 진행 상황을 반환합니다.
     * 
     * @return 로그 파일 진행 상황
     */
    public LogFileProgress getProgress() {
        return progress;
    }

    /**
     * 로그 파일 진행 상황을 설정합니다.
     * 
     * @param progress 설정할 로그 파일 진행 상황
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadProgressFile setProgress(LogFileProgress progress) {
        this.progress = progress;
        return this;
    }
}
