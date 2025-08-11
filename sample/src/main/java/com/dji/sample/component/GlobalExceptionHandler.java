package com.dji.sample.component;

import com.dji.sdk.common.HttpResultResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 전역 예외 처리 핸들러 클래스
 * 
 * 애플리케이션에서 발생하는 모든 예외를 중앙에서 처리하는 핸들러입니다.
 * 다양한 예외 타입에 대해 적절한 HTTP 응답을 반환하여 클라이언트에게 일관된 오류 정보를 제공합니다.
 * 
 * @author sean
 * @version 0.2
 * @date 2021/12/1
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 일반적인 예외를 처리하는 핸들러입니다.
     * 
     * 모든 예외를 포괄적으로 처리하며, 예외의 지역화된 메시지를 반환합니다.
     * 주의: 이렇게 직접 반환하는 것은 보안상 위험이 있을 수 있습니다.
     * 
     * @param e 발생한 예외 객체
     * @return 오류 응답 객체
     */
    @ExceptionHandler(Exception.class)
    public HttpResultResponse exceptionHandler(Exception e) {
        e.printStackTrace();
        return HttpResultResponse.error(e.getLocalizedMessage());
    }

    /**
     * NullPointerException을 처리하는 핸들러입니다.
     * 
     * null 객체 참조로 인한 예외를 처리하며, 사용자 친화적인 오류 메시지를 반환합니다.
     * 
     * @param e 발생한 NullPointerException 객체
     * @return 오류 응답 객체
     */
    @ExceptionHandler(NullPointerException.class)
    public HttpResultResponse nullPointerExceptionHandler(NullPointerException e) {
        e.printStackTrace();
        return HttpResultResponse.error("A null object appeared.");
    }

    /**
     * 메서드 인자 유효성 검증 예외를 처리하는 핸들러입니다.
     * 
     * @Valid 어노테이션이 적용된 메서드 파라미터의 유효성 검증 실패 시 발생하는 예외를 처리합니다.
     * 필드명과 기본 오류 메시지를 조합하여 상세한 오류 정보를 반환합니다.
     * 
     * @param e 발생한 BindException 객체 (MethodArgumentNotValidException 또는 BindException)
     * @return 오류 응답 객체
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public HttpResultResponse methodArgumentNotValidExceptionHandler(BindException e) {
        e.printStackTrace();
        return HttpResultResponse.error(e.getFieldError().getField() + e.getFieldError().getDefaultMessage());
    }

}
