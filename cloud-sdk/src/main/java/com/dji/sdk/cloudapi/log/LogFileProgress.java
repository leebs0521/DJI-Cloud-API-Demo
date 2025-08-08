package com.dji.sdk.cloudapi.log;

/**
 * 로그 파일 진행 상황을 나타내는 클래스
 * 로그 파일 업로드 과정의 진행 상황과 상태 정보를 관리합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/9
 */
public class LogFileProgress {

    /** 현재 단계 */
    private Integer currentStep;

    /** 전체 단계 수 */
    private Integer totalStep;

    /** 진행률 (퍼센트) */
    private Integer progress;

    /** 완료 시간 (타임스탬프) */
    private Long finishTime;

    /** 업로드 속도 (MB/s) */
    private Float uploadRate;

    /** 업로드 상태 */
    private FileUploadStatusEnum status;

    /** 결과 코드 */
    private Integer result;

    /**
     * 기본 생성자
     */
    public LogFileProgress() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "LogFileProgress{" +
                "currentStep=" + currentStep +
                ", totalStep=" + totalStep +
                ", progress=" + progress +
                ", finishTime=" + finishTime +
                ", uploadRate=" + uploadRate +
                ", status=" + status +
                ", result=" + result +
                '}';
    }

    /**
     * 현재 단계를 반환합니다.
     * 
     * @return 현재 단계
     */
    public Integer getCurrentStep() {
        return currentStep;
    }

    /**
     * 현재 단계를 설정합니다.
     * 
     * @param currentStep 설정할 현재 단계
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LogFileProgress setCurrentStep(Integer currentStep) {
        this.currentStep = currentStep;
        return this;
    }

    /**
     * 전체 단계 수를 반환합니다.
     * 
     * @return 전체 단계 수
     */
    public Integer getTotalStep() {
        return totalStep;
    }

    /**
     * 전체 단계 수를 설정합니다.
     * 
     * @param totalStep 설정할 전체 단계 수
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LogFileProgress setTotalStep(Integer totalStep) {
        this.totalStep = totalStep;
        return this;
    }

    /**
     * 진행률을 반환합니다.
     * 
     * @return 진행률 (퍼센트)
     */
    public Integer getProgress() {
        return progress;
    }

    /**
     * 진행률을 설정합니다.
     * 
     * @param progress 설정할 진행률 (퍼센트)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LogFileProgress setProgress(Integer progress) {
        this.progress = progress;
        return this;
    }

    /**
     * 완료 시간을 반환합니다.
     * 
     * @return 완료 시간 (타임스탬프)
     */
    public Long getFinishTime() {
        return finishTime;
    }

    /**
     * 완료 시간을 설정합니다.
     * 
     * @param finishTime 설정할 완료 시간 (타임스탬프)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LogFileProgress setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
        return this;
    }

    /**
     * 업로드 속도를 반환합니다.
     * 
     * @return 업로드 속도 (MB/s)
     */
    public Float getUploadRate() {
        return uploadRate;
    }

    /**
     * 업로드 속도를 설정합니다.
     * 
     * @param uploadRate 설정할 업로드 속도 (MB/s)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LogFileProgress setUploadRate(Float uploadRate) {
        this.uploadRate = uploadRate;
        return this;
    }

    /**
     * 업로드 상태를 반환합니다.
     * 
     * @return 업로드 상태
     */
    public FileUploadStatusEnum getStatus() {
        return status;
    }

    /**
     * 업로드 상태를 설정합니다.
     * 
     * @param status 설정할 업로드 상태
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public LogFileProgress setStatus(FileUploadStatusEnum status) {
        this.status = status;
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
    public LogFileProgress setResult(Integer result) {
        this.result = result;
        return this;
    }
}
