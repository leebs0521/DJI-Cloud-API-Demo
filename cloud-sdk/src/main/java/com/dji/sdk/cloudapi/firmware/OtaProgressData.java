package com.dji.sdk.cloudapi.firmware;

/**
 * OTA 진행 데이터 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 OTA(Over-The-Air) 펌웨어 업데이트의 진행 데이터를 담는 클래스입니다.
 * 진행률과 현재 단계를 포함하여 OTA 펌웨어 업데이트의 진행 상황을 추적합니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/7/29
 */
public class OtaProgressData {

    /**
     * 진행률
     * OTA 펌웨어 업데이트의 진행률 (퍼센트)
     */
    private Integer percent;

    /**
     * 현재 단계
     * 현재 진행 중인 OTA 단계
     */
    private OtaProgressStepEnum currentStep;

    /**
     * 기본 생성자
     */
    public OtaProgressData() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "OtaProgressData{" +
                "percent=" + percent +
                ", currentStep=" + currentStep +
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
     * @return 현재 OtaProgressData 객체
     */
    public OtaProgressData setPercent(Integer percent) {
        this.percent = percent;
        return this;
    }

    /**
     * 현재 단계를 반환합니다.
     * 
     * @return 현재 단계
     */
    public OtaProgressStepEnum getCurrentStep() {
        return currentStep;
    }

    /**
     * 현재 단계를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param currentStep 설정할 현재 단계
     * @return 현재 OtaProgressData 객체
     */
    public OtaProgressData setCurrentStep(OtaProgressStepEnum currentStep) {
        this.currentStep = currentStep;
        return this;
    }
}
