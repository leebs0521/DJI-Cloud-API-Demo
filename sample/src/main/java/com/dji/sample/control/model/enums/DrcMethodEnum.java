package com.dji.sample.control.model.enums;

import lombok.Getter;

/**
 * DRC 메서드 열거형 클래스
 * 
 * DRC(Direct Remote Control) 모드의 메서드를 정의하는 열거형입니다.
 * DRC 모드 진입과 종료 메서드를 포함합니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2023/1/11
 */
@Getter
public enum DrcMethodEnum {

    /** DRC 모드 진입 */
    DRC_MODE_ENTER("drc_mode_enter"),

    /** DRC 모드 종료 */
    DRC_MODE_EXIT("drc_mode_exit");

    /** 메서드 이름 */
    String method;

    /**
     * DRC 메서드 열거형 생성자
     * 
     * @param method 메서드 이름
     */
    DrcMethodEnum(String method) {
        this.method = method;
    }
}
