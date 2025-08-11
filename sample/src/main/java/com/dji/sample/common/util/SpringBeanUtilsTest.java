package com.dji.sample.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring Bean 유틸리티 테스트 클래스
 * 
 * Spring ApplicationContext에 접근하여 Bean을 가져오는 유틸리티 클래스입니다.
 * ApplicationContextAware 인터페이스를 구현하여 ApplicationContext를 주입받습니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/10
 */
@Component
public class SpringBeanUtilsTest implements ApplicationContextAware {

    /** Spring ApplicationContext */
    private static ApplicationContext applicationContext;

    /**
     * ApplicationContext를 설정합니다.
     * 
     * @param applicationContext Spring ApplicationContext
     * @throws BeansException Bean 관련 예외
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtilsTest.applicationContext = applicationContext;
    }

    /**
     * 클래스 타입으로 Bean을 가져옵니다.
     * 
     * @param clazz Bean의 클래스 타입
     * @param <T> Bean의 타입
     * @return 해당 타입의 Bean 객체
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * Bean 이름으로 Bean을 가져옵니다.
     * 
     * @param beanName Bean의 이름
     * @return 해당 이름의 Bean 객체
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
}
