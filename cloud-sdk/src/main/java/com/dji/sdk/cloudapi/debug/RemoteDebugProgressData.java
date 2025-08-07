package com.dji.sdk.cloudapi.debug;

/**
 * 원격 디버그 진행 데이터 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 원격 디버그의 진행 데이터를 담는 클래스입니다.
 * 진행률, 현재 단계, 전체 단계 수, 단계 키, 단계 결과를 포함합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/7/29
 */
public class RemoteDebugProgressData {

    /**
     * 진행률
     * 원격 디버그의 진행률 (퍼센트)
     */
    private Integer percent;

    /**
     * 현재 단계
     * 현재 진행 중인 단계 번호
     */
    private Integer currentStep;

    /**
     * 전체 단계 수
     * 전체 디버그 단계의 수
     */
    private Integer totalSteps;

    /**
     * 단계 키
     * 현재 단계의 키 정보
     */
    private RemoteDebugStepKeyEnum stepKey;

    /**
     * 단계 결과
     * 현재 단계의 실행 결과
     */
    private Integer stepResult;

    /**
     * 기본 생성자
     */
    public RemoteDebugProgressData() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "RemoteDebugProgressData{" +
                "percent=" + percent +
                ", currentStep=" + currentStep +
                ", totalSteps=" + totalSteps +
                ", stepKey='" + stepKey + '\'' +
                ", stepResult=" + stepResult +
                '}';
    }

    /**
     * 진행률을 반환합니다.
     * 
     * @return 진행률 (퍼센트)
     */
    public Integer getPercent() {
        return percent;
    }

    /**
     * 진행률을 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param percent 설정할 진행률 (퍼센트)
     * @return 현재 RemoteDebugProgressData 객체
     */
    public RemoteDebugProgressData setPercent(Integer percent) {
        this.percent = percent;
        return this;
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
     * 현재 단계를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param currentStep 설정할 현재 단계
     * @return 현재 RemoteDebugProgressData 객체
     */
    public RemoteDebugProgressData setCurrentStep(Integer currentStep) {
        this.currentStep = currentStep;
        return this;
    }

    /**
     * 전체 단계 수를 반환합니다.
     * 
     * @return 전체 단계 수
     */
    public Integer getTotalSteps() {
        return totalSteps;
    }

    /**
     * 전체 단계 수를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param totalSteps 설정할 전체 단계 수
     * @return 현재 RemoteDebugProgressData 객체
     */
    public RemoteDebugProgressData setTotalSteps(Integer totalSteps) {
        this.totalSteps = totalSteps;
        return this;
    }

    /**
     * 단계 키를 반환합니다.
     * 
     * @return 단계 키
     */
    public RemoteDebugStepKeyEnum getStepKey() {
        return stepKey;
    }

    /**
     * 단계 키를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param stepKey 설정할 단계 키
     * @return 현재 RemoteDebugProgressData 객체
     */
    public RemoteDebugProgressData setStepKey(RemoteDebugStepKeyEnum stepKey) {
        this.stepKey = stepKey;
        return this;
    }

    /**
     * 단계 결과를 반환합니다.
     * 
     * @return 단계 결과
     */
    public Integer getStepResult() {
        return stepResult;
    }

    /**
     * 단계 결과를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param stepResult 설정할 단계 결과
     * @return 현재 RemoteDebugProgressData 객체
     */
    public RemoteDebugProgressData setStepResult(Integer stepResult) {
        this.stepResult = stepResult;
        return this;
    }
}
