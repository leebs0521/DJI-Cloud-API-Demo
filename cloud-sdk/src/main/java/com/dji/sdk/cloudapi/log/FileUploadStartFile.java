package com.dji.sdk.cloudapi.log;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 파일 업로드 시작 파일 정보를 나타내는 클래스
 * 파일 업로드를 시작할 때 필요한 파일 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
public class FileUploadStartFile {

    /** 장치 시리얼 번호 */
    @NotNull
    private String deviceSn;

    /** 로그 파일 인덱스 목록 */
    @NotNull
    private List<@Valid LogFileIndex> list;

    /** 로그 모듈 (드론/독) */
    @NotNull
    private LogModuleEnum module;

    /** 객체 키 */
    @NotNull
    private String objectKey;

    /**
     * 기본 생성자
     */
    public FileUploadStartFile() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FileUploadStartFile{" +
                "deviceSn='" + deviceSn + '\'' +
                ", list=" + list +
                ", module=" + module +
                ", objectKey='" + objectKey + '\'' +
                '}';
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
    public FileUploadStartFile setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
        return this;
    }

    /**
     * 로그 파일 인덱스 목록을 반환합니다.
     * 
     * @return 로그 파일 인덱스 목록
     */
    public List<LogFileIndex> getList() {
        return list;
    }

    /**
     * 로그 파일 인덱스 목록을 설정합니다.
     * 
     * @param list 설정할 로그 파일 인덱스 목록
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadStartFile setList(List<LogFileIndex> list) {
        this.list = list;
        return this;
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
    public FileUploadStartFile setModule(LogModuleEnum module) {
        this.module = module;
        return this;
    }

    /**
     * 객체 키를 반환합니다.
     * 
     * @return 객체 키
     */
    public String getObjectKey() {
        return objectKey;
    }

    /**
     * 객체 키를 설정합니다.
     * 
     * @param objectKey 설정할 객체 키
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadStartFile setObjectKey(String objectKey) {
        this.objectKey = objectKey;
        return this;
    }
}
