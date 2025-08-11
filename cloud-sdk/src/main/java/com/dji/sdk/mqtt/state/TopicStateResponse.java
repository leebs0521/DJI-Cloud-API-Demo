package com.dji.sdk.mqtt.state;

import com.dji.sdk.mqtt.CommonTopicResponse;

/**
 * MQTT 상태 토픽 응답 클래스
 * 상태 응답에 대한 통합된 토픽 응답 형식을 정의하는 제네릭 클래스
 * 
 * @author sean
 * @version 1.7
 * @date 2023/10/11
 */
public class TopicStateResponse<T> extends CommonTopicResponse<T> {

    /**
     * 기본 생성자
     */
    public TopicStateResponse() {
    }

    @Override
    public String toString() {
        return "TopicStateResponse{" +
                "tid='" + tid + '\'' +
                ", bid='" + bid + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }

    /**
     * 트랜잭션 ID를 반환합니다.
     * @return 트랜잭션 ID
     */
    @Override
    public String getTid() {
        return super.getTid();
    }

    /**
     * 트랜잭션 ID를 설정합니다.
     * @param tid 트랜잭션 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    @Override
    public TopicStateResponse<T> setTid(String tid) {
        super.setTid(tid);
        return this;
    }

    /**
     * 비즈니스 ID를 반환합니다.
     * @return 비즈니스 ID
     */
    @Override
    public String getBid() {
        return super.getBid();
    }

    /**
     * 비즈니스 ID를 설정합니다.
     * @param bid 비즈니스 ID
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    @Override
    public TopicStateResponse<T> setBid(String bid) {
        super.setBid(bid);
        return this;
    }

    /**
     * 상태 응답 데이터를 반환합니다.
     * @return 상태 응답 데이터
     */
    @Override
    public T getData() {
        return super.getData();
    }

    /**
     * 상태 응답 데이터를 설정합니다.
     * @param data 상태 응답 데이터
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    @Override
    public TopicStateResponse<T> setData(T data) {
        super.setData(data);
        return this;
    }

    /**
     * 타임스탬프를 반환합니다.
     * @return 타임스탬프
     */
    @Override
    public Long getTimestamp() {
        return super.getTimestamp();
    }

    /**
     * 타임스탬프를 설정합니다.
     * @param timestamp 타임스탬프
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    @Override
    public TopicStateResponse<T> setTimestamp(Long timestamp) {
        super.setTimestamp(timestamp);
        return this;
    }
}
