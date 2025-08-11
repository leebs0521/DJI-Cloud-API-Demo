package com.dji.sample.manage.model.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * 상태 스위치 열거형
 * 
 * DJI Cloud API의 상태 스위치를 정의하는 열거형 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 상태 스위치 정의
 *    - 비활성화 상태 (DISABLE)
 *    - 활성화 상태 (ENABLE)
 *    - 단순한 켜기/끄기 상태 관리
 * 
 * 2. 상태 값 검색
 *    - 정수 값으로부터 열거형 상수 검색
 *    - Optional을 활용한 안전한 검색
 *    - 일치하는 값이 없을 경우 빈 Optional 반환
 * 
 * 3. 디바이스 상태 제어
 *    - 디바이스 기능의 활성화/비활성화 제어
 *    - 설정 옵션의 켜기/끄기 상태 관리
 *    - 시스템 상태의 간단한 제어
 * 
 * 이 열거형은 DJI 디바이스의 다양한 기능과 설정의
 * 활성화/비활성화 상태를 표준화된 방식으로
 * 관리할 수 있도록 지원합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/10/28
 */
public enum StateSwitchEnum {

    /**
     * 비활성화 상태
     * 기능이나 설정이 꺼진 상태
     */
    DISABLE, 

    /**
     * 활성화 상태
     * 기능이나 설정이 켜진 상태
     */
    ENABLE;

    /**
     * 정수 값으로부터 열거형 상수를 찾습니다.
     * 
     * 주어진 정수 값에 해당하는 열거형 상수를 반환합니다.
     * 일치하는 값이 없으면 빈 Optional을 반환합니다.
     * 
     * @param value 찾을 정수 값
     * @return 해당하는 열거형 상수의 Optional 또는 빈 Optional
     */
    public static Optional<StateSwitchEnum> find(int value) {
        return Arrays.stream(StateSwitchEnum.values()).filter(state -> state.ordinal() == value).findAny();
    }
}
