package com.dji.sdk.cloudapi.log;

import java.util.List;

/**
 * 파일 업로드 목록 파일 정보를 나타내는 클래스
 * 파일 업로드 목록 조회 시 반환되는 파일 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
public class FileUploadListFile {

    /** 장치 시리얼 번호 */
    private String deviceSn;

    /** 로그 파일 인덱스 목록 */
    private List<LogFileIndex> list;

    /** 로그 모듈 (드론/독) */
    private LogModuleEnum module;

    /** 결과 코드 */
    private Integer result;

    /**
     * 기본 생성자
     */
    public FileUploadListFile() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "FileUploadListFile{" +
                "deviceSn='" + deviceSn + '\'' +
                ", list=" + list +
                ", module=" + module +
                ", result=" + result +
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
    public FileUploadListFile setDeviceSn(String deviceSn) {
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
    public FileUploadListFile setList(List<LogFileIndex> list) {
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
    public FileUploadListFile setModule(LogModuleEnum module) {
        this.module = module;
        return this;
    }

    /**
     * 결과 코드를 반환합니다.
     * 
     * @return 결과 코드
     */
    public Integer getResult() {
        return result;
    }

    /**
     * 결과 코드를 설정합니다.
     * 
     * @param result 설정할 결과 코드
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FileUploadListFile setResult(Integer result) {
        this.result = result;
        return this;
    }
}
