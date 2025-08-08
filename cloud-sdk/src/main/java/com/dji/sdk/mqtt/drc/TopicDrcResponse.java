package com.dji.sdk.mqtt.drc;

import com.dji.sdk.mqtt.CommonTopicResponse;

/**
 * DRC 토픽 응답 클래스
 * 
 * 이 클래스는 DRC(Direct Remote Control) 토픽 응답의
 * 표준화된 형식을 정의합니다.
 * 
 * 주요 구성 요소:
 * - method: DRC 메서드 이름
 * - tid: 요청 ID (상속)
 * - bid: 비즈니스 ID (상속)
 * - timestamp: 타임스탬프 (상속)
 * - data: 응답 데이터 (상속)
 * 
 * 이 클래스는 CommonTopicResponse를 확장하여
 * DRC 특화 기능을 추가합니다.
 * 
 * @param <T> 응답 데이터의 타입
 * @author sean.zhou
 * @date 2021/11/15
 * @version 0.1
 */
public class TopicDrcResponse<T> extends CommonTopicResponse<T> {

    /**
     * DRC 메서드 이름
     * 
     * DRC 응답의 메서드를 식별하는 이름입니다.
     */
    private String method;

    /**
     * 문자열 표현을 반환합니다.
     * 
     * @return 문자열 표현
     */
    @Override
    public String toString() {
        return "TopicDrcResponse{" +
                "tid='" + tid + '\'' +
                ", bid='" + bid + '\'' +
                ", method='" + method + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }

    /**
     * 기본 생성자
     * 
     * 빈 TopicDrcResponse 인스턴스를 생성합니다.
     */
    public TopicDrcResponse() {
    }

    /**
     * 요청 ID를 반환합니다.
     * 
     * @return 요청 ID
     */
    public String getTid() {
        return tid;
    }

    /**
     * 요청 ID를 설정합니다.
     * 
     * @param tid 설정할 요청 ID
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public TopicDrcResponse<T> setTid(String tid) {
        this.tid = tid;
        return this;
    }

    /**
     * 비즈니스 ID를 반환합니다.
     * 
     * @return 비즈니스 ID
     */
    public String getBid() {
        return bid;
    }

    /**
     * 비즈니스 ID를 설정합니다.
     * 
     * @param bid 설정할 비즈니스 ID
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public TopicDrcResponse<T> setBid(String bid) {
        this.bid = bid;
        return this;
    }

    /**
     * DRC 메서드 이름을 반환합니다.
     * 
     * @return DRC 메서드 이름
     */
    public String getMethod() {
        return method;
    }

    /**
     * DRC 메서드 이름을 설정합니다.
     * 
     * @param method 설정할 DRC 메서드 이름
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public TopicDrcResponse<T> setMethod(String method) {
        this.method = method;
        return this;
    }

    /**
     * 응답 데이터를 반환합니다.
     * 
     * @return 응답 데이터
     */
    public T getData() {
        return data;
    }

    /**
     * 응답 데이터를 설정합니다.
     * 
     * @param data 설정할 응답 데이터
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public TopicDrcResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 타임스탬프를 반환합니다.
     * 
     * @return 타임스탬프
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * 타임스탬프를 설정합니다.
     * 
     * @param timestamp 설정할 타임스탬프
     * @return 현재 인스턴스 (메서드 체이닝을 위해)
     */
    public TopicDrcResponse<T> setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}