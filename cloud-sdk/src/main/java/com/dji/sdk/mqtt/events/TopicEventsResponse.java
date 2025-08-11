package com.dji.sdk.mqtt.events;

import com.dji.sdk.mqtt.CommonTopicResponse;

/**
 * MQTT 이벤트 토픽 응답 클래스
 * 이벤트 응답에 대한 통합된 토픽 응답 형식을 정의하는 제네릭 클래스
 * 
 * @author sean.zhou
 * @date 2021/11/15
 * @version 0.1
 */
public class TopicEventsResponse<T> extends CommonTopicResponse<T> {

    /** 이벤트 메서드명 */
    private String method;

    @Override
    public String toString() {
        return "TopicEventsResponse{" +
                "tid='" + tid + '\'' +
                ", bid='" + bid + '\'' +
                ", method='" + method + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }

    /**
     * 기본 생성자
     */
    public TopicEventsResponse() {
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
    public TopicEventsResponse<T> setTid(String tid) {
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
    public TopicEventsResponse<T> setBid(String bid) {
        this.bid = bid;
        return this;
    }

    /**
     * 이벤트 메서드명을 반환합니다.
     * @return 이벤트 메서드명
     */
    public String getMethod() {
        return method;
    }

    /**
     * 이벤트 메서드명을 설정합니다.
     * @param method 이벤트 메서드명
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopicEventsResponse<T> setMethod(String method) {
        this.method = method;
        return this;
    }

    /**
     * 이벤트 데이터를 반환합니다.
     * @return 이벤트 데이터
     */
    public T getData() {
        return data;
    }

    /**
     * 이벤트 데이터를 설정합니다.
     * @param data 이벤트 데이터
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopicEventsResponse<T> setData(T data) {
        this.data = data;
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
    public TopicEventsResponse<T> setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}