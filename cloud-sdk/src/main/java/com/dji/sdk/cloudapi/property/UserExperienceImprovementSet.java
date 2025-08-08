package com.dji.sdk.cloudapi.property;

import com.dji.sdk.cloudapi.device.UserExperienceImprovementEnum;
import com.dji.sdk.common.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * 사용자 경험 개선 설정 클래스
 * 
 * 이 클래스는 사용자 경험을 개선하기 위한 설정을 정의합니다.
 * BaseModel을 상속받아 유효성 검사를 지원하며, 드론과 도크의
 * 사용성과 편의성을 향상시키는 기능들을 관리합니다.
 * 
 * 주요 구성 요소:
 * - userExperienceImprovement: 사용자 경험 개선 열거형
 * 
 * 이 클래스는 드론과 도크의 사용자 경험을 개선하기 위한 다양한 설정을
 * 활성화하거나 비활성화하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/13
 */
public class UserExperienceImprovementSet extends BaseModel {

    /**
     * 사용자 경험 개선 설정
     * 
     * 드론과 도크의 사용자 경험을 개선하기 위한 설정을 제어합니다.
     * UserExperienceImprovementEnum을 사용하여 다양한 개선 옵션 중 하나를 선택할 수 있습니다.
     * 
     * 가능한 설정:
     * - ENABLE: 사용자 경험 개선 기능 활성화
     * - DISABLE: 사용자 경험 개선 기능 비활성화
     * 
     * 개선 기능에는 자동 최적화, 스마트 알림, 사용자 정의 설정 등이 포함될 수 있습니다.
     */
    @NotNull
    @JsonProperty("user_experience_improvement")
    private UserExperienceImprovementEnum userExperienceImprovement;

    public UserExperienceImprovementSet() {
    }

    @Override
    public String toString() {
        return "UserExperienceImprovementSet{" +
                "userExperienceImprovement=" + userExperienceImprovement +
                '}';
    }

    /**
     * 사용자 경험 개선 설정을 반환합니다.
     * 
     * @return 사용자 경험 개선 열거형
     */
    public UserExperienceImprovementEnum getUserExperienceImprovement() {
        return userExperienceImprovement;
    }

    /**
     * 사용자 경험 개선 설정을 설정합니다.
     * 
     * @param userExperienceImprovement 사용자 경험 개선 열거형
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public UserExperienceImprovementSet setUserExperienceImprovement(UserExperienceImprovementEnum userExperienceImprovement) {
        this.userExperienceImprovement = userExperienceImprovement;
        return this;
    }
}
