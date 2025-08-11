package com.dji.sample.component.oss.service.impl;

import com.dji.sample.component.oss.model.OssConfiguration;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * OSS 서비스 AOP 핸들러 클래스
 * 
 * OSS 서비스 메서드 실행 전에 사전 검증을 수행하는 AOP(Aspect-Oriented Programming) 핸들러입니다.
 * OSS 설정이 활성화되어 있는지와 OSS 서비스가 올바르게 구성되어 있는지 확인합니다.
 * 
 * @author sean
 * @version 1.1
 * @date 2022/6/20
 */
@Component
@Aspect
public class OssAspectHandler {

    /** OSS 서비스 컨텍스트 */
    @Autowired
    private OssServiceContext ossServiceContext;

    /**
     * OSS 서비스 메서드 실행 전 사전 검증을 수행합니다.
     * 
     * OssServiceContext의 모든 public 메서드 실행 전에 호출됩니다.
     * OSS 설정이 활성화되어 있는지와 OSS 서비스가 올바르게 구성되어 있는지 확인합니다.
     */
    @Before("execution(public * com.dji.sample.component.oss.service.impl.OssServiceContext.*(..))")
    public void before() {
        if (!OssConfiguration.enable) {
            throw new IllegalArgumentException("Please enable OssConfiguration.");
        }
        if (this.ossServiceContext.getOssService() == null) {
            throw new IllegalArgumentException("Please check the OssConfiguration configuration.");
        }
        this.ossServiceContext.createClient();
    }
}
