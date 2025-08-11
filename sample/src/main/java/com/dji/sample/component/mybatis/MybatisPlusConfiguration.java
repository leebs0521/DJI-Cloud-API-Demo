package com.dji.sample.component.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis Plus 설정 클래스
 * 
 * MyBatis Plus의 인터셉터와 플러그인을 설정하는 클래스입니다.
 * 페이징 기능을 위한 PaginationInnerInterceptor를 설정합니다.
 * 
 * @author sean
 * @version 0.3
 * @date 2021/12/22
 */
@Configuration
public class MybatisPlusConfiguration {

    /**
     * MyBatis Plus 인터셉터를 생성합니다.
     * 
     * MySQL 데이터베이스를 위한 페이징 인터셉터를 추가하여
     * 페이징 쿼리를 자동으로 처리할 수 있도록 합니다.
     * 
     * @return MyBatis Plus 인터셉터
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // MySQL 데이터베이스용 페이징 인터셉터 추가
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
