package com.dji.sample.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 전역 스레드 풀 설정 클래스
 * 
 * 애플리케이션에서 사용할 커스텀 스레드 풀을 설정하는 클래스입니다.
 * ThreadPoolExecutor를 사용하여 비동기 작업을 효율적으로 처리할 수 있도록 구성합니다.
 * 
 * @author sean.zhou
 * @date 2021/11/10
 * @version 0.1
 */
@Configuration
public class GlobalThreadPoolConfiguration {

    /** 코어 스레드 풀 크기 (기본값: 10) */
    @Value("${thread.pool.core-pool-size: 10}")
    private int corePoolSize;

    /** 최대 스레드 풀 크기 (기본값: 20) */
    @Value("${thread.pool.maximum-pool-size: 20}")
    private int maximumPoolSize;

    /** 스레드 유지 시간 (기본값: 60초) */
    @Value("${thread.pool.keep-alive-time: 60}")
    private long keepAliveTime;

    /** 작업 큐 용량 (기본값: 1000) */
    @Value("${thread.pool.queue.capacity: 1000}")
    private int capacity;

    /**
     * 커스텀 스레드 풀을 생성합니다.
     * 
     * ThreadPoolExecutor를 사용하여 애플리케이션에 최적화된 스레드 풀을 구성합니다.
     * 설정 가능한 파라미터들을 통해 성능과 리소스 사용량을 조절할 수 있습니다.
     * 
     * @return 구성된 Executor 객체
     */
    @Bean
    public Executor threadPool() {
        return new ThreadPoolExecutor(
                corePoolSize,                    // 코어 스레드 수
                maximumPoolSize,                 // 최대 스레드 수
                keepAliveTime,                   // 스레드 유지 시간
                TimeUnit.SECONDS,                // 시간 단위
                new LinkedBlockingQueue<>(capacity),  // 작업 큐
                Executors.defaultThreadFactory(),     // 스레드 팩토리
                new ThreadPoolExecutor.DiscardOldestPolicy()  // 거부 정책
        );
    }

}
