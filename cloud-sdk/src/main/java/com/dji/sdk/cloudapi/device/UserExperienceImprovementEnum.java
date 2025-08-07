package com.dji.sdk.cloudapi.device;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * 사용자 경험 개선 열거형 클래스
 * 
 * 이 클래스는 사용자 경험 개선 프로그램의 참여 상태를 정의합니다.
 * 초기, 거부, 동의 상태를 통해 사용자의 개인정보 수집 동의 여부를 관리합니다.
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/13
 */
public enum UserExperienceImprovementEnum {

    /**
     * 초기 상태 (정수값: 0)
     */
    INITIAL(0),

    /**
     * 거부 상태 (정수값: 1)
     */
    REFUSE(1),

    /**
     * 동의 상태 (정수값: 2)
     */
    AGREE(2),
    ;

    /**
     * 상태 정수값
     */
    private final int state;

    /**
     * 사용자 경험 개선 열거형 생성자
     * 
     * @param state 상태 정수값
     */
    UserExperienceImprovementEnum(int state) {
        this.state = state;
    }

    /**
     * 상태 정수값을 반환합니다.
     * 
     * @return 상태 정수값
     */
    @JsonValue
    public int getState() {
        return state;
    }

    /**
     * 정수값으로 사용자 경험 개선 상태를 찾습니다.
     * 
     * @param state 찾을 상태 정수값
     * @return 찾은 사용자 경험 개선 열거형
     * @throws CloudSDKException 해당하는 상태를 찾을 수 없는 경우
     */
    @JsonCreator
    public static UserExperienceImprovementEnum find(int state) {
        return Arrays.stream(values()).filter(stateEnum -> stateEnum.state == state).findAny()
            .orElseThrow(() -> new CloudSDKException(UserExperienceImprovementEnum.class, state));
    }

}
