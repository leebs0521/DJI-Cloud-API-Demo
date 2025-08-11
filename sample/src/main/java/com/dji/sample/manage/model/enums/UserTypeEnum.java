package com.dji.sample.manage.model.enums;

/**
 * 사용자 타입 열거형
 * 
 * DJI Cloud API의 사용자 타입을 정의하는 열거형 클래스입니다.
 * 이 클래스는 다음과 같은 주요 기능들을 제공합니다:
 * 
 * 1. 사용자 타입 정의
 *    - 웹 사용자 (WEB)
 *    - 파일럿 사용자 (PILOT)
 *    - 알 수 없는 사용자 타입 (UNKNOWN)
 * 
 * 2. 사용자 타입 관리
 *    - 각 타입별 정수 값 및 설명 관리
 *    - 정수 값으로부터 열거형 상수 검색 기능
 *    - 사용자 타입별 권한 및 접근 제어
 * 
 * 3. 사용자 인터페이스 분리
 *    - 웹 인터페이스와 파일럿 인터페이스 구분
 *    - 사용자 타입별 기능 및 화면 분리
 *    - 사용자 경험 최적화를 위한 타입별 처리
 * 
 * 이 열거형은 DJI Cloud API의 사용자 관리 시스템에서
 * 다양한 사용자 타입을 표준화된 방식으로
 * 구분하고 관리할 수 있도록 지원합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/2
 */
public enum UserTypeEnum {

    /**
     * 웹 사용자
     * 웹 브라우저를 통해 접근하는 사용자
     */
    WEB(1, "Web"),

    /**
     * 파일럿 사용자
     * DJI 파일럿 앱을 통해 접근하는 사용자
     */
    PILOT(2, "Pilot"),

    /**
     * 알 수 없는 사용자 타입
     * 정의되지 않은 사용자 타입에 대한 기본값
     */
    UNKNOWN(-1, "Unknown");

    /**
     * 사용자 타입 값
     * 각 사용자 타입에 대응하는 정수 값
     */
    private int val;

    /**
     * 사용자 타입 설명
     * 각 사용자 타입에 대한 설명 문자열
     */
    private String desc;

    /**
     * 생성자
     * @param val 사용자 타입 값
     * @param desc 사용자 타입 설명
     */
    UserTypeEnum(int val, String desc) {
        this.val = val;
        this.desc = desc;
    }

    /**
     * 사용자 타입 값을 반환합니다.
     * @return 사용자 타입 값
     */
    public int getVal() {
        return this.val;
    }

    /**
     * 사용자 타입 설명을 반환합니다.
     * @return 사용자 타입 설명
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * 정수 값으로부터 열거형 상수를 찾습니다.
     * 
     * 주어진 정수 값에 해당하는 열거형 상수를 반환합니다.
     * 일치하는 값이 없으면 UNKNOWN을 반환합니다.
     * 
     * @param val 찾을 사용자 타입 값
     * @return 해당하는 열거형 상수 또는 UNKNOWN
     */
    public static UserTypeEnum find(int val) {
        if (val == WEB.val) {
            return WEB;
        }
        if (val == PILOT.val) {
            return PILOT;
        }
        return UNKNOWN;
    }
}
