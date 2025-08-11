package com.dji.sdk.mqtt.property;

import com.dji.sdk.mqtt.CommonTopicRequest;

/**
 * MQTT 프로퍼티 설정 토픽 요청 클래스
 * 프로퍼티 설정 요청에 대한 통합된 토픽 요청 형식을 정의하는 제네릭 클래스
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/24
 */
public class TopicPropertySetRequest<T> extends CommonTopicRequest<T> {

    /**
     * 기본 생성자
     */
    public TopicPropertySetRequest() {
    }

    @Override
    public String toString() {
        return "TopicPropertySetRequest{" +
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
    public TopicPropertySetRequest<T> setTid(String tid) {
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
    public TopicPropertySetRequest<T> setBid(String bid) {
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
    public TopicPropertySetRequest<T> setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * 프로퍼티 설정 데이터를 반환합니다.
     * @return 프로퍼티 설정 데이터
     */
    public T getData() {
        return data;
    }

    /**
     * 프로퍼티 설정 데이터를 설정합니다.
     * @param data 프로퍼티 설정 데이터
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopicPropertySetRequest<T> setData(T data) {
        this.data = data;
        return this;
    }

}
