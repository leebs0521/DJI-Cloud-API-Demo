package com.dji.sdk.mqtt.status;

import com.dji.sdk.mqtt.CommonTopicRequest;

/**
 * MQTT 상태 토픽 요청 클래스
 * 상태 요청에 대한 통합된 토픽 요청 형식을 정의하는 제네릭 클래스
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/24
 */
public class TopicStatusRequest<T> extends CommonTopicRequest<T> {

    /** 상태 메서드명 */
    private String method;

    /** 상태 데이터 발생 소스 */
    private String from;

    /**
     * 기본 생성자
     */
    public TopicStatusRequest() {
    }

    @Override
    public String toString() {
        return "TopicStatusRequest{" +
                "method='" + method + '\'' +
                ", from='" + from + '\'' +
                ", tid='" + tid + '\'' +
                ", bid='" + bid + '\'' +
                ", timestamp=" + timestamp +
                ", data=" + data +
                '}';
    }

    /**
     * 트랜잭션 ID를 반환합니다.
     * @return 트랜잭션 ID
     */
    public String getTid() {
        return tid;
    }

    /**
     * 트랜잭션 ID를 설정합니다.
     * @param tid 트랜잭션 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopicStatusRequest<T> setTid(String tid) {
        this.tid = tid;
        return this;
    }

    /**
     * 비즈니스 ID를 반환합니다.
     * @return 비즈니스 ID
     */
    public String getBid() {
        return bid;
    }

    /**
     * 비즈니스 ID를 설정합니다.
     * @param bid 비즈니스 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopicStatusRequest<T> setBid(String bid) {
        this.bid = bid;
        return this;
    }

    /**
     * 타임스탬프를 반환합니다.
     * @return 타임스탬프
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * 타임스탬프를 설정합니다.
     * @param timestamp 타임스탬프
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopicStatusRequest<T> setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * 상태 데이터를 반환합니다.
     * @return 상태 데이터
     */
    public T getData() {
        return data;
    }

    /**
     * 상태 데이터를 설정합니다.
     * @param data 상태 데이터
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopicStatusRequest<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 상태 메서드명을 반환합니다.
     * @return 상태 메서드명
     */
    public String getMethod() {
        return method;
    }

    /**
     * 상태 메서드명을 설정합니다.
     * @param method 상태 메서드명
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopicStatusRequest<T> setMethod(String method) {
        this.method = method;
        return this;
    }

    /**
     * 상태 데이터 발생 소스를 반환합니다.
     * @return 상태 데이터 발생 소스
     */
    public String getFrom() {
        return from;
    }

    /**
     * 상태 데이터 발생 소스를 설정합니다.
     * @param from 상태 데이터 발생 소스
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopicStatusRequest<T> setFrom(String from) {
        this.from = from;
        return this;
    }
}
