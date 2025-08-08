package com.dji.sdk.cloudapi.log;

import javax.validation.constraints.NotNull;

/**
 * 로그 파일 인덱스 정보를 나타내는 클래스
 * 로그 파일의 메타데이터 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/7
 */
public class LogFileIndex {

    /** 부팅 인덱스 */
    @NotNull
    private Integer bootIndex;

    /** 로그 종료 시간 (타임스탬프) */
    @NotNull
    private Long endTime;

    /** 로그 시작 시간 (타임스탬프) */
    @NotNull
    private Long startTime;

    /** 로그 파일 크기 (바이트) */
    @NotNull
    private Long size;

    /**
     * 기본 생성자
     */
    public LogFileIndex() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "LogFileIndex{" +
                "bootIndex=" + bootIndex +
                ", endTime=" + endTime +
                ", startTime=" + startTime +
                ", size=" + size +
                '}';
    }

    /**
     * 부팅 인덱스를 반환합니다.
     * 
     * @return 부팅 인덱스
     */
    public Integer getBootIndex() {
        return bootIndex;
    }

    /**
     * 부팅 인덱스를 설정합니다.
     * 
     * @param bootIndex 설정할 부팅 인덱스
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LogFileIndex setBootIndex(Integer bootIndex) {
        this.bootIndex = bootIndex;
        return this;
    }

    /**
     * 로그 종료 시간을 반환합니다.
     * 
     * @return 로그 종료 시간 (타임스탬프)
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * 로그 종료 시간을 설정합니다.
     * 
     * @param endTime 설정할 로그 종료 시간 (타임스탬프)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LogFileIndex setEndTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * 로그 시작 시간을 반환합니다.
     * 
     * @return 로그 시작 시간 (타임스탬프)
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * 로그 시작 시간을 설정합니다.
     * 
     * @param startTime 설정할 로그 시작 시간 (타임스탬프)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LogFileIndex setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * 로그 파일 크기를 반환합니다.
     * 
     * @return 로그 파일 크기 (바이트)
     */
    public Long getSize() {
        return size;
    }

    /**
     * 로그 파일 크기를 설정합니다.
     * 
     * @param size 설정할 로그 파일 크기 (바이트)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LogFileIndex setSize(Long size) {
        this.size = size;
        return this;
    }
}
