package com.dji.sdk.mqtt.events;

import com.dji.sdk.mqtt.CommonTopicRequest;

/**
 * MQTT 이벤트 토픽 요청 클래스
 * 이벤트 요청에 대한 통합된 토픽 요청 형식을 정의하는 제네릭 클래스
 * 
 * @author sean
 * @version 1.7
 * @date 2023/5/24
 */
public class TopicEventsRequest<T> extends CommonTopicRequest<T> {

    /** 이벤트 메서드명 */
    private String method;

    /** 게이트웨이 정보 */
    private String gateway;

    /** 이벤트 발생 소스 */
    private String from;

    /** 응답 필요 여부 */
    private boolean needReply;

    /**
     * 기본 생성자
     */
    public TopicEventsRequest() {
    }

    @Override
    public String toString() {
        return "TopicRequestsRequest{" +
                "method='" + method + '\'' +
                ", gateway='" + gateway + '\'' +
                ", from='" + from + '\'' +
                ", needReply=" + needReply +
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
    public TopicEventsRequest<T> setTid(String tid) {
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
    public TopicEventsRequest<T> setBid(String bid) {
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
    public TopicEventsRequest<T> setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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
    public TopicEventsRequest<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 게이트웨이 정보를 반환합니다.
     * @return 게이트웨이 정보
     */
    public String getGateway() {
        return gateway;
    }

    /**
     * 게이트웨이 정보를 설정합니다.
     * @param gateway 게이트웨이 정보
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopicEventsRequest<T> setGateway(String gateway) {
        this.gateway = gateway;
        return this;
    }

    /**
     * 이벤트 발생 소스를 반환합니다.
     * @return 이벤트 발생 소스
     */
    public String getFrom() {
        return from;
    }

    /**
     * 이벤트 발생 소스를 설정합니다.
     * @param from 이벤트 발생 소스
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopicEventsRequest<T> setFrom(String from) {
        this.from = from;
        return this;
    }

    /**
     * 응답 필요 여부를 반환합니다.
     * @return 응답 필요 여부
     */
    public boolean isNeedReply() {
        return needReply;
    }

    /**
     * 응답 필요 여부를 설정합니다.
     * @param needReply 응답 필요 여부
     * @return 현재 객체 (메서드 체이닝 지원)
     */
    public TopicEventsRequest<T> setNeedReply(boolean needReply) {
        this.needReply = needReply;
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
    public TopicEventsRequest<T> setMethod(String method) {
        this.method = method;
        return this;
    }
}
