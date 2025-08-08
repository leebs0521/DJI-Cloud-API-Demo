package com.dji.sdk.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring Bean 유틸리티 클래스
 * 
 * 이 클래스는 Spring ApplicationContext에 접근하여
 * Bean을 가져오는 유틸리티 기능을 제공합니다.
 * 
 * 주요 기능:
 * - ApplicationContext 설정 및 관리
 * - 클래스 타입으로 Bean 가져오기
 * - Bean 이름으로 Bean 가져오기
 * 
 * 이 클래스는 Spring 컨테이너 외부에서
 * Bean에 접근해야 할 때 사용됩니다.
 * 
 * @author sean
 * @version 1.3
 * @date 2022/11/10
 */
@Component
public class SpringBeanUtils implements ApplicationContextAware {

    /**
     * Spring ApplicationContext
     * 
     * Spring 컨테이너의 ApplicationContext 인스턴스입니다.
     */
    private static ApplicationContext applicationContext;

    /**
     * ApplicationContext를 설정합니다.
     * 
     * Spring 컨테이너가 초기화될 때 자동으로 호출되어
     * ApplicationContext를 설정합니다.
     * 
     * @param applicationContext 설정할 ApplicationContext
     * @throws BeansException Bean 관련 오류 발생 시
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtils.applicationContext = applicationContext;
    }

    /**
     * 클래스 타입으로 Bean을 가져옵니다.
     * 
     * 지정된 클래스 타입에 해당하는 Bean을 반환합니다.
     * 
     * @param clazz 가져올 Bean의 클래스 타입
     * @param <T> Bean의 타입
     * @return 해당 타입의 Bean 인스턴스
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * Bean 이름으로 Bean을 가져옵니다.
     * 
     * 지정된 이름의 Bean을 반환합니다.
     * 
     * @param beanName 가져올 Bean의 이름
     * @return 해당 이름의 Bean 인스턴스
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
}
