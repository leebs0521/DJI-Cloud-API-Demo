package com.dji.sdk.cloudapi.control;

import com.dji.sdk.cloudapi.device.DrcStateEnum;

/**
 * DRC 상태 알림 클래스
 * 
 * 이 클래스는 DJI Cloud API에서 DRC(Direct Remote Control) 상태 알림을 담는 클래스입니다.
 * DRC 상태 결과와 DRC 상태를 포함하여 DRC 상태 알림을 관리합니다.
 * 
 * @author sean
 * @version 1.4
 * @date 2023/3/17
 */
public class DrcStatusNotify {

    /**
     * DRC 상태 결과
     * DRC 상태의 결과 정보
     */
    private DrcStatusErrorEnum result;

    /**
     * DRC 상태
     * DRC의 현재 상태
     */
    private DrcStateEnum drcState;

    /**
     * 기본 생성자
     */
    public DrcStatusNotify() {
    }

    /**
     * 객체의 문자열 표현을 반환합니다.
     * 
     * @return 객체의 문자열 표현
     */
    @Override
    public String toString() {
        return "DrcStatusNotify{" +
                "result=" + result +
                ", drcState=" + drcState +
                '}';
    }

    /**
     * DRC 상태 결과를 반환합니다.
     * 
     * @return DRC 상태 결과
     */
    public DrcStatusErrorEnum getResult() {
        return result;
    }

    /**
     * DRC 상태 결과를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param result 설정할 DRC 상태 결과
     * @return 현재 DrcStatusNotify 객체
     */
    public DrcStatusNotify setResult(DrcStatusErrorEnum result) {
        this.result = result;
        return this;
    }

    /**
     * DRC 상태를 반환합니다.
     * 
     * @return DRC 상태
     */
    public DrcStateEnum getDrcState() {
        return drcState;
    }

    /**
     * DRC 상태를 설정하고 현재 객체를 반환합니다. (메서드 체이닝 지원)
     * 
     * @param drcState 설정할 DRC 상태
     * @return 현재 DrcStatusNotify 객체
     */
    public DrcStatusNotify setDrcState(DrcStateEnum drcState) {
        this.drcState = drcState;
        return this;
    }
}
