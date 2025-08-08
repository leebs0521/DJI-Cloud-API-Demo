package com.dji.sdk.cloudapi.wayline;

/**
 * 비행 작업 진행률 데이터 클래스
 * 
 * 이 클래스는 웨이라인 비행 작업의 진행률 정보를 정의합니다.
 * 현재 실행 단계와 진행률 퍼센트를 포함하여
 * 작업의 세부적인 진행 상황을 추적할 수 있습니다.
 * 
 * 주요 구성 요소:
 * - currentStep: 현재 실행 단계
 * - percent: 진행률 값
 * 
 * 이 클래스는 비행 작업의 진행 상황을
 * 정확하고 상세하게 모니터링하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/9
 */
public class FlighttaskProgressData {

    /**
     * 현재 실행 단계
     * 
     * 비행 작업의 현재 실행 단계를 나타냅니다.
     * 웨이포인트 도착, 액션 실행, 다음 웨이포인트로 이동 등의
     * 세부적인 실행 단계를 구분합니다.
     */
    private ExecutionStepEnum currentStep;

    /**
     * 진행률 값
     * 
     * 비행 작업의 전체 진행률을 퍼센트로 나타냅니다.
     * 0부터 100까지의 값으로, 작업의 완료 정도를 표시합니다.
     * 
     * 예시: 75 (75% 완료)
     */
    private Integer percent;

    public FlighttaskProgressData() {
    }

    @Override
    public String toString() {
        return "FlighttaskProgressData{" +
                "currentStep=" + currentStep +
                ", percent=" + percent +
                '}';
    }

    /**
     * 현재 실행 단계를 반환합니다.
     * 
     * @return 현재 실행 단계
     */
    public ExecutionStepEnum getCurrentStep() {
        return currentStep;
    }

    /**
     * 현재 실행 단계를 설정합니다.
     * 
     * @param currentStep 현재 실행 단계
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskProgressData setCurrentStep(ExecutionStepEnum currentStep) {
        this.currentStep = currentStep;
        return this;
    }

    /**
     * 진행률 값을 반환합니다.
     * 
     * @return 진행률 값 (0-100)
     */
    public Integer getPercent() {
        return percent;
    }

    /**
     * 진행률 값을 설정합니다.
     * 
     * @param percent 진행률 값 (0-100)
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public FlighttaskProgressData setPercent(Integer percent) {
        this.percent = percent;
        return this;
    }
}
