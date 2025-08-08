package com.dji.sdk.cloudapi.wayline;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 웨이라인 메서드 열거형
 * 
 * 이 열거형은 웨이라인 비행 작업과 관련된 다양한 메서드를 정의합니다.
 * 비행 작업의 생성, 준비, 실행, 취소, 일시정지, 복구, 귀환 등의
 * 작업을 구분하여 관리합니다.
 * 
 * 주요 구성 요소:
 * - FLIGHTTASK_CREATE: 비행 작업 생성
 * - FLIGHTTASK_PREPARE: 비행 작업 준비
 * - FLIGHTTASK_EXECUTE: 비행 작업 실행
 * - FLIGHTTASK_UNDO: 비행 작업 취소
 * - FLIGHTTASK_PAUSE: 비행 작업 일시정지
 * - FLIGHTTASK_RECOVERY: 비행 작업 복구
 * - RETURN_HOME: 홈포인트 귀환
 * - RETURN_HOME_CANCEL: 홈포인트 귀환 취소
 * 
 * 이 열거형은 웨이라인 비행 작업의 각 단계를 식별하고
 * 적절한 처리 로직을 적용하는 데 사용됩니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/14
 */
public enum WaylineMethodEnum {

    /**
     * 비행 작업 생성
     * 
     * 새로운 웨이라인 비행 작업을 생성하는 메서드입니다.
     * 웨이라인 파일을 업로드하고 비행 작업을 초기화합니다.
     */
    FLIGHTTASK_CREATE("flighttask_create"),

    /**
     * 비행 작업 준비
     * 
     * 비행 작업을 실행하기 전에 준비하는 메서드입니다.
     * 비행 조건을 확인하고 작업을 준비 상태로 만듭니다.
     */
    FLIGHTTASK_PREPARE("flighttask_prepare"),

    /**
     * 비행 작업 실행
     * 
     * 준비된 비행 작업을 실제로 실행하는 메서드입니다.
     * 드론이 웨이라인에 따라 자동 비행을 시작합니다.
     */
    FLIGHTTASK_EXECUTE("flighttask_execute"),

    /**
     * 비행 작업 취소
     * 
     * 진행 중인 비행 작업을 취소하는 메서드입니다.
     * 비행을 중단하고 안전하게 착륙합니다.
     */
    FLIGHTTASK_UNDO("flighttask_undo"),

    /**
     * 비행 작업 일시정지
     * 
     * 진행 중인 비행 작업을 일시정지하는 메서드입니다.
     * 현재 위치에서 호버링하며 작업을 일시 중단합니다.
     */
    FLIGHTTASK_PAUSE("flighttask_pause"),

    /**
     * 비행 작업 복구
     * 
     * 일시정지된 비행 작업을 복구하는 메서드입니다.
     * 중단된 지점부터 비행을 재개합니다.
     */
    FLIGHTTASK_RECOVERY("flighttask_recovery"),

    /**
     * 홈포인트 귀환
     * 
     * 드론을 홈포인트로 귀환시키는 메서드입니다.
     * 현재 위치에서 안전하게 홈포인트로 돌아갑니다.
     */
    RETURN_HOME("return_home"),

    /**
     * 홈포인트 귀환 취소
     * 
     * 진행 중인 홈포인트 귀환을 취소하는 메서드입니다.
     * 귀환을 중단하고 현재 위치에서 호버링합니다.
     */
    RETURN_HOME_CANCEL("return_home_cancel");

    /**
     * 메서드 값
     * 
     * 각 메서드를 구분하는 문자열 값입니다.
     */
    private final String method;

    /**
     * 웨이라인 메서드 열거형 생성자
     * 
     * @param method 메서드 값
     */
    WaylineMethodEnum(String method) {
        this.method = method;
    }

    /**
     * 메서드 값을 반환합니다.
     * 
     * @return 메서드 값
     */
    @JsonValue
    public String getMethod() {
        return this.method;
    }

}
