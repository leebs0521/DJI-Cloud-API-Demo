package com.dji.sdk.config;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

/**
 * Snake Case 인수 처리기
 * 
 * 이 클래스는 HTTP 요청의 snake_case 파라미터를
 * camelCase로 자동 변환하는 기능을 제공합니다.
 * 
 * 주요 기능:
 * - HTTP 요청 파라미터의 snake_case를 camelCase로 변환
 * - GetSnakeDataBinder를 사용한 데이터 바인딩
 * - Spring MVC의 ServletModelAttributeMethodProcessor 확장
 * 
 * 이 클래스는 CloudSDKMvcConfigurer에서 등록되어
 * 모든 HTTP 요청에 자동으로 적용됩니다.
 * 
 * @author sean
 * @version 1.2
 * @date 2022/9/16
 */
public class GetSnakeArgumentProcessor extends ServletModelAttributeMethodProcessor {

    /**
     * 클래스 생성자
     * 
     * @param annotationNotRequired true인 경우, @ModelAttribute 어노테이션이 없어도
     *                              복잡한 메서드 인수와 반환값을 모델 속성으로 간주합니다
     */
    public GetSnakeArgumentProcessor(boolean annotationNotRequired) {
        super(annotationNotRequired);
    }

    /**
     * 요청 파라미터를 바인딩합니다.
     * 
     * GetSnakeDataBinder를 사용하여 snake_case 파라미터를
     * camelCase로 변환하면서 데이터를 바인딩합니다.
     * 
     * @param binder 데이터 바인더
     * @param request 네이티브 웹 요청
     */
    @Override
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        super.bindRequestParameters(new GetSnakeDataBinder(binder.getTarget(), binder.getObjectName()), request);
    }
}
