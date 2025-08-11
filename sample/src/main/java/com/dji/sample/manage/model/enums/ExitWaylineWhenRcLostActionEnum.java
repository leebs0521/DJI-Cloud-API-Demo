package com.dji.sample.manage.model.enums;

import com.dji.sdk.exception.CloudSDKException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

/**
 * RC 신호 손실 시 웨이라인 종료 동작 열거형
 * 
 * DJI Cloud API의 RC 신호 손실 시 웨이라인 종료 동작을 정의하는 열거형 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. RC 신호 손실 동작 정의
 *    - 웨이라인 계속 실행 (CONTINUE_WAYLINE)
 *    - RC 신호 손실 동작 실행 (EXECUTE_RC_LOST_ACTION)
 *    - 안전한 비행 제어를 위한 동작 선택
 * 
 * 2. JSON 직렬화/역직렬화 지원
 *    - @JsonValue 어노테이션으로 JSON 직렬화 지원
 *    - @JsonCreator 어노테이션으로 JSON 역직렬화 지원
 *    - API 통신을 위한 표준화된 데이터 형식 제공
 * 
 * 3. 안전한 비행 제어
 *    - RC 신호 손실 시 안전한 대응 방안 제공
 *    - 웨이라인 실행 중단 여부 결정
 *    - 비행 안전성 확보를 위한 동작 제어
 * 
 * 이 열거형은 DJI 디바이스의 웨이라인 비행 시스템에서
 * RC 신호 손실 시의 동작을 표준화된 방식으로
 * 관리하고 제어할 수 있도록 지원합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/15
 */
public enum ExitWaylineWhenRcLostActionEnum {

    /**
     * 웨이라인 계속 실행
     * RC 신호가 손실되어도 웨이라인을 계속 실행
     */
    CONTINUE_WAYLINE, 

    /**
     * RC 신호 손실 동작 실행
     * RC 신호가 손실되면 미리 정의된 RC 신호 손실 동작을 실행
     */
    EXECUTE_RC_LOST_ACTION;

    /**
     * JSON 직렬화를 위한 정수 값을 반환합니다.
     * @return 열거형 상수의 순서 값
     */
    @JsonValue
    public int getVal() {
        return ordinal();
    }

    /**
     * 정수 값으로부터 열거형 상수를 찾습니다.
     * 
     * 주어진 정수 값에 해당하는 열거형 상수를 반환합니다.
     * 일치하는 값이 없으면 CloudSDKException을 발생시킵니다.
     * 
     * @param val 찾을 정수 값
     * @return 해당하는 열거형 상수
     * @throws CloudSDKException 일치하는 값이 없을 경우
     */
    @JsonCreator
    public static ExitWaylineWhenRcLostActionEnum find(int val) {
        return Arrays.stream(values()).filter(lostActionEnum -> lostActionEnum.ordinal() == val).findAny()
                .orElseThrow(() -> new CloudSDKException(ExitWaylineWhenRcLostActionEnum.class, val));
    }
}
