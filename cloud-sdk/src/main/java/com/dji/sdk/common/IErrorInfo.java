package com.dji.sdk.common;

/**
 * 오류 정보 인터페이스
 * 
 * 이 인터페이스는 SDK에서 발생하는 모든 오류 정보를
 * 표준화된 방식으로 처리하기 위한 기본 인터페이스입니다.
 * 
 * 주요 구성 요소:
 * - getMessage(): 오류 메시지 반환
 * - getCode(): 오류 코드 반환
 * 
 * 이 인터페이스를 구현하는 클래스들은
 * 일관된 오류 처리 방식을 제공합니다.
 * 
 * @author sean.zhou
 * @version 0.1
 * @date 2021/11/25
 */
public interface IErrorInfo {

    /**
     * 오류 메시지를 반환합니다.
     * 
     * @return 오류 메시지
     */
    String getMessage();

    /**
     * 오류 코드를 반환합니다.
     * 
     * @return 오류 코드
     */
    Integer getCode();

}
