package com.dji.sdk.cloudapi.control;

/**
 * 사진 촬영 진행 데이터 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 사진 촬영의 진행 데이터를 담는 클래스입니다.
 * 현재 단계와 진행률을 포함합니다.
 * 
 * @author sean
 * @version 1.9
 * @date 2023/12/12
 */
public class PhotoTakeProgressData {

    /**
     * 현재 단계
     * 사진 촬영의 현재 진행 단계
     */
    private PhotoTakeProgressStepEnum currentStep;

    /**
     * 진행률
     * 사진 촬영의 진행률 (퍼센트)
     */
    private Integer percent;

    /**
     * 기본 생성자
     */
    public PhotoTakeProgressData() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "PhotoTakeProgressData{" +
                "currentStep=" + currentStep +
                ", percent=" + percent +
                '}';
    }

    /**
     * 현재 단계를 반환합니다.
     * 
     * @return 현재 단계
     */
    public PhotoTakeProgressStepEnum getCurrentStep() {
        return currentStep;
    }

    /**
     * 현재 단계를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param currentStep 설정할 현재 단계
     * @return 현재 PhotoTakeProgressData 객체
     */
    public PhotoTakeProgressData setCurrentStep(PhotoTakeProgressStepEnum currentStep) {
        this.currentStep = currentStep;
        return this;
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
     * @return 현재 PhotoTakeProgressData 객체
     */
    public PhotoTakeProgressData setPercent(Integer percent) {
        this.percent = percent;
        return this;
    }
}
